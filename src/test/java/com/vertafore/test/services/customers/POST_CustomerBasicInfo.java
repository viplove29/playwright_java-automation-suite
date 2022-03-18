package com.vertafore.test.services.customers;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseCustomerTo;
import com.vertafore.test.util.CustomerUtil;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_CustomerBasicInfo extends TokenSuperClass {

  /*The purpose of this test is to validate that we can successfully create a new customer
  using the POST customer/basic-info endpoint, then retrieve the newly created entry with
  the POST customer/search endpoint, followed by changing the data for some of the fields
  using PUT customer/basic-info, which lastly gets validated once again using the search
  endpoint to ensure that the field updates have persisted.
   */
  @Test
  public void customerBasicInfoPostsCustomerBasicInfo() {

    // The actors for User, App, and Admin contexts
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    // Services for endpoint methods
    UseCustomerTo customerAPI = new UseCustomerTo();

    // Models for requests and responses
    CustomerBasicInfoPostRequest customerBody =
        CustomerUtil.createBasicCustomer("Customer", "Family", AADM_User);

    // Send POST customer request

    // Check AdminContext
    VADM_Admin.attemptsTo(
        customerAPI.POSTCustomerBasicInfoOnTheCustomersController(customerBody, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // Check AppContext
    ORAN_App.attemptsTo(
        customerAPI.POSTCustomerBasicInfoOnTheCustomersController(customerBody, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Check UserContext
    AADM_User.attemptsTo(
        customerAPI.POSTCustomerBasicInfoOnTheCustomersController(customerBody, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Grab customerNumber for POST Search
    int customerNumber =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", CustomerIdResponse.class)
            .getCustomerNumber();

    // Grab customerId for POST Search
    String customerId =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", CustomerIdResponse.class)
            .getCustomerId();

    // Send POST Search request for created Customer
    CustomerBasicInfoResponse customer =
        CustomerUtil.searchForACustomer(customerId, customerNumber, AADM_User);

    // Validate response fields
    CustomerUtil.validateBasicCustomer(customer);
  }
}
