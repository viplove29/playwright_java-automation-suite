package com.vertafore.test.services.outbound_notification_service;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ActivityActionNotificationResponse;
import com.vertafore.test.models.ems.NotificationClientFullInfoResponse;
import com.vertafore.test.servicewrappers.UseNotificationsTo;
import com.vertafore.test.util.NotificationUtil;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.After;
import org.junit.Test;

public class GET_NotificationsActivityActions extends TokenSuperClass {

  String clientId;
  Actor AADM_User = theActorCalled("AADM_User");

  @Test
  public void getNotificationsActivityActionsBaselineTest() {

    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    // Post random client and get client
    NotificationClientFullInfoResponse clientResponse =
        NotificationUtil.postNotificationClientAndGetClientDetails(AADM_User, "3.0");
    clientId = clientResponse.getClientId();
    String recipientId = clientResponse.getRecipients().get(0).getRecipientId();

    ORAN_App.attemptsTo(
        notificationsApi.GETNotificationsActivityActionsOnTheOutboundnotificationserviceController(
            recipientId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        notificationsApi.GETNotificationsActivityActionsOnTheOutboundnotificationserviceController(
            recipientId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        notificationsApi.GETNotificationsActivityActionsOnTheOutboundnotificationserviceController(
            recipientId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<ActivityActionNotificationResponse> actionsResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", ActivityActionNotificationResponse.class);
    assertThat(actionsResponse.size())
        .isEqualTo(clientResponse.getRecipients().get(0).getActivityActions().size());

    // pass empty recipient id
    AADM_User.attemptsTo(
        notificationsApi.GETNotificationsActivityActionsOnTheOutboundnotificationserviceController(
            "", ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    actionsResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", ActivityActionNotificationResponse.class);
    assertThat(actionsResponse.isEmpty()).isFalse();
  }

  @After
  public void tearDown() {
    if (clientId != null) {
      // delete the client.
      NotificationUtil.deleteNotificationsClient(clientId, AADM_User);
      clientId = null;
    }
  }
}
