package com.vertafore.test.services.submissions;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.BasicPolicyInfoResponse;
import com.vertafore.test.models.ems.PagingRequestPoliciesSearchPostRequest;
import com.vertafore.test.models.ems.PagingResponseBasicPolicyInfoResponse;
import com.vertafore.test.models.ems.PoliciesSearchPostRequest;
import com.vertafore.test.servicewrappers.UseSubmissionsTo;
import com.vertafore.test.util.EnvVariables;
import com.vertafore.test.util.PolicyUtil;
import java.util.stream.Collectors;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_SubmissionsSearch extends TokenSuperClass {

  @Test
  public void submissionsSuccessfullyReturnsAllSubmissions() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");
    Actor AADM_V4App = theActorCalled("AADM_V4App");
    Actor AGNY_User = theActorCalled("AGNY_User");
    Actor VERT_User = theActorCalled("VERT_User");
    Actor VERT_V4App = theActorCalled("VERT_V4App");

    UseSubmissionsTo submissionsAPI = new UseSubmissionsTo();

    PagingRequestPoliciesSearchPostRequest pageSearch =
        new PagingRequestPoliciesSearchPostRequest();
    PoliciesSearchPostRequest polPostBody = new PoliciesSearchPostRequest();

    // response will include all policies
    polPostBody.setIsCurrentlyInForce(false);
    polPostBody.setIncludeAllPolicyTypes(true);
    pageSearch.setModel(polPostBody);

    VADM_Admin.attemptsTo(
        (submissionsAPI.POSTSubmissionsSearchOnTheSubmissionsController(pageSearch, "string")));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        (submissionsAPI.POSTSubmissionsSearchOnTheSubmissionsController(pageSearch, "string")));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_V4App.attemptsTo(
        (submissionsAPI.POSTSubmissionsSearchOnTheSubmissionsController(pageSearch, "string")));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    /*AGNY keys need to be activated via the agency whitelist, which isn't available
    for regular QA people in MDC
    Also we're still having trouble getting working keys for VERT users and V4apps in MDC as well*/

    int agnyStatusCode = 200;
    int vertUserCode = 200;
    int vertAppCode = 401;

    if (EnvVariables.BASE_URL.contains("mdc")) {
      agnyStatusCode = 401;
      vertUserCode = 401;
      vertAppCode = 401;
    }
    AGNY_User.attemptsTo(
        (submissionsAPI.POSTSubmissionsSearchOnTheSubmissionsController(pageSearch, "string")));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(agnyStatusCode);

    VERT_User.attemptsTo(
        (submissionsAPI.POSTSubmissionsSearchOnTheSubmissionsController(pageSearch, "string")));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(vertUserCode);

    VERT_V4App.attemptsTo(
        (submissionsAPI.POSTSubmissionsSearchOnTheSubmissionsController(pageSearch, "string")));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(vertAppCode);

    // response should now only include submission policies
    polPostBody.setIncludeAllPolicyTypes(false);
    pageSearch.setModel(polPostBody);

    AADM_User.attemptsTo(
        (submissionsAPI.POSTSubmissionsSearchOnTheSubmissionsController(pageSearch, "string")));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PagingResponseBasicPolicyInfoResponse pageResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
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
    Actor AADM_User = theActorCalled("AADM_User");

    BasicPolicyInfoResponse randomPolicy = PolicyUtil.selectRandomPolicy(AADM_User, "submission");
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
    AADM_User.attemptsTo(
        (submissionsAPI.POSTSubmissionsSearchOnTheSubmissionsController(pageSearch, "string")));

    BasicPolicyInfoResponse onePolicyByPolId =
        LastResponse.received()
            .answeredBy(AADM_User)
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
    AADM_User.attemptsTo(
        (submissionsAPI.POSTSubmissionsSearchOnTheSubmissionsController(pageSearch, "string")));

    // filters by policy id
    BasicPolicyInfoResponse onePolicyByCustId =
        LastResponse.received()
            .answeredBy(AADM_User)
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
