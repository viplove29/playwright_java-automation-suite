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

public class POST_BankTransactionSearch extends TokenSuperClass {

  @Test
  public void bankTransactionSearchReturnsTransactions() throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    AppLockUtil.releaseAllBankRecApplicationLocks(AADM_User);

    UseBankTransactionTo bankTransactionAPI = new UseBankTransactionTo();
    LocalDateTime currentDate = LocalDateTime.now();

    // AADM USER
    // Get random bank
    BankAccountResponse randomBank = BankUtil.getRandomBank(AADM_User, false);
    String bankName = randomBank.getBankName();

    // Import one bank transaction to ensure there is at least one in the random bank
    BankUtil.importDummyBankTransaction(
        AADM_User, randomBank, currentDate, CSVUtil.generateUniqueFilename("BTS1a"), true);

    // Create search request
    SortedPagingRequestBankTransactionsSearchPostRequestBankTransactionsSortOptions
        pagingRequestBankTransactionsSearchPostRequest =
            BankUtil.formatBankTransactionSearchRequest(
                randomBank.getBankCode(),
                currentDate,
                SortOptionBankTransactionsSortOptions.FieldSortEnum.TRANSACTIONDATE,
                true);

    // Search for bank transactions
    AADM_User.attemptsTo(
        bankTransactionAPI.POSTBankTransactionSearchOnTheBanktransactionController(
            pagingRequestBankTransactionsSearchPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    PagingResponseBankTransactionsSearchResponse searchResponseAADM =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseBankTransactionsSearchResponse.class);
    assertThat(searchResponseAADM).isNotNull();
    assertThat(searchResponseAADM.getClass().getDeclaredFields().length).isEqualTo(4);
    List<BankTransactionsSearchResponse> bankTransactionsAADM = searchResponseAADM.getResponse();
    assertThat(searchResponseAADM.getTotalCount()).isGreaterThan(0);
    assertThat(searchResponseAADM.getTotalCount()).isEqualTo(bankTransactionsAADM.size());
    assertThat(bankTransactionsAADM).isNotNull();
    assertThat(bankTransactionsAADM.get(0)).isNotNull();
    assertThat(bankTransactionsAADM.get(0).getBankName()).isEqualTo(bankName);

    // ORAN APP
    // Get random bank
    randomBank = BankUtil.getRandomBank(AADM_User, false);
    bankName = randomBank.getBankName();

    // Import one bank transaction to ensure there is at least one in the random bank
    BankUtil.importDummyBankTransaction(
        AADM_User, randomBank, currentDate, CSVUtil.generateUniqueFilename("BTS1b"), true);

    // Create search request
    pagingRequestBankTransactionsSearchPostRequest =
        BankUtil.formatBankTransactionSearchRequest(
            randomBank.getBankCode(),
            currentDate,
            SortOptionBankTransactionsSortOptions.FieldSortEnum.TRANSACTIONDATE,
            true);

    // Search for bank transactions
    ORAN_App.attemptsTo(
        bankTransactionAPI.POSTBankTransactionSearchOnTheBanktransactionController(
            pagingRequestBankTransactionsSearchPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    PagingResponseBankTransactionsSearchResponse searchResponseORAN =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseBankTransactionsSearchResponse.class);
    assertThat(searchResponseORAN).isNotNull();
    assertThat(searchResponseORAN.getClass().getDeclaredFields().length).isEqualTo(4);
    List<BankTransactionsSearchResponse> bankTransactionsORAN = searchResponseORAN.getResponse();
    assertThat(searchResponseORAN.getTotalCount()).isGreaterThan(0);
    assertThat(searchResponseORAN.getTotalCount()).isEqualTo(bankTransactionsORAN.size());
    assertThat(bankTransactionsORAN).isNotNull();
    assertThat(bankTransactionsORAN.get(0)).isNotNull();
    assertThat(bankTransactionsORAN.get(0).getBankName()).isEqualTo(bankName);

    // VADM ADMIN
    // Attempt to search for bank transactions
    VADM_Admin.attemptsTo(
        bankTransactionAPI.POSTBankTransactionSearchOnTheBanktransactionController(
            pagingRequestBankTransactionsSearchPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // Verify response contains expected error
    Util.validateErrorResponseContainsString(
        "The information needed to establish the required context could not be found", VADM_Admin);
  }

  @Test
  public void bankTransactionSearchReturnsNothingWithoutAccess() throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor AADM_NBTAUser = theActorCalled("AADM_NBTAUser");

    AppLockUtil.releaseAllBankRecApplicationLocks(AADM_User);

    UseBankTransactionTo bankTransactionAPI = new UseBankTransactionTo();
    LocalDateTime currentDate = LocalDateTime.now();
    BankAccountResponse randomBank = BankUtil.getRandomBank(AADM_User, false);

    // Import one bank transaction to ensure there is at least one in the random bank
    BankTransactionImportPostRequest bankTransactionImportPostRequest =
        BankUtil.importDummyBankTransaction(
            AADM_User, randomBank, currentDate, CSVUtil.generateUniqueFilename("BTS2"), true);
    String expectedDescription =
        bankTransactionImportPostRequest.getBankStatementDetails().get(0).getDescription();
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Attempt to search for bank transactions - should return zero transactions
    SortedPagingRequestBankTransactionsSearchPostRequestBankTransactionsSortOptions
        bankTransactionSearchPostRequest =
            BankUtil.formatBankTransactionSearchRequest(
                randomBank.getBankCode(),
                currentDate,
                SortOptionBankTransactionsSortOptions.FieldSortEnum.TRANSACTIONDATE,
                true);
    AADM_NBTAUser.attemptsTo(
        bankTransactionAPI.POSTBankTransactionSearchOnTheBanktransactionController(
            bankTransactionSearchPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    PagingResponseBankTransactionsSearchResponse searchResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseBankTransactionsSearchResponse.class);
    assertThat(searchResponse).isNotNull();
    assertThat(searchResponse.getClass().getDeclaredFields().length).isEqualTo(4);
    assertThat(searchResponse.getResponse()).isNull();
    assertThat(searchResponse.getNextPageLink()).isNull();
    assertThat(searchResponse.getNextPageRequestBody()).isNull();
    assertThat(searchResponse.getTotalCount()).isZero();

    // Verify transaction exists (using AADM User with Bank Transaction access)
    BankTransactionsSearchResponse bankTransaction =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescription);
    assertThat(bankTransaction).isNotNull();
  }

  @Test
  public void bankTransactionSearchReturnsNothingWithoutSpecifiedBankAccess()
      throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor AADM_FBTAUser = theActorCalled("AADM_FBTAUser");

    AppLockUtil.releaseAllBankRecApplicationLocks(AADM_User);

    UseBankTransactionTo bankTransactionAPI = new UseBankTransactionTo();
    LocalDateTime currentDate = LocalDateTime.now();
    BankAccountResponse accessExcludedBank =
        BankUtil.getBankByName(AADM_FBTAUser, EnvVariables.EMS_ACCESS_EXCLUDED_BANK, true);

    // Import one bank transaction (using AADM User with access to all banks)
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

    // Attempt to search for bank transactions - should return zero transactions
    SortedPagingRequestBankTransactionsSearchPostRequestBankTransactionsSortOptions
        bankTransactionSearchPostRequest =
            BankUtil.formatBankTransactionSearchRequest(
                accessExcludedBank.getBankCode(),
                currentDate,
                SortOptionBankTransactionsSortOptions.FieldSortEnum.TRANSACTIONDATE,
                true);
    AADM_User.attemptsTo(
        bankTransactionAPI.POSTBankTransactionSearchOnTheBanktransactionController(
            bankTransactionSearchPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    PagingResponseBankTransactionsSearchResponse searchResponseAADM =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseBankTransactionsSearchResponse.class);
    assertThat(searchResponseAADM).isNotNull();
    assertThat(searchResponseAADM.getResponse()).isNull();
    assertThat(searchResponseAADM.getNextPageLink()).isNull();
    assertThat(searchResponseAADM.getNextPageRequestBody()).isNull();
    assertThat(searchResponseAADM.getTotalCount()).isZero();

    // Verify transaction exists (using AADM User with access to all banks)
    BankTransactionsSearchResponse bankTransaction =
        BankUtil.getBankTransactionByDescription(
            AADM_FBTAUser, accessExcludedBank.getBankCode(), currentDate, expectedDescription);
    assertThat(bankTransaction).isNotNull();
  }

  @Test
  public void bankTransactionSearchReturnsSortedTransactions() throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");

    AppLockUtil.releaseAllBankRecApplicationLocks(AADM_User);

    UseBankTransactionTo bankTransactionAPI = new UseBankTransactionTo();
    LocalDateTime currentDate = LocalDateTime.now();
    BankAccountResponse randomBank = BankUtil.getRandomBank(AADM_User, false);

    // Import multiple bank transactions with different dates and filenames
    BankUtil.importDummyBankTransaction(
        AADM_User, randomBank, currentDate, CSVUtil.generateUniqueFilename("BTS3e"), true);
    Thread.sleep(1000);
    BankUtil.importDummyBankTransaction(
        AADM_User,
        randomBank,
        currentDate.minusDays(1),
        CSVUtil.generateUniqueFilename("BTS3d"),
        true);
    Thread.sleep(1000);
    BankUtil.importDummyBankTransaction(
        AADM_User,
        randomBank,
        currentDate.minusMonths(1),
        CSVUtil.generateUniqueFilename("BTS3c"),
        true);
    Thread.sleep(1000);
    BankUtil.importDummyBankTransaction(
        AADM_User,
        randomBank,
        currentDate.plusMonths(1),
        CSVUtil.generateUniqueFilename("BTS3b"),
        true);
    Thread.sleep(1000);
    BankUtil.importDummyBankTransaction(
        AADM_User,
        randomBank,
        currentDate.plusDays(1),
        CSVUtil.generateUniqueFilename("BTS3a"),
        true);
    Thread.sleep(1000);

    // Search with sort: date descending
    SortedPagingRequestBankTransactionsSearchPostRequestBankTransactionsSortOptions
        bankTransactionSearchPostRequestDateDescending =
            BankUtil.formatBankTransactionSearchRequest(
                randomBank.getBankCode(),
                currentDate,
                SortOptionBankTransactionsSortOptions.FieldSortEnum.TRANSACTIONDATE,
                true);
    AADM_User.attemptsTo(
        bankTransactionAPI.POSTBankTransactionSearchOnTheBanktransactionController(
            bankTransactionSearchPostRequestDateDescending, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    PagingResponseBankTransactionsSearchResponse searchResponseDateDescending =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseBankTransactionsSearchResponse.class);
    assertThat(searchResponseDateDescending).isNotNull();

    // Verify response is sorted by date descending
    List<BankTransactionsSearchResponse> bankTransactionsDateDescending =
        searchResponseDateDescending.getResponse();
    assertThat(searchResponseDateDescending.getTotalCount()).isGreaterThanOrEqualTo(5);
    String previousDate = bankTransactionsDateDescending.get(0).getTransactionDate();
    for (BankTransactionsSearchResponse bankTransaction : bankTransactionsDateDescending) {
      assertThat(bankTransaction.getTransactionDate()).isLessThanOrEqualTo(previousDate);
    }

    // Search with sort: date ascending
    SortedPagingRequestBankTransactionsSearchPostRequestBankTransactionsSortOptions
        bankTransactionSearchPostRequestDateAscending =
            BankUtil.formatBankTransactionSearchRequest(
                randomBank.getBankCode(),
                currentDate,
                SortOptionBankTransactionsSortOptions.FieldSortEnum.TRANSACTIONDATE,
                false);

    AADM_User.attemptsTo(
        bankTransactionAPI.POSTBankTransactionSearchOnTheBanktransactionController(
            bankTransactionSearchPostRequestDateAscending, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    PagingResponseBankTransactionsSearchResponse searchResponseDateAscending =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseBankTransactionsSearchResponse.class);
    assertThat(searchResponseDateAscending).isNotNull();

    // Verify response is sorted by date ascending
    List<BankTransactionsSearchResponse> bankTransactionsDateAscending =
        searchResponseDateAscending.getResponse();
    assertThat(searchResponseDateAscending.getTotalCount()).isGreaterThanOrEqualTo(5);
    previousDate = bankTransactionsDateAscending.get(0).getTransactionDate();
    for (BankTransactionsSearchResponse bankTransaction : bankTransactionsDateAscending) {
      assertThat(bankTransaction.getTransactionDate()).isGreaterThanOrEqualTo(previousDate);
    }

    // Search with sort: filename descending
    SortedPagingRequestBankTransactionsSearchPostRequestBankTransactionsSortOptions
        bankTransactionSearchPostRequestFilenameDescending =
            BankUtil.formatBankTransactionSearchRequest(
                randomBank.getBankCode(),
                currentDate,
                SortOptionBankTransactionsSortOptions.FieldSortEnum.FILENAME,
                true);
    AADM_User.attemptsTo(
        bankTransactionAPI.POSTBankTransactionSearchOnTheBanktransactionController(
            bankTransactionSearchPostRequestFilenameDescending, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    PagingResponseBankTransactionsSearchResponse searchResponseFilenameDescending =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseBankTransactionsSearchResponse.class);
    assertThat(searchResponseFilenameDescending).isNotNull();

    // Verify response is sorted by filename descending
    List<BankTransactionsSearchResponse> bankTransactionsFilenameDescending =
        searchResponseFilenameDescending.getResponse();
    assertThat(searchResponseFilenameDescending.getTotalCount()).isGreaterThanOrEqualTo(5);
    String previousFilename = bankTransactionsFilenameDescending.get(0).getFilename();
    for (BankTransactionsSearchResponse bankTransaction : bankTransactionsFilenameDescending) {
      assertThat(bankTransaction.getFilename()).isLessThanOrEqualTo(previousFilename);
    }

    // Search with sort: filename ascending
    SortedPagingRequestBankTransactionsSearchPostRequestBankTransactionsSortOptions
        bankTransactionSearchPostRequestFilenameAscending =
            BankUtil.formatBankTransactionSearchRequest(
                randomBank.getBankCode(),
                currentDate,
                SortOptionBankTransactionsSortOptions.FieldSortEnum.FILENAME,
                false);
    AADM_User.attemptsTo(
        bankTransactionAPI.POSTBankTransactionSearchOnTheBanktransactionController(
            bankTransactionSearchPostRequestFilenameAscending, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    PagingResponseBankTransactionsSearchResponse searchResponseFilenameAscending =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseBankTransactionsSearchResponse.class);
    assertThat(searchResponseFilenameAscending).isNotNull();

    // Verify response is sorted by filename ascending
    List<BankTransactionsSearchResponse> bankTransactionsFilenameAscending =
        searchResponseFilenameAscending.getResponse();
    assertThat(searchResponseFilenameAscending.getTotalCount()).isGreaterThanOrEqualTo(5);
    previousFilename = bankTransactionsFilenameAscending.get(0).getFilename();
    for (BankTransactionsSearchResponse bankTransaction : bankTransactionsFilenameAscending) {
      assertThat(bankTransaction.getFilename()).isGreaterThanOrEqualTo(previousFilename);
    }

    // Search with sort: deposit amount descending
    SortedPagingRequestBankTransactionsSearchPostRequestBankTransactionsSortOptions
        bankTransactionSearchPostRequestDepositDescending =
            BankUtil.formatBankTransactionSearchRequest(
                randomBank.getBankCode(),
                currentDate,
                SortOptionBankTransactionsSortOptions.FieldSortEnum.DEPOSIT,
                true);
    AADM_User.attemptsTo(
        bankTransactionAPI.POSTBankTransactionSearchOnTheBanktransactionController(
            bankTransactionSearchPostRequestDepositDescending, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    PagingResponseBankTransactionsSearchResponse searchResponseDepositDescending =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseBankTransactionsSearchResponse.class);
    assertThat(searchResponseDepositDescending).isNotNull();

    // Verify response is sorted by deposit amount descending
    List<BankTransactionsSearchResponse> bankTransactionsDepositDescending =
        searchResponseDepositDescending.getResponse();
    assertThat(searchResponseDepositDescending.getTotalCount()).isGreaterThanOrEqualTo(5);
    Double previousDeposit = bankTransactionsDepositDescending.get(0).getDeposit();
    for (BankTransactionsSearchResponse bankTransaction : bankTransactionsDepositDescending) {
      assertThat(bankTransaction.getDeposit()).isLessThanOrEqualTo(previousDeposit);
    }

    // Search with sort: deposit amount ascending
    SortedPagingRequestBankTransactionsSearchPostRequestBankTransactionsSortOptions
        bankTransactionSearchPostRequestDepositAscending =
            BankUtil.formatBankTransactionSearchRequest(
                randomBank.getBankCode(),
                currentDate,
                SortOptionBankTransactionsSortOptions.FieldSortEnum.DEPOSIT,
                false);
    AADM_User.attemptsTo(
        bankTransactionAPI.POSTBankTransactionSearchOnTheBanktransactionController(
            bankTransactionSearchPostRequestDepositAscending, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    PagingResponseBankTransactionsSearchResponse searchResponseDepositAscending =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseBankTransactionsSearchResponse.class);
    assertThat(searchResponseDepositDescending).isNotNull();

    // Verify response is sorted by deposit amount ascending
    List<BankTransactionsSearchResponse> bankTransactionsDepositAscending =
        searchResponseDepositAscending.getResponse();
    assertThat(searchResponseDepositAscending.getTotalCount()).isGreaterThanOrEqualTo(5);
    previousDeposit = bankTransactionsDepositAscending.get(0).getDeposit();
    for (BankTransactionsSearchResponse bankTransaction : bankTransactionsDepositAscending) {
      assertThat(bankTransaction.getDeposit()).isGreaterThanOrEqualTo(previousDeposit);
    }
  }
}
