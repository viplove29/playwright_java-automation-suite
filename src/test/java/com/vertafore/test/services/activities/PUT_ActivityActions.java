package com.vertafore.test.services.activities;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ActionResponse;
import com.vertafore.test.models.ems.ActivityActionPutRequest;
import com.vertafore.test.models.ems.ActivityActionUpdateResponse;
import com.vertafore.test.servicewrappers.UseActivityTo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class PUT_ActivityActions extends TokenSuperClass {

  @Test
  public void PutActivityActionUpdatesActivityAction() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseActivityTo ActivityAPI = new UseActivityTo();

    // Getting a random activity to update
    AADM_User.attemptsTo(ActivityAPI.GETActivityActionsOnTheActivitiesController(null, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<ActionResponse> actions =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", ActionResponse.class);

    ActionResponse activityAction = actions.get(new Random().nextInt(actions.size()));
    String actionId = activityAction.getActionId();
    String oldActionName = activityAction.getActionName();
    String newActionName = "Updated Action";

    ActivityActionPutRequest putRequest = new ActivityActionPutRequest();
    putRequest.setActionId(actionId);
    putRequest.setActionName(newActionName);
    List<ActivityActionPutRequest> pagingRequest = new ArrayList<>();
    pagingRequest.add(putRequest);

    // make the request for 3 contexts
    VADM_Admin.attemptsTo(
        ActivityAPI.PUTActivityActionsOnTheActivitiesController(pagingRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(ActivityAPI.PUTActivityActionsOnTheActivitiesController(pagingRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        ActivityAPI.PUTActivityActionsOnTheActivitiesController(pagingRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // validate the response
    ActivityActionUpdateResponse response =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", ActivityActionUpdateResponse.class)
            .get(0);

    assertThat(response).isNotNull();
    assertThat(response.getActionId()).isEqualTo(actionId);

    // validate the action was updated
    AADM_User.attemptsTo(ActivityAPI.GETActivityActionsOnTheActivitiesController(actionId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    actions =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", ActionResponse.class);

    ActionResponse actionValidated = actions.get(0);
    assertThat(actionValidated).isNotNull();
    assertThat(actionValidated.getActionName()).isEqualTo(newActionName);

    // Reinsert old data
    pagingRequest.clear();
    putRequest.setActionName(oldActionName);
    pagingRequest.add(putRequest);

    AADM_User.attemptsTo(
        ActivityAPI.PUTActivityActionsOnTheActivitiesController(pagingRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
