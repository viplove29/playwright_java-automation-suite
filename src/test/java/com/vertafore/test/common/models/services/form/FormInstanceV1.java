package com.vertafore.test.common.models.services.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.Instant;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FormInstanceV1 {
  public String id;
  public Instant createdOn;
  public Instant updatedOn;
  public String formTemplateVersionId;
  public String name;
  public String notes;
  public ArrayList<FormDataV1> unmappedFieldData;

  public FormInstanceV1() {}
}
