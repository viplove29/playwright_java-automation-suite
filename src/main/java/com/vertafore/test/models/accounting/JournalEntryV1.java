package com.vertafore.test.models.accounting;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JournalEntryV1 {

  private String createdBy;
  private String journalId;
  private String postedOn;
  private String description;
  private String modifiedBy;
  private String id;
  private Instant updatedOn;
  private String type;
  private List<EntryTransactionV1> transactions;
  private Instant createdOn;

  public String getCreatedBy() {
    return this.createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public String getJournalId() {
    return this.journalId;
  }

  public void setJournalId(String journalId) {
    this.journalId = journalId;
  }

  public String getPostedOn() {
    return this.postedOn;
  }

  public void setPostedOn(String postedOn) {
    this.postedOn = postedOn;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
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

  public List<EntryTransactionV1> getTransactions() {
    return this.transactions;
  }

  public void setTransactions(List<EntryTransactionV1> transactions) {
    this.transactions = transactions;
  }

  public Instant getCreatedOn() {
    return this.createdOn;
  }

  public void setCreatedOn(Instant createdOn) {
    this.createdOn = createdOn;
  }
}
