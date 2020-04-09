package modelPackage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ServiceRoleV1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class ServiceRoleV1   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("serviceName")
  private String serviceName = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("entitlements")
  @Valid
  private java.util.List<String> entitlements = null;

  @JsonProperty("subServiceRoles")
  @Valid
  private java.util.Map<String, java.util.List<String>> subServiceRoles = null;

  @JsonProperty("createdOn")
  private OffsetDateTime createdOn = null;

  @JsonProperty("updatedOn")
  private OffsetDateTime updatedOn = null;

  public ServiceRoleV1 name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Provided display name, unique in combination with `serviceName`
   * @return name
  **/
  @ApiModelProperty(example = "admin", required = true, value = "Provided display name, unique in combination with `serviceName`")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ServiceRoleV1 serviceName(String serviceName) {
    this.serviceName = serviceName;
    return this;
  }

  /**
   * Service to which this Service Role belongs
   * @return serviceName
  **/
  @ApiModelProperty(example = "example-service", required = true, value = "Service to which this Service Role belongs")
  @NotNull


  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public ServiceRoleV1 description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Brief description of this Service Role's purpose
   * @return description
  **/
  @ApiModelProperty(example = "Represents Admin Functionality", value = "Brief description of this Service Role's purpose")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ServiceRoleV1 entitlements(java.util.List<String> entitlements) {
    this.entitlements = entitlements;
    return this;
  }

  public ServiceRoleV1 addEntitlementsItem(String entitlementsItem) {
    if (this.entitlements == null) {
      this.entitlements = new java.util.ArrayList<String>();
    }
    this.entitlements.add(entitlementsItem);
    return this;
  }

  /**
   * List of Entitlements owned by this Service Role
   * @return entitlements
  **/
  @ApiModelProperty(example = "\"['example-service.user.delete', 'example-service.user.read']\"", value = "List of Entitlements owned by this Service Role")


  public java.util.List<String> getEntitlements() {
    return entitlements;
  }

  public void setEntitlements(java.util.List<String> entitlements) {
    this.entitlements = entitlements;
  }

  public ServiceRoleV1 subServiceRoles(java.util.Map<String, java.util.List<String>> subServiceRoles) {
    this.subServiceRoles = subServiceRoles;
    return this;
  }

  public ServiceRoleV1 putSubServiceRolesItem(String key, java.util.List<String> subServiceRolesItem) {
    if (this.subServiceRoles == null) {
      this.subServiceRoles = new java.util.HashMap<String, java.util.List<String>>();
    }
    this.subServiceRoles.put(key, subServiceRolesItem);
    return this;
  }

  /**
   * Map of service names to service role names for Service Roles owned by this Role.
   * @return subServiceRoles
  **/
  @ApiModelProperty(example = "\"{'example-service': ['admin'], 'other-example-service': ['viewThing', 'updateThing']}\"", value = "Map of service names to service role names for Service Roles owned by this Role.")

  @Valid

  public java.util.Map<String, java.util.List<String>> getSubServiceRoles() {
    return subServiceRoles;
  }

  public void setSubServiceRoles(java.util.Map<String, java.util.List<String>> subServiceRoles) {
    this.subServiceRoles = subServiceRoles;
  }

  public ServiceRoleV1 createdOn(OffsetDateTime createdOn) {
    this.createdOn = createdOn;
    return this;
  }

  /**
   * When the ServiceRole was created
   * @return createdOn
  **/
  @ApiModelProperty(example = "1970-01-01T00:00:00.000Z", readOnly = true, value = "When the ServiceRole was created")

  @Valid

  public OffsetDateTime getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(OffsetDateTime createdOn) {
    this.createdOn = createdOn;
  }

  public ServiceRoleV1 updatedOn(OffsetDateTime updatedOn) {
    this.updatedOn = updatedOn;
    return this;
  }

  /**
   * When the ServiceRole was last modified
   * @return updatedOn
  **/
  @ApiModelProperty(example = "1970-01-01T00:00:00.000Z", readOnly = true, value = "When the ServiceRole was last modified")

  @Valid

  public OffsetDateTime getUpdatedOn() {
    return updatedOn;
  }

  public void setUpdatedOn(OffsetDateTime updatedOn) {
    this.updatedOn = updatedOn;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ServiceRoleV1 serviceRoleV1 = (ServiceRoleV1) o;
    return Objects.equals(this.name, serviceRoleV1.name) &&
        Objects.equals(this.serviceName, serviceRoleV1.serviceName) &&
        Objects.equals(this.description, serviceRoleV1.description) &&
        Objects.equals(this.entitlements, serviceRoleV1.entitlements) &&
        Objects.equals(this.subServiceRoles, serviceRoleV1.subServiceRoles) &&
        Objects.equals(this.createdOn, serviceRoleV1.createdOn) &&
        Objects.equals(this.updatedOn, serviceRoleV1.updatedOn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, serviceName, description, entitlements, subServiceRoles, createdOn, updatedOn);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServiceRoleV1 {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    serviceName: ").append(toIndentedString(serviceName)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    entitlements: ").append(toIndentedString(entitlements)).append("\n");
    sb.append("    subServiceRoles: ").append(toIndentedString(subServiceRoles)).append("\n");
    sb.append("    createdOn: ").append(toIndentedString(createdOn)).append("\n");
    sb.append("    updatedOn: ").append(toIndentedString(updatedOn)).append("\n");
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

