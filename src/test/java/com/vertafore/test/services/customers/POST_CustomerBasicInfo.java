package com.vertafore.test.services.customers;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseCustomerTo;
import com.vertafore.test.servicewrappers.UseCustomersTo;
import com.vertafore.test.util.CreateUpdateCustomer;
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
    UseCustomersTo customersAPI = new UseCustomersTo();
    CreateUpdateCustomer customerCreator = new CreateUpdateCustomer();

    // Models for requests and responses
    CustomerBasicInfoPostRequest customerBody =
        customerCreator.createBasicCustomer("Customer", "Family", bob);
    CustomerFilterPostRequest customerSearch = new CustomerFilterPostRequest();
    PagingRequestCustomerFilterPostRequest pageSearch =
        new PagingRequestCustomerFilterPostRequest();

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
    Integer customerNumber =
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

    // Create POST object for customer search
    customerSearch.setCustomerNumber(customerNumber);
    customerSearch.setCustomerId(customerId);
    pageSearch.setModel(customerSearch);

    // Send POST Search request for created Customer

    // Check AdminContext
    adam.attemptsTo(
        customersAPI.POSTCustomersSearchOnTheCustomersController(customerSearch, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // Check AppContext
    doug.attemptsTo(
        customersAPI.POSTCustomersSearchOnTheCustomersController(customerSearch, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Check UserContext
    bob.attemptsTo(customersAPI.POSTCustomersSearchOnTheCustomersController(pageSearch, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Capture POST Customer search response
    PagingResponseCustomerBasicInfoResponse pageResponse =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseCustomerBasicInfoResponse.class);

    // Get first index of response to access customer
    CustomerBasicInfoResponse customer = pageResponse.getResponse().get(0);

    // Response validations for POST search
    assertThat(customer != null).isTrue();
    assertThat(customer.getClass().getDeclaredFields().length).isEqualTo(22);
    assertThat(customer.getCustomerNumber()).isEqualTo(customerNumber);
    assertThat(customer.getCustomerId()).isEqualTo(customerId);
    assertThat(customer.getCustomerType()).isEqualTo(customerBody.getCustomerType());
    assertThat(customer.getFullName()).isEqualTo(customerBody.getCustomerName().getFirmName());
    assertThat(customer.getFirstName()).isEqualTo(customerBody.getCustomerName().getFirstName());
    assertThat(customer.getMiddleName()).isEqualTo(customerBody.getCustomerName().getMiddleName());
    assertThat(customer.getLastName()).isEqualTo(customerBody.getCustomerName().getLastName());
    assertThat(customer.getAddressLine1())
        .isEqualTo(customerBody.getCustomerAddress().getAddressLine1());
    assertThat(customer.getAddressLine2())
        .isEqualTo(customerBody.getCustomerAddress().getAddressLine2());
    assertThat(customer.getZipCode().replaceAll("[\\D]", ""))
        .isEqualTo(customerBody.getCustomerAddress().getZipCode().replaceAll("[\\D]", ""));
    assertThat(customer.getCity()).isEqualTo(customerBody.getCustomerAddress().getCity());
    assertThat(customer.getState()).isEqualTo(customerBody.getCustomerAddress().getState());
    assertThat(customer.getCountry()).isEqualTo(customerBody.getCustomerAddress().getCountry());
    assertThat(customer.getPrimaryEmail()).isEqualTo(customerBody.getPrimaryEmail());
    assertThat(customer.getSecondaryEmail()).isEqualTo(customerBody.getSecondaryEmail());
    assertThat(customer.getResidencePhone().replaceAll("[\\D]", ""))
        .isEqualTo(customerBody.getPhoneNumbers().getResidencePhone().replaceAll("[\\D]", ""));
    assertThat(customer.getBusinessPhone().replaceAll("[\\D]", ""))
        .isEqualTo(customerBody.getPhoneNumbers().getBusinessPhone().replaceAll("[\\D]", ""));
    assertThat(customer.getMobilePhone().replaceAll("[\\D]", ""))
        .isEqualTo(customerBody.getPhoneNumbers().getCell().replaceAll("[\\D]", ""));
  }
}
