package com.vertafore.test.abilities;

import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;

public class HaveALoginKey implements Ability {

  private final String keyType;
  private String loginKey = null;

  private HaveALoginKey(String keyType) {
    this.keyType = keyType;
  }

  public static HaveALoginKey with(String keyType) {
    return new HaveALoginKey(keyType);
  }

  public static HaveALoginKey as(Actor actor) {
    if (actor.abilityTo(HaveALoginKey.class) == null) {
      throw new IllegalArgumentException(
          actor.getName() + "doesn't have the ability to Have A Login Key");
    }
    return actor.abilityTo(HaveALoginKey.class);
  }

  public static String keyTypeForActor(Actor actor) {
    return HaveALoginKey.as(actor).keyType;
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
