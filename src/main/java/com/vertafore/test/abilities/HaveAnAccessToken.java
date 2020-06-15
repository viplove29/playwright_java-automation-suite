package com.vertafore.test.abilities;

import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;

public class HaveAnAccessToken implements Ability {

  private final String loginType;
  private final String username;
  private final String password;
  private String accessJwt = null;

  private HaveAnAccessToken(String loginType, String username, String password) {
    this.loginType = loginType;
    this.username = username;
    this.password = password;
  }

  public static HaveAnAccessToken with(String loginType, String username, String password) {
    return new HaveAnAccessToken(loginType, username, password);
  }

  public static HaveAnAccessToken as(Actor actor) {
    if (actor.abilityTo(HaveAnAccessToken.class) == null) {
      throw new IllegalArgumentException(
          actor.getName() + "doesn't have the ability to Have A Token");
    }
    return actor.abilityTo(HaveAnAccessToken.class);
  }

  public static String loginTypeForActor(Actor actor) {
    return HaveAnAccessToken.as(actor).loginType;
  }

  public static String usernameForActor(Actor actor) {
    return HaveAnAccessToken.as(actor).username;
  }

  public static String passwordForActor(Actor actor) {
    return HaveAnAccessToken.as(actor).password;
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
