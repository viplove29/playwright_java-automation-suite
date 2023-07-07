package com.vertafore.test.services.maintenance_windows;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.MaintenanceWindowIdResponse;
import com.vertafore.test.models.ems.MaintenanceWindowPostRequest;
import com.vertafore.test.models.ems.MaintenanceWindowPutRequest;
import com.vertafore.test.servicewrappers.UseMaintenanceWindowsTo;
import com.vertafore.test.util.Util;
import java.time.LocalDateTime;
import java.util.Arrays;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class PUT_MaintenanceWindows extends TokenSuperClass {

  @Test
  public void putMaintenanceWindowsBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseMaintenanceWindowsTo maintwinAPI = new UseMaintenanceWindowsTo();
    LocalDateTime currentDate = LocalDateTime.now();

    MaintenanceWindowPostRequest maintenanceWindowPostRequest = new MaintenanceWindowPostRequest();
    maintenanceWindowPostRequest.setStartTime(currentDate.toString());
    maintenanceWindowPostRequest.setEnabled(false);
    maintenanceWindowPostRequest.setProdVersion("22R1");
    maintenanceWindowPostRequest.setComments("test comment");
    maintenanceWindowPostRequest.setAgencies(Arrays.asList("MainQAC-1"));

    VADM_Admin.attemptsTo(
        maintwinAPI.POSTMaintenanceWindowsOnTheMaintenancewindowsController(
            maintenanceWindowPostRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    MaintenanceWindowIdResponse maintenanceWindowIdResponse =
        LastResponse.received()
            .answeredBy(ORAN_App)
            .getBody()
            .jsonPath()
            .getObject("", MaintenanceWindowIdResponse.class);

    String mwId = maintenanceWindowIdResponse.getId();

    // update the endpoint

    MaintenanceWindowPutRequest maintenanceWindowPutRequest = new MaintenanceWindowPutRequest();
    maintenanceWindowPutRequest.setId(mwId);
    maintenanceWindowPutRequest.setComments("update comments");
    maintenanceWindowPutRequest.setStartTime(currentDate.toString());
    maintenanceWindowPutRequest.setEnabled(false);

    Util.printObjectAsJson(maintenanceWindowPutRequest);

    AADM_User.attemptsTo(
        maintwinAPI.PUTMaintenanceWindowsOnTheMaintenancewindowsController(
            maintenanceWindowPutRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        maintwinAPI.PUTMaintenanceWindowsOnTheMaintenancewindowsController(
            maintenanceWindowPutRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    VADM_Admin.attemptsTo(
        maintwinAPI.PUTMaintenanceWindowsOnTheMaintenancewindowsController(
            maintenanceWindowPutRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
