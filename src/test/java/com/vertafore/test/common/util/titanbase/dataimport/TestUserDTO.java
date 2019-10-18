package com.vertafore.test.common.util.titanbase.dataimport;

import java.util.List;

// * This DTO is used when deserializing the User information in the
// * testData.yaml file.

public class TestUserDTO {

  private String username;
  private String password;
  private String displayName;
  private String userId;
  private List<UserContextDTO> context;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public List<UserContextDTO> getContext() {
    return context;
  }

  public void setContext(List<UserContextDTO> context) {
    this.context = context;
  }
}
