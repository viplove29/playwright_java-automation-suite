package com.vertafore.test.services.maintenance_windows;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.MaintenanceWindowPostRequest;
import com.vertafore.test.servicewrappers.UseMaintenanceWindowsTo;
import java.time.LocalDateTime;
import java.util.Arrays;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_MaintenanceWindows extends TokenSuperClass {

  @Test
  public void postMaintenanceWindowsBaselineTest() {
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

    AADM_User.attemptsTo(
        maintwinAPI.POSTMaintenanceWindowsOnTheMaintenancewindowsController(
            maintenanceWindowPostRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        maintwinAPI.POSTMaintenanceWindowsOnTheMaintenancewindowsController(
            maintenanceWindowPostRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    VADM_Admin.attemptsTo(
        maintwinAPI.POSTMaintenanceWindowsOnTheMaintenancewindowsController(
            maintenanceWindowPostRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
