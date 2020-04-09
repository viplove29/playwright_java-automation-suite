package modelPackage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import modelPackage.ProductContextualIdV1;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AuthUserRoleAssignmentV1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class AuthUserRoleAssignmentV1   {
  @JsonProperty("entityId")
  private String entityId = null;

  @JsonProperty("groupId")
  private String groupId = null;

  @JsonProperty("productId")
  private String productId = null;

  /**
   * Source of the role's assignment to the user
   */
  public enum RoleAssignmentSourceEnum {
    FEDERATED("federated"),
    
    GROUP("group"),
    
    UNFEDERATED("unfederated");

    private String value;

    RoleAssignmentSourceEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static RoleAssignmentSourceEnum fromValue(String text) {
      for (RoleAssignmentSourceEnum b : RoleAssignmentSourceEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("roleAssignmentSource")
  private RoleAssignmentSourceEnum roleAssignmentSource = null;

  @JsonProperty("roleContextualId")
  private ProductContextualIdV1 roleContextualId = null;

  @JsonProperty("tenantId")
  private String tenantId = null;

  @JsonProperty("userId")
  private String userId = null;

  @JsonProperty("userIds")
  @Valid
  private java.util.List<String> userIds = null;

  public AuthUserRoleAssignmentV1 entityId(String entityId) {
    this.entityId = entityId;
    return this;
  }

  /**
   * Id of entity context for role assignment
   * @return entityId
  **/
  @ApiModelProperty(readOnly = true, value = "Id of entity context for role assignment")


  public String getEntityId() {
    return entityId;
  }

  public void setEntityId(String entityId) {
    this.entityId = entityId;
  }

  public AuthUserRoleAssignmentV1 groupId(String groupId) {
    this.groupId = groupId;
    return this;
  }

  /**
   * Id of the group associated with the role assigned
   * @return groupId
  **/
  @ApiModelProperty(readOnly = true, value = "Id of the group associated with the role assigned")


  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  public AuthUserRoleAssignmentV1 productId(String productId) {
    this.productId = productId;
    return this;
  }

  /**
   * Id of product context for role assignment
   * @return productId
  **/
  @ApiModelProperty(readOnly = true, value = "Id of product context for role assignment")


  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public AuthUserRoleAssignmentV1 roleAssignmentSource(RoleAssignmentSourceEnum roleAssignmentSource) {
    this.roleAssignmentSource = roleAssignmentSource;
    return this;
  }

  /**
   * Source of the role's assignment to the user
   * @return roleAssignmentSource
  **/
  @ApiModelProperty(readOnly = true, value = "Source of the role's assignment to the user")


  public RoleAssignmentSourceEnum getRoleAssignmentSource() {
    return roleAssignmentSource;
  }

  public void setRoleAssignmentSource(RoleAssignmentSourceEnum roleAssignmentSource) {
    this.roleAssignmentSource = roleAssignmentSource;
  }

  public AuthUserRoleAssignmentV1 roleContextualId(ProductContextualIdV1 roleContextualId) {
    this.roleContextualId = roleContextualId;
    return this;
  }

  /**
   * Id, Product, Tenant, and Entity of the role
   * @return roleContextualId
  **/
  @ApiModelProperty(required = true, value = "Id, Product, Tenant, and Entity of the role")
  @NotNull

  @Valid

  public ProductContextualIdV1 getRoleContextualId() {
    return roleContextualId;
  }

  public void setRoleContextualId(ProductContextualIdV1 roleContextualId) {
    this.roleContextualId = roleContextualId;
  }

  public AuthUserRoleAssignmentV1 tenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  /**
   * Id of tenant context for role assignment
   * @return tenantId
  **/
  @ApiModelProperty(readOnly = true, value = "Id of tenant context for role assignment")


  public String getTenantId() {
    return tenantId;
  }

  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

  public AuthUserRoleAssignmentV1 userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Id of user for role assignment
   * @return userId
  **/
  @ApiModelProperty(value = "Id of user for role assignment")


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public AuthUserRoleAssignmentV1 userIds(java.util.List<String> userIds) {
    this.userIds = userIds;
    return this;
  }

  public AuthUserRoleAssignmentV1 addUserIdsItem(String userIdsItem) {
    if (this.userIds == null) {
      this.userIds = new java.util.ArrayList<String>();
    }
    this.userIds.add(userIdsItem);
    return this;
  }

  /**
   * Ids of users for bulk role assignment
   * @return userIds
  **/
  @ApiModelProperty(value = "Ids of users for bulk role assignment")


  public java.util.List<String> getUserIds() {
    return userIds;
  }

  public void setUserIds(java.util.List<String> userIds) {
    this.userIds = userIds;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthUserRoleAssignmentV1 authUserRoleAssignmentV1 = (AuthUserRoleAssignmentV1) o;
    return Objects.equals(this.entityId, authUserRoleAssignmentV1.entityId) &&
        Objects.equals(this.groupId, authUserRoleAssignmentV1.groupId) &&
        Objects.equals(this.productId, authUserRoleAssignmentV1.productId) &&
        Objects.equals(this.roleAssignmentSource, authUserRoleAssignmentV1.roleAssignmentSource) &&
        Objects.equals(this.roleContextualId, authUserRoleAssignmentV1.roleContextualId) &&
        Objects.equals(this.tenantId, authUserRoleAssignmentV1.tenantId) &&
        Objects.equals(this.userId, authUserRoleAssignmentV1.userId) &&
        Objects.equals(this.userIds, authUserRoleAssignmentV1.userIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(entityId, groupId, productId, roleAssignmentSource, roleContextualId, tenantId, userId, userIds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuthUserRoleAssignmentV1 {\n");
    
    sb.append("    entityId: ").append(toIndentedString(entityId)).append("\n");
    sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
    sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
    sb.append("    roleAssignmentSource: ").append(toIndentedString(roleAssignmentSource)).append("\n");
    sb.append("    roleContextualId: ").append(toIndentedString(roleContextualId)).append("\n");
    sb.append("    tenantId: ").append(toIndentedString(tenantId)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    userIds: ").append(toIndentedString(userIds)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

