package modelPackage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AssignedRoleCountV1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class AssignedRoleCountV1   {
  @JsonProperty("tenantId")
  private String tenantId = null;

  @JsonProperty("entityId")
  private String entityId = null;

  @JsonProperty("roleIds")
  @Valid
  private java.util.List<String> roleIds = new java.util.ArrayList<String>();

  @JsonProperty("userStatus")
  private String userStatus = null;

  @JsonProperty("assignedRoleCount")
  private Integer assignedRoleCount = null;

  public AssignedRoleCountV1 tenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  /**
   * Id of tenant context for users assigned roles that are included in the count
   * @return tenantId
  **/
  @ApiModelProperty(example = "exampleTenant", required = true, readOnly = true, value = "Id of tenant context for users assigned roles that are included in the count")
  @NotNull


  public String getTenantId() {
    return tenantId;
  }

  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

  public AssignedRoleCountV1 entityId(String entityId) {
    this.entityId = entityId;
    return this;
  }

  /**
   * Id of entity context for users assigned roles that are included in the count
   * @return entityId
  **/
  @ApiModelProperty(example = "exampleEntityId", required = true, readOnly = true, value = "Id of entity context for users assigned roles that are included in the count")
  @NotNull


  public String getEntityId() {
    return entityId;
  }

  public void setEntityId(String entityId) {
    this.entityId = entityId;
  }

  public AssignedRoleCountV1 roleIds(java.util.List<String> roleIds) {
    this.roleIds = roleIds;
    return this;
  }

  public AssignedRoleCountV1 addRoleIdsItem(String roleIdsItem) {
    this.roleIds.add(roleIdsItem);
    return this;
  }

  /**
   * Role ids included in the count
   * @return roleIds
  **/
  @ApiModelProperty(example = "\"['UUID1', 'UUID2']\"", required = true, value = "Role ids included in the count")
  @NotNull


  public java.util.List<String> getRoleIds() {
    return roleIds;
  }

  public void setRoleIds(java.util.List<String> roleIds) {
    this.roleIds = roleIds;
  }

  public AssignedRoleCountV1 userStatus(String userStatus) {
    this.userStatus = userStatus;
    return this;
  }

  /**
   * Status of users to include, true for only active users, false for only inactive user and all for all 
   * @return userStatus
  **/
  @ApiModelProperty(example = "true", required = true, value = "Status of users to include, true for only active users, false for only inactive user and all for all ")
  @NotNull


  public String getUserStatus() {
    return userStatus;
  }

  public void setUserStatus(String userStatus) {
    this.userStatus = userStatus;
  }

  public AssignedRoleCountV1 assignedRoleCount(Integer assignedRoleCount) {
    this.assignedRoleCount = assignedRoleCount;
    return this;
  }

  /**
   * Number of assigned roles
   * @return assignedRoleCount
  **/
  @ApiModelProperty(readOnly = true, value = "Number of assigned roles")


  public Integer getAssignedRoleCount() {
    return assignedRoleCount;
  }

  public void setAssignedRoleCount(Integer assignedRoleCount) {
    this.assignedRoleCount = assignedRoleCount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AssignedRoleCountV1 assignedRoleCountV1 = (AssignedRoleCountV1) o;
    return Objects.equals(this.tenantId, assignedRoleCountV1.tenantId) &&
        Objects.equals(this.entityId, assignedRoleCountV1.entityId) &&
        Objects.equals(this.roleIds, assignedRoleCountV1.roleIds) &&
        Objects.equals(this.userStatus, assignedRoleCountV1.userStatus) &&
        Objects.equals(this.assignedRoleCount, assignedRoleCountV1.assignedRoleCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tenantId, entityId, roleIds, userStatus, assignedRoleCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AssignedRoleCountV1 {\n");
    
    sb.append("    tenantId: ").append(toIndentedString(tenantId)).append("\n");
    sb.append("    entityId: ").append(toIndentedString(entityId)).append("\n");
    sb.append("    roleIds: ").append(toIndentedString(roleIds)).append("\n");
    sb.append("    userStatus: ").append(toIndentedString(userStatus)).append("\n");
    sb.append("    assignedRoleCount: ").append(toIndentedString(assignedRoleCount)).append("\n");
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

