package com.vertafore.test.abilities;

import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;

public class HaveAccessToken implements Ability {

  private final String accessToken;

  private HaveAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  // used to give actor ability
  public static HaveAccessToken of(String accessToken) {
    return new HaveAccessToken(accessToken);
  }

  // returns ability if actor has the ability
  public static HaveAccessToken as(Actor actor) {
    if (actor.abilityTo(HaveAccessToken.class) == null) {
      throw new IllegalArgumentException(
          actor.getName() + "doesn't have ability to have a tenant Id");
    }
    return actor.abilityTo(HaveAccessToken.class);
  }

  // returns tenant Id if actor has ability
  public static String theAccessTokenOf(Actor actor) {
    return HaveAccessToken.as(actor).accessToken;
  }
}
