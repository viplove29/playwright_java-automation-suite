package com.vertafore.test.services.activity_checkpoints;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ActivityCheckpointResponse;
import com.vertafore.test.servicewrappers.UseActivityCheckpointTo;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_ActivityCheckpoint extends TokenSuperClass {

  /* A smoke test that validates the GET /activity-checkpoint endpoint against admin,app, and user contexts.
  This test checks that when "Claim" or "1" are used as parameters for the Checkpoint, the response
  is the same.*/
  @Test
  public void activityCheckpointClaimReturnsAllActivityClaimCheckpoint() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseActivityCheckpointTo activityCheckpointApi = new UseActivityCheckpointTo();

    VADM_Admin.attemptsTo(
        activityCheckpointApi.GETActivityCheckpointOnTheActivitycheckpointsController(
            "Claim", "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        activityCheckpointApi.GETActivityCheckpointOnTheActivitycheckpointsController(
            "Claim", "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        activityCheckpointApi.GETActivityCheckpointOnTheActivitycheckpointsController(
            "Claim", "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ActivityCheckpointResponse activityCheckpointClaimResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", ActivityCheckpointResponse.class);

    assertThat(activityCheckpointClaimResponse.getClass().getDeclaredFields().length).isEqualTo(7);

    /*This request queries the endpoint using "1" which should return the same result as inputting
    the word "Claim" as a parameter*/
    AADM_User.attemptsTo(
        activityCheckpointApi.GETActivityCheckpointOnTheActivitycheckpointsController(
            "1", "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ActivityCheckpointResponse activityCheckpointClaimNumberResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", ActivityCheckpointResponse.class);

    /*Assertions to validate that the response bodies match*/
    assertThat(activityCheckpointClaimNumberResponse.getActivityCheckpointKey())
        .isEqualTo(activityCheckpointClaimResponse.getActivityCheckpointKey());
    assertThat(activityCheckpointClaimNumberResponse.getCheckpointDescription())
        .isEqualTo(activityCheckpointClaimResponse.getCheckpointDescription());
    assertThat(activityCheckpointClaimNumberResponse.getIsActive())
        .isEqualTo(activityCheckpointClaimResponse.getIsActive());
    assertThat(activityCheckpointClaimNumberResponse.getPromptType())
        .isEqualTo(activityCheckpointClaimResponse.getPromptType());
    assertThat(activityCheckpointClaimNumberResponse.getIsActivityRequired())
        .isEqualTo(activityCheckpointClaimResponse.getIsActivityRequired());
    assertThat(activityCheckpointClaimNumberResponse.getIsSuspenseCreated())
        .isEqualTo(activityCheckpointClaimResponse.getIsSuspenseCreated());
    assertThat(activityCheckpointClaimNumberResponse.getDbAction())
        .isEqualTo(activityCheckpointClaimResponse.getDbAction());
  }

  /* A smoke test that validates the GET /activity-checkpoint endpoint against admin,app, and user contexts.
  This test checks that when "Customer" or "4" are used as parameters for the Checkpoint, the response
  is the same.*/
  @Test
  public void activityCheckpointCustomerReturnsAllActivityCustomerCheckpoint() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseActivityCheckpointTo activityCheckpointApi = new UseActivityCheckpointTo();

    VADM_Admin.attemptsTo(
        activityCheckpointApi.GETActivityCheckpointOnTheActivitycheckpointsController(
            "Customer", "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        activityCheckpointApi.GETActivityCheckpointOnTheActivitycheckpointsController(
            "Customer", "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        activityCheckpointApi.GETActivityCheckpointOnTheActivitycheckpointsController(
            "Customer", "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ActivityCheckpointResponse activityCheckpointCustomerResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", ActivityCheckpointResponse.class);

    assertThat(activityCheckpointCustomerResponse.getClass().getDeclaredFields().length)
        .isEqualTo(7);

    /*This request queries the endpoint using "4" which should return the same result as inputting
    the word "Customer" as a parameter*/
    AADM_User.attemptsTo(
        activityCheckpointApi.GETActivityCheckpointOnTheActivitycheckpointsController(
            "4", "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ActivityCheckpointResponse activityCheckpointCustomerNumberResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", ActivityCheckpointResponse.class);

    /*Assertions to validate that the response bodies match*/
    assertThat(activityCheckpointCustomerNumberResponse.getActivityCheckpointKey())
        .isEqualTo(activityCheckpointCustomerResponse.getActivityCheckpointKey());
    assertThat(activityCheckpointCustomerNumberResponse.getCheckpointDescription())
        .isEqualTo(activityCheckpointCustomerResponse.getCheckpointDescription());
    assertThat(activityCheckpointCustomerNumberResponse.getIsActive())
        .isEqualTo(activityCheckpointCustomerResponse.getIsActive());
    assertThat(activityCheckpointCustomerNumberResponse.getPromptType())
        .isEqualTo(activityCheckpointCustomerResponse.getPromptType());
    assertThat(activityCheckpointCustomerNumberResponse.getIsActivityRequired())
        .isEqualTo(activityCheckpointCustomerResponse.getIsActivityRequired());
    assertThat(activityCheckpointCustomerNumberResponse.getIsSuspenseCreated())
        .isEqualTo(activityCheckpointCustomerResponse.getIsSuspenseCreated());
    assertThat(activityCheckpointCustomerNumberResponse.getDbAction())
        .isEqualTo(activityCheckpointCustomerResponse.getDbAction());
  }

  /* A smoke test that validates the GET /activity-checkpoint endpoint against admin,app, and user contexts.
  This test checks that when "Download" or "6" are used as parameters for the Checkpoint, the response
  is the same.*/
  @Test
  public void activityCheckpointDownloadReturnsAllActivityDownloadCheckpoint() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseActivityCheckpointTo activityCheckpointApi = new UseActivityCheckpointTo();

    VADM_Admin.attemptsTo(
        activityCheckpointApi.GETActivityCheckpointOnTheActivitycheckpointsController(
            "Download", "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        activityCheckpointApi.GETActivityCheckpointOnTheActivitycheckpointsController(
            "Download", "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        activityCheckpointApi.GETActivityCheckpointOnTheActivitycheckpointsController(
            "Download", "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ActivityCheckpointResponse activityCheckpointDownloadResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", ActivityCheckpointResponse.class);

    assertThat(activityCheckpointDownloadResponse.getClass().getDeclaredFields().length)
        .isEqualTo(7);

    /*This request queries the endpoint using "6" which should return the same result as inputting
    the word "Download" as a parameter*/
    AADM_User.attemptsTo(
        activityCheckpointApi.GETActivityCheckpointOnTheActivitycheckpointsController(
            "6", "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ActivityCheckpointResponse activityCheckpointDownloadNumberResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", ActivityCheckpointResponse.class);

    /*Assertions to validate that the response bodies match*/
    assertThat(activityCheckpointDownloadNumberResponse.getActivityCheckpointKey())
        .isEqualTo(activityCheckpointDownloadResponse.getActivityCheckpointKey());
    assertThat(activityCheckpointDownloadNumberResponse.getCheckpointDescription())
        .isEqualTo(activityCheckpointDownloadResponse.getCheckpointDescription());
    assertThat(activityCheckpointDownloadNumberResponse.getIsActive())
        .isEqualTo(activityCheckpointDownloadResponse.getIsActive());
    assertThat(activityCheckpointDownloadNumberResponse.getPromptType())
        .isEqualTo(activityCheckpointDownloadResponse.getPromptType());
    assertThat(activityCheckpointDownloadNumberResponse.getIsActivityRequired())
        .isEqualTo(activityCheckpointDownloadResponse.getIsActivityRequired());
    assertThat(activityCheckpointDownloadNumberResponse.getIsSuspenseCreated())
        .isEqualTo(activityCheckpointDownloadResponse.getIsSuspenseCreated());
    assertThat(activityCheckpointDownloadNumberResponse.getDbAction())
        .isEqualTo(activityCheckpointDownloadResponse.getDbAction());
  }

  /* A smoke test that validates the GET /activity-checkpoint endpoint against admin,app, and user contexts.
  This test checks that when "Policy Edit" or "20" are used as parameters for the Checkpoint, the response
  is the same.*/
  @Test
  public void activityCheckpointPolicyEditReturnsAllActivityPolicyEditCheckpoint() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseActivityCheckpointTo activityCheckpointApi = new UseActivityCheckpointTo();

    VADM_Admin.attemptsTo(
        activityCheckpointApi.GETActivityCheckpointOnTheActivitycheckpointsController(
            "Policy Edit", "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        activityCheckpointApi.GETActivityCheckpointOnTheActivitycheckpointsController(
            "Policy Edit", "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        activityCheckpointApi.GETActivityCheckpointOnTheActivitycheckpointsController(
            "Policy Edit", "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ActivityCheckpointResponse activityCheckpointPolicyEditResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", ActivityCheckpointResponse.class);

    assertThat(activityCheckpointPolicyEditResponse.getClass().getDeclaredFields().length)
        .isEqualTo(7);

    /*This request queries the endpoint using "20" which should return the same result as inputting
    the word "Policy Edit" as a parameter*/
    AADM_User.attemptsTo(
        activityCheckpointApi.GETActivityCheckpointOnTheActivitycheckpointsController(
            "20", "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ActivityCheckpointResponse activityCheckpointPolicyEditNumberResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", ActivityCheckpointResponse.class);

    /*Assertions to validate that the response bodies match*/
    assertThat(activityCheckpointPolicyEditNumberResponse.getActivityCheckpointKey())
        .isEqualTo(activityCheckpointPolicyEditResponse.getActivityCheckpointKey());
    assertThat(activityCheckpointPolicyEditNumberResponse.getCheckpointDescription())
        .isEqualTo(activityCheckpointPolicyEditResponse.getCheckpointDescription());
    assertThat(activityCheckpointPolicyEditNumberResponse.getIsActive())
        .isEqualTo(activityCheckpointPolicyEditResponse.getIsActive());
    assertThat(activityCheckpointPolicyEditNumberResponse.getPromptType())
        .isEqualTo(activityCheckpointPolicyEditResponse.getPromptType());
    assertThat(activityCheckpointPolicyEditNumberResponse.getIsActivityRequired())
        .isEqualTo(activityCheckpointPolicyEditResponse.getIsActivityRequired());
    assertThat(activityCheckpointPolicyEditNumberResponse.getIsSuspenseCreated())
        .isEqualTo(activityCheckpointPolicyEditResponse.getIsSuspenseCreated());
    assertThat(activityCheckpointPolicyEditNumberResponse.getDbAction())
        .isEqualTo(activityCheckpointPolicyEditResponse.getDbAction());
  }
}
