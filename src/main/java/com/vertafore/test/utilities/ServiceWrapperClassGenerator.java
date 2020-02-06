package com.vertafore.test.utilities;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.Set;

public class ServiceWrapperClassGenerator {

  static final String json =
      "{\"swagger\":\"2.0\",\"info\":{\"description\":\"TODO: Please specify the description of this service\",\"version\":\"1\",\"title\":\"QuickBooks Integration Service Service API\"},\"host\":\"api.dev.titan.v4af.com\",\"basePath\":\"/quickbooks-integration\",\"tags\":[{\"name\":\"authorization-controller-v-1\",\"description\":\"Authorization Controller V 1\"},{\"name\":\"account-mapping-controller-v-1\",\"description\":\"Account Mapping Controller V 1\"},{\"name\":\"quickbooks-account-controller-v-1\",\"description\":\"Quickbooks Account Controller V 1\"},{\"name\":\"status-controller-v-1\",\"description\":\"Status Controller V 1\"}],\"paths\":{\"/quickbooks-integration/v1/{productId}/{tenantId}/entities/{entityId}/accounts/{accountId}/mapping\":{\"get\":{\"tags\":[\"account-mapping-controller-v-1\"],\"summary\":\"Get Account Mapping by Account ID\",\"description\":\"Retrieves the Account Mapping of an associated Account.\",\"operationId\":\"getAccountMappingUsingGET\",\"consumes\":[\"application/json\"],\"produces\":[\"application/json\"],\"parameters\":[{\"name\":\"productId\",\"in\":\"path\",\"description\":\"ID of the product for which the request is being made\",\"required\":false,\"type\":\"string\"},{\"name\":\"tenantId\",\"in\":\"path\",\"description\":\"ID of the tenant for which the request is being made\",\"required\":false,\"type\":\"string\"},{\"name\":\"entityId\",\"in\":\"path\",\"description\":\"ID of the entity for which the request is being made\",\"required\":false,\"type\":\"string\"},{\"name\":\"accountId\",\"in\":\"path\",\"description\":\"ID of the Account\",\"required\":true,\"type\":\"string\"}],\"responses\":{\"200\":{\"description\":\"OK\",\"schema\":{\"$ref\":\"#/definitions/SingleResponseV1«AccountMappingV1»\"}},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"},\"404\":{\"description\":\"Not Found\"}}},\"post\":{\"tags\":[\"account-mapping-controller-v-1\"],\"summary\":\"Create Account Mapping by Account ID\",\"description\":\"Creates an account mapping for the associated account.\",\"operationId\":\"createAccountMappingUsingPOST\",\"consumes\":[\"application/json\"],\"produces\":[\"application/json\"],\"parameters\":[{\"name\":\"productId\",\"in\":\"path\",\"description\":\"ID of the product for which the request is being made\",\"required\":false,\"type\":\"string\"},{\"name\":\"tenantId\",\"in\":\"path\",\"description\":\"ID of the tenant for which the request is being made\",\"required\":false,\"type\":\"string\"},{\"name\":\"entityId\",\"in\":\"path\",\"description\":\"ID of the entity for which the request is being made\",\"required\":false,\"type\":\"string\"},{\"name\":\"accountId\",\"in\":\"path\",\"description\":\"ID of the Account\",\"required\":true,\"type\":\"string\"},{\"in\":\"body\",\"name\":\"accountMapping\",\"description\":\"accountMapping\",\"required\":true,\"schema\":{\"$ref\":\"#/definitions/AccountMappingV1\"}}],\"responses\":{\"201\":{\"description\":\"Created\",\"schema\":{\"$ref\":\"#/definitions/SingleResponseV1«AccountMappingV1»\"}},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"},\"404\":{\"description\":\"Not Found\"}}},\"patch\":{\"tags\":[\"account-mapping-controller-v-1\"],\"summary\":\"Update/Patch Account mapping by Account ID\",\"description\":\"Patch the Account mapping specified by the Account ID provided in the URI. The only field that can be updated is the `key` field. \",\"operationId\":\"updateAccountMappingUsingPATCH\",\"consumes\":[\"application/json-patch+json\"],\"produces\":[\"application/json\"],\"parameters\":[{\"name\":\"productId\",\"in\":\"path\",\"description\":\"ID of the product for which the request is being made\",\"required\":false,\"type\":\"string\"},{\"name\":\"tenantId\",\"in\":\"path\",\"description\":\"ID of the tenant for which the request is being made\",\"required\":false,\"type\":\"string\"},{\"name\":\"entityId\",\"in\":\"path\",\"description\":\"ID of the entity for which the request is being made\",\"required\":false,\"type\":\"string\"},{\"name\":\"accountId\",\"in\":\"path\",\"description\":\"ID of the Account\",\"required\":true,\"type\":\"string\"},{\"in\":\"body\",\"name\":\"patch\",\"description\":\"A JSON Patch to update an existing key in Account Mapping\",\"required\":true,\"schema\":{\"$ref\":\"#/definitions/JsonPatch«AccountMappingV1»\"}}],\"responses\":{\"200\":{\"description\":\"OK\",\"schema\":{\"$ref\":\"#/definitions/SingleResponseV1«AccountMappingV1»\"}},\"204\":{\"description\":\"No Content\"},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"}}}},\"/quickbooks-integration/v1/{productId}/{tenantId}/entities/{entityId}/accounts{?pageSize,page}\":{\"get\":{\"tags\":[\"quickbooks-account-controller-v-1\"],\"summary\":\"Get all QuickBooks Accounts\",\"description\":\"Retrieves all Accounts from the QuickBooks API using existing Quickbooks authorization. Quickbooks authorization must be stored for this endpoint to work.\",\"operationId\":\"getQuickBooksAccountsUsingGET\",\"consumes\":[\"application/json\"],\"produces\":[\"application/json\"],\"parameters\":[{\"name\":\"productId\",\"in\":\"path\",\"description\":\"ID of the product for which the request is being made\",\"required\":false,\"type\":\"string\"},{\"name\":\"tenantId\",\"in\":\"path\",\"description\":\"ID of the tenant for which the request is being made\",\"required\":false,\"type\":\"string\"},{\"name\":\"entityId\",\"in\":\"path\",\"description\":\"ID of the entity for which the request is being made\",\"required\":false,\"type\":\"string\"},{\"name\":\"pageSize\",\"in\":\"query\",\"description\":\"The maximum number of items to include in the response\",\"required\":false,\"type\":\"integer\",\"format\":\"int32\"},{\"name\":\"page\",\"in\":\"query\",\"description\":\"The page of items to retrieve\",\"required\":false,\"type\":\"integer\",\"format\":\"int32\"}],\"responses\":{\"200\":{\"description\":\"OK\",\"schema\":{\"$ref\":\"#/definitions/PagedResponseV1«QuickbooksAccountV1,LimitOffsetPagingInfoV1»\"}},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"},\"404\":{\"description\":\"Not Found\"}}}},\"/quickbooks-integration/v1/{productId}/{tenantId}/entities/{entityId}/authorization\":{\"put\":{\"tags\":[\"authorization-controller-v-1\"],\"summary\":\"Store QuickBooks Credentials\",\"description\":\"Requests and stores credentials from QuickBooks, to be used for service operations.\",\"operationId\":\"putAuthorizationCredentialUsingPUT\",\"consumes\":[\"application/json\"],\"produces\":[\"application/json\"],\"parameters\":[{\"name\":\"productId\",\"in\":\"path\",\"description\":\"ID of the product for which the request is being made\",\"required\":false,\"type\":\"string\"},{\"name\":\"tenantId\",\"in\":\"path\",\"description\":\"ID of the tenant for which the request is being made\",\"required\":false,\"type\":\"string\"},{\"name\":\"entityId\",\"in\":\"path\",\"description\":\"ID of the entity for which the request is being made\",\"required\":false,\"type\":\"string\"},{\"in\":\"body\",\"name\":\"intuitInfo\",\"description\":\"intuitInfo\",\"required\":true,\"schema\":{\"$ref\":\"#/definitions/QuickBooksInfoV1\"}}],\"responses\":{\"200\":{\"description\":\"OK\",\"schema\":{\"$ref\":\"#/definitions/EmptyResponseV1\"}},\"201\":{\"description\":\"Created\"},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"},\"404\":{\"description\":\"Not Found\"}}},\"delete\":{\"tags\":[\"authorization-controller-v-1\"],\"summary\":\"Delete QuickBooks Credentials\",\"description\":\"Deletes current QuickBooks credentials from the system.\",\"operationId\":\"deleteAuthorizationCredentialUsingDELETE\",\"consumes\":[\"application/json\"],\"produces\":[\"application/json\"],\"parameters\":[{\"name\":\"productId\",\"in\":\"path\",\"description\":\"ID of the product for which the request is being made\",\"required\":false,\"type\":\"string\"},{\"name\":\"tenantId\",\"in\":\"path\",\"description\":\"ID of the tenant for which the request is being made\",\"required\":false,\"type\":\"string\"},{\"name\":\"entityId\",\"in\":\"path\",\"description\":\"ID of the entity for which the request is being made\",\"required\":false,\"type\":\"string\"}],\"responses\":{\"200\":{\"description\":\"OK\",\"schema\":{\"$ref\":\"#/definitions/EmptyResponseV1\"}},\"204\":{\"description\":\"No Content\"},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"}}}},\"/quickbooks-integration/v1/{productId}/{tenantId}/entities/{entityId}/authorization/connect-url\":{\"get\":{\"tags\":[\"authorization-controller-v-1\"],\"summary\":\"Get QuickBooks URL\",\"description\":\"Retrieves and formats the URL for UI to present a login flow.\",\"operationId\":\"connectToQuickBooksUsingGET\",\"consumes\":[\"application/json\"],\"produces\":[\"application/json\"],\"parameters\":[{\"name\":\"productId\",\"in\":\"path\",\"description\":\"ID of the product for which the request is being made\",\"required\":false,\"type\":\"string\"},{\"name\":\"tenantId\",\"in\":\"path\",\"description\":\"ID of the tenant for which the request is being made\",\"required\":false,\"type\":\"string\"},{\"name\":\"entityId\",\"in\":\"path\",\"description\":\"ID of the entity for which the request is being made\",\"required\":false,\"type\":\"string\"}],\"responses\":{\"200\":{\"description\":\"OK\",\"schema\":{\"$ref\":\"#/definitions/SingleResponseV1«string»\"}},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"},\"404\":{\"description\":\"Not Found\"}}}},\"/quickbooks-integration/v1/{productId}/{tenantId}/entities/{entityId}/authorization/status\":{\"get\":{\"tags\":[\"status-controller-v-1\"],\"summary\":\"Get QuickBooks Authorization Status.\",\"description\":\"Retrieves the status of the QuickBooks integration under the given tenant/entity combo.\",\"operationId\":\"retrieveQuickBooksIntegrationStatusUsingGET\",\"consumes\":[\"application/json\"],\"produces\":[\"application/json\"],\"parameters\":[{\"name\":\"productId\",\"in\":\"path\",\"description\":\"ID of the product for which the request is being made\",\"required\":false,\"type\":\"string\"},{\"name\":\"tenantId\",\"in\":\"path\",\"description\":\"ID of the tenant for which the request is being made\",\"required\":false,\"type\":\"string\"},{\"name\":\"entityId\",\"in\":\"path\",\"description\":\"ID of the entity for which the request is being made\",\"required\":false,\"type\":\"string\"}],\"responses\":{\"200\":{\"description\":\"OK\",\"schema\":{\"$ref\":\"#/definitions/SingleResponseV1«AuthorizationStatusV1»\"}},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"},\"404\":{\"description\":\"Not Found\"}}}}},\"definitions\":{\"AccountMappingV1\":{\"type\":\"object\",\"properties\":{\"createdOn\":{\"type\":\"string\",\"format\":\"date-time\",\"example\":\"1970-01-01T00:00:00.000Z\",\"description\":\"When the Account Mapping was created.\",\"readOnly\":true},\"quickbooksAccountId\":{\"type\":\"string\",\"example\":\"12345\",\"description\":\"Mapping key.\"},\"status\":{\"type\":\"string\",\"example\":\"OK\",\"description\":\"Account Mapping status\",\"enum\":[\"OK\",\"ERROR\"]},\"titanAccountId\":{\"type\":\"string\",\"description\":\"The Account ID for the Account Mapping relation.\",\"readOnly\":true},\"updatedOn\":{\"type\":\"string\",\"format\":\"date-time\",\"example\":\"1970-01-01T00:00:00.000Z\",\"description\":\"When the Account Mapping was last modified.\",\"readOnly\":true}}},\"AuthorizationStatusV1\":{\"type\":\"object\",\"required\":[\"message\",\"status\"],\"properties\":{\"message\":{\"type\":\"string\",\"example\":\"This is a message.\",\"description\":\"The message accompanying the current status. May contain details from errors received from QuickBooks.\"},\"status\":{\"type\":\"string\",\"example\":\"OK\",\"description\":\"The code indicating the status of the authorization.\",\"enum\":[\"OK\",\"NONE\",\"EXPIRED\",\"INVALID\"]}},\"description\":\"The body to use when retrieving the status of a QuickBooks authorization.\"},\"EmptyResponseV1\":{\"type\":\"object\",\"properties\":{\"error\":{\"description\":\"This field will contain information about the error if one occurred\",\"readOnly\":true,\"$ref\":\"#/definitions/ErrorResponseV1\"},\"requestId\":{\"type\":\"string\",\"description\":\"The unique ID of this request.\",\"readOnly\":true},\"spanId\":{\"type\":\"string\",\"description\":\"The unique ID of the span.\",\"readOnly\":true},\"traceId\":{\"type\":\"string\",\"description\":\"The unique ID of the trace.\",\"readOnly\":true}}},\"ErrorResponseV1\":{\"type\":\"object\",\"properties\":{\"message\":{\"type\":\"string\"},\"status\":{\"type\":\"integer\",\"format\":\"int32\"}}},\"JsonPatch«AccountMappingV1»\":{\"type\":\"object\",\"description\":\"A JSON Patch (see http://jsonpatch.com/)\"},\"LimitOffsetPagingInfoV1\":{\"type\":\"object\",\"properties\":{\"method\":{\"type\":\"string\",\"description\":\"The method of paging that this pagingInfo contains\",\"readOnly\":true},\"numberOfElements\":{\"type\":\"integer\",\"format\":\"int32\",\"description\":\"The number of data elements in this response.\",\"readOnly\":true},\"page\":{\"type\":\"integer\",\"format\":\"int32\",\"description\":\"The page number of the response.\",\"readOnly\":true},\"pageSize\":{\"type\":\"integer\",\"format\":\"int32\",\"description\":\"The page size of this response.\",\"readOnly\":true},\"totalElements\":{\"type\":\"integer\",\"format\":\"int32\",\"description\":\"The total number of elements\",\"readOnly\":true},\"totalPages\":{\"type\":\"integer\",\"format\":\"int32\",\"description\":\"The total number of pages, based on the pageSize and totalElements\",\"readOnly\":true}}},\"PagedResponseV1«QuickbooksAccountV1,LimitOffsetPagingInfoV1»\":{\"type\":\"object\",\"properties\":{\"content\":{\"type\":\"array\",\"description\":\"The data elements requested\",\"readOnly\":true,\"items\":{\"$ref\":\"#/definitions/QuickbooksAccountV1\"}},\"error\":{\"description\":\"This field will contain information about the error if one occurred\",\"readOnly\":true,\"$ref\":\"#/definitions/ErrorResponseV1\"},\"paging\":{\"description\":\"The paging method used\",\"readOnly\":true,\"$ref\":\"#/definitions/LimitOffsetPagingInfoV1\"},\"requestId\":{\"type\":\"string\",\"description\":\"The unique ID of this request.\",\"readOnly\":true},\"spanId\":{\"type\":\"string\",\"description\":\"The unique ID of the span.\",\"readOnly\":true},\"traceId\":{\"type\":\"string\",\"description\":\"The unique ID of the trace.\",\"readOnly\":true}}},\"QuickBooksInfoV1\":{\"type\":\"object\",\"required\":[\"authorizationCode\",\"realmId\"],\"properties\":{\"authorizationCode\":{\"type\":\"string\",\"example\":\"authorization_code\",\"description\":\"The authorization code Intuit has granted from the user successfully logging in. Can be exchanged for an auth token and refresh token\"},\"realmId\":{\"type\":\"string\",\"example\":\"123456748912\",\"description\":\"The Realm Id representing the company in QuickBooks.\"}},\"description\":\"The body to use when sending in the auth code for QuickBooks integration.\"},\"QuickbooksAccountV1\":{\"type\":\"object\",\"properties\":{\"key\":{\"type\":\"string\",\"example\":\"{ id: 45fd5f31-98e1-4315-8475-eff92631d3df }\",\"description\":\"Unique key of the Account.\",\"readOnly\":true},\"name\":{\"type\":\"string\",\"example\":\"Accounts Payable (A/P)\",\"description\":\"Name of the Account\",\"readOnly\":true},\"number\":{\"type\":\"string\",\"example\":\"1000000\",\"description\":\"Account Number\",\"readOnly\":true}},\"description\":\"The account information from Quickbooks\"},\"SingleResponseV1«AccountMappingV1»\":{\"type\":\"object\",\"properties\":{\"content\":{\"description\":\"The response data element\",\"readOnly\":true,\"$ref\":\"#/definitions/AccountMappingV1\"},\"error\":{\"description\":\"This field will contain information about the error if one occurred\",\"readOnly\":true,\"$ref\":\"#/definitions/ErrorResponseV1\"},\"requestId\":{\"type\":\"string\",\"description\":\"The unique ID of this request.\",\"readOnly\":true},\"spanId\":{\"type\":\"string\",\"description\":\"The unique ID of the span.\",\"readOnly\":true},\"traceId\":{\"type\":\"string\",\"description\":\"The unique ID of the trace.\",\"readOnly\":true}}},\"SingleResponseV1«AuthorizationStatusV1»\":{\"type\":\"object\",\"properties\":{\"content\":{\"description\":\"The response data element\",\"readOnly\":true,\"$ref\":\"#/definitions/AuthorizationStatusV1\"},\"error\":{\"description\":\"This field will contain information about the error if one occurred\",\"readOnly\":true,\"$ref\":\"#/definitions/ErrorResponseV1\"},\"requestId\":{\"type\":\"string\",\"description\":\"The unique ID of this request.\",\"readOnly\":true},\"spanId\":{\"type\":\"string\",\"description\":\"The unique ID of the span.\",\"readOnly\":true},\"traceId\":{\"type\":\"string\",\"description\":\"The unique ID of the trace.\",\"readOnly\":true}}},\"SingleResponseV1«string»\":{\"type\":\"object\",\"properties\":{\"content\":{\"type\":\"string\",\"description\":\"The response data element\",\"readOnly\":true},\"error\":{\"description\":\"This field will contain information about the error if one occurred\",\"readOnly\":true,\"$ref\":\"#/definitions/ErrorResponseV1\"},\"requestId\":{\"type\":\"string\",\"description\":\"The unique ID of this request.\",\"readOnly\":true},\"spanId\":{\"type\":\"string\",\"description\":\"The unique ID of the span.\",\"readOnly\":true},\"traceId\":{\"type\":\"string\",\"description\":\"The unique ID of the trace.\",\"readOnly\":true}}}}}\n";

  static final String CLASS_TEMPLATE = "public class Use%sTo {\n\n%s\n\n}";
  static final String METHOD_TEMPLATE =
      "public static Performable %s(%s){\n"
          + "\treturn Task.where(\n\t\t\"{0} %s\", \n\t\t\tactor -> {\n\t\t\t\t%s;\n\t\t\t}\n\t)"
          + ";\n}\n\n";

  static final String CONSTANT_TEMPLATE = "private static final String %s = \"%s\";\n";

  static final String start = "rest().with().";
  static final String pathParam = "pathParam(%s).";
  static final String body = "body(body).";
  static final String get = "get(as(actor).toEndpoint(%s))";
  static final String post = "post(as(actor).toEndpoint(%s))";
  static final String patch = "patch(as(actor).toEndpoint(%s))";
  static final String put = "put(as(actor).toEndpoint(%s))";
  static final String delete = "delete(as(actor).toEndpoint(%s))";

  public static void generateClasses() {

    StringBuilder constantsResults = new StringBuilder();
    StringBuilder methodResults = new StringBuilder();

    JsonObject swaggerJsonObject = new Gson().fromJson(json, JsonObject.class);
    JsonObject pathsJsonObject = swaggerJsonObject.get("paths").getAsJsonObject();
    new Gson().fromJson(json, JsonObject.class);
    Set<String> paths = pathsJsonObject.getAsJsonObject().keySet();

    for (String path : paths) {

      Set<String> pathApiCalls = pathsJsonObject.get(path).getAsJsonObject().keySet();
      JsonObject pathJsonObject = pathsJsonObject.getAsJsonObject(path);

      for (String apiCall : pathApiCalls) {

        String summary = pathJsonObject.get(apiCall).getAsJsonObject().get("summary").getAsString();
        constantsResults.append(generateConstant(path, summary));
        methodResults.append(
            generateMethod(summary, pathJsonObject.get(apiCall).getAsJsonObject()));
      }
    }
    System.out.println(
        String.format(
            CLASS_TEMPLATE,
            "NOT_SET_UP_YET",
            constantsResults.toString() + "\n" + methodResults.toString()));
  }

  private static String generateConstant(String path, String summary) {
    path = generateEndpointFromPath(path);
    summary = generateConstantNameFromSummary(summary);
    return String.format(CONSTANT_TEMPLATE, summary, path);
  }

  private static String generateEndpointFromPath(String path) {
    return path.replaceAll("^.*\\{entityId}", "");
  }

  private static String generateConstantNameFromSummary(String summary) {
    return summary
        .toUpperCase()
        .trim()
        .replaceAll("[^A-z_ ]+", "")
        .replaceAll("\\s", "_")
        .replaceAll("UPDATEPATCH", "PATCH");
  }

  private static String generateMethod(String summary, JsonObject apiCallJsonObject) {
    String methodName = getCamalCaseMethodNameFromSummary(summary);
    String methodParams = generateMethodParamsFromapiCallJsonObject(apiCallJsonObject);
    String taskDescription = apiCallJsonObject.get("description").getAsString();
    String restCallMethodChain = "";
    return String.format(
        METHOD_TEMPLATE, methodName, methodParams, taskDescription, restCallMethodChain);
  }

  // TODO: room for refactoring
  private static String getCamalCaseMethodNameFromSummary(String str) {
    String[] arr = str.toLowerCase().trim().split("\\s");
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < arr.length; i++) {
      if (i == 0) {
        result.append(arr[i]);
      } else {
        char firstChar = Character.toUpperCase(arr[i].charAt(0));
        result.append(firstChar + (arr[i].length() > 1 ? arr[i].substring(1) : ""));
      }
    }
    return result.toString().replaceAll("update/patch", "patch").replaceAll("\\.", "");
  }

  // TODO: room for refactoring
  private static String generateMethodParamsFromapiCallJsonObject(JsonObject apiCallJsonObject) {
    StringBuilder result = new StringBuilder();
    apiCallJsonObject
        .get("parameters")
        .getAsJsonArray()
        .forEach(
            p -> {
              JsonObject param = p.getAsJsonObject();
              if (param.get("in").getAsString().equalsIgnoreCase("body")) {
                result.append("Object body, ");
              } else {
                boolean paramIsADefault =
                    param.get("name").getAsString().equalsIgnoreCase("productId")
                        || param.get("name").getAsString().equalsIgnoreCase("tenantId")
                        || param.get("name").getAsString().equalsIgnoreCase("entityId");

                if (!paramIsADefault) {
                  result.append("String ").append(param.get("name")).append(", ");
                }
              }
            });
    return result.toString().replaceAll(", $", "").replaceAll("\"", "");
  }

  private static String generateClassNameFromBasePath(String str) {
    String[] arr = str.toLowerCase().trim().split("[^A-z]+");
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < arr.length; i++) {
      char firstChar = Character.toUpperCase(arr[i].charAt(0));
      result.append(firstChar).append(arr[i].length() > 1 ? arr[i].substring(1) : "");
    }
    return result.toString();
  }

  private static String getRestPathParamsFromEndPoint(String endpoint) {
    String[] arr = endpoint.split("/");
    String result = "";
    for (int i = 0; i < arr.length; i++) {
      if (arr[i].contains("{")) {
        String part1 = arr[i].replaceAll("\\{", "\"");
        part1 = part1.replaceAll("}", "\"");

        String part2 = arr[i].replaceAll("\\{", "");
        part2 = part2.replaceAll("}", "");
        result += String.format(pathParam, part1 + ", " + part2);
      }
    }
    return result;
  }

  private static String getRestCall(String type, String params, String endpoint) {
    params = params.replaceAll("Object body,", "");
    String[] arr = endpoint.split("/");
    String result = "";
    if (type.equalsIgnoreCase("get")) {
      if (params.isEmpty()) {
        result = start + String.format(get, endpoint);
      } else {
        result = start + params + String.format(get, endpoint);
      }
    } else if (type.equalsIgnoreCase("delete")) {
      if (params.isEmpty()) {
        result = start + String.format(delete, endpoint);
      } else {
        result = start + params + String.format(delete, endpoint);
      }
    } else if (type.equalsIgnoreCase("put")) {
      if (params.isEmpty()) {
        result = start + body + String.format(put, endpoint);
      } else {
        result = start + body + params + String.format(put, endpoint);
      }
    } else if (type.equalsIgnoreCase("post")) {
      if (params.isEmpty()) {
        result = start + body + String.format(post, endpoint);
      } else {
        result = start + body + params + String.format(post, endpoint);
      }
    } else if (type.equalsIgnoreCase("patch")) {
      if (params.isEmpty()) {
        result = start + body + String.format(patch, endpoint);
      } else {
        result = start + body + params + String.format(patch, endpoint);
      }
    } else {
      throw new IllegalArgumentException("set the type right, fool");
    }
    return result;
  }
}
