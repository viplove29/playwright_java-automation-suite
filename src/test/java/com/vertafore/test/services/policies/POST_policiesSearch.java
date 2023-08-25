package com.vertafore.test.services.policies;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.BasicPolicyInfoResponse;
import com.vertafore.test.models.ems.PagingRequestPoliciesSearchPostRequest;
import com.vertafore.test.models.ems.PagingResponseBasicPolicyInfoResponse;
import com.vertafore.test.models.ems.PoliciesSearchPostRequest;
import com.vertafore.test.servicewrappers.UsePoliciesTo;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_policiesSearch extends TokenSuperClass {

  @Test
  public void postPoliciesSearchBaselineTest() {
    int basicPolicyInfoNumFields = 23;
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");
    PagingRequestPoliciesSearchPostRequest pageSearch =
        new PagingRequestPoliciesSearchPostRequest();
    PoliciesSearchPostRequest polPostBody = new PoliciesSearchPostRequest();

    polPostBody.setIsCurrentlyInForce(false);
    polPostBody.setIncludeAllPolicyTypes(true);
    pageSearch.setModel(polPostBody);
    pageSearch.setTop(100);
    UsePoliciesTo policiesAPI = new UsePoliciesTo();

    VADM_Admin.attemptsTo(
        (policiesAPI.POSTPoliciesSearchOnThePoliciesController(pageSearch, "string")));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        (policiesAPI.POSTPoliciesSearchOnThePoliciesController(pageSearch, "string")));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        (policiesAPI.POSTPoliciesSearchOnThePoliciesController(pageSearch, "string")));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<BasicPolicyInfoResponse> policyList =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseBasicPolicyInfoResponse.class)
            .getResponse();

    assertThat(policyList).isNotEmpty();
    assertThat(policyList.get(0)).isNotNull();
    assertThat(policyList.get(0).getClass().getDeclaredFields().length)
        .isEqualTo(basicPolicyInfoNumFields);
    assertThat(policyList.get(0).getPolicyId()).isNotNull();
    assertThat(policyList.get(0).getRenewalReportStatus()).isNotNull();
    assertThat(policyList.get(0).getRenewalTermCount()).isGreaterThan(-1);
    assertThat(policyList.get(0).getPolicyNumber()).isNotNull();
    assertThat(policyList.get(0).getExecutiveCode()).isNotNull();
    assertThat(policyList.get(0).getPolicyEffectiveDate()).isNotNull();
    assertThat(policyList.get(0).getPolicyExpirationDate()).isNotNull();
  }
}
