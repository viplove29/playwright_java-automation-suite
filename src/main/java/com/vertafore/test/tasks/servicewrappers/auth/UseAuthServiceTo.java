package com.vertafore.test.tasks.servicewrappers.auth;

import com.vertafore.test.abilities.CallTitanApi;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class UseAuthServiceTo {

  private static final String THIS_SERVICE = "auth";
  private static final String GET_PRODUCT_USING_GET_ON_THE_PRODUCTS_CONTROLLER =
      "products/{productIdToRetrieve}";
  private static final String GET_PRODUCT_BY_NAME_USING_GET_ON_THE_PRODUCTS_CONTROLLER =
      "products?filter=byName";
  private static final String GET_USERS_BY_CONTEXT_USING_GET_ON_THE_USERS_CONTROLLER =
      "users?filter=byScope";
  private static final String FIND_ROLES_FOR_USER_USING_GET_ON_THE_ROLE_ASSIGNMENTS_CONTROLLER =
      "role-assignments?filter=byUserId";
  private static final String GET_REFRESH_TOKEN_USING_POST_ON_THE_IDENTITY_PROVIDER_CONTROLLER =
      "/auth/v1/token/refresh";
  private static final String CREATE_ROLE_USING_POST_ON_THE_ROLES_CONTROLLER = "roles";
  private static final String
      FIND_AUTH_USER_MEMBERSHIPS_IN_AUTH_GROUP_USING_GET_ON_THE_GROUP_MEMBERSHIP_CONTROLLER =
          "groups/{groupId}/users";
  private static final String DELETE_ENTITY_USING_DELETE_ON_THE_ENTITIES_CONTROLLER =
      "entities/{entityIdToDelete}";
  private static final String BULK_DELETE_USER_USING_DELETE_ON_THE_USERS_CONTROLLER = "users/bulk";
  private static final String GRANT_ROLE_USING_POST_ON_THE_AUTHORIZATION_GROUPS_CONTROLLER =
      "groups/{groupId}/roles/{roleId}";
  private static final String REVOKE_ROLE_FROM_USING_DELETE_ON_THE_AUTHORIZATION_GROUPS_CONTROLLER =
      "groups/{groupId}/roles/{roleId}";
  private static final String GET_PRODUCTS_BY_IDS_USING_GET_ON_THE_PRODUCTS_CONTROLLER =
      "products?filter=byIds";
  private static final String GET_CONTEXTUAL_AUTH_USING_GET_ON_THE_AUTHORIZATION_CONTROLLER =
      "auth-info/services/{serviceName}";
  private static final String GET_ACCESS_TOKEN_USING_POST_ON_THE_IDENTITY_PROVIDER_CONTROLLER =
      "/auth/v1/token";
  private static final String GET_USING_GET_ON_THE_AUTHENTICATION_SETTINGS_CONTROLLER =
      "authenticationsettings";
  private static final String CREATE_OR_UPDATE_USING_PUT_ON_THE_AUTHENTICATION_SETTINGS_CONTROLLER =
      "authenticationsettings";
  private static final String ASSIGN_A_ROLE_TO_A_USER_ON_THE_ROLE_ASSIGNMENTS_CONTROLLER =
      "role-assignments";
  private static final String REVOKE_A_ROLE_FROM_A_USER_ON_THE_ROLE_ASSIGNMENTS_CONTROLLER =
      "role-assignments";
  private static final String CREATE_OR_UPDATE_TENANT_USING_PUT_ON_THE_TENANTS_CONTROLLER =
      "tenants/{tenantIdToModify}";
  private static final String GET_ENTITY_HIERARCHY_USING_GET_ON_THE_ENTITIES_CONTROLLER =
      "entities/{entityIdToRetrieve}";
  private static final String
      GET_ENTITY_IDS_FOR_ENTITLEMENT_USING_GET_ON_THE_AUTHORIZATION_CONTROLLER =
          "entities?filter=byEntitlement";
  private static final String CHANGE_PASSWORD_USING_PUT_ON_THE_AUTHENTICATION_CONTROLLER =
      "/auth/v1/change-password";
  private static final String CREATE_REALM_USING_POST_ON_THE_REALM_MANAGEMENT_CONTROLLER = "realms";
  private static final String CREATE_AUTH_GROUP_USING_POST_ON_THE_GROUPS_CONTROLLER = "groups";
  private static final String FIND_ROLE_BY_NAME_USING_GET_ON_THE_ROLES_CONTROLLER =
      "roles?filter=byName";
  private static final String FIND_ROLE_BY_IDS_USING_GET_ON_THE_ROLES_CONTROLLER =
      "roles?filter=byIds";
  private static final String CREATE_OR_UPDATE_ENTITY_USING_PUT_ON_THE_ENTITIES_CONTROLLER =
      "entities/{entityIdToWrite}";
  private static final String GET_REALM_USING_GET_ON_THE_REALM_MANAGEMENT_CONTROLLER =
      "realms/{name}";
  private static final String DELETE_REALM_USING_DELETE_ON_THE_REALM_MANAGEMENT_CONTROLLER =
      "realms/{name}";
  private static final String UPDATE_REALM_USING_PUT_ON_THE_REALM_MANAGEMENT_CONTROLLER =
      "realms/{name}";
  private static final String BULK_GRANT_ROLE_USING_POST_ON_THE_ROLE_ASSIGNMENTS_CONTROLLER =
      "role-assignments/bulk";
  private static final String DELETE_USER_USING_DELETE_ON_THE_USERS_CONTROLLER = "users/{userId}";
  private static final String GET_AUTH_GROUP_USING_GET_ON_THE_GROUPS_CONTROLLER =
      "groups/{groupId}";
  private static final String DELETE_AUTH_GROUP_USING_DELETE_ON_THE_GROUPS_CONTROLLER =
      "groups/{groupId}";
  private static final String CREATE_OR_UPDATE_AUTH_GROUP_USING_PUT_ON_THE_GROUPS_CONTROLLER =
      "groups/{groupId}";
  private static final String GET_TENANT_USING_GET_ON_THE_TENANTS_CONTROLLER =
      "tenants/{tenantIdToRetrieve}";
  private static final String GET_AUTHGROUPS_ON_THE_GROUPS_CONTROLLER = "groups";
  private static final String BULK_CREATE_USER_USING_POST_ON_THE_USERS_CONTROLLER = "users/bulk";
  private static final String DELETE_TENANT_USING_DELETE_ON_THE_TENANTS_CONTROLLER =
      "tenants/{tenantIdToDelete}";
  private static final String DEACTIVATE_USER_USING_POST_ON_THE_USERS_CONTROLLER =
      "users/{userId}/deactivate";
  private static final String GET_ROLE_LOOKUP_BY_ID_USING_GET_ON_THE_ROLES_CONTROLLER =
      "roles/{id}?lookupBy=id";
  private static final String FIND_SERVICEROLE_ON_THE_SERVICE_ROLES_CONTROLLER =
      "services/{serviceName}/roles/{name}";
  private static final String CREATE_PRODUCT_USING_PUT_ON_THE_PRODUCTS_CONTROLLER =
      "products/{productIdToCreate}";
  private static final String BULK_DEACTIVATE_USER_USING_POST_ON_THE_USERS_CONTROLLER =
      "users/deactivate/bulk";
  private static final String ACTIVATE_USER_USING_POST_ON_THE_USERS_CONTROLLER =
      "users/{userId}/activate";
  private static final String CREATE_ENTITY_USING_POST_ON_THE_ENTITIES_CONTROLLER = "entities";
  private static final String DELETE_AND_MIGRATE_ENTITY_USING_DELETE_ON_THE_ENTITIES_CONTROLLER =
      "entities/{entityIdToDelete}";
  private static final String CREATE_ENTITY_CONCORDANCE_USING_POST_ON_THE_REFERENCES_CONTROLLER =
      "references";
  private static final String DELETE_ENTITY_CONCORDANCE_USING_DELETE_ON_THE_REFERENCES_CONTROLLER =
      "references";
  private static final String CREATE_USER_USING_POST_ON_THE_USERS_CONTROLLER = "users";
  private static final String GET_ROLE_USING_GET_ON_THE_ROLES_CONTROLLER = "roles/{id}";
  private static final String DELETE_ROLE_USING_DELETE_ON_THE_ROLES_CONTROLLER = "roles/{id}";
  private static final String UPDATE_ROLE_USING_PUT_ON_THE_ROLES_CONTROLLER = "roles/{id}";
  private static final String PATCH_UPDATE_USER_USING_PATCH_ON_THE_USERS_CONTROLLER =
      "users/{userId}";
  private static final String GET_USER_USING_GET_ON_THE_USERS_CONTROLLER = "users/{userId}";
  private static final String UPDATE_USER_USING_PUT_ON_THE_USERS_CONTROLLER = "users/{userId}";
  private static final String ADD_AUTH_USER_USING_POST_ON_THE_GROUP_MEMBERSHIP_CONTROLLER =
      "groups/{groupId}/users/{userId}";
  private static final String
      REVOKE_AUTH_USER_FROM_USING_DELETE_ON_THE_GROUP_MEMBERSHIP_CONTROLLER =
          "groups/{groupId}/users/{userId}";
  private static final String BULK_ACTIVATE_USER_USING_POST_ON_THE_USERS_CONTROLLER =
      "users/activate/bulk";
  private static final String
      GET_ENTITY_CONCORDANCE_BY_ENTITY_IDS_USING_GET_ON_THE_REFERENCES_CONTROLLER = "references";
  private static final String GET_USER_SECRET_USING_GET_ON_THE_USERS_CONTROLLER =
      "users/{userId}/secret";
  private static final String GET_USER_BY_USERNAME_USING_GET_ON_THE_USERS_CONTROLLER = "users";
  private static final String
      FIND_ROLES_FOR_GROUP_USING_GET_ON_THE_AUTHORIZATION_GROUPS_CONTROLLER =
          "groups/{groupId}/roles";
  private static final String
      FIND_ALL_SERVICE_ROLES_FOR_SERVICE_USING_GET_ON_THE_SERVICE_ROLES_CONTROLLER =
          "services/{serviceName}/roles";
  private static final String SAVE_ALL_USING_PUT_ON_THE_SERVICE_ROLES_CONTROLLER =
      "services/{serviceName}/roles";
  private static final String GET_USERS_BY_IDS_USING_GET_ON_THE_USERS_CONTROLLER =
      "users?filter=byIds";
  private static final String
      GET_CONTEXTUAL_AUTH_PREVIEW_USING_GET_ON_THE_AUTHORIZATION_CONTROLLER =
          "auth-tree/services/{serviceName}";
  private static final String GET_ROLE_BY_NAME_USING_GET_ON_THE_ROLES_CONTROLLER =
      "roles/{name}?lookupBy=name";

  public static Performable getProductUsingGetOnTheProductsController(String productIdToRetrieve) {
    return Task.where(
        "{0} Get Product",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("productIdToRetrieve", productIdToRetrieve)
              .get(GET_PRODUCT_USING_GET_ON_THE_PRODUCTS_CONTROLLER);
        });
  }

  public static Performable getProductByNameUsingGetOnTheProductsController(
      String name, String filter) {
    return Task.where(
        "{0} Get Product",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("name", name)
              .queryParam("filter", filter)
              .get(GET_PRODUCT_BY_NAME_USING_GET_ON_THE_PRODUCTS_CONTROLLER);
        });
  }

  public static Performable getUsersByContextUsingGetOnTheUsersController(
      String scope,
      String userOrDisplayNameIncludes,
      String sortBy,
      String sortDirection,
      String activeFilter,
      String pageSize,
      String page,
      String filter) {
    return Task.where(
        "{0} Retrieve Users for a given Context",
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
              .queryParam("filter", filter)
              .get(GET_USERS_BY_CONTEXT_USING_GET_ON_THE_USERS_CONTROLLER);
        });
  }

  public static Performable findRolesForUserUsingGetOnTheRoleAssignmentsController(
      String userId, String pageSize, String page, String filter) {
    return Task.where(
        "{0} List Role Assignments for a User in a Context",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("userId", userId)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get(FIND_ROLES_FOR_USER_USING_GET_ON_THE_ROLE_ASSIGNMENTS_CONTROLLER);
        });
  }

  public static Performable getRefreshTokenUsingPostOnTheIdentityProviderController(Object body) {
    return Task.where(
        "{0} Refresh token",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post(GET_REFRESH_TOKEN_USING_POST_ON_THE_IDENTITY_PROVIDER_CONTROLLER);
        });
  }

  public static Performable createRoleUsingPostOnTheRolesController(Object body) {
    return Task.where(
        "{0} Create Role",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post(CREATE_ROLE_USING_POST_ON_THE_ROLES_CONTROLLER);
        });
  }

  public static Performable
      findAuthUserMembershipsInAuthGroupUsingGetOnTheGroupMembershipController(
          String groupId, String pageSize, String page) {
    return Task.where(
        "{0} List AuthUser membership for the specified AuthGroup in a Context",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("groupId", groupId)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get(
                  FIND_AUTH_USER_MEMBERSHIPS_IN_AUTH_GROUP_USING_GET_ON_THE_GROUP_MEMBERSHIP_CONTROLLER);
        });
  }

  public static Performable deleteEntityUsingDeleteOnTheEntitiesController(
      String entityIdToDelete, String requestParams) {
    return Task.where(
        "{0} Delete Entity",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("entityIdToDelete", entityIdToDelete)
              .queryParam("requestParams", requestParams)
              .delete(DELETE_ENTITY_USING_DELETE_ON_THE_ENTITIES_CONTROLLER);
        });
  }

  public static Performable bulkDeleteUserUsingDeleteOnTheUsersController(String userIds) {
    return Task.where(
        "{0} Delete multiple Users",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("userIds", userIds)
              .delete(BULK_DELETE_USER_USING_DELETE_ON_THE_USERS_CONTROLLER);
        });
  }

  public static Performable grantRoleUsingPostOnTheAuthorizationGroupsController(
      String groupId, String roleId, Object body) {
    return Task.where(
        "{0} Assign a Role to a Group",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("groupId", groupId)
              .pathParam("roleId", roleId)
              .body(body)
              .post(GRANT_ROLE_USING_POST_ON_THE_AUTHORIZATION_GROUPS_CONTROLLER);
        });
  }

  public static Performable revokeRoleFromUsingDeleteOnTheAuthorizationGroupsController(
      String groupId, String roleId, Object body) {
    return Task.where(
        "{0} Revoke a Role from a Group",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("groupId", groupId)
              .pathParam("roleId", roleId)
              .body(body)
              .delete(REVOKE_ROLE_FROM_USING_DELETE_ON_THE_AUTHORIZATION_GROUPS_CONTROLLER);
        });
  }

  public static Performable getProductsByIdsUsingGetOnTheProductsController(
      String ids, String pageSize, String page, String filter, String internal) {
    return Task.where(
        "{0} Retrieve Products by Ids",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("ids", ids)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .queryParam("internal", internal)
              .get(GET_PRODUCTS_BY_IDS_USING_GET_ON_THE_PRODUCTS_CONTROLLER);
        });
  }

  public static Performable getContextualAuthUsingGetOnTheAuthorizationController(
      String serviceName) {
    return Task.where(
        "{0} Get the authorization information associated with the context of this request.  The entitlements returned can be used to enforce security in other services",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("serviceName", serviceName)
              .get(GET_CONTEXTUAL_AUTH_USING_GET_ON_THE_AUTHORIZATION_CONTROLLER);
        });
  }

  public static Performable getAccessTokenUsingPostOnTheIdentityProviderController(Object body) {
    return Task.where(
        "{0} Get token",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post(GET_ACCESS_TOKEN_USING_POST_ON_THE_IDENTITY_PROVIDER_CONTROLLER);
        });
  }

  public static Performable getUsingGetOnTheAuthenticationSettingsController() {
    return Task.where(
        "{0} Get Authentication Settings and/or computed values for provided Context",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .get(GET_USING_GET_ON_THE_AUTHENTICATION_SETTINGS_CONTROLLER);
        });
  }

  public static Performable createOrUpdateUsingPutOnTheAuthenticationSettingsController(
      Object body) {
    return Task.where(
        "{0} Create or Update Authentication Settings for provided Context",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .put(CREATE_OR_UPDATE_USING_PUT_ON_THE_AUTHENTICATION_SETTINGS_CONTROLLER);
        });
  }

  public static Performable assignARoleToAUserOnTheRoleAssignmentsController(Object body) {
    return Task.where(
        "{0} Assign a Role to a User",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post(ASSIGN_A_ROLE_TO_A_USER_ON_THE_ROLE_ASSIGNMENTS_CONTROLLER);
        });
  }

  public static Performable revokeARoleFromAUserOnTheRoleAssignmentsController(Object body) {
    return Task.where(
        "{0} Revoke a Role from a User",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .delete(REVOKE_A_ROLE_FROM_A_USER_ON_THE_ROLE_ASSIGNMENTS_CONTROLLER);
        });
  }

  public static Performable createOrUpdateTenantUsingPutOnTheTenantsController(
      String tenantIdToModify, Object body) {
    return Task.where(
        "{0} Create or Update Tenant",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("tenantIdToModify", tenantIdToModify)
              .body(body)
              .put(CREATE_OR_UPDATE_TENANT_USING_PUT_ON_THE_TENANTS_CONTROLLER);
        });
  }

  public static Performable getEntityHierarchyUsingGetOnTheEntitiesController(
      String entityIdToRetrieve,
      String ancestor_levels,
      String ancestor_properties,
      String descendant_levels,
      String descendant_properties,
      String entity_type) {
    return Task.where(
        "{0} Get Entity Hierarchy",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("entityIdToRetrieve", entityIdToRetrieve)
              .queryParam("ancestor_levels", ancestor_levels)
              .queryParam("ancestor_properties", ancestor_properties)
              .queryParam("descendant_levels", descendant_levels)
              .queryParam("descendant_properties", descendant_properties)
              .queryParam("entity_type", entity_type)
              .get(GET_ENTITY_HIERARCHY_USING_GET_ON_THE_ENTITIES_CONTROLLER);
        });
  }

  public static Performable getEntityIdsForEntitlementUsingGetOnTheAuthorizationController(
      String entityIds, String entitlement, String userId, String filter) {
    return Task.where(
        "{0} Determine whether user has the entitlement at the given entities.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("entityIds", entityIds)
              .queryParam("entitlement", entitlement)
              .queryParam("userId", userId)
              .queryParam("filter", filter)
              .get(GET_ENTITY_IDS_FOR_ENTITLEMENT_USING_GET_ON_THE_AUTHORIZATION_CONTROLLER);
        });
  }

  public static Performable changePasswordUsingPutOnTheAuthenticationController(Object body) {
    return Task.where(
        "{0} Change a User's Password",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .put(CHANGE_PASSWORD_USING_PUT_ON_THE_AUTHENTICATION_CONTROLLER);
        });
  }

  public static Performable createRealmUsingPostOnTheRealmManagementController(Object body) {
    return Task.where(
        "{0} Create Realm",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post(CREATE_REALM_USING_POST_ON_THE_REALM_MANAGEMENT_CONTROLLER);
        });
  }

  public static Performable createAuthGroupUsingPostOnTheGroupsController(Object body) {
    return Task.where(
        "{0} Create AuthGroup",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post(CREATE_AUTH_GROUP_USING_POST_ON_THE_GROUPS_CONTROLLER);
        });
  }

  public static Performable findRoleByNameUsingGetOnTheRolesController(String name, String filter) {
    return Task.where(
        "{0} Get Role by Name",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("name", name)
              .queryParam("filter", filter)
              .get(FIND_ROLE_BY_NAME_USING_GET_ON_THE_ROLES_CONTROLLER);
        });
  }

  public static Performable findRoleByIdsUsingGetOnTheRolesController(
      String ids, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Retrieve lots of Roles by id",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("ids", ids)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get(FIND_ROLE_BY_IDS_USING_GET_ON_THE_ROLES_CONTROLLER);
        });
  }

  public static Performable createOrUpdateEntityUsingPutOnTheEntitiesController(
      String entityIdToWrite, Object body) {
    return Task.where(
        "{0} Create or update Entity",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("entityIdToWrite", entityIdToWrite)
              .body(body)
              .put(CREATE_OR_UPDATE_ENTITY_USING_PUT_ON_THE_ENTITIES_CONTROLLER);
        });
  }

  public static Performable getRealmUsingGetOnTheRealmManagementController(String name) {
    return Task.where(
        "{0} Get Realm",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("name", name)
              .get(GET_REALM_USING_GET_ON_THE_REALM_MANAGEMENT_CONTROLLER);
        });
  }

  public static Performable deleteRealmUsingDeleteOnTheRealmManagementController(String name) {
    return Task.where(
        "{0} Delete a Realm",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("name", name)
              .delete(DELETE_REALM_USING_DELETE_ON_THE_REALM_MANAGEMENT_CONTROLLER);
        });
  }

  public static Performable updateRealmUsingPutOnTheRealmManagementController(
      String name, Object body) {
    return Task.where(
        "{0} Replace Realm",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("name", name)
              .body(body)
              .put(UPDATE_REALM_USING_PUT_ON_THE_REALM_MANAGEMENT_CONTROLLER);
        });
  }

  public static Performable bulkGrantRoleUsingPostOnTheRoleAssignmentsController(Object body) {
    return Task.where(
        "{0} Assign a Role to multiple Users",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post(BULK_GRANT_ROLE_USING_POST_ON_THE_ROLE_ASSIGNMENTS_CONTROLLER);
        });
  }

  public static Performable deleteUserUsingDeleteOnTheUsersController(
      String userId, String cascade) {
    return Task.where(
        "{0} Delete a User",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("userId", userId)
              .queryParam("cascade", cascade)
              .delete(DELETE_USER_USING_DELETE_ON_THE_USERS_CONTROLLER);
        });
  }

  public static Performable getAuthGroupUsingGetOnTheGroupsController(String groupId) {
    return Task.where(
        "{0} Get AuthGroup",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("groupId", groupId)
              .get(GET_AUTH_GROUP_USING_GET_ON_THE_GROUPS_CONTROLLER);
        });
  }

  public static Performable deleteAuthGroupUsingDeleteOnTheGroupsController(String groupId) {
    return Task.where(
        "{0} Delete AuthGroup",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("groupId", groupId)
              .delete(DELETE_AUTH_GROUP_USING_DELETE_ON_THE_GROUPS_CONTROLLER);
        });
  }

  public static Performable createOrUpdateAuthGroupUsingPutOnTheGroupsController(
      String groupId, Object body) {
    return Task.where(
        "{0} Update or Create AuthGroup",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("groupId", groupId)
              .body(body)
              .put(CREATE_OR_UPDATE_AUTH_GROUP_USING_PUT_ON_THE_GROUPS_CONTROLLER);
        });
  }

  public static Performable getTenantUsingGetOnTheTenantsController(String tenantIdToRetrieve) {
    return Task.where(
        "{0} Get Tenant",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("tenantIdToRetrieve", tenantIdToRetrieve)
              .get(GET_TENANT_USING_GET_ON_THE_TENANTS_CONTROLLER);
        });
  }

  public static Performable getAuthgroupsOnTheGroupsController(String pageSize, String page) {
    return Task.where(
        "{0} Get AuthGroups",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get(GET_AUTHGROUPS_ON_THE_GROUPS_CONTROLLER);
        });
  }

  public static Performable bulkCreateUserUsingPostOnTheUsersController(Object body) {
    return Task.where(
        "{0} Create bulk Users",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post(BULK_CREATE_USER_USING_POST_ON_THE_USERS_CONTROLLER);
        });
  }

  public static Performable deleteTenantUsingDeleteOnTheTenantsController(String tenantIdToDelete) {
    return Task.where(
        "{0} Delete Tenant",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("tenantIdToDelete", tenantIdToDelete)
              .delete(DELETE_TENANT_USING_DELETE_ON_THE_TENANTS_CONTROLLER);
        });
  }

  public static Performable deactivateUserUsingPostOnTheUsersController(String userId) {
    return Task.where(
        "{0} Deactivate Users",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("userId", userId)
              .post(DEACTIVATE_USER_USING_POST_ON_THE_USERS_CONTROLLER);
        });
  }

  public static Performable getRoleLookupByIdUsingGetOnTheRolesController(
      String id, String lookupBy) {
    return Task.where(
        "{0} Get Role By ID Lookup By ID",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .queryParam("lookupBy", lookupBy)
              .get(GET_ROLE_LOOKUP_BY_ID_USING_GET_ON_THE_ROLES_CONTROLLER);
        });
  }

  public static Performable findServiceroleOnTheServiceRolesController(
      String serviceName, String name) {
    return Task.where(
        "{0} Find ServiceRole",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("serviceName", serviceName)
              .pathParam("name", name)
              .get(FIND_SERVICEROLE_ON_THE_SERVICE_ROLES_CONTROLLER);
        });
  }

  public static Performable createProductUsingPutOnTheProductsController(
      String productIdToCreate, Object body) {
    return Task.where(
        "{0} Create Product",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("productIdToCreate", productIdToCreate)
              .body(body)
              .put(CREATE_PRODUCT_USING_PUT_ON_THE_PRODUCTS_CONTROLLER);
        });
  }

  public static Performable bulkDeactivateUserUsingPostOnTheUsersController(String userIds) {
    return Task.where(
        "{0} Deactivate a User",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("userIds", userIds)
              .post(BULK_DEACTIVATE_USER_USING_POST_ON_THE_USERS_CONTROLLER);
        });
  }

  public static Performable activateUserUsingPostOnTheUsersController(String userId) {
    return Task.where(
        "{0} Activate a User",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("userId", userId)
              .post(ACTIVATE_USER_USING_POST_ON_THE_USERS_CONTROLLER);
        });
  }

  public static Performable createEntityUsingPostOnTheEntitiesController(Object body) {
    return Task.where(
        "{0} Create Entity",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post(CREATE_ENTITY_USING_POST_ON_THE_ENTITIES_CONTROLLER);
        });
  }

  public static Performable deleteAndMigrateEntityUsingDeleteOnTheEntitiesController(
      String entityIdToDelete, String migrateTo) {
    return Task.where(
        "{0} Delete And Migrate Entity",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("entityIdToDelete", entityIdToDelete)
              .queryParam("migrateTo", migrateTo)
              .delete(DELETE_AND_MIGRATE_ENTITY_USING_DELETE_ON_THE_ENTITIES_CONTROLLER);
        });
  }

  public static Performable createEntityConcordanceUsingPostOnTheReferencesController(Object body) {
    return Task.where(
        "{0} Create EntityConcordance",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post(CREATE_ENTITY_CONCORDANCE_USING_POST_ON_THE_REFERENCES_CONTROLLER);
        });
  }

  public static Performable deleteEntityConcordanceUsingDeleteOnTheReferencesController(
      Object body) {
    return Task.where(
        "{0} Delete EntityConcordance",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .delete(DELETE_ENTITY_CONCORDANCE_USING_DELETE_ON_THE_REFERENCES_CONTROLLER);
        });
  }

  public static Performable createUserUsingPostOnTheUsersController(Object body) {
    return Task.where(
        "{0} Create a User",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post(CREATE_USER_USING_POST_ON_THE_USERS_CONTROLLER);
        });
  }

  public static Performable getRoleUsingGetOnTheRolesController(String id) {
    return Task.where(
        "{0} Get Role By ID",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get(GET_ROLE_USING_GET_ON_THE_ROLES_CONTROLLER);
        });
  }

  public static Performable deleteRoleUsingDeleteOnTheRolesController(String id) {
    return Task.where(
        "{0} Delete a Role",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .delete(DELETE_ROLE_USING_DELETE_ON_THE_ROLES_CONTROLLER);
        });
  }

  public static Performable updateRoleUsingPutOnTheRolesController(String id, Object body) {
    return Task.where(
        "{0} Replace Role",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .body(body)
              .put(UPDATE_ROLE_USING_PUT_ON_THE_ROLES_CONTROLLER);
        });
  }

  public static Performable patchUpdateUserUsingPatchOnTheUsersController(
      String userId, Object body) {
    return Task.where(
        "{0} Patch Auth User",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json-patch+json")
              .pathParam("userId", userId)
              .body(body)
              .patch(PATCH_UPDATE_USER_USING_PATCH_ON_THE_USERS_CONTROLLER);
        });
  }

  public static Performable getUserUsingGetOnTheUsersController(String userId) {
    return Task.where(
        "{0} Get a User",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("userId", userId)
              .get(GET_USER_USING_GET_ON_THE_USERS_CONTROLLER);
        });
  }

  public static Performable updateUserUsingPutOnTheUsersController(Object body, String userId) {
    return Task.where(
        "{0} Update a User",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .pathParam("userId", userId)
              .put(UPDATE_USER_USING_PUT_ON_THE_USERS_CONTROLLER);
        });
  }

  public static Performable addAuthUserUsingPostOnTheGroupMembershipController(
      String userId, String groupId) {
    return Task.where(
        "{0} Assign an AuthUser to an AuthGroup",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("userId", userId)
              .pathParam("groupId", groupId)
              .post(ADD_AUTH_USER_USING_POST_ON_THE_GROUP_MEMBERSHIP_CONTROLLER);
        });
  }

  public static Performable revokeAuthUserFromUsingDeleteOnTheGroupMembershipController(
      String groupId, String userId, Object body) {
    return Task.where(
        "{0} Remove an AuthUser from an AuthGroup",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("groupId", groupId)
              .pathParam("userId", userId)
              .body(body)
              .delete(REVOKE_AUTH_USER_FROM_USING_DELETE_ON_THE_GROUP_MEMBERSHIP_CONTROLLER);
        });
  }

  public static Performable bulkActivateUserUsingPostOnTheUsersController(String userIds) {
    return Task.where(
        "{0} Activate Users in Bulk",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("userIds", userIds)
              .post(BULK_ACTIVATE_USER_USING_POST_ON_THE_USERS_CONTROLLER);
        });
  }

  public static Performable getEntityConcordanceByEntityIdsUsingGetOnTheReferencesController(
      String pageSize, String page) {
    return Task.where(
        "{0} Retrieves a list of EntityConcordances by Entity Id",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get(GET_ENTITY_CONCORDANCE_BY_ENTITY_IDS_USING_GET_ON_THE_REFERENCES_CONTROLLER);
        });
  }

  public static Performable getUserSecretUsingGetOnTheUsersController(String userId) {
    return Task.where(
        "{0} Get a Client Credentials Service User Secret",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("userId", userId)
              .get(GET_USER_SECRET_USING_GET_ON_THE_USERS_CONTROLLER);
        });
  }

  public static Performable getUserByUsernameUsingGetOnTheUsersController(
      String withUsername, String inRealm) {
    return Task.where(
        "{0} Get a User",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("withUsername", withUsername)
              .queryParam("inRealm", inRealm)
              .get(GET_USER_BY_USERNAME_USING_GET_ON_THE_USERS_CONTROLLER);
        });
  }

  public static Performable findRolesForGroupUsingGetOnTheAuthorizationGroupsController(
      String groupId, String pageSize, String page) {
    return Task.where(
        "{0} List Role assignments for an AuthGroup in a Context",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("groupId", groupId)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get(FIND_ROLES_FOR_GROUP_USING_GET_ON_THE_AUTHORIZATION_GROUPS_CONTROLLER);
        });
  }

  public static Performable findAllServiceRolesForServiceUsingGetOnTheServiceRolesController(
      String serviceName) {
    return Task.where(
        "{0} Find All By Service Name",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("serviceName", serviceName)
              .get(FIND_ALL_SERVICE_ROLES_FOR_SERVICE_USING_GET_ON_THE_SERVICE_ROLES_CONTROLLER);
        });
  }

  public static Performable saveAllUsingPutOnTheServiceRolesController(
      String serviceName, Object body) {
    return Task.where(
        "{0} Create or update a List of Roles",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("serviceName", serviceName)
              .body(body)
              .put(SAVE_ALL_USING_PUT_ON_THE_SERVICE_ROLES_CONTROLLER);
        });
  }

  public static Performable getUsersByIdsUsingGetOnTheUsersController(
      String ids, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Retrieve Users by Ids",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("ids", ids)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get(GET_USERS_BY_IDS_USING_GET_ON_THE_USERS_CONTROLLER);
        });
  }

  public static Performable getContextualAuthPreviewUsingGetOnTheAuthorizationController(
      String serviceName) {
    return Task.where(
        "{0} Get the authorization information for the user for the Tenant. The entitlements returned can be used to enforce security with User Interfaces and APIs, where they need to see the permissions you hold at ALL levels.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("serviceName", serviceName)
              .get(GET_CONTEXTUAL_AUTH_PREVIEW_USING_GET_ON_THE_AUTHORIZATION_CONTROLLER);
        });
  }

  public static Performable getRoleByNameUsingGetOnTheRolesController(
      String name, String lookupBy) {
    return Task.where(
        "{0} Get Role by Name Lookup By Name",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("name", name)
              .queryParam("lookupBy", lookupBy)
              .get(GET_ROLE_BY_NAME_USING_GET_ON_THE_ROLES_CONTROLLER);
        });
  }
}
