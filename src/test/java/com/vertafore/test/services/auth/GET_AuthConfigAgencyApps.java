package com.vertafore.test.services.auth;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.AuthAppResponse;
import com.vertafore.test.servicewrappers.UseAuthTo;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class GET_AuthConfigAgencyApps extends TokenSuperClass {

  @Test
  public void authConfigAgencyAppsBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseAuthTo AuthAPI = new UseAuthTo();

    // ORAN TEST
    ORAN_App.attemptsTo(
        AuthAPI.GETAuthConfigAgencyAppsOnTheConfigauthController(null, true, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // VADM TEST
    VADM_Admin.attemptsTo(
        AuthAPI.GETAuthConfigAgencyAppsOnTheConfigauthController(null, true, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // AADM TEST
    AADM_User.attemptsTo(
        AuthAPI.GETAuthConfigAgencyAppsOnTheConfigauthController(null, true, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<AuthAppResponse> authAppsList =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", AuthAppResponse.class);

    assertThat(authAppsList.size()).isGreaterThan(0);
  }
}
