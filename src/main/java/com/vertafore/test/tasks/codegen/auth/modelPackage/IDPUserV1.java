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
 * IDPUserV1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class IDPUserV1   {
  @JsonProperty("password")
  private String password = null;

  @JsonProperty("username")
  private String username = null;

  public IDPUserV1 password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Password of the user requesting a token
   * @return password
  **/
  @ApiModelProperty(example = "p4ssw0rd!", required = true, value = "Password of the user requesting a token")
  @NotNull


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public IDPUserV1 username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Username of the user requesting a token
   * @return username
  **/
  @ApiModelProperty(example = "username", required = true, value = "Username of the user requesting a token")
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
    IDPUserV1 idPUserV1 = (IDPUserV1) o;
    return Objects.equals(this.password, idPUserV1.password) &&
        Objects.equals(this.username, idPUserV1.username);
  }

  @Override
  public int hashCode() {
    return Objects.hash(password, username);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IDPUserV1 {\n");
    
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
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

