package com.vertafore.test.services.customers;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseCustomerTo;
import com.vertafore.test.servicewrappers.UseCustomersTo;
import com.vertafore.test.util.CustomerUtil;
import java.util.List;
import java.util.Random;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class PUT_CustomerBasicInfo extends TokenSuperClass {

  @Test
  public void customerBasicInfoPutsCustomerBasicInfo() {

    // The actors for User, App, and Admin contexts
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    // Services for endpoint methods
    UseCustomerTo customerAPI = new UseCustomerTo();
    UseCustomersTo customersApi = new UseCustomersTo();

    // Models for requests and responses
    CustomerFilterPostRequest customerSearch = new CustomerFilterPostRequest();

    // Send POST search request to capture an existing customer object to edit with PUT endpoint
    AADM_User.attemptsTo(
        customersApi.POSTCustomersSearchOnTheCustomersController(customerSearch, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Page Response containing customer list
    PagingResponseCustomerBasicInfoResponse pageResponseAllCustomers =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseCustomerBasicInfoResponse.class);

    // Grab list of customers from page response
    List<CustomerBasicInfoResponse> allCustomers = pageResponseAllCustomers.getResponse();

    // Random number generator to pick a customer for editing in PUT using size of POST search
    // response
    int customerSelector = new Random().nextInt(allCustomers.size());

    // Capture random customers number
    int customerNumber = allCustomers.get(customerSelector).getCustomerNumber();
    // Capture random customers id
    String customerId = allCustomers.get(customerSelector).getCustomerId();

    // Create updated customer object using id and number from request above
    CustomerBasicInfoPutRequest customerPUT =
        CustomerUtil.updateBasicCustomer(
            customerNumber, customerId, "Suspect", "Individual", AADM_User);

    // Make and send PUT request to update customer

    // Check AdminContext
    VADM_Admin.attemptsTo(
        customerAPI.PUTCustomerBasicInfoOnTheCustomersController(customerPUT, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // Check AppContext
    ORAN_App.attemptsTo(
        customerAPI.PUTCustomerBasicInfoOnTheCustomersController(customerPUT, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Check UserContext
    AADM_User.attemptsTo(
        customerAPI.PUTCustomerBasicInfoOnTheCustomersController(customerPUT, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Send POST Search request for updated Customer
    CustomerBasicInfoResponse customer =
        CustomerUtil.searchForACustomer(customerId, customerNumber, AADM_User);

    // Validate response fields
    CustomerUtil.validateBasicCustomer(customer);
  }
}
