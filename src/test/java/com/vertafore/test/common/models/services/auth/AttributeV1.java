package com.vertafore.test.common.models.services.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AttributeV1 {
  public String key;
  public String value;

  public AttributeV1() {}
}
