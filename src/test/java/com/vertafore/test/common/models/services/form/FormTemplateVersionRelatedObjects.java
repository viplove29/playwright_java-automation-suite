package com.vertafore.test.common.models.services.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FormTemplateVersionRelatedObjects {
  public Map<String, AssociatedObjectDefinitionV1> associatedObjectDefinitions;
  public Map<String, FormFieldV1> formFields;
  public Map<String, FormListV1> formLists;

  public FormTemplateVersionRelatedObjects() {}
}
