package com.vertafore.test.services.outbound_notification_service;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseActivityTo;
import com.vertafore.test.servicewrappers.UseNotificationsTo;
import com.vertafore.test.util.NotificationUtil;
import com.vertafore.test.util.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class PUT_NotificationsActivityActions extends TokenSuperClass {

  String clientId;
  Actor AADM_User = theActorCalled("AADM_User");

  @Test
  public void putNotificationsActivityActionsUpdatesNotificationsActivityActions() {

    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseNotificationsTo notificationsApi = new UseNotificationsTo();
    UseActivityTo activityAPI = new UseActivityTo();

    // Post version 3.0 client and get client
    NotificationClientFullInfoResponse clientResponse =
        NotificationUtil.postNotificationClientAndGetClientDetails(AADM_User, "3.0");
    clientId = clientResponse.getClientId();
    String recipientId = clientResponse.getRecipients().get(0).getRecipientId();

    // get activity actions
    AADM_User.attemptsTo(activityAPI.GETActivityActionsOnTheActivitiesController(null, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<ActionResponse> actions =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", ActionResponse.class);

    // Get notification activity actions for the recipient
    AADM_User.attemptsTo(
        notificationsApi.GETNotificationsActivityActionsOnTheOutboundnotificationserviceController(
            recipientId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<ActivityActionNotificationResponse> actionsResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", ActivityActionNotificationResponse.class);

    // There are 2 actions being done
    // 1. Set and existing action to false and update.
    // 2. Find 2 new activity actions which are not checked/true for the recipient and add them to
    // the recipient.
    List<ActivityActionNotificationPutRequest> putRequestActions = new ArrayList<>();
    String modifiedOnsActivityActionId = actionsResponse.get(0).getOnsActivityActionId();
    // uncheck the first activity action on the list
    ActivityActionNotificationPutRequest request = new ActivityActionNotificationPutRequest();
    request.setActionId(actionsResponse.get(0).getActionId());
    request.setIsChecked(false);
    request.setOnsRecipientId(actionsResponse.get(0).getOnsRecipientId());
    putRequestActions.add(request);

    // Pick 2 new action ids which are not existing on the recipient to send in PUT call
    List<String> newActionsIds = new ArrayList<>();
    for (ActionResponse action : actions) {
      for (ActivityActionNotificationResponse actionResponse : actionsResponse) {
        if (action.getActionId() != actionResponse.getActionId()
            && !newActionsIds.contains(action.getActionId())) {
          newActionsIds.add(action.getActionId());
          if (newActionsIds.size() == 2) {
            break;
          }
        }
      }
      if (newActionsIds.size() == 2) {
        break;
      }
    }

    request = new ActivityActionNotificationPutRequest();
    request.setActionId(newActionsIds.get(0));
    request.setIsChecked(true);
    request.setOnsRecipientId(actionsResponse.get(0).getOnsRecipientId());
    putRequestActions.add(request);

    request = new ActivityActionNotificationPutRequest();
    request.setActionId(newActionsIds.get(1));
    request.setIsChecked(true);
    request.setOnsRecipientId(actionsResponse.get(0).getOnsRecipientId());
    putRequestActions.add(request);

    ORAN_App.attemptsTo(
        notificationsApi.PUTNotificationsActivityActionsOnTheOutboundnotificationserviceController(
            putRequestActions, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        notificationsApi.PUTNotificationsActivityActionsOnTheOutboundnotificationserviceController(
            putRequestActions, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        notificationsApi.PUTNotificationsActivityActionsOnTheOutboundnotificationserviceController(
            putRequestActions, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<ActivityActionNotificationUpdateResponse> putActionsResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", ActivityActionNotificationUpdateResponse.class);

    // Get Activity actions again for the recipient and verify that the unchecked activity action id
    // is not in the list and the 2 newly added actions are also returned.
    AADM_User.attemptsTo(
        notificationsApi.GETNotificationsActivityActionsOnTheOutboundnotificationserviceController(
            recipientId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    actionsResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", ActivityActionNotificationResponse.class);
    Map<String, Boolean> actionIdsMap = new HashMap<>();
    for (String id : newActionsIds) {
      actionIdsMap.put(id, false);
    }
    for (ActivityActionNotificationResponse action : actionsResponse) {
      if (action.getOnsActivityActionId().equals(modifiedOnsActivityActionId)) {
        Assert.fail(
            "The unchecked action id"
                + modifiedOnsActivityActionId
                + " returned in the get activity actions response.");
      } else {
        if (newActionsIds.contains(action.getActionId())) {
          actionIdsMap.put(action.getActionId(), true);
        }
      }
    }
    for (String id : actionIdsMap.keySet()) {
      if (!actionIdsMap.get(id)) {
        Assert.fail(
            "The new action id " + id + " is not returned in the get activity actions response.");
      }
    }
  }

  @After
  public void tearDown() {
    if (clientId != null) {
      // delete the client.
      NotificationUtil.deleteNotificationsClient(clientId, AADM_User);
      clientId = null;
    }
  }

  @Test
  public void putNotificationsActivityActionsErrorTest() {
    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    // Post version 3.0 client and get client
    NotificationClientFullInfoResponse clientResponse =
        NotificationUtil.postNotificationClientAndGetClientDetails(AADM_User, "3.0");
    clientId = clientResponse.getClientId();
    String recipientId = clientResponse.getRecipients().get(0).getRecipientId();

    // Get activity actions for the recipient
    AADM_User.attemptsTo(
        notificationsApi.GETNotificationsActivityActionsOnTheOutboundnotificationserviceController(
            recipientId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<ActivityActionNotificationResponse> actionsResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", ActivityActionNotificationResponse.class);

    List<ActivityActionNotificationPutRequest> putRequestActions = new ArrayList<>();
    ActivityActionNotificationResponse firstAction = actionsResponse.get(0);

    String randomGUID = "f24a8ea4-9a7c-4ccf-b80b-86be100f9da7";

    // Send random recipient id for which the recipient doesn't exists in database.
    ActivityActionNotificationPutRequest request = new ActivityActionNotificationPutRequest();
    request.setActionId(firstAction.getActionId());
    request.setIsChecked(false);
    request.setOnsRecipientId(randomGUID);
    putRequestActions.add(request);

    AADM_User.attemptsTo(
        notificationsApi.PUTNotificationsActivityActionsOnTheOutboundnotificationserviceController(
            putRequestActions, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(404);
    Util.validateErrorResponseContainsString(
        "OnsRecipientId " + randomGUID + " is invalid", AADM_User);

    // Send random action id for which the action doesn't exists in database.
    putRequestActions.clear();
    request = new ActivityActionNotificationPutRequest();
    request.setActionId(randomGUID);
    request.setIsChecked(false);
    request.setOnsRecipientId(recipientId);
    putRequestActions.add(request);

    AADM_User.attemptsTo(
        notificationsApi.PUTNotificationsActivityActionsOnTheOutboundnotificationserviceController(
            putRequestActions, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(404);
    Util.validateErrorResponseContainsString("ActionId " + randomGUID + " is invalid", AADM_User);

    // Send empty recipient id
    putRequestActions.clear();
    request = new ActivityActionNotificationPutRequest();
    request.setActionId(firstAction.getActionId());
    request.setIsChecked(false);
    request.setOnsRecipientId("");
    putRequestActions.add(request);

    AADM_User.attemptsTo(
        notificationsApi.PUTNotificationsActivityActionsOnTheOutboundnotificationserviceController(
            putRequestActions, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString("The OnsRecipientId field is required.", AADM_User);

    // Send empty action id
    putRequestActions.clear();
    request = new ActivityActionNotificationPutRequest();
    request.setActionId("");
    request.setIsChecked(false);
    request.setOnsRecipientId(recipientId);
    putRequestActions.add(request);

    AADM_User.attemptsTo(
        notificationsApi.PUTNotificationsActivityActionsOnTheOutboundnotificationserviceController(
            putRequestActions, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString("The ActionId field is required.", AADM_User);
  }
}
