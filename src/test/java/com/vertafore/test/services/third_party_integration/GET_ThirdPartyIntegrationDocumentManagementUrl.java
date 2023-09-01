package com.vertafore.test.services.third_party_integration;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.DocumentManagementURLResponse;
import com.vertafore.test.models.ems.WorksmartWebAttributesResponse;
import com.vertafore.test.servicewrappers.UseThirdPartyIntegrationTo;
import com.vertafore.test.util.WorksmartUtil;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.After;
import org.junit.Test;

public class GET_ThirdPartyIntegrationDocumentManagementUrl extends TokenSuperClass {

  private String initialBaseUrl;

  @Test
  public void getThirdPartyIntegrationDocumentManagementUrlBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseThirdPartyIntegrationTo thirdPartyIntegrationApi = new UseThirdPartyIntegrationTo();

    // save original url before it is modified
    WorksmartWebAttributesResponse worksmartWebAttributesResponse =
        WorksmartUtil.getWorksmartWebAttributes(AADM_User);
    initialBaseUrl = worksmartWebAttributesResponse.getBaseUrl();

    // set a new url to verify that it is present in the response from this endpoint
    WorksmartUtil.setWorksmartBaseURL(AADM_User, WorksmartUtil.testBaseUrl);

    ORAN_App.attemptsTo(
        thirdPartyIntegrationApi
            .GETThirdPartyIntegrationDocumentManagementUrlOnTheThirdpartyintegrationController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        thirdPartyIntegrationApi
            .GETThirdPartyIntegrationDocumentManagementUrlOnTheThirdpartyintegrationController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        thirdPartyIntegrationApi
            .GETThirdPartyIntegrationDocumentManagementUrlOnTheThirdpartyintegrationController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    DocumentManagementURLResponse documentManagementURLResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", DocumentManagementURLResponse.class);
    assertThat(documentManagementURLResponse.getClass().getDeclaredFields().length).isEqualTo(3);

    // base url should match the newly set worksmart base url
    assertThat(documentManagementURLResponse.getBaseUrl()).isEqualTo(WorksmartUtil.testBaseUrl);
    assertThat(documentManagementURLResponse.getClass().getDeclaredFields()[1].getName())
        .isEqualTo("isEnabled");
    assertThat(documentManagementURLResponse.getClass().getDeclaredFields()[2].getName())
        .isEqualTo("menuText");
  }

  @After
  public void resetBaseUrl() {
    // reset base url to what it was prior to the test
    Actor AADM_User = theActorCalled("AADM_User");
    WorksmartUtil.setWorksmartBaseURL(AADM_User, initialBaseUrl);
  }
}
