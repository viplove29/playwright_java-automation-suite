package com.vertafore.test.services.business_units;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.servicewrappers.UseBusinessUnitsTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class GET_BusinessUnitsEmployeeDivisions extends TokenSuperClass {

  @Test
  public void getBusinessUnitsEmployeeDivisionsBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseBusinessUnitsTo buGroupsAPI = new UseBusinessUnitsTo();

    // ORAN TEST
    ORAN_App.attemptsTo(
        buGroupsAPI.GETBusinessUnitsEmployeeDivisionsOnTheBusinessunitsController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // VADM TEST
    VADM_Admin.attemptsTo(
        buGroupsAPI.GETBusinessUnitsEmployeeDivisionsOnTheBusinessunitsController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // AADM TEST
    AADM_User.attemptsTo(
        buGroupsAPI.GETBusinessUnitsEmployeeDivisionsOnTheBusinessunitsController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
