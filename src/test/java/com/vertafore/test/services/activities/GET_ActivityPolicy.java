package com.vertafore.test.services.activities;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ActivityIdResponse;
import com.vertafore.test.models.ems.BasicPolicyInfoResponse;
import com.vertafore.test.models.ems.PolicyActivityPostRequest;
import com.vertafore.test.models.ems.PolicyActivityResponse;
import com.vertafore.test.servicewrappers.UseActivityTo;
import com.vertafore.test.util.ActivityUtil;
import com.vertafore.test.util.PolicyUtil;
import java.util.List;
import java.util.stream.Collectors;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class GET_ActivityPolicy extends TokenSuperClass {

  @Test
  public void getActivityPolicyRetrievesPolicyActivity() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    // STAGE DATA
    BasicPolicyInfoResponse randomPolicy = PolicyUtil.selectRandomPolicy(AADM_User, "policy");
    String randomCustomerId = randomPolicy.getCustomerId();
    String randomPolicyId = randomPolicy.getPolicyId();

    String action = ActivityUtil.getRandomActivityAction(AADM_User);
    String comment = "GET Activity Policy test";

    // create and populate request model
    PolicyActivityPostRequest activityPostRequest = new PolicyActivityPostRequest();
    activityPostRequest.setAction(action);
    activityPostRequest.setComment(comment);
    activityPostRequest.setPolicyId(randomPolicyId);
    activityPostRequest.setCustomerId(randomCustomerId);

    UseActivityTo activityApi = new UseActivityTo();

    // post the activity
    AADM_User.attemptsTo(
        activityApi.POSTActivityPolicyOnTheActivitiesController(activityPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ActivityIdResponse stageDataResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", ActivityIdResponse.class);
    String stageDataActivityId = stageDataResponse.getActivityId();

    // BASELINE TESTS
    VADM_Admin.attemptsTo(
        activityApi.GETActivityPolicyOnTheActivitiesController(randomPolicyId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(activityApi.GETActivityPolicyOnTheActivitiesController(randomPolicyId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        activityApi.GETActivityPolicyOnTheActivitiesController(randomPolicyId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // VALIDATION
    List<PolicyActivityResponse> response =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", PolicyActivityResponse.class);

    assertThat(response).isNotNull();
    assertThat(response.size()).isGreaterThan(0);

    PolicyActivityResponse desiredActivity =
        response
            .stream()
            .filter(activity -> activity.getActivityId().equals(stageDataActivityId))
            .collect(Collectors.toList())
            .get(0);

    assertThat(desiredActivity).isNotNull();
    assertThat(desiredActivity.getPolicyId()).isEqualTo(randomPolicyId);
    assertThat(desiredActivity.getActivityComment()).contains(comment);
  }
}
