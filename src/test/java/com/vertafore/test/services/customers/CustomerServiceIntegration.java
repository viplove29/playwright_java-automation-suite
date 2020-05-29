package com.vertafore.test.services.customers;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.servicewrappers.UseCustomersTo;
import java.io.IOException;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class CustomerServiceIntegration {
  Actor bob = Actor.named("bob");
  private EMSActor actor = new EMSActor(bob, "appContext");

  @Test
  public void customersReturnsAllCustomers() throws IOException {
    UseCustomersTo customersApi = new UseCustomersTo();

    bob.attemptsTo(customersApi.gETCustomersOnTheCustomersController(null, "string"));

    bob.should(seeThatResponse(res -> res.statusCode(200)));
  }
}
