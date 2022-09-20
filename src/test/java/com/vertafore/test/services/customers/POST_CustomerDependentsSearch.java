package com.vertafore.test.services.customers;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseCustomersTo;
import com.vertafore.test.util.CustomerUtil;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_CustomerDependentsSearch extends TokenSuperClass {

  @Test
  public void customerDependentsSearchWithCustomerIdReturnsOneCustomerDependent() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseCustomersTo customersApi = new UseCustomersTo();

    /* Get all customers' contacts and dependents and select a random
    customer that has at least one customer dependent */
    CustomerContactDependentResponse randomCustomerWithDependents =
        CustomerUtil.getRandomCustomerWithDependents(AADM_User, null);

    String randCustomerId = randomCustomerWithDependents.getCustomerId();

    // fill out the request body
    PagingRequestCustomerDependentsFilterPostRequest pagingRequest =
        new PagingRequestCustomerDependentsFilterPostRequest();
    CustomerDependentsFilterPostRequest postRequest = new CustomerDependentsFilterPostRequest();
    postRequest.setCustomerId(randCustomerId);
    pagingRequest.setModel(postRequest);

    // send requests
    ORAN_App.attemptsTo(
        customersApi.POSTCustomersDependentsSearchOnTheCustomersController(
            pagingRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        customersApi.POSTCustomersDependentsSearchOnTheCustomersController(
            pagingRequest, "string"));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        customersApi.POSTCustomersDependentsSearchOnTheCustomersController(
            pagingRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // validate response
    PagingResponseCustomerDependentResponse customerDependents =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseCustomerDependentResponse.class);

    assertThat(customerDependents.getResponse().get(0).getCustomerId()).isEqualTo(randCustomerId);
  }

  @Test
  public void customerDependentsSearchWithCustomerNumberReturnsOneCustomerDependent() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseCustomersTo customersApi = new UseCustomersTo();

    /* Get a random customer that has at least one customer dependent */
    CustomerContactDependentResponse randomCustomerWithDependents =
        CustomerUtil.getRandomCustomerWithDependents(AADM_User, null);

    String randCustomerId = randomCustomerWithDependents.getCustomerId();
    int randCustomerNumber =
        CustomerUtil.getCustomerInfoByCustomerId(AADM_User, randCustomerId).getCustomerNumber();

    // fill out the request body
    PagingRequestCustomerDependentsFilterPostRequest pagingRequest =
        new PagingRequestCustomerDependentsFilterPostRequest();
    CustomerDependentsFilterPostRequest postRequest = new CustomerDependentsFilterPostRequest();
    postRequest.setCustomerNumber(randCustomerNumber);
    pagingRequest.setModel(postRequest);

    // send requests
    ORAN_App.attemptsTo(
        customersApi.POSTCustomersDependentsSearchOnTheCustomersController(
            pagingRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        customersApi.POSTCustomersDependentsSearchOnTheCustomersController(
            pagingRequest, "string"));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        customersApi.POSTCustomersDependentsSearchOnTheCustomersController(
            pagingRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // validate response
    PagingResponseCustomerDependentResponse customerDependents =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseCustomerDependentResponse.class);

    assertThat(customerDependents != null).isTrue();
    assertThat(customerDependents.getResponse().get(0).getCustomerId()).isEqualTo(randCustomerId);
  }

  @Test
  public void
      customerDependentsSearchWithMismatchingCustomerIdAndCustomerNumberReturnsEmptyDependents() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseCustomersTo customersApi = new UseCustomersTo();

    /* Get a random customer that has at least one customer dependent */
    CustomerContactDependentResponse randomCustomerWithDependents =
        CustomerUtil.getRandomCustomerWithDependents(AADM_User, null);

    String randCustomerId = randomCustomerWithDependents.getCustomerId();
    int randCustomerNumber =
        CustomerUtil.getCustomerInfoByCustomerId(AADM_User, randCustomerId).getCustomerNumber();

    // fill out the request body
    PagingRequestCustomerDependentsFilterPostRequest pagingRequest =
        new PagingRequestCustomerDependentsFilterPostRequest();
    CustomerDependentsFilterPostRequest postRequest = new CustomerDependentsFilterPostRequest();
    postRequest.setCustomerId(randCustomerId);
    // set mismatching customer number
    postRequest.setCustomerNumber(randCustomerNumber + 1);
    pagingRequest.setModel(postRequest);

    // send requests
    ORAN_App.attemptsTo(
        customersApi.POSTCustomersDependentsSearchOnTheCustomersController(
            pagingRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        customersApi.POSTCustomersDependentsSearchOnTheCustomersController(
            pagingRequest, "string"));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        customersApi.POSTCustomersDependentsSearchOnTheCustomersController(
            pagingRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // validate response
    PagingResponseCustomerDependentResponse customerDependents =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseCustomerDependentResponse.class);

    assertThat(customerDependents != null).isTrue();
    // check no customer is returned
    assertThat(customerDependents.getResponse().isEmpty()).isTrue();
  }

  @Test
  public void customerDependentsSearchForCustomerWithNoDependentsReturnsEmptyResponse() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseCustomersTo customersApi = new UseCustomersTo();

    /* Get a random customer that has no customer dependents */
    List<CustomerContactDependentResponse> contactDependentResponseList =
        CustomerUtil.getAllCustomerContactDependents(AADM_User, null);
    assert contactDependentResponseList != null;
    contactDependentResponseList =
        contactDependentResponseList
            .stream()
            .filter(customer -> customer.getCustomerDependents().isEmpty())
            .collect(Collectors.toList());

    CustomerContactDependentResponse randomCustomersWithNoContacts =
        contactDependentResponseList.get(new Random().nextInt(contactDependentResponseList.size()));

    String randCustomerId = randomCustomersWithNoContacts.getCustomerId();

    // fill out the request body
    PagingRequestCustomerDependentsFilterPostRequest pagingRequest =
        new PagingRequestCustomerDependentsFilterPostRequest();
    CustomerDependentsFilterPostRequest postRequest = new CustomerDependentsFilterPostRequest();
    postRequest.setCustomerId(randCustomerId);
    pagingRequest.setModel(postRequest);

    ORAN_App.attemptsTo(
        customersApi.POSTCustomersDependentsSearchOnTheCustomersController(
            pagingRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        customersApi.POSTCustomersDependentsSearchOnTheCustomersController(
            pagingRequest, "string"));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        customersApi.POSTCustomersDependentsSearchOnTheCustomersController(
            pagingRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PagingResponseCustomerDependentResponse customerDependents =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseCustomerDependentResponse.class);

    assertThat(customerDependents != null).isTrue();
    // check no customer is returned
    assertThat(customerDependents.getResponse().isEmpty()).isTrue();

    // Repeat the test with customer number
    int randCustomerNumber =
        CustomerUtil.getCustomerInfoByCustomerId(AADM_User, randCustomerId).getCustomerNumber();

    // fill out the request body
    pagingRequest = new PagingRequestCustomerDependentsFilterPostRequest();
    postRequest = new CustomerDependentsFilterPostRequest();
    postRequest.setCustomerNumber(randCustomerNumber);
    pagingRequest.setModel(postRequest);

    // send requests
    ORAN_App.attemptsTo(
        customersApi.POSTCustomersDependentsSearchOnTheCustomersController(
            pagingRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        customersApi.POSTCustomersDependentsSearchOnTheCustomersController(
            pagingRequest, "string"));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        customersApi.POSTCustomersDependentsSearchOnTheCustomersController(
            pagingRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // validate response
    customerDependents =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseCustomerDependentResponse.class);

    assertThat(customerDependents != null).isTrue();
    // check no customer is returned
    assertThat(customerDependents.getResponse().isEmpty()).isTrue();
  }

  @Test
  public void customerDependentsSearchByChangeDateReturnsCustomers() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseCustomersTo customersApi = new UseCustomersTo();

    /* Use 2 years old date as changeDate to test the API. This is not ideal. However there is no way
    to add a dependent for a customer through API. So we can not add a contact to a customer use the current time to
    send as changed date. Also, CustomerContactDependents search also do not return the dependent changed date. If it
    returned changedData we could use that to test.
     */
    DateTime twoYearsOldDateTime = DateTime.now().minusYears(2);
    String changedDate = twoYearsOldDateTime.toString();

    // fill out the request body
    PagingRequestCustomerDependentsFilterPostRequest pagingRequest =
        new PagingRequestCustomerDependentsFilterPostRequest();
    CustomerDependentsFilterPostRequest postRequest = new CustomerDependentsFilterPostRequest();
    postRequest.setChangedDate(changedDate);
    pagingRequest.setModel(postRequest);

    // send requests
    ORAN_App.attemptsTo(
        customersApi.POSTCustomersDependentsSearchOnTheCustomersController(
            pagingRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        customersApi.POSTCustomersDependentsSearchOnTheCustomersController(
            pagingRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        customersApi.POSTCustomersDependentsSearchOnTheCustomersController(
            pagingRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // validate response
    PagingResponseCustomerDependentResponse customerDependents =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseCustomerDependentResponse.class);
    assertThat(customerDependents).isNotNull();
    // there should be at least one customer with a dependent updated in last 2 years.
    assertThat(customerDependents.getResponse().isEmpty()).isFalse();
  }
}
