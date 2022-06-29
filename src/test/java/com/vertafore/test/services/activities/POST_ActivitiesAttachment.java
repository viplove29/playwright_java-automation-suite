package com.vertafore.test.services.activities;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseActivitiesTo;
import com.vertafore.test.servicewrappers.UseActivityTo;
import com.vertafore.test.util.ActivityUtil;
import com.vertafore.test.util.CustomerUtil;
import com.vertafore.test.util.Util;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_ActivitiesAttachment extends TokenSuperClass {

  @Test
  public void postActivitiesAttachmentAppendsAttachmentToActivities() throws IOException {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    List<ActivityIdResponse> activitiesToAppendAttachment =
        stageActivitiesToAppendAttachmentTo(AADM_User);
    List<String> activityIds =
        activitiesToAppendAttachment
            .stream()
            .map(ActivityIdResponse::getActivityId)
            .collect(Collectors.toList());

    UseActivitiesTo activitiesApi = new UseActivitiesTo();

    File attachment = Util.createTestFile("Filename.txt");
    MultiActivityAttachmentPostRequest postRequest = new MultiActivityAttachmentPostRequest();
    byte[] data = Files.readAllBytes(attachment.toPath());
    postRequest.setActivityIds(activityIds);
    postRequest.setComment("comment");
    postRequest.setData(data);
    postRequest.setFileSizeInBytes(data.length);
    postRequest.setSourceFileName(attachment.getName());
    postRequest.setCompressed(false);

    // we already have necessary data
    // clean up file before tests, in case tests fail
    attachment.delete();

    VADM_Admin.attemptsTo(
        activitiesApi.POSTActivitiesAttachmentOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        activitiesApi.POSTActivitiesAttachmentOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        activitiesApi.POSTActivitiesAttachmentOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    DocAttachmentResponse response =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", DocAttachmentResponse.class);
    assertThat(response.getAttachmentId()).isNotNull();
  }

  public List<ActivityIdResponse> stageActivitiesToAppendAttachmentTo(Actor actor) {
    UseActivityTo activityApi = new UseActivityTo();
    List<ActivityIdResponse> activities = new ArrayList<>();

    for (int i = 0; i < 2; i++) {
      String action = ActivityUtil.getRandomActivityAction(actor);
      ActivityPostRequest activity = new ActivityPostRequest();
      String customerId = CustomerUtil.selectRandomCustomer(actor, "customer").getCustomerId();
      activity.setAction(action);
      activity.setDescription("Description");
      activity.setCustomerId(customerId);
      activity.setEntityId(customerId);

      actor.attemptsTo(activityApi.POSTActivityOnTheActivitiesController(activity, ""));
      ActivityIdResponse response =
          LastResponse.received()
              .answeredBy(actor)
              .getBody()
              .jsonPath()
              .getObject("", ActivityIdResponse.class);

      activities.add(response);
    }

    return activities;
  }
}
