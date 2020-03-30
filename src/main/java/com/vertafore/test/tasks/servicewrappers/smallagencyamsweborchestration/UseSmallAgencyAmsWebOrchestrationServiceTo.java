package com.vertafore.test.tasks.servicewrappers.smallagencyamsweborchestration;

import com.vertafore.test.abilities.CallTitanApi;
import java.io.File;
import java.net.URLConnection;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

/** This class was automatically generated on 2020/03/30 11:17:05 */
public class UseSmallAgencyAmsWebOrchestrationServiceTo {

  private static final String THIS_SERVICE = "small-agency-ams-web-orchestration";

  public static Performable updateReadOnUsingPostOnTheEmailmessagecontrollerController(
      Object body) {
    return Task.where(
        "{0} Accept a list of MessageIds to update readOn",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("email-messages");
        });
  }

  public static Performable getRolePermissionsUsingGetOnTheRolesController() {
    return Task.where(
        "{0} Retrieve Agency roles",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .get("agency-roles");
        });
  }

  public static Performable
      createOpportunityAndMoveExistingApplicationIntoItUsingPostOnTheOpportunityController(
          Object body, String existingApplicationId) {
    return Task.where(
        "{0} Create new Opportunity and move existing Application into it",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .pathParam("existingApplicationId", existingApplicationId)
              .post("opportunities/applications/{existingApplicationId}");
        });
  }

  public static Performable getMatchResultsByIdUsingGetOnTheDownloadscontrollerController(
      String matchResultId) {
    return Task.where(
        "{0} Get a Match Result",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("matchResultId", matchResultId)
              .get("matchresults/{matchResultId}");
        });
  }

  public static Performable deleteDocumentFromFolderUsingDeleteOnTheQuotecontrollerController(
      String applicationId, String quoteId, String documentId) {
    return Task.where(
        "{0} Delete Document from Folder",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("applicationId", applicationId)
              .pathParam("quoteId", quoteId)
              .pathParam("documentId", documentId)
              .delete("applications/{applicationId}/quotes/{quoteId}/documents/{documentId}");
        });
  }

  public static Performable
      getInvoicesAccountSummaryUsingGetOnTheInvoiceorchestrationcontrollerController() {
    return Task.where(
        "{0} Get Invoice Account Summary",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .get("invoices/account-summary");
        });
  }

  public static Performable searchForCustomersUsingGetOnTheGlobalsearchcontrollerController(
      String pageSize, String page, String searchTerm, String scope) {
    return Task.where(
        "{0} Search for Customers",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("searchTerm", searchTerm)
              .queryParam("scope", scope)
              .get("global-search/customers?scope=descendants{&pageSize,page,searchTerm}");
        });
  }

  public static Performable getOpportunityByIdUsingGetOnTheOpportunityController(String id) {
    return Task.where(
        "{0} Get Opportunity by id",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("opportunities/{id}");
        });
  }

  public static Performable
      getInvoicesSettingsUsingGetOnTheInvoiceorchestrationcontrollerController() {
    return Task.where(
        "{0} Get Invoice settings",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .get("invoices/settings");
        });
  }

  public static Performable moveAttachmentUsingPostOnTheEmailmessagecontrollerController(
      Object body) {
    return Task.where(
        "{0} Move attachment from email to customer documents",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("email-messages/attachments");
        });
  }

  public static Performable searchFormTemplatesUsingGetOnTheFormcontrollerController(
      String pageSize, String page, String type, String numberOfVersions, String filter) {
    return Task.where(
        "{0} Get latest form template versions",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("type", type)
              .queryParam("numberOfVersions", numberOfVersions)
              .queryParam("filter", filter)
              .get("form-templates?filter=bySearchTerm");
        });
  }

  public static Performable getTaskUsingGetOnTheTaskController(String id) {
    return Task.where(
        "{0} Get task by ID",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("tasks/{id}");
        });
  }

  public static Performable searchEmailMessagesUsingGetOnTheEmailmessagecontrollerController(
      String pageSize,
      String page,
      String isDescending,
      String searchTerm,
      String sortField,
      String status,
      String filterRead,
      String filter) {
    return Task.where(
        "{0} Search Email Messages",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("isDescending", isDescending)
              .queryParam("searchTerm", searchTerm)
              .queryParam("sortField", sortField)
              .queryParam("status", status)
              .queryParam("filterRead", filterRead)
              .queryParam("filter", filter)
              .get("email-messages?filter=byEmailMessages");
        });
  }

  public static Performable setupEmailInformationUsingGetOnTheEmailmessagecontrollerController() {
    return Task.where(
        "{0} Get primary email addresses information and email folder",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .get("email-setup-information");
        });
  }

  public static Performable getAgencyTasksUsingGetOnTheTaskController(
      String pageSize,
      String page,
      String sortField,
      String isDescending,
      String customerName,
      String assigneeUserId,
      String hideCced,
      String statuses,
      String types,
      String dueDate,
      String filter) {
    return Task.where(
        "{0} Get agency tasks",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("sortField", sortField)
              .queryParam("isDescending", isDescending)
              .queryParam("customerName", customerName)
              .queryParam("assigneeUserId", assigneeUserId)
              .queryParam("hideCced", hideCced)
              .queryParam("statuses", statuses)
              .queryParam("types", types)
              .queryParam("dueDate", dueDate)
              .queryParam("filter", filter)
              .get("tasks?filter=byAgencyTasks");
        });
  }

  public static Performable putRolesUsingPutOnTheRolesController(Object body) {
    return Task.where(
        "{0} Updates Agency roles",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .put("agency-roles");
        });
  }

  public static Performable
      setBillingPreferencesUsingPutOnTheBillingpreferencescontrollerController(Object body) {
    return Task.where(
        "{0} Set billing preferences",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .put("billing-preferences");
        });
  }

  public static Performable getClaimsUsingGetOnTheClaimController(
      String sortField,
      String isDescending,
      String pageSize,
      String page,
      String statuses,
      String customerId) {
    return Task.where(
        "{0} Get All Claims",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("sortField", sortField)
              .queryParam("isDescending", isDescending)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("statuses", statuses)
              .queryParam("customerId", customerId)
              .get("claims");
        });
  }

  public static Performable uploadFileToFolderUsingPostOnTheDocumentcontrollerController(
      File file, String folder) {
    String mime = URLConnection.guessContentTypeFromName(file.getName());
    return Task.where(
        "{0} Upload file to folder",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("multipart/form-data")
              .multiPart("file", file, mime)
              .queryParam("folder", folder)
              .post("documents");
        });
  }

  public static Performable getLatestPolicyVersionUsingGetOnThePolicycontrollerController(
      String policyId) {
    return Task.where(
        "{0} Get Latest Policy Version",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("policyId", policyId)
              .get("policies/{policyId}/versions/latest");
        });
  }

  public static Performable searchClosedOpportunitiesByCustomerUsingGetOnTheOpportunityController(
      String pageSize,
      String page,
      String isDescending,
      String searchTerm,
      String sortField,
      String status,
      String type,
      String customerId,
      String filter) {
    return Task.where(
        "{0} Search Closed Opportunities",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("isDescending", isDescending)
              .queryParam("searchTerm", searchTerm)
              .queryParam("sortField", sortField)
              .queryParam("status", status)
              .queryParam("type", type)
              .queryParam("customerId", customerId)
              .queryParam("filter", filter)
              .get("opportunities?filter=byClosedOpportunities");
        });
  }

  public static Performable getQuoteByIdUsingGetOnTheQuotecontrollerController(
      String applicationId, String id) {
    return Task.where(
        "{0} Get Quote by id",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("applicationId", applicationId)
              .pathParam("id", id)
              .get("applications/{applicationId}/quotes/{id}");
        });
  }

  public static Performable
      getBillingPreferencesUsingGetOnTheBillingpreferencescontrollerController() {
    return Task.where(
        "{0} Get Billing Preferences",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .get("billing-preferences");
        });
  }

  public static Performable deleteEmailMessagesUsingDeleteOnTheEmailmessagecontrollerController(
      Object body) {
    return Task.where(
        "{0} Deleting email messages by ids",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .delete("email-messages");
        });
  }

  public static Performable getTaskAccordionInfoUsingGetOnTheTaskController(String id) {
    return Task.where(
        "{0} Get task accordion info by ID",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("tasks/{id}/accordion-info");
        });
  }

  public static Performable searchForInvoicesUsingGetOnTheInvoiceorchestrationcontrollerController(
      String pageSize,
      String page,
      String searchTerm,
      String statuses,
      String sortField,
      String isDescending,
      String filter) {
    return Task.where(
        "{0} Search for Invoices",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("searchTerm", searchTerm)
              .queryParam("statuses", statuses)
              .queryParam("sortField", sortField)
              .queryParam("isDescending", isDescending)
              .queryParam("filter", filter)
              .get("invoices?filter=bySearchTerm");
        });
  }

  public static Performable getAuthUsersWithRolesByContextUsingGetOnTheAuthUserController(
      String scope,
      String userOrDisplayNameIncludes,
      String sortBy,
      String sortDirection,
      String activeFilter,
      String pageSize,
      String page) {
    return Task.where(
        "{0} Retrieve Users for a given Context with their roles",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("scope", scope)
              .queryParam("userOrDisplayNameIncludes", userOrDisplayNameIncludes)
              .queryParam("sortBy", sortBy)
              .queryParam("sortDirection", sortDirection)
              .queryParam("activeFilter", activeFilter)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get("users");
        });
  }

  public static Performable getEmailMessageUsingGetOnTheEmailmessagecontrollerController(
      String id) {
    return Task.where(
        "{0} Get Email Message",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("email-messages/{id}");
        });
  }

  public static Performable getAssignableRolesUsingGetOnTheRolesController() {
    return Task.where(
        "{0} Retrieve Assignable Agency roles",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .get("assignable-agency-roles");
        });
  }

  public static Performable searchSubmissionsByUserUsingGetOnTheSubmissionController(
      String searchTerm,
      String sortField,
      String isDescending,
      String type,
      String hideClosed,
      String pageSize,
      String page,
      String filter) {
    return Task.where(
        "{0} Search for Submission by customer name",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("searchTerm", searchTerm)
              .queryParam("sortField", sortField)
              .queryParam("isDescending", isDescending)
              .queryParam("type", type)
              .queryParam("hideClosed", hideClosed)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("submissions?filter=bySearchTerm");
        });
  }

  public static Performable getDownloadConfigUsingGetOnTheDownloadscontrollerController() {
    return Task.where(
        "{0} getDownloadConfig",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .get("downloads/settings");
        });
  }

  public static Performable getApplicationsUsingGetOnTheApplicationController(
      String pageSize, String page, String id) {
    return Task.where(
        "{0} Get applications",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .pathParam("id", id)
              .get("opportunities/{id}/applications");
        });
  }

  public static Performable getPolicyVersionUsingGetOnThePolicycontrollerController(
      String policyId, String versionId) {
    return Task.where(
        "{0} Get Policy Version",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("policyId", policyId)
              .pathParam("versionId", versionId)
              .get("policies/{policyId}/versions/{versionId}");
        });
  }

  public static Performable getRolesDescriptionsUsingGetOnTheRolesController() {
    return Task.where(
        "{0} Retrieve Agency roles descriptions",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .get("agency-roles-descriptions");
        });
  }

  public static Performable getApplicationSummariesUsingGetOnTheApplicationController(
      String pageSize, String page, String id) {
    return Task.where(
        "{0} Get applications",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .pathParam("id", id)
              .get("opportunities/{id}/application-summaries");
        });
  }

  public static Performable getDownloadsPoliciesByNumberUsingGetOnTheDownloadscontrollerController(
      String policyNumber, String pageSize, String page, String customerId) {
    return Task.where(
        "{0} Get Downloads Policies by Policy Number",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("policyNumber", policyNumber)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("customerId", customerId)
              .get("policies");
        });
  }

  public static Performable searchQuotesByApplicationUsingGetOnTheQuotecontrollerController(
      String pageSize, String page, String applicationId) {
    return Task.where(
        "{0} Search Quotes Application ID",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .pathParam("applicationId", applicationId)
              .get("applications/{applicationId}/quotes");
        });
  }

  public static Performable getInvoiceByIdsUsingGetOnTheInvoiceorchestrationcontrollerController(
      String ids, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Get Invoices by IDs",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("ids", ids)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("invoices?filter=byIds");
        });
  }

  public static Performable updateDownloadConfigUsingPutOnTheDownloadscontrollerController(
      Object body) {
    return Task.where(
        "{0} updateDownloadConfig",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .put("downloads/settings");
        });
  }

  public static Performable getMyTasksUsingGetOnTheTaskController(
      String pageSize,
      String page,
      String sortField,
      String isDescending,
      String customerName,
      String statuses,
      String types,
      String hideCced,
      String dueDate,
      String filter) {
    return Task.where(
        "{0} Get my tasks",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("sortField", sortField)
              .queryParam("isDescending", isDescending)
              .queryParam("customerName", customerName)
              .queryParam("statuses", statuses)
              .queryParam("types", types)
              .queryParam("hideCced", hideCced)
              .queryParam("dueDate", dueDate)
              .queryParam("filter", filter)
              .get("tasks?filter=byMyTasks");
        });
  }

  public static Performable searchForPoliciesUsingGetOnTheGlobalsearchcontrollerController(
      String pageSize, String page, String searchTerm) {
    return Task.where(
        "{0} Search for Policies",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("searchTerm", searchTerm)
              .get("global-search/policies");
        });
  }

  public static Performable searchOpenOpportunitiesByCustomerUsingGetOnTheOpportunityController(
      String pageSize,
      String page,
      String isDescending,
      String searchTerm,
      String sortField,
      String status,
      String type,
      String customerId,
      String filter) {
    return Task.where(
        "{0} Search Open Opportunities",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("isDescending", isDescending)
              .queryParam("searchTerm", searchTerm)
              .queryParam("sortField", sortField)
              .queryParam("status", status)
              .queryParam("type", type)
              .queryParam("customerId", customerId)
              .queryParam("filter", filter)
              .get("opportunities?filter=byOpenOpportunities");
        });
  }

  public static Performable putRolesDescriptionsUsingPutOnTheRolesController(Object body) {
    return Task.where(
        "{0} Updates Agency roles descriptions",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .put("agency-roles-descriptions");
        });
  }
}
