package com.vertafore.test.services.bank_transaction;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseBankTransactionTo;
import com.vertafore.test.util.BankUtil;
import com.vertafore.test.util.CSVUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
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

    BankTransactionImportPostRequest bankTransactionImportPostRequest =
        BankUtil.importDummyBankTransaction(
            AADM_User, randomBank, currentDate, CSVUtil.generateUniqueFilename("BTD1"));
    String expectedDescription =
        bankTransactionImportPostRequest.getBankStatementDetails().get(0).getDescription();

    Thread.sleep(3000);

    BankTransactionsSearchResponse bankTransaction =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescription);
    assertThat(bankTransaction).isNotNull();

    BankTransactionDeletePostRequest bankTransactionDeletePostRequest =
        new BankTransactionDeletePostRequest();
    List<String> bankStatementDetailIds = new ArrayList<>();
    bankStatementDetailIds.add(bankTransaction.getBankStatementDetailId());
    bankTransactionDeletePostRequest.setBankStatementDetailIds(bankStatementDetailIds);

    AADM_User.attemptsTo(
        bankTransactionAPI.POSTBankTransactionDeleteOnTheBanktransactionController(
            bankTransactionDeletePostRequest, ""));

    BankTransactionsSearchResponse bankTransactionAfterDelete =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescription);
    assertThat(bankTransactionAfterDelete).isNull();

    BankTransactionImportPostRequest bankTransactionImportPostRequest2 =
        BankUtil.importDummyBankTransaction(
            AADM_User, randomBank, currentDate, CSVUtil.generateUniqueFilename("BTD2"));
    String expectedDescription2 =
        bankTransactionImportPostRequest2.getBankStatementDetails().get(0).getDescription();
    Thread.sleep(3000);
    BankTransactionsSearchResponse bankTransaction2 =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescription2);
    assertThat(bankTransaction2).isNotNull();
    BankTransactionDeletePostRequest bankTransactionDeletePostRequest2 =
        new BankTransactionDeletePostRequest();
    List<String> bankStatementDetailIds2 = new ArrayList<>();
    bankStatementDetailIds.add(bankTransaction2.getBankStatementDetailId());
    bankTransactionDeletePostRequest.setBankStatementDetailIds(bankStatementDetailIds2);
    ORAN_App.attemptsTo(
        bankTransactionAPI.POSTBankTransactionDeleteOnTheBanktransactionController(
            bankTransactionDeletePostRequest2, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    BankTransactionImportPostRequest bankTransactionImportPostRequest3 =
        BankUtil.importDummyBankTransaction(
            AADM_User, randomBank, currentDate, CSVUtil.generateUniqueFilename("BTD3"));
    String expectedDescription3 =
        bankTransactionImportPostRequest3.getBankStatementDetails().get(0).getDescription();
    Thread.sleep(3000);
    BankTransactionsSearchResponse bankTransaction3 =
        BankUtil.getBankTransactionByDescription(
            AADM_User, randomBank.getBankCode(), currentDate, expectedDescription3);
    assertThat(bankTransaction3).isNotNull();
    BankTransactionDeletePostRequest bankTransactionDeletePostRequest3 =
        new BankTransactionDeletePostRequest();
    List<String> bankStatementDetailIds3 = new ArrayList<>();
    bankStatementDetailIds.add(bankTransaction3.getBankStatementDetailId());
    bankTransactionDeletePostRequest.setBankStatementDetailIds(bankStatementDetailIds3);
    VADM_Admin.attemptsTo(
        bankTransactionAPI.POSTBankTransactionDeleteOnTheBanktransactionController(
            bankTransactionDeletePostRequest3, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }
}
