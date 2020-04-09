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
 * AuthGroupMembershipV1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class AuthGroupMembershipV1   {
  @JsonProperty("userId")
  private String userId = null;

  @JsonProperty("productId")
  private String productId = null;

  @JsonProperty("tenantId")
  private String tenantId = null;

  @JsonProperty("entityId")
  private String entityId = null;

  @JsonProperty("authGroupContextualId")
  private ProductContextualIdV1 authGroupContextualId = null;

  /**
   * Source of the AuthUser's Membership to the AuthGroup
   */
  public enum AuthGroupMembershipSourceEnum {
    FEDERATED("federated"),
    
    UNFEDERATED("unfederated");

    private String value;

    AuthGroupMembershipSourceEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static AuthGroupMembershipSourceEnum fromValue(String text) {
      for (AuthGroupMembershipSourceEnum b : AuthGroupMembershipSourceEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("authGroupMembershipSource")
  private AuthGroupMembershipSourceEnum authGroupMembershipSource = null;

  public AuthGroupMembershipV1 userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * ID of the AuthUser
   * @return userId
  **/
  @ApiModelProperty(example = "b647a79e-b500-4640-a1bf-2aeb2efef379", readOnly = true, value = "ID of the AuthUser")


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public AuthGroupMembershipV1 productId(String productId) {
    this.productId = productId;
    return this;
  }

  /**
   * Product ID assignment context in which the AuthUser has membership in the AuthGroup.
   * @return productId
  **/
  @ApiModelProperty(readOnly = true, value = "Product ID assignment context in which the AuthUser has membership in the AuthGroup.")


  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public AuthGroupMembershipV1 tenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  /**
   * Tenant ID assignment context in which the AuthUser has membership in the AuthGroup.
   * @return tenantId
  **/
  @ApiModelProperty(readOnly = true, value = "Tenant ID assignment context in which the AuthUser has membership in the AuthGroup.")


  public String getTenantId() {
    return tenantId;
  }

  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

  public AuthGroupMembershipV1 entityId(String entityId) {
    this.entityId = entityId;
    return this;
  }

  /**
   * Entity ID assignment context in which the AuthUser has membership in the AuthGroup.
   * @return entityId
  **/
  @ApiModelProperty(readOnly = true, value = "Entity ID assignment context in which the AuthUser has membership in the AuthGroup.")


  public String getEntityId() {
    return entityId;
  }

  public void setEntityId(String entityId) {
    this.entityId = entityId;
  }

  public AuthGroupMembershipV1 authGroupContextualId(ProductContextualIdV1 authGroupContextualId) {
    this.authGroupContextualId = authGroupContextualId;
    return this;
  }

  /**
   * Product, Tenant, Entity, ID of the AuthGroup
   * @return authGroupContextualId
  **/
  @ApiModelProperty(readOnly = true, value = "Product, Tenant, Entity, ID of the AuthGroup")

  @Valid

  public ProductContextualIdV1 getAuthGroupContextualId() {
    return authGroupContextualId;
  }

  public void setAuthGroupContextualId(ProductContextualIdV1 authGroupContextualId) {
    this.authGroupContextualId = authGroupContextualId;
  }

  public AuthGroupMembershipV1 authGroupMembershipSource(AuthGroupMembershipSourceEnum authGroupMembershipSource) {
    this.authGroupMembershipSource = authGroupMembershipSource;
    return this;
  }

  /**
   * Source of the AuthUser's Membership to the AuthGroup
   * @return authGroupMembershipSource
  **/
  @ApiModelProperty(example = "UNFEDERATED", readOnly = true, value = "Source of the AuthUser's Membership to the AuthGroup")


  public AuthGroupMembershipSourceEnum getAuthGroupMembershipSource() {
    return authGroupMembershipSource;
  }

  public void setAuthGroupMembershipSource(AuthGroupMembershipSourceEnum authGroupMembershipSource) {
    this.authGroupMembershipSource = authGroupMembershipSource;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthGroupMembershipV1 authGroupMembershipV1 = (AuthGroupMembershipV1) o;
    return Objects.equals(this.userId, authGroupMembershipV1.userId) &&
        Objects.equals(this.productId, authGroupMembershipV1.productId) &&
        Objects.equals(this.tenantId, authGroupMembershipV1.tenantId) &&
        Objects.equals(this.entityId, authGroupMembershipV1.entityId) &&
        Objects.equals(this.authGroupContextualId, authGroupMembershipV1.authGroupContextualId) &&
        Objects.equals(this.authGroupMembershipSource, authGroupMembershipV1.authGroupMembershipSource);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, productId, tenantId, entityId, authGroupContextualId, authGroupMembershipSource);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuthGroupMembershipV1 {\n");
    
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
    sb.append("    tenantId: ").append(toIndentedString(tenantId)).append("\n");
    sb.append("    entityId: ").append(toIndentedString(entityId)).append("\n");
    sb.append("    authGroupContextualId: ").append(toIndentedString(authGroupContextualId)).append("\n");
    sb.append("    authGroupMembershipSource: ").append(toIndentedString(authGroupMembershipSource)).append("\n");
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

