package com.vertafore.test.services.activities;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ActivityCommentPostRequest;
import com.vertafore.test.models.ems.ActivityIdResponse;
import com.vertafore.test.models.ems.SpecificActivityResponse;
import com.vertafore.test.servicewrappers.UseActivityTo;
import com.vertafore.test.util.ActivityUtil;
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
    ActivityIdResponse postedActivity = ActivityUtil.postRandomActivity(AADM_User);
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
}
