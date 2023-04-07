package com.vertafore.test.services.outbound_notification_service;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.NotificationClientPostRequest;
import com.vertafore.test.models.ems.NotificationClientResponse;
import com.vertafore.test.servicewrappers.UseNotificationsTo;
import com.vertafore.test.util.NotificationUtil;
import com.vertafore.test.util.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.After;
import org.junit.Test;

public class GET_NotificationsClients extends TokenSuperClass {

  Map<String, String> clientsMap = new HashMap<>();
  Actor AADM_User = theActorCalled("AADM_User");

  @Test
  public void getNotificationsClientsBaseTest() {

    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    for (int i = 1; i <= 3; i++) {

      List<String> notificationTypes = new ArrayList<>();

      String endpointURI = "";

      List<String> activityActionTypes = new ArrayList<>();
      if (i == 3) {
        // only version 3.0 has activity action types
        activityActionTypes.add("Billing");
        activityActionTypes.add("E-Mail Out");

        notificationTypes.add("Customer");
        notificationTypes.add("Activity");

        endpointURI = "https://botd-nsclient1.devop.vertafore.com:8083/notification";
      } else {
        notificationTypes.add("Customer");
        notificationTypes.add("Policy");
        notificationTypes.add("Broker");
        notificationTypes.add("GLGroup");

        endpointURI = "https://botd-nsclient2.devop.vertafore.com/NSLogger";
      }

      String clientName = NotificationUtil.getRandomStringAppendedClientName("Client V" + i);

      NotificationClientPostRequest notificationClientPostRequest =
          NotificationUtil.getPostNotificationClientRequest(
              i + ".0",
              clientName + "1",
              endpointURI,
              "On",
              notificationTypes,
              activityActionTypes);

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
      // check for valid empty client Id
      assertThat(notificationClientResponse.getClientId().isEmpty()).isFalse();
      clientsMap.put(
          notificationClientResponse.getClientId(), notificationClientResponse.getName());
    }

    AADM_User.attemptsTo(
        notificationsApi.GETNotificationsClientsOnTheOutboundnotificationserviceController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ORAN_App.attemptsTo(
        notificationsApi.GETNotificationsClientsOnTheOutboundnotificationserviceController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<NotificationClientResponse> clientsResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", NotificationClientResponse.class);

    assertThat(clientsResponse).isNotNull();
    assertThat(clientsResponse.size() > 0).isTrue();

    Map<String, Boolean> clientsFoundMap = new HashMap<>();
    for (String clientId : clientsMap.keySet()) {
      clientsFoundMap.put(clientId, false);
    }

    // iterate the response and see if the created client id exists
    for (NotificationClientResponse clientResponse : clientsResponse) {
      String clientId = clientResponse.getClientId();
      if (clientsFoundMap.containsKey(clientId)) {
        clientsFoundMap.put(clientId, true);
        // verify the client name
        assertThat(clientResponse.getName()).isEqualTo(clientsMap.get(clientId));
      }
    }

    // verify all the staged clients are returned
    boolean allClientsFound = true;
    for (String clientId : clientsMap.keySet()) {
      allClientsFound = allClientsFound && clientsFoundMap.get(clientId);
    }
    assertThat(allClientsFound).isTrue();

    VADM_Admin.attemptsTo(
        notificationsApi.GETNotificationsClientsOnTheOutboundnotificationserviceController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "The information needed to establish the required context could not be found", VADM_Admin);
  }

  @After
  public void tearDown() {
    if (clientsMap != null) {
      // clean up the test data. Delete the notification client
      for (String clientId : clientsMap.keySet()) {
        NotificationUtil.deleteNotificationsClient(clientId, AADM_User);
      }
      clientsMap = null;
    }
  }
}
