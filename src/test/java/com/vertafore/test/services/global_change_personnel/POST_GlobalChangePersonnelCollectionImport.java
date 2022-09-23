package com.vertafore.test.services.global_change_personnel;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseGlobalChangeTo;
import com.vertafore.test.util.CustomerUtil;
import com.vertafore.test.util.EmployeeUtil;
import com.vertafore.test.util.PolicyUtil;
import java.util.Objects;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_GlobalChangePersonnelCollectionImport extends TokenSuperClass {

  @Test
  public void globalChangePersonnelImportForCustomerIsSuccessful() {
    Actor AADM_User = theActorCalled("AADM_User");

    BasicPolicyInfoResponse randomPolicy = PolicyUtil.selectRandomPolicy(AADM_User, "policy");
    int customerNumber =
        Objects.requireNonNull(
                CustomerUtil.getCustomerInfoByCustomerId(AADM_User, randomPolicy.getCustomerId()))
            .getCustomerNumber();
    String fromEmployee = randomPolicy.getExecutiveCode();
    String toEmployee = EmployeeUtil.getRandomExec(AADM_User).getEmpCode();

    while (toEmployee.equals(fromEmployee)) {
      toEmployee = EmployeeUtil.getRandomExec(AADM_User).getEmpCode();
    }
    String fromEmployeeShortName =
        Objects.requireNonNull(EmployeeUtil.getEmployeeDetailsByEmpCode(AADM_User, fromEmployee))
            .getShortName();
    String toEmployeeShortName = EmployeeUtil.getRandomExec(AADM_User).getShortName();

    UseGlobalChangeTo gcpApi = new UseGlobalChangeTo();

    GCPCollectionImportPostRequest gcpCollectionImportPostRequest =
        new GCPCollectionImportPostRequest();

    GCPImportPostRequest gcpImportPostRequest = new GCPImportPostRequest();
    gcpImportPostRequest.setPersonnelType("Exec");
    gcpImportPostRequest.setFromPersonnelShortName(fromEmployeeShortName);
    gcpImportPostRequest.setToPersonnelShortName(toEmployeeShortName);
    gcpImportPostRequest.setCustomerNumber(customerNumber);
    gcpCollectionImportPostRequest.addGcpCollectionItem(gcpImportPostRequest);

    gcpCollectionImportPostRequest.setCustomerFileName("AutomationCustomerGCP.csv");
    gcpCollectionImportPostRequest.setCollectionMemo("GCP EMS Automated Test");
    gcpCollectionImportPostRequest.setIncludePersonalSuspenseChanges(false);
    gcpCollectionImportPostRequest.setChangeCustomerSuspense("None");
    gcpCollectionImportPostRequest.setIncludeAlertChanges(false);
    gcpCollectionImportPostRequest.setIncludePersonalNoteChanges(false);
    gcpCollectionImportPostRequest.setSetZeroFromPersonnelCommissionAmounts(false);
    gcpCollectionImportPostRequest.setSetDefaultToPersonnelCommissionAmounts(false);

    AADM_User.attemptsTo(
        gcpApi.POSTGlobalChangePersonnelCollectionImportOnThePersonnelglobalchangeController(
            gcpCollectionImportPostRequest, ""));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    GCPCollectionImportResponse gcpCollectionImportResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", GCPCollectionImportResponse.class);

    assertThat(gcpCollectionImportResponse).isNotNull();
    assertThat(gcpCollectionImportResponse.getClass().getDeclaredFields().length).isEqualTo(2);
    assertThat(gcpCollectionImportResponse.getClass().getDeclaredFields()[0].getName())
        .isEqualTo("globalChangeCollectionId");
    assertThat(gcpCollectionImportResponse.getClass().getDeclaredFields()[1].getName())
        .isEqualTo("eventLogReferenceId");
    assertThat(gcpCollectionImportResponse.getGlobalChangeCollectionId()).isNotNull();
    assertThat(gcpCollectionImportResponse.getEventLogReferenceId()).isNotNull();
  }

  @Test
  public void globalChangePersonnelImportForPolicyIsSuccessful() {
    Actor AADM_User = theActorCalled("AADM_User");

    BasicPolicyInfoResponse randomPolicy = PolicyUtil.selectRandomPolicy(AADM_User, "policy");
    int customerNumber =
        Objects.requireNonNull(
                CustomerUtil.getCustomerInfoByCustomerId(AADM_User, randomPolicy.getCustomerId()))
            .getCustomerNumber();
    String policyId = randomPolicy.getPolicyId();
    String fromEmployee = randomPolicy.getExecutiveCode();
    String toEmployee = EmployeeUtil.getRandomExec(AADM_User).getEmpCode();

    while (toEmployee.equals(fromEmployee)) {
      toEmployee = EmployeeUtil.getRandomExec(AADM_User).getEmpCode();
    }
    String fromEmployeeShortName =
        Objects.requireNonNull(EmployeeUtil.getEmployeeDetailsByEmpCode(AADM_User, fromEmployee))
            .getShortName();
    String toEmployeeShortName = EmployeeUtil.getRandomExec(AADM_User).getShortName();

    UseGlobalChangeTo gcpApi = new UseGlobalChangeTo();

    GCPCollectionImportPostRequest gcpCollectionImportPostRequest =
        new GCPCollectionImportPostRequest();

    GCPImportPostRequest gcpImportPostRequest = new GCPImportPostRequest();
    gcpImportPostRequest.setPersonnelType("Exec");
    gcpImportPostRequest.setFromPersonnelShortName(fromEmployeeShortName);
    gcpImportPostRequest.setToPersonnelShortName(toEmployeeShortName);
    gcpImportPostRequest.setCustomerNumber(customerNumber);
    gcpImportPostRequest.setPolicyId(policyId);
    gcpCollectionImportPostRequest.addGcpCollectionItem(gcpImportPostRequest);

    gcpCollectionImportPostRequest.setPolicyFileName("AutomationPolicyGCP.csv");
    gcpCollectionImportPostRequest.setCollectionMemo("GCP EMS Automated Test");
    gcpCollectionImportPostRequest.setIncludePersonalSuspenseChanges(false);
    gcpCollectionImportPostRequest.setChangeCustomerSuspense("None");
    gcpCollectionImportPostRequest.setIncludeAlertChanges(false);
    gcpCollectionImportPostRequest.setIncludePersonalNoteChanges(false);
    gcpCollectionImportPostRequest.setSetZeroFromPersonnelCommissionAmounts(false);
    gcpCollectionImportPostRequest.setSetDefaultToPersonnelCommissionAmounts(false);

    AADM_User.attemptsTo(
        gcpApi.POSTGlobalChangePersonnelCollectionImportOnThePersonnelglobalchangeController(
            gcpCollectionImportPostRequest, ""));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    GCPCollectionImportResponse gcpCollectionImportResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", GCPCollectionImportResponse.class);

    assertThat(gcpCollectionImportResponse).isNotNull();
    assertThat(gcpCollectionImportResponse.getClass().getDeclaredFields().length).isEqualTo(2);
    assertThat(gcpCollectionImportResponse.getClass().getDeclaredFields()[0].getName())
        .isEqualTo("globalChangeCollectionId");
    assertThat(gcpCollectionImportResponse.getClass().getDeclaredFields()[1].getName())
        .isEqualTo("eventLogReferenceId");
    assertThat(gcpCollectionImportResponse.getGlobalChangeCollectionId()).isNotNull();
    assertThat(gcpCollectionImportResponse.getEventLogReferenceId()).isNotNull();
  }
}
