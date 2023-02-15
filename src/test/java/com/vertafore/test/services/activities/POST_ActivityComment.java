package com.vertafore.test.services.activities;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ActivityCommentPostRequest;
import com.vertafore.test.models.ems.ActivityIdResponse;
import com.vertafore.test.models.ems.SpecificActivityResponse;
import com.vertafore.test.servicewrappers.UseActivityTo;
import com.vertafore.test.util.ActivityUtil;
import com.vertafore.test.util.EnvVariables;
import com.vertafore.test.util.Util;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_ActivityComment extends TokenSuperClass {

  @Test
  public void postActivityCommentPostsCommentOnActivity() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseActivityTo activityApi = new UseActivityTo();

    // stage activity to post comment to
    ActivityIdResponse postedActivity = ActivityUtil.postCustomerRandomActivity(AADM_User);
    String postedActivityId = postedActivity.getActivityId();
    String comment = "activity comment";
    String action = ActivityUtil.getRandomActivityAction(AADM_User);

    // fill out post request body
    ActivityCommentPostRequest commentPostRequest = new ActivityCommentPostRequest();
    commentPostRequest.setComment(comment);
    commentPostRequest.setActivityId(postedActivityId);
    commentPostRequest.setAction(action);

    // BASELINE TESTS FOR ENDPOINT
    AADM_User.attemptsTo(
        activityApi.POSTActivityCommentOnTheActivitiesController(commentPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ORAN_App.attemptsTo(
        activityApi.POSTActivityCommentOnTheActivitiesController(commentPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        activityApi.POSTActivityCommentOnTheActivitiesController(commentPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // VALIDATE COMMENT WAS POSTED TWICE
    AADM_User.attemptsTo(activityApi.GETActivityOnTheActivitiesController(postedActivityId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    SpecificActivityResponse activityResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", SpecificActivityResponse.class);

    String totalActivityComment = activityResponse.getActivityComment();
    int activityCommentOccurrences = StringUtils.countMatches(totalActivityComment, comment);
    assertThat(activityCommentOccurrences)
        .isEqualTo(2); // two successes and one failure, the comment should be appended twice.
  }

  @Test
  public void postActivityCommentWriteMaskTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor AADM_NAUser = theActorCalled("AADM_NAUser");
    Actor AADM_CBUUSER = theActorCalled("AADM_CBUUser");
    Actor AADM_PBUUSER = theActorCalled("AADM_PBUUser");
    Actor AADM_EXECUSER = theActorCalled("AADM_EXECUser");
    Actor AADM_PPUser = theActorCalled("AADM_PPUser");
    Actor AADM_SGUser = theActorCalled("AADM_SGUser");

    UseActivityTo activityApi = new UseActivityTo();

    // use the staged customer ID
    String customerId = EnvVariables.READ_WRITE_MASK_CUSTOMER_ID;
    String policyId = EnvVariables.READ_WRITE_MASK_POLICY_ID;

    // stage customer activity to post comment to
    ActivityIdResponse postedActivity =
        ActivityUtil.postCustomerRandomActivity(AADM_User, customerId);
    String postedActivityId = postedActivity.getActivityId();
    String comment = "activity comment";
    String action = ActivityUtil.getRandomActivityAction(AADM_User);

    // fill out post request body
    ActivityCommentPostRequest commentPostRequest = new ActivityCommentPostRequest();
    commentPostRequest.setComment(comment);
    commentPostRequest.setActivityId(postedActivityId);
    commentPostRequest.setAction(action);

    // No access user doesn't have access to customer or policy
    AADM_NAUser.attemptsTo(
        activityApi.POSTActivityCommentOnTheActivitiesController(commentPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No activity was found using the arguments supplied", AADM_NAUser);

    // Customer Business user has read and write access to the customer but no policy access
    AADM_CBUUSER.attemptsTo(
        activityApi.POSTActivityCommentOnTheActivitiesController(commentPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Policy Business Unit user has read access to customer and read and write access to policy
    AADM_PBUUSER.attemptsTo(
        activityApi.POSTActivityCommentOnTheActivitiesController(commentPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Executive user has read and write access to customer but no policy access
    AADM_EXECUSER.attemptsTo(
        activityApi.POSTActivityCommentOnTheActivitiesController(commentPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Policy Personnel user has read access to customer and read and write access to policy
    AADM_PPUser.attemptsTo(
        activityApi.POSTActivityCommentOnTheActivitiesController(commentPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Service Group user has read only access to customer and no access to policy
    AADM_SGUser.attemptsTo(
        activityApi.POSTActivityCommentOnTheActivitiesController(commentPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // stage policy activity to post comment to
    postedActivity = ActivityUtil.postPolicyRandomActivity(AADM_User, customerId, policyId);
    postedActivityId = postedActivity.getActivityId();
    comment = "activity comment";
    action = ActivityUtil.getRandomActivityAction(AADM_User);

    // fill out post request body
    commentPostRequest = new ActivityCommentPostRequest();
    commentPostRequest.setComment(comment);
    commentPostRequest.setActivityId(postedActivityId);
    commentPostRequest.setAction(action);

    // No access user doesn't have access to customer or policy
    AADM_NAUser.attemptsTo(
        activityApi.POSTActivityCommentOnTheActivitiesController(commentPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No activity was found using the arguments supplied", AADM_NAUser);

    // Customer Business user has read and write access to the customer but no policy access
    AADM_CBUUSER.attemptsTo(
        activityApi.POSTActivityCommentOnTheActivitiesController(commentPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No activity was found using the arguments supplied", AADM_NAUser);

    // Policy Business Unit user has read access to customer and read and write access to policy
    AADM_PBUUSER.attemptsTo(
        activityApi.POSTActivityCommentOnTheActivitiesController(commentPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Executive user has read and write access to customer but no policy access
    AADM_EXECUSER.attemptsTo(
        activityApi.POSTActivityCommentOnTheActivitiesController(commentPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No activity was found using the arguments supplied", AADM_EXECUSER);

    // Policy Personnel user has read access to customer and read and write access to policy
    AADM_PPUser.attemptsTo(
        activityApi.POSTActivityCommentOnTheActivitiesController(commentPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Service Group user has read only access to customer and no access to policy
    AADM_SGUser.attemptsTo(
        activityApi.POSTActivityCommentOnTheActivitiesController(commentPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No activity was found using the arguments supplied", AADM_SGUser);
  }
}
