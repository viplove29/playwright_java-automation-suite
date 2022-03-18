package com.vertafore.test.services.brokers;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.BrokerResponse;
import com.vertafore.test.servicewrappers.UseBrokersTo;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_Brokers extends TokenSuperClass {

  /* This is a simple smoke test that sends a request to get the first 5 brokers and checks that the
  response body is not empty as well as the correct number of fields being returned.
   */
  @Test
  public void brokersReturnsAllBrokers() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseBrokersTo brokersAPI = new UseBrokersTo();

    /*Send requests with the skip value set to 0, and top as well as totalRecords set to 5
    to return only first 5 results.
     */

    VADM_Admin.attemptsTo(brokersAPI.GETBrokersOnTheBrokersController(0, 5, 5, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(brokersAPI.GETBrokersOnTheBrokersController(0, 5, 5, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(brokersAPI.GETBrokersOnTheBrokersController(0, 5, 5, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<BrokerResponse> brokerResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("response", BrokerResponse.class);

    assertThat(brokerResponse.get(0).getClass().getDeclaredFields().length).isEqualTo(9);
    assertThat(brokerResponse.size()).isGreaterThan(0);
  }
}
