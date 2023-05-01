package com.vertafore.test.services.customers;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.servicewrappers.UseCustomersTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class GET_CustomersContactPhoneCache extends TokenSuperClass {

  @Test
  public void getCustomersContactPhoneCacheBaselineTest() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseCustomersTo customersApi = new UseCustomersTo();

    String contactPhone = "0812898065";

    // BASELINE TESTS
    VADM_Admin.attemptsTo(
        customersApi.GETCustomersContactPhoneCacheOnTheCustomersController(contactPhone, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        customersApi.GETCustomersContactPhoneCacheOnTheCustomersController(contactPhone, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        customersApi.GETCustomersContactPhoneCacheOnTheCustomersController(contactPhone, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
