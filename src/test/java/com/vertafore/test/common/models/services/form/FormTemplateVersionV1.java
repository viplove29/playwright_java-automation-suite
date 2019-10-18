package com.vertafore.test.common.models.services.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.File;
import java.time.Instant;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FormTemplateVersionV1 {

  public String id;
  public Instant createdOn;
  public Instant updatedOn;
  public List<String> associatedObjectDefinitionIds;
  public List<String> formFieldIds;
  public List<String> formListIds;
  public String name;
  public String status; //  DRAFT, ACTIVE, INACTIVE
  // params
  public File file;
  public String version;

  public FormTemplateVersionV1() {}
}
