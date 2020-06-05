package com.vertafore.test.models;

public class EMSActor {
  private String actorName;
  private String context;
  private String loginType = "Native";

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

  public String getActorName() {
    return actorName;
  }

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

  private EMSActor setActorName(String actorName) {
    this.actorName = actorName;
    return this;
  }

  public String getContext() {
    return context;
  }

  private EMSActor setContext(String context) {
    this.context = context;
    return this;
  }

  public String getLoginType() {
    return loginType;
  }

  private EMSActor setLoginType(String loginType) {
    this.loginType = loginType;
    return this;
  }
}
