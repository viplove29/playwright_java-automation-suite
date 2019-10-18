package com.vertafore.test.common.models.services.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FormListV1 {
  public String id;
  public Instant createdOn;
  public Instant updatedOn;
  public String name;
  public String path;
  public String associatedObjectDefinitionId;
  public String overflowFormTemplateVersionId;
  public String parentFormListId;
  public Integer limit;
  public Integer listIndex;
  public String filterOperation; //  EQUALS, UNIQUE
  public String filterPath;
  public String filterReferenceField;
  public String filterValue;
  public Integer overflowPriority;

  public FormListV1() {}
}
