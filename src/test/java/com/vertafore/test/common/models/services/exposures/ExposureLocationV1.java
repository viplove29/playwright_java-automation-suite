package com.vertafore.test.common.models.services.exposures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vertafore.test.common.models.AddressV1;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExposureLocationV1 {
  public String id;
  public AddressV1 address;
  public List<String> buildingNames = new ArrayList();

  public ExposureLocationV1() {}
}
