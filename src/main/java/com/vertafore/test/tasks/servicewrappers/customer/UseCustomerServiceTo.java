package com.vertafore.test.tasks.servicewrappers.customer;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import static com.vertafore.test.abilities.CallTitanApi.as;
import static net.serenitybdd.rest.SerenityRest.rest;

public class UseCustomerServiceTo {

	private static final String GET_LEAD_SOURCES_BY_CONTEXT_USING_GET = "lead-sources";
	private static final String GET_DISQUALIFICATION_REASONS_BY_CONTEXT_USING_GET = "disqualification-reasons";
	private static final String CREATE_ACCOUNT_USING_POST = "accounts";
	private static final String CREATE_SERVICE_TEAM_MEMBER_USING_POST = "accounts/{accountId}/service-team-members";
	private static final String PATCH_CUSTOMER_USING_PATCH = "customers/{customerId}";
	private static final String GET_CUSTOMER_USING_GET = "customers/{customerId}";
	private static final String DELETE_CUSTOMER_USING_DELETE = "customers/{customerId}";
	private static final String UPDATE_CUSTOMER_USING_PUT = "customers/{customerId}";
	private static final String PATCH_LEAD_USING_PATCH = "leads/{leadId}";
	private static final String GET_LEAD_USING_GET = "leads/{leadId}";
	private static final String DELETE_LEAD_USING_DELETE = "leads/{leadId}";
	private static final String UPDATE_LEAD_USING_PUT = "leads/{leadId}";
	private static final String GET_LOCATIONS_PER_ACCOUNT_USING_GET = "accounts/{accountId}/locations";
	private static final String CONVERT_LEAD_USING_POST = "customers";
	private static final String GET_LEADS_BY_IDS_USING_GET = "leads?filter=byIds&scope=descendants{&pageSize,page,ids}";
	private static final String GET_CONTACTS_FOR_ACCOUNT_BY_ID_USING_GET = "accounts/{accountId}/contacts";
	private static final String CREATE_DISQUALIFICATION_REASON_USING_POST = "disqualification-reasons";
	private static final String CREATE_LEAD_USING_POST = "leads";
	private static final String GET_SERVICE_TEAM_MEMBERS_PER_ACCOUNT_USING_GET = "accounts/{accountId}/service-team-members";
	private static final String PATCH_ACCOUNT_USING_PATCH = "accounts/{id}";
	private static final String GET_ACCOUNT_BY_ID_USING_GET = "accounts/{id}";
	private static final String DELETE_ACCOUNT_USING_DELETE = "accounts/{id}";
	private static final String UPDATE_ACCOUNT_USING_PUT = "accounts/{id}";
	private static final String GET_ACCOUNTS_BY_IDS_USING_GET = "accounts?filter=byIds&scope=descendants{&pageSize,page,ids}";
	private static final String GET_CUSTOMERS_BY_SEARCH_TERM_USING_GET = "customers?filter=bySearchTerm&scope=descendants{&searchTerm,isDescending,sortField,pageSize,page}";
	private static final String PATCH_CONTACT_USING_PATCH = "contacts/{id}";
	private static final String GET_CONTACT_USING_GET = "contacts/{id}";
	private static final String DELETE_CONTACT_USING_DELETE = "contacts/{id}";
	private static final String UPDATE_CONTACT_USING_PUT = "contacts/{id}";
	private static final String SEARCH_LEADS_USING_GET = "leads?filter=bySearchTerm&scope=descendants{&searchTerm,isDescending,sortField,isDisqualified,pageSize,page}";
	private static final String CREATE_RELATED_ACCOUNT_USING_POST = "accounts/{accountId}/related-accounts";
	private static final String SEARCH_ACCOUNTS_USING_GET = "accounts?filter=bySearchTerm&scope=descendants{&searchTerm,isDescending,sortField,isDisqualified,pageSize,page}";
	private static final String PATCH_DISQUALIFICATION_REASON_USING_PATCH = "disqualification-reasons/{id}";
	private static final String GET_DISQUALIFICATION_REASON_BY_ID_USING_GET = "disqualification-reasons/{id}";
	private static final String DELETE_DISQUALIFICATION_REASON_USING_DELETE = "disqualification-reasons/{id}";
	private static final String UPDATE_DISQUALIFICATION_REASON_USING_PUT = "disqualification-reasons/{id}";
	private static final String DELETE_RELATED_ACCOUNT_USING_DELETE = "related-accounts/{id}";
	private static final String CREATE_CONTACT_USING_POST = "accounts/{accountId}/contacts";
	private static final String CREATE_LOCATION_USING_POST = "accounts/{accountId}/locations";
	private static final String PATCH_SERVICE_TEAM_MEMBER_USING_PATCH = "service-team-members/{id}";
	private static final String GET_SERVICE_TEAM_MEMBER_USING_GET = "service-team-members/{id}";
	private static final String DELETE_SERVICE_TEAM_MEMBER_USING_DELETE = "service-team-members/{id}";
	private static final String UPDATE_SERVICE_TEAM_MEMBER_USING_PUT = "service-team-members/{id}";
	private static final String PATCH_LOCATION_USING_PATCH = "locations/{id}";
	private static final String GET_LOCATION_USING_GET = "locations/{id}";
	private static final String DELETE_LOCATION_USING_DELETE = "locations/{id}";
	private static final String UPDATE_LOCATION_USING_PUT = "locations/{id}";
	private static final String GET_CUSTOMERS_BY_IDS_USING_GET = "customers?filter=byIds&scope=descendants{&pageSize,page,ids}";
	private static final String GET_LEADS_USING_GET = "leads";
	private static final String CONVERT_LEAD_ACCOUNT_TO_PROSPECT_USING_POST = "accounts";
	private static final String PATCH_LEAD_SOURCE_USING_PATCH = "lead-sources/{id}";
	private static final String GET_LEAD_SOURCE_BY_ID_USING_GET = "lead-sources/{id}";
	private static final String DELETE_LEAD_SOURCE_USING_DELETE = "lead-sources/{id}";
	private static final String UPDATE_LEAD_SOURCE_USING_PUT = "lead-sources/{id}";
	private static final String CREATE_CUSTOMER_USING_POST = "customers";
	private static final String CREATE_LEAD_SOURCE_USING_POST = "lead-sources";
	private static final String GET_RELATED_ACCOUNTS_USING_GET = "accounts/{accountId}/related-accounts";
	private static final String CREATE_DISQUALIFICATION_USING_POST = "leads/{leadId}/disqualification";
	private static final String DELETE_DISQUALIFICATION_ON_LEAD_USING_DELETE = "leads/{leadId}/disqualification";
	private static final String UPDATE_DISQUALIFICATION_ON_LEAD_USING_PUT = "leads/{leadId}/disqualification";

	public static Performable getLeadSourcesByContextUsingGet(String pageSize, String page){
		return Task.where(
		"{0} Get lead sources", 
			actor -> {
				rest().with().contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(as(actor).toEndpoint(GET_LEAD_SOURCES_BY_CONTEXT_USING_GET));
			}
		);
	}

	public static Performable getDisqualificationReasonsByContextUsingGet(String pageSize, String page){
		return Task.where(
		"{0} Get Disqualification Reasons", 
			actor -> {
				rest().with().contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(as(actor).toEndpoint(GET_DISQUALIFICATION_REASONS_BY_CONTEXT_USING_GET));
			}
		);
	}

	public static Performable createAccountUsingPost(Object body){
		return Task.where(
		"{0} Create Account (Customer/Lead)", 
			actor -> {
				rest().with().contentType("application/json").body(body).post(as(actor).toEndpoint(CREATE_ACCOUNT_USING_POST));
			}
		);
	}

	public static Performable createServiceTeamMemberUsingPost(String accountId, Object body){
		return Task.where(
		"{0} Post a ServiceTeamMember per Customer", 
			actor -> {
				rest().with().contentType("application/json").pathParam("accountId", accountId).body(body).post(as(actor).toEndpoint(CREATE_SERVICE_TEAM_MEMBER_USING_POST));
			}
		);
	}

	public static Performable patchCustomerUsingPatch(Object body, String customerId){
		return Task.where(
		"{0} Patch Customer", 
			actor -> {
				rest().with().contentType("application/json-patch+json").body(body).pathParam("customerId", customerId).patch(as(actor).toEndpoint(PATCH_CUSTOMER_USING_PATCH));
			}
		);
	}

	public static Performable getCustomerUsingGet(String customerId){
		return Task.where(
		"{0} Get Customer", 
			actor -> {
				rest().with().contentType("application/json").pathParam("customerId", customerId).get(as(actor).toEndpoint(GET_CUSTOMER_USING_GET));
			}
		);
	}

	public static Performable deleteCustomerUsingDelete(String customerId){
		return Task.where(
		"{0} Delete Customer", 
			actor -> {
				rest().with().contentType("application/json").pathParam("customerId", customerId).delete(as(actor).toEndpoint(DELETE_CUSTOMER_USING_DELETE));
			}
		);
	}

	public static Performable updateCustomerUsingPut(Object body, String customerId){
		return Task.where(
		"{0} Replace Customer", 
			actor -> {
				rest().with().contentType("application/json").body(body).pathParam("customerId", customerId).put(as(actor).toEndpoint(UPDATE_CUSTOMER_USING_PUT));
			}
		);
	}

	public static Performable patchLeadUsingPatch(Object body, String leadId){
		return Task.where(
		"{0} Patch Lead", 
			actor -> {
				rest().with().contentType("application/json-patch+json").body(body).pathParam("leadId", leadId).patch(as(actor).toEndpoint(PATCH_LEAD_USING_PATCH));
			}
		);
	}

	public static Performable getLeadUsingGet(String leadId){
		return Task.where(
		"{0} Get Lead", 
			actor -> {
				rest().with().contentType("application/json").pathParam("leadId", leadId).get(as(actor).toEndpoint(GET_LEAD_USING_GET));
			}
		);
	}

	public static Performable deleteLeadUsingDelete(String leadId){
		return Task.where(
		"{0} Delete Lead", 
			actor -> {
				rest().with().contentType("application/json").pathParam("leadId", leadId).delete(as(actor).toEndpoint(DELETE_LEAD_USING_DELETE));
			}
		);
	}

	public static Performable updateLeadUsingPut(Object body, String leadId){
		return Task.where(
		"{0} Replace Lead", 
			actor -> {
				rest().with().contentType("application/json").body(body).pathParam("leadId", leadId).put(as(actor).toEndpoint(UPDATE_LEAD_USING_PUT));
			}
		);
	}

	public static Performable getLocationsPerAccountUsingGet(String accountId, String pageSize, String page){
		return Task.where(
		"{0} Get a Page of Locations per Customer", 
			actor -> {
				rest().with().contentType("application/json").pathParam("accountId", accountId).queryParam("pageSize", pageSize).queryParam("page", page).get(as(actor).toEndpoint(GET_LOCATIONS_PER_ACCOUNT_USING_GET));
			}
		);
	}

	public static Performable convertLeadUsingPost(String convertLead){
		return Task.where(
		"{0} Convert Lead to Prospect", 
			actor -> {
				rest().with().contentType("application/json").queryParam("convertLead", convertLead).post(as(actor).toEndpoint(CONVERT_LEAD_USING_POST));
			}
		);
	}

	public static Performable getLeadsByIdsUsingGet(String pageSize, String page, String ids, String filter, String scope){
		return Task.where(
		"{0} Get Leads by IDs", 
			actor -> {
				rest().with().contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("ids", ids).queryParam("filter", filter).queryParam("scope", scope).get(as(actor).toEndpoint(GET_LEADS_BY_IDS_USING_GET));
			}
		);
	}

	public static Performable getContactsForAccountByIdUsingGet(String pageSize, String page, String accountId){
		return Task.where(
		"{0} Get a page of Contacts for Account by Id", 
			actor -> {
				rest().with().contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).pathParam("accountId", accountId).get(as(actor).toEndpoint(GET_CONTACTS_FOR_ACCOUNT_BY_ID_USING_GET));
			}
		);
	}

	public static Performable createDisqualificationReasonUsingPost(Object body){
		return Task.where(
		"{0} Post Disqualification Reason", 
			actor -> {
				rest().with().contentType("application/json").body(body).post(as(actor).toEndpoint(CREATE_DISQUALIFICATION_REASON_USING_POST));
			}
		);
	}

	public static Performable createLeadUsingPost(Object body){
		return Task.where(
		"{0} Create Lead", 
			actor -> {
				rest().with().contentType("application/json").body(body).post(as(actor).toEndpoint(CREATE_LEAD_USING_POST));
			}
		);
	}

	public static Performable getServiceTeamMembersPerAccountUsingGet(String accountId, String pageSize, String page){
		return Task.where(
		"{0} Get a Page of ServiceTeamMembers per Customer", 
			actor -> {
				rest().with().contentType("application/json").pathParam("accountId", accountId).queryParam("pageSize", pageSize).queryParam("page", page).get(as(actor).toEndpoint(GET_SERVICE_TEAM_MEMBERS_PER_ACCOUNT_USING_GET));
			}
		);
	}

	public static Performable patchAccountUsingPatch(Object body, String id){
		return Task.where(
		"{0} Patch Account (Customer/Lead)", 
			actor -> {
				rest().with().contentType("application/json-patch+json").body(body).pathParam("id", id).patch(as(actor).toEndpoint(PATCH_ACCOUNT_USING_PATCH));
			}
		);
	}

	public static Performable getAccountByIdUsingGet(String id){
		return Task.where(
		"{0} Get Account (Customer/Lead)", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).get(as(actor).toEndpoint(GET_ACCOUNT_BY_ID_USING_GET));
			}
		);
	}

	public static Performable deleteAccountUsingDelete(String id){
		return Task.where(
		"{0} Delete Account (Customer/Lead)", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).delete(as(actor).toEndpoint(DELETE_ACCOUNT_USING_DELETE));
			}
		);
	}

	public static Performable updateAccountUsingPut(Object body, String id){
		return Task.where(
		"{0} Replace Account (Customer/Lead)", 
			actor -> {
				rest().with().contentType("application/json").body(body).pathParam("id", id).put(as(actor).toEndpoint(UPDATE_ACCOUNT_USING_PUT));
			}
		);
	}

	public static Performable getAccountsByIdsUsingGet(String pageSize, String page, String ids, String filter, String scope){
		return Task.where(
		"{0} Get Accounts by IDs", 
			actor -> {
				rest().with().contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("ids", ids).queryParam("filter", filter).queryParam("scope", scope).get(as(actor).toEndpoint(GET_ACCOUNTS_BY_IDS_USING_GET));
			}
		);
	}

	public static Performable getCustomersBySearchTermUsingGet(String searchTerm, String isDescending, String sortField, String pageSize, String page, String filter, String scope){
		return Task.where(
		"{0} Get Customers by search term", 
			actor -> {
				rest().with().contentType("application/json").queryParam("searchTerm", searchTerm).queryParam("isDescending", isDescending).queryParam("sortField", sortField).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).queryParam("scope", scope).get(as(actor).toEndpoint(GET_CUSTOMERS_BY_SEARCH_TERM_USING_GET));
			}
		);
	}

	public static Performable patchContactUsingPatch(Object body, String id){
		return Task.where(
		"{0} Patch Contact", 
			actor -> {
				rest().with().contentType("application/json-patch+json").body(body).pathParam("id", id).patch(as(actor).toEndpoint(PATCH_CONTACT_USING_PATCH));
			}
		);
	}

	public static Performable getContactUsingGet(String id){
		return Task.where(
		"{0} Get Contact", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).get(as(actor).toEndpoint(GET_CONTACT_USING_GET));
			}
		);
	}

	public static Performable deleteContactUsingDelete(String id){
		return Task.where(
		"{0} Delete Contact", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).delete(as(actor).toEndpoint(DELETE_CONTACT_USING_DELETE));
			}
		);
	}

	public static Performable updateContactUsingPut(Object body, String id){
		return Task.where(
		"{0} Replace Contact", 
			actor -> {
				rest().with().contentType("application/json").body(body).pathParam("id", id).put(as(actor).toEndpoint(UPDATE_CONTACT_USING_PUT));
			}
		);
	}

	public static Performable searchLeadsUsingGet(String searchTerm, String isDescending, String sortField, String isDisqualified, String pageSize, String page, String filter, String scope){
		return Task.where(
		"{0} Get Leads by search term", 
			actor -> {
				rest().with().contentType("application/json").queryParam("searchTerm", searchTerm).queryParam("isDescending", isDescending).queryParam("sortField", sortField).queryParam("isDisqualified", isDisqualified).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).queryParam("scope", scope).get(as(actor).toEndpoint(SEARCH_LEADS_USING_GET));
			}
		);
	}

	public static Performable createRelatedAccountUsingPost(Object body, String accountId){
		return Task.where(
		"{0} Add Related Account", 
			actor -> {
				rest().with().contentType("application/json").body(body).pathParam("accountId", accountId).post(as(actor).toEndpoint(CREATE_RELATED_ACCOUNT_USING_POST));
			}
		);
	}

	public static Performable searchAccountsUsingGet(String searchTerm, String isDescending, String sortField, String isDisqualified, String pageSize, String page, String filter, String scope){
		return Task.where(
		"{0} Get Accounts by search term", 
			actor -> {
				rest().with().contentType("application/json").queryParam("searchTerm", searchTerm).queryParam("isDescending", isDescending).queryParam("sortField", sortField).queryParam("isDisqualified", isDisqualified).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).queryParam("scope", scope).get(as(actor).toEndpoint(SEARCH_ACCOUNTS_USING_GET));
			}
		);
	}

	public static Performable patchDisqualificationReasonUsingPatch(String id, Object body){
		return Task.where(
		"{0} Patch disqualification reason", 
			actor -> {
				rest().with().contentType("application/json-patch+json").pathParam("id", id).body(body).patch(as(actor).toEndpoint(PATCH_DISQUALIFICATION_REASON_USING_PATCH));
			}
		);
	}

	public static Performable getDisqualificationReasonByIdUsingGet(String id){
		return Task.where(
		"{0} Get a Disqualification Reason", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).get(as(actor).toEndpoint(GET_DISQUALIFICATION_REASON_BY_ID_USING_GET));
			}
		);
	}

	public static Performable deleteDisqualificationReasonUsingDelete(String id){
		return Task.where(
		"{0} Delete Disqualification Reason", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).delete(as(actor).toEndpoint(DELETE_DISQUALIFICATION_REASON_USING_DELETE));
			}
		);
	}

	public static Performable updateDisqualificationReasonUsingPut(String id, Object body){
		return Task.where(
		"{0} Replace disqualification reason", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).body(body).put(as(actor).toEndpoint(UPDATE_DISQUALIFICATION_REASON_USING_PUT));
			}
		);
	}

	public static Performable deleteRelatedAccountUsingDelete(String id){
		return Task.where(
		"{0} Delete relationship between customers", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).delete(as(actor).toEndpoint(DELETE_RELATED_ACCOUNT_USING_DELETE));
			}
		);
	}

	public static Performable createContactUsingPost(Object body, String accountId){
		return Task.where(
		"{0} Create Contact", 
			actor -> {
				rest().with().contentType("application/json").body(body).pathParam("accountId", accountId).post(as(actor).toEndpoint(CREATE_CONTACT_USING_POST));
			}
		);
	}

	public static Performable createLocationUsingPost(String accountId, Object body){
		return Task.where(
		"{0} Post a Location per Customer", 
			actor -> {
				rest().with().contentType("application/json").pathParam("accountId", accountId).body(body).post(as(actor).toEndpoint(CREATE_LOCATION_USING_POST));
			}
		);
	}

	public static Performable patchServiceTeamMemberUsingPatch(String id, Object body){
		return Task.where(
		"{0} Patch a ServiceTeamMember by Id", 
			actor -> {
				rest().with().contentType("application/json-patch+json").pathParam("id", id).body(body).patch(as(actor).toEndpoint(PATCH_SERVICE_TEAM_MEMBER_USING_PATCH));
			}
		);
	}

	public static Performable getServiceTeamMemberUsingGet(String id){
		return Task.where(
		"{0} Get a single ServiceTeamMember by ServiceTeamMember Id", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).get(as(actor).toEndpoint(GET_SERVICE_TEAM_MEMBER_USING_GET));
			}
		);
	}

	public static Performable deleteServiceTeamMemberUsingDelete(String id){
		return Task.where(
		"{0} Delete a ServiceTeamMember by Id", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).delete(as(actor).toEndpoint(DELETE_SERVICE_TEAM_MEMBER_USING_DELETE));
			}
		);
	}

	public static Performable updateServiceTeamMemberUsingPut(String id, Object body){
		return Task.where(
		"{0} Replace a ServiceTeamMember by Id", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).body(body).put(as(actor).toEndpoint(UPDATE_SERVICE_TEAM_MEMBER_USING_PUT));
			}
		);
	}

	public static Performable patchLocationUsingPatch(String id, Object body){
		return Task.where(
		"{0} Patch a Location by Id", 
			actor -> {
				rest().with().contentType("application/json-patch+json").pathParam("id", id).body(body).patch(as(actor).toEndpoint(PATCH_LOCATION_USING_PATCH));
			}
		);
	}

	public static Performable getLocationUsingGet(String id){
		return Task.where(
		"{0} Get a single Location by Location Id", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).get(as(actor).toEndpoint(GET_LOCATION_USING_GET));
			}
		);
	}

	public static Performable deleteLocationUsingDelete(String id){
		return Task.where(
		"{0} Delete a Location by Id", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).delete(as(actor).toEndpoint(DELETE_LOCATION_USING_DELETE));
			}
		);
	}

	public static Performable updateLocationUsingPut(String id, Object body){
		return Task.where(
		"{0} Replace a Location by Id", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).body(body).put(as(actor).toEndpoint(UPDATE_LOCATION_USING_PUT));
			}
		);
	}

	public static Performable getCustomersByIdsUsingGet(String pageSize, String page, String ids, String filter, String scope){
		return Task.where(
		"{0} Get Customers by IDs", 
			actor -> {
				rest().with().contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("ids", ids).queryParam("filter", filter).queryParam("scope", scope).get(as(actor).toEndpoint(GET_CUSTOMERS_BY_IDS_USING_GET));
			}
		);
	}

	public static Performable getLeadsUsingGet(String pageSize, String page, String isDisqualified, String searchTerm, String isDescending, String sortField, String ids){
		return Task.where(
		"{0} Get a Page of Leads", 
			actor -> {
				rest().with().contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("isDisqualified", isDisqualified).queryParam("searchTerm", searchTerm).queryParam("isDescending", isDescending).queryParam("sortField", sortField).queryParam("ids", ids).get(as(actor).toEndpoint(GET_LEADS_USING_GET));
			}
		);
	}

	public static Performable convertLeadAccountToProspectUsingPost(String leadId){
		return Task.where(
		"{0} Convert Lead to Prospect", 
			actor -> {
				rest().with().contentType("application/json").queryParam("leadId", leadId).post(as(actor).toEndpoint(CONVERT_LEAD_ACCOUNT_TO_PROSPECT_USING_POST));
			}
		);
	}

	public static Performable patchLeadSourceUsingPatch(String id, Object body){
		return Task.where(
		"{0} Patch lead source", 
			actor -> {
				rest().with().contentType("application/json-patch+json").pathParam("id", id).body(body).patch(as(actor).toEndpoint(PATCH_LEAD_SOURCE_USING_PATCH));
			}
		);
	}

	public static Performable getLeadSourceByIdUsingGet(String id){
		return Task.where(
		"{0} Get a lead source", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).get(as(actor).toEndpoint(GET_LEAD_SOURCE_BY_ID_USING_GET));
			}
		);
	}

	public static Performable deleteLeadSourceUsingDelete(String id){
		return Task.where(
		"{0} Delete lead source", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).delete(as(actor).toEndpoint(DELETE_LEAD_SOURCE_USING_DELETE));
			}
		);
	}

	public static Performable updateLeadSourceUsingPut(String id, Object body){
		return Task.where(
		"{0} Replace lead source", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).body(body).put(as(actor).toEndpoint(UPDATE_LEAD_SOURCE_USING_PUT));
			}
		);
	}

	public static Performable createCustomerUsingPost(Object body){
		return Task.where(
		"{0} Create Customer", 
			actor -> {
				rest().with().contentType("application/json").body(body).post(as(actor).toEndpoint(CREATE_CUSTOMER_USING_POST));
			}
		);
	}

	public static Performable createLeadSourceUsingPost(Object body){
		return Task.where(
		"{0} Post Lead Source", 
			actor -> {
				rest().with().contentType("application/json").body(body).post(as(actor).toEndpoint(CREATE_LEAD_SOURCE_USING_POST));
			}
		);
	}

	public static Performable getRelatedAccountsUsingGet(String accountId, String pageSize, String page){
		return Task.where(
		"{0} Retrieve Related Accounts for a Customer", 
			actor -> {
				rest().with().contentType("application/json").pathParam("accountId", accountId).queryParam("pageSize", pageSize).queryParam("page", page).get(as(actor).toEndpoint(GET_RELATED_ACCOUNTS_USING_GET));
			}
		);
	}

	public static Performable createDisqualificationUsingPost(String leadId, Object body){
		return Task.where(
		"{0} Create a disqualification on a Lead", 
			actor -> {
				rest().with().contentType("application/json").pathParam("leadId", leadId).body(body).post(as(actor).toEndpoint(CREATE_DISQUALIFICATION_USING_POST));
			}
		);
	}

	public static Performable deleteDisqualificationOnLeadUsingDelete(String leadId){
		return Task.where(
		"{0} Delete a disqualification on a Lead", 
			actor -> {
				rest().with().contentType("application/json").pathParam("leadId", leadId).delete(as(actor).toEndpoint(DELETE_DISQUALIFICATION_ON_LEAD_USING_DELETE));
			}
		);
	}

	public static Performable updateDisqualificationOnLeadUsingPut(String leadId, Object body){
		return Task.where(
		"{0} Update a disqualification on a Lead", 
			actor -> {
				rest().with().contentType("application/json").pathParam("leadId", leadId).body(body).put(as(actor).toEndpoint(UPDATE_DISQUALIFICATION_ON_LEAD_USING_PUT));
			}
		);
	}



}
