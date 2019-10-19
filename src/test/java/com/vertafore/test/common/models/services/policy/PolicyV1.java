package com.vertafore.test.common.models.services.policy;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PolicyV1 {

  public String id;
  public String entityId;
  public String policyNumber;
  public String policyStatus;
  public String carrierId;
  public ContextualIdV1 exposureGroupId;
  public ArrayList<PolicyProductLineOfBusinessV1> policyProductLinesOfBusiness;
  public BigDecimal premium;
  public ArrayList<String> namedInsureds;
  public String billingType;
  public BigDecimal agencyCommission;
  public BigDecimal producerCommission;
  public String modifiedBy;
  public Instant versionUpdatedOn;
  public String latestVersionId;
  public String createdBy;
  public Instant createdOn;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-dd-MM")
  public LocalDate effectiveDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-dd-MM")
  public LocalDate expirationDate;

  public PolicyV1() {
    policyProductLinesOfBusiness = new ArrayList<>();
    namedInsureds = new ArrayList<>();
  }
}
