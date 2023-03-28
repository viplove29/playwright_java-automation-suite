package com.vertafore.test.services.outbound_notification_service;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseNotificationsTo;
import com.vertafore.test.util.NotificationUtil;
import com.vertafore.test.util.Util;
import java.util.Random;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class PUT_NotificationsClient extends TokenSuperClass {

  @Test
  public void putNotificationsClientBaselineTest() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    // Post random notification client
    NotificationClientResponse notificationClientResponse =
        NotificationUtil.postRandomNotificationClient(AADM_User);
    String clientId = notificationClientResponse.getClientId();

    // update client name
    String clientName = "NewClientName " + new Random().nextInt(1000);
    NotificationClientPutRequest putRequest = new NotificationClientPutRequest();
    putRequest.setClientId(clientId);
    putRequest.setClientName(clientName);

    // Update the client
    ORAN_App.attemptsTo(
        notificationsApi.PUTNotificationsClientOnTheOutboundnotificationserviceController(
            putRequest, ""));
    SerenityRest.lastResponse().prettyPrint();
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        notificationsApi.PUTNotificationsClientOnTheOutboundnotificationserviceController(
            putRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    clientName = "NewClientName " + new Random().nextInt(1000);
    putRequest.setClientName(clientName);

    AADM_User.attemptsTo(
        notificationsApi.PUTNotificationsClientOnTheOutboundnotificationserviceController(
            putRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PutGenericLoggingResponse putResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PutGenericLoggingResponse.class);

    assertThat(putResponse).isNotNull();
    assertThat(putResponse.getNumberOfRecordsUpdated()).isEqualTo(1);

    // Get the client name again to check if it's updated
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
    assertThat(updatedClientResponse.getName()).isEqualTo(clientName);
  }

  @Test
  public void putNotificationsClientErrorTest() {

    Actor AADM_User = theActorCalled("AADM_User");

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    // Check passing default client id GUID.
    NotificationClientPutRequest notificationClientPutRequest = new NotificationClientPutRequest();
    notificationClientPutRequest.setClientId("00000000-0000-0000-0000-000000000000");
    notificationClientPutRequest.setClientName("client_name");

    AADM_User.attemptsTo(
        notificationsApi.PUTNotificationsClientOnTheOutboundnotificationserviceController(
            notificationClientPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString(
        "The ClientId must not equal its default value.", AADM_User);

    // Send random client id which doesn't exists in db.
    String randomClientId = "d3f241ac-df1b-42e8-9831-d59b07846f0e";
    notificationClientPutRequest = new NotificationClientPutRequest();
    notificationClientPutRequest.setClientId(randomClientId);
    notificationClientPutRequest.setClientName("client_name");

    AADM_User.attemptsTo(
        notificationsApi.PUTNotificationsClientOnTheOutboundnotificationserviceController(
            notificationClientPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(404);
    Util.validateErrorResponseContainsString(
        "A notification client with Client Id '" + randomClientId + "' could not be found.",
        AADM_User);

    // Post random notification client
    NotificationClientResponse notificationClientResponse =
        NotificationUtil.postRandomNotificationClient(AADM_User);
    String clientId = notificationClientResponse.getClientId();

    // Set the client name to empty string
    notificationClientPutRequest = new NotificationClientPutRequest();
    notificationClientPutRequest.setClientId(clientId);
    notificationClientPutRequest.setClientName("");

    AADM_User.attemptsTo(
        notificationsApi.PUTNotificationsClientOnTheOutboundnotificationserviceController(
            notificationClientPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString(
        "The field ClientName must be a string with a minimum length of 1 and a maximum length of 150.",
        AADM_User);
    Util.validateErrorResponseContainsString("The ClientName field is required.", AADM_User);

    // test for client name to be unique
    String clientName1 = notificationClientResponse.getName();

    notificationClientResponse = NotificationUtil.postRandomNotificationClient(AADM_User);
    String clientId2 = notificationClientResponse.getClientId();

    // update client2 with client1 name and it should return error
    notificationClientPutRequest = new NotificationClientPutRequest();
    notificationClientPutRequest.setClientId(clientId2);
    notificationClientPutRequest.setClientName(clientName1);

    AADM_User.attemptsTo(
        notificationsApi.PUTNotificationsClientOnTheOutboundnotificationserviceController(
            notificationClientPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString(
        "A notification client with Client Name '" + clientName1 + "' already exists.", AADM_User);
  }
}
