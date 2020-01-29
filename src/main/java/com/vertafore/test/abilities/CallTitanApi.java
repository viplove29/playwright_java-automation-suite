package com.vertafore.test.abilities;

import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;

public class CallTitanApi implements Ability {

  private final String BASE_URL;
  private final String CONTEXT = "{service}/v1/{productId}/{tenantId}/entities/{entityId}/";

  private CallTitanApi(String baseUrl) {
    this.BASE_URL = baseUrl + CONTEXT;
  }

  public static CallTitanApi atBaseUrl(String baseURL) {
    return new CallTitanApi(baseURL);
  }

  public static CallTitanApi as(Actor actor) {
    if (actor.abilityTo(CallTitanApi.class) == null) {
      throw new IllegalArgumentException(
          actor.getName() + "doesn't have ability to call a titan api");
    }
    return actor.abilityTo(CallTitanApi.class);
  }

  // resolve returns the fully hydrated URL ready to be appended by any service url.
  public String toEndpoint(String path) {
    return BASE_URL + path;
  }
}
