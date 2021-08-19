package com.vertafore.test.services.conversations_sms;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.ConversationCountResponse;
import com.vertafore.test.servicewrappers.UseSmsTo;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_SMSConversationsCount {

  private List<EMSActor> actors = new ArrayList<>();

  @Before
  public void getAnAccessToken() {
    actors.addAll(
        List.of(
            new EMSActor().called("bob").withContext("userContext"),
            new EMSActor().called("doug").withContext("appContext"),
            new EMSActor().called("adam").withContext("adminContext")));
    OnStage.setTheStage(GetAnAccessToken(actors));
  }

  /* Smoke test that checks for correct response codes for the GET sms/conversations/count endpoint,
  as well as validates the correct number of fields is being returned and that the count is not null.
   */
  @Test
  public void smsConversationCountReturnsSmsConversationCount() {

    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    UseSmsTo conversationsAPI = new UseSmsTo();

    adam.attemptsTo(conversationsAPI.GETSmsConversationsCountOnTheConversationssmsController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    doug.attemptsTo(conversationsAPI.GETSmsConversationsCountOnTheConversationssmsController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    bob.attemptsTo(conversationsAPI.GETSmsConversationsCountOnTheConversationssmsController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    SerenityRest.lastResponse().prettyPrint();

    ConversationCountResponse conversationCountResponse =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getObject("", ConversationCountResponse.class);

    assertThat(conversationCountResponse.getClass().getDeclaredFields().length).isEqualTo(1);
    assertThat(conversationCountResponse.getCount()).isNotNull();
  }
}
