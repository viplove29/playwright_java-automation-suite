package com.vertafore.test.services.customer_agencies;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.CustomerAgencySearchPostRequest;
import com.vertafore.test.models.ems.PagingRequestCustomerAgencySearchPostRequest;
import com.vertafore.test.servicewrappers.UseCustomerAgenciesTo;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_CustomerAgenciesSearch extends TokenSuperClass {

  @Test
  public void postCustomerAgenciesSearchBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseCustomerAgenciesTo customerAgencyApi = new UseCustomerAgenciesTo();

    PagingRequestCustomerAgencySearchPostRequest pagingSearchPostRequest =
        new PagingRequestCustomerAgencySearchPostRequest();
    CustomerAgencySearchPostRequest customerAgencySearchModel =
        new CustomerAgencySearchPostRequest();
    pagingSearchPostRequest.setModel(customerAgencySearchModel);

    ORAN_App.attemptsTo(
        customerAgencyApi.POSTCustomerAgenciesSearchOnTheCustomeragenciesController(
            pagingSearchPostRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        customerAgencyApi.POSTCustomerAgenciesSearchOnTheCustomeragenciesController(
            pagingSearchPostRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    VADM_Admin.attemptsTo(
        customerAgencyApi.POSTCustomerAgenciesSearchOnTheCustomeragenciesController(
            pagingSearchPostRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
