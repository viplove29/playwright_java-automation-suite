package com.vertafore.test.services.suspenses;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.AgencyResponse;
import com.vertafore.test.models.ems.BasicPolicyInfoResponse;
import com.vertafore.test.models.ems.EdocsSuspensePostRequest;
import com.vertafore.test.models.ems.SuspenseIdResponse;
import com.vertafore.test.servicewrappers.UseAgencyTo;
import com.vertafore.test.servicewrappers.UseSuspenseTo;
import com.vertafore.test.util.*;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_SuspenseEdocs extends TokenSuperClass {

  @Test
  public void postSuspenseEdocsBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");
    Actor IVAN_User = theActorCalled("IVAN_User");

    UseSuspenseTo suspenseApi = new UseSuspenseTo();
    UseAgencyTo agencyApi = new UseAgencyTo();
    String agencyNo = EnvVariables.AGENCY_NO;

    IVAN_User.attemptsTo(agencyApi.GETAgencyDetailsOnTheAgencyController(agencyNo, ""));

    AgencyResponse agencyResponse =
        LastResponse.received()
            .answeredBy(IVAN_User)
            .getBody()
            .jsonPath()
            .getObject("", AgencyResponse.class);

    String globalTenantId =
        agencyResponse.getGlobalTenantId(); // '"57b4b5c6-9616-47de-8f08-1f644ef1e308";

    BasicPolicyInfoResponse randomPolicy = PolicyUtil.selectRandomPolicy(AADM_User, "all");
    EdocsSuspensePostRequest edocsSuspensePostRequest = new EdocsSuspensePostRequest();
    edocsSuspensePostRequest.setGlobalTenantId(globalTenantId);
    edocsSuspensePostRequest.setAgencyNo(agencyNo);
    edocsSuspensePostRequest.setEmpCode(randomPolicy.getCsrCode());
    edocsSuspensePostRequest.setCustomerId(randomPolicy.getCustomerId());
    edocsSuspensePostRequest.setPolId(randomPolicy.getPolicyId());
    edocsSuspensePostRequest.setDescription("e docs test");

    IVAN_User.attemptsTo(
        suspenseApi.POSTSuspenseEDocsOnTheSuspensesController(edocsSuspensePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    SuspenseIdResponse idResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", SuspenseIdResponse.class);

    assertThat(idResponse).isNotNull();
    assertThat(idResponse.getSuspenseId()).isNotNull();
    assertThat(Util.isValidGUID(idResponse.getSuspenseId())).isTrue();

    ORAN_App.attemptsTo(
        suspenseApi.POSTSuspenseEDocsOnTheSuspensesController(edocsSuspensePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString(
        "Claim:username is empty or does not exist; Claim:mailbox is empty or does not exist",
        ORAN_App);

    AADM_User.attemptsTo(
        suspenseApi.POSTSuspenseEDocsOnTheSuspensesController(edocsSuspensePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString(
        "Claim:username is empty or does not exist; Claim:mailbox is empty or does not exist",
        AADM_User);

    VADM_Admin.attemptsTo(
        suspenseApi.POSTSuspenseEDocsOnTheSuspensesController(edocsSuspensePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString(
        "Claim:username is empty or does not exist; Claim:mailbox is empty or does not exist",
        VADM_Admin);
  }
}
