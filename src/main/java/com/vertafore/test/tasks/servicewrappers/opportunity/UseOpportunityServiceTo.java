package com.vertafore.test.tasks.servicewrappers.opportunity;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import com.vertafore.test.abilities.CallTitanApi;

/**
* This class was automatically generated on 2020/03/25 13:40:48

*/
public class UseOpportunityServiceTo {

	private static final String THIS_SERVICE = "opportunity";
	private static final String GET_APPLICATIONS_BY_OPPORTUNITY_USING_GET_ON_THE_APPLICATION_CONTROLLER = "opportunities/{opportunityId}/applications";
	private static final String DELETE_BY_ID_USING_DELETE_ON_THE_CARRIERSUBMISSION_CONTROLLER = "submissions/{submissionId}/carrier-submissions/";
	private static final String DELETE_MANUAL_FORM_INSTANCE_USING_DELETE_ON_THE_APPLICATION_CONTROLLER = "opportunities/{opportunityId}/applications/{applicationId}/manual-forms/{formInstanceId}";
	private static final String PATCH_SUBMISSION_USING_PATCH_ON_THE_SUBMISSION_CONTROLLER = "submissions/{submissionId}";
	private static final String GET_SUBMISSION_BY_ID_USING_GET_ON_THE_SUBMISSION_CONTROLLER = "submissions/{submissionId}";
	private static final String DELETE_SUBMISSION_USING_DELETE_ON_THE_SUBMISSION_CONTROLLER = "submissions/{submissionId}";
	private static final String REPLACE_SUBMISSION_USING_PUT_ON_THE_SUBMISSION_CONTROLLER = "submissions/{submissionId}";
	private static final String SEARCH_OPEN_OPPORTUNITIES_BY_CUSTOMER_USING_GET_ON_THE_OPPORTUNITY_CONTROLLER = "opportunities?filter=byOpenOpportunities";
	private static final String CREATE_SUBMISSION_USING_POST_ON_THE_SUBMISSION_CONTROLLER = "submissions";
	private static final String GET_CARRIER_SUBMISSIONS_BY_SUBMISSION_ID_USING_GET_ON_THE_CARRIERSUBMISSION_CONTROLLER = "submissions/{submissionId}/carrier-submissions";
	private static final String GET_DEFAULT_APPLICATION_FORM_USING_GET_ON_THE_DEFAULTAPPLICATIONFORM_CONTROLLER = "default-application-forms/{productKey}";
	private static final String DELETE_DEFAULT_APPLICATION_FORM_USING_DELETE_ON_THE_DEFAULTAPPLICATIONFORM_CONTROLLER = "default-application-forms/{productKey}";
	private static final String UPSERT_DEFAULT_APPLICATION_FORM_USING_PUT_ON_THE_DEFAULTAPPLICATIONFORM_CONTROLLER = "default-application-forms/{productKey}";
	private static final String DELETE_BY_CARRIER_ID_USING_DELETE_ON_THE_CARRIERSUBMISSION_CONTROLLER = "submissions/{submissionId}/carrier-submissions/{carrierId}";
	private static final String PATCH_QUOTE_USING_PATCH_ON_THE_QUOTE_CONTROLLER = "applications/{applicationId}/quotes/{quoteId}";
	private static final String GET_QUOTE_BY_ID_USING_GET_ON_THE_QUOTE_CONTROLLER = "applications/{applicationId}/quotes/{quoteId}";
	private static final String DELETE_QUOTE_USING_DELETE_ON_THE_QUOTE_CONTROLLER = "applications/{applicationId}/quotes/{quoteId}";
	private static final String SEARCH_SUBMISSIONS_USING_GET_ON_THE_SUBMISSION_CONTROLLER = "submissions?filter=bySearchTerm";
	private static final String REPLACE_QUOTE_USING_PUT_ON_THE_QUOTE_CONTROLLER = "quotes/{quoteId}";
	private static final String ADD_CARRIER_IDS_USING_PUT_ON_THE_APPLICATION_CONTROLLER = "opportunities/{opportunityId}/applications/carrier-ids";
	private static final String CREATE_QUOTE_USING_POST_ON_THE_QUOTE_CONTROLLER = "applications/{applicationId}/quotes";
	private static final String SEARCH_QUOTES_BY_APPLICATION_ID_USING_GET_ON_THE_QUOTE_CONTROLLER = "applications/{applicationId}/quotes?filter=byApplicationId";
	private static final String MOVE_APPLICATION_TO_OPPORTUNITY_USING_PUT_ON_THE_APPLICATION_CONTROLLER = "opportunities/{newOpportunityId}/applications/{existingApplicationId}";
	private static final String PATCH_OPPORTUNITY_USING_PATCH_ON_THE_OPPORTUNITY_CONTROLLER = "opportunities/{opportunityId}";
	private static final String GET_OPPORTUNITY_BY_ID_USING_GET_ON_THE_OPPORTUNITY_CONTROLLER = "opportunities/{opportunityId}";
	private static final String DELETE_OPPORTUNITY_USING_DELETE_ON_THE_OPPORTUNITY_CONTROLLER = "opportunities/{opportunityId}";
	private static final String REPLACE_OPPORTUNITY_USING_PUT_ON_THE_OPPORTUNITY_CONTROLLER = "opportunities/{opportunityId}";
	private static final String GET_APPLICATION_BY_ID_USING_GET_ON_THE_APPLICATION_CONTROLLER = "opportunities/{opportunityId}/applications/{applicationId}";
	private static final String PATCH_CARRIER_SUBMISSION_USING_PATCH_ON_THE_CARRIERSUBMISSION_CONTROLLER = "carrier-submissions/{carrierSubmissionId}";
	private static final String CREATE_OPPORTUNITY_USING_POST_ON_THE_OPPORTUNITY_CONTROLLER = "opportunities";
	private static final String ADD_MANUAL_FORM_INSTANCES_USING_PUT_ON_THE_APPLICATION_CONTROLLER = "opportunities/{opportunityId}/applications/manual-forms";
	private static final String SEARCH_CLOSED_OPPORTUNITIES_BY_CUSTOMER_USING_GET_ON_THE_OPPORTUNITY_CONTROLLER = "opportunities?filter=byClosedOpportunities";

	public static Performable getApplicationsByOpportunityUsingGetOnTheApplicationController(String opportunityId, String pageSize, String page){
		return Task.where(
		"{0} Get all applications for a given opportunity", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("opportunityId", opportunityId).queryParam("pageSize", pageSize).queryParam("page", page).get(GET_APPLICATIONS_BY_OPPORTUNITY_USING_GET_ON_THE_APPLICATION_CONTROLLER);
			}
		);
	}

	public static Performable deleteByIdUsingDeleteOnTheCarriersubmissionController(String submissionId, String carrierSubmissionId, String filter){
		return Task.where(
		"{0} Delete LOB (by ID) from carrier submission (by ID)", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("submissionId", submissionId).pathParam("carrierSubmissionId", carrierSubmissionId).queryParam("filter", filter).delete(DELETE_BY_ID_USING_DELETE_ON_THE_CARRIERSUBMISSION_CONTROLLER);
			}
		);
	}

	public static Performable deleteManualFormInstanceUsingDeleteOnTheApplicationController(String opportunityId, String applicationId, String formInstanceId){
		return Task.where(
		"{0} Delete manual application form", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("opportunityId", opportunityId).pathParam("applicationId", applicationId).pathParam("formInstanceId", formInstanceId).delete(DELETE_MANUAL_FORM_INSTANCE_USING_DELETE_ON_THE_APPLICATION_CONTROLLER);
			}
		);
	}

	public static Performable patchSubmissionUsingPatchOnTheSubmissionController(Object body, String submissionId){
		return Task.where(
		"{0} Patch Submission", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").body(body).pathParam("submissionId", submissionId).patch(PATCH_SUBMISSION_USING_PATCH_ON_THE_SUBMISSION_CONTROLLER);
			}
		);
	}

	public static Performable getSubmissionByIdUsingGetOnTheSubmissionController(String submissionId){
		return Task.where(
		"{0} Get Submission", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("submissionId", submissionId).get(GET_SUBMISSION_BY_ID_USING_GET_ON_THE_SUBMISSION_CONTROLLER);
			}
		);
	}

	public static Performable deleteSubmissionUsingDeleteOnTheSubmissionController(String submissionId){
		return Task.where(
		"{0} Delete Submission", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("submissionId", submissionId).delete(DELETE_SUBMISSION_USING_DELETE_ON_THE_SUBMISSION_CONTROLLER);
			}
		);
	}

	public static Performable replaceSubmissionUsingPutOnTheSubmissionController(Object body, String submissionId){
		return Task.where(
		"{0} Replace Submission", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).pathParam("submissionId", submissionId).put(REPLACE_SUBMISSION_USING_PUT_ON_THE_SUBMISSION_CONTROLLER);
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

	public static Performable createSubmissionUsingPostOnTheSubmissionController(Object body){
		return Task.where(
		"{0} Create Submission", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_SUBMISSION_USING_POST_ON_THE_SUBMISSION_CONTROLLER);
			}
		);
	}

	public static Performable getCarrierSubmissionsBySubmissionIdUsingGetOnTheCarriersubmissionController(String submissionId, String pageSize, String page){
		return Task.where(
		"{0} Get all carrier submissions for a given submission", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("submissionId", submissionId).queryParam("pageSize", pageSize).queryParam("page", page).get(GET_CARRIER_SUBMISSIONS_BY_SUBMISSION_ID_USING_GET_ON_THE_CARRIERSUBMISSION_CONTROLLER);
			}
		);
	}

	public static Performable getDefaultApplicationFormUsingGetOnTheDefaultapplicationformController(String productKey){
		return Task.where(
		"{0} Get Default Application Form", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("productKey", productKey).get(GET_DEFAULT_APPLICATION_FORM_USING_GET_ON_THE_DEFAULTAPPLICATIONFORM_CONTROLLER);
			}
		);
	}

	public static Performable deleteDefaultApplicationFormUsingDeleteOnTheDefaultapplicationformController(String productKey){
		return Task.where(
		"{0} Delete Default Application Form", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("productKey", productKey).delete(DELETE_DEFAULT_APPLICATION_FORM_USING_DELETE_ON_THE_DEFAULTAPPLICATIONFORM_CONTROLLER);
			}
		);
	}

	public static Performable upsertDefaultApplicationFormUsingPutOnTheDefaultapplicationformController(Object body, String productKey){
		return Task.where(
		"{0} Upsert Default Application Form", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).pathParam("productKey", productKey).put(UPSERT_DEFAULT_APPLICATION_FORM_USING_PUT_ON_THE_DEFAULTAPPLICATIONFORM_CONTROLLER);
			}
		);
	}

	public static Performable deleteByCarrierIdUsingDeleteOnTheCarriersubmissionController(String submissionId, String carrierId){
		return Task.where(
		"{0} Delete Carrier from carrier submission (all LOBs included)", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("submissionId", submissionId).pathParam("carrierId", carrierId).delete(DELETE_BY_CARRIER_ID_USING_DELETE_ON_THE_CARRIERSUBMISSION_CONTROLLER);
			}
		);
	}

	public static Performable patchQuoteUsingPatchOnTheQuoteController(Object body, String applicationId, String quoteId){
		return Task.where(
		"{0} Patch Quote", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").body(body).pathParam("applicationId", applicationId).pathParam("quoteId", quoteId).patch(PATCH_QUOTE_USING_PATCH_ON_THE_QUOTE_CONTROLLER);
			}
		);
	}

	public static Performable getQuoteByIdUsingGetOnTheQuoteController(String applicationId, String quoteId){
		return Task.where(
		"{0} Get Quote", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("applicationId", applicationId).pathParam("quoteId", quoteId).get(GET_QUOTE_BY_ID_USING_GET_ON_THE_QUOTE_CONTROLLER);
			}
		);
	}

	public static Performable deleteQuoteUsingDeleteOnTheQuoteController(String applicationId, String quoteId){
		return Task.where(
		"{0} Delete Quote", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("applicationId", applicationId).pathParam("quoteId", quoteId).delete(DELETE_QUOTE_USING_DELETE_ON_THE_QUOTE_CONTROLLER);
			}
		);
	}

	public static Performable searchSubmissionsUsingGetOnTheSubmissionController(String pageSize, String page, String isDescending, String searchTerm, String sortField, String type, String hideClosed, String filter){
		return Task.where(
		"{0} Search Submissions", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("isDescending", isDescending).queryParam("searchTerm", searchTerm).queryParam("sortField", sortField).queryParam("type", type).queryParam("hideClosed", hideClosed).queryParam("filter", filter).get(SEARCH_SUBMISSIONS_USING_GET_ON_THE_SUBMISSION_CONTROLLER);
			}
		);
	}

	public static Performable replaceQuoteUsingPutOnTheQuoteController(Object body, String quoteId){
		return Task.where(
		"{0} Replace Quote", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).pathParam("quoteId", quoteId).put(REPLACE_QUOTE_USING_PUT_ON_THE_QUOTE_CONTROLLER);
			}
		);
	}

	public static Performable addCarrierIdsUsingPutOnTheApplicationController(String opportunityId, Object body){
		return Task.where(
		"{0} Add carrier ids", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("opportunityId", opportunityId).body(body).put(ADD_CARRIER_IDS_USING_PUT_ON_THE_APPLICATION_CONTROLLER);
			}
		);
	}

	public static Performable createQuoteUsingPostOnTheQuoteController(Object body, String applicationId){
		return Task.where(
		"{0} Create Quote", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).pathParam("applicationId", applicationId).post(CREATE_QUOTE_USING_POST_ON_THE_QUOTE_CONTROLLER);
			}
		);
	}

	public static Performable searchQuotesByApplicationIdUsingGetOnTheQuoteController(String pageSize, String page, String applicationId, String filter){
		return Task.where(
		"{0} Search Quotes", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).pathParam("applicationId", applicationId).queryParam("filter", filter).get(SEARCH_QUOTES_BY_APPLICATION_ID_USING_GET_ON_THE_QUOTE_CONTROLLER);
			}
		);
	}

	public static Performable moveApplicationToOpportunityUsingPutOnTheApplicationController(String newOpportunityId, String existingApplicationId){
		return Task.where(
		"{0} Move an application to opportunity.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("newOpportunityId", newOpportunityId).pathParam("existingApplicationId", existingApplicationId).put(MOVE_APPLICATION_TO_OPPORTUNITY_USING_PUT_ON_THE_APPLICATION_CONTROLLER);
			}
		);
	}

	public static Performable patchOpportunityUsingPatchOnTheOpportunityController(Object body, String opportunityId){
		return Task.where(
		"{0} Patch Opportunity", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").body(body).pathParam("opportunityId", opportunityId).patch(PATCH_OPPORTUNITY_USING_PATCH_ON_THE_OPPORTUNITY_CONTROLLER);
			}
		);
	}

	public static Performable getOpportunityByIdUsingGetOnTheOpportunityController(String opportunityId){
		return Task.where(
		"{0} Get Opportunity", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("opportunityId", opportunityId).get(GET_OPPORTUNITY_BY_ID_USING_GET_ON_THE_OPPORTUNITY_CONTROLLER);
			}
		);
	}

	public static Performable deleteOpportunityUsingDeleteOnTheOpportunityController(String opportunityId){
		return Task.where(
		"{0} Delete Opportunity", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("opportunityId", opportunityId).delete(DELETE_OPPORTUNITY_USING_DELETE_ON_THE_OPPORTUNITY_CONTROLLER);
			}
		);
	}

	public static Performable replaceOpportunityUsingPutOnTheOpportunityController(Object body, String opportunityId){
		return Task.where(
		"{0} Replace Opportunity", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).pathParam("opportunityId", opportunityId).put(REPLACE_OPPORTUNITY_USING_PUT_ON_THE_OPPORTUNITY_CONTROLLER);
			}
		);
	}

	public static Performable getApplicationByIdUsingGetOnTheApplicationController(String opportunityId, String applicationId){
		return Task.where(
		"{0} Get Application", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("opportunityId", opportunityId).pathParam("applicationId", applicationId).get(GET_APPLICATION_BY_ID_USING_GET_ON_THE_APPLICATION_CONTROLLER);
			}
		);
	}

	public static Performable patchCarrierSubmissionUsingPatchOnTheCarriersubmissionController(Object body, String carrierSubmissionId){
		return Task.where(
		"{0} Patch carrier submission", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").body(body).pathParam("carrierSubmissionId", carrierSubmissionId).patch(PATCH_CARRIER_SUBMISSION_USING_PATCH_ON_THE_CARRIERSUBMISSION_CONTROLLER);
			}
		);
	}

	public static Performable createOpportunityUsingPostOnTheOpportunityController(Object body){
		return Task.where(
		"{0} Create Opportunity", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_OPPORTUNITY_USING_POST_ON_THE_OPPORTUNITY_CONTROLLER);
			}
		);
	}

	public static Performable addManualFormInstancesUsingPutOnTheApplicationController(String opportunityId, Object body){
		return Task.where(
		"{0} Add manual application forms", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("opportunityId", opportunityId).body(body).put(ADD_MANUAL_FORM_INSTANCES_USING_PUT_ON_THE_APPLICATION_CONTROLLER);
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



}
