package com.vertafore.test.models.quickbooksintegration;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountMappingV1 {

  private Instant createdOn;
  private String mappedAccountCode;
  private String mappedAccountLabel;
  private String quickbooksAccountId;
  private String status;
  private String titanAccountId;
  private Instant updatedOn;

  public Instant getCreatedOn() {
    return this.createdOn;
  }

  public void setCreatedOn(Instant createdOn) {
    this.createdOn = createdOn;
  }

  public String getMappedAccountCode() {
    return this.mappedAccountCode;
  }

  public void setMappedAccountCode(String mappedAccountCode) {
    this.mappedAccountCode = mappedAccountCode;
  }

  public String getMappedAccountLabel() {
    return this.mappedAccountLabel;
  }

  public void setMappedAccountLabel(String mappedAccountLabel) {
    this.mappedAccountLabel = mappedAccountLabel;
  }

  public String getQuickbooksAccountId() {
    return this.quickbooksAccountId;
  }

  public void setQuickbooksAccountId(String quickbooksAccountId) {
    this.quickbooksAccountId = quickbooksAccountId;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getTitanAccountId() {
    return this.titanAccountId;
  }

  public void setTitanAccountId(String titanAccountId) {
    this.titanAccountId = titanAccountId;
  }

  public Instant getUpdatedOn() {
    return this.updatedOn;
  }

  public void setUpdatedOn(Instant updatedOn) {
    this.updatedOn = updatedOn;
  }
}
