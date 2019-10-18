package com.vertafore.test.common.util.titanbase.dataimport;

import java.util.List;

// * This DTO is used when deserializing the testData.yaml file provided at
// * test execution, containing a default uri and the users, with context,
// * available for that testing.

public class TestDataDTO {
  private List<TestUserDTO> users;

  public List<TestUserDTO> getUsers() {
    return users;
  }

  public void setUsers(List<TestUserDTO> users) {
    this.users = users;
  }
}
