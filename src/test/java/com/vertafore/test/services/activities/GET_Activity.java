package com.vertafore.test.services.activities;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ActivityIdResponse;
import com.vertafore.test.models.ems.SpecificActivityResponse;
import com.vertafore.test.servicewrappers.UseActivityTo;
import com.vertafore.test.util.ActivityUtil;
import com.vertafore.test.util.EnvVariables;
import com.vertafore.test.util.Util;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_Activity extends TokenSuperClass {

  @Test
  public void getActivityGetsActivity() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    // stage data
    ActivityIdResponse activityPosted = ActivityUtil.postCustomerRandomActivity(AADM_User);
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

  @Test
  public void getActivityReadMaskTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor AADM_NAUser = theActorCalled("AADM_NAUser");
    Actor AADM_CBUUSER = theActorCalled("AADM_CBUUser");
    Actor AADM_PBUUSER = theActorCalled("AADM_PBUUser");
    Actor AADM_EXECUSER = theActorCalled("AADM_EXECUser");
    Actor AADM_PPUser = theActorCalled("AADM_PPUser");
    Actor AADM_SGUser = theActorCalled("AADM_SGUser");

    // use the staged customer ID
    String customerId = EnvVariables.READ_WRITE_MASK_CUSTOMER_ID;
    String policyId = EnvVariables.READ_WRITE_MASK_POLICY_ID;

    // stage data for customer activity
    ActivityIdResponse activityPosted =
        ActivityUtil.postCustomerRandomActivity(AADM_User, customerId);
    String activityId = activityPosted.getActivityId();

    UseActivityTo activityApi = new UseActivityTo();

    // No access user doesn't have access to customer or policy
    AADM_NAUser.attemptsTo(activityApi.GETActivityOnTheActivitiesController(activityId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No activity was found using the arguments supplied", AADM_NAUser);

    // Customer Business user has read and write access to the customer but no policy access
    AADM_CBUUSER.attemptsTo(activityApi.GETActivityOnTheActivitiesController(activityId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Policy Business Unit user has read access to customer and read and write access to policy
    AADM_PBUUSER.attemptsTo(activityApi.GETActivityOnTheActivitiesController(activityId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Executive user has read and write access to customer but no policy access
    AADM_EXECUSER.attemptsTo(activityApi.GETActivityOnTheActivitiesController(activityId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Policy Personnel user has read access to customer and read and write access to policy
    AADM_PPUser.attemptsTo(activityApi.GETActivityOnTheActivitiesController(activityId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Service Group user has read only access to customer and no access to policy
    AADM_SGUser.attemptsTo(activityApi.GETActivityOnTheActivitiesController(activityId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // stage data for policy activity
    activityPosted = ActivityUtil.postPolicyRandomActivity(AADM_User, customerId, policyId);
    activityId = activityPosted.getActivityId();

    // No access user doesn't have access to customer or policy
    AADM_NAUser.attemptsTo(activityApi.GETActivityOnTheActivitiesController(activityId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No activity was found using the arguments supplied", AADM_NAUser);

    // Customer Business user has read and write access to the customer but no policy access
    AADM_CBUUSER.attemptsTo(activityApi.GETActivityOnTheActivitiesController(activityId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No activity was found using the arguments supplied", AADM_CBUUSER);

    // Policy Business Unit user has read access to customer and read and write access to policy
    AADM_PBUUSER.attemptsTo(activityApi.GETActivityOnTheActivitiesController(activityId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Executive user has read and write access to customer but no policy access
    AADM_EXECUSER.attemptsTo(activityApi.GETActivityOnTheActivitiesController(activityId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No activity was found using the arguments supplied", AADM_EXECUSER);

    // Policy Personnel user has read access to customer and read and write access to policy
    AADM_PPUser.attemptsTo(activityApi.GETActivityOnTheActivitiesController(activityId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Service Group user has read only access to customer and no access to policy
    AADM_SGUser.attemptsTo(activityApi.GETActivityOnTheActivitiesController(activityId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No activity was found using the arguments supplied", AADM_SGUser);
  }
}
