package com.vertafore.test.common.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PhoneNumberV1 {

  public String countryCode;
  public String extension;
  public String phoneNumber;
  public Boolean preferred;
  public String type;

  public PhoneNumberV1() {}
}
