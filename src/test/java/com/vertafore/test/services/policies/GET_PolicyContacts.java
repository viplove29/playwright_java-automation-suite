package com.vertafore.test.services.policies;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.BasicPolicyInfoResponse;
import com.vertafore.test.servicewrappers.UsePolicyTo;
import com.vertafore.test.util.PolicyUtil;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class GET_PolicyContacts extends TokenSuperClass {

  @Test
  public void getPolicyContactsBaselineTest() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UsePolicyTo policyAPI = new UsePolicyTo();

    BasicPolicyInfoResponse policyInfoResponse = PolicyUtil.selectRandomPolicy(AADM_User, "policy");
    String policyId = policyInfoResponse.getPolicyId();

    AADM_User.attemptsTo(
        policyAPI.GETPolicyContactsOnThePoliciesController(policyId, "", true, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ORAN_App.attemptsTo(policyAPI.GETPolicyContactsOnThePoliciesController(policyId, "", true, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        policyAPI.GETPolicyContactsOnThePoliciesController(policyId, "", true, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }
}
