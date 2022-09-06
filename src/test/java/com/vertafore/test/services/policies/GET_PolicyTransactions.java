package com.vertafore.test.services.policies;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UsePoliciesTo;
import com.vertafore.test.servicewrappers.UsePolicyTo;
import com.vertafore.test.util.PolicyUtil;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_PolicyTransactions extends TokenSuperClass {

  @Test
  public void PoliciesTransactionsReturnsAllPolicyTransactions() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    List<BasicPolicyInfoResponse> policies = PolicyUtil.getAllPolicies(AADM_User, "all");

    List<String> policyIds =
        policies.stream().map(BasicPolicyInfoResponse::getPolicyId).collect(Collectors.toList());

    UsePolicyTo policyApi = new UsePolicyTo();
    UsePoliciesTo policiesApi = new UsePoliciesTo();

    PoliciesTransactionsSearchPostRequest searchRequest =
        new PoliciesTransactionsSearchPostRequest();
    searchRequest.setPolicyIds(policyIds);
    searchRequest.setIncludeAllPolicyTypes(true);

    ORAN_App.attemptsTo(
        policiesApi.POSTPoliciesTransactionsSearchOnThePoliciesController(searchRequest, ""));
    List<PolicyTransactionResponse> policyTransactionSearchResponse =
        LastResponse.received()
            .answeredBy(ORAN_App)
            .getBody()
            .jsonPath()
            .getList("", PolicyTransactionResponse.class);

    Random random = new Random();
    int randomInt = random.nextInt(policyTransactionSearchResponse.size());
    PolicyTransactionResponse randomTransaction = policyTransactionSearchResponse.get(randomInt);

    /*Create a list of variables to store a random selection from the list of transactions call*/
    String randPolicyId = randomTransaction.getPolicyId();
    String randTransactionEffectiveDate = randomTransaction.getTransactionEffectiveDate();
    String randTransactionType = randomTransaction.getTransactionType();
    String randDescription = randomTransaction.getDescription();
    String randEnteredDate = randomTransaction.getEnteredDate();
    String randSource = randomTransaction.getSource();
    Double randBilledNonPremium = randomTransaction.getBilledNonPremium();
    String randIsUploaded = randomTransaction.getIsUploaded();
    String randBillMethod = randomTransaction.getBillMethod();
    int randInstallmentDay = randomTransaction.getInstallmentDay();
    String randPaymentPlanId = randomTransaction.getPaymentPlanId();
    String randReasonForCancellation = randomTransaction.getReasonForCancellation();
    String randReplaceDateBinder = randomTransaction.getReplaceDateBinder();
    String randBinderReplaceEffectiveDate = randomTransaction.getBinderReplaceEffectiveDate();
    Double randPremiumToBillOnEffectiveDate = randomTransaction.getPremiumToBillOnEffectiveDate();
    String randIsPosted = randomTransaction.getIsPosted();
    Double randEstimatedRevenuePercent = randomTransaction.getEstimatedRevenuePercent();

    ORAN_App.attemptsTo(policyApi.GETPolicyTransactionsOnThePoliciesController(randPolicyId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(policyApi.GETPolicyTransactionsOnThePoliciesController(randPolicyId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(policyApi.GETPolicyTransactionsOnThePoliciesController(randPolicyId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<PolicyTransactionResponse> policyTransactionResponseList =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", PolicyTransactionResponse.class);

    // Find the matching transaction with the selected rand policy id. Use the combination of policy
    // id and transaction
    // effective data to identify the transaction.
    PolicyTransactionResponse policyTransactionResponse =
        policyTransactionResponseList
            .stream()
            .filter(
                transactionResponse ->
                    transactionResponse.getPolicyId().equals(randPolicyId)
                        && transactionResponse
                            .getTransactionEffectiveDate()
                            .equals(randTransactionEffectiveDate))
            .collect(Collectors.toList())
            .get(0);

    // Response body format assertions
    assertThat(policyTransactionResponse != null).isTrue();
    assertThat(policyTransactionResponse.getClass().getDeclaredFields().length).isEqualTo(17);

    // Response body field data assertions
    assertThat(policyTransactionResponse.getPolicyId()).isEqualTo(randPolicyId);
    assertThat(policyTransactionResponse.getTransactionEffectiveDate())
        .isEqualTo(randTransactionEffectiveDate);
    assertThat(policyTransactionResponse.getTransactionType()).isEqualTo(randTransactionType);
    assertThat(policyTransactionResponse.getDescription()).isEqualTo(randDescription);
    assertThat(policyTransactionResponse.getEnteredDate()).isEqualTo(randEnteredDate);
    assertThat(policyTransactionResponse.getSource()).isEqualTo(randSource);
    assertThat(policyTransactionResponse.getBilledNonPremium()).isEqualTo(randBilledNonPremium);
    assertThat(policyTransactionResponse.getIsUploaded()).isEqualTo(randIsUploaded);
    assertThat(policyTransactionResponse.getBillMethod()).isEqualTo(randBillMethod);
    assertThat(policyTransactionResponse.getInstallmentDay()).isEqualTo(randInstallmentDay);
    assertThat(policyTransactionResponse.getPaymentPlanId()).isEqualTo(randPaymentPlanId);
    assertThat(policyTransactionResponse.getReasonForCancellation())
        .isEqualTo(randReasonForCancellation);
    assertThat(policyTransactionResponse.getReplaceDateBinder()).isEqualTo(randReplaceDateBinder);
    assertThat(policyTransactionResponse.getBinderReplaceEffectiveDate())
        .isEqualTo(randBinderReplaceEffectiveDate);
    assertThat(policyTransactionResponse.getPremiumToBillOnEffectiveDate())
        .isEqualTo(randPremiumToBillOnEffectiveDate);
    assertThat(policyTransactionResponse.getIsPosted()).isEqualTo(randIsPosted);
    assertThat(policyTransactionResponse.getEstimatedRevenuePercent())
        .isEqualTo(randEstimatedRevenuePercent);
  }
}
