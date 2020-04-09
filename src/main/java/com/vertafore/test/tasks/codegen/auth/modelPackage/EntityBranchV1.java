package modelPackage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import modelPackage.AttributeV1;
import modelPackage.EntityV1;
import modelPackage.JsonNode;
import modelPackage.LocationV1;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * EntityBranch
 */
@ApiModel(description = "EntityBranch")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class EntityBranchV1   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("createdOn")
  private OffsetDateTime createdOn = null;

  @JsonProperty("updatedOn")
  private OffsetDateTime updatedOn = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("parentEntityId")
  private String parentEntityId = null;

  @JsonProperty("ancestors")
  @Valid
  private java.util.Map<String, EntityV1> ancestors = null;

  @JsonProperty("attributes")
  @Valid
  private java.util.List<AttributeV1> attributes = null;

  @JsonProperty("descendants")
  @Valid
  private java.util.Map<String, EntityV1> descendants = null;

  @JsonProperty("emailAddress")
  private String emailAddress = null;

  @JsonProperty("locations")
  @Valid
  private java.util.List<LocationV1> locations = null;

  @JsonProperty("metadata")
  @Valid
  private java.util.Map<String, JsonNode> metadata = null;

  @JsonProperty("webAddress")
  private String webAddress = null;

  @JsonProperty("zoneId")
  private String zoneId = null;

  /**
   * Type of the Entity
   */
  public enum EntityTypeEnum {
    AGENCY("AGENCY"),
    
    CUSTOMER("CUSTOMER");

    private String value;

    EntityTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static EntityTypeEnum fromValue(String text) {
      for (EntityTypeEnum b : EntityTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("entityType")
  private EntityTypeEnum entityType = null;

  public EntityBranchV1 id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique id of the Entity
   * @return id
  **/
  @ApiModelProperty(example = "b647a79e-b500-4640-a1bf-2aeb2efef379", readOnly = true, value = "Unique id of the Entity")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public EntityBranchV1 createdOn(OffsetDateTime createdOn) {
    this.createdOn = createdOn;
    return this;
  }

  /**
   * When the Entity was created
   * @return createdOn
  **/
  @ApiModelProperty(example = "1970-01-01T00:00:00.000Z", readOnly = true, value = "When the Entity was created")

  @Valid

  public OffsetDateTime getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(OffsetDateTime createdOn) {
    this.createdOn = createdOn;
  }

  public EntityBranchV1 updatedOn(OffsetDateTime updatedOn) {
    this.updatedOn = updatedOn;
    return this;
  }

  /**
   * When the Entity was last modified
   * @return updatedOn
  **/
  @ApiModelProperty(example = "1970-01-01T00:00:00.000Z", readOnly = true, value = "When the Entity was last modified")

  @Valid

  public OffsetDateTime getUpdatedOn() {
    return updatedOn;
  }

  public void setUpdatedOn(OffsetDateTime updatedOn) {
    this.updatedOn = updatedOn;
  }

  public EntityBranchV1 name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the Entity
   * @return name
  **/
  @ApiModelProperty(example = "Insurance West Division - Chicago", required = true, value = "Name of the Entity")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public EntityBranchV1 parentEntityId(String parentEntityId) {
    this.parentEntityId = parentEntityId;
    return this;
  }

  /**
   * Unique id of a parent Entity of this Entity
   * @return parentEntityId
  **/
  @ApiModelProperty(example = "b647a79e-b500-4640-a1bf-2aeb2efef379", value = "Unique id of a parent Entity of this Entity")


  public String getParentEntityId() {
    return parentEntityId;
  }

  public void setParentEntityId(String parentEntityId) {
    this.parentEntityId = parentEntityId;
  }

  public EntityBranchV1 ancestors(java.util.Map<String, EntityV1> ancestors) {
    this.ancestors = ancestors;
    return this;
  }

  public EntityBranchV1 putAncestorsItem(String key, EntityV1 ancestorsItem) {
    if (this.ancestors == null) {
      this.ancestors = new java.util.HashMap<String, EntityV1>();
    }
    this.ancestors.put(key, ancestorsItem);
    return this;
  }

  /**
   * ancestors
   * @return ancestors
  **/
  @ApiModelProperty(example = "\"{ '1234-1070-5707-e7h2-1dkl' : {'name' : 'Insurance Division Parent','parent_entity_id': '1234-1070-5707-e7h2-1dkl' }\"", value = "ancestors")

  @Valid

  public java.util.Map<String, EntityV1> getAncestors() {
    return ancestors;
  }

  public void setAncestors(java.util.Map<String, EntityV1> ancestors) {
    this.ancestors = ancestors;
  }

  public EntityBranchV1 attributes(java.util.List<AttributeV1> attributes) {
    this.attributes = attributes;
    return this;
  }

  public EntityBranchV1 addAttributesItem(AttributeV1 attributesItem) {
    if (this.attributes == null) {
      this.attributes = new java.util.ArrayList<AttributeV1>();
    }
    this.attributes.add(attributesItem);
    return this;
  }

  /**
   * Attributes of the Entity
   * @return attributes
  **/
  @ApiModelProperty(value = "Attributes of the Entity")

  @Valid

  public java.util.List<AttributeV1> getAttributes() {
    return attributes;
  }

  public void setAttributes(java.util.List<AttributeV1> attributes) {
    this.attributes = attributes;
  }

  public EntityBranchV1 descendants(java.util.Map<String, EntityV1> descendants) {
    this.descendants = descendants;
    return this;
  }

  public EntityBranchV1 putDescendantsItem(String key, EntityV1 descendantsItem) {
    if (this.descendants == null) {
      this.descendants = new java.util.HashMap<String, EntityV1>();
    }
    this.descendants.put(key, descendantsItem);
    return this;
  }

  /**
   * descendants
   * @return descendants
  **/
  @ApiModelProperty(example = "\"{ '1234-1070-5707-e7h2-1dkl' : {'name' : 'Insurance Division Parent','parent_entity_id': '1234-1070-5707-e7h2-1dkl' }\"", value = "descendants")

  @Valid

  public java.util.Map<String, EntityV1> getDescendants() {
    return descendants;
  }

  public void setDescendants(java.util.Map<String, EntityV1> descendants) {
    this.descendants = descendants;
  }

  public EntityBranchV1 emailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
    return this;
  }

  /**
   * Email address of the Entity
   * @return emailAddress
  **/
  @ApiModelProperty(example = "example@gmail.com", value = "Email address of the Entity")


  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public EntityBranchV1 locations(java.util.List<LocationV1> locations) {
    this.locations = locations;
    return this;
  }

  public EntityBranchV1 addLocationsItem(LocationV1 locationsItem) {
    if (this.locations == null) {
      this.locations = new java.util.ArrayList<LocationV1>();
    }
    this.locations.add(locationsItem);
    return this;
  }

  /**
   * Location List of the entity
   * @return locations
  **/
  @ApiModelProperty(value = "Location List of the entity")

  @Valid

  public java.util.List<LocationV1> getLocations() {
    return locations;
  }

  public void setLocations(java.util.List<LocationV1> locations) {
    this.locations = locations;
  }

  public EntityBranchV1 metadata(java.util.Map<String, JsonNode> metadata) {
    this.metadata = metadata;
    return this;
  }

  public EntityBranchV1 putMetadataItem(String key, JsonNode metadataItem) {
    if (this.metadata == null) {
      this.metadata = new java.util.HashMap<String, JsonNode>();
    }
    this.metadata.put(key, metadataItem);
    return this;
  }

  /**
   * Map of custom metadata attached to the Entity. This can be any valid JSON value.
   * @return metadata
  **/
  @ApiModelProperty(example = "\"{'ams-web-ui': { 'office-type': 'shared' }}\"", value = "Map of custom metadata attached to the Entity. This can be any valid JSON value.")

  @Valid

  public java.util.Map<String, JsonNode> getMetadata() {
    return metadata;
  }

  public void setMetadata(java.util.Map<String, JsonNode> metadata) {
    this.metadata = metadata;
  }

  public EntityBranchV1 webAddress(String webAddress) {
    this.webAddress = webAddress;
    return this;
  }

  /**
   * Website of the Entity
   * @return webAddress
  **/
  @ApiModelProperty(example = "https://insurancewebsite.com/", value = "Website of the Entity")


  public String getWebAddress() {
    return webAddress;
  }

  public void setWebAddress(String webAddress) {
    this.webAddress = webAddress;
  }

  public EntityBranchV1 zoneId(String zoneId) {
    this.zoneId = zoneId;
    return this;
  }

  /**
   * The Entity's time zone, in IANA Time Zone database format [RFC6557], also known as the 'Olson' time zone database format [Olson-TZ] (e.g., 'America/Los_Angeles').
   * @return zoneId
  **/
  @ApiModelProperty(example = "America/Los_Angeles", value = "The Entity's time zone, in IANA Time Zone database format [RFC6557], also known as the 'Olson' time zone database format [Olson-TZ] (e.g., 'America/Los_Angeles').")


  public String getZoneId() {
    return zoneId;
  }

  public void setZoneId(String zoneId) {
    this.zoneId = zoneId;
  }

  public EntityBranchV1 entityType(EntityTypeEnum entityType) {
    this.entityType = entityType;
    return this;
  }

  /**
   * Type of the Entity
   * @return entityType
  **/
  @ApiModelProperty(required = true, value = "Type of the Entity")
  @NotNull


  public EntityTypeEnum getEntityType() {
    return entityType;
  }

  public void setEntityType(EntityTypeEnum entityType) {
    this.entityType = entityType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EntityBranchV1 entityBranchV1 = (EntityBranchV1) o;
    return Objects.equals(this.id, entityBranchV1.id) &&
        Objects.equals(this.createdOn, entityBranchV1.createdOn) &&
        Objects.equals(this.updatedOn, entityBranchV1.updatedOn) &&
        Objects.equals(this.name, entityBranchV1.name) &&
        Objects.equals(this.parentEntityId, entityBranchV1.parentEntityId) &&
        Objects.equals(this.ancestors, entityBranchV1.ancestors) &&
        Objects.equals(this.attributes, entityBranchV1.attributes) &&
        Objects.equals(this.descendants, entityBranchV1.descendants) &&
        Objects.equals(this.emailAddress, entityBranchV1.emailAddress) &&
        Objects.equals(this.locations, entityBranchV1.locations) &&
        Objects.equals(this.metadata, entityBranchV1.metadata) &&
        Objects.equals(this.webAddress, entityBranchV1.webAddress) &&
        Objects.equals(this.zoneId, entityBranchV1.zoneId) &&
        Objects.equals(this.entityType, entityBranchV1.entityType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdOn, updatedOn, name, parentEntityId, ancestors, attributes, descendants, emailAddress, locations, metadata, webAddress, zoneId, entityType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EntityBranchV1 {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    createdOn: ").append(toIndentedString(createdOn)).append("\n");
    sb.append("    updatedOn: ").append(toIndentedString(updatedOn)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    parentEntityId: ").append(toIndentedString(parentEntityId)).append("\n");
    sb.append("    ancestors: ").append(toIndentedString(ancestors)).append("\n");
    sb.append("    attributes: ").append(toIndentedString(attributes)).append("\n");
    sb.append("    descendants: ").append(toIndentedString(descendants)).append("\n");
    sb.append("    emailAddress: ").append(toIndentedString(emailAddress)).append("\n");
    sb.append("    locations: ").append(toIndentedString(locations)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    webAddress: ").append(toIndentedString(webAddress)).append("\n");
    sb.append("    zoneId: ").append(toIndentedString(zoneId)).append("\n");
    sb.append("    entityType: ").append(toIndentedString(entityType)).append("\n");
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

