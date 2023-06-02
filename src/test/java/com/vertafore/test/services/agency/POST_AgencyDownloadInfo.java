package com.vertafore.test.services.agency;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.DownloadInformationPostRequest;
import com.vertafore.test.servicewrappers.UseAgencyTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class POST_AgencyDownloadInfo extends TokenSuperClass {

  @Test
  public void postAgencyDownloadInfoBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseAgencyTo agencyApi = new UseAgencyTo();

    DownloadInformationPostRequest downloadInformationPostRequest =
        new DownloadInformationPostRequest();
    downloadInformationPostRequest.setIsAgencyDownloadOn(true);
    downloadInformationPostRequest.setMachineAddress("192.161.4.4.4");
    downloadInformationPostRequest.setIvansUsageCategory("Agency");
    downloadInformationPostRequest.setParticipantCode("some");

    ORAN_App.attemptsTo(
        agencyApi.POSTAgencyDownloadInfoOnTheAgencyController(downloadInformationPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(201);

    VADM_Admin.attemptsTo(
        agencyApi.POSTAgencyDownloadInfoOnTheAgencyController(downloadInformationPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        agencyApi.POSTAgencyDownloadInfoOnTheAgencyController(downloadInformationPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(201);
  }
}
