package com.vertafore.test.services.features;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

import com.vertafore.test.actor.ems.BuildEMSCast;
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

  //  Actor emsuser =
  //      Actor.named("emsuser")
  //          .whoCan(HaveALoginKey.with("userContext"))
  //          .whoCan(HaveAnAccessToken.with())
  //          .whoCan(CallAnApi.at("https://botd-q-360iis-1.devop.vertafore.com/ems"));

  private EMSActor jorj = new EMSActor("jorj", "userContext");

  @Before
  //  public void getAnAccessToken() {
  //    emsuser.attemptsTo(GetAnAccessToken.forActor());
  //  }
  public void setupActors() {
    OnStage.setTheStage(BuildEMSCast.GetAnAccessToken(jorj));
  }

  @Test
  public void featuresReturnsAllFeatures() throws IOException {
    Actor jorj = theActorCalled("jorj");

    UseFeaturesTo featuresApi = new UseFeaturesTo();

    jorj.attemptsTo((featuresApi.gETFeaturesOnTheFeaturesController()));

    SerenityRest.lastResponse().prettyPrint();

    jorj.should(seeThatResponse(res -> res.statusCode(200)));
  }
}
