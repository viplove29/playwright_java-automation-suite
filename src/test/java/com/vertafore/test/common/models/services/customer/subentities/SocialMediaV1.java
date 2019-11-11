package com.vertafore.test.common.models.services.customer.subentities;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SocialMediaV1 {

  public Boolean preferred;
  public String type;
  public String url;
}
