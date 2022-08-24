package com.vertafore.test.services.purge;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UsePurgeTo;
import com.vertafore.test.util.PolicyUtil;
import com.vertafore.test.util.PurgeUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_PurgeStatus extends TokenSuperClass {

  @Test
  public void purgeStatus() throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");

    UsePurgeTo purgeAPI = new UsePurgeTo();

    // Purge Policy Search Object
    PurgePolicySearchPostRequest purgePolicySearchPostRequest = new PurgePolicySearchPostRequest();

    Map<String, String> fiscalEndDateAndDivisionCode =
        PurgeUtil.getPurgeFiscalEndDateAndDivisionCode(AADM_User, purgePolicySearchPostRequest);

    // Set Fiscal End Date Division Code in Purge Policies Search Object
    // Fiscal End Date only needs to be the Year
    purgePolicySearchPostRequest.setFiscalYear(fiscalEndDateAndDivisionCode.get("fiscalEndDate"));
    purgePolicySearchPostRequest.setDivision(fiscalEndDateAndDivisionCode.get("divisionCode"));

    // Paging Request Purge Policy Search object
    PagingRequestPurgePolicySearchPostRequest pagingRequestPurgePolicySearchPostRequest =
        new PagingRequestPurgePolicySearchPostRequest();

    // Add Purge Policy Search object to Paging Purge Policy object
    pagingRequestPurgePolicySearchPostRequest.model(purgePolicySearchPostRequest);

    // Set Skip to 0, Top and Total to 1000
    pagingRequestPurgePolicySearchPostRequest.setSkip(0);
    pagingRequestPurgePolicySearchPostRequest.setTop(1000);
    pagingRequestPurgePolicySearchPostRequest.setTotalRecords(1000);

    List<PurgePolicyCandidateResponse> purgePolicyCandidateResponseList =
        PurgeUtil.getPurgePolicyResponse(AADM_User, pagingRequestPurgePolicySearchPostRequest);

    // Purge Policy Delete Object
    PurgePolicyDeletePostRequest purgePolicyDeletePostRequest = new PurgePolicyDeletePostRequest();

    // Set Fiscal End Date and Division Code in Purge Policies Delete Object
    // Fiscal End Date only needs to be the Year
    purgePolicyDeletePostRequest.setFiscalYear(fiscalEndDateAndDivisionCode.get("fiscalEndDate"));

    purgePolicyDeletePostRequest.setDivision(fiscalEndDateAndDivisionCode.get("divisionCode"));

    // Check if policy exist
    BasicPolicyInfoResponse basicPolicyInfoResponse =
        PolicyUtil.getPolicyById(AADM_User, purgePolicyCandidateResponseList.get(0).getPolicyId());
    assertThat(basicPolicyInfoResponse).isNotNull();

    // Set Policy ID in purgePolicyDelete Object
    List<String> purgePolicyIDs = new ArrayList<>();
    purgePolicyIDs.add(purgePolicyCandidateResponseList.get(0).getPolicyId());
    purgePolicyDeletePostRequest.policyIds(purgePolicyIDs);

    AADM_User.attemptsTo(
        purgeAPI.POSTPurgePoliciesDeleteOnThePurgeController(purgePolicyDeletePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(purgeAPI.GETPurgeStatusOnThePurgeController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PurgeStatusResponse purgeStatusResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PurgeStatusResponse.class);

    assertThat(purgeStatusResponse != null).isTrue();
    assertThat(purgeStatusResponse.getClass().getDeclaredFields().length).isEqualTo(1);
    assertThat(purgeStatusResponse.getClass().getDeclaredFields()[0].getName())
        .isEqualTo("isPurgeRunning");
    assertThat(purgeStatusResponse.getIsPurgeRunning()).isTrue();

    // Call wait for purge to complete before end of test
    // This will allow the app lock clear for the next test
    PurgeUtil.waitForPurgeProcessToComplete(AADM_User);
  }
}
