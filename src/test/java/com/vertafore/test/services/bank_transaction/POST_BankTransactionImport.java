package com.vertafore.test.services.bank_transaction;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseBankTransactionTo;
import com.vertafore.test.util.*;
import java.time.LocalDateTime;
import java.util.List;
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

    AppLockUtil.releaseAllBankRecApplicationLocks(AADM_User);

    UseBankTransactionTo bankTransactionAPI = new UseBankTransactionTo();
    LocalDateTime currentDate = LocalDateTime.now();

    // AADM USER
    // Get random bank
    BankAccountResponse randomBank = BankUtil.getRandomBank(AADM_User, false);

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
    // Get random bank
    randomBank = BankUtil.getRandomBank(AADM_User, false);

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
            ORAN_App, randomBank.getBankCode(), currentDate, expectedDescriptionORAN);
    assertThat(bankTransactionORAN).isNotNull();
    assertThat(bankTransactionORAN.getDeposit()).isEqualTo(expectedAmountORAN);
    assertThat(bankTransactionORAN.getFilename()).isEqualTo(expectedFilenameORAN);

    // VADM ADMIN
    // Get random bank
    randomBank = BankUtil.getRandomBank(AADM_User, false);

    // Attempt to import bank transaction (using AADM User)
    BankTransactionImportPostRequest bankTransactionImportPostRequestVADM =
        BankUtil.formatBankTransactionImportRequest(
            randomBank,
            currentDate.toString(),
            "EMSAuto " + Util.randomText(10),
            Util.randomDollarAmount(1.00, 99999.99),
            CSVUtil.generateUniqueFilename("BTI1c"));
    String expectedDescriptionVADM =
        bankTransactionImportPostRequestVADM.getBankStatementDetails().get(0).getDescription();
    VADM_Admin.attemptsTo(
        bankTransactionAPI.POSTBankTransactionImportOnTheBanktransactionController(
            bankTransactionImportPostRequestVADM, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // Verify response contains expected error
    Util.validateErrorResponseContainsString(
        "The information needed to establish the required context could not be found", VADM_Admin);

    // Verify transaction was NOT imported (using AADM User with Bank Transaction access)
    BankTransactionsSearchResponse bankTransactionVADM =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescriptionVADM);
    assertThat(bankTransactionVADM).isNull();
  }

  @Test
  public void bankTransactionImportIsUnsuccessfulWithoutAccess() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor AADM_NBTAUser = theActorCalled("AADM_NBTAUser");

    AppLockUtil.releaseAllBankRecApplicationLocks(AADM_User);

    UseBankTransactionTo bankTransactionAPI = new UseBankTransactionTo();
    LocalDateTime currentDate = LocalDateTime.now();
    BankAccountResponse randomBank = BankUtil.getRandomBank(AADM_User, false);

    // Attempt to import bank transaction
    BankTransactionImportPostRequest bankTransactionImportPostRequest =
        BankUtil.formatBankTransactionImportRequest(
            randomBank,
            currentDate.toString(),
            "EMSAuto " + Util.randomText(10),
            Util.randomDollarAmount(1.00, 99999.99),
            CSVUtil.generateUniqueFilename("BTI2"));
    String expectedDescription =
        bankTransactionImportPostRequest.getBankStatementDetails().get(0).getDescription();
    AADM_NBTAUser.attemptsTo(
        bankTransactionAPI.POSTBankTransactionImportOnTheBanktransactionController(
            bankTransactionImportPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // Verify response contains expected error
    Util.validateErrorResponseContainsString(
        "Requires at least Full  access to Bank Transactions View.", AADM_NBTAUser);

    // Verify transaction was NOT imported (using AADM User with Bank Transaction access)
    BankTransactionsSearchResponse bankTransaction =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescription);
    assertThat(bankTransaction).isNull();
  }

  @Test
  public void bankTransactionImportIsUnsuccessfulWithoutSpecifiedBankAccess()
      throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor AADM_FBTAUser = theActorCalled("AADM_FBTAUser");

    AppLockUtil.releaseAllBankRecApplicationLocks(AADM_User);

    UseBankTransactionTo bankTransactionAPI = new UseBankTransactionTo();
    LocalDateTime currentDate = LocalDateTime.now();
    BankAccountResponse accessExcludedBank =
        BankUtil.getBankByName(AADM_FBTAUser, EnvVariables.EMS_ACCESS_EXCLUDED_BANK, true);

    // Attempt to import transaction with inaccessible bank
    BankTransactionImportPostRequest bankTransactionImportPostRequest =
        BankUtil.formatBankTransactionImportRequest(
            accessExcludedBank,
            currentDate.toString(),
            "EMSAuto " + Util.randomText(10),
            Util.randomDollarAmount(1.00, 99999.99),
            CSVUtil.generateUniqueFilename("BTI3"));
    String expectedDescription =
        bankTransactionImportPostRequest.getBankStatementDetails().get(0).getDescription();
    AADM_User.attemptsTo(
        bankTransactionAPI.POSTBankTransactionImportOnTheBanktransactionController(
            bankTransactionImportPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    BankTransactionImportResponse bankTransactionImportResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", BankTransactionImportResponse.class);
    assertThat(bankTransactionImportResponse).isNotNull();
    assertThat(bankTransactionImportResponse.getBankStatementHeaderId()).isNotNull();
    assertThat(bankTransactionImportResponse.getEventLogReferenceId()).isNotNull();
    Thread.sleep(3000);

    // Check error logs for expected error
    List<String> errorMessages =
        ErrorLogUtil.getErrorMessages(
            ErrorLogUtil.getErrorsAndWarningsByReferenceId(
                AADM_User, bankTransactionImportResponse.getEventLogReferenceId()));
    assertThat(errorMessages).isNotEmpty();
    assertThat(errorMessages).contains("Requires Full Access to the bank these details belong to.");

    // Verify transaction was NOT imported (using AADM User with access to all banks)
    BankTransactionsSearchResponse bankTransaction =
        BankUtil.getBankTransactionByDescription(
            AADM_FBTAUser, accessExcludedBank.getBankCode(), currentDate, expectedDescription);
    assertThat(bankTransaction).isNull();
  }
}
