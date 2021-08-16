package com.vertafore.test.services.bank_accounts;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.BankAccountResponse;
import com.vertafore.test.servicewrappers.UseBankAccountsTo;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_BankAccounts {

  private List<EMSActor> actors = new ArrayList<>();

  @Before
  public void getAnAccessToken() {
    actors.addAll(
        List.of(
            new EMSActor().called("bob").withContext("userContext"),
            new EMSActor().called("doug").withContext("appContext"),
            new EMSActor().called("adam").withContext("adminContext")));
    OnStage.setTheStage(GetAnAccessToken(actors));
  }

  /* This is a basic smoke test that checks the GET /bank-accounts endpoint against user, app and admin
  context - as well as ensuring the endpoint is returning bank data with the showHidden flag set to both
  true as well as false.
   */
  @Test
  public void banksReturnsAllBanks() {

    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    UseBankAccountsTo bankAccountsAPI = new UseBankAccountsTo();

    // Testing with showHidden set to true

    adam.attemptsTo(bankAccountsAPI.GETBankAccountsOnTheBankaccountsController(true, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    doug.attemptsTo(bankAccountsAPI.GETBankAccountsOnTheBankaccountsController(true, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    bob.attemptsTo(bankAccountsAPI.GETBankAccountsOnTheBankaccountsController(true, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<BankAccountResponse> banksHiddenTrue =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getList("", BankAccountResponse.class);

    assertThat(banksHiddenTrue.get(0).getClass().getDeclaredFields().length).isEqualTo(4);
    assertThat(banksHiddenTrue.size()).isGreaterThan(0);

    // Testing with showHidden set to false

    adam.attemptsTo(bankAccountsAPI.GETBankAccountsOnTheBankaccountsController(false, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    doug.attemptsTo(bankAccountsAPI.GETBankAccountsOnTheBankaccountsController(false, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    bob.attemptsTo(bankAccountsAPI.GETBankAccountsOnTheBankaccountsController(false, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<BankAccountResponse> banksHiddenFalse =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getList("", BankAccountResponse.class);

    assertThat(banksHiddenFalse.get(0).getClass().getDeclaredFields().length).isEqualTo(4);
    assertThat(banksHiddenFalse.size()).isGreaterThan(0);
  }
}
