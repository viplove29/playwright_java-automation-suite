package com.vertafore.test.common.models.services.exposures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExposureV1 {
  public String type;
  public String lineOfBusinessType;
}
