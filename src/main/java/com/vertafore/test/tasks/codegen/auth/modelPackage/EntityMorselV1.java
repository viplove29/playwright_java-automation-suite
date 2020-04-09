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
 * EntityMorselV1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class EntityMorselV1   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("parentEntityId")
  private String parentEntityId = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("zoneId")
  private String zoneId = null;

  public EntityMorselV1 id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public EntityMorselV1 name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public EntityMorselV1 parentEntityId(String parentEntityId) {
    this.parentEntityId = parentEntityId;
    return this;
  }

  /**
   * Get parentEntityId
   * @return parentEntityId
  **/
  @ApiModelProperty(value = "")


  public String getParentEntityId() {
    return parentEntityId;
  }

  public void setParentEntityId(String parentEntityId) {
    this.parentEntityId = parentEntityId;
  }

  public EntityMorselV1 type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(value = "")


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public EntityMorselV1 zoneId(String zoneId) {
    this.zoneId = zoneId;
    return this;
  }

  /**
   * Get zoneId
   * @return zoneId
  **/
  @ApiModelProperty(value = "")


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
    EntityMorselV1 entityMorselV1 = (EntityMorselV1) o;
    return Objects.equals(this.id, entityMorselV1.id) &&
        Objects.equals(this.name, entityMorselV1.name) &&
        Objects.equals(this.parentEntityId, entityMorselV1.parentEntityId) &&
        Objects.equals(this.type, entityMorselV1.type) &&
        Objects.equals(this.zoneId, entityMorselV1.zoneId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, parentEntityId, type, zoneId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EntityMorselV1 {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    parentEntityId: ").append(toIndentedString(parentEntityId)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

