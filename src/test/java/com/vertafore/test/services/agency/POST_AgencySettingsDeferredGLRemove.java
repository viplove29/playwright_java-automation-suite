package com.vertafore.test.services.agency;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.AgencySettingsPostRequest;
import com.vertafore.test.models.ems.AgencySettingsResponse;
import com.vertafore.test.models.ems.DirectBillDeferredGLAccountPostRequest;
import com.vertafore.test.models.ems.DirectBillDeferredGLNumberResponse;
import com.vertafore.test.servicewrappers.UseAgencyTo;
import java.util.List;
import java.util.stream.Collectors;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_AgencySettingsDeferredGLRemove extends TokenSuperClass {

  @Test
  public void deferredGLGetsRemovedFromAgencySettings() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseAgencyTo agencyApi = new UseAgencyTo();

    // set up data
    DirectBillDeferredGLAccountPostRequest deferredGl =
        new DirectBillDeferredGLAccountPostRequest();
    String deferredGLNumber = "21490000";
    deferredGl.setDeferredGLNumber(deferredGLNumber);
    deferredGl.setSubledgerType("Insurance");

    AgencySettingsPostRequest settingsPostRequest = new AgencySettingsPostRequest();
    settingsPostRequest.addDirectBillDeferredGLAccountsItem(deferredGl);

    // post data in case it doesn't exist
    AADM_User.attemptsTo(
        agencyApi.POSTAgencySettingsOnTheAgencyController(settingsPostRequest, ""));

    // run tests
    ORAN_App.attemptsTo(
        agencyApi.POSTAgencySettingsDeferredGlRemoveOnTheAgencyController(settingsPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        agencyApi.POSTAgencySettingsDeferredGlRemoveOnTheAgencyController(settingsPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        agencyApi.POSTAgencySettingsDeferredGlRemoveOnTheAgencyController(settingsPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // validate
    AADM_User.attemptsTo(agencyApi.GETAgencySettingsOnTheAgencyController());

    AgencySettingsResponse settingsResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", AgencySettingsResponse.class);

    List<String> deferredGLNumbers =
        settingsResponse
            .getDirectBillDeferredGLNumbers()
            .stream()
            .map(DirectBillDeferredGLNumberResponse::getDeferredGLNumber)
            .collect(Collectors.toList());

    assertThat(deferredGLNumbers.contains(deferredGLNumber)).isFalse();
  }
}
