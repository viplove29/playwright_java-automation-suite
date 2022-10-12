package com.vertafore.test.services.global_change_personnel;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseGlobalChangeTo;
import com.vertafore.test.util.*;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_GlobalChangePersonnelSetup extends TokenSuperClass {

  @Test
  public void optionalFieldsCanBeLeftOutOfSetupBody() {
    Actor AADM_User = theActorCalled("AADM_User");

    BasicPolicyInfoResponse randomPolicy = PolicyUtil.selectRandomPolicy(AADM_User, "policy");
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
    gcpSetupPostRequest.setChangeCustomersAndPolicies("B");

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
    gcpSetupPostRequest.setChangeCustomersAndPolicies("B");
    gcpSetupPostRequest.setChangePersonalSuspenses(true);
    gcpSetupPostRequest.setChangeCustomerSuspenses("All");
    gcpSetupPostRequest.setChangeAlerts(false);
    gcpSetupPostRequest.setChangePersonalNotes(false);

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

    // if it turns out the two employees are the same no gcp processing will occur
    while (toEmployee.equals(fromEmployee)) {
      toEmployee = EmployeeUtil.getRandomExec(AADM_User).getEmpCode();
    }

    UseGlobalChangeTo gcpApi = new UseGlobalChangeTo();
    GCPSetupPostRequest gcpSetupPostRequest = new GCPSetupPostRequest();

    // set required fields for the GCP request model
    gcpSetupPostRequest.setFromPersonnelCode(fromEmployee);
    gcpSetupPostRequest.setToPersonnelCode(toEmployee);
    gcpSetupPostRequest.setPersonnelType("Exec");
    gcpSetupPostRequest.setChangeCustomersAndPolicies("B");
    gcpSetupPostRequest.setChangePersonalSuspenses(true);
    gcpSetupPostRequest.setChangeCustomerSuspenses("All");
    gcpSetupPostRequest.setChangeAlerts(true);
    gcpSetupPostRequest.setChangePersonalNotes(true);

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

    // if it turns out the two employees are the same no gcp processing will occur
    while (toEmployee.equals(fromEmployee)) {
      toEmployee = EmployeeUtil.getRandomExec(AADM_User).getEmpCode();
    }

    UseGlobalChangeTo gcpApi = new UseGlobalChangeTo();
    GCPSetupPostRequest gcpSetupPostRequest = new GCPSetupPostRequest();

    // set required fields for the GCP request model
    gcpSetupPostRequest.setFromPersonnelCode(fromEmployee);
    gcpSetupPostRequest.setToPersonnelCode(toEmployee);
    gcpSetupPostRequest.setPersonnelType("Exec");
    gcpSetupPostRequest.setChangeCustomersAndPolicies("N");
    gcpSetupPostRequest.setChangePersonalSuspenses(false);
    gcpSetupPostRequest.setChangeCustomerSuspenses("None");
    gcpSetupPostRequest.setChangeAlerts(false);
    gcpSetupPostRequest.setChangeAlerts(false);

    // make the request
    AADM_User.attemptsTo(
        gcpApi.POSTGlobalChangePersonnelSetupOnThePersonnelglobalchangeController(
            gcpSetupPostRequest, ""));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponse("No changes detected for Global Change Processing", AADM_User);
  }

  // test to ensure the employees provided match the flag (Rep, Exec, Broker)
  @Test
  public void empCodesMatchEmployeeTypeFlagValidation() {
    Actor AADM_User = theActorCalled("AADM_User");

    BasicPolicyInfoResponse randomPolicy = PolicyUtil.selectRandomPolicy(AADM_User, "policy");
    String fromEmployee = randomPolicy.getCsrCode();
    String toEmployee = EmployeeUtil.getRandomRep(AADM_User).getEmpCode();

    // if it turns out the two employees are the same no gcp processing will occur
    while (toEmployee.equals(fromEmployee)) {
      toEmployee = EmployeeUtil.getRandomRep(AADM_User).getEmpCode();
    }

    UseGlobalChangeTo gcpApi = new UseGlobalChangeTo();
    GCPSetupPostRequest gcpSetupPostRequest = new GCPSetupPostRequest();

    // set required fields for the GCP request model
    gcpSetupPostRequest.setFromPersonnelCode(fromEmployee);
    gcpSetupPostRequest.setToPersonnelCode(toEmployee);
    gcpSetupPostRequest.setPersonnelType(
        "Exec"); // both employees are reps, this should return error
    gcpSetupPostRequest.setChangeCustomersAndPolicies("B");

    // make the request
    AADM_User.attemptsTo(
        gcpApi.POSTGlobalChangePersonnelSetupOnThePersonnelglobalchangeController(
            gcpSetupPostRequest, ""));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    String errorMessage =
        "The following errors occurred while setting up the GCP process: { Employee Code with value ("
            + toEmployee
            + ") provided to 'ToPersonnelCode' does not match the PersonnelType 'Exec' }";
    Util.validateErrorResponse(errorMessage, AADM_User);
  }

  // edge case sad path testing to ensure employee type can only be of types P, R, B, or T
  @Test
  public void employeeTypeAcceptableValuesValidation() {
    Actor AADM_User = theActorCalled("AADM_User");

    BasicPolicyInfoResponse randomPolicy = PolicyUtil.selectRandomPolicy(AADM_User, "policy");
    String fromEmployee = randomPolicy.getCsrCode();
    String toEmployee = EmployeeUtil.getRandomRep(AADM_User).getEmpCode();
    UseGlobalChangeTo gcpApi = new UseGlobalChangeTo();
    GCPSetupPostRequest gcpSetupPostRequest = new GCPSetupPostRequest();

    // set required fields for the GCP request model
    gcpSetupPostRequest.setFromPersonnelCode(fromEmployee);
    gcpSetupPostRequest.setToPersonnelCode(toEmployee);
    gcpSetupPostRequest.setPersonnelType("Something"); // should return error
    gcpSetupPostRequest.setChangeCustomersAndPolicies("B");

    // make the request
    AADM_User.attemptsTo(
        gcpApi.POSTGlobalChangePersonnelSetupOnThePersonnelglobalchangeController(
            gcpSetupPostRequest, ""));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    String errorMessage =
        "The PersonnelType must equal one of the following: { 'Exec' or 'P', 'Rep' or 'R', 'Broker' or 'B', 'Sales Center Rep' or 'T' }.";
    Util.validateErrorResponse(errorMessage, AADM_User);
  }

  // edge case sad path testing to ensure changeCustomerSuspense can only be A, S, or N
  @Test
  public void changeCustomerSuspenseAcceptableValuesValidation() {
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
    gcpSetupPostRequest.setChangeCustomersAndPolicies("B");
    gcpSetupPostRequest.setChangeCustomerSuspenses("something"); // should return error

    // make the request
    AADM_User.attemptsTo(
        gcpApi.POSTGlobalChangePersonnelSetupOnThePersonnelglobalchangeController(
            gcpSetupPostRequest, ""));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    String errorMessage =
        "The ChangeCustomerSuspenses must equal one of the following: { 'All' or 'A', 'Selected' or 'S', 'None' or 'N' }.";
    Util.validateErrorResponse(errorMessage, AADM_User);
  }

  // test to validate that the flags to pass alerts, suspenses, and notes to GCP
  // cannot be passed as true if the personnel type is Broker
  @Test
  public void validateNotesAlertsSuspensesCannotBePassedWithBrokerPersonnelType() {
    Actor AADM_User = theActorCalled("AADM_User");

    BrokerResponse fromEmployee = BrokerUtil.getRandomActiveBroker(AADM_User);
    BrokerResponse toEmployee = BrokerUtil.getRandomActiveBroker(AADM_User);

    while (toEmployee.getBrokerCode().equals(fromEmployee.getBrokerCode())) {
      toEmployee = BrokerUtil.getRandomActiveBroker(AADM_User);
    }

    // in case brokers are empty, stage a customer
    CustomerUtil.createBrokerCustomer(AADM_User, fromEmployee.getShortName());

    UseGlobalChangeTo gcpApi = new UseGlobalChangeTo();
    GCPSetupPostRequest gcpSetupPostRequest = new GCPSetupPostRequest();

    // set required fields for the GCP request model
    gcpSetupPostRequest.setFromPersonnelCode(fromEmployee.getBrokerCode());
    gcpSetupPostRequest.setToPersonnelCode(toEmployee.getBrokerCode());
    gcpSetupPostRequest.setPersonnelType("Broker");
    gcpSetupPostRequest.setChangeCustomersAndPolicies("B");

    // the following fields should automatically be set to false/None once you post
    gcpSetupPostRequest.setChangeAlerts(true);
    gcpSetupPostRequest.setChangePersonalSuspenses(true);
    gcpSetupPostRequest.setChangePersonalNotes(true);
    gcpSetupPostRequest.setChangeCustomerSuspenses("All");

    // make the request
    AADM_User.attemptsTo(
        gcpApi.POSTGlobalChangePersonnelSetupOnThePersonnelglobalchangeController(
            gcpSetupPostRequest, ""));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // validate that the fields are flipped in the details model
    GCPSetupResponse gcpSetupResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", GCPSetupResponse.class);

    AADM_User.attemptsTo(
        gcpApi.GETGlobalChangePersonnelDetailsOnThePersonnelglobalchangeController(
            gcpSetupResponse.getGlobalChangeHeaderId(), ""));

    GCPDetailsResponse detailsResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", GCPDetailsResponse.class);

    assertThat(detailsResponse).isNotNull();
    assertThat(detailsResponse.getHeader()).isNotNull();
    assertThat(detailsResponse.getHeader().getChangeCustomerSuspenses()).isEqualTo("None");
    assertThat(detailsResponse.getHeader().getChangeAlerts()).isEqualTo(false);
    assertThat(detailsResponse.getHeader().getChangePersonalNotes()).isEqualTo(false);
    assertThat(detailsResponse.getHeader().getChangePersonalSuspenses()).isEqualTo(false);
  }
}
