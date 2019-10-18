package com.vertafore.test.common.servicewrappers.exposure;

import com.vertafore.autotest.policies.dtos.exposuregroup.ExposureGroupV1DTO;
import com.vertafore.test.common.util.ServiceUtils;
import io.restassured.response.Response;
import java.io.IOException;

public class ExposureService {

  private ServiceUtils serviceUtils;

  public ExposureService() throws IOException {
    serviceUtils = new ServiceUtils();
  }

  // endpoints
  public static final String BASE_PATH = "/{service}/v1/{productId}/{tenantId}/entities/{entityId}";

  //    Create an ExposureGroup
  public static final String CREATE_EXPOSURE_GROUP = BASE_PATH + "/exposure-groups";

  //    GET Exposure Group by id
  public static final String GET_EXPOSURE_GROUP_BY_ID =
      BASE_PATH + "/exposure-groups/{exposureGroupId}";

  //   DELETE Exposure Group by id
  public static final String DELETE_EXPOSURE_GROUP_BY_ID =
      BASE_PATH + "/exposure-groups/{exposureGroupId}";

  //   PATCH an Exposure Group by id
  public static final String UPDATE_EXPOSURE_GROUP_BY_ID =
      BASE_PATH + "/exposure-groups/{exposureGroupId}";

  //    Static Values Controller V1
  //    GET  Retrieves all of the possible values for Unscheduled Farm Property Sub-Class Types
  public static final String GET_STATIC_VALUES_UNSCHEDULED_FARM_PROPERTY =
      BASE_PATH + "/static-values/farm-unscheduled-property-class-types/{farmUPC}{?pageSize,page}";

  //    GET   Retrieves Occupations that contains the given text
  public static final String GET_STATIC_VALUES_OCCUPATIONS_BY_TEXT =
      BASE_PATH + "/occupations?filter=byText{&pageSize,page,text}";

  //    GET  Retrieves all of the possible values for Occupations
  public static final String GET_STATIC_VALUES_OCCUPATIONS_ALL =
      BASE_PATH + "/occupations{?pageSize,page}";

  //    Additional Coverage Controller V1
  //    GET Retrieves the AdditionalCoverages list that match the given Exposure Type code
  public static final String GET_ADDITIONAL_COVERAGES_BY_EXPOSURE_TYPE =
      BASE_PATH + "/additional-coverages?filter=byExposureCode{&pageSize,page,exposureCode}";

  //    GET Retrieves the AdditionalCoverages list that match the given Exposure Type and Category
  public static final String GET_ADDITIONAL_COVERAGES_BY_EXPOSURE_TYPE_AND_CATEGORY =
      BASE_PATH
          + "/additional-coverages?filter=byExposureCodeAndCategory{&pageSize,page,exposureCode,category}";

  //    GET Retrieves the AdditionalCoverages list that match the given Exposure Type excluding the
  // given Category
  public static final String GET_ADDITIONAL_COVERAGES_BY_EXPOSURE_TYPE_EXCLUDING_CATEGORY =
      BASE_PATH
          + "/additional-coverages?filter=byExposureCodeExcludeCategory{&pageSize,page,exposureCode,categoryToExclude}";

  // Exposure Type Controller V1
  //  GET     Retrieves the ExposureTypes list
  public static final String GET_LIST_OF_EXPOSURE_TYPES =
      BASE_PATH + "/exposure-types{?pageSize,page}";

  // post an exposure group
  public ExposureGroupV1DTO postExposureGroup(ExposureGroupV1DTO requestBody) {
    Response response = serviceUtils.sendPostRequest(CREATE_EXPOSURE_GROUP, requestBody);
    // return the ExposureGroupV1DTO object
    return response.getBody().jsonPath().getObject("content", ExposureGroupV1DTO.class);
  }
}
