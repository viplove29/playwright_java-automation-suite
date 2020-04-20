package com.vertafore.test.interactions;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.rest.abilities.CallAnApi.as;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.interactions.RestInteraction;
import net.thucydides.core.annotations.Step;

/**
 * Perform a GET query on a given REST resource
 *
 * <p>this class overwrites the Get from serenity-screenplay-rest to add a 'to' method so that it is
 * standardized and all verbs can go Verb.to()
 */
public class Get extends RestInteraction {

  private final String resource;

  public Get(String resource) {
    this.resource = resource;
  }

  @Step("{0} executes a GET on the resource #resource")
  @Override
  public <T extends Actor> void performAs(T actor) {
    rest().get(as(actor).resolve(resource));
  }

  public static Get resource(String resource) {
    return instrumented(Get.class, resource);
  }

  public static Get to(String resource) {
    return instrumented(Get.class, resource);
  }
}
