package com.vertafore.test.services.bank_transaction;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseBankTransactionTo;
import com.vertafore.test.util.BankUtil;
import com.vertafore.test.util.Util;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class GET_BankTransactionTotal extends TokenSuperClass {

  @Test
  public void bankTransactionTotalWithRandomBankReturnsSuccessfulResponse() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");
    Actor AADM_NBTAUser = theActorCalled("AADM_NBTAUser");

    UseBankTransactionTo bankTransactionAPI = new UseBankTransactionTo();
    BankAccountResponse randomBank = BankUtil.getRandomBank(AADM_NBTAUser, false);
    String bankCode = randomBank.getBankCode();
    String bankShortName = randomBank.getShortName();

    // unauthorized user cannot access transactions
    AADM_NBTAUser.attemptsTo(
        bankTransactionAPI.GETBankTransactionTotalOnTheBanktransactionController(
            bankCode, bankShortName, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString("An Authorization Error has occurred.", AADM_NBTAUser);

    // vadm cannot access transactions
    VADM_Admin.attemptsTo(
        bankTransactionAPI.GETBankTransactionTotalOnTheBanktransactionController(
            bankCode, bankShortName, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // Orange partner can access transactions
    randomBank = BankUtil.getRandomBank(ORAN_App, false);
    bankCode = randomBank.getBankCode();
    bankShortName = randomBank.getShortName();

    ORAN_App.attemptsTo(
        bankTransactionAPI.GETBankTransactionTotalOnTheBanktransactionController(
            bankCode, bankShortName, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // AADM User can access transactions
    randomBank = BankUtil.getRandomBank(AADM_User, false);
    bankCode = randomBank.getBankCode();
    bankShortName = randomBank.getShortName();

    AADM_User.attemptsTo(
        bankTransactionAPI.GETBankTransactionTotalOnTheBanktransactionController(
            bankCode, bankShortName, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    BankTransactionTotalResponse bankTransactionTotalResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", BankTransactionTotalResponse.class);

    assertThat(bankTransactionTotalResponse.getClass().getDeclaredFields().length).isEqualTo(4);
    assertThat(bankTransactionTotalResponse.getMatchedCount()).isGreaterThanOrEqualTo(0);
    assertThat(bankTransactionTotalResponse.getUnmatchedCount()).isGreaterThanOrEqualTo(0);
    assertThat(bankTransactionTotalResponse.getCurrentBalance()).isNotNull();
  }
}
