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
 * NameV1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class NameV1   {
  @JsonProperty("familyName")
  private String familyName = null;

  @JsonProperty("formatted")
  private String formatted = null;

  @JsonProperty("givenName")
  private String givenName = null;

  @JsonProperty("honorificPrefix")
  private String honorificPrefix = null;

  @JsonProperty("honorificSuffix")
  private String honorificSuffix = null;

  @JsonProperty("middleName")
  private String middleName = null;

  public NameV1 familyName(String familyName) {
    this.familyName = familyName;
    return this;
  }

  /**
   * The family name of the User, or last name in most Western languages (e.g., \"Jensen\" given the full name \"Ms. Barbara Jane Jensen, III\"). 
   * @return familyName
  **/
  @ApiModelProperty(example = "Jensen", value = "The family name of the User, or last name in most Western languages (e.g., \"Jensen\" given the full name \"Ms. Barbara Jane Jensen, III\"). ")


  public String getFamilyName() {
    return familyName;
  }

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }

  public NameV1 formatted(String formatted) {
    this.formatted = formatted;
    return this;
  }

  /**
   * The full name, including all middle names, titles, and suffixes as appropriate, formatted for display (e.g., \"Ms. Barbara Jane Jensen, III\".
   * @return formatted
  **/
  @ApiModelProperty(example = "Ms. Barbara Jane Jensen, III", value = "The full name, including all middle names, titles, and suffixes as appropriate, formatted for display (e.g., \"Ms. Barbara Jane Jensen, III\".")


  public String getFormatted() {
    return formatted;
  }

  public void setFormatted(String formatted) {
    this.formatted = formatted;
  }

  public NameV1 givenName(String givenName) {
    this.givenName = givenName;
    return this;
  }

  /**
   * The given name of the User, or first name in most Western languages (e.g., \"Barbara\" given the full name \"Ms. Barbara Jane Jensen, III\").
   * @return givenName
  **/
  @ApiModelProperty(example = "Barbara", value = "The given name of the User, or first name in most Western languages (e.g., \"Barbara\" given the full name \"Ms. Barbara Jane Jensen, III\").")


  public String getGivenName() {
    return givenName;
  }

  public void setGivenName(String givenName) {
    this.givenName = givenName;
  }

  public NameV1 honorificPrefix(String honorificPrefix) {
    this.honorificPrefix = honorificPrefix;
    return this;
  }

  /**
   * The honorific prefix(es) of the User, or title in most Western languages (e.g., \"Ms.\" given the full name \"Ms. Barbara Jane Jensen, III\").
   * @return honorificPrefix
  **/
  @ApiModelProperty(example = "Ms.", value = "The honorific prefix(es) of the User, or title in most Western languages (e.g., \"Ms.\" given the full name \"Ms. Barbara Jane Jensen, III\").")


  public String getHonorificPrefix() {
    return honorificPrefix;
  }

  public void setHonorificPrefix(String honorificPrefix) {
    this.honorificPrefix = honorificPrefix;
  }

  public NameV1 honorificSuffix(String honorificSuffix) {
    this.honorificSuffix = honorificSuffix;
    return this;
  }

  /**
   * The honorific suffix(es) of the User, or suffix in most Western languages (e.g., \"III\" given the full name \"Ms. Barbara Jane Jensen, III\").
   * @return honorificSuffix
  **/
  @ApiModelProperty(example = "III", value = "The honorific suffix(es) of the User, or suffix in most Western languages (e.g., \"III\" given the full name \"Ms. Barbara Jane Jensen, III\").")


  public String getHonorificSuffix() {
    return honorificSuffix;
  }

  public void setHonorificSuffix(String honorificSuffix) {
    this.honorificSuffix = honorificSuffix;
  }

  public NameV1 middleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

  /**
   * The middle name(s) of the User (e.g., \"Jane\" given the full name \"Ms. Barbara Jane Jensen, III\").
   * @return middleName
  **/
  @ApiModelProperty(example = "Jane", value = "The middle name(s) of the User (e.g., \"Jane\" given the full name \"Ms. Barbara Jane Jensen, III\").")


  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NameV1 nameV1 = (NameV1) o;
    return Objects.equals(this.familyName, nameV1.familyName) &&
        Objects.equals(this.formatted, nameV1.formatted) &&
        Objects.equals(this.givenName, nameV1.givenName) &&
        Objects.equals(this.honorificPrefix, nameV1.honorificPrefix) &&
        Objects.equals(this.honorificSuffix, nameV1.honorificSuffix) &&
        Objects.equals(this.middleName, nameV1.middleName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(familyName, formatted, givenName, honorificPrefix, honorificSuffix, middleName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NameV1 {\n");
    
    sb.append("    familyName: ").append(toIndentedString(familyName)).append("\n");
    sb.append("    formatted: ").append(toIndentedString(formatted)).append("\n");
    sb.append("    givenName: ").append(toIndentedString(givenName)).append("\n");
    sb.append("    honorificPrefix: ").append(toIndentedString(honorificPrefix)).append("\n");
    sb.append("    honorificSuffix: ").append(toIndentedString(honorificSuffix)).append("\n");
    sb.append("    middleName: ").append(toIndentedString(middleName)).append("\n");
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

