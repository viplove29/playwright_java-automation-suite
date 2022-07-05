package com.vertafore.test.services.activities;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ActivityIdResponse;
import com.vertafore.test.models.ems.SpecificActivityResponse;
import com.vertafore.test.servicewrappers.UseActivityTo;
import com.vertafore.test.util.ActivityUtil;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class GET_Activity extends TokenSuperClass {

  @Test
  public void getActivityGetsActivity() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    // stage data
    ActivityIdResponse activityPosted = ActivityUtil.postRandomActivity(AADM_User);
    String activityId = activityPosted.getActivityId();

    UseActivityTo activityApi = new UseActivityTo();

    ORAN_App.attemptsTo(activityApi.GETActivityOnTheActivitiesController(activityId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(activityApi.GETActivityOnTheActivitiesController(activityId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(activityApi.GETActivityOnTheActivitiesController(activityId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    SpecificActivityResponse activityResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", SpecificActivityResponse.class);

    assertThat(activityResponse).isNotNull();
    assertThat(activityResponse.getClass().getDeclaredFields().length).isEqualTo(7);
    assertThat(activityResponse.getActivityId()).isEqualTo(activityId);
  }
}
