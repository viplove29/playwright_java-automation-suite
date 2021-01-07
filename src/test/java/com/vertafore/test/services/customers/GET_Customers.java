package com.vertafore.test.services.customers;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.CustomerResponse;
import com.vertafore.test.servicewrappers.UseCustomersTo;
import io.restassured.path.json.JsonPath;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import net.thucydides.core.annotations.WithTag;
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
            new EMSActor().called("adam").withContext("adminContext"),
            new EMSActor().called("mary").withContext("userContext").withVersion("19R2")));
    OnStage.setTheStage(GetAnAccessToken(actors));
  }

  @Test
  public void customersReturnsAllCustomers() {
    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    UseCustomersTo customersApi = new UseCustomersTo();

    bob.attemptsTo(customersApi.GETCustomersOnTheCustomersController(null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    doug.attemptsTo(customersApi.GETCustomersOnTheCustomersController(null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    adam.attemptsTo(customersApi.GETCustomersOnTheCustomersController(null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }

  @Test
  public void customersReturnsOneCustomer() {
    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    UseCustomersTo customersApi = new UseCustomersTo();

    doug.attemptsTo(customersApi.GETCustomersOnTheCustomersController(186, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    adam.attemptsTo(customersApi.GETCustomersOnTheCustomersController(186, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    bob.attemptsTo(customersApi.GETCustomersOnTheCustomersController(186, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    JsonPath jsonPath = LastResponse.received().answeredBy(bob).getBody().jsonPath();

    SerenityRest.lastResponse().prettyPrint();

    CustomerResponse customer =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getList("customerList", CustomerResponse.class)
            .get(0);

    // Response body format assertions
    assertThat(customer != null).isTrue();
    assertThat(customer.getClass().getDeclaredFields().length).isEqualTo(10);

    // Response body field data assertions
    assertThat(customer.getCustId()).isEqualTo("2759ce42-fc31-414c-968d-09b0a80f04c6");

    assertThat(customer.getSortName()).isEqualTo("Yronwood of Yronwood");

    assertThat(customer.getContactName()).isEqualTo("Lanelle Tremblay");

    assertThat(customer.getFirmName()).isEqualTo("Yronwood of Yronwood");

    assertThat(customer.getPrimaryEmail()).isEqualTo("reed.watsica@gmail.com");

    assertThat(customer.getSecondaryEmail()).isEqualTo("manie.koepp@hotmail.com");

    assertThat(customer.getCustNo()).isEqualTo(186);

    assertThat(customer.getZipCode()).isEqualTo("80011");
  }

  @Test
  @WithTag("19R2")
  public void customersReturnsCustomers19R2() {
    Actor mary = theActorCalled("mary");

    UseCustomersTo customersApi = new UseCustomersTo();

    // Get ALL Customers
    mary.attemptsTo(customersApi.GETCustomersOnTheCustomersController(null, "string"));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Get Customer by Customer Number
    mary.attemptsTo(customersApi.GETCustomersOnTheCustomersController(186, "string"));

    CustomerResponse customer =
        LastResponse.received()
            .answeredBy(mary)
            .getBody()
            .jsonPath()
            .getList("customerList", CustomerResponse.class)
            .get(0);

    // Response body format assertions
    assertThat(customer != null).isTrue();
    assertThat(customer.getClass().getDeclaredFields().length).isEqualTo(10);

    // Response body field data assertions
    assertThat(customer.getCustId()).isEqualTo("2759ce42-fc31-414c-968d-09b0a80f04c6");

    assertThat(customer.getSortName()).isEqualTo("Yronwood of Yronwood");

    assertThat(customer.getContactName()).isEqualTo("Lanelle Tremblay");

    assertThat(customer.getFirmName()).isEqualTo("Yronwood of Yronwood");

    assertThat(customer.getPrimaryEmail()).isEqualTo("reed.watsica@gmail.com");

    assertThat(customer.getSecondaryEmail()).isEqualTo("manie.koepp@hotmail.com");

    assertThat(customer.getCustNo()).isEqualTo(186);

    assertThat(customer.getZipCode()).isEqualTo("80011");
  }

  @Test
  public void customerByPhoneReturnsCustomerByPhone() {
    Actor bob = theActorCalled("bob");

    UseCustomersTo customersApi = new UseCustomersTo();

    bob.attemptsTo(
        customersApi.GETCustomersContactPhoneOnTheCustomersController("4111600767", "string"));

    CustomerResponse customer =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getList("customerList", CustomerResponse.class)
            .get(0);

    assertThat(customer != null).isTrue();
  }
}
