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
import org.junit.Assert;
import org.junit.Test;

public class PUT_NotificationsContact extends TokenSuperClass {

  @Test
  public void putNotificationsContactBaselineTest() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    // Post random notification client
    NotificationClientResponse notificationClientResponse =
        NotificationUtil.postRandomNotificationClient(AADM_User);
    String clientId = notificationClientResponse.getClientId();

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
    NotificationContactResponse notificationContactResponse = clientResponse.getContacts().get(0);
    String contactId = notificationContactResponse.getContactId();

    String randomTestSuffix = "test";

    // Update contact information with a suffix
    NotificationContactPutRequest notificationContactPutRequest =
        new NotificationContactPutRequest();
    notificationContactPutRequest.setContactId(contactId);
    notificationContactPutRequest.setContactName(
        notificationContactResponse.getName() + randomTestSuffix);
    notificationContactPutRequest.setTitle(
        notificationContactResponse.getTitle() + randomTestSuffix);
    notificationContactPutRequest.setEmail(
        notificationContactResponse.getEmail() + randomTestSuffix);
    AddressPostRequest addressPostRequest = new AddressPostRequest();
    addressPostRequest.setAddressLine1(
        notificationContactResponse.getAddress1() + randomTestSuffix);
    addressPostRequest.setCity(notificationContactResponse.getCity() + randomTestSuffix);
    notificationContactPutRequest.setContactAddress(addressPostRequest);

    // Update the contact
    ORAN_App.attemptsTo(
        notificationsApi.PUTNotificationsContactOnTheOutboundnotificationserviceController(
            notificationContactPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        notificationsApi.PUTNotificationsContactOnTheOutboundnotificationserviceController(
            notificationContactPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        notificationsApi.PUTNotificationsContactOnTheOutboundnotificationserviceController(
            notificationContactPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PutGenericLoggingResponse putResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PutGenericLoggingResponse.class);

    assertThat(putResponse).isNotNull();

    // Get the contact details again to check if they were updated
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
    for (NotificationContactResponse contact : updatedClientResponse.getContacts()) {
      if (contact.getContactId().equals(contactId)) {
        assertThat(contact.getName())
            .isEqualTo(notificationContactResponse.getName() + randomTestSuffix);
        assertThat(contact.getTitle())
            .isEqualTo(notificationContactResponse.getTitle() + randomTestSuffix);
        assertThat(contact.getEmail())
            .isEqualTo(notificationContactResponse.getEmail() + randomTestSuffix);
        assertThat(contact.getAddress1())
            .isEqualTo(notificationContactResponse.getAddress1() + randomTestSuffix);
        assertThat(contact.getCity())
            .isEqualTo(notificationContactResponse.getCity() + randomTestSuffix);
        return;
      }
    }
    // contact not found in the updated client response. Fail the test
    Assert.fail(
        "Updated contact not found in notification client response after the contact is updated.");
  }

  @Test
  public void putNotificationsContactErrorTest() {

    Actor AADM_User = theActorCalled("AADM_User");

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    // Check passing invalid contact id GUID.
    NotificationContactPutRequest notificationContactPutRequest =
        new NotificationContactPutRequest();
    notificationContactPutRequest.setContactId("00000000-0000-0000-0000-000000000000");

    AADM_User.attemptsTo(
        notificationsApi.PUTNotificationsContactOnTheOutboundnotificationserviceController(
            notificationContactPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString(
        "The ContactId must not equal its default value.", AADM_User);

    // Check without passing GUID.
    notificationContactPutRequest = new NotificationContactPutRequest();
    notificationContactPutRequest.setContactId(null);
    AADM_User.attemptsTo(
        notificationsApi.PUTNotificationsContactOnTheOutboundnotificationserviceController(
            notificationContactPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString("The ContactId field is required.", AADM_User);

    // test sending valid contact id with empty contact name

    // Post random notification client
    NotificationClientResponse notificationClientResponse =
        NotificationUtil.postRandomNotificationClient(AADM_User);
    String clientId = notificationClientResponse.getClientId();

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
    NotificationContactResponse notificationContactResponse = clientResponse.getContacts().get(0);
    String contactId = notificationContactResponse.getContactId();

    // Update contact information with a empty contact name
    notificationContactPutRequest = new NotificationContactPutRequest();
    notificationContactPutRequest.setContactId(contactId);
    notificationContactPutRequest.setContactName("");

    // Update the contact
    AADM_User.attemptsTo(
        notificationsApi.PUTNotificationsContactOnTheOutboundnotificationserviceController(
            notificationContactPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString(
        "The field ContactName must be a string with a minimum length of 1 and a maximum length of 75.",
        AADM_User);
  }
}
