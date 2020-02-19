package com.vertafore.test.models.accounting;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionV1 {

  private Double amount;
  private Integer accountCode;
  private String journalEntryType;
  private String accountName;
  private Integer transactionNumber;
  private String journalId;
  private String memo;
  private Instant updatedOn;
  private String type;
  private String journalEntryDescription;
  private Instant createdOn;
  private String accountId;
  private String journalEntryId;
  private Instant reconciledOn;
  private String createdBy;
  private String postedOn;
  private String modifiedBy;
  private String id;

  public Double getAmount() {
    return this.amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Integer getAccountCode() {
    return this.accountCode;
  }

  public void setAccountCode(Integer accountCode) {
    this.accountCode = accountCode;
  }

  public String getJournalEntryType() {
    return this.journalEntryType;
  }

  public void setJournalEntryType(String journalEntryType) {
    this.journalEntryType = journalEntryType;
  }

  public String getAccountName() {
    return this.accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public Integer getTransactionNumber() {
    return this.transactionNumber;
  }

  public void setTransactionNumber(Integer transactionNumber) {
    this.transactionNumber = transactionNumber;
  }

  public String getJournalId() {
    return this.journalId;
  }

  public void setJournalId(String journalId) {
    this.journalId = journalId;
  }

  public String getMemo() {
    return this.memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public Instant getUpdatedOn() {
    return this.updatedOn;
  }

  public void setUpdatedOn(Instant updatedOn) {
    this.updatedOn = updatedOn;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getJournalEntryDescription() {
    return this.journalEntryDescription;
  }

  public void setJournalEntryDescription(String journalEntryDescription) {
    this.journalEntryDescription = journalEntryDescription;
  }

  public Instant getCreatedOn() {
    return this.createdOn;
  }

  public void setCreatedOn(Instant createdOn) {
    this.createdOn = createdOn;
  }

  public String getAccountId() {
    return this.accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public String getJournalEntryId() {
    return this.journalEntryId;
  }

  public void setJournalEntryId(String journalEntryId) {
    this.journalEntryId = journalEntryId;
  }

  public Instant getReconciledOn() {
    return this.reconciledOn;
  }

  public void setReconciledOn(Instant reconciledOn) {
    this.reconciledOn = reconciledOn;
  }

  public String getCreatedBy() {
    return this.createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public String getPostedOn() {
    return this.postedOn;
  }

  public void setPostedOn(String postedOn) {
    this.postedOn = postedOn;
  }

  public String getModifiedBy() {
    return this.modifiedBy;
  }

  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
