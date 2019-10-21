package com.vertafore.test.common.servicewrappers.accounting;

import com.vertafore.test.common.models.services.accounting.account.AccountV1;
import com.vertafore.test.common.models.services.accounting.accountBalance.AccountBalanceV1;
import com.vertafore.test.common.models.services.accounting.accountGroup.AccountGroupV1;
import com.vertafore.test.common.models.services.accounting.jounralEntry.JournalEntryV1;
import com.vertafore.test.common.models.services.accounting.journal.JournalV1;
import com.vertafore.test.common.models.services.accounting.settings.ScheduleSettingsV1;
import com.vertafore.test.common.models.services.accounting.transaction.TransactionV1;
import com.vertafore.test.common.util.ServiceUtils;
import io.restassured.response.Response;
import java.io.IOException;

public class AccountingService {

  // journal controller constants
  public final String POST_JOURNAL = "/journals";
  public final String GET_JOURNAL_BY_ID = "/journals/{id}";
  public final String GET_ACTIVE_JOURNAL = "/journals/active";
  public final String PUT_JOURNAL_BY_ID = "/journals/{id}";
  public final String DELETE_JOURNAL_BY_ID = "/journals/{id}";
  public final String PATCH_JOURNAL_BY_ID = "/journals/{id}";

  // account group controller constants
  public final String GET_ACCOUNT_GROUP_BY_ID = "/groups/{id}";
  public final String PUT_ACCOUNT_GROUP_BY_ID = "/groups/{id}";
  public final String DELETE_ACCOUNT_GROUP_BY_ID = "/groups/{id}";
  public final String POST_ACCOUNT_GROUP_BY_JOURNAL_ID = "/journals/{journalId}/groups";

  // account controller constants
  public final String GET_ACCOUNT_BY_ID = "/accounts/{id}";
  public final String PUT_ACCOUNT_BY_ID = "/accounts/{id}";
  public final String DELETE_ACCOUNT_BY_ID = "/accounts/{id}";
  public final String POST_ACCOUNT_BY_JOURNAL_ID = "/journals/{journalId}/accounts";

  // account balances controller constants
  public final String GET_BALANCES_BY_JOURNAL_ID = "/journals/{journalId}/balances";

  // settings controller constants
  public final String GET_SCHEDULE_SETTINGS = "/setting-groups/schedules";
  public final String PUT_SCHEDULE_SETTINGS = "/setting-groups/schedules";

  // journal entry controller constants
  public final String GET_JOURNAL_ENTRY_BY_ID = "/entries/{id}";
  public final String PATCH_JOURNAL_ENTRY_BY_ID = "/entries/{id}";
  public final String POST_JOURNAL_ENTRY_BY_JOURNAL_ID = "/journals/{journalId}/entries";

  // transaction controller constants
  public final String GET_TRANSACTION_BY_ID = "/transactions/{id}";
  public final String PATCH_TRANSACTION_BY_ID = "/transactions/{id}";

  private ServiceUtils serviceUtils;

  public AccountingService() throws IOException {
    serviceUtils = new ServiceUtils();
  }

  // Journal API calls
  public JournalV1 postJournal(JournalV1 requestBody) {
    Response response = serviceUtils.sendPostRequest(POST_JOURNAL, requestBody);
    return response.getBody().jsonPath().getObject("content", JournalV1.class);
  }

  public JournalV1 getActiveJournal() {
    Response response = serviceUtils.sendGetRequest(GET_ACTIVE_JOURNAL);
    return response.getBody().jsonPath().getObject("content", JournalV1.class);
  }

  public Response deleteJournalById(String id) {
    String hydratedURL = hydrateURL(DELETE_JOURNAL_BY_ID, "{id}", id);
    return serviceUtils.sendDeleteRequest(hydratedURL);
  }

  public JournalV1 getJournalById(String id){
    String hydratedURL = hydrateURL(GET_JOURNAL_BY_ID, "{id}", id);
    Response response = serviceUtils.sendGetRequest(hydratedURL);
    return response.getBody().jsonPath().getObject("content", JournalV1.class);
  }

  public JournalV1 putJournalById(String id, JournalV1 requestBody){
    String hydratedURL = hydrateURL(PUT_JOURNAL_BY_ID, "{id}", id);
    Response response = serviceUtils.sendPutRequest(hydratedURL, requestBody);
    return response.getBody().jsonPath().getObject("content", JournalV1.class);
  }

  // finish this later
//  public JournalV1 patchJournalById(String id){}

  // Account Group API calls
  public AccountGroupV1 postAccountGroupByJournalId(String journalId, AccountGroupV1 requestBody) {
    String hydratedURL = hydrateURL(POST_ACCOUNT_GROUP_BY_JOURNAL_ID, "{journalId}", journalId);
    Response response = serviceUtils.sendPostRequest(hydratedURL, requestBody);
    return response.getBody().jsonPath().getObject("content", AccountGroupV1.class);
  }

  public AccountGroupV1 getAccountGroupById(String id) {
    String hydratedURL = hydrateURL(GET_ACCOUNT_GROUP_BY_ID, "{id}", id);
    Response response = serviceUtils.sendGetRequest(hydratedURL);
    return response.getBody().jsonPath().getObject("content", AccountGroupV1.class);
  }

  public AccountGroupV1 putAccountGroupById(String id, AccountGroupV1 requestBody) {
    String hydratedURL = hydrateURL(PUT_ACCOUNT_GROUP_BY_ID, "{id}", id);
    Response response = serviceUtils.sendPutRequest(hydratedURL, requestBody);
    return response.getBody().jsonPath().getObject("content", AccountGroupV1.class);
  }

  public Response deleteAccountGroupById(String id) {
    String hydratedURL = hydrateURL(DELETE_ACCOUNT_GROUP_BY_ID, "{id}", id);
    return serviceUtils.sendDeleteRequest(hydratedURL);
  }

  // account API calls
  public AccountV1 getAccountById(String id){
    String hydratedURL = hydrateURL(GET_ACCOUNT_BY_ID, "{id}", id);
    Response response = serviceUtils.sendGetRequest(hydratedURL);
    return response.getBody().jsonPath().getObject("content", AccountV1.class);
  }

  public AccountV1 putAccountById(String id, AccountV1 requestBody){
    String hydratedURL = hydrateURL(PUT_ACCOUNT_BY_ID, "{id}", id);
    Response response = serviceUtils.sendPutRequest(hydratedURL, requestBody);
    return response.getBody().jsonPath().getObject("content", AccountV1.class);
  }

  public Response deleteAccountById(String id){
    String hydratedURL = hydrateURL(DELETE_ACCOUNT_BY_ID, "{id}", id);
    return serviceUtils.sendDeleteRequest(hydratedURL);
  }

  public AccountV1 postAccountByJournalId(String journalId, AccountV1 requestBody){
    String hydratedURL = hydrateURL(POST_ACCOUNT_BY_JOURNAL_ID, "{journalId}", journalId);
    Response response = serviceUtils.sendPostRequest(hydratedURL, requestBody);
    return response.getBody().jsonPath().getObject("content", AccountV1.class);
  }

  // schedule settings API calls
  public ScheduleSettingsV1 getScheduleSettings(){
    Response response = serviceUtils.sendGetRequest(GET_SCHEDULE_SETTINGS);
    return response.getBody().jsonPath().getObject("content", ScheduleSettingsV1.class);
  }

  public ScheduleSettingsV1 putScheduleSettings(ScheduleSettingsV1 requestBody){
    Response response = serviceUtils.sendPutRequest(PUT_SCHEDULE_SETTINGS, requestBody);
    return response.getBody().jsonPath().getObject("content", ScheduleSettingsV1.class);
  }

  // journal entry API calls
  public JournalEntryV1 getJournalEntryById(String id){
    String hydratedURL = hydrateURL(GET_JOURNAL_ENTRY_BY_ID, "{id}", id);
    Response response = serviceUtils.sendGetRequest(hydratedURL);
    return response.getBody().jsonPath().getObject("content", JournalEntryV1.class);
  }

  //make patch journal entry later

  public JournalEntryV1 postJournalEntryByJournalId(String journalId, JournalEntryV1 requestBody){
    String hydratedURL = hydrateURL(POST_JOURNAL_ENTRY_BY_JOURNAL_ID, "{journalId}", journalId);
    Response response = serviceUtils.sendPostRequest(hydratedURL, requestBody);
    return response.getBody().jsonPath().getObject("content", JournalEntryV1.class);
  }

  // Transaction API calls
  public TransactionV1 getTransactionById(String id){
    String hydratedURL = hydrateURL(GET_TRANSACTION_BY_ID, "{id}", id);
    Response response = serviceUtils.sendGetRequest(hydratedURL);
    return response.getBody().jsonPath().getObject("content", TransactionV1.class);
  }

  // make patch transaction later

  // balances API calls
  public AccountBalanceV1 getAccountBalancesByJournalId(String journalId){
    String hydratedURL = hydrateURL(GET_BALANCES_BY_JOURNAL_ID, "{journalId}", journalId);
    Response response = serviceUtils.sendGetRequest(hydratedURL);
    return response.getBody().jsonPath().getObject("content", AccountBalanceV1.class);
  }

  // helper methods
  private String hydrateURL(String url, String target, String value) {
    return url.replace(target, value);
  }
}
