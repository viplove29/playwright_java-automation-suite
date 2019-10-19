package com.vertafore.test.common.models.services.policy;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PolicyVersionV1 {

  public String id;
  public String policyId;
  public String policyNumber;
  public String policyStatus; // [ ACTIVE, CANCELLED, DRAFT, EXPIRED, FUTURE, VOIDED ]
  public String carrierId;
  public BigDecimal agencyCommission;
  public BigDecimal producerCommission;
  public ContextualIdV1 exposureGroupId;
  public BigDecimal premium;
  public ArrayList<String> namedInsureds;
  public String createdBy;
  public String modifiedBy;
  public Instant createdOn;
  public Instant updatedOn;
  public String billingType; // Enum: [ DIRECT, AGENCY ]
  public ArrayList<String> formInstanceIds;
  public String cancellationDescription;
  public String cancellationReason; // Enum: [ NON_PAY, INSURED_REQUEST, OTHER ]
  public String cancellationType; // Enum : [ FLAT, SHORT_RATE, PRO_RATE ]
  public BigDecimal earnedAgencyCommission;
  public BigDecimal fullTermPremium;
  public String reinstatementDescription;
  public BigDecimal unearnedAgencyCommission;
  public BigDecimal unearnedPremium;
  public String voidedReason; // [ ERROR, DUPLICATE, OTHER ]

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-dd-MM")
  public LocalDate effectiveDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-dd-MM")
  public LocalDate expirationDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-dd-MM")
  public LocalDate reinstatementDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-dd-MM")
  public LocalDate cancellationDate;

  public PolicyVersionV1() {
    namedInsureds = new ArrayList<>();
    ;
    formInstanceIds = new ArrayList<>();
    ;
  }
}
