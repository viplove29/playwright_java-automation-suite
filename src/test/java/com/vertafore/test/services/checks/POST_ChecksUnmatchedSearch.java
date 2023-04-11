package com.vertafore.test.services.checks;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseChecksTo;
import com.vertafore.test.util.BankUtil;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_ChecksUnmatchedSearch extends TokenSuperClass {

  @Test
  public void checksUnmatchedSearchIsSuccessful() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseChecksTo checksAPI = new UseChecksTo();

    ChecksSearchPostRequest checksSearchPostRequest = new ChecksSearchPostRequest();
    BankAccountResponse randomBank = BankUtil.getRandomBank(AADM_User, false);
    checksSearchPostRequest.setBankCode(randomBank.getBankCode());

    SortedPagingRequestChecksSearchPostRequestChecksSortOptions
        pagingRequestChecksSearchPostRequest =
            new SortedPagingRequestChecksSearchPostRequestChecksSortOptions();
    pagingRequestChecksSearchPostRequest.setModel(checksSearchPostRequest);
    pagingRequestChecksSearchPostRequest.setSkip(0);
    pagingRequestChecksSearchPostRequest.setTop(1000);
    pagingRequestChecksSearchPostRequest.setTotalRecords(1000);

    ORAN_App.attemptsTo(
        checksAPI.POSTChecksUnmatchedSearchOnTheChecksController(
            pagingRequestChecksSearchPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        checksAPI.POSTChecksUnmatchedSearchOnTheChecksController(
            pagingRequestChecksSearchPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        checksAPI.POSTChecksUnmatchedSearchOnTheChecksController(
            pagingRequestChecksSearchPostRequest, ""));

    PagingResponseChecksSearchResponse pagingResponseChecksSearchResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseChecksSearchResponse.class);

    assertThat(pagingResponseChecksSearchResponse).isNotNull();
    assertThat(pagingResponseChecksSearchResponse.getClass().getDeclaredFields().length)
        .isEqualTo(4);

    List<ChecksSearchResponse> checksSearchResponseList =
        pagingResponseChecksSearchResponse.getResponse();

    assertThat(checksSearchResponseList).isNotNull();
    assertThat(checksSearchResponseList.size()).isGreaterThanOrEqualTo(0);

    if (checksSearchResponseList.size() != 0) {
      ChecksSearchResponse checksSearchResponse = checksSearchResponseList.get(0);
      assertThat(checksSearchResponse.getClass().getDeclaredFields().length).isEqualTo(9);
    }
  }
}
