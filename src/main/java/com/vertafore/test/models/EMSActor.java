package com.vertafore.test.models;

public class EMSActor {
  private String actorName;
  private String keyType = "";
  private String loginPath = "";
  private String loginType = "";
  private String username = "";
  private String password = "";

  public EMSActor(
      String actorName,
      String keyType,
      String loginPath,
      String loginType,
      String username,
      String password) {
    this.actorName = actorName;
    this.keyType = keyType;
    this.loginPath = loginPath;
    this.loginType = loginType;
    this.username = username;
    this.password = password;
  }

  public EMSActor() {}

  public EMSActor called(String actorName) {
    setActorName(actorName);
    return this;
  }

  public EMSActor withKeyType(String keyType) {
    setKeyType(keyType);
    return this;
  }

  public EMSActor withLoginPath(String loginPath) {
    setLoginPath(loginPath);
    return this;
  }

  public EMSActor withLoginType(String loginType) {
    setLoginType(loginType);
    return this;
  }

  public EMSActor withUsername(String username) {
    setUsername(username);
    return this;
  }

  public EMSActor withPassword(String password) {
    setPassword(password);
    return this;
  }

  public String getActorName() {
    return actorName;
  }

  private void setActorName(String actorName) {
    this.actorName = actorName;
  }

  public String getKeyType() {
    return keyType;
  }

  private void setKeyType(String keyType) {
    this.keyType = keyType;
  }

  public String getLoginPath() {
    return loginPath;
  }

  private void setLoginPath(String loginPath) {
    this.loginPath = loginPath;
  }

  public String getLoginType() {
    return loginType;
  }

  private void setLoginType(String loginType) {
    this.loginType = loginType;
  }

  public String getUsername() {
    return username;
  }

  private void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  private void setPassword(String password) {
    this.password = password;
  }
}
