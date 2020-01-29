package com.vertafore.test.abilities;

import static com.vertafore.test.abilities.HaveAccessToken.theAccessTokenOf;

import java.util.HashMap;
import java.util.Map;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;

public class ConfigureHeaders implements Ability {

  private final Map<String, String> headers;

  private ConfigureHeaders(Map<String, String> headers) {
    this.headers = headers;
  }

  // used to give actor ability
  public static ConfigureHeaders of(Map<String, String> headers) {
    return new ConfigureHeaders(headers);
  }

  public static ConfigureHeaders forVertaforeAuthorizationOf(Actor actor) {
    Map<String, String> map = new HashMap<>();
    if (actor.abilityTo(HaveAccessToken.class) != null) {
      map.put("Vertafore-Authorization", theAccessTokenOf(actor));
    }
    return new ConfigureHeaders(map);
  }

  // returns ability if actor has the ability
  public static ConfigureHeaders as(Actor actor) {
    if (actor.abilityTo(ConfigureHeaders.class) == null) {
      throw new IllegalArgumentException(
          actor.getName() + "doesn't have ability to configure headers");
    }
    return actor.abilityTo(ConfigureHeaders.class);
  }

  // returns tenant Id if actor has ability
  public static Map<String, String> theHeadersOf(Actor actor) {
    return ConfigureHeaders.as(actor).headers;
  }
}
