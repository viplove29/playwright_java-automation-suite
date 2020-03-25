package com.vertafore.test.tasks.servicewrappers.company;

import net.serenitybdd.screenplay.Performable;
import com.vertafore.test.abilities.CallTitanApi;
import net.serenitybdd.screenplay.Task;

/**
* This class was automatically generated on 2020/03/25 14:48:10

*/
public class UseCompanyServiceTo {

	private static final String THIS_SERVICE = "company";

	public static Performable getCompanyByAmBestIdUsingGetOnTheCompanyController(String amBestId, String lookupBy){
		return Task.where(
		"{0} Find company by AmBest ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("amBestId", amBestId).queryParam("lookupBy", lookupBy).get("companies/{amBestId}?lookupBy=amBestId");
			}
		);
	}

	public static Performable getCompanyUsingGetOnTheCompanyController(String id, String lookupBy){
		return Task.where(
		"{0} Get Company", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).queryParam("lookupBy", lookupBy).get("companies/{id}?lookupBy=id");
			}
		);
	}

	public static Performable riskmatchUpdateCompanyUsingGetOnTheCompanyController(String id){
		return Task.where(
		"{0} RiskMatch Update Sync", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get("companies/riskmatch/{id}");
			}
		);
	}

	public static Performable getCompanyChildrenByRolesUsingGetOnTheCompanyController(String id, String primaryRoles, String pageSize, String page, String filter){
		return Task.where(
		"{0} Retrieve company children by role", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).queryParam("primaryRoles", primaryRoles).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("companies/{id}/children?filter=byPrimaryRoles");
			}
		);
	}

	public static Performable getCompanyParentUsingGetOnTheCompanyController(String id){
		return Task.where(
		"{0} Retrieve company parent", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get("companies/{id}/parent");
			}
		);
	}

	public static Performable findCompaniesByPrimaryRolesUsingGetOnTheCompanyController(String primaryRoles, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find companies by primary role", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("primaryRoles", primaryRoles).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("companies?filter=byPrimaryRoles");
			}
		);
	}

	public static Performable findCompaniesByNameAndPrimaryRolesAndTopLevelOnlyUsingGetOnTheCompanyController(String name, String primaryRoles, String topLevelOnly, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find companies by name and roles", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("name", name).queryParam("primaryRoles", primaryRoles).queryParam("topLevelOnly", topLevelOnly).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("companies?filter=byNameAndPrimaryRolesAndTopLevel");
			}
		);
	}

	public static Performable getCompanyByAiicUsingGetOnTheCompanyController(String aiic, String lookupBy){
		return Task.where(
		"{0} Find company by AIIC", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("aiic", aiic).queryParam("lookupBy", lookupBy).get("companies/{aiic}?lookupBy=aiic");
			}
		);
	}

	public static Performable getCompanyByNaicUsingGetOnTheCompanyController(String naic, String lookupBy){
		return Task.where(
		"{0} Find company by NAIC", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("naic", naic).queryParam("lookupBy", lookupBy).get("companies/{naic}?lookupBy=naic");
			}
		);
	}

	public static Performable createCompanyUsingPostOnTheCompanyController(Object body){
		return Task.where(
		"{0} Create Company", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("companies");
			}
		);
	}

	public static Performable getCompanyChildrenUsingGetOnTheCompanyController(String id, String pageSize, String page){
		return Task.where(
		"{0} Retrieve company children", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).queryParam("pageSize", pageSize).queryParam("page", page).get("companies/{id}/children");
			}
		);
	}

	public static Performable findCompaniesByNameUsingGetOnTheCompanyController(String name, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find companies by name", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("name", name).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("companies?filter=byName");
			}
		);
	}

	public static Performable getCompaniesUsingGetOnTheCompanyController(String pageSize, String page){
		return Task.where(
		"{0} Get all companies", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get("companies");
			}
		);
	}

	public static Performable findCompanyByAmBestCarrierNameUsingGetOnTheCompanyController(String amBestCarrierName, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find company by AmBest Carrier Name", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("amBestCarrierName", amBestCarrierName).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("companies?filter=byAmBestCarrierName");
			}
		);
	}

	public static Performable findCompaniesByAmBestRatingUsingGetOnTheCompanyController(String amBestRating, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find companies by AM Best rating", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("amBestRating", amBestRating).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("companies?filter=byAmBestRating");
			}
		);
	}

	public static Performable findCompaniesByAmBestDomicileUsingGetOnTheCompanyController(String amBestDomicile, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find companies by AM Best domicile", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("amBestDomicile", amBestDomicile).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("companies?filter=byAmBestDomicile");
			}
		);
	}

	public static Performable findCompaniesByIdsUsingGetOnTheCompanyController(String ids, String pageSize, String page, String filter){
		return Task.where(
		"{0} Retrieve Companies by ids", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("ids", ids).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("companies?filter=byIds");
			}
		);
	}

	public static Performable findCompaniesByAdmittedStatesUsingGetOnTheCompanyController(String admittedStates, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find companies by admitted states", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("admittedStates", admittedStates).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("companies?filter=byAdmittedStates");
			}
		);
	}

	public static Performable updateCompanyUsingPutOnTheCompanyController(String id, Object body){
		return Task.where(
		"{0} Replace Company", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put("companies/{id}");
			}
		);
	}

	public static Performable deleteCompanyUsingDeleteOnTheCompanyController(String id){
		return Task.where(
		"{0} Delete a company.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete("companies/{id}");
			}
		);
	}

	public static Performable findCompaniesByBusinessTypeUsingGetOnTheCompanyController(String businessType, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find company by business type", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("businessType", businessType).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("companies?filter=byBusinessType");
			}
		);
	}

	public static Performable getCompanyByFeinUsingGetOnTheCompanyController(String fein, String lookupBy){
		return Task.where(
		"{0} Find company by FEIN", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("fein", fein).queryParam("lookupBy", lookupBy).get("companies/{fein}?lookupBy=fein");
			}
		);
	}

	public static Performable findCompaniesByNameOrNaicAndPrimaryRolesAndTopLevelUsingGetOnTheCompanyController(String nameOrNaic, String primaryRoles, String topLevelOnly, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find companies by name or naic and primary roles", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("nameOrNaic", nameOrNaic).queryParam("primaryRoles", primaryRoles).queryParam("topLevelOnly", topLevelOnly).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("companies?filter=byNameOrNaicAndPrimaryRolesAndTopLevel");
			}
		);
	}



}
