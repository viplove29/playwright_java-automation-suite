package com.vertafore.test.services.tracking;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.TrackingPostRequest;
import com.vertafore.test.servicewrappers.UseTrackingTo;
import com.vertafore.test.util.Util;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class POST_TrackingUserRequest extends TokenSuperClass {
  @Test
  public void postTrackingUserRequest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");
    UseTrackingTo trackingApi = new UseTrackingTo();
    TrackingPostRequest trackingPostRequest = new TrackingPostRequest();

    ORAN_App.attemptsTo(
        trackingApi.POSTTrackingUserRequestOnTheTrackingController(trackingPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);

    VADM_Admin.attemptsTo(
        trackingApi.POSTTrackingUserActionsByAgencySearchOnTheTrackingController(
            trackingPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        trackingApi.POSTTrackingUserRequestOnTheTrackingController(trackingPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString("The RequestName field is required.", AADM_User);

    trackingPostRequest.setRequestName("10");
    AADM_User.attemptsTo(
        trackingApi.POSTTrackingUserRequestOnTheTrackingController(trackingPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
