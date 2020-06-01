package com.vertafore.test.services.customers;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

import com.vertafore.test.actor.ems.AuthorizeEMSActor;
import com.vertafore.test.servicewrappers.UseCustomersTo;
import java.io.IOException;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class CustomerServiceIntegration {
  Actor bob = Actor.named("bob");
  private AuthorizeEMSActor appActor = new AuthorizeEMSActor(bob, "appContext");

  Actor doug = Actor.named("doug");
  private AuthorizeEMSActor userActor = new AuthorizeEMSActor(doug, "userContext");

  Actor adam = Actor.named("adam");
  private AuthorizeEMSActor adminActor = new AuthorizeEMSActor(adam, "adminContext");

  @Test
  public void customersReturnsAllCustomers() throws IOException {
    UseCustomersTo customersApi = new UseCustomersTo();

    bob.attemptsTo(customersApi.gETCustomersOnTheCustomersController(null, "string"));

    bob.should(seeThatResponse(res -> res.statusCode(200)));

    doug.attemptsTo(customersApi.gETCustomersOnTheCustomersController(null, "string"));

    doug.should(seeThatResponse(res -> res.statusCode(200)));

    adam.attemptsTo(customersApi.gETCustomersOnTheCustomersController(null, "string"));

    adam.should(seeThatResponse("Context is not valid", res -> res.statusCode(403)));
  }
}
