package com.vertafore.test.abilities;

import static com.vertafore.test.abilities.HaveEntityId.theEntityIdOf;
import static com.vertafore.test.abilities.HaveProductId.theProductIdOf;
import static com.vertafore.test.abilities.HaveTenantId.theTenantIdOf;
import static com.vertafore.test.abilities.UseAService.theServiceOf;

import java.util.HashMap;
import java.util.Map;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;

public class ConfigurePathParams implements Ability {

  private final Map<String, String> pathParams;

  private ConfigurePathParams(Map<String, String> pathParams) {
    this.pathParams = pathParams;
  }

  // used to give actor ability
  public static ConfigurePathParams of(Map<String, String> pathParams) {
    return new ConfigurePathParams(pathParams);
  }

  public static ConfigurePathParams forContextOf(Actor actor) {
    Map<String, String> map = new HashMap<>();
    // if statements will skip adding the path param if actor doesnt have ability
    if (actor.abilityTo(HaveProductId.class) != null) {
      map.put("productId", theProductIdOf(actor));
    }
    if (actor.abilityTo(HaveEntityId.class) != null) {
      map.put("entityId", theEntityIdOf(actor));
    }
    if (actor.abilityTo(HaveTenantId.class) != null) {
      map.put("tenantId", theTenantIdOf(actor));
    }
    if (actor.abilityTo(UseAService.class) != null) {
      map.put("service", theServiceOf(actor));
    }
    return new ConfigurePathParams(map);
  }

  // returns ability if actor has the ability
  public static ConfigurePathParams as(Actor actor) {
    if (actor.abilityTo(ConfigurePathParams.class) == null) {
      throw new IllegalArgumentException(
          actor.getName() + "doesn't have ability to configure path params");
    }
    return actor.abilityTo(ConfigurePathParams.class);
  }

  // returns tenant Id if actor has ability
  public static Map<String, String> thePathParamsOf(Actor actor) {
    return ConfigurePathParams.as(actor).pathParams;
  }
}
