package com.vertafore.test.services.bank_transaction;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseBankTransactionTo;
import com.vertafore.test.util.BankUtil;
import com.vertafore.test.util.CSVUtil;
import com.vertafore.test.util.ErrorLogUtil;
import com.vertafore.test.util.Util;
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

    UseBankTransactionTo bankTransactionAPI = new UseBankTransactionTo();
    LocalDateTime currentDate = LocalDateTime.now();
    BankAccountResponse randomBank = BankUtil.getRandomBank(AADM_User, false);

    // AADM USER
    // Import bank transaction
    BankTransactionImportPostRequest bankTransactionImportPostRequestAADM =
        BankUtil.importDummyBankTransaction(
            AADM_User, randomBank, currentDate, CSVUtil.generateUniqueFilename("BTD1a"));
    String expectedDescriptionAADM =
        bankTransactionImportPostRequestAADM.getBankStatementDetails().get(0).getDescription();

    Thread.sleep(3000);

    // Search for imported bank transaction
    BankTransactionsSearchResponse bankTransactionAADM =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescriptionAADM);
    assertThat(bankTransactionAADM).isNotNull();

    // Delete imported bank transaction using bankStatementDetailId from search response
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
    assertThat(deleteResponseAADM.getEventLogReferenceId()).isNull();
    assertThat(deleteResponseAADM.getNumberOfRecordsDeleted()).isEqualTo(1);

    // Make sure transaction was actually deleted
    BankTransactionsSearchResponse bankTransactionAfterDeleteAADM =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescriptionAADM);
    assertThat(bankTransactionAfterDeleteAADM).isNull();

    // ORAN APP
    // Import bank transaction
    BankTransactionImportPostRequest bankTransactionImportPostRequestORAN =
        BankUtil.importDummyBankTransaction(
            ORAN_App, randomBank, currentDate, CSVUtil.generateUniqueFilename("BTD1b"));
    String expectedDescriptionORAN =
        bankTransactionImportPostRequestORAN.getBankStatementDetails().get(0).getDescription();

    Thread.sleep(3000);

    // Search for imported bank transaction
    BankTransactionsSearchResponse bankTransactionORAN =
        BankUtil.getBankTransactionByDescription(
            ORAN_App, randomBank.getBankCode(), currentDate, expectedDescriptionORAN);
    assertThat(bankTransactionORAN).isNotNull();

    // Delete imported bank transaction using bankStatementDetailId from search response
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
    assertThat(deleteResponseORAN.getEventLogReferenceId()).isNull();
    assertThat(deleteResponseORAN.getNumberOfRecordsDeleted()).isEqualTo(1);

    // Make sure transaction was actually deleted
    BankTransactionsSearchResponse bankTransactionAfterDeleteORAN =
        BankUtil.getBankTransactionByDescription(
            ORAN_App, randomBank.getBankCode(), currentDate, expectedDescriptionORAN);
    assertThat(bankTransactionAfterDeleteORAN).isNull();

    // VADM ADMIN
    // Import bank transaction (using AADM User)
    BankTransactionImportPostRequest bankTransactionImportPostRequestVADM =
        BankUtil.importDummyBankTransaction(
            AADM_User, randomBank, currentDate, CSVUtil.generateUniqueFilename("BTD1c"));
    String expectedDescriptionVADM =
        bankTransactionImportPostRequestVADM.getBankStatementDetails().get(0).getDescription();
    Thread.sleep(3000);
    // Search for imported bank transaction (using AADM User)
    BankTransactionsSearchResponse bankTransactionVADM =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescriptionVADM);
    assertThat(bankTransactionVADM).isNotNull();
    // Attempt to delete imported bank transaction using bankStatementDetailId from search response
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
    // Make sure transaction was NOT deleted (using AADM User)
    BankTransactionsSearchResponse bankTransactionAfterDeleteVADM =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescriptionVADM);
    assertThat(bankTransactionAfterDeleteVADM).isNotNull();
  }

  @Test
  public void bankTransactionDeleteIsUnsuccessfulWithoutAccess() throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor AADM_NBTAUser = theActorCalled("AADM_NBTAUser");

    UseBankTransactionTo bankTransactionAPI = new UseBankTransactionTo();
    LocalDateTime currentDate = LocalDateTime.now();
    BankAccountResponse randomBank = BankUtil.getRandomBank(AADM_User, false);

    // AADM USER (with No Bank Transactions Access)
    // Import bank transaction (using AADM User with Bank Transaction access)
    BankTransactionImportPostRequest bankTransactionImportPostRequest =
        BankUtil.importDummyBankTransaction(
            AADM_User, randomBank, currentDate, CSVUtil.generateUniqueFilename("BTD2"));
    String expectedDescription =
        bankTransactionImportPostRequest.getBankStatementDetails().get(0).getDescription();

    Thread.sleep(3000);

    // Search for imported bank transaction (using AADM User with Bank Transaction access)
    BankTransactionsSearchResponse bankTransaction =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescription);
    assertThat(bankTransaction).isNotNull();

    // Attempt to delete imported bank transaction using bankStatementDetailId from search response
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

    // Make sure transaction was NOT deleted (using AADM User with Bank Transaction access)
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
}
