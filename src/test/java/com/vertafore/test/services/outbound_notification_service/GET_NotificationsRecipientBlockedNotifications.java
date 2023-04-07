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

public class GET_NotificationsRecipientBlockedNotifications extends TokenSuperClass {

  String clientId;
  Actor AADM_User = theActorCalled("AADM_User");

  @Test
  public void getNotificationsRecipientBlockedNotificationsReturnsBlockedNotifications() {
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

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

    // Check get blocked notifications API call
    VADM_Admin.attemptsTo(
        notificationsApi
            .GETNotificationsRecipientBlockedNotificationsOnTheOutboundnotificationserviceController(
                recipientId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        notificationsApi
            .GETNotificationsRecipientBlockedNotificationsOnTheOutboundnotificationserviceController(
                recipientId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // sometimes it takes to get the blocked notification to be returned in GET call. Hence try it
    // multiple times with delay
    boolean isGetBlockedNotificationsCallSuccess = false;
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
        // wait for a seconds and retry
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      } else {
        isGetBlockedNotificationsCallSuccess = true;
        for (BlockedMessageResponse messageResponse : blockedNotificationsResponse) {
          assertThat(messageResponse.getOnsRecipientId()).isEqualTo(recipientId);
          assertThat(messageResponse.getNumberOfNotificationsBlocked() > 0).isTrue();
        }
        break;
      }
    }

    if (!isGetBlockedNotificationsCallSuccess) {
      Assert.fail(
          "Blocked notification is not returned in GETNotificationsRecipientBlockedNotifications call.");
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
  public void getNotificationsRecipientBlockedNotificationsErrorTest() {

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    // Test blocked notifications call with passing empty recipient Id
    AADM_User.attemptsTo(
        notificationsApi
            .GETNotificationsRecipientBlockedNotificationsOnTheOutboundnotificationserviceController(
                "", ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Test Get blocked notifications call without passing recipient Id
    AADM_User.attemptsTo(
        notificationsApi
            .GETNotificationsRecipientBlockedNotificationsOnTheOutboundnotificationserviceController(
                null, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Test blocked notifications call with passing not valid (GUID) recipient Id
    String recipientId = "4295d91a44d5";

    AADM_User.attemptsTo(
        notificationsApi
            .GETNotificationsRecipientBlockedNotificationsOnTheOutboundnotificationserviceController(
                recipientId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString(
        "The value '" + recipientId + "' is not valid for OnsRecipientId.", AADM_User);

    // Test blocked notifications call with passing invalid recipient Id
    recipientId = "9488f1cc-c59d-c59d-c59d-4295d91a44d5";
    AADM_User.attemptsTo(
        notificationsApi
            .GETNotificationsRecipientBlockedNotificationsOnTheOutboundnotificationserviceController(
                recipientId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
