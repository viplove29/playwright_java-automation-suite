package com.vertafore.test.services.customer_agencies;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.CustomerAgencyDetailResponse;
import com.vertafore.test.servicewrappers.UseCustomerAgenciesTo;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_CustomerAgencies {
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

/* A smoke test that validates the GET /customer-agencies/agency-details endpoint against admin,app, and user contexts.
Validate that only admin returns customer agency data and returns a list.*/
  @Test
  public void customerAgenciesReturnsAllCustomerAgencies() {
    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    UseCustomerAgenciesTo customerAgencyApi = new UseCustomerAgenciesTo();

    doug.attemptsTo(
        customerAgencyApi.GETCustomerAgenciesAgencyDetailsOnTheCustomeragenciesController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    bob.attemptsTo(
        customerAgencyApi.GETCustomerAgenciesAgencyDetailsOnTheCustomeragenciesController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    adam.attemptsTo(
        customerAgencyApi.GETCustomerAgenciesAgencyDetailsOnTheCustomeragenciesController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<CustomerAgencyDetailResponse> customerAgencyDetail =
        LastResponse.received()
            .answeredBy(adam)
            .getBody()
            .jsonPath()
            .getList("", CustomerAgencyDetailResponse.class);

    assertThat(customerAgencyDetail.get(0).getClass().getDeclaredFields().length).isEqualTo(6);
    assertThat(customerAgencyDetail.size()).isGreaterThan(0);
  }
}
