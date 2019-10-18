package com.vertafore.test.common.models.services.exposures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonalAutoDriverPriorCoverageV1 {
  public Integer yearsWithCarrier;
  public String currentCarrier;
  public LocalDate currentPolicyExpirationDate;
  public BigDecimal currentPolicyPremium;
  public BigDecimal currentPolicyLimit;
}
