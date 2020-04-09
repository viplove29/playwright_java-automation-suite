package modelPackage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import modelPackage.AuthorizationInfoV1;
import modelPackage.VertaforeUserV1;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ContextualAuthInfoV1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class ContextualAuthInfoV1   {
  @JsonProperty("authz")
  private AuthorizationInfoV1 authz = null;

  @JsonProperty("user")
  private VertaforeUserV1 user = null;

  public ContextualAuthInfoV1 authz(AuthorizationInfoV1 authz) {
    this.authz = authz;
    return this;
  }

  /**
   * Get authz
   * @return authz
  **/
  @ApiModelProperty(value = "")

  @Valid

  public AuthorizationInfoV1 getAuthz() {
    return authz;
  }

  public void setAuthz(AuthorizationInfoV1 authz) {
    this.authz = authz;
  }

  public ContextualAuthInfoV1 user(VertaforeUserV1 user) {
    this.user = user;
    return this;
  }

  /**
   * Get user
   * @return user
  **/
  @ApiModelProperty(value = "")

  @Valid

  public VertaforeUserV1 getUser() {
    return user;
  }

  public void setUser(VertaforeUserV1 user) {
    this.user = user;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ContextualAuthInfoV1 contextualAuthInfoV1 = (ContextualAuthInfoV1) o;
    return Objects.equals(this.authz, contextualAuthInfoV1.authz) &&
        Objects.equals(this.user, contextualAuthInfoV1.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authz, user);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ContextualAuthInfoV1 {\n");
    
    sb.append("    authz: ").append(toIndentedString(authz)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
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

