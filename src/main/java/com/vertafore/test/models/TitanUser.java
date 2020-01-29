package com.vertafore.test.models;

public class TitanUser {
  private String displayName;
  private String entityName;
  private String tenantId;

  public TitanUser(String displayName, String entityName, String tenantId) {
    this.displayName = displayName;
    this.entityName = entityName;
    this.tenantId = tenantId;
  }

  public TitanUser() {}

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getEntityName() {
    return entityName;
  }

  public void setEntityName(String entityName) {
    this.entityName = entityName;
  }

  public String getTenantId() {
    return tenantId;
  }

  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }
}
