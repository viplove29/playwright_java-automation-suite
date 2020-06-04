package com.vertafore.test.services.features;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.EMSActorBuilder;
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
  EMSActor bob =
      new EMSActorBuilder()
          .actorName("bob")
          .context("userContext")
          .loginType("vsso")
          .buildEMSActor();

  @Before
  public void getAnAccessToken() {
    OnStage.setTheStage(GetAnAccessToken(bob));
  }

  @Test
  public void featuresReturnsAllFeatures() throws IOException {
    Actor bob = theActorCalled("bob");

    UseFeaturesTo featuresApi = new UseFeaturesTo();

    bob.attemptsTo((featuresApi.gETFeaturesOnTheFeaturesController()));

    SerenityRest.lastResponse().prettyPrint();

    bob.should(seeThatResponse(res -> res.statusCode(200)));
  }
}
