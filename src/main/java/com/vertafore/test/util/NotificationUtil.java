package com.vertafore.test.util;

import com.vertafore.test.models.ems.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationUtil {

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
    recipientPostRequest.setRecipientName("Test Contact");
    recipientPostRequest.setRecipientEndPointURI(recipientEndPointURI);
    recipientPostRequest.setRecipientVersion(clientVersion);
    recipientPostRequest.setStatus(recipientStatus);

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

    notificationClientPostRequest.setNotificationRecipients(recipientList);

    return notificationClientPostRequest;
  }
}
