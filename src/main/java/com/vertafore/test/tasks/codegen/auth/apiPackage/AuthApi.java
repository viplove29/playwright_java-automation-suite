package apiPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vertafore.test.abilities.CallTitanApi;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

  public class UseAuthApiTo {
  String BASE_PATH_FULL = "https://api.dev.titan.v4af.com/auth";

public Performable activateUserUsingPOST(String productId,String tenantId,String entityId,String userId) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Activate a User", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("userId", userId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/users/{userId}/activate").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .POST(path);
    });
    }
public Performable addAuthUserUsingPOST(String productId,String tenantId,String entityId,String userId,String groupId) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Assign an AuthUser to an AuthGroup", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("userId", userId);
    uriVariables.put("groupId", groupId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/groups/{groupId}/users/{userId}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .POST(path);
    });
    }
public Performable bulkActivateUserUsingPOST(String productId,String tenantId,String entityId,java.util.List<String> userIds) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
  queryParams.put("userIds",userIds);


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Activate Users in Bulk", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/users/activate/bulk{?userIds}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .POST(path);
    });
    }
public Performable bulkCreateUserUsingPOST(String productId,String tenantId,String entityId,java.util.List<AuthUserV1> toBeCreated) {
java.util.List&lt;AuthUserV1&gt; postBody = toBeCreated ;
  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Create bulk Users", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/users/bulk").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams).body(postBody)
      .formParams(formParams)
    .POST(path);
    });
    }
public Performable bulkDeactivateUserUsingPOST(String productId,String tenantId,String entityId,java.util.List<String> userIds) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
  queryParams.put("userIds",userIds);


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Deactivate a User", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/users/deactivate/bulk{?userIds}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .POST(path);
    });
    }
public Performable bulkDeleteUserUsingDELETE(String productId,String tenantId,String entityId,java.util.List<String> userIds) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
  queryParams.put("userIds",userIds);


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Delete multiple Users", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/users/bulk{?userIds}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .DELETE(path);
    });
    }
public Performable bulkGrantRoleUsingPOST(String productId,String tenantId,String entityId,AuthUserRoleAssignmentV1 authUserRoleAssignmentV1) {
AuthUserRoleAssignmentV1 postBody = authUserRoleAssignmentV1 ;
  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Assign a Role to multiple Users", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/role-assignments/bulk").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams).body(postBody)
      .formParams(formParams)
    .POST(path);
    });
    }
public Performable changePasswordUsingPUT(String productId,String tenantId,String entityId,ChangePasswordRequestV1 changePassword) {
ChangePasswordRequestV1 postBody = changePassword ;
  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Change a User&#39;s Password", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/change-password").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams).body(postBody)
      .formParams(formParams)
    .PUT(path);
    });
    }
public Performable createAuthGroupUsingPOST(String productId,String tenantId,String entityId,AuthGroupV1 toBeCreated) {
AuthGroupV1 postBody = toBeCreated ;
  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Create AuthGroup", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/groups").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams).body(postBody)
      .formParams(formParams)
    .POST(path);
    });
    }
public Performable createEntityConcordanceUsingPOST(String productId,String tenantId,String entityId,EntityConcordanceV1 toBeCreated) {
EntityConcordanceV1 postBody = toBeCreated ;
  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Create EntityConcordance", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/entities/{entityId}/references").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams).body(postBody)
      .formParams(formParams)
    .POST(path);
    });
    }
public Performable createEntityUsingPOST(String productId,String tenantId,String entityId,EntityV1 toBeCreated) {
EntityV1 postBody = toBeCreated ;
  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Create Entity", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/entities").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams).body(postBody)
      .formParams(formParams)
    .POST(path);
    });
    }
public Performable createOrUpdateAuthGroupUsingPUT(String productId,String tenantId,String entityId,String groupId,AuthGroupV1 toBeUpdated) {
AuthGroupV1 postBody = toBeUpdated ;
  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Update or Create AuthGroup", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("groupId", groupId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/groups/{groupId}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams).body(postBody)
      .formParams(formParams)
    .PUT(path);
    });
    }
public Performable createOrUpdateEntityUsingPUT(String productId,String tenantId,String entityId,String entityIdToWrite,EntityV1 toBeUpdated) {
EntityV1 postBody = toBeUpdated ;
  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Create or update Entity", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("entityIdToWrite", entityIdToWrite);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/entities/{entityIdToWrite}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams).body(postBody)
      .formParams(formParams)
    .PUT(path);
    });
    }
public Performable createOrUpdateTenantUsingPUT(String productId,String tenantId,String entityId,String tenantIdToModify,EntityV1 tenantToUpdate) {
EntityV1 postBody = tenantToUpdate ;
  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Create or Update Tenant", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("tenantIdToModify", tenantIdToModify);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/tenants/{tenantIdToModify}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams).body(postBody)
      .formParams(formParams)
    .PUT(path);
    });
    }
public Performable createOrUpdateUsingPUT(String productId,String tenantId,String entityId,AuthenticationSettingsV1 toBeUpdated) {
AuthenticationSettingsV1 postBody = toBeUpdated ;
  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Create or Update Authentication Settings for provided Context", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/authenticationsettings").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams).body(postBody)
      .formParams(formParams)
    .PUT(path);
    });
    }
public Performable createProductUsingPUT(String productId,String tenantId,String entityId,String productIdToCreate,ProductV1 toBeCreated) {
ProductV1 postBody = toBeCreated ;
  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Create Product", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("productIdToCreate", productIdToCreate);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/products/{productIdToCreate}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams).body(postBody)
      .formParams(formParams)
    .PUT(path);
    });
    }
public Performable createRealmUsingPOST(String productId,String tenantId,String entityId,RealmV1 newRealmV1) {
RealmV1 postBody = newRealmV1 ;
  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Create Realm", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/realms").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams).body(postBody)
      .formParams(formParams)
    .POST(path);
    });
    }
public Performable createRoleUsingPOST(String productId,String tenantId,String entityId,RoleV1 newRoleV1) {
RoleV1 postBody = newRoleV1 ;
  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Create Role", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/roles").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams).body(postBody)
      .formParams(formParams)
    .POST(path);
    });
    }
public Performable createUserUsingPOST(String productId,String tenantId,String entityId,AuthUserV1 toBeCreated) {
AuthUserV1 postBody = toBeCreated ;
  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Create a User", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/users").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams).body(postBody)
      .formParams(formParams)
    .POST(path);
    });
    }
public Performable deactivateUserUsingPOST(String productId,String tenantId,String entityId,String userId) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Deactivate Users", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("userId", userId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/users/{userId}/deactivate").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .POST(path);
    });
    }
public Performable deleteAndMigrateEntityUsingDELETE(String productId,String tenantId,String entityId,String entityIdToDelete,String migrateTo) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
  queryParams.put("migrateTo",migrateTo);


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Delete And Migrate Entity", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("entityIdToDelete", entityIdToDelete);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/entities/{entityIdToDelete}{?migrateTo}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .DELETE(path);
    });
    }
public Performable deleteAuthGroupUsingDELETE(String productId,String tenantId,String entityId,String groupId) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Delete AuthGroup", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("groupId", groupId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/groups/{groupId}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .DELETE(path);
    });
    }
public Performable deleteEntityConcordanceUsingDELETE(String productId,String tenantId,String entityId,EntityConcordanceV1 toBeDeleted) {
EntityConcordanceV1 postBody = toBeDeleted ;
  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Delete EntityConcordance", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/entities/{entityId}/references").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams).body(postBody)
      .formParams(formParams)
    .DELETE(path);
    });
    }
public Performable deleteEntityUsingDELETE(String productId,String tenantId,String entityId,String entityIdToDelete,ERRORUNKNOWN requestParams) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
  queryParams.put("requestParams",requestParams);


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Delete Entity", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("entityIdToDelete", entityIdToDelete);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/entities/{entityIdToDelete}{?requestParams}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .DELETE(path);
    });
    }
public Performable deleteRealmUsingDELETE(String productId,String tenantId,String entityId,String name) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Delete a Realm", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("name", name);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/realms/{name}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .DELETE(path);
    });
    }
public Performable deleteRoleUsingDELETE(String productId,String tenantId,String entityId,String id) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Delete a Role", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("id", id);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/roles/{id}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .DELETE(path);
    });
    }
public Performable deleteTenantUsingDELETE(String productId,String tenantId,String entityId,String tenantIdToDelete) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Delete Tenant", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("tenantIdToDelete", tenantIdToDelete);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/tenants/{tenantIdToDelete}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .DELETE(path);
    });
    }
public Performable deleteUserUsingDELETE(String productId,String tenantId,String entityId,String userId,Boolean cascade) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
  queryParams.put("cascade",cascade);


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Delete a User", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("userId", userId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/users/{userId}{?cascade}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .DELETE(path);
    });
    }
public Performable findAllServiceRolesForServiceUsingGET(String productId,String tenantId,String entityId,String serviceName) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Find All By Service Name", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("serviceName", serviceName);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/services/{serviceName}/roles").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .GET(path);
    });
    }
public Performable findAuthUserMembershipsInAuthGroupUsingGET(String productId,String tenantId,String entityId,String groupId,Integer pageSize,Integer page) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
  queryParams.put("pageSize",pageSize);
queryParams.put("page",page);


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  List AuthUser membership for the specified AuthGroup in a Context", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("groupId", groupId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/groups/{groupId}/users{?pageSize,page}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .GET(path);
    });
    }
public Performable findRoleByIdsUsingGET(String productId,String tenantId,String entityId,String filter,java.util.List<String> ids,Integer pageSize,Integer page) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
  queryParams.put("ids",ids);
queryParams.put("pageSize",pageSize);
queryParams.put("page",page);
queryParams.put("filter",filter);


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Retrieve lots of Roles by id", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/roles?filter=byIds{&ids,pageSize,page}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .GET(path);
    });
    }
public Performable findRoleByNameUsingGET(String productId,String tenantId,String entityId,String name,String filter) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
  queryParams.put("name",name);
queryParams.put("filter",filter);


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Get Role by Name", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/roles?filter=byName{&name}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .GET(path);
    });
    }
public Performable findRolesForGroupUsingGET(String productId,String tenantId,String entityId,String groupId,Integer pageSize,Integer page) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
  queryParams.put("pageSize",pageSize);
queryParams.put("page",page);


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  List Role assignments for an AuthGroup in a Context", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("groupId", groupId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/groups/{groupId}/roles{?pageSize,page}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .GET(path);
    });
    }
public Performable findRolesForUserUsingGET(String productId,String tenantId,String entityId,String filter,String userId,Integer pageSize,Integer page) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
  queryParams.put("userId",userId);
queryParams.put("pageSize",pageSize);
queryParams.put("page",page);
queryParams.put("filter",filter);


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  List Role Assignments for a User in a Context", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/role-assignments?filter=byUserId{&userId,pageSize,page}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .GET(path);
    });
    }
public Performable getAccessTokenUsingPOST(String productId,String tenantId,String entityId,IDPUserV1 idpUser) {
IDPUserV1 postBody = idpUser ;
  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Get token", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/token").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams).body(postBody)
      .formParams(formParams)
    .POST(path);
    });
    }
public Performable getAssignedRoleCountUsingGET(String productId,String tenantId,String entityId,String filter,java.util.List<String> roleIds,String activeFilter) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
  queryParams.put("roleIds",roleIds);
queryParams.put("activeFilter",activeFilter);
queryParams.put("filter",filter);


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Get number of users who are assigned any role in the list", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/users/assigned-role-count?filter=byRoleIds{&roleIds,activeFilter}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .GET(path);
    });
    }
public Performable getAuthGroupUsingGET(String productId,String tenantId,String entityId,String groupId) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Get AuthGroup", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("groupId", groupId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/groups/{groupId}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .GET(path);
    });
    }
public Performable getAuthGroupUsingGET1(String productId,String tenantId,String entityId,Integer pageSize,Integer page) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
  queryParams.put("pageSize",pageSize);
queryParams.put("page",page);


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Get AuthGroups", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/groups{?pageSize,page}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .GET(path);
    });
    }
public Performable getContextualAuthPreviewUsingGET(String productId,String tenantId,String entityId,String serviceName) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Get the authorization information for the user for the Tenant. The entitlements returned can be used to enforce security with User Interfaces and APIs, where they need to see the permissions you hold at ALL levels.", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("serviceName", serviceName);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/auth-tree/services/{serviceName}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .GET(path);
    });
    }
public Performable getContextualAuthUsingGET(String productId,String tenantId,String entityId,String serviceName) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Get the authorization information associated with the context of this request.  The entitlements returned can be used to enforce security in other services", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("serviceName", serviceName);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/auth-info/services/{serviceName}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .GET(path);
    });
    }
public Performable getEntityConcordanceByEntityIdsUsingGET(String productId,String tenantId,String entityId,Integer pageSize,Integer page) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
  queryParams.put("pageSize",pageSize);
queryParams.put("page",page);


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Retrieves a list of EntityConcordances by Entity Id", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/entities/{entityId}/references{?pageSize,page}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .GET(path);
    });
    }
public Performable getEntityHierarchyUsingGET(String productId,String tenantId,String entityId,String entityIdToRetrieve,String ancestorLevels,String ancestorProperties,String descendantLevels,String descendantProperties,java.util.List<String> entityType) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
  queryParams.put("ancestor_levels",ancestorLevels);
queryParams.put("ancestor_properties",ancestorProperties);
queryParams.put("descendant_levels",descendantLevels);
queryParams.put("descendant_properties",descendantProperties);
queryParams.put("entity_type",entityType);


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Get Entity Hierarchy", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("entityIdToRetrieve", entityIdToRetrieve);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/entities/{entityIdToRetrieve}{?ancestor_levels,ancestor_properties,descendant_levels,descendant_properties,entity_type}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .GET(path);
    });
    }
public Performable getEntityIdsForEntitlementUsingGET(String productId,String tenantId,String entityId,String entitlement,String userId,String filter,java.util.List<String> entityIds) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
  queryParams.put("entityIds",entityIds);
queryParams.put("entitlement",entitlement);
queryParams.put("userId",userId);
queryParams.put("filter",filter);


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Determine whether user has the entitlement at the given entities.", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/entities?filter=byEntitlement{&entityIds,entitlement,userId}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .GET(path);
    });
    }
public Performable getProductByNameUsingGET(String productId,String tenantId,String entityId,String filter,String name) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
  queryParams.put("name",name);
queryParams.put("filter",filter);


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Get Product", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/products?filter=byName{&name}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .GET(path);
    });
    }
public Performable getProductUsingGET(String productId,String tenantId,String entityId,String productIdToRetrieve) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Get Product", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("productIdToRetrieve", productIdToRetrieve);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/products/{productIdToRetrieve}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .GET(path);
    });
    }
public Performable getProductsByIdsUsingGET(String productId,String tenantId,String entityId,String filter,String internal,java.util.List<String> ids,Integer pageSize,Integer page) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
  queryParams.put("ids",ids);
queryParams.put("pageSize",pageSize);
queryParams.put("page",page);
queryParams.put("filter",filter);
queryParams.put("internal",internal);


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Retrieve Products by Ids", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/products?filter=byIds{&ids,pageSize,page,internal}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .GET(path);
    });
    }
public Performable getRealmUsingGET(String productId,String tenantId,String entityId,String name) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Get Realm", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("name", name);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/realms/{name}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .GET(path);
    });
    }
public Performable getRefreshTokenUsingPOST(String productId,String tenantId,String entityId,String refreshToken) {
String postBody = refreshToken ;
  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Refresh token", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/token/refresh").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams).body(postBody)
      .formParams(formParams)
    .POST(path);
    });
    }
public Performable getRoleByNameUsingGET(String productId,String tenantId,String entityId,String name,String lookupBy) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
  queryParams.put("lookupBy",lookupBy);


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Get Role by Name Lookup By Name", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("name", name);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/roles/{name}?lookupBy=name").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .GET(path);
    });
    }
public Performable getRoleLookupByIdUsingGET(String productId,String tenantId,String entityId,String id,String lookupBy) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
  queryParams.put("lookupBy",lookupBy);


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Get Role By ID Lookup By ID", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("id", id);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/roles/{id}?lookupBy=id").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .GET(path);
    });
    }
public Performable getRoleUsingGET(String productId,String tenantId,String entityId,String id) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Get Role By ID", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("id", id);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/roles/{id}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .GET(path);
    });
    }
public Performable getRoleUsingGET1(String productId,String tenantId,String entityId,String serviceName,String name) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Find ServiceRole", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("serviceName", serviceName);
    uriVariables.put("name", name);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/services/{serviceName}/roles/{name}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .GET(path);
    });
    }
public Performable getTenantUsingGET(String productId,String tenantId,String entityId,String tenantIdToRetrieve) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Get Tenant", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("tenantIdToRetrieve", tenantIdToRetrieve);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/tenants/{tenantIdToRetrieve}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .GET(path);
    });
    }
public Performable getUserByUsernameUsingGET(String productId,String tenantId,String entityId,String withUsername,String inRealm) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
  queryParams.put("withUsername",withUsername);
queryParams.put("inRealm",inRealm);


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Get a User", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/users{?withUsername,inRealm}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .GET(path);
    });
    }
public Performable getUserSecretUsingGET(String productId,String tenantId,String entityId,String userId) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Get a Client Credentials Service User Secret", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("userId", userId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/users/{userId}/secret").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .GET(path);
    });
    }
public Performable getUserUsingGET(String productId,String tenantId,String entityId,String userId) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Get a User", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("userId", userId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/users/{userId}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .GET(path);
    });
    }
public Performable getUsersByAssignedRolesUsingGET(String productId,String tenantId,String entityId,String filter,java.util.List<String> roleIds,String activeFilter,java.util.List<String> entityIds,java.util.List<String> userIds,String displayName,Integer pageSize,Integer page) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
  queryParams.put("roleIds",roleIds);
queryParams.put("activeFilter",activeFilter);
queryParams.put("entityIds",entityIds);
queryParams.put("userIds",userIds);
queryParams.put("displayName",displayName);
queryParams.put("pageSize",pageSize);
queryParams.put("page",page);
queryParams.put("filter",filter);


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Get a list of users who are assigned any role", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/users/assigned-roles?filter=ByAssignedRoles{&roleIds,activeFilter,entityIds,userIds,displayName,pageSize,page}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .GET(path);
    });
    }
public Performable getUsersByContextUsingGET(String productId,String tenantId,String entityId,String filter,String scope,String userOrDisplayNameIncludes,String sortBy,String sortDirection,String activeFilter,Integer pageSize,Integer page) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
  queryParams.put("scope",scope);
queryParams.put("userOrDisplayNameIncludes",userOrDisplayNameIncludes);
queryParams.put("sortBy",sortBy);
queryParams.put("sortDirection",sortDirection);
queryParams.put("activeFilter",activeFilter);
queryParams.put("pageSize",pageSize);
queryParams.put("page",page);
queryParams.put("filter",filter);


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Retrieve Users for a given Context", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/users?filter=byScope{&scope,userOrDisplayNameIncludes,sortBy,sortDirection,activeFilter,pageSize,page}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .GET(path);
    });
    }
public Performable getUsersByIdsUsingGET(String productId,String tenantId,String entityId,String filter,java.util.List<String> ids,Integer pageSize,Integer page) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
  queryParams.put("ids",ids);
queryParams.put("pageSize",pageSize);
queryParams.put("page",page);
queryParams.put("filter",filter);


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Retrieve Users by Ids", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/users?filter=byIds{&ids,pageSize,page}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .GET(path);
    });
    }
public Performable getUsersLastLoginTimesByIdsUsingGET(String productId,String tenantId,String entityId,String filter,java.util.List<String> ids) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
  queryParams.put("ids",ids);
queryParams.put("filter",filter);


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Retrieve Users last login time by Ids", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/users/lastlogintimes?filter=byIds{&ids}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .GET(path);
    });
    }
public Performable getUsingGET(String productId,String tenantId,String entityId) {

  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Get Authentication Settings and/or computed values for provided Context", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/authenticationsettings").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams)
      .formParams(formParams)
    .GET(path);
    });
    }
public Performable grantRoleUsingPOST(String productId,String tenantId,String entityId,String groupId,String roleId,AuthGroupRoleAssignmentV1 authGroupRoleAssignmentV1) {
AuthGroupRoleAssignmentV1 postBody = authGroupRoleAssignmentV1 ;
  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Assign a Role to a Group", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("groupId", groupId);
    uriVariables.put("roleId", roleId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/groups/{groupId}/roles/{roleId}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams).body(postBody)
      .formParams(formParams)
    .POST(path);
    });
    }
public Performable grantRoleUsingPOST1(String productId,String tenantId,String entityId,AuthUserRoleAssignmentV1 authUserRoleAssignmentV1) {
AuthUserRoleAssignmentV1 postBody = authUserRoleAssignmentV1 ;
  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Assign a Role to a User", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/role-assignments").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams).body(postBody)
      .formParams(formParams)
    .POST(path);
    });
    }
public Performable patchUpdateUserUsingPATCH(String productId,String tenantId,String entityId,String userId,JsonPatchPatchableAuthUserV1 jsonPatch) {
JsonPatchPatchableAuthUserV1 postBody = jsonPatch ;
  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json-patch+json"
    };
    return Task.where("{0}  Patch Auth User", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("userId", userId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/users/{userId}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams).body(postBody)
      .formParams(formParams)
    .PATCH(path);
    });
    }
public Performable revokeAuthUserFromUsingDELETE(String productId,String tenantId,String entityId,String groupId,String userId,AuthGroupMembershipV1 authGroupMembershipV1) {
AuthGroupMembershipV1 postBody = authGroupMembershipV1 ;
  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Remove an AuthUser from an AuthGroup", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("groupId", groupId);
    uriVariables.put("userId", userId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/groups/{groupId}/users/{userId}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams).body(postBody)
      .formParams(formParams)
    .DELETE(path);
    });
    }
public Performable revokeRoleFromUsingDELETE(String productId,String tenantId,String entityId,String groupId,String roleId,AuthGroupRoleAssignmentV1 authGroupRoleAssignmentV1) {
AuthGroupRoleAssignmentV1 postBody = authGroupRoleAssignmentV1 ;
  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Revoke a Role from a Group", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("groupId", groupId);
    uriVariables.put("roleId", roleId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/groups/{groupId}/roles/{roleId}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams).body(postBody)
      .formParams(formParams)
    .DELETE(path);
    });
    }
public Performable revokeRoleFromUsingDELETE1(String productId,String tenantId,String entityId,AuthUserRoleAssignmentV1 authUserRoleAssignmentV1) {
AuthUserRoleAssignmentV1 postBody = authUserRoleAssignmentV1 ;
  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Revoke a Role from a User", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/role-assignments").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams).body(postBody)
      .formParams(formParams)
    .DELETE(path);
    });
    }
public Performable saveAllUsingPUT(String productId,String tenantId,String entityId,String serviceName,java.util.List<ServiceRoleV1> serviceRoleV1s) {
java.util.List&lt;ServiceRoleV1&gt; postBody = serviceRoleV1s ;
  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Create or update a List of Roles", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("serviceName", serviceName);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/services/{serviceName}/roles").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams).body(postBody)
      .formParams(formParams)
    .PUT(path);
    });
    }
public Performable updateRealmUsingPUT(String productId,String tenantId,String entityId,String name,RealmV1 newRealmV1) {
RealmV1 postBody = newRealmV1 ;
  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Replace Realm", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("name", name);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/realms/{name}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams).body(postBody)
      .formParams(formParams)
    .PUT(path);
    });
    }
public Performable updateRoleUsingPUT(String productId,String tenantId,String entityId,String id,RoleV1 newRoleV1) {
RoleV1 postBody = newRoleV1 ;
  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Replace Role", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("id", id);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/roles/{id}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams).body(postBody)
      .formParams(formParams)
    .PUT(path);
    });
    }
public Performable updateUserUsingPUT(String productId,String tenantId,String entityId,String userId,AuthUserV1 toBeUpdated) {
AuthUserV1 postBody = toBeUpdated ;
  final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
  final HttpHeaders headerParams = new HttpHeaders();
  final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


final String[] contentTypes = {
    "application/json"
    };
    return Task.where("{0}  Update a User", actor -> {
  // create path and map variables
  final Map<String, Object> uriVariables = new HashMap<String, Object>();
    uriVariables.put("productId", productId);
    uriVariables.put("tenantId", tenantId);
    uriVariables.put("entityId", entityId);
    uriVariables.put("userId", userId);

String path = UriComponentsBuilder.fromPath("/auth/v1/{productId}/{tenantId}/entities/{entityId}/users/{userId}").buildAndExpand(uriVariables).toUriString();

    CallTitanApi
    .asActorUsingService(actor)
      .contentType(contentTypes.toString())
      .queryParams(queryParams).body(postBody)
      .formParams(formParams)
    .PUT(path);
    });
    }
}
