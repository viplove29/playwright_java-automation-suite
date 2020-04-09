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
 * ChangePasswordRequestV1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class ChangePasswordRequestV1   {
  @JsonProperty("currentPassword")
  private String currentPassword = null;

  @JsonProperty("newPassword")
  private String newPassword = null;

  @JsonProperty("username")
  private String username = null;

  public ChangePasswordRequestV1 currentPassword(String currentPassword) {
    this.currentPassword = currentPassword;
    return this;
  }

  /**
   * Current password of the user
   * @return currentPassword
  **/
  @ApiModelProperty(required = true, value = "Current password of the user")
  @NotNull


  public String getCurrentPassword() {
    return currentPassword;
  }

  public void setCurrentPassword(String currentPassword) {
    this.currentPassword = currentPassword;
  }

  public ChangePasswordRequestV1 newPassword(String newPassword) {
    this.newPassword = newPassword;
    return this;
  }

  /**
   * New password for the user
   * @return newPassword
  **/
  @ApiModelProperty(required = true, value = "New password for the user")
  @NotNull


  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  public ChangePasswordRequestV1 username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Changes the password for this user
   * @return username
  **/
  @ApiModelProperty(required = true, readOnly = true, value = "Changes the password for this user")
  @NotNull


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChangePasswordRequestV1 changePasswordRequestV1 = (ChangePasswordRequestV1) o;
    return Objects.equals(this.currentPassword, changePasswordRequestV1.currentPassword) &&
        Objects.equals(this.newPassword, changePasswordRequestV1.newPassword) &&
        Objects.equals(this.username, changePasswordRequestV1.username);
  }

  @Override
  public int hashCode() {
    return Objects.hash(currentPassword, newPassword, username);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChangePasswordRequestV1 {\n");
    
    sb.append("    currentPassword: ").append(toIndentedString(currentPassword)).append("\n");
    sb.append("    newPassword: ").append(toIndentedString(newPassword)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
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

