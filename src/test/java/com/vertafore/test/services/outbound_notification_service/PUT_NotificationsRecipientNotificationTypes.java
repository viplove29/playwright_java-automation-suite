package com.vertafore.test.services.outbound_notification_service;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseNotificationsTo;
import com.vertafore.test.util.NotificationUtil;
import com.vertafore.test.util.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class PUT_NotificationsRecipientNotificationTypes extends TokenSuperClass {

  String clientId;
  Actor AADM_User = theActorCalled("AADM_User");

  @Test
  public void putNotificationsRecipientNotificationTypesUpdatesNotificationTypes() {

    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    // Post random notification client and get client details
    NotificationClientFullInfoResponse clientResponse =
        NotificationUtil.postRandomNotificationClientAndGetClientDetails(AADM_User);
    clientId = clientResponse.getClientId();
    String clientVersion = clientResponse.getRecipients().get(0).getVersion();

    // get the first recipient and notification types
    NotificationRecipientResponse recipientResponse = clientResponse.getRecipients().get(0);
    String recipientId = recipientResponse.getRecipientId();

    RecipientNotificationTypePutRequest recipientNotificationTypePutRequest =
        new RecipientNotificationTypePutRequest();
    recipientNotificationTypePutRequest.setRecipientId(recipientId);

    List<NotificationTypePutRequest> notificationTypes = new ArrayList<>();

    // Enable all the notification types
    String removedNotificationTypeId =
        recipientResponse.getNotificationTypes().get(0).getNotificationTypeId();
    NotificationTypePutRequest putRequest = new NotificationTypePutRequest();
    putRequest.setNotificationTypeId(removedNotificationTypeId);
    putRequest.setIsEnabled(false);
    notificationTypes.add(putRequest);

    AADM_User.attemptsTo(
        notificationsApi
            .GETNotificationsNotificationTypesOnTheOutboundnotificationserviceController(
                recipientResponse.getVersion(), ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<NotificationTypeResponse> notificationTypeResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", NotificationTypeResponse.class);
    assertThat(notificationTypeResponse.isEmpty()).isFalse();

    // Pick 2 new notification types ids which are not existing on the recipient to send in PUT call
    List<String> newNotificationTypeIds = new ArrayList<>();
    for (NotificationTypeResponse notificationType : notificationTypeResponse) {
      for (NotificationTypeResponse type : recipientResponse.getNotificationTypes()) {
        if (notificationType.getNotificationTypeId() != type.getNotificationTypeId()
            && !newNotificationTypeIds.contains(notificationType.getNotificationTypeId())) {
          newNotificationTypeIds.add(notificationType.getNotificationTypeId());
          if (newNotificationTypeIds.size() == 2) {
            break;
          }
        }
      }
      if (newNotificationTypeIds.size() == 2) {
        break;
      }
    }

    putRequest = new NotificationTypePutRequest();
    putRequest.setNotificationTypeId(newNotificationTypeIds.get(0));
    putRequest.setIsEnabled(true);
    notificationTypes.add(putRequest);

    putRequest = new NotificationTypePutRequest();
    putRequest.setNotificationTypeId(newNotificationTypeIds.get(1));
    putRequest.setIsEnabled(true);
    notificationTypes.add(putRequest);

    recipientNotificationTypePutRequest.setNotificationTypes(notificationTypes);

    ORAN_App.attemptsTo(
        notificationsApi
            .PUTNotificationsRecipientNotificationTypesOnTheOutboundnotificationserviceController(
                recipientNotificationTypePutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        notificationsApi
            .PUTNotificationsRecipientNotificationTypesOnTheOutboundnotificationserviceController(
                recipientNotificationTypePutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        notificationsApi
            .PUTNotificationsRecipientNotificationTypesOnTheOutboundnotificationserviceController(
                recipientNotificationTypePutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PutGenericLoggingResponse putResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PutGenericLoggingResponse.class);
    assertThat(putResponse).isNotNull();

    // Get client
    AADM_User.attemptsTo(
        notificationsApi.GETNotificationsClientOnTheOutboundnotificationserviceController(
            clientId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    NotificationClientFullInfoResponse updatedClientResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", NotificationClientFullInfoResponse.class);

    Map<String, Boolean> actionIdsMap = new HashMap<>();
    for (String id : newNotificationTypeIds) {
      actionIdsMap.put(id, false);
    }
    for (NotificationTypeResponse notificationType :
        updatedClientResponse.getRecipients().get(0).getNotificationTypes()) {
      if (notificationType.getNotificationTypeId().equals(removedNotificationTypeId)) {
        Assert.fail(
            "The unchecked notification type id"
                + removedNotificationTypeId
                + " returned in the get client response.");
      } else {
        if (newNotificationTypeIds.contains(notificationType.getNotificationTypeId())) {
          actionIdsMap.put(notificationType.getNotificationTypeId(), true);
        }
      }
    }
    for (String id : actionIdsMap.keySet()) {
      if (!actionIdsMap.get(id)) {
        Assert.fail(
            "The new notification type id " + id + " is not returned in the get client response.");
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
  public void putNotificationsRecipientNotificationTypesErrorTest() {

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    // Send null recipientId and null notificationTypes
    RecipientNotificationTypePutRequest recipientNotificationTypePutRequest =
        new RecipientNotificationTypePutRequest();
    recipientNotificationTypePutRequest.setRecipientId(null);
    recipientNotificationTypePutRequest.setNotificationTypes(null);

    AADM_User.attemptsTo(
        notificationsApi
            .PUTNotificationsRecipientNotificationTypesOnTheOutboundnotificationserviceController(
                recipientNotificationTypePutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString(
        "The RecipientId field is required.; The NotificationTypes field is required.", AADM_User);

    // Send empty recipientId
    recipientNotificationTypePutRequest = new RecipientNotificationTypePutRequest();
    recipientNotificationTypePutRequest.setRecipientId("");

    AADM_User.attemptsTo(
        notificationsApi
            .PUTNotificationsRecipientNotificationTypesOnTheOutboundnotificationserviceController(
                recipientNotificationTypePutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString("The RecipientId field is required.", AADM_User);

    String defaultRecipientId = "00000000-0000-0000-0000-000000000000";

    // Send default recipientId
    recipientNotificationTypePutRequest = new RecipientNotificationTypePutRequest();
    recipientNotificationTypePutRequest.setRecipientId(defaultRecipientId);

    AADM_User.attemptsTo(
        notificationsApi
            .PUTNotificationsRecipientNotificationTypesOnTheOutboundnotificationserviceController(
                recipientNotificationTypePutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString(
        "The RecipientId must not equal its default value.", AADM_User);

    String randomGUID = "d3f241ac-df1b-42e8-9831-d59b07846f0e";

    // Send random recipientId which does not exists in database
    recipientNotificationTypePutRequest = new RecipientNotificationTypePutRequest();
    recipientNotificationTypePutRequest.setRecipientId(randomGUID);

    List<NotificationTypePutRequest> notificationTypes = new ArrayList<>();
    NotificationTypePutRequest putRequest = new NotificationTypePutRequest();
    putRequest.setNotificationTypeId(randomGUID);
    putRequest.setIsEnabled(true);
    notificationTypes.add(putRequest);
    recipientNotificationTypePutRequest.setNotificationTypes(notificationTypes);

    AADM_User.attemptsTo(
        notificationsApi
            .PUTNotificationsRecipientNotificationTypesOnTheOutboundnotificationserviceController(
                recipientNotificationTypePutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(404);
    Util.validateErrorResponseContainsString(
        "Recipient with Recipient Id '" + randomGUID + "' could not be found.", AADM_User);

    // Post random notification client and get client details
    NotificationClientFullInfoResponse clientResponse =
        NotificationUtil.postRandomNotificationClientAndGetClientDetails(AADM_User);
    clientId = clientResponse.getClientId();

    // get the first recipient and notification types
    NotificationRecipientResponse recipientResponse = clientResponse.getRecipients().get(0);
    String recipientId = recipientResponse.getRecipientId();

    // Set valid recipient id and invalid/random notificationType Id
    recipientNotificationTypePutRequest = new RecipientNotificationTypePutRequest();
    recipientNotificationTypePutRequest.setRecipientId(recipientId);

    notificationTypes = new ArrayList<>();
    putRequest = new NotificationTypePutRequest();
    putRequest.setNotificationTypeId(randomGUID);
    putRequest.setIsEnabled(true);
    notificationTypes.add(putRequest);
    recipientNotificationTypePutRequest.setNotificationTypes(notificationTypes);

    AADM_User.attemptsTo(
        notificationsApi
            .PUTNotificationsRecipientNotificationTypesOnTheOutboundnotificationserviceController(
                recipientNotificationTypePutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(404);
    Util.validateErrorResponseContainsString(
        "Recipient Notification type with Id '"
            + randomGUID
            + "' could not be found. Update failed.",
        AADM_User);
  }

  @Test
  public void
      putNotificationsRecipientNotificationTypesReturnsErrorForNotApplicableNotificationType() {

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    // Post Version 1.0 notification client and get client details
    NotificationClientFullInfoResponse clientResponse =
        NotificationUtil.postNotificationClientAndGetClientDetails(AADM_User, "1.0");
    clientId = clientResponse.getClientId();

    // get the first recipient
    NotificationRecipientResponse recipientResponse = clientResponse.getRecipients().get(0);
    String recipientId = recipientResponse.getRecipientId();

    // Set valid recipient id
    RecipientNotificationTypePutRequest recipientNotificationTypePutRequest =
        new RecipientNotificationTypePutRequest();
    recipientNotificationTypePutRequest.setRecipientId(recipientId);

    List<NotificationTypePutRequest> notificationTypes = new ArrayList<>();
    NotificationTypePutRequest putRequest = new NotificationTypePutRequest();

    // Set Activity Notification Type Id which is only available for version 3.0 client
    List<NotificationTypeResponse> v3NotificationTypes =
        NotificationUtil.getNotificationsNotificationTypes(AADM_User, "3.0");
    // filter for Activity notification type
    v3NotificationTypes =
        v3NotificationTypes
            .stream()
            .filter(notificationType -> notificationType.getName().equals("Activity"))
            .collect(Collectors.toList());

    String activityNotificationTypeId = v3NotificationTypes.get(0).getNotificationTypeId();
    putRequest.setNotificationTypeId(activityNotificationTypeId);
    putRequest.setIsEnabled(true);
    notificationTypes.add(putRequest);
    recipientNotificationTypePutRequest.setNotificationTypes(notificationTypes);

    AADM_User.attemptsTo(
        notificationsApi
            .PUTNotificationsRecipientNotificationTypesOnTheOutboundnotificationserviceController(
                recipientNotificationTypePutRequest, ""));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(404);
    Util.validateErrorResponseContainsString(
        "Recipient Notification type with Id '"
            + activityNotificationTypeId
            + "' could not be found. Update failed.",
        AADM_User);

    // Send 2 valid notifications types and one invalid notification type. It should return error.
    List<NotificationTypeResponse> v1NotificationTypes =
        NotificationUtil.getNotificationsNotificationTypes(AADM_User, "1.0");
    // Check at least 2 notification types are preset since we need 2 notification types to send
    assertThat(v1NotificationTypes.size() >= 2).isTrue();
    String notificationType1 = v1NotificationTypes.get(0).getNotificationTypeId();
    String notificationType2 = v1NotificationTypes.get(1).getNotificationTypeId();

    recipientNotificationTypePutRequest = new RecipientNotificationTypePutRequest();
    recipientNotificationTypePutRequest.setRecipientId(recipientId);

    notificationTypes = new ArrayList<>();
    putRequest = new NotificationTypePutRequest();
    putRequest.setNotificationTypeId(notificationType1);
    putRequest.setIsEnabled(true);
    notificationTypes.add(putRequest);

    putRequest = new NotificationTypePutRequest();
    putRequest.setNotificationTypeId(notificationType2);
    putRequest.setIsEnabled(true);
    notificationTypes.add(putRequest);

    String randomGUID = "d3f241ac-df1b-42e8-9831-d59b07846f0e";
    putRequest = new NotificationTypePutRequest();
    putRequest.setNotificationTypeId(randomGUID);
    putRequest.setIsEnabled(true);
    notificationTypes.add(putRequest);

    recipientNotificationTypePutRequest.setNotificationTypes(notificationTypes);

    AADM_User.attemptsTo(
        notificationsApi
            .PUTNotificationsRecipientNotificationTypesOnTheOutboundnotificationserviceController(
                recipientNotificationTypePutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(404);
    Util.validateErrorResponseContainsString(
        "Recipient Notification type with Id '"
            + randomGUID
            + "' could not be found. Update failed.",
        AADM_User);

    // Since the previous PUT request failed. Check the 2 valid notification types sent are also not
    // updated for the client
    AADM_User.attemptsTo(
        notificationsApi.GETNotificationsClientOnTheOutboundnotificationserviceController(
            clientResponse.getClientId(), ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    NotificationClientFullInfoResponse updatedClientResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", NotificationClientFullInfoResponse.class);
    List<NotificationTypeResponse> updatedNotificationTypes =
        updatedClientResponse.getRecipients().get(0).getNotificationTypes();
    for (NotificationTypeResponse notificationTypeResponse : updatedNotificationTypes) {
      if (notificationTypeResponse.getNotificationTypeId().equals(notificationType1)
          || notificationTypeResponse.getNotificationTypeId().equals(notificationType2)) {
        Assert.fail(
            "One of the notification type got updated even though the PUTNotificationsRecipientNotificationTypes request was failed.");
      }
    }
  }
}
