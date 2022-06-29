package com.vertafore.test.services.activities;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ActivityIdResponse;
import com.vertafore.test.models.ems.DocAttachmentDataResponse;
import com.vertafore.test.models.ems.DocAttachmentResponse;
import com.vertafore.test.servicewrappers.UseActivityTo;
import com.vertafore.test.util.ActivityUtil;
import com.vertafore.test.util.Util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class GET_ActivityAttachmentData extends TokenSuperClass {

  @Test
  public void getActivityAttachmentGetsActivityAttachmentData() throws IOException {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    File attachment = Util.createTestFile("Filename.txt");
    FileWriter fileWriter = new FileWriter(attachment);

    fileWriter.write("attachment data");
    fileWriter.close();

    byte[] data = Files.readAllBytes(attachment.toPath());
    String dataAsString = new String(data);
    ActivityIdResponse activityToAppendAttachment = ActivityUtil.postRandomActivity(AADM_User);
    DocAttachmentResponse response =
        ActivityUtil.postAttachmentToActivity(
            activityToAppendAttachment.getActivityId(), attachment, AADM_User);

    attachment.delete();

    UseActivityTo activityApi = new UseActivityTo();
    AADM_User.attemptsTo(
        activityApi.GETActivityAttachmentInfoOnTheActivitiesController(
            response.getAttachmentId(), ""));

    DocAttachmentResponse attachmentResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", DocAttachmentResponse.class)
            .get(0);

    String dataId = attachmentResponse.getDataId();

    ORAN_App.attemptsTo(
        activityApi.GETActivityAttachmentDataOnTheActivitiesController(dataId, false, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        activityApi.GETActivityAttachmentDataOnTheActivitiesController(dataId, false, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        activityApi.GETActivityAttachmentDataOnTheActivitiesController(dataId, false, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    DocAttachmentDataResponse dataResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", DocAttachmentDataResponse.class);

    assertThat(dataResponse.getAttachmentData().equals(dataAsString)).isTrue();
  }
}
