package com.vertafore.test.services.auth;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.AppAccessToEndpointPutRequest;
import com.vertafore.test.models.ems.AuthAppResponse;
import com.vertafore.test.models.ems.EmsEndpointResponse;
import com.vertafore.test.servicewrappers.UseAuthTo;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Assert;
import org.junit.Test;

public class PUT_AuthConfigAppEndpointAccess extends TokenSuperClass {

  @Test
  public void putAuthConfigAppEndpointAccessBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseAuthTo AuthAPI = new UseAuthTo();

    // GET Endpoints
    VADM_Admin.attemptsTo(AuthAPI.GETAuthConfigEndpointsOnTheConfigauthController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<EmsEndpointResponse> endPoints =
        LastResponse.received()
            .answeredBy(VADM_Admin)
            .getBody()
            .jsonPath()
            .getList("", EmsEndpointResponse.class);

    if (endPoints.isEmpty()) {
      Assert.fail("There are no endpoints send as input to PUT auth/config/app-endpoint-access");
    }
    EmsEndpointResponse endPoint = endPoints.get(0);

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

    AppAccessToEndpointPutRequest endpointAccessPutRequest = new AppAccessToEndpointPutRequest();
    endpointAccessPutRequest.setAppId(app.getAppId());
    endpointAccessPutRequest.setEndpointIds(List.of(endPoint.getEndpointId()));

    VADM_Admin.attemptsTo(
        AuthAPI.PUTAuthConfigAppEndpointAccessOnTheConfigauthController(
            endpointAccessPutRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        AuthAPI.PUTAuthConfigAppEndpointAccessOnTheConfigauthController(
            endpointAccessPutRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        AuthAPI.PUTAuthConfigAppEndpointAccessOnTheConfigauthController(
            endpointAccessPutRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }
}
