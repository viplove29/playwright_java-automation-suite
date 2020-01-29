package com.vertafore.test.abilities;

import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;

public class HaveTenantId implements Ability {

  private final String tenantId;

  private HaveTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

  // used to give actor ability
  public static HaveTenantId of(String tenantId) {
    return new HaveTenantId(tenantId);
  }

  // returns ability if actor has the ability
  public static HaveTenantId as(Actor actor) {
    if (actor.abilityTo(HaveTenantId.class) == null) {
      throw new IllegalArgumentException(
          actor.getName() + "doesn't have ability to have a tenant Id");
    }
    return actor.abilityTo(HaveTenantId.class);
  }

  // returns tenant Id if actor has ability
  public static String theTenantIdOf(Actor actor) {
    return HaveTenantId.as(actor).tenantId;
  }
}
