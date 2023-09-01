package com.vertafore.test.services.third_party_integration;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.WorksmartWebAttributesResponse;
import com.vertafore.test.servicewrappers.UseThirdPartyIntegrationTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class GET_ThirdPartyIntegrationDocumentManagementWorksmartWebAttributes
    extends TokenSuperClass {

  @Test
  public void getThirdPartyIntegrationDocumentManagementWorksmartWebAttributesLookupBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseThirdPartyIntegrationTo thirdPartyIntegrationApi = new UseThirdPartyIntegrationTo();

    ORAN_App.attemptsTo(
        thirdPartyIntegrationApi
            .GETThirdPartyIntegrationDocumentManagementWorksmartWebAttributesOnTheThirdpartyintegrationController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        thirdPartyIntegrationApi
            .GETThirdPartyIntegrationDocumentManagementWorksmartWebAttributesOnTheThirdpartyintegrationController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        thirdPartyIntegrationApi
            .GETThirdPartyIntegrationDocumentManagementWorksmartWebAttributesOnTheThirdpartyintegrationController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    WorksmartWebAttributesResponse worksmartWebAttributesResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", WorksmartWebAttributesResponse.class);
    assertThat(worksmartWebAttributesResponse.getClass().getDeclaredFields().length).isEqualTo(3);
    assertThat(worksmartWebAttributesResponse.getClass().getDeclaredFields()[0].getName())
        .isEqualTo("fileAttribute");
    assertThat(worksmartWebAttributesResponse.getClass().getDeclaredFields()[1].getName())
        .isEqualTo("folderAttribute");
    assertThat(worksmartWebAttributesResponse.getClass().getDeclaredFields()[2].getName())
        .isEqualTo("baseUrl");
  }
}
