package com.vertafore.test.tasks.servicewrappers.company;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import com.vertafore.test.abilities.CallTitanApi;

/**
* This class was automatically generated on 2020/03/25 13:35:07

*/
public class UseCompanyServiceTo {

	private static final String THIS_SERVICE = "company";
	private static final String GET_COMPANY_BY_FEIN_USING_GET_ON_THE_COMPANY_CONTROLLER = "companies/{fein}?lookupBy=fein";
	private static final String FIND_COMPANIES_BY_AM_BEST_DOMICILE_USING_GET_ON_THE_COMPANY_CONTROLLER = "companies?filter=byAmBestDomicile";
	private static final String FIND_COMPANIES_BY_IDS_USING_GET_ON_THE_COMPANY_CONTROLLER = "companies?filter=byIds";
	private static final String FIND_COMPANIES_BY_PRIMARY_ROLES_USING_GET_ON_THE_COMPANY_CONTROLLER = "companies?filter=byPrimaryRoles";
	private static final String GET_COMPANIES_USING_GET_ON_THE_COMPANY_CONTROLLER = "companies";
	private static final String FIND_COMPANIES_BY_NAME_OR_NAIC_AND_PRIMARY_ROLES_AND_TOP_LEVEL_USING_GET_ON_THE_COMPANY_CONTROLLER = "companies?filter=byNameOrNaicAndPrimaryRolesAndTopLevel";
	private static final String GET_COMPANY_CHILDREN_BY_ROLES_USING_GET_ON_THE_COMPANY_CONTROLLER = "companies/{id}/children?filter=byPrimaryRoles";
	private static final String FIND_COMPANIES_BY_BUSINESS_TYPE_USING_GET_ON_THE_COMPANY_CONTROLLER = "companies?filter=byBusinessType";
	private static final String GET_COMPANY_PARENT_USING_GET_ON_THE_COMPANY_CONTROLLER = "companies/{id}/parent";
	private static final String FIND_COMPANIES_BY_NAME_USING_GET_ON_THE_COMPANY_CONTROLLER = "companies?filter=byName";
	private static final String GET_COMPANY_BY_AIIC_USING_GET_ON_THE_COMPANY_CONTROLLER = "companies/{aiic}?lookupBy=aiic";
	private static final String GET_COMPANY_USING_GET_ON_THE_COMPANY_CONTROLLER = "companies/{id}?lookupBy=id";
	private static final String FIND_COMPANIES_BY_ADMITTED_STATES_USING_GET_ON_THE_COMPANY_CONTROLLER = "companies?filter=byAdmittedStates";
	private static final String GET_COMPANY_BY_NAIC_USING_GET_ON_THE_COMPANY_CONTROLLER = "companies/{naic}?lookupBy=naic";
	private static final String FIND_COMPANY_BY_AM_BEST_CARRIER_NAME_USING_GET_ON_THE_COMPANY_CONTROLLER = "companies?filter=byAmBestCarrierName";
	private static final String GET_COMPANY_BY_AM_BEST_ID_USING_GET_ON_THE_COMPANY_CONTROLLER = "companies/{amBestId}?lookupBy=amBestId";
	private static final String CREATE_COMPANY_USING_POST_ON_THE_COMPANY_CONTROLLER = "companies";
	private static final String FIND_COMPANIES_BY_AM_BEST_RATING_USING_GET_ON_THE_COMPANY_CONTROLLER = "companies?filter=byAmBestRating";
	private static final String DELETE_COMPANY_USING_DELETE_ON_THE_COMPANY_CONTROLLER = "companies/{id}";
	private static final String UPDATE_COMPANY_USING_PUT_ON_THE_COMPANY_CONTROLLER = "companies/{id}";
	private static final String GET_COMPANY_CHILDREN_USING_GET_ON_THE_COMPANY_CONTROLLER = "companies/{id}/children";
	private static final String FIND_COMPANIES_BY_NAME_AND_PRIMARY_ROLES_AND_TOP_LEVEL_ONLY_USING_GET_ON_THE_COMPANY_CONTROLLER = "companies?filter=byNameAndPrimaryRolesAndTopLevel";
	private static final String RISKMATCH_UPDATE_COMPANY_USING_GET_ON_THE_COMPANY_CONTROLLER = "companies/riskmatch/{id}";

	public static Performable getCompanyByFeinUsingGetOnTheCompanyController(String fein, String lookupBy){
		return Task.where(
		"{0} Find company by FEIN", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("fein", fein).queryParam("lookupBy", lookupBy).get(GET_COMPANY_BY_FEIN_USING_GET_ON_THE_COMPANY_CONTROLLER);
			}
		);
	}

	public static Performable findCompaniesByAmBestDomicileUsingGetOnTheCompanyController(String amBestDomicile, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find companies by AM Best domicile", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("amBestDomicile", amBestDomicile).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(FIND_COMPANIES_BY_AM_BEST_DOMICILE_USING_GET_ON_THE_COMPANY_CONTROLLER);
			}
		);
	}

	public static Performable findCompaniesByIdsUsingGetOnTheCompanyController(String ids, String pageSize, String page, String filter){
		return Task.where(
		"{0} Retrieve Companies by ids", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("ids", ids).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(FIND_COMPANIES_BY_IDS_USING_GET_ON_THE_COMPANY_CONTROLLER);
			}
		);
	}

	public static Performable findCompaniesByPrimaryRolesUsingGetOnTheCompanyController(String primaryRoles, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find companies by primary role", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("primaryRoles", primaryRoles).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(FIND_COMPANIES_BY_PRIMARY_ROLES_USING_GET_ON_THE_COMPANY_CONTROLLER);
			}
		);
	}

	public static Performable getCompaniesUsingGetOnTheCompanyController(String pageSize, String page){
		return Task.where(
		"{0} Get all companies", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(GET_COMPANIES_USING_GET_ON_THE_COMPANY_CONTROLLER);
			}
		);
	}

	public static Performable findCompaniesByNameOrNaicAndPrimaryRolesAndTopLevelUsingGetOnTheCompanyController(String nameOrNaic, String primaryRoles, String topLevelOnly, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find companies by name or naic and primary roles", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("nameOrNaic", nameOrNaic).queryParam("primaryRoles", primaryRoles).queryParam("topLevelOnly", topLevelOnly).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(FIND_COMPANIES_BY_NAME_OR_NAIC_AND_PRIMARY_ROLES_AND_TOP_LEVEL_USING_GET_ON_THE_COMPANY_CONTROLLER);
			}
		);
	}

	public static Performable getCompanyChildrenByRolesUsingGetOnTheCompanyController(String id, String primaryRoles, String pageSize, String page, String filter){
		return Task.where(
		"{0} Retrieve company children by role", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).queryParam("primaryRoles", primaryRoles).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_COMPANY_CHILDREN_BY_ROLES_USING_GET_ON_THE_COMPANY_CONTROLLER);
			}
		);
	}

	public static Performable findCompaniesByBusinessTypeUsingGetOnTheCompanyController(String businessType, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find company by business type", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("businessType", businessType).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(FIND_COMPANIES_BY_BUSINESS_TYPE_USING_GET_ON_THE_COMPANY_CONTROLLER);
			}
		);
	}

	public static Performable getCompanyParentUsingGetOnTheCompanyController(String id){
		return Task.where(
		"{0} Retrieve company parent", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_COMPANY_PARENT_USING_GET_ON_THE_COMPANY_CONTROLLER);
			}
		);
	}

	public static Performable findCompaniesByNameUsingGetOnTheCompanyController(String name, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find companies by name", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("name", name).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(FIND_COMPANIES_BY_NAME_USING_GET_ON_THE_COMPANY_CONTROLLER);
			}
		);
	}

	public static Performable getCompanyByAiicUsingGetOnTheCompanyController(String aiic, String lookupBy){
		return Task.where(
		"{0} Find company by AIIC", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("aiic", aiic).queryParam("lookupBy", lookupBy).get(GET_COMPANY_BY_AIIC_USING_GET_ON_THE_COMPANY_CONTROLLER);
			}
		);
	}

	public static Performable getCompanyUsingGetOnTheCompanyController(String id, String lookupBy){
		return Task.where(
		"{0} Get Company", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).queryParam("lookupBy", lookupBy).get(GET_COMPANY_USING_GET_ON_THE_COMPANY_CONTROLLER);
			}
		);
	}

	public static Performable findCompaniesByAdmittedStatesUsingGetOnTheCompanyController(String admittedStates, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find companies by admitted states", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("admittedStates", admittedStates).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(FIND_COMPANIES_BY_ADMITTED_STATES_USING_GET_ON_THE_COMPANY_CONTROLLER);
			}
		);
	}

	public static Performable getCompanyByNaicUsingGetOnTheCompanyController(String naic, String lookupBy){
		return Task.where(
		"{0} Find company by NAIC", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("naic", naic).queryParam("lookupBy", lookupBy).get(GET_COMPANY_BY_NAIC_USING_GET_ON_THE_COMPANY_CONTROLLER);
			}
		);
	}

	public static Performable findCompanyByAmBestCarrierNameUsingGetOnTheCompanyController(String amBestCarrierName, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find company by AmBest Carrier Name", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("amBestCarrierName", amBestCarrierName).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(FIND_COMPANY_BY_AM_BEST_CARRIER_NAME_USING_GET_ON_THE_COMPANY_CONTROLLER);
			}
		);
	}

	public static Performable getCompanyByAmBestIdUsingGetOnTheCompanyController(String amBestId, String lookupBy){
		return Task.where(
		"{0} Find company by AmBest ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("amBestId", amBestId).queryParam("lookupBy", lookupBy).get(GET_COMPANY_BY_AM_BEST_ID_USING_GET_ON_THE_COMPANY_CONTROLLER);
			}
		);
	}

	public static Performable createCompanyUsingPostOnTheCompanyController(Object body){
		return Task.where(
		"{0} Create Company", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_COMPANY_USING_POST_ON_THE_COMPANY_CONTROLLER);
			}
		);
	}

	public static Performable findCompaniesByAmBestRatingUsingGetOnTheCompanyController(String amBestRating, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find companies by AM Best rating", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("amBestRating", amBestRating).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(FIND_COMPANIES_BY_AM_BEST_RATING_USING_GET_ON_THE_COMPANY_CONTROLLER);
			}
		);
	}

	public static Performable deleteCompanyUsingDeleteOnTheCompanyController(String id){
		return Task.where(
		"{0} Delete a company.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_COMPANY_USING_DELETE_ON_THE_COMPANY_CONTROLLER);
			}
		);
	}

	public static Performable updateCompanyUsingPutOnTheCompanyController(String id, Object body){
		return Task.where(
		"{0} Replace Company", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put(UPDATE_COMPANY_USING_PUT_ON_THE_COMPANY_CONTROLLER);
			}
		);
	}

	public static Performable getCompanyChildrenUsingGetOnTheCompanyController(String id, String pageSize, String page){
		return Task.where(
		"{0} Retrieve company children", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).queryParam("pageSize", pageSize).queryParam("page", page).get(GET_COMPANY_CHILDREN_USING_GET_ON_THE_COMPANY_CONTROLLER);
			}
		);
	}

	public static Performable findCompaniesByNameAndPrimaryRolesAndTopLevelOnlyUsingGetOnTheCompanyController(String name, String primaryRoles, String topLevelOnly, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find companies by name and roles", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("name", name).queryParam("primaryRoles", primaryRoles).queryParam("topLevelOnly", topLevelOnly).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(FIND_COMPANIES_BY_NAME_AND_PRIMARY_ROLES_AND_TOP_LEVEL_ONLY_USING_GET_ON_THE_COMPANY_CONTROLLER);
			}
		);
	}

	public static Performable riskmatchUpdateCompanyUsingGetOnTheCompanyController(String id){
		return Task.where(
		"{0} RiskMatch Update Sync", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(RISKMATCH_UPDATE_COMPANY_USING_GET_ON_THE_COMPANY_CONTROLLER);
			}
		);
	}



}
