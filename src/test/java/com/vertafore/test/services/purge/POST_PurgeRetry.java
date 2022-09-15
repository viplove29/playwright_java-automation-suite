package com.vertafore.test.services.purge;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UsePurgeTo;
import com.vertafore.test.util.PolicyUtil;
import com.vertafore.test.util.PurgeUtil;
import com.vertafore.test.util.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_PurgeRetry extends TokenSuperClass {

  @Test
  public void postPurgeRetry() throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");

    UsePurgeTo purgeAPI = new UsePurgeTo();

    // Purge Policy Search Object
    PurgePolicySearchPostRequest purgePolicySearchPostRequest = new PurgePolicySearchPostRequest();

    Map<String, String> fiscalEndDateAndDivisionCode =
        PurgeUtil.getPurgeFiscalEndDateAndDivisionCode(AADM_User, purgePolicySearchPostRequest);

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

    PurgePolicyDeletePostRequest purgePolicyDeletePostRequest = new PurgePolicyDeletePostRequest();

    //     Set Fiscal End Date and Division Code in Purge Policies Delete Object
    //     Fiscal End Date only needs to be the Year
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

    String purgeSessionId = PurgeUtil.purgePolicy(AADM_User, purgePolicyDeletePostRequest);

    // Wait for purge process to complete
    PurgeUtil.waitForPurgeProcessToComplete(AADM_User);

    // Purge Session Post Request object
    PurgeSessionPostRequest purgeSessionPostRequest = new PurgeSessionPostRequest();

    // Set purge session id in Purge Retry Object
    purgeSessionPostRequest.setPurgeSessionId(purgeSessionId);

    // Make call to Purge Retry. Status code will be 400 since purge delete will be complete
    AADM_User.attemptsTo(purgeAPI.POSTPurgeRetryOnThePurgeController(purgeSessionPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponse(
        "Purge Session ID provided is fully purged, session cannot be retried", AADM_User);
  }
}
