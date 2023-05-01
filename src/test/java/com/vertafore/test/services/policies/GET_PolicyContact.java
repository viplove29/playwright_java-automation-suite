package com.vertafore.test.services.policies;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.servicewrappers.UsePolicyTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class GET_PolicyContact extends TokenSuperClass {

  @Test
  public void getPolicyContactBaselineTest() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UsePolicyTo policyAPI = new UsePolicyTo();

    String randomPolicyContactId = "fc347580-7150-4e65-8d07-71da103cd962";

    AADM_User.attemptsTo(
        policyAPI.GETPolicyContactOnThePoliciesController(randomPolicyContactId, "", true, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ORAN_App.attemptsTo(
        policyAPI.GETPolicyContactOnThePoliciesController(randomPolicyContactId, "", true, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        policyAPI.GETPolicyContactOnThePoliciesController(randomPolicyContactId, "", true, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }
}
