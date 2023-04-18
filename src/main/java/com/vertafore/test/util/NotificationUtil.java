package com.vertafore.test.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseNotificationsTo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class NotificationUtil {

  private static List<String> clientVersions = new ArrayList<>(Arrays.asList("1.0", "2.0", "3.0"));
  private static List<String> clientEndpointsURIs =
      new ArrayList<>(
          Arrays.asList(
              "https://botd-nsclient2.devop.vertafore.com/NSLogger",
              "https://botd-nsclient2.devop.vertafore.com/NSLogger",
              "https://botd-nsclient1.devop.vertafore.com:8083/notification"));

  public static String getRandomStringAppendedClientName(String clientName) {
    return clientName + " " + (int) (Math.random() * 999);
  }

  public static NotificationClientPostRequest getPostNotificationClientRequest(
      String clientVersion,
      String clientName,
      String recipientEndPointURI,
      String recipientStatus,
      List<String> notificationTypes,
      List<String> notificationActionTypes) {
    NotificationClientPostRequest notificationClientPostRequest =
        new NotificationClientPostRequest();
    notificationClientPostRequest.setClientName(clientName);

    List<NotificationContactPostRequest> contactsList = new ArrayList<>();
    NotificationContactPostRequest notificationContact = new NotificationContactPostRequest();
    notificationContact.setContactName("Test Contact");
    notificationContact.setTitle("Test Title");
    notificationContact.setEmail("testnotifications@email.com");
    notificationContact.setBusinessPhoneNumber("+12223334444");
    notificationContact.setMobilePhoneNumber("+12223334444");

    AddressPostRequest address = new AddressPostRequest();
    address.setAddressLine1("1 Annapolis St.");
    address.setCity("Annapolis");
    address.setState("MD");
    notificationContact.setContactAddress(address);

    NotificationContactPostRequest notificationContact1 = new NotificationContactPostRequest();
    notificationContact1.setContactName("Test Contact1");
    notificationContact1.setTitle("Test Title1");
    notificationContact1.setEmail("testnotifications1@email.com");
    notificationContact1.setBusinessPhoneNumber("+12223334444");
    notificationContact1.setMobilePhoneNumber("+12223334444");

    AddressPostRequest address1 = new AddressPostRequest();
    address1.setAddressLine1("12 denver St.");
    address1.setCity("denver");
    address1.setState("CO");
    notificationContact1.setContactAddress(address1);
    contactsList.add(notificationContact);
    contactsList.add(notificationContact1);
    notificationClientPostRequest.setNotificationContacts(contactsList);

    List<NotificationRecipientPostRequest> recipientList = new ArrayList<>();
    NotificationRecipientPostRequest recipientPostRequest = new NotificationRecipientPostRequest();
    recipientPostRequest.setRecipientName("Test Recipient");
    recipientPostRequest.setRecipientEndPointURI(recipientEndPointURI);
    recipientPostRequest.setRecipientVersion(clientVersion);
    recipientPostRequest.setStatus(recipientStatus);
    recipientPostRequest.setPrimaryContactName("Test Contact");
    recipientPostRequest.setBackupContactName("Test Contact1");

    List<NotificationTypePostRequest> notificationTypesList = new ArrayList<>();
    for (String notificationType : notificationTypes) {
      NotificationTypePostRequest notificationTypePostRequest = new NotificationTypePostRequest();
      notificationTypePostRequest.setNotificationTypeName(notificationType);
      notificationTypesList.add(notificationTypePostRequest);
    }
    recipientPostRequest.setNotificationTypes(notificationTypesList);

    List<NotificationActivityActionPostRequest> notificationActivityList = new ArrayList<>();
    for (String activityAction : notificationActionTypes) {
      NotificationActivityActionPostRequest actionRequest =
          new NotificationActivityActionPostRequest();
      actionRequest.setActivityActionName(activityAction);
      notificationActivityList.add(actionRequest);
    }
    recipientPostRequest.setNotificationActivityActions(notificationActivityList);

    recipientList.add(recipientPostRequest);

    // Add one more recipient
    recipientPostRequest = new NotificationRecipientPostRequest();
    recipientPostRequest.setRecipientName("Test Recipient1");
    recipientPostRequest.setRecipientEndPointURI(recipientEndPointURI);
    recipientPostRequest.setRecipientVersion(clientVersion);
    recipientPostRequest.setStatus(recipientStatus);
    recipientPostRequest.setPrimaryContactName("Test Contact");
    recipientPostRequest.setBackupContactName("Test Contact1");
    recipientPostRequest.setNotificationTypes(notificationTypesList);
    recipientPostRequest.setNotificationActivityActions(notificationActivityList);

    recipientList.add(recipientPostRequest);

    notificationClientPostRequest.setNotificationRecipients(recipientList);

    return notificationClientPostRequest;
  }

  public static NotificationClientResponse postRandomNotificationsClient(
      Actor actor, String clientVersion) {
    int clientIndex;
    if (clientVersion.equals("1.0")) {
      clientIndex = 0;
    } else if (clientVersion.equals("2.0")) {
      clientIndex = 1;
    } else if (clientVersion.equals("3.0")) {
      clientIndex = 2;
    } else {
      clientIndex = 0;
    }
    return postNotificationClientByClientIndex(actor, clientIndex);
  }

  public static NotificationClientResponse postRandomNotificationClient(Actor actor) {
    int clientIndex = new Random().nextInt(3);
    return postNotificationClientByClientIndex(actor, clientIndex);
  }

  private static NotificationClientResponse postNotificationClientByClientIndex(
      Actor actor, int clientIndex) {
    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    List<String> notificationTypes = new ArrayList<>();

    List<String> activityActionTypes = new ArrayList<>();

    if (clientIndex == 2) {
      // only version 3.0 has activity action types
      activityActionTypes.add("Billing");
      activityActionTypes.add("E-Mail Out");

      notificationTypes.add("Customer");
      notificationTypes.add("Activity");
    } else {
      notificationTypes.add("Customer");
      notificationTypes.add("Policy");
      notificationTypes.add("Broker");
      notificationTypes.add("GLGroup");
    }

    String endpointURI = clientEndpointsURIs.get(clientIndex);
    String clientName =
        NotificationUtil.getRandomStringAppendedClientName("Client V" + (clientIndex + 1));

    NotificationClientPostRequest notificationClientPostRequest =
        NotificationUtil.getPostNotificationClientRequest(
            clientVersions.get(clientIndex),
            clientName + "1",
            endpointURI,
            "On",
            notificationTypes,
            activityActionTypes);

    actor.attemptsTo(
        notificationsApi.POSTNotificationsClientOnTheOutboundnotificationserviceController(
            notificationClientPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    NotificationClientResponse notificationClientResponse =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", NotificationClientResponse.class);
    return notificationClientResponse;
  }

  public static NotificationClientFullInfoResponse postRandomNotificationClientAndGetClientDetails(
      Actor actor) {
    UseNotificationsTo notificationsApi = new UseNotificationsTo();
    NotificationClientResponse notificationClientResponse = postRandomNotificationClient(actor);
    String clientId = notificationClientResponse.getClientId();

    // Get the client based on client id. This would get the contact details.
    actor.attemptsTo(
        notificationsApi.GETNotificationsClientOnTheOutboundnotificationserviceController(
            clientId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    NotificationClientFullInfoResponse clientResponse =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", NotificationClientFullInfoResponse.class);
    return clientResponse;
  }

  public static NotificationClientFullInfoResponse postNotificationClientAndGetClientDetails(
      Actor actor, String clientVersion) {
    UseNotificationsTo notificationsApi = new UseNotificationsTo();
    NotificationClientResponse notificationClientResponse =
        postRandomNotificationsClient(actor, clientVersion);
    String clientId = notificationClientResponse.getClientId();

    // Get the client based on client id. This would get the contact details.
    actor.attemptsTo(
        notificationsApi.GETNotificationsClientOnTheOutboundnotificationserviceController(
            clientId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    NotificationClientFullInfoResponse clientResponse =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", NotificationClientFullInfoResponse.class);
    return clientResponse;
  }

  public static List<NotificationTypeResponse> getNotificationsNotificationTypes(
      Actor actor, String clientVersion) {
    UseNotificationsTo notificationsApi = new UseNotificationsTo();
    actor.attemptsTo(
        notificationsApi
            .GETNotificationsNotificationTypesOnTheOutboundnotificationserviceController(
                clientVersion, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    List<NotificationTypeResponse> notificationTypes =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getList("", NotificationTypeResponse.class);
    return notificationTypes;
  }

  public static DeleteNotificationClientResponse deleteNotificationsClient(
      String clientId, Actor actor) {
    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    // Delete the client based on client Id
    actor.attemptsTo(
        notificationsApi.DELETENotificationsClientOnTheOutboundnotificationserviceController(
            clientId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    DeleteNotificationClientResponse deletedClientResponse =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", DeleteNotificationClientResponse.class);
    return deletedClientResponse;
  }
}
