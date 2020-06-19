package com.vertafore.test.services.underwriters;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.servicewrappers.UseUnderwritersTo;
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
public class GET_Underwriters {

  private List<EMSActor> actors = new ArrayList<>();

  @Before
  public void getAnAccessToken() {
    actors.addAll(List.of(new EMSActor().called("bob").withContext("userContext")));
    OnStage.setTheStage(GetAnAccessToken(actors));
  }

  @Test
  public void UnderwritersReturnsAllUnderwriters() {

    Actor bob = theActorCalled("bob");

    UseUnderwritersTo underwritersAPI = new UseUnderwritersTo();

    bob.attemptsTo(underwritersAPI.GETUnderwritersOnTheUnderwritersController(null, "string"));

    SerenityRest.lastResponse().prettyPrint();

    bob.should(seeThatResponse("successfully gets underwriters", res -> res.statusCode(200)));
  }
}
