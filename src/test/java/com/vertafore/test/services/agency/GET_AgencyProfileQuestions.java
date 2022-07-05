package com.vertafore.test.services.agency;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.servicewrappers.UseAgencyTo;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_AgencyProfileQuestions extends TokenSuperClass {

  @Test
  public void getAgencyProfileQuestionsGetsProfileQuestions() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseAgencyTo agencyApi = new UseAgencyTo();

    AADM_User.attemptsTo(agencyApi.GETAgencyProfileQuestionsOnTheAgencyController(false, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ORAN_App.attemptsTo(agencyApi.GETAgencyProfileQuestionsOnTheAgencyController(false, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(agencyApi.GETAgencyProfileQuestionsOnTheAgencyController(false, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }
}
