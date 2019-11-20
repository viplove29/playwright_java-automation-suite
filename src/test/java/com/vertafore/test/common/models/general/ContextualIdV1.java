package com.vertafore.test.common.models.general;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContextualIdV1 {

  public String tenantId;
  public String entityId;
  public String id;

  public ContextualIdV1 createDefaultContextualIdV1() {
    ContextualIdV1 contextualId = new ContextualIdV1();
    contextualId.tenantId = "DIA";
    contextualId.entityId = "DIA";
    contextualId.id = "DIA";
    return contextualId;
  }
}
