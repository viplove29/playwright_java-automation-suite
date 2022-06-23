package com.vertafore.test.services.policies;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.BasicPolicyInfoResponse;
import com.vertafore.test.models.ems.PoliciesEndorsePostRequest;
import com.vertafore.test.models.ems.PolicyEndorsePostRequest;
import com.vertafore.test.servicewrappers.UsePoliciesTo;
import com.vertafore.test.util.PolicyUtil;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.joda.time.LocalDateTime;
import org.junit.Test;

public class POST_PoliciesEndorse extends TokenSuperClass {

  @Test
  public void postPoliciesEndorsePostsPolicyEndorsements() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    BasicPolicyInfoResponse randomPolicy = PolicyUtil.selectRandomPolicy(AADM_User, "policy");
    String policyId = randomPolicy.getPolicyId();
    String transactionEffectiveDate =
        LocalDateTime.parse(randomPolicy.getPolicyEffectiveDate()).plusDays(15).toString();

    UsePoliciesTo policiesApi = new UsePoliciesTo();

    PolicyEndorsePostRequest postRequest = new PolicyEndorsePostRequest();
    postRequest.setPolicyId(policyId);
    postRequest.setTransactionEffectiveDate(transactionEffectiveDate);
    PoliciesEndorsePostRequest listRequest = new PoliciesEndorsePostRequest();
    listRequest.addPolicyEndorsementsItem(postRequest);

    AADM_User.attemptsTo(policiesApi.POSTPoliciesEndorseOnThePoliciesController(listRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ORAN_App.attemptsTo(policiesApi.POSTPoliciesEndorseOnThePoliciesController(listRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(policiesApi.POSTPoliciesEndorseOnThePoliciesController(listRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }
}
