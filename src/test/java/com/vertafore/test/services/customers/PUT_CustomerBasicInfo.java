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
public class PUT_CustomerBasicInfo {

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
  public void customerBasicInfoPutsCustomerBasicInfo() {

    // The actors for User, App, and Admin contexts
    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    // Services for endpoint methods
    UseCustomerTo customerAPI = new UseCustomerTo();
    UseCustomersTo customersApi = new UseCustomersTo();
    CreateUpdateCustomer customerCreator = new CreateUpdateCustomer();

    // Random number generator to pick a customer for editing in PUT
    Integer customerSelector = new Random().nextInt(5);

    // Models for requests and responses
    CustomerFilterPostRequest customerSearch = new CustomerFilterPostRequest();
    PagingRequestCustomerFilterPostRequest pageSearch =
        new PagingRequestCustomerFilterPostRequest();

    // Send request to capture an existing customer object to edit with PUT endpoint
    bob.attemptsTo(
        customerAPI.GETCustomerOnTheCustomersController(null, customerSelector, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Capture random customers number
    Integer customerNumber =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getObject("", CustomerIdResponse.class)
            .getCustomerNumber();

    // Capture random customers id
    String customerId =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getObject("", CustomerIdResponse.class)
            .getCustomerId();

    // Create updated customer object using id and number from request above
    CustomerBasicInfoPutRequest customerPUT =
        customerCreator.updateBasicCustomer(
            customerNumber, customerId, "Suspect", "Individual", bob);

    // Make and send PUT request to update customer

    // Check AdminContext
    adam.attemptsTo(
        customerAPI.PUTCustomerBasicInfoOnTheCustomersController(customerPUT, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // TODO comment this back in once the Service User Application Lock defect is completed
    // (DE27864)
    // Check AppContext
    //    doug.attemptsTo(
    //        customerAPI.PUTCustomerBasicInfoOnTheCustomersController(customerPUT, "string"));
    //    SerenityRest.lastResponse().prettyPrint();
    //    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Check UserContext
    bob.attemptsTo(customerAPI.PUTCustomerBasicInfoOnTheCustomersController(customerPUT, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Create POST object for customer search
    customerSearch.setCustomerNumber(customerNumber);
    customerSearch.setCustomerId(customerId);
    pageSearch.setModel(customerSearch);

    // Send out new POST customer search to get updated PUT fields
    bob.attemptsTo(customersApi.POSTCustomersSearchOnTheCustomersController(pageSearch, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Capture POST Customer search response with updated PUT fields
    PagingResponseCustomerBasicInfoResponse pageResponse =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseCustomerBasicInfoResponse.class);

    // Get first index of response to access customer
    CustomerBasicInfoResponse customer = pageResponse.getResponse().get(0);

    // Response Validations for PUT
    assertThat(customer != null).isTrue();
    assertThat(customer.getClass().getDeclaredFields().length).isEqualTo(22);
    assertThat(customer.getCustomerNumber()).isEqualTo(customerNumber);
    assertThat(customer.getCustomerId()).isEqualTo(customerId);
    assertThat(customer.getCustomerType()).isEqualTo(customerPUT.getCustomerType());
    assertThat(customer.getFullName()).isEqualTo(customerPUT.getCustomerName().getFirmName());
    assertThat(customer.getFirstName()).isEqualTo(customerPUT.getCustomerName().getFirstName());
    assertThat(customer.getMiddleName()).isEqualTo(customerPUT.getCustomerName().getMiddleName());
    assertThat(customer.getLastName()).isEqualTo(customerPUT.getCustomerName().getLastName());
    assertThat(customer.getAddressLine1())
        .isEqualTo(customerPUT.getCustomerAddress().getAddressLine1());
    assertThat(customer.getAddressLine2())
        .isEqualTo(customerPUT.getCustomerAddress().getAddressLine2());
    assertThat(customer.getZipCode().replaceAll("[\\D]", ""))
        .isEqualTo(customerPUT.getCustomerAddress().getZipCode().replaceAll("[\\D]", ""));
    assertThat(customer.getCity()).isEqualTo(customerPUT.getCustomerAddress().getCity());
    assertThat(customer.getState()).isEqualTo(customerPUT.getCustomerAddress().getState());
    assertThat(customer.getCountry()).isEqualTo(customerPUT.getCustomerAddress().getCountry());
    assertThat(customer.getPrimaryEmail()).isEqualTo(customerPUT.getPrimaryEmail());
    assertThat(customer.getSecondaryEmail()).isEqualTo(customerPUT.getSecondaryEmail());
    assertThat(customer.getResidencePhone().replaceAll("[\\D]", ""))
        .isEqualTo(customerPUT.getPhoneNumbers().getResidencePhone().replaceAll("[\\D]", ""));
    assertThat(customer.getBusinessPhone().replaceAll("[\\D]", ""))
        .isEqualTo(customerPUT.getPhoneNumbers().getBusinessPhone().replaceAll("[\\D]", ""));
    assertThat(customer.getMobilePhone().replaceAll("[\\D]", ""))
        .isEqualTo(customerPUT.getPhoneNumbers().getCell().replaceAll("[\\D]", ""));
  }
}
