package com.vertafore.test.common.servicewrappers.policyOrchestration;

import com.vertafore.test.common.models.services.policy.PolicyV1;
import com.vertafore.test.common.util.ServiceUtils;
import io.restassured.response.Response;
import java.io.IOException;

public class PolicyOrchestrationService {

  // policy orchestration controller constants
  public final String POST_POLICY_WITH_POSSIBLE_INVOICE = "/policies{?invoice}";

  private ServiceUtils serviceUtils;

  public PolicyOrchestrationService() throws IOException {
    serviceUtils = new ServiceUtils();
  }

  // policy orchestration API calls
  public PolicyV1 postPolicyWithPossibleInvoice(PolicyV1 requestBody, Boolean withInvoice) {
    String hydratedURL =
        hydrateURL(
            POST_POLICY_WITH_POSSIBLE_INVOICE, "{?invoice}", "?invoice=" + withInvoice.toString());
    Response response = serviceUtils.sendPostRequest(hydratedURL, requestBody);
    return response.getBody().jsonPath().getObject("content", PolicyV1.class);
  }

  // helper methods
  private String hydrateURL(String url, String target, String value) {
    return url.replace(target, value);
  }
}
