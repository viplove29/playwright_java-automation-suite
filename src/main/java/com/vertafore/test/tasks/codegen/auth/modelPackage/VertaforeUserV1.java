package modelPackage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import modelPackage.VertaforeUserV1;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * VertaforeUserV1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class VertaforeUserV1   {
  @JsonProperty("authenticatedUser")
  private VertaforeUserV1 authenticatedUser = null;

  @JsonProperty("displayName")
  private String displayName = null;

  @JsonProperty("familyName")
  private String familyName = null;

  @JsonProperty("givenName")
  private String givenName = null;

  @JsonProperty("id")
  private String id = null;

  public VertaforeUserV1 authenticatedUser(VertaforeUserV1 authenticatedUser) {
    this.authenticatedUser = authenticatedUser;
    return this;
  }

  /**
   * Get authenticatedUser
   * @return authenticatedUser
  **/
  @ApiModelProperty(value = "")

  @Valid

  public VertaforeUserV1 getAuthenticatedUser() {
    return authenticatedUser;
  }

  public void setAuthenticatedUser(VertaforeUserV1 authenticatedUser) {
    this.authenticatedUser = authenticatedUser;
  }

  public VertaforeUserV1 displayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  /**
   * Get displayName
   * @return displayName
  **/
  @ApiModelProperty(value = "")


  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public VertaforeUserV1 familyName(String familyName) {
    this.familyName = familyName;
    return this;
  }

  /**
   * Get familyName
   * @return familyName
  **/
  @ApiModelProperty(value = "")


  public String getFamilyName() {
    return familyName;
  }

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }

  public VertaforeUserV1 givenName(String givenName) {
    this.givenName = givenName;
    return this;
  }

  /**
   * Get givenName
   * @return givenName
  **/
  @ApiModelProperty(value = "")


  public String getGivenName() {
    return givenName;
  }

  public void setGivenName(String givenName) {
    this.givenName = givenName;
  }

  public VertaforeUserV1 id(String id) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VertaforeUserV1 vertaforeUserV1 = (VertaforeUserV1) o;
    return Objects.equals(this.authenticatedUser, vertaforeUserV1.authenticatedUser) &&
        Objects.equals(this.displayName, vertaforeUserV1.displayName) &&
        Objects.equals(this.familyName, vertaforeUserV1.familyName) &&
        Objects.equals(this.givenName, vertaforeUserV1.givenName) &&
        Objects.equals(this.id, vertaforeUserV1.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authenticatedUser, displayName, familyName, givenName, id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VertaforeUserV1 {\n");
    
    sb.append("    authenticatedUser: ").append(toIndentedString(authenticatedUser)).append("\n");
    sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
    sb.append("    familyName: ").append(toIndentedString(familyName)).append("\n");
    sb.append("    givenName: ").append(toIndentedString(givenName)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

