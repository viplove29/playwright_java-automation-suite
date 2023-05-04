package com.vertafore.test.services.bank_transaction;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseBankTransactionTo;
import com.vertafore.test.util.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_BankTransactionDelete extends TokenSuperClass {

  @Test
  public void bankTransactionDeleteRemovesSpecificTransaction() throws InterruptedException {
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
        BankUtil.importDummyBankTransaction(
            AADM_User, randomBank, currentDate, CSVUtil.generateUniqueFilename("BTD1a"), true);
    String expectedDescriptionAADM =
        bankTransactionImportPostRequestAADM.getBankStatementDetails().get(0).getDescription();

    // Search for imported bank transaction
    BankTransactionsSearchResponse bankTransactionAADM =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescriptionAADM);
    assertThat(bankTransactionAADM).isNotNull();

    // Delete imported bank transaction
    BankTransactionDeletePostRequest bankTransactionDeletePostRequestAADM =
        new BankTransactionDeletePostRequest();
    List<String> bankStatementDetailIdsAADM = new ArrayList<>();
    bankStatementDetailIdsAADM.add(bankTransactionAADM.getBankStatementDetailId());
    bankTransactionDeletePostRequestAADM.setBankStatementDetailIds(bankStatementDetailIdsAADM);
    AADM_User.attemptsTo(
        bankTransactionAPI.POSTBankTransactionDeleteOnTheBanktransactionController(
            bankTransactionDeletePostRequestAADM, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    DeleteGenericLoggingResponse deleteResponseAADM =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", DeleteGenericLoggingResponse.class);
    assertThat(deleteResponseAADM).isNotNull();
    assertThat(deleteResponseAADM.getClass().getDeclaredFields().length).isEqualTo(2);
    assertThat(deleteResponseAADM.getNumberOfRecordsDeleted()).isEqualTo(1);

    // Verify transaction was actually deleted
    BankTransactionsSearchResponse bankTransactionAfterDeleteAADM =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescriptionAADM);
    assertThat(bankTransactionAfterDeleteAADM).isNull();

    // ORAN APP
    // Get random bank
    randomBank = BankUtil.getRandomBank(AADM_User, false);

    // Import bank transaction
    BankTransactionImportPostRequest bankTransactionImportPostRequestORAN =
        BankUtil.importDummyBankTransaction(
            ORAN_App, randomBank, currentDate, CSVUtil.generateUniqueFilename("BTD1b"), true);
    String expectedDescriptionORAN =
        bankTransactionImportPostRequestORAN.getBankStatementDetails().get(0).getDescription();

    // Search for imported bank transaction
    BankTransactionsSearchResponse bankTransactionORAN =
        BankUtil.getBankTransactionByDescription(
            ORAN_App, randomBank.getBankCode(), currentDate, expectedDescriptionORAN);
    assertThat(bankTransactionORAN).isNotNull();

    // Delete imported bank transaction
    BankTransactionDeletePostRequest bankTransactionDeletePostRequestORAN =
        new BankTransactionDeletePostRequest();
    List<String> bankStatementDetailIdsORAN = new ArrayList<>();
    bankStatementDetailIdsORAN.add(bankTransactionORAN.getBankStatementDetailId());
    bankTransactionDeletePostRequestORAN.setBankStatementDetailIds(bankStatementDetailIdsORAN);
    ORAN_App.attemptsTo(
        bankTransactionAPI.POSTBankTransactionDeleteOnTheBanktransactionController(
            bankTransactionDeletePostRequestORAN, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    DeleteGenericLoggingResponse deleteResponseORAN =
        LastResponse.received()
            .answeredBy(ORAN_App)
            .getBody()
            .jsonPath()
            .getObject("", DeleteGenericLoggingResponse.class);
    assertThat(deleteResponseORAN).isNotNull();
    assertThat(deleteResponseORAN.getClass().getDeclaredFields().length).isEqualTo(2);
    assertThat(deleteResponseORAN.getNumberOfRecordsDeleted()).isEqualTo(1);

    // Verify transaction was actually deleted
    BankTransactionsSearchResponse bankTransactionAfterDeleteORAN =
        BankUtil.getBankTransactionByDescription(
            ORAN_App, randomBank.getBankCode(), currentDate, expectedDescriptionORAN);
    assertThat(bankTransactionAfterDeleteORAN).isNull();

    // VADM ADMIN
    // Get random bank
    randomBank = BankUtil.getRandomBank(AADM_User, false);

    // Import bank transaction (using AADM User)
    BankTransactionImportPostRequest bankTransactionImportPostRequestVADM =
        BankUtil.importDummyBankTransaction(
            AADM_User, randomBank, currentDate, CSVUtil.generateUniqueFilename("BTD1c"), true);
    String expectedDescriptionVADM =
        bankTransactionImportPostRequestVADM.getBankStatementDetails().get(0).getDescription();

    // Search for imported bank transaction (using AADM User)
    BankTransactionsSearchResponse bankTransactionVADM =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescriptionVADM);
    assertThat(bankTransactionVADM).isNotNull();

    // Attempt to delete imported bank transaction
    BankTransactionDeletePostRequest bankTransactionDeletePostRequestVADM =
        new BankTransactionDeletePostRequest();
    List<String> bankStatementDetailIdsVADM = new ArrayList<>();
    bankStatementDetailIdsVADM.add(bankTransactionVADM.getBankStatementDetailId());
    bankTransactionDeletePostRequestVADM.setBankStatementDetailIds(bankStatementDetailIdsVADM);
    VADM_Admin.attemptsTo(
        bankTransactionAPI.POSTBankTransactionDeleteOnTheBanktransactionController(
            bankTransactionDeletePostRequestVADM, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "The information needed to establish the required context could not be found", VADM_Admin);

    // Verify transaction was NOT deleted (using AADM User)
    BankTransactionsSearchResponse bankTransactionAfterDeleteVADM =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescriptionVADM);
    assertThat(bankTransactionAfterDeleteVADM).isNotNull();
  }

  @Test
  public void bankTransactionDeleteIsUnsuccessfulWithoutAccess() throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor AADM_NBTAUser = theActorCalled("AADM_NBTAUser");

    AppLockUtil.releaseAllBankRecApplicationLocks(AADM_User);

    UseBankTransactionTo bankTransactionAPI = new UseBankTransactionTo();
    LocalDateTime currentDate = LocalDateTime.now();
    BankAccountResponse randomBank = BankUtil.getRandomBank(AADM_User, false);

    // Import bank transaction (using AADM User with Bank Transaction access)
    BankTransactionImportPostRequest bankTransactionImportPostRequest =
        BankUtil.importDummyBankTransaction(
            AADM_User, randomBank, currentDate, CSVUtil.generateUniqueFilename("BTD2"), true);
    String expectedDescription =
        bankTransactionImportPostRequest.getBankStatementDetails().get(0).getDescription();

    // Search for imported bank transaction (using AADM User with Bank Transaction access)
    BankTransactionsSearchResponse bankTransaction =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescription);
    assertThat(bankTransaction).isNotNull();

    // Attempt to delete imported bank transaction
    BankTransactionDeletePostRequest bankTransactionDeletePostRequest =
        new BankTransactionDeletePostRequest();
    List<String> bankStatementDetailIds = new ArrayList<>();
    bankStatementDetailIds.add(bankTransaction.getBankStatementDetailId());
    bankTransactionDeletePostRequest.setBankStatementDetailIds(bankStatementDetailIds);
    AADM_NBTAUser.attemptsTo(
        bankTransactionAPI.POSTBankTransactionDeleteOnTheBanktransactionController(
            bankTransactionDeletePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    DeleteGenericLoggingResponse deleteResponse =
        LastResponse.received()
            .answeredBy(AADM_NBTAUser)
            .getBody()
            .jsonPath()
            .getObject("", DeleteGenericLoggingResponse.class);
    assertThat(deleteResponse).isNotNull();
    assertThat(deleteResponse.getClass().getDeclaredFields().length).isEqualTo(2);
    assertThat(deleteResponse.getEventLogReferenceId()).isNotNull();
    assertThat(deleteResponse.getNumberOfRecordsDeleted()).isZero();

    // Verify transaction was NOT deleted (using AADM User with Bank Transaction access)
    BankTransactionsSearchResponse bankTransactionAfterDelete =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescription);
    assertThat(bankTransactionAfterDelete).isNotNull();

    // Check error log using eventLogReferenceId from delete response
    List<String> errors =
        ErrorLogUtil.getErrorMessages(
            ErrorLogUtil.getErrorsAndWarningsByReferenceId(
                AADM_User, deleteResponse.getEventLogReferenceId()));
    assertThat(errors).isNotEmpty();
    assertThat(errors)
        .contains("Requires Full Access to either Bank Reconciliation or Bank Transactions.");
  }

  @Test
  public void bankTransactionDeleteIsUnsuccessfulWithoutSpecifiedBankAccess()
      throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor AADM_FBTAUser = theActorCalled("AADM_FBTAUser");

    AppLockUtil.releaseAllBankRecApplicationLocks(AADM_User);

    UseBankTransactionTo bankTransactionAPI = new UseBankTransactionTo();
    LocalDateTime currentDate = LocalDateTime.now();
    BankAccountResponse accessExcludedBank =
        BankUtil.getBankByName(AADM_FBTAUser, EnvVariables.EMS_ACCESS_EXCLUDED_BANK, true);

    // Import bank transaction (using AADM User with access to all banks)
    BankTransactionImportPostRequest bankTransactionImportPostRequest =
        BankUtil.importDummyBankTransaction(
            AADM_FBTAUser,
            accessExcludedBank,
            currentDate,
            CSVUtil.generateUniqueFilename("BTD3"),
            true);
    String expectedDescription =
        bankTransactionImportPostRequest.getBankStatementDetails().get(0).getDescription();
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Search for imported bank transaction (using AADM User with access to all banks)
    BankTransactionsSearchResponse bankTransaction =
        BankUtil.getBankTransactionByDescription(
            AADM_FBTAUser, accessExcludedBank.getBankCode(), currentDate, expectedDescription);
    assertThat(bankTransaction).isNotNull();

    // Attempt to delete imported bank transaction
    BankTransactionDeletePostRequest bankTransactionDeletePostRequest =
        new BankTransactionDeletePostRequest();
    List<String> bankStatementDetailIds = new ArrayList<>();
    bankStatementDetailIds.add(bankTransaction.getBankStatementDetailId());
    bankTransactionDeletePostRequest.setBankStatementDetailIds(bankStatementDetailIds);
    AADM_User.attemptsTo(
        bankTransactionAPI.POSTBankTransactionDeleteOnTheBanktransactionController(
            bankTransactionDeletePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    DeleteGenericLoggingResponse deleteResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", DeleteGenericLoggingResponse.class);
    assertThat(deleteResponse).isNotNull();
    assertThat(deleteResponse.getClass().getDeclaredFields().length).isEqualTo(2);
    assertThat(deleteResponse.getEventLogReferenceId()).isNotNull();
    assertThat(deleteResponse.getNumberOfRecordsDeleted()).isZero();
    // Verify transaction was NOT deleted (using AADM User with access to all banks)
    BankTransactionsSearchResponse bankTransactionAfterDelete =
        BankUtil.getBankTransactionByDescription(
            AADM_FBTAUser, accessExcludedBank.getBankCode(), currentDate, expectedDescription);
    assertThat(bankTransactionAfterDelete).isNotNull();

    // Check error log using eventLogReferenceId from delete response
    List<String> errors =
        ErrorLogUtil.getErrorMessages(
            ErrorLogUtil.getErrorsAndWarningsByReferenceId(
                AADM_User, deleteResponse.getEventLogReferenceId()));
    assertThat(errors).isNotEmpty();
    assertThat(errors).contains("Requires Full Access to the bank these details belong to.");
  }

  @Test
  public void bankTransactionDeleteIsUnsuccessfulWhenMultipleBanksAreSpecified()
      throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");

    AppLockUtil.releaseAllBankRecApplicationLocks(AADM_User);

    UseBankTransactionTo bankTransactionAPI = new UseBankTransactionTo();
    LocalDateTime currentDate = LocalDateTime.now();
    BankAccountResponse randomBank = BankUtil.getRandomBank(AADM_User, false);

    // Get an additional random bank to import a separate bank transaction for
    BankAccountResponse otherRandomBank = BankUtil.getRandomBank(AADM_User, false);
    int tries = 0;
    int maxTries = 20;
    while (otherRandomBank.getBankCode().equals(randomBank.getBankCode()) && tries < maxTries) {
      otherRandomBank = BankUtil.getRandomBank(AADM_User, false);
      tries++;
    }
    assumeThat(tries)
        .as("Could not find 2 unique banks in " + maxTries + " tries.")
        .isLessThan(maxTries);

    // Import bank transactions for both banks
    BankTransactionImportPostRequest bankTransactionImportPostRequest =
        BankUtil.importDummyBankTransaction(
            AADM_User, randomBank, currentDate, CSVUtil.generateUniqueFilename("BTD4a"), true);
    String expectedDescription =
        bankTransactionImportPostRequest.getBankStatementDetails().get(0).getDescription();
    BankTransactionImportPostRequest bankTransactionImportPostRequestOtherBank =
        BankUtil.importDummyBankTransaction(
            AADM_User, otherRandomBank, currentDate, CSVUtil.generateUniqueFilename("BTD4b"), true);
    String expectedDescriptionOtherBank =
        bankTransactionImportPostRequestOtherBank.getBankStatementDetails().get(0).getDescription();

    // Search for both imported bank transactions
    BankTransactionsSearchResponse bankTransaction =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescription);
    BankTransactionsSearchResponse bankTransactionOtherBank =
        BankUtil.getBankTransactionByDescription(
            AADM_User, otherRandomBank.getBankCode(), currentDate, expectedDescriptionOtherBank);
    assertThat(bankTransaction).isNotNull();
    assertThat(bankTransactionOtherBank).isNotNull();

    // Attempt to delete both imported transactions
    BankTransactionDeletePostRequest bankTransactionDeletePostRequest =
        new BankTransactionDeletePostRequest();
    List<String> bankStatementDetailIds = new ArrayList<>();
    bankStatementDetailIds.add(bankTransaction.getBankStatementDetailId());
    bankStatementDetailIds.add(bankTransactionOtherBank.getBankStatementDetailId());
    bankTransactionDeletePostRequest.setBankStatementDetailIds(bankStatementDetailIds);
    AADM_User.attemptsTo(
        bankTransactionAPI.POSTBankTransactionDeleteOnTheBanktransactionController(
            bankTransactionDeletePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    DeleteGenericLoggingResponse deleteResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", DeleteGenericLoggingResponse.class);
    assertThat(deleteResponse).isNotNull();
    assertThat(deleteResponse.getClass().getDeclaredFields().length).isEqualTo(2);
    assertThat(deleteResponse.getEventLogReferenceId()).isNotNull();
    assertThat(deleteResponse.getNumberOfRecordsDeleted()).isZero();

    // Verify both transactions were NOT deleted
    BankTransactionsSearchResponse bankTransactionAfterDelete =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescription);
    BankTransactionsSearchResponse bankTransactionOtherBankAfterDelete =
        BankUtil.getBankTransactionByDescription(
            AADM_User, otherRandomBank.getBankCode(), currentDate, expectedDescriptionOtherBank);
    assertThat(bankTransactionAfterDelete).isNotNull();
    assertThat(bankTransactionOtherBankAfterDelete).isNotNull();

    // Check error log using eventLogReferenceId from delete response
    List<String> errors =
        ErrorLogUtil.getErrorMessages(
            ErrorLogUtil.getErrorsAndWarningsByReferenceId(
                AADM_User, deleteResponse.getEventLogReferenceId()));
    assertThat(errors).isNotEmpty();
    assertThat(errors).contains("Cannot process transactions from more than one bank at a time.");
  }
}
