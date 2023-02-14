package com.vertafore.test.util;

import com.vertafore.test.models.ems.BankAccountResponse;
import com.vertafore.test.models.ems.BankTransactionsSearchPostRequest;
import com.vertafore.test.models.ems.BankTransactionsSearchResponse;
import com.vertafore.test.models.ems.PagingRequestBankTransactionsSearchPostRequest;
import com.vertafore.test.servicewrappers.UseBankAccountsTo;
import com.vertafore.test.servicewrappers.UseBankTransactionTo;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class BankUtil {

  public static List<BankAccountResponse> getAllAvailableBanks(Actor actor, boolean includeHidden) {
    UseBankAccountsTo bankAccountsAPI = new UseBankAccountsTo();
    actor.attemptsTo(
        bankAccountsAPI.GETBankAccountsOnTheBankaccountsController(includeHidden, "string"));
    return LastResponse.received()
        .answeredBy(actor)
        .getBody()
        .jsonPath()
        .getList("", BankAccountResponse.class);
  }

  public static BankAccountResponse getRandomBank(Actor actor, boolean includeHidden) {
    List<BankAccountResponse> banks = getAllAvailableBanks(actor, includeHidden);
    int randomNum = new Random().nextInt(banks.size());
    return banks.get(randomNum);
  }

  public static List<BankTransactionsSearchResponse> getBankTransactions(
      Actor actor, String bankCode, LocalDateTime startDate) {
    UseBankTransactionTo bankTransactionAPI = new UseBankTransactionTo();
    PagingRequestBankTransactionsSearchPostRequest pagingRequestBankTransactionsSearchPostRequest =
        new PagingRequestBankTransactionsSearchPostRequest();
    BankTransactionsSearchPostRequest bankTransactionsSearchPostRequest =
        new BankTransactionsSearchPostRequest();
    bankTransactionsSearchPostRequest.setBankCode(bankCode);
    bankTransactionsSearchPostRequest.setStartDate(startDate.toString());
    bankTransactionsSearchPostRequest.setEndDate(startDate.plusMonths(1).toString());
    pagingRequestBankTransactionsSearchPostRequest.setModel(bankTransactionsSearchPostRequest);

    actor.attemptsTo(
        bankTransactionAPI.POSTBankTransactionSearchOnTheBanktransactionController(
            pagingRequestBankTransactionsSearchPostRequest, ""));

    return LastResponse.received()
        .answeredBy(actor)
        .getBody()
        .jsonPath()
        .getList("response", BankTransactionsSearchResponse.class);
  }

  public static BankTransactionsSearchResponse getBankTransactionByDescription(
      Actor actor, String bankCode, LocalDateTime startDate, String expectedDescription) {
    List<BankTransactionsSearchResponse> bankTransactions =
        getBankTransactions(actor, bankCode, startDate);
    List<BankTransactionsSearchResponse> matchingBankTransactions =
        bankTransactions
            .stream()
            .filter(tran -> tran.getDescription().equals(expectedDescription))
            .collect(Collectors.toList());
    if (matchingBankTransactions.size() == 0) {
      return null;
    }
    return matchingBankTransactions.get(0);
  }
}
