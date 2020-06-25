package com.vertafore.test.abilities;

import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;

public class HaveALoginKey implements Ability {

  private final String context;
  private final String version;
  private String loginKey = null;

  private HaveALoginKey(String context, String version) {
    this.context = context;
    this.version = version;
  }

  public static HaveALoginKey with(String context, String version) {
    return new HaveALoginKey(context, version);
  }

  public static HaveALoginKey as(Actor actor) {
    if (actor.abilityTo(HaveALoginKey.class) == null) {
      throw new IllegalArgumentException(
          actor.getName() + "doesn't have the ability to Have A Login Key");
    }
    return actor.abilityTo(HaveALoginKey.class);
  }

  public static String contextForActor(Actor actor) {
    return HaveALoginKey.as(actor).context;
  }

  public static String versionForActor(Actor actor) {
    return HaveALoginKey.as(actor).version;
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
