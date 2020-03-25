package com.vertafore.test.tasks.servicewrappers.auth;

import net.serenitybdd.screenplay.Performable;
import com.vertafore.test.abilities.CallTitanApi;
import net.serenitybdd.screenplay.Task;

/**
* This class was automatically generated on 2020/03/25 14:48:07

*/
public class UseAuthServiceTo {

	private static final String THIS_SERVICE = "auth";

	public static Performable createProductUsingPutOnTheProductsController(String productIdToCreate, Object body){
		return Task.where(
		"{0} Create Product", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("productIdToCreate", productIdToCreate).body(body).put("products/{productIdToCreate}");
			}
		);
	}

	public static Performable activateUserUsingPostOnTheUsersController(String userId){
		return Task.where(
		"{0} Activate a User", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("userId", userId).post("users/{userId}/activate");
			}
		);
	}

	public static Performable createOrUpdateTenantUsingPutOnTheTenantsController(String tenantIdToModify, Object body){
		return Task.where(
		"{0} Create or Update Tenant", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("tenantIdToModify", tenantIdToModify).body(body).put("tenants/{tenantIdToModify}");
			}
		);
	}

	public static Performable getRoleUsingGetOnTheRolesController(String id){
		return Task.where(
		"{0} Get Role By ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get("roles/{id}");
			}
		);
	}

	public static Performable updateUserUsingPutOnTheUsersController(Object body, String userId){
		return Task.where(
		"{0} Update a User", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).pathParam("userId", userId).put("users/{userId}");
			}
		);
	}

	public static Performable revokeRoleFromUsingDeleteOnTheAuthorizationGroupsController(String groupId, String roleId, Object body){
		return Task.where(
		"{0} Revoke a Role from a Group", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("groupId", groupId).pathParam("roleId", roleId).body(body).delete("groups/{groupId}/roles/{roleId}");
			}
		);
	}

	public static Performable deleteRealmUsingDeleteOnTheRealmManagementController(String name){
		return Task.where(
		"{0} Delete a Realm", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("name", name).delete("realms/{name}");
			}
		);
	}

	public static Performable deleteUserUsingDeleteOnTheUsersController(String userId, String cascade){
		return Task.where(
		"{0} Delete a User", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("userId", userId).queryParam("cascade", cascade).delete("users/{userId}");
			}
		);
	}

	public static Performable findRolesForUserUsingGetOnTheRoleAssignmentsController(String userId, String pageSize, String page, String filter){
		return Task.where(
		"{0} List Role Assignments for a User in a Context", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("userId", userId).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("role-assignments?filter=byUserId");
			}
		);
	}

	public static Performable getProductByNameUsingGetOnTheProductsController(String name, String filter){
		return Task.where(
		"{0} Get Product", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("name", name).queryParam("filter", filter).get("products?filter=byName");
			}
		);
	}

	public static Performable getAuthgroupsOnTheGroupsController(String pageSize, String page){
		return Task.where(
		"{0} Get AuthGroups", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get("groups");
			}
		);
	}

	public static Performable getEntityConcordanceByEntityIdsUsingGetOnTheReferencesController(String pageSize, String page){
		return Task.where(
		"{0} Retrieves a list of EntityConcordances by Entity Id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get("references");
			}
		);
	}

	public static Performable addAuthUserUsingPostOnTheGroupMembershipController(String userId, String groupId){
		return Task.where(
		"{0} Assign an AuthUser to an AuthGroup", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("userId", userId).pathParam("groupId", groupId).post("groups/{groupId}/users/{userId}");
			}
		);
	}

	public static Performable getUsersByIdsUsingGetOnTheUsersController(String ids, String pageSize, String page, String filter){
		return Task.where(
		"{0} Retrieve Users by Ids", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("ids", ids).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("users?filter=byIds");
			}
		);
	}

	public static Performable deleteEntityUsingDeleteOnTheEntitiesController(String entityIdToDelete, String requestParams){
		return Task.where(
		"{0} Delete Entity", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("entityIdToDelete", entityIdToDelete).queryParam("requestParams", requestParams).delete("entities/{entityIdToDelete}");
			}
		);
	}

	public static Performable createAuthGroupUsingPostOnTheGroupsController(Object body){
		return Task.where(
		"{0} Create AuthGroup", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("groups");
			}
		);
	}

	public static Performable getUserByUsernameUsingGetOnTheUsersController(String withUsername, String inRealm){
		return Task.where(
		"{0} Get a User", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("withUsername", withUsername).queryParam("inRealm", inRealm).get("users");
			}
		);
	}

	public static Performable getEntityIdsForEntitlementUsingGetOnTheAuthorizationController(String entityIds, String entitlement, String userId, String filter){
		return Task.where(
		"{0} Determine whether user has the entitlement at the given entities.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("entityIds", entityIds).queryParam("entitlement", entitlement).queryParam("userId", userId).queryParam("filter", filter).get("entities?filter=byEntitlement");
			}
		);
	}

	public static Performable findRoleByNameUsingGetOnTheRolesController(String name, String filter){
		return Task.where(
		"{0} Get Role by Name", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("name", name).queryParam("filter", filter).get("roles?filter=byName");
			}
		);
	}

	public static Performable getRealmUsingGetOnTheRealmManagementController(String name){
		return Task.where(
		"{0} Get Realm", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("name", name).get("realms/{name}");
			}
		);
	}

	public static Performable findAuthUserMembershipsInAuthGroupUsingGetOnTheGroupMembershipController(String groupId, String pageSize, String page){
		return Task.where(
		"{0} List AuthUser membership for the specified AuthGroup in a Context", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("groupId", groupId).queryParam("pageSize", pageSize).queryParam("page", page).get("groups/{groupId}/users");
			}
		);
	}

	public static Performable getContextualAuthPreviewUsingGetOnTheAuthorizationController(String serviceName){
		return Task.where(
		"{0} Get the authorization information for the user for the Tenant. The entitlements returned can be used to enforce security with User Interfaces and APIs, where they need to see the permissions you hold at ALL levels.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("serviceName", serviceName).get("auth-tree/services/{serviceName}");
			}
		);
	}

	public static Performable createOrUpdateAuthGroupUsingPutOnTheGroupsController(String groupId, Object body){
		return Task.where(
		"{0} Update or Create AuthGroup", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("groupId", groupId).body(body).put("groups/{groupId}");
			}
		);
	}

	public static Performable getUsingGetOnTheAuthenticationSettingsController(){
		return Task.where(
		"{0} Get Authentication Settings and/or computed values for provided Context", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").get("authenticationsettings");
			}
		);
	}

	public static Performable revokeAuthUserFromUsingDeleteOnTheGroupMembershipController(String groupId, String userId, Object body){
		return Task.where(
		"{0} Remove an AuthUser from an AuthGroup", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("groupId", groupId).pathParam("userId", userId).body(body).delete("groups/{groupId}/users/{userId}");
			}
		);
	}

	public static Performable deleteEntityConcordanceUsingDeleteOnTheReferencesController(Object body){
		return Task.where(
		"{0} Delete EntityConcordance", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).delete("references");
			}
		);
	}

	public static Performable getRoleByNameUsingGetOnTheRolesController(String name, String lookupBy){
		return Task.where(
		"{0} Get Role by Name Lookup By Name", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("name", name).queryParam("lookupBy", lookupBy).get("roles/{name}?lookupBy=name");
			}
		);
	}

	public static Performable getUserUsingGetOnTheUsersController(String userId){
		return Task.where(
		"{0} Get a User", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("userId", userId).get("users/{userId}");
			}
		);
	}

	public static Performable getAccessTokenUsingPostOnTheIdentityProviderController(Object body){
		return Task.where(
		"{0} Get token", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("/auth/v1/token");
			}
		);
	}

	public static Performable getContextualAuthUsingGetOnTheAuthorizationController(String serviceName){
		return Task.where(
		"{0} Get the authorization information associated with the context of this request.  The entitlements returned can be used to enforce security in other services", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("serviceName", serviceName).get("auth-info/services/{serviceName}");
			}
		);
	}

	public static Performable deleteAndMigrateEntityUsingDeleteOnTheEntitiesController(String entityIdToDelete, String migrateTo){
		return Task.where(
		"{0} Delete And Migrate Entity", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("entityIdToDelete", entityIdToDelete).queryParam("migrateTo", migrateTo).delete("entities/{entityIdToDelete}");
			}
		);
	}

	public static Performable bulkGrantRoleUsingPostOnTheRoleAssignmentsController(Object body){
		return Task.where(
		"{0} Assign a Role to multiple Users", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("role-assignments/bulk");
			}
		);
	}

	public static Performable createRoleUsingPostOnTheRolesController(Object body){
		return Task.where(
		"{0} Create Role", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("roles");
			}
		);
	}

	public static Performable createOrUpdateUsingPutOnTheAuthenticationSettingsController(Object body){
		return Task.where(
		"{0} Create or Update Authentication Settings for provided Context", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).put("authenticationsettings");
			}
		);
	}

	public static Performable bulkCreateUserUsingPostOnTheUsersController(Object body){
		return Task.where(
		"{0} Create bulk Users", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("users/bulk");
			}
		);
	}

	public static Performable getRefreshTokenUsingPostOnTheIdentityProviderController(Object body){
		return Task.where(
		"{0} Refresh token", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("/auth/v1/token/refresh");
			}
		);
	}

	public static Performable createOrUpdateEntityUsingPutOnTheEntitiesController(String entityIdToWrite, Object body){
		return Task.where(
		"{0} Create or update Entity", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("entityIdToWrite", entityIdToWrite).body(body).put("entities/{entityIdToWrite}");
			}
		);
	}

	public static Performable getTenantUsingGetOnTheTenantsController(String tenantIdToRetrieve){
		return Task.where(
		"{0} Get Tenant", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("tenantIdToRetrieve", tenantIdToRetrieve).get("tenants/{tenantIdToRetrieve}");
			}
		);
	}

	public static Performable patchUpdateUserUsingPatchOnTheUsersController(String userId, Object body){
		return Task.where(
		"{0} Patch Auth User", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").pathParam("userId", userId).body(body).patch("users/{userId}");
			}
		);
	}

	public static Performable updateRoleUsingPutOnTheRolesController(String id, Object body){
		return Task.where(
		"{0} Replace Role", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put("roles/{id}");
			}
		);
	}

	public static Performable findAllServiceRolesForServiceUsingGetOnTheServiceRolesController(String serviceName){
		return Task.where(
		"{0} Find All By Service Name", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("serviceName", serviceName).get("services/{serviceName}/roles");
			}
		);
	}

	public static Performable updateRealmUsingPutOnTheRealmManagementController(String name, Object body){
		return Task.where(
		"{0} Replace Realm", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("name", name).body(body).put("realms/{name}");
			}
		);
	}

	public static Performable deactivateUserUsingPostOnTheUsersController(String userId){
		return Task.where(
		"{0} Deactivate Users", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("userId", userId).post("users/{userId}/deactivate");
			}
		);
	}

	public static Performable createEntityUsingPostOnTheEntitiesController(Object body){
		return Task.where(
		"{0} Create Entity", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("entities");
			}
		);
	}

	public static Performable createEntityConcordanceUsingPostOnTheReferencesController(Object body){
		return Task.where(
		"{0} Create EntityConcordance", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("references");
			}
		);
	}

	public static Performable createRealmUsingPostOnTheRealmManagementController(Object body){
		return Task.where(
		"{0} Create Realm", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("realms");
			}
		);
	}

	public static Performable getProductsByIdsUsingGetOnTheProductsController(String ids, String pageSize, String page, String filter, String internal){
		return Task.where(
		"{0} Retrieve Products by Ids", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("ids", ids).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).queryParam("internal", internal).get("products?filter=byIds");
			}
		);
	}

	public static Performable getEntityHierarchyUsingGetOnTheEntitiesController(String entityIdToRetrieve, String ancestor_levels, String ancestor_properties, String descendant_levels, String descendant_properties, String entity_type){
		return Task.where(
		"{0} Get Entity Hierarchy", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("entityIdToRetrieve", entityIdToRetrieve).queryParam("ancestor_levels", ancestor_levels).queryParam("ancestor_properties", ancestor_properties).queryParam("descendant_levels", descendant_levels).queryParam("descendant_properties", descendant_properties).queryParam("entity_type", entity_type).get("entities/{entityIdToRetrieve}");
			}
		);
	}

	public static Performable getUsersByContextUsingGetOnTheUsersController(String scope, String userOrDisplayNameIncludes, String sortBy, String sortDirection, String activeFilter, String pageSize, String page, String filter){
		return Task.where(
		"{0} Retrieve Users for a given Context", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("scope", scope).queryParam("userOrDisplayNameIncludes", userOrDisplayNameIncludes).queryParam("sortBy", sortBy).queryParam("sortDirection", sortDirection).queryParam("activeFilter", activeFilter).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("users?filter=byScope");
			}
		);
	}

	public static Performable changePasswordUsingPutOnTheAuthenticationController(Object body){
		return Task.where(
		"{0} Change a User's Password", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).put("/auth/v1/change-password");
			}
		);
	}

	public static Performable getAuthGroupUsingGetOnTheGroupsController(String groupId){
		return Task.where(
		"{0} Get AuthGroup", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("groupId", groupId).get("groups/{groupId}");
			}
		);
	}

	public static Performable deleteTenantUsingDeleteOnTheTenantsController(String tenantIdToDelete){
		return Task.where(
		"{0} Delete Tenant", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("tenantIdToDelete", tenantIdToDelete).delete("tenants/{tenantIdToDelete}");
			}
		);
	}

	public static Performable assignARoleToAUserOnTheRoleAssignmentsController(Object body){
		return Task.where(
		"{0} Assign a Role to a User", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("role-assignments");
			}
		);
	}

	public static Performable bulkDeleteUserUsingDeleteOnTheUsersController(String userIds){
		return Task.where(
		"{0} Delete multiple Users", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("userIds", userIds).delete("users/bulk");
			}
		);
	}

	public static Performable createUserUsingPostOnTheUsersController(Object body){
		return Task.where(
		"{0} Create a User", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("users");
			}
		);
	}

	public static Performable getProductUsingGetOnTheProductsController(String productIdToRetrieve){
		return Task.where(
		"{0} Get Product", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("productIdToRetrieve", productIdToRetrieve).get("products/{productIdToRetrieve}");
			}
		);
	}

	public static Performable getUserSecretUsingGetOnTheUsersController(String userId){
		return Task.where(
		"{0} Get a Client Credentials Service User Secret", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("userId", userId).get("users/{userId}/secret");
			}
		);
	}

	public static Performable saveAllUsingPutOnTheServiceRolesController(String serviceName, Object body){
		return Task.where(
		"{0} Create or update a List of Roles", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("serviceName", serviceName).body(body).put("services/{serviceName}/roles");
			}
		);
	}

	public static Performable deleteRoleUsingDeleteOnTheRolesController(String id){
		return Task.where(
		"{0} Delete a Role", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete("roles/{id}");
			}
		);
	}

	public static Performable findRolesForGroupUsingGetOnTheAuthorizationGroupsController(String groupId, String pageSize, String page){
		return Task.where(
		"{0} List Role assignments for an AuthGroup in a Context", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("groupId", groupId).queryParam("pageSize", pageSize).queryParam("page", page).get("groups/{groupId}/roles");
			}
		);
	}

	public static Performable deleteAuthGroupUsingDeleteOnTheGroupsController(String groupId){
		return Task.where(
		"{0} Delete AuthGroup", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("groupId", groupId).delete("groups/{groupId}");
			}
		);
	}

	public static Performable findServiceroleOnTheServiceRolesController(String serviceName, String name){
		return Task.where(
		"{0} Find ServiceRole", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("serviceName", serviceName).pathParam("name", name).get("services/{serviceName}/roles/{name}");
			}
		);
	}

	public static Performable getRoleLookupByIdUsingGetOnTheRolesController(String id, String lookupBy){
		return Task.where(
		"{0} Get Role By ID Lookup By ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).queryParam("lookupBy", lookupBy).get("roles/{id}?lookupBy=id");
			}
		);
	}

	public static Performable bulkDeactivateUserUsingPostOnTheUsersController(String userIds){
		return Task.where(
		"{0} Deactivate a User", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("userIds", userIds).post("users/deactivate/bulk");
			}
		);
	}

	public static Performable bulkActivateUserUsingPostOnTheUsersController(String userIds){
		return Task.where(
		"{0} Activate Users in Bulk", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("userIds", userIds).post("users/activate/bulk");
			}
		);
	}

	public static Performable revokeARoleFromAUserOnTheRoleAssignmentsController(Object body){
		return Task.where(
		"{0} Revoke a Role from a User", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).delete("role-assignments");
			}
		);
	}

	public static Performable grantRoleUsingPostOnTheAuthorizationGroupsController(String groupId, String roleId, Object body){
		return Task.where(
		"{0} Assign a Role to a Group", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("groupId", groupId).pathParam("roleId", roleId).body(body).post("groups/{groupId}/roles/{roleId}");
			}
		);
	}

	public static Performable findRoleByIdsUsingGetOnTheRolesController(String ids, String pageSize, String page, String filter){
		return Task.where(
		"{0} Retrieve lots of Roles by id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("ids", ids).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("roles?filter=byIds");
			}
		);
	}



}
