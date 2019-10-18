package com.vertafore.test.common.models.services.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FormDataV1 {

  public HashMap<String, String> fillData;
  public String formTemplateVersionId;

  public FormDataV1() {}
}
