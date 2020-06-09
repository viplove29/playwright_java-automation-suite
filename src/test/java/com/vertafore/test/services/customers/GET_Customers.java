package com.vertafore.test.services.customers;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.servicewrappers.UseCustomersTo;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_Customers {
  private List<EMSActor> actors = new ArrayList<>();

  @Before
  public void getAnAccessToken() {
    actors.addAll(
        List.of(
            new EMSActor().called("bob").withContext("userContext"),
            new EMSActor().called("doug").withContext("appContext"),
            new EMSActor().called("adam").withContext("adminContext")));
    OnStage.setTheStage(GetAnAccessToken(actors));
  }

  @Test
  public void customersReturnsAllCustomers() {
    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    UseCustomersTo customersApi = new UseCustomersTo();

    bob.attemptsTo(customersApi.GETCustomersOnTheCustomersController(null, "string"));
    bob.should(seeThatResponse(res -> res.statusCode(200)));

    doug.attemptsTo(customersApi.GETCustomersOnTheCustomersController(null, "string"));
    doug.should(seeThatResponse(res -> res.statusCode(200)));

    adam.attemptsTo(customersApi.GETCustomersOnTheCustomersController(null, "string"));
    adam.should(seeThatResponse("Context is not valid", res -> res.statusCode(403)));
  }

  @Test
  public void customersReturnsOneCustomer() {
    Actor bob = theActorCalled("bob");

    UseCustomersTo customersApi = new UseCustomersTo();

    bob.attemptsTo(customersApi.GETCustomersOnTheCustomersController(259, "string"));
    bob.should(seeThatResponse(res -> res.statusCode(200)));
  }
}
