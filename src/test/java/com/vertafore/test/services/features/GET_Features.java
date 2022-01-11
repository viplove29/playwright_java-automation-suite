package com.vertafore.test.services.features;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
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

  /* The three additional actors used in this test are examples of optional params that can be used when
  building your actors. The first two are specifically for use with "userContext". The first,
  "bob", uses the project's default VSSO user. The second, "fred", uses AMS login credentials
  that are different from the project's default user credentials. In this case it is not
  necessary to indicate whether it is a native or VSSO login, as it doesn't matter in terms of
  getting a token. The third, "mary" does not have a context indicated - in this case the project
  will automatically use the deprecated auth endpoints. "mary" also has a version indicated - if
  this is not included then by default the project will use the current working version in QA.
  This will be especially useful if you need to test earlier versions of EMS.
  Without using a context you can still use the default vsso user, or different credentials.
   */

  @Before
  public void getAnAccessToken() {
    actors.addAll(
        List.of(
            new EMSActor().called("doug").withContext("appContext"),
            new EMSActor().called("adam").withContext("adminContext"),
            new EMSActor().called("bob").withContext("userContext").withLoginType("vsso"),
            new EMSActor()
                .called("fred")
                .withContext("userContext")
                .withUsername("admin")
                .withPassword("AMS4all!")));
    OnStage.setTheStage(GetAnAccessToken(actors));
  }

  /* The purpose of this test is to hit the Get /features endpoint using admin, user, and app context -
   as well as an actor with vsso login and an actor with a custom username + password combination.
   The test validates proper response codes, and then captures a features object used to query through the
  same endpoint again. By doing so the data validations are not hardcoded.
   */
  @Test
  public void featuresReturnsAllFeatures() {
    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");
    Actor fred = theActorCalled("fred");

    UseFeaturesTo featuresApi = new UseFeaturesTo();

    fred.attemptsTo((featuresApi.GETFeaturesOnTheFeaturesController()));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    adam.attemptsTo((featuresApi.GETFeaturesOnTheFeaturesController()));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    doug.attemptsTo((featuresApi.GETFeaturesOnTheFeaturesController()));
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

    doug.attemptsTo(featuresApi.GETFeaturesFeatureNameOnTheFeaturesController(name, "string"));
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
