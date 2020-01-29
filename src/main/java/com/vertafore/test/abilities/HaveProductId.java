package com.vertafore.test.abilities;

import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;

public class HaveProductId implements Ability {
  private final String productId;

  private HaveProductId(String productId) {
    this.productId = productId;
  }

  // used to give actor ability
  public static HaveProductId of(String productId) {
    return new HaveProductId(productId);
  }

  // returns ability if actor has the ability
  public static HaveProductId as(Actor actor) {
    if (actor.abilityTo(HaveProductId.class) == null) {
      throw new IllegalArgumentException(
          actor.getName() + "doesn't have ability to have a product Id");
    }
    return actor.abilityTo(HaveProductId.class);
  }

  // returns tenant Id if actor has ability
  public static String theProductIdOf(Actor actor) {
    return HaveProductId.as(actor).productId;
  }
}
