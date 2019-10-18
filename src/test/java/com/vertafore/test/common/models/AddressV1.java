package com.vertafore.test.common.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.vertafore.models.v1.StateProvinceV1;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressV1 {
  public String formattedAddress;
  public String streetAddress;
  public String streetAddress2;
  public String streetAddress3;
  public String streetAddress4;
  public String locality;
  public StateProvinceV1 region;
  public String subRegion;
  public String postalCode;
  public String country;
  public Boolean preferred;
  public String type;

  public AddressV1() {}
}
