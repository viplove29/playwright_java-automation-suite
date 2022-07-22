package com.vertafore.test.services.balance_journal_entries;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.PagingResponseBalanceJournalEntriesResponse;
import com.vertafore.test.servicewrappers.UseBalanceJournalEntriesTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class GET_BalanceJournalEntriesListCollections extends TokenSuperClass {

  @Test
  public void getBJEListCollectionsGetsBJEListCollections() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseBalanceJournalEntriesTo balanceJournalEntriesApi = new UseBalanceJournalEntriesTo();

    int skip = 0;
    int maxRecords = 1000;

    VADM_Admin.attemptsTo(
        balanceJournalEntriesApi
            .GETBalanceJournalEntriesListCollectionsOnTheBalancejournalentriesController(
                skip, maxRecords, null, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        balanceJournalEntriesApi
            .GETBalanceJournalEntriesListCollectionsOnTheBalancejournalentriesController(
                skip, maxRecords, null, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ORAN_App.attemptsTo(
        balanceJournalEntriesApi
            .GETBalanceJournalEntriesListCollectionsOnTheBalancejournalentriesController(
                skip, maxRecords, null, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PagingResponseBalanceJournalEntriesResponse response =
        LastResponse.received()
            .answeredBy(ORAN_App)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseBalanceJournalEntriesResponse.class);

    assertThat(response).isNotNull();
    assertThat(response.getClass().getDeclaredFields().length).isEqualTo(4);
  }
}
