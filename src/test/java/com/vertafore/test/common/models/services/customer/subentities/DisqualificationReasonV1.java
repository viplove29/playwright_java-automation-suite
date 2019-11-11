package com.vertafore.test.common.models.services.customer.subentities;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DisqualificationReasonV1 {

  public String id;
  public String title;
  public String description;
  public Instant createdOn;
  public Instant updatedOn;
}
