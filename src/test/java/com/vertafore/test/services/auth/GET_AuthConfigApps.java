package com.vertafore.test.services.auth;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.servicewrappers.UseAuthTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class GET_AuthConfigApps extends TokenSuperClass {

  @Test
  public void getAuthConfigAppBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseAuthTo AuthAPI = new UseAuthTo();

    // ORAN TEST
    ORAN_App.attemptsTo(
        AuthAPI.GETAuthConfigAppEndpointAccessWhitelistTreeOnTheConfigauthController(
            null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // VADM TEST
    VADM_Admin.attemptsTo(
        AuthAPI.GETAuthConfigAppEndpointAccessWhitelistTreeOnTheConfigauthController(
            null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // AADM TEST
    AADM_User.attemptsTo(
        AuthAPI.GETAuthConfigAppEndpointAccessWhitelistTreeOnTheConfigauthController(
            null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }
}
