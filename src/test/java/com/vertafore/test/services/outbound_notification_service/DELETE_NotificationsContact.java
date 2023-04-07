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

public class DELETE_NotificationsContact extends TokenSuperClass {

  String clientId;
  Actor AADM_User = theActorCalled("AADM_User");

  @Test
  public void deleteNotificationsContactBaselineTest() {

    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    // Post random notification client
    NotificationClientResponse notificationClientResponse =
        NotificationUtil.postRandomNotificationClient(AADM_User);
    clientId = notificationClientResponse.getClientId();

    // Get the client based on client id. This would get the contact details.
    AADM_User.attemptsTo(
        notificationsApi.GETNotificationsClientOnTheOutboundnotificationserviceController(
            clientId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    NotificationClientFullInfoResponse clientResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", NotificationClientFullInfoResponse.class);
    String contactId1 = clientResponse.getContacts().get(0).getContactId();
    String contactId2 = clientResponse.getContacts().get(1).getContactId();

    // delete first contact
    ORAN_App.attemptsTo(
        notificationsApi.DELETENotificationsContactOnTheOutboundnotificationserviceController(
            contactId1, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        notificationsApi.DELETENotificationsContactOnTheOutboundnotificationserviceController(
            contactId1, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // delete second contact
    AADM_User.attemptsTo(
        notificationsApi.DELETENotificationsContactOnTheOutboundnotificationserviceController(
            contactId2, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    DeleteGenericLoggingResponse deleteResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", DeleteGenericLoggingResponse.class);

    assertThat(deleteResponse).isNotNull();
    assertThat(deleteResponse.getNumberOfRecordsDeleted()).isEqualTo(1);

    // get the client response again and check that primary contact and secondary contact ids are
    // set to null since the contact is deleted.
    // Get the client based on client id. This would get the contact details.
    AADM_User.attemptsTo(
        notificationsApi.GETNotificationsClientOnTheOutboundnotificationserviceController(
            clientId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    clientResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", NotificationClientFullInfoResponse.class);
    assertThat(clientResponse.getRecipients().get(0).getPrimaryContactId()).isNull();
    assertThat(clientResponse.getRecipients().get(0).getBackupContactId()).isNull();
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
  public void deleteNotificationsContactErrorTest() {

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    String randomGuid = "da2d51d7-1cee-4e24-f790-7c5d389dbf51";
    // Send random contact GUID
    AADM_User.attemptsTo(
        notificationsApi.DELETENotificationsContactOnTheOutboundnotificationserviceController(
            randomGuid, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    DeleteGenericLoggingResponse deleteResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", DeleteGenericLoggingResponse.class);

    assertThat(deleteResponse).isNotNull();
    assertThat(deleteResponse.getNumberOfRecordsDeleted()).isEqualTo(0);

    // Send empty contact GUID
    AADM_User.attemptsTo(
        notificationsApi.DELETENotificationsContactOnTheOutboundnotificationserviceController(
            "", ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString("The ContactId field is required.", AADM_User);

    // Send null contact GUID
    AADM_User.attemptsTo(
        notificationsApi.DELETENotificationsContactOnTheOutboundnotificationserviceController(
            null, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString(
        "There were missing required fields for the Notification Contact Delete Request: { 'ContactId' }",
        AADM_User);
  }
}
