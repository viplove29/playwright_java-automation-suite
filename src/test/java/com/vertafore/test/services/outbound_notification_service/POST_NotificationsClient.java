package com.vertafore.test.services.outbound_notification_service;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseNotificationsTo;
import com.vertafore.test.util.NotificationUtil;
import com.vertafore.test.util.Util;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_NotificationsClient extends TokenSuperClass {

  @Test
  public void postNotificationsClientBaselineTest() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    for (int i = 1; i <= 3; i++) {

      List<String> notificationTypes = new ArrayList<>();

      String endpointURI = "";

      List<String> activityActionTypes = new ArrayList<>();
      if (i == 3) {
        // only version 3.0 has activity action types
        activityActionTypes.add("Billing");
        activityActionTypes.add("E-Mail Out");

        notificationTypes.add("Customer");
        notificationTypes.add("Activity");

        endpointURI = "https://botd-nsclient1.devop.vertafore.com:8083/notification";
      } else {
        notificationTypes.add("Customer");
        notificationTypes.add("Policy");
        notificationTypes.add("Broker");
        notificationTypes.add("GLGroup");

        endpointURI = "https://botd-nsclient2.devop.vertafore.com/NSLogger";
      }

      String clientName = NotificationUtil.getRandomStringAppendedClientName("Client V" + i);

      NotificationClientPostRequest notificationClientPostRequest =
          NotificationUtil.getPostNotificationClientRequest(
              i + ".0",
              clientName + "1",
              endpointURI,
              "On",
              notificationTypes,
              activityActionTypes);

      AADM_User.attemptsTo(
          notificationsApi.POSTNotificationsClientOnTheOutboundnotificationserviceController(
              notificationClientPostRequest, ""));

      assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

      NotificationClientResponse notificationClientResponse =
          LastResponse.received()
              .answeredBy(AADM_User)
              .getBody()
              .jsonPath()
              .getObject("", NotificationClientResponse.class);
      // check for valid empty client Id
      assertThat(notificationClientResponse.getClientId().isEmpty()).isFalse();

      // update client name before sending another request since the client names are unique
      notificationClientPostRequest.setClientName(clientName + "2");
      ORAN_App.attemptsTo(
          notificationsApi.POSTNotificationsClientOnTheOutboundnotificationserviceController(
              notificationClientPostRequest, ""));
      assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

      notificationClientPostRequest.setClientName(clientName + "3");
      VADM_Admin.attemptsTo(
          notificationsApi.POSTNotificationsClientOnTheOutboundnotificationserviceController(
              notificationClientPostRequest, ""));
      assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

      // check for off status
      notificationClientPostRequest.setClientName(clientName + "4");
      notificationClientPostRequest.getNotificationRecipients().get(0).setStatus("Off");
      AADM_User.attemptsTo(
          notificationsApi.POSTNotificationsClientOnTheOutboundnotificationserviceController(
              notificationClientPostRequest, ""));
      assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

      // check for suspended status
      notificationClientPostRequest.setClientName(clientName + "5");
      notificationClientPostRequest.getNotificationRecipients().get(0).setStatus("Suspended");
      AADM_User.attemptsTo(
          notificationsApi.POSTNotificationsClientOnTheOutboundnotificationserviceController(
              notificationClientPostRequest, ""));
      assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

      // clean up the test data. Delete the notification client
      NotificationUtil.deleteNotificationsClient(
          notificationClientResponse.getClientId(), AADM_User);
    }
  }

  @Test
  public void postNotificationsClientErrorTest() {

    Actor AADM_User = theActorCalled("AADM_User");

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    List<String> notificationTypes = new ArrayList<>();

    String endpointURI = "https://botd-nsclient2.devop.vertafore.com/NSLogger";

    List<String> activityActionTypes = new ArrayList<>();

    notificationTypes.add("Customer");
    notificationTypes.add("Policy");
    notificationTypes.add("Broker");
    notificationTypes.add("GLGroup");

    String clientName = NotificationUtil.getRandomStringAppendedClientName("Client V1");

    NotificationClientPostRequest notificationClientPostRequest =
        NotificationUtil.getPostNotificationClientRequest(
            "1.0", clientName + "1", endpointURI, "On", notificationTypes, activityActionTypes);

    // check for invalid empty status
    notificationClientPostRequest.setClientName(clientName);
    notificationClientPostRequest.getNotificationRecipients().get(0).setStatus("");
    AADM_User.attemptsTo(
        notificationsApi.POSTNotificationsClientOnTheOutboundnotificationserviceController(
            notificationClientPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString("The Status field is required", AADM_User);

    // check for invalid client version
    notificationClientPostRequest.setClientName(clientName + "1");
    notificationClientPostRequest.getNotificationRecipients().get(0).setStatus("On");
    notificationClientPostRequest.getNotificationRecipients().get(0).setRecipientVersion("10.0");
    AADM_User.attemptsTo(
        notificationsApi.POSTNotificationsClientOnTheOutboundnotificationserviceController(
            notificationClientPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString(
        "Notification recipient version is not valid. Valid values are 1.0, 2.0, 3.0.", AADM_User);

    // check for invalid empty client name
    notificationClientPostRequest.setClientName("");
    notificationClientPostRequest.getNotificationRecipients().get(0).setStatus("On");
    notificationClientPostRequest.getNotificationRecipients().get(0).setRecipientVersion("1.0");

    AADM_User.attemptsTo(
        notificationsApi.POSTNotificationsClientOnTheOutboundnotificationserviceController(
            notificationClientPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString("The ClientName field is required.", AADM_User);
  }
}
