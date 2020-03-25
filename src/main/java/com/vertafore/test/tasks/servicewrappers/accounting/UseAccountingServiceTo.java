package com.vertafore.test.tasks.servicewrappers.accounting;

import net.serenitybdd.screenplay.Performable;
import com.vertafore.test.abilities.CallTitanApi;
import net.serenitybdd.screenplay.Task;

/**
* This class was automatically generated on 2020/03/25 14:48:06

*/
public class UseAccountingServiceTo {

	private static final String THIS_SERVICE = "accounting";

	public static Performable getAccountByIdUsingGetOnTheAccountController(String id){
		return Task.where(
		"{0} Get Account by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get("accounts/{id}");
			}
		);
	}

	public static Performable getAllJournalsUsingGetOnTheJournalController(String pageSize, String page){
		return Task.where(
		"{0} Get all Journals", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get("journals");
			}
		);
	}

	public static Performable getActiveJournalUsingGetOnTheJournalController(){
		return Task.where(
		"{0} Get active Journal", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").get("journals/active");
			}
		);
	}

	public static Performable getJournalEntryUsingGetOnTheJournalEntryController(String id){
		return Task.where(
		"{0} Get Journal Entry by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get("entries/{id}");
			}
		);
	}

	public static Performable createAccountGroupUsingPostOnTheAccountGroupController(String journalId, Object body){
		return Task.where(
		"{0} Create Account Group by Journal ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("journalId", journalId).body(body).post("journals/{journalId}/groups");
			}
		);
	}

	public static Performable getJournalEntriesCreatedByUserUsingGetOnTheJournalEntryController(String journalId, String createdBy, String pageSize, String page, String filter){
		return Task.where(
		"{0} Filter Journal Entries by User who Created It", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("journalId", journalId).queryParam("createdBy", createdBy).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("journals/{journalId}/entries?filter=byUserId");
			}
		);
	}

	public static Performable getTransactionsByAccountGroupUsingGetOnTheTransactionController(String accountGroupId, String pageSize, String page, String filter){
		return Task.where(
		"{0} Filter Transactions by Account Group ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("accountGroupId", accountGroupId).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("transactions?filter=byAccountGroupId");
			}
		);
	}

	public static Performable getAllByJournalIdUsingGetOnTheAccountGroupController(String journalId, String pageSize, String page){
		return Task.where(
		"{0} Get all Account Groups by Journal ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("journalId", journalId).queryParam("pageSize", pageSize).queryParam("page", page).get("journals/{journalId}/groups");
			}
		);
	}

	public static Performable deleteJournalUsingDeleteOnTheJournalController(String id){
		return Task.where(
		"{0} Delete Journal by ID.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete("journals/{id}");
			}
		);
	}

	public static Performable getTransactionsByMultiFilterUsingGetOnTheTransactionController(String accountGroupId, String accountId, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get all transactions by multi-filter.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("accountGroupId", accountGroupId).queryParam("accountId", accountId).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("transactions?filter=full");
			}
		);
	}

	public static Performable updateJournalEntryUsingPatchOnTheJournalEntryController(String id, Object body){
		return Task.where(
		"{0} Patch/Update a Journal Entry by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").pathParam("id", id).body(body).patch("entries/{id}");
			}
		);
	}

	public static Performable deleteAccountGroupUsingDeleteOnTheAccountGroupController(String id){
		return Task.where(
		"{0} Delete Account Group by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete("groups/{id}");
			}
		);
	}

	public static Performable getAccountsUsingGetOnTheAccountController(String journalId, String pageSize, String page){
		return Task.where(
		"{0} Get Accounts by Journal ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("journalId", journalId).queryParam("pageSize", pageSize).queryParam("page", page).get("journals/{journalId}/accounts");
			}
		);
	}

	public static Performable createJournalEntryUsingPostOnTheJournalEntryController(String journalId, Object body){
		return Task.where(
		"{0} Create Journal Entry by Journal ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("journalId", journalId).body(body).post("journals/{journalId}/entries");
			}
		);
	}

	public static Performable getJournalUsingGetOnTheJournalController(String id){
		return Task.where(
		"{0} Get Journal by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get("journals/{id}");
			}
		);
	}

	public static Performable getTransactionByIdUsingGetOnTheTransactionController(String id){
		return Task.where(
		"{0} Get Transaction by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get("transactions/{id}");
			}
		);
	}

	public static Performable deleteAccountUsingDeleteOnTheAccountController(String id){
		return Task.where(
		"{0} Delete Account by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete("accounts/{id}");
			}
		);
	}

	public static Performable setScheduleSettingsUsingPutOnTheSettingsController(Object body){
		return Task.where(
		"{0} Set schedule settings", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).put("setting-groups/schedules");
			}
		);
	}

	public static Performable getAccountsByCodesUsingGetOnTheAccountController(String journalId, String codes, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get Accounts by their codes within a Journal ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("journalId", journalId).queryParam("codes", codes).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("journals/{journalId}/accounts?filter=byCodes");
			}
		);
	}

	public static Performable createJournalUsingPostOnTheJournalController(Object body){
		return Task.where(
		"{0} Create Journal", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("journals");
			}
		);
	}

	public static Performable updateTransactionUsingPatchOnTheTransactionController(String id, Object body){
		return Task.where(
		"{0} Patch/Update a Transaction by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").pathParam("id", id).body(body).patch("transactions/{id}");
			}
		);
	}

	public static Performable findJournalByTypeUsingGetOnTheJournalController(String journalType, String pageSize, String page, String filter){
		return Task.where(
		"{0} Filter Journals by Type", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("journalType", journalType).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("journals?filter=byJournalType");
			}
		);
	}

	public static Performable updateJournalUsingPutOnTheJournalController(String id, Object body){
		return Task.where(
		"{0} Replace Journal by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put("journals/{id}");
			}
		);
	}

	public static Performable getBalancesUsingGetOnTheAccountBalanceController(String journalId){
		return Task.where(
		"{0} Get balances", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("journalId", journalId).get("journals/{journalId}/balances");
			}
		);
	}

	public static Performable GetAllJournalEntriesUsingGetOnTheJournalEntryController(String journalId, String pageSize, String page){
		return Task.where(
		"{0} Get All Journal Entries by Journal ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("journalId", journalId).queryParam("pageSize", pageSize).queryParam("page", page).get("journals/{journalId}/entries");
			}
		);
	}

	public static Performable createAccountUsingPostOnTheAccountController(String journalId, Object body){
		return Task.where(
		"{0} Create Account by Journal ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("journalId", journalId).body(body).post("journals/{journalId}/accounts");
			}
		);
	}

	public static Performable getJournalEntriesByMultiFilterUsingGetOnTheJournalEntryController(String journalId, String createdBy, String startDate, String endDate, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get all journal entries by multi-filter.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("journalId", journalId).queryParam("createdBy", createdBy).queryParam("startDate", startDate).queryParam("endDate", endDate).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("journals/{journalId}/entries?filter=full");
			}
		);
	}

	public static Performable updateJournalUsingPatchOnTheJournalController(String id, Object body){
		return Task.where(
		"{0} Update/Patch Journal by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").pathParam("id", id).body(body).patch("journals/{id}");
			}
		);
	}

	public static Performable updateAccountGroupUsingPutOnTheAccountGroupController(String id, Object body){
		return Task.where(
		"{0} Update Account Group by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put("groups/{id}");
			}
		);
	}

	public static Performable getTransactionsByAccountUsingGetOnTheTransactionController(String accountId, String pageSize, String page, String filter){
		return Task.where(
		"{0} Filter Transactions by Account ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("accountId", accountId).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("transactions?filter=byAccountId");
			}
		);
	}

	public static Performable getByIdUsingGetOnTheAccountGroupController(String id){
		return Task.where(
		"{0} Get Account Group by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get("groups/{id}");
			}
		);
	}

	public static Performable getJournalEntriesByDateRangeUsingGetOnTheJournalEntryController(String journalId, String startDate, String endDate, String pageSize, String page, String filter){
		return Task.where(
		"{0} Filter Journal Entries by Date Range", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("journalId", journalId).queryParam("startDate", startDate).queryParam("endDate", endDate).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("journals/{journalId}/entries?filter=byDateRange");
			}
		);
	}

	public static Performable getScheduleSettingsUsingGetOnTheSettingsController(){
		return Task.where(
		"{0} Get schedule settings", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").get("setting-groups/schedules");
			}
		);
	}

	public static Performable updateAccountUsingPutOnTheAccountController(String id, Object body){
		return Task.where(
		"{0} Update Account by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put("accounts/{id}");
			}
		);
	}



}
