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
public class POST_PurgeResultsDetailSearch extends TokenSuperClass {

  @Test
  public void postPurgeResultsDetailSearch() throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

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

    // Purge Policy Delete Object
    PurgePolicyDeletePostRequest purgePolicyDeletePostRequestADMIN =
        new PurgePolicyDeletePostRequest();
    PurgePolicyDeletePostRequest purgePolicyDeletePostRequestORAN =
        new PurgePolicyDeletePostRequest();

    // Set Fiscal End Date and Division Code in Purge Policies Delete Object
    // Fiscal End Date only needs to be the Year
    purgePolicyDeletePostRequestADMIN.setFiscalYear(
        fiscalEndDateAndDivisionCode.get("fiscalEndDate"));
    purgePolicyDeletePostRequestORAN.setFiscalYear(
        fiscalEndDateAndDivisionCode.get("fiscalEndDate"));

    purgePolicyDeletePostRequestADMIN.setDivision(fiscalEndDateAndDivisionCode.get("divisionCode"));
    purgePolicyDeletePostRequestORAN.setDivision(fiscalEndDateAndDivisionCode.get("divisionCode"));

    // Check if policy exist
    BasicPolicyInfoResponse basicPolicyInfoResponseAADM =
        PolicyUtil.getPolicyById(AADM_User, purgePolicyCandidateResponseList.get(0).getPolicyId());
    assertThat(basicPolicyInfoResponseAADM).isNotNull();

    // Check if policy exist
    BasicPolicyInfoResponse basicPolicyInfoResponseORAN =
        PolicyUtil.getPolicyById(AADM_User, purgePolicyCandidateResponseList.get(1).getPolicyId());
    assertThat(basicPolicyInfoResponseORAN).isNotNull();

    // Set Policy ID in purgePolicyDelete Object
    List<String> purgePolicyIDsAdmin = new ArrayList<>();
    purgePolicyIDsAdmin.add(purgePolicyCandidateResponseList.get(0).getPolicyId());
    purgePolicyDeletePostRequestADMIN.policyIds(purgePolicyIDsAdmin);

    List<String> purgePolicyIDsORAN = new ArrayList<>();
    purgePolicyIDsORAN.add(purgePolicyCandidateResponseList.get(1).getPolicyId());
    purgePolicyDeletePostRequestORAN.policyIds(purgePolicyIDsORAN);

    // Make Call to Purge Policy Delete
    VADM_Admin.attemptsTo(
        purgeAPI.POSTPurgePoliciesDeleteOnThePurgeController(
            purgePolicyDeletePostRequestADMIN, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    ORAN_App.attemptsTo(
        purgeAPI.POSTPurgePoliciesDeleteOnThePurgeController(purgePolicyDeletePostRequestORAN, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Call waitForPurgeProcessToComplete method for ORAN_App user before making Purge Policies
    // Delete Call for AADM_User
    PurgeUtil.waitForPurgeProcessToComplete(ORAN_App);

    AADM_User.attemptsTo(
        purgeAPI.POSTPurgePoliciesDeleteOnThePurgeController(
            purgePolicyDeletePostRequestADMIN, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Wait for Purge Policy to complete
    PurgeUtil.waitForPurgeProcessToComplete(AADM_User);

    PurgeSessionResponse purgeSessionResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PurgeSessionResponse.class);

    // Purge Result Search Post Request object
    PurgeResultSearchPostRequest purgeResultSearchPostRequest = new PurgeResultSearchPostRequest();

    // Set the purgeSessionID
    purgeResultSearchPostRequest.setPurgeSessionId(purgeSessionResponse.getPurgeSessionId());

    // Paging Request Purge Results Search Post Request object
    PagingRequestPurgeResultSearchPostRequest pagingRequestPurgeResultSearchPostRequest =
        new PagingRequestPurgeResultSearchPostRequest();

    // Add Purge Result Search Post Request model to Paging Request Purge Results Search Post
    // Request
    pagingRequestPurgeResultSearchPostRequest.setModel(purgeResultSearchPostRequest);

    // Make call to purge/results-detail/search
    AADM_User.attemptsTo(
        purgeAPI.POSTPurgeResultsDetailSearchOnThePurgeController(
            pagingRequestPurgeResultSearchPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PagingResponsePurgeResultDetailResponse pagingResponsePurgeResultDetailResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponsePurgeResultDetailResponse.class);

    assertThat(pagingResponsePurgeResultDetailResponse).isNotNull();
    assertThat(pagingResponsePurgeResultDetailResponse.getClass().getDeclaredFields().length)
        .isEqualTo(4);
    assertThat(pagingResponsePurgeResultDetailResponse.getClass().getDeclaredFields()[0].getName())
        .isEqualTo("response");
    assertThat(pagingResponsePurgeResultDetailResponse.getClass().getDeclaredFields()[1].getName())
        .isEqualTo("nextPageLink");
    assertThat(pagingResponsePurgeResultDetailResponse.getClass().getDeclaredFields()[2].getName())
        .isEqualTo("nextPageRequestBody");
    assertThat(pagingResponsePurgeResultDetailResponse.getClass().getDeclaredFields()[3].getName())
        .isEqualTo("totalCount");

    PurgeResultDetailResponse purgeResultDetailResponse =
        pagingResponsePurgeResultDetailResponse.getResponse().get(0);

    assertThat(purgeResultDetailResponse.getClass().getDeclaredFields().length).isEqualTo(9);
    assertThat(purgeResultDetailResponse.getClass().getDeclaredFields()[0].getName())
        .isEqualTo("purgeSessionId");
    assertThat(purgeResultDetailResponse.getClass().getDeclaredFields()[1].getName())
        .isEqualTo("purgeItemDescription");
    assertThat(purgeResultDetailResponse.getClass().getDeclaredFields()[2].getName())
        .isEqualTo("purgeStatus");
    assertThat(purgeResultDetailResponse.getClass().getDeclaredFields()[3].getName())
        .isEqualTo("rejectReason");
    assertThat(purgeResultDetailResponse.getClass().getDeclaredFields()[4].getName())
        .isEqualTo("divisionName");
    assertThat(purgeResultDetailResponse.getClass().getDeclaredFields()[5].getName())
        .isEqualTo("branchName");
    assertThat(purgeResultDetailResponse.getClass().getDeclaredFields()[6].getName())
        .isEqualTo("departmentName");
    assertThat(purgeResultDetailResponse.getClass().getDeclaredFields()[7].getName())
        .isEqualTo("groupName");
    assertThat(purgeResultDetailResponse.getClass().getDeclaredFields()[8].getName())
        .isEqualTo("itemsPurged");
  }
}
