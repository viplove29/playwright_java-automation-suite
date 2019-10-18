package com.vertafore.test.common.models.services.auth;

// * The DTO for the content object in the response from the Auth service
// * when trying to authenticate a user or refresh a valid token.

// OAuthTokenV1 in auth service
public class OAuthTokenV1 {
  private String accessToken;
  private Integer expiresIn;
  private String refreshToken;
  private String tokenType;

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public void setExpiresIn(Integer expiresIn) {
    this.expiresIn = expiresIn;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public Integer getExpiresIn() {
    return expiresIn;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public String getTokenType() {
    return tokenType;
  }
}
