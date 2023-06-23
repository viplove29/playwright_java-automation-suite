package com.vertafore.test.services.auth;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.AppKeyPutRequest;
import com.vertafore.test.servicewrappers.UseAuthTo;
import com.vertafore.test.util.EnvVariables;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class PUT_AuthConfigAppKeyGenerate extends TokenSuperClass {

  @Test
  public void putAuthConfigAppKeyGenerateBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseAuthTo AuthAPI = new UseAuthTo();

    AppKeyPutRequest appKeyPutRequest = new AppKeyPutRequest();
    appKeyPutRequest.setAppId(EnvVariables.ORAN_APP_ID);
    appKeyPutRequest.setProductKey(true);
    appKeyPutRequest.setRefreshKey(true);
    appKeyPutRequest.setDescription("EMS Automation Test Key");
    appKeyPutRequest.setExpirationDate("9000-01-01");

    // ORAN TEST
    ORAN_App.attemptsTo(
        AuthAPI.PUTAuthConfigAppKeyGenerateOnTheConfigauthController(appKeyPutRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // VADM TEST
    VADM_Admin.attemptsTo(
        AuthAPI.PUTAuthConfigAppKeyGenerateOnTheConfigauthController(appKeyPutRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // AADM TEST
    AADM_User.attemptsTo(
        AuthAPI.PUTAuthConfigAppKeyGenerateOnTheConfigauthController(appKeyPutRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }
}
