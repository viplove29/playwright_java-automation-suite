package com.vertafore.test.common.models.services.policy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vertafore.test.common.models.general.ContextualIdV1;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PolicyProductLineOfBusinessV1 {
  public ContextualIdV1 productLineOfBusinessId;
  public ContextualIdV1 productLineOfCoverageId;

  public PolicyProductLineOfBusinessV1 createGenericProductLineOfBusiness() {
    PolicyProductLineOfBusinessV1 policyLob = new PolicyProductLineOfBusinessV1();
    policyLob.productLineOfBusinessId = new ContextualIdV1().createDefaultContextualIdV1();
    policyLob.productLineOfCoverageId = new ContextualIdV1().createDefaultContextualIdV1();
    return policyLob;
  }
}
