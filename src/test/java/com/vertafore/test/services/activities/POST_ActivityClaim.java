package com.vertafore.test.services.activities;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ActivityClaimPostRequest;
import com.vertafore.test.models.ems.ActivityClaimResponse;
import com.vertafore.test.models.ems.CustomerIdResponse;
import com.vertafore.test.servicewrappers.UseActivityTo;
import com.vertafore.test.util.CustomerUtil;
import com.vertafore.test.util.EnvVariables;
import com.vertafore.test.util.Util;
import java.time.LocalDateTime;
import java.util.UUID;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_ActivityClaim extends TokenSuperClass {

  /* TODO this test needs checks for ORAN and VADM keys, as well as checks on all the other parts of the post body */
  @Test
  public void PostActivityClaimSuccessfullyPostsOneClaim() {

    Actor AADM_User = theActorCalled("AADM_User");
    CustomerIdResponse randomCustomer = CustomerUtil.stageARandomCustomer(AADM_User);

    UseActivityTo ActivityAPI = new UseActivityTo();
    String randomClaimId = UUID.randomUUID().toString();
    String randomCustId = randomCustomer.getCustomerId();

    ActivityClaimPostRequest postReqWithCustId = new ActivityClaimPostRequest();
    postReqWithCustId.setAction("string");
    postReqWithCustId.setClaimId(randomClaimId);
    postReqWithCustId.setCustomerId(randomCustId);
    postReqWithCustId.setTransactionDate(LocalDateTime.now().toString());

    AADM_User.attemptsTo(
        ActivityAPI.POSTActivityClaimOnTheActivitiesController(postReqWithCustId, "string"));

    AADM_User.should(seeThatResponse("Activity Claims Returned", res -> res.statusCode(200)));

    ActivityClaimResponse claimResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", ActivityClaimResponse.class);

    assertThat(claimResponse != null).isTrue();

    /* TODO this test is using the GET /activity endpoint to check, this should be wrapped up in a larger test for that endpoint */
    AADM_User.attemptsTo(ActivityAPI.GETActivityOnTheActivitiesController(randomClaimId, "string"));

    AADM_User.should(seeThatResponse("Activity Claims Returned", res -> res.statusCode(200)));
  }

  @Test
  public void postActivityClaimWriteMaskTest() {
    Actor AADM_NAUser = theActorCalled("AADM_NAUser");
    Actor AADM_CBUUSER = theActorCalled("AADM_CBUUser");
    Actor AADM_PBUUSER = theActorCalled("AADM_PBUUser");
    Actor AADM_EXECUSER = theActorCalled("AADM_EXECUser");
    Actor AADM_PPUser = theActorCalled("AADM_PPUser");
    Actor AADM_SGUser = theActorCalled("AADM_SGUser");

    UseActivityTo activityApi = new UseActivityTo();
    // use the staged customer Id and policy Id
    String customerId = EnvVariables.READ_WRITE_MASK_CUSTOMER_ID;
    String policyId = EnvVariables.READ_WRITE_MASK_POLICY_ID;

    String randomClaimId = UUID.randomUUID().toString();

    ActivityClaimPostRequest postReqWithCustAndPolicyId = new ActivityClaimPostRequest();
    postReqWithCustAndPolicyId.setAction("string");
    postReqWithCustAndPolicyId.setClaimId(randomClaimId);
    postReqWithCustAndPolicyId.setCustomerId(customerId);
    postReqWithCustAndPolicyId.setPolicyId(policyId);
    postReqWithCustAndPolicyId.setTransactionDate(LocalDateTime.now().toString());

    ActivityClaimPostRequest postReqWithCustId = new ActivityClaimPostRequest();
    postReqWithCustId.setAction("string");
    postReqWithCustId.setClaimId(randomClaimId);
    postReqWithCustId.setCustomerId(customerId);
    postReqWithCustId.setTransactionDate(LocalDateTime.now().toString());

    AADM_NAUser.attemptsTo(
        activityApi.POSTActivityClaimOnTheActivitiesController(postReqWithCustAndPolicyId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No policy was found using the arguments supplied", AADM_NAUser);

    AADM_NAUser.attemptsTo(
        activityApi.POSTActivityClaimOnTheActivitiesController(postReqWithCustId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No customer was found using the arguments supplied", AADM_NAUser);

    // Customer Business user has read and write access to the customer but no policy access
    AADM_CBUUSER.attemptsTo(
        activityApi.POSTActivityClaimOnTheActivitiesController(postReqWithCustAndPolicyId, ""));
    // returns error since  both customer id and policy id submitted
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No policy was found using the arguments supplied", AADM_CBUUSER);

    AADM_CBUUSER.attemptsTo(
        activityApi.POSTActivityClaimOnTheActivitiesController(postReqWithCustId, ""));
    // returns success since  only customer id is submitted
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Policy Business Unit user has read access to customer and read and write access to policy
    AADM_PBUUSER.attemptsTo(
        activityApi.POSTActivityClaimOnTheActivitiesController(postReqWithCustId, ""));
    // returns success since only customer id is submitted and user has the read access to customer
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_PBUUSER.attemptsTo(
        activityApi.POSTActivityClaimOnTheActivitiesController(postReqWithCustAndPolicyId, ""));
    // returns success since both customer id and policy id is submitted
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Executive user has read and write access to customer but no policy access
    AADM_EXECUSER.attemptsTo(
        activityApi.POSTActivityClaimOnTheActivitiesController(postReqWithCustAndPolicyId, ""));
    // returns error since  both customer id and policy id submitted
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No policy was found using the arguments supplied", AADM_EXECUSER);

    AADM_EXECUSER.attemptsTo(
        activityApi.POSTActivityClaimOnTheActivitiesController(postReqWithCustId, ""));
    // returns success since  only customer id is submitted
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Policy Personnel user has read access to customer and read and write access to policy
    AADM_PPUser.attemptsTo(
        activityApi.POSTActivityClaimOnTheActivitiesController(postReqWithCustId, ""));
    // returns success since only customer id is submitted and user has the read access to customer
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_PPUser.attemptsTo(
        activityApi.POSTActivityClaimOnTheActivitiesController(postReqWithCustAndPolicyId, ""));
    // returns success since both customer id and policy id is submitted
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Service Group user has read only access to customer and no access to policy
    AADM_SGUser.attemptsTo(
        activityApi.POSTActivityClaimOnTheActivitiesController(postReqWithCustId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_SGUser.attemptsTo(
        activityApi.POSTActivityClaimOnTheActivitiesController(postReqWithCustAndPolicyId, ""));
    // returns error since customer id and policy id is submitted
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No policy was found using the arguments supplied", AADM_SGUser);
  }
}
