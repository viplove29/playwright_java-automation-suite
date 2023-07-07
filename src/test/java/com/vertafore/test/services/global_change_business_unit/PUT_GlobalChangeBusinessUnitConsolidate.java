package com.vertafore.test.services.global_change_business_unit;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.GCBUDivisionConsolidationPutRequest;
import com.vertafore.test.servicewrappers.UseGlobalChangeTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class PUT_GlobalChangeBusinessUnitConsolidate extends TokenSuperClass {

  @Test
  public void putGlobalChangeBusinessUnitConsolidateBaselineTest() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseGlobalChangeTo globalChangeToApi = new UseGlobalChangeTo();

    GCBUDivisionConsolidationPutRequest gcbuDivisionConsolidationPutRequest =
        new GCBUDivisionConsolidationPutRequest();
    gcbuDivisionConsolidationPutRequest.setSourceDivisionName("Source");
    gcbuDivisionConsolidationPutRequest.setTargetDivisionName("Target");
    gcbuDivisionConsolidationPutRequest.setMemoText("Test Memo");

    AADM_User.attemptsTo(
        globalChangeToApi
            .PUTGlobalChangeBusinessUnitConsolidateOnTheBusinessunitglobalchangeController(
                gcbuDivisionConsolidationPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ORAN_App.attemptsTo(
        globalChangeToApi
            .PUTGlobalChangeBusinessUnitConsolidateOnTheBusinessunitglobalchangeController(
                gcbuDivisionConsolidationPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        globalChangeToApi
            .PUTGlobalChangeBusinessUnitConsolidateOnTheBusinessunitglobalchangeController(
                gcbuDivisionConsolidationPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }
}
