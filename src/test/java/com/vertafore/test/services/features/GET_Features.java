package com.vertafore.test.services.features;

import static com.vertafore.test.actor.BuildEMSCast.GetAccessTokens;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.FeatureAvailabilityResponse;
import com.vertafore.test.servicewrappers.UseFeaturesTo;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_Features {
  private List<EMSActor> actors = new ArrayList<>();

  /* The suite can automatically get tokens to cover different combinations of keys/logins to use for your tests, but this test is an example of getting a token outside of those parameters. Every actor requires a keyType and a loginPath, but you can use whatever name, username, and password you want - see example of "fred". The project also has a default VSSO user that you can use, you just need to indicate that - see example of "bob. */

  @Before
  public void getAccessTokens() {
    actors.addAll(
        List.of(
            new EMSActor().called("mary").withKeyType("ORAN").withLoginPath("app"),
            new EMSActor().called("adam").withKeyType("VADM").withLoginPath("admin"),
            new EMSActor()
                .called("bob")
                .withKeyType("AADM")
                .withLoginPath("user")
                .withLoginType("vsso"),
            new EMSActor()
                .called("fred")
                .withKeyType("AGNY")
                .withLoginPath("user")
                .withUsername("admin")
                .withPassword("AMS4all!")));
    OnStage.setTheStage(GetAccessTokens(actors));
  }

  /* The purpose of this test is to hit the Get /features endpoint using admin, user, and app context -
   as well as an actor with vsso login and an actor with a custom username + password combination.
   The test validates proper response codes, and then captures a features object used to query through the
  same endpoint again. By doing so the data validations are not hardcoded.
   */
  @Test
  public void featuresReturnsAllFeatures() {
    Actor bob = theActorCalled("bob");
    Actor mary = theActorCalled("mary");
    Actor adam = theActorCalled("adam");
    Actor fred = theActorCalled("fred");

    UseFeaturesTo featuresApi = new UseFeaturesTo();

    fred.attemptsTo((featuresApi.GETFeaturesOnTheFeaturesController()));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    adam.attemptsTo((featuresApi.GETFeaturesOnTheFeaturesController()));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    mary.attemptsTo((featuresApi.GETFeaturesOnTheFeaturesController()));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    bob.attemptsTo((featuresApi.GETFeaturesOnTheFeaturesController()));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    FeatureAvailabilityResponse featureResponse =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getList("", FeatureAvailabilityResponse.class)
            .get(0);

    assertThat(featureResponse.getClass().getDeclaredFields().length).isEqualTo(3);

    String name = featureResponse.getName();
    Boolean agencyEnabled = featureResponse.getAgencyEnabled();
    Boolean globalEnabled = featureResponse.getGlobalEnabled();

    // TODO should this part be its own class?
    adam.attemptsTo(featuresApi.GETFeaturesFeatureNameOnTheFeaturesController(name, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    mary.attemptsTo(featuresApi.GETFeaturesFeatureNameOnTheFeaturesController(name, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    bob.attemptsTo(featuresApi.GETFeaturesFeatureNameOnTheFeaturesController(name, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    FeatureAvailabilityResponse featureCheck =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getObject("", FeatureAvailabilityResponse.class);

    assertThat(featureResponse.getClass().getDeclaredFields().length).isEqualTo(3);

    assertThat(featureCheck.getName()).isEqualTo(name);
    assertThat(featureCheck.getAgencyEnabled()).isEqualTo(agencyEnabled);
    assertThat(featureCheck.getGlobalEnabled()).isEqualTo(globalEnabled);
  }
}
