package com.vertafore.test.tasks.servicewrappers.smallagencyamsweborchestration;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import com.vertafore.test.abilities.CallTitanApi;
import java.io.File;
import java.net.URLConnection;

/**
* This class was automatically generated on 2020/03/25 13:40:55

*/
public class UseSmallAgencyAmsWebOrchestrationServiceTo {

	private static final String THIS_SERVICE = "small-agency-ams-web-orchestration";
	private static final String DELETE_DOCUMENT_FROM_FOLDER_USING_DELETE_ON_THE_QUOTECONTROLLER_CONTROLLER = "applications/{applicationId}/quotes/{quoteId}/documents/{documentId}";
	private static final String UPLOAD_FILE_TO_FOLDER_USING_POST_ON_THE_DOCUMENTCONTROLLER_CONTROLLER = "documents";
	private static final String GET_DOWNLOAD_CONFIG_USING_GET_ON_THE_DOWNLOADSCONTROLLER_CONTROLLER = "downloads/settings";
	private static final String UPDATE_DOWNLOAD_CONFIG_USING_PUT_ON_THE_DOWNLOADSCONTROLLER_CONTROLLER = "downloads/settings";
	private static final String GET_ROLE_PERMISSIONS_USING_GET_ON_THE_ROLES_CONTROLLER = "agency-roles";
	private static final String PUT_ROLES_USING_PUT_ON_THE_ROLES_CONTROLLER = "agency-roles";
	private static final String SEARCH_CLOSED_OPPORTUNITIES_BY_CUSTOMER_USING_GET_ON_THE_OPPORTUNITY_CONTROLLER = "opportunities?filter=byClosedOpportunities";
	private static final String SEARCH_OPEN_OPPORTUNITIES_BY_CUSTOMER_USING_GET_ON_THE_OPPORTUNITY_CONTROLLER = "opportunities?filter=byOpenOpportunities";
	private static final String GET_POLICY_VERSION_USING_GET_ON_THE_POLICYCONTROLLER_CONTROLLER = "policies/{policyId}/versions/{versionId}";
	private static final String SETUP_EMAIL_INFORMATION_USING_GET_ON_THE_EMAILMESSAGECONTROLLER_CONTROLLER = "email-setup-information";
	private static final String GET_ASSIGNABLE_ROLES_USING_GET_ON_THE_ROLES_CONTROLLER = "assignable-agency-roles";
	private static final String GET_INVOICES_ACCOUNT_SUMMARY_USING_GET_ON_THE_INVOICEORCHESTRATIONCONTROLLER_CONTROLLER = "invoices/account-summary";
	private static final String GET_ROLES_DESCRIPTIONS_USING_GET_ON_THE_ROLES_CONTROLLER = "agency-roles-descriptions";
	private static final String PUT_ROLES_DESCRIPTIONS_USING_PUT_ON_THE_ROLES_CONTROLLER = "agency-roles-descriptions";
	private static final String GET_OPPORTUNITY_BY_ID_USING_GET_ON_THE_OPPORTUNITY_CONTROLLER = "opportunities/{id}";
	private static final String GET_INVOICES_SETTINGS_USING_GET_ON_THE_INVOICEORCHESTRATIONCONTROLLER_CONTROLLER = "invoices/settings";
	private static final String GET_AUTH_USERS_WITH_ROLES_BY_CONTEXT_USING_GET_ON_THE_AUTH_USER_CONTROLLER = "users";
	private static final String SEARCH_FORM_TEMPLATES_USING_GET_ON_THE_FORMCONTROLLER_CONTROLLER = "form-templates?filter=bySearchTerm";
	private static final String GET_BILLING_PREFERENCES_USING_GET_ON_THE_BILLINGPREFERENCESCONTROLLER_CONTROLLER = "billing-preferences";
	private static final String SET_BILLING_PREFERENCES_USING_PUT_ON_THE_BILLINGPREFERENCESCONTROLLER_CONTROLLER = "billing-preferences";
	private static final String GET_DOWNLOADS_POLICIES_BY_NUMBER_USING_GET_ON_THE_DOWNLOADSCONTROLLER_CONTROLLER = "policies";
	private static final String CREATE_OPPORTUNITY_AND_MOVE_EXISTING_APPLICATION_INTO_IT_USING_POST_ON_THE_OPPORTUNITY_CONTROLLER = "opportunities/applications/{existingApplicationId}";
	private static final String GET_CLAIMS_USING_GET_ON_THE_CLAIM_CONTROLLER = "claims";
	private static final String GET_INVOICE_BY_IDS_USING_GET_ON_THE_INVOICEORCHESTRATIONCONTROLLER_CONTROLLER = "invoices?filter=byIds";
	private static final String GET_APPLICATION_SUMMARIES_USING_GET_ON_THE_APPLICATION_CONTROLLER = "opportunities/{id}/application-summaries";
	private static final String GET_TASK_USING_GET_ON_THE_TASK_CONTROLLER = "tasks/{id}";
	private static final String GET_TASK_ACCORDION_INFO_USING_GET_ON_THE_TASK_CONTROLLER = "tasks/{id}/accordion-info";
	private static final String GET_MY_TASKS_USING_GET_ON_THE_TASK_CONTROLLER = "tasks?filter=byMyTasks";
	private static final String GET_EMAIL_MESSAGE_USING_GET_ON_THE_EMAILMESSAGECONTROLLER_CONTROLLER = "email-messages/{id}";
	private static final String SEARCH_FOR_CUSTOMERS_USING_GET_ON_THE_GLOBALSEARCHCONTROLLER_CONTROLLER = "global-search/customers?scope=descendants{&pageSize,page,searchTerm}";
	private static final String SEARCH_FOR_POLICIES_USING_GET_ON_THE_GLOBALSEARCHCONTROLLER_CONTROLLER = "global-search/policies";
	private static final String GET_QUOTE_BY_ID_USING_GET_ON_THE_QUOTECONTROLLER_CONTROLLER = "applications/{applicationId}/quotes/{id}";
	private static final String SEARCH_EMAIL_MESSAGES_USING_GET_ON_THE_EMAILMESSAGECONTROLLER_CONTROLLER = "email-messages?filter=byEmailMessages";
	private static final String GET_MATCH_RESULTS_BY_ID_USING_GET_ON_THE_DOWNLOADSCONTROLLER_CONTROLLER = "matchresults/{matchResultId}";
	private static final String SEARCH_FOR_INVOICES_USING_GET_ON_THE_INVOICEORCHESTRATIONCONTROLLER_CONTROLLER = "invoices?filter=bySearchTerm";
	private static final String GET_AGENCY_TASKS_USING_GET_ON_THE_TASK_CONTROLLER = "tasks?filter=byAgencyTasks";
	private static final String GET_LATEST_POLICY_VERSION_USING_GET_ON_THE_POLICYCONTROLLER_CONTROLLER = "policies/{policyId}/versions/latest";
	private static final String SEARCH_QUOTES_BY_APPLICATION_USING_GET_ON_THE_QUOTECONTROLLER_CONTROLLER = "applications/{applicationId}/quotes";
	private static final String SEARCH_SUBMISSIONS_BY_USER_USING_GET_ON_THE_SUBMISSION_CONTROLLER = "submissions?filter=bySearchTerm";
	private static final String GET_APPLICATIONS_USING_GET_ON_THE_APPLICATION_CONTROLLER = "opportunities/{id}/applications";
	private static final String MOVE_ATTACHMENT_USING_POST_ON_THE_EMAILMESSAGECONTROLLER_CONTROLLER = "email-messages/attachments";
	private static final String UPDATE_READ_ON_USING_POST_ON_THE_EMAILMESSAGECONTROLLER_CONTROLLER = "email-messages";
	private static final String DELETE_EMAIL_MESSAGES_USING_DELETE_ON_THE_EMAILMESSAGECONTROLLER_CONTROLLER = "email-messages";

	public static Performable deleteDocumentFromFolderUsingDeleteOnTheQuotecontrollerController(String applicationId, String quoteId, String documentId){
		return Task.where(
		"{0} Delete Document from Folder", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("applicationId", applicationId).pathParam("quoteId", quoteId).pathParam("documentId", documentId).delete(DELETE_DOCUMENT_FROM_FOLDER_USING_DELETE_ON_THE_QUOTECONTROLLER_CONTROLLER);
			}
		);
	}

	public static Performable uploadFileToFolderUsingPostOnTheDocumentcontrollerController(File file, String folder){
		String mime = URLConnection.guessContentTypeFromName(file.getName());return Task.where(
		"{0} Upload file to folder", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("multipart/form-data").multiPart("file", file , mime).queryParam("folder", folder).post(UPLOAD_FILE_TO_FOLDER_USING_POST_ON_THE_DOCUMENTCONTROLLER_CONTROLLER);
			}
		);
	}

	public static Performable getDownloadConfigUsingGetOnTheDownloadscontrollerController(){
		return Task.where(
		"{0} getDownloadConfig", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").get(GET_DOWNLOAD_CONFIG_USING_GET_ON_THE_DOWNLOADSCONTROLLER_CONTROLLER);
			}
		);
	}

	public static Performable updateDownloadConfigUsingPutOnTheDownloadscontrollerController(Object body){
		return Task.where(
		"{0} updateDownloadConfig", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).put(UPDATE_DOWNLOAD_CONFIG_USING_PUT_ON_THE_DOWNLOADSCONTROLLER_CONTROLLER);
			}
		);
	}

	public static Performable getRolePermissionsUsingGetOnTheRolesController(){
		return Task.where(
		"{0} Retrieve Agency roles", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").get(GET_ROLE_PERMISSIONS_USING_GET_ON_THE_ROLES_CONTROLLER);
			}
		);
	}

	public static Performable putRolesUsingPutOnTheRolesController(Object body){
		return Task.where(
		"{0} Updates Agency roles", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).put(PUT_ROLES_USING_PUT_ON_THE_ROLES_CONTROLLER);
			}
		);
	}

	public static Performable searchClosedOpportunitiesByCustomerUsingGetOnTheOpportunityController(String pageSize, String page, String isDescending, String searchTerm, String sortField, String status, String type, String customerId, String filter){
		return Task.where(
		"{0} Search Closed Opportunities", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("isDescending", isDescending).queryParam("searchTerm", searchTerm).queryParam("sortField", sortField).queryParam("status", status).queryParam("type", type).queryParam("customerId", customerId).queryParam("filter", filter).get(SEARCH_CLOSED_OPPORTUNITIES_BY_CUSTOMER_USING_GET_ON_THE_OPPORTUNITY_CONTROLLER);
			}
		);
	}

	public static Performable searchOpenOpportunitiesByCustomerUsingGetOnTheOpportunityController(String pageSize, String page, String isDescending, String searchTerm, String sortField, String status, String type, String customerId, String filter){
		return Task.where(
		"{0} Search Open Opportunities", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("isDescending", isDescending).queryParam("searchTerm", searchTerm).queryParam("sortField", sortField).queryParam("status", status).queryParam("type", type).queryParam("customerId", customerId).queryParam("filter", filter).get(SEARCH_OPEN_OPPORTUNITIES_BY_CUSTOMER_USING_GET_ON_THE_OPPORTUNITY_CONTROLLER);
			}
		);
	}

	public static Performable getPolicyVersionUsingGetOnThePolicycontrollerController(String policyId, String versionId){
		return Task.where(
		"{0} Get Policy Version", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("policyId", policyId).pathParam("versionId", versionId).get(GET_POLICY_VERSION_USING_GET_ON_THE_POLICYCONTROLLER_CONTROLLER);
			}
		);
	}

	public static Performable setupEmailInformationUsingGetOnTheEmailmessagecontrollerController(){
		return Task.where(
		"{0} Get primary email addresses information and email folder", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").get(SETUP_EMAIL_INFORMATION_USING_GET_ON_THE_EMAILMESSAGECONTROLLER_CONTROLLER);
			}
		);
	}

	public static Performable getAssignableRolesUsingGetOnTheRolesController(){
		return Task.where(
		"{0} Retrieve Assignable Agency roles", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").get(GET_ASSIGNABLE_ROLES_USING_GET_ON_THE_ROLES_CONTROLLER);
			}
		);
	}

	public static Performable getInvoicesAccountSummaryUsingGetOnTheInvoiceorchestrationcontrollerController(){
		return Task.where(
		"{0} Get Invoice Account Summary", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").get(GET_INVOICES_ACCOUNT_SUMMARY_USING_GET_ON_THE_INVOICEORCHESTRATIONCONTROLLER_CONTROLLER);
			}
		);
	}

	public static Performable getRolesDescriptionsUsingGetOnTheRolesController(){
		return Task.where(
		"{0} Retrieve Agency roles descriptions", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").get(GET_ROLES_DESCRIPTIONS_USING_GET_ON_THE_ROLES_CONTROLLER);
			}
		);
	}

	public static Performable putRolesDescriptionsUsingPutOnTheRolesController(Object body){
		return Task.where(
		"{0} Updates Agency roles descriptions", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).put(PUT_ROLES_DESCRIPTIONS_USING_PUT_ON_THE_ROLES_CONTROLLER);
			}
		);
	}

	public static Performable getOpportunityByIdUsingGetOnTheOpportunityController(String id){
		return Task.where(
		"{0} Get Opportunity by id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_OPPORTUNITY_BY_ID_USING_GET_ON_THE_OPPORTUNITY_CONTROLLER);
			}
		);
	}

	public static Performable getInvoicesSettingsUsingGetOnTheInvoiceorchestrationcontrollerController(){
		return Task.where(
		"{0} Get Invoice settings", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").get(GET_INVOICES_SETTINGS_USING_GET_ON_THE_INVOICEORCHESTRATIONCONTROLLER_CONTROLLER);
			}
		);
	}

	public static Performable getAuthUsersWithRolesByContextUsingGetOnTheAuthUserController(String scope, String userOrDisplayNameIncludes, String sortBy, String sortDirection, String activeFilter, String pageSize, String page){
		return Task.where(
		"{0} Retrieve Users for a given Context with their roles", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("scope", scope).queryParam("userOrDisplayNameIncludes", userOrDisplayNameIncludes).queryParam("sortBy", sortBy).queryParam("sortDirection", sortDirection).queryParam("activeFilter", activeFilter).queryParam("pageSize", pageSize).queryParam("page", page).get(GET_AUTH_USERS_WITH_ROLES_BY_CONTEXT_USING_GET_ON_THE_AUTH_USER_CONTROLLER);
			}
		);
	}

	public static Performable searchFormTemplatesUsingGetOnTheFormcontrollerController(String pageSize, String page, String type, String numberOfVersions, String filter){
		return Task.where(
		"{0} Get latest form template versions", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("type", type).queryParam("numberOfVersions", numberOfVersions).queryParam("filter", filter).get(SEARCH_FORM_TEMPLATES_USING_GET_ON_THE_FORMCONTROLLER_CONTROLLER);
			}
		);
	}

	public static Performable getBillingPreferencesUsingGetOnTheBillingpreferencescontrollerController(){
		return Task.where(
		"{0} Get Billing Preferences", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").get(GET_BILLING_PREFERENCES_USING_GET_ON_THE_BILLINGPREFERENCESCONTROLLER_CONTROLLER);
			}
		);
	}

	public static Performable setBillingPreferencesUsingPutOnTheBillingpreferencescontrollerController(Object body){
		return Task.where(
		"{0} Set billing preferences", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).put(SET_BILLING_PREFERENCES_USING_PUT_ON_THE_BILLINGPREFERENCESCONTROLLER_CONTROLLER);
			}
		);
	}

	public static Performable getDownloadsPoliciesByNumberUsingGetOnTheDownloadscontrollerController(String policyNumber, String pageSize, String page, String customerId){
		return Task.where(
		"{0} Get Downloads Policies by Policy Number", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("policyNumber", policyNumber).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("customerId", customerId).get(GET_DOWNLOADS_POLICIES_BY_NUMBER_USING_GET_ON_THE_DOWNLOADSCONTROLLER_CONTROLLER);
			}
		);
	}

	public static Performable createOpportunityAndMoveExistingApplicationIntoItUsingPostOnTheOpportunityController(Object body, String existingApplicationId){
		return Task.where(
		"{0} Create new Opportunity and move existing Application into it", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).pathParam("existingApplicationId", existingApplicationId).post(CREATE_OPPORTUNITY_AND_MOVE_EXISTING_APPLICATION_INTO_IT_USING_POST_ON_THE_OPPORTUNITY_CONTROLLER);
			}
		);
	}

	public static Performable getClaimsUsingGetOnTheClaimController(String sortField, String isDescending, String pageSize, String page, String statuses, String customerId){
		return Task.where(
		"{0} Get All Claims", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("sortField", sortField).queryParam("isDescending", isDescending).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("statuses", statuses).queryParam("customerId", customerId).get(GET_CLAIMS_USING_GET_ON_THE_CLAIM_CONTROLLER);
			}
		);
	}

	public static Performable getInvoiceByIdsUsingGetOnTheInvoiceorchestrationcontrollerController(String ids, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get Invoices by IDs", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("ids", ids).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_INVOICE_BY_IDS_USING_GET_ON_THE_INVOICEORCHESTRATIONCONTROLLER_CONTROLLER);
			}
		);
	}

	public static Performable getApplicationSummariesUsingGetOnTheApplicationController(String pageSize, String page, String id){
		return Task.where(
		"{0} Get applications", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).pathParam("id", id).get(GET_APPLICATION_SUMMARIES_USING_GET_ON_THE_APPLICATION_CONTROLLER);
			}
		);
	}

	public static Performable getTaskUsingGetOnTheTaskController(String id){
		return Task.where(
		"{0} Get task by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_TASK_USING_GET_ON_THE_TASK_CONTROLLER);
			}
		);
	}

	public static Performable getTaskAccordionInfoUsingGetOnTheTaskController(String id){
		return Task.where(
		"{0} Get task accordion info by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_TASK_ACCORDION_INFO_USING_GET_ON_THE_TASK_CONTROLLER);
			}
		);
	}

	public static Performable getMyTasksUsingGetOnTheTaskController(String pageSize, String page, String sortField, String isDescending, String customerName, String statuses, String types, String hideCced, String dueDate, String filter){
		return Task.where(
		"{0} Get my tasks", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("sortField", sortField).queryParam("isDescending", isDescending).queryParam("customerName", customerName).queryParam("statuses", statuses).queryParam("types", types).queryParam("hideCced", hideCced).queryParam("dueDate", dueDate).queryParam("filter", filter).get(GET_MY_TASKS_USING_GET_ON_THE_TASK_CONTROLLER);
			}
		);
	}

	public static Performable getEmailMessageUsingGetOnTheEmailmessagecontrollerController(String id){
		return Task.where(
		"{0} Get Email Message", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_EMAIL_MESSAGE_USING_GET_ON_THE_EMAILMESSAGECONTROLLER_CONTROLLER);
			}
		);
	}

	public static Performable searchForCustomersUsingGetOnTheGlobalsearchcontrollerController(String pageSize, String page, String searchTerm, String scope){
		return Task.where(
		"{0} Search for Customers", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("searchTerm", searchTerm).queryParam("scope", scope).get(SEARCH_FOR_CUSTOMERS_USING_GET_ON_THE_GLOBALSEARCHCONTROLLER_CONTROLLER);
			}
		);
	}

	public static Performable searchForPoliciesUsingGetOnTheGlobalsearchcontrollerController(String pageSize, String page, String searchTerm){
		return Task.where(
		"{0} Search for Policies", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("searchTerm", searchTerm).get(SEARCH_FOR_POLICIES_USING_GET_ON_THE_GLOBALSEARCHCONTROLLER_CONTROLLER);
			}
		);
	}

	public static Performable getQuoteByIdUsingGetOnTheQuotecontrollerController(String applicationId, String id){
		return Task.where(
		"{0} Get Quote by id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("applicationId", applicationId).pathParam("id", id).get(GET_QUOTE_BY_ID_USING_GET_ON_THE_QUOTECONTROLLER_CONTROLLER);
			}
		);
	}

	public static Performable searchEmailMessagesUsingGetOnTheEmailmessagecontrollerController(String pageSize, String page, String isDescending, String searchTerm, String sortField, String status, String filterRead, String filter){
		return Task.where(
		"{0} Search Email Messages", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("isDescending", isDescending).queryParam("searchTerm", searchTerm).queryParam("sortField", sortField).queryParam("status", status).queryParam("filterRead", filterRead).queryParam("filter", filter).get(SEARCH_EMAIL_MESSAGES_USING_GET_ON_THE_EMAILMESSAGECONTROLLER_CONTROLLER);
			}
		);
	}

	public static Performable getMatchResultsByIdUsingGetOnTheDownloadscontrollerController(String matchResultId){
		return Task.where(
		"{0} Get a Match Result", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("matchResultId", matchResultId).get(GET_MATCH_RESULTS_BY_ID_USING_GET_ON_THE_DOWNLOADSCONTROLLER_CONTROLLER);
			}
		);
	}

	public static Performable searchForInvoicesUsingGetOnTheInvoiceorchestrationcontrollerController(String pageSize, String page, String searchTerm, String statuses, String sortField, String isDescending, String filter){
		return Task.where(
		"{0} Search for Invoices", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("searchTerm", searchTerm).queryParam("statuses", statuses).queryParam("sortField", sortField).queryParam("isDescending", isDescending).queryParam("filter", filter).get(SEARCH_FOR_INVOICES_USING_GET_ON_THE_INVOICEORCHESTRATIONCONTROLLER_CONTROLLER);
			}
		);
	}

	public static Performable getAgencyTasksUsingGetOnTheTaskController(String pageSize, String page, String sortField, String isDescending, String customerName, String assigneeUserId, String hideCced, String statuses, String types, String dueDate, String filter){
		return Task.where(
		"{0} Get agency tasks", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("sortField", sortField).queryParam("isDescending", isDescending).queryParam("customerName", customerName).queryParam("assigneeUserId", assigneeUserId).queryParam("hideCced", hideCced).queryParam("statuses", statuses).queryParam("types", types).queryParam("dueDate", dueDate).queryParam("filter", filter).get(GET_AGENCY_TASKS_USING_GET_ON_THE_TASK_CONTROLLER);
			}
		);
	}

	public static Performable getLatestPolicyVersionUsingGetOnThePolicycontrollerController(String policyId){
		return Task.where(
		"{0} Get Latest Policy Version", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("policyId", policyId).get(GET_LATEST_POLICY_VERSION_USING_GET_ON_THE_POLICYCONTROLLER_CONTROLLER);
			}
		);
	}

	public static Performable searchQuotesByApplicationUsingGetOnTheQuotecontrollerController(String pageSize, String page, String applicationId){
		return Task.where(
		"{0} Search Quotes Application ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).pathParam("applicationId", applicationId).get(SEARCH_QUOTES_BY_APPLICATION_USING_GET_ON_THE_QUOTECONTROLLER_CONTROLLER);
			}
		);
	}

	public static Performable searchSubmissionsByUserUsingGetOnTheSubmissionController(String searchTerm, String sortField, String isDescending, String type, String hideClosed, String pageSize, String page, String filter){
		return Task.where(
		"{0} Search for Submission by customer name", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("searchTerm", searchTerm).queryParam("sortField", sortField).queryParam("isDescending", isDescending).queryParam("type", type).queryParam("hideClosed", hideClosed).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(SEARCH_SUBMISSIONS_BY_USER_USING_GET_ON_THE_SUBMISSION_CONTROLLER);
			}
		);
	}

	public static Performable getApplicationsUsingGetOnTheApplicationController(String pageSize, String page, String id){
		return Task.where(
		"{0} Get applications", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).pathParam("id", id).get(GET_APPLICATIONS_USING_GET_ON_THE_APPLICATION_CONTROLLER);
			}
		);
	}

	public static Performable moveAttachmentUsingPostOnTheEmailmessagecontrollerController(Object body){
		return Task.where(
		"{0} Move attachment from email to customer documents", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(MOVE_ATTACHMENT_USING_POST_ON_THE_EMAILMESSAGECONTROLLER_CONTROLLER);
			}
		);
	}

	public static Performable updateReadOnUsingPostOnTheEmailmessagecontrollerController(Object body){
		return Task.where(
		"{0} Accept a list of MessageIds to update readOn", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(UPDATE_READ_ON_USING_POST_ON_THE_EMAILMESSAGECONTROLLER_CONTROLLER);
			}
		);
	}

	public static Performable deleteEmailMessagesUsingDeleteOnTheEmailmessagecontrollerController(Object body){
		return Task.where(
		"{0} Deleting email messages by ids", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).delete(DELETE_EMAIL_MESSAGES_USING_DELETE_ON_THE_EMAILMESSAGECONTROLLER_CONTROLLER);
			}
		);
	}



}
