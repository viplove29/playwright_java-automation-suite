package com.vertafore.test.models;

public class EMSActor {
  private String actorName;
  private String context;
  private String loginType;

  public EMSActor(String actorName, String context, String loginType) {
    this.actorName = actorName;
    this.context = context;
    this.loginType = loginType;
  }

  public EMSActor(String actorName, String context) {
    this.actorName = actorName;
    this.context = context;
  }

  public String getActorName() {
    return actorName;
  }

  public void setActorName(String actorName) {
    this.actorName = actorName;
  }

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  public String getLoginType() {
    return loginType;
  }

  public void setLoginType(String loginType) {
    this.loginType = loginType;
  }
}
