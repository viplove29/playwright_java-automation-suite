package com.vertafore.test.services.agency;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.AgencyMaintenanceWindowsResponse;
import com.vertafore.test.servicewrappers.UseAgencyTo;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_AgencyMaintenanceWindows extends TokenSuperClass {

  @Test
  public void getAgencyMaintenanceWindowsGetsMaintenanceWindows() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseAgencyTo agencyApi = new UseAgencyTo();

    ORAN_App.attemptsTo(agencyApi.GETAgencyMaintenanceWindowsOnTheAgencyController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(agencyApi.GETAgencyMaintenanceWindowsOnTheAgencyController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(agencyApi.GETAgencyMaintenanceWindowsOnTheAgencyController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AgencyMaintenanceWindowsResponse windowsResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", AgencyMaintenanceWindowsResponse.class);

    assertThat(windowsResponse).isNotNull();
    assertThat(windowsResponse.getClass().getDeclaredFields().length).isEqualTo(2);
  }
}
