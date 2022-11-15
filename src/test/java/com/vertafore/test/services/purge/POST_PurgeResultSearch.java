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
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_PurgeResultSearch extends TokenSuperClass {

  @Test
  public void postPurgeResultSearch() throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    AppLockUtil.releaseAllPolicyApplicationLocks(AADM_User);

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
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);

    PurgeSessionResponse purgeSessionResponseORAN =
        LastResponse.received()
            .answeredBy(ORAN_App)
            .getBody()
            .jsonPath()
            .getObject("", PurgeSessionResponse.class);

    // Purge Result Post Request Object
    PurgeResultPostRequest purgeResultPostRequestORAN = new PurgeResultPostRequest();

    // Add session ID to Purge Results Post Request Object
    purgeResultPostRequestORAN.setPurgeSessionId(purgeSessionResponseORAN.getPurgeSessionId());

    //  Paging Request Purge Result Post Request object
    PagingRequestPurgeResultPostRequest pagingRequestPurgeResultPostRequestORAN =
        new PagingRequestPurgeResultPostRequest();

    // Add Purge Result Post Request Model to Paging Request object
    pagingRequestPurgeResultPostRequestORAN.setModel(purgeResultPostRequestORAN);

    // Set Skip to 0, Top and Total to 1000
    pagingRequestPurgeResultPostRequestORAN.setSkip(0);
    pagingRequestPurgeResultPostRequestORAN.setTotalRecords(1000);
    pagingRequestPurgeResultPostRequestORAN.setTotalRecords(1000);

    // Make call to Purge Results Search
    ORAN_App.attemptsTo(
        purgeAPI.POSTPurgeResultsSearchOnThePurgeController(
            pagingRequestPurgeResultPostRequestORAN, ""));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);

    // Make call to Purge Results Search for VADM_Admin
    VADM_Admin.attemptsTo(
        purgeAPI.POSTPurgeResultsSearchOnThePurgeController(
            pagingRequestPurgeResultPostRequestORAN, ""));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // Make call to waitForPurgeProcessToComplete to wait for purge to complete running
    PurgeUtil.waitForPurgeProcessToComplete(AADM_User);

    // Make Call to Purge Policy Delete for AADM user
    AADM_User.attemptsTo(
        purgeAPI.POSTPurgePoliciesDeleteOnThePurgeController(
            purgePolicyDeletePostRequestADMIN, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PurgeSessionResponse purgeSessionResponseADMIN =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PurgeSessionResponse.class);

    // Purge Result Post Request Object
    PurgeResultPostRequest purgeResultPostRequestADMIN = new PurgeResultPostRequest();

    // Add session ID to Purge Results Post Request Object
    purgeResultPostRequestADMIN.setPurgeSessionId(purgeSessionResponseADMIN.getPurgeSessionId());

    //  Paging Request Purge Result Post Request object
    PagingRequestPurgeResultPostRequest pagingRequestPurgeResultPostRequest =
        new PagingRequestPurgeResultPostRequest();

    // Add Purge Result Post Request Model to Paging Request object
    pagingRequestPurgeResultPostRequest.setModel(purgeResultPostRequestADMIN);

    // Set Skip to 0, Top and Total to 1000
    pagingRequestPurgeResultPostRequest.setSkip(0);
    pagingRequestPurgeResultPostRequest.setTotalRecords(1000);
    pagingRequestPurgeResultPostRequest.setTotalRecords(1000);

    // Make call to Purge Results Search
    AADM_User.attemptsTo(
        purgeAPI.POSTPurgeResultsSearchOnThePurgeController(
            pagingRequestPurgeResultPostRequest, ""));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Purge Result Summary Response
    PagingResponsePurgeResultSummaryResponse pagingResponsePurgeResultSummaryResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponsePurgeResultSummaryResponse.class);

    assertThat(pagingResponsePurgeResultSummaryResponse != null).isTrue();
    assertThat(pagingResponsePurgeResultSummaryResponse.getClass().getDeclaredFields().length)
        .isEqualTo(4);
    assertThat(pagingResponsePurgeResultSummaryResponse.getClass().getDeclaredFields()[0].getName())
        .isEqualTo("response");
    assertThat(pagingResponsePurgeResultSummaryResponse.getClass().getDeclaredFields()[1].getName())
        .isEqualTo("nextPageLink");
    assertThat(pagingResponsePurgeResultSummaryResponse.getClass().getDeclaredFields()[2].getName())
        .isEqualTo("nextPageRequestBody");
    assertThat(pagingResponsePurgeResultSummaryResponse.getClass().getDeclaredFields()[3].getName())
        .isEqualTo("totalCount");

    PurgeResultSummaryResponse purgeResultSummaryResponse =
        pagingResponsePurgeResultSummaryResponse.getResponse().get(0);

    assertThat(purgeResultSummaryResponse.getClass().getDeclaredFields().length).isEqualTo(15);
    assertThat(purgeResultSummaryResponse.getClass().getDeclaredFields()[0].getName())
        .isEqualTo("purgeSessionId");
    assertThat(purgeResultSummaryResponse.getClass().getDeclaredFields()[1].getName())
        .isEqualTo("purgeEntityType");
    assertThat(purgeResultSummaryResponse.getClass().getDeclaredFields()[2].getName())
        .isEqualTo("purgeStatus");
    assertThat(purgeResultSummaryResponse.getClass().getDeclaredFields()[3].getName())
        .isEqualTo("purgeInitiator");
    assertThat(purgeResultSummaryResponse.getClass().getDeclaredFields()[4].getName())
        .isEqualTo("purgeInitiatorName");
    assertThat(purgeResultSummaryResponse.getClass().getDeclaredFields()[5].getName())
        .isEqualTo("purgeInitiatedDate");
    assertThat(purgeResultSummaryResponse.getClass().getDeclaredFields()[6].getName())
        .isEqualTo("purgeCompletionDate");
    assertThat(purgeResultSummaryResponse.getClass().getDeclaredFields()[7].getName())
        .isEqualTo("purgeRangeStartDate");
    assertThat(purgeResultSummaryResponse.getClass().getDeclaredFields()[8].getName())
        .isEqualTo("purgeRangeEndDate");
    assertThat(purgeResultSummaryResponse.getClass().getDeclaredFields()[9].getName())
        .isEqualTo("remainingCount");
    assertThat(purgeResultSummaryResponse.getClass().getDeclaredFields()[10].getName())
        .isEqualTo("totalCount");
    assertThat(purgeResultSummaryResponse.getClass().getDeclaredFields()[11].getName())
        .isEqualTo("successCount");
    assertThat(purgeResultSummaryResponse.getClass().getDeclaredFields()[12].getName())
        .isEqualTo("errorCount");
    assertThat(purgeResultSummaryResponse.getClass().getDeclaredFields()[13].getName())
        .isEqualTo("itemsPurged");
    assertThat(purgeResultSummaryResponse.getClass().getDeclaredFields()[14].getName())
        .isEqualTo("errorItems");

    // Make call to waitForPurgeProcessToComplete to wait for purge to complete running
    PurgeUtil.waitForPurgeProcessToComplete(AADM_User);
  }
}
