package com.vertafore.test.abilities;

import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;

public class Authenticate implements Ability {

  private final String username;
  private final String password;

  private Authenticate(String username, String password) {
    this.username = username;
    this.password = password;
  }

  // used to give actor ability
  public static Authenticate with(String username, String password) {
    return new Authenticate(username, password);
  }

  // returns ability if actor has the ability
  public static Authenticate as(Actor actor) {
    if (actor.abilityTo(Authenticate.class) == null) {
      throw new IllegalArgumentException(actor.getName() + "doesn't have ability to authenticate");
    }
    return actor.abilityTo(Authenticate.class);
  }

  // returns username if actor has the ability
  public static String usernameForAuthenticatedActor(Actor actor) {
    return Authenticate.as(actor).username;
  }

  // returns password if actor has the ability
  public static String passwordForAuthenticatedActor(Actor actor) {
    return Authenticate.as(actor).password;
  }
}
