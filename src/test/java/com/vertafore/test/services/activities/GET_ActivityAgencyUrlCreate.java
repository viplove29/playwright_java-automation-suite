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
            new EMSActor().called("adam").withContext("adminContext")));
    OnStage.setTheStage(GetAnAccessToken(actors));
  }

  @Test
  public void ActivityAgencyUrlCreateReturnsCreatedUrl() {
    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    String randomEntityId;
    UseActivityTo activityApi = new UseActivityTo();

    // VADM key returns 403
    adam.attemptsTo(
        activityApi.GETActivityAgencyUrlCreateOnTheActivitiesController(
            "Employee", UUID.randomUUID().toString(), "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // ORAN key successful, tests Employee EntityType
    randomEntityId = UUID.randomUUID().toString();
    doug.attemptsTo(
        activityApi.GETActivityAgencyUrlCreateOnTheActivitiesController(
            "Employee", randomEntityId, "string"));

    ActivityUrlResponse urlResponse =
        LastResponse.received()
            .answeredBy(doug)
            .getBody()
            .jsonPath()
            .getObject("", ActivityUrlResponse.class);

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    assertThat(urlResponse != null).isTrue();
    assertThat(urlResponse.getClass().getDeclaredFields().length).isEqualTo(1);
    assertThat(
            urlResponse.getAgencyActivityUrl().contains(randomEntityId)
                && urlResponse.getAgencyActivityUrl().contains("Employee"))
        .isTrue();

    // AADM key successful, tests Company
    randomEntityId = UUID.randomUUID().toString();
    bob.attemptsTo(
        activityApi.GETActivityAgencyUrlCreateOnTheActivitiesController(
            "Company", randomEntityId, "string"));

    urlResponse =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getObject("", ActivityUrlResponse.class);

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    assertThat(urlResponse != null).isTrue();
    assertThat(urlResponse.getClass().getDeclaredFields().length).isEqualTo(1);
    assertThat(
            urlResponse.getAgencyActivityUrl().contains(randomEntityId)
                && urlResponse.getAgencyActivityUrl().contains("Company"))
        .isTrue();

    // and bob again for Customer
    randomEntityId = UUID.randomUUID().toString();
    bob.attemptsTo(
        activityApi.GETActivityAgencyUrlCreateOnTheActivitiesController(
            "Customer", randomEntityId, "string"));

    urlResponse =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getObject("", ActivityUrlResponse.class);

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    assertThat(urlResponse != null).isTrue();
    assertThat(urlResponse.getClass().getDeclaredFields().length).isEqualTo(1);
    assertThat(
            urlResponse.getAgencyActivityUrl().contains(randomEntityId)
                && urlResponse.getAgencyActivityUrl().contains("Customer"))
        .isTrue();
  }
}
