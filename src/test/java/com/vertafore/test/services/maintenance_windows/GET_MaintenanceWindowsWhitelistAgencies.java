package com.vertafore.test.services.maintenance_windows;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.servicewrappers.UseMaintenanceWindowsTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class GET_MaintenanceWindowsWhitelistAgencies extends TokenSuperClass {

  @Test
  public void getMaintenanceWindowsWhitelistAgenciesBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseMaintenanceWindowsTo maintwinAPI = new UseMaintenanceWindowsTo();

    AADM_User.attemptsTo(
        maintwinAPI.GETMaintenanceWindowsWhitelistAgenciesOnTheMaintenancewindowsController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        maintwinAPI.GETMaintenanceWindowsWhitelistAgenciesOnTheMaintenancewindowsController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    VADM_Admin.attemptsTo(
        maintwinAPI.GETMaintenanceWindowsWhitelistAgenciesOnTheMaintenancewindowsController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
