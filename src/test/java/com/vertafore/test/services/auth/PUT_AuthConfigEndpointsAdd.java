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
import org.junit.After;
import org.junit.Test;

public class PUT_AuthConfigEndpointsAdd extends TokenSuperClass {

  List<EmsEndpointPostPutRequest> endpointPostPutRequests;

  @Test
  public void putAuthConfigEndpointsAddBaselineTest() {
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

    endpointPostPutRequests = new ArrayList<>();
    endpointPostPutRequests.add(emsEndpointPostPutRequest);

    // ORAN TEST
    ORAN_App.attemptsTo(
        AuthAPI.PUTAuthConfigEndpointsAddOnTheConfigauthController(
            endpointPostPutRequests, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // AADM TEST
    AADM_User.attemptsTo(
        AuthAPI.PUTAuthConfigEndpointsAddOnTheConfigauthController(
            endpointPostPutRequests, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // VADM TEST
    VADM_Admin.attemptsTo(
        AuthAPI.PUTAuthConfigEndpointsAddOnTheConfigauthController(
            endpointPostPutRequests, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }

  @After
  public void tearDown() {
    if (endpointPostPutRequests != null) {
      // delete the category.
      Actor VADM_Admin = theActorCalled("VADM_Admin");
      UseAuthTo AuthAPI = new UseAuthTo();
      VADM_Admin.attemptsTo(
          AuthAPI.POSTAuthConfigEndpointsDeleteOnTheConfigauthController(
              endpointPostPutRequests, "string"));
      assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
      endpointPostPutRequests = null;
    }
  }
}
