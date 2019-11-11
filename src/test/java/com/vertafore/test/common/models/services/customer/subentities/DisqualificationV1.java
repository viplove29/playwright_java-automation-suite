package com.vertafore.test.common.models.services.customer.subentities;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DisqualificationV1 {

  public String disqualifiedBy;
  public DisqualificationReasonV1 disqualificationReason;
  public String additionalInfo;
  public Instant createdOn;
  public Instant updatedOn;
}
