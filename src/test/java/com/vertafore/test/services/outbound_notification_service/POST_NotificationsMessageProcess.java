package com.vertafore.test.services.outbound_notification_service;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseNotificationsTo;
import com.vertafore.test.util.EnvVariables;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class POST_NotificationsMessageProcess extends TokenSuperClass {

  @Test
  public void postNotificationsMessageProcessBaselineTest() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    NotificationMessagePostRequest notificationMessagePostRequest =
        new NotificationMessagePostRequest();

    notificationMessagePostRequest.setAgencyId(EnvVariables.AGENCY_NO);
    notificationMessagePostRequest.setNotificationURI(
        "https://botd-nsclient1.devop.vertafore.com:8083/notification");
    notificationMessagePostRequest.setServiceVersion("3.0");

    List<NotificationChangePostRequest> notificationChangeModelList = new ArrayList<>();
    NotificationChangePostRequest changePostRequest = new NotificationChangePostRequest();
    changePostRequest.setOnsEntityType("PolicyContact");
    changePostRequest.setChangeOperation("Updated");

    List<KeyValuePairStringString> primaryKeys = new ArrayList<>();
    KeyValuePairStringString keyValuePair = new KeyValuePairStringString();
    keyValuePair.setKey("testKey");
    keyValuePair.setValue("testValue");
    primaryKeys.add(keyValuePair);

    changePostRequest.setPrimaryKeys(primaryKeys);

    notificationChangeModelList.add(changePostRequest);

    notificationMessagePostRequest.setNotificationChangeModelList(notificationChangeModelList);

    ORAN_App.attemptsTo(
        notificationsApi.POSTNotificationsMessageProcessOnTheOutboundnotificationserviceController(
            notificationMessagePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        notificationsApi.POSTNotificationsMessageProcessOnTheOutboundnotificationserviceController(
            notificationMessagePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        notificationsApi.POSTNotificationsMessageProcessOnTheOutboundnotificationserviceController(
            notificationMessagePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
