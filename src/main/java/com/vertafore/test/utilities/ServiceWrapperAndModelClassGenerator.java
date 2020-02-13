package com.vertafore.test.utilities;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Set;

public class ServiceWrapperAndModelClassGenerator {

  static final String json =
      "{\n"
          + "\"swagger\": \"2.0\",\n"
          + "\"info\": {\n"
          + "\"description\": \"TODO: Please specify the description of this service\",\n"
          + "\"version\": \"1\",\n"
          + "\"title\": \"QuickBooks Integration Service Service API\"\n"
          + "},\n"
          + "\"host\": \"api.dev.titan.v4af.com\",\n"
          + "\"basePath\": \"/quickbooks-integration\",\n"
          + "\"tags\": [\n"
          + "{\n"
          + "\"name\": \"authorization-controller-v-1\",\n"
          + "\"description\": \"Authorization Controller V 1\"\n"
          + "},\n"
          + "{\n"
          + "\"name\": \"account-mapping-controller-v-1\",\n"
          + "\"description\": \"Account Mapping Controller V 1\"\n"
          + "},\n"
          + "{\n"
          + "\"name\": \"quickbooks-account-controller-v-1\",\n"
          + "\"description\": \"Quickbooks Account Controller V 1\"\n"
          + "},\n"
          + "{\n"
          + "\"name\": \"status-controller-v-1\",\n"
          + "\"description\": \"Status Controller V 1\"\n"
          + "}\n"
          + "],\n"
          + "\"paths\": {\n"
          + "\"/quickbooks-integration/v1/{productId}/{tenantId}/entities/{entityId}/accounts/{accountId}/mapping\": {\n"
          + "\"get\": {\n"
          + "\"tags\": [\n"
          + "\"account-mapping-controller-v-1\"\n"
          + "],\n"
          + "\"summary\": \"Get Account Mapping by Account ID\",\n"
          + "\"description\": \"Retrieves the Account Mapping of an associated Account.\",\n"
          + "\"operationId\": \"getAccountMappingUsingGET\",\n"
          + "\"consumes\": [\n"
          + "\"application/json\"\n"
          + "],\n"
          + "\"produces\": [\n"
          + "\"application/json\"\n"
          + "],\n"
          + "\"parameters\": [\n"
          + "{\n"
          + "\"name\": \"productId\",\n"
          + "\"in\": \"path\",\n"
          + "\"description\": \"ID of the product for which the request is being made\",\n"
          + "\"required\": false,\n"
          + "\"type\": \"string\"\n"
          + "},\n"
          + "{\n"
          + "\"name\": \"tenantId\",\n"
          + "\"in\": \"path\",\n"
          + "\"description\": \"ID of the tenant for which the request is being made\",\n"
          + "\"required\": false,\n"
          + "\"type\": \"string\"\n"
          + "},\n"
          + "{\n"
          + "\"name\": \"entityId\",\n"
          + "\"in\": \"path\",\n"
          + "\"description\": \"ID of the entity for which the request is being made\",\n"
          + "\"required\": false,\n"
          + "\"type\": \"string\"\n"
          + "},\n"
          + "{\n"
          + "\"name\": \"accountId\",\n"
          + "\"in\": \"path\",\n"
          + "\"description\": \"ID of the Account\",\n"
          + "\"required\": true,\n"
          + "\"type\": \"string\"\n"
          + "}\n"
          + "],\n"
          + "\"responses\": {\n"
          + "\"200\": {\n"
          + "\"description\": \"OK\",\n"
          + "\"schema\": {\n"
          + "\"$ref\": \"#/definitions/SingleResponseV1«AccountMappingV1»\"\n"
          + "}\n"
          + "},\n"
          + "\"401\": {\n"
          + "\"description\": \"Unauthorized\"\n"
          + "},\n"
          + "\"403\": {\n"
          + "\"description\": \"Forbidden\"\n"
          + "},\n"
          + "\"404\": {\n"
          + "\"description\": \"Not Found\"\n"
          + "}\n"
          + "}\n"
          + "},\n"
          + "\"post\": {\n"
          + "\"tags\": [\n"
          + "\"account-mapping-controller-v-1\"\n"
          + "],\n"
          + "\"summary\": \"Create Account Mapping by Account ID\",\n"
          + "\"description\": \"Creates an account mapping for the associated account.\",\n"
          + "\"operationId\": \"createAccountMappingUsingPOST\",\n"
          + "\"consumes\": [\n"
          + "\"application/json\"\n"
          + "],\n"
          + "\"produces\": [\n"
          + "\"application/json\"\n"
          + "],\n"
          + "\"parameters\": [\n"
          + "{\n"
          + "\"name\": \"productId\",\n"
          + "\"in\": \"path\",\n"
          + "\"description\": \"ID of the product for which the request is being made\",\n"
          + "\"required\": false,\n"
          + "\"type\": \"string\"\n"
          + "},\n"
          + "{\n"
          + "\"name\": \"tenantId\",\n"
          + "\"in\": \"path\",\n"
          + "\"description\": \"ID of the tenant for which the request is being made\",\n"
          + "\"required\": false,\n"
          + "\"type\": \"string\"\n"
          + "},\n"
          + "{\n"
          + "\"name\": \"entityId\",\n"
          + "\"in\": \"path\",\n"
          + "\"description\": \"ID of the entity for which the request is being made\",\n"
          + "\"required\": false,\n"
          + "\"type\": \"string\"\n"
          + "},\n"
          + "{\n"
          + "\"name\": \"accountId\",\n"
          + "\"in\": \"path\",\n"
          + "\"description\": \"ID of the Account\",\n"
          + "\"required\": true,\n"
          + "\"type\": \"string\"\n"
          + "},\n"
          + "{\n"
          + "\"in\": \"body\",\n"
          + "\"name\": \"accountMapping\",\n"
          + "\"description\": \"accountMapping\",\n"
          + "\"required\": true,\n"
          + "\"schema\": {\n"
          + "\"$ref\": \"#/definitions/AccountMappingV1\"\n"
          + "}\n"
          + "}\n"
          + "],\n"
          + "\"responses\": {\n"
          + "\"201\": {\n"
          + "\"description\": \"Created\",\n"
          + "\"schema\": {\n"
          + "\"$ref\": \"#/definitions/SingleResponseV1«AccountMappingV1»\"\n"
          + "}\n"
          + "},\n"
          + "\"401\": {\n"
          + "\"description\": \"Unauthorized\"\n"
          + "},\n"
          + "\"403\": {\n"
          + "\"description\": \"Forbidden\"\n"
          + "},\n"
          + "\"404\": {\n"
          + "\"description\": \"Not Found\"\n"
          + "}\n"
          + "}\n"
          + "},\n"
          + "\"patch\": {\n"
          + "\"tags\": [\n"
          + "\"account-mapping-controller-v-1\"\n"
          + "],\n"
          + "\"summary\": \"Update/Patch Account mapping by Account ID\",\n"
          + "\"description\": \"Patch the Account mapping specified by the Account ID provided in the URI. The only field that can be updated is the `key` field. \",\n"
          + "\"operationId\": \"updateAccountMappingUsingPATCH\",\n"
          + "\"consumes\": [\n"
          + "\"application/json-patch+json\"\n"
          + "],\n"
          + "\"produces\": [\n"
          + "\"application/json\"\n"
          + "],\n"
          + "\"parameters\": [\n"
          + "{\n"
          + "\"name\": \"productId\",\n"
          + "\"in\": \"path\",\n"
          + "\"description\": \"ID of the product for which the request is being made\",\n"
          + "\"required\": false,\n"
          + "\"type\": \"string\"\n"
          + "},\n"
          + "{\n"
          + "\"name\": \"tenantId\",\n"
          + "\"in\": \"path\",\n"
          + "\"description\": \"ID of the tenant for which the request is being made\",\n"
          + "\"required\": false,\n"
          + "\"type\": \"string\"\n"
          + "},\n"
          + "{\n"
          + "\"name\": \"entityId\",\n"
          + "\"in\": \"path\",\n"
          + "\"description\": \"ID of the entity for which the request is being made\",\n"
          + "\"required\": false,\n"
          + "\"type\": \"string\"\n"
          + "},\n"
          + "{\n"
          + "\"name\": \"accountId\",\n"
          + "\"in\": \"path\",\n"
          + "\"description\": \"ID of the Account\",\n"
          + "\"required\": true,\n"
          + "\"type\": \"string\"\n"
          + "},\n"
          + "{\n"
          + "\"in\": \"body\",\n"
          + "\"name\": \"patch\",\n"
          + "\"description\": \"A JSON Patch to update an existing key in Account Mapping\",\n"
          + "\"required\": true,\n"
          + "\"schema\": {\n"
          + "\"$ref\": \"#/definitions/JsonPatch«AccountMappingV1»\"\n"
          + "}\n"
          + "}\n"
          + "],\n"
          + "\"responses\": {\n"
          + "\"200\": {\n"
          + "\"description\": \"OK\",\n"
          + "\"schema\": {\n"
          + "\"$ref\": \"#/definitions/SingleResponseV1«AccountMappingV1»\"\n"
          + "}\n"
          + "},\n"
          + "\"204\": {\n"
          + "\"description\": \"No Content\"\n"
          + "},\n"
          + "\"401\": {\n"
          + "\"description\": \"Unauthorized\"\n"
          + "},\n"
          + "\"403\": {\n"
          + "\"description\": \"Forbidden\"\n"
          + "}\n"
          + "}\n"
          + "}\n"
          + "},\n"
          + "\"/quickbooks-integration/v1/{productId}/{tenantId}/entities/{entityId}/accounts{?pageSize,page}\": {\n"
          + "\"get\": {\n"
          + "\"tags\": [\n"
          + "\"quickbooks-account-controller-v-1\"\n"
          + "],\n"
          + "\"summary\": \"Get all QuickBooks Accounts\",\n"
          + "\"description\": \"Retrieves all Accounts from the QuickBooks API using existing Quickbooks authorization. Quickbooks authorization must be stored for this endpoint to work.\",\n"
          + "\"operationId\": \"getQuickBooksAccountsUsingGET\",\n"
          + "\"consumes\": [\n"
          + "\"application/json\"\n"
          + "],\n"
          + "\"produces\": [\n"
          + "\"application/json\"\n"
          + "],\n"
          + "\"parameters\": [\n"
          + "{\n"
          + "\"name\": \"productId\",\n"
          + "\"in\": \"path\",\n"
          + "\"description\": \"ID of the product for which the request is being made\",\n"
          + "\"required\": false,\n"
          + "\"type\": \"string\"\n"
          + "},\n"
          + "{\n"
          + "\"name\": \"tenantId\",\n"
          + "\"in\": \"path\",\n"
          + "\"description\": \"ID of the tenant for which the request is being made\",\n"
          + "\"required\": false,\n"
          + "\"type\": \"string\"\n"
          + "},\n"
          + "{\n"
          + "\"name\": \"entityId\",\n"
          + "\"in\": \"path\",\n"
          + "\"description\": \"ID of the entity for which the request is being made\",\n"
          + "\"required\": false,\n"
          + "\"type\": \"string\"\n"
          + "},\n"
          + "{\n"
          + "\"name\": \"pageSize\",\n"
          + "\"in\": \"query\",\n"
          + "\"description\": \"The maximum number of items to include in the response\",\n"
          + "\"required\": false,\n"
          + "\"type\": \"integer\",\n"
          + "\"format\": \"int32\"\n"
          + "},\n"
          + "{\n"
          + "\"name\": \"page\",\n"
          + "\"in\": \"query\",\n"
          + "\"description\": \"The page of items to retrieve\",\n"
          + "\"required\": false,\n"
          + "\"type\": \"integer\",\n"
          + "\"format\": \"int32\"\n"
          + "}\n"
          + "],\n"
          + "\"responses\": {\n"
          + "\"200\": {\n"
          + "\"description\": \"OK\",\n"
          + "\"schema\": {\n"
          + "\"$ref\": \"#/definitions/PagedResponseV1«QuickbooksAccountV1,LimitOffsetPagingInfoV1»\"\n"
          + "}\n"
          + "},\n"
          + "\"401\": {\n"
          + "\"description\": \"Unauthorized\"\n"
          + "},\n"
          + "\"403\": {\n"
          + "\"description\": \"Forbidden\"\n"
          + "},\n"
          + "\"404\": {\n"
          + "\"description\": \"Not Found\"\n"
          + "}\n"
          + "}\n"
          + "}\n"
          + "},\n"
          + "\"/quickbooks-integration/v1/{productId}/{tenantId}/entities/{entityId}/authorization\": {\n"
          + "\"put\": {\n"
          + "\"tags\": [\n"
          + "\"authorization-controller-v-1\"\n"
          + "],\n"
          + "\"summary\": \"Store QuickBooks Credentials\",\n"
          + "\"description\": \"Requests and stores credentials from QuickBooks, to be used for service operations.\",\n"
          + "\"operationId\": \"putAuthorizationCredentialUsingPUT\",\n"
          + "\"consumes\": [\n"
          + "\"application/json\"\n"
          + "],\n"
          + "\"produces\": [\n"
          + "\"application/json\"\n"
          + "],\n"
          + "\"parameters\": [\n"
          + "{\n"
          + "\"name\": \"productId\",\n"
          + "\"in\": \"path\",\n"
          + "\"description\": \"ID of the product for which the request is being made\",\n"
          + "\"required\": false,\n"
          + "\"type\": \"string\"\n"
          + "},\n"
          + "{\n"
          + "\"name\": \"tenantId\",\n"
          + "\"in\": \"path\",\n"
          + "\"description\": \"ID of the tenant for which the request is being made\",\n"
          + "\"required\": false,\n"
          + "\"type\": \"string\"\n"
          + "},\n"
          + "{\n"
          + "\"name\": \"entityId\",\n"
          + "\"in\": \"path\",\n"
          + "\"description\": \"ID of the entity for which the request is being made\",\n"
          + "\"required\": false,\n"
          + "\"type\": \"string\"\n"
          + "},\n"
          + "{\n"
          + "\"in\": \"body\",\n"
          + "\"name\": \"intuitInfo\",\n"
          + "\"description\": \"intuitInfo\",\n"
          + "\"required\": true,\n"
          + "\"schema\": {\n"
          + "\"$ref\": \"#/definitions/QuickBooksInfoV1\"\n"
          + "}\n"
          + "}\n"
          + "],\n"
          + "\"responses\": {\n"
          + "\"200\": {\n"
          + "\"description\": \"OK\",\n"
          + "\"schema\": {\n"
          + "\"$ref\": \"#/definitions/EmptyResponseV1\"\n"
          + "}\n"
          + "},\n"
          + "\"201\": {\n"
          + "\"description\": \"Created\"\n"
          + "},\n"
          + "\"401\": {\n"
          + "\"description\": \"Unauthorized\"\n"
          + "},\n"
          + "\"403\": {\n"
          + "\"description\": \"Forbidden\"\n"
          + "},\n"
          + "\"404\": {\n"
          + "\"description\": \"Not Found\"\n"
          + "}\n"
          + "}\n"
          + "},\n"
          + "\"delete\": {\n"
          + "\"tags\": [\n"
          + "\"authorization-controller-v-1\"\n"
          + "],\n"
          + "\"summary\": \"Delete QuickBooks Credentials\",\n"
          + "\"description\": \"Deletes current QuickBooks credentials from the system.\",\n"
          + "\"operationId\": \"deleteAuthorizationCredentialUsingDELETE\",\n"
          + "\"consumes\": [\n"
          + "\"application/json\"\n"
          + "],\n"
          + "\"produces\": [\n"
          + "\"application/json\"\n"
          + "],\n"
          + "\"parameters\": [\n"
          + "{\n"
          + "\"name\": \"productId\",\n"
          + "\"in\": \"path\",\n"
          + "\"description\": \"ID of the product for which the request is being made\",\n"
          + "\"required\": false,\n"
          + "\"type\": \"string\"\n"
          + "},\n"
          + "{\n"
          + "\"name\": \"tenantId\",\n"
          + "\"in\": \"path\",\n"
          + "\"description\": \"ID of the tenant for which the request is being made\",\n"
          + "\"required\": false,\n"
          + "\"type\": \"string\"\n"
          + "},\n"
          + "{\n"
          + "\"name\": \"entityId\",\n"
          + "\"in\": \"path\",\n"
          + "\"description\": \"ID of the entity for which the request is being made\",\n"
          + "\"required\": false,\n"
          + "\"type\": \"string\"\n"
          + "}\n"
          + "],\n"
          + "\"responses\": {\n"
          + "\"200\": {\n"
          + "\"description\": \"OK\",\n"
          + "\"schema\": {\n"
          + "\"$ref\": \"#/definitions/EmptyResponseV1\"\n"
          + "}\n"
          + "},\n"
          + "\"204\": {\n"
          + "\"description\": \"No Content\"\n"
          + "},\n"
          + "\"401\": {\n"
          + "\"description\": \"Unauthorized\"\n"
          + "},\n"
          + "\"403\": {\n"
          + "\"description\": \"Forbidden\"\n"
          + "}\n"
          + "}\n"
          + "}\n"
          + "},\n"
          + "\"/quickbooks-integration/v1/{productId}/{tenantId}/entities/{entityId}/authorization/connect-url\": {\n"
          + "\"get\": {\n"
          + "\"tags\": [\n"
          + "\"authorization-controller-v-1\"\n"
          + "],\n"
          + "\"summary\": \"Get QuickBooks URL\",\n"
          + "\"description\": \"Retrieves and formats the URL for UI to present a login flow.\",\n"
          + "\"operationId\": \"connectToQuickBooksUsingGET\",\n"
          + "\"consumes\": [\n"
          + "\"application/json\"\n"
          + "],\n"
          + "\"produces\": [\n"
          + "\"application/json\"\n"
          + "],\n"
          + "\"parameters\": [\n"
          + "{\n"
          + "\"name\": \"productId\",\n"
          + "\"in\": \"path\",\n"
          + "\"description\": \"ID of the product for which the request is being made\",\n"
          + "\"required\": false,\n"
          + "\"type\": \"string\"\n"
          + "},\n"
          + "{\n"
          + "\"name\": \"tenantId\",\n"
          + "\"in\": \"path\",\n"
          + "\"description\": \"ID of the tenant for which the request is being made\",\n"
          + "\"required\": false,\n"
          + "\"type\": \"string\"\n"
          + "},\n"
          + "{\n"
          + "\"name\": \"entityId\",\n"
          + "\"in\": \"path\",\n"
          + "\"description\": \"ID of the entity for which the request is being made\",\n"
          + "\"required\": false,\n"
          + "\"type\": \"string\"\n"
          + "}\n"
          + "],\n"
          + "\"responses\": {\n"
          + "\"200\": {\n"
          + "\"description\": \"OK\",\n"
          + "\"schema\": {\n"
          + "\"$ref\": \"#/definitions/SingleResponseV1«string»\"\n"
          + "}\n"
          + "},\n"
          + "\"401\": {\n"
          + "\"description\": \"Unauthorized\"\n"
          + "},\n"
          + "\"403\": {\n"
          + "\"description\": \"Forbidden\"\n"
          + "},\n"
          + "\"404\": {\n"
          + "\"description\": \"Not Found\"\n"
          + "}\n"
          + "}\n"
          + "}\n"
          + "},\n"
          + "\"/quickbooks-integration/v1/{productId}/{tenantId}/entities/{entityId}/authorization/status\": {\n"
          + "\"get\": {\n"
          + "\"tags\": [\n"
          + "\"status-controller-v-1\"\n"
          + "],\n"
          + "\"summary\": \"Get QuickBooks Authorization Status.\",\n"
          + "\"description\": \"Retrieves the status of the QuickBooks integration under the given tenant/entity combo.\",\n"
          + "\"operationId\": \"retrieveQuickBooksIntegrationStatusUsingGET\",\n"
          + "\"consumes\": [\n"
          + "\"application/json\"\n"
          + "],\n"
          + "\"produces\": [\n"
          + "\"application/json\"\n"
          + "],\n"
          + "\"parameters\": [\n"
          + "{\n"
          + "\"name\": \"productId\",\n"
          + "\"in\": \"path\",\n"
          + "\"description\": \"ID of the product for which the request is being made\",\n"
          + "\"required\": false,\n"
          + "\"type\": \"string\"\n"
          + "},\n"
          + "{\n"
          + "\"name\": \"tenantId\",\n"
          + "\"in\": \"path\",\n"
          + "\"description\": \"ID of the tenant for which the request is being made\",\n"
          + "\"required\": false,\n"
          + "\"type\": \"string\"\n"
          + "},\n"
          + "{\n"
          + "\"name\": \"entityId\",\n"
          + "\"in\": \"path\",\n"
          + "\"description\": \"ID of the entity for which the request is being made\",\n"
          + "\"required\": false,\n"
          + "\"type\": \"string\"\n"
          + "}\n"
          + "],\n"
          + "\"responses\": {\n"
          + "\"200\": {\n"
          + "\"description\": \"OK\",\n"
          + "\"schema\": {\n"
          + "\"$ref\": \"#/definitions/SingleResponseV1«AuthorizationStatusV1»\"\n"
          + "}\n"
          + "},\n"
          + "\"401\": {\n"
          + "\"description\": \"Unauthorized\"\n"
          + "},\n"
          + "\"403\": {\n"
          + "\"description\": \"Forbidden\"\n"
          + "},\n"
          + "\"404\": {\n"
          + "\"description\": \"Not Found\"\n"
          + "}\n"
          + "}\n"
          + "}\n"
          + "}\n"
          + "},\n"
          + "\"definitions\": {\n"
          + "\"AccountMappingV1\": {\n"
          + "\"type\": \"object\",\n"
          + "\"properties\": {\n"
          + "\"createdOn\": {\n"
          + "\"type\": \"string\",\n"
          + "\"format\": \"date-time\",\n"
          + "\"example\": \"1970-01-01T00:00:00.000Z\",\n"
          + "\"description\": \"When the Account Mapping was created.\",\n"
          + "\"readOnly\": true\n"
          + "},\n"
          + "\"mappedAccountCode\": {\n"
          + "\"type\": \"string\",\n"
          + "\"example\": \"1111222333\",\n"
          + "\"description\": \"Account number for the account in QuickBooks.\"\n"
          + "},\n"
          + "\"mappedAccountLabel\": {\n"
          + "\"type\": \"string\",\n"
          + "\"example\": \"Tax Account\",\n"
          + "\"description\": \"Name for the account in QuickBooks.\"\n"
          + "},\n"
          + "\"quickbooksAccountId\": {\n"
          + "\"type\": \"string\",\n"
          + "\"example\": \"12345\",\n"
          + "\"description\": \"Mapping key.\"\n"
          + "},\n"
          + "\"status\": {\n"
          + "\"type\": \"string\",\n"
          + "\"example\": \"OK\",\n"
          + "\"description\": \"Account Mapping status\",\n"
          + "\"enum\": [\n"
          + "\"OK\",\n"
          + "\"ERROR\"\n"
          + "]\n"
          + "},\n"
          + "\"titanAccountId\": {\n"
          + "\"type\": \"string\",\n"
          + "\"description\": \"The Account ID for the Account Mapping relation.\",\n"
          + "\"readOnly\": true\n"
          + "},\n"
          + "\"updatedOn\": {\n"
          + "\"type\": \"string\",\n"
          + "\"format\": \"date-time\",\n"
          + "\"example\": \"1970-01-01T00:00:00.000Z\",\n"
          + "\"description\": \"When the Account Mapping was last modified.\",\n"
          + "\"readOnly\": true\n"
          + "}\n"
          + "}\n"
          + "},\n"
          + "\"AuthorizationStatusV1\": {\n"
          + "\"type\": \"object\",\n"
          + "\"required\": [\n"
          + "\"message\",\n"
          + "\"status\"\n"
          + "],\n"
          + "\"properties\": {\n"
          + "\"message\": {\n"
          + "\"type\": \"string\",\n"
          + "\"example\": \"This is a message.\",\n"
          + "\"description\": \"The message accompanying the current status. May contain details from errors received from QuickBooks.\"\n"
          + "},\n"
          + "\"status\": {\n"
          + "\"type\": \"string\",\n"
          + "\"example\": \"OK\",\n"
          + "\"description\": \"The code indicating the status of the authorization.\",\n"
          + "\"enum\": [\n"
          + "\"OK\",\n"
          + "\"NONE\",\n"
          + "\"EXPIRED\",\n"
          + "\"INVALID\"\n"
          + "]\n"
          + "}\n"
          + "},\n"
          + "\"description\": \"The body to use when retrieving the status of a QuickBooks authorization.\"\n"
          + "},\n"
          + "\"EmptyResponseV1\": {\n"
          + "\"type\": \"object\",\n"
          + "\"properties\": {\n"
          + "\"error\": {\n"
          + "\"description\": \"This field will contain information about the error if one occurred\",\n"
          + "\"readOnly\": true,\n"
          + "\"$ref\": \"#/definitions/ErrorResponseV1\"\n"
          + "},\n"
          + "\"requestId\": {\n"
          + "\"type\": \"string\",\n"
          + "\"description\": \"The unique ID of this request.\",\n"
          + "\"readOnly\": true\n"
          + "},\n"
          + "\"spanId\": {\n"
          + "\"type\": \"string\",\n"
          + "\"description\": \"The unique ID of the span.\",\n"
          + "\"readOnly\": true\n"
          + "},\n"
          + "\"traceId\": {\n"
          + "\"type\": \"string\",\n"
          + "\"description\": \"The unique ID of the trace.\",\n"
          + "\"readOnly\": true\n"
          + "}\n"
          + "}\n"
          + "},\n"
          + "\"ErrorResponseV1\": {\n"
          + "\"type\": \"object\",\n"
          + "\"properties\": {\n"
          + "\"message\": {\n"
          + "\"type\": \"string\"\n"
          + "},\n"
          + "\"status\": {\n"
          + "\"type\": \"integer\",\n"
          + "\"format\": \"int32\"\n"
          + "}\n"
          + "}\n"
          + "},\n"
          + "\"JsonPatch«AccountMappingV1»\": {\n"
          + "\"type\": \"object\",\n"
          + "\"description\": \"A JSON Patch (see http://jsonpatch.com/)\"\n"
          + "},\n"
          + "\"LimitOffsetPagingInfoV1\": {\n"
          + "\"type\": \"object\",\n"
          + "\"properties\": {\n"
          + "\"method\": {\n"
          + "\"type\": \"string\",\n"
          + "\"description\": \"The method of paging that this pagingInfo contains\",\n"
          + "\"readOnly\": true\n"
          + "},\n"
          + "\"numberOfElements\": {\n"
          + "\"type\": \"integer\",\n"
          + "\"format\": \"int32\",\n"
          + "\"description\": \"The number of data elements in this response.\",\n"
          + "\"readOnly\": true\n"
          + "},\n"
          + "\"page\": {\n"
          + "\"type\": \"integer\",\n"
          + "\"format\": \"int32\",\n"
          + "\"description\": \"The page number of the response.\",\n"
          + "\"readOnly\": true\n"
          + "},\n"
          + "\"pageSize\": {\n"
          + "\"type\": \"integer\",\n"
          + "\"format\": \"int32\",\n"
          + "\"description\": \"The page size of this response.\",\n"
          + "\"readOnly\": true\n"
          + "},\n"
          + "\"totalElements\": {\n"
          + "\"type\": \"integer\",\n"
          + "\"format\": \"int32\",\n"
          + "\"description\": \"The total number of elements\",\n"
          + "\"readOnly\": true\n"
          + "},\n"
          + "\"totalPages\": {\n"
          + "\"type\": \"integer\",\n"
          + "\"format\": \"int32\",\n"
          + "\"description\": \"The total number of pages, based on the pageSize and totalElements\",\n"
          + "\"readOnly\": true\n"
          + "}\n"
          + "}\n"
          + "},\n"
          + "\"PagedResponseV1«QuickbooksAccountV1,LimitOffsetPagingInfoV1»\": {\n"
          + "\"type\": \"object\",\n"
          + "\"properties\": {\n"
          + "\"content\": {\n"
          + "\"type\": \"array\",\n"
          + "\"description\": \"The data elements requested\",\n"
          + "\"readOnly\": true,\n"
          + "\"items\": {\n"
          + "\"$ref\": \"#/definitions/QuickbooksAccountV1\"\n"
          + "}\n"
          + "},\n"
          + "\"error\": {\n"
          + "\"description\": \"This field will contain information about the error if one occurred\",\n"
          + "\"readOnly\": true,\n"
          + "\"$ref\": \"#/definitions/ErrorResponseV1\"\n"
          + "},\n"
          + "\"paging\": {\n"
          + "\"description\": \"The paging method used\",\n"
          + "\"readOnly\": true,\n"
          + "\"$ref\": \"#/definitions/LimitOffsetPagingInfoV1\"\n"
          + "},\n"
          + "\"requestId\": {\n"
          + "\"type\": \"string\",\n"
          + "\"description\": \"The unique ID of this request.\",\n"
          + "\"readOnly\": true\n"
          + "},\n"
          + "\"spanId\": {\n"
          + "\"type\": \"string\",\n"
          + "\"description\": \"The unique ID of the span.\",\n"
          + "\"readOnly\": true\n"
          + "},\n"
          + "\"traceId\": {\n"
          + "\"type\": \"string\",\n"
          + "\"description\": \"The unique ID of the trace.\",\n"
          + "\"readOnly\": true\n"
          + "}\n"
          + "}\n"
          + "},\n"
          + "\"QuickBooksInfoV1\": {\n"
          + "\"type\": \"object\",\n"
          + "\"required\": [\n"
          + "\"authorizationCode\",\n"
          + "\"realmId\"\n"
          + "],\n"
          + "\"properties\": {\n"
          + "\"authorizationCode\": {\n"
          + "\"type\": \"string\",\n"
          + "\"example\": \"authorization_code\",\n"
          + "\"description\": \"The authorization code Intuit has granted from the user successfully logging in. Can be exchanged for an auth token and refresh token\"\n"
          + "},\n"
          + "\"realmId\": {\n"
          + "\"type\": \"string\",\n"
          + "\"example\": \"123456748912\",\n"
          + "\"description\": \"The Realm Id representing the company in QuickBooks.\"\n"
          + "}\n"
          + "},\n"
          + "\"description\": \"The body to use when sending in the auth code for QuickBooks integration.\"\n"
          + "},\n"
          + "\"QuickbooksAccountV1\": {\n"
          + "\"type\": \"object\",\n"
          + "\"properties\": {\n"
          + "\"key\": {\n"
          + "\"type\": \"string\",\n"
          + "\"example\": \"{ id: 45fd5f31-98e1-4315-8475-eff92631d3df }\",\n"
          + "\"description\": \"Unique key of the Account.\",\n"
          + "\"readOnly\": true\n"
          + "},\n"
          + "\"name\": {\n"
          + "\"type\": \"string\",\n"
          + "\"example\": \"Accounts Payable (A/P)\",\n"
          + "\"description\": \"Name of the Account\",\n"
          + "\"readOnly\": true\n"
          + "},\n"
          + "\"number\": {\n"
          + "\"type\": \"string\",\n"
          + "\"example\": \"1000000\",\n"
          + "\"description\": \"Account Number\",\n"
          + "\"readOnly\": true\n"
          + "}\n"
          + "},\n"
          + "\"description\": \"The account information from Quickbooks\"\n"
          + "},\n"
          + "\"SingleResponseV1«AccountMappingV1»\": {\n"
          + "\"type\": \"object\",\n"
          + "\"properties\": {\n"
          + "\"content\": {\n"
          + "\"description\": \"The response data element\",\n"
          + "\"readOnly\": true,\n"
          + "\"$ref\": \"#/definitions/AccountMappingV1\"\n"
          + "},\n"
          + "\"error\": {\n"
          + "\"description\": \"This field will contain information about the error if one occurred\",\n"
          + "\"readOnly\": true,\n"
          + "\"$ref\": \"#/definitions/ErrorResponseV1\"\n"
          + "},\n"
          + "\"requestId\": {\n"
          + "\"type\": \"string\",\n"
          + "\"description\": \"The unique ID of this request.\",\n"
          + "\"readOnly\": true\n"
          + "},\n"
          + "\"spanId\": {\n"
          + "\"type\": \"string\",\n"
          + "\"description\": \"The unique ID of the span.\",\n"
          + "\"readOnly\": true\n"
          + "},\n"
          + "\"traceId\": {\n"
          + "\"type\": \"string\",\n"
          + "\"description\": \"The unique ID of the trace.\",\n"
          + "\"readOnly\": true\n"
          + "}\n"
          + "}\n"
          + "},\n"
          + "\"SingleResponseV1«AuthorizationStatusV1»\": {\n"
          + "\"type\": \"object\",\n"
          + "\"properties\": {\n"
          + "\"content\": {\n"
          + "\"description\": \"The response data element\",\n"
          + "\"readOnly\": true,\n"
          + "\"$ref\": \"#/definitions/AuthorizationStatusV1\"\n"
          + "},\n"
          + "\"error\": {\n"
          + "\"description\": \"This field will contain information about the error if one occurred\",\n"
          + "\"readOnly\": true,\n"
          + "\"$ref\": \"#/definitions/ErrorResponseV1\"\n"
          + "},\n"
          + "\"requestId\": {\n"
          + "\"type\": \"string\",\n"
          + "\"description\": \"The unique ID of this request.\",\n"
          + "\"readOnly\": true\n"
          + "},\n"
          + "\"spanId\": {\n"
          + "\"type\": \"string\",\n"
          + "\"description\": \"The unique ID of the span.\",\n"
          + "\"readOnly\": true\n"
          + "},\n"
          + "\"traceId\": {\n"
          + "\"type\": \"string\",\n"
          + "\"description\": \"The unique ID of the trace.\",\n"
          + "\"readOnly\": true\n"
          + "}\n"
          + "}\n"
          + "},\n"
          + "\"SingleResponseV1«string»\": {\n"
          + "\"type\": \"object\",\n"
          + "\"properties\": {\n"
          + "\"content\": {\n"
          + "\"type\": \"string\",\n"
          + "\"description\": \"The response data element\",\n"
          + "\"readOnly\": true\n"
          + "},\n"
          + "\"error\": {\n"
          + "\"description\": \"This field will contain information about the error if one occurred\",\n"
          + "\"readOnly\": true,\n"
          + "\"$ref\": \"#/definitions/ErrorResponseV1\"\n"
          + "},\n"
          + "\"requestId\": {\n"
          + "\"type\": \"string\",\n"
          + "\"description\": \"The unique ID of this request.\",\n"
          + "\"readOnly\": true\n"
          + "},\n"
          + "\"spanId\": {\n"
          + "\"type\": \"string\",\n"
          + "\"description\": \"The unique ID of the span.\",\n"
          + "\"readOnly\": true\n"
          + "},\n"
          + "\"traceId\": {\n"
          + "\"type\": \"string\",\n"
          + "\"description\": \"The unique ID of the trace.\",\n"
          + "\"readOnly\": true\n"
          + "}\n"
          + "}\n"
          + "}\n"
          + "}\n"
          + "}";

  static final String SERVICE_WRAPPER_PACKAGE_AND_IMPORT_TEMPLATE =
      "package com.vertafore.test.tasks.servicewrappers.%s;\n\n"
          + "import static com.vertafore.test.abilities.CallTitanApi.as;\n"
          + "import static net.serenitybdd.rest.SerenityRest.rest;\n"
          + "import net.serenitybdd.screenplay.Performable;\n"
          + "import net.serenitybdd.screenplay.Task;\n\n";

  static final String SERVICE_WRAPPER_CLASS_TEMPLATE = "public class Use%sServiceTo {\n\n%s}";
  static final String CONSTANT_TEMPLATE = "\tprivate static final String %s = \"%s\";\n";
  static final String SERVICE_WRAPPER_METHOD_TEMPLATE =
      "\tpublic static Performable %s(%s){\n"
          + "\t\treturn Task.where(\n\t\t\"{0} %s\", \n\t\t\tactor -> {\n\t\t\t\t%s\n\t\t\t}\n\t\t)"
          + ";\n\t}\n\n";

  static final String REST_CALL_METHOD_CHAIN_TEMPLATE =
      "rest().with().%s%s(as(actor).toEndpoint(%s));";
  static final String PATH_PARAM_TEMPLATE = "pathParam(\"%s\", %s).";
  static final String QUERY_PARAM_TEMPLATE = "queryParam(\"%s\", %s).";
  static final String BODY_TEMPLATE = "body(body).";
  static final String SERVICE_WRAPPER_DEFAULT_PATH =
      "./src/main/java/com/vertafore/test/tasks/servicewrappers/%s/";
  static final String SERVICE_WRAPPER_FILE_NAME_TEMPLATE = "Use%sServiceTo.java";

  static final String MODEL_CLASS_TEMPLATE =
      "\n@JsonInclude(JsonInclude.Include.NON_NULL)\npublic class %s {\n\n%s}";
  static final String MODEL_SETTER_METHOD_TEMPLATE =
      "\tpublic void set%s(%s %s) {\n\t\tthis.%s = %s;\n\t}\n";
  static final String MODEL_GETTER_METHOD_TEMPLATE =
      "\tpublic %s %s() {\n\t\treturn this.%s;\n\t}\n";
  static final String MODEL_FIELD_TEMPLATE = "\tprivate %s %s;\n";
  static final String MODEL_PACKAGE_TEMPLATE = "package com.vertafore.test.models.%s;\n\n";
  static final String IMPORT_TEMPLATE = "import %s;\n";
  static final String MODEL_DEFAULT_PATH = "./src/main/java/com/vertafore/test/models/%s/";

  public static void generateServiceWrapperClasses() {

    StringBuilder constantsResults = new StringBuilder();
    StringBuilder methodResults = new StringBuilder();

    JsonObject swaggerJsonObject = new Gson().fromJson(json, JsonObject.class);
    JsonObject pathsJsonObject = swaggerJsonObject.get("paths").getAsJsonObject();

    Set<String> paths = pathsJsonObject.keySet();

    for (String path : paths) {

      JsonObject pathJsonObject = pathsJsonObject.getAsJsonObject(path);
      Set<String> pathApiCalls = pathJsonObject.keySet();

      for (String apiCall : pathApiCalls) {

        JsonObject apiCallJsonObject = pathJsonObject.getAsJsonObject(apiCall);

        // variables for readability and reuse
        String summary = apiCallJsonObject.get("summary").getAsString();
        String endpointConstantValue = generateServiceWrapperEndpoint(path);
        String endpointConstantName = generateServiceWrapperConstantName(summary);

        constantsResults.append(
            String.format(CONSTANT_TEMPLATE, endpointConstantName, endpointConstantValue));

        // variables for readability
        String methodName = generateCamalCaseMethodName(summary);
        String methodArguments = generateServiceWrapperMethodParams(apiCallJsonObject);
        String taskDescription = apiCallJsonObject.get("description").getAsString();
        String restCallMethodChain =
            String.format(
                REST_CALL_METHOD_CHAIN_TEMPLATE,
                generateRestParamsMethodChain(apiCallJsonObject),
                apiCall,
                endpointConstantName);

        methodResults.append(
            String.format(
                SERVICE_WRAPPER_METHOD_TEMPLATE,
                methodName,
                methodArguments,
                taskDescription,
                restCallMethodChain));
      }
    }
    String className =
        generateServiceWrapperClassName(swaggerJsonObject.get("basePath").getAsString());
    String packageAndImports =
        String.format(
            SERVICE_WRAPPER_PACKAGE_AND_IMPORT_TEMPLATE,
            generatePackageName(swaggerJsonObject.get("basePath").getAsString()));
    String fileContent =
        packageAndImports
            + String.format(
                SERVICE_WRAPPER_CLASS_TEMPLATE,
                className,
                constantsResults.toString() + "\n" + methodResults.toString());

    String basePath =
        String.format(
            SERVICE_WRAPPER_DEFAULT_PATH,
            generatePackageName(swaggerJsonObject.get("basePath").getAsString()));

    generateBaseDirectory(basePath);

    basePath += String.format(SERVICE_WRAPPER_FILE_NAME_TEMPLATE, className);

    generateBaseFile(basePath);
    writeUsingBufferedWriter(basePath, fileContent);
  }

  public static void generateModelClasses() {

    JsonObject swaggerJsonObject = new Gson().fromJson(json, JsonObject.class);
    JsonObject definitionsJsonObject = swaggerJsonObject.get("definitions").getAsJsonObject();

    String basePath =
        String.format(
            MODEL_DEFAULT_PATH,
            generatePackageName(swaggerJsonObject.get("basePath").getAsString()));

    generateBaseDirectory(basePath);
    Set<String> models = definitionsJsonObject.keySet();

    for (String model : models) {
      if (model.matches(
          "JsonPatch.*|LimitOffsetPagingInfoV1.*|PagedResponseV1.*|SingleResponseV1.*|EmptyResponseV1.*|ErrorResponseV1.*")) {
        continue;
      }
      basePath =
          String.format(
              MODEL_DEFAULT_PATH,
              generatePackageName(swaggerJsonObject.get("basePath").getAsString()));

      StringBuilder fieldResults = new StringBuilder();
      StringBuilder setterGetterResults = new StringBuilder();
      JsonObject modelPropertiesJsonObject =
          definitionsJsonObject.getAsJsonObject(model).getAsJsonObject("properties");
      Set<String> fields = modelPropertiesJsonObject.keySet();

      for (String field : fields) {

        String dataType =
            generateModelFieldType(modelPropertiesJsonObject.get(field).getAsJsonObject());
        fieldResults.append(String.format(MODEL_FIELD_TEMPLATE, dataType, field));

        setterGetterResults
            .append(generateModelGetterMethod(dataType, field))
            .append("\n")
            .append(generateModelSetterMethod(dataType, field));
      }
      String packageName = generatePackageName(swaggerJsonObject.get("basePath").getAsString());
      String fileContent = generateModelPackageAndImports(packageName, fieldResults.toString());
      fileContent +=
          String.format(
              MODEL_CLASS_TEMPLATE,
              model,
              fieldResults.toString() + "\n" + setterGetterResults.toString());

      basePath += model + ".java";
      generateBaseFile(basePath);
      writeUsingBufferedWriter(basePath, fileContent);

      System.out.println(fileContent);
    }
  }

  private static String generateModelPackageAndImports(String packageName, String fieldResults) {
    StringBuilder result = new StringBuilder(String.format(MODEL_PACKAGE_TEMPLATE, packageName));

    result.append(String.format(IMPORT_TEMPLATE, "com.fasterxml.jackson.annotation.JsonInclude"));

    if (fieldResults.contains("List<")) {
      result.append(String.format(IMPORT_TEMPLATE, "java.util.List"));
    }
    if (fieldResults.contains(" Instant ")) {
      result.append(String.format(IMPORT_TEMPLATE, "java.time.Instant"));
    }
    return result.toString();
  }

  private static String generateModelGetterMethod(String dataType, String field) {
    String methodName =
        dataType.equalsIgnoreCase("boolean") ? field : "get" + capitalizeString(field);
    return String.format(MODEL_GETTER_METHOD_TEMPLATE, dataType, methodName, field);
  }

  private static String generateModelSetterMethod(String dataType, String field) {
    return String.format(
        MODEL_SETTER_METHOD_TEMPLATE,
        capitalizeString(field.replaceAll("is", "")),
        dataType,
        field,
        field,
        field);
  }

  private static String capitalizeString(String str) {
    if (str.length() >= 2) {
      return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
    throw new IllegalArgumentException("could not capitalize string " + str);
  }

  private static String generateModelFieldType(JsonObject fieldJsonObject) {
    String result = null;
    String type =
        fieldJsonObject.get("type") == null ? null : fieldJsonObject.get("type").getAsString();
    String format =
        fieldJsonObject.get("format") == null ? null : fieldJsonObject.get("format").getAsString();
    boolean formatPresent = format != null && !format.isEmpty();
    boolean typePresent = type != null && !type.isEmpty();

    if (!typePresent) {
      if (fieldJsonObject.get("$ref") != null) {
        result = fieldJsonObject.get("$ref").getAsString().replace("#/definitions/", "");
      }
    } else if (type.equalsIgnoreCase("string")) {
      if (formatPresent && format.equalsIgnoreCase("date-time")) {
        result = "Instant";
      } else {
        result = "String";
      }
    } else if (type.equalsIgnoreCase("boolean")) {
      result = "Boolean";
    } else if (type.equalsIgnoreCase("integer")) {
      result = "Integer";
    } else if (type.equalsIgnoreCase("number")) {
      result = "Double";
    } else if (type.equalsIgnoreCase("array")) {
      result =
          "List<"
              + fieldJsonObject
                  .get("items")
                  .getAsJsonObject()
                  .get("$ref")
                  .getAsString()
                  .replaceAll("#/definitions/", "")
              + ">";
    } else {
      throw new IllegalArgumentException(
          "could not determine or have not set up logic to handle type: "
              + type
              + " or format: "
              + format);
    }
    return result;
  }

  private static String generateServiceWrapperEndpoint(String path) {
    return path.replaceAll("^.*\\{entityId}", "");
  }

  private static String generateServiceWrapperConstantName(String summary) {
    return summary
        .toUpperCase()
        .trim()
        .replaceAll("[^A-z_ ]+", "")
        .replaceAll("\\s", "_")
        .replaceAll("UPDATEPATCH", "PATCH");
  }

  private static String generateCamalCaseMethodName(String summary) {
    StringBuilder result = new StringBuilder();
    String[] arr =
        summary
            .toLowerCase()
            .trim()
            .replaceAll("Update/Patch", "patch")
            .replaceAll("[^A-z]", " ")
            .split("\\s");

    Arrays.stream(arr)
        .forEach(
            word -> {
              char firstChar = Character.toUpperCase(word.charAt(0));
              String restOfWord = word.length() > 1 ? word.substring(1) : "";
              result.append(firstChar).append(restOfWord);
            });

    result.setCharAt(0, Character.toLowerCase(result.charAt(0)));
    return result.toString().replaceAll("update/patch", "patch").replaceAll("\\.", "");
  }

  private static String generateServiceWrapperMethodParams(JsonObject apiCallJsonObject) {
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
                String paramName = param.get("name").getAsString();
                boolean paramIsADefault =
                    paramName.equalsIgnoreCase("productId")
                        || paramName.equalsIgnoreCase("tenantId")
                        || paramName.equalsIgnoreCase("entityId");

                if (!paramIsADefault) {
                  result.append("String ").append(paramName).append(", ");
                }
              }
            });
    return result.toString().replaceAll(", $", "").replaceAll("\"", "");
  }

  private static String generateServiceWrapperClassName(String basePath) {
    StringBuilder result = new StringBuilder();

    String[] arr = basePath.toLowerCase().replaceAll("/", "").trim().split("[^A-z]+");
    Arrays.stream(arr)
        .forEach(
            word -> {
              char firstChar = Character.toUpperCase(word.charAt(0));
              String restOfWord = word.length() > 1 ? word.substring(1) : "";
              result.append(firstChar).append(restOfWord);
            });

    return result.toString();
  }

  private static String generateRestParamsMethodChain(JsonObject apiCallJsonObject) {
    StringBuilder result = new StringBuilder();
    apiCallJsonObject
        .get("parameters")
        .getAsJsonArray()
        .forEach(
            p -> {
              String paramName = p.getAsJsonObject().get("name").getAsString();

              // for every param that isnt product, entity or tenant id...
              if (paramName.matches("^(?!productId$|entityId$|tenantId$).*")) {

                // if body param, add body param method
                if (p.getAsJsonObject().get("in").getAsString().equalsIgnoreCase("body")) {
                  result.append(BODY_TEMPLATE);
                }
                // if query param, add query param method
                else if (p.getAsJsonObject().get("in").getAsString().equalsIgnoreCase("query")) {
                  result.append(String.format(QUERY_PARAM_TEMPLATE, paramName, paramName));
                }
                // else treat param as a path param
                else {
                  result.append(String.format(PATH_PARAM_TEMPLATE, paramName, paramName));
                }
              }
            });

    return result.toString();
  }

  private static String generatePackageName(String basePath) {
    return basePath.replaceAll("[^A-z]+", "").toLowerCase();
  }

  private static void generateBaseDirectory(String path) {
    boolean result;
    String newPath = Paths.get(path).toAbsolutePath().normalize().toString();
    File file = new File(newPath); // initialize File object and passing path as argument
    try {
      result = file.mkdir();
      System.out.println(
          result ? "directory successfully created" : "directory already exists at location");
    } catch (Exception e) {
      e.printStackTrace(); // prints exception if any
    }
  }

  private static void generateBaseFile(String path) {
    boolean result;
    String newPath = Paths.get(path).toAbsolutePath().normalize().toString();
    File file = new File(newPath); // initialize File object and passing path as argument
    try {
      result = file.createNewFile(); // makes a new file
      System.out.println(result ? "file successfully created" : "file already exists at location");
    } catch (Exception e) {
      e.printStackTrace(); // prints exception if any
    }
  }

  private static void writeUsingBufferedWriter(String path, String fileContent) {

    File file = new File(path);
    FileWriter fr = null;
    BufferedWriter br = null;
    String dataWithNewLine = fileContent + System.getProperty("line.separator");
    try {
      fr = new FileWriter(file);
      br = new BufferedWriter(fr);
      br.write(dataWithNewLine);

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        br.close();
        fr.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
