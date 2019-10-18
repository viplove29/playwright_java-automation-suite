package com.vertafore.test.common.models.services.auth;

import com.fasterxml.jackson.annotation.JsonInclude;

// * This DTO is used in a request to get a new Auth token.

// should probably just be the IDPUserV1 that lives in auth..
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IDPUserV1 {
  private String username;
  private String password;

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}
