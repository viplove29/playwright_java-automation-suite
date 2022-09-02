package com.vertafore.test.services.activities;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ActivityIdResponse;
import com.vertafore.test.models.ems.DocAttachmentResponse;
import com.vertafore.test.servicewrappers.UseActivityTo;
import com.vertafore.test.util.ActivityUtil;
import com.vertafore.test.util.Util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class GET_ActivityAttachmentInfo extends TokenSuperClass {

  @Test
  public void getActivityAttachmentInfoGetsAttachmentInfo() throws IOException {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    // stage activity and append an attachment to it to retrieve later
    ActivityIdResponse randomActivity = ActivityUtil.postRandomActivity(AADM_User);
    String comment = "activity attachment info test";
    String activityId = randomActivity.getActivityId();

    // create file attachment
    String filename = "GET_Activity_Attachment_Info_Test_File";
    String extension = "txt";
    File attachment = Util.createTestFile(filename + "." + extension);
    FileWriter fileWriter = new FileWriter(attachment);

    fileWriter.write("attachment data");
    fileWriter.close();

    DocAttachmentResponse response =
        ActivityUtil.postAttachmentToActivity(activityId, attachment, comment, AADM_User);

    // delete test file
    attachment.delete();

    UseActivityTo activityApi = new UseActivityTo();

    // send the requests
    ORAN_App.attemptsTo(
        activityApi.GETActivityAttachmentInfoOnTheActivitiesController(
            response.getAttachmentId(), ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        activityApi.GETActivityAttachmentInfoOnTheActivitiesController(
            response.getAttachmentId(), ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        activityApi.GETActivityAttachmentInfoOnTheActivitiesController(
            response.getAttachmentId(), ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // validate the response
    DocAttachmentResponse attachmentResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", DocAttachmentResponse.class)
            .get(0);

    assertThat(attachmentResponse).isNotNull();
    assertThat(attachmentResponse.getComment()).isEqualTo(comment);
    assertThat(attachmentResponse.getFileName()).isEqualTo(filename);
    assertThat(attachmentResponse.getFileExt()).isEqualTo(extension.toUpperCase());
  }
}
