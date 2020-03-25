package com.vertafore.test.tasks.servicewrappers.exposure;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import com.vertafore.test.abilities.CallTitanApi;

/**
* This class was automatically generated on 2020/03/25 13:40:44

*/
public class UseExposureServiceTo {

	private static final String THIS_SERVICE = "exposure";
	private static final String GET_OCCUPATION_VALUES_USING_GET_ON_THE_STATIC_VALUES_CONTROLLER = "static-values/occupations";
	private static final String POST_EXPOSURE_GROUP_USING_POST_ON_THE_EXPOSURE_GROUP_CONTROLLER = "exposure-groups";
	private static final String GET_ADDITIONAL_COVERAGES_BY_EXPOSURE_EXCLUDE_CATEGORY_USING_GET_ON_THE_ADDITIONAL_COVERAGE_CONTROLLER = "additional-coverages?filter=byExposureCodeExcludeCategory";
	private static final String GET_NCCI_CODES_BY_KEYS_USING_GET_ON_THE_STATIC_VALUES_CONTROLLER = "static-values/ncci-codes?filter=byKeys";
	private static final String UPDATE_EXPOSURE_GROUP_USING_PATCH_ON_THE_EXPOSURE_GROUP_CONTROLLER = "exposure-groups/{exposureGroupId}";
	private static final String GET_EXPOSURE_GROUP_USING_GET_ON_THE_EXPOSURE_GROUP_CONTROLLER = "exposure-groups/{exposureGroupId}";
	private static final String DELETE_EXPOSURE_GROUP_USING_DELETE_ON_THE_EXPOSURE_GROUP_CONTROLLER = "exposure-groups/{exposureGroupId}";
	private static final String GET_ADDITIONAL_COVERAGES_BY_EXPOSURE_CODE_USING_GET_ON_THE_ADDITIONAL_COVERAGE_CONTROLLER = "additional-coverages?filter=byExposureCode";
	private static final String GET_EXPOSURE_TYPES_USING_GET_ON_THE_EXPOSURE_TYPE_CONTROLLER = "exposure-types";
	private static final String GET_NCCI_CODES_BY_SEARCH_TERM_USING_GET_ON_THE_STATIC_VALUES_CONTROLLER = "static-values/ncci-codes?filter=bySearchTerm";
	private static final String GET_ADDITIONAL_COVERAGES_BY_EXPOSURE_CODE_AND_CATEGORY_USING_GET_ON_THE_ADDITIONAL_COVERAGE_CONTROLLER = "additional-coverages?filter=byExposureCodeAndCategory";
	private static final String GET_FARM_UNSCHEDULED_PROPERTY_SUB_CLASS_TYPE_BY_CLASS_USING_GET_ON_THE_STATIC_VALUES_CONTROLLER = "static-values/farm-unscheduled-property-class-types/{farmUPC}";
	private static final String GET_OCCUPATION_VALUES_BY_TEXT_USING_GET_ON_THE_STATIC_VALUES_CONTROLLER = "static-values/occupations?filter=byText";

	public static Performable getOccupationValuesUsingGetOnTheStaticValuesController(String pageSize, String page){
		return Task.where(
		"{0} Retrieves all of the possible values for Occupations", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(GET_OCCUPATION_VALUES_USING_GET_ON_THE_STATIC_VALUES_CONTROLLER);
			}
		);
	}

	public static Performable postExposureGroupUsingPostOnTheExposureGroupController(Object body){
		return Task.where(
		"{0} Create an ExposureGroup", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(POST_EXPOSURE_GROUP_USING_POST_ON_THE_EXPOSURE_GROUP_CONTROLLER);
			}
		);
	}

	public static Performable getAdditionalCoveragesByExposureExcludeCategoryUsingGetOnTheAdditionalCoverageController(String pageSize, String page, String exposureCode, String categoryToExclude, String filter){
		return Task.where(
		"{0} Retrieves the AdditionalCoverages list that match the given Exposure Type excluding the given Category", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("exposureCode", exposureCode).queryParam("categoryToExclude", categoryToExclude).queryParam("filter", filter).get(GET_ADDITIONAL_COVERAGES_BY_EXPOSURE_EXCLUDE_CATEGORY_USING_GET_ON_THE_ADDITIONAL_COVERAGE_CONTROLLER);
			}
		);
	}

	public static Performable getNcciCodesByKeysUsingGetOnTheStaticValuesController(String pageSize, String page, String keys, String filter){
		return Task.where(
		"{0} Retrieves NCCI Codes that match the given keys", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("keys", keys).queryParam("filter", filter).get(GET_NCCI_CODES_BY_KEYS_USING_GET_ON_THE_STATIC_VALUES_CONTROLLER);
			}
		);
	}

	public static Performable updateExposureGroupUsingPatchOnTheExposureGroupController(String exposureGroupId, Object body, String If-Match){
		return Task.where(
		"{0} Update an ExposureGroup", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("exposureGroupId", exposureGroupId).body(body).pathParam("If-Match", If-Match).patch(UPDATE_EXPOSURE_GROUP_USING_PATCH_ON_THE_EXPOSURE_GROUP_CONTROLLER);
			}
		);
	}

	public static Performable getExposureGroupUsingGetOnTheExposureGroupController(String exposureGroupId){
		return Task.where(
		"{0} Retrieves the ExposureGroup specified by id provided", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("exposureGroupId", exposureGroupId).get(GET_EXPOSURE_GROUP_USING_GET_ON_THE_EXPOSURE_GROUP_CONTROLLER);
			}
		);
	}

	public static Performable deleteExposureGroupUsingDeleteOnTheExposureGroupController(String exposureGroupId){
		return Task.where(
		"{0} Delete an ExposureGroup by id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("exposureGroupId", exposureGroupId).delete(DELETE_EXPOSURE_GROUP_USING_DELETE_ON_THE_EXPOSURE_GROUP_CONTROLLER);
			}
		);
	}

	public static Performable getAdditionalCoveragesByExposureCodeUsingGetOnTheAdditionalCoverageController(String pageSize, String page, String exposureCode, String filter){
		return Task.where(
		"{0} Retrieves the AdditionalCoverages list that match the given Exposure Type code", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("exposureCode", exposureCode).queryParam("filter", filter).get(GET_ADDITIONAL_COVERAGES_BY_EXPOSURE_CODE_USING_GET_ON_THE_ADDITIONAL_COVERAGE_CONTROLLER);
			}
		);
	}

	public static Performable getExposureTypesUsingGetOnTheExposureTypeController(String pageSize, String page){
		return Task.where(
		"{0} Retrieves the ExposureTypes list", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(GET_EXPOSURE_TYPES_USING_GET_ON_THE_EXPOSURE_TYPE_CONTROLLER);
			}
		);
	}

	public static Performable getNcciCodesBySearchTermUsingGetOnTheStaticValuesController(String pageSize, String page, String searchTerm, String filter){
		return Task.where(
		"{0} Retrieves NCCI Codes that match the given search term", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("searchTerm", searchTerm).queryParam("filter", filter).get(GET_NCCI_CODES_BY_SEARCH_TERM_USING_GET_ON_THE_STATIC_VALUES_CONTROLLER);
			}
		);
	}

	public static Performable getAdditionalCoveragesByExposureCodeAndCategoryUsingGetOnTheAdditionalCoverageController(String pageSize, String page, String exposureCode, String category, String filter){
		return Task.where(
		"{0} Retrieves the AdditionalCoverages list that match the given Exposure Type and Category", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("exposureCode", exposureCode).queryParam("category", category).queryParam("filter", filter).get(GET_ADDITIONAL_COVERAGES_BY_EXPOSURE_CODE_AND_CATEGORY_USING_GET_ON_THE_ADDITIONAL_COVERAGE_CONTROLLER);
			}
		);
	}

	public static Performable getFarmUnscheduledPropertySubClassTypeByClassUsingGetOnTheStaticValuesController(String farmUPC, String pageSize, String page){
		return Task.where(
		"{0} Retrieves all of the possible values for Unscheduled Farm Property Sub-Class Types", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("farmUPC", farmUPC).queryParam("pageSize", pageSize).queryParam("page", page).get(GET_FARM_UNSCHEDULED_PROPERTY_SUB_CLASS_TYPE_BY_CLASS_USING_GET_ON_THE_STATIC_VALUES_CONTROLLER);
			}
		);
	}

	public static Performable getOccupationValuesByTextUsingGetOnTheStaticValuesController(String pageSize, String page, String text, String filter){
		return Task.where(
		"{0} Retrieves Occupations that contains the given text", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("text", text).queryParam("filter", filter).get(GET_OCCUPATION_VALUES_BY_TEXT_USING_GET_ON_THE_STATIC_VALUES_CONTROLLER);
			}
		);
	}



}
