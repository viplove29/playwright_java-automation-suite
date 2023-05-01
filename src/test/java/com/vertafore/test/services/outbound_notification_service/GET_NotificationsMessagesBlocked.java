package com.vertafore.test.services.outbound_notification_service;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.servicewrappers.UseNotificationsTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class GET_NotificationsMessagesBlocked extends TokenSuperClass {

  @Test
  public void getNotificationsMessagesBlockedBaselineTest() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    String randomRecipientId = "543f6dc8-c89c-4cf0-a8d6-aa4c5015fdbd";

    AADM_User.attemptsTo(
        notificationsApi.GETNotificationsMessagesBlockedOnTheOutboundnotificationserviceController(
            randomRecipientId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ORAN_App.attemptsTo(
        notificationsApi.GETNotificationsMessagesBlockedOnTheOutboundnotificationserviceController(
            randomRecipientId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        notificationsApi.GETNotificationsMessagesBlockedOnTheOutboundnotificationserviceController(
            randomRecipientId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }
}
