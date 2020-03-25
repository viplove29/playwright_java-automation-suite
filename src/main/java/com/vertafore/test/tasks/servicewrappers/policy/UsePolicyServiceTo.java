package com.vertafore.test.tasks.servicewrappers.policy;

import net.serenitybdd.screenplay.Performable;
import com.vertafore.test.abilities.CallTitanApi;
import net.serenitybdd.screenplay.Task;

/**
* This class was automatically generated on 2020/03/25 14:48:17

*/
public class UsePolicyServiceTo {

	private static final String THIS_SERVICE = "policy";

	public static Performable updatePolicyVersionUsingPatchOnThePolicyVersionController(Object body, String policyId, String versionId){
		return Task.where(
		"{0} Update a policy version", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").body(body).pathParam("policyId", policyId).pathParam("versionId", versionId).patch("policies/{policyId}/versions/{versionId}");
			}
		);
	}

	public static Performable createPolicyUsingPostOnThePolicyController(Object body){
		return Task.where(
		"{0} Create a policy.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("policies");
			}
		);
	}

	public static Performable getPolicyByIdUsingGetOnThePolicyController(String id){
		return Task.where(
		"{0} Get a specific policy.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get("policies/{id}");
			}
		);
	}

	public static Performable searchPoliciesUsingGetOnThePolicyController(String hideCancelled, String sortField, String isDescending, String searchTerm, String carrierId, String customerId, String pageSize, String page){
		return Task.where(
		"{0} Get policies.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("hideCancelled", hideCancelled).queryParam("sortField", sortField).queryParam("isDescending", isDescending).queryParam("searchTerm", searchTerm).queryParam("carrierId", carrierId).queryParam("customerId", customerId).queryParam("pageSize", pageSize).queryParam("page", page).get("policies");
			}
		);
	}

	public static Performable getPoliciesByIdsUsingGetOnThePolicyController(String pageSize, String page, String ids, String filter){
		return Task.where(
		"{0} Get policies by IDs.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("ids", ids).queryParam("filter", filter).get("policies?filter=byIds");
			}
		);
	}

	public static Performable getCarriersForAllPoliciesUsingGetOnThePolicyController(String pageSize, String page){
		return Task.where(
		"{0} Get carriers for policies.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get("policies/carriers");
			}
		);
	}

	public static Performable deletePolicyUsingDeleteOnThePolicyController(String id){
		return Task.where(
		"{0} Delete a policy.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete("policies/{id}");
			}
		);
	}

	public static Performable getPoliciesByContextualIdsUsingGetOnThePolicyController(String pageSize, String page, String ids, String filter){
		return Task.where(
		"{0} Get policies by contextual IDs.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("ids", ids).queryParam("filter", filter).get("policies?filter=byContextualIds");
			}
		);
	}

	public static Performable getPoliciesByStatusesUsingGetOnThePolicyController(String pageSize, String page, String statuses, String customerId, String filter){
		return Task.where(
		"{0} Get policies by statuses.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("statuses", statuses).queryParam("customerId", customerId).queryParam("filter", filter).get("policies?filter=byStatuses");
			}
		);
	}

	public static Performable getPolicyVersionUsingGetOnThePolicyVersionController(String policyId, String versionId){
		return Task.where(
		"{0} Get a policy version.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("policyId", policyId).pathParam("versionId", versionId).get("policies/{policyId}/versions/{versionId}");
			}
		);
	}

	public static Performable createPolicyVersionUsingPostOnThePolicyVersionController(String policyId, Object body){
		return Task.where(
		"{0} Create policy version", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("policyId", policyId).body(body).post("policies/{policyId}/versions");
			}
		);
	}

	public static Performable getAllVersionsByPolicyIdUsingGetOnThePolicyVersionController(String id, String pageSize, String page){
		return Task.where(
		"{0} Get policy versions for a policy.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).queryParam("pageSize", pageSize).queryParam("page", page).get("policies/{id}/versions");
			}
		);
	}



}
