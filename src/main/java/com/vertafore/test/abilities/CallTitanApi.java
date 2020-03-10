package com.vertafore.test.abilities;

import static com.vertafore.test.abilities.Authenticate.theAuthTokenOf;
import static com.vertafore.test.abilities.HaveTitanContext.theDomainURIOf;
import static com.vertafore.test.abilities.HaveTitanContext.theEntityIdOf;
import static com.vertafore.test.abilities.HaveTitanContext.theProductIdOf;
import static com.vertafore.test.abilities.HaveTitanContext.theTenantIdOf;
import static net.serenitybdd.rest.SerenityRest.rest;

import io.restassured.specification.RequestSpecification;
import java.util.Map;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;

public class CallTitanApi implements Ability {

  private CallTitanApi() {}

  public static CallTitanApi asAuthenticatedUser() {
    return new CallTitanApi();
  }

  public static CallTitanApi as(Actor actor) {
    if (actor.abilityTo(CallTitanApi.class) == null) {
      throw new IllegalArgumentException(
          actor.getName() + "doesn't have ability to call a titan api");
    }
    return actor.abilityTo(CallTitanApi.class);
  }

  public static RequestSpecification asActorUsingService(Actor actor, String thisService) {
    return rest()
        .header("Vertafore-Authorization", theAuthTokenOf(actor))
        .baseUri(theDomainURIOf(actor))
        .basePath("{service}/v1/{product}/{tenant}/entities/{entity}/")
        .pathParams(
            Map.of(
                "service", thisService,
                "product", theProductIdOf(actor),
                "tenant", theTenantIdOf(actor),
                "entity", theEntityIdOf(actor)));
  }
}
