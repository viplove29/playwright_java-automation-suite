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
 * PhotoAddressV1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class PhotoAddressV1   {
  @JsonProperty("value")
  private String value = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("display")
  private String display = null;

  @JsonProperty("preferred")
  private Boolean preferred = null;

  public PhotoAddressV1 value(String value) {
    this.value = value;
    return this;
  }

  /**
   * Identifier, URI/address of the photo
   * @return value
  **/
  @ApiModelProperty(readOnly = true, value = "Identifier, URI/address of the photo")


  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public PhotoAddressV1 type(String type) {
    this.type = type;
    return this;
  }

  /**
   * A label indicating the photo's function
   * @return type
  **/
  @ApiModelProperty(example = "'photo' or 'thumbnail'", readOnly = true, value = "A label indicating the photo's function")


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public PhotoAddressV1 display(String display) {
    this.display = display;
    return this;
  }

  /**
   * A human-readable name, primarily used for display purposes.
   * @return display
  **/
  @ApiModelProperty(example = "My-Favority-Profile-Pic", readOnly = true, value = "A human-readable name, primarily used for display purposes.")


  public String getDisplay() {
    return display;
  }

  public void setDisplay(String display) {
    this.display = display;
  }

  public PhotoAddressV1 preferred(Boolean preferred) {
    this.preferred = preferred;
    return this;
  }

  /**
   * A Boolean value indicating the 'primary' or preferred attribute value for this attribute, e.g., the preferred photo. The primary attribute value 'true' MUST appear no more than once.
   * @return preferred
  **/
  @ApiModelProperty(example = "true", readOnly = true, value = "A Boolean value indicating the 'primary' or preferred attribute value for this attribute, e.g., the preferred photo. The primary attribute value 'true' MUST appear no more than once.")


  public Boolean isPreferred() {
    return preferred;
  }

  public void setPreferred(Boolean preferred) {
    this.preferred = preferred;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PhotoAddressV1 photoAddressV1 = (PhotoAddressV1) o;
    return Objects.equals(this.value, photoAddressV1.value) &&
        Objects.equals(this.type, photoAddressV1.type) &&
        Objects.equals(this.display, photoAddressV1.display) &&
        Objects.equals(this.preferred, photoAddressV1.preferred);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, type, display, preferred);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PhotoAddressV1 {\n");
    
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    display: ").append(toIndentedString(display)).append("\n");
    sb.append("    preferred: ").append(toIndentedString(preferred)).append("\n");
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

