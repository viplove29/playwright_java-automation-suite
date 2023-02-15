package com.vertafore.test.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseActivityTo;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.serenitybdd.rest.SerenityRest;
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

  public static ActivityIdResponse postCustomerRandomActivity(Actor actor, String customerId) {
    String action = ActivityUtil.getRandomActivityAction(actor);
    ActivityPostRequest activity = new ActivityPostRequest();
    activity.setAction(action);
    activity.setDescription("Description");
    activity.setCustomerId(customerId);
    activity.setEntityId(customerId);

    actor.attemptsTo(activityApi.POSTActivityOnTheActivitiesController(activity, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ActivityIdResponse response =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", ActivityIdResponse.class);

    return response;
  }

  public static ActivityIdResponse postCustomerRandomActivity(Actor actor) {
    String customerId = CustomerUtil.selectRandomCustomer(actor, "customer").getCustomerId();
    return postCustomerRandomActivity(actor, customerId);
  }

  public static ActivityIdResponse postPolicyRandomActivity(
      Actor actor, String customerId, String policyId) {
    String action = ActivityUtil.getRandomActivityAction(actor);
    ActivityPostRequest activity = new ActivityPostRequest();
    activity.setAction(action);
    activity.setDescription("Description");
    activity.setPolicyId(policyId);
    activity.setCustomerId(customerId);
    activity.setEntityId(customerId);

    actor.attemptsTo(activityApi.POSTActivityOnTheActivitiesController(activity, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ActivityIdResponse response =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", ActivityIdResponse.class);

    return response;
  }

  public static DocAttachmentResponse postAttachmentToActivity(
      String activityID, File attachment, String comment, Actor actor) throws IOException {
    AppendAttachmentPostRequest postRequest = new AppendAttachmentPostRequest();
    byte[] data = Files.readAllBytes(attachment.toPath());
    postRequest.setActivityId(activityID);
    postRequest.setComment(comment);
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

  public static MultiActivityAttachmentPostRequest getActivitiesAttachmentPostRequest(
      List<String> activityIds) throws IOException {

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

    return postRequest;
  }

  public static List<ActivityIdResponse> stageMultipleCustomerActivities(
      Actor actor, int numActivities) {
    String customerId = CustomerUtil.selectRandomCustomer(actor, "customer").getCustomerId();
    return stageMultipleCustomerActivities(actor, numActivities, customerId);
  }

  public static List<ActivityIdResponse> stageMultipleCustomerActivities(
      Actor actor, int numActivities, String customerId) {
    UseActivityTo activityApi = new UseActivityTo();
    List<ActivityIdResponse> activities = new ArrayList<>();

    for (int i = 0; i < numActivities; i++) {
      String action = ActivityUtil.getRandomActivityAction(actor);
      ActivityPostRequest activity = new ActivityPostRequest();
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

  public static IVANSeDocsActivityPostRequest getIvansActivityPostRequest(
      String action, String customerId, String policyId) throws IOException {

    File attachment = Util.createTestFile("Filename.txt");
    byte[] data = Files.readAllBytes(attachment.toPath());

    IVANSeDocsActivityPostRequest activity = new IVANSeDocsActivityPostRequest();
    activity.setDescription("Description");
    if (customerId != null && !customerId.isEmpty()) {
      activity.setCustomerId(customerId);
    }
    if (policyId != null && !policyId.isEmpty()) {
      activity.setPolId(policyId);
    }
    if (action != null && !action.isEmpty()) {
      activity.setAction(action);
    }

    ActivityAttachmentPostRequest attachmentPostRequest = new ActivityAttachmentPostRequest();
    attachmentPostRequest.setComments("comment");
    attachmentPostRequest.setData(data);
    attachmentPostRequest.setFileSizeInBytes(data.length);
    attachmentPostRequest.setSourceFileName(attachment.getName());
    attachmentPostRequest.setCompressed(false);

    List<ActivityAttachmentPostRequest> attachmentList = new ArrayList<>();
    attachmentList.add(attachmentPostRequest);
    activity.setAttachments(attachmentList);

    // we already have necessary data
    // clean up file before tests, in case tests fail
    attachment.delete();

    return activity;
  }
}
