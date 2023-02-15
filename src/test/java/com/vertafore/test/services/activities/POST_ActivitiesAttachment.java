package com.vertafore.test.services.activities;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseActivitiesTo;
import com.vertafore.test.util.ActivityUtil;
import com.vertafore.test.util.EnvVariables;
import com.vertafore.test.util.Util;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_ActivitiesAttachment extends TokenSuperClass {

  @Test
  public void postActivitiesAttachmentAppendsAttachmentToActivities() throws IOException {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseActivitiesTo activitiesApi = new UseActivitiesTo();

    List<ActivityIdResponse> activitiesToAppendAttachment =
        ActivityUtil.stageMultipleCustomerActivities(AADM_User, 2);
    List<String> activityIds =
        activitiesToAppendAttachment
            .stream()
            .map(ActivityIdResponse::getActivityId)
            .collect(Collectors.toList());

    MultiActivityAttachmentPostRequest postRequest =
        ActivityUtil.getActivitiesAttachmentPostRequest(activityIds);

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

    UseActivitiesTo activitiesApi = new UseActivitiesTo();

    List<ActivityIdResponse> activitiesToAppendAttachment =
        ActivityUtil.stageMultipleCustomerActivities(AADM_User, 2, customerId);
    List<String> activityIds =
        activitiesToAppendAttachment
            .stream()
            .map(ActivityIdResponse::getActivityId)
            .collect(Collectors.toList());
    MultiActivityAttachmentPostRequest postRequest =
        ActivityUtil.getActivitiesAttachmentPostRequest(activityIds);

    // No access user doesn't have access to customer or policy
    AADM_NAUser.attemptsTo(
        activitiesApi.POSTActivitiesAttachmentOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString(
        "One or more validation errors occurred while processing the request. Please see the error log for more information",
        AADM_NAUser);

    // Customer Business user has read and write access to the customer but no policy access
    AADM_CBUUSER.attemptsTo(
        activitiesApi.POSTActivitiesAttachmentOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Policy Business Unit user has read access to customer and read and write access to policy
    AADM_PBUUSER.attemptsTo(
        activitiesApi.POSTActivitiesAttachmentOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Executive user has read and write access to customer but no policy access
    AADM_EXECUSER.attemptsTo(
        activitiesApi.POSTActivitiesAttachmentOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Policy Personnel user has read access to customer and read and write access to policy
    AADM_PPUser.attemptsTo(
        activitiesApi.POSTActivitiesAttachmentOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Service Group user has read only access to customer and no access to policy
    AADM_SGUser.attemptsTo(
        activitiesApi.POSTActivitiesAttachmentOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
