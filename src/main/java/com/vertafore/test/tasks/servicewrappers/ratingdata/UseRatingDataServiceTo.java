package com.vertafore.test.tasks.servicewrappers.ratingdata;

import net.serenitybdd.screenplay.Performable;
import com.vertafore.test.abilities.CallTitanApi;
import net.serenitybdd.screenplay.Task;

/**
* This class was automatically generated on 2020/03/25 14:48:19

*/
public class UseRatingDataServiceTo {

	private static final String THIS_SERVICE = "rating-data";

	public static Performable getAllRatingUnitRatingDatasUsingGetOnTheRatingUnitRatingDataController(String pageSize, String page){
		return Task.where(
		"{0} Get all RatingUnitRatingData", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get("rating-data/units");
			}
		);
	}

	public static Performable createValueListItemUsingPostOnTheValueListController(String id, Object body){
		return Task.where(
		"{0} Create ValueListItem", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).post("value-lists/{id}/items");
			}
		);
	}

	public static Performable getAllApiTypesUsingGetOnTheApiTypeController(String pageSize, String page){
		return Task.where(
		"{0} Get all ApiTypes", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get("api-types");
			}
		);
	}

	public static Performable getAllRulesUsingGetOnTheRuleController(String pageSize, String page){
		return Task.where(
		"{0} Get all Rules", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get("rules");
			}
		);
	}

	public static Performable getAllCategoriesUsingGetOnTheCategoryController(String pageSize, String page){
		return Task.where(
		"{0} Get all Categories", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get("categories");
			}
		);
	}

	public static Performable updateCategoryUsingPutOnTheCategoryController(String id, Object body){
		return Task.where(
		"{0} Update Category", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put("category/{id}");
			}
		);
	}

	public static Performable getProvisionedRatingUnitsUsingGetOnTheRatingUnitController(String pageSize, String page, String filter, String scope){
		return Task.where(
		"{0} Get all Provisioned RatingUnits", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).queryParam("scope", scope).get("rating-units?filter=byProvisioned&scope=ancestors");
			}
		);
	}

	public static Performable updateRatingUnitRatingDataUsingPutOnTheRatingUnitRatingDataController(String id, Object body){
		return Task.where(
		"{0} Update RatingUnitRatingData", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put("rating-data/{ratingDataId}/unit/{id}");
			}
		);
	}

	public static Performable getRatingUnitsByNameUsingGetOnTheProvisionedUnitController(String name, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get ProvisionedUnits by Name", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("name", name).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("provisioned-units?filter=byName");
			}
		);
	}

	public static Performable updateRuleUsingPutOnTheRuleController(String id, Object body){
		return Task.where(
		"{0} Update Rule", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put("rules/{id}");
			}
		);
	}

	public static Performable getRatingUnitsByStatusUsingGetOnTheRatingUnitController(String status, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get RatingUnits by Status", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("status", status).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("rating-units?filter=byStatus");
			}
		);
	}

	public static Performable getRatingDataUsingGetOnTheRatingDataController(String id, String version){
		return Task.where(
		"{0} Get RatingData by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).queryParam("version", version).get("rating-data/{id}");
			}
		);
	}

	public static Performable getAllApiVersionsUsingGetOnTheApiVersionController(String pageSize, String page){
		return Task.where(
		"{0} Get all ApiVersions", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get("api-versions");
			}
		);
	}

	public static Performable findValueListsByRatingDataIdUsingGetOnTheValueListController(String ratingDataId, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find ValueLists by RatingData ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("ratingDataId", ratingDataId).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("value-lists?filter=ratingDataId");
			}
		);
	}

	public static Performable getRatingUnitsByLobUsingGetOnTheRatingUnitController(String lob, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get RatingUnits by LOB", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("lob", lob).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("rating-units?filter=byLob");
			}
		);
	}

	public static Performable findRatingDatasByTagUsingGetOnTheRatingDataController(String tag, String version, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find RatingDatas by Tag", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("tag", tag).queryParam("version", version).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("rating-data?filter=byTag");
			}
		);
	}

	public static Performable getProvisionedUnitsByNameUsingGetOnTheRatingUnitController(String name, String pageSize, String page, String filter, String scope){
		return Task.where(
		"{0} Get Provisioned RatingUnits by Name", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("name", name).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).queryParam("scope", scope).get("rating-units?filter=byProvisionedAndName&scope=ancestors");
			}
		);
	}

	public static Performable getAllApiPropertiesUsingGetOnTheApiPropertyController(String pageSize, String page){
		return Task.where(
		"{0} Get all ApiProperties", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get("api-properties");
			}
		);
	}

	public static Performable getRatingUnitsByStateUsingGetOnTheRatingUnitController(String state, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get RatingUnits by State", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("state", state).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("rating-units?filter=byState");
			}
		);
	}

	public static Performable getCategoryByIdUsingGetOnTheCategoryController(String id){
		return Task.where(
		"{0} Get Category by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get("category/{id}");
			}
		);
	}

	public static Performable findByRatingDataIdUsingGetOnTheRatingUnitRatingDataController(String ratingDataId, String pageSize, String page){
		return Task.where(
		"{0} Find RatingUnitRatingData by RatingData ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("ratingDataId", ratingDataId).queryParam("pageSize", pageSize).queryParam("page", page).get("rating-data/{ratingDataId}/units");
			}
		);
	}

	public static Performable deleteRuleUsingDeleteOnTheRuleController(String id){
		return Task.where(
		"{0} Delete Rule", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete("rules/{id}");
			}
		);
	}

	public static Performable getRatingUnitByIdUsingGetOnTheProvisionedUnitController(String id){
		return Task.where(
		"{0} Get ProvisionedUnit by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get("provisioned-units/{id}");
			}
		);
	}

	public static Performable getRatingunitByCompanyIdAndPlanIdOnTheRatingUnitController(String companyId, String planId, String filter){
		return Task.where(
		"{0} Get RatingUnit by Company ID and Plan ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("companyId", companyId).queryParam("planId", planId).queryParam("filter", filter).get("rating-units?filter=byCompanyIdAndPlanId");
			}
		);
	}

	public static Performable getRatingunitByIdOnTheRatingUnitController(String id){
		return Task.where(
		"{0} Get RatingUnit by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get("rating-units/{id}");
			}
		);
	}

	public static Performable getProvisionedUnitsByStatusUsingGetOnTheRatingUnitController(String status, String pageSize, String page, String filter, String scope){
		return Task.where(
		"{0} Get Provisioned RatingUnits by Status", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("status", status).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).queryParam("scope", scope).get("rating-units?filter=byProvisionedAndStatus&scope=ancestors");
			}
		);
	}

	public static Performable getAllRatingDatasUsingGetOnTheRatingDataController(String pageSize, String page){
		return Task.where(
		"{0} Get all RatingData", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get("rating-data");
			}
		);
	}

	public static Performable getRatingunitsByNameOnTheRatingUnitController(String name, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get RatingUnits by Name", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("name", name).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("rating-units?filter=byName");
			}
		);
	}

	public static Performable createValueListUsingPostOnTheValueListController(Object body){
		return Task.where(
		"{0} Create ValueList", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("value-lists");
			}
		);
	}

	public static Performable getAllRuleActionsUsingGetOnTheRuleActionController(String pageSize, String page){
		return Task.where(
		"{0} Get all RuleActions", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get("rule-actions");
			}
		);
	}

	public static Performable findRatingDataByByApiTypeNameApiPropertyNameUsingGetOnTheRatingDataController(String apiTypeName, String apiPropertyName, String version, String filter){
		return Task.where(
		"{0} Find RatingData by ApiType Name and ApiProperty Name", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("apiTypeName", apiTypeName).queryParam("apiPropertyName", apiPropertyName).queryParam("version", version).queryParam("filter", filter).get("rating-data?filter=byApiTypeNameApiPropertyName");
			}
		);
	}

	public static Performable getProvisionedUnitsByStateUsingGetOnTheRatingUnitController(String state, String pageSize, String page, String filter, String scope){
		return Task.where(
		"{0} Get Provisioned RatingUnits by State", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("state", state).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).queryParam("scope", scope).get("rating-units?filter=byProvisionedAndState&scope=ancestors");
			}
		);
	}

	public static Performable createRatingDataUsingPostOnTheRatingDataController(Object body){
		return Task.where(
		"{0} Create RatingData", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("rating-data");
			}
		);
	}

	public static Performable deleteValueListItemUsingDeleteOnTheValueListController(String itemId){
		return Task.where(
		"{0} Delete ValueListItem", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("itemId", itemId).delete("value-lists/{id}/items/{itemId}");
			}
		);
	}

	public static Performable deleteRatingUnitRatingDataUsingDeleteOnTheRatingUnitRatingDataController(String id){
		return Task.where(
		"{0} Delete RatingUnitRatingData", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete("rating-data/{ratingDataId}/unit/{id}");
			}
		);
	}

	public static Performable createCategoryUsingPostOnTheCategoryController(Object body){
		return Task.where(
		"{0} Create Category", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("category");
			}
		);
	}

	public static Performable deleteApiVersionUsingDeleteOnTheApiVersionController(String id){
		return Task.where(
		"{0} Delete ApiVersion", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete("api-version/{id}");
			}
		);
	}

	public static Performable updateRatingUnitUsingPutOnTheRatingUnitController(String id, Object body){
		return Task.where(
		"{0} Update RatingUnit", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put("rating-units/{id}");
			}
		);
	}

	public static Performable getValueListByIdUsingGetOnTheValueListController(String id){
		return Task.where(
		"{0} Get ValueList by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get("value-lists/{id}");
			}
		);
	}

	public static Performable findRatingDatasByIdsUsingGetOnTheRatingDataController(String ids, String version, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find RatingDatas by IDs", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("ids", ids).queryParam("version", version).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("rating-data?filter=byIds");
			}
		);
	}

	public static Performable updateApiVersionUsingPutOnTheApiVersionController(String id, Object body){
		return Task.where(
		"{0} Update ApiVersion", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put("api-version/{id}");
			}
		);
	}

	public static Performable findRatingDataByLobStateRatingUnitIdsUsingGetOnTheRatingDataController(String lob, String state, String ratingUnitIds, String version, String pageSize, String page, String search){
		return Task.where(
		"{0} Find RatingDatas by LOB, State, and RatingUnit IDs", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("lob", lob).queryParam("state", state).queryParam("ratingUnitIds", ratingUnitIds).queryParam("version", version).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("search", search).get("rating-data?search=byLobStateRatingUnitIds{&lob,state,ratingUnitIds,version,pageSize,page}");
			}
		);
	}

	public static Performable updateApiTypeUsingPutOnTheApiTypeController(String id, Object body){
		return Task.where(
		"{0} Update ApiType", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put("api-type/{id}");
			}
		);
	}

	public static Performable getApiVersionByIdUsingGetOnTheApiVersionController(String id){
		return Task.where(
		"{0} Get ApiVersion by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get("api-version/{id}");
			}
		);
	}

	public static Performable createRatingUnitUsingPostOnTheRatingUnitController(Object body){
		return Task.where(
		"{0} Create RatingUnit", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("rating-units");
			}
		);
	}

	public static Performable deleteProvisionedUnitUsingDeleteOnTheProvisionedUnitController(String id){
		return Task.where(
		"{0} Delete ProvisionedUnit", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete("provisioned-units/{id}");
			}
		);
	}

	public static Performable deleteApiTypeUsingDeleteOnTheApiTypeController(String id){
		return Task.where(
		"{0} Delete ApiType", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete("api-type/{id}");
			}
		);
	}

	public static Performable deleteRatingUnitUsingDeleteOnTheRatingUnitController(String id){
		return Task.where(
		"{0} Delete RatingUnit", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete("rating-units/{id}");
			}
		);
	}

	public static Performable createRuleActionUsingPostOnTheRuleActionController(Object body){
		return Task.where(
		"{0} Create RuleAction", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("rule-actions");
			}
		);
	}

	public static Performable findValueListsByNameUsingGetOnTheValueListController(String namePart, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find ValueLists by Name", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("namePart", namePart).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("value-lists?filter=byNamePart");
			}
		);
	}

	public static Performable getRatingUnitsUsingGetOnTheRatingUnitController(String pageSize, String page){
		return Task.where(
		"{0} Get all RatingUnits", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get("rating-units");
			}
		);
	}

	public static Performable getAllValueListsUsingGetOnTheValueListController(String pageSize, String page){
		return Task.where(
		"{0} Get all ValueLists", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get("value-lists");
			}
		);
	}

	public static Performable createProvisionedUnitUsingPostOnTheProvisionedUnitController(Object body){
		return Task.where(
		"{0} Create ProvisionedUnit", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("provisioned-units");
			}
		);
	}

	public static Performable getRatingUnitByCompanyIdAndPlanIdUsingGetOnTheProvisionedUnitController(String companyId, String planId, String filter){
		return Task.where(
		"{0} Get ProvisionedUnit by Company ID and Plan ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("companyId", companyId).queryParam("planId", planId).queryParam("filter", filter).get("provisioned-units?filter=byCompanyIdAndPlanId");
			}
		);
	}

	public static Performable deleteCategoryUsingDeleteOnTheCategoryController(String id){
		return Task.where(
		"{0} Delete Category", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete("category/{id}");
			}
		);
	}

	public static Performable createApiTypeUsingPostOnTheApiTypeController(Object body){
		return Task.where(
		"{0} Create ApiType", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("api-type");
			}
		);
	}

	public static Performable getRuleByIdUsingGetOnTheRuleController(String id){
		return Task.where(
		"{0} Get Rule by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get("rules/{id}");
			}
		);
	}

	public static Performable createApiVersionUsingPostOnTheApiVersionController(Object body){
		return Task.where(
		"{0} Create ApiVersion", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("api-version");
			}
		);
	}

	public static Performable deleteRuleActionUsingDeleteOnTheRuleActionController(String id){
		return Task.where(
		"{0} Delete RuleAction", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete("rule-actions/{id}");
			}
		);
	}

	public static Performable findRuleByRatingdataIdOnTheRuleController(String ratingDataId, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find Rule by RatingData ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("ratingDataId", ratingDataId).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("rules?filter=ratingDataId");
			}
		);
	}

	public static Performable updateValueListUsingPutOnTheValueListController(String id, Object body){
		return Task.where(
		"{0} Update ValueList", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put("value-lists/{id}");
			}
		);
	}

	public static Performable updateRuleActionUsingPutOnTheRuleActionController(String id, Object body){
		return Task.where(
		"{0} Update RuleAction", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put("rule-actions/{id}");
			}
		);
	}

	public static Performable deleteValueListUsingDeleteOnTheValueListController(String id){
		return Task.where(
		"{0} Delete ValueList", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete("value-lists/{id}");
			}
		);
	}

	public static Performable getProvisionedUnitsByLobUsingGetOnTheRatingUnitController(String lob, String pageSize, String page, String filter, String scope){
		return Task.where(
		"{0} Get Provisioned RatingUnits by LOB", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("lob", lob).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).queryParam("scope", scope).get("rating-units?filter=byProvisionedAndLob&scope=ancestors");
			}
		);
	}

	public static Performable getApiTypeByIdUsingGetOnTheApiTypeController(String id){
		return Task.where(
		"{0} Get ApiType by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get("api-type/{id}");
			}
		);
	}

	public static Performable deleteRatingDataUsingDeleteOnTheRatingDataController(String id){
		return Task.where(
		"{0} Delete RatingData", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete("rating-data/{id}");
			}
		);
	}

	public static Performable createRuleUsingPostOnTheRuleController(Object body){
		return Task.where(
		"{0} Create Rule", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("rules");
			}
		);
	}

	public static Performable deleteApiPropertyUsingDeleteOnTheApiPropertyController(String id){
		return Task.where(
		"{0} Delete ApiProperty", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete("api-property/{id}");
			}
		);
	}

	public static Performable updateRatingDataUsingPutOnTheRatingDataController(String id, Object body){
		return Task.where(
		"{0} Update RatingData", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put("rating-data/{id}");
			}
		);
	}

	public static Performable createApiPropertyUsingPostOnTheApiPropertyController(Object body){
		return Task.where(
		"{0} Create ApiProperty", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("api-property");
			}
		);
	}

	public static Performable createRatingUnitRatingDataUsingPostOnTheRatingUnitRatingDataController(Object body){
		return Task.where(
		"{0} Create RatingUnitRatingData", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("rating-data/{ratingDataId}/unit");
			}
		);
	}

	public static Performable updateRatingDataUsingPatchOnTheRatingDataController(String id, Object body){
		return Task.where(
		"{0} Patch RatingData", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").pathParam("id", id).body(body).patch("rating-data/{id}");
			}
		);
	}

	public static Performable updateApiPropertyUsingPutOnTheApiPropertyController(String id, Object body){
		return Task.where(
		"{0} Update ApiProperty", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put("api-property/{id}");
			}
		);
	}

	public static Performable findApiPropertiesByApiTypeIdUsingGetOnTheApiPropertyController(String apiTypeId, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find ApiProperties by ApiType ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("apiTypeId", apiTypeId).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("api-property?filter=byApiTypeId");
			}
		);
	}

	public static Performable getApiPropertyByIdUsingGetOnTheApiPropertyController(String id){
		return Task.where(
		"{0} Get ApiProperty by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get("api-property/{id}");
			}
		);
	}



}
