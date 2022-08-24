package com.vertafore.test.services.purge;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UsePurgeTo;
import com.vertafore.test.util.PurgeUtil;
import java.util.Map;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_PurgePoliciesSearch extends TokenSuperClass {

  @Test
  public void postPurgePoliciesSearch() {
    Actor AADM_User = theActorCalled("AADM_User");

    UsePurgeTo purgeAPI = new UsePurgeTo();

    // Purge Policy Search Object
    PurgePolicySearchPostRequest purgePolicySearchPostRequest = new PurgePolicySearchPostRequest();

    Map<String, String> fiscalEndDateAndDivisionCode =
        PurgeUtil.getPurgeFiscalEndDateAndDivisionCode(AADM_User, purgePolicySearchPostRequest);

    // Set Fiscal End Date Division Code in Purge Policies Search Object
    // Fiscal End Date only needs to be the Year
    purgePolicySearchPostRequest.setFiscalYear(fiscalEndDateAndDivisionCode.get("fiscalEndDate"));

    // Paging Request Purge Policy Search object
    PagingRequestPurgePolicySearchPostRequest pagingRequestPurgePolicySearchPostRequest =
        new PagingRequestPurgePolicySearchPostRequest();

    // Add Purge Policy Search object to Paging Purge Policy object
    pagingRequestPurgePolicySearchPostRequest.model(purgePolicySearchPostRequest);

    // Set Skip to 0, Top and Total to 1000
    pagingRequestPurgePolicySearchPostRequest.setSkip(0);
    pagingRequestPurgePolicySearchPostRequest.setTop(1000);
    pagingRequestPurgePolicySearchPostRequest.setTotalRecords(1000);

    AADM_User.attemptsTo(
        purgeAPI.POSTPurgePoliciesSearchOnThePurgeController(
            pagingRequestPurgePolicySearchPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PagingResponsePurgePolicyCandidateResponse pagingResponsePurgePolicyCandidateResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponsePurgePolicyCandidateResponse.class);

    assertThat(pagingResponsePurgePolicyCandidateResponse).isNotNull();
    assertThat(pagingResponsePurgePolicyCandidateResponse.getClass().getDeclaredFields().length)
        .isEqualTo(4);
    assertThat(
            pagingResponsePurgePolicyCandidateResponse.getClass().getDeclaredFields()[0].getName())
        .isEqualTo("response");
    assertThat(
            pagingResponsePurgePolicyCandidateResponse.getClass().getDeclaredFields()[1].getName())
        .isEqualTo("nextPageLink");
    assertThat(
            pagingResponsePurgePolicyCandidateResponse.getClass().getDeclaredFields()[2].getName())
        .isEqualTo("nextPageRequestBody");
    assertThat(
            pagingResponsePurgePolicyCandidateResponse.getClass().getDeclaredFields()[3].getName())
        .isEqualTo("totalCount");

    PurgePolicyCandidateResponse purgePolicyCandidateResponse =
        pagingResponsePurgePolicyCandidateResponse.getResponse().get(0);

    assertThat(purgePolicyCandidateResponse).isNotNull();
    assertThat(purgePolicyCandidateResponse.getClass().getDeclaredFields().length).isEqualTo(10);
    assertThat(purgePolicyCandidateResponse.getClass().getDeclaredFields()[0].getName())
        .isEqualTo("customerName");
    assertThat(purgePolicyCandidateResponse.getClass().getDeclaredFields()[1].getName())
        .isEqualTo("customerNumber");
    assertThat(purgePolicyCandidateResponse.getClass().getDeclaredFields()[2].getName())
        .isEqualTo("policyNumber");
    assertThat(purgePolicyCandidateResponse.getClass().getDeclaredFields()[3].getName())
        .isEqualTo("policyId");
    assertThat(purgePolicyCandidateResponse.getClass().getDeclaredFields()[4].getName())
        .isEqualTo("policyEffectiveDate");
    assertThat(purgePolicyCandidateResponse.getClass().getDeclaredFields()[5].getName())
        .isEqualTo("policyExpirationDate");
    assertThat(purgePolicyCandidateResponse.getClass().getDeclaredFields()[6].getName())
        .isEqualTo("division");
    assertThat(purgePolicyCandidateResponse.getClass().getDeclaredFields()[7].getName())
        .isEqualTo("branch");
    assertThat(purgePolicyCandidateResponse.getClass().getDeclaredFields()[8].getName())
        .isEqualTo("department");
    assertThat(purgePolicyCandidateResponse.getClass().getDeclaredFields()[9].getName())
        .isEqualTo("group");
  }
}
