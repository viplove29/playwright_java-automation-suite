package com.vertafore.test.services.eforms;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.AcordDataPostRequest;
import com.vertafore.test.servicewrappers.UseEFormsTo;
import com.vertafore.test.util.EnvVariables;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class POST_EformsFormData extends TokenSuperClass {

  @Test
  public void postEformsFormDataBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseEFormsTo eformsApi = new UseEFormsTo();

    String customerId = EnvVariables.READ_WRITE_MASK_CUSTOMER_ID;
    String policyId = EnvVariables.READ_WRITE_MASK_POLICY_ID;

    AcordDataPostRequest acordDataPostRequest = new AcordDataPostRequest();
    acordDataPostRequest.setCustomerId(customerId);
    acordDataPostRequest.setPolicyId(policyId);
    acordDataPostRequest.setFormNumber("125");

    ORAN_App.attemptsTo(
        eformsApi.POSTEFormsFormDataOnTheEformsController(acordDataPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        eformsApi.POSTEFormsFormDataOnTheEformsController(acordDataPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        eformsApi.POSTEFormsFormDataOnTheEformsController(acordDataPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
