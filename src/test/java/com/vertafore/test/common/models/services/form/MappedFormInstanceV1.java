package com.vertafore.test.common.models.services.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MappedFormInstanceV1 {
  public ArrayList<FormDataV1> mappedFormData;
  public ArrayList<FormDataV1> unmappedFormData;
  public Boolean hasError;

  public MappedFormInstanceV1() {}
}
