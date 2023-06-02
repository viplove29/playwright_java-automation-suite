package com.vertafore.test.services.agency;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.GlobalTenantIdPostRequest;
import com.vertafore.test.servicewrappers.UseAgencyTo;
import com.vertafore.test.util.EnvVariables;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class POST_AgencyGlobalTenantId extends TokenSuperClass {

  @Test
  public void postAgencyGlobalTenantIdBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseAgencyTo agencyapi = new UseAgencyTo();

    String randomApplicationId = "4f2d2f90-159d-494c-b65c-c4e04e290e06";
    String randomAmsCustId = "b3e3bb0b-ff73-491c-9afd-a0d61bc04809";

    GlobalTenantIdPostRequest globalTenantIdPostRequest = new GlobalTenantIdPostRequest();
    globalTenantIdPostRequest.setAgencyNo(EnvVariables.AGENCY_NO);
    globalTenantIdPostRequest.setApplicationId(randomApplicationId);
    globalTenantIdPostRequest.setAmsCustId(randomAmsCustId);

    ORAN_App.attemptsTo(
        agencyapi.POSTAgencyGlobalTenantIdOnTheAgencyController(globalTenantIdPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        agencyapi.POSTAgencyGlobalTenantIdOnTheAgencyController(globalTenantIdPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        agencyapi.POSTAgencyGlobalTenantIdOnTheAgencyController(globalTenantIdPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
