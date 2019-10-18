package com.vertafore.test.common.servicewrappers.accounting;

import com.vertafore.test.common.models.services.accounting.accountGroup.AccountGroupV1;
import com.vertafore.test.common.models.services.accounting.journal.JournalV1;
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

  // Account Group API calls
  public AccountGroupV1 postAccountGroupByJournalId(String id, AccountGroupV1 requestBody) {
    String hydratedURL = hydrateURL(POST_ACCOUNT_GROUP_BY_JOURNAL_ID, "{journalId}", id);
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

  // helper methods
  private String hydrateURL(String url, String target, String value) {
    return url.replace(target, value);
  }
}
