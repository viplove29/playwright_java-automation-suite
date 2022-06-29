package com.vertafore.test.services.service_agreements;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.BasicPolicyInfoResponse;
import com.vertafore.test.models.ems.PoliciesTransactionsSearchPostRequest;
import com.vertafore.test.models.ems.PolicyTransactionResponse;
import com.vertafore.test.servicewrappers.UseServiceAgreementsTo;
import com.vertafore.test.util.*;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_ServiceAgreementsTransactionsSearch extends TokenSuperClass {

  @Test
  public void serviceAgreementsTransactionsSearchFiltersBySecuredCustomerAccess() {
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor AADM_User = theActorCalled("AADM_User");

    String serviceEmployeeEmpCode = AuthGroupUtility.getCurrentServiceEmployeeEmpCode(AADM_User);

    // give Service Employee access to all secured customer Ids
    // have to iterate through all of them because not every customer might have a policy
    List<String> securedCustomerIds = CustomerUtil.getAllSecuredCustomerIds(AADM_User);
    EmployeeUtil.insertMultipleSecuredCustomerAccessesForEmployee(
        AADM_User, serviceEmployeeEmpCode, securedCustomerIds);

    // get a random policy attributed to a secured customer
    BasicPolicyInfoResponse randomSecuredPolicy;
    String randomSecuredPolicyId = "";
    try {
      randomSecuredPolicy = PolicyUtil.getRandomSecuredCustomerPolicy(ORAN_App, securedCustomerIds);
      randomSecuredPolicyId = randomSecuredPolicy.getPolicyId();

    } catch (NullPointerException e) {
      System.out.println(
          "no policies found within list of secured customers, need to stage data in environment");
    }

    PoliciesTransactionsSearchPostRequest postRequest = new PoliciesTransactionsSearchPostRequest();
    postRequest.setIncludeAllPolicyTypes(true);
    postRequest.addPolicyIdsItem(randomSecuredPolicyId);

    UseServiceAgreementsTo serviceAgreementsApi = new UseServiceAgreementsTo();

    // call successful
    ORAN_App.attemptsTo(
        serviceAgreementsApi
            .POSTServiceAgreementsTransactionsSearchOnTheServiceagreementsController(
                postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PolicyTransactionResponse response =
        LastResponse.received()
            .answeredBy(ORAN_App)
            .getBody()
            .jsonPath()
            .getList("", PolicyTransactionResponse.class)
            .get(0);

    assertThat(response.getPolicyId()).isEqualTo(randomSecuredPolicyId);

    // delete secured customer access to employee
    EmployeeUtil.deleteMultipleSecuredCustomerAccessForEmployee(
        AADM_User, serviceEmployeeEmpCode, securedCustomerIds);

    // call unsuccessful
    ORAN_App.attemptsTo(
        serviceAgreementsApi
            .POSTServiceAgreementsTransactionsSearchOnTheServiceagreementsController(
                postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponse(
        "User does not have access to 1 or more of the provided policies.", ORAN_App);
  }
}
