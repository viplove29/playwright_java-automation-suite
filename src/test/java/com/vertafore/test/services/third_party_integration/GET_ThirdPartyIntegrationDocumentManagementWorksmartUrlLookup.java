package com.vertafore.test.services.third_party_integration;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.BasicPolicyInfoResponse;
import com.vertafore.test.models.ems.WorkSmartUrlResponse;
import com.vertafore.test.models.ems.WorksmartWebAttributesResponse;
import com.vertafore.test.servicewrappers.UseThirdPartyIntegrationTo;
import com.vertafore.test.util.PolicyUtil;
import com.vertafore.test.util.WorksmartUtil;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.After;
import org.junit.Test;

public class GET_ThirdPartyIntegrationDocumentManagementWorksmartUrlLookup extends TokenSuperClass {

  private String initialWorksmartFileAttribute;
  private String initialWorksmartFolderAttribute;
  private String initialWorksmartBaseUrl;

  @Test
  public void getThirdPartyIntegrationDocumentManagementWorksmartUrlLookupBaselineTest() {
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

    // set new web attributes to verify they are present in the generated url in the response
    WorksmartUtil.setWorksmartWebAttributes(
        AADM_User,
        WorksmartUtil.testFileAttribute,
        WorksmartUtil.testFolderAttribute,
        WorksmartUtil.testBaseUrl);

    // get random policy id and matching customer id
    BasicPolicyInfoResponse randomPolicy = PolicyUtil.selectRandomPolicy(AADM_User, "policy");
    String customerId = randomPolicy.getCustomerId();
    String policyId = randomPolicy.getPolicyId();

    ORAN_App.attemptsTo(
        thirdPartyIntegrationApi
            .GETThirdPartyIntegrationDocumentManagementWorksmartUrlLookupOnTheThirdpartyintegrationController(
                policyId, customerId, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        thirdPartyIntegrationApi
            .GETThirdPartyIntegrationDocumentManagementWorksmartUrlLookupOnTheThirdpartyintegrationController(
                policyId, customerId, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        thirdPartyIntegrationApi
            .GETThirdPartyIntegrationDocumentManagementWorksmartUrlLookupOnTheThirdpartyintegrationController(
                policyId, customerId, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    WorkSmartUrlResponse workSmartUrlResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", WorkSmartUrlResponse.class);
    assertThat(workSmartUrlResponse.getClass().getDeclaredFields().length).isEqualTo(1);

    // generated url should contain the base url and the folder attribute set to the policy id
    assertThat(workSmartUrlResponse.getWorkSmartUrl()).contains(WorksmartUtil.testBaseUrl);
    assertThat(workSmartUrlResponse.getWorkSmartUrl()).contains(WorksmartUtil.testFolderAttribute);
    assertThat(workSmartUrlResponse.getWorkSmartUrl()).contains(policyId);
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
