package com.vertafore.test.common.models.services.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TitanAssociatedObjectDefinitionV1 {
  public String associatedObjectDefinitionId;
  public String filter;
  public String notes;
  public TitanObjectType selectableType;

  public TitanAssociatedObjectDefinitionV1() {}
}
