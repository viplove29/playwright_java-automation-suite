package com.vertafore.test.services.outbound_notification_service;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.NotificationTypeResponse;
import com.vertafore.test.servicewrappers.UseNotificationsTo;
import com.vertafore.test.util.Util;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class GET_NotificationsNotificationTypes extends TokenSuperClass {

  @Test
  public void getNotificationsNotificationTypesBaselineTest() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseNotificationsTo notificationsApi = new UseNotificationsTo();
    // Check for 1.0, 2.0, 3.0 versions
    for (int i = 1; i <= 3; i++) {
      ORAN_App.attemptsTo(
          notificationsApi
              .GETNotificationsNotificationTypesOnTheOutboundnotificationserviceController(
                  "" + i, ""));
      assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

      VADM_Admin.attemptsTo(
          notificationsApi
              .GETNotificationsNotificationTypesOnTheOutboundnotificationserviceController(
                  "" + i, ""));
      assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

      AADM_User.attemptsTo(
          notificationsApi
              .GETNotificationsNotificationTypesOnTheOutboundnotificationserviceController(
                  "" + i, ""));
      assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

      List<NotificationTypeResponse> notificationTypeResponse =
          LastResponse.received()
              .answeredBy(AADM_User)
              .getBody()
              .jsonPath()
              .getList("", NotificationTypeResponse.class);
      assertThat(notificationTypeResponse.isEmpty()).isFalse();
    }
    // Check without passing version
    AADM_User.attemptsTo(
        notificationsApi
            .GETNotificationsNotificationTypesOnTheOutboundnotificationserviceController(null, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<NotificationTypeResponse> notificationTypeResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", NotificationTypeResponse.class);
    assertThat(notificationTypeResponse.isEmpty()).isFalse();
  }

  @Test
  public void getNotificationsNotificationTypesErrorTest() {
    Actor AADM_User = theActorCalled("AADM_User");

    UseNotificationsTo notificationsApi = new UseNotificationsTo();
    // Check with wrong version
    AADM_User.attemptsTo(
        notificationsApi
            .GETNotificationsNotificationTypesOnTheOutboundnotificationserviceController(
                "1.2", ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString(
        "The Version must equal one of the following: { '1.0' or '1', '2.0' or '2', '3.0' or '3' }.",
        AADM_User);
  }
}
