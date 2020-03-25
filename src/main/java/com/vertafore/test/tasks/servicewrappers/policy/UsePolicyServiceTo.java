package com.vertafore.test.tasks.servicewrappers.policy;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import com.vertafore.test.abilities.CallTitanApi;

/**
* This class was automatically generated on 2020/03/25 13:40:49

*/
public class UsePolicyServiceTo {

	private static final String THIS_SERVICE = "policy";
	private static final String GET_POLICIES_BY_IDS_USING_GET_ON_THE_POLICY_CONTROLLER = "policies?filter=byIds";
	private static final String GET_POLICIES_BY_CONTEXTUAL_IDS_USING_GET_ON_THE_POLICY_CONTROLLER = "policies?filter=byContextualIds";
	private static final String GET_POLICY_BY_ID_USING_GET_ON_THE_POLICY_CONTROLLER = "policies/{id}";
	private static final String DELETE_POLICY_USING_DELETE_ON_THE_POLICY_CONTROLLER = "policies/{id}";
	private static final String GET_POLICIES_BY_STATUSES_USING_GET_ON_THE_POLICY_CONTROLLER = "policies?filter=byStatuses";
	private static final String CREATE_POLICY_USING_POST_ON_THE_POLICY_CONTROLLER = "policies";
	private static final String UPDATE_POLICY_VERSION_USING_PATCH_ON_THE_POLICY_VERSION_CONTROLLER = "policies/{policyId}/versions/{versionId}";
	private static final String GET_POLICY_VERSION_USING_GET_ON_THE_POLICY_VERSION_CONTROLLER = "policies/{policyId}/versions/{versionId}";
	private static final String GET_ALL_VERSIONS_BY_POLICY_ID_USING_GET_ON_THE_POLICY_VERSION_CONTROLLER = "policies/{id}/versions";
	private static final String SEARCH_POLICIES_USING_GET_ON_THE_POLICY_CONTROLLER = "policies";
	private static final String GET_CARRIERS_FOR_ALL_POLICIES_USING_GET_ON_THE_POLICY_CONTROLLER = "policies/carriers";
	private static final String CREATE_POLICY_VERSION_USING_POST_ON_THE_POLICY_VERSION_CONTROLLER = "policies/{policyId}/versions";

	public static Performable getPoliciesByIdsUsingGetOnThePolicyController(String pageSize, String page, String ids, String filter){
		return Task.where(
		"{0} Get policies by IDs.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("ids", ids).queryParam("filter", filter).get(GET_POLICIES_BY_IDS_USING_GET_ON_THE_POLICY_CONTROLLER);
			}
		);
	}

	public static Performable getPoliciesByContextualIdsUsingGetOnThePolicyController(String pageSize, String page, String ids, String filter){
		return Task.where(
		"{0} Get policies by contextual IDs.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("ids", ids).queryParam("filter", filter).get(GET_POLICIES_BY_CONTEXTUAL_IDS_USING_GET_ON_THE_POLICY_CONTROLLER);
			}
		);
	}

	public static Performable getPolicyByIdUsingGetOnThePolicyController(String id){
		return Task.where(
		"{0} Get a specific policy.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_POLICY_BY_ID_USING_GET_ON_THE_POLICY_CONTROLLER);
			}
		);
	}

	public static Performable deletePolicyUsingDeleteOnThePolicyController(String id){
		return Task.where(
		"{0} Delete a policy.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_POLICY_USING_DELETE_ON_THE_POLICY_CONTROLLER);
			}
		);
	}

	public static Performable getPoliciesByStatusesUsingGetOnThePolicyController(String pageSize, String page, String statuses, String customerId, String filter){
		return Task.where(
		"{0} Get policies by statuses.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("statuses", statuses).queryParam("customerId", customerId).queryParam("filter", filter).get(GET_POLICIES_BY_STATUSES_USING_GET_ON_THE_POLICY_CONTROLLER);
			}
		);
	}

	public static Performable createPolicyUsingPostOnThePolicyController(Object body){
		return Task.where(
		"{0} Create a policy.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_POLICY_USING_POST_ON_THE_POLICY_CONTROLLER);
			}
		);
	}

	public static Performable updatePolicyVersionUsingPatchOnThePolicyVersionController(Object body, String policyId, String versionId){
		return Task.where(
		"{0} Update a policy version", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").body(body).pathParam("policyId", policyId).pathParam("versionId", versionId).patch(UPDATE_POLICY_VERSION_USING_PATCH_ON_THE_POLICY_VERSION_CONTROLLER);
			}
		);
	}

	public static Performable getPolicyVersionUsingGetOnThePolicyVersionController(String policyId, String versionId){
		return Task.where(
		"{0} Get a policy version.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("policyId", policyId).pathParam("versionId", versionId).get(GET_POLICY_VERSION_USING_GET_ON_THE_POLICY_VERSION_CONTROLLER);
			}
		);
	}

	public static Performable getAllVersionsByPolicyIdUsingGetOnThePolicyVersionController(String id, String pageSize, String page){
		return Task.where(
		"{0} Get policy versions for a policy.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).queryParam("pageSize", pageSize).queryParam("page", page).get(GET_ALL_VERSIONS_BY_POLICY_ID_USING_GET_ON_THE_POLICY_VERSION_CONTROLLER);
			}
		);
	}

	public static Performable searchPoliciesUsingGetOnThePolicyController(String hideCancelled, String sortField, String isDescending, String searchTerm, String carrierId, String customerId, String pageSize, String page){
		return Task.where(
		"{0} Get policies.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("hideCancelled", hideCancelled).queryParam("sortField", sortField).queryParam("isDescending", isDescending).queryParam("searchTerm", searchTerm).queryParam("carrierId", carrierId).queryParam("customerId", customerId).queryParam("pageSize", pageSize).queryParam("page", page).get(SEARCH_POLICIES_USING_GET_ON_THE_POLICY_CONTROLLER);
			}
		);
	}

	public static Performable getCarriersForAllPoliciesUsingGetOnThePolicyController(String pageSize, String page){
		return Task.where(
		"{0} Get carriers for policies.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(GET_CARRIERS_FOR_ALL_POLICIES_USING_GET_ON_THE_POLICY_CONTROLLER);
			}
		);
	}

	public static Performable createPolicyVersionUsingPostOnThePolicyVersionController(String policyId, Object body){
		return Task.where(
		"{0} Create policy version", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("policyId", policyId).body(body).post(CREATE_POLICY_VERSION_USING_POST_ON_THE_POLICY_VERSION_CONTROLLER);
			}
		);
	}



}
