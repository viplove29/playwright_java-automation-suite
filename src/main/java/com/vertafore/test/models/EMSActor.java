package com.vertafore.test.models;

public class EMSActor {
  private String actorName;
  private String context;
  private String loginType = "";
  private String username = "";
  private String password = "";

  public EMSActor(
      String actorName, String context, String loginType, String username, String password) {
    this.actorName = actorName;
    this.context = context;
    this.loginType = loginType;
    this.username = username;
    this.password = password;
  }

  public EMSActor(String actorName, String context, String loginType) {
    this.actorName = actorName;
    this.context = context;
    this.loginType = loginType;
  }

  public EMSActor(String actorName, String context) {
    this.actorName = actorName;
    this.context = context;
  }

  public EMSActor() {}

  public EMSActor called(String actorName) {
    setActorName(actorName);
    return this;
  }

  public EMSActor withContext(String context) {
    setContext(context);
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

  public String getContext() {
    return context;
  }

  private void setContext(String context) {
    this.context = context;
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
