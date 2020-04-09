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
 * AuthUserContextV1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class AuthUserContextV1   {
  @JsonProperty("active")
  private Boolean active = null;

  @JsonProperty("entityId")
  private String entityId = null;

  @JsonProperty("tenantId")
  private String tenantId = null;

  public AuthUserContextV1 active(Boolean active) {
    this.active = active;
    return this;
  }

  /**
   * active status of entity context assignment
   * @return active
  **/
  @ApiModelProperty(example = "false", readOnly = true, value = "active status of entity context assignment")


  public Boolean isActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public AuthUserContextV1 entityId(String entityId) {
    this.entityId = entityId;
    return this;
  }

  /**
   * Id of entity context for assignment
   * @return entityId
  **/
  @ApiModelProperty(readOnly = true, value = "Id of entity context for assignment")


  public String getEntityId() {
    return entityId;
  }

  public void setEntityId(String entityId) {
    this.entityId = entityId;
  }

  public AuthUserContextV1 tenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  /**
   * Id of tenant context for assignment
   * @return tenantId
  **/
  @ApiModelProperty(readOnly = true, value = "Id of tenant context for assignment")


  public String getTenantId() {
    return tenantId;
  }

  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthUserContextV1 authUserContextV1 = (AuthUserContextV1) o;
    return Objects.equals(this.active, authUserContextV1.active) &&
        Objects.equals(this.entityId, authUserContextV1.entityId) &&
        Objects.equals(this.tenantId, authUserContextV1.tenantId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(active, entityId, tenantId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuthUserContextV1 {\n");
    
    sb.append("    active: ").append(toIndentedString(active)).append("\n");
    sb.append("    entityId: ").append(toIndentedString(entityId)).append("\n");
    sb.append("    tenantId: ").append(toIndentedString(tenantId)).append("\n");
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

