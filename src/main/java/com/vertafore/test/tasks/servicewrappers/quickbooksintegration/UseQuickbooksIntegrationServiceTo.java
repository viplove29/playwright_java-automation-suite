package com.vertafore.test.tasks.servicewrappers.quickbooksintegration;

import net.serenitybdd.screenplay.Performable;
import com.vertafore.test.abilities.CallTitanApi;
import net.serenitybdd.screenplay.Task;

/**
* This class was automatically generated on 2020/03/25 14:48:18

*/
public class UseQuickbooksIntegrationServiceTo {

	private static final String THIS_SERVICE = "quickbooks-integration";

	public static Performable createAccountMappingUsingPostOnTheAccountMappingController(String accountId, Object body){
		return Task.where(
		"{0} Create Account Mapping by Account ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("accountId", accountId).body(body).post("accounts/{accountId}/mapping");
			}
		);
	}

	public static Performable updateAccountMappingUsingPatchOnTheAccountMappingController(String accountId, Object body){
		return Task.where(
		"{0} Update/Patch Account mapping by Account ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").pathParam("accountId", accountId).body(body).patch("accounts/{accountId}/mapping");
			}
		);
	}

	public static Performable connectToQuickBooksUsingGetOnTheAuthorizationController(){
		return Task.where(
		"{0} Get QuickBooks URL", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").get("authorization/connect-url");
			}
		);
	}

	public static Performable deleteAuthorizationCredentialUsingDeleteOnTheAuthorizationController(){
		return Task.where(
		"{0} Delete QuickBooks Credentials", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").delete("authorization");
			}
		);
	}

	public static Performable getAccountMappingUsingGetOnTheAccountMappingController(String accountId){
		return Task.where(
		"{0} Get Account Mapping by Account ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("accountId", accountId).get("accounts/{accountId}/mapping");
			}
		);
	}

	public static Performable retrieveQuickBooksIntegrationStatusUsingGetOnTheStatusController(){
		return Task.where(
		"{0} Get QuickBooks Authorization Status.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").get("authorization/status");
			}
		);
	}

	public static Performable putAuthorizationCredentialUsingPutOnTheAuthorizationController(Object body){
		return Task.where(
		"{0} Store QuickBooks Credentials", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).put("authorization");
			}
		);
	}

	public static Performable getQuickBooksAccountsUsingGetOnTheQuickbooksAccountController(String pageSize, String page){
		return Task.where(
		"{0} Get all QuickBooks Accounts", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get("accounts");
			}
		);
	}



}
