package com.vertafore.test.services.bank_transaction;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

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

public class PUT_BankTransactionMatchUnmatch extends TokenSuperClass {

  private static final String CHECK_MATCH_TYPE = "144";
  private static final String DEPOSIT_MATCH_TYPE = "149";

  @Test
  public void bankTransactionMatchAndUnmatchAreSuccessful() throws InterruptedException {
    // This test is long due to the need to test the three following contexts with
    // proper data set up each time. Since there must be a matched transaction to test unmatch,
    // the match and unmatch endpoints are tested within the same test.
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    AppLockUtil.releaseAllBankRecApplicationLocks(AADM_User);

    UseBankTransactionTo bankTransactionAPI = new UseBankTransactionTo();
    LocalDateTime currentDate = LocalDateTime.now();

    // AADM USER
    // Get unmatched deposit from random bank and import a corresponding bank transaction
    BankAccountResponse randomBankAADM =
        BankUtil.getRandomBankWithAtLeastOneDeposit(AADM_User, false);
    DepositsSearchResponse randomDepositAADM =
        BankUtil.getRandomDepositForBank(AADM_User, randomBankAADM.getBankCode());
    String entityIdAADM = randomDepositAADM.getDepositId();
    Double amountAADM = randomDepositAADM.getAmount();
    BankTransactionImportPostRequest bankTransactionImportPostRequestAADM =
        BankUtil.importDummyBankTransaction(
            AADM_User,
            randomBankAADM,
            currentDate,
            CSVUtil.generateUniqueFilename("BTM1a"),
            amountAADM);
    String expectedDescriptionAADM =
        bankTransactionImportPostRequestAADM.getBankStatementDetails().get(0).getDescription();

    // Verify imported bank transaction is "unmatched" before matching
    BankTransactionsSearchResponse bankTransactionAADM =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBankAADM.getBankCode(), currentDate, expectedDescriptionAADM);
    assertThat(bankTransactionAADM).isNotNull();
    assertThat(bankTransactionAADM.getMatchStatus()).isNotEqualTo("Matched");
    assertThat(bankTransactionAADM.getMatchedBy()).isEqualTo("");
    assertThat(bankTransactionAADM.getMatchedDate()).isNull();
    assertThat(bankTransactionAADM.getMatchedEntityType()).isNull();
    assertThat(bankTransactionAADM.getMatchedEntityIdentifier()).isNull();
    assertThat(bankTransactionAADM.getMatchConfirmedBy()).isEqualTo("");
    assertThat(bankTransactionAADM.getMatchConfirmedDate()).isNull();

    // Match imported bank transaction to unmatched deposit
    String bankStatementDetailIdAADM = bankTransactionAADM.getBankStatementDetailId();
    BankTransactionMatchPutRequest bankTransactionMatchPutRequestAADM =
        new BankTransactionMatchPutRequest();
    BankTransactionMatchEntityPutRequest matchedEntitiesAADM =
        new BankTransactionMatchEntityPutRequest();
    matchedEntitiesAADM.setMatchType(DEPOSIT_MATCH_TYPE);
    matchedEntitiesAADM.setMatchEntityId(entityIdAADM);
    matchedEntitiesAADM.setBankTransactionDetailId(bankStatementDetailIdAADM);
    bankTransactionMatchPutRequestAADM.addMatchedEntitiesItem(matchedEntitiesAADM);
    AADM_User.attemptsTo(
        bankTransactionAPI.PUTBankTransactionMatchOnTheBanktransactionController(
            bankTransactionMatchPutRequestAADM, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    Thread.sleep(2000);

    // Verify response
    PutGenericLoggingResponse matchResponseAADM =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PutGenericLoggingResponse.class);
    assertThat(matchResponseAADM.getClass().getDeclaredFields().length).isEqualTo(2);
    assertThat(matchResponseAADM.getNumberOfRecordsUpdated()).isEqualTo(1);

    // Verify imported bank transaction is "matched" to the deposit
    BankTransactionsSearchResponse bankTransactionAfterMatchAADM =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBankAADM.getBankCode(), currentDate, expectedDescriptionAADM);
    assertThat(bankTransactionAfterMatchAADM).isNotNull();
    assertThat(bankTransactionAfterMatchAADM.getMatchStatus()).isEqualTo("Matched");
    assertThat(bankTransactionAfterMatchAADM.getMatchedBy()).isEqualTo("Automation, EMSAuto");
    assertThat(bankTransactionAfterMatchAADM.getMatchedDate()).isNotNull();
    assertThat(bankTransactionAfterMatchAADM.getMatchedEntityType())
        .isEqualTo(Integer.parseInt(DEPOSIT_MATCH_TYPE));
    assertThat(bankTransactionAfterMatchAADM.getMatchedEntityIdentifier()).isEqualTo(entityIdAADM);
    assertThat(bankTransactionAfterMatchAADM.getMatchConfirmedBy())
        .isEqualTo("Automation, EMSAuto");
    assertThat(bankTransactionAfterMatchAADM.getMatchConfirmedDate()).isNotNull();

    // Unmatch imported bank transaction
    BankTransactionUpdateStatusPutRequest bankTransactionUnmatchPutRequestAADM =
        new BankTransactionUpdateStatusPutRequest();
    bankTransactionUnmatchPutRequestAADM.addBankStatementDetailIdsItem(bankStatementDetailIdAADM);
    AADM_User.attemptsTo(
        bankTransactionAPI.PUTBankTransactionUnmatchOnTheBanktransactionController(
            bankTransactionUnmatchPutRequestAADM, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    Thread.sleep(2000);

    // Verify response
    PutGenericLoggingResponse unmatchResponseAADM =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PutGenericLoggingResponse.class);
    assertThat(unmatchResponseAADM.getClass().getDeclaredFields().length).isEqualTo(2);
    assertThat(unmatchResponseAADM.getNumberOfRecordsUpdated()).isEqualTo(1);

    // Verify imported bank transaction is once again "unmatched"
    BankTransactionsSearchResponse bankTransactionAfterUnmatchAADM =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBankAADM.getBankCode(), currentDate, expectedDescriptionAADM);
    assertThat(bankTransactionAfterUnmatchAADM).isNotNull();
    assertThat(bankTransactionAfterUnmatchAADM.getMatchStatus()).isNotEqualTo("Matched");
    assertThat(bankTransactionAfterUnmatchAADM.getMatchedBy()).isEqualTo("");
    assertThat(bankTransactionAfterUnmatchAADM.getMatchedDate()).isNull();
    assertThat(bankTransactionAfterUnmatchAADM.getMatchedEntityType()).isNull();
    assertThat(bankTransactionAfterUnmatchAADM.getMatchedEntityIdentifier()).isNull();
    assertThat(bankTransactionAfterUnmatchAADM.getMatchConfirmedBy()).isEqualTo("");
    assertThat(bankTransactionAfterUnmatchAADM.getMatchConfirmedDate()).isNull();

    // ORAN APP
    // Get unmatched deposit from random bank and import a corresponding bank transaction
    BankAccountResponse randomBankORAN =
        BankUtil.getRandomBankWithAtLeastOneDeposit(ORAN_App, false);
    DepositsSearchResponse randomDepositORAN =
        BankUtil.getRandomDepositForBank(ORAN_App, randomBankORAN.getBankCode());
    String entityIdORAN = randomDepositORAN.getDepositId();
    Double amountORAN = randomDepositORAN.getAmount();
    BankTransactionImportPostRequest bankTransactionImportPostRequestORAN =
        BankUtil.importDummyBankTransaction(
            ORAN_App,
            randomBankORAN,
            currentDate,
            CSVUtil.generateUniqueFilename("BTM1b"),
            amountORAN);
    String expectedDescriptionORAN =
        bankTransactionImportPostRequestORAN.getBankStatementDetails().get(0).getDescription();

    // Verify imported bank transaction is "unmatched" before matching
    BankTransactionsSearchResponse bankTransactionORAN =
        BankUtil.getBankTransactionByDescription(
            ORAN_App, randomBankORAN.getBankCode(), currentDate, expectedDescriptionORAN);
    assertThat(bankTransactionORAN).isNotNull();
    assertThat(bankTransactionORAN.getMatchStatus()).isNotEqualTo("Matched");
    assertThat(bankTransactionORAN.getMatchedBy()).isEqualTo("");
    assertThat(bankTransactionORAN.getMatchedDate()).isNull();
    assertThat(bankTransactionORAN.getMatchedEntityType()).isNull();
    assertThat(bankTransactionORAN.getMatchedEntityIdentifier()).isNull();
    assertThat(bankTransactionORAN.getMatchConfirmedBy()).isEqualTo("");
    assertThat(bankTransactionORAN.getMatchConfirmedDate()).isNull();

    // Match imported bank transaction to unmatched deposit
    String bankStatementDetailIdORAN = bankTransactionORAN.getBankStatementDetailId();
    BankTransactionMatchPutRequest bankTransactionMatchPutRequestORAN =
        new BankTransactionMatchPutRequest();
    BankTransactionMatchEntityPutRequest matchedEntitiesORAN =
        new BankTransactionMatchEntityPutRequest();
    matchedEntitiesORAN.setMatchType(DEPOSIT_MATCH_TYPE);
    matchedEntitiesORAN.setMatchEntityId(entityIdORAN);
    matchedEntitiesORAN.setBankTransactionDetailId(bankStatementDetailIdORAN);
    bankTransactionMatchPutRequestORAN.addMatchedEntitiesItem(matchedEntitiesORAN);
    ORAN_App.attemptsTo(
        bankTransactionAPI.PUTBankTransactionMatchOnTheBanktransactionController(
            bankTransactionMatchPutRequestORAN, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    Thread.sleep(2000);
    PutGenericLoggingResponse matchResponseORAN =
        LastResponse.received()
            .answeredBy(ORAN_App)
            .getBody()
            .jsonPath()
            .getObject("", PutGenericLoggingResponse.class);
    assertThat(matchResponseORAN.getClass().getDeclaredFields().length).isEqualTo(2);
    assertThat(matchResponseORAN.getNumberOfRecordsUpdated()).isEqualTo(1);

    // Verify imported bank transaction is "matched" to the deposit
    BankTransactionsSearchResponse bankTransactionAfterMatchORAN =
        BankUtil.getBankTransactionByDescription(
            ORAN_App, randomBankORAN.getBankCode(), currentDate, expectedDescriptionORAN);
    assertThat(bankTransactionAfterMatchORAN).isNotNull();
    assertThat(bankTransactionAfterMatchORAN.getMatchStatus()).isEqualTo("Matched");
    assertThat(bankTransactionAfterMatchORAN.getMatchedBy()).isEqualTo("LastEMS, First");
    assertThat(bankTransactionAfterMatchORAN.getMatchedDate()).isNotNull();
    assertThat(bankTransactionAfterMatchORAN.getMatchedEntityType())
        .isEqualTo(Integer.parseInt(DEPOSIT_MATCH_TYPE));
    assertThat(bankTransactionAfterMatchORAN.getMatchedEntityIdentifier()).isEqualTo(entityIdORAN);
    assertThat(bankTransactionAfterMatchORAN.getMatchConfirmedBy()).isEqualTo("LastEMS, First");
    assertThat(bankTransactionAfterMatchORAN.getMatchConfirmedDate()).isNotNull();

    // Unmatch imported bank transaction
    BankTransactionUpdateStatusPutRequest bankTransactionUnmatchPutRequestORAN =
        new BankTransactionUpdateStatusPutRequest();
    bankTransactionUnmatchPutRequestORAN.addBankStatementDetailIdsItem(bankStatementDetailIdORAN);
    ORAN_App.attemptsTo(
        bankTransactionAPI.PUTBankTransactionUnmatchOnTheBanktransactionController(
            bankTransactionUnmatchPutRequestORAN, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    Thread.sleep(2000);

    // Verify response
    PutGenericLoggingResponse unmatchResponseORAN =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PutGenericLoggingResponse.class);
    assertThat(unmatchResponseORAN.getClass().getDeclaredFields().length).isEqualTo(2);
    assertThat(unmatchResponseORAN.getNumberOfRecordsUpdated()).isEqualTo(1);

    // Verify imported bank transaction is once again "unmatched"
    BankTransactionsSearchResponse bankTransactionAfterUnmatchORAN =
        BankUtil.getBankTransactionByDescription(
            ORAN_App, randomBankORAN.getBankCode(), currentDate, expectedDescriptionORAN);
    assertThat(bankTransactionAfterUnmatchORAN).isNotNull();
    assertThat(bankTransactionAfterUnmatchORAN.getMatchStatus()).isNotEqualTo("Matched");
    assertThat(bankTransactionAfterUnmatchORAN.getMatchedBy()).isEqualTo("");
    assertThat(bankTransactionAfterUnmatchORAN.getMatchedDate()).isNull();
    assertThat(bankTransactionAfterUnmatchORAN.getMatchedEntityType()).isNull();
    assertThat(bankTransactionAfterUnmatchORAN.getMatchedEntityIdentifier()).isNull();
    assertThat(bankTransactionAfterUnmatchORAN.getMatchConfirmedBy()).isEqualTo("");
    assertThat(bankTransactionAfterUnmatchORAN.getMatchConfirmedDate()).isNull();

    // VADM ADMIN
    // Get unmatched deposit from random bank and import a corresponding bank transaction
    BankAccountResponse randomBankVADM =
        BankUtil.getRandomBankWithAtLeastOneDeposit(AADM_User, false);
    DepositsSearchResponse randomDepositVADM =
        BankUtil.getRandomDepositForBank(AADM_User, randomBankVADM.getBankCode());
    String entityIdVADM = randomDepositVADM.getDepositId();
    Double amountVADM = randomDepositVADM.getAmount();
    BankTransactionImportPostRequest bankTransactionImportPostRequestVADM =
        BankUtil.importDummyBankTransaction(
            AADM_User,
            randomBankVADM,
            currentDate,
            CSVUtil.generateUniqueFilename("BTM1c"),
            amountVADM);
    String expectedDescriptionVADM =
        bankTransactionImportPostRequestVADM.getBankStatementDetails().get(0).getDescription();

    // Verify imported bank transaction is "unmatched" before matching
    BankTransactionsSearchResponse bankTransactionVADM =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBankVADM.getBankCode(), currentDate, expectedDescriptionVADM);
    assertThat(bankTransactionVADM).isNotNull();
    assertThat(bankTransactionVADM.getMatchStatus()).isNotEqualTo("Matched");
    assertThat(bankTransactionVADM.getMatchedBy()).isEqualTo("");
    assertThat(bankTransactionVADM.getMatchedDate()).isNull();
    assertThat(bankTransactionVADM.getMatchedEntityType()).isNull();
    assertThat(bankTransactionVADM.getMatchedEntityIdentifier()).isNull();
    assertThat(bankTransactionVADM.getMatchConfirmedBy()).isEqualTo("");
    assertThat(bankTransactionVADM.getMatchConfirmedDate()).isNull();

    // Attempt to match imported bank transaction to unmatched deposit
    String bankStatementDetailIdVADM = bankTransactionVADM.getBankStatementDetailId();
    BankTransactionMatchPutRequest bankTransactionMatchPutRequestVADM =
        new BankTransactionMatchPutRequest();
    BankTransactionMatchEntityPutRequest matchedEntitiesVADM =
        new BankTransactionMatchEntityPutRequest();
    matchedEntitiesVADM.setMatchType(DEPOSIT_MATCH_TYPE);
    matchedEntitiesVADM.setMatchEntityId(entityIdVADM);
    matchedEntitiesVADM.setBankTransactionDetailId(bankStatementDetailIdVADM);
    bankTransactionMatchPutRequestVADM.addMatchedEntitiesItem(matchedEntitiesVADM);

    // Verify bank transaction is NOT matched when access is denied
    VADM_Admin.attemptsTo(
        bankTransactionAPI.PUTBankTransactionMatchOnTheBanktransactionController(
            bankTransactionMatchPutRequestVADM, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Thread.sleep(2000);

    // Verify error in the response
    Util.validateErrorResponseContainsString(
        "The information needed to establish the required context could not be found", VADM_Admin);
    BankTransactionsSearchResponse bankTransactionAfterFailedMatchVADM =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBankVADM.getBankCode(), currentDate, expectedDescriptionVADM);
    assertThat(bankTransactionAfterFailedMatchVADM).isNotNull();
    assertThat(bankTransactionAfterFailedMatchVADM.getMatchStatus()).isNotEqualTo("Matched");

    // Successfully match the bank transaction for testing unmatch (using AADM User)
    // This also verifies that the request body would have been accepted if access was not denied
    AADM_User.attemptsTo(
        bankTransactionAPI.PUTBankTransactionMatchOnTheBanktransactionController(
            bankTransactionMatchPutRequestVADM, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    Thread.sleep(2000);

    // Verify match was successful for next portion of the test
    PutGenericLoggingResponse matchResponseVADM =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PutGenericLoggingResponse.class);
    assertThat(matchResponseVADM.getClass().getDeclaredFields().length).isEqualTo(2);
    assertThat(matchResponseVADM.getNumberOfRecordsUpdated()).isEqualTo(1);
    BankTransactionsSearchResponse bankTransactionAfterMatchVADM =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBankVADM.getBankCode(), currentDate, expectedDescriptionVADM);
    assertThat(bankTransactionAfterMatchVADM).isNotNull();
    assertThat(bankTransactionAfterMatchVADM.getMatchStatus()).isEqualTo("Matched");

    // Attempt to unmatch imported bank transaction
    BankTransactionUpdateStatusPutRequest bankTransactionUnmatchPutRequestVADM =
        new BankTransactionUpdateStatusPutRequest();
    bankTransactionUnmatchPutRequestVADM.addBankStatementDetailIdsItem(bankStatementDetailIdVADM);
    VADM_Admin.attemptsTo(
        bankTransactionAPI.PUTBankTransactionUnmatchOnTheBanktransactionController(
            bankTransactionUnmatchPutRequestVADM, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Thread.sleep(2000);

    // Verify bank transaction is NOT unmatched when access is denied
    Util.validateErrorResponseContainsString(
        "The information needed to establish the required context could not be found", VADM_Admin);
    BankTransactionsSearchResponse bankTransactionAfterFailedUnmatchVADM =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBankVADM.getBankCode(), currentDate, expectedDescriptionVADM);
    assertThat(bankTransactionAfterFailedUnmatchVADM).isNotNull();
    assertThat(bankTransactionAfterFailedUnmatchVADM.getMatchStatus()).isEqualTo("Matched");

    // Successfully unmatch bank transaction for clean up (using AADM User)
    // This also verifies that the request body would have been accepted if access was not denied
    AADM_User.attemptsTo(
        bankTransactionAPI.PUTBankTransactionUnmatchOnTheBanktransactionController(
            bankTransactionUnmatchPutRequestVADM, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }

  @Test
  public void bankTransactionMatchAndUnmatchWithChecksAreSuccessful() throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");

    AppLockUtil.releaseAllBankRecApplicationLocks(AADM_User);

    UseBankTransactionTo bankTransactionAPI = new UseBankTransactionTo();
    LocalDateTime currentDate = LocalDateTime.now();

    // Get unmatched check from random bank and import a corresponding bank transaction
    BankAccountResponse randomBank = BankUtil.getRandomBankWithAtLeastOneCheck(AADM_User, false);
    ChecksSearchResponse randomCheck =
        BankUtil.getRandomCheckForBank(AADM_User, randomBank.getBankCode());
    String entityId = randomCheck.getCashDisbursementIdentifier();
    // Since this bank transaction will be matched to a check, the imported amount must be negative
    Double amount = -(randomCheck.getAmount());
    BankTransactionImportPostRequest bankTransactionImportPostRequest =
        BankUtil.importDummyBankTransaction(
            AADM_User, randomBank, currentDate, CSVUtil.generateUniqueFilename("BTM2"), amount);
    String expectedDescription =
        bankTransactionImportPostRequest.getBankStatementDetails().get(0).getDescription();

    // Verify imported bank transaction is "unmatched" before matching
    BankTransactionsSearchResponse bankTransaction =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescription);
    assertThat(bankTransaction).isNotNull();
    assertThat(bankTransaction.getMatchStatus()).isNotEqualTo("Matched");
    assertThat(bankTransaction.getMatchedBy()).isEqualTo("");
    assertThat(bankTransaction.getMatchedDate()).isNull();
    assertThat(bankTransaction.getMatchedEntityType()).isNull();
    assertThat(bankTransaction.getMatchedEntityIdentifier()).isNull();
    assertThat(bankTransaction.getMatchConfirmedBy()).isEqualTo("");
    assertThat(bankTransaction.getMatchConfirmedDate()).isNull();

    // Match imported bank transaction to unmatched check
    String bankStatementDetailId = bankTransaction.getBankStatementDetailId();
    BankTransactionMatchPutRequest bankTransactionMatchPutRequest =
        new BankTransactionMatchPutRequest();
    BankTransactionMatchEntityPutRequest matchedEntities =
        new BankTransactionMatchEntityPutRequest();
    matchedEntities.setMatchType(CHECK_MATCH_TYPE);
    matchedEntities.setMatchEntityId(entityId);
    matchedEntities.setBankTransactionDetailId(bankStatementDetailId);
    bankTransactionMatchPutRequest.addMatchedEntitiesItem(matchedEntities);
    AADM_User.attemptsTo(
        bankTransactionAPI.PUTBankTransactionMatchOnTheBanktransactionController(
            bankTransactionMatchPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    Thread.sleep(2000);

    // Verify response
    PutGenericLoggingResponse matchResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PutGenericLoggingResponse.class);
    assertThat(matchResponse.getClass().getDeclaredFields().length).isEqualTo(2);
    assertThat(matchResponse.getNumberOfRecordsUpdated()).isEqualTo(1);

    // Verify imported bank transaction is "matched" to the check
    BankTransactionsSearchResponse bankTransactionAfterMatch =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescription);
    assertThat(bankTransactionAfterMatch).isNotNull();
    assertThat(bankTransactionAfterMatch.getMatchStatus()).isEqualTo("Matched");
    assertThat(bankTransactionAfterMatch.getMatchedBy()).isEqualTo("Automation, EMSAuto");
    assertThat(bankTransactionAfterMatch.getMatchedDate()).isNotNull();
    assertThat(bankTransactionAfterMatch.getMatchedEntityType())
        .isEqualTo(Integer.parseInt(CHECK_MATCH_TYPE));
    assertThat(bankTransactionAfterMatch.getMatchedEntityIdentifier()).isEqualTo(entityId);
    assertThat(bankTransactionAfterMatch.getMatchConfirmedBy()).isEqualTo("Automation, EMSAuto");
    assertThat(bankTransactionAfterMatch.getMatchConfirmedDate()).isNotNull();

    // Unmatch imported bank transaction
    BankTransactionUpdateStatusPutRequest bankTransactionUnmatchPutRequest =
        new BankTransactionUpdateStatusPutRequest();
    bankTransactionUnmatchPutRequest.addBankStatementDetailIdsItem(bankStatementDetailId);
    AADM_User.attemptsTo(
        bankTransactionAPI.PUTBankTransactionUnmatchOnTheBanktransactionController(
            bankTransactionUnmatchPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    Thread.sleep(2000);

    // Verify response
    PutGenericLoggingResponse unmatchResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PutGenericLoggingResponse.class);
    assertThat(unmatchResponse.getClass().getDeclaredFields().length).isEqualTo(2);
    assertThat(unmatchResponse.getNumberOfRecordsUpdated()).isEqualTo(1);

    // Verify imported bank transaction is once again "unmatched"
    BankTransactionsSearchResponse bankTransactionAfterUnmatch =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescription);
    assertThat(bankTransactionAfterUnmatch).isNotNull();
    assertThat(bankTransactionAfterUnmatch.getMatchStatus()).isNotEqualTo("Matched");
    assertThat(bankTransactionAfterUnmatch.getMatchedBy()).isEqualTo("");
    assertThat(bankTransactionAfterUnmatch.getMatchedDate()).isNull();
    assertThat(bankTransactionAfterUnmatch.getMatchedEntityType()).isNull();
    assertThat(bankTransactionAfterUnmatch.getMatchedEntityIdentifier()).isNull();
    assertThat(bankTransactionAfterUnmatch.getMatchConfirmedBy()).isEqualTo("");
    assertThat(bankTransactionAfterUnmatch.getMatchConfirmedDate()).isNull();
  }

  @Test
  public void bankTransactionMatchAndUnmatchAreUnsuccessfulWithoutAccess()
      throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor AADM_NBTAUser = theActorCalled("AADM_NBTAUser");

    AppLockUtil.releaseAllBankRecApplicationLocks(AADM_User);

    UseBankTransactionTo bankTransactionAPI = new UseBankTransactionTo();
    LocalDateTime currentDate = LocalDateTime.now();

    // Get unmatched deposit from random bank (using AADM User with Bank Transaction access)
    BankAccountResponse randomBank = BankUtil.getRandomBankWithAtLeastOneDeposit(AADM_User, false);
    DepositsSearchResponse randomDeposit =
        BankUtil.getRandomDepositForBank(AADM_User, randomBank.getBankCode());
    String entityId = randomDeposit.getDepositId();
    Double amount = randomDeposit.getAmount();

    // Import corresponding bank transaction with matching amount (using AADM User with Bank
    // Transaction access)
    BankTransactionImportPostRequest bankTransactionImportPostRequest =
        BankUtil.importDummyBankTransaction(
            AADM_User, randomBank, currentDate, CSVUtil.generateUniqueFilename("BTM3"), amount);
    String expectedDescription =
        bankTransactionImportPostRequest.getBankStatementDetails().get(0).getDescription();

    // Verify imported bank transaction is "unmatched" before attempting to match (using AADM User
    // with Bank Transaction access)
    BankTransactionsSearchResponse bankTransaction =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescription);
    assertThat(bankTransaction).isNotNull();
    assertThat(bankTransaction.getMatchStatus()).isNotEqualTo("Matched");

    // Attempt to match imported bank transaction to unmatched deposit
    String bankStatementDetailId = bankTransaction.getBankStatementDetailId();
    BankTransactionMatchPutRequest bankTransactionMatchPutRequest =
        new BankTransactionMatchPutRequest();
    BankTransactionMatchEntityPutRequest matchedEntities =
        new BankTransactionMatchEntityPutRequest();
    matchedEntities.setMatchType(DEPOSIT_MATCH_TYPE);
    matchedEntities.setMatchEntityId(entityId);
    matchedEntities.setBankTransactionDetailId(bankStatementDetailId);
    bankTransactionMatchPutRequest.addMatchedEntitiesItem(matchedEntities);
    AADM_NBTAUser.attemptsTo(
        bankTransactionAPI.PUTBankTransactionMatchOnTheBanktransactionController(
            bankTransactionMatchPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    Thread.sleep(2000);

    // Verify bank transaction is NOT matched when access is denied (using AADM User with Bank
    // Transaction access)
    PutGenericLoggingResponse failedMatchResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PutGenericLoggingResponse.class);
    assertThat(failedMatchResponse.getClass().getDeclaredFields().length).isEqualTo(2);
    assertThat(failedMatchResponse.getNumberOfRecordsUpdated()).isEqualTo(0);
    assertThat(failedMatchResponse.getEventLogReferenceId()).isNotNull();
    List<String> matchErrors =
        ErrorLogUtil.getErrorMessages(
            ErrorLogUtil.getErrorsAndWarningsByReferenceId(
                AADM_User, failedMatchResponse.getEventLogReferenceId()));
    assertThat(matchErrors).isNotEmpty();
    assertThat(matchErrors).contains("Requires Full Access to Bank Transactions.");
    BankTransactionsSearchResponse bankTransactionAfterFailedMatch =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescription);
    assertThat(bankTransactionAfterFailedMatch).isNotNull();
    assertThat(bankTransactionAfterFailedMatch.getMatchStatus()).isNotEqualTo("Matched");

    // Successfully match the bank transaction for testing unmatch (using AADM User with Bank
    // Transaction access)
    AADM_User.attemptsTo(
        bankTransactionAPI.PUTBankTransactionMatchOnTheBanktransactionController(
            bankTransactionMatchPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    Thread.sleep(2000);

    // Verify match was successful for next portion of the test (using AADM User with Bank
    // Transaction access)
    PutGenericLoggingResponse matchResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PutGenericLoggingResponse.class);
    assertThat(matchResponse.getClass().getDeclaredFields().length).isEqualTo(2);
    assertThat(matchResponse.getNumberOfRecordsUpdated()).isEqualTo(1);
    BankTransactionsSearchResponse bankTransactionAfterMatch =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescription);
    assertThat(bankTransactionAfterMatch).isNotNull();
    assertThat(bankTransactionAfterMatch.getMatchStatus()).isEqualTo("Matched");

    // Attempt to unmatch imported bank transaction
    BankTransactionUpdateStatusPutRequest bankTransactionUnmatchPutRequest =
        new BankTransactionUpdateStatusPutRequest();
    bankTransactionUnmatchPutRequest.addBankStatementDetailIdsItem(bankStatementDetailId);
    AADM_NBTAUser.attemptsTo(
        bankTransactionAPI.PUTBankTransactionUnmatchOnTheBanktransactionController(
            bankTransactionUnmatchPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    Thread.sleep(2000);

    // Verify bank transaction is NOT unmatched when access is denied (using AADM User with Bank
    // Transaction access)
    PutGenericLoggingResponse failedUnmatchResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PutGenericLoggingResponse.class);
    assertThat(failedUnmatchResponse.getClass().getDeclaredFields().length).isEqualTo(2);
    assertThat(failedUnmatchResponse.getNumberOfRecordsUpdated()).isEqualTo(0);
    assertThat(failedMatchResponse.getEventLogReferenceId()).isNotNull();
    List<String> unmatchErrors =
        ErrorLogUtil.getErrorMessages(
            ErrorLogUtil.getErrorsAndWarningsByReferenceId(
                AADM_User, failedUnmatchResponse.getEventLogReferenceId()));
    assertThat(unmatchErrors).isNotEmpty();
    assertThat(unmatchErrors)
        .contains(
            "Requires Full Access to either 'Bank Reconciliation', 'New Bank Reconciliation', or 'Bank Transactions View'");
    BankTransactionsSearchResponse bankTransactionAfterFailedUnmatch =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescription);
    assertThat(bankTransactionAfterFailedUnmatch).isNotNull();
    assertThat(bankTransactionAfterFailedUnmatch.getMatchStatus()).isEqualTo("Matched");

    // Successfully unmatch bank transaction for clean up (using AADM User with Bank Transaction
    // access)
    AADM_User.attemptsTo(
        bankTransactionAPI.PUTBankTransactionUnmatchOnTheBanktransactionController(
            bankTransactionUnmatchPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }

  @Test
  public void bankTransactionMatchAndUnmatchAreUnsuccessfulWithoutSpecifiedBankAccess()
      throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor AADM_FBTAUser = theActorCalled("AADM_FBTAUser");

    AppLockUtil.releaseAllBankRecApplicationLocks(AADM_User);

    UseBankTransactionTo bankTransactionAPI = new UseBankTransactionTo();
    LocalDateTime currentDate = LocalDateTime.now();

    // Get unmatched check from random bank (using AADM User with access to all banks)
    BankAccountResponse accessExcludedBank =
        BankUtil.getBankByName(AADM_FBTAUser, EnvVariables.EMS_ACCESS_EXCLUDED_BANK, true);
    ChecksSearchResponse randomCheck =
        BankUtil.getRandomCheckForBank(AADM_FBTAUser, accessExcludedBank.getBankCode());
    String entityId = randomCheck.getCashDisbursementIdentifier();
    // Since this bank transaction will be matched to a check, the imported amount must be negative
    Double amount = -(randomCheck.getAmount());

    // Import corresponding bank transaction with matching amount (using AADM User with access to
    // all banks)
    BankTransactionImportPostRequest bankTransactionImportPostRequest =
        BankUtil.importDummyBankTransaction(
            AADM_FBTAUser,
            accessExcludedBank,
            currentDate,
            CSVUtil.generateUniqueFilename("BTM4"),
            amount);
    String expectedDescription =
        bankTransactionImportPostRequest.getBankStatementDetails().get(0).getDescription();

    // Verify imported bank transaction is "unmatched" before attempting to match (using AADM User
    // with access to all banks)
    BankTransactionsSearchResponse bankTransaction =
        BankUtil.getBankTransactionByDescription(
            AADM_FBTAUser, accessExcludedBank.getBankCode(), currentDate, expectedDescription);
    assertThat(bankTransaction).isNotNull();
    assertThat(bankTransaction.getMatchStatus()).isNotEqualTo("Matched");

    // Attempt to match imported bank transaction to unmatched check
    String bankStatementDetailId = bankTransaction.getBankStatementDetailId();
    BankTransactionMatchPutRequest bankTransactionMatchPutRequest =
        new BankTransactionMatchPutRequest();
    BankTransactionMatchEntityPutRequest matchedEntities =
        new BankTransactionMatchEntityPutRequest();
    matchedEntities.setMatchType(CHECK_MATCH_TYPE);
    matchedEntities.setMatchEntityId(entityId);
    matchedEntities.setBankTransactionDetailId(bankStatementDetailId);
    bankTransactionMatchPutRequest.addMatchedEntitiesItem(matchedEntities);
    AADM_User.attemptsTo(
        bankTransactionAPI.PUTBankTransactionMatchOnTheBanktransactionController(
            bankTransactionMatchPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    Thread.sleep(2000);

    // Verify bank transaction is NOT matched when access to bank is denied (using AADM User with
    // access to all banks)
    PutGenericLoggingResponse failedMatchResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PutGenericLoggingResponse.class);
    assertThat(failedMatchResponse.getClass().getDeclaredFields().length).isEqualTo(2);
    assertThat(failedMatchResponse.getNumberOfRecordsUpdated()).isEqualTo(0);
    assertThat(failedMatchResponse.getEventLogReferenceId()).isNotNull();
    List<String> matchErrors =
        ErrorLogUtil.getErrorMessages(
            ErrorLogUtil.getErrorsAndWarningsByReferenceId(
                AADM_User, failedMatchResponse.getEventLogReferenceId()));
    assertThat(matchErrors).isNotEmpty();
    assertThat(matchErrors).contains("Requires Full Access to the bank these details belong to.");
    BankTransactionsSearchResponse bankTransactionAfterFailedMatch =
        BankUtil.getBankTransactionByDescription(
            AADM_FBTAUser, accessExcludedBank.getBankCode(), currentDate, expectedDescription);
    assertThat(bankTransactionAfterFailedMatch).isNotNull();
    assertThat(bankTransactionAfterFailedMatch.getMatchStatus()).isNotEqualTo("Matched");

    // Successfully match the bank transaction for testing unmatch (using AADM User with access to
    // all banks)
    AADM_FBTAUser.attemptsTo(
        bankTransactionAPI.PUTBankTransactionMatchOnTheBanktransactionController(
            bankTransactionMatchPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    Thread.sleep(2000);

    // Verify match was successful for next portion of the test (using AADM User with access to all
    // banks)
    PutGenericLoggingResponse matchResponse =
        LastResponse.received()
            .answeredBy(AADM_FBTAUser)
            .getBody()
            .jsonPath()
            .getObject("", PutGenericLoggingResponse.class);
    assertThat(matchResponse.getClass().getDeclaredFields().length).isEqualTo(2);
    assertThat(matchResponse.getNumberOfRecordsUpdated()).isEqualTo(1);
    BankTransactionsSearchResponse bankTransactionAfterMatch =
        BankUtil.getBankTransactionByDescription(
            AADM_FBTAUser, accessExcludedBank.getBankCode(), currentDate, expectedDescription);
    assertThat(bankTransactionAfterMatch).isNotNull();
    assertThat(bankTransactionAfterMatch.getMatchStatus()).isEqualTo("Matched");

    // Attempt to unmatch imported bank transaction
    BankTransactionUpdateStatusPutRequest bankTransactionUnmatchPutRequest =
        new BankTransactionUpdateStatusPutRequest();
    bankTransactionUnmatchPutRequest.addBankStatementDetailIdsItem(bankStatementDetailId);
    AADM_User.attemptsTo(
        bankTransactionAPI.PUTBankTransactionUnmatchOnTheBanktransactionController(
            bankTransactionUnmatchPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    Thread.sleep(2000);

    // Verify bank transaction is NOT unmatched when access to bank is denied (using AADM User with
    // access to all banks)
    PutGenericLoggingResponse failedUnmatchResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PutGenericLoggingResponse.class);
    assertThat(failedUnmatchResponse.getClass().getDeclaredFields().length).isEqualTo(2);
    assertThat(failedUnmatchResponse.getNumberOfRecordsUpdated()).isEqualTo(0);
    assertThat(failedMatchResponse.getEventLogReferenceId()).isNotNull();
    List<String> unmatchErrors =
        ErrorLogUtil.getErrorMessages(
            ErrorLogUtil.getErrorsAndWarningsByReferenceId(
                AADM_User, failedUnmatchResponse.getEventLogReferenceId()));
    assertThat(unmatchErrors).isNotEmpty();
    assertThat(unmatchErrors).contains("Requires Full Access to the bank these details belong to.");
    BankTransactionsSearchResponse bankTransactionAfterFailedUnmatch =
        BankUtil.getBankTransactionByDescription(
            AADM_FBTAUser, accessExcludedBank.getBankCode(), currentDate, expectedDescription);
    assertThat(bankTransactionAfterFailedUnmatch).isNotNull();
    assertThat(bankTransactionAfterFailedUnmatch.getMatchStatus()).isEqualTo("Matched");

    // Successfully unmatch bank transaction for clean up (using AADM User with access to all banks)
    AADM_FBTAUser.attemptsTo(
        bankTransactionAPI.PUTBankTransactionUnmatchOnTheBanktransactionController(
            bankTransactionUnmatchPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }

  @Test
  public void bankTransactionMatchAndUnmatchAreUnsuccessfulWhenMultipleBanksAreSpecified()
      throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");

    AppLockUtil.releaseAllBankRecApplicationLocks(AADM_User);

    UseBankTransactionTo bankTransactionAPI = new UseBankTransactionTo();
    LocalDateTime currentDate = LocalDateTime.now();

    // Get unmatched deposit from random bank
    BankAccountResponse randomBank = BankUtil.getRandomBankWithAtLeastOneDeposit(AADM_User, false);
    DepositsSearchResponse randomDeposit =
        BankUtil.getRandomDepositForBank(AADM_User, randomBank.getBankCode());
    String entityId = randomDeposit.getDepositId();
    Double amount = randomDeposit.getAmount();

    // Import corresponding bank transaction with matching amount
    BankTransactionImportPostRequest bankTransactionImportPostRequest =
        BankUtil.importDummyBankTransaction(
            AADM_User, randomBank, currentDate, CSVUtil.generateUniqueFilename("BTM5a"), amount);
    String expectedDescription =
        bankTransactionImportPostRequest.getBankStatementDetails().get(0).getDescription();

    // Get another unmatched deposit from an additional random bank
    BankAccountResponse otherRandomBank =
        BankUtil.getRandomBankWithAtLeastOneDeposit(AADM_User, false);
    int tries = 0;
    int maxTries = 20;
    while (otherRandomBank.getBankCode().equals(randomBank.getBankCode()) && tries < maxTries) {
      otherRandomBank = BankUtil.getRandomBankWithAtLeastOneDeposit(AADM_User, false);
      tries++;
    }
    assumeThat(tries)
        .as(
            "Could not find 2 unique banks with at least one deposit each in "
                + maxTries
                + " tries.")
        .isLessThan(maxTries);

    DepositsSearchResponse randomDepositOtherBank =
        BankUtil.getRandomDepositForBank(AADM_User, otherRandomBank.getBankCode());
    String entityIdOtherBank = randomDepositOtherBank.getDepositId();
    Double amountOtherBank = randomDepositOtherBank.getAmount();

    // Import another corresponding bank transaction with matching amount
    BankTransactionImportPostRequest bankTransactionImportPostRequestOtherBank =
        BankUtil.importDummyBankTransaction(
            AADM_User,
            otherRandomBank,
            currentDate,
            CSVUtil.generateUniqueFilename("BTM5b"),
            amountOtherBank);
    String expectedDescriptionOtherBank =
        bankTransactionImportPostRequestOtherBank.getBankStatementDetails().get(0).getDescription();

    // Verify both imported bank transactions are "unmatched" before matching
    BankTransactionsSearchResponse bankTransaction =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescription);
    BankTransactionsSearchResponse bankTransactionOtherBank =
        BankUtil.getBankTransactionByDescription(
            AADM_User, otherRandomBank.getBankCode(), currentDate, expectedDescriptionOtherBank);
    assertThat(bankTransaction).isNotNull();
    assertThat(bankTransaction.getMatchStatus()).isNotEqualTo("Matched");
    assertThat(bankTransactionOtherBank).isNotNull();
    assertThat(bankTransactionOtherBank.getMatchStatus()).isNotEqualTo("Matched");

    // Attempt to match both imported bank transactions to unmatched deposits from separate banks
    String bankStatementDetailId = bankTransaction.getBankStatementDetailId();
    String bankStatementDetailIdOtherBank = bankTransactionOtherBank.getBankStatementDetailId();
    BankTransactionMatchPutRequest bankTransactionMatchPutRequestInvalid =
        new BankTransactionMatchPutRequest();
    BankTransactionMatchEntityPutRequest matchedEntities =
        new BankTransactionMatchEntityPutRequest();
    BankTransactionMatchEntityPutRequest matchedEntitiesOtherBank =
        new BankTransactionMatchEntityPutRequest();
    matchedEntities.setMatchType(DEPOSIT_MATCH_TYPE);
    matchedEntities.setMatchEntityId(entityId);
    matchedEntities.setBankTransactionDetailId(bankStatementDetailId);
    matchedEntitiesOtherBank.setMatchType(DEPOSIT_MATCH_TYPE);
    matchedEntitiesOtherBank.setMatchEntityId(entityIdOtherBank);
    matchedEntitiesOtherBank.setBankTransactionDetailId(bankStatementDetailIdOtherBank);
    bankTransactionMatchPutRequestInvalid.addMatchedEntitiesItem(matchedEntities);
    bankTransactionMatchPutRequestInvalid.addMatchedEntitiesItem(matchedEntitiesOtherBank);
    AADM_User.attemptsTo(
        bankTransactionAPI.PUTBankTransactionMatchOnTheBanktransactionController(
            bankTransactionMatchPutRequestInvalid, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    Thread.sleep(2000);

    // Verify error in the response
    PutGenericLoggingResponse failedMatchResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PutGenericLoggingResponse.class);
    assertThat(failedMatchResponse.getClass().getDeclaredFields().length).isEqualTo(2);
    assertThat(failedMatchResponse.getNumberOfRecordsUpdated()).isEqualTo(0);
    assertThat(failedMatchResponse.getEventLogReferenceId()).isNotNull();
    List<String> matchErrors =
        ErrorLogUtil.getErrorMessages(
            ErrorLogUtil.getErrorsAndWarningsByReferenceId(
                AADM_User, failedMatchResponse.getEventLogReferenceId()));
    assertThat(matchErrors).isNotEmpty();
    assertThat(matchErrors)
        .contains("Cannot process transactions from more than one bank at a time.");

    // Verify both bank transactions were NOT matched
    BankTransactionsSearchResponse bankTransactionAfterFailedMatch =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescription);
    BankTransactionsSearchResponse bankTransactionOtherBankAfterFailedMatch =
        BankUtil.getBankTransactionByDescription(
            AADM_User, otherRandomBank.getBankCode(), currentDate, expectedDescriptionOtherBank);
    assertThat(bankTransactionAfterFailedMatch).isNotNull();
    assertThat(bankTransactionAfterFailedMatch.getMatchStatus()).isNotEqualTo("Matched");
    assertThat(bankTransactionOtherBankAfterFailedMatch).isNotNull();
    assertThat(bankTransactionOtherBankAfterFailedMatch.getMatchStatus()).isNotEqualTo("Matched");

    // Successfully match both bank transactions for testing unmatch (using separate requests)
    BankTransactionMatchPutRequest bankTransactionMatchPutRequest =
        new BankTransactionMatchPutRequest();
    bankTransactionMatchPutRequest.addMatchedEntitiesItem(matchedEntities);
    AADM_User.attemptsTo(
        bankTransactionAPI.PUTBankTransactionMatchOnTheBanktransactionController(
            bankTransactionMatchPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    PutGenericLoggingResponse matchResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PutGenericLoggingResponse.class);
    Thread.sleep(2000);

    BankTransactionMatchPutRequest bankTransactionMatchPutRequestOtherBank =
        new BankTransactionMatchPutRequest();
    bankTransactionMatchPutRequestOtherBank.addMatchedEntitiesItem(matchedEntitiesOtherBank);
    AADM_User.attemptsTo(
        bankTransactionAPI.PUTBankTransactionMatchOnTheBanktransactionController(
            bankTransactionMatchPutRequestOtherBank, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    PutGenericLoggingResponse matchResponseOtherBank =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PutGenericLoggingResponse.class);
    Thread.sleep(2000);

    // Verify both matches were successful for next portion of the test
    assertThat(matchResponse.getNumberOfRecordsUpdated()).isEqualTo(1);
    BankTransactionsSearchResponse bankTransactionAfterMatch =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescription);
    assertThat(bankTransactionAfterMatch).isNotNull();
    assertThat(bankTransactionAfterMatch.getMatchStatus()).isEqualTo("Matched");
    assertThat(matchResponseOtherBank.getNumberOfRecordsUpdated()).isEqualTo(1);
    BankTransactionsSearchResponse bankTransactionOtherBankAfterMatch =
        BankUtil.getBankTransactionByDescription(
            AADM_User, otherRandomBank.getBankCode(), currentDate, expectedDescriptionOtherBank);
    assertThat(bankTransactionOtherBankAfterMatch).isNotNull();
    assertThat(bankTransactionOtherBankAfterMatch.getMatchStatus()).isEqualTo("Matched");

    // Attempt to unmatch both imported bank transactions
    BankTransactionUpdateStatusPutRequest bankTransactionUnmatchPutRequestInvalid =
        new BankTransactionUpdateStatusPutRequest();
    bankTransactionUnmatchPutRequestInvalid.addBankStatementDetailIdsItem(bankStatementDetailId);
    bankTransactionUnmatchPutRequestInvalid.addBankStatementDetailIdsItem(
        bankStatementDetailIdOtherBank);
    AADM_User.attemptsTo(
        bankTransactionAPI.PUTBankTransactionUnmatchOnTheBanktransactionController(
            bankTransactionUnmatchPutRequestInvalid, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    Thread.sleep(2000);

    // Verify error in the response
    PutGenericLoggingResponse failedUnmatchResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PutGenericLoggingResponse.class);
    assertThat(failedUnmatchResponse.getClass().getDeclaredFields().length).isEqualTo(2);
    assertThat(failedUnmatchResponse.getNumberOfRecordsUpdated()).isEqualTo(0);
    assertThat(failedMatchResponse.getEventLogReferenceId()).isNotNull();
    List<String> unmatchErrors =
        ErrorLogUtil.getErrorMessages(
            ErrorLogUtil.getErrorsAndWarningsByReferenceId(
                AADM_User, failedUnmatchResponse.getEventLogReferenceId()));
    assertThat(unmatchErrors).isNotEmpty();
    assertThat(unmatchErrors)
        .contains("Cannot process transactions from more than one bank at a time.");

    // Verify both bank transactions were NOT unmatched
    BankTransactionsSearchResponse bankTransactionAfterFailedUnmatch =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescription);
    BankTransactionsSearchResponse bankTransactionOtherBankAfterFailedUnmatch =
        BankUtil.getBankTransactionByDescription(
            AADM_User, otherRandomBank.getBankCode(), currentDate, expectedDescriptionOtherBank);
    assertThat(bankTransactionAfterFailedUnmatch).isNotNull();
    assertThat(bankTransactionAfterFailedUnmatch.getMatchStatus()).isEqualTo("Matched");
    assertThat(bankTransactionOtherBankAfterFailedUnmatch).isNotNull();
    assertThat(bankTransactionOtherBankAfterFailedUnmatch.getMatchStatus()).isEqualTo("Matched");

    // Successfully unmatch both bank transactions for clean up (using separate requests)
    BankTransactionUpdateStatusPutRequest bankTransactionUnmatchPutRequest =
        new BankTransactionUpdateStatusPutRequest();
    bankTransactionUnmatchPutRequest.addBankStatementDetailIdsItem(bankStatementDetailId);
    AADM_User.attemptsTo(
        bankTransactionAPI.PUTBankTransactionUnmatchOnTheBanktransactionController(
            bankTransactionUnmatchPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    Thread.sleep(2000);

    BankTransactionUpdateStatusPutRequest bankTransactionOtherBankUnmatchPutRequest =
        new BankTransactionUpdateStatusPutRequest();
    bankTransactionOtherBankUnmatchPutRequest.addBankStatementDetailIdsItem(
        bankStatementDetailIdOtherBank);
    AADM_User.attemptsTo(
        bankTransactionAPI.PUTBankTransactionUnmatchOnTheBanktransactionController(
            bankTransactionOtherBankUnmatchPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
