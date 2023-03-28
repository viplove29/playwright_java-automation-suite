package com.vertafore.test.services.outbound_notification_service;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseActivityTo;
import com.vertafore.test.servicewrappers.UseNotificationsTo;
import com.vertafore.test.util.ActivityUtil;
import com.vertafore.test.util.CustomerUtil;
import com.vertafore.test.util.Util;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_NotificationsCreate extends TokenSuperClass {

  @Test
  public void postNotificationsCreateBaselineTest() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    // creating Activity
    UseActivityTo activityApi = new UseActivityTo();

    String action = ActivityUtil.getRandomActivityAction(AADM_User);
    ActivityPostRequest activity = new ActivityPostRequest();
    String customerId = CustomerUtil.selectRandomCustomer(AADM_User, "customer").getCustomerId();
    activity.setAction(action);
    activity.setDescription("Description");
    activity.setCustomerId(customerId);
    activity.setEntityId(customerId);

    AADM_User.attemptsTo(activityApi.POSTActivityOnTheActivitiesController(activity, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ActivityIdResponse activityIdResponse =
        LastResponse.received()
            .answeredBy(VADM_Admin)
            .getBody()
            .jsonPath()
            .getObject("", ActivityIdResponse.class);
    String activityId = activityIdResponse.getActivityId();

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    NotificationCreatePostRequest notificationCreatePostRequest =
        new NotificationCreatePostRequest();

    List<NotificationChangePostRequest> changes = new ArrayList<>();
    NotificationChangePostRequest changeRequest = new NotificationChangePostRequest();
    changeRequest.setOnsEntityType("Activity");
    List<KeyValuePairStringString> primaryKeys = new ArrayList<>();
    KeyValuePairStringString keyValuePair = new KeyValuePairStringString();
    keyValuePair.setKey("EntityId");
    keyValuePair.value(customerId);
    primaryKeys.add(keyValuePair);

    keyValuePair = new KeyValuePairStringString();
    keyValuePair.setKey("TranId");
    keyValuePair.value(activityId);
    primaryKeys.add(keyValuePair);

    keyValuePair = new KeyValuePairStringString();
    keyValuePair.setKey("EntityType");
    keyValuePair.value("Customer");
    primaryKeys.add(keyValuePair);

    keyValuePair = new KeyValuePairStringString();
    keyValuePair.setKey("PolId");
    keyValuePair.value("null");
    primaryKeys.add(keyValuePair);

    changeRequest.setPrimaryKeys(primaryKeys);
    changeRequest.setChangeOperation("Updated");
    changes.add(changeRequest);
    notificationCreatePostRequest.setChanges(changes);

    ORAN_App.attemptsTo(
        notificationsApi.POSTNotificationsCreateOnTheOutboundnotificationserviceController(
            notificationCreatePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        notificationsApi.POSTNotificationsCreateOnTheOutboundnotificationserviceController(
            notificationCreatePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        notificationsApi.POSTNotificationsCreateOnTheOutboundnotificationserviceController(
            notificationCreatePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    NotificationCreatedResponse notificationCreatedResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", NotificationCreatedResponse.class);
    // check for empty response
    assertThat(notificationCreatedResponse).isNotNull();
  }

  @Test
  public void postNotificationsCreateErrorTest() {

    Actor AADM_User = theActorCalled("AADM_User");

    // creating Activity
    UseActivityTo activityApi = new UseActivityTo();

    String action = ActivityUtil.getRandomActivityAction(AADM_User);
    ActivityPostRequest activity = new ActivityPostRequest();
    String customerId = CustomerUtil.selectRandomCustomer(AADM_User, "customer").getCustomerId();

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    NotificationCreatePostRequest notificationCreatePostRequest =
        new NotificationCreatePostRequest();

    List<NotificationChangePostRequest> changes = new ArrayList<>();
    NotificationChangePostRequest changeRequest = new NotificationChangePostRequest();
    changeRequest.setOnsEntityType("Customer");
    List<KeyValuePairStringString> primaryKeys = new ArrayList<>();
    KeyValuePairStringString keyValuePair = new KeyValuePairStringString();
    keyValuePair.setKey("CustId");
    keyValuePair.value(customerId);
    primaryKeys.add(keyValuePair);

    changeRequest.setPrimaryKeys(primaryKeys);
    changes.add(changeRequest);
    notificationCreatePostRequest.setChanges(changes);

    // Sending request without Change Operation
    AADM_User.attemptsTo(
        notificationsApi.POSTNotificationsCreateOnTheOutboundnotificationserviceController(
            notificationCreatePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString("The ChangeOperation field is required.", AADM_User);

    // set change operation back and remove entity type
    changeRequest.setChangeOperation("Inserted");

    changes.get(0).setOnsEntityType("");

    AADM_User.attemptsTo(
        notificationsApi.POSTNotificationsCreateOnTheOutboundnotificationserviceController(
            notificationCreatePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString("The ONSEntityType field is required.", AADM_User);

    // setting activity ons entity type needs tranid, polid, entityid and entity type.
    changeRequest.setOnsEntityType("Activity");

    AADM_User.attemptsTo(
        notificationsApi.POSTNotificationsCreateOnTheOutboundnotificationserviceController(
            notificationCreatePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    NotificationCreatedResponse notificationCreatedResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", NotificationCreatedResponse.class);
    assertThat(notificationCreatedResponse.getNotificationsInvalidCount()).isEqualTo(1);
    assertThat(notificationCreatedResponse.getNotificationErrors().get(0))
        .isEqualTo(
            "NotificationType activity must include primary keys: tranid, entityid, entitytype, polid. TranId cannot be null or empty. ");
  }
}
