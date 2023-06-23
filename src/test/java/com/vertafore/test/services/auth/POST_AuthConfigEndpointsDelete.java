package com.vertafore.test.services.auth;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.EmsEndpointPostPutRequest;
import com.vertafore.test.models.ems.EmsEndpointResponse;
import com.vertafore.test.servicewrappers.UseAuthTo;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_AuthConfigEndpointsDelete extends TokenSuperClass {

  @Test
  public void postAuthConfigEndpointsDeleteBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseAuthTo AuthAPI = new UseAuthTo();

    VADM_Admin.attemptsTo(AuthAPI.GETAuthConfigEndpointsOnTheConfigauthController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<EmsEndpointResponse> emsEndpointResponseList =
        LastResponse.received()
            .answeredBy(ORAN_App)
            .getBody()
            .jsonPath()
            .getList("", EmsEndpointResponse.class);

    // init random category id
    String categoryId = "3c08e07a-0133-4f90-adb3-604960d9f9b7";

    EmsEndpointPostPutRequest emsEndpointPostPutRequest = new EmsEndpointPostPutRequest();
    emsEndpointPostPutRequest.setCategoryId(categoryId);
    emsEndpointPostPutRequest.setEndpointName(emsEndpointResponseList.get(0).getEndpointName());

    List<EmsEndpointPostPutRequest> categoriesList = new ArrayList<>();
    categoriesList.add(emsEndpointPostPutRequest);

    // Created end point
    VADM_Admin.attemptsTo(
        AuthAPI.PUTAuthConfigEndpointsAddOnTheConfigauthController(categoriesList, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Make post delete calls
    AADM_User.attemptsTo(
        AuthAPI.POSTAuthConfigEndpointsDeleteOnTheConfigauthController(categoriesList, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        AuthAPI.POSTAuthConfigEndpointsDeleteOnTheConfigauthController(categoriesList, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    VADM_Admin.attemptsTo(
        AuthAPI.POSTAuthConfigEndpointsDeleteOnTheConfigauthController(categoriesList, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
