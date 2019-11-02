package com.vertafore.test.common.models.services.customer.subentities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vertafore.test.common.models.AddressV1;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationV1 {

  public String titleOrDescriptor;
  public AddressV1 address;
}
