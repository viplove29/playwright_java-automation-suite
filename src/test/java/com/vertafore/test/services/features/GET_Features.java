package com.vertafore.test.services.features;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.servicewrappers.UseFeaturesTo;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.conditions.Check;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_Features {
  private List<EMSActor> actors = new ArrayList<>();

  // The two actors used in this test are examples of optional params for use with userContext. The
  // first, "bob", uses the project's default VSSO user. The second, "fred", uses AMS log in
  // credentials that are different
  // from the project's
  // default user credentials. In this case it is not necessary to indicate whether it is a native
  // or vsso login, as it
  // doesn't matter in terms of getting a token.

  @Before
  public void getAnAccessToken() {
    actors.addAll(
        List.of(
            new EMSActor().called("bob").withContext("userContext").withLoginType("vsso"),
            new EMSActor()
                .called("fred")
                .withContext("userContext")
                .withUsername("admin")
                .withPassword("AMS4all!")));
    OnStage.setTheStage(GetAnAccessToken(actors));
  }

  @Test
  public void featuresReturnsAllFeatures() {
    Actor bob = theActorCalled("bob");
    Actor fred = theActorCalled("fred");

    UseFeaturesTo featuresApi = new UseFeaturesTo();

    bob.attemptsTo((featuresApi.GETFeaturesOnTheFeaturesController()));

    SerenityRest.lastResponse().prettyPrint();

    bob.should(seeThatResponse(res -> res.statusCode(200)));
    Check.whether(SerenityRest.lastResponse().getStatusCode() == 200);

    fred.attemptsTo((featuresApi.GETFeaturesOnTheFeaturesController()));

    fred.should(seeThatResponse(res -> res.statusCode(200)));
    Check.whether(SerenityRest.lastResponse().getStatusCode() == 200);
  }
}
