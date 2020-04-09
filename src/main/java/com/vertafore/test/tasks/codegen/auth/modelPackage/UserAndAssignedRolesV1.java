package modelPackage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import modelPackage.AuthUserV1;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UserAndAssignedRolesV1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class UserAndAssignedRolesV1   {
  @JsonProperty("roles")
  @Valid
  private java.util.Map<String, java.util.List<String>> roles = null;

  @JsonProperty("user")
  private AuthUserV1 user = null;

  public UserAndAssignedRolesV1 roles(java.util.Map<String, java.util.List<String>> roles) {
    this.roles = roles;
    return this;
  }

  public UserAndAssignedRolesV1 putRolesItem(String key, java.util.List<String> rolesItem) {
    if (this.roles == null) {
      this.roles = new java.util.HashMap<String, java.util.List<String>>();
    }
    this.roles.put(key, rolesItem);
    return this;
  }

  /**
   * Get roles
   * @return roles
  **/
  @ApiModelProperty(value = "")

  @Valid

  public java.util.Map<String, java.util.List<String>> getRoles() {
    return roles;
  }

  public void setRoles(java.util.Map<String, java.util.List<String>> roles) {
    this.roles = roles;
  }

  public UserAndAssignedRolesV1 user(AuthUserV1 user) {
    this.user = user;
    return this;
  }

  /**
   * Get user
   * @return user
  **/
  @ApiModelProperty(value = "")

  @Valid

  public AuthUserV1 getUser() {
    return user;
  }

  public void setUser(AuthUserV1 user) {
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
    UserAndAssignedRolesV1 userAndAssignedRolesV1 = (UserAndAssignedRolesV1) o;
    return Objects.equals(this.roles, userAndAssignedRolesV1.roles) &&
        Objects.equals(this.user, userAndAssignedRolesV1.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(roles, user);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserAndAssignedRolesV1 {\n");
    
    sb.append("    roles: ").append(toIndentedString(roles)).append("\n");
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

