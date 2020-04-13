package com.vertafore.test.abilities;

import static com.vertafore.test.abilities.Authenticate.theAuthTokenOf;
import static com.vertafore.test.abilities.HaveTitanContext.theDomainURIOf;
import static net.serenitybdd.rest.SerenityRest.rest;

import io.restassured.specification.RequestSpecification;
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

  // manages cookies, base URI, and Header for each request.
  public static RequestSpecification asUser(Actor actor) {
    String cookies = System.getProperty("cookies") != null ? System.getProperty("cookies") : "";
    return rest()
        .header("Vertafore-Authorization", theAuthTokenOf(actor))
        .baseUri(theDomainURIOf(actor))
        .cookie(cookies);
  }
}
