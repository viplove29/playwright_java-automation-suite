package com.vertafore.test.services.customers;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

import com.vertafore.test.actor.BuildEMSCast;
import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.EMSActorBuilder;
import com.vertafore.test.servicewrappers.UseCustomersTo;
import java.io.IOException;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class CustomerServiceIntegration {
  EMSActor bob = new EMSActorBuilder().actorName("bob").context("userContext").buildEMSActor();
  EMSActor doug = new EMSActorBuilder().actorName("doug").context("appContext").buildEMSActor();
  EMSActor adam = new EMSActorBuilder().actorName("adam").context("adminContext").buildEMSActor();

  @Before
  public void getAnAccessToken() {
    OnStage.setTheStage(BuildEMSCast.GetAnAccessToken(bob));
    OnStage.setTheStage(BuildEMSCast.GetAnAccessToken(doug));
    OnStage.setTheStage(BuildEMSCast.GetAnAccessToken(adam));
  }

  @Test
  public void customersReturnsAllCustomers() throws IOException {
    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    UseCustomersTo customersApi = new UseCustomersTo();

    bob.attemptsTo(customersApi.gETCustomersOnTheCustomersController(null, "string"));
    bob.should(seeThatResponse(res -> res.statusCode(200)));

    doug.attemptsTo(customersApi.gETCustomersOnTheCustomersController(null, "string"));
    doug.should(seeThatResponse(res -> res.statusCode(200)));

    adam.attemptsTo(customersApi.gETCustomersOnTheCustomersController(null, "string"));
    adam.should(seeThatResponse("Context is not valid", res -> res.statusCode(403)));
  }

  @Test
  public void customersReturnsOneCustomer() throws IOException {
    Actor bob = theActorCalled("bob");

    UseCustomersTo customersApi = new UseCustomersTo();

    bob.attemptsTo(customersApi.gETCustomersOnTheCustomersController(259, "string"));
    bob.should(seeThatResponse(res -> res.statusCode(200)));
  }
}
