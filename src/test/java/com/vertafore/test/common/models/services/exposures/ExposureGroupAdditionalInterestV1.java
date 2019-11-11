package com.vertafore.test.common.models.services.exposures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vertafore.test.common.models.AddressV1;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExposureGroupAdditionalInterestV1 {
  public String additionalInterestNumber;

  public String type;

  public String name;

  public AddressV1 address;

  public String rank;

  public String referenceLoan;

  public Boolean isCertificateOfInsuranceNeeded;

  public Boolean isEvidenceOfPolicyNeeded;

  public Boolean shouldSendBill;

  public ExposureGroupAdditionalInterestV1() {}
}
