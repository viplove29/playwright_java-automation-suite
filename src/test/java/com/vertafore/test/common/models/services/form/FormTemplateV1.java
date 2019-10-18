package com.vertafore.test.common.models.services.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vertafore.test.common.models.StateProvinceV1;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FormTemplateV1 {

  public String id;
  public String publisherType; //   ACORD, CARRIER
  public Instant createdOn;
  public Instant updatedOn;
  public ArrayList<String> linesOfBusiness;
  public String name;
  public String publisherSubtype;
  public List<StateProvinceV1> states;
  public String
      type; //  APPLICATION, AUTO_ID, CERTIFICATE, ENDORSEMENT, REINSTATEMENT, CANCELLATION
  public Boolean isOverflowForm;
  public List<String> versionIds;

  public FormTemplateV1() {}
}
