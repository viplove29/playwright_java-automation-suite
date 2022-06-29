package com.vertafore.test.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseActivityTo;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class ActivityUtil {
  private static UseActivityTo activityApi = new UseActivityTo();

  public static String getRandomActivityAction(Actor actor) {
    actor.attemptsTo(activityApi.GETActivityActionsOnTheActivitiesController());
    List<ActionResponse> actions =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getList("", ActionResponse.class);

    if (actions == null || actions.size() == 0) {
      return null;
    }

    int randomIndex = new Random().nextInt(actions.size());
    return actions.get(randomIndex).getActionName();
  }

  public static ActivityIdResponse postRandomActivity(Actor actor) {

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

    return response;
  }

  public static DocAttachmentResponse postAttachmentToActivity(
      String activityID, File attachment, Actor actor) throws IOException {
    AppendAttachmentPostRequest postRequest = new AppendAttachmentPostRequest();
    byte[] data = Files.readAllBytes(attachment.toPath());
    postRequest.setActivityId(activityID);
    postRequest.setComment("comment");
    postRequest.setData(data);
    postRequest.setFileSizeInBytes(data.length);
    postRequest.setSourceFileName(attachment.getName());
    postRequest.setCompressed(false);

    actor.attemptsTo(activityApi.POSTActivityAttachmentOnTheActivitiesController(postRequest, ""));

    DocAttachmentResponse response =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", DocAttachmentResponse.class);
    assertThat(response.getAttachmentId()).isNotNull();

    return response;
  }
}
