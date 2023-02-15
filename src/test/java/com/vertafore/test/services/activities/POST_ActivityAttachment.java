package com.vertafore.test.services.activities;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ActivityIdResponse;
import com.vertafore.test.models.ems.AppendAttachmentPostRequest;
import com.vertafore.test.models.ems.DocAttachmentResponse;
import com.vertafore.test.servicewrappers.UseActivityTo;
import com.vertafore.test.util.ActivityUtil;
import com.vertafore.test.util.EnvVariables;
import com.vertafore.test.util.Util;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_ActivityAttachment extends TokenSuperClass {

  @Test
  public void activityAttachmentPostsAttachmentToActivity() throws IOException {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseActivityTo activityApi = new UseActivityTo();
    ActivityIdResponse activityToAppendAttachment =
        ActivityUtil.postCustomerRandomActivity(AADM_User);
    AppendAttachmentPostRequest postRequest =
        getActivityAttachmentPostRequest(activityToAppendAttachment.getActivityId());

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

  public AppendAttachmentPostRequest getActivityAttachmentPostRequest(String activityId)
      throws IOException {

    File attachment = Util.createTestFile("Filename.txt");
    AppendAttachmentPostRequest postRequest = new AppendAttachmentPostRequest();
    byte[] data = Files.readAllBytes(attachment.toPath());
    postRequest.setActivityId(activityId);
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

  @Test
  public void postActivityAttachmentWriteMaskTest() throws IOException {
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

    UseActivityTo activityApi = new UseActivityTo();

    // Stage data for customer activity attachment
    ActivityIdResponse activityToAppendAttachment =
        ActivityUtil.postCustomerRandomActivity(AADM_User, customerId);
    AppendAttachmentPostRequest postRequest =
        getActivityAttachmentPostRequest(activityToAppendAttachment.getActivityId());

    // No access user doesn't have access to customer or policy
    AADM_NAUser.attemptsTo(
        activityApi.POSTActivityAttachmentOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No activity was found using the arguments supplied", AADM_NAUser);

    // Customer Business user has read and write access to the customer but no policy access
    AADM_CBUUSER.attemptsTo(
        activityApi.POSTActivityAttachmentOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Policy Business Unit user has read access to customer and read and write access to policy
    AADM_PBUUSER.attemptsTo(
        activityApi.POSTActivityAttachmentOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Executive user has read and write access to customer but no policy access
    AADM_EXECUSER.attemptsTo(
        activityApi.POSTActivityAttachmentOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Policy Personnel user has read access to customer and read and write access to policy
    AADM_PPUser.attemptsTo(
        activityApi.POSTActivityAttachmentOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Service Group user has read only access to customer and no access to policy
    AADM_SGUser.attemptsTo(
        activityApi.POSTActivityAttachmentOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Stage data for policy activity attachment
    activityToAppendAttachment =
        ActivityUtil.postPolicyRandomActivity(AADM_User, customerId, policyId);
    postRequest = getActivityAttachmentPostRequest(activityToAppendAttachment.getActivityId());

    // No access user doesn't have access to customer or policy
    AADM_NAUser.attemptsTo(
        activityApi.POSTActivityAttachmentOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No activity was found using the arguments supplied", AADM_NAUser);

    // Customer Business user has read and write access to the customer but no policy access
    AADM_CBUUSER.attemptsTo(
        activityApi.POSTActivityAttachmentOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No activity was found using the arguments supplied", AADM_CBUUSER);

    // Policy Business Unit user has read access to customer and read and write access to policy
    AADM_PBUUSER.attemptsTo(
        activityApi.POSTActivityAttachmentOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Executive user has read and write access to customer but no policy access
    AADM_EXECUSER.attemptsTo(
        activityApi.POSTActivityAttachmentOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No activity was found using the arguments supplied", AADM_EXECUSER);

    // Policy Personnel user has read access to customer and read and write access to policy
    AADM_PPUser.attemptsTo(
        activityApi.POSTActivityAttachmentOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Service Group user has read only access to customer and no access to policy
    AADM_SGUser.attemptsTo(
        activityApi.POSTActivityAttachmentOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    Util.validateErrorResponseContainsString(
        "No activity was found using the arguments supplied", AADM_SGUser);
  }
}
