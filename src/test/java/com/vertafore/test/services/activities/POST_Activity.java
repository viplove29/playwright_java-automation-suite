package com.vertafore.test.services.activities;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ActivityPostRequest;
import com.vertafore.test.servicewrappers.UseActivityTo;
import com.vertafore.test.util.ActivityUtil;
import com.vertafore.test.util.CustomerUtil;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_Activity extends TokenSuperClass {

  @Test
  public void postActivityPostsActivity() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseActivityTo activityApi = new UseActivityTo();

    String action = ActivityUtil.getRandomActivityAction(AADM_User);
    ActivityPostRequest activity = new ActivityPostRequest();
    String customerId = CustomerUtil.selectRandomCustomer(AADM_User, "customer").getCustomerId();
    activity.setAction(action);
    activity.setDescription("Description");
    activity.setCustomerId(customerId);
    activity.setEntityId(customerId);

    AADM_User.attemptsTo(activityApi.POSTActivityOnTheActivitiesController(activity, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ORAN_App.attemptsTo(activityApi.POSTActivityOnTheActivitiesController(activity, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(activityApi.POSTActivityOnTheActivitiesController(activity, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }
}
