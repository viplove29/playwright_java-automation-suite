package com.vertafore.test.services.agency;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.PrCodeResponse;
import com.vertafore.test.servicewrappers.UseAgencyTo;
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
public class GET_AgencyLists {

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

  /* This a smoke test that validates the GET agency/lists endpoint against user, app, and admin context
  as well as making sure the list returned by passing in the master PRcode (^^^) is not empty and contains
  the right number of fields.*/
  @Test
  public void agencyListsReturnsAllPRCodes() {

    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    UseAgencyTo agencyAPI = new UseAgencyTo();

    String masterCode = "^^^";

    adam.attemptsTo(agencyAPI.GETAgencyListsOnTheAgencyController(masterCode, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    doug.attemptsTo(agencyAPI.GETAgencyListsOnTheAgencyController(masterCode, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    bob.attemptsTo(agencyAPI.GETAgencyListsOnTheAgencyController(masterCode, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<PrCodeResponse> prCodeResponse =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getList("", PrCodeResponse.class);

    assertThat(prCodeResponse.get(0).getClass().getDeclaredFields().length).isEqualTo(6);
    assertThat(prCodeResponse.size()).isGreaterThan(0);
  }
}
