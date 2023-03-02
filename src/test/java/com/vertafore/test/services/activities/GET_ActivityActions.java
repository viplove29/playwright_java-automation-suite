package com.vertafore.test.services.activities;

import static net.serenitybdd.screenplay.actors.OnStage.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ActionResponse;
import com.vertafore.test.servicewrappers.UseActivityTo;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_ActivityActions extends TokenSuperClass {

  @Test
  public void ActivityActionsReturnsAllActivityActions() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseActivityTo ActivityAPI = new UseActivityTo();

    ORAN_App.attemptsTo(ActivityAPI.GETActivityActionsOnTheActivitiesController(null, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(ActivityAPI.GETActivityActionsOnTheActivitiesController(null, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(ActivityAPI.GETActivityActionsOnTheActivitiesController(null, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ActionResponse action =
        LastResponse.received()
            .answeredBy(AADM_User)
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
