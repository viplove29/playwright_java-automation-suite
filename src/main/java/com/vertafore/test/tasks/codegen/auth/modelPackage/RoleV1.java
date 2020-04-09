package modelPackage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import modelPackage.ProductContextualIdV1;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RoleV1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class RoleV1   {
  @JsonProperty("createdOn")
  private OffsetDateTime createdOn = null;

  @JsonProperty("updatedOn")
  private OffsetDateTime updatedOn = null;

  @JsonProperty("productId")
  private String productId = null;

  @JsonProperty("tenantId")
  private String tenantId = null;

  @JsonProperty("entityId")
  private String entityId = null;

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("subRoleContextualIds")
  @Valid
  private java.util.List<ProductContextualIdV1> subRoleContextualIds = null;

  @JsonProperty("metadata")
  private Object metadata = null;

  @JsonProperty("subServiceRoles")
  @Valid
  private java.util.Map<String, java.util.List<String>> subServiceRoles = null;

  public RoleV1 createdOn(OffsetDateTime createdOn) {
    this.createdOn = createdOn;
    return this;
  }

  /**
   * When the Role was created
   * @return createdOn
  **/
  @ApiModelProperty(example = "1970-01-01T00:00:00.000Z", readOnly = true, value = "When the Role was created")

  @Valid

  public OffsetDateTime getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(OffsetDateTime createdOn) {
    this.createdOn = createdOn;
  }

  public RoleV1 updatedOn(OffsetDateTime updatedOn) {
    this.updatedOn = updatedOn;
    return this;
  }

  /**
   * When the Role was last modified
   * @return updatedOn
  **/
  @ApiModelProperty(example = "1970-01-01T00:00:00.000Z", readOnly = true, value = "When the Role was last modified")

  @Valid

  public OffsetDateTime getUpdatedOn() {
    return updatedOn;
  }

  public void setUpdatedOn(OffsetDateTime updatedOn) {
    this.updatedOn = updatedOn;
  }

  public RoleV1 productId(String productId) {
    this.productId = productId;
    return this;
  }

  /**
   * Id of product context for role
   * @return productId
  **/
  @ApiModelProperty(example = "exampleProduct", required = true, readOnly = true, value = "Id of product context for role")
  @NotNull


  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public RoleV1 tenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  /**
   * Id of tenant context for role
   * @return tenantId
  **/
  @ApiModelProperty(example = "exampleTenant", required = true, readOnly = true, value = "Id of tenant context for role")
  @NotNull


  public String getTenantId() {
    return tenantId;
  }

  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

  public RoleV1 entityId(String entityId) {
    this.entityId = entityId;
    return this;
  }

  /**
   * Id of entity context for role
   * @return entityId
  **/
  @ApiModelProperty(example = "exampleEntity", required = true, readOnly = true, value = "Id of entity context for role")
  @NotNull


  public String getEntityId() {
    return entityId;
  }

  public void setEntityId(String entityId) {
    this.entityId = entityId;
  }

  public RoleV1 id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique GUID id of a role.
   * @return id
  **/
  @ApiModelProperty(example = "20b9b890-4f2f-4dea-8dad-48d34ee22dbe", required = true, readOnly = true, value = "Unique GUID id of a role.")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public RoleV1 name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the role
   * @return name
  **/
  @ApiModelProperty(example = "Administration", required = true, value = "Name of the role")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RoleV1 description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Brief description of this Role's purpose
   * @return description
  **/
  @ApiModelProperty(example = "Represents Admin Functionality", value = "Brief description of this Role's purpose")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public RoleV1 subRoleContextualIds(java.util.List<ProductContextualIdV1> subRoleContextualIds) {
    this.subRoleContextualIds = subRoleContextualIds;
    return this;
  }

  public RoleV1 addSubRoleContextualIdsItem(ProductContextualIdV1 subRoleContextualIdsItem) {
    if (this.subRoleContextualIds == null) {
      this.subRoleContextualIds = new java.util.ArrayList<ProductContextualIdV1>();
    }
    this.subRoleContextualIds.add(subRoleContextualIdsItem);
    return this;
  }

  /**
   * List of Sub Role ContextualIds owned by this Role
   * @return subRoleContextualIds
  **/
  @ApiModelProperty(example = "\"[{\\\"id\\\":\\\"some-subRole-id\\\",\\\"productId\\\":\\\"core\\\",\\\"tenantId\\\":\\\"some-tenant-id\\\",\\\"entityId\\\":\\\"some-entity-id\\\"}]\"", value = "List of Sub Role ContextualIds owned by this Role")

  @Valid

  public java.util.List<ProductContextualIdV1> getSubRoleContextualIds() {
    return subRoleContextualIds;
  }

  public void setSubRoleContextualIds(java.util.List<ProductContextualIdV1> subRoleContextualIds) {
    this.subRoleContextualIds = subRoleContextualIds;
  }

  public RoleV1 metadata(Object metadata) {
    this.metadata = metadata;
    return this;
  }

  /**
   * Map of custom metadata attached to the role. This can be any valid JSON value.
   * @return metadata
  **/
  @ApiModelProperty(example = "\"{'structure': { 'categories': [ { 'label': 'Customers', 'serviceRoles': [ 'Customers-View', 'Customers-Manage' ] 'subCategories': [] }] }}\"", value = "Map of custom metadata attached to the role. This can be any valid JSON value.")


  public Object getMetadata() {
    return metadata;
  }

  public void setMetadata(Object metadata) {
    this.metadata = metadata;
  }

  public RoleV1 subServiceRoles(java.util.Map<String, java.util.List<String>> subServiceRoles) {
    this.subServiceRoles = subServiceRoles;
    return this;
  }

  public RoleV1 putSubServiceRolesItem(String key, java.util.List<String> subServiceRolesItem) {
    if (this.subServiceRoles == null) {
      this.subServiceRoles = new java.util.HashMap<String, java.util.List<String>>();
    }
    this.subServiceRoles.put(key, subServiceRolesItem);
    return this;
  }

  /**
   * Map of service names to service role names for Service Rolesowned by this Role.
   * @return subServiceRoles
  **/
  @ApiModelProperty(example = "\"{'example-service': ['admin'], 'other-example-service': ['viewThing', 'updateThing']}\"", value = "Map of service names to service role names for Service Rolesowned by this Role.")

  @Valid

  public java.util.Map<String, java.util.List<String>> getSubServiceRoles() {
    return subServiceRoles;
  }

  public void setSubServiceRoles(java.util.Map<String, java.util.List<String>> subServiceRoles) {
    this.subServiceRoles = subServiceRoles;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RoleV1 roleV1 = (RoleV1) o;
    return Objects.equals(this.createdOn, roleV1.createdOn) &&
        Objects.equals(this.updatedOn, roleV1.updatedOn) &&
        Objects.equals(this.productId, roleV1.productId) &&
        Objects.equals(this.tenantId, roleV1.tenantId) &&
        Objects.equals(this.entityId, roleV1.entityId) &&
        Objects.equals(this.id, roleV1.id) &&
        Objects.equals(this.name, roleV1.name) &&
        Objects.equals(this.description, roleV1.description) &&
        Objects.equals(this.subRoleContextualIds, roleV1.subRoleContextualIds) &&
        Objects.equals(this.metadata, roleV1.metadata) &&
        Objects.equals(this.subServiceRoles, roleV1.subServiceRoles);
  }

  @Override
  public int hashCode() {
    return Objects.hash(createdOn, updatedOn, productId, tenantId, entityId, id, name, description, subRoleContextualIds, metadata, subServiceRoles);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RoleV1 {\n");
    
    sb.append("    createdOn: ").append(toIndentedString(createdOn)).append("\n");
    sb.append("    updatedOn: ").append(toIndentedString(updatedOn)).append("\n");
    sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
    sb.append("    tenantId: ").append(toIndentedString(tenantId)).append("\n");
    sb.append("    entityId: ").append(toIndentedString(entityId)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    subRoleContextualIds: ").append(toIndentedString(subRoleContextualIds)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    subServiceRoles: ").append(toIndentedString(subServiceRoles)).append("\n");
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

