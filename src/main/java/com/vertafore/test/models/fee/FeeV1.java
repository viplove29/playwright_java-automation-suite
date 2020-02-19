package com.vertafore.test.models.fee;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeeV1 {

  private String calculationType;
  private Double amount;
  private String name;
  private String id;
  private Instant updatedOn;
  private Instant createdOn;

  public String getCalculationType() {
    return this.calculationType;
  }

  public void setCalculationType(String calculationType) {
    this.calculationType = calculationType;
  }

  public Double getAmount() {
    return this.amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
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

  public Instant getCreatedOn() {
    return this.createdOn;
  }

  public void setCreatedOn(Instant createdOn) {
    this.createdOn = createdOn;
  }
}
