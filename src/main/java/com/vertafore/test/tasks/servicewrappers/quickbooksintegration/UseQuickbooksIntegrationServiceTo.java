package com.vertafore.test.tasks.servicewrappers.quickbooksintegration;

import static com.vertafore.test.abilities.CallTitanApi.as;
import static net.serenitybdd.rest.SerenityRest.rest;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class UseQuickbooksIntegrationServiceTo {

  private static final String GET_ALL_QUICKBOOKS_ACCOUNTS = "/accounts{?pageSize,page}";
  private static final String GET_QUICKBOOKS_URL = "/authorization/connect-url";
  private static final String GET_QUICKBOOKS_AUTHORIZATION_STATUS = "/authorization/status";
  private static final String DELETE_QUICKBOOKS_CREDENTIALS = "/authorization";
  private static final String STORE_QUICKBOOKS_CREDENTIALS = "/authorization";
  private static final String PATCH_ACCOUNT_MAPPING_BY_ACCOUNT_ID = "/accounts/{accountId}/mapping";
  private static final String CREATE_ACCOUNT_MAPPING_BY_ACCOUNT_ID =
      "/accounts/{accountId}/mapping";
  private static final String GET_ACCOUNT_MAPPING_BY_ACCOUNT_ID = "/accounts/{accountId}/mapping";

  public static Performable getAllQuickbooksAccounts(String pageSize, String page) {
    return Task.where(
        "{0} Retrieves all Accounts from the QuickBooks API using existing Quickbooks authorization. Quickbooks authorization must be stored for this endpoint to work.",
        actor -> {
          rest()
              .with()
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get(as(actor).toEndpoint(GET_ALL_QUICKBOOKS_ACCOUNTS));
        });
  }

  public static Performable getQuickbooksUrl() {
    return Task.where(
        "{0} Retrieves and formats the URL for UI to present a login flow.",
        actor -> {
          rest().with().get(as(actor).toEndpoint(GET_QUICKBOOKS_URL));
        });
  }

  public static Performable getQuickbooksAuthorizationStatus() {
    return Task.where(
        "{0} Retrieves the status of the QuickBooks integration under the given tenant/entity combo.",
        actor -> {
          rest().with().get(as(actor).toEndpoint(GET_QUICKBOOKS_AUTHORIZATION_STATUS));
        });
  }

  public static Performable deleteQuickbooksCredentials() {
    return Task.where(
        "{0} Deletes current QuickBooks credentials from the system.",
        actor -> {
          rest().with().delete(as(actor).toEndpoint(DELETE_QUICKBOOKS_CREDENTIALS));
        });
  }

  public static Performable storeQuickbooksCredentials(Object body) {
    return Task.where(
        "{0} Requests and stores credentials from QuickBooks, to be used for service operations.",
        actor -> {
          rest().with().body(body).put(as(actor).toEndpoint(STORE_QUICKBOOKS_CREDENTIALS));
        });
  }

  public static Performable updatePatchAccountMappingByAccountId(String accountId, Object body) {
    return Task.where(
        "{0} Patch the Account mapping specified by the Account ID provided in the URI. The only field that can be updated is the `key` field. ",
        actor -> {
          rest()
              .with()
              .pathParam("accountId", accountId)
              .body(body)
              .patch(as(actor).toEndpoint(PATCH_ACCOUNT_MAPPING_BY_ACCOUNT_ID));
        });
  }

  public static Performable createAccountMappingByAccountId(String accountId, Object body) {
    return Task.where(
        "{0} Creates an account mapping for the associated account.",
        actor -> {
          rest()
              .with()
              .pathParam("accountId", accountId)
              .body(body)
              .post(as(actor).toEndpoint(CREATE_ACCOUNT_MAPPING_BY_ACCOUNT_ID));
        });
  }

  public static Performable getAccountMappingByAccountId(String accountId) {
    return Task.where(
        "{0} Retrieves the Account Mapping of an associated Account.",
        actor -> {
          rest()
              .with()
              .pathParam("accountId", accountId)
              .get(as(actor).toEndpoint(GET_ACCOUNT_MAPPING_BY_ACCOUNT_ID));
        });
  }
}
