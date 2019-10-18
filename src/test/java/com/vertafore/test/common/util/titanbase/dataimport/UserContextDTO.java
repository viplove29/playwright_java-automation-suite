package com.vertafore.test.common.util.titanbase.dataimport;

/**
 * This DTO is used when reading a user's context from the testData.yaml file provided at runtime.
 * It contains a single context for that user and other UserContextDTO objects will contain any
 * other contexts for that user.
 */
public class UserContextDTO {
  private String name;
  private String productId;
  private String tenantId;
  private String entityId;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getTenantId() {
    return tenantId;
  }

  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

  public String getEntityId() {
    return entityId;
  }

  public void setEntityId(String entityId) {
    this.entityId = entityId;
  }
}
