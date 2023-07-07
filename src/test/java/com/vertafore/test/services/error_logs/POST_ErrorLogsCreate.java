package com.vertafore.test.services.error_logs;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ErrorLogCreatePostRequest;
import com.vertafore.test.servicewrappers.UseErrorLogsTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class POST_ErrorLogsCreate extends TokenSuperClass {

  @Test
  public void postErrorLogsCreateBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseErrorLogsTo errorApi = new UseErrorLogsTo();

    ErrorLogCreatePostRequest createPostRequest = new ErrorLogCreatePostRequest();
    createPostRequest.setSeverity(ErrorLogCreatePostRequest.SeverityEnum.ERROR);
    createPostRequest.setProductName("Test Product");
    createPostRequest.setUserId("Test User");
    createPostRequest.setMessage("Test Message");

    VADM_Admin.attemptsTo(
        errorApi.POSTErrorLogsCreateOnTheErrorlogsController(createPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        errorApi.POSTErrorLogsCreateOnTheErrorlogsController(createPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        errorApi.POSTErrorLogsCreateOnTheErrorlogsController(createPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
