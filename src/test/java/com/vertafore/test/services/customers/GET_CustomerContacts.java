package com.vertafore.test.services.customers;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.CustomerContactResponse;
import com.vertafore.test.servicewrappers.UseCustomerTo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_CustomerContacts {
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

  // helpers

  public static String randCustomerId = "";
  public static int randCustomerNumber = 0;
  public static String randCustomerName = "";

  Random random = new Random();
  int randomInt = random.nextInt(100);

  @Test
  public void customerContactsReturnsAllCustomerContacts() {
    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    UseCustomerTo customersApi = new UseCustomerTo();

    doug.attemptsTo(customersApi.GETCustomerContactsOnTheCustomersController(null, null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    adam.attemptsTo(customersApi.GETCustomerContactsOnTheCustomersController(null, null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    bob.attemptsTo(customersApi.GETCustomerContactsOnTheCustomersController(null, null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<CustomerContactResponse> customerContacts =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getList("", CustomerContactResponse.class);

    assertThat(customerContacts.get(0).getClass().getDeclaredFields().length).isEqualTo(11);
    assertThat(customerContacts.size()).isGreaterThan(0);

    randCustomerId = customerContacts.get(randomInt).getCustId();
    randCustomerNumber = customerContacts.get(randomInt).getCustNo();
    randCustomerName = customerContacts.get(randomInt).getName();
  }

  @Test
  public void customerContactsReturnsOneCustomerContact() {
    Actor bob = theActorCalled("bob");

    UseCustomerTo customersApi = new UseCustomerTo();

    bob.attemptsTo(
        customersApi.GETCustomerContactsOnTheCustomersController(
            randCustomerId, randCustomerNumber, "string"));

    CustomerContactResponse customerContact =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getList("", CustomerContactResponse.class)
            .get(0);

    assertThat(customerContact.getCustId()).isEqualTo(randCustomerId);
    assertThat(customerContact.getCustNo()).isEqualTo(randCustomerNumber);
    assertThat(customerContact.getName()).isEqualTo(randCustomerName);
  }
}
