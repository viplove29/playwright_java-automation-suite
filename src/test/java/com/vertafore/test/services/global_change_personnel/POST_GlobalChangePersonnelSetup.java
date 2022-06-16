package com.vertafore.test.services.global_change_personnel;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseGlobalChangeTo;
import com.vertafore.test.util.EmployeeUtil;
import com.vertafore.test.util.PolicyUtil;
import com.vertafore.test.util.SuspenseUtil;
import com.vertafore.test.util.Util;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_GlobalChangePersonnelSetup extends TokenSuperClass {

  @Test
  public void personnelReassignmentDateCanBePassedAsNull() {
    Actor AADM_User = theActorCalled("AADM_User");

    BasicPolicyInfoResponse randomPolicy = PolicyUtil.selectRandomPolicy(AADM_User, "policy");
    String randomPolicyId = randomPolicy.getPolicyId();
    String fromEmployee = randomPolicy.getExecutiveCode();
    String toEmployee = EmployeeUtil.getRandomExec(AADM_User).getEmpCode();
    // if it turns out the two employees are the same no gcp processing will occur
    while (toEmployee.equals(fromEmployee)) {
      toEmployee = EmployeeUtil.getRandomExec(AADM_User).getEmpCode();
    }
    UseGlobalChangeTo gcpApi = new UseGlobalChangeTo();
    GCPSetupPostRequest gcpSetupPostRequest = new GCPSetupPostRequest();

    // set required fields for the gcp setup model at the highest level
    gcpSetupPostRequest.setFromPersonnelCode(fromEmployee);
    gcpSetupPostRequest.setToPersonnelCode(toEmployee);
    gcpSetupPostRequest.setPersonnelType("Exec");
    gcpSetupPostRequest.setChangeCustomers(true);
    gcpSetupPostRequest.setChangePolicies(true);
    gcpSetupPostRequest.setIncludePersonalSuspenseChanges(true);
    gcpSetupPostRequest.setChangeCustomerSuspense("All");

    // set required fields in the policy selections sub model
    GCPPolicySelectionsPostRequest policySelectionsPostRequest =
        new GCPPolicySelectionsPostRequest();
    policySelectionsPostRequest.setIncludePolicyLevelPolicies(false);
    policySelectionsPostRequest.setSetDefaultToPersonnelCommissionAmounts(false);
    policySelectionsPostRequest.setExcludeExpiringPolicies(false);
    policySelectionsPostRequest.setDefaultToPersonnelCommissionAmounts(false);
    gcpSetupPostRequest.setPolicySelections(policySelectionsPostRequest);

    // set personnelReassignmentDate equal to null in the policy selections sub model for the
    // request body
    String nullReassignmentDateModel =
        Util.addNullFieldToSubModel(
            gcpSetupPostRequest, "policySelections", "PersonnelReassignmentDate");

    // send the model with the serialized null field passed as a string, it is possible to do this
    // rather than a typical
    // java object/hashmap with how the serialization works
    AADM_User.attemptsTo(
        gcpApi.POSTGlobalChangePersonnelSetupOnThePersonnelglobalchangeController(
            nullReassignmentDateModel, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // validate the response
    GCPSetupResponse gcpSetupResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", GCPSetupResponse.class);

    assertThat(gcpSetupResponse).isNotNull();
    assertThat(gcpSetupResponse.getClass().getDeclaredFields().length).isEqualTo(2);
    assertThat(gcpSetupResponse.getClass().getDeclaredFields()[0].getName())
        .isEqualTo("globalChangeHeaderId");
    assertThat(gcpSetupResponse.getClass().getDeclaredFields()[1].getName())
        .isEqualTo("enteredDate");
  }

  @Test
  public void optionalFieldsCanBeLeftOutOfSetupBody() {
    Actor AADM_User = theActorCalled("AADM_User");

    BasicPolicyInfoResponse randomPolicy = PolicyUtil.selectRandomPolicy(AADM_User, "policy");
    String fromEmployee = randomPolicy.getExecutiveCode();
    String toEmployee = EmployeeUtil.getRandomEmployee(AADM_User).getEmpCode();

    // if it turns out the two employees are the same no gcp processing will occur
    while (toEmployee.equals(fromEmployee)) {
      toEmployee = EmployeeUtil.getRandomExec(AADM_User).getEmpCode();
    }
    UseGlobalChangeTo gcpApi = new UseGlobalChangeTo();
    GCPSetupPostRequest gcpSetupPostRequest = new GCPSetupPostRequest();

    // set required fields for the gcp setup model at the highest level
    gcpSetupPostRequest.setFromPersonnelCode(fromEmployee);
    gcpSetupPostRequest.setToPersonnelCode(toEmployee);
    gcpSetupPostRequest.setPersonnelType("Exec");
    gcpSetupPostRequest.setChangeCustomers(true);
    gcpSetupPostRequest.setChangePolicies(true);

    AADM_User.attemptsTo(
        gcpApi.POSTGlobalChangePersonnelSetupOnThePersonnelglobalchangeController(
            gcpSetupPostRequest, ""));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // validate the response
    GCPSetupResponse gcpSetupResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", GCPSetupResponse.class);

    assertThat(gcpSetupResponse).isNotNull();
    assertThat(gcpSetupResponse.getClass().getDeclaredFields().length).isEqualTo(2);
    assertThat(gcpSetupResponse.getClass().getDeclaredFields()[0].getName())
        .isEqualTo("globalChangeHeaderId");
    assertThat(gcpSetupResponse.getClass().getDeclaredFields()[1].getName())
        .isEqualTo("enteredDate");
  }

  @Test
  public void suspenseStatusUpdatesAppropriately() {
    Actor AADM_User = theActorCalled("AADM_User");

    BasicPolicyInfoResponse randomPolicy = PolicyUtil.selectRandomPolicy(AADM_User, "policy");
    String randomCustomerId = randomPolicy.getCustomerId();
    String randomPolicyId = randomPolicy.getPolicyId();
    String fromEmployee = randomPolicy.getExecutiveCode();
    String toEmployee = EmployeeUtil.getRandomExec(AADM_User).getEmpCode();

    // if it turns out the two employees are the same no gcp processing will occur
    while (toEmployee.equals(fromEmployee)) {
      toEmployee = EmployeeUtil.getRandomExec(AADM_User).getEmpCode();
    }

    // get the original number of suspenses the fromEmployee has to compare against later
    int originalNumSuspenses =
        SuspenseUtil.getNumberOfSuspensesTiedToEmployee(AADM_User, fromEmployee, "P");
    assertThat(originalNumSuspenses).isGreaterThan(-1);

    // insert the three types of suspenses under the fromEmployee, its customer and policy
    SuspenseUtil.insertAllSuspenseTypesUnderEmployee(
        AADM_User, fromEmployee, randomCustomerId, randomPolicyId);

    UseGlobalChangeTo gcpApi = new UseGlobalChangeTo();
    GCPSetupPostRequest gcpSetupPostRequest = new GCPSetupPostRequest();

    // set required fields for the GCP request model
    gcpSetupPostRequest.setFromPersonnelCode(fromEmployee);
    gcpSetupPostRequest.setToPersonnelCode(toEmployee);
    gcpSetupPostRequest.setPersonnelType("Exec");
    gcpSetupPostRequest.setChangeCustomers(true);
    gcpSetupPostRequest.setChangePolicies(true);
    gcpSetupPostRequest.setIncludePersonalSuspenseChanges(true);
    gcpSetupPostRequest.setChangeCustomerSuspense("All");
    gcpSetupPostRequest.setIncludeAlertChanges(false);
    gcpSetupPostRequest.setIncludePersonalNoteChanges(false);

    // make the request
    AADM_User.attemptsTo(
        gcpApi.POSTGlobalChangePersonnelSetupOnThePersonnelglobalchangeController(
            gcpSetupPostRequest, ""));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // validate the response
    GCPSetupResponse gcpSetupResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", GCPSetupResponse.class);

    AADM_User.attemptsTo(
        gcpApi.GETGlobalChangePersonnelStatusOnThePersonnelglobalchangeController(
            gcpSetupResponse.getGlobalChangeHeaderId(), ""));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    GCPStatusResponse statusResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", GCPStatusResponse.class);

    assertThat(statusResponse.getSuspenseStatus().getTotalToProcessCount())
        .isEqualTo(originalNumSuspenses + 3);
  }

  /*
  This test verifies that when a field that can be turned on for processing (alerts, notes, suspenses, etc)
  is turned on, then the toProcessedCount for those fields will always be greater than -1. E.g. even if
  there are no alerts to be processed, it will still show 0.
   */
  @Test
  public void verifyIncludedProcessedFieldsAreGreaterThanNegativeOne() {
    Actor AADM_User = theActorCalled("AADM_User");

    BasicPolicyInfoResponse randomPolicy = PolicyUtil.selectRandomPolicy(AADM_User, "policy");
    String fromEmployee = randomPolicy.getExecutiveCode();
    String toEmployee = EmployeeUtil.getRandomExec(AADM_User).getEmpCode();
    UseGlobalChangeTo gcpApi = new UseGlobalChangeTo();
    GCPSetupPostRequest gcpSetupPostRequest = new GCPSetupPostRequest();

    // set required fields for the GCP request model
    gcpSetupPostRequest.setFromPersonnelCode(fromEmployee);
    gcpSetupPostRequest.setToPersonnelCode(toEmployee);
    gcpSetupPostRequest.setPersonnelType("Exec");
    gcpSetupPostRequest.setChangeCustomers(true);
    gcpSetupPostRequest.setChangePolicies(true);
    gcpSetupPostRequest.setIncludePersonalSuspenseChanges(true);
    gcpSetupPostRequest.setChangeCustomerSuspense("All");
    gcpSetupPostRequest.setIncludeAlertChanges(true);
    gcpSetupPostRequest.setIncludePersonalNoteChanges(true);

    // make the request
    AADM_User.attemptsTo(
        gcpApi.POSTGlobalChangePersonnelSetupOnThePersonnelglobalchangeController(
            gcpSetupPostRequest, ""));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // validate the response
    GCPSetupResponse gcpSetupResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", GCPSetupResponse.class);

    AADM_User.attemptsTo(
        gcpApi.GETGlobalChangePersonnelStatusOnThePersonnelglobalchangeController(
            gcpSetupResponse.getGlobalChangeHeaderId(), ""));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    GCPStatusResponse statusResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", GCPStatusResponse.class);

    SerenityRest.lastResponse().prettyPrint();

    assertThat(statusResponse.getAlertsStatus().getTotalToProcessCount()).isGreaterThan(-1);
    assertThat(statusResponse.getCustomersStatus().getTotalToProcessCount()).isGreaterThan(-1);
    assertThat(statusResponse.getPoliciesStatus().getTotalToProcessCount()).isGreaterThan(-1);
    assertThat(statusResponse.getNotesStatus().getTotalToProcessCount()).isGreaterThan(-1);
    assertThat(statusResponse.getSuspenseStatus().getTotalToProcessCount()).isGreaterThan(-1);
  }

  @Test
  public void noChangesDetectedReturnsAnError() {
    Actor AADM_User = theActorCalled("AADM_User");

    BasicPolicyInfoResponse randomPolicy = PolicyUtil.selectRandomPolicy(AADM_User, "policy");
    String fromEmployee = randomPolicy.getExecutiveCode();
    String toEmployee = EmployeeUtil.getRandomExec(AADM_User).getEmpCode();
    UseGlobalChangeTo gcpApi = new UseGlobalChangeTo();
    GCPSetupPostRequest gcpSetupPostRequest = new GCPSetupPostRequest();

    // set required fields for the GCP request model
    gcpSetupPostRequest.setFromPersonnelCode(fromEmployee);
    gcpSetupPostRequest.setToPersonnelCode(toEmployee);
    gcpSetupPostRequest.setPersonnelType("Exec");
    gcpSetupPostRequest.setChangeCustomers(false);
    gcpSetupPostRequest.setChangePolicies(false);
    gcpSetupPostRequest.setIncludePersonalSuspenseChanges(false);
    gcpSetupPostRequest.setChangeCustomerSuspense("None");
    gcpSetupPostRequest.setIncludeAlertChanges(false);
    gcpSetupPostRequest.setIncludePersonalNoteChanges(false);

    // make the request
    AADM_User.attemptsTo(
        gcpApi.POSTGlobalChangePersonnelSetupOnThePersonnelglobalchangeController(
            gcpSetupPostRequest, ""));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponse("No changes detected for Global Change Processing", AADM_User);
  }
}
