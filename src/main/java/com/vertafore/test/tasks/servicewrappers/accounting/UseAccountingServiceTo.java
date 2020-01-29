package com.vertafore.test.tasks.servicewrappers.accounting;

import static com.vertafore.test.abilities.CallTitanApi.as;
import static net.serenitybdd.rest.SerenityRest.rest;

import net.serenitybdd.screenplay.AnonymousPerformableFunction;
import net.serenitybdd.screenplay.Task;

public class UseAccountingServiceTo {

  private static final String GET_ACTIVE_JOURNAL = "journals/active";
  // We need to not just have {id} - it should be specific like journalId
  private static final String DELETE_ACTIVE_JOURNAL = "journals/{journalId}";
  private static final String CREATE_ACTIVE_JOURNAL = "journals";

  public static AnonymousPerformableFunction getActiveJournal() {
    return Task.where(
        "",
        actor -> {
          rest().with().get(as(actor).toEndpoint(GET_ACTIVE_JOURNAL));
        });
  }

  public static AnonymousPerformableFunction deleteJournalById(String journalId) {
    return Task.where(
        "",
        actor -> {
          rest()
              .with()
              .pathParam("journalId", journalId)
              .delete(as(actor).toEndpoint(DELETE_ACTIVE_JOURNAL));
        });
  }

  public static AnonymousPerformableFunction createJournal(String body) {
    return Task.where(
        "",
        actor -> {
          rest().with().body(body).post(as(actor).toEndpoint(CREATE_ACTIVE_JOURNAL));
        });
  }
}
