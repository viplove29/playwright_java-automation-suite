package com.vertafore.test.services.tracking;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.TrackingActionResponse;
import com.vertafore.test.models.ems.TrackingSearchByActionPostRequest;
import com.vertafore.test.servicewrappers.UseTrackingTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_TrackingUserActionsSearch extends TokenSuperClass {

  @Test
  public void postTrackingUserActionsSearch() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseTrackingTo trackingApi = new UseTrackingTo();
    TrackingSearchByActionPostRequest trackingPostRequest = new TrackingSearchByActionPostRequest();
    trackingPostRequest.setAppCode(1);

    ORAN_App.attemptsTo(
        trackingApi.POSTTrackingUserActionsSearchOnTheTrackingController(trackingPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    VADM_Admin.attemptsTo(
        trackingApi.POSTTrackingUserActionsSearchOnTheTrackingController(trackingPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        trackingApi.POSTTrackingUserActionsSearchOnTheTrackingController(trackingPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    TrackingActionResponse response =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", TrackingActionResponse.class);

    assertThat(response).isNotNull();
    assertThat(response.getClass().getDeclaredFields().length).isEqualTo(2);
    assertThat(response.getClass().getDeclaredFields()[0].getName()).isEqualTo("actionName");
    assertThat(response.getClass().getDeclaredFields()[1].getName()).isEqualTo("actionCount");
  }
}
