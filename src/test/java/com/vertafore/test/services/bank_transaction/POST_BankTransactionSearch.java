package com.vertafore.test.services.bank_transaction;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseBankTransactionTo;
import com.vertafore.test.util.BankUtil;
import com.vertafore.test.util.CSVUtil;
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
        AADM_User, randomBank, currentDate, CSVUtil.generateUniqueFilename("BTS"));

    Thread.sleep(3000);

    PagingRequestBankTransactionsSearchPostRequest pagingRequestBankTransactionsSearchPostRequest =
        new PagingRequestBankTransactionsSearchPostRequest();
    BankTransactionsSearchPostRequest bankTransactionsSearchPostRequest =
        new BankTransactionsSearchPostRequest();
    bankTransactionsSearchPostRequest.setBankCode(randomBank.getBankCode());
    bankTransactionsSearchPostRequest.setStartDate(LocalDateTime.now().minusYears(1).toString());
    bankTransactionsSearchPostRequest.setEndDate(LocalDateTime.now().plusYears(1).toString());
    pagingRequestBankTransactionsSearchPostRequest.setModel(bankTransactionsSearchPostRequest);

    AADM_User.attemptsTo(
        bankTransactionAPI.POSTBankTransactionSearchOnTheBanktransactionController(
            pagingRequestBankTransactionsSearchPostRequest, ""));

    List<BankTransactionsSearchResponse> bankTransactions =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("response", BankTransactionsSearchResponse.class);
    assertThat(bankTransactions).isNotNull();
    assertThat(bankTransactions.get(0)).isNotNull();
    assertThat(bankTransactions.get(0).getBankName()).isEqualTo(bankName);

    ORAN_App.attemptsTo(
        bankTransactionAPI.POSTBankTransactionSearchOnTheBanktransactionController(
            pagingRequestBankTransactionsSearchPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        bankTransactionAPI.POSTBankTransactionSearchOnTheBanktransactionController(
            pagingRequestBankTransactionsSearchPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }
}
