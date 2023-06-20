package com.vertafore.test.services.error_logs;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.NonAgencyErrorLogsPostRequest;
import com.vertafore.test.models.ems.PagingRequestNonAgencyErrorLogsPostRequest;
import com.vertafore.test.servicewrappers.UseErrorLogsTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class POST_ErrorLogsSearchNonAgency extends TokenSuperClass {

  @Test
  public void postErrorLogsSearchNonAgencyBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseErrorLogsTo errorApi = new UseErrorLogsTo();
    String eventLogId = "073C35D4-C1CB-481A-9B78-640EA0DDBC13";

    PagingRequestNonAgencyErrorLogsPostRequest pagingRequestErrorLogsPostRequest =
        new PagingRequestNonAgencyErrorLogsPostRequest();

    NonAgencyErrorLogsPostRequest errorLogsPostRequest = new NonAgencyErrorLogsPostRequest();
    errorLogsPostRequest.setEventLogId(eventLogId);
    pagingRequestErrorLogsPostRequest.setModel(errorLogsPostRequest);

    VADM_Admin.attemptsTo(
        errorApi.POSTErrorLogsSearchNonAgencyOnTheErrorlogsController(
            pagingRequestErrorLogsPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        errorApi.POSTErrorLogsSearchNonAgencyOnTheErrorlogsController(
            pagingRequestErrorLogsPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        errorApi.POSTErrorLogsSearchNonAgencyOnTheErrorlogsController(
            pagingRequestErrorLogsPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
