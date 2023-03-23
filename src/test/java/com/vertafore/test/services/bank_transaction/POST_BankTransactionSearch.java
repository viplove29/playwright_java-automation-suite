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

    UseBankTransactionTo bankTransactionAPI = new UseBankTransactionTo();
    LocalDateTime currentDate = LocalDateTime.now();
    BankAccountResponse randomBank = BankUtil.getRandomBank(AADM_User, false);
    String bankName = randomBank.getBankName();

    // Import one bank transaction to ensure there is at least one in the random bank
    BankUtil.importDummyBankTransaction(
        AADM_User, randomBank, currentDate, CSVUtil.generateUniqueFilename("BTS1"));
    Thread.sleep(3000);

    // Create custom search request for random bank
    SortedPagingRequestBankTransactionsSearchPostRequestBankTransactionsSortOptions
        pagingRequestBankTransactionsSearchPostRequest =
            new SortedPagingRequestBankTransactionsSearchPostRequestBankTransactionsSortOptions();
    SortOptionBankTransactionsSortOptions sortOptions = new SortOptionBankTransactionsSortOptions();
    sortOptions.setFieldSort(SortOptionBankTransactionsSortOptions.FieldSortEnum.TRANSACTIONDATE);
    sortOptions.setIsDescendingOrder(true);
    pagingRequestBankTransactionsSearchPostRequest.setSortOption(sortOptions);

    BankTransactionsSearchPostRequest bankTransactionsSearchPostRequest =
        new BankTransactionsSearchPostRequest();
    bankTransactionsSearchPostRequest.setBankCode(randomBank.getBankCode());
    bankTransactionsSearchPostRequest.setStartDate(LocalDateTime.now().minusYears(1).toString());
    bankTransactionsSearchPostRequest.setEndDate(LocalDateTime.now().plusYears(1).toString());
    pagingRequestBankTransactionsSearchPostRequest.setModel(bankTransactionsSearchPostRequest);

    // AADM USER
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
    Util.validateErrorResponseContainsString(
        "The information needed to establish the required context could not be found", VADM_Admin);
  }

  @Test
  public void bankTransactionSearchReturnsNothingWithoutAccess() throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor AADM_NBTAUser = theActorCalled("AADM_NBTAUser");

    UseBankTransactionTo bankTransactionAPI = new UseBankTransactionTo();
    LocalDateTime currentDate = LocalDateTime.now();
    BankAccountResponse randomBank = BankUtil.getRandomBank(AADM_User, false);

    // Import one bank transaction to ensure there is at least one in the random bank
    BankUtil.importDummyBankTransaction(
        AADM_User, randomBank, currentDate, CSVUtil.generateUniqueFilename("BTS2"));
    Thread.sleep(3000);

    // Create custom search request for random bank
    SortedPagingRequestBankTransactionsSearchPostRequestBankTransactionsSortOptions
        pagingRequestBankTransactionsSearchPostRequest =
            new SortedPagingRequestBankTransactionsSearchPostRequestBankTransactionsSortOptions();
    SortOptionBankTransactionsSortOptions sortOptions = new SortOptionBankTransactionsSortOptions();
    sortOptions.setFieldSort(SortOptionBankTransactionsSortOptions.FieldSortEnum.TRANSACTIONDATE);
    sortOptions.setIsDescendingOrder(true);
    pagingRequestBankTransactionsSearchPostRequest.setSortOption(sortOptions);
    BankTransactionsSearchPostRequest bankTransactionsSearchPostRequest =
        new BankTransactionsSearchPostRequest();
    bankTransactionsSearchPostRequest.setBankCode(randomBank.getBankCode());
    bankTransactionsSearchPostRequest.setStartDate(LocalDateTime.now().minusYears(1).toString());
    bankTransactionsSearchPostRequest.setEndDate(LocalDateTime.now().plusYears(1).toString());
    pagingRequestBankTransactionsSearchPostRequest.setModel(bankTransactionsSearchPostRequest);

    // AADM USER (with No Bank Transactions Access)
    // Attempt to search for bank transactions
    AADM_NBTAUser.attemptsTo(
        bankTransactionAPI.POSTBankTransactionSearchOnTheBanktransactionController(
            pagingRequestBankTransactionsSearchPostRequest, ""));
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
  }

  @Test
  public void bankTransactionSearchReturnsSortedTransactions() throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");

    UseBankTransactionTo bankTransactionAPI = new UseBankTransactionTo();
    LocalDateTime currentDate = LocalDateTime.now();
    BankAccountResponse randomBank = BankUtil.getRandomBank(AADM_User, false);

    // Import multiple bank transactions with different dates and filenames
    BankUtil.importDummyBankTransaction(
        AADM_User, randomBank, currentDate, CSVUtil.generateUniqueFilename("BTS3e"));
    Thread.sleep(1000);
    BankUtil.importDummyBankTransaction(
        AADM_User, randomBank, currentDate.minusDays(1), CSVUtil.generateUniqueFilename("BTS3d"));
    Thread.sleep(1000);
    BankUtil.importDummyBankTransaction(
        AADM_User, randomBank, currentDate.minusHours(1), CSVUtil.generateUniqueFilename("BTS3c"));
    Thread.sleep(1000);
    BankUtil.importDummyBankTransaction(
        AADM_User, randomBank, currentDate.plusHours(1), CSVUtil.generateUniqueFilename("BTS3b"));
    Thread.sleep(1000);
    BankUtil.importDummyBankTransaction(
        AADM_User, randomBank, currentDate.plusDays(1), CSVUtil.generateUniqueFilename("BTS3a"));
    Thread.sleep(1000);

    // Create custom search request for random bank - filename, descending
    SortedPagingRequestBankTransactionsSearchPostRequestBankTransactionsSortOptions
        pagingRequestBankTransactionsSearchPostRequest =
            new SortedPagingRequestBankTransactionsSearchPostRequestBankTransactionsSortOptions();
    SortOptionBankTransactionsSortOptions sortOptions = new SortOptionBankTransactionsSortOptions();
    sortOptions.setFieldSort(SortOptionBankTransactionsSortOptions.FieldSortEnum.TRANSACTIONDATE);
    sortOptions.setIsDescendingOrder(true);
    pagingRequestBankTransactionsSearchPostRequest.setSortOption(sortOptions);
    BankTransactionsSearchPostRequest bankTransactionsSearchPostRequest =
        new BankTransactionsSearchPostRequest();
    bankTransactionsSearchPostRequest.setBankCode(randomBank.getBankCode());
    bankTransactionsSearchPostRequest.setStartDate(LocalDateTime.now().minusYears(1).toString());
    bankTransactionsSearchPostRequest.setEndDate(LocalDateTime.now().plusYears(1).toString());
    pagingRequestBankTransactionsSearchPostRequest.setModel(bankTransactionsSearchPostRequest);

    // Search and verify response is sorted by date descending
    AADM_User.attemptsTo(
        bankTransactionAPI.POSTBankTransactionSearchOnTheBanktransactionController(
            pagingRequestBankTransactionsSearchPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    PagingResponseBankTransactionsSearchResponse searchResponse1 =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseBankTransactionsSearchResponse.class);
    assertThat(searchResponse1).isNotNull();
    List<BankTransactionsSearchResponse> bankTransactions1 = searchResponse1.getResponse();
    assertThat(searchResponse1.getTotalCount()).isGreaterThanOrEqualTo(5);
    String previousDate1 = bankTransactions1.get(0).getTransactionDate();
    for (BankTransactionsSearchResponse bankTransaction : bankTransactions1) {
      assertThat(bankTransaction.getTransactionDate()).isLessThanOrEqualTo(previousDate1);
    }

    // Update search request - date, ascending
    sortOptions.setIsDescendingOrder(false);
    pagingRequestBankTransactionsSearchPostRequest.setSortOption(sortOptions);
    pagingRequestBankTransactionsSearchPostRequest.setModel(bankTransactionsSearchPostRequest);

    // Search and verify response is sorted by date ascending
    AADM_User.attemptsTo(
        bankTransactionAPI.POSTBankTransactionSearchOnTheBanktransactionController(
            pagingRequestBankTransactionsSearchPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    PagingResponseBankTransactionsSearchResponse searchResponse2 =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseBankTransactionsSearchResponse.class);
    assertThat(searchResponse2).isNotNull();
    List<BankTransactionsSearchResponse> bankTransactions2 = searchResponse2.getResponse();
    assertThat(searchResponse2.getTotalCount()).isGreaterThanOrEqualTo(5);
    String previousDate2 = bankTransactions2.get(0).getTransactionDate();
    for (BankTransactionsSearchResponse bankTransaction : bankTransactions2) {
      assertThat(bankTransaction.getTransactionDate()).isGreaterThanOrEqualTo(previousDate2);
    }

    // Update search request - filename, descending
    sortOptions.setFieldSort(SortOptionBankTransactionsSortOptions.FieldSortEnum.FILENAME);
    sortOptions.setIsDescendingOrder(true);
    pagingRequestBankTransactionsSearchPostRequest.setSortOption(sortOptions);
    pagingRequestBankTransactionsSearchPostRequest.setModel(bankTransactionsSearchPostRequest);

    // Search and verify response is sorted by filename descending
    AADM_User.attemptsTo(
        bankTransactionAPI.POSTBankTransactionSearchOnTheBanktransactionController(
            pagingRequestBankTransactionsSearchPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    PagingResponseBankTransactionsSearchResponse searchResponse3 =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseBankTransactionsSearchResponse.class);
    assertThat(searchResponse3).isNotNull();
    List<BankTransactionsSearchResponse> bankTransactions3 = searchResponse3.getResponse();
    assertThat(searchResponse3.getTotalCount()).isGreaterThanOrEqualTo(5);
    String previousFilename1 = bankTransactions3.get(0).getFilename();
    for (BankTransactionsSearchResponse bankTransaction : bankTransactions3) {
      assertThat(bankTransaction.getFilename()).isLessThanOrEqualTo(previousFilename1);
    }

    // Update search request - filename, ascending
    sortOptions.setFieldSort(SortOptionBankTransactionsSortOptions.FieldSortEnum.FILENAME);
    sortOptions.setIsDescendingOrder(false);
    pagingRequestBankTransactionsSearchPostRequest.setSortOption(sortOptions);
    pagingRequestBankTransactionsSearchPostRequest.setModel(bankTransactionsSearchPostRequest);

    // Search and verify response is sorted by filename ascending
    AADM_User.attemptsTo(
        bankTransactionAPI.POSTBankTransactionSearchOnTheBanktransactionController(
            pagingRequestBankTransactionsSearchPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    PagingResponseBankTransactionsSearchResponse searchResponse4 =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseBankTransactionsSearchResponse.class);
    assertThat(searchResponse4).isNotNull();
    List<BankTransactionsSearchResponse> bankTransactions4 = searchResponse4.getResponse();
    assertThat(searchResponse4.getTotalCount()).isGreaterThanOrEqualTo(5);
    String previousFilename2 = bankTransactions4.get(0).getFilename();
    for (BankTransactionsSearchResponse bankTransaction : bankTransactions4) {
      assertThat(bankTransaction.getFilename()).isGreaterThanOrEqualTo(previousFilename2);
    }

    // Update search request - deposit amount, descending
    sortOptions.setFieldSort(SortOptionBankTransactionsSortOptions.FieldSortEnum.DEPOSIT);
    sortOptions.setIsDescendingOrder(true);
    pagingRequestBankTransactionsSearchPostRequest.setSortOption(sortOptions);
    pagingRequestBankTransactionsSearchPostRequest.setModel(bankTransactionsSearchPostRequest);

    // Search and verify response is sorted by deposit descending
    AADM_User.attemptsTo(
        bankTransactionAPI.POSTBankTransactionSearchOnTheBanktransactionController(
            pagingRequestBankTransactionsSearchPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    PagingResponseBankTransactionsSearchResponse searchResponse5 =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseBankTransactionsSearchResponse.class);
    assertThat(searchResponse5).isNotNull();
    List<BankTransactionsSearchResponse> bankTransactions5 = searchResponse5.getResponse();
    assertThat(searchResponse5.getTotalCount()).isGreaterThanOrEqualTo(5);
    Double previousDeposit1 = bankTransactions5.get(0).getDeposit();
    for (BankTransactionsSearchResponse bankTransaction : bankTransactions5) {
      assertThat(bankTransaction.getDeposit()).isLessThanOrEqualTo(previousDeposit1);
    }

    // Update search request - deposit amount, ascending
    sortOptions.setFieldSort(SortOptionBankTransactionsSortOptions.FieldSortEnum.DEPOSIT);
    sortOptions.setIsDescendingOrder(false);
    pagingRequestBankTransactionsSearchPostRequest.setSortOption(sortOptions);
    pagingRequestBankTransactionsSearchPostRequest.setModel(bankTransactionsSearchPostRequest);

    // Search and verify response is sorted by deposit ascending
    AADM_User.attemptsTo(
        bankTransactionAPI.POSTBankTransactionSearchOnTheBanktransactionController(
            pagingRequestBankTransactionsSearchPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    PagingResponseBankTransactionsSearchResponse searchResponse6 =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseBankTransactionsSearchResponse.class);
    assertThat(searchResponse6).isNotNull();
    List<BankTransactionsSearchResponse> bankTransactions6 = searchResponse6.getResponse();
    assertThat(searchResponse6.getTotalCount()).isGreaterThanOrEqualTo(5);
    Double previousDeposit2 = bankTransactions6.get(0).getDeposit();
    for (BankTransactionsSearchResponse bankTransaction : bankTransactions6) {
      assertThat(bankTransaction.getDeposit()).isGreaterThanOrEqualTo(previousDeposit2);
    }
  }
}
