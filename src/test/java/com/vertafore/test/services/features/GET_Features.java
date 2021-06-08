package com.vertafore.test.services.features;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.servicewrappers.UseFeaturesTo;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.thucydides.core.annotations.WithTag;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_Features {
  private List<EMSActor> actors = new ArrayList<>();

  // The three actors used in this test are examples of optional params that can be used when
  // building your actors. The first two are specifically for use with "userContext". The first,
  // "bob", uses the project's default VSSO user. The second, "fred", uses AMS login credentials
  // that are different from the project's default user credentials. In this case it is not
  // necessary to indicate whether it is a native or VSSO login, as it doesn't matter in terms of
  // getting a token. The
  // third, "mary" does not have a context indicated - in this case the project will automatically
  // use the deprecated auth endpoints. "mary" also has a version indicated - if this is not
  // included then by default the
  // project will use the current working version in QA. This will be especially useful if you need
  // to test earlier versions of
  // EMS. Without using a context you can still use the default vsso user, or different credentials.

  @Before
  public void getAnAccessToken() {
    actors.addAll(
        List.of(
            new EMSActor().called("bob").withContext("userContext").withLoginType("vsso"),
            new EMSActor()
                .called("fred")
                .withContext("userContext")
                .withUsername("qaown")
                .withPassword("P@ssw0rd2"),
            new EMSActor().called("mary").withVersion("19R2")));
    OnStage.setTheStage(GetAnAccessToken(actors));
  }

  @Test
  public void featuresReturnsAllFeatures() {
    Actor bob = theActorCalled("bob");
    Actor fred = theActorCalled("fred");

    UseFeaturesTo featuresApi = new UseFeaturesTo();

    bob.attemptsTo((featuresApi.GETFeaturesOnTheFeaturesController()));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    fred.attemptsTo((featuresApi.GETFeaturesOnTheFeaturesController()));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }

  @Test
  @WithTag("19R2")
  public void featuresReturnsAllFeatures19R2() {
    Actor mary = theActorCalled("mary");

    UseFeaturesTo featuresApi = new UseFeaturesTo();

    mary.attemptsTo((featuresApi.GETFeaturesOnTheFeaturesController()));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
