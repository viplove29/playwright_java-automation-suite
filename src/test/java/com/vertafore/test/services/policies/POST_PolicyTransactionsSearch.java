package com.vertafore.test.services.policies;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseInvoicesTo;
import com.vertafore.test.servicewrappers.UsePoliciesTo;
import com.vertafore.test.util.CustomerUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_PolicyTransactionsSearch extends TokenSuperClass {

  @Test
  public void PolicyTransactionsSearchReturnsPolicyTransactions() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    // Get all customers
    List<CustomerBasicInfoResponse> customerResponse = CustomerUtil.getAllCustomers(AADM_User);

    List<String> customerIds =
        customerResponse
            .stream()
            .map(CustomerBasicInfoResponse::getCustomerId)
            .collect(Collectors.toList());

    // Iterate each customer and find the first customer with transaction invoices
    for (String custId : customerIds) {
      UseInvoicesTo invoicesApi = new UseInvoicesTo();
      InvoicesSearchFilterPostRequest invoicesSearchFilterPostRequest =
          new InvoicesSearchFilterPostRequest();
      invoicesSearchFilterPostRequest.setCustomerId(custId);
      // search for invoices for the customer
      ORAN_App.attemptsTo(
          invoicesApi.POSTInvoicesOnTheInvoicesController(invoicesSearchFilterPostRequest, ""));
      List<InvoiceResponse> invoiceResponse =
          LastResponse.received()
              .answeredBy(ORAN_App)
              .getBody()
              .jsonPath()
              .getList("", InvoiceResponse.class);
      if (!invoiceResponse.isEmpty()) {
        // There could be multiple policies. Iterate policies and find the policy with transactions
        List<InvoiceResponse> invoicesWithTransactions =
            invoiceResponse
                .stream()
                .filter(e -> !e.getInvoiceTransactions().isEmpty())
                .collect(Collectors.toList());
        if (!invoicesWithTransactions.isEmpty()) {
          // get the policyId for the transaction
          String policyId = invoicesWithTransactions.get(0).getPolicyId();

          // Do transaction search for the policy
          UsePoliciesTo policiesApi = new UsePoliciesTo();

          PoliciesTransactionsSearchPostRequest searchRequest =
              new PoliciesTransactionsSearchPostRequest();
          List<String> policyIdsList = new ArrayList<>();
          policyIdsList.add(policyId);
          searchRequest.setPolicyIds(policyIdsList);
          searchRequest.setIncludeAllPolicyTypes(true);

          ORAN_App.attemptsTo(
              policiesApi.POSTPoliciesTransactionsSearchOnThePoliciesController(searchRequest, ""));
          assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

          VADM_Admin.attemptsTo(
              policiesApi.POSTPoliciesTransactionsSearchOnThePoliciesController(searchRequest, ""));
          assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

          AADM_User.attemptsTo(
              policiesApi.POSTPoliciesTransactionsSearchOnThePoliciesController(searchRequest, ""));
          assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

          List<PolicyTransactionResponse> policyTransactionResponses =
              LastResponse.received()
                  .answeredBy(AADM_User)
                  .getBody()
                  .jsonPath()
                  .getList("", PolicyTransactionResponse.class);

          // Response body format assertions
          assertThat(policyTransactionResponses != null).isTrue();
          assertThat(policyTransactionResponses.isEmpty()).isFalse();
          PolicyTransactionResponse policyTransactionResponse = policyTransactionResponses.get(0);
          assertThat(policyTransactionResponse.getClass().getDeclaredFields().length).isEqualTo(17);
          assertThat(policyTransactionResponse.getClass().getDeclaredFields()[0].getName())
              .isEqualTo("policyId");
          assertThat(policyTransactionResponse.getClass().getDeclaredFields()[1].getName())
              .isEqualTo("transactionEffectiveDate");
          assertThat(policyTransactionResponse.getClass().getDeclaredFields()[2].getName())
              .isEqualTo("transactionType");
          assertThat(policyTransactionResponse.getClass().getDeclaredFields()[3].getName())
              .isEqualTo("description");
          assertThat(policyTransactionResponse.getClass().getDeclaredFields()[4].getName())
              .isEqualTo("enteredDate");
          assertThat(policyTransactionResponse.getClass().getDeclaredFields()[5].getName())
              .isEqualTo("source");
          assertThat(policyTransactionResponse.getClass().getDeclaredFields()[6].getName())
              .isEqualTo("billedNonPremium");
          assertThat(policyTransactionResponse.getClass().getDeclaredFields()[7].getName())
              .isEqualTo("isUploaded");
          assertThat(policyTransactionResponse.getClass().getDeclaredFields()[8].getName())
              .isEqualTo("billMethod");
          assertThat(policyTransactionResponse.getClass().getDeclaredFields()[9].getName())
              .isEqualTo("installmentDay");
          assertThat(policyTransactionResponse.getClass().getDeclaredFields()[10].getName())
              .isEqualTo("paymentPlanId");
          assertThat(policyTransactionResponse.getClass().getDeclaredFields()[11].getName())
              .isEqualTo("reasonForCancellation");
          assertThat(policyTransactionResponse.getClass().getDeclaredFields()[12].getName())
              .isEqualTo("replaceDateBinder");
          assertThat(policyTransactionResponse.getClass().getDeclaredFields()[13].getName())
              .isEqualTo("binderReplaceEffectiveDate");
          assertThat(policyTransactionResponse.getClass().getDeclaredFields()[14].getName())
              .isEqualTo("premiumToBillOnEffectiveDate");
          assertThat(policyTransactionResponse.getClass().getDeclaredFields()[15].getName())
              .isEqualTo("isPosted");
          assertThat(policyTransactionResponse.getClass().getDeclaredFields()[16].getName())
              .isEqualTo("estimatedRevenuePercent");
          return;
        }
      }
    }
  }
}
