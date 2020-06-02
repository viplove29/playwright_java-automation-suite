package com.vertafore.test.abilities;

import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;

public class HaveALoginKey implements Ability {

  private String loginKey = null;

  public static HaveALoginKey with() {
    return new HaveALoginKey();
  }

  public static HaveALoginKey as(Actor actor) {
    if (actor.abilityTo(HaveALoginKey.class) == null) {
      throw new IllegalArgumentException(
          actor.getName() + "doesn't have the ability to Have A Login Key");
    }
    return actor.abilityTo(HaveALoginKey.class);
  }

  private void setLoginKey(String newLoginKey) {
    this.loginKey = newLoginKey;
  }

  public static void theNewLoginKeyOf(Actor actor, String newLoginKey) {
    HaveALoginKey.as(actor).setLoginKey(newLoginKey);
  }

  public static String theLoginKeyOf(Actor actor) {
    return HaveALoginKey.as(actor).loginKey;
  }
}
