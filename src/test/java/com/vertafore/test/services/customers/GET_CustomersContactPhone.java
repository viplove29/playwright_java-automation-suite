package com.vertafore.test.services.customers;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseCustomerTo;
import com.vertafore.test.servicewrappers.UseCustomersTo;
import com.vertafore.test.util.CustomerUtil;
import java.util.stream.Collectors;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class GET_CustomersContactPhone extends TokenSuperClass {

  @Test
  public void getCustomersContactPhoneGetsCustomerByCellPhone() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseCustomersTo customersApi = new UseCustomersTo();
    UseCustomerTo customerApi = new UseCustomerTo();

    // STAGE CUSTOMER TO SEARCH
    CustomerBasicInfoPostRequest customer =
        CustomerUtil.createBasicCustomer("Customer", "Individual", AADM_User);
    String mobilePhone = customer.getPhoneNumbers().getCell().replaceAll("[^\\d]", "");

    AADM_User.attemptsTo(customerApi.POSTCustomerBasicInfoOnTheCustomersController(customer, ""));

    CustomerIdResponse customerIdResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", CustomerIdResponse.class);

    String customerId = customerIdResponse.getCustomerId();

    // BASELINE TESTS
    VADM_Admin.attemptsTo(
        customersApi.GETCustomersContactPhoneOnTheCustomersController(mobilePhone, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        customersApi.GETCustomersContactPhoneOnTheCustomersController(mobilePhone, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        customersApi.GETCustomersContactPhoneOnTheCustomersController(mobilePhone, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // VALIDATION OF RESPONSE
    CustomerListResponse customersWithDesiredPhoneNumber =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", CustomerListResponse.class);

    assertThat(customersWithDesiredPhoneNumber.getCustomerList()).isNotNull();
    assertThat(customersWithDesiredPhoneNumber.getCustomerList().isEmpty()).isFalse();

    CustomerResponse desiredCustomer =
        customersWithDesiredPhoneNumber
            .getCustomerList()
            .stream()
            .filter(customerModel -> customerModel.getCustId().equals(customerId))
            .collect(Collectors.toList())
            .get(0);

    assertThat(desiredCustomer.getContactName())
        .isEqualTo(customer.getCustomerName().getFirmName());
  }

  @Test
  public void getCustomersContactPhoneGetsCustomerByResidencePhone() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseCustomersTo customersApi = new UseCustomersTo();
    UseCustomerTo customerApi = new UseCustomerTo();

    // STAGE CUSTOMER TO SEARCH
    CustomerBasicInfoPostRequest customer =
        CustomerUtil.createBasicCustomer("Customer", "Individual", AADM_User);
    String residencePhone = customer.getPhoneNumbers().getResidencePhone().replaceAll("[^\\d]", "");

    AADM_User.attemptsTo(customerApi.POSTCustomerBasicInfoOnTheCustomersController(customer, ""));

    CustomerIdResponse customerIdResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", CustomerIdResponse.class);

    String customerId = customerIdResponse.getCustomerId();

    // BASELINE TESTS
    VADM_Admin.attemptsTo(
        customersApi.GETCustomersContactPhoneOnTheCustomersController(residencePhone, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        customersApi.GETCustomersContactPhoneOnTheCustomersController(residencePhone, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        customersApi.GETCustomersContactPhoneOnTheCustomersController(residencePhone, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // VALIDATION OF RESPONSE
    CustomerListResponse customersWithDesiredPhoneNumber =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", CustomerListResponse.class);

    assertThat(customersWithDesiredPhoneNumber.getCustomerList()).isNotNull();
    assertThat(customersWithDesiredPhoneNumber.getCustomerList().isEmpty()).isFalse();

    CustomerResponse desiredCustomer =
        customersWithDesiredPhoneNumber
            .getCustomerList()
            .stream()
            .filter(customerModel -> customerModel.getCustId().equals(customerId))
            .collect(Collectors.toList())
            .get(0);

    assertThat(desiredCustomer.getContactName())
        .isEqualTo(customer.getCustomerName().getFirmName());
  }

  @Test
  public void getCustomersContactPhoneGetsCustomerByBusinessPhone() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseCustomersTo customersApi = new UseCustomersTo();
    UseCustomerTo customerApi = new UseCustomerTo();

    // STAGE CUSTOMER TO SEARCH
    CustomerBasicInfoPostRequest customer =
        CustomerUtil.createBasicCustomer("Customer", "Individual", AADM_User);
    String businessPhone = customer.getPhoneNumbers().getBusinessPhone().replaceAll("[^\\d]", "");

    AADM_User.attemptsTo(customerApi.POSTCustomerBasicInfoOnTheCustomersController(customer, ""));

    CustomerIdResponse customerIdResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", CustomerIdResponse.class);

    String customerId = customerIdResponse.getCustomerId();

    // BASELINE TESTS
    VADM_Admin.attemptsTo(
        customersApi.GETCustomersContactPhoneOnTheCustomersController(businessPhone, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        customersApi.GETCustomersContactPhoneOnTheCustomersController(businessPhone, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        customersApi.GETCustomersContactPhoneOnTheCustomersController(businessPhone, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // VALIDATION OF RESPONSE
    CustomerListResponse customersWithDesiredPhoneNumber =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", CustomerListResponse.class);

    assertThat(customersWithDesiredPhoneNumber.getCustomerList()).isNotNull();
    assertThat(customersWithDesiredPhoneNumber.getCustomerList().isEmpty()).isFalse();

    CustomerResponse desiredCustomer =
        customersWithDesiredPhoneNumber
            .getCustomerList()
            .stream()
            .filter(customerModel -> customerModel.getCustId().equals(customerId))
            .collect(Collectors.toList())
            .get(0);

    assertThat(desiredCustomer.getContactName())
        .isEqualTo(customer.getCustomerName().getFirmName());
  }
}
