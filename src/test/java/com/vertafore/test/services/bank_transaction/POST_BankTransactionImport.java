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

public class POST_BankTransactionImport extends TokenSuperClass {

  @Test
  public void bankTransactionImportIsSuccessful() throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseBankTransactionTo bankTransactionAPI = new UseBankTransactionTo();

    LocalDateTime currentDate = LocalDateTime.now();
    BankAccountResponse randomBank = BankUtil.getRandomBank(AADM_User, false);
    BankTransactionImportPostRequest bankTransactionImportPostRequest =
        BankUtil.formatBankTransactionImportRequest(
            randomBank,
            currentDate.toString(),
            "EMSAuto " + Util.randomText(10),
            Util.randomDollarAmount(1.00, 99999.99),
            CSVUtil.generateUniqueFilename("BTI"));
    String expectedFilename = bankTransactionImportPostRequest.getFileName();
    Double expectedAmount =
        bankTransactionImportPostRequest.getBankStatementDetails().get(0).getAmount();
    String expectedDescription =
        bankTransactionImportPostRequest.getBankStatementDetails().get(0).getDescription();

    AADM_User.attemptsTo(
        bankTransactionAPI.POSTBankTransactionImportOnTheBanktransactionController(
            bankTransactionImportPostRequest, ""));
    BankTransactionImportResponse bankTransactionImportResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", BankTransactionImportResponse.class);
    assertThat(bankTransactionImportResponse).isNotNull();
    assertThat(bankTransactionImportResponse.getClass().getDeclaredFields().length).isEqualTo(2);
    assertThat(bankTransactionImportResponse.getBankStatementHeaderId()).isNotNull();
    assertThat(bankTransactionImportResponse.getEventLogReferenceId()).isNotNull();

    Thread.sleep(3000);

    BankTransactionsSearchResponse bankTransaction =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescription);
    assertThat(bankTransaction).isNotNull();
    assertThat(bankTransaction.getDeposit()).isEqualTo(expectedAmount);
    assertThat(bankTransaction.getFilename()).isEqualTo(expectedFilename);

    ORAN_App.attemptsTo(
        bankTransactionAPI.POSTBankTransactionImportOnTheBanktransactionController(
            bankTransactionImportPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        bankTransactionAPI.POSTBankTransactionImportOnTheBanktransactionController(
            bankTransactionImportPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }
}
