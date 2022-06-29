package com.vertafore.test.services.agency;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.AgencyResponse;
import com.vertafore.test.servicewrappers.UseAgencyTo;
import com.vertafore.test.util.EnvVariables;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class GET_AgencyDetails extends TokenSuperClass {

  @Test
  public void getAgencyDetailsGetsAgencyDetails() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseAgencyTo agencyApi = new UseAgencyTo();

    ORAN_App.attemptsTo(
        agencyApi.GETAgencyDetailsOnTheAgencyController(EnvVariables.AGENCY_NO, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        agencyApi.GETAgencyDetailsOnTheAgencyController(EnvVariables.AGENCY_NO, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        agencyApi.GETAgencyDetailsOnTheAgencyController(EnvVariables.AGENCY_NO, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AgencyResponse agencyResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", AgencyResponse.class);

    assertThat(agencyResponse).isNotNull();
    assertThat(agencyResponse.getClass().getDeclaredFields().length).isEqualTo(19);
    assertThat(agencyResponse.getAgencyNo()).isEqualTo(EnvVariables.AGENCY_NO);
    assertThat(agencyResponse.getAddress().getClass().getDeclaredFields().length).isEqualTo(5);
  }
}
