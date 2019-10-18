package com.vertafore.test.common.servicewrappers.policy;

import com.vertafore.test.common.models.general.PatchBody;
import com.vertafore.test.common.models.services.policy.PolicyV1;
import com.vertafore.test.common.models.services.policy.PolicyVersionV1;
import com.vertafore.test.common.util.ServiceUtils;
import io.restassured.response.Response;
import java.io.IOException;
import java.util.ArrayList;

public class PolicyService {

  private ServiceUtils serviceUtils;

  public PolicyService() throws IOException {
    serviceUtils = new ServiceUtils();
  }

  // Endpoints
  public static final String BASE_PATH = "/policy/v1/{productId}/{tenantId}/entities/{entityId}";

  // Creates a policy, and creates a policy versionId under the hood.
  public static final String POST_POLICY = "/policies";

  // Updates a non-draft policy and rolls out a new version each time
  public static final String POST_VERSION = "/policies/{id}/versions";

  // Get a policy by id
  public static final String GET_POLICY = "/policies/{id}";

  // Get policy version by id
  public static final String GET_POLICY_VERSION = "/policies/{id}/versions/{versionId}";

  // Updates a policy version, only way to change from 'DRAFT' -> 'ACTIVE' which does not roll out a
  // new version
  // If the update is doing anything other than moving from 'DRAFT' -> 'ACTIVE' then a new version
  // is rolled out
  // with each request
  public static final String PATCH_VERSION = "/policies/{id}/versions/{versionId}";

  // Deletes a policy with status of draft only.
  public static final String DELETE_DRAFT = "/policies/{id}";

  public PolicyV1 postPolicy(PolicyV1 policyRequestBody) {
    Response response = serviceUtils.sendPostRequest(POST_POLICY, policyRequestBody);
    return response.getBody().jsonPath().getObject("content", PolicyV1.class);
  }

  public PolicyVersionV1 postPolicyVersion(
      PolicyVersionV1 policyVersionRequestBody, String policyId) throws IOException {
    Response response =
        serviceUtils.sendPostRequest(
            POST_VERSION.replace("{id}", policyId), policyVersionRequestBody);
    return response.getBody().jsonPath().getObject("content", PolicyVersionV1.class);
  }

  public PolicyVersionV1 patchPolicyVersion(PolicyVersionV1 policyVersion, String policyId)
      throws NoSuchFieldException, IllegalAccessException, IOException {
    ArrayList<PatchBody> requestBody = serviceUtils.buildPatchRequestFromModel(policyVersion);
    Response response =
        serviceUtils.sendPatchRequest(PATCH_VERSION.replace("{id}", policyId), policyVersion);
    return response.getBody().jsonPath().getObject("content", PolicyVersionV1.class);
  }

  public PolicyVersionV1 getPolicyVersion(String policyId, String policyVersionId) {
    String hydratedUrl =
        applyPolicyAndVersionIdToUrl(GET_POLICY_VERSION, policyId, policyVersionId);
    Response response = serviceUtils.sendGetRequest(hydratedUrl);
    // return PolicyVersionV1 object back
    return response.getBody().jsonPath().getObject("content", PolicyVersionV1.class);
  }

  // Helpers
  // this applies the policyId and policyVersionId to our policy url
  private String applyPolicyAndVersionIdToUrl(
      String policyUrl, String policyId, String policyVersionId) {
    return policyUrl.replace("{id}", policyId).replace("{versionId}", policyVersionId);
  }
}
