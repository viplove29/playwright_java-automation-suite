package com.vertafore.test.services.tracking;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.TrackingAgencyResponse;
import com.vertafore.test.models.ems.TrackingSearchByAgencyPostRequest;
import com.vertafore.test.servicewrappers.UseTrackingTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_TrackingUserActionsByAgencySearch extends TokenSuperClass {
  @Test
  public void postTrackingUserActionsByAgencySearch() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");
    UseTrackingTo trackingApi = new UseTrackingTo();
    TrackingSearchByAgencyPostRequest trackingPostRequest = new TrackingSearchByAgencyPostRequest();

    ORAN_App.attemptsTo(
        trackingApi.POSTTrackingUserActionsByAgencySearchOnTheTrackingController(
            trackingPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    VADM_Admin.attemptsTo(
        trackingApi.POSTTrackingUserActionsByAgencySearchOnTheTrackingController(
            trackingPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        trackingApi.POSTTrackingUserActionsByAgencySearchOnTheTrackingController(
            trackingPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    TrackingAgencyResponse response =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", TrackingAgencyResponse.class);

    assertThat(response).isNotNull();
    assertThat(response.getClass().getDeclaredFields().length).isEqualTo(2);
    assertThat(response.getClass().getDeclaredFields()[0].getName()).isEqualTo("agencyNumber");
    assertThat(response.getClass().getDeclaredFields()[1].getName()).isEqualTo("actionCount");
  }
}
