package com.vertafore.test.services.error_logs;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.servicewrappers.UseErrorLogsTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class POST_ErrorLogsCleanup extends TokenSuperClass {

  @Test
  public void postErrorLogsCleanupBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseErrorLogsTo errorApi = new UseErrorLogsTo();

    VADM_Admin.attemptsTo(errorApi.POSTErrorLogsCleanupOnTheErrorlogsController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(errorApi.POSTErrorLogsCleanupOnTheErrorlogsController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(errorApi.POSTErrorLogsCleanupOnTheErrorlogsController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
