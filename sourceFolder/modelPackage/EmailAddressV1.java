/*
 * Authentication Service API
 * Authentication and Authorization
 *
 * OpenAPI spec version: 1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package modelPackage;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * EmailAddressV1
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-04-02T00:57:29.075-06:00")
public class EmailAddressV1 {
  @SerializedName("value")
  private String value = null;

  @SerializedName("type")
  private String type = null;

  @SerializedName("preferred")
  private Boolean preferred = null;

   /**
   * Identifier, the email address
   * @return value
  **/
  @ApiModelProperty(value = "Identifier, the email address")
  public String getValue() {
    return value;
  }

   /**
   * A label indicating the email&#39;s type
   * @return type
  **/
  @ApiModelProperty(example = "'work' or 'personal'", value = "A label indicating the email's type")
  public String getType() {
    return type;
  }

   /**
   * A Boolean value indicating the &#39;primary&#39; or preferred attribute value for this attribute, e.g., the preferred phone number or primary phone number. The primary attribute value &#39;true&#39; MUST appear no more than once.
   * @return preferred
  **/
  @ApiModelProperty(example = "true", value = "A Boolean value indicating the 'primary' or preferred attribute value for this attribute, e.g., the preferred phone number or primary phone number. The primary attribute value 'true' MUST appear no more than once.")
  public Boolean isPreferred() {
    return preferred;
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

