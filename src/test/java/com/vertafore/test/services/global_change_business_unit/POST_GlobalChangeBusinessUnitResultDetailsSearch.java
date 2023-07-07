package com.vertafore.test.services.global_change_business_unit;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.GlobalChangeBusinessUnitDetailPostRequest;
import com.vertafore.test.models.ems.PagingRequestGlobalChangeBusinessUnitDetailPostRequest;
import com.vertafore.test.servicewrappers.UseGlobalChangeTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class POST_GlobalChangeBusinessUnitResultDetailsSearch extends TokenSuperClass {

  @Test
  public void getGlobalChangeBusinessUnitResultDetailsBaselineTest() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseGlobalChangeTo globalChangeToApi = new UseGlobalChangeTo();
    String randomGlobalChangeBusinessUnitDetailId = "9dff7a9e-ef5e-4446-acd4-e225edd0b57e";

    PagingRequestGlobalChangeBusinessUnitDetailPostRequest globalChangeSearchRequest =
        new PagingRequestGlobalChangeBusinessUnitDetailPostRequest();
    GlobalChangeBusinessUnitDetailPostRequest detailPostRequest =
        new GlobalChangeBusinessUnitDetailPostRequest();
    detailPostRequest.setGlobalChangeBusinessUnitHeaderId(randomGlobalChangeBusinessUnitDetailId);

    globalChangeSearchRequest.setModel(detailPostRequest);

    AADM_User.attemptsTo(
        globalChangeToApi
            .POSTGlobalChangeBusinessUnitResultDetailsSearchOnTheBusinessunitglobalchangeController(
                globalChangeSearchRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ORAN_App.attemptsTo(
        globalChangeToApi
            .POSTGlobalChangeBusinessUnitResultDetailsSearchOnTheBusinessunitglobalchangeController(
                globalChangeSearchRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        globalChangeToApi
            .POSTGlobalChangeBusinessUnitResultDetailsSearchOnTheBusinessunitglobalchangeController(
                globalChangeSearchRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }
}
