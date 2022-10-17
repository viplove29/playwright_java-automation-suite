package com.vertafore.test.services.purge;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UsePurgeTo;
import com.vertafore.test.util.AppLockUtil;
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
public class POST_PurgeResultsMarkComplete extends TokenSuperClass {

  @Test
  public void postPurgeResultMarkComplete() throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");

    UsePurgeTo purgeAPI = new UsePurgeTo();

    AppLockUtil.releaseAllPolicyApplicationLocks(AADM_User);

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

    // Set Policy ID in purgePolicyDelete Object
    List<String> purgePolicyIDs = new ArrayList<>();
    purgePolicyIDs.add(purgePolicyCandidateResponseList.get(0).getPolicyId());
    purgePolicyDeletePostRequest.policyIds(purgePolicyIDs);

    String purgeSessionId = PurgeUtil.purgePolicy(AADM_User, purgePolicyDeletePostRequest);

    // Make call to waitForPurgeProcessToComplete to wait for purge to complete running
    PurgeUtil.waitForPurgeProcessToComplete(AADM_User);

    // Purge Session Post Request object
    PurgeSessionPostRequest purgeSessionPostRequest = new PurgeSessionPostRequest();

    // Set session ID in Purge Header Post Request Object
    purgeSessionPostRequest.setPurgeSessionId(purgeSessionId);

    // Make call to Purge Results Mark complete
    AADM_User.attemptsTo(
        purgeAPI.POSTPurgeResultsMarkCompleteOnThePurgeController(purgeSessionPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PurgeSessionResponse purgeSessionResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PurgeSessionResponse.class);

    assertThat(purgeSessionResponse != null).isTrue();
    assertThat(purgeSessionResponse.getClass().getDeclaredFields().length).isEqualTo(1);
    assertThat(purgeSessionResponse.getPurgeSessionId()).isEqualTo(purgeSessionId);
  }
}
