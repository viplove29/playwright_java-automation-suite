package com.vertafore.test.services.activities;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.ActionResponse;
import com.vertafore.test.servicewrappers.UseActivityTo;
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
public class GET_ActivityActions {

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
  public void ActivityActionsReturnsAllActivityActions() {

    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    UseActivityTo ActivityAPI = new UseActivityTo();

    doug.attemptsTo(ActivityAPI.GETActivityActionsOnTheActivitiesController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    adam.attemptsTo(ActivityAPI.GETActivityActionsOnTheActivitiesController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    bob.attemptsTo(ActivityAPI.GETActivityActionsOnTheActivitiesController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ActionResponse action =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getList("", ActionResponse.class)
            .get(0);

    assertThat(action).isNotNull();
    assertThat(action.getClass().getDeclaredFields().length).isEqualTo(4);
    assertThat(action.getClass().getDeclaredFields()[0].getName()).isEqualTo("actionId");
    assertThat(action.getClass().getDeclaredFields()[1].getName()).isEqualTo("actionName");
    assertThat(action.getClass().getDeclaredFields()[2].getName()).isEqualTo("isPolicyAction");
    assertThat(action.getClass().getDeclaredFields()[3].getName()).isEqualTo("isClaimAction");
  }
}
