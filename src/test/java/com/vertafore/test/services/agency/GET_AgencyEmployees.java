package com.vertafore.test.services.agency;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.servicewrappers.UseAgencyTo;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_AgencyEmployees {
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

  @Test
  public void agencyEmployeesReturnAllAgencyEmployees() {
    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    UseAgencyTo agencyEmployeesApi = new UseAgencyTo();

    bob.attemptsTo(agencyEmployeesApi.GETAgencyEmployeesOnTheAgencyController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    doug.attemptsTo(agencyEmployeesApi.GETAgencyEmployeesOnTheAgencyController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    adam.attemptsTo(agencyEmployeesApi.GETAgencyEmployeesOnTheAgencyController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }
}
