package com.vertafore.test.services.outbound_notification_service;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseNotificationsTo;
import com.vertafore.test.util.NotificationUtil;
import com.vertafore.test.util.Util;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.After;
import org.junit.Test;

public class PUT_NotificationsRecipientStatus extends TokenSuperClass {

  String clientId;
  Actor AADM_User = theActorCalled("AADM_User");

  @Test
  public void putNotificationsRecipientStatusBaselineTest() {
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    // Post random notification client
    NotificationClientFullInfoResponse clientResponse =
        NotificationUtil.postRandomNotificationClientAndGetClientDetails(AADM_User);

    NotificationRecipientResponse recipientResponse = clientResponse.getRecipients().get(0);
    String recipientId = recipientResponse.getRecipientId();

    NotificationRecipientStatusPutRequest notificationRecipientStatusPutRequest =
        new NotificationRecipientStatusPutRequest();
    notificationRecipientStatusPutRequest.setRecipientId(recipientId);
    notificationRecipientStatusPutRequest.setStatus("1");

    ORAN_App.attemptsTo(
        notificationsApi.PUTNotificationsRecipientStatusOnTheOutboundnotificationserviceController(
            notificationRecipientStatusPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PutGenericResponse putResponse =
        LastResponse.received()
            .answeredBy(ORAN_App)
            .getBody()
            .jsonPath()
            .getObject("", PutGenericResponse.class);

    assertThat(putResponse).isNotNull();
    assertThat(putResponse.getNumberOfRecordsUpdated()).isEqualTo(1);

    VADM_Admin.attemptsTo(
        notificationsApi.PUTNotificationsRecipientStatusOnTheOutboundnotificationserviceController(
            notificationRecipientStatusPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // update status for AADM_USER
    notificationRecipientStatusPutRequest.setStatus("2");

    AADM_User.attemptsTo(
        notificationsApi.PUTNotificationsRecipientStatusOnTheOutboundnotificationserviceController(
            notificationRecipientStatusPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    putResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PutGenericResponse.class);

    assertThat(putResponse).isNotNull();
    assertThat(putResponse.getNumberOfRecordsUpdated()).isEqualTo(1);
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
  public void putNotificationsRecipientStatusErrorTest() {

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    // Send valid RecipientId guid but there is no recipient in db with this id.
    String randomRecipientId = "7407611a-c7e2-4df9-8ea3-7fe52c1fde10";
    NotificationRecipientStatusPutRequest notificationRecipientStatusPutRequest =
        new NotificationRecipientStatusPutRequest();
    notificationRecipientStatusPutRequest.setRecipientId(randomRecipientId);
    notificationRecipientStatusPutRequest.setStatus("1");

    AADM_User.attemptsTo(
        notificationsApi.PUTNotificationsRecipientStatusOnTheOutboundnotificationserviceController(
            notificationRecipientStatusPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(404);
    Util.validateErrorResponseContainsString(
        "A notification recipient with Id '" + randomRecipientId + "' could not be found.",
        AADM_User);

    // send valid recipient ID with invalid status
    // Post random notification client
    NotificationClientFullInfoResponse clientResponse =
        NotificationUtil.postRandomNotificationClientAndGetClientDetails(AADM_User);
    String clientId = clientResponse.getClientId();

    NotificationRecipientResponse recipientResponse = clientResponse.getRecipients().get(0);
    String recipientId = recipientResponse.getRecipientId();

    notificationRecipientStatusPutRequest = new NotificationRecipientStatusPutRequest();
    notificationRecipientStatusPutRequest.setRecipientId(recipientId);
    notificationRecipientStatusPutRequest.setStatus("4");

    AADM_User.attemptsTo(
        notificationsApi.PUTNotificationsRecipientStatusOnTheOutboundnotificationserviceController(
            notificationRecipientStatusPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString(
        "The Status must equal one of the following: { 'Off' or '1', 'On' or '2', 'Suspended' or '3' }.",
        AADM_User);
  }
}
