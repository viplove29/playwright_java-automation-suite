package com.vertafore.test.services.customers;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.CustomerContactResponse;
import com.vertafore.test.models.ems.CustomerResponse;
import com.vertafore.test.servicewrappers.UseCustomerTo;
import com.vertafore.test.servicewrappers.UseCustomersTo;
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
    bob.should(seeThatResponse("Successfully gets response", res -> res.statusCode(200)));

    doug.attemptsTo(customersApi.GETCustomerContactsOnTheCustomersController(null, null, "string"));
    doug.should(seeThatResponse("Successfully gets response", res -> res.statusCode(200)));

    adam.attemptsTo(customersApi.GETCustomerContactsOnTheCustomersController(null, null, "string"));
    adam.should(seeThatResponse("Context is not valid", res -> res.statusCode(403)));
  }

  @Test
  public void customerContactsReturnsOneCustomerContact() {
    Actor bob = theActorCalled("bob");
    //  Actor doug = theActorCalled("doug");
    //  Actor adam = theActorCalled("adam");

    UseCustomerTo customersApi = new UseCustomerTo();

    // doug.attemptsTo(customersApi.GETCustomerContactsOnTheCustomersController(null, 2, "string"));
    // doug.should(seeThatResponse("Successfully gets response", res -> res.statusCode(200)));

    // adam.attemptsTo(customersApi.GETCustomerContactsOnTheCustomersController(null, 2, "string"));
    // adam.should(seeThatResponse("Context is not valid", res -> res.statusCode(403)));

    bob.attemptsTo(customersApi.GETCustomerContactsOnTheCustomersController(null, 2, "string"));
    bob.should(seeThatResponse("Successfully gets response", res -> res.statusCode(200)));

    SerenityRest.lastResponse().prettyPrint();

    CustomerContactResponse customerContact =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getList("Contact", CustomerContactResponse.class)
            .get(0);

    // Response body format assertions
    assertThat(customerContact != null).isTrue();
    // assertThat(customerContact.getClass().getDeclaredFields().length).isEqualTo(11);

    // Response body field data assertions
    // assertThat(customerContact.getCustId()).isEqualTo("fde4d010-5c17-40b4-941d-850e0d609b24");
    // assertThat(customerContact.getContactType()).isEqualTo(0);
    // assertThat(customerContact.getName()).isEqualTo("Suspect");
  }

  @Test
  @WithTag("19R2")
  public void customersReturnsCustomers19R2() {
    Actor mary = theActorCalled("mary");

    UseCustomersTo customersApi = new UseCustomersTo();

    // Get ALL Customers
    mary.attemptsTo(customersApi.GETCustomersOnTheCustomersController(null, "string"));

    mary.should(seeThatResponse("Successfully gets response", res -> res.statusCode(200)));

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
}
