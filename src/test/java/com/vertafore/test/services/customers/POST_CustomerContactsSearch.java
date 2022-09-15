package com.vertafore.test.services.customers;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseCustomersTo;
import com.vertafore.test.util.AuthGroupUtility;
import com.vertafore.test.util.CustomerUtil;
import com.vertafore.test.util.EmployeeUtil;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_CustomerContactsSearch extends TokenSuperClass {

  @Test
  public void customerContactsSearchWithCustomerIdReturnsOneCustomerContact() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseCustomersTo customersApi = new UseCustomersTo();

    /* Get all customers' contacts and dependents and select a random
    customer that has at least one customer contact */
    CustomerContactDependentResponse randomCustomerWithContacts =
        CustomerUtil.getRandomCustomerWithContacts(AADM_User, null);

    String randCustomerId = randomCustomerWithContacts.getCustomerId();

    // fill out the request body
    PagingRequestCustomerContactsFilterPostRequest pagingRequest =
        new PagingRequestCustomerContactsFilterPostRequest();
    CustomerContactsFilterPostRequest postRequest = new CustomerContactsFilterPostRequest();
    postRequest.setCustomerId(randCustomerId);
    pagingRequest.setModel(postRequest);

    // send requests
    ORAN_App.attemptsTo(
        customersApi.POSTCustomersContactsSearchOnTheCustomersController(pagingRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        customersApi.POSTCustomersContactsSearchOnTheCustomersController(pagingRequest, "string"));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        customersApi.POSTCustomersContactsSearchOnTheCustomersController(pagingRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // validate response
    PagingResponseCustomerContactResponse customerContacts =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseCustomerContactResponse.class);

    assertThat(customerContacts.getResponse().get(0).getCustomerId()).isEqualTo(randCustomerId);
  }

  @Test
  public void customerContactsSearchWithCustomerNumberReturnsOneCustomerContact() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseCustomersTo customersApi = new UseCustomersTo();

    /* Get a random customer that has at least one customer contact */
    CustomerContactDependentResponse randomCustomerWithContacts =
        CustomerUtil.getRandomCustomerWithContacts(AADM_User, null);

    String randCustomerId = randomCustomerWithContacts.getCustomerId();
    int randCustomerNumber =
        CustomerUtil.getCustomerInfoByCustomerId(AADM_User, randCustomerId).getCustomerNumber();

    // fill out the request body
    PagingRequestCustomerContactsFilterPostRequest pagingRequest =
        new PagingRequestCustomerContactsFilterPostRequest();
    CustomerContactsFilterPostRequest postRequest = new CustomerContactsFilterPostRequest();
    postRequest.setCustomerNumber(randCustomerNumber);
    pagingRequest.setModel(postRequest);

    // send requests
    ORAN_App.attemptsTo(
        customersApi.POSTCustomersContactsSearchOnTheCustomersController(pagingRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        customersApi.POSTCustomersContactsSearchOnTheCustomersController(pagingRequest, "string"));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        customersApi.POSTCustomersContactsSearchOnTheCustomersController(pagingRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // validate response
    PagingResponseCustomerContactResponse customerContacts =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseCustomerContactResponse.class);

    assertThat(customerContacts != null).isTrue();
    assertThat(customerContacts.getResponse().get(0).getCustomerId()).isEqualTo(randCustomerId);
  }

  @Test
  public void
      customerContactsSearchWithMismatchingCustomerIdAndCustomerNumberReturnsEmptyCustomers() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseCustomersTo customersApi = new UseCustomersTo();

    /* Get a random customer that has at least one customer contact */
    CustomerContactDependentResponse randomCustomerWithContacts =
        CustomerUtil.getRandomCustomerWithContacts(AADM_User, null);

    String randCustomerId = randomCustomerWithContacts.getCustomerId();
    int randCustomerNumber =
        CustomerUtil.getCustomerInfoByCustomerId(AADM_User, randCustomerId).getCustomerNumber();

    // fill out the request body
    PagingRequestCustomerContactsFilterPostRequest pagingRequest =
        new PagingRequestCustomerContactsFilterPostRequest();
    CustomerContactsFilterPostRequest postRequest = new CustomerContactsFilterPostRequest();
    postRequest.setCustomerId(randCustomerId);
    // set mismatching customer number
    postRequest.setCustomerNumber(randCustomerNumber + 1);
    pagingRequest.setModel(postRequest);

    // send requests
    ORAN_App.attemptsTo(
        customersApi.POSTCustomersContactsSearchOnTheCustomersController(pagingRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        customersApi.POSTCustomersContactsSearchOnTheCustomersController(pagingRequest, "string"));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        customersApi.POSTCustomersContactsSearchOnTheCustomersController(pagingRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // validate response
    PagingResponseCustomerContactResponse customerContacts =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseCustomerContactResponse.class);

    assertThat(customerContacts != null).isTrue();
    // check no customer is returned
    assertThat(customerContacts.getResponse().isEmpty()).isTrue();
  }

  @Test
  public void customerContactsSearchForCustomerWithNoContactsReturnsEmptyResponse() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseCustomersTo customersApi = new UseCustomersTo();

    /* Get a random customer that has no customer contact */
    List<CustomerContactDependentResponse> contactDependentResponseList =
        CustomerUtil.getAllCustomerContactDependents(AADM_User, null);
    assert contactDependentResponseList != null;
    contactDependentResponseList =
        contactDependentResponseList
            .stream()
            .filter(customer -> customer.getCustomerContacts().isEmpty())
            .collect(Collectors.toList());

    CustomerContactDependentResponse randomCustomersWithNoContacts =
        contactDependentResponseList.get(new Random().nextInt(contactDependentResponseList.size()));

    String randCustomerId = randomCustomersWithNoContacts.getCustomerId();

    // fill out the request body
    PagingRequestCustomerContactsFilterPostRequest pagingRequest =
        new PagingRequestCustomerContactsFilterPostRequest();
    CustomerContactsFilterPostRequest postRequest = new CustomerContactsFilterPostRequest();
    postRequest.setCustomerId(randCustomerId);
    pagingRequest.setModel(postRequest);

    ORAN_App.attemptsTo(
        customersApi.POSTCustomersContactsSearchOnTheCustomersController(pagingRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        customersApi.POSTCustomersContactsSearchOnTheCustomersController(pagingRequest, "string"));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        customersApi.POSTCustomersContactsSearchOnTheCustomersController(pagingRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PagingResponseCustomerContactResponse customerContacts =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseCustomerContactResponse.class);

    assertThat(customerContacts != null).isTrue();
    // check no customer is returned
    assertThat(customerContacts.getResponse().isEmpty()).isTrue();

    // Repeat the test with customer number
    int randCustomerNumber =
        CustomerUtil.getCustomerInfoByCustomerId(AADM_User, randCustomerId).getCustomerNumber();

    // fill out the request body
    pagingRequest = new PagingRequestCustomerContactsFilterPostRequest();
    postRequest = new CustomerContactsFilterPostRequest();
    postRequest.setCustomerNumber(randCustomerNumber);
    pagingRequest.setModel(postRequest);

    // send requests
    ORAN_App.attemptsTo(
        customersApi.POSTCustomersContactsSearchOnTheCustomersController(pagingRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        customersApi.POSTCustomersContactsSearchOnTheCustomersController(pagingRequest, "string"));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        customersApi.POSTCustomersContactsSearchOnTheCustomersController(pagingRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // validate response
    customerContacts =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseCustomerContactResponse.class);

    assertThat(customerContacts != null).isTrue();
    // check no customer is returned
    assertThat(customerContacts.getResponse().isEmpty()).isTrue();
  }

  @Test
  public void customerContactsFiltersBySecuredCustomerAccessForServiceEmployees() {
    Actor AADM_User = theActorCalled("AADM_User");
    String serviceEmployeeEmpCode = AuthGroupUtility.getCurrentServiceEmployeeEmpCode(AADM_User);

    // give service employee access to random secured customer
    String randomSecuredCustomerId =
        CustomerUtil.getRandomSecuredCustomer(AADM_User).getCustomerId();
    EmployeeUtil.insertSecuredCustomerAccessForEmployee(
        AADM_User, serviceEmployeeEmpCode, randomSecuredCustomerId);

    CustomerContactsFilterPostRequest postRequest = new CustomerContactsFilterPostRequest();
    postRequest.setCustomerId(randomSecuredCustomerId);
    PagingRequestCustomerContactsFilterPostRequest pagingRequest =
        new PagingRequestCustomerContactsFilterPostRequest();
    pagingRequest.setModel(postRequest);

    Actor ORAN_App = theActorCalled("ORAN_App");

    UseCustomersTo customerAPI = new UseCustomersTo();

    // validate service employee can successfully retrieve info
    ORAN_App.attemptsTo(
        customerAPI.POSTCustomersContactsSearchOnTheCustomersController(pagingRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PagingResponseCustomerContactResponse securedCustomerContactResponse =
        LastResponse.received()
            .answeredBy(ORAN_App)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseCustomerContactResponse.class);

    assertThat(securedCustomerContactResponse.getResponse().size()).isGreaterThan(0);
    assertThat(securedCustomerContactResponse.getResponse().get(0).getCustomerId())
        .isEqualTo(randomSecuredCustomerId);

    // remove random secured customer access from employee
    EmployeeUtil.deleteSecuredCustomerAccessForEmployee(
        AADM_User, serviceEmployeeEmpCode, randomSecuredCustomerId);

    // validate service employee can no longer retrieve info
    ORAN_App.attemptsTo(
        customerAPI.POSTCustomersContactsSearchOnTheCustomersController(pagingRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    securedCustomerContactResponse =
        LastResponse.received()
            .answeredBy(ORAN_App)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseCustomerContactResponse.class);
    assertThat(securedCustomerContactResponse.getResponse().size()).isEqualTo(0);
    assertThat(securedCustomerContactResponse.getTotalCount()).isEqualTo(0);
  }
}
