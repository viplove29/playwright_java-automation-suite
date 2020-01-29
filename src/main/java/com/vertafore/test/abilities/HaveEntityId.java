package com.vertafore.test.abilities;

import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;

public class HaveEntityId implements Ability {

  private final String entityId;

  private HaveEntityId(String entityId) {
    this.entityId = entityId;
  }

  // used to give actor ability
  public static HaveEntityId of(String entityId) {
    return new HaveEntityId(entityId);
  }

  // returns ability if actor has the ability
  public static HaveEntityId as(Actor actor) {
    if (actor.abilityTo(HaveEntityId.class) == null) {
      throw new IllegalArgumentException(
          actor.getName() + "doesn't have ability to have a entity Id");
    }
    return actor.abilityTo(HaveEntityId.class);
  }

  // returns tenant Id if actor has ability
  public static String theEntityIdOf(Actor actor) {
    return HaveEntityId.as(actor).entityId;
  }
}
