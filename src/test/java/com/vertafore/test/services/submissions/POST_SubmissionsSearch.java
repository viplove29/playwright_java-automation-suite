package com.vertafore.test.services.submissions;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.BasicPolicyInfoResponse;
import com.vertafore.test.models.ems.PagingRequestPoliciesSearchPostRequest;
import com.vertafore.test.models.ems.PagingResponseBasicPolicyInfoResponse;
import com.vertafore.test.models.ems.PoliciesSearchPostRequest;
import com.vertafore.test.servicewrappers.UseSubmissionsTo;
import com.vertafore.test.util.PolicyUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_SubmissionsSearch {

  private List<EMSActor> actors = new ArrayList<>();

  @Before
  public void getAnAccessToken() {
    actors.addAll(
        List.of(
            new EMSActor().called("bob").withContext("userContext"),
            new EMSActor().called("doug").withContext("appContext"),
            new EMSActor().called("adam").withContext("adminContext")));
    OnStage.setTheStage(GetAnAccessToken(actors));
  }

  @Test
  public void submissionsSuccessfullyReturnsAllSubmissions() {
    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    UseSubmissionsTo submissionsAPI = new UseSubmissionsTo();

    PagingRequestPoliciesSearchPostRequest pageSearch =
        new PagingRequestPoliciesSearchPostRequest();
    PoliciesSearchPostRequest polPostBody = new PoliciesSearchPostRequest();

    // response will include all policies
    polPostBody.setIsCurrentlyInForce(false);
    polPostBody.setIncludeAllPolicyTypes(true);
    pageSearch.setModel(polPostBody);

    adam.attemptsTo(
        (submissionsAPI.POSTSubmissionsSearchOnTheSubmissionsController(pageSearch, "string")));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    doug.attemptsTo(
        (submissionsAPI.POSTSubmissionsSearchOnTheSubmissionsController(pageSearch, "string")));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // response should now only include submission policies
    polPostBody.setIncludeAllPolicyTypes(false);
    pageSearch.setModel(polPostBody);

    bob.attemptsTo(
        (submissionsAPI.POSTSubmissionsSearchOnTheSubmissionsController(pageSearch, "string")));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PagingResponseBasicPolicyInfoResponse pageResponse =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseBasicPolicyInfoResponse.class);

    assertThat(pageResponse.getTotalCount()).isGreaterThan(0);
    int numOfSubPols = pageResponse.getResponse().size();

    int numOfTypeS =
        (int)
            pageResponse
                .getResponse()
                .stream()
                .filter(pols -> pols.getPolicySubType().equals("S"))
                .count();

    // confirm that the response only contains submission policies
    assertThat(numOfSubPols).isEqualTo(numOfTypeS);
  }

  @Test
  public void submissionsSuccessfullyReturnsOneSubmission() {
    Actor bob = theActorCalled("bob");

    BasicPolicyInfoResponse randomPolicy = PolicyUtil.selectRandomPolicy(bob, "submission");
    String custId = randomPolicy.getCustomerId();
    String polId = randomPolicy.getPolicyId();
    PolicyUtil.setSinglePolicyResponseVariables(randomPolicy);

    UseSubmissionsTo submissionsAPI = new UseSubmissionsTo();
    PagingRequestPoliciesSearchPostRequest pageSearch =
        new PagingRequestPoliciesSearchPostRequest();
    PoliciesSearchPostRequest polPostBody = new PoliciesSearchPostRequest();

    polPostBody.setPolicyId(polId);
    polPostBody.setIsCurrentlyInForce(false);
    polPostBody.setIncludeAllPolicyTypes(false);
    pageSearch.setModel(polPostBody);

    // gets just the one policy using policy id
    bob.attemptsTo(
        (submissionsAPI.POSTSubmissionsSearchOnTheSubmissionsController(pageSearch, "string")));

    BasicPolicyInfoResponse onePolicyByPolId =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseBasicPolicyInfoResponse.class)
            .getResponse()
            .get(0);

    PolicyUtil.validateSinglePolicyResponseVariables(onePolicyByPolId);

    // set up to filter by customer id
    PoliciesSearchPostRequest polPostBody2 = new PoliciesSearchPostRequest();
    polPostBody2.setCustomerId(custId);
    polPostBody2.setIsCurrentlyInForce(false);
    polPostBody2.setIncludeAllPolicyTypes(false);
    pageSearch.setModel(polPostBody2);
    pageSearch.setTop(1000); // 1000 is the max that will return in a page

    // gets all submission policies for customer
    bob.attemptsTo(
        (submissionsAPI.POSTSubmissionsSearchOnTheSubmissionsController(pageSearch, "string")));

    // filters by policy id
    BasicPolicyInfoResponse onePolicyByCustId =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseBasicPolicyInfoResponse.class)
            .getResponse()
            .stream()
            .filter(pols -> pols.getPolicyId().contains(polId))
            .collect(Collectors.toList())
            .get(0);

    PolicyUtil.validateSinglePolicyResponseVariables(onePolicyByCustId);
  }
}
