package modelPackage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import modelPackage.AttributeV1;
import modelPackage.JsonNode;
import modelPackage.LocationV1;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Entity
 */
@ApiModel(description = "Entity")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class EntityV1   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("createdOn")
  private OffsetDateTime createdOn = null;

  @JsonProperty("updatedOn")
  private OffsetDateTime updatedOn = null;

  @JsonProperty("name")
  private String name = null;

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

  @JsonProperty("parentEntityId")
  private String parentEntityId = null;

  @JsonProperty("attributes")
  @Valid
  private java.util.List<AttributeV1> attributes = null;

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

  public EntityV1 id(String id) {
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

  public EntityV1 createdOn(OffsetDateTime createdOn) {
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

  public EntityV1 updatedOn(OffsetDateTime updatedOn) {
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

  public EntityV1 name(String name) {
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

  public EntityV1 entityType(EntityTypeEnum entityType) {
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

  public EntityV1 parentEntityId(String parentEntityId) {
    this.parentEntityId = parentEntityId;
    return this;
  }

  /**
   * Unique id of the parent Entity of this Entity
   * @return parentEntityId
  **/
  @ApiModelProperty(example = "b647a79e-b500-4640-a1bf-2aeb2efef379", readOnly = true, value = "Unique id of the parent Entity of this Entity")


  public String getParentEntityId() {
    return parentEntityId;
  }

  public void setParentEntityId(String parentEntityId) {
    this.parentEntityId = parentEntityId;
  }

  public EntityV1 attributes(java.util.List<AttributeV1> attributes) {
    this.attributes = attributes;
    return this;
  }

  public EntityV1 addAttributesItem(AttributeV1 attributesItem) {
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

  public EntityV1 emailAddress(String emailAddress) {
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

  public EntityV1 locations(java.util.List<LocationV1> locations) {
    this.locations = locations;
    return this;
  }

  public EntityV1 addLocationsItem(LocationV1 locationsItem) {
    if (this.locations == null) {
      this.locations = new java.util.ArrayList<LocationV1>();
    }
    this.locations.add(locationsItem);
    return this;
  }

  /**
   * Location List of the Entity
   * @return locations
  **/
  @ApiModelProperty(value = "Location List of the Entity")

  @Valid

  public java.util.List<LocationV1> getLocations() {
    return locations;
  }

  public void setLocations(java.util.List<LocationV1> locations) {
    this.locations = locations;
  }

  public EntityV1 metadata(java.util.Map<String, JsonNode> metadata) {
    this.metadata = metadata;
    return this;
  }

  public EntityV1 putMetadataItem(String key, JsonNode metadataItem) {
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

  public EntityV1 webAddress(String webAddress) {
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

  public EntityV1 zoneId(String zoneId) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EntityV1 entityV1 = (EntityV1) o;
    return Objects.equals(this.id, entityV1.id) &&
        Objects.equals(this.createdOn, entityV1.createdOn) &&
        Objects.equals(this.updatedOn, entityV1.updatedOn) &&
        Objects.equals(this.name, entityV1.name) &&
        Objects.equals(this.entityType, entityV1.entityType) &&
        Objects.equals(this.parentEntityId, entityV1.parentEntityId) &&
        Objects.equals(this.attributes, entityV1.attributes) &&
        Objects.equals(this.emailAddress, entityV1.emailAddress) &&
        Objects.equals(this.locations, entityV1.locations) &&
        Objects.equals(this.metadata, entityV1.metadata) &&
        Objects.equals(this.webAddress, entityV1.webAddress) &&
        Objects.equals(this.zoneId, entityV1.zoneId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdOn, updatedOn, name, entityType, parentEntityId, attributes, emailAddress, locations, metadata, webAddress, zoneId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EntityV1 {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    createdOn: ").append(toIndentedString(createdOn)).append("\n");
    sb.append("    updatedOn: ").append(toIndentedString(updatedOn)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    entityType: ").append(toIndentedString(entityType)).append("\n");
    sb.append("    parentEntityId: ").append(toIndentedString(parentEntityId)).append("\n");
    sb.append("    attributes: ").append(toIndentedString(attributes)).append("\n");
    sb.append("    emailAddress: ").append(toIndentedString(emailAddress)).append("\n");
    sb.append("    locations: ").append(toIndentedString(locations)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    webAddress: ").append(toIndentedString(webAddress)).append("\n");
    sb.append("    zoneId: ").append(toIndentedString(zoneId)).append("\n");
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

