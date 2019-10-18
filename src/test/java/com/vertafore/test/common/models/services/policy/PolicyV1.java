package com.vertafore.test.common.models.services.policy;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vertafore.models.v1.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PolicyV1 implements com.vertafore.models.v1.PolicyV1 {

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

  public PolicyV1() {}

  @Override
  public PolicyLobV1 getPolicyLob() {
    return null;
  }

  @Override
  public String getPolicyNumber() {
    return null;
  }

  @Override
  public PolicyStatusV1 getPolicyStatus() {
    return null;
  }

  @Override
  public LocalDate getEffectiveDate() {
    return null;
  }

  @Override
  public LocalDate getExpirationDate() {
    return null;
  }

  @Override
  public BigDecimal getPremium() {
    return null;
  }

  @Override
  public List<String> getNamedInsureds() {
    return null;
  }

  @Override
  public CompanyV1 getCompany() {
    return null;
  }

  @Override
  public List<? extends AdditionalInterestV1> getAdditionalInterests() {
    return null;
  }

  @Override
  public LocalDate getCancellationDate() {
    return null;
  }

  @Override
  public LocalDate getReinstatementDate() {
    return null;
  }

  @Override
  public String getReinstatementDescription() {
    return null;
  }

  @Override
  public String getCancellationReason() {
    return null;
  }

  @Override
  public PolicyCancellationTypeV1 getCancellationType() {
    return null;
  }

  @Override
  public BigDecimal getFullTermPremium() {
    return null;
  }

  @Override
  public BigDecimal getUnearnedPremium() {
    return null;
  }

  @Override
  public BigDecimal getEarnedAgencyCommission() {
    return null;
  }

  @Override
  public BigDecimal getUnearnedAgencyCommission() {
    return null;
  }

  @Override
  public String getCancellationDescription() {
    return null;
  }

  @Override
  public String getVoidedReason() {
    return null;
  }
}
