package com.vertafore.test.tasks.servicewrappers.accounting;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import com.vertafore.test.abilities.CallTitanApi;

/**
* This class was automatically generated on 2020/03/25 13:15:31

*/
public class UseAccountingServiceTo {

	private static final String THIS_SERVICE = "accounting";
	private static final String GET_TRANSACTIONS_BY_MULTI_FILTER_USING_GET_ON_THE_TRANSACTION_CONTROLLER = "transactions?filter=full";
	private static final String GET_ACCOUNT_BY_ID_USING_GET_ON_THE_ACCOUNT_CONTROLLER = "accounts/{id}";
	private static final String DELETE_ACCOUNT_USING_DELETE_ON_THE_ACCOUNT_CONTROLLER = "accounts/{id}";
	private static final String UPDATE_ACCOUNT_USING_PUT_ON_THE_ACCOUNT_CONTROLLER = "accounts/{id}";
	private static final String CREATE_ACCOUNT_GROUP_USING_POST_ON_THE_ACCOUNT_GROUP_CONTROLLER = "journals/{journalId}/groups";
	private static final String GET_SCHEDULE_SETTINGS_USING_GET_ON_THE_SETTINGS_CONTROLLER = "setting-groups/schedules";
	private static final String SET_SCHEDULE_SETTINGS_USING_PUT_ON_THE_SETTINGS_CONTROLLER = "setting-groups/schedules";
	private static final String UPDATE_JOURNAL_ENTRY_USING_PATCH_ON_THE_JOURNAL_ENTRY_CONTROLLER = "entries/{id}";
	private static final String GET_JOURNAL_ENTRY_USING_GET_ON_THE_JOURNAL_ENTRY_CONTROLLER = "entries/{id}";
	private static final String GET_ACTIVE_JOURNAL_USING_GET_ON_THE_JOURNAL_CONTROLLER = "journals/active";
	private static final String CREATE_ACCOUNT_USING_POST_ON_THE_ACCOUNT_CONTROLLER = "journals/{journalId}/accounts";
	private static final String UPDATE_JOURNAL_USING_PATCH_ON_THE_JOURNAL_CONTROLLER = "journals/{id}";
	private static final String GET_JOURNAL_USING_GET_ON_THE_JOURNAL_CONTROLLER = "journals/{id}";
	private static final String DELETE_JOURNAL_USING_DELETE_ON_THE_JOURNAL_CONTROLLER = "journals/{id}";
	private static final String UPDATE_JOURNAL_USING_PUT_ON_THE_JOURNAL_CONTROLLER = "journals/{id}";
	private static final String GET_TRANSACTIONS_BY_ACCOUNT_USING_GET_ON_THE_TRANSACTION_CONTROLLER = "transactions?filter=byAccountId";
	private static final String CREATE_JOURNAL_USING_POST_ON_THE_JOURNAL_CONTROLLER = "journals";
	private static final String FIND_JOURNAL_BY_TYPE_USING_GET_ON_THE_JOURNAL_CONTROLLER = "journals?filter=byJournalType";
	private static final String UPDATE_TRANSACTION_USING_PATCH_ON_THE_TRANSACTION_CONTROLLER = "transactions/{id}";
	private static final String GET_TRANSACTION_BY_ID_USING_GET_ON_THE_TRANSACTION_CONTROLLER = "transactions/{id}";
	private static final String GET_ACCOUNTS_BY_CODES_USING_GET_ON_THE_ACCOUNT_CONTROLLER = "journals/{journalId}/accounts?filter=byCodes";
	private static final String GET_BY_ID_USING_GET_ON_THE_ACCOUNT_GROUP_CONTROLLER = "groups/{id}";
	private static final String DELETE_ACCOUNT_GROUP_USING_DELETE_ON_THE_ACCOUNT_GROUP_CONTROLLER = "groups/{id}";
	private static final String UPDATE_ACCOUNT_GROUP_USING_PUT_ON_THE_ACCOUNT_GROUP_CONTROLLER = "groups/{id}";
	private static final String GET_ACCOUNTS_USING_GET_ON_THE_ACCOUNT_CONTROLLER = "journals/{journalId}/accounts";
	private static final String GET_JOURNAL_ENTRIES_BY_MULTI_FILTER_USING_GET_ON_THE_JOURNAL_ENTRY_CONTROLLER = "journals/{journalId}/entries?filter=full";
	private static final String CREATE_JOURNAL_ENTRY_USING_POST_ON_THE_JOURNAL_ENTRY_CONTROLLER = "journals/{journalId}/entries";
	private static final String GET_TRANSACTIONS_BY_ACCOUNT_GROUP_USING_GET_ON_THE_TRANSACTION_CONTROLLER = "transactions?filter=byAccountGroupId";
	private static final String GET_ALL_BY_JOURNAL_ID_USING_GET_ON_THE_ACCOUNT_GROUP_CONTROLLER = "journals/{journalId}/groups";
	private static final String GET_ALL_JOURNALS_USING_GET_ON_THE_JOURNAL_CONTROLLER = "journals";
	private static final String GET_JOURNAL_ENTRIES_CREATED_BY_USER_USING_GET_ON_THE_JOURNAL_ENTRY_CONTROLLER = "journals/{journalId}/entries?filter=byUserId";
	private static final String GET_ALL_JOURNAL_ENTRIES_USING_GET_ON_THE_JOURNAL_ENTRY_CONTROLLER = "journals/{journalId}/entries";
	private static final String GET_BALANCES_USING_GET_ON_THE_ACCOUNT_BALANCE_CONTROLLER = "journals/{journalId}/balances";
	private static final String GET_JOURNAL_ENTRIES_BY_DATE_RANGE_USING_GET_ON_THE_JOURNAL_ENTRY_CONTROLLER = "journals/{journalId}/entries?filter=byDateRange";

	public static Performable getTransactionsByMultiFilterUsingGetOnTheTransactionController(String accountGroupId, String accountId, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get all transactions by multi-filter.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("accountGroupId", accountGroupId).queryParam("accountId", accountId).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_TRANSACTIONS_BY_MULTI_FILTER_USING_GET_ON_THE_TRANSACTION_CONTROLLER);
			}
		);
	}

	public static Performable getAccountByIdUsingGetOnTheAccountController(String id){
		return Task.where(
		"{0} Get Account by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_ACCOUNT_BY_ID_USING_GET_ON_THE_ACCOUNT_CONTROLLER);
			}
		);
	}

	public static Performable deleteAccountUsingDeleteOnTheAccountController(String id){
		return Task.where(
		"{0} Delete Account by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_ACCOUNT_USING_DELETE_ON_THE_ACCOUNT_CONTROLLER);
			}
		);
	}

	public static Performable updateAccountUsingPutOnTheAccountController(String id, Object body){
		return Task.where(
		"{0} Update Account by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put(UPDATE_ACCOUNT_USING_PUT_ON_THE_ACCOUNT_CONTROLLER);
			}
		);
	}

	public static Performable createAccountGroupUsingPostOnTheAccountGroupController(String journalId, Object body){
		return Task.where(
		"{0} Create Account Group by Journal ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("journalId", journalId).body(body).post(CREATE_ACCOUNT_GROUP_USING_POST_ON_THE_ACCOUNT_GROUP_CONTROLLER);
			}
		);
	}

	public static Performable getScheduleSettingsUsingGetOnTheSettingsController(){
		return Task.where(
		"{0} Get schedule settings", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").get(GET_SCHEDULE_SETTINGS_USING_GET_ON_THE_SETTINGS_CONTROLLER);
			}
		);
	}

	public static Performable setScheduleSettingsUsingPutOnTheSettingsController(Object body){
		return Task.where(
		"{0} Set schedule settings", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).put(SET_SCHEDULE_SETTINGS_USING_PUT_ON_THE_SETTINGS_CONTROLLER);
			}
		);
	}

	public static Performable updateJournalEntryUsingPatchOnTheJournalEntryController(String id, Object body){
		return Task.where(
		"{0} Patch/Update a Journal Entry by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").pathParam("id", id).body(body).patch(UPDATE_JOURNAL_ENTRY_USING_PATCH_ON_THE_JOURNAL_ENTRY_CONTROLLER);
			}
		);
	}

	public static Performable getJournalEntryUsingGetOnTheJournalEntryController(String id){
		return Task.where(
		"{0} Get Journal Entry by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_JOURNAL_ENTRY_USING_GET_ON_THE_JOURNAL_ENTRY_CONTROLLER);
			}
		);
	}

	public static Performable getActiveJournalUsingGetOnTheJournalController(){
		return Task.where(
		"{0} Get active Journal", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").get(GET_ACTIVE_JOURNAL_USING_GET_ON_THE_JOURNAL_CONTROLLER);
			}
		);
	}

	public static Performable createAccountUsingPostOnTheAccountController(String journalId, Object body){
		return Task.where(
		"{0} Create Account by Journal ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("journalId", journalId).body(body).post(CREATE_ACCOUNT_USING_POST_ON_THE_ACCOUNT_CONTROLLER);
			}
		);
	}

	public static Performable updateJournalUsingPatchOnTheJournalController(String id, Object body){
		return Task.where(
		"{0} Update/Patch Journal by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").pathParam("id", id).body(body).patch(UPDATE_JOURNAL_USING_PATCH_ON_THE_JOURNAL_CONTROLLER);
			}
		);
	}

	public static Performable getJournalUsingGetOnTheJournalController(String id){
		return Task.where(
		"{0} Get Journal by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_JOURNAL_USING_GET_ON_THE_JOURNAL_CONTROLLER);
			}
		);
	}

	public static Performable deleteJournalUsingDeleteOnTheJournalController(String id){
		return Task.where(
		"{0} Delete Journal by ID.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_JOURNAL_USING_DELETE_ON_THE_JOURNAL_CONTROLLER);
			}
		);
	}

	public static Performable updateJournalUsingPutOnTheJournalController(String id, Object body){
		return Task.where(
		"{0} Replace Journal by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put(UPDATE_JOURNAL_USING_PUT_ON_THE_JOURNAL_CONTROLLER);
			}
		);
	}

	public static Performable getTransactionsByAccountUsingGetOnTheTransactionController(String accountId, String pageSize, String page, String filter){
		return Task.where(
		"{0} Filter Transactions by Account ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("accountId", accountId).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_TRANSACTIONS_BY_ACCOUNT_USING_GET_ON_THE_TRANSACTION_CONTROLLER);
			}
		);
	}

	public static Performable createJournalUsingPostOnTheJournalController(Object body){
		return Task.where(
		"{0} Create Journal", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_JOURNAL_USING_POST_ON_THE_JOURNAL_CONTROLLER);
			}
		);
	}

	public static Performable findJournalByTypeUsingGetOnTheJournalController(String journalType, String pageSize, String page, String filter){
		return Task.where(
		"{0} Filter Journals by Type", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("journalType", journalType).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(FIND_JOURNAL_BY_TYPE_USING_GET_ON_THE_JOURNAL_CONTROLLER);
			}
		);
	}

	public static Performable updateTransactionUsingPatchOnTheTransactionController(String id, Object body){
		return Task.where(
		"{0} Patch/Update a Transaction by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").pathParam("id", id).body(body).patch(UPDATE_TRANSACTION_USING_PATCH_ON_THE_TRANSACTION_CONTROLLER);
			}
		);
	}

	public static Performable getTransactionByIdUsingGetOnTheTransactionController(String id){
		return Task.where(
		"{0} Get Transaction by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_TRANSACTION_BY_ID_USING_GET_ON_THE_TRANSACTION_CONTROLLER);
			}
		);
	}

	public static Performable getAccountsByCodesUsingGetOnTheAccountController(String journalId, String codes, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get Accounts by their codes within a Journal ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("journalId", journalId).queryParam("codes", codes).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_ACCOUNTS_BY_CODES_USING_GET_ON_THE_ACCOUNT_CONTROLLER);
			}
		);
	}

	public static Performable getByIdUsingGetOnTheAccountGroupController(String id){
		return Task.where(
		"{0} Get Account Group by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_BY_ID_USING_GET_ON_THE_ACCOUNT_GROUP_CONTROLLER);
			}
		);
	}

	public static Performable deleteAccountGroupUsingDeleteOnTheAccountGroupController(String id){
		return Task.where(
		"{0} Delete Account Group by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_ACCOUNT_GROUP_USING_DELETE_ON_THE_ACCOUNT_GROUP_CONTROLLER);
			}
		);
	}

	public static Performable updateAccountGroupUsingPutOnTheAccountGroupController(String id, Object body){
		return Task.where(
		"{0} Update Account Group by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put(UPDATE_ACCOUNT_GROUP_USING_PUT_ON_THE_ACCOUNT_GROUP_CONTROLLER);
			}
		);
	}

	public static Performable getAccountsUsingGetOnTheAccountController(String journalId, String pageSize, String page){
		return Task.where(
		"{0} Get Accounts by Journal ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("journalId", journalId).queryParam("pageSize", pageSize).queryParam("page", page).get(GET_ACCOUNTS_USING_GET_ON_THE_ACCOUNT_CONTROLLER);
			}
		);
	}

	public static Performable getJournalEntriesByMultiFilterUsingGetOnTheJournalEntryController(String journalId, String createdBy, String startDate, String endDate, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get all journal entries by multi-filter.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("journalId", journalId).queryParam("createdBy", createdBy).queryParam("startDate", startDate).queryParam("endDate", endDate).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_JOURNAL_ENTRIES_BY_MULTI_FILTER_USING_GET_ON_THE_JOURNAL_ENTRY_CONTROLLER);
			}
		);
	}

	public static Performable createJournalEntryUsingPostOnTheJournalEntryController(String journalId, Object body){
		return Task.where(
		"{0} Create Journal Entry by Journal ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("journalId", journalId).body(body).post(CREATE_JOURNAL_ENTRY_USING_POST_ON_THE_JOURNAL_ENTRY_CONTROLLER);
			}
		);
	}

	public static Performable getTransactionsByAccountGroupUsingGetOnTheTransactionController(String accountGroupId, String pageSize, String page, String filter){
		return Task.where(
		"{0} Filter Transactions by Account Group ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("accountGroupId", accountGroupId).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_TRANSACTIONS_BY_ACCOUNT_GROUP_USING_GET_ON_THE_TRANSACTION_CONTROLLER);
			}
		);
	}

	public static Performable getAllByJournalIdUsingGetOnTheAccountGroupController(String journalId, String pageSize, String page){
		return Task.where(
		"{0} Get all Account Groups by Journal ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("journalId", journalId).queryParam("pageSize", pageSize).queryParam("page", page).get(GET_ALL_BY_JOURNAL_ID_USING_GET_ON_THE_ACCOUNT_GROUP_CONTROLLER);
			}
		);
	}

	public static Performable getAllJournalsUsingGetOnTheJournalController(String pageSize, String page){
		return Task.where(
		"{0} Get all Journals", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(GET_ALL_JOURNALS_USING_GET_ON_THE_JOURNAL_CONTROLLER);
			}
		);
	}

	public static Performable getJournalEntriesCreatedByUserUsingGetOnTheJournalEntryController(String journalId, String createdBy, String pageSize, String page, String filter){
		return Task.where(
		"{0} Filter Journal Entries by User who Created It", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("journalId", journalId).queryParam("createdBy", createdBy).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_JOURNAL_ENTRIES_CREATED_BY_USER_USING_GET_ON_THE_JOURNAL_ENTRY_CONTROLLER);
			}
		);
	}

	public static Performable GetAllJournalEntriesUsingGetOnTheJournalEntryController(String journalId, String pageSize, String page){
		return Task.where(
		"{0} Get All Journal Entries by Journal ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("journalId", journalId).queryParam("pageSize", pageSize).queryParam("page", page).get(GET_ALL_JOURNAL_ENTRIES_USING_GET_ON_THE_JOURNAL_ENTRY_CONTROLLER);
			}
		);
	}

	public static Performable getBalancesUsingGetOnTheAccountBalanceController(String journalId){
		return Task.where(
		"{0} Get balances", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("journalId", journalId).get(GET_BALANCES_USING_GET_ON_THE_ACCOUNT_BALANCE_CONTROLLER);
			}
		);
	}

	public static Performable getJournalEntriesByDateRangeUsingGetOnTheJournalEntryController(String journalId, String startDate, String endDate, String pageSize, String page, String filter){
		return Task.where(
		"{0} Filter Journal Entries by Date Range", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("journalId", journalId).queryParam("startDate", startDate).queryParam("endDate", endDate).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_JOURNAL_ENTRIES_BY_DATE_RANGE_USING_GET_ON_THE_JOURNAL_ENTRY_CONTROLLER);
			}
		);
	}



}
