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

    // AADM USER
    // Import bank transaction
    BankTransactionImportPostRequest bankTransactionImportPostRequestAADM =
        BankUtil.formatBankTransactionImportRequest(
            randomBank,
            currentDate.toString(),
            "EMSAuto " + Util.randomText(10),
            Util.randomDollarAmount(1.00, 99999.99),
            CSVUtil.generateUniqueFilename("BTI1a"));
    String expectedFilenameAADM = bankTransactionImportPostRequestAADM.getFileName();
    Double expectedAmountAADM =
        bankTransactionImportPostRequestAADM.getBankStatementDetails().get(0).getAmount();
    String expectedDescriptionAADM =
        bankTransactionImportPostRequestAADM.getBankStatementDetails().get(0).getDescription();
    AADM_User.attemptsTo(
        bankTransactionAPI.POSTBankTransactionImportOnTheBanktransactionController(
            bankTransactionImportPostRequestAADM, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    BankTransactionImportResponse bankTransactionImportResponseAADM =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", BankTransactionImportResponse.class);
    assertThat(bankTransactionImportResponseAADM).isNotNull();
    assertThat(bankTransactionImportResponseAADM.getClass().getDeclaredFields().length)
        .isEqualTo(2);
    assertThat(bankTransactionImportResponseAADM.getBankStatementHeaderId()).isNotNull();
    assertThat(bankTransactionImportResponseAADM.getEventLogReferenceId()).isNotNull();

    Thread.sleep(3000);

    // Search for imported bank transaction
    BankTransactionsSearchResponse bankTransactionAADM =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescriptionAADM);
    assertThat(bankTransactionAADM).isNotNull();
    assertThat(bankTransactionAADM.getDeposit()).isEqualTo(expectedAmountAADM);
    assertThat(bankTransactionAADM.getFilename()).isEqualTo(expectedFilenameAADM);

    // ORAN APP
    // Import bank transaction
    BankTransactionImportPostRequest bankTransactionImportPostRequestORAN =
        BankUtil.formatBankTransactionImportRequest(
            randomBank,
            currentDate.toString(),
            "EMSAuto " + Util.randomText(10),
            Util.randomDollarAmount(1.00, 99999.99),
            CSVUtil.generateUniqueFilename("BTI1b"));
    String expectedFilenameORAN = bankTransactionImportPostRequestORAN.getFileName();
    Double expectedAmountORAN =
        bankTransactionImportPostRequestORAN.getBankStatementDetails().get(0).getAmount();
    String expectedDescriptionORAN =
        bankTransactionImportPostRequestORAN.getBankStatementDetails().get(0).getDescription();
    ORAN_App.attemptsTo(
        bankTransactionAPI.POSTBankTransactionImportOnTheBanktransactionController(
            bankTransactionImportPostRequestORAN, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    BankTransactionImportResponse bankTransactionImportResponseORAN =
        LastResponse.received()
            .answeredBy(ORAN_App)
            .getBody()
            .jsonPath()
            .getObject("", BankTransactionImportResponse.class);
    assertThat(bankTransactionImportResponseORAN).isNotNull();
    assertThat(bankTransactionImportResponseORAN.getClass().getDeclaredFields().length)
        .isEqualTo(2);
    assertThat(bankTransactionImportResponseORAN.getBankStatementHeaderId()).isNotNull();
    assertThat(bankTransactionImportResponseORAN.getEventLogReferenceId()).isNotNull();

    Thread.sleep(3000);

    // Search for imported bank transaction
    BankTransactionsSearchResponse bankTransactionORAN =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescriptionORAN);
    assertThat(bankTransactionORAN).isNotNull();
    assertThat(bankTransactionORAN.getDeposit()).isEqualTo(expectedAmountORAN);
    assertThat(bankTransactionORAN.getFilename()).isEqualTo(expectedFilenameORAN);

    // VADM ADMIN
    // Import bank transaction (using AADM User)
    BankTransactionImportPostRequest bankTransactionImportPostRequestVADM =
        BankUtil.formatBankTransactionImportRequest(
            randomBank,
            currentDate.toString(),
            "EMSAuto " + Util.randomText(10),
            Util.randomDollarAmount(1.00, 99999.99),
            CSVUtil.generateUniqueFilename("BTI1c"));
    AADM_User.attemptsTo(
        bankTransactionAPI.POSTBankTransactionImportOnTheBanktransactionController(
            bankTransactionImportPostRequestORAN, ""));

    // Attempt to search for imported bank transaction
    VADM_Admin.attemptsTo(
        bankTransactionAPI.POSTBankTransactionImportOnTheBanktransactionController(
            bankTransactionImportPostRequestVADM, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "The information needed to establish the required context could not be found", VADM_Admin);
  }

  @Test
  public void bankTransactionImportIsUnsuccessfulWithoutAccess() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor AADM_NBTAUser = theActorCalled("AADM_NBTAUser");

    UseBankTransactionTo bankTransactionAPI = new UseBankTransactionTo();
    LocalDateTime currentDate = LocalDateTime.now();
    BankAccountResponse randomBank = BankUtil.getRandomBank(AADM_User, false);

    // AADM USER (with No Bank Transactions Access)
    // Attempt to import bank transaction
    BankTransactionImportPostRequest bankTransactionImportPostRequest =
        BankUtil.formatBankTransactionImportRequest(
            randomBank,
            currentDate.toString(),
            "EMSAuto " + Util.randomText(10),
            Util.randomDollarAmount(1.00, 99999.99),
            CSVUtil.generateUniqueFilename("BTI2"));
    AADM_NBTAUser.attemptsTo(
        bankTransactionAPI.POSTBankTransactionImportOnTheBanktransactionController(
            bankTransactionImportPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "Requires at least Full  access to Bank Transactions View.", AADM_NBTAUser);
  }
}
