package com.vertafore.test.tasks.servicewrappers.customer;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import com.vertafore.test.abilities.CallTitanApi;

/**
* This class was automatically generated on 2020/03/25 13:35:09

*/
public class UseCustomerServiceTo {

	private static final String THIS_SERVICE = "customer";
	private static final String GET_SERVICE_TEAM_MEMBERS_FILTERED_BY_MEMBER_TYPES_USING_GET_ON_THE_SERVICE_TEAM_MEMBER_CONTROLLER = "accounts/{accountId}/service-team-members?filter=byMemberTypes";
	private static final String GET_LEAD_SOURCES_BY_CONTEXT_USING_GET_ON_THE_LEAD_SOURCE_CONTROLLER = "lead-sources";
	private static final String GET_DISQUALIFICATION_REASONS_BY_CONTEXT_USING_GET_ON_THE_DISQUALIFICATION_REASON_CONTROLLER = "disqualification-reasons";
	private static final String CREATE_ACCOUNT_USING_POST_ON_THE_ACCOUNT_CONTROLLER = "accounts";
	private static final String CREATE_SERVICE_TEAM_MEMBER_USING_POST_ON_THE_SERVICE_TEAM_MEMBER_CONTROLLER = "accounts/{accountId}/service-team-members";
	private static final String PATCH_CUSTOMER_USING_PATCH_ON_THE_CUSTOMER_CONTROLLER = "customers/{customerId}";
	private static final String GET_CUSTOMER_USING_GET_ON_THE_CUSTOMER_CONTROLLER = "customers/{customerId}";
	private static final String DELETE_CUSTOMER_USING_DELETE_ON_THE_CUSTOMER_CONTROLLER = "customers/{customerId}";
	private static final String UPDATE_CUSTOMER_USING_PUT_ON_THE_CUSTOMER_CONTROLLER = "customers/{customerId}";
	private static final String PATCH_LEAD_USING_PATCH_ON_THE_LEAD_CONTROLLER = "leads/{leadId}";
	private static final String GET_LEAD_USING_GET_ON_THE_LEAD_CONTROLLER = "leads/{leadId}";
	private static final String DELETE_LEAD_USING_DELETE_ON_THE_LEAD_CONTROLLER = "leads/{leadId}";
	private static final String UPDATE_LEAD_USING_PUT_ON_THE_LEAD_CONTROLLER = "leads/{leadId}";
	private static final String GET_LOCATIONS_PER_ACCOUNT_USING_GET_ON_THE_LOCATION_CONTROLLER = "accounts/{accountId}/locations";
	private static final String CONVERT_LEAD_USING_POST_ON_THE_CUSTOMER_CONTROLLER = "customers";
	private static final String GET_LEADS_BY_IDS_USING_GET_ON_THE_LEAD_CONTROLLER = "leads?filter=byIds&scope=descendants";
	private static final String GET_CONTACTS_FOR_ACCOUNT_BY_ID_USING_GET_ON_THE_CONTACT_CONTROLLER = "accounts/{accountId}/contacts";
	private static final String CREATE_DISQUALIFICATION_REASON_USING_POST_ON_THE_DISQUALIFICATION_REASON_CONTROLLER = "disqualification-reasons";
	private static final String CREATE_LEAD_USING_POST_ON_THE_LEAD_CONTROLLER = "leads";
	private static final String GET_SERVICE_TEAM_MEMBERS_PER_ACCOUNT_USING_GET_ON_THE_SERVICE_TEAM_MEMBER_CONTROLLER = "accounts/{accountId}/service-team-members";
	private static final String PATCH_ACCOUNT_USING_PATCH_ON_THE_ACCOUNT_CONTROLLER = "accounts/{id}";
	private static final String GET_ACCOUNT_BY_ID_USING_GET_ON_THE_ACCOUNT_CONTROLLER = "accounts/{id}";
	private static final String DELETE_ACCOUNT_USING_DELETE_ON_THE_ACCOUNT_CONTROLLER = "accounts/{id}";
	private static final String UPDATE_ACCOUNT_USING_PUT_ON_THE_ACCOUNT_CONTROLLER = "accounts/{id}";
	private static final String GET_ACCOUNTS_BY_IDS_USING_GET_ON_THE_ACCOUNT_CONTROLLER = "accounts?filter=byIds&scope=descendants";
	private static final String GET_CUSTOMERS_BY_SEARCH_TERM_USING_GET_ON_THE_CUSTOMER_CONTROLLER = "customers?filter=bySearchTerm&scope=descendants";
	private static final String PATCH_CONTACT_USING_PATCH_ON_THE_CONTACT_CONTROLLER = "contacts/{id}";
	private static final String GET_CONTACT_USING_GET_ON_THE_CONTACT_CONTROLLER = "contacts/{id}";
	private static final String DELETE_CONTACT_USING_DELETE_ON_THE_CONTACT_CONTROLLER = "contacts/{id}";
	private static final String UPDATE_CONTACT_USING_PUT_ON_THE_CONTACT_CONTROLLER = "contacts/{id}";
	private static final String SEARCH_LEADS_USING_GET_ON_THE_LEAD_CONTROLLER = "leads?filter=bySearchTerm&scope=descendants";
	private static final String CREATE_RELATED_ACCOUNT_USING_POST_ON_THE_RELATED_ACCOUNT_CONTROLLER = "accounts/{accountId}/related-accounts";
	private static final String SEARCH_ACCOUNTS_USING_GET_ON_THE_ACCOUNT_CONTROLLER = "accounts?filter=bySearchTerm&scope=descendants";
	private static final String PATCH_DISQUALIFICATION_REASON_USING_PATCH_ON_THE_DISQUALIFICATION_REASON_CONTROLLER = "disqualification-reasons/{id}";
	private static final String GET_DISQUALIFICATION_REASON_BY_ID_USING_GET_ON_THE_DISQUALIFICATION_REASON_CONTROLLER = "disqualification-reasons/{id}";
	private static final String DELETE_DISQUALIFICATION_REASON_USING_DELETE_ON_THE_DISQUALIFICATION_REASON_CONTROLLER = "disqualification-reasons/{id}";
	private static final String UPDATE_DISQUALIFICATION_REASON_USING_PUT_ON_THE_DISQUALIFICATION_REASON_CONTROLLER = "disqualification-reasons/{id}";
	private static final String DELETE_RELATED_ACCOUNT_USING_DELETE_ON_THE_RELATED_ACCOUNT_CONTROLLER = "related-accounts/{id}";
	private static final String CREATE_CONTACT_USING_POST_ON_THE_CONTACT_CONTROLLER = "accounts/{accountId}/contacts";
	private static final String CREATE_LOCATION_USING_POST_ON_THE_LOCATION_CONTROLLER = "accounts/{accountId}/locations";
	private static final String PATCH_SERVICE_TEAM_MEMBER_USING_PATCH_ON_THE_SERVICE_TEAM_MEMBER_CONTROLLER = "service-team-members/{id}";
	private static final String GET_SERVICE_TEAM_MEMBER_USING_GET_ON_THE_SERVICE_TEAM_MEMBER_CONTROLLER = "service-team-members/{id}";
	private static final String DELETE_SERVICE_TEAM_MEMBER_USING_DELETE_ON_THE_SERVICE_TEAM_MEMBER_CONTROLLER = "service-team-members/{id}";
	private static final String UPDATE_SERVICE_TEAM_MEMBER_USING_PUT_ON_THE_SERVICE_TEAM_MEMBER_CONTROLLER = "service-team-members/{id}";
	private static final String PATCH_LOCATION_USING_PATCH_ON_THE_LOCATION_CONTROLLER = "locations/{id}";
	private static final String GET_LOCATION_USING_GET_ON_THE_LOCATION_CONTROLLER = "locations/{id}";
	private static final String DELETE_LOCATION_USING_DELETE_ON_THE_LOCATION_CONTROLLER = "locations/{id}";
	private static final String UPDATE_LOCATION_USING_PUT_ON_THE_LOCATION_CONTROLLER = "locations/{id}";
	private static final String GET_CUSTOMERS_BY_IDS_USING_GET_ON_THE_CUSTOMER_CONTROLLER = "customers?filter=byIds&scope=descendants";
	private static final String GET_LEADS_USING_GET_ON_THE_LEAD_CONTROLLER = "leads";
	private static final String CONVERT_LEAD_ACCOUNT_TO_PROSPECT_USING_POST_ON_THE_ACCOUNT_CONTROLLER = "accounts";
	private static final String PATCH_LEAD_SOURCE_USING_PATCH_ON_THE_LEAD_SOURCE_CONTROLLER = "lead-sources/{id}";
	private static final String GET_LEAD_SOURCE_BY_ID_USING_GET_ON_THE_LEAD_SOURCE_CONTROLLER = "lead-sources/{id}";
	private static final String DELETE_LEAD_SOURCE_USING_DELETE_ON_THE_LEAD_SOURCE_CONTROLLER = "lead-sources/{id}";
	private static final String UPDATE_LEAD_SOURCE_USING_PUT_ON_THE_LEAD_SOURCE_CONTROLLER = "lead-sources/{id}";
	private static final String CREATE_CUSTOMER_USING_POST_ON_THE_CUSTOMER_CONTROLLER = "customers";
	private static final String CREATE_LEAD_SOURCE_USING_POST_ON_THE_LEAD_SOURCE_CONTROLLER = "lead-sources";
	private static final String GET_RELATED_ACCOUNTS_USING_GET_ON_THE_RELATED_ACCOUNT_CONTROLLER = "accounts/{accountId}/related-accounts";
	private static final String CREATE_DISQUALIFICATION_USING_POST_ON_THE_DISQUALIFICATION_CONTROLLER = "leads/{leadId}/disqualification";
	private static final String DELETE_DISQUALIFICATION_ON_LEAD_USING_DELETE_ON_THE_DISQUALIFICATION_CONTROLLER = "leads/{leadId}/disqualification";
	private static final String UPDATE_DISQUALIFICATION_ON_LEAD_USING_PUT_ON_THE_DISQUALIFICATION_CONTROLLER = "leads/{leadId}/disqualification";

	public static Performable getServiceTeamMembersFilteredByMemberTypesUsingGetOnTheServiceTeamMemberController(String accountId, String pageSize, String page, String memberTypes, String filter){
		return Task.where(
		"{0} Get a Page of ServiceTeamMembers per Customer, filtered by member types", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("accountId", accountId).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("memberTypes", memberTypes).queryParam("filter", filter).get(GET_SERVICE_TEAM_MEMBERS_FILTERED_BY_MEMBER_TYPES_USING_GET_ON_THE_SERVICE_TEAM_MEMBER_CONTROLLER);
			}
		);
	}

	public static Performable getLeadSourcesByContextUsingGetOnTheLeadSourceController(String pageSize, String page){
		return Task.where(
		"{0} Get lead sources", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(GET_LEAD_SOURCES_BY_CONTEXT_USING_GET_ON_THE_LEAD_SOURCE_CONTROLLER);
			}
		);
	}

	public static Performable getDisqualificationReasonsByContextUsingGetOnTheDisqualificationReasonController(String pageSize, String page){
		return Task.where(
		"{0} Get Disqualification Reasons", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(GET_DISQUALIFICATION_REASONS_BY_CONTEXT_USING_GET_ON_THE_DISQUALIFICATION_REASON_CONTROLLER);
			}
		);
	}

	public static Performable createAccountUsingPostOnTheAccountController(Object body){
		return Task.where(
		"{0} Create Account (Customer/Lead)", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_ACCOUNT_USING_POST_ON_THE_ACCOUNT_CONTROLLER);
			}
		);
	}

	public static Performable createServiceTeamMemberUsingPostOnTheServiceTeamMemberController(String accountId, Object body){
		return Task.where(
		"{0} Post a ServiceTeamMember per Customer", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("accountId", accountId).body(body).post(CREATE_SERVICE_TEAM_MEMBER_USING_POST_ON_THE_SERVICE_TEAM_MEMBER_CONTROLLER);
			}
		);
	}

	public static Performable patchCustomerUsingPatchOnTheCustomerController(Object body, String customerId){
		return Task.where(
		"{0} Patch Customer", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").body(body).pathParam("customerId", customerId).patch(PATCH_CUSTOMER_USING_PATCH_ON_THE_CUSTOMER_CONTROLLER);
			}
		);
	}

	public static Performable getCustomerUsingGetOnTheCustomerController(String customerId){
		return Task.where(
		"{0} Get Customer", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("customerId", customerId).get(GET_CUSTOMER_USING_GET_ON_THE_CUSTOMER_CONTROLLER);
			}
		);
	}

	public static Performable deleteCustomerUsingDeleteOnTheCustomerController(String customerId){
		return Task.where(
		"{0} Delete Customer", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("customerId", customerId).delete(DELETE_CUSTOMER_USING_DELETE_ON_THE_CUSTOMER_CONTROLLER);
			}
		);
	}

	public static Performable updateCustomerUsingPutOnTheCustomerController(Object body, String customerId){
		return Task.where(
		"{0} Replace Customer", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).pathParam("customerId", customerId).put(UPDATE_CUSTOMER_USING_PUT_ON_THE_CUSTOMER_CONTROLLER);
			}
		);
	}

	public static Performable patchLeadUsingPatchOnTheLeadController(Object body, String leadId){
		return Task.where(
		"{0} Patch Lead", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").body(body).pathParam("leadId", leadId).patch(PATCH_LEAD_USING_PATCH_ON_THE_LEAD_CONTROLLER);
			}
		);
	}

	public static Performable getLeadUsingGetOnTheLeadController(String leadId){
		return Task.where(
		"{0} Get Lead", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("leadId", leadId).get(GET_LEAD_USING_GET_ON_THE_LEAD_CONTROLLER);
			}
		);
	}

	public static Performable deleteLeadUsingDeleteOnTheLeadController(String leadId){
		return Task.where(
		"{0} Delete Lead", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("leadId", leadId).delete(DELETE_LEAD_USING_DELETE_ON_THE_LEAD_CONTROLLER);
			}
		);
	}

	public static Performable updateLeadUsingPutOnTheLeadController(Object body, String leadId){
		return Task.where(
		"{0} Replace Lead", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).pathParam("leadId", leadId).put(UPDATE_LEAD_USING_PUT_ON_THE_LEAD_CONTROLLER);
			}
		);
	}

	public static Performable getLocationsPerAccountUsingGetOnTheLocationController(String accountId, String pageSize, String page){
		return Task.where(
		"{0} Get a Page of Locations per Customer", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("accountId", accountId).queryParam("pageSize", pageSize).queryParam("page", page).get(GET_LOCATIONS_PER_ACCOUNT_USING_GET_ON_THE_LOCATION_CONTROLLER);
			}
		);
	}

	public static Performable convertLeadUsingPostOnTheCustomerController(String convertLead){
		return Task.where(
		"{0} Convert Lead to Prospect", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("convertLead", convertLead).post(CONVERT_LEAD_USING_POST_ON_THE_CUSTOMER_CONTROLLER);
			}
		);
	}

	public static Performable getLeadsByIdsUsingGetOnTheLeadController(String pageSize, String page, String ids, String filter, String scope){
		return Task.where(
		"{0} Get Leads by IDs", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("ids", ids).queryParam("filter", filter).queryParam("scope", scope).get(GET_LEADS_BY_IDS_USING_GET_ON_THE_LEAD_CONTROLLER);
			}
		);
	}

	public static Performable getContactsForAccountByIdUsingGetOnTheContactController(String pageSize, String page, String accountId){
		return Task.where(
		"{0} Get a page of Contacts for Account by Id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).pathParam("accountId", accountId).get(GET_CONTACTS_FOR_ACCOUNT_BY_ID_USING_GET_ON_THE_CONTACT_CONTROLLER);
			}
		);
	}

	public static Performable createDisqualificationReasonUsingPostOnTheDisqualificationReasonController(Object body){
		return Task.where(
		"{0} Post Disqualification Reason", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_DISQUALIFICATION_REASON_USING_POST_ON_THE_DISQUALIFICATION_REASON_CONTROLLER);
			}
		);
	}

	public static Performable createLeadUsingPostOnTheLeadController(Object body){
		return Task.where(
		"{0} Create Lead", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_LEAD_USING_POST_ON_THE_LEAD_CONTROLLER);
			}
		);
	}

	public static Performable getServiceTeamMembersPerAccountUsingGetOnTheServiceTeamMemberController(String accountId, String pageSize, String page){
		return Task.where(
		"{0} Get a Page of ServiceTeamMembers per Customer", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("accountId", accountId).queryParam("pageSize", pageSize).queryParam("page", page).get(GET_SERVICE_TEAM_MEMBERS_PER_ACCOUNT_USING_GET_ON_THE_SERVICE_TEAM_MEMBER_CONTROLLER);
			}
		);
	}

	public static Performable patchAccountUsingPatchOnTheAccountController(Object body, String id){
		return Task.where(
		"{0} Patch Account (Customer/Lead)", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").body(body).pathParam("id", id).patch(PATCH_ACCOUNT_USING_PATCH_ON_THE_ACCOUNT_CONTROLLER);
			}
		);
	}

	public static Performable getAccountByIdUsingGetOnTheAccountController(String id){
		return Task.where(
		"{0} Get Account (Customer/Lead)", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_ACCOUNT_BY_ID_USING_GET_ON_THE_ACCOUNT_CONTROLLER);
			}
		);
	}

	public static Performable deleteAccountUsingDeleteOnTheAccountController(String id){
		return Task.where(
		"{0} Delete Account (Customer/Lead)", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_ACCOUNT_USING_DELETE_ON_THE_ACCOUNT_CONTROLLER);
			}
		);
	}

	public static Performable updateAccountUsingPutOnTheAccountController(Object body, String id){
		return Task.where(
		"{0} Replace Account (Customer/Lead)", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).pathParam("id", id).put(UPDATE_ACCOUNT_USING_PUT_ON_THE_ACCOUNT_CONTROLLER);
			}
		);
	}

	public static Performable getAccountsByIdsUsingGetOnTheAccountController(String pageSize, String page, String ids, String filter, String scope){
		return Task.where(
		"{0} Get Accounts by IDs", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("ids", ids).queryParam("filter", filter).queryParam("scope", scope).get(GET_ACCOUNTS_BY_IDS_USING_GET_ON_THE_ACCOUNT_CONTROLLER);
			}
		);
	}

	public static Performable getCustomersBySearchTermUsingGetOnTheCustomerController(String searchTerm, String isDescending, String sortField, String pageSize, String page, String filter, String scope){
		return Task.where(
		"{0} Get Customers by search term", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("searchTerm", searchTerm).queryParam("isDescending", isDescending).queryParam("sortField", sortField).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).queryParam("scope", scope).get(GET_CUSTOMERS_BY_SEARCH_TERM_USING_GET_ON_THE_CUSTOMER_CONTROLLER);
			}
		);
	}

	public static Performable patchContactUsingPatchOnTheContactController(Object body, String id){
		return Task.where(
		"{0} Patch Contact", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").body(body).pathParam("id", id).patch(PATCH_CONTACT_USING_PATCH_ON_THE_CONTACT_CONTROLLER);
			}
		);
	}

	public static Performable getContactUsingGetOnTheContactController(String id){
		return Task.where(
		"{0} Get Contact", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_CONTACT_USING_GET_ON_THE_CONTACT_CONTROLLER);
			}
		);
	}

	public static Performable deleteContactUsingDeleteOnTheContactController(String id){
		return Task.where(
		"{0} Delete Contact", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_CONTACT_USING_DELETE_ON_THE_CONTACT_CONTROLLER);
			}
		);
	}

	public static Performable updateContactUsingPutOnTheContactController(Object body, String id){
		return Task.where(
		"{0} Replace Contact", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).pathParam("id", id).put(UPDATE_CONTACT_USING_PUT_ON_THE_CONTACT_CONTROLLER);
			}
		);
	}

	public static Performable searchLeadsUsingGetOnTheLeadController(String searchTerm, String isDescending, String sortField, String isDisqualified, String pageSize, String page, String filter, String scope){
		return Task.where(
		"{0} Get Leads by search term", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("searchTerm", searchTerm).queryParam("isDescending", isDescending).queryParam("sortField", sortField).queryParam("isDisqualified", isDisqualified).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).queryParam("scope", scope).get(SEARCH_LEADS_USING_GET_ON_THE_LEAD_CONTROLLER);
			}
		);
	}

	public static Performable createRelatedAccountUsingPostOnTheRelatedAccountController(Object body, String accountId){
		return Task.where(
		"{0} Add Related Account", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).pathParam("accountId", accountId).post(CREATE_RELATED_ACCOUNT_USING_POST_ON_THE_RELATED_ACCOUNT_CONTROLLER);
			}
		);
	}

	public static Performable searchAccountsUsingGetOnTheAccountController(String searchTerm, String isDescending, String sortField, String isDisqualified, String pageSize, String page, String filter, String scope){
		return Task.where(
		"{0} Get Accounts by search term", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("searchTerm", searchTerm).queryParam("isDescending", isDescending).queryParam("sortField", sortField).queryParam("isDisqualified", isDisqualified).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).queryParam("scope", scope).get(SEARCH_ACCOUNTS_USING_GET_ON_THE_ACCOUNT_CONTROLLER);
			}
		);
	}

	public static Performable patchDisqualificationReasonUsingPatchOnTheDisqualificationReasonController(String id, Object body){
		return Task.where(
		"{0} Patch disqualification reason", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").pathParam("id", id).body(body).patch(PATCH_DISQUALIFICATION_REASON_USING_PATCH_ON_THE_DISQUALIFICATION_REASON_CONTROLLER);
			}
		);
	}

	public static Performable getDisqualificationReasonByIdUsingGetOnTheDisqualificationReasonController(String id){
		return Task.where(
		"{0} Get a Disqualification Reason", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_DISQUALIFICATION_REASON_BY_ID_USING_GET_ON_THE_DISQUALIFICATION_REASON_CONTROLLER);
			}
		);
	}

	public static Performable deleteDisqualificationReasonUsingDeleteOnTheDisqualificationReasonController(String id){
		return Task.where(
		"{0} Delete Disqualification Reason", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_DISQUALIFICATION_REASON_USING_DELETE_ON_THE_DISQUALIFICATION_REASON_CONTROLLER);
			}
		);
	}

	public static Performable updateDisqualificationReasonUsingPutOnTheDisqualificationReasonController(String id, Object body){
		return Task.where(
		"{0} Replace disqualification reason", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put(UPDATE_DISQUALIFICATION_REASON_USING_PUT_ON_THE_DISQUALIFICATION_REASON_CONTROLLER);
			}
		);
	}

	public static Performable deleteRelatedAccountUsingDeleteOnTheRelatedAccountController(String id){
		return Task.where(
		"{0} Delete relationship between customers", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_RELATED_ACCOUNT_USING_DELETE_ON_THE_RELATED_ACCOUNT_CONTROLLER);
			}
		);
	}

	public static Performable createContactUsingPostOnTheContactController(Object body, String accountId){
		return Task.where(
		"{0} Create Contact", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).pathParam("accountId", accountId).post(CREATE_CONTACT_USING_POST_ON_THE_CONTACT_CONTROLLER);
			}
		);
	}

	public static Performable createLocationUsingPostOnTheLocationController(String accountId, Object body){
		return Task.where(
		"{0} Post a Location per Customer", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("accountId", accountId).body(body).post(CREATE_LOCATION_USING_POST_ON_THE_LOCATION_CONTROLLER);
			}
		);
	}

	public static Performable patchServiceTeamMemberUsingPatchOnTheServiceTeamMemberController(String id, Object body){
		return Task.where(
		"{0} Patch a ServiceTeamMember by Id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").pathParam("id", id).body(body).patch(PATCH_SERVICE_TEAM_MEMBER_USING_PATCH_ON_THE_SERVICE_TEAM_MEMBER_CONTROLLER);
			}
		);
	}

	public static Performable getServiceTeamMemberUsingGetOnTheServiceTeamMemberController(String id){
		return Task.where(
		"{0} Get a single ServiceTeamMember by ServiceTeamMember Id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_SERVICE_TEAM_MEMBER_USING_GET_ON_THE_SERVICE_TEAM_MEMBER_CONTROLLER);
			}
		);
	}

	public static Performable deleteServiceTeamMemberUsingDeleteOnTheServiceTeamMemberController(String id){
		return Task.where(
		"{0} Delete a ServiceTeamMember by Id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_SERVICE_TEAM_MEMBER_USING_DELETE_ON_THE_SERVICE_TEAM_MEMBER_CONTROLLER);
			}
		);
	}

	public static Performable updateServiceTeamMemberUsingPutOnTheServiceTeamMemberController(String id, Object body){
		return Task.where(
		"{0} Replace a ServiceTeamMember by Id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put(UPDATE_SERVICE_TEAM_MEMBER_USING_PUT_ON_THE_SERVICE_TEAM_MEMBER_CONTROLLER);
			}
		);
	}

	public static Performable patchLocationUsingPatchOnTheLocationController(String id, Object body){
		return Task.where(
		"{0} Patch a Location by Id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").pathParam("id", id).body(body).patch(PATCH_LOCATION_USING_PATCH_ON_THE_LOCATION_CONTROLLER);
			}
		);
	}

	public static Performable getLocationUsingGetOnTheLocationController(String id){
		return Task.where(
		"{0} Get a single Location by Location Id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_LOCATION_USING_GET_ON_THE_LOCATION_CONTROLLER);
			}
		);
	}

	public static Performable deleteLocationUsingDeleteOnTheLocationController(String id){
		return Task.where(
		"{0} Delete a Location by Id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_LOCATION_USING_DELETE_ON_THE_LOCATION_CONTROLLER);
			}
		);
	}

	public static Performable updateLocationUsingPutOnTheLocationController(String id, Object body){
		return Task.where(
		"{0} Replace a Location by Id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put(UPDATE_LOCATION_USING_PUT_ON_THE_LOCATION_CONTROLLER);
			}
		);
	}

	public static Performable getCustomersByIdsUsingGetOnTheCustomerController(String pageSize, String page, String ids, String filter, String scope){
		return Task.where(
		"{0} Get Customers by IDs", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("ids", ids).queryParam("filter", filter).queryParam("scope", scope).get(GET_CUSTOMERS_BY_IDS_USING_GET_ON_THE_CUSTOMER_CONTROLLER);
			}
		);
	}

	public static Performable getLeadsUsingGetOnTheLeadController(String pageSize, String page, String isDisqualified, String searchTerm, String isDescending, String sortField, String ids){
		return Task.where(
		"{0} Get a Page of Leads", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("isDisqualified", isDisqualified).queryParam("searchTerm", searchTerm).queryParam("isDescending", isDescending).queryParam("sortField", sortField).queryParam("ids", ids).get(GET_LEADS_USING_GET_ON_THE_LEAD_CONTROLLER);
			}
		);
	}

	public static Performable convertLeadAccountToProspectUsingPostOnTheAccountController(String leadId){
		return Task.where(
		"{0} Convert Lead to Prospect", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("leadId", leadId).post(CONVERT_LEAD_ACCOUNT_TO_PROSPECT_USING_POST_ON_THE_ACCOUNT_CONTROLLER);
			}
		);
	}

	public static Performable patchLeadSourceUsingPatchOnTheLeadSourceController(String id, Object body){
		return Task.where(
		"{0} Patch lead source", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").pathParam("id", id).body(body).patch(PATCH_LEAD_SOURCE_USING_PATCH_ON_THE_LEAD_SOURCE_CONTROLLER);
			}
		);
	}

	public static Performable getLeadSourceByIdUsingGetOnTheLeadSourceController(String id){
		return Task.where(
		"{0} Get a lead source", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_LEAD_SOURCE_BY_ID_USING_GET_ON_THE_LEAD_SOURCE_CONTROLLER);
			}
		);
	}

	public static Performable deleteLeadSourceUsingDeleteOnTheLeadSourceController(String id){
		return Task.where(
		"{0} Delete lead source", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_LEAD_SOURCE_USING_DELETE_ON_THE_LEAD_SOURCE_CONTROLLER);
			}
		);
	}

	public static Performable updateLeadSourceUsingPutOnTheLeadSourceController(String id, Object body){
		return Task.where(
		"{0} Replace lead source", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put(UPDATE_LEAD_SOURCE_USING_PUT_ON_THE_LEAD_SOURCE_CONTROLLER);
			}
		);
	}

	public static Performable createCustomerUsingPostOnTheCustomerController(Object body){
		return Task.where(
		"{0} Create Customer", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_CUSTOMER_USING_POST_ON_THE_CUSTOMER_CONTROLLER);
			}
		);
	}

	public static Performable createLeadSourceUsingPostOnTheLeadSourceController(Object body){
		return Task.where(
		"{0} Post Lead Source", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_LEAD_SOURCE_USING_POST_ON_THE_LEAD_SOURCE_CONTROLLER);
			}
		);
	}

	public static Performable getRelatedAccountsUsingGetOnTheRelatedAccountController(String accountId, String pageSize, String page){
		return Task.where(
		"{0} Retrieve Related Accounts for a Customer", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("accountId", accountId).queryParam("pageSize", pageSize).queryParam("page", page).get(GET_RELATED_ACCOUNTS_USING_GET_ON_THE_RELATED_ACCOUNT_CONTROLLER);
			}
		);
	}

	public static Performable createDisqualificationUsingPostOnTheDisqualificationController(String leadId, Object body){
		return Task.where(
		"{0} Create a disqualification on a Lead", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("leadId", leadId).body(body).post(CREATE_DISQUALIFICATION_USING_POST_ON_THE_DISQUALIFICATION_CONTROLLER);
			}
		);
	}

	public static Performable deleteDisqualificationOnLeadUsingDeleteOnTheDisqualificationController(String leadId){
		return Task.where(
		"{0} Delete a disqualification on a Lead", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("leadId", leadId).delete(DELETE_DISQUALIFICATION_ON_LEAD_USING_DELETE_ON_THE_DISQUALIFICATION_CONTROLLER);
			}
		);
	}

	public static Performable updateDisqualificationOnLeadUsingPutOnTheDisqualificationController(String leadId, Object body){
		return Task.where(
		"{0} Update a disqualification on a Lead", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("leadId", leadId).body(body).put(UPDATE_DISQUALIFICATION_ON_LEAD_USING_PUT_ON_THE_DISQUALIFICATION_CONTROLLER);
			}
		);
	}



}
