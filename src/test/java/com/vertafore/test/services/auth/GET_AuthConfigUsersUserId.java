package com.vertafore.test.services.auth;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.servicewrappers.UseAuthTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class GET_AuthConfigUsersUserId extends TokenSuperClass {

  @Test
  public void getAuthConfigUsersUserIdBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseAuthTo AuthAPI = new UseAuthTo();

    String randomUserId = "3c08e07a-0133-4f90-adb3-604960d9f9b7";
    // ORAN TEST
    ORAN_App.attemptsTo(
        AuthAPI.GETAuthConfigUsersUserIdOnTheConfigauthController(randomUserId, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // VADM TEST
    VADM_Admin.attemptsTo(
        AuthAPI.GETAuthConfigUsersUserIdOnTheConfigauthController(randomUserId, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // AADM TEST
    AADM_User.attemptsTo(
        AuthAPI.GETAuthConfigUsersUserIdOnTheConfigauthController(randomUserId, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }
}
