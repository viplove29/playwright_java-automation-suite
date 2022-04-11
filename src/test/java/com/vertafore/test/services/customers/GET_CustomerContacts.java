package com.vertafore.test.services.customers;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.CustomerBasicInfoResponse;
import com.vertafore.test.models.ems.CustomerContactResponse;
import com.vertafore.test.servicewrappers.UseCustomerTo;
import com.vertafore.test.util.AuthGroupUtility;
import com.vertafore.test.util.CustomerUtil;
import com.vertafore.test.util.EmployeeUtil;
import com.vertafore.test.util.Util;
import java.util.List;
import java.util.Random;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_CustomerContacts extends TokenSuperClass {

  // helpers

  public static String randCustomerId = "";
  public static int randCustomerNumber = 0;
  public static String randCustomerName = "";

  Random random = new Random();
  int randomInt = random.nextInt(100);

  @Test
  public void customerContactsReturnsAllCustomerContacts() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseCustomerTo customersApi = new UseCustomerTo();

    ORAN_App.attemptsTo(
        customersApi.GETCustomerContactsOnTheCustomersController(null, null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        customersApi.GETCustomerContactsOnTheCustomersController(null, null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        customersApi.GETCustomerContactsOnTheCustomersController(null, null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<CustomerContactResponse> customerContacts =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", CustomerContactResponse.class);

    assertThat(customerContacts.get(0).getClass().getDeclaredFields().length).isEqualTo(11);
    assertThat(customerContacts.size()).isGreaterThan(0);

    randCustomerId = customerContacts.get(randomInt).getCustId();
    randCustomerNumber = customerContacts.get(randomInt).getCustNo();
    randCustomerName = customerContacts.get(randomInt).getName();
  }

  @Test
  public void customerContactsReturnsOneCustomerContact() {
    Actor AADM_User = theActorCalled("AADM_User");

    UseCustomerTo customersApi = new UseCustomerTo();

    CustomerBasicInfoResponse randomCustomer =
        CustomerUtil.selectRandomCustomer(AADM_User, "customer");
    randCustomerId = randomCustomer.getCustomerId();
    randCustomerNumber = randomCustomer.getCustomerNumber();
    randCustomerName = randomCustomer.getFullName();

    AADM_User.attemptsTo(
        customersApi.GETCustomerContactsOnTheCustomersController(
            randCustomerId, randCustomerNumber, "string"));

    CustomerContactResponse customerContact =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", CustomerContactResponse.class)
            .get(0);

    assertThat(customerContact.getCustId()).isEqualTo(randCustomerId);
    assertThat(customerContact.getCustNo()).isEqualTo(randCustomerNumber);
    assertThat(customerContact.getName()).isEqualTo(randCustomerName);
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

    Actor ORAN_App = theActorCalled("ORAN_App");

    UseCustomerTo customerAPI = new UseCustomerTo();

    // validate service employee can successfully retrieve info
    ORAN_App.attemptsTo(
        customerAPI.GETCustomerContactsOnTheCustomersController(randomSecuredCustomerId, 0, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    CustomerContactResponse securedCustomerContactResponse =
        LastResponse.received()
            .answeredBy(ORAN_App)
            .getBody()
            .jsonPath()
            .getList("", CustomerContactResponse.class)
            .get(0);
    assertThat(securedCustomerContactResponse.getCustId()).isEqualTo(randomSecuredCustomerId);
    assertThat(securedCustomerContactResponse.getClass().getDeclaredFields().length).isEqualTo(11);

    // remove random secured customer access from employee
    EmployeeUtil.deleteSecuredCustomerAccessForEmployee(
        AADM_User, serviceEmployeeEmpCode, randomSecuredCustomerId);

    // validate service employee can no longer retrieve info
    ORAN_App.attemptsTo(
        customerAPI.GETCustomerContactsOnTheCustomersController(randomSecuredCustomerId, 0, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponse("No customer was found using the arguments supplied", ORAN_App);
  }
}
