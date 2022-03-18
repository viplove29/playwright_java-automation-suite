package com.vertafore.test.services.customers;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.CustomerResponse;
import com.vertafore.test.servicewrappers.UseCustomersTo;
import java.util.Random;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_Customers extends TokenSuperClass {

  // Helpers
  Random random = new Random();
  int randomInt = random.nextInt(100);

  public static String randCustId = "";
  public static String randSortName = "";
  public static String randContactName = "";
  public static String randFirmName = "";
  public static String randPrimaryEmail = "";
  public static String randSecondEmail = "";
  public static int randCustNumber = 0;
  public static String randZipCode = "";

  @Test
  public void customersReturnsAllCustomers() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseCustomersTo customersApi = new UseCustomersTo();

    ORAN_App.attemptsTo(
        customersApi.GETCustomersOnTheCustomersControllerDeprecated(null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        customersApi.GETCustomersOnTheCustomersControllerDeprecated(null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        customersApi.GETCustomersOnTheCustomersControllerDeprecated(null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    CustomerResponse customers =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("customerList", CustomerResponse.class)
            .get(randomInt);

    assertThat(customers.getClass().getDeclaredFields().length).isEqualTo(11);

    /*Create a list of variables to store a random selection from the first getCustomers call*/
    randCustId = customers.getCustId();
    randSortName = customers.getSortName();
    randContactName = customers.getContactName();
    randFirmName = customers.getFirmName();
    randPrimaryEmail = customers.getPrimaryEmail();
    randSecondEmail = customers.getSecondaryEmail();
    randCustNumber = customers.getCustNo();
    randZipCode = customers.getZipCode();

    /*Create a second call to the Get Customers endpoint in order to validate that you can get a certain customer and that the data is correct*/
    AADM_User.attemptsTo(
        customersApi.GETCustomersOnTheCustomersControllerDeprecated(randCustNumber, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    CustomerResponse customer =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("customerList", CustomerResponse.class)
            .get(0);

    // Response body format assertions
    assertThat(customer != null).isTrue();
    assertThat(customer.getClass().getDeclaredFields().length).isEqualTo(11);

    // Response body field data assertions
    assertThat(customer.getCustId()).isEqualTo(randCustId);

    assertThat(customer.getSortName()).isEqualTo(randSortName);

    assertThat(customer.getContactName()).isEqualTo(randContactName);

    assertThat(customer.getFirmName()).isEqualTo(randFirmName);

    assertThat(customer.getPrimaryEmail()).isEqualTo(randPrimaryEmail);

    assertThat(customer.getSecondaryEmail()).isEqualTo(randSecondEmail);

    assertThat(customer.getCustNo()).isEqualTo(randCustNumber);

    assertThat(customer.getZipCode()).isEqualTo(randZipCode);
  }

  // TODO this should not only be its own test but should also not use a hardcoded phone number.
  // Also, it should check that just the one customer is returned.
  @Test
  public void customerByPhoneReturnsCustomerByPhone() {
    Actor AADM_User = theActorCalled("AADM_User");

    UseCustomersTo customersApi = new UseCustomersTo();

    AADM_User.attemptsTo(
        customersApi.GETCustomersContactPhoneOnTheCustomersController("4111600767", "string"));

    CustomerResponse customer =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("customerList", CustomerResponse.class)
            .get(0);

    assertThat(customer != null).isTrue();
  }
}
