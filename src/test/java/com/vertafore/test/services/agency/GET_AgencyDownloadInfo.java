package com.vertafore.test.services.agency;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.servicewrappers.UseAgencyTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class GET_AgencyDownloadInfo extends TokenSuperClass {

  @Test
  public void getAgencyDownloadInfoBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseAgencyTo agencyApi = new UseAgencyTo();

    ORAN_App.attemptsTo(agencyApi.GETAgencyDownloadInfoOnTheAgencyController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(agencyApi.GETAgencyDownloadInfoOnTheAgencyController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(agencyApi.GETAgencyDownloadInfoOnTheAgencyController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
