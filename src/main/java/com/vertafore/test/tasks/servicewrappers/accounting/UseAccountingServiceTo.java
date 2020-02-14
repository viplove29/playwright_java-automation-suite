package com.vertafore.test.tasks.servicewrappers.accounting;

import static com.vertafore.test.abilities.CallTitanApi.as;
import static net.serenitybdd.rest.SerenityRest.rest;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class UseAccountingServiceTo {

	private static final String GET_ALL_TRANSACTIONS_BY_MULTIFILTER = "/transactions?filter=full{&accountGroupId,accountId,pageSize,page}";
	private static final String GET_ACCOUNT_BY_ID = "/accounts/{id}";
	private static final String DELETE_ACCOUNT_BY_ID = "/accounts/{id}";
	private static final String UPDATE_ACCOUNT_BY_ID = "/accounts/{id}";
	private static final String CREATE_ACCOUNT_GROUP_BY_JOURNAL_ID = "/journals/{journalId}/groups";
	private static final String GET_SCHEDULE_SETTINGS = "/setting-groups/schedules";
	private static final String SET_SCHEDULE_SETTINGS = "/setting-groups/schedules";
	private static final String PATCHUPDATE_A_JOURNAL_ENTRY_BY_ID = "/entries/{id}";
	private static final String GET_JOURNAL_ENTRY_BY_ID = "/entries/{id}";
	private static final String GET_ACTIVE_JOURNAL = "/journals/active";
	private static final String CREATE_ACCOUNT_BY_JOURNAL_ID = "/journals/{journalId}/accounts";
	private static final String PATCH_JOURNAL_BY_ID = "/journals/{id}";
	private static final String GET_JOURNAL_BY_ID = "/journals/{id}";
	private static final String DELETE_JOURNAL_BY_ID = "/journals/{id}";
	private static final String REPLACE_JOURNAL_BY_ID = "/journals/{id}";
	private static final String FILTER_TRANSACTIONS_BY_ACCOUNT_ID = "/transactions?filter=byAccountId{&accountId,pageSize,page}";
	private static final String CREATE_JOURNAL = "/journals";
	private static final String FILTER_JOURNALS_BY_TYPE = "/journals?filter=byJournalType{&journalType,pageSize,page}";
	private static final String PATCHUPDATE_A_TRANSACTION_BY_ID = "/transactions/{id}";
	private static final String GET_TRANSACTION_BY_ID = "/transactions/{id}";
	private static final String GET_ACCOUNTS_BY_THEIR_CODES_WITHIN_A_JOURNAL_ID = "/journals/{journalId}/accounts?filter=byCodes{&codes,pageSize,page}";
	private static final String GET_ACCOUNT_GROUP_BY_ID = "/groups/{id}";
	private static final String DELETE_ACCOUNT_GROUP_BY_ID = "/groups/{id}";
	private static final String UPDATE_ACCOUNT_GROUP_BY_ID = "/groups/{id}";
	private static final String GET_ACCOUNTS_BY_JOURNAL_ID = "/journals/{journalId}/accounts{?pageSize,page}";
	private static final String GET_ALL_JOURNAL_ENTRIES_BY_MULTIFILTER = "/journals/{journalId}/entries?filter=full{&createdBy,startDate,endDate,pageSize,page}";
	private static final String CREATE_JOURNAL_ENTRY_BY_JOURNAL_ID = "/journals/{journalId}/entries";
	private static final String FILTER_TRANSACTIONS_BY_ACCOUNT_GROUP_ID = "/transactions?filter=byAccountGroupId{&accountGroupId,pageSize,page}";
	private static final String GET_ALL_ACCOUNT_GROUPS_BY_JOURNAL_ID = "/journals/{journalId}/groups{?pageSize,page}";
	private static final String GET_ALL_JOURNALS = "/journals{?pageSize,page}";
	private static final String FILTER_JOURNAL_ENTRIES_BY_USER_WHO_CREATED_IT = "/journals/{journalId}/entries?filter=byUserId{&createdBy,pageSize,page}";
	private static final String GET_ALL_JOURNAL_ENTRIES_BY_JOURNAL_ID = "/journals/{journalId}/entries{?pageSize,page}";
	private static final String GET_BALANCES = "/journals/{journalId}/balances";
	private static final String FILTER_JOURNAL_ENTRIES_BY_DATE_RANGE = "/journals/{journalId}/entries?filter=byDateRange{&startDate,endDate,pageSize,page}";

	public static Performable getAllTransactionsByMultiFilter(String accountGroupId, String accountId, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get all transactions by multi-filter.", 
			actor -> {
				rest().with().queryParam("accountGroupId", accountGroupId).queryParam("accountId", accountId).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(as(actor).toEndpoint(GET_ALL_TRANSACTIONS_BY_MULTIFILTER));
			}
		);
	}

	public static Performable getAccountById(String id){
		return Task.where(
		"{0} Get Account by ID", 
			actor -> {
				rest().with().pathParam("id", id).get(as(actor).toEndpoint(GET_ACCOUNT_BY_ID));
			}
		);
	}

	public static Performable deleteAccountById(String id){
		return Task.where(
		"{0} Delete Account by ID", 
			actor -> {
				rest().with().pathParam("id", id).delete(as(actor).toEndpoint(DELETE_ACCOUNT_BY_ID));
			}
		);
	}

	public static Performable updateAccountById(String id, Object body){
		return Task.where(
		"{0} Update Account by ID", 
			actor -> {
				rest().with().pathParam("id", id).body(body).put(as(actor).toEndpoint(UPDATE_ACCOUNT_BY_ID));
			}
		);
	}

	public static Performable createAccountGroupByJournalId(String journalId, Object body){
		return Task.where(
		"{0} Create Account Group by Journal ID", 
			actor -> {
				rest().with().pathParam("journalId", journalId).body(body).post(as(actor).toEndpoint(CREATE_ACCOUNT_GROUP_BY_JOURNAL_ID));
			}
		);
	}

	public static Performable getScheduleSettings(){
		return Task.where(
		"{0} Get schedule settings", 
			actor -> {
				rest().with().get(as(actor).toEndpoint(GET_SCHEDULE_SETTINGS));
			}
		);
	}

	public static Performable setScheduleSettings(Object body){
		return Task.where(
		"{0} Set schedule settings", 
			actor -> {
				rest().with().body(body).put(as(actor).toEndpoint(SET_SCHEDULE_SETTINGS));
			}
		);
	}

	public static Performable patchUpdateAJournalEntryById(String id, Object body){
		return Task.where(
		"{0} Patch/Update a Journal Entry by ID", 
			actor -> {
				rest().with().pathParam("id", id).body(body).patch(as(actor).toEndpoint(PATCHUPDATE_A_JOURNAL_ENTRY_BY_ID));
			}
		);
	}

	public static Performable getJournalEntryById(String id){
		return Task.where(
		"{0} Get Journal Entry by ID", 
			actor -> {
				rest().with().pathParam("id", id).get(as(actor).toEndpoint(GET_JOURNAL_ENTRY_BY_ID));
			}
		);
	}

	public static Performable getActiveJournal(){
		return Task.where(
		"{0} Get active Journal", 
			actor -> {
				rest().with().get(as(actor).toEndpoint(GET_ACTIVE_JOURNAL));
			}
		);
	}

	public static Performable createAccountByJournalId(String journalId, Object body){
		return Task.where(
		"{0} Create Account by Journal ID", 
			actor -> {
				rest().with().pathParam("journalId", journalId).body(body).post(as(actor).toEndpoint(CREATE_ACCOUNT_BY_JOURNAL_ID));
			}
		);
	}

	public static Performable updatePatchJournalById(String id, Object body){
		return Task.where(
		"{0} Update/Patch Journal by ID", 
			actor -> {
				rest().with().pathParam("id", id).body(body).patch(as(actor).toEndpoint(PATCH_JOURNAL_BY_ID));
			}
		);
	}

	public static Performable getJournalById(String id){
		return Task.where(
		"{0} Get Journal by ID", 
			actor -> {
				rest().with().pathParam("id", id).get(as(actor).toEndpoint(GET_JOURNAL_BY_ID));
			}
		);
	}

	public static Performable deleteJournalById(String id){
		return Task.where(
		"{0} Delete Journal by ID.", 
			actor -> {
				rest().with().pathParam("id", id).delete(as(actor).toEndpoint(DELETE_JOURNAL_BY_ID));
			}
		);
	}

	public static Performable replaceJournalById(String id, Object body){
		return Task.where(
		"{0} Replace Journal by ID", 
			actor -> {
				rest().with().pathParam("id", id).body(body).put(as(actor).toEndpoint(REPLACE_JOURNAL_BY_ID));
			}
		);
	}

	public static Performable filterTransactionsByAccountId(String accountId, String pageSize, String page, String filter){
		return Task.where(
		"{0} Filter Transactions by Account ID", 
			actor -> {
				rest().with().queryParam("accountId", accountId).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(as(actor).toEndpoint(FILTER_TRANSACTIONS_BY_ACCOUNT_ID));
			}
		);
	}

	public static Performable createJournal(Object body){
		return Task.where(
		"{0} Create Journal", 
			actor -> {
				rest().with().body(body).post(as(actor).toEndpoint(CREATE_JOURNAL));
			}
		);
	}

	public static Performable filterJournalsByType(String journalType, String pageSize, String page, String filter){
		return Task.where(
		"{0} Filter Journals by Type", 
			actor -> {
				rest().with().queryParam("journalType", journalType).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(as(actor).toEndpoint(FILTER_JOURNALS_BY_TYPE));
			}
		);
	}

	public static Performable patchUpdateATransactionById(String id, Object body){
		return Task.where(
		"{0} Patch/Update a Transaction by ID", 
			actor -> {
				rest().with().pathParam("id", id).body(body).patch(as(actor).toEndpoint(PATCHUPDATE_A_TRANSACTION_BY_ID));
			}
		);
	}

	public static Performable getTransactionById(String id){
		return Task.where(
		"{0} Get Transaction by ID", 
			actor -> {
				rest().with().pathParam("id", id).get(as(actor).toEndpoint(GET_TRANSACTION_BY_ID));
			}
		);
	}

	public static Performable getAccountsByTheirCodesWithinAJournalId(String journalId, String codes, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get Accounts by their codes within a Journal ID", 
			actor -> {
				rest().with().pathParam("journalId", journalId).queryParam("codes", codes).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(as(actor).toEndpoint(GET_ACCOUNTS_BY_THEIR_CODES_WITHIN_A_JOURNAL_ID));
			}
		);
	}

	public static Performable getAccountGroupById(String id){
		return Task.where(
		"{0} Get Account Group by ID", 
			actor -> {
				rest().with().pathParam("id", id).get(as(actor).toEndpoint(GET_ACCOUNT_GROUP_BY_ID));
			}
		);
	}

	public static Performable deleteAccountGroupById(String id){
		return Task.where(
		"{0} Delete Account Group by ID", 
			actor -> {
				rest().with().pathParam("id", id).delete(as(actor).toEndpoint(DELETE_ACCOUNT_GROUP_BY_ID));
			}
		);
	}

	public static Performable updateAccountGroupById(String id, Object body){
		return Task.where(
		"{0} Update Account Group by ID", 
			actor -> {
				rest().with().pathParam("id", id).body(body).put(as(actor).toEndpoint(UPDATE_ACCOUNT_GROUP_BY_ID));
			}
		);
	}

	public static Performable getAccountsByJournalId(String journalId, String pageSize, String page){
		return Task.where(
		"{0} Get Accounts by Journal ID", 
			actor -> {
				rest().with().pathParam("journalId", journalId).queryParam("pageSize", pageSize).queryParam("page", page).get(as(actor).toEndpoint(GET_ACCOUNTS_BY_JOURNAL_ID));
			}
		);
	}

	public static Performable getAllJournalEntriesByMultiFilter(String journalId, String createdBy, String startDate, String endDate, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get all journal entries by multi-filter.", 
			actor -> {
				rest().with().pathParam("journalId", journalId).queryParam("createdBy", createdBy).queryParam("startDate", startDate).queryParam("endDate", endDate).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(as(actor).toEndpoint(GET_ALL_JOURNAL_ENTRIES_BY_MULTIFILTER));
			}
		);
	}

	public static Performable createJournalEntryByJournalId(String journalId, Object body){
		return Task.where(
		"{0} Create Journal Entry by Journal ID", 
			actor -> {
				rest().with().pathParam("journalId", journalId).body(body).post(as(actor).toEndpoint(CREATE_JOURNAL_ENTRY_BY_JOURNAL_ID));
			}
		);
	}

	public static Performable filterTransactionsByAccountGroupId(String accountGroupId, String pageSize, String page, String filter){
		return Task.where(
		"{0} Filter Transactions by Account Group ID", 
			actor -> {
				rest().with().queryParam("accountGroupId", accountGroupId).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(as(actor).toEndpoint(FILTER_TRANSACTIONS_BY_ACCOUNT_GROUP_ID));
			}
		);
	}

	public static Performable getAllAccountGroupsByJournalId(String journalId, String pageSize, String page){
		return Task.where(
		"{0} Get all Account Groups by Journal ID", 
			actor -> {
				rest().with().pathParam("journalId", journalId).queryParam("pageSize", pageSize).queryParam("page", page).get(as(actor).toEndpoint(GET_ALL_ACCOUNT_GROUPS_BY_JOURNAL_ID));
			}
		);
	}

	public static Performable getAllJournals(String pageSize, String page){
		return Task.where(
		"{0} Get all Journals", 
			actor -> {
				rest().with().queryParam("pageSize", pageSize).queryParam("page", page).get(as(actor).toEndpoint(GET_ALL_JOURNALS));
			}
		);
	}

	public static Performable filterJournalEntriesByUserWhoCreatedIt(String journalId, String createdBy, String pageSize, String page, String filter){
		return Task.where(
		"{0} Filter Journal Entries by User who Created It", 
			actor -> {
				rest().with().pathParam("journalId", journalId).queryParam("createdBy", createdBy).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(as(actor).toEndpoint(FILTER_JOURNAL_ENTRIES_BY_USER_WHO_CREATED_IT));
			}
		);
	}

	public static Performable getAllJournalEntriesByJournalId(String journalId, String pageSize, String page){
		return Task.where(
		"{0} Get All Journal Entries by Journal ID", 
			actor -> {
				rest().with().pathParam("journalId", journalId).queryParam("pageSize", pageSize).queryParam("page", page).get(as(actor).toEndpoint(GET_ALL_JOURNAL_ENTRIES_BY_JOURNAL_ID));
			}
		);
	}

	public static Performable getBalances(String journalId){
		return Task.where(
		"{0} Get balances", 
			actor -> {
				rest().with().pathParam("journalId", journalId).get(as(actor).toEndpoint(GET_BALANCES));
			}
		);
	}

	public static Performable filterJournalEntriesByDateRange(String journalId, String startDate, String endDate, String pageSize, String page, String filter){
		return Task.where(
		"{0} Filter Journal Entries by Date Range", 
			actor -> {
				rest().with().pathParam("journalId", journalId).queryParam("startDate", startDate).queryParam("endDate", endDate).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(as(actor).toEndpoint(FILTER_JOURNAL_ENTRIES_BY_DATE_RANGE));
			}
		);
	}

}
