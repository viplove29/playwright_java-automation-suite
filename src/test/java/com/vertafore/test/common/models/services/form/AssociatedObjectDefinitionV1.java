package com.vertafore.test.common.models.services.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vertafore.test.common.models.canonical.CanonicalObjectType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AssociatedObjectDefinitionV1 {
  public String id;
  public String name;
  public String notes;
  public CanonicalObjectType type;

  public AssociatedObjectDefinitionV1() {}
}
