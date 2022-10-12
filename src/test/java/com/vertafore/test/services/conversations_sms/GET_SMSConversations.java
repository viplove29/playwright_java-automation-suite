package com.vertafore.test.services.conversations_sms;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.servicewrappers.UseSmsTo;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_SMSConversations extends TokenSuperClass {

  /* This a smoke test that validates the user, app, and admin context return correct response codes
  for the GET sms/conversations endpoint, since our user currently does not have any sms set up in
  the UI */
  @Test
  public void conversationsReturnsAllConversations() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseSmsTo conversationsAPI = new UseSmsTo();

    VADM_Admin.attemptsTo(
        conversationsAPI.GETSmsConversationsOnTheConversationssmsController(null, "String"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        conversationsAPI.GETSmsConversationsOnTheConversationssmsController(null, "String"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);

    AADM_User.attemptsTo(
        conversationsAPI.GETSmsConversationsOnTheConversationssmsController(null, "String"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
