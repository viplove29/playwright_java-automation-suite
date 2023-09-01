package com.vertafore.test.services.third_party_integration;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.WorksmartWebAttributesPostRequest;
import com.vertafore.test.models.ems.WorksmartWebAttributesResponse;
import com.vertafore.test.servicewrappers.UseThirdPartyIntegrationTo;
import com.vertafore.test.util.WorksmartUtil;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.After;
import org.junit.Test;

public class POST_ThirdPartyIntegrationDocumentManagementWorksmartWebAttributes
    extends TokenSuperClass {

  private String initialWorksmartFileAttribute;
  private String initialWorksmartFolderAttribute;
  private String initialWorksmartBaseUrl;

  @Test
  public void
      postThirdPartyIntegrationDocumentManagementWorksmartWebAttributesLookupBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseThirdPartyIntegrationTo thirdPartyIntegrationApi = new UseThirdPartyIntegrationTo();

    // save original web attributes before they are modified
    WorksmartWebAttributesResponse worksmartWebAttributesResponse =
        WorksmartUtil.getWorksmartWebAttributes(AADM_User);
    initialWorksmartFileAttribute = worksmartWebAttributesResponse.getFileAttribute();
    initialWorksmartFolderAttribute = worksmartWebAttributesResponse.getFolderAttribute();
    initialWorksmartBaseUrl = worksmartWebAttributesResponse.getBaseUrl();

    WorksmartWebAttributesPostRequest worksmartWebAttributesPostRequest =
        new WorksmartWebAttributesPostRequest();
    worksmartWebAttributesPostRequest.setFileAttribute(WorksmartUtil.testFileAttribute);
    worksmartWebAttributesPostRequest.setFolderAttribute(WorksmartUtil.testFolderAttribute);
    worksmartWebAttributesPostRequest.setBaseUrl(WorksmartUtil.testBaseUrl);

    ORAN_App.attemptsTo(
        thirdPartyIntegrationApi
            .POSTThirdPartyIntegrationDocumentManagementWorksmartWebAttributesOnTheThirdpartyintegrationController(
                worksmartWebAttributesPostRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        thirdPartyIntegrationApi
            .POSTThirdPartyIntegrationDocumentManagementWorksmartWebAttributesOnTheThirdpartyintegrationController(
                worksmartWebAttributesPostRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // reset the web attributes for AADM_User because ORAN_App updated them
    WorksmartUtil.setWorksmartWebAttributes(
        AADM_User,
        initialWorksmartFileAttribute,
        initialWorksmartFolderAttribute,
        initialWorksmartBaseUrl);

    AADM_User.attemptsTo(
        thirdPartyIntegrationApi
            .POSTThirdPartyIntegrationDocumentManagementWorksmartWebAttributesOnTheThirdpartyintegrationController(
                worksmartWebAttributesPostRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    WorksmartWebAttributesResponse worksmartWebAttributes =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", WorksmartWebAttributesResponse.class);
    assertThat(worksmartWebAttributes.getClass().getDeclaredFields().length).isEqualTo(3);
    assertThat(worksmartWebAttributes.getFileAttribute()).isEqualTo(initialWorksmartFileAttribute);
    assertThat(worksmartWebAttributes.getFolderAttribute())
        .isEqualTo(initialWorksmartFolderAttribute);
    assertThat(worksmartWebAttributes.getBaseUrl()).isEqualTo(initialWorksmartBaseUrl);

    // verify web attributes are updated
    WorksmartWebAttributesResponse updatedWorksmartWebAttributes =
        WorksmartUtil.getWorksmartWebAttributes(AADM_User);
    assertThat(updatedWorksmartWebAttributes.getClass().getDeclaredFields().length).isEqualTo(3);
    assertThat(updatedWorksmartWebAttributes.getFileAttribute())
        .isEqualTo(WorksmartUtil.testFileAttribute);
    assertThat(updatedWorksmartWebAttributes.getFolderAttribute())
        .isEqualTo(WorksmartUtil.testFolderAttribute);
    assertThat(updatedWorksmartWebAttributes.getBaseUrl()).isEqualTo(WorksmartUtil.testBaseUrl);
  }

  @After
  public void resetWebAttributes() {
    // reset the web attributes to what they were prior to the test
    Actor AADM_User = theActorCalled("AADM_User");
    WorksmartUtil.setWorksmartWebAttributes(
        AADM_User,
        initialWorksmartFileAttribute,
        initialWorksmartFolderAttribute,
        initialWorksmartBaseUrl);
  }
}
