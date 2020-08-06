package com.vertafore.test.services.underwriters;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.*;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.servicewrappers.UseUnderwritersTo;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.thucydides.core.annotations.WithTag;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_Underwriters {

  private List<EMSActor> actors = new ArrayList<>();

  @Before
  public void getAnAccessToken() {
    actors.addAll(
        List.of(
            new EMSActor().called("bob").withContext("userContext"),
            new EMSActor().called("mary").withVersion("19R2")));
    OnStage.setTheStage(GetAnAccessToken(actors));
  }

  @Test
  @WithTag("19R2")
  public void UnderwritersReturnsAllUnderwriters() {

    Actor mary = theActorCalled("mary");

    UseUnderwritersTo underwritersAPI = new UseUnderwritersTo();

    mary.attemptsTo(underwritersAPI.GETUnderwritersOnTheUnderwritersController(null, "string"));

    mary.should(seeThatResponse("successfully gets underwriters", res -> res.statusCode(200)));

    //    UnderwriterResponse underwriter =
    //        LastResponse.received()
    //            .answeredBy(mary)
    //            .getBody()
    //            .jsonPath()
    //            .getList("", UnderwriterResponse.class)
    //            .get(0);
    //    assertThat(underwriter != null).isTrue();
    //    assertThat(underwriter.getClass().getDeclaredFields().length).isEqualTo(2);
  }
}
