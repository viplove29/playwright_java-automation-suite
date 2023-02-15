package com.vertafore.test.services.activities;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ActivityIdResponse;
import com.vertafore.test.models.ems.IVANSeDocsActivityPostRequest;
import com.vertafore.test.servicewrappers.UseIvansTo;
import com.vertafore.test.util.ActivityUtil;
import com.vertafore.test.util.CustomerUtil;
import com.vertafore.test.util.EnvVariables;
import com.vertafore.test.util.Util;
import java.io.IOException;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_IvansActivity extends TokenSuperClass {

  @Test
  public void postIvanActivityPostsActivity() throws IOException {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseIvansTo activityApi = new UseIvansTo();

    String customerId = CustomerUtil.selectRandomCustomer(AADM_User, "customer").getCustomerId();
    String action = ActivityUtil.getRandomActivityAction(AADM_User);
    IVANSeDocsActivityPostRequest activity =
        ActivityUtil.getIvansActivityPostRequest(action, customerId, "");

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
  public void postIvanActivityWithoutActionPostsActivity() throws IOException {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseIvansTo activityApi = new UseIvansTo();

    String customerId = CustomerUtil.selectRandomCustomer(AADM_User, "customer").getCustomerId();
    IVANSeDocsActivityPostRequest activity =
        ActivityUtil.getIvansActivityPostRequest("", customerId, "");

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
  public void postIvanActivityWriteMaskTest() throws IOException {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor AADM_NAUser = theActorCalled("AADM_NAUser");
    Actor AADM_CBUUSER = theActorCalled("AADM_CBUUser");
    Actor AADM_PBUUSER = theActorCalled("AADM_PBUUser");
    Actor AADM_EXECUSER = theActorCalled("AADM_EXECUser");
    Actor AADM_PPUser = theActorCalled("AADM_PPUser");
    Actor AADM_SGUser = theActorCalled("AADM_SGUser");

    // use the staged customer Id and policy Id
    String customerId = EnvVariables.READ_WRITE_MASK_CUSTOMER_ID;
    String policyId = EnvVariables.READ_WRITE_MASK_POLICY_ID;

    String action = ActivityUtil.getRandomActivityAction(AADM_User);

    UseIvansTo activityApi = new UseIvansTo();

    IVANSeDocsActivityPostRequest activity =
        ActivityUtil.getIvansActivityPostRequest(action, customerId, policyId);

    // No access user doesn't have access to customer or policy
    AADM_NAUser.attemptsTo(
        activityApi.POSTIvansEdocsActivityOnTheActivitiesController(activity, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No policy was found using the arguments supplied", AADM_NAUser);

    activity = ActivityUtil.getIvansActivityPostRequest(action, customerId, "");

    AADM_NAUser.attemptsTo(
        activityApi.POSTIvansEdocsActivityOnTheActivitiesController(activity, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No customer was found using the arguments supplied", AADM_NAUser);

    // Customer Business user has read and write access to the customer but no policy access
    activity = ActivityUtil.getIvansActivityPostRequest(action, customerId, policyId);

    AADM_CBUUSER.attemptsTo(
        activityApi.POSTIvansEdocsActivityOnTheActivitiesController(activity, ""));
    // returns error since  both customer id and policy id submitted
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No policy was found using the arguments supplied", AADM_CBUUSER);

    activity = ActivityUtil.getIvansActivityPostRequest(action, customerId, "");

    AADM_CBUUSER.attemptsTo(
        activityApi.POSTIvansEdocsActivityOnTheActivitiesController(activity, ""));
    // returns success since  only customer id is submitted
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Policy Business Unit user has read access to customer and read and write access to policy
    activity = ActivityUtil.getIvansActivityPostRequest(action, customerId, "");

    AADM_PBUUSER.attemptsTo(
        activityApi.POSTIvansEdocsActivityOnTheActivitiesController(activity, ""));
    // returns success since only customer id is submitted and user has the read access to customer
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    activity = ActivityUtil.getIvansActivityPostRequest(action, customerId, policyId);

    AADM_PBUUSER.attemptsTo(
        activityApi.POSTIvansEdocsActivityOnTheActivitiesController(activity, ""));
    // returns success since both customer id and policy id is submitted
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Executive user has read and write access to customer but no policy access
    activity = ActivityUtil.getIvansActivityPostRequest(action, customerId, policyId);

    AADM_EXECUSER.attemptsTo(
        activityApi.POSTIvansEdocsActivityOnTheActivitiesController(activity, ""));
    // returns error since  both customer id and policy id submitted
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No policy was found using the arguments supplied", AADM_EXECUSER);

    activity = ActivityUtil.getIvansActivityPostRequest(action, customerId, "");

    AADM_EXECUSER.attemptsTo(
        activityApi.POSTIvansEdocsActivityOnTheActivitiesController(activity, ""));
    // returns success since  only customer id is submitted
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Policy Personnel user has read access to customer and read and write access to policy
    activity = ActivityUtil.getIvansActivityPostRequest(action, customerId, "");

    AADM_PPUser.attemptsTo(
        activityApi.POSTIvansEdocsActivityOnTheActivitiesController(activity, ""));

    // returns success since only customer id is submitted and user has the read access to customer
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    activity = ActivityUtil.getIvansActivityPostRequest(action, customerId, policyId);

    AADM_PPUser.attemptsTo(
        activityApi.POSTIvansEdocsActivityOnTheActivitiesController(activity, ""));

    // returns success since both customer id and policy id is submitted
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    // Service Group user has read only access to customer and no access to policy
    activity = ActivityUtil.getIvansActivityPostRequest(action, customerId, "");

    AADM_SGUser.attemptsTo(
        activityApi.POSTIvansEdocsActivityOnTheActivitiesController(activity, ""));
    // returns error since customer id is submitted
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    activity = ActivityUtil.getIvansActivityPostRequest(action, customerId, policyId);

    AADM_SGUser.attemptsTo(
        activityApi.POSTIvansEdocsActivityOnTheActivitiesController(activity, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }
}
