package com.vertafore.test.tasks.servicewrappers.quickbooksintegration;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import static com.vertafore.test.abilities.CallTitanApi.as;
import static net.serenitybdd.rest.SerenityRest.rest;

public class UseQuickbooksIntegrationServiceTo {

	private static final String GET_QUICK_BOOKS_ACCOUNTS_USING_GET = "accounts";
	private static final String CONNECT_TO_QUICK_BOOKS_USING_GET = "authorization/connect-url";
	private static final String RETRIEVE_QUICK_BOOKS_INTEGRATION_STATUS_USING_GET = "authorization/status";
	private static final String DELETE_AUTHORIZATION_CREDENTIAL_USING_DELETE = "authorization";
	private static final String PUT_AUTHORIZATION_CREDENTIAL_USING_PUT = "authorization";
	private static final String UPDATE_ACCOUNT_MAPPING_USING_PATCH = "accounts/{accountId}/mapping";
	private static final String CREATE_ACCOUNT_MAPPING_USING_POST = "accounts/{accountId}/mapping";
	private static final String GET_ACCOUNT_MAPPING_USING_GET = "accounts/{accountId}/mapping";

	public static Performable getQuickBooksAccountsUsingGet(String pageSize, String page){
		return Task.where(
		"{0} Get all QuickBooks Accounts", 
			actor -> {
				rest().with().contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(as(actor).toEndpoint(GET_QUICK_BOOKS_ACCOUNTS_USING_GET));
			}
		);
	}

	public static Performable connectToQuickBooksUsingGet(){
		return Task.where(
		"{0} Get QuickBooks URL", 
			actor -> {
				rest().with().contentType("application/json").get(as(actor).toEndpoint(CONNECT_TO_QUICK_BOOKS_USING_GET));
			}
		);
	}

	public static Performable retrieveQuickBooksIntegrationStatusUsingGet(){
		return Task.where(
		"{0} Get QuickBooks Authorization Status.", 
			actor -> {
				rest().with().contentType("application/json").get(as(actor).toEndpoint(RETRIEVE_QUICK_BOOKS_INTEGRATION_STATUS_USING_GET));
			}
		);
	}

	public static Performable deleteAuthorizationCredentialUsingDelete(){
		return Task.where(
		"{0} Delete QuickBooks Credentials", 
			actor -> {
				rest().with().contentType("application/json").delete(as(actor).toEndpoint(DELETE_AUTHORIZATION_CREDENTIAL_USING_DELETE));
			}
		);
	}

	public static Performable putAuthorizationCredentialUsingPut(Object body){
		return Task.where(
		"{0} Store QuickBooks Credentials", 
			actor -> {
				rest().with().contentType("application/json").body(body).put(as(actor).toEndpoint(PUT_AUTHORIZATION_CREDENTIAL_USING_PUT));
			}
		);
	}

	public static Performable updateAccountMappingUsingPatch(String accountId, Object body){
		return Task.where(
		"{0} Update/Patch Account mapping by Account ID", 
			actor -> {
				rest().with().contentType("application/json-patch+json").pathParam("accountId", accountId).body(body).patch(as(actor).toEndpoint(UPDATE_ACCOUNT_MAPPING_USING_PATCH));
			}
		);
	}

	public static Performable createAccountMappingUsingPost(String accountId, Object body){
		return Task.where(
		"{0} Create Account Mapping by Account ID", 
			actor -> {
				rest().with().contentType("application/json").pathParam("accountId", accountId).body(body).post(as(actor).toEndpoint(CREATE_ACCOUNT_MAPPING_USING_POST));
			}
		);
	}

	public static Performable getAccountMappingUsingGet(String accountId){
		return Task.where(
		"{0} Get Account Mapping by Account ID", 
			actor -> {
				rest().with().contentType("application/json").pathParam("accountId", accountId).get(as(actor).toEndpoint(GET_ACCOUNT_MAPPING_USING_GET));
			}
		);
	}



}
