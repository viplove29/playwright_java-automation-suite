package com.vertafore.test.common.models.services.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AssociatedObjectV1 {
  public String id;
  public Instant createdOn;
  public Instant updatedOn;
  public String objectId;
  public String secondaryObjectId;
  public String associatedObjectDefinitionId;

  public AssociatedObjectV1() {}
}
