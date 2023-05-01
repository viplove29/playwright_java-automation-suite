package com.vertafore.test.services.features;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.servicewrappers.UseFeaturesTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class GET_FeaturesFeaturesName extends TokenSuperClass {

  @Test
  public void getFeaturesFeaturesNameBaselineTest() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseFeaturesTo featuresApi = new UseFeaturesTo();
    String featureName = "SMS";

    VADM_Admin.attemptsTo(
        featuresApi.GETFeaturesFeatureNameOnTheFeaturesController(featureName, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        (featuresApi.GETFeaturesFeatureNameOnTheFeaturesController(featureName, "")));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        (featuresApi.GETFeaturesFeatureNameOnTheFeaturesController(featureName, "")));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
