package com.vertafore.test.tasks.servicewrappers.opportunity;

import net.serenitybdd.screenplay.Performable;
import com.vertafore.test.abilities.CallTitanApi;
import net.serenitybdd.screenplay.Task;

/**
* This class was automatically generated on 2020/03/25 14:48:16

*/
public class UseOpportunityServiceTo {

	private static final String THIS_SERVICE = "opportunity";

	public static Performable patchSubmissionUsingPatchOnTheSubmissionController(Object body, String submissionId){
		return Task.where(
		"{0} Patch Submission", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").body(body).pathParam("submissionId", submissionId).patch("submissions/{submissionId}");
			}
		);
	}

	public static Performable searchSubmissionsUsingGetOnTheSubmissionController(String pageSize, String page, String isDescending, String searchTerm, String sortField, String type, String hideClosed, String filter){
		return Task.where(
		"{0} Search Submissions", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("isDescending", isDescending).queryParam("searchTerm", searchTerm).queryParam("sortField", sortField).queryParam("type", type).queryParam("hideClosed", hideClosed).queryParam("filter", filter).get("submissions?filter=bySearchTerm");
			}
		);
	}

	public static Performable deleteByCarrierIdUsingDeleteOnTheCarriersubmissionController(String submissionId, String carrierId){
		return Task.where(
		"{0} Delete Carrier from carrier submission (all LOBs included)", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("submissionId", submissionId).pathParam("carrierId", carrierId).delete("submissions/{submissionId}/carrier-submissions/{carrierId}");
			}
		);
	}

	public static Performable deleteDefaultApplicationFormUsingDeleteOnTheDefaultapplicationformController(String productKey){
		return Task.where(
		"{0} Delete Default Application Form", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("productKey", productKey).delete("default-application-forms/{productKey}");
			}
		);
	}

	public static Performable getCarrierSubmissionsBySubmissionIdUsingGetOnTheCarriersubmissionController(String submissionId, String pageSize, String page){
		return Task.where(
		"{0} Get all carrier submissions for a given submission", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("submissionId", submissionId).queryParam("pageSize", pageSize).queryParam("page", page).get("submissions/{submissionId}/carrier-submissions");
			}
		);
	}

	public static Performable getApplicationsByOpportunityUsingGetOnTheApplicationController(String opportunityId, String pageSize, String page){
		return Task.where(
		"{0} Get all applications for a given opportunity", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("opportunityId", opportunityId).queryParam("pageSize", pageSize).queryParam("page", page).get("opportunities/{opportunityId}/applications");
			}
		);
	}

	public static Performable upsertDefaultApplicationFormUsingPutOnTheDefaultapplicationformController(Object body, String productKey){
		return Task.where(
		"{0} Upsert Default Application Form", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).pathParam("productKey", productKey).put("default-application-forms/{productKey}");
			}
		);
	}

	public static Performable patchOpportunityUsingPatchOnTheOpportunityController(Object body, String opportunityId){
		return Task.where(
		"{0} Patch Opportunity", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").body(body).pathParam("opportunityId", opportunityId).patch("opportunities/{opportunityId}");
			}
		);
	}

	public static Performable addManualFormInstancesUsingPutOnTheApplicationController(String opportunityId, Object body){
		return Task.where(
		"{0} Add manual application forms", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("opportunityId", opportunityId).body(body).put("opportunities/{opportunityId}/applications/manual-forms");
			}
		);
	}

	public static Performable createOpportunityUsingPostOnTheOpportunityController(Object body){
		return Task.where(
		"{0} Create Opportunity", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("opportunities");
			}
		);
	}

	public static Performable deleteQuoteUsingDeleteOnTheQuoteController(String applicationId, String quoteId){
		return Task.where(
		"{0} Delete Quote", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("applicationId", applicationId).pathParam("quoteId", quoteId).delete("applications/{applicationId}/quotes/{quoteId}");
			}
		);
	}

	public static Performable deleteByIdUsingDeleteOnTheCarriersubmissionController(String submissionId, String carrierSubmissionId, String filter){
		return Task.where(
		"{0} Delete LOB (by ID) from carrier submission (by ID)", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("submissionId", submissionId).pathParam("carrierSubmissionId", carrierSubmissionId).queryParam("filter", filter).delete("submissions/{submissionId}/carrier-submissions/");
			}
		);
	}

	public static Performable patchQuoteUsingPatchOnTheQuoteController(Object body, String applicationId, String quoteId){
		return Task.where(
		"{0} Patch Quote", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").body(body).pathParam("applicationId", applicationId).pathParam("quoteId", quoteId).patch("applications/{applicationId}/quotes/{quoteId}");
			}
		);
	}

	public static Performable deleteManualFormInstanceUsingDeleteOnTheApplicationController(String opportunityId, String applicationId, String formInstanceId){
		return Task.where(
		"{0} Delete manual application form", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("opportunityId", opportunityId).pathParam("applicationId", applicationId).pathParam("formInstanceId", formInstanceId).delete("opportunities/{opportunityId}/applications/{applicationId}/manual-forms/{formInstanceId}");
			}
		);
	}

	public static Performable getQuoteByIdUsingGetOnTheQuoteController(String applicationId, String quoteId){
		return Task.where(
		"{0} Get Quote", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("applicationId", applicationId).pathParam("quoteId", quoteId).get("applications/{applicationId}/quotes/{quoteId}");
			}
		);
	}

	public static Performable createQuoteUsingPostOnTheQuoteController(Object body, String applicationId){
		return Task.where(
		"{0} Create Quote", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).pathParam("applicationId", applicationId).post("applications/{applicationId}/quotes");
			}
		);
	}

	public static Performable deleteSubmissionUsingDeleteOnTheSubmissionController(String submissionId){
		return Task.where(
		"{0} Delete Submission", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("submissionId", submissionId).delete("submissions/{submissionId}");
			}
		);
	}

	public static Performable replaceSubmissionUsingPutOnTheSubmissionController(Object body, String submissionId){
		return Task.where(
		"{0} Replace Submission", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).pathParam("submissionId", submissionId).put("submissions/{submissionId}");
			}
		);
	}

	public static Performable getDefaultApplicationFormUsingGetOnTheDefaultapplicationformController(String productKey){
		return Task.where(
		"{0} Get Default Application Form", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("productKey", productKey).get("default-application-forms/{productKey}");
			}
		);
	}

	public static Performable searchClosedOpportunitiesByCustomerUsingGetOnTheOpportunityController(String pageSize, String page, String isDescending, String searchTerm, String sortField, String status, String type, String customerId, String filter){
		return Task.where(
		"{0} Search Closed Opportunities", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("isDescending", isDescending).queryParam("searchTerm", searchTerm).queryParam("sortField", sortField).queryParam("status", status).queryParam("type", type).queryParam("customerId", customerId).queryParam("filter", filter).get("opportunities?filter=byClosedOpportunities");
			}
		);
	}

	public static Performable addCarrierIdsUsingPutOnTheApplicationController(String opportunityId, Object body){
		return Task.where(
		"{0} Add carrier ids", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("opportunityId", opportunityId).body(body).put("opportunities/{opportunityId}/applications/carrier-ids");
			}
		);
	}

	public static Performable replaceOpportunityUsingPutOnTheOpportunityController(Object body, String opportunityId){
		return Task.where(
		"{0} Replace Opportunity", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).pathParam("opportunityId", opportunityId).put("opportunities/{opportunityId}");
			}
		);
	}

	public static Performable deleteOpportunityUsingDeleteOnTheOpportunityController(String opportunityId){
		return Task.where(
		"{0} Delete Opportunity", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("opportunityId", opportunityId).delete("opportunities/{opportunityId}");
			}
		);
	}

	public static Performable createSubmissionUsingPostOnTheSubmissionController(Object body){
		return Task.where(
		"{0} Create Submission", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("submissions");
			}
		);
	}

	public static Performable getSubmissionByIdUsingGetOnTheSubmissionController(String submissionId){
		return Task.where(
		"{0} Get Submission", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("submissionId", submissionId).get("submissions/{submissionId}");
			}
		);
	}

	public static Performable searchQuotesByApplicationIdUsingGetOnTheQuoteController(String pageSize, String page, String applicationId, String filter){
		return Task.where(
		"{0} Search Quotes", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).pathParam("applicationId", applicationId).queryParam("filter", filter).get("applications/{applicationId}/quotes?filter=byApplicationId");
			}
		);
	}

	public static Performable replaceQuoteUsingPutOnTheQuoteController(Object body, String quoteId){
		return Task.where(
		"{0} Replace Quote", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).pathParam("quoteId", quoteId).put("quotes/{quoteId}");
			}
		);
	}

	public static Performable moveApplicationToOpportunityUsingPutOnTheApplicationController(String newOpportunityId, String existingApplicationId){
		return Task.where(
		"{0} Move an application to opportunity.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("newOpportunityId", newOpportunityId).pathParam("existingApplicationId", existingApplicationId).put("opportunities/{newOpportunityId}/applications/{existingApplicationId}");
			}
		);
	}

	public static Performable patchCarrierSubmissionUsingPatchOnTheCarriersubmissionController(Object body, String carrierSubmissionId){
		return Task.where(
		"{0} Patch carrier submission", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").body(body).pathParam("carrierSubmissionId", carrierSubmissionId).patch("carrier-submissions/{carrierSubmissionId}");
			}
		);
	}

	public static Performable getOpportunityByIdUsingGetOnTheOpportunityController(String opportunityId){
		return Task.where(
		"{0} Get Opportunity", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("opportunityId", opportunityId).get("opportunities/{opportunityId}");
			}
		);
	}

	public static Performable searchOpenOpportunitiesByCustomerUsingGetOnTheOpportunityController(String pageSize, String page, String isDescending, String searchTerm, String sortField, String status, String type, String customerId, String filter){
		return Task.where(
		"{0} Search Open Opportunities", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("isDescending", isDescending).queryParam("searchTerm", searchTerm).queryParam("sortField", sortField).queryParam("status", status).queryParam("type", type).queryParam("customerId", customerId).queryParam("filter", filter).get("opportunities?filter=byOpenOpportunities");
			}
		);
	}

	public static Performable getApplicationByIdUsingGetOnTheApplicationController(String opportunityId, String applicationId){
		return Task.where(
		"{0} Get Application", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("opportunityId", opportunityId).pathParam("applicationId", applicationId).get("opportunities/{opportunityId}/applications/{applicationId}");
			}
		);
	}



}
