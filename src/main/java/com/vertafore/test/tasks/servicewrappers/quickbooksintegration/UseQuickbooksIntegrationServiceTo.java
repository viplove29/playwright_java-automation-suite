package com.vertafore.test.tasks.servicewrappers.quickbooksintegration;

import static com.vertafore.test.abilities.CallTitanApi.as;
import static net.serenitybdd.rest.SerenityRest.rest;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class UseQuickbooksIntegrationServiceTo {

  private static final String GET_ALL_QUICKBOOKS_ACCOUNTS = "accounts{?pageSize,page}";
  private static final String GET_QUICKBOOKS_URL = "authorization/connect-url";
  private static final String GET_QUICKBOOKS_AUTHORIZATION_STATUS = "authorization/status";
  private static final String DELETE_QUICKBOOKS_CREDENTIALS = "authorization";
  private static final String STORE_QUICKBOOKS_CREDENTIALS = "authorization";
  private static final String PATCH_ACCOUNT_MAPPING_BY_ACCOUNT_ID = "accounts/{accountId}/mapping";
  private static final String CREATE_ACCOUNT_MAPPING_BY_ACCOUNT_ID = "accounts/{accountId}/mapping";
  private static final String GET_ACCOUNT_MAPPING_BY_ACCOUNT_ID = "accounts/{accountId}/mapping";

  public static Performable getAllQuickbooksAccounts(String pageSize, String page) {
    return Task.where(
        "{0} Get all QuickBooks Accounts",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get(as(actor).toEndpoint(GET_ALL_QUICKBOOKS_ACCOUNTS));
        });
  }

  public static Performable getQuickbooksUrl() {
    return Task.where(
        "{0} Get QuickBooks URL",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .get(as(actor).toEndpoint(GET_QUICKBOOKS_URL));
        });
  }

  public static Performable getQuickbooksAuthorizationStatus() {
    return Task.where(
        "{0} Get QuickBooks Authorization Status.",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .get(as(actor).toEndpoint(GET_QUICKBOOKS_AUTHORIZATION_STATUS));
        });
  }

  public static Performable deleteQuickbooksCredentials() {
    return Task.where(
        "{0} Delete QuickBooks Credentials",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .delete(as(actor).toEndpoint(DELETE_QUICKBOOKS_CREDENTIALS));
        });
  }

  public static Performable storeQuickbooksCredentials(Object body) {
    return Task.where(
        "{0} Store QuickBooks Credentials",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .body(body)
              .put(as(actor).toEndpoint(STORE_QUICKBOOKS_CREDENTIALS));
        });
  }

  public static Performable updatePatchAccountMappingByAccountId(String accountId, Object body) {
    return Task.where(
        "{0} Update/Patch Account mapping by Account ID",
        actor -> {
          rest()
              .with()
              .contentType("application/json-patch+json")
              .pathParam("accountId", accountId)
              .body(body)
              .patch(as(actor).toEndpoint(PATCH_ACCOUNT_MAPPING_BY_ACCOUNT_ID));
        });
  }

  public static Performable createAccountMappingByAccountId(String accountId, Object body) {
    return Task.where(
        "{0} Create Account Mapping by Account ID",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("accountId", accountId)
              .body(body)
              .post(as(actor).toEndpoint(CREATE_ACCOUNT_MAPPING_BY_ACCOUNT_ID));
        });
  }

  public static Performable getAccountMappingByAccountId(String accountId) {
    return Task.where(
        "{0} Get Account Mapping by Account ID",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("accountId", accountId)
              .get(as(actor).toEndpoint(GET_ACCOUNT_MAPPING_BY_ACCOUNT_ID));
        });
  }
}
