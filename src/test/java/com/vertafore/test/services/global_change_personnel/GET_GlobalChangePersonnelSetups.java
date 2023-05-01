package com.vertafore.test.services.global_change_personnel;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.servicewrappers.UseGlobalChangeTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class GET_GlobalChangePersonnelSetups extends TokenSuperClass {

  @Test
  public void getGlobalChangePersonnelSetupsBaselineTest() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseGlobalChangeTo gcpApi = new UseGlobalChangeTo();

    AADM_User.attemptsTo(
        gcpApi.GETGlobalChangePersonnelSetupsOnThePersonnelglobalchangeController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ORAN_App.attemptsTo(
        gcpApi.GETGlobalChangePersonnelSetupsOnThePersonnelglobalchangeController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        gcpApi.GETGlobalChangePersonnelSetupsOnThePersonnelglobalchangeController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }
}
