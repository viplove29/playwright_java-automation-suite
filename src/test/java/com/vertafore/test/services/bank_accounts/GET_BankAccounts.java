package com.vertafore.test.services.bank_accounts;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.BankAccountResponse;
import com.vertafore.test.servicewrappers.UseBankAccountsTo;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_BankAccounts extends TokenSuperClass {

  /* This is a basic smoke test that checks the GET /bank-accounts endpoint against user, app and admin
  context - as well as ensuring the endpoint is returning bank data with the showHidden flag set to both
  true as well as false.
   */
  @Test
  public void banksReturnsAllBanks() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseBankAccountsTo bankAccountsAPI = new UseBankAccountsTo();

    // Testing with showHidden set to true

    VADM_Admin.attemptsTo(
        bankAccountsAPI.GETBankAccountsOnTheBankaccountsController(true, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(bankAccountsAPI.GETBankAccountsOnTheBankaccountsController(true, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        bankAccountsAPI.GETBankAccountsOnTheBankaccountsController(true, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<BankAccountResponse> banksHiddenTrue =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", BankAccountResponse.class);

    assertThat(banksHiddenTrue.get(0).getClass().getDeclaredFields().length).isEqualTo(4);
    assertThat(banksHiddenTrue.size()).isGreaterThan(0);

    // Testing with showHidden set to false

    VADM_Admin.attemptsTo(
        bankAccountsAPI.GETBankAccountsOnTheBankaccountsController(false, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        bankAccountsAPI.GETBankAccountsOnTheBankaccountsController(false, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        bankAccountsAPI.GETBankAccountsOnTheBankaccountsController(false, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<BankAccountResponse> banksHiddenFalse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", BankAccountResponse.class);

    assertThat(banksHiddenFalse.get(0).getClass().getDeclaredFields().length).isEqualTo(4);
    assertThat(banksHiddenFalse.size()).isGreaterThan(0);
  }
}
