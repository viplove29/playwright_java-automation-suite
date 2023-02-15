package com.vertafore.test.services.activities;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ActivityIdResponse;
import com.vertafore.test.models.ems.CustomerActivityPostRequest;
import com.vertafore.test.servicewrappers.UseActivityTo;
import com.vertafore.test.util.ActivityUtil;
import com.vertafore.test.util.CustomerUtil;
import com.vertafore.test.util.EnvVariables;
import com.vertafore.test.util.Util;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_ActivityCustomer extends TokenSuperClass {

  @Test
  public void postActivityCustomerPostsCustomerActivity() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseActivityTo activityApi = new UseActivityTo();
    CustomerActivityPostRequest postRequest = new CustomerActivityPostRequest();
    postRequest.setCustomerId(
        CustomerUtil.selectRandomCustomer(AADM_User, "customer").getCustomerId());
    postRequest.setComment("Automation Test");
    postRequest.setAction(ActivityUtil.getRandomActivityAction(AADM_User));

    AADM_User.attemptsTo(
        activityApi.POSTActivityCustomerOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        activityApi.POSTActivityCustomerOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(activityApi.POSTActivityCustomerOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ActivityIdResponse response =
        LastResponse.received()
            .answeredBy(ORAN_App)
            .getBody()
            .jsonPath()
            .getObject("", ActivityIdResponse.class);

    assertThat(response.getActivityId()).isNotNull();
  }

  @Test
  public void postActivityCustomerWriteMaskTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor AADM_NAUser = theActorCalled("AADM_NAUser");
    Actor AADM_CBUUSER = theActorCalled("AADM_CBUUser");
    Actor AADM_PBUUSER = theActorCalled("AADM_PBUUser");
    Actor AADM_EXECUSER = theActorCalled("AADM_EXECUser");
    Actor AADM_PPUser = theActorCalled("AADM_PPUser");
    Actor AADM_SGUser = theActorCalled("AADM_SGUser");

    // use the staged customer ID
    String customerId = EnvVariables.READ_WRITE_MASK_CUSTOMER_ID;

    UseActivityTo activityApi = new UseActivityTo();
    // No access user doesn't have access to customer or policy
    String action = ActivityUtil.getRandomActivityAction(AADM_User);
    CustomerActivityPostRequest postRequest = new CustomerActivityPostRequest();
    postRequest.setCustomerId(customerId);
    postRequest.setComment("Automation Test");
    postRequest.setAction(action);

    AADM_NAUser.attemptsTo(
        activityApi.POSTActivityCustomerOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No customer was found using the arguments supplied", AADM_NAUser);

    // Customer Business user has read and write access to the customer but no policy access
    AADM_CBUUSER.attemptsTo(
        activityApi.POSTActivityCustomerOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Policy Business Unit user has read access to customer and read and write access to policy
    AADM_PBUUSER.attemptsTo(
        activityApi.POSTActivityCustomerOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Executive user has read and write access to customer but no policy access
    AADM_EXECUSER.attemptsTo(
        activityApi.POSTActivityCustomerOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Policy Personnel user has read access to customer and read and write access to policy
    AADM_PPUser.attemptsTo(
        activityApi.POSTActivityCustomerOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Service Group user has read only access to customer and no access to policy
    AADM_SGUser.attemptsTo(
        activityApi.POSTActivityCustomerOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
