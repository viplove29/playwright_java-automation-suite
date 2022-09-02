package com.vertafore.test.services.activities;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ActivityIdResponse;
import com.vertafore.test.models.ems.BasicPolicyInfoResponse;
import com.vertafore.test.models.ems.PolicyActivityPostRequest;
import com.vertafore.test.models.ems.SpecificActivityResponse;
import com.vertafore.test.servicewrappers.UseActivityTo;
import com.vertafore.test.util.ActivityUtil;
import com.vertafore.test.util.PolicyUtil;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_ActivityPolicy extends TokenSuperClass {

  @Test
  public void postActivityPolicyPostsActivityToPolicy() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    // Get a random policy to post activity to
    BasicPolicyInfoResponse randomPolicy = PolicyUtil.selectRandomPolicy(AADM_User, "policy");
    String randomCustomerId = randomPolicy.getCustomerId();
    String randomPolicyId = randomPolicy.getPolicyId();

    String action = ActivityUtil.getRandomActivityAction(AADM_User);
    String comment = "POST Activity Policy test";

    // create and populate request model
    PolicyActivityPostRequest activityPostRequest = new PolicyActivityPostRequest();
    activityPostRequest.setAction(action);
    activityPostRequest.setComment(comment);
    activityPostRequest.setPolicyId(randomPolicyId);
    activityPostRequest.setCustomerId(randomCustomerId);

    UseActivityTo activityApi = new UseActivityTo();

    // send requests
    VADM_Admin.attemptsTo(
        activityApi.POSTActivityPolicyOnTheActivitiesController(activityPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        activityApi.POSTActivityPolicyOnTheActivitiesController(activityPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        activityApi.POSTActivityPolicyOnTheActivitiesController(activityPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // validate the response and that the activity was posted
    ActivityIdResponse idResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", ActivityIdResponse.class);

    AADM_User.attemptsTo(
        activityApi.GETActivityOnTheActivitiesController(idResponse.getActivityId(), ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    SpecificActivityResponse activityResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", SpecificActivityResponse.class);

    assertThat(activityResponse).isNotNull();
    assertThat(activityResponse.getEntityId()).isEqualTo(randomCustomerId);
    assertThat(activityResponse.getPolicyId()).isEqualTo(randomPolicyId);
    assertThat(activityResponse.getActivityComment()).isEqualTo(comment);
  }
}
