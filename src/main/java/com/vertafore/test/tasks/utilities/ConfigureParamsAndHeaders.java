package com.vertafore.test.tasks.utilities;

import static com.vertafore.test.abilities.ConfigureHeaders.theHeadersOf;
import static com.vertafore.test.abilities.ConfigurePathParams.thePathParamsOf;
import static net.serenitybdd.screenplay.Tasks.instrumented;

import com.vertafore.test.abilities.ConfigureHeaders;
import com.vertafore.test.abilities.ConfigurePathParams;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class ConfigureParamsAndHeaders implements Task {

  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.can(ConfigureHeaders.forVertaforeAuthorizationOf(actor));
    actor.can(ConfigurePathParams.forContextOf(actor));

    RequestSpecification reqSpec =
        new RequestSpecBuilder()
            .addHeaders(theHeadersOf(actor))
            .addPathParams(thePathParamsOf(actor))
            .build();

    SerenityRest.setDefaultRequestSpecification(reqSpec);
  }

  public static ConfigureParamsAndHeaders asUser() {
    return instrumented(ConfigureParamsAndHeaders.class);
  }
}
