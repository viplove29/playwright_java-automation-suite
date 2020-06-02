package com.vertafore.test.services.customers;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

import com.vertafore.test.servicewrappers.UseCustomersTo;
import com.vertafore.test.tasks.GetAnAccessToken;
import java.io.IOException;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class CustomerServiceIntegration {
  Actor bob = Actor.named("bob").describedAs("userContext");
  //  Actor doug = Actor.named("doug").describedAs("appContext");
  //  Actor adam = Actor.named("adam").describedAs("adminContext");

  @Before
  public void getAnAccessToken() {
    bob.attemptsTo(GetAnAccessToken.forActor());
    //    doug.attemptsTo(GetAnAccessToken.forActor());
    //    adam.attemptsTo(GetAnAccessToken.forActor());
  }

  @Test
  public void customersReturnsAllCustomers() throws IOException {
    UseCustomersTo customersApi = new UseCustomersTo();

    bob.attemptsTo(customersApi.gETCustomersOnTheCustomersController(null, "string"));
    bob.should(seeThatResponse(res -> res.statusCode(200)));
    //
    //    doug.attemptsTo(customersApi.gETCustomersOnTheCustomersController(null, "string"));
    //    doug.should(seeThatResponse(res -> res.statusCode(200)));
    //
    //    adam.attemptsTo(customersApi.gETCustomersOnTheCustomersController(null, "string"));
    //    adam.should(seeThatResponse("Context is not valid", res -> res.statusCode(403)));
  }

  @Test
  public void customersReturnsOneCustomer() throws IOException {
    UseCustomersTo customersApi = new UseCustomersTo();

    bob.attemptsTo(customersApi.gETCustomersOnTheCustomersController(259, "string"));
    bob.should(seeThatResponse(res -> res.statusCode(200)));
  }
}
