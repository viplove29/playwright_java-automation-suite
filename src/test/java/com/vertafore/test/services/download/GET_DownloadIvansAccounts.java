package com.vertafore.test.services.download;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.servicewrappers.UseDownloadTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class GET_DownloadIvansAccounts extends TokenSuperClass {

  @Test
  public void getDownloadIvansAccountsBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseDownloadTo downloadAPI = new UseDownloadTo();
    VADM_Admin.attemptsTo(downloadAPI.GETDownloadIvansAccountsOnTheDownloadController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(downloadAPI.GETDownloadIvansAccountsOnTheDownloadController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(downloadAPI.GETDownloadIvansAccountsOnTheDownloadController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
