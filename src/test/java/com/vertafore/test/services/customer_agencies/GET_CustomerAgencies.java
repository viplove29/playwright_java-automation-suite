package com.vertafore.test.services.customer_agencies;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.CustomerAgencyDetailResponse;
import com.vertafore.test.servicewrappers.UseCustomerAgenciesTo;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_CustomerAgencies extends TokenSuperClass {

  /* A smoke test that validates the GET /customer-agencies/agency-details endpoint against admin,app, and user contexts.
  Validate that only admin returns customer agency data and returns a list.*/
  @Test
  public void customerAgenciesReturnsAllCustomerAgencies() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseCustomerAgenciesTo customerAgencyApi = new UseCustomerAgenciesTo();

    ORAN_App.attemptsTo(
        customerAgencyApi
            .GETCustomerAgenciesAgencyDetailsOnTheCustomeragenciesControllerDeprecated());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        customerAgencyApi
            .GETCustomerAgenciesAgencyDetailsOnTheCustomeragenciesControllerDeprecated());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    VADM_Admin.attemptsTo(
        customerAgencyApi
            .GETCustomerAgenciesAgencyDetailsOnTheCustomeragenciesControllerDeprecated());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<CustomerAgencyDetailResponse> customerAgencyDetail =
        LastResponse.received()
            .answeredBy(VADM_Admin)
            .getBody()
            .jsonPath()
            .getList("", CustomerAgencyDetailResponse.class);

    assertThat(customerAgencyDetail.get(0).getClass().getDeclaredFields().length).isEqualTo(9);
    assertThat(customerAgencyDetail.size()).isGreaterThan(0);
  }
}
