package com.vertafore.test.services.activities;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ActivityPostRequest;
import com.vertafore.test.servicewrappers.UseActivityTo;
import com.vertafore.test.util.ActivityUtil;
import com.vertafore.test.util.CustomerUtil;
import com.vertafore.test.util.EnvVariables;
import com.vertafore.test.util.Util;
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

  @Test
  public void postActivityWriteMaskTest() {
    Actor AADM_NAUser = theActorCalled("AADM_NAUser");
    Actor AADM_CBUUSER = theActorCalled("AADM_CBUUser");
    Actor AADM_PBUUSER = theActorCalled("AADM_PBUUser");
    Actor AADM_EXECUSER = theActorCalled("AADM_EXECUser");
    Actor AADM_PPUser = theActorCalled("AADM_PPUser");
    Actor AADM_SGUser = theActorCalled("AADM_SGUser");

    // use the staged customer Id and policy Id
    String customerId = EnvVariables.READ_WRITE_MASK_CUSTOMER_ID;
    String policyId = EnvVariables.READ_WRITE_MASK_POLICY_ID;

    UseActivityTo activityApi = new UseActivityTo();

    // No access user doesn't have access to customer or policy
    String action = ActivityUtil.getRandomActivityAction(AADM_NAUser);
    ActivityPostRequest activity = new ActivityPostRequest();
    activity.setAction(action);
    activity.setDescription("Description");
    activity.setCustomerId(customerId);
    activity.setEntityId(customerId);
    activity.setPolicyId(policyId);

    AADM_NAUser.attemptsTo(activityApi.POSTActivityOnTheActivitiesController(activity, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No policy was found using the arguments supplied", AADM_NAUser);

    activity = new ActivityPostRequest();
    activity.setAction(action);
    activity.setDescription("Description");
    activity.setCustomerId(customerId);
    activity.setEntityId(customerId);

    AADM_NAUser.attemptsTo(activityApi.POSTActivityOnTheActivitiesController(activity, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No customer was found using the arguments supplied", AADM_NAUser);

    // Customer Business user has read and write access to the customer but no policy access
    activity = new ActivityPostRequest();
    activity.setAction(action);
    activity.setDescription("Description");
    activity.setCustomerId(customerId);
    activity.setEntityId(customerId);
    activity.setPolicyId(policyId);

    AADM_CBUUSER.attemptsTo(activityApi.POSTActivityOnTheActivitiesController(activity, ""));
    // returns error since  both customer id and policy id submitted
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No policy was found using the arguments supplied", AADM_CBUUSER);

    activity = new ActivityPostRequest();
    activity.setAction(action);
    activity.setDescription("Description");
    activity.setCustomerId(customerId);
    activity.setEntityId(customerId);

    AADM_CBUUSER.attemptsTo(activityApi.POSTActivityOnTheActivitiesController(activity, ""));
    // returns success since  only customer id is submitted
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Policy Business Unit user has read access to customer and read and write access to policy
    activity = new ActivityPostRequest();
    activity.setAction(action);
    activity.setDescription("Description");
    activity.setCustomerId(customerId);
    activity.setEntityId(customerId);

    AADM_PBUUSER.attemptsTo(activityApi.POSTActivityOnTheActivitiesController(activity, ""));
    // returns success since only customer id is submitted and user has the read access to customer
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    activity = new ActivityPostRequest();
    activity.setAction(action);
    activity.setDescription("Description");
    activity.setCustomerId(customerId);
    activity.setEntityId(customerId);
    activity.setPolicyId(policyId);

    AADM_PBUUSER.attemptsTo(activityApi.POSTActivityOnTheActivitiesController(activity, ""));
    // returns success since both customer id and policy id is submitted
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Executive user has read and write access to customer but no policy access
    activity = new ActivityPostRequest();
    activity.setAction(action);
    activity.setDescription("Description");
    activity.setCustomerId(customerId);
    activity.setEntityId(customerId);
    activity.setPolicyId(policyId);

    AADM_EXECUSER.attemptsTo(activityApi.POSTActivityOnTheActivitiesController(activity, ""));
    // returns error since  both customer id and policy id submitted
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No policy was found using the arguments supplied", AADM_EXECUSER);

    activity = new ActivityPostRequest();
    activity.setAction(action);
    activity.setDescription("Description");
    activity.setCustomerId(customerId);
    activity.setEntityId(customerId);

    AADM_EXECUSER.attemptsTo(activityApi.POSTActivityOnTheActivitiesController(activity, ""));
    // returns success since  only customer id is submitted
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Policy Personnel user has read access to customer and read and write access to policy
    activity = new ActivityPostRequest();
    activity.setAction(action);
    activity.setDescription("Description");
    activity.setCustomerId(customerId);
    activity.setEntityId(customerId);

    AADM_PPUser.attemptsTo(activityApi.POSTActivityOnTheActivitiesController(activity, ""));
    // returns success since only customer id is submitted and user has the read access to customer
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    activity = new ActivityPostRequest();
    activity.setAction(action);
    activity.setDescription("Description");
    activity.setCustomerId(customerId);
    activity.setEntityId(customerId);
    activity.setPolicyId(policyId);

    AADM_PPUser.attemptsTo(activityApi.POSTActivityOnTheActivitiesController(activity, ""));
    // returns success since both customer id and policy id is submitted
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Service Group user has read only access to customer and no access to policy
    activity = new ActivityPostRequest();
    activity.setAction(action);
    activity.setDescription("Description");
    activity.setCustomerId(customerId);
    activity.setEntityId(customerId);

    AADM_SGUser.attemptsTo(activityApi.POSTActivityOnTheActivitiesController(activity, ""));
    // returns error since customer id is submitted
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    activity = new ActivityPostRequest();
    activity.setAction(action);
    activity.setDescription("Description");
    activity.setCustomerId(customerId);
    activity.setEntityId(customerId);
    activity.setPolicyId(policyId);

    AADM_SGUser.attemptsTo(activityApi.POSTActivityOnTheActivitiesController(activity, ""));
    // returns error since customer id and policy id is submitted
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No policy was found using the arguments supplied", AADM_SGUser);
  }
}
