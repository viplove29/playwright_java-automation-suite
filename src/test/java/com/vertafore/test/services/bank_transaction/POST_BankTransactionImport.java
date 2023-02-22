package com.vertafore.test.services.bank_transaction;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseBankTransactionTo;
import com.vertafore.test.util.BankUtil;
import com.vertafore.test.util.CSVUtil;
import com.vertafore.test.util.Util;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_BankTransactionImport extends TokenSuperClass {
  private static final int MAX_TRIES = 5;

  private BankTransactionImportPostRequest getBankTransactionImportData(
      BankAccountResponse bank,
      String dateString,
      String description,
      Double amount,
      String csvFilename) {
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

  @Test
  public void bankTransactionImportIsSuccessful() {
    Actor AADM_User = theActorCalled("AADM_User");
    List<Actor> actors = new ArrayList<>();
    actors.add(AADM_User);
    actors.add(theActorCalled("ORAN_App"));
    actors.add(theActorCalled("VADM_Admin"));

    UseBankTransactionTo bankTransactionAPI = new UseBankTransactionTo();

    BankAccountResponse randomBank = BankUtil.getRandomBank(AADM_User, false);

    for (Actor actor : actors) {
      Double expectedAmount = null;
      String expectedFilename = null;
      BankTransactionsSearchResponse bankTransaction = null;

      int tries = 0;
      while (tries < MAX_TRIES) {
        LocalDateTime currentDate = LocalDateTime.now();
        BankTransactionImportPostRequest bankTransactionImportPostRequest =
            getBankTransactionImportData(
                randomBank,
                currentDate.toString(),
                "EMSAuto " + Util.randomText(10),
                Util.randomDollarAmount(1.00, 99999.99),
                CSVUtil.generateUniqueFilename("BTI"));

        String expectedDescription =
            bankTransactionImportPostRequest.getBankStatementDetails().get(0).getDescription();
        expectedAmount =
            bankTransactionImportPostRequest.getBankStatementDetails().get(0).getAmount();
        expectedFilename = bankTransactionImportPostRequest.getFileName();

        actor.attemptsTo(
            bankTransactionAPI.POSTBankTransactionImportOnTheBanktransactionController(
                bankTransactionImportPostRequest, ""));

        if (actor.getName().equals("VADM_Admin")) {
          assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
          break;
        }

        BankTransactionImportResponse bankTransactionImportResponse =
            LastResponse.received()
                .answeredBy(actor)
                .getBody()
                .jsonPath()
                .getObject("", BankTransactionImportResponse.class);
        assertThat(bankTransactionImportResponse).isNotNull();
        assertThat(bankTransactionImportResponse.getClass().getDeclaredFields().length)
            .isEqualTo(2);
        assertThat(bankTransactionImportResponse.getBankStatementHeaderId()).isNotNull();
        assertThat(bankTransactionImportResponse.getEventLogReferenceId()).isNotNull();

        bankTransaction =
            BankUtil.getBankTransactionByDescription(
                actor, randomBank.getBankCode(), currentDate, expectedDescription);
        tries++;
        if (bankTransaction != null) {
          break;
        }
      }
      if (actor.getName().equals("VADM_Admin")) {
        return;
      }
      assumeThat(tries)
          .as("Could not find an eligible bank in " + MAX_TRIES + " tries. Skipping test.")
          .isLessThan(MAX_TRIES);
      assertThat(bankTransaction).isNotNull();
      assertThat(bankTransaction.getDeposit()).isEqualTo(expectedAmount);
      assertThat(bankTransaction.getFilename()).isEqualTo(expectedFilename);
    }
  }
}
