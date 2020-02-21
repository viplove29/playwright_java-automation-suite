package com.vertafore.test.tasks.servicewrappers.policy;

import static com.vertafore.test.abilities.CallTitanApi.as;
import static net.serenitybdd.rest.SerenityRest.rest;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class UsePolicyServiceTo {

  private static final String GET_POLICIES_BY_IDS_USING_GET =
      "policies?filter=byIds{&pageSize,page,ids}";
  private static final String GET_POLICIES_BY_CONTEXTUAL_IDS_USING_GET =
      "policies?filter=byContextualIds{&pageSize,page,ids}";
  private static final String GET_POLICY_BY_ID_USING_GET = "policies/{id}";
  private static final String DELETE_POLICY_USING_DELETE = "policies/{id}";
  private static final String GET_POLICIES_BY_STATUSES_USING_GET =
      "policies?filter=byStatuses{&pageSize,page,statuses,customerId}";
  private static final String CREATE_POLICY_USING_POST = "policies";
  private static final String UPDATE_POLICY_VERSION_USING_PATCH =
      "policies/{policyId}/versions/{versionId}";
  private static final String GET_POLICY_VERSION_USING_GET =
      "policies/{policyId}/versions/{versionId}";
  private static final String GET_ALL_VERSIONS_BY_POLICY_ID_USING_GET = "policies/{id}/versions";
  private static final String SEARCH_POLICIES_USING_GET = "policies";
  private static final String GET_CARRIERS_FOR_ALL_POLICIES_USING_GET = "policies/carriers";
  private static final String CREATE_POLICY_VERSION_USING_POST = "policies/{policyId}/versions";

  public static Performable getPoliciesByIdsUsingGet(
      String pageSize, String page, String ids, String filter) {
    return Task.where(
        "{0} Get policies by IDs.",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("ids", ids)
              .queryParam("filter", filter)
              .get(as(actor).toEndpoint(GET_POLICIES_BY_IDS_USING_GET));
        });
  }

  public static Performable getPoliciesByContextualIdsUsingGet(
      String pageSize, String page, String ids, String filter) {
    return Task.where(
        "{0} Get policies by contextual IDs.",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("ids", ids)
              .queryParam("filter", filter)
              .get(as(actor).toEndpoint(GET_POLICIES_BY_CONTEXTUAL_IDS_USING_GET));
        });
  }

  public static Performable getPolicyByIdUsingGet(String id) {
    return Task.where(
        "{0} Get a specific policy.",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("id", id)
              .get(as(actor).toEndpoint(GET_POLICY_BY_ID_USING_GET));
        });
  }

  public static Performable deletePolicyUsingDelete(String id) {
    return Task.where(
        "{0} Delete a policy.",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("id", id)
              .delete(as(actor).toEndpoint(DELETE_POLICY_USING_DELETE));
        });
  }

  public static Performable getPoliciesByStatusesUsingGet(
      String pageSize, String page, String statuses, String customerId, String filter) {
    return Task.where(
        "{0} Get policies by statuses.",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("statuses", statuses)
              .queryParam("customerId", customerId)
              .queryParam("filter", filter)
              .get(as(actor).toEndpoint(GET_POLICIES_BY_STATUSES_USING_GET));
        });
  }

  public static Performable createPolicyUsingPost(Object body) {
    return Task.where(
        "{0} Create a policy.",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .body(body)
              .post(as(actor).toEndpoint(CREATE_POLICY_USING_POST));
        });
  }

  public static Performable updatePolicyVersionUsingPatch(
      Object body, String policyId, String versionId) {
    return Task.where(
        "{0} Update a policy version",
        actor -> {
          rest()
              .with()
              .contentType("application/json-patch+json")
              .body(body)
              .pathParam("policyId", policyId)
              .pathParam("versionId", versionId)
              .patch(as(actor).toEndpoint(UPDATE_POLICY_VERSION_USING_PATCH));
        });
  }

  public static Performable getPolicyVersionUsingGet(String policyId, String versionId) {
    return Task.where(
        "{0} Get a policy version.",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("policyId", policyId)
              .pathParam("versionId", versionId)
              .get(as(actor).toEndpoint(GET_POLICY_VERSION_USING_GET));
        });
  }

  public static Performable getAllVersionsByPolicyIdUsingGet(
      String id, String pageSize, String page) {
    return Task.where(
        "{0} Get policy versions for a policy.",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("id", id)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get(as(actor).toEndpoint(GET_ALL_VERSIONS_BY_POLICY_ID_USING_GET));
        });
  }

  public static Performable searchPoliciesUsingGet(
      String hideCancelled,
      String sortField,
      String isDescending,
      String searchTerm,
      String carrierId,
      String customerId,
      String pageSize,
      String page) {
    return Task.where(
        "{0} Get policies.",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .queryParam("hideCancelled", hideCancelled)
              .queryParam("sortField", sortField)
              .queryParam("isDescending", isDescending)
              .queryParam("searchTerm", searchTerm)
              .queryParam("carrierId", carrierId)
              .queryParam("customerId", customerId)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get(as(actor).toEndpoint(SEARCH_POLICIES_USING_GET));
        });
  }

  public static Performable getCarriersForAllPoliciesUsingGet(String pageSize, String page) {
    return Task.where(
        "{0} Get carriers for policies.",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get(as(actor).toEndpoint(GET_CARRIERS_FOR_ALL_POLICIES_USING_GET));
        });
  }

  public static Performable createPolicyVersionUsingPost(String policyId, Object body) {
    return Task.where(
        "{0} Create policy version",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("policyId", policyId)
              .body(body)
              .post(as(actor).toEndpoint(CREATE_POLICY_VERSION_USING_POST));
        });
  }
}
