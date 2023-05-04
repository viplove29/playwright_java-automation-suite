package com.vertafore.test.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseBankAccountsTo;
import com.vertafore.test.servicewrappers.UseBankTransactionTo;
import com.vertafore.test.servicewrappers.UseChecksTo;
import com.vertafore.test.servicewrappers.UseDepositsTo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class BankUtil {

  public static List<BankAccountResponse> getAllAvailableBanks(Actor actor, boolean includeHidden) {
    UseBankAccountsTo bankAccountsAPI = new UseBankAccountsTo();
    actor.attemptsTo(
        bankAccountsAPI.GETBankAccountsOnTheBankaccountsController(includeHidden, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    List<BankAccountResponse> response =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getList("", BankAccountResponse.class);

    assertThat(response).isNotNull();
    assertThat(response.size()).isGreaterThan(0);
    return response;
  }

  public static BankAccountResponse getRandomBank(Actor actor, boolean includeHidden) {
    List<BankAccountResponse> banks = getAllAvailableBanks(actor, includeHidden);
    int randomNum = new Random().nextInt(banks.size());
    return banks.get(randomNum);
  }

  public static BankAccountResponse getBankByName(
      Actor actor, String bankName, boolean includeHidden) {
    List<BankAccountResponse> banks =
        getAllAvailableBanks(actor, includeHidden)
            .stream()
            .filter(bank -> bank.getBankName().equals(bankName))
            .collect(Collectors.toList());
    assertThat(banks.size()).isGreaterThan(0);
    return banks.get(0);
  }

  public static List<BankTransactionsSearchResponse> getBankTransactions(
      Actor actor, String bankCode, LocalDateTime startDate) {
    UseBankTransactionTo bankTransactionAPI = new UseBankTransactionTo();
    SortedPagingRequestBankTransactionsSearchPostRequestBankTransactionsSortOptions
        pagingRequestBankTransactionsSearchPostRequest =
            new SortedPagingRequestBankTransactionsSearchPostRequestBankTransactionsSortOptions();
    SortOptionBankTransactionsSortOptions sortOptions = new SortOptionBankTransactionsSortOptions();
    sortOptions.setFieldSort(SortOptionBankTransactionsSortOptions.FieldSortEnum.TRANSACTIONDATE);
    sortOptions.setIsDescendingOrder(true);
    pagingRequestBankTransactionsSearchPostRequest.setSortOption(sortOptions);

    BankTransactionsSearchPostRequest bankTransactionsSearchPostRequest =
        new BankTransactionsSearchPostRequest();
    bankTransactionsSearchPostRequest.setBankCode(bankCode);
    bankTransactionsSearchPostRequest.setStartDate(startDate.minusDays(1).toString());
    bankTransactionsSearchPostRequest.setEndDate(startDate.plusMonths(1).toString());
    pagingRequestBankTransactionsSearchPostRequest.setModel(bankTransactionsSearchPostRequest);
    pagingRequestBankTransactionsSearchPostRequest.setTop(10000);

    actor.attemptsTo(
        bankTransactionAPI.POSTBankTransactionSearchOnTheBanktransactionController(
            pagingRequestBankTransactionsSearchPostRequest, ""));

    PagingResponseBankTransactionsSearchResponse searchResponse =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseBankTransactionsSearchResponse.class);

    return searchResponse.getResponse();
  }

  public static BankTransactionsSearchResponse getBankTransactionByDescription(
      Actor actor, String bankCode, LocalDateTime startDate, String expectedDescription) {
    List<BankTransactionsSearchResponse> bankTransactions =
        getBankTransactions(actor, bankCode, startDate);
    if (bankTransactions == null || bankTransactions.isEmpty()) {
      return null;
    }
    List<BankTransactionsSearchResponse> matchingBankTransactions =
        bankTransactions
            .stream()
            .filter(tran -> tran.getDescription().equals(expectedDescription))
            .collect(Collectors.toList());
    if (matchingBankTransactions.isEmpty()) {
      return null;
    }
    return matchingBankTransactions.get(0);
  }

  public static BankTransactionImportPostRequest formatBankTransactionImportRequest(
      BankAccountResponse bank,
      String dateString,
      String description,
      Double amount,
      String csvFilename) {
    // Amount must be POSITIVE if matching to a deposit, or NEGATIVE if matching to a check
    BankTransactionImportPostRequest request = new BankTransactionImportPostRequest();
    request.setBankCode(bank.getBankCode());
    request.setBankShortName(bank.getShortName());

    List<BankStatementPostRequest> detailsList = new ArrayList<>();
    BankStatementPostRequest details = new BankStatementPostRequest();
    details.setTransactionDate(dateString);
    details.setDescription(description);
    details.setAmount(amount);
    detailsList.add(details);

    request.setBankStatementDetails(detailsList);
    request.setFileName(csvFilename);
    return request;
  }

  public static BankTransactionImportPostRequest importDummyBankTransaction(
      Actor actor,
      BankAccountResponse bank,
      LocalDateTime currentDate,
      String filename,
      Boolean positiveAmount)
      throws InterruptedException {
    // Amount must be POSITIVE if matching to a deposit, or NEGATIVE if matching to a check
    UseBankTransactionTo bankTransactionAPI = new UseBankTransactionTo();
    Double amount = Util.randomDollarAmount(1.00, 99999.99);
    if (!positiveAmount) {
      amount = Util.randomDollarAmount(-1.00, -99999.99);
    }
    BankTransactionImportPostRequest bankTransactionImportPostRequest =
        formatBankTransactionImportRequest(
            bank, currentDate.toString(), "EMSAuto " + Util.randomText(10), amount, filename);
    actor.attemptsTo(
        bankTransactionAPI.POSTBankTransactionImportOnTheBanktransactionController(
            bankTransactionImportPostRequest, ""));

    // Verify the import was successful
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    BankTransactionImportResponse bankTransactionImportResponse =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", BankTransactionImportResponse.class);
    assertThat(bankTransactionImportResponse.getBankStatementHeaderId()).isNotNull();
    assertThat(bankTransactionImportResponse.getEventLogReferenceId()).isNotNull();
    Thread.sleep(3000);
    List<String> errorMessages =
        ErrorLogUtil.getErrorMessages(
            ErrorLogUtil.getErrorsAndWarningsByReferenceId(
                actor, bankTransactionImportResponse.getEventLogReferenceId()));
    assertThat(errorMessages).isEmpty();

    return bankTransactionImportPostRequest;
  }

  public static BankTransactionImportPostRequest importDummyBankTransaction(
      Actor actor,
      BankAccountResponse bank,
      LocalDateTime currentDate,
      String filename,
      Double amount)
      throws InterruptedException {
    // Amount must be POSITIVE if matching to a deposit, or NEGATIVE if matching to a check
    UseBankTransactionTo bankTransactionAPI = new UseBankTransactionTo();
    BankTransactionImportPostRequest bankTransactionImportPostRequest =
        formatBankTransactionImportRequest(
            bank, currentDate.toString(), "EMSAuto " + Util.randomText(10), amount, filename);
    actor.attemptsTo(
        bankTransactionAPI.POSTBankTransactionImportOnTheBanktransactionController(
            bankTransactionImportPostRequest, ""));

    // Verify the import was successful
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    BankTransactionImportResponse bankTransactionImportResponse =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", BankTransactionImportResponse.class);
    assertThat(bankTransactionImportResponse.getBankStatementHeaderId()).isNotNull();
    assertThat(bankTransactionImportResponse.getEventLogReferenceId()).isNotNull();
    Thread.sleep(3000);
    List<String> errorMessages =
        ErrorLogUtil.getErrorMessages(
            ErrorLogUtil.getErrorsAndWarningsByReferenceId(
                actor, bankTransactionImportResponse.getEventLogReferenceId()));
    assertThat(errorMessages).isEmpty();

    return bankTransactionImportPostRequest;
  }

  public static SortedPagingRequestBankTransactionsSearchPostRequestBankTransactionsSortOptions
      formatBankTransactionSearchRequest(
          String bankCode,
          LocalDateTime currentDate,
          SortOptionBankTransactionsSortOptions.FieldSortEnum columnName,
          boolean isDescending) {
    SortedPagingRequestBankTransactionsSearchPostRequestBankTransactionsSortOptions
        pagingRequestBankTransactionsSearchPostRequest =
            new SortedPagingRequestBankTransactionsSearchPostRequestBankTransactionsSortOptions();
    SortOptionBankTransactionsSortOptions sortOptions = new SortOptionBankTransactionsSortOptions();
    sortOptions.setFieldSort(columnName);
    sortOptions.setIsDescendingOrder(isDescending);
    pagingRequestBankTransactionsSearchPostRequest.setSortOption(sortOptions);
    BankTransactionsSearchPostRequest bankTransactionsSearchPostRequest =
        new BankTransactionsSearchPostRequest();
    bankTransactionsSearchPostRequest.setBankCode(bankCode);
    bankTransactionsSearchPostRequest.setStartDate(currentDate.minusYears(1).toString());
    bankTransactionsSearchPostRequest.setEndDate(currentDate.plusYears(1).toString());
    pagingRequestBankTransactionsSearchPostRequest.setModel(bankTransactionsSearchPostRequest);

    return pagingRequestBankTransactionsSearchPostRequest;
  }

  public static List<DepositsSearchResponse> getUnmatchedDepositsForBank(
      Actor actor, String bankCode) {
    UseDepositsTo depositsAPI = new UseDepositsTo();

    SortedPagingRequestDepositsSearchPostRequestDepositsSortOptions
        pagingDepositsSearchPostRequest =
            new SortedPagingRequestDepositsSearchPostRequestDepositsSortOptions();

    SortOptionDepositsSortOptions sortOptions = new SortOptionDepositsSortOptions();
    sortOptions.setFieldSort(SortOptionDepositsSortOptions.FieldSortEnum.POSTEDDATE);
    sortOptions.setIsDescendingOrder(true);
    pagingDepositsSearchPostRequest.setSortOption(sortOptions);

    DepositsSearchPostRequest depositsSearchPostRequest = new DepositsSearchPostRequest();
    depositsSearchPostRequest.setBankCode(bankCode);
    depositsSearchPostRequest.setStartDate(LocalDateTime.now().minusYears(10).toString());
    depositsSearchPostRequest.setEndDate(LocalDateTime.now().plusYears(10).toString());
    pagingDepositsSearchPostRequest.setModel(depositsSearchPostRequest);

    actor.attemptsTo(
        depositsAPI.POSTDepositsUnmatchedSearchOnTheDepositsController(
            pagingDepositsSearchPostRequest, ""));

    return LastResponse.received()
        .answeredBy(actor)
        .getBody()
        .jsonPath()
        .getList("response", DepositsSearchResponse.class);
  }

  public static DepositsSearchResponse getRandomDepositForBank(Actor actor, String bankCode) {
    List<DepositsSearchResponse> deposits = getUnmatchedDepositsForBank(actor, bankCode);
    int randomNum = new Random().nextInt(deposits.size());
    return deposits.get(randomNum);
  }

  public static List<ChecksSearchResponse> getUnmatchedChecksForBank(Actor actor, String bankCode) {
    UseChecksTo checksAPI = new UseChecksTo();

    SortedPagingRequestChecksSearchPostRequestChecksSortOptions pagingChecksSearchPostRequest =
        new SortedPagingRequestChecksSearchPostRequestChecksSortOptions();

    SortOptionChecksSortOptions sortOptions = new SortOptionChecksSortOptions();
    sortOptions.setFieldSort(SortOptionChecksSortOptions.FieldSortEnum.CHECKDATE);
    sortOptions.setIsDescendingOrder(true);
    pagingChecksSearchPostRequest.setSortOption(sortOptions);

    ChecksSearchPostRequest checksSearchPostRequest = new ChecksSearchPostRequest();
    checksSearchPostRequest.setBankCode(bankCode);
    checksSearchPostRequest.setStartDate(LocalDateTime.now().minusYears(10).toString());
    checksSearchPostRequest.setEndDate(LocalDateTime.now().plusYears(10).toString());
    pagingChecksSearchPostRequest.setModel(checksSearchPostRequest);

    actor.attemptsTo(
        checksAPI.POSTChecksUnmatchedSearchOnTheChecksController(
            pagingChecksSearchPostRequest, ""));

    return LastResponse.received()
        .answeredBy(actor)
        .getBody()
        .jsonPath()
        .getList("response", ChecksSearchResponse.class);
  }

  public static ChecksSearchResponse getRandomCheckForBank(Actor actor, String bankCode) {
    List<ChecksSearchResponse> checks = getUnmatchedChecksForBank(actor, bankCode);
    int randomNum = new Random().nextInt(checks.size());
    return checks.get(randomNum);
  }

  public static BankAccountResponse getRandomBankWithAtLeastOneDeposit(
      Actor actor, boolean includeHidden) {
    List<BankAccountResponse> banks = getAllAvailableBanks(actor, includeHidden);
    int tries = 0;
    int maxTries = 20;
    int randomNum = new Random().nextInt(banks.size());
    while (tries < maxTries) {
      randomNum = new Random().nextInt(banks.size());
      if (getUnmatchedDepositsForBank(actor, banks.get(randomNum).getBankCode()).size() > 0) {
        break;
      }
      tries++;
    }
    // test will be skipped if max tries are used without finding a bank with deposit
    assumeThat(tries).isLessThan(maxTries);
    return banks.get(randomNum);
  }

  public static BankAccountResponse getRandomBankWithAtLeastOneCheck(
      Actor actor, boolean includeHidden) {
    List<BankAccountResponse> banks = getAllAvailableBanks(actor, includeHidden);
    int tries = 0;
    int maxTries = 20;
    int randomNum = new Random().nextInt(banks.size());
    while (tries < maxTries) {
      randomNum = new Random().nextInt(banks.size());
      if (getUnmatchedChecksForBank(actor, banks.get(randomNum).getBankCode()).size() > 0) {
        break;
      }
      tries++;
    }
    // test will be skipped if max tries are used without finding a bank with check
    assumeThat(tries).isLessThan(maxTries);
    return banks.get(randomNum);
  }
}
