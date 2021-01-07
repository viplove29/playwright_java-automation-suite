package com.vertafore.test.services.customers;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.CustomerContactResponse;
import com.vertafore.test.servicewrappers.UseCustomerTo;
import io.restassured.path.json.JsonPath;
import java.util.ArrayList;
import java.util.List;
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
            new EMSActor().called("adam").withContext("adminContext"),
            new EMSActor().called("mary").withVersion("19R2")));
    OnStage.setTheStage(GetAnAccessToken(actors));
  }

  @Test
  public void customerContactsReturnsAllCustomerContacts() {
    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    UseCustomerTo customersApi = new UseCustomerTo();

    bob.attemptsTo(customersApi.GETCustomerContactsOnTheCustomersController(null, null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    doug.attemptsTo(customersApi.GETCustomerContactsOnTheCustomersController(null, null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    adam.attemptsTo(customersApi.GETCustomerContactsOnTheCustomersController(null, null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }

  @Test
  public void customerContactsReturnsOneCustomerContact() {
    Actor bob = theActorCalled("bob");

    UseCustomerTo customersApi = new UseCustomerTo();

    bob.attemptsTo(
        customersApi.GETCustomerContactsOnTheCustomersController(
            "e9b9a2a4-356c-436b-a298-1b96a6eca43b", 5, "string"));

    JsonPath jsonPath = LastResponse.received().answeredBy(bob).getBody().jsonPath();

    CustomerContactResponse customerContact =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getList("", CustomerContactResponse.class)
            .get(0);

    assertThat(customerContact.getCustId()).isEqualTo("e9b9a2a4-356c-436b-a298-1b96a6eca43b");
    assertThat(customerContact.getCustNo()).isEqualTo(5);
    assertThat(customerContact.getName()).isEqualTo("ForGridExport");
  }
}
