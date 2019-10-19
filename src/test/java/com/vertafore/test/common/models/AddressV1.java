package com.vertafore.test.common.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressV1 {
  public String formatted;
  public String streetAddress;
  public String streetAddress2;
  public String locality;
  public String region;
  public String country;
  public String postalCode;
  public Boolean preferred;
  public String type;

  public AddressV1() {}
}
