package com.vertafore.test.tasks.servicewrappers.quickbooksintegration;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import com.vertafore.test.abilities.CallTitanApi;

/**
* This class was automatically generated on 2020/03/25 13:40:50

*/
public class UseQuickbooksIntegrationServiceTo {

	private static final String THIS_SERVICE = "quickbooks-integration";
	private static final String GET_QUICK_BOOKS_ACCOUNTS_USING_GET_ON_THE_QUICKBOOKS_ACCOUNT_CONTROLLER = "accounts";
	private static final String CONNECT_TO_QUICK_BOOKS_USING_GET_ON_THE_AUTHORIZATION_CONTROLLER = "authorization/connect-url";
	private static final String RETRIEVE_QUICK_BOOKS_INTEGRATION_STATUS_USING_GET_ON_THE_STATUS_CONTROLLER = "authorization/status";
	private static final String DELETE_AUTHORIZATION_CREDENTIAL_USING_DELETE_ON_THE_AUTHORIZATION_CONTROLLER = "authorization";
	private static final String PUT_AUTHORIZATION_CREDENTIAL_USING_PUT_ON_THE_AUTHORIZATION_CONTROLLER = "authorization";
	private static final String UPDATE_ACCOUNT_MAPPING_USING_PATCH_ON_THE_ACCOUNT_MAPPING_CONTROLLER = "accounts/{accountId}/mapping";
	private static final String CREATE_ACCOUNT_MAPPING_USING_POST_ON_THE_ACCOUNT_MAPPING_CONTROLLER = "accounts/{accountId}/mapping";
	private static final String GET_ACCOUNT_MAPPING_USING_GET_ON_THE_ACCOUNT_MAPPING_CONTROLLER = "accounts/{accountId}/mapping";

	public static Performable getQuickBooksAccountsUsingGetOnTheQuickbooksAccountController(String pageSize, String page){
		return Task.where(
		"{0} Get all QuickBooks Accounts", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(GET_QUICK_BOOKS_ACCOUNTS_USING_GET_ON_THE_QUICKBOOKS_ACCOUNT_CONTROLLER);
			}
		);
	}

	public static Performable connectToQuickBooksUsingGetOnTheAuthorizationController(){
		return Task.where(
		"{0} Get QuickBooks URL", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").get(CONNECT_TO_QUICK_BOOKS_USING_GET_ON_THE_AUTHORIZATION_CONTROLLER);
			}
		);
	}

	public static Performable retrieveQuickBooksIntegrationStatusUsingGetOnTheStatusController(){
		return Task.where(
		"{0} Get QuickBooks Authorization Status.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").get(RETRIEVE_QUICK_BOOKS_INTEGRATION_STATUS_USING_GET_ON_THE_STATUS_CONTROLLER);
			}
		);
	}

	public static Performable deleteAuthorizationCredentialUsingDeleteOnTheAuthorizationController(){
		return Task.where(
		"{0} Delete QuickBooks Credentials", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").delete(DELETE_AUTHORIZATION_CREDENTIAL_USING_DELETE_ON_THE_AUTHORIZATION_CONTROLLER);
			}
		);
	}

	public static Performable putAuthorizationCredentialUsingPutOnTheAuthorizationController(Object body){
		return Task.where(
		"{0} Store QuickBooks Credentials", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).put(PUT_AUTHORIZATION_CREDENTIAL_USING_PUT_ON_THE_AUTHORIZATION_CONTROLLER);
			}
		);
	}

	public static Performable updateAccountMappingUsingPatchOnTheAccountMappingController(String accountId, Object body){
		return Task.where(
		"{0} Update/Patch Account mapping by Account ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").pathParam("accountId", accountId).body(body).patch(UPDATE_ACCOUNT_MAPPING_USING_PATCH_ON_THE_ACCOUNT_MAPPING_CONTROLLER);
			}
		);
	}

	public static Performable createAccountMappingUsingPostOnTheAccountMappingController(String accountId, Object body){
		return Task.where(
		"{0} Create Account Mapping by Account ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("accountId", accountId).body(body).post(CREATE_ACCOUNT_MAPPING_USING_POST_ON_THE_ACCOUNT_MAPPING_CONTROLLER);
			}
		);
	}

	public static Performable getAccountMappingUsingGetOnTheAccountMappingController(String accountId){
		return Task.where(
		"{0} Get Account Mapping by Account ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("accountId", accountId).get(GET_ACCOUNT_MAPPING_USING_GET_ON_THE_ACCOUNT_MAPPING_CONTROLLER);
			}
		);
	}



}
