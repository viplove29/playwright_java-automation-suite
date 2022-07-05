package com.vertafore.test.services.agency;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.AgenciesPostRequest;
import com.vertafore.test.models.ems.AgencyResponse;
import com.vertafore.test.servicewrappers.UseAgenciesTo;
import com.vertafore.test.util.EnvVariables;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_Agencies extends TokenSuperClass {

  @Test
  public void postAgenciesRetrievesCorrectAgency() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseAgenciesTo agenciesApi = new UseAgenciesTo();

    AgenciesPostRequest postRequest = new AgenciesPostRequest();
    postRequest.setSearchTerm(EnvVariables.AGENCY_NO);

    ORAN_App.attemptsTo(agenciesApi.POSTAgenciesOnTheAgencyController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(agenciesApi.POSTAgenciesOnTheAgencyController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(agenciesApi.POSTAgenciesOnTheAgencyController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AgencyResponse response =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", AgencyResponse.class)
            .get(0);

    assertThat(response).isNotNull();
    assertThat(response.getAgencyNo()).isEqualTo(EnvVariables.AGENCY_NO);
    assertThat(response.getClass().getDeclaredFields().length).isEqualTo(19);
  }
}
