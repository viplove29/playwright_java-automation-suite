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
 * Standard Phone Number
 */
@ApiModel(description = "Standard Phone Number")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class PhoneNumberV1   {
  @JsonProperty("countryCode")
  private String countryCode = null;

  @JsonProperty("extension")
  private String extension = null;

  @JsonProperty("phoneNumber")
  private String phoneNumber = null;

  @JsonProperty("preferred")
  private Boolean preferred = null;

  @JsonProperty("type")
  private String type = null;

  public PhoneNumberV1 countryCode(String countryCode) {
    this.countryCode = countryCode;
    return this;
  }

  /**
   * Country calling code
   * @return countryCode
  **/
  @ApiModelProperty(example = "1", value = "Country calling code")


  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public PhoneNumberV1 extension(String extension) {
    this.extension = extension;
    return this;
  }

  /**
   * Extension number
   * @return extension
  **/
  @ApiModelProperty(example = "4567", value = "Extension number")


  public String getExtension() {
    return extension;
  }

  public void setExtension(String extension) {
    this.extension = extension;
  }

  public PhoneNumberV1 phoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  /**
   * Phone number
   * @return phoneNumber
  **/
  @ApiModelProperty(example = "5555555555", value = "Phone number")


  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public PhoneNumberV1 preferred(Boolean preferred) {
    this.preferred = preferred;
    return this;
  }

  /**
   * Indicates a preferred Phone Number
   * @return preferred
  **/
  @ApiModelProperty(example = "true", value = "Indicates a preferred Phone Number")


  public Boolean isPreferred() {
    return preferred;
  }

  public void setPreferred(Boolean preferred) {
    this.preferred = preferred;
  }

  public PhoneNumberV1 type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Type of phone number
   * @return type
  **/
  @ApiModelProperty(example = "Cell Phone", value = "Type of phone number")


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PhoneNumberV1 phoneNumberV1 = (PhoneNumberV1) o;
    return Objects.equals(this.countryCode, phoneNumberV1.countryCode) &&
        Objects.equals(this.extension, phoneNumberV1.extension) &&
        Objects.equals(this.phoneNumber, phoneNumberV1.phoneNumber) &&
        Objects.equals(this.preferred, phoneNumberV1.preferred) &&
        Objects.equals(this.type, phoneNumberV1.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(countryCode, extension, phoneNumber, preferred, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PhoneNumberV1 {\n");
    
    sb.append("    countryCode: ").append(toIndentedString(countryCode)).append("\n");
    sb.append("    extension: ").append(toIndentedString(extension)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
    sb.append("    preferred: ").append(toIndentedString(preferred)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

