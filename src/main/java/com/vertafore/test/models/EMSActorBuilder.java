package com.vertafore.test.models;

public class EMSActorBuilder {
  private String _actorName;
  private String _context;
  private String _loginType = "Native";

  public EMSActorBuilder() {}

  public EMSActor buildEMSActor() {
    return new EMSActor(_actorName, _context, _loginType);
  }

  public EMSActorBuilder actorName(String _actorName) {
    this._actorName = _actorName;
    return this;
  }

  public EMSActorBuilder context(String _context) {
    this._context = _context;
    return this;
  }

  public EMSActorBuilder loginType(String _loginType) {
    this._loginType = _loginType;
    return this;
  }
}
