/*
 * Authentication Service API
 * Authentication and Authorization
 *
 * OpenAPI spec version: 1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package modelPackage;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import modelPackage.ProductContextualIdV1;
import org.threeten.bp.OffsetDateTime;

/**
 * AuthGroupRoleAssignmentV1
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-04-02T00:57:29.075-06:00")
public class AuthGroupRoleAssignmentV1 {
  @SerializedName("roleContextualId")
  private ProductContextualIdV1 roleContextualId = null;

  @SerializedName("authGroupContextualId")
  private ProductContextualIdV1 authGroupContextualId = null;

  @SerializedName("productId")
  private String productId = null;

  @SerializedName("tenantId")
  private String tenantId = null;

  @SerializedName("entityId")
  private String entityId = null;

  @SerializedName("createdOn")
  private OffsetDateTime createdOn = null;

  @SerializedName("updatedOn")
  private OffsetDateTime updatedOn = null;

  @SerializedName("deletedOn")
  private OffsetDateTime deletedOn = null;

  /**
   * Source of the Role&#39;s assignment to the AuthGroup
   */
  @JsonAdapter(RoleAssignmentSourceEnum.Adapter.class)
  public enum RoleAssignmentSourceEnum {
    FEDERATED("federated"),
    
    GROUP("group"),
    
    UNFEDERATED("unfederated");

    private String value;

    RoleAssignmentSourceEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static RoleAssignmentSourceEnum fromValue(String text) {
      for (RoleAssignmentSourceEnum b : RoleAssignmentSourceEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<RoleAssignmentSourceEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final RoleAssignmentSourceEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public RoleAssignmentSourceEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return RoleAssignmentSourceEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("roleAssignmentSource")
  private RoleAssignmentSourceEnum roleAssignmentSource = null;

  public AuthGroupRoleAssignmentV1 roleContextualId(ProductContextualIdV1 roleContextualId) {
    this.roleContextualId = roleContextualId;
    return this;
  }

   /**
   * Product, Tenant, Entity, ID of the Role
   * @return roleContextualId
  **/
  @ApiModelProperty(value = "Product, Tenant, Entity, ID of the Role")
  public ProductContextualIdV1 getRoleContextualId() {
    return roleContextualId;
  }

  public void setRoleContextualId(ProductContextualIdV1 roleContextualId) {
    this.roleContextualId = roleContextualId;
  }

  public AuthGroupRoleAssignmentV1 authGroupContextualId(ProductContextualIdV1 authGroupContextualId) {
    this.authGroupContextualId = authGroupContextualId;
    return this;
  }

   /**
   * Product, Tenant, Entity, ID of the AuthGroup
   * @return authGroupContextualId
  **/
  @ApiModelProperty(value = "Product, Tenant, Entity, ID of the AuthGroup")
  public ProductContextualIdV1 getAuthGroupContextualId() {
    return authGroupContextualId;
  }

  public void setAuthGroupContextualId(ProductContextualIdV1 authGroupContextualId) {
    this.authGroupContextualId = authGroupContextualId;
  }

  public AuthGroupRoleAssignmentV1 productId(String productId) {
    this.productId = productId;
    return this;
  }

   /**
   * Product ID assignment context in which the Role is assigned to the AuthGroup.
   * @return productId
  **/
  @ApiModelProperty(value = "Product ID assignment context in which the Role is assigned to the AuthGroup.")
  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public AuthGroupRoleAssignmentV1 tenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

   /**
   * Tenant ID assignment context in which the Role is assigned to the AuthGroup.
   * @return tenantId
  **/
  @ApiModelProperty(value = "Tenant ID assignment context in which the Role is assigned to the AuthGroup.")
  public String getTenantId() {
    return tenantId;
  }

  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

  public AuthGroupRoleAssignmentV1 entityId(String entityId) {
    this.entityId = entityId;
    return this;
  }

   /**
   * Entity ID assignment context in which the Role is assigned to the AuthGroup.
   * @return entityId
  **/
  @ApiModelProperty(value = "Entity ID assignment context in which the Role is assigned to the AuthGroup.")
  public String getEntityId() {
    return entityId;
  }

  public void setEntityId(String entityId) {
    this.entityId = entityId;
  }

   /**
   * When the Role was first granted to the Group
   * @return createdOn
  **/
  @ApiModelProperty(value = "When the Role was first granted to the Group")
  public OffsetDateTime getCreatedOn() {
    return createdOn;
  }

   /**
   * When the Role was last granted to the Group or last modified
   * @return updatedOn
  **/
  @ApiModelProperty(value = "When the Role was last granted to the Group or last modified")
  public OffsetDateTime getUpdatedOn() {
    return updatedOn;
  }

   /**
   * Date Role was revoked from Group
   * @return deletedOn
  **/
  @ApiModelProperty(value = "Date Role was revoked from Group")
  public OffsetDateTime getDeletedOn() {
    return deletedOn;
  }

   /**
   * Source of the Role&#39;s assignment to the AuthGroup
   * @return roleAssignmentSource
  **/
  @ApiModelProperty(example = "FEDERATED", value = "Source of the Role's assignment to the AuthGroup")
  public RoleAssignmentSourceEnum getRoleAssignmentSource() {
    return roleAssignmentSource;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthGroupRoleAssignmentV1 authGroupRoleAssignmentV1 = (AuthGroupRoleAssignmentV1) o;
    return Objects.equals(this.roleContextualId, authGroupRoleAssignmentV1.roleContextualId) &&
        Objects.equals(this.authGroupContextualId, authGroupRoleAssignmentV1.authGroupContextualId) &&
        Objects.equals(this.productId, authGroupRoleAssignmentV1.productId) &&
        Objects.equals(this.tenantId, authGroupRoleAssignmentV1.tenantId) &&
        Objects.equals(this.entityId, authGroupRoleAssignmentV1.entityId) &&
        Objects.equals(this.createdOn, authGroupRoleAssignmentV1.createdOn) &&
        Objects.equals(this.updatedOn, authGroupRoleAssignmentV1.updatedOn) &&
        Objects.equals(this.deletedOn, authGroupRoleAssignmentV1.deletedOn) &&
        Objects.equals(this.roleAssignmentSource, authGroupRoleAssignmentV1.roleAssignmentSource);
  }

  @Override
  public int hashCode() {
    return Objects.hash(roleContextualId, authGroupContextualId, productId, tenantId, entityId, createdOn, updatedOn, deletedOn, roleAssignmentSource);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuthGroupRoleAssignmentV1 {\n");
    
    sb.append("    roleContextualId: ").append(toIndentedString(roleContextualId)).append("\n");
    sb.append("    authGroupContextualId: ").append(toIndentedString(authGroupContextualId)).append("\n");
    sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
    sb.append("    tenantId: ").append(toIndentedString(tenantId)).append("\n");
    sb.append("    entityId: ").append(toIndentedString(entityId)).append("\n");
    sb.append("    createdOn: ").append(toIndentedString(createdOn)).append("\n");
    sb.append("    updatedOn: ").append(toIndentedString(updatedOn)).append("\n");
    sb.append("    deletedOn: ").append(toIndentedString(deletedOn)).append("\n");
    sb.append("    roleAssignmentSource: ").append(toIndentedString(roleAssignmentSource)).append("\n");
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

