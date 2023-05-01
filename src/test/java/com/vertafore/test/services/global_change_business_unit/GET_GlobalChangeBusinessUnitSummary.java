package com.vertafore.test.services.global_change_business_unit;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.servicewrappers.UseGlobalChangeTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class GET_GlobalChangeBusinessUnitSummary extends TokenSuperClass {

  @Test
  public void getGlobalChangeBusinessUnitSummaryBaselineTest() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseGlobalChangeTo globalChangeToApi = new UseGlobalChangeTo();
    String randomGlobalChangeBusinessUnitHeaderId = "ce7e13f8-b1ea-4729-8ba0-e37b228d49f5";

    AADM_User.attemptsTo(
        globalChangeToApi.GETGlobalChangeBusinessUnitSummaryOnTheBusinessunitglobalchangeController(
            randomGlobalChangeBusinessUnitHeaderId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ORAN_App.attemptsTo(
        globalChangeToApi.GETGlobalChangeBusinessUnitSummaryOnTheBusinessunitglobalchangeController(
            randomGlobalChangeBusinessUnitHeaderId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        globalChangeToApi.GETGlobalChangeBusinessUnitSummaryOnTheBusinessunitglobalchangeController(
            randomGlobalChangeBusinessUnitHeaderId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }
}
