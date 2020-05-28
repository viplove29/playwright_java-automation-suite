package com.vertafore.test.abilities;

import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;

public class HaveAnAccessToken implements Ability {

  private String accessJwt = null;

  public static HaveAnAccessToken with() {
    return new HaveAnAccessToken();
  }

  public static HaveAnAccessToken as(Actor actor) {
    if (actor.abilityTo(HaveAnAccessToken.class) == null) {
      throw new IllegalArgumentException(
          actor.getName() + "doesn't have the ability to Have A Token");
    }
    return actor.abilityTo(HaveAnAccessToken.class);
  }

  private void setAccessToken(String newAccessToken) {
    this.accessJwt = newAccessToken;
  }

  public static void theNewAccessTokenOf(Actor actor, String newAccessToken) {
    HaveAnAccessToken.as(actor).setAccessToken(newAccessToken);
  }

  public static String theAccessTokenOf(Actor actor) {
    return HaveAnAccessToken.as(actor).accessJwt;
  }
}
