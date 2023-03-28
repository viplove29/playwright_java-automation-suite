package com.vertafore.test.services.bank_transaction;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseBankTransactionTo;
import com.vertafore.test.util.BankUtil;
import com.vertafore.test.util.CSVUtil;
import com.vertafore.test.util.ErrorLogUtil;
import com.vertafore.test.util.Util;
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

    UseBankTransactionTo bankTransactionAPI = new UseBankTransactionTo();
    LocalDateTime currentDate = LocalDateTime.now();

    // AADM USER
    // Get unmatched deposit from random bank and import a corresponding bank transaction
    BankAccountResponse randomBankAADM =
        BankUtil.getRandomBankWithAtLeastOneDeposit(AADM_User, false);
    List<DepositsSearchResponse> depositsSearchResponseAADM =
        BankUtil.getUnmatchedDepositsForBank(AADM_User, randomBankAADM.getBankCode());
    // This assumption should not be met, since getRandomBankWithAtLeastOneDeposit() should
    // guarantee that a deposit is present. In the event that a deposit is found but inaccessible
    // for whatever reason, the following assumption will ensure that the test is skipped.
    assumeThat(depositsSearchResponseAADM).isNotEmpty();
    String entityIdAADM = depositsSearchResponseAADM.get(0).getDepositId();
    Double amountAADM = depositsSearchResponseAADM.get(0).getAmount();
    BankTransactionImportPostRequest bankTransactionImportPostRequestAADM =
        BankUtil.importDummyBankTransaction(
            AADM_User,
            randomBankAADM,
            currentDate,
            CSVUtil.generateUniqueFilename("BTM1a"),
            amountAADM);
    String expectedDescriptionAADM =
        bankTransactionImportPostRequestAADM.getBankStatementDetails().get(0).getDescription();

    Thread.sleep(3000);

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
    List<DepositsSearchResponse> depositsSearchResponseORAN =
        BankUtil.getUnmatchedDepositsForBank(ORAN_App, randomBankORAN.getBankCode());
    assumeThat(depositsSearchResponseORAN).isNotEmpty();
    String entityIdORAN = depositsSearchResponseORAN.get(0).getDepositId();
    Double amountORAN = depositsSearchResponseORAN.get(0).getAmount();
    BankTransactionImportPostRequest bankTransactionImportPostRequestORAN =
        BankUtil.importDummyBankTransaction(
            ORAN_App,
            randomBankORAN,
            currentDate,
            CSVUtil.generateUniqueFilename("BTM1b"),
            amountORAN);
    String expectedDescriptionORAN =
        bankTransactionImportPostRequestORAN.getBankStatementDetails().get(0).getDescription();

    Thread.sleep(3000);

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
    List<DepositsSearchResponse> depositsSearchResponseVADM =
        BankUtil.getUnmatchedDepositsForBank(AADM_User, randomBankVADM.getBankCode());
    assumeThat(depositsSearchResponseVADM).isNotEmpty();
    String entityIdVADM = depositsSearchResponseVADM.get(0).getDepositId();
    Double amountVADM = depositsSearchResponseVADM.get(0).getAmount();
    BankTransactionImportPostRequest bankTransactionImportPostRequestVADM =
        BankUtil.importDummyBankTransaction(
            AADM_User,
            randomBankVADM,
            currentDate,
            CSVUtil.generateUniqueFilename("BTM1c"),
            amountVADM);
    String expectedDescriptionVADM =
        bankTransactionImportPostRequestVADM.getBankStatementDetails().get(0).getDescription();

    Thread.sleep(3000);

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

    // Verify bank transaction is NOT unmatched when access is denied
    VADM_Admin.attemptsTo(
        bankTransactionAPI.PUTBankTransactionUnmatchOnTheBanktransactionController(
            bankTransactionUnmatchPutRequestVADM, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Thread.sleep(2000);
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
  public void bankTransactionMatchAndUnmatchAreUnsuccessfulWithoutAccess()
      throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor AADM_NBTAUser = theActorCalled("AADM_NBTAUser");

    UseBankTransactionTo bankTransactionAPI = new UseBankTransactionTo();
    LocalDateTime currentDate = LocalDateTime.now();

    // AADM USER (with No Bank Transactions Access)
    // Get unmatched deposit from random bank and import a corresponding bank transaction (using
    // AADM User with Bank Transaction access)
    BankAccountResponse randomBank = BankUtil.getRandomBankWithAtLeastOneDeposit(AADM_User, false);
    List<DepositsSearchResponse> depositsSearchResponse =
        BankUtil.getUnmatchedDepositsForBank(AADM_User, randomBank.getBankCode());
    // This assumption should not be met, since getRandomBankWithAtLeastOneDeposit() should
    // guarantee that a deposit is present. In the event that a deposit is found but inaccessible
    // for whatever reason, the following assumption will ensure that the test is skipped.
    assumeThat(depositsSearchResponse).isNotEmpty();
    String entityId = depositsSearchResponse.get(0).getDepositId();
    Double amount = depositsSearchResponse.get(0).getAmount();
    BankTransactionImportPostRequest bankTransactionImportPostRequest =
        BankUtil.importDummyBankTransaction(
            AADM_User, randomBank, currentDate, CSVUtil.generateUniqueFilename("BTM1a"), amount);
    String expectedDescription =
        bankTransactionImportPostRequest.getBankStatementDetails().get(0).getDescription();

    Thread.sleep(3000);

    // Verify imported bank transaction is "unmatched" before matching
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

    // Verify bank transaction is NOT matched when access is denied
    AADM_NBTAUser.attemptsTo(
        bankTransactionAPI.PUTBankTransactionMatchOnTheBanktransactionController(
            bankTransactionMatchPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    Thread.sleep(2000);
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

    // Verify match was successful for next portion of the test
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

    // Verify bank transaction is NOT unmatched when access is denied
    AADM_NBTAUser.attemptsTo(
        bankTransactionAPI.PUTBankTransactionUnmatchOnTheBanktransactionController(
            bankTransactionUnmatchPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    Thread.sleep(2000);
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
}
