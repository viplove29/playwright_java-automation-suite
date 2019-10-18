package com.vertafore.test.common.models.services.policy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PolicyCarrierV1 {
  private String id;
  private String name;
}
