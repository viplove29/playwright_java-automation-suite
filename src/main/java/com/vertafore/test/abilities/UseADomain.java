package com.vertafore.test.abilities;

import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;

public class UseADomain implements Ability {

  private final String domain;

  private UseADomain(String domain) {
    this.domain = domain;
  }

  // used to give actor ability
  public static UseADomain of(String domain) {
    return new UseADomain(domain);
  }

  // returns ability if actor has the ability
  public static UseADomain as(Actor actor) {
    if (actor.abilityTo(UseADomain.class) == null) {
      throw new IllegalArgumentException(actor.getName() + "doesn't have ability to use a domain");
    }
    return actor.abilityTo(UseADomain.class);
  }

  // returns tenant Id if actor has ability
  public static String theDomainOf(Actor actor) {
    return UseADomain.as(actor).domain;
  }
}
