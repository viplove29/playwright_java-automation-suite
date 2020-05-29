package com.vertafore.test.services.features;

import static com.vertafore.test.actor.ems.AuthorizeEMSActor.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.servicewrappers.UseFeaturesTo;
import java.io.IOException;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class FeatureServiceIntegration {
  private EMSActor bob = new EMSActor("bob", "userContext");

  @Before
  public void getAnAccessToken() {
    OnStage.setTheStage(GetAnAccessToken(bob));
  }

  @Test
  public void featuresReturnsAllFeatures() throws IOException {
    Actor currentActor = theActorCalled("bob");

    UseFeaturesTo featuresApi = new UseFeaturesTo();

    currentActor.attemptsTo((featuresApi.gETFeaturesOnTheFeaturesController()));

    SerenityRest.lastResponse().prettyPrint();

    currentActor.should(seeThatResponse(res -> res.statusCode(200)));
  }
}
