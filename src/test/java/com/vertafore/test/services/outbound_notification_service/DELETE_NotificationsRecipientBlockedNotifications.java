package com.vertafore.test.services.outbound_notification_service;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseCustomerTo;
import com.vertafore.test.servicewrappers.UseNotificationsTo;
import com.vertafore.test.util.CustomerUtil;
import com.vertafore.test.util.NotificationUtil;
import com.vertafore.test.util.Util;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class DELETE_NotificationsRecipientBlockedNotifications extends TokenSuperClass {

  String clientId;
  Actor AADM_User = theActorCalled("AADM_User");

  @Test
  public void deleteNotificationsRecipientBlockedNotificationsDeletesTheBlockedNotifications() {

    UseNotificationsTo notificationsApi = new UseNotificationsTo();
    UseCustomerTo customerAPI = new UseCustomerTo();

    List<String> notificationTypes = new ArrayList<>();
    notificationTypes.add("Customer");
    List<String> activityActionTypes = new ArrayList<>();

    String endpointURI = "invalidURL";
    String clientName = NotificationUtil.getRandomStringAppendedClientName("Client V3");
    // Testing it only for V3 client since this API only returns blocked notifications for V3
    NotificationClientPostRequest notificationClientPostRequest =
        NotificationUtil.getPostNotificationClientRequest(
            "3.0", clientName + "1", endpointURI, "On", notificationTypes, activityActionTypes);

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

    clientId = notificationClientResponse.getClientId();

    // Get the client based on client id.
    AADM_User.attemptsTo(
        notificationsApi.GETNotificationsClientOnTheOutboundnotificationserviceController(
            clientId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    NotificationClientFullInfoResponse clientFullResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", NotificationClientFullInfoResponse.class);
    String recipientId = clientFullResponse.getRecipients().get(0).getRecipientId();
    NotificationRecipientResponse recipient = clientFullResponse.getRecipients().get(0);

    // post a customer to create a ONS notification
    CustomerBasicInfoPostRequest customerBody =
        CustomerUtil.createBasicCustomer("Customer", "Family", AADM_User);

    AADM_User.attemptsTo(
        customerAPI.POSTCustomerBasicInfoOnTheCustomersController(customerBody, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode())
        .as(SerenityRest.lastResponse().toString())
        .isEqualTo(200);

    CustomerIdResponse customerResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", CustomerIdResponse.class);

    // It might take some time to get the ONS notification generated. Hence, keep trying the GET
    // call with 1 sec. delay
    boolean getBlockedNotificationsCallSuccess = false;
    for (int i = 0; i < 15; i++) {
      AADM_User.attemptsTo(
          notificationsApi
              .GETNotificationsRecipientBlockedNotificationsOnTheOutboundnotificationserviceController(
                  recipientId, ""));
      assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

      List<BlockedMessageResponse> blockedNotificationsResponse =
          LastResponse.received()
              .answeredBy(AADM_User)
              .getBody()
              .jsonPath()
              .getList("", BlockedMessageResponse.class);
      assertThat(blockedNotificationsResponse).isNotNull();
      if (!blockedNotificationsResponse.isEmpty()) {
        getBlockedNotificationsCallSuccess = true;
        for (BlockedMessageResponse messageResponse : blockedNotificationsResponse) {
          assertThat(messageResponse.getOnsRecipientId()).isEqualTo(recipientId);
          assertThat(messageResponse.getNumberOfNotificationsBlocked() > 0).isTrue();
        }
        break;
      } else {
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }
    if (!getBlockedNotificationsCallSuccess) {
      Assert.fail(
          "Blocked notification was not found even after setting the NS Client with wrong url for the recipient");
    }

    // Delete notifications recipient blocked notifications
    AADM_User.attemptsTo(
        notificationsApi
            .DELETENotificationsRecipientBlockedNotificationsOnTheOutboundnotificationserviceController(
                recipientId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    DeleteGenericResponse deleteGenericResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", DeleteGenericResponse.class);

    assertThat(deleteGenericResponse).isNotNull();
    assertThat(deleteGenericResponse.getNumberOfRecordsDeleted()).isGreaterThanOrEqualTo(1);

    // checking for blocked notifications again to make sure that the previous blocked notifications
    // are unblocked
    // It might take some time to get the ONS notification generated. Hence, keep trying the GET
    // call with 1 sec. delay
    getBlockedNotificationsCallSuccess = false;
    for (int i = 0; i < 15; i++) {
      AADM_User.attemptsTo(
          notificationsApi
              .GETNotificationsRecipientBlockedNotificationsOnTheOutboundnotificationserviceController(
                  recipientId, ""));
      assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

      List<BlockedMessageResponse> blockedNotificationsResponse =
          LastResponse.received()
              .answeredBy(AADM_User)
              .getBody()
              .jsonPath()
              .getList("", BlockedMessageResponse.class);
      assertThat(blockedNotificationsResponse).isNotNull();
      if (blockedNotificationsResponse.isEmpty()) {
        getBlockedNotificationsCallSuccess = true;
        assertThat(blockedNotificationsResponse.size()).isEqualTo(0);
        break;
      } else {
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }
    if (!getBlockedNotificationsCallSuccess) {
      Assert.fail(
          "Blocked notifications are still present even after deleting the blocked notifications.");
    }
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
  public void deleteNotificationsRecipientBlockedNotificationsErrorTest() {

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    // Test blocked notifications recipient call with passing not valid (GUID) recipient Id
    String recipientId = "4295d91a44d5";
    AADM_User.attemptsTo(
        notificationsApi
            .DELETENotificationsRecipientBlockedNotificationsOnTheOutboundnotificationserviceController(
                recipientId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString(
        "The value '"
            + recipientId
            + "' is not valid for RecipientId.; The RecipientId field is required.",
        AADM_User);

    // Test blocked notifications recipient call with passing empty recipient Id
    AADM_User.attemptsTo(
        notificationsApi
            .DELETENotificationsRecipientBlockedNotificationsOnTheOutboundnotificationserviceController(
                "", ""));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString("The RecipientId field is required.", AADM_User);

    // Test delete notifications recipient call by passing non existing recipient Id
    // Post notification client and get client details.
    NotificationClientFullInfoResponse clientResponse =
        NotificationUtil.postRandomNotificationClientAndGetClientDetails(AADM_User);

    clientId = clientResponse.getClientId();

    String recipientId1ToDelete = clientResponse.getRecipients().get(0).getRecipientId();

    // call delete recipient
    AADM_User.attemptsTo(
        notificationsApi.DELETENotificationsRecipientOnTheOutboundnotificationserviceController(
            recipientId1ToDelete, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Call POST block notifications with same recipient again.
    AADM_User.attemptsTo(
        notificationsApi
            .DELETENotificationsRecipientBlockedNotificationsOnTheOutboundnotificationserviceController(
                recipientId1ToDelete, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(404);
    Util.validateErrorResponseContainsString(
        "A notification recipient with '" + recipientId1ToDelete + "' could not be found.",
        AADM_User);
  }
}
