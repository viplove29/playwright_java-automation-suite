package com.vertafore.test.services.features;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

import com.vertafore.test.actor.ems.AuthorizeEMSActor;
import com.vertafore.test.servicewrappers.UseFeaturesTo;
import java.io.IOException;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class FeatureServiceIntegration {
  Actor bob = Actor.named("bob");
  private AuthorizeEMSActor emsActor = new AuthorizeEMSActor(bob, "userContext");

  @Test
  public void featuresReturnsAllFeatures() throws IOException {
    UseFeaturesTo featuresApi = new UseFeaturesTo();

    bob.attemptsTo((featuresApi.gETFeaturesOnTheFeaturesController()));

    SerenityRest.lastResponse().prettyPrint();

    bob.should(seeThatResponse(res -> res.statusCode(200)));
  }
}
