package com.vertafore.test.services.auth;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.AuthGroupResponse;
import com.vertafore.test.servicewrappers.UseAuthTo;
import com.vertafore.test.util.AuthGroupUtility;
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
public class GET_AuthGroups {

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
  public void AuthGroupsReturnsAllAuthGroups() {
    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    UseAuthTo AuthAPI = new UseAuthTo();

    // ORANG TEST
    doug.attemptsTo(
        AuthAPI.GETAuthConfigAuthGroupsAuthGroupIdOnTheConfigauthController(null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // VADM TEST
    adam.attemptsTo(
        AuthAPI.GETAuthConfigAuthGroupsAuthGroupIdOnTheConfigauthController(null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // AADM TEST
    bob.attemptsTo(
        AuthAPI.GETAuthConfigAuthGroupsAuthGroupIdOnTheConfigauthController(null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<AuthGroupResponse> allAvailableAuthGroupsList =
        LastResponse.received()
            .answeredBy(doug)
            .getBody()
            .jsonPath()
            .getList("", AuthGroupResponse.class);

    assertThat(allAvailableAuthGroupsList.size()).isGreaterThan(0);
  }

  @Test
  public void AuthGroupsReturnsSpecificAuthGroup() {
    Actor doug = theActorCalled("doug");
    UseAuthTo AuthAPI = new UseAuthTo();

    // set helper variables for validation
    AuthGroupUtility.setAuthGroupHelperVariables(AuthGroupUtility.selectRandomAuthGroup(doug));

    // Test to validate that a specific AuthGroup can be found
    doug.attemptsTo(
        AuthAPI.GETAuthConfigAuthGroupsAuthGroupIdOnTheConfigauthController(
            AuthGroupUtility.currentAGrpId, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AuthGroupResponse authGroupResponse =
        LastResponse.received()
            .answeredBy(doug)
            .getBody()
            .jsonPath()
            .getList("", AuthGroupResponse.class)
            .get(0); // should be first and only item in the list

    AuthGroupUtility.validateSingleAuthGroup(authGroupResponse);
  }
}
