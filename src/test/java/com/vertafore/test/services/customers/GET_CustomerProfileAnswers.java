package com.vertafore.test.services.customers;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.CustomerProfileAnswerResponse;
import com.vertafore.test.servicewrappers.UseCustomerTo;
import com.vertafore.test.util.CustomerUtil;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class GET_CustomerProfileAnswers extends TokenSuperClass {

  @Test
  public void getCustomerProfileAnswersBaselineTests() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    String randomCustomerId =
        CustomerUtil.selectRandomCustomer(AADM_User, "customer").getCustomerId();

    UseCustomerTo customerApi = new UseCustomerTo();

    VADM_Admin.attemptsTo(
        customerApi.GETCustomerProfileAnswersOnTheCustomersController(randomCustomerId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        customerApi.GETCustomerProfileAnswersOnTheCustomersController(randomCustomerId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        customerApi.GETCustomerProfileAnswersOnTheCustomersController(randomCustomerId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<CustomerProfileAnswerResponse> profileAnswerResponses =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", CustomerProfileAnswerResponse.class);

    assertThat(profileAnswerResponses).isNotNull();
  }
}
