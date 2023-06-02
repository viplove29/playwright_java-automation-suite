package com.vertafore.test.services.grid_config;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.servicewrappers.UseGridConfigTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class GET_GridConfig extends TokenSuperClass {

  @Test
  public void getGridConfigBaselineTest() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseGridConfigTo gridApi = new UseGridConfigTo();

    String gridName = "CustomerListGrid";

    AADM_User.attemptsTo(gridApi.GETGridConfigOnTheGridconfigController(gridName, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ORAN_App.attemptsTo(gridApi.GETGridConfigOnTheGridconfigController(gridName, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(gridApi.GETGridConfigOnTheGridconfigController(gridName, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }
}
