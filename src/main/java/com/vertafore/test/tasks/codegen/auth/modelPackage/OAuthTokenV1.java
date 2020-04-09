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
 * OAuthTokenV1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class OAuthTokenV1   {
  @JsonProperty("accessToken")
  private String accessToken = null;

  @JsonProperty("expiresIn")
  private Integer expiresIn = null;

  @JsonProperty("refreshToken")
  private String refreshToken = null;

  @JsonProperty("tokenType")
  private String tokenType = null;

  public OAuthTokenV1 accessToken(String accessToken) {
    this.accessToken = accessToken;
    return this;
  }

  /**
   * Access token
   * @return accessToken
  **/
  @ApiModelProperty(required = true, value = "Access token")
  @NotNull


  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public OAuthTokenV1 expiresIn(Integer expiresIn) {
    this.expiresIn = expiresIn;
    return this;
  }

  /**
   * Numbers of seconds before token expires
   * @return expiresIn
  **/
  @ApiModelProperty(required = true, value = "Numbers of seconds before token expires")
  @NotNull


  public Integer getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(Integer expiresIn) {
    this.expiresIn = expiresIn;
  }

  public OAuthTokenV1 refreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
    return this;
  }

  /**
   * Refresh token
   * @return refreshToken
  **/
  @ApiModelProperty(required = true, value = "Refresh token")
  @NotNull


  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public OAuthTokenV1 tokenType(String tokenType) {
    this.tokenType = tokenType;
    return this;
  }

  /**
   * Type of token
   * @return tokenType
  **/
  @ApiModelProperty(required = true, value = "Type of token")
  @NotNull


  public String getTokenType() {
    return tokenType;
  }

  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OAuthTokenV1 oauthTokenV1 = (OAuthTokenV1) o;
    return Objects.equals(this.accessToken, oauthTokenV1.accessToken) &&
        Objects.equals(this.expiresIn, oauthTokenV1.expiresIn) &&
        Objects.equals(this.refreshToken, oauthTokenV1.refreshToken) &&
        Objects.equals(this.tokenType, oauthTokenV1.tokenType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accessToken, expiresIn, refreshToken, tokenType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OAuthTokenV1 {\n");
    
    sb.append("    accessToken: ").append(toIndentedString(accessToken)).append("\n");
    sb.append("    expiresIn: ").append(toIndentedString(expiresIn)).append("\n");
    sb.append("    refreshToken: ").append(toIndentedString(refreshToken)).append("\n");
    sb.append("    tokenType: ").append(toIndentedString(tokenType)).append("\n");
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

