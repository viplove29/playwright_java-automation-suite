package com.vertafore.test.services.submissions;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.PoliciesTransactionsSearchPostRequest;
import com.vertafore.test.models.ems.PolicyTransactionResponse;
import com.vertafore.test.servicewrappers.UseSubmissionsTo;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_SubmissionsTransactionsSearch {

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

  // TODO these tests all rely on hardcoded data, please change

  @Test
  public void submissionsTransactionsSearchSuccessfullyReturnsForAuthorizedUsers() {
    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    String policyGUID = "959d63d6-5709-4a4c-a042-03311792be5b";
    ArrayList<String> policies = new ArrayList<>();
    policies.add(policyGUID);

    PoliciesTransactionsSearchPostRequest requestBody = new PoliciesTransactionsSearchPostRequest();
    requestBody.setPolicyIds(policies);
    requestBody.setIncludeAllPolicyTypes(true);

    UseSubmissionsTo submissionsAPI = new UseSubmissionsTo();

    bob.attemptsTo(
        submissionsAPI.POSTSubmissionsTransactionsSearchOnTheSubmissionsController(
            requestBody, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    doug.attemptsTo(
        submissionsAPI.POSTSubmissionsTransactionsSearchOnTheSubmissionsController(
            requestBody, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    adam.attemptsTo(
        submissionsAPI.POSTSubmissionsTransactionsSearchOnTheSubmissionsController(
            requestBody, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }

  /*
    This is a test that populates the body with a normal policy guid that should not appear if includeAllPolicies = false
    The endpoint should return a 400 bad data request because we are passing it more than just a submission in the
    policyIds array.
  */
  @Test
  public void falseIncludeAllPoliciesFlagDoesNotReturnAllPolicyTransactions() {
    Actor bob = theActorCalled("bob");
    String submissionGUID = "959d63d6-5709-4a4c-a042-03311792be5b";
    String policyGUID = "fa06dea6-dec6-4767-9b03-0087c91ccc58";

    ArrayList<String> policies = new ArrayList<>();
    policies.add(submissionGUID); // submission
    policies.add(policyGUID); // normal policy

    PoliciesTransactionsSearchPostRequest requestBody = new PoliciesTransactionsSearchPostRequest();
    requestBody.setPolicyIds(policies);
    requestBody.setIncludeAllPolicyTypes(false);

    UseSubmissionsTo submissionsAPI = new UseSubmissionsTo();

    bob.attemptsTo(
        submissionsAPI.POSTSubmissionsTransactionsSearchOnTheSubmissionsController(
            requestBody, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    assertThat(SerenityRest.lastResponse().asString())
        .isEqualTo("{\"error\":\"Unable to process Policy with ID: 'MNM' as type 'Submission'.\"}");

    // remove the non-submission policy and re-post
    policies.remove(policyGUID);
    requestBody.setPolicyIds(policies);

    bob.attemptsTo(
        submissionsAPI.POSTSubmissionsTransactionsSearchOnTheSubmissionsController(
            requestBody, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }

  @Test
  public void trueIncludeAllPoliciesFlagDoesReturnAllPolicyTransactions() {
    Actor bob = theActorCalled("bob");
    String submissionGUID = "959d63d6-5709-4a4c-a042-03311792be5b";
    String policyGUID = "fa06dea6-dec6-4767-9b03-0087c91ccc58";

    ArrayList<String> policies = new ArrayList<>();
    policies.add(submissionGUID); // submission
    policies.add(policyGUID); // normal policy

    PoliciesTransactionsSearchPostRequest requestBody = new PoliciesTransactionsSearchPostRequest();
    requestBody.setPolicyIds(policies);
    requestBody.setIncludeAllPolicyTypes(true);

    UseSubmissionsTo submissionsAPI = new UseSubmissionsTo();

    bob.attemptsTo(
        submissionsAPI.POSTSubmissionsTransactionsSearchOnTheSubmissionsController(
            requestBody, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }

  @Test
  public void submissionsTransactionSearchReturnsCorrectData() {
    Actor bob = theActorCalled("bob");
    String submissionGUID = "959d63d6-5709-4a4c-a042-03311792be5b";
    String policyGUID = "fa06dea6-dec6-4767-9b03-0087c91ccc58";
    String submissionTransactionType = "NBQ";
    String policyTransactionType = "NBS";
    String submissionDescription = "New business quote";
    String policyDescription = "New business";

    ArrayList<String> policies = new ArrayList<>();
    policies.add(submissionGUID); // submission
    policies.add(policyGUID); // normal policy

    PoliciesTransactionsSearchPostRequest requestBody = new PoliciesTransactionsSearchPostRequest();
    requestBody.setPolicyIds(policies);
    requestBody.setIncludeAllPolicyTypes(true);

    UseSubmissionsTo submissionsAPI = new UseSubmissionsTo();

    bob.attemptsTo(
        submissionsAPI.POSTSubmissionsTransactionsSearchOnTheSubmissionsController(
            requestBody, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<PolicyTransactionResponse> transactionResponse =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getList("", PolicyTransactionResponse.class);

    assertThat(transactionResponse.size()).isEqualTo(2);
    assertThat(transactionResponse.get(0).getPolicyId()).isEqualTo(submissionGUID);
    assertThat(transactionResponse.get(0).getTransactionType())
        .isEqualTo(submissionTransactionType);
    assertThat(transactionResponse.get(0).getDescription()).isEqualTo(submissionDescription);
    assertThat(transactionResponse.get(1).getPolicyId()).isEqualTo(policyGUID);
    assertThat(transactionResponse.get(1).getTransactionType()).isEqualTo(policyTransactionType);
    assertThat(transactionResponse.get(1).getDescription()).isEqualTo(policyDescription);
  }
}
