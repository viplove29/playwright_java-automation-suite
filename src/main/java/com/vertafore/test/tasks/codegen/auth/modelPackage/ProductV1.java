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
 * ProductV1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class ProductV1   {
  @JsonProperty("createdOn")
  private OffsetDateTime createdOn = null;

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("updatedOn")
  private OffsetDateTime updatedOn = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("internal")
  private Boolean internal = null;

  public ProductV1 createdOn(OffsetDateTime createdOn) {
    this.createdOn = createdOn;
    return this;
  }

  /**
   * When the Product was created
   * @return createdOn
  **/
  @ApiModelProperty(readOnly = true, value = "When the Product was created")

  @Valid

  public OffsetDateTime getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(OffsetDateTime createdOn) {
    this.createdOn = createdOn;
  }

  public ProductV1 id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique, human-readable id of the Product
   * @return id
  **/
  @ApiModelProperty(example = "VERTAFORE", required = true, value = "Unique, human-readable id of the Product")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ProductV1 updatedOn(OffsetDateTime updatedOn) {
    this.updatedOn = updatedOn;
    return this;
  }

  /**
   * When the Product was last modified
   * @return updatedOn
  **/
  @ApiModelProperty(readOnly = true, value = "When the Product was last modified")

  @Valid

  public OffsetDateTime getUpdatedOn() {
    return updatedOn;
  }

  public void setUpdatedOn(OffsetDateTime updatedOn) {
    this.updatedOn = updatedOn;
  }

  public ProductV1 name(String name) {
    this.name = name;
    return this;
  }

  /**
   * A human-readable name for the Product.
   * @return name
  **/
  @ApiModelProperty(example = "AMS360", required = true, value = "A human-readable name for the Product.")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ProductV1 internal(Boolean internal) {
    this.internal = internal;
    return this;
  }

  /**
   * Whether the Product is intended to be used internally
   * @return internal
  **/
  @ApiModelProperty(example = "false", required = true, value = "Whether the Product is intended to be used internally")
  @NotNull


  public Boolean isInternal() {
    return internal;
  }

  public void setInternal(Boolean internal) {
    this.internal = internal;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductV1 productV1 = (ProductV1) o;
    return Objects.equals(this.createdOn, productV1.createdOn) &&
        Objects.equals(this.id, productV1.id) &&
        Objects.equals(this.updatedOn, productV1.updatedOn) &&
        Objects.equals(this.name, productV1.name) &&
        Objects.equals(this.internal, productV1.internal);
  }

  @Override
  public int hashCode() {
    return Objects.hash(createdOn, id, updatedOn, name, internal);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductV1 {\n");
    
    sb.append("    createdOn: ").append(toIndentedString(createdOn)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    updatedOn: ").append(toIndentedString(updatedOn)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    internal: ").append(toIndentedString(internal)).append("\n");
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

