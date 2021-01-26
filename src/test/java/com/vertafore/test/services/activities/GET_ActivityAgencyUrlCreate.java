package com.vertafore.test.services.activities;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.ActivityUrlResponse;
import com.vertafore.test.servicewrappers.UseActivityTo;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import net.thucydides.core.annotations.WithTag;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_ActivityAgencyUrlCreate {
  private List<EMSActor> actors = new ArrayList<>();

  @Before
  public void getAnAccessToken() {
    actors.addAll(
        List.of(
            new EMSActor().called("bob").withContext("userContext"),
            new EMSActor().called("doug").withContext("appContext"),
            new EMSActor().called("adam").withContext("adminContext"),
            new EMSActor().called("mary").withContext("userContext").withVersion("19R2")));
    OnStage.setTheStage(GetAnAccessToken(actors));
  }

  @Test
  public void ActivityAgencyUrlCreateReturnsCreatedUrl() {
    String randomEntityId = UUID.randomUUID().toString();
    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    UseActivityTo activityApi = new UseActivityTo();

    bob.attemptsTo(
        activityApi.GETActivityAgencyUrlCreateOnTheActivitiesController(
            "Company", randomEntityId, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    doug.attemptsTo(
        activityApi.GETActivityAgencyUrlCreateOnTheActivitiesController(
            "Employee", randomEntityId, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    adam.attemptsTo(
        activityApi.GETActivityAgencyUrlCreateOnTheActivitiesController(
            "Customer", randomEntityId, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }

  @Test
  @WithTag("19R2")
  public void ActivityAgencyUrlCreate19R2() {
    Actor mary = theActorCalled("mary");
    UseActivityTo activityApi = new UseActivityTo();
    String randomEntityId = UUID.randomUUID().toString();

    mary.attemptsTo(
        activityApi.GETActivityAgencyUrlCreateOnTheActivitiesController(
            "Company", randomEntityId, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    SerenityRest.lastResponse().prettyPrint();
    ActivityUrlResponse urlResponse =
        LastResponse.received()
            .answeredBy(mary)
            .getBody()
            .jsonPath()
            .getObject("", ActivityUrlResponse.class);

    assertThat(urlResponse != null).isTrue();
    assertThat(urlResponse.getClass().getDeclaredFields().length).isEqualTo(1);
    assertThat(
            urlResponse.getAgencyActivityUrl().contains(randomEntityId)
                && urlResponse.getAgencyActivityUrl().contains("Company"))
        .isTrue();
  }
}
