package com.vertafore.test.services.maintenance_windows;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.servicewrappers.UseMaintenanceWindowsTo;
import com.vertafore.test.util.EnvVariables;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_MaintenanceWindowsMaintenanceStatus extends TokenSuperClass {

  @Test
  public void getMaintenanceWindowsMaintenanceStatusBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseMaintenanceWindowsTo maintwinAPI = new UseMaintenanceWindowsTo();

    String agencyNo = EnvVariables.AGENCY_NO;

    // basic status code assertions
    AADM_User.attemptsTo(
        maintwinAPI.GETMaintenanceWindowsMaintenanceStatusOnTheMaintenancewindowsController(
            agencyNo, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        maintwinAPI.GETMaintenanceWindowsMaintenanceStatusOnTheMaintenancewindowsController(
            agencyNo, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    VADM_Admin.attemptsTo(
        maintwinAPI.GETMaintenanceWindowsMaintenanceStatusOnTheMaintenancewindowsController(
            agencyNo, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
