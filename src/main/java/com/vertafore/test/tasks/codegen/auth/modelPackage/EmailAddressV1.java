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
 * EmailAddressV1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class EmailAddressV1   {
  @JsonProperty("value")
  private String value = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("preferred")
  private Boolean preferred = null;

  public EmailAddressV1 value(String value) {
    this.value = value;
    return this;
  }

  /**
   * Identifier, the email address
   * @return value
  **/
  @ApiModelProperty(readOnly = true, value = "Identifier, the email address")


  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public EmailAddressV1 type(String type) {
    this.type = type;
    return this;
  }

  /**
   * A label indicating the email's type
   * @return type
  **/
  @ApiModelProperty(example = "'work' or 'personal'", readOnly = true, value = "A label indicating the email's type")


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public EmailAddressV1 preferred(Boolean preferred) {
    this.preferred = preferred;
    return this;
  }

  /**
   * A Boolean value indicating the 'primary' or preferred attribute value for this attribute, e.g., the preferred phone number or primary phone number. The primary attribute value 'true' MUST appear no more than once.
   * @return preferred
  **/
  @ApiModelProperty(example = "true", readOnly = true, value = "A Boolean value indicating the 'primary' or preferred attribute value for this attribute, e.g., the preferred phone number or primary phone number. The primary attribute value 'true' MUST appear no more than once.")


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
    EmailAddressV1 emailAddressV1 = (EmailAddressV1) o;
    return Objects.equals(this.value, emailAddressV1.value) &&
        Objects.equals(this.type, emailAddressV1.type) &&
        Objects.equals(this.preferred, emailAddressV1.preferred);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, type, preferred);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EmailAddressV1 {\n");
    
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

