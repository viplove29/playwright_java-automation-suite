package com.vertafore.test.services.auth;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.AppAccessToAgencyPutRequest;
import com.vertafore.test.models.ems.BusinessUnitAccessGroupResponse;
import com.vertafore.test.servicewrappers.UseAuthTo;
import com.vertafore.test.servicewrappers.UseBusinessUnitsTo;
import com.vertafore.test.util.EnvVariables;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Assert;
import org.junit.Test;

public class PUT_AuthConfigAgencyKeysGenerate extends TokenSuperClass {

  @Test
  public void putAuthConfigAgencyKeysGenerateBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    // Get BuAcsId - Unique Identifier for the Business Unit Access group
    UseBusinessUnitsTo buGroupsAPI = new UseBusinessUnitsTo();

    AADM_User.attemptsTo(buGroupsAPI.GETBusinessUnitsGroupsOnTheBusinessunitsController());
    List<BusinessUnitAccessGroupResponse> buList =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", BusinessUnitAccessGroupResponse.class);
    if (buList.isEmpty()) {
      Assert.fail("No business units found to generate Agency keys");
    }
    String buAcsId = buList.get(0).getId();

    UseAuthTo AuthAPI = new UseAuthTo();

    List<AppAccessToAgencyPutRequest> putRequestList = new ArrayList<>();
    AppAccessToAgencyPutRequest request = new AppAccessToAgencyPutRequest();
    request.setAppId(EnvVariables.ORAN_APP_ID);
    request.setIsActive(true);
    request.setExpirationDate("9999-01-01");
    request.setBuAcsId(buAcsId);

    putRequestList.add(request);

    ORAN_App.attemptsTo(
        AuthAPI.PUTAuthConfigAgencyKeysGenerateOnTheConfigauthController(putRequestList, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        AuthAPI.PUTAuthConfigAgencyKeysGenerateOnTheConfigauthController(putRequestList, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        AuthAPI.PUTAuthConfigAgencyKeysGenerateOnTheConfigauthController(putRequestList, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
