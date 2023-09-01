package com.vertafore.test.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.ems.WorksmartWebAttributesPostRequest;
import com.vertafore.test.models.ems.WorksmartWebAttributesResponse;
import com.vertafore.test.servicewrappers.UseThirdPartyIntegrationTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class WorksmartUtil {

  public static final String testFileAttribute = "ClientID";
  public static final String testFolderAttribute = "PolicyID";
  public static final String testBaseUrl =
      "https://webclient.wsol.mdc.vertafore.com/imageright.web.client7/";

  private static UseThirdPartyIntegrationTo thirdPartyIntegrationApi =
      new UseThirdPartyIntegrationTo();

  public static WorksmartWebAttributesResponse getWorksmartWebAttributes(Actor actor) {
    actor.attemptsTo(
        thirdPartyIntegrationApi
            .GETThirdPartyIntegrationDocumentManagementWorksmartWebAttributesOnTheThirdpartyintegrationController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    return LastResponse.received()
        .answeredBy(actor)
        .getBody()
        .jsonPath()
        .getObject("", WorksmartWebAttributesResponse.class);
  }

  public static void setWorksmartWebAttributes(
      Actor actor, String fileAttribute, String folderAttribute, String baseUrl) {
    WorksmartWebAttributesPostRequest worksmartWebAttributesPostRequest =
        new WorksmartWebAttributesPostRequest();
    worksmartWebAttributesPostRequest.setFileAttribute(fileAttribute);
    worksmartWebAttributesPostRequest.setFolderAttribute(folderAttribute);
    worksmartWebAttributesPostRequest.setBaseUrl(baseUrl);
    actor.attemptsTo(
        thirdPartyIntegrationApi
            .POSTThirdPartyIntegrationDocumentManagementWorksmartWebAttributesOnTheThirdpartyintegrationController(
                worksmartWebAttributesPostRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }

  public static void setWorksmartBaseURL(Actor actor, String baseUrl) {
    WorksmartWebAttributesPostRequest worksmartWebAttributesPostRequest =
        new WorksmartWebAttributesPostRequest();
    worksmartWebAttributesPostRequest.setBaseUrl(baseUrl);
    actor.attemptsTo(
        thirdPartyIntegrationApi
            .POSTThirdPartyIntegrationDocumentManagementWorksmartWebAttributesOnTheThirdpartyintegrationController(
                worksmartWebAttributesPostRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
