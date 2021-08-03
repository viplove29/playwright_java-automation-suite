package com.vertafore.test.services.lines_of_business;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LOB {
  @JsonProperty("description")
  private String description;

  @JsonProperty("lineofBusinessCode")
  private String lineofBusinessCode;

  @JsonProperty("sdeCode")
  private String sdeCode;

  @JsonProperty("sdeDescription")
  private String sdeDescription;

  public LOB() {}
}
