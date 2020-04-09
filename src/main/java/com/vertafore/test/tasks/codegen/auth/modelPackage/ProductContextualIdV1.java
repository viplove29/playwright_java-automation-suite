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
 * ProductContextualIdV1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class ProductContextualIdV1   {
  @JsonProperty("productId")
  private String productId = null;

  @JsonProperty("tenantId")
  private String tenantId = null;

  @JsonProperty("entityId")
  private String entityId = null;

  @JsonProperty("id")
  private String id = null;

  public ProductContextualIdV1 productId(String productId) {
    this.productId = productId;
    return this;
  }

  /**
   * ID of the Product in which the resource is stored
   * @return productId
  **/
  @ApiModelProperty(value = "ID of the Product in which the resource is stored")


  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public ProductContextualIdV1 tenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  /**
   * ID of the Tenant in which the resource is stored
   * @return tenantId
  **/
  @ApiModelProperty(value = "ID of the Tenant in which the resource is stored")


  public String getTenantId() {
    return tenantId;
  }

  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

  public ProductContextualIdV1 entityId(String entityId) {
    this.entityId = entityId;
    return this;
  }

  /**
   * ID of the Entity in which the resource is stored
   * @return entityId
  **/
  @ApiModelProperty(value = "ID of the Entity in which the resource is stored")


  public String getEntityId() {
    return entityId;
  }

  public void setEntityId(String entityId) {
    this.entityId = entityId;
  }

  public ProductContextualIdV1 id(String id) {
    this.id = id;
    return this;
  }

  /**
   * ID of the resource itself
   * @return id
  **/
  @ApiModelProperty(value = "ID of the resource itself")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductContextualIdV1 productContextualIdV1 = (ProductContextualIdV1) o;
    return Objects.equals(this.productId, productContextualIdV1.productId) &&
        Objects.equals(this.tenantId, productContextualIdV1.tenantId) &&
        Objects.equals(this.entityId, productContextualIdV1.entityId) &&
        Objects.equals(this.id, productContextualIdV1.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId, tenantId, entityId, id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductContextualIdV1 {\n");
    
    sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
    sb.append("    tenantId: ").append(toIndentedString(tenantId)).append("\n");
    sb.append("    entityId: ").append(toIndentedString(entityId)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

