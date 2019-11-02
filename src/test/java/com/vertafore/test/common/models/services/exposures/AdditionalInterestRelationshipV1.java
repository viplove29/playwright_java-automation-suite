package com.vertafore.test.common.models.services.exposures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdditionalInterestRelationshipV1 {

  public String itemNumber;
  public String additionalInterestNumber;

  public AdditionalInterestRelationshipV1() {}
}
