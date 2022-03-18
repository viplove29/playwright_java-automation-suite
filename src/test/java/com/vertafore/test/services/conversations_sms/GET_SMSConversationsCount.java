package com.vertafore.test.services.conversations_sms;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ConversationCountResponse;
import com.vertafore.test.servicewrappers.UseSmsTo;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_SMSConversationsCount extends TokenSuperClass {

  /* This a smoke test that validates the user, app, and admin context return correct response codes
  for the GET sms/conversations/count endpoint, as well as validates the correct number of fields is
  being returned and that the count is not null.*/
  @Test
  public void smsConversationCountReturnsSmsConversationCount() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseSmsTo conversationsAPI = new UseSmsTo();

    VADM_Admin.attemptsTo(
        conversationsAPI.GETSmsConversationsCountOnTheConversationssmsController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(conversationsAPI.GETSmsConversationsCountOnTheConversationssmsController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        conversationsAPI.GETSmsConversationsCountOnTheConversationssmsController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    SerenityRest.lastResponse().prettyPrint();

    ConversationCountResponse conversationCountResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", ConversationCountResponse.class);

    assertThat(conversationCountResponse.getClass().getDeclaredFields().length).isEqualTo(1);
    assertThat(conversationCountResponse.getCount()).isNotNull();
  }
}
