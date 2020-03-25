package com.vertafore.test.tasks.servicewrappers.ratingdata;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import com.vertafore.test.abilities.CallTitanApi;

/**
* This class was automatically generated on 2020/03/25 13:40:51

*/
public class UseRatingDataServiceTo {

	private static final String THIS_SERVICE = "rating-data";
	private static final String GET_VALUE_LIST_BY_ID_USING_GET_ON_THE_VALUE_LIST_CONTROLLER = "value-lists/{id}";
	private static final String DELETE_VALUE_LIST_USING_DELETE_ON_THE_VALUE_LIST_CONTROLLER = "value-lists/{id}";
	private static final String UPDATE_VALUE_LIST_USING_PUT_ON_THE_VALUE_LIST_CONTROLLER = "value-lists/{id}";
	private static final String GET_ALL_API_PROPERTIES_USING_GET_ON_THE_API_PROPERTY_CONTROLLER = "api-properties";
	private static final String FIND_RATING_DATAS_BY_IDS_USING_GET_ON_THE_RATING_DATA_CONTROLLER = "rating-data?filter=byIds";
	private static final String GET_PROVISIONED_RATING_UNITS_USING_GET_ON_THE_RATING_UNIT_CONTROLLER = "rating-units?filter=byProvisioned&scope=ancestors";
	private static final String DELETE_RATING_UNIT_RATING_DATA_USING_DELETE_ON_THE_RATING_UNIT_RATING_DATA_CONTROLLER = "rating-data/{ratingDataId}/unit/{id}";
	private static final String UPDATE_RATING_UNIT_RATING_DATA_USING_PUT_ON_THE_RATING_UNIT_RATING_DATA_CONTROLLER = "rating-data/{ratingDataId}/unit/{id}";
	private static final String CREATE_CATEGORY_USING_POST_ON_THE_CATEGORY_CONTROLLER = "category";
	private static final String GET_ALL_RULE_ACTIONS_USING_GET_ON_THE_RULE_ACTION_CONTROLLER = "rule-actions";
	private static final String CREATE_API_PROPERTY_USING_POST_ON_THE_API_PROPERTY_CONTROLLER = "api-property";
	private static final String GET_ALL_RATING_UNIT_RATING_DATAS_USING_GET_ON_THE_RATING_UNIT_RATING_DATA_CONTROLLER = "rating-data/units";
	private static final String FIND_RATING_DATA_BY_LOB_STATE_RATING_UNIT_IDS_USING_GET_ON_THE_RATING_DATA_CONTROLLER = "rating-data?search=byLobStateRatingUnitIds{&lob,state,ratingUnitIds,version,pageSize,page}";
	private static final String GET_RATINGUNITS_BY_NAME_ON_THE_RATING_UNIT_CONTROLLER = "rating-units?filter=byName";
	private static final String GET_PROVISIONED_UNITS_BY_STATE_USING_GET_ON_THE_RATING_UNIT_CONTROLLER = "rating-units?filter=byProvisionedAndState&scope=ancestors";
	private static final String GET_ALL_VALUE_LISTS_USING_GET_ON_THE_VALUE_LIST_CONTROLLER = "value-lists";
	private static final String GET_CATEGORY_BY_ID_USING_GET_ON_THE_CATEGORY_CONTROLLER = "category/{id}";
	private static final String DELETE_CATEGORY_USING_DELETE_ON_THE_CATEGORY_CONTROLLER = "category/{id}";
	private static final String UPDATE_CATEGORY_USING_PUT_ON_THE_CATEGORY_CONTROLLER = "category/{id}";
	private static final String GET_RATING_UNIT_BY_COMPANY_ID_AND_PLAN_ID_USING_GET_ON_THE_PROVISIONED_UNIT_CONTROLLER = "provisioned-units?filter=byCompanyIdAndPlanId";
	private static final String GET_ALL_API_TYPES_USING_GET_ON_THE_API_TYPE_CONTROLLER = "api-types";
	private static final String FIND_RATING_DATA_BY_BY_API_TYPE_NAME_API_PROPERTY_NAME_USING_GET_ON_THE_RATING_DATA_CONTROLLER = "rating-data?filter=byApiTypeNameApiPropertyName";
	private static final String CREATE_RULE_USING_POST_ON_THE_RULE_CONTROLLER = "rules";
	private static final String GET_API_PROPERTY_BY_ID_USING_GET_ON_THE_API_PROPERTY_CONTROLLER = "api-property/{id}";
	private static final String DELETE_API_PROPERTY_USING_DELETE_ON_THE_API_PROPERTY_CONTROLLER = "api-property/{id}";
	private static final String UPDATE_API_PROPERTY_USING_PUT_ON_THE_API_PROPERTY_CONTROLLER = "api-property/{id}";
	private static final String GET_RATINGUNIT_BY_COMPANY_ID_AND_PLAN_ID_ON_THE_RATING_UNIT_CONTROLLER = "rating-units?filter=byCompanyIdAndPlanId";
	private static final String FIND_BY_RATING_DATA_ID_USING_GET_ON_THE_RATING_UNIT_RATING_DATA_CONTROLLER = "rating-data/{ratingDataId}/units";
	private static final String GET_ALL_RULES_USING_GET_ON_THE_RULE_CONTROLLER = "rules";
	private static final String FIND_RULE_BY_RATINGDATA_ID_ON_THE_RULE_CONTROLLER = "rules?filter=ratingDataId";
	private static final String GET_API_TYPE_BY_ID_USING_GET_ON_THE_API_TYPE_CONTROLLER = "api-type/{id}";
	private static final String DELETE_API_TYPE_USING_DELETE_ON_THE_API_TYPE_CONTROLLER = "api-type/{id}";
	private static final String UPDATE_API_TYPE_USING_PUT_ON_THE_API_TYPE_CONTROLLER = "api-type/{id}";
	private static final String FIND_API_PROPERTIES_BY_API_TYPE_ID_USING_GET_ON_THE_API_PROPERTY_CONTROLLER = "api-property?filter=byApiTypeId";
	private static final String GET_RATINGUNIT_BY_ID_ON_THE_RATING_UNIT_CONTROLLER = "rating-units/{id}";
	private static final String DELETE_RATING_UNIT_USING_DELETE_ON_THE_RATING_UNIT_CONTROLLER = "rating-units/{id}";
	private static final String UPDATE_RATING_UNIT_USING_PUT_ON_THE_RATING_UNIT_CONTROLLER = "rating-units/{id}";
	private static final String GET_RATING_UNITS_BY_NAME_USING_GET_ON_THE_PROVISIONED_UNIT_CONTROLLER = "provisioned-units?filter=byName";
	private static final String FIND_RATING_DATAS_BY_TAG_USING_GET_ON_THE_RATING_DATA_CONTROLLER = "rating-data?filter=byTag";
	private static final String DELETE_VALUE_LIST_ITEM_USING_DELETE_ON_THE_VALUE_LIST_CONTROLLER = "value-lists/{id}/items/{itemId}";
	private static final String GET_RATING_DATA_USING_GET_ON_THE_RATING_DATA_CONTROLLER = "rating-data/{id}";
	private static final String CREATE_API_VERSION_USING_POST_ON_THE_API_VERSION_CONTROLLER = "api-version";
	private static final String CREATE_VALUE_LIST_ITEM_USING_POST_ON_THE_VALUE_LIST_CONTROLLER = "value-lists/{id}/items";
	private static final String GET_PROVISIONED_UNITS_BY_LOB_USING_GET_ON_THE_RATING_UNIT_CONTROLLER = "rating-units?filter=byProvisionedAndLob&scope=ancestors";
	private static final String DELETE_RULE_ACTION_USING_DELETE_ON_THE_RULE_ACTION_CONTROLLER = "rule-actions/{id}";
	private static final String UPDATE_RULE_ACTION_USING_PUT_ON_THE_RULE_ACTION_CONTROLLER = "rule-actions/{id}";
	private static final String GET_PROVISIONED_UNITS_BY_NAME_USING_GET_ON_THE_RATING_UNIT_CONTROLLER = "rating-units?filter=byProvisionedAndName&scope=ancestors";
	private static final String CREATE_VALUE_LIST_USING_POST_ON_THE_VALUE_LIST_CONTROLLER = "value-lists";
	private static final String CREATE_RATING_DATA_USING_POST_ON_THE_RATING_DATA_CONTROLLER = "rating-data";
	private static final String GET_ALL_CATEGORIES_USING_GET_ON_THE_CATEGORY_CONTROLLER = "categories";
	private static final String CREATE_RATING_UNIT_RATING_DATA_USING_POST_ON_THE_RATING_UNIT_RATING_DATA_CONTROLLER = "rating-data/{ratingDataId}/unit";
	private static final String CREATE_API_TYPE_USING_POST_ON_THE_API_TYPE_CONTROLLER = "api-type";
	private static final String FIND_VALUE_LISTS_BY_RATING_DATA_ID_USING_GET_ON_THE_VALUE_LIST_CONTROLLER = "value-lists?filter=ratingDataId";
	private static final String GET_RATING_UNITS_BY_STATE_USING_GET_ON_THE_RATING_UNIT_CONTROLLER = "rating-units?filter=byState";
	private static final String GET_RATING_UNITS_BY_LOB_USING_GET_ON_THE_RATING_UNIT_CONTROLLER = "rating-units?filter=byLob";
	private static final String GET_API_VERSION_BY_ID_USING_GET_ON_THE_API_VERSION_CONTROLLER = "api-version/{id}";
	private static final String DELETE_API_VERSION_USING_DELETE_ON_THE_API_VERSION_CONTROLLER = "api-version/{id}";
	private static final String UPDATE_API_VERSION_USING_PUT_ON_THE_API_VERSION_CONTROLLER = "api-version/{id}";
	private static final String GET_RATING_UNIT_BY_ID_USING_GET_ON_THE_PROVISIONED_UNIT_CONTROLLER = "provisioned-units/{id}";
	private static final String DELETE_PROVISIONED_UNIT_USING_DELETE_ON_THE_PROVISIONED_UNIT_CONTROLLER = "provisioned-units/{id}";
	private static final String GET_RATING_UNITS_USING_GET_ON_THE_RATING_UNIT_CONTROLLER = "rating-units";
	private static final String CREATE_RULE_ACTION_USING_POST_ON_THE_RULE_ACTION_CONTROLLER = "rule-actions";
	private static final String UPDATE_RATING_DATA_USING_PATCH_ON_THE_RATING_DATA_CONTROLLER = "rating-data/{id}";
	private static final String DELETE_RATING_DATA_USING_DELETE_ON_THE_RATING_DATA_CONTROLLER = "rating-data/{id}";
	private static final String UPDATE_RATING_DATA_USING_PUT_ON_THE_RATING_DATA_CONTROLLER = "rating-data/{id}";
	private static final String FIND_VALUE_LISTS_BY_NAME_USING_GET_ON_THE_VALUE_LIST_CONTROLLER = "value-lists?filter=byNamePart";
	private static final String GET_PROVISIONED_UNITS_BY_STATUS_USING_GET_ON_THE_RATING_UNIT_CONTROLLER = "rating-units?filter=byProvisionedAndStatus&scope=ancestors";
	private static final String CREATE_PROVISIONED_UNIT_USING_POST_ON_THE_PROVISIONED_UNIT_CONTROLLER = "provisioned-units";
	private static final String GET_ALL_API_VERSIONS_USING_GET_ON_THE_API_VERSION_CONTROLLER = "api-versions";
	private static final String CREATE_RATING_UNIT_USING_POST_ON_THE_RATING_UNIT_CONTROLLER = "rating-units";
	private static final String GET_ALL_RATING_DATAS_USING_GET_ON_THE_RATING_DATA_CONTROLLER = "rating-data";
	private static final String GET_RULE_BY_ID_USING_GET_ON_THE_RULE_CONTROLLER = "rules/{id}";
	private static final String DELETE_RULE_USING_DELETE_ON_THE_RULE_CONTROLLER = "rules/{id}";
	private static final String UPDATE_RULE_USING_PUT_ON_THE_RULE_CONTROLLER = "rules/{id}";
	private static final String GET_RATING_UNITS_BY_STATUS_USING_GET_ON_THE_RATING_UNIT_CONTROLLER = "rating-units?filter=byStatus";

	public static Performable getValueListByIdUsingGetOnTheValueListController(String id){
		return Task.where(
		"{0} Get ValueList by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_VALUE_LIST_BY_ID_USING_GET_ON_THE_VALUE_LIST_CONTROLLER);
			}
		);
	}

	public static Performable deleteValueListUsingDeleteOnTheValueListController(String id){
		return Task.where(
		"{0} Delete ValueList", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_VALUE_LIST_USING_DELETE_ON_THE_VALUE_LIST_CONTROLLER);
			}
		);
	}

	public static Performable updateValueListUsingPutOnTheValueListController(String id, Object body){
		return Task.where(
		"{0} Update ValueList", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put(UPDATE_VALUE_LIST_USING_PUT_ON_THE_VALUE_LIST_CONTROLLER);
			}
		);
	}

	public static Performable getAllApiPropertiesUsingGetOnTheApiPropertyController(String pageSize, String page){
		return Task.where(
		"{0} Get all ApiProperties", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(GET_ALL_API_PROPERTIES_USING_GET_ON_THE_API_PROPERTY_CONTROLLER);
			}
		);
	}

	public static Performable findRatingDatasByIdsUsingGetOnTheRatingDataController(String ids, String version, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find RatingDatas by IDs", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("ids", ids).queryParam("version", version).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(FIND_RATING_DATAS_BY_IDS_USING_GET_ON_THE_RATING_DATA_CONTROLLER);
			}
		);
	}

	public static Performable getProvisionedRatingUnitsUsingGetOnTheRatingUnitController(String pageSize, String page, String filter, String scope){
		return Task.where(
		"{0} Get all Provisioned RatingUnits", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).queryParam("scope", scope).get(GET_PROVISIONED_RATING_UNITS_USING_GET_ON_THE_RATING_UNIT_CONTROLLER);
			}
		);
	}

	public static Performable deleteRatingUnitRatingDataUsingDeleteOnTheRatingUnitRatingDataController(String id){
		return Task.where(
		"{0} Delete RatingUnitRatingData", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_RATING_UNIT_RATING_DATA_USING_DELETE_ON_THE_RATING_UNIT_RATING_DATA_CONTROLLER);
			}
		);
	}

	public static Performable updateRatingUnitRatingDataUsingPutOnTheRatingUnitRatingDataController(String id, Object body){
		return Task.where(
		"{0} Update RatingUnitRatingData", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put(UPDATE_RATING_UNIT_RATING_DATA_USING_PUT_ON_THE_RATING_UNIT_RATING_DATA_CONTROLLER);
			}
		);
	}

	public static Performable createCategoryUsingPostOnTheCategoryController(Object body){
		return Task.where(
		"{0} Create Category", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_CATEGORY_USING_POST_ON_THE_CATEGORY_CONTROLLER);
			}
		);
	}

	public static Performable getAllRuleActionsUsingGetOnTheRuleActionController(String pageSize, String page){
		return Task.where(
		"{0} Get all RuleActions", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(GET_ALL_RULE_ACTIONS_USING_GET_ON_THE_RULE_ACTION_CONTROLLER);
			}
		);
	}

	public static Performable createApiPropertyUsingPostOnTheApiPropertyController(Object body){
		return Task.where(
		"{0} Create ApiProperty", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_API_PROPERTY_USING_POST_ON_THE_API_PROPERTY_CONTROLLER);
			}
		);
	}

	public static Performable getAllRatingUnitRatingDatasUsingGetOnTheRatingUnitRatingDataController(String pageSize, String page){
		return Task.where(
		"{0} Get all RatingUnitRatingData", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(GET_ALL_RATING_UNIT_RATING_DATAS_USING_GET_ON_THE_RATING_UNIT_RATING_DATA_CONTROLLER);
			}
		);
	}

	public static Performable findRatingDataByLobStateRatingUnitIdsUsingGetOnTheRatingDataController(String lob, String state, String ratingUnitIds, String version, String pageSize, String page, String search){
		return Task.where(
		"{0} Find RatingDatas by LOB, State, and RatingUnit IDs", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("lob", lob).queryParam("state", state).queryParam("ratingUnitIds", ratingUnitIds).queryParam("version", version).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("search", search).get(FIND_RATING_DATA_BY_LOB_STATE_RATING_UNIT_IDS_USING_GET_ON_THE_RATING_DATA_CONTROLLER);
			}
		);
	}

	public static Performable getRatingunitsByNameOnTheRatingUnitController(String name, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get RatingUnits by Name", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("name", name).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_RATINGUNITS_BY_NAME_ON_THE_RATING_UNIT_CONTROLLER);
			}
		);
	}

	public static Performable getProvisionedUnitsByStateUsingGetOnTheRatingUnitController(String state, String pageSize, String page, String filter, String scope){
		return Task.where(
		"{0} Get Provisioned RatingUnits by State", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("state", state).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).queryParam("scope", scope).get(GET_PROVISIONED_UNITS_BY_STATE_USING_GET_ON_THE_RATING_UNIT_CONTROLLER);
			}
		);
	}

	public static Performable getAllValueListsUsingGetOnTheValueListController(String pageSize, String page){
		return Task.where(
		"{0} Get all ValueLists", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(GET_ALL_VALUE_LISTS_USING_GET_ON_THE_VALUE_LIST_CONTROLLER);
			}
		);
	}

	public static Performable getCategoryByIdUsingGetOnTheCategoryController(String id){
		return Task.where(
		"{0} Get Category by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_CATEGORY_BY_ID_USING_GET_ON_THE_CATEGORY_CONTROLLER);
			}
		);
	}

	public static Performable deleteCategoryUsingDeleteOnTheCategoryController(String id){
		return Task.where(
		"{0} Delete Category", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_CATEGORY_USING_DELETE_ON_THE_CATEGORY_CONTROLLER);
			}
		);
	}

	public static Performable updateCategoryUsingPutOnTheCategoryController(String id, Object body){
		return Task.where(
		"{0} Update Category", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put(UPDATE_CATEGORY_USING_PUT_ON_THE_CATEGORY_CONTROLLER);
			}
		);
	}

	public static Performable getRatingUnitByCompanyIdAndPlanIdUsingGetOnTheProvisionedUnitController(String companyId, String planId, String filter){
		return Task.where(
		"{0} Get ProvisionedUnit by Company ID and Plan ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("companyId", companyId).queryParam("planId", planId).queryParam("filter", filter).get(GET_RATING_UNIT_BY_COMPANY_ID_AND_PLAN_ID_USING_GET_ON_THE_PROVISIONED_UNIT_CONTROLLER);
			}
		);
	}

	public static Performable getAllApiTypesUsingGetOnTheApiTypeController(String pageSize, String page){
		return Task.where(
		"{0} Get all ApiTypes", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(GET_ALL_API_TYPES_USING_GET_ON_THE_API_TYPE_CONTROLLER);
			}
		);
	}

	public static Performable findRatingDataByByApiTypeNameApiPropertyNameUsingGetOnTheRatingDataController(String apiTypeName, String apiPropertyName, String version, String filter){
		return Task.where(
		"{0} Find RatingData by ApiType Name and ApiProperty Name", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("apiTypeName", apiTypeName).queryParam("apiPropertyName", apiPropertyName).queryParam("version", version).queryParam("filter", filter).get(FIND_RATING_DATA_BY_BY_API_TYPE_NAME_API_PROPERTY_NAME_USING_GET_ON_THE_RATING_DATA_CONTROLLER);
			}
		);
	}

	public static Performable createRuleUsingPostOnTheRuleController(Object body){
		return Task.where(
		"{0} Create Rule", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_RULE_USING_POST_ON_THE_RULE_CONTROLLER);
			}
		);
	}

	public static Performable getApiPropertyByIdUsingGetOnTheApiPropertyController(String id){
		return Task.where(
		"{0} Get ApiProperty by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_API_PROPERTY_BY_ID_USING_GET_ON_THE_API_PROPERTY_CONTROLLER);
			}
		);
	}

	public static Performable deleteApiPropertyUsingDeleteOnTheApiPropertyController(String id){
		return Task.where(
		"{0} Delete ApiProperty", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_API_PROPERTY_USING_DELETE_ON_THE_API_PROPERTY_CONTROLLER);
			}
		);
	}

	public static Performable updateApiPropertyUsingPutOnTheApiPropertyController(String id, Object body){
		return Task.where(
		"{0} Update ApiProperty", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put(UPDATE_API_PROPERTY_USING_PUT_ON_THE_API_PROPERTY_CONTROLLER);
			}
		);
	}

	public static Performable getRatingunitByCompanyIdAndPlanIdOnTheRatingUnitController(String companyId, String planId, String filter){
		return Task.where(
		"{0} Get RatingUnit by Company ID and Plan ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("companyId", companyId).queryParam("planId", planId).queryParam("filter", filter).get(GET_RATINGUNIT_BY_COMPANY_ID_AND_PLAN_ID_ON_THE_RATING_UNIT_CONTROLLER);
			}
		);
	}

	public static Performable findByRatingDataIdUsingGetOnTheRatingUnitRatingDataController(String ratingDataId, String pageSize, String page){
		return Task.where(
		"{0} Find RatingUnitRatingData by RatingData ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("ratingDataId", ratingDataId).queryParam("pageSize", pageSize).queryParam("page", page).get(FIND_BY_RATING_DATA_ID_USING_GET_ON_THE_RATING_UNIT_RATING_DATA_CONTROLLER);
			}
		);
	}

	public static Performable getAllRulesUsingGetOnTheRuleController(String pageSize, String page){
		return Task.where(
		"{0} Get all Rules", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(GET_ALL_RULES_USING_GET_ON_THE_RULE_CONTROLLER);
			}
		);
	}

	public static Performable findRuleByRatingdataIdOnTheRuleController(String ratingDataId, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find Rule by RatingData ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("ratingDataId", ratingDataId).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(FIND_RULE_BY_RATINGDATA_ID_ON_THE_RULE_CONTROLLER);
			}
		);
	}

	public static Performable getApiTypeByIdUsingGetOnTheApiTypeController(String id){
		return Task.where(
		"{0} Get ApiType by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_API_TYPE_BY_ID_USING_GET_ON_THE_API_TYPE_CONTROLLER);
			}
		);
	}

	public static Performable deleteApiTypeUsingDeleteOnTheApiTypeController(String id){
		return Task.where(
		"{0} Delete ApiType", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_API_TYPE_USING_DELETE_ON_THE_API_TYPE_CONTROLLER);
			}
		);
	}

	public static Performable updateApiTypeUsingPutOnTheApiTypeController(String id, Object body){
		return Task.where(
		"{0} Update ApiType", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put(UPDATE_API_TYPE_USING_PUT_ON_THE_API_TYPE_CONTROLLER);
			}
		);
	}

	public static Performable findApiPropertiesByApiTypeIdUsingGetOnTheApiPropertyController(String apiTypeId, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find ApiProperties by ApiType ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("apiTypeId", apiTypeId).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(FIND_API_PROPERTIES_BY_API_TYPE_ID_USING_GET_ON_THE_API_PROPERTY_CONTROLLER);
			}
		);
	}

	public static Performable getRatingunitByIdOnTheRatingUnitController(String id){
		return Task.where(
		"{0} Get RatingUnit by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_RATINGUNIT_BY_ID_ON_THE_RATING_UNIT_CONTROLLER);
			}
		);
	}

	public static Performable deleteRatingUnitUsingDeleteOnTheRatingUnitController(String id){
		return Task.where(
		"{0} Delete RatingUnit", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_RATING_UNIT_USING_DELETE_ON_THE_RATING_UNIT_CONTROLLER);
			}
		);
	}

	public static Performable updateRatingUnitUsingPutOnTheRatingUnitController(String id, Object body){
		return Task.where(
		"{0} Update RatingUnit", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put(UPDATE_RATING_UNIT_USING_PUT_ON_THE_RATING_UNIT_CONTROLLER);
			}
		);
	}

	public static Performable getRatingUnitsByNameUsingGetOnTheProvisionedUnitController(String name, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get ProvisionedUnits by Name", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("name", name).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_RATING_UNITS_BY_NAME_USING_GET_ON_THE_PROVISIONED_UNIT_CONTROLLER);
			}
		);
	}

	public static Performable findRatingDatasByTagUsingGetOnTheRatingDataController(String tag, String version, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find RatingDatas by Tag", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("tag", tag).queryParam("version", version).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(FIND_RATING_DATAS_BY_TAG_USING_GET_ON_THE_RATING_DATA_CONTROLLER);
			}
		);
	}

	public static Performable deleteValueListItemUsingDeleteOnTheValueListController(String itemId){
		return Task.where(
		"{0} Delete ValueListItem", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("itemId", itemId).delete(DELETE_VALUE_LIST_ITEM_USING_DELETE_ON_THE_VALUE_LIST_CONTROLLER);
			}
		);
	}

	public static Performable getRatingDataUsingGetOnTheRatingDataController(String id, String version){
		return Task.where(
		"{0} Get RatingData by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).queryParam("version", version).get(GET_RATING_DATA_USING_GET_ON_THE_RATING_DATA_CONTROLLER);
			}
		);
	}

	public static Performable createApiVersionUsingPostOnTheApiVersionController(Object body){
		return Task.where(
		"{0} Create ApiVersion", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_API_VERSION_USING_POST_ON_THE_API_VERSION_CONTROLLER);
			}
		);
	}

	public static Performable createValueListItemUsingPostOnTheValueListController(String id, Object body){
		return Task.where(
		"{0} Create ValueListItem", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).post(CREATE_VALUE_LIST_ITEM_USING_POST_ON_THE_VALUE_LIST_CONTROLLER);
			}
		);
	}

	public static Performable getProvisionedUnitsByLobUsingGetOnTheRatingUnitController(String lob, String pageSize, String page, String filter, String scope){
		return Task.where(
		"{0} Get Provisioned RatingUnits by LOB", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("lob", lob).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).queryParam("scope", scope).get(GET_PROVISIONED_UNITS_BY_LOB_USING_GET_ON_THE_RATING_UNIT_CONTROLLER);
			}
		);
	}

	public static Performable deleteRuleActionUsingDeleteOnTheRuleActionController(String id){
		return Task.where(
		"{0} Delete RuleAction", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_RULE_ACTION_USING_DELETE_ON_THE_RULE_ACTION_CONTROLLER);
			}
		);
	}

	public static Performable updateRuleActionUsingPutOnTheRuleActionController(String id, Object body){
		return Task.where(
		"{0} Update RuleAction", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put(UPDATE_RULE_ACTION_USING_PUT_ON_THE_RULE_ACTION_CONTROLLER);
			}
		);
	}

	public static Performable getProvisionedUnitsByNameUsingGetOnTheRatingUnitController(String name, String pageSize, String page, String filter, String scope){
		return Task.where(
		"{0} Get Provisioned RatingUnits by Name", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("name", name).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).queryParam("scope", scope).get(GET_PROVISIONED_UNITS_BY_NAME_USING_GET_ON_THE_RATING_UNIT_CONTROLLER);
			}
		);
	}

	public static Performable createValueListUsingPostOnTheValueListController(Object body){
		return Task.where(
		"{0} Create ValueList", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_VALUE_LIST_USING_POST_ON_THE_VALUE_LIST_CONTROLLER);
			}
		);
	}

	public static Performable createRatingDataUsingPostOnTheRatingDataController(Object body){
		return Task.where(
		"{0} Create RatingData", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_RATING_DATA_USING_POST_ON_THE_RATING_DATA_CONTROLLER);
			}
		);
	}

	public static Performable getAllCategoriesUsingGetOnTheCategoryController(String pageSize, String page){
		return Task.where(
		"{0} Get all Categories", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(GET_ALL_CATEGORIES_USING_GET_ON_THE_CATEGORY_CONTROLLER);
			}
		);
	}

	public static Performable createRatingUnitRatingDataUsingPostOnTheRatingUnitRatingDataController(Object body){
		return Task.where(
		"{0} Create RatingUnitRatingData", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_RATING_UNIT_RATING_DATA_USING_POST_ON_THE_RATING_UNIT_RATING_DATA_CONTROLLER);
			}
		);
	}

	public static Performable createApiTypeUsingPostOnTheApiTypeController(Object body){
		return Task.where(
		"{0} Create ApiType", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_API_TYPE_USING_POST_ON_THE_API_TYPE_CONTROLLER);
			}
		);
	}

	public static Performable findValueListsByRatingDataIdUsingGetOnTheValueListController(String ratingDataId, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find ValueLists by RatingData ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("ratingDataId", ratingDataId).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(FIND_VALUE_LISTS_BY_RATING_DATA_ID_USING_GET_ON_THE_VALUE_LIST_CONTROLLER);
			}
		);
	}

	public static Performable getRatingUnitsByStateUsingGetOnTheRatingUnitController(String state, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get RatingUnits by State", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("state", state).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_RATING_UNITS_BY_STATE_USING_GET_ON_THE_RATING_UNIT_CONTROLLER);
			}
		);
	}

	public static Performable getRatingUnitsByLobUsingGetOnTheRatingUnitController(String lob, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get RatingUnits by LOB", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("lob", lob).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_RATING_UNITS_BY_LOB_USING_GET_ON_THE_RATING_UNIT_CONTROLLER);
			}
		);
	}

	public static Performable getApiVersionByIdUsingGetOnTheApiVersionController(String id){
		return Task.where(
		"{0} Get ApiVersion by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_API_VERSION_BY_ID_USING_GET_ON_THE_API_VERSION_CONTROLLER);
			}
		);
	}

	public static Performable deleteApiVersionUsingDeleteOnTheApiVersionController(String id){
		return Task.where(
		"{0} Delete ApiVersion", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_API_VERSION_USING_DELETE_ON_THE_API_VERSION_CONTROLLER);
			}
		);
	}

	public static Performable updateApiVersionUsingPutOnTheApiVersionController(String id, Object body){
		return Task.where(
		"{0} Update ApiVersion", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put(UPDATE_API_VERSION_USING_PUT_ON_THE_API_VERSION_CONTROLLER);
			}
		);
	}

	public static Performable getRatingUnitByIdUsingGetOnTheProvisionedUnitController(String id){
		return Task.where(
		"{0} Get ProvisionedUnit by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_RATING_UNIT_BY_ID_USING_GET_ON_THE_PROVISIONED_UNIT_CONTROLLER);
			}
		);
	}

	public static Performable deleteProvisionedUnitUsingDeleteOnTheProvisionedUnitController(String id){
		return Task.where(
		"{0} Delete ProvisionedUnit", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_PROVISIONED_UNIT_USING_DELETE_ON_THE_PROVISIONED_UNIT_CONTROLLER);
			}
		);
	}

	public static Performable getRatingUnitsUsingGetOnTheRatingUnitController(String pageSize, String page){
		return Task.where(
		"{0} Get all RatingUnits", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(GET_RATING_UNITS_USING_GET_ON_THE_RATING_UNIT_CONTROLLER);
			}
		);
	}

	public static Performable createRuleActionUsingPostOnTheRuleActionController(Object body){
		return Task.where(
		"{0} Create RuleAction", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_RULE_ACTION_USING_POST_ON_THE_RULE_ACTION_CONTROLLER);
			}
		);
	}

	public static Performable updateRatingDataUsingPatchOnTheRatingDataController(String id, Object body){
		return Task.where(
		"{0} Patch RatingData", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").pathParam("id", id).body(body).patch(UPDATE_RATING_DATA_USING_PATCH_ON_THE_RATING_DATA_CONTROLLER);
			}
		);
	}

	public static Performable deleteRatingDataUsingDeleteOnTheRatingDataController(String id){
		return Task.where(
		"{0} Delete RatingData", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_RATING_DATA_USING_DELETE_ON_THE_RATING_DATA_CONTROLLER);
			}
		);
	}

	public static Performable updateRatingDataUsingPutOnTheRatingDataController(String id, Object body){
		return Task.where(
		"{0} Update RatingData", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put(UPDATE_RATING_DATA_USING_PUT_ON_THE_RATING_DATA_CONTROLLER);
			}
		);
	}

	public static Performable findValueListsByNameUsingGetOnTheValueListController(String namePart, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find ValueLists by Name", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("namePart", namePart).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(FIND_VALUE_LISTS_BY_NAME_USING_GET_ON_THE_VALUE_LIST_CONTROLLER);
			}
		);
	}

	public static Performable getProvisionedUnitsByStatusUsingGetOnTheRatingUnitController(String status, String pageSize, String page, String filter, String scope){
		return Task.where(
		"{0} Get Provisioned RatingUnits by Status", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("status", status).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).queryParam("scope", scope).get(GET_PROVISIONED_UNITS_BY_STATUS_USING_GET_ON_THE_RATING_UNIT_CONTROLLER);
			}
		);
	}

	public static Performable createProvisionedUnitUsingPostOnTheProvisionedUnitController(Object body){
		return Task.where(
		"{0} Create ProvisionedUnit", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_PROVISIONED_UNIT_USING_POST_ON_THE_PROVISIONED_UNIT_CONTROLLER);
			}
		);
	}

	public static Performable getAllApiVersionsUsingGetOnTheApiVersionController(String pageSize, String page){
		return Task.where(
		"{0} Get all ApiVersions", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(GET_ALL_API_VERSIONS_USING_GET_ON_THE_API_VERSION_CONTROLLER);
			}
		);
	}

	public static Performable createRatingUnitUsingPostOnTheRatingUnitController(Object body){
		return Task.where(
		"{0} Create RatingUnit", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_RATING_UNIT_USING_POST_ON_THE_RATING_UNIT_CONTROLLER);
			}
		);
	}

	public static Performable getAllRatingDatasUsingGetOnTheRatingDataController(String pageSize, String page){
		return Task.where(
		"{0} Get all RatingData", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(GET_ALL_RATING_DATAS_USING_GET_ON_THE_RATING_DATA_CONTROLLER);
			}
		);
	}

	public static Performable getRuleByIdUsingGetOnTheRuleController(String id){
		return Task.where(
		"{0} Get Rule by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_RULE_BY_ID_USING_GET_ON_THE_RULE_CONTROLLER);
			}
		);
	}

	public static Performable deleteRuleUsingDeleteOnTheRuleController(String id){
		return Task.where(
		"{0} Delete Rule", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_RULE_USING_DELETE_ON_THE_RULE_CONTROLLER);
			}
		);
	}

	public static Performable updateRuleUsingPutOnTheRuleController(String id, Object body){
		return Task.where(
		"{0} Update Rule", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put(UPDATE_RULE_USING_PUT_ON_THE_RULE_CONTROLLER);
			}
		);
	}

	public static Performable getRatingUnitsByStatusUsingGetOnTheRatingUnitController(String status, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get RatingUnits by Status", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("status", status).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_RATING_UNITS_BY_STATUS_USING_GET_ON_THE_RATING_UNIT_CONTROLLER);
			}
		);
	}



}
