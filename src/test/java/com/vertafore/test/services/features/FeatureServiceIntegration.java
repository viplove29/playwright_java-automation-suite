package com.vertafore.test.services.features;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

import com.vertafore.test.abilities.Authenticate;
import com.vertafore.test.servicewrappers.UseFeaturesTo;
import com.vertafore.test.tasks.GetEMSAuthToken;
import java.io.IOException;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class FeatureServiceIntegration {
  Actor emsuser =
      Actor.named("emsuser")
          .whoCan(CallAnApi.at("https://botd-q-360iis-1.devop.vertafore.com/ems"))
          .whoCan(Authenticate.with("emsuser", "Password2!"));

  @Before
  public void emsuserCanGetToken() {
    emsuser.attemptsTo(GetEMSAuthToken.forActor());
  }

  @Test
  public void featuresReturnsAllFeatures() throws IOException {
    UseFeaturesTo featuresApi = new UseFeaturesTo();

    emsuser.attemptsTo((featuresApi.gETFeaturesOnTheFeaturesController()));

    SerenityRest.lastResponse().prettyPrint();

    emsuser.should(seeThatResponse(res -> res.statusCode(200)));
  }
}
