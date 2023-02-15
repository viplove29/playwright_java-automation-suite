package com.vertafore.test.services.activities;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ActivityIdResponse;
import com.vertafore.test.models.ems.DocAttachmentDataResponse;
import com.vertafore.test.models.ems.DocAttachmentResponse;
import com.vertafore.test.servicewrappers.UseActivityTo;
import com.vertafore.test.util.ActivityUtil;
import com.vertafore.test.util.EnvVariables;
import com.vertafore.test.util.Util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
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
    String comment = "comment";
    ActivityIdResponse activityToAppendAttachment =
        ActivityUtil.postCustomerRandomActivity(AADM_User);
    DocAttachmentResponse response =
        ActivityUtil.postAttachmentToActivity(
            activityToAppendAttachment.getActivityId(), attachment, comment, AADM_User);

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

  @Test
  public void getActivityAttachmentDataReadMaskTest() throws IOException {
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
    File attachment = Util.createTestFile("Filename.txt");
    FileWriter fileWriter = new FileWriter(attachment);

    fileWriter.write("attachment data");
    fileWriter.close();

    byte[] data = Files.readAllBytes(attachment.toPath());
    String comment = "comment";
    ActivityIdResponse activityToAppendAttachment =
        ActivityUtil.postCustomerRandomActivity(AADM_User, customerId);
    DocAttachmentResponse response =
        ActivityUtil.postAttachmentToActivity(
            activityToAppendAttachment.getActivityId(), attachment, comment, AADM_User);

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

    // No access user doesn't have access to customer or policy
    AADM_NAUser.attemptsTo(
        activityApi.GETActivityAttachmentDataOnTheActivitiesController(dataId, false, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No image data was found using the arguments supplied", AADM_NAUser);

    // Customer Business user has read and write access to the customer but no policy access
    AADM_CBUUSER.attemptsTo(
        activityApi.GETActivityAttachmentDataOnTheActivitiesController(dataId, false, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Policy Business Unit user has read access to customer and read and write access to policy
    AADM_PBUUSER.attemptsTo(
        activityApi.GETActivityAttachmentDataOnTheActivitiesController(dataId, false, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Executive user has read and write access to customer but no policy access
    AADM_EXECUSER.attemptsTo(
        activityApi.GETActivityAttachmentDataOnTheActivitiesController(dataId, false, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Policy Personnel user has read access to customer and read and write access to policy
    AADM_PPUser.attemptsTo(
        activityApi.GETActivityAttachmentDataOnTheActivitiesController(dataId, false, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Service Group user has read only access to customer and no access to policy
    AADM_SGUser.attemptsTo(
        activityApi.GETActivityAttachmentDataOnTheActivitiesController(dataId, false, ""));
    // returns error since customer id is submitted
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Stage policy activity and append an attachment to it to retrieve later
    activityToAppendAttachment =
        ActivityUtil.postPolicyRandomActivity(AADM_User, customerId, policyId);
    response =
        ActivityUtil.postAttachmentToActivity(
            activityToAppendAttachment.getActivityId(), attachment, comment, AADM_User);

    attachment.delete();

    AADM_User.attemptsTo(
        activityApi.GETActivityAttachmentInfoOnTheActivitiesController(
            response.getAttachmentId(), ""));

    attachmentResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", DocAttachmentResponse.class)
            .get(0);

    dataId = attachmentResponse.getDataId();

    // No access user doesn't have access to customer or policy
    AADM_NAUser.attemptsTo(
        activityApi.GETActivityAttachmentDataOnTheActivitiesController(dataId, false, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No image data was found using the arguments supplied", AADM_NAUser);

    // Customer Business user has read and write access to the customer but no policy access
    AADM_CBUUSER.attemptsTo(
        activityApi.GETActivityAttachmentDataOnTheActivitiesController(dataId, false, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No image data was found using the arguments supplied", AADM_CBUUSER);

    // Policy Business Unit user has read access to customer and read and write access to policy
    AADM_PBUUSER.attemptsTo(
        activityApi.GETActivityAttachmentDataOnTheActivitiesController(dataId, false, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Executive user has read and write access to customer but no policy access
    AADM_EXECUSER.attemptsTo(
        activityApi.GETActivityAttachmentDataOnTheActivitiesController(dataId, false, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No image data was found using the arguments supplied", AADM_EXECUSER);

    // Policy Personnel user has read access to customer and read and write access to policy
    AADM_PPUser.attemptsTo(
        activityApi.GETActivityAttachmentDataOnTheActivitiesController(dataId, false, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Service Group user has read only access to customer and no access to policy
    AADM_SGUser.attemptsTo(
        activityApi.GETActivityAttachmentDataOnTheActivitiesController(dataId, false, ""));
    // returns error since customer id is submitted
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No image data was found using the arguments supplied", AADM_SGUser);
  }
}
