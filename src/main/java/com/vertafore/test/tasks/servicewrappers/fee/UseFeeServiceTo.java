package com.vertafore.test.tasks.servicewrappers.fee;

import static com.vertafore.test.abilities.CallTitanApi.as;
import static net.serenitybdd.rest.SerenityRest.rest;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class UseFeeServiceTo {

  private static final String RETURNS_ALL_FEES = "fees{?pageSize,page}";
  private static final String RETURNS_ALL_TAXES = "taxes{?pageSize,page}";

  public static Performable returnsAllFees(String pageSize, String page) {
    return Task.where(
        "{0} Returns all fees",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get(as(actor).toEndpoint(RETURNS_ALL_FEES));
        });
  }

  public static Performable returnsAllTaxes(String pageSize, String page) {
    return Task.where(
        "{0} Returns all taxes",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get(as(actor).toEndpoint(RETURNS_ALL_TAXES));
        });
  }
}
