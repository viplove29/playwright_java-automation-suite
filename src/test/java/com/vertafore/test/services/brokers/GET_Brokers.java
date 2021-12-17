package com.vertafore.test.services.brokers;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.BrokerResponse;
import com.vertafore.test.servicewrappers.UseBrokersTo;
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
public class GET_Brokers {

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

  /* This is a simple smoke test that sends a request to get the first 5 brokers and checks that the
  response body is not empty as well as the correct number of fields being returned.
   */
  @Test
  public void brokersReturnsAllBrokers() {

    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    UseBrokersTo brokersAPI = new UseBrokersTo();

    /*Send requests with the skip value set to 0, and top as well as totalRecords set to 5
    to return only first 5 results.
     */

    adam.attemptsTo(brokersAPI.GETBrokersOnTheBrokersController(0, 5, 5, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    doug.attemptsTo(brokersAPI.GETBrokersOnTheBrokersController(0, 5, 5, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    bob.attemptsTo(brokersAPI.GETBrokersOnTheBrokersController(0, 5, 5, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<BrokerResponse> brokerResponse =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getList("response", BrokerResponse.class);

    assertThat(brokerResponse.get(0).getClass().getDeclaredFields().length).isEqualTo(9);
    assertThat(brokerResponse.size()).isGreaterThan(0);
  }
}
