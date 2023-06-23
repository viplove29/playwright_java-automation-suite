package com.vertafore.test.services.auth;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.AuthAppPutRequest;
import com.vertafore.test.models.ems.AuthAppResponse;
import com.vertafore.test.servicewrappers.UseAuthTo;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Assert;
import org.junit.Test;

public class PUT_AuthConfigApp extends TokenSuperClass {

  @Test
  public void putAuthConfigAppBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseAuthTo AuthAPI = new UseAuthTo();

    // GET Apps
    VADM_Admin.attemptsTo(AuthAPI.GETAuthConfigAppsOnTheConfigauthController(null, true, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<AuthAppResponse> apps =
        LastResponse.received()
            .answeredBy(VADM_Admin)
            .getBody()
            .jsonPath()
            .getList("", AuthAppResponse.class);

    if (apps.isEmpty()) {
      Assert.fail("There are no apps send as input to PUT auth/config/app-endpoint-access");
    }

    AuthAppResponse app = apps.get(0);

    AuthAppPutRequest authAppPutRequest = new AuthAppPutRequest();
    authAppPutRequest.setEmsUserId(app.getEmsUserId());
    authAppPutRequest.setAppName("Ems Test Automation " + (int) (Math.random() * 999));
    authAppPutRequest.setAppType(app.getAppType());
    authAppPutRequest.setStatus(app.getStatus());
    authAppPutRequest.setRequiresAgencyWhiteList(app.getRequiresAgencyWhiteList());
    authAppPutRequest.setDescription(app.getDescription());
    authAppPutRequest.setHasSecuredCustomerAccessOption(app.getHasSecuredCustomerAccessOption());

    VADM_Admin.attemptsTo(
        AuthAPI.PUTAuthConfigAppOnTheConfigauthController(authAppPutRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        AuthAPI.PUTAuthConfigAppOnTheConfigauthController(authAppPutRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        AuthAPI.PUTAuthConfigAppOnTheConfigauthController(authAppPutRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }
}
