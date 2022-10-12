package com.vertafore.test.services.auth;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.AuthGroupResponse;
import com.vertafore.test.servicewrappers.UseAuthTo;
import com.vertafore.test.util.AuthGroupUtility;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_AuthGroups extends TokenSuperClass {

  @Test
  public void AuthGroupsReturnsAllAuthGroups() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseAuthTo AuthAPI = new UseAuthTo();

    // ORANG TEST
    ORAN_App.attemptsTo(
        AuthAPI.GETAuthConfigAuthGroupsAuthGroupIdOnTheConfigauthController(null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);

    // VADM TEST
    VADM_Admin.attemptsTo(
        AuthAPI.GETAuthConfigAuthGroupsAuthGroupIdOnTheConfigauthController(null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // AADM TEST
    AADM_User.attemptsTo(
        AuthAPI.GETAuthConfigAuthGroupsAuthGroupIdOnTheConfigauthController(null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<AuthGroupResponse> allAvailableAuthGroupsList =
        LastResponse.received()
            .answeredBy(ORAN_App)
            .getBody()
            .jsonPath()
            .getList("", AuthGroupResponse.class);

    assertThat(allAvailableAuthGroupsList.size()).isGreaterThan(0);
  }

  @Test
  public void AuthGroupsReturnsSpecificAuthGroup() {
    Actor AADM_User = theActorCalled("AADM_User");
    UseAuthTo AuthAPI = new UseAuthTo();

    // set helper variables for validation
    AuthGroupUtility.setAuthGroupHelperVariables(AuthGroupUtility.selectRandomAuthGroup(AADM_User));

    // Test to validate that a specific AuthGroup can be found
    AADM_User.attemptsTo(
        AuthAPI.GETAuthConfigAuthGroupsAuthGroupIdOnTheConfigauthController(
            AuthGroupUtility.currentAGrpId, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AuthGroupResponse authGroupResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", AuthGroupResponse.class)
            .get(0); // should be first and only item in the list

    AuthGroupUtility.validateSingleAuthGroup(authGroupResponse);
  }
}
