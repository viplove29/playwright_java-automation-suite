package com.vertafore.test.services.outbound_notification_service;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ActivityActionNotificationResponse;
import com.vertafore.test.models.ems.DeleteNotificationClientResponse;
import com.vertafore.test.models.ems.NotificationClientFullInfoResponse;
import com.vertafore.test.models.ems.NotificationRecipientResponse;
import com.vertafore.test.servicewrappers.UseNotificationsTo;
import com.vertafore.test.util.NotificationUtil;
import com.vertafore.test.util.Util;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class DELETE_NotificationsClient extends TokenSuperClass {

  @Test
  public void deleteNotificationsClientDeletesTheNotificationClient() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    // Post random notification client
    NotificationClientFullInfoResponse notificationClientResponse1 =
        NotificationUtil.postRandomNotificationClientAndGetClientDetails(AADM_User);
    String clientId1 = notificationClientResponse1.getClientId();

    // delete the client
    AADM_User.attemptsTo(
        notificationsApi.DELETENotificationsClientOnTheOutboundnotificationserviceController(
            clientId1, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    DeleteNotificationClientResponse deleteResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", DeleteNotificationClientResponse.class);

    assertThat(deleteResponse).isNotNull();
    assertThat(deleteResponse.getNumberOfClientsDeleted()).isEqualTo(1);
    assertThat(deleteResponse.getNumberOfRecipientsDeleted())
        .isEqualTo(notificationClientResponse1.getRecipients().size());

    // Get the client based on client id.
    AADM_User.attemptsTo(
        notificationsApi.GETNotificationsClientOnTheOutboundnotificationserviceController(
            clientId1, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(404);
    Util.validateErrorResponseContainsString(
        "A notification client with Id '" + clientId1 + "' could not be found", AADM_User);

    // Post random notification client
    NotificationClientFullInfoResponse notificationClientResponse2 =
        NotificationUtil.postRandomNotificationClientAndGetClientDetails(AADM_User);
    String clientId2 = notificationClientResponse2.getClientId();

    // delete the client
    ORAN_App.attemptsTo(
        notificationsApi.DELETENotificationsClientOnTheOutboundnotificationserviceController(
            clientId2, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        notificationsApi.DELETENotificationsClientOnTheOutboundnotificationserviceController(
            clientId2, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }

  @Test
  public void deleteNotificationsClientDeletesNotificationRecipientActivityActions() {

    Actor AADM_User = theActorCalled("AADM_User");

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    // Post version 3.0 notification client and get client details. Only version 3.0 supports
    // notification actions
    NotificationClientFullInfoResponse clientResponse =
        NotificationUtil.postNotificationClientAndGetClientDetails(AADM_User, "3.0");

    // call delete client
    AADM_User.attemptsTo(
        notificationsApi.DELETENotificationsClientOnTheOutboundnotificationserviceController(
            clientResponse.getClientId(), ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    DeleteNotificationClientResponse deleteResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", DeleteNotificationClientResponse.class);

    assertThat(deleteResponse).isNotNull();
    assertThat(deleteResponse.getNumberOfClientsDeleted()).isEqualTo(1);
    assertThat(deleteResponse.getNumberOfRecipientsDeleted())
        .isEqualTo(clientResponse.getRecipients().size());

    // Get the activity actions for the recipient. Check activity actions are also deleted since the
    // recipient is deleted.
    for (NotificationRecipientResponse recipient : clientResponse.getRecipients()) {
      AADM_User.attemptsTo(
          notificationsApi
              .GETNotificationsActivityActionsOnTheOutboundnotificationserviceController(
                  recipient.getRecipientId(), ""));
      assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

      List<ActivityActionNotificationResponse> activityActions =
          LastResponse.received()
              .answeredBy(AADM_User)
              .getBody()
              .jsonPath()
              .getList("", ActivityActionNotificationResponse.class);
      assertThat(activityActions.size()).isEqualTo(0);
    }
  }

  @Test
  public void deleteNotificationsClientErrorTest() {

    Actor AADM_User = theActorCalled("AADM_User");

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    String randomGuid = "da2d51d7-1cee-4e24-f790-7c5d389dbf51";
    // Send random client GUID
    AADM_User.attemptsTo(
        notificationsApi.DELETENotificationsClientOnTheOutboundnotificationserviceController(
            randomGuid, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(404);
    Util.validateErrorResponseContainsString(
        "A notification client with ID '" + randomGuid + "' could not be found.", AADM_User);

    // Send empty client GUID
    AADM_User.attemptsTo(
        notificationsApi.DELETENotificationsClientOnTheOutboundnotificationserviceController(
            "", ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString("The ClientId field is required.", AADM_User);

    // Send null client GUID
    AADM_User.attemptsTo(
        notificationsApi.DELETENotificationsClientOnTheOutboundnotificationserviceController(
            null, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString(
        "There were missing required fields for the Notification Client Delete Request: { 'ClientId' }",
        AADM_User);
  }
}
