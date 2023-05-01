package com.vertafore.test.services.auth;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.servicewrappers.UseAuthTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class GET_AuthConfigAppEndpointAccessWhiteListTree extends TokenSuperClass {

  @Test
  public void getAuthConfigAppEndpointAccessWhiteListBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseAuthTo AuthAPI = new UseAuthTo();

    String randomAppId = "34dfc22e-12f7-44a9-b754-8c720782e8a4";
    // ORAN TEST
    ORAN_App.attemptsTo(
        AuthAPI.GETAuthConfigAppEndpointAccessWhitelistTreeOnTheConfigauthController(
            randomAppId, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // VADM TEST
    VADM_Admin.attemptsTo(
        AuthAPI.GETAuthConfigAppEndpointAccessWhitelistTreeOnTheConfigauthController(
            randomAppId, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // AADM TEST
    AADM_User.attemptsTo(
        AuthAPI.GETAuthConfigAppEndpointAccessWhitelistTreeOnTheConfigauthController(
            randomAppId, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }
}
