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
import org.junit.Assert;
import org.junit.Test;

public class PUT_NotificationsRecipient extends TokenSuperClass {

  String clientId;
  Actor AADM_User = theActorCalled("AADM_User");

  @Test
  public void putNotificationsRecipientBaselineTest() {

    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    // Post random notification client and get client details
    NotificationClientFullInfoResponse clientResponse =
        NotificationUtil.postRandomNotificationClientAndGetClientDetails(AADM_User);
    String clientId = clientResponse.getClientId();

    // get the first recipient and contact
    NotificationContactResponse contactResponse = clientResponse.getContacts().get(0);
    String contactId = contactResponse.getContactId();
    NotificationRecipientResponse recipientResponse = clientResponse.getRecipients().get(0);
    String recipientId = recipientResponse.getRecipientId();

    String recipientName = "NewRecipientName";
    String endPointURI = "endPointURI";
    String clientVersion = "2.0";
    String status = "Off";
    String authenticationCode = "abc";

    NotificationRecipientPutRequest notificationRecipientPutRequest =
        new NotificationRecipientPutRequest();
    notificationRecipientPutRequest.setRecipientId(recipientId);
    notificationRecipientPutRequest.setRecipientName(recipientName);
    notificationRecipientPutRequest.setStatus(status);
    notificationRecipientPutRequest.setRecipientVersion(clientVersion);
    notificationRecipientPutRequest.setRecipientEndPointURI(endPointURI);
    notificationRecipientPutRequest.setAuthenticationCode(authenticationCode);
    notificationRecipientPutRequest.setPrimaryContactId(contactId);

    ORAN_App.attemptsTo(
        notificationsApi.PUTNotificationsRecipientOnTheOutboundnotificationserviceController(
            notificationRecipientPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        notificationsApi.PUTNotificationsRecipientOnTheOutboundnotificationserviceController(
            notificationRecipientPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        notificationsApi.PUTNotificationsRecipientOnTheOutboundnotificationserviceController(
            notificationRecipientPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PutGenericLoggingResponse putResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PutGenericLoggingResponse.class);

    assertThat(putResponse).isNotNull();
    assertThat(putResponse.getNumberOfRecordsUpdated()).isEqualTo(1);

    // Get the recipient details again to check if they were updated
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
    for (NotificationRecipientResponse recipient : updatedClientResponse.getRecipients()) {
      if (recipient.getRecipientId().equals(recipientId)) {
        assertThat(recipient.getName()).isEqualTo(recipientName);
        assertThat(recipient.getVersion()).isEqualTo(clientVersion);
        assertThat(recipient.getServiceStatus().getDescription()).isEqualTo(status);
        assertThat(recipient.getEndpointUri()).isEqualTo(endPointURI);
        assertThat(recipient.getAuthenticationDetail()).isEqualTo(authenticationCode);
        assertThat(recipient.getPrimaryContactId()).isEqualTo(contactId);
        return;
      }
    }
    // recipient not found in the updated client response. Fail the test
    Assert.fail(
        "Updated recipient not found in notification client response after the recipient is updated.");
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
  public void putNotificationsRecipientErrorTest() {

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    // Check passing invalid recipient id GUID.
    NotificationRecipientPutRequest notificationRecipientPutRequest =
        new NotificationRecipientPutRequest();
    notificationRecipientPutRequest.setRecipientId("00000000-0000-0000-0000-000000000000");
    notificationRecipientPutRequest.setRecipientVersion("2.0");
    notificationRecipientPutRequest.setStatus("Off");
    notificationRecipientPutRequest.setRecipientName("test");
    notificationRecipientPutRequest.setRecipientEndPointURI("test_uri");

    AADM_User.attemptsTo(
        notificationsApi.PUTNotificationsRecipientOnTheOutboundnotificationserviceController(
            notificationRecipientPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString(
        "The RecipientId must not equal its default value.", AADM_User);

    // Check passing null recipient id GUID.
    notificationRecipientPutRequest = new NotificationRecipientPutRequest();
    notificationRecipientPutRequest.setRecipientId(null);

    AADM_User.attemptsTo(
        notificationsApi.PUTNotificationsRecipientOnTheOutboundnotificationserviceController(
            notificationRecipientPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString("The RecipientId field is required.", AADM_User);

    // Check sending invalid ONS version
    NotificationClientFullInfoResponse clientResponse =
        NotificationUtil.postRandomNotificationClientAndGetClientDetails(AADM_User);
    // get the first recipient
    NotificationRecipientResponse recipientResponse = clientResponse.getRecipients().get(0);
    String recipientId = recipientResponse.getRecipientId();
    String contactId = recipientResponse.getPrimaryContactId();

    notificationRecipientPutRequest = new NotificationRecipientPutRequest();
    notificationRecipientPutRequest.setRecipientId(recipientId);
    notificationRecipientPutRequest.setRecipientVersion("15");
    notificationRecipientPutRequest.setStatus("Off");
    notificationRecipientPutRequest.setRecipientName("test");
    notificationRecipientPutRequest.setRecipientEndPointURI("test_uri");
    notificationRecipientPutRequest.setPrimaryContactId(contactId);
    notificationRecipientPutRequest.setBackupContactId(contactId);

    AADM_User.attemptsTo(
        notificationsApi.PUTNotificationsRecipientOnTheOutboundnotificationserviceController(
            notificationRecipientPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString(
        "The RecipientVersion must equal one of the following: { '1.0' or '1', '2.0' or '2', '3.0' or '3' }.",
        AADM_User);

    // Check sending invalid status
    notificationRecipientPutRequest = new NotificationRecipientPutRequest();
    notificationRecipientPutRequest.setRecipientId(recipientId);
    notificationRecipientPutRequest.setRecipientVersion("2.0");
    notificationRecipientPutRequest.setStatus("true");
    notificationRecipientPutRequest.setRecipientName("test");
    notificationRecipientPutRequest.setRecipientEndPointURI("test_uri");
    notificationRecipientPutRequest.setPrimaryContactId(contactId);
    notificationRecipientPutRequest.setBackupContactId(contactId);

    AADM_User.attemptsTo(
        notificationsApi.PUTNotificationsRecipientOnTheOutboundnotificationserviceController(
            notificationRecipientPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString(
        "The Status must equal one of the following: { 'Off' or '1', 'On' or '2', 'Suspended' or '3' }.",
        AADM_User);

    // Check sending empty recipient name
    notificationRecipientPutRequest = new NotificationRecipientPutRequest();
    notificationRecipientPutRequest.setRecipientId(recipientId);
    notificationRecipientPutRequest.setRecipientVersion("2.0");
    notificationRecipientPutRequest.setStatus("Off");
    notificationRecipientPutRequest.setRecipientName("");
    notificationRecipientPutRequest.setRecipientEndPointURI("test_uri");
    notificationRecipientPutRequest.setPrimaryContactId(contactId);
    notificationRecipientPutRequest.setBackupContactId(contactId);

    AADM_User.attemptsTo(
        notificationsApi.PUTNotificationsRecipientOnTheOutboundnotificationserviceController(
            notificationRecipientPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);

    Util.validateErrorResponseContainsString(
        "The RecipientName field is required.; The field RecipientName must be a string with a minimum length of 1 and a maximum length of 150.",
        AADM_User);

    // Check sending empty endpoint URI
    notificationRecipientPutRequest = new NotificationRecipientPutRequest();
    notificationRecipientPutRequest.setRecipientId(recipientId);
    notificationRecipientPutRequest.setRecipientVersion("2.0");
    notificationRecipientPutRequest.setStatus("Off");
    notificationRecipientPutRequest.setRecipientName("NewClientName");
    notificationRecipientPutRequest.setRecipientEndPointURI("");
    notificationRecipientPutRequest.setPrimaryContactId(contactId);
    notificationRecipientPutRequest.setBackupContactId(contactId);

    AADM_User.attemptsTo(
        notificationsApi.PUTNotificationsRecipientOnTheOutboundnotificationserviceController(
            notificationRecipientPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString(
        "The RecipientEndPointURI field is required.; The field RecipientEndPointURI must be a string with a minimum length of 1 and a maximum length of 255.",
        AADM_User);

    // send invalid contact id
    notificationRecipientPutRequest.setRecipientVersion("2.0");
    notificationRecipientPutRequest.setStatus("Off");
    notificationRecipientPutRequest.setRecipientName("NewClientName");
    notificationRecipientPutRequest.setRecipientEndPointURI("endpointURI");
    notificationRecipientPutRequest.setPrimaryContactId("00000000-0000-0000-0000-000000000000");
    notificationRecipientPutRequest.setBackupContactId("00000000-0000-0000-0000-000000000000");
    AADM_User.attemptsTo(
        notificationsApi.PUTNotificationsRecipientOnTheOutboundnotificationserviceController(
            notificationRecipientPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString(
        "The PrimaryContactId must not equal its default value.; The BackupContactId must not equal its default value.",
        AADM_User);

    String randomGUID = "7407611a-c7e2-4df9-8ea3-7fe52c1fde10";
    // Send valid RecipientId guid but there is no recipient in db with this id.
    notificationRecipientPutRequest = new NotificationRecipientPutRequest();
    notificationRecipientPutRequest.setRecipientId(randomGUID);
    notificationRecipientPutRequest.setRecipientVersion("2.0");
    notificationRecipientPutRequest.setStatus("Off");
    notificationRecipientPutRequest.setRecipientName("NewClientName");
    notificationRecipientPutRequest.setRecipientEndPointURI("endpointURI");
    notificationRecipientPutRequest.setPrimaryContactId(contactId);
    notificationRecipientPutRequest.setBackupContactId(contactId);

    AADM_User.attemptsTo(
        notificationsApi.PUTNotificationsRecipientOnTheOutboundnotificationserviceController(
            notificationRecipientPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(404);
    Util.validateErrorResponseContainsString(
        "A notification recipient with Id '" + randomGUID + "' could not be found.", AADM_User);

    // Send random PrimaryContactId
    notificationRecipientPutRequest = new NotificationRecipientPutRequest();
    notificationRecipientPutRequest.setRecipientId(recipientId);
    notificationRecipientPutRequest.setRecipientVersion("2.0");
    notificationRecipientPutRequest.setStatus("Off");
    notificationRecipientPutRequest.setRecipientName("NewClientName");
    notificationRecipientPutRequest.setRecipientEndPointURI("endpointURI");
    notificationRecipientPutRequest.setPrimaryContactId(randomGUID);
    notificationRecipientPutRequest.setBackupContactId(contactId);
    AADM_User.attemptsTo(
        notificationsApi.PUTNotificationsRecipientOnTheOutboundnotificationserviceController(
            notificationRecipientPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(404);
    Util.validateErrorResponseContainsString(
        "A primary contact with Id '" + randomGUID + "' could not be found.", AADM_User);

    // Send random BackupContactId
    notificationRecipientPutRequest = new NotificationRecipientPutRequest();
    notificationRecipientPutRequest.setRecipientId(recipientId);
    notificationRecipientPutRequest.setRecipientVersion("2.0");
    notificationRecipientPutRequest.setStatus("Off");
    notificationRecipientPutRequest.setRecipientName("NewClientName");
    notificationRecipientPutRequest.setRecipientEndPointURI("endpointURI");
    notificationRecipientPutRequest.setPrimaryContactId(contactId);
    notificationRecipientPutRequest.setBackupContactId(randomGUID);

    AADM_User.attemptsTo(
        notificationsApi.PUTNotificationsRecipientOnTheOutboundnotificationserviceController(
            notificationRecipientPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(404);
    Util.validateErrorResponseContainsString(
        "A backup contact with Id '" + randomGUID + "' could not be found.", AADM_User);
  }
}
