package com.vertafore.test.services.outbound_notification_service;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.NotificationClientFullInfoResponse;
import com.vertafore.test.models.ems.NotificationClientPostRequest;
import com.vertafore.test.models.ems.NotificationClientResponse;
import com.vertafore.test.servicewrappers.UseNotificationsTo;
import com.vertafore.test.util.NotificationUtil;
import com.vertafore.test.util.Util;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class GET_NotificationsClient extends TokenSuperClass {

  @Test
  public void getNotificationsClientBaselineTest() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    List<String> notificationTypes = new ArrayList<>();
    notificationTypes.add("Customer");

    List<String> activityActionTypes = new ArrayList<>();

    String clientVersion = "1.0";
    String clientName = NotificationUtil.getRandomStringAppendedClientName("Client V1");
    String endPointURI = "https://botd-nsclient2.devop.vertafore.com/NSLogger";
    String status = "On";

    NotificationClientPostRequest notificationClientPostRequest =
        NotificationUtil.getPostNotificationClientRequest(
            clientVersion, clientName, endPointURI, status, notificationTypes, activityActionTypes);

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
    String clientId = notificationClientResponse.getClientId();

    // Test Get client call for all users
    AADM_User.attemptsTo(
        notificationsApi.GETNotificationsClientOnTheOutboundnotificationserviceController(
            clientId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ORAN_App.attemptsTo(
        notificationsApi.GETNotificationsClientOnTheOutboundnotificationserviceController(
            clientId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    NotificationClientFullInfoResponse clientResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", NotificationClientFullInfoResponse.class);

    assertThat(clientResponse).isNotNull();
    assertThat(clientResponse.getName()).isEqualTo(clientName);
    assertThat(clientResponse.getRecipients().get(0).getVersion()).isEqualTo(clientVersion);
    assertThat(clientResponse.getRecipients().get(0).getEndpointUri()).isEqualTo(endPointURI);
    assertThat(clientResponse.getRecipients().get(0).getServiceStatus().getDescription())
        .isEqualTo(status);

    VADM_Admin.attemptsTo(
        notificationsApi.GETNotificationsClientOnTheOutboundnotificationserviceController(
            clientId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }

  @Test
  public void getNotificationsClientErrorsTest() {

    Actor AADM_User = theActorCalled("AADM_User");

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    // Test Get client call without passing client Id
    AADM_User.attemptsTo(
        notificationsApi.GETNotificationsClientOnTheOutboundnotificationserviceController(
            null, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString(
        "There were missing required fields for the Notification Client Full Info Get Request: { 'ClientId' }",
        AADM_User);

    // Test Get client call with passing not valid (GUID) client Id
    AADM_User.attemptsTo(
        notificationsApi.GETNotificationsClientOnTheOutboundnotificationserviceController(
            "4295d91a44d5", ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString(
        "is not valid for ClientId.; The ClientId field is required.", AADM_User);

    // Test Get client call with passing invalid client Id
    String clientId = "9488f1cc-c59d-c59d-c59d-4295d91a44d5";
    AADM_User.attemptsTo(
        notificationsApi.GETNotificationsClientOnTheOutboundnotificationserviceController(
            clientId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(404);
    Util.validateErrorResponseContainsString(
        "A notification client with Id '" + clientId + "' could not be found", AADM_User);
  }
}
