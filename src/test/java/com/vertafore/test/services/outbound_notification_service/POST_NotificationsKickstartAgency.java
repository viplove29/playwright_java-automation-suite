package com.vertafore.test.services.outbound_notification_service;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.NotificationKickstartPostRequest;
import com.vertafore.test.servicewrappers.UseNotificationsTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class POST_NotificationsKickstartAgency extends TokenSuperClass {

  @Test
  public void postNotificationsKickstartAgencyBaselineTest() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    NotificationKickstartPostRequest notificationKickstartPostRequest =
        new NotificationKickstartPostRequest();
    notificationKickstartPostRequest.setNumberOfKicks(1);

    AADM_User.attemptsTo(
        notificationsApi.POSTNotificationsKickstartAgencyOnTheOutboundnotificationserviceController(
            notificationKickstartPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ORAN_App.attemptsTo(
        notificationsApi.POSTNotificationsKickstartAgencyOnTheOutboundnotificationserviceController(
            notificationKickstartPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        notificationsApi.POSTNotificationsKickstartAgencyOnTheOutboundnotificationserviceController(
            notificationKickstartPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }
}
