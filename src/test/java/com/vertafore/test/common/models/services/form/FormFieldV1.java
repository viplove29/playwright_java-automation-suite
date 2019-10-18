package com.vertafore.test.common.models.services.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.Instant;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FormFieldV1 {
  public String id;
  public Instant createdOn;
  public Instant updatedOn;
  public String name;
  public String associatedObjectDefinitionId;
  public String formListId;
  public Integer listIndex;
  public String checkComparator;
  public String
      calculationOperation; //  NOW, SUM, GET_GENERATED_ID, GET_GENERATED_IDS_FOR_LIST, REPLACE,
  // LIST_CONTAINS, DIVIDE_BY_1000, MULTIPLY_BY_1000
  public ArrayList<String> calculationParameters;
  public String filterOperation; //  EQUALS, UNIQUE
  public String filterPath;
  public String filterReferenceField;
  public String filterValue;
  public Format format;
  public String type; // TEXT, CHECKBOX
  public String overflowFormTemplateVersionId;
  public Boolean overrideDefault;
  public String path;
  public Integer textLimit;
  public Integer overflowPriority;
  public Boolean startAtOverflowPoint;

  public FormFieldV1() {
    calculationParameters = new ArrayList<>();
  }
}
