package com.vertafore.test.services.submissions;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UsePoliciesTo;
import com.vertafore.test.servicewrappers.UseSubmissionsTo;
import com.vertafore.test.util.PolicyUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_SubmissionsTransactionsSearch extends TokenSuperClass {

  @Test
  public void submissionsTransactionsSearchSuccessfullyReturnsForAuthorizedUsers() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    // endpoint requires at least one policyId
    BasicPolicyInfoResponse randomPolicy = PolicyUtil.selectRandomPolicy(AADM_User, "submission");
    String policyId = randomPolicy.getPolicyId();
    ArrayList<String> policies = new ArrayList<>();
    policies.add(policyId);

    PoliciesTransactionsSearchPostRequest requestBody = new PoliciesTransactionsSearchPostRequest();
    requestBody.setPolicyIds(policies);
    requestBody.setIncludeAllPolicyTypes(true);

    UseSubmissionsTo submissionsAPI = new UseSubmissionsTo();

    ORAN_App.attemptsTo(
        submissionsAPI.POSTSubmissionsTransactionsSearchOnTheSubmissionsController(
            requestBody, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        submissionsAPI.POSTSubmissionsTransactionsSearchOnTheSubmissionsController(
            requestBody, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    requestBody.setIncludeAllPolicyTypes(false);
    AADM_User.attemptsTo(
        submissionsAPI.POSTSubmissionsTransactionsSearchOnTheSubmissionsController(
            requestBody, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PolicyTransactionResponse transactionResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", PolicyTransactionResponse.class)
            .get(0);

    // basic field name assertions
    assertThat(transactionResponse.getClass().getDeclaredFields().length).isEqualTo(5);
    assertThat(transactionResponse.getClass().getDeclaredFields()[0].getName())
        .isEqualTo("policyId");
    assertThat(transactionResponse.getClass().getDeclaredFields()[1].getName())
        .isEqualTo("transactionEffectiveDate");
    assertThat(transactionResponse.getClass().getDeclaredFields()[2].getName())
        .isEqualTo("transactionType");
    assertThat(transactionResponse.getClass().getDeclaredFields()[3].getName())
        .isEqualTo("description");
    assertThat(transactionResponse.getClass().getDeclaredFields()[4].getName())
        .isEqualTo("enteredDate");
  }

  @Test
  public void includeAllPoliciesFlagReturnsCorrectResponses() {
    Actor AADM_User = theActorCalled("AADM_User");

    BasicPolicyInfoResponse subPolicy = PolicyUtil.selectRandomPolicy(AADM_User, "submission");
    BasicPolicyInfoResponse regPolicy = PolicyUtil.selectRandomPolicy(AADM_User, "policy");
    String subPolId = subPolicy.getPolicyId();
    String regPolId = regPolicy.getPolicyId();
    String regPolNumber = regPolicy.getPolicyNumber();

    ArrayList<String> policies = new ArrayList<>();
    policies.add(subPolId);
    policies.add(regPolId);

    PoliciesTransactionsSearchPostRequest requestBody = new PoliciesTransactionsSearchPostRequest();
    requestBody.setPolicyIds(policies);
    requestBody.setIncludeAllPolicyTypes(true);

    UseSubmissionsTo submissionsAPI = new UseSubmissionsTo();

    // includeAllPolicies set to true should return both policies
    AADM_User.attemptsTo(
        submissionsAPI.POSTSubmissionsTransactionsSearchOnTheSubmissionsController(
            requestBody, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<PolicyTransactionResponse> twoPolsResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", PolicyTransactionResponse.class);

    assertThat(twoPolsResponse.size()).isGreaterThanOrEqualTo(2);

    // includesAllPolicies set to false should return an error for the regular policy
    requestBody.setIncludeAllPolicyTypes(false);
    AADM_User.attemptsTo(
        submissionsAPI.POSTSubmissionsTransactionsSearchOnTheSubmissionsController(
            requestBody, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    assertThat(SerenityRest.lastResponse().asString())
        .isEqualTo(
            "{\"error\":\"Unable to process Policy with ID: '"
                + regPolNumber
                + "' as type 'Submission'.\"}");

    // removing the regular policy should be successful
    policies.remove(regPolId);
    requestBody.setPolicyIds(policies);

    AADM_User.attemptsTo(
        submissionsAPI.POSTSubmissionsTransactionsSearchOnTheSubmissionsController(
            requestBody, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }

  @Test
  public void newTransactionIncludedInResponse() {
    Actor AADM_User = theActorCalled("AADM_User");

    BasicPolicyInfoResponse randomPolicy = PolicyUtil.selectRandomPolicy(AADM_User, "submission");
    String policyId = randomPolicy.getPolicyId();
    DateTime effDate = randomPolicy.getPolicyEffectiveDate();
    DateTime expDate = randomPolicy.getPolicyExpirationDate();

    ArrayList<String> policies = new ArrayList<>();
    policies.add(policyId);

    PoliciesTransactionsSearchPostRequest requestBody = new PoliciesTransactionsSearchPostRequest();
    requestBody.setPolicyIds(policies);
    requestBody.setIncludeAllPolicyTypes(true);

    UseSubmissionsTo submissionsAPI = new UseSubmissionsTo();
    UsePoliciesTo policiesAPI = new UsePoliciesTo();

    // check to see how many endorsements exist for the random policy
    AADM_User.attemptsTo(
        submissionsAPI.POSTSubmissionsTransactionsSearchOnTheSubmissionsController(
            requestBody, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    int numOfTransactions =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", PolicyTransactionResponse.class)
            .size();

    // choose a random datetime between the effective date and expiration date
    int diff = Math.abs(Minutes.minutesBetween(effDate, expDate).getMinutes());
    int randomMinutes = new Random().nextInt(diff);
    DateTime randomDate = effDate.plusMinutes(randomMinutes);

    /*TODO can't get the models to work due to a problem with accepting DateTimes. The current way works but feels hacky - I've left the original model instantiations as a guide for later.*/

    //    PolicyEndorsePostRequest policyInfo = new PolicyEndorsePostRequest();
    //    PoliciesEndorsePostRequest postBody = new PoliciesEndorsePostRequest();

    HashMap<String, Object> policyInfo = new HashMap<>();
    policyInfo.put("policyId", policyId);
    policyInfo.put("transactionEffectiveDate", randomDate.toString());

    ArrayList<HashMap> endorsements = new ArrayList<>();

    //    policyInfo.setPolicyId(policyId);
    //    policyInfo.setTransactionEffectiveDate(randomDate);
    endorsements.add(policyInfo);
    //    postBody.setPolicyEndorsements(endorsements);
    HashMap<String, Object> postBody = new HashMap<>();
    postBody.put("policyEndorsements", endorsements);

    // add a transaction to the policy
    AADM_User.attemptsTo(
        policiesAPI.POSTPoliciesEndorseOnThePoliciesController(postBody, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // confirm that one transaction was added to the policy
    AADM_User.attemptsTo(
        submissionsAPI.POSTSubmissionsTransactionsSearchOnTheSubmissionsController(
            requestBody, "string"));

    int numOfTransPlusOne =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", PolicyTransactionResponse.class)
            .size();

    assertThat(numOfTransactions + 1).isEqualTo(numOfTransPlusOne);
  }
}
