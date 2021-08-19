package com.vertafore.test.services.conversations_sms;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.servicewrappers.UseSmsTo;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_SMSConversations {

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
  /* Smoke test that simply checks for correct response codes for the GET sms/conversations endpoint,
  since our user currently does not have any sms set up in the UI */
  @Test
  public void conversationsReturnsAllConversations() {

    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    UseSmsTo conversationsAPI = new UseSmsTo();

    adam.attemptsTo(
        conversationsAPI.GETSmsConversationsOnTheConversationssmsController(null, "String"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    doug.attemptsTo(
        conversationsAPI.GETSmsConversationsOnTheConversationssmsController(null, "String"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    bob.attemptsTo(
        conversationsAPI.GETSmsConversationsOnTheConversationssmsController(null, "String"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    SerenityRest.lastResponse().prettyPrint();
  }
}
