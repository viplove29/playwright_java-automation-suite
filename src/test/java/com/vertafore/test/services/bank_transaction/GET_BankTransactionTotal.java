package com.vertafore.test.services.bank_transaction;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseBankTransactionTo;
import com.vertafore.test.util.BankUtil;
import com.vertafore.test.util.CSVUtil;
import com.vertafore.test.util.Util;
import java.time.LocalDateTime;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class GET_BankTransactionTotal extends TokenSuperClass {

  @Test
  public void bankTransactionTotalWithRandomBankReturnsSuccessfulResponse()
      throws InterruptedException {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");
    Actor AADM_NBTAUser = theActorCalled("AADM_NBTAUser");

    UseBankTransactionTo bankTransactionAPI = new UseBankTransactionTo();
    BankAccountResponse randomBank = BankUtil.getRandomBank(AADM_User, false);
    String bankCode = randomBank.getBankCode();
    String bankShortName = randomBank.getShortName();
    String bankName = randomBank.getBankName();

    AADM_NBTAUser.attemptsTo(
        bankTransactionAPI.GETBankTransactionTotalOnTheBanktransactionController(
            bankCode, bankShortName, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString("An Authorization Error has occurred.", AADM_NBTAUser);

    VADM_Admin.attemptsTo(
        bankTransactionAPI.GETBankTransactionTotalOnTheBanktransactionController(
            bankCode, bankShortName, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        bankTransactionAPI.GETBankTransactionTotalOnTheBanktransactionController(
            bankCode, bankShortName, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        bankTransactionAPI.GETBankTransactionTotalOnTheBanktransactionController(
            bankCode, bankShortName, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    BankTransactionTotalResponse bankTransactionTotalResponses =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", BankTransactionTotalResponse.class);

    assertThat(bankTransactionTotalResponses.getClass().getDeclaredFields().length).isEqualTo(4);
    assertThat(bankTransactionTotalResponses.getMatchedCount()).isGreaterThanOrEqualTo(0);
    assertThat(bankTransactionTotalResponses.getUnmatchedCount()).isGreaterThanOrEqualTo(0);

    Double randomAmount = Util.randomDollarAmount(1.00, 99999.99);
    LocalDateTime currentDate = LocalDateTime.now();

    BankTransactionImportPostRequest bankTransactionImportPostRequestAADM =
        BankUtil.formatBankTransactionImportRequest(
            randomBank,
            currentDate.toString(),
            "EMSAuto " + Util.randomText(10),
            randomAmount,
            CSVUtil.generateUniqueFilename("BTI"));
    String expectedFilenameAADM = bankTransactionImportPostRequestAADM.getFileName();

    AADM_User.attemptsTo(
        bankTransactionAPI.POSTBankTransactionImportOnTheBanktransactionController(
            bankTransactionImportPostRequestAADM, ""));
    Double expectedAmount = bankTransactionTotalResponses.getCurrentBalance() - randomAmount;

    AADM_User.attemptsTo(
        bankTransactionAPI.GETBankTransactionTotalOnTheBanktransactionController(
            bankCode, bankShortName, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    BankTransactionTotalResponse bankTransactionTotalResponsesAfterImport =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", BankTransactionTotalResponse.class);
    assertThat(bankTransactionTotalResponsesAfterImport.getCurrentBalance())
        .isEqualTo(expectedAmount);
  }
}
