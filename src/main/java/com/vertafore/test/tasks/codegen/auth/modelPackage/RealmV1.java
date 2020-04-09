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
 * RealmV1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class RealmV1   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("updatedOn")
  private OffsetDateTime updatedOn = null;

  @JsonProperty("createdOn")
  private OffsetDateTime createdOn = null;

  public RealmV1 name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Displayable name of the Realm
   * @return name
  **/
  @ApiModelProperty(required = true, value = "Displayable name of the Realm")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RealmV1 description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Description of the Realm
   * @return description
  **/
  @ApiModelProperty(value = "Description of the Realm")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public RealmV1 updatedOn(OffsetDateTime updatedOn) {
    this.updatedOn = updatedOn;
    return this;
  }

  /**
   * When the Realm was last modified
   * @return updatedOn
  **/
  @ApiModelProperty(example = "1970-01-01T00:00:00.000Z", readOnly = true, value = "When the Realm was last modified")

  @Valid

  public OffsetDateTime getUpdatedOn() {
    return updatedOn;
  }

  public void setUpdatedOn(OffsetDateTime updatedOn) {
    this.updatedOn = updatedOn;
  }

  public RealmV1 createdOn(OffsetDateTime createdOn) {
    this.createdOn = createdOn;
    return this;
  }

  /**
   * When the Realm was created
   * @return createdOn
  **/
  @ApiModelProperty(example = "1970-01-01T00:00:00.000Z", readOnly = true, value = "When the Realm was created")

  @Valid

  public OffsetDateTime getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(OffsetDateTime createdOn) {
    this.createdOn = createdOn;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RealmV1 realmV1 = (RealmV1) o;
    return Objects.equals(this.name, realmV1.name) &&
        Objects.equals(this.description, realmV1.description) &&
        Objects.equals(this.updatedOn, realmV1.updatedOn) &&
        Objects.equals(this.createdOn, realmV1.createdOn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, updatedOn, createdOn);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RealmV1 {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    updatedOn: ").append(toIndentedString(updatedOn)).append("\n");
    sb.append("    createdOn: ").append(toIndentedString(createdOn)).append("\n");
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

