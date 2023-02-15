package com.vertafore.test.services.activities;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ActivityIdResponse;
import com.vertafore.test.models.ems.DocAttachmentResponse;
import com.vertafore.test.servicewrappers.UseActivityTo;
import com.vertafore.test.util.ActivityUtil;
import com.vertafore.test.util.EnvVariables;
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
    ActivityIdResponse randomActivity = ActivityUtil.postCustomerRandomActivity(AADM_User);
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

  @Test
  public void getActivityAttachmentInfoReadMaskTest() throws IOException {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor AADM_NAUser = theActorCalled("AADM_NAUser");
    Actor AADM_CBUUSER = theActorCalled("AADM_CBUUser");
    Actor AADM_PBUUSER = theActorCalled("AADM_PBUUser");
    Actor AADM_EXECUSER = theActorCalled("AADM_EXECUser");
    Actor AADM_PPUser = theActorCalled("AADM_PPUser");
    Actor AADM_SGUser = theActorCalled("AADM_SGUser");

    // use the staged customer ID
    String customerId = EnvVariables.READ_WRITE_MASK_CUSTOMER_ID;
    String policyId = EnvVariables.READ_WRITE_MASK_POLICY_ID;

    // stage activity and append an attachment to it to retrieve later
    ActivityIdResponse randomActivity =
        ActivityUtil.postCustomerRandomActivity(AADM_User, customerId);
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
    String attachmentId = response.getAttachmentId();

    UseActivityTo activityApi = new UseActivityTo();

    // No access user doesn't have access to customer or policy
    AADM_NAUser.attemptsTo(
        activityApi.GETActivityAttachmentInfoOnTheActivitiesController(attachmentId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No attachment was found using the arguments supplied", AADM_NAUser);

    // Customer Business user has read and write access to the customer but no policy access
    AADM_CBUUSER.attemptsTo(
        activityApi.GETActivityAttachmentInfoOnTheActivitiesController(attachmentId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Policy Business Unit user has read access to customer and read and write access to policy
    AADM_PBUUSER.attemptsTo(
        activityApi.GETActivityAttachmentInfoOnTheActivitiesController(attachmentId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Executive user has read and write access to customer but no policy access
    AADM_EXECUSER.attemptsTo(
        activityApi.GETActivityAttachmentInfoOnTheActivitiesController(attachmentId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Policy Personnel user has read access to customer and read and write access to policy
    AADM_PPUser.attemptsTo(
        activityApi.GETActivityAttachmentInfoOnTheActivitiesController(attachmentId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Service Group user has read only access to customer and no access to policy
    AADM_SGUser.attemptsTo(
        activityApi.GETActivityAttachmentInfoOnTheActivitiesController(attachmentId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // stage policy activity and append an attachment to it to retrieve later
    randomActivity = ActivityUtil.postPolicyRandomActivity(AADM_User, customerId, policyId);
    activityId = randomActivity.getActivityId();

    response = ActivityUtil.postAttachmentToActivity(activityId, attachment, comment, AADM_User);
    attachmentId = response.getAttachmentId();

    // delete test file
    attachment.delete();

    // No access user doesn't have access to customer or policy
    AADM_NAUser.attemptsTo(
        activityApi.GETActivityAttachmentInfoOnTheActivitiesController(attachmentId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No attachment was found using the arguments supplied", AADM_NAUser);

    // Customer Business user has read and write access to the customer but no policy access
    AADM_CBUUSER.attemptsTo(
        activityApi.GETActivityAttachmentInfoOnTheActivitiesController(attachmentId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No attachment was found using the arguments supplied", AADM_CBUUSER);

    // Policy Business Unit user has read access to customer and read and write access to policy
    AADM_PBUUSER.attemptsTo(
        activityApi.GETActivityAttachmentInfoOnTheActivitiesController(attachmentId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Executive user has read and write access to customer but no policy access
    AADM_EXECUSER.attemptsTo(
        activityApi.GETActivityAttachmentInfoOnTheActivitiesController(attachmentId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No attachment was found using the arguments supplied", AADM_EXECUSER);

    // Policy Personnel user has read access to customer and read and write access to policy
    AADM_PPUser.attemptsTo(
        activityApi.GETActivityAttachmentInfoOnTheActivitiesController(attachmentId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Service Group user has read only access to customer and no access to policy
    AADM_SGUser.attemptsTo(
        activityApi.GETActivityAttachmentInfoOnTheActivitiesController(attachmentId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No attachment was found using the arguments supplied", AADM_SGUser);
  }
}
