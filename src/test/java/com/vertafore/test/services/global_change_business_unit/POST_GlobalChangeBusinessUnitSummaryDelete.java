package com.vertafore.test.services.global_change_business_unit;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.BasicPolicyInfoResponse;
import com.vertafore.test.models.ems.GlobalChangeBusinessUnitResponse;
import com.vertafore.test.models.ems.GlobalChangeBusinessUnitSummaryPostRequest;
import com.vertafore.test.servicewrappers.UseGlobalChangeTo;
import com.vertafore.test.util.CSVUtil;
import com.vertafore.test.util.GlobalChangeUtil;
import com.vertafore.test.util.PolicyUtil;
import java.util.HashMap;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_GlobalChangeBusinessUnitSummaryDelete extends TokenSuperClass {

  @Test
  public void postGlobalChangeBusinessUnitSummaryDeleteBaselineTest() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    BasicPolicyInfoResponse randomPolicy = PolicyUtil.selectRandomPolicy(AADM_User, "policy");
    String randomPolicyId = randomPolicy.getPolicyId();
    String csvRow = CSVUtil.generateGlobalChangeBUCSVRowFromPolicy(AADM_User, randomPolicy);

    String csvHeaders = CSVUtil.generateGlobalChangeBUCSVHeaders("policy", false);
    String csv = csvHeaders + csvRow + System.lineSeparator();
    HashMap<String, Object> body1 =
        GlobalChangeUtil.getBodyForGlobalChangeBusinessUnit(
            CSVUtil.toByteArray(csv),
            "policy",
            "EMS Automation",
            CSVUtil.generateUniqueFilename("GCBU"));

    UseGlobalChangeTo globalChangeApi = new UseGlobalChangeTo();

    AADM_User.attemptsTo(
        globalChangeApi.PUTGlobalChangeBusinessUnitImportOnTheBusinessunitglobalchangeController(
            body1, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    GlobalChangeBusinessUnitResponse globalChangeBusinessUnitResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", GlobalChangeBusinessUnitResponse.class);

    GlobalChangeBusinessUnitSummaryPostRequest deletePostRequest =
        new GlobalChangeBusinessUnitSummaryPostRequest();
    deletePostRequest.setGlobalChangeBusinessUnitHeaderId(
        globalChangeBusinessUnitResponse.getGlobalChangeBusinessUnitHeaderId());

    AADM_User.attemptsTo(
        globalChangeApi
            .POSTGlobalChangeBusinessUnitSummaryDeleteOnTheBusinessunitglobalchangeController(
                deletePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ORAN_App.attemptsTo(
        globalChangeApi
            .POSTGlobalChangeBusinessUnitSummaryDeleteOnTheBusinessunitglobalchangeController(
                deletePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        globalChangeApi
            .POSTGlobalChangeBusinessUnitSummaryDeleteOnTheBusinessunitglobalchangeController(
                deletePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }
}
