package com.vertafore.test.common.models.services.customer;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NameV1 {
  public String formattedName;
}
