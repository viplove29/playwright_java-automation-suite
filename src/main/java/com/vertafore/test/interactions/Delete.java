package com.vertafore.test.interactions;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.rest.abilities.CallAnApi.as;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.interactions.RestInteraction;
import net.thucydides.core.annotations.Step;

/**
 * this class overwrites the Delete from serenity-screenplay-rest to add a 'to' method so that it is
 * standardized and all verbs can go Verb.to()
 */
public class Delete extends RestInteraction {

  private final String resource;

  public Delete(String resource) {
    this.resource = resource;
  }

  @Step("{0} executes a DELETE on the resource #resource")
  @Override
  public <T extends Actor> void performAs(T actor) {
    rest().delete(as(actor).resolve(resource));
  }

  public static Delete from(String resource) {
    return instrumented(Delete.class, resource);
  }

  public static Delete to(String resource) {
    return instrumented(Delete.class, resource);
  }
}
