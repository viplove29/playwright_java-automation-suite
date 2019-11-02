package com.vertafore.test.common.models.services.customer.name;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class NameV1 {

  public String formattedName;
}
