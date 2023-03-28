package com.vertafore.test.services.deposits;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseDepositsTo;
import com.vertafore.test.util.BankUtil;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;

public class POST_DepositsUnmatchedSearch extends TokenSuperClass {

  @Test
  @WithTag("deposits")
  public void postDepositsUnmatchedSearch() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseDepositsTo depositsAPI = new UseDepositsTo();

    List<BankAccountResponse> banks = BankUtil.getAllAvailableBanks(AADM_User, true);

    DepositsSearchPostRequest depositsSearchPostRequest = new DepositsSearchPostRequest();
    try {
      for (BankAccountResponse bank : banks) {
        if (bank.getBankName().equals("EMSOnly"))
          depositsSearchPostRequest.setBankCode(bank.getBankCode());
      }
    } catch (NullPointerException e) {
      throw new NullPointerException("No Bank available with the name EMSOnly");
    }

    SortedPagingRequestDepositsSearchPostRequestDepositsSortOptions
        pagingRequestDepositsSearchPostRequest =
            new SortedPagingRequestDepositsSearchPostRequestDepositsSortOptions();
    pagingRequestDepositsSearchPostRequest.setModel(depositsSearchPostRequest);
    pagingRequestDepositsSearchPostRequest.setSkip(0);
    pagingRequestDepositsSearchPostRequest.setTop(1000);
    pagingRequestDepositsSearchPostRequest.setTotalRecords(1000);

    VADM_Admin.attemptsTo(
        depositsAPI.POSTDepositsUnmatchedSearchOnTheDepositsController(
            pagingRequestDepositsSearchPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        depositsAPI.POSTDepositsUnmatchedSearchOnTheDepositsController(
            pagingRequestDepositsSearchPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);

    AADM_User.attemptsTo(
        depositsAPI.POSTDepositsUnmatchedSearchOnTheDepositsController(
            pagingRequestDepositsSearchPostRequest, ""));

    PagingResponseDepositsSearchResponse pagingResponseDepositsSearchResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseDepositsSearchResponse.class);

    assertThat(pagingResponseDepositsSearchResponse).isNotNull();
    assertThat(pagingRequestDepositsSearchPostRequest.getClass().getDeclaredFields().length)
        .isEqualTo(5);

    List<DepositsSearchResponse> depositsSearchResponseList =
        pagingResponseDepositsSearchResponse.getResponse();
    assertThat(depositsSearchResponseList.size()).isGreaterThan(0);

    DepositsSearchResponse depositsSearchResponse = depositsSearchResponseList.get(0);
    assertThat(depositsSearchResponse.getClass().getDeclaredFields().length).isEqualTo(9);
  }
}
