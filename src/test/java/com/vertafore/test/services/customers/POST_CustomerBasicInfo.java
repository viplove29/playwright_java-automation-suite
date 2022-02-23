package com.vertafore.test.services.customers;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseCustomerTo;
import com.vertafore.test.util.CustomerUtil;
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
public class POST_CustomerBasicInfo {

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

  /*The purpose of this test is to validate that we can successfully create a new customer
  using the POST customer/basic-info endpoint, then retrieve the newly created entry with
  the POST customer/search endpoint, followed by changing the data for some of the fields
  using PUT customer/basic-info, which lastly gets validated once again using the search
  endpoint to ensure that the field updates have persisted.
   */
  @Test
  public void customerBasicInfoPostsCustomerBasicInfo() {

    // The actors for User, App, and Admin contexts
    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    // Services for endpoint methods
    UseCustomerTo customerAPI = new UseCustomerTo();

    // Models for requests and responses
    CustomerBasicInfoPostRequest customerBody =
        CustomerUtil.createBasicCustomer("Customer", "Family", bob);

    // Send POST customer request

    // Check AdminContext
    adam.attemptsTo(
        customerAPI.POSTCustomerBasicInfoOnTheCustomersController(customerBody, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // Check AppContext
    doug.attemptsTo(
        customerAPI.POSTCustomerBasicInfoOnTheCustomersController(customerBody, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Check UserContext
    bob.attemptsTo(
        customerAPI.POSTCustomerBasicInfoOnTheCustomersController(customerBody, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Grab customerNumber for POST Search
    int customerNumber =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getObject("", CustomerIdResponse.class)
            .getCustomerNumber();

    // Grab customerId for POST Search
    String customerId =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getObject("", CustomerIdResponse.class)
            .getCustomerId();

    // Send POST Search request for created Customer
    CustomerBasicInfoResponse customer =
        CustomerUtil.searchForACustomer(customerId, customerNumber, bob);

    // Validate response fields
    CustomerUtil.validateBasicCustomer(customer);
  }
}
