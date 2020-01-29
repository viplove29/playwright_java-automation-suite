package com.vertafore.test.abilities;

import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;

public class UseAService implements Ability {

  private final String service;

  private UseAService(String service) {
    this.service = service;
  }

  // used to give actor ability
  public static UseAService called(String service) {
    return new UseAService(service);
  }

  // returns ability if actor has the ability
  public static UseAService as(Actor actor) {
    if (actor.abilityTo(UseAService.class) == null) {
      throw new IllegalArgumentException(actor.getName() + "doesn't have ability to use a service");
    }
    return actor.abilityTo(UseAService.class);
  }

  // returns tenant Id if actor has ability
  public static String theServiceOf(Actor actor) {
    return UseAService.as(actor).service;
  }
}
