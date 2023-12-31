package com.vertafore.test.services.policies;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UsePoliciesTo;
import com.vertafore.test.util.PolicyUtil;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class PUT_PoliciesFirstNamedInsuredCopyCustomerInfo extends TokenSuperClass {

  @Test
  public void putPoliciesFirstNamedInsuredCopyCustomerInfoUpdatesOnePolicyUnderCustomer() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    // get customer that at least has one policy
    BasicPolicyInfoResponse randomPolicy = PolicyUtil.selectRandomPolicy(AADM_User, "policy");
    String customerId = randomPolicy.getCustomerId();

    UsePoliciesTo policiesApi = new UsePoliciesTo();

    ApplicantsInfoCopyFromCustomerPutRequest putRequest =
        new ApplicantsInfoCopyFromCustomerPutRequest();
    putRequest.setCustomerId(customerId);
    putRequest.addPolicyIdsItem(randomPolicy.getPolicyId());

    VADM_Admin.attemptsTo(
        policiesApi.PUTPoliciesFirstNamedInsuredCopyCustomerInfoOnThePoliciesController(
            putRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        policiesApi.PUTPoliciesFirstNamedInsuredCopyCustomerInfoOnThePoliciesController(
            putRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        policiesApi.PUTPoliciesFirstNamedInsuredCopyCustomerInfoOnThePoliciesController(
            putRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PutGenericResponse response =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PutGenericResponse.class);

    assertThat(response).isNotNull();
    assertThat(response.getNumberOfRecordsUpdated()).isNotNull();
    assertThat(response.getNumberOfRecordsUpdated()).isEqualTo(1);
  }
}
