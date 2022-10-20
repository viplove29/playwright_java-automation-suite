package com.vertafore.test.services.customers;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseCustomerTo;
import com.vertafore.test.servicewrappers.UseCustomersTo;
import com.vertafore.test.util.CustomerUtil;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_CustomersSearch extends TokenSuperClass {

  @Test
  public void customersSearchBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseCustomersTo customersApi = new UseCustomersTo();

    // fill out the request body
    PagingRequestCustomerFilterPostRequest pagingRequest =
        new PagingRequestCustomerFilterPostRequest();
    CustomerFilterPostRequest postRequest = new CustomerFilterPostRequest();
    postRequest.setIncludeCustomers(true);
    postRequest.setIncludeProspects(true);
    postRequest.setIncludeSuspects(true);
    pagingRequest.setModel(postRequest);

    // send requests
    ORAN_App.attemptsTo(
        customersApi.POSTCustomersSearchOnTheCustomersController(pagingRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        customersApi.POSTCustomersSearchOnTheCustomersController(pagingRequest, "string"));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        customersApi.POSTCustomersSearchOnTheCustomersController(pagingRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // validate response
    PagingResponseCustomerBasicInfoResponse customersBasicInfo =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseCustomerBasicInfoResponse.class);

    assertThat(customersBasicInfo.getTotalCount() >= 0).isTrue();
    assertThat(customersBasicInfo.getResponse().isEmpty()).isFalse();
  }

  @Test
  public void customersSearchByCustomerNameReturnsCustomers() {
    Actor AADM_User = theActorCalled("AADM_User");

    UseCustomersTo customersApi = new UseCustomersTo();

    // Get a random customer
    CustomerBasicInfoResponse customerBasicInfo =
        CustomerUtil.selectRandomCustomer(AADM_User, "all");
    int customerNumber = customerBasicInfo.getCustomerNumber();
    String customerId = customerBasicInfo.getCustomerId();
    String customerName = customerBasicInfo.getFirmName();

    // fill out the request body
    PagingRequestCustomerFilterPostRequest pagingRequest =
        new PagingRequestCustomerFilterPostRequest();
    CustomerFilterPostRequest postRequest = new CustomerFilterPostRequest();
    postRequest.setCustomerName(customerName);
    pagingRequest.setModel(postRequest);

    AADM_User.attemptsTo(
        customersApi.POSTCustomersSearchOnTheCustomersController(pagingRequest, "string"));
    SerenityRest.lastResponse().prettyPrint();
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // validate response
    PagingResponseCustomerBasicInfoResponse customersBasicInfo =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseCustomerBasicInfoResponse.class);

    assertThat(customersBasicInfo.getTotalCount() >= 0).isTrue();
    assertThat(customersBasicInfo.getResponse().isEmpty()).isFalse();
    boolean customerFound = false;
    // iterate through the returned customers and check the updated customer is present
    for (CustomerBasicInfoResponse customerInfo : customersBasicInfo.getResponse()) {
      if (customerInfo.getCustomerId().equals(customerId)
          && customerBasicInfo.getCustomerNumber() == customerNumber) {
        customerFound = true;
        break;
      }
    }
    assertThat(customerFound).isTrue();
  }

  @Test
  public void customersSearchByChangedDateReturnsCustomers() {
    Actor AADM_User = theActorCalled("AADM_User");

    UseCustomersTo customersApi = new UseCustomersTo();
    UseCustomerTo customerApi = new UseCustomerTo();

    // Get a random customer
    CustomerBasicInfoResponse customerBasicInfo =
        CustomerUtil.selectRandomCustomer(AADM_User, "all");
    int customerNumber = customerBasicInfo.getCustomerNumber();
    String customerId = customerBasicInfo.getCustomerId();

    // Create updated customer object using id and number from request above
    CustomerBasicInfoPutRequest customerPUT =
        CustomerUtil.updateBasicCustomer(
            customerNumber, customerId, "Suspect", "Individual", AADM_User);

    // Make and send PUT request to update customer
    AADM_User.attemptsTo(
        customerApi.PUTCustomerBasicInfoOnTheCustomersController(customerPUT, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // fill out the request body
    PagingRequestCustomerFilterPostRequest pagingRequest =
        new PagingRequestCustomerFilterPostRequest();
    CustomerFilterPostRequest postRequest = new CustomerFilterPostRequest();
    postRequest.setIncludeCustomers(false);
    postRequest.setIncludeProspects(false);
    postRequest.setIncludeSuspects(true);
    // set change date to 5 minutes ago.
    DateTime fiveMinsAgo = DateTime.now().minusMinutes(5);
    postRequest.setChangedSince(fiveMinsAgo.toString());
    pagingRequest.setModel(postRequest);

    AADM_User.attemptsTo(
        customersApi.POSTCustomersSearchOnTheCustomersController(pagingRequest, "string"));
    SerenityRest.lastResponse().prettyPrint();
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // validate response
    PagingResponseCustomerBasicInfoResponse customersBasicInfo =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseCustomerBasicInfoResponse.class);

    assertThat(customersBasicInfo.getTotalCount() >= 0).isTrue();
    assertThat(customersBasicInfo.getResponse().isEmpty()).isFalse();
    boolean customerFound = false;
    // iterate through the returned customers and check the updated customer is present
    for (CustomerBasicInfoResponse customerInfo : customersBasicInfo.getResponse()) {
      if (customerInfo.getCustomerId().equals(customerId)
          && customerBasicInfo.getCustomerNumber() == customerNumber) {
        customerFound = true;
        break;
      }
    }
    assertThat(customerFound).isTrue();
  }
}
