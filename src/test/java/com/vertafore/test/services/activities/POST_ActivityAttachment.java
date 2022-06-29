package com.vertafore.test.services.activities;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ActivityIdResponse;
import com.vertafore.test.models.ems.AppendAttachmentPostRequest;
import com.vertafore.test.models.ems.DocAttachmentResponse;
import com.vertafore.test.servicewrappers.UseActivityTo;
import com.vertafore.test.util.ActivityUtil;
import com.vertafore.test.util.Util;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_ActivityAttachment extends TokenSuperClass {

  @Test
  public void activityAttachmentPostsAttachmentToActivity() throws IOException {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    ActivityIdResponse activityToAppendAttachment = ActivityUtil.postRandomActivity(AADM_User);

    UseActivityTo activityApi = new UseActivityTo();

    File attachment = Util.createTestFile("Filename.txt");
    AppendAttachmentPostRequest postRequest = new AppendAttachmentPostRequest();
    byte[] data = Files.readAllBytes(attachment.toPath());
    postRequest.setActivityId(activityToAppendAttachment.getActivityId());
    postRequest.setComment("comment");
    postRequest.setData(data);
    postRequest.setFileSizeInBytes(data.length);
    postRequest.setSourceFileName(attachment.getName());
    postRequest.setCompressed(false);

    // we already have necessary data
    // clean up file before tests, in case tests fail
    attachment.delete();

    VADM_Admin.attemptsTo(
        activityApi.POSTActivityAttachmentOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        activityApi.POSTActivityAttachmentOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        activityApi.POSTActivityAttachmentOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    DocAttachmentResponse response =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", DocAttachmentResponse.class);
    assertThat(response.getAttachmentId()).isNotNull();
  }
}
