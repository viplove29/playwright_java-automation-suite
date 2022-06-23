package com.vertafore.test.services.suspenses;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseSuspensesTo;
import com.vertafore.test.util.EmployeeUtil;
import com.vertafore.test.util.PolicyUtil;
import com.vertafore.test.util.SuspenseUtil;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_SuspensesPolicy extends TokenSuperClass {

  @Test
  public void ccEmployeeCodesCanBeAddedWithoutError() {
    Actor AADM_User = theActorCalled("AADM_User");

    // retrieve data necessary for suspense
    BasicPolicyInfoResponse randomPolicy = PolicyUtil.selectRandomPolicy(AADM_User, "policy");
    String policyId = randomPolicy.getPolicyId();
    String empCode = randomPolicy.getExecutiveCode();
    String customerId = randomPolicy.getCustomerId();

    UseSuspensesTo suspensesApi = new UseSuspensesTo();

    PolicySuspenseCollectionPostRequest request =
        SuspenseUtil.createMultipleRandomPolicySuspensesTiedToCustomer(
            empCode, customerId, policyId, 1, AADM_User);
    request
        .getPolicySuspenses()
        .get(0)
        .addCcEmployeeCodesItem(
            EmployeeUtil.getRandomExec(AADM_User)
                .getEmpCode()); // add an empCode to the ccEmployeeCodes list

    AADM_User.attemptsTo(suspensesApi.POSTSuspensesPolicyOnTheSuspensesController(request, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<MultiSuspenseIdResponse> suspenseIdResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", MultiSuspenseIdResponse.class);

    assertThat(suspenseIdResponse.get(0).getSuspenseId()).isNotNull();
  }
}
