package com.vertafore.test.services.balance_journal_entries;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.BalanceJournalEntriesPostingPostRequest;
import com.vertafore.test.models.ems.ImportBalanceJournalEntryResponse;
import com.vertafore.test.servicewrappers.UseBalanceJournalEntriesTo;
import com.vertafore.test.util.EmployeeUtil;
import java.io.UnsupportedEncodingException;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class POST_BalanceJournalEntriesPostCollection extends TokenSuperClass {

  @Test
  public void postBalanceJournalEntriesPostCollectionBaselineTest()
      throws UnsupportedEncodingException {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseBalanceJournalEntriesTo balanceJournalEntriesApi = new UseBalanceJournalEntriesTo();

    ImportBalanceJournalEntryResponse importBalanceJournalEntryResponse =
        EmployeeUtil.putBalanceJournalEntriesCustomerImport(AADM_User);
    String journalEntryCollectionId =
        importBalanceJournalEntryResponse.getBalanceJournalEntryCollectionId();

    BalanceJournalEntriesPostingPostRequest balanceJournalEntriesPostingPostRequest =
        new BalanceJournalEntriesPostingPostRequest();
    balanceJournalEntriesPostingPostRequest.setBalanceJournalEntryCollectionId(
        journalEntryCollectionId);

    VADM_Admin.attemptsTo(
        balanceJournalEntriesApi
            .POSTBalanceJournalEntriesPostCollectionOnTheBalancejournalentriesController(
                balanceJournalEntriesPostingPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        balanceJournalEntriesApi
            .POSTBalanceJournalEntriesPostCollectionOnTheBalancejournalentriesController(
                balanceJournalEntriesPostingPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Post one more journal for the ORAN_App user
    importBalanceJournalEntryResponse =
        EmployeeUtil.putBalanceJournalEntriesCustomerImport(AADM_User);
    journalEntryCollectionId =
        importBalanceJournalEntryResponse.getBalanceJournalEntryCollectionId();

    balanceJournalEntriesPostingPostRequest = new BalanceJournalEntriesPostingPostRequest();
    balanceJournalEntriesPostingPostRequest.setBalanceJournalEntryCollectionId(
        journalEntryCollectionId);

    ORAN_App.attemptsTo(
        balanceJournalEntriesApi
            .POSTBalanceJournalEntriesPostCollectionOnTheBalancejournalentriesController(
                balanceJournalEntriesPostingPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
