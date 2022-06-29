package com.vertafore.test.services.agency;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.AgencyUrlResponse;
import com.vertafore.test.servicewrappers.UseAgencyTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class GET_AgencyBaseUrl extends TokenSuperClass {

  @Test
  public void getAgencyBaseUrlGetsBaseUrl() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseAgencyTo agencyApi = new UseAgencyTo();

    VADM_Admin.attemptsTo(agencyApi.GETAgencyBaseUrlOnTheAgencyController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(agencyApi.GETAgencyBaseUrlOnTheAgencyController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(agencyApi.GETAgencyBaseUrlOnTheAgencyController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AgencyUrlResponse urlResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", AgencyUrlResponse.class);

    assertThat(urlResponse).isNotNull();
    assertThat(urlResponse.getClass().getDeclaredFields().length).isEqualTo(1);
    assertThat(urlResponse.getAgencyBaseUrl()).isNotNull();
  }
}
