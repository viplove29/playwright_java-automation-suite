package com.vertafore.test.services.activities;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ActivityIdResponse;
import com.vertafore.test.models.ems.IVANSeDocsActivityPostRequest;
import com.vertafore.test.servicewrappers.UseIvansTo;
import com.vertafore.test.util.ActivityUtil;
import com.vertafore.test.util.CustomerUtil;
import com.vertafore.test.util.Util;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_IvansActivity extends TokenSuperClass {

  @Test
  public void postIvanActivityPostsActivity() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseIvansTo activityApi = new UseIvansTo();

    String action = ActivityUtil.getRandomActivityAction(AADM_User);
    IVANSeDocsActivityPostRequest activity = new IVANSeDocsActivityPostRequest();
    String customerId = CustomerUtil.selectRandomCustomer(AADM_User, "customer").getCustomerId();
    activity.setAction(action);
    activity.setDescription("Description");
    activity.setCustomerId(customerId);

    VADM_Admin.attemptsTo(
        activityApi.POSTIvansEdocsActivityOnTheActivitiesController(activity, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(activityApi.POSTIvansEdocsActivityOnTheActivitiesController(activity, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ORAN_App.attemptsTo(activityApi.POSTIvansEdocsActivityOnTheActivitiesController(activity, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ActivityIdResponse activityIdResponse =
        LastResponse.received()
            .answeredBy(ORAN_App)
            .getBody()
            .jsonPath()
            .getObject("", ActivityIdResponse.class);

    assertThat(activityIdResponse).isNotNull();
    assertThat(Util.isValidGUID(activityIdResponse.getActivityId())).isTrue();
  }

  @Test
  public void postIvanActivityWithoutActionPostsActivity() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseIvansTo activityApi = new UseIvansTo();

    IVANSeDocsActivityPostRequest activity = new IVANSeDocsActivityPostRequest();
    String customerId = CustomerUtil.selectRandomCustomer(AADM_User, "customer").getCustomerId();
    activity.setDescription("Description");
    activity.setCustomerId(customerId);

    VADM_Admin.attemptsTo(
        activityApi.POSTIvansEdocsActivityOnTheActivitiesController(activity, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(activityApi.POSTIvansEdocsActivityOnTheActivitiesController(activity, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ORAN_App.attemptsTo(activityApi.POSTIvansEdocsActivityOnTheActivitiesController(activity, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ActivityIdResponse activityIdResponse =
        LastResponse.received()
            .answeredBy(ORAN_App)
            .getBody()
            .jsonPath()
            .getObject("", ActivityIdResponse.class);

    assertThat(activityIdResponse).isNotNull();
    assertThat(Util.isValidGUID(activityIdResponse.getActivityId())).isTrue();
  }
}
