package com.vertafore.test.services.global_change_personnel;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseCustomerTo;
import com.vertafore.test.servicewrappers.UseGlobalChangeTo;
import com.vertafore.test.servicewrappers.UseSuspenseTo;
import com.vertafore.test.util.*;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_GlobalChangePersonnelStart extends TokenSuperClass {

  @Test
  public void gcpStartIgnoresAppLockedCustomer() throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");

    // release all gcp application locks
    AppLockUtil.releaseAllGCPLocks(AADM_User);

    // create the customer objects to stage
    CustomerBasicInfoPostRequest customerToLock =
        CustomerUtil.createBasicCustomer("Customer", "Individual", AADM_User);
    CustomerBasicInfoPostRequest customerToUnlock =
        CustomerUtil.createBasicCustomer("Customer", "Individual", AADM_User);

    // Set second customer under first customer exec
    String employeeShortName = customerToLock.getAgencyPersonnel().getAccountExecShortName();
    customerToUnlock.getAgencyPersonnel().setAccountExecShortName(employeeShortName);

    // get empCodes
    EmployeeResponse fromEmployee =
        EmployeeUtil.getEmployeeDetailsByShortName(employeeShortName, AADM_User);
    String fromEmployeeEmpCode = fromEmployee.getEmpCode();

    // get second employee
    EmployeeResponse toEmployee = EmployeeUtil.getRandomExec(AADM_User);

    // if it turns out the two employees are the same no gcp processing will occur
    while (toEmployee.getEmpCode().equals(fromEmployeeEmpCode)) {
      toEmployee = EmployeeUtil.getRandomExec(AADM_User);
    }

    String toEmployeeEmpCode = toEmployee.getEmpCode();

    // stage customers under from employee
    UseCustomerTo customersApi = new UseCustomerTo();
    AADM_User.attemptsTo(
        customersApi.POSTCustomerBasicInfoOnTheCustomersController(customerToLock, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    System.out.println("LOCKED CUSTOMER POSTED");
    SerenityRest.lastResponse().prettyPrint();

    CustomerBasicInfoResponse customerToLockResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", CustomerBasicInfoResponse.class);

    assertThat(customerToLockResponse).isNotNull();
    assertThat(customerToLockResponse.getCustomerId()).isNotNull();

    String customerToLockId = customerToLockResponse.getCustomerId();

    AADM_User.attemptsTo(
        customersApi.POSTCustomerBasicInfoOnTheCustomersController(customerToUnlock, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    System.out.println("UNLOCKED CUSTOMER POSTED");
    SerenityRest.lastResponse().prettyPrint();

    CustomerBasicInfoResponse customerToUnlockResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", CustomerBasicInfoResponse.class);

    assertThat(customerToUnlockResponse).isNotNull();
    assertThat(customerToUnlockResponse.getCustomerId()).isNotNull();

    String customerToUnlockId = customerToUnlockResponse.getCustomerId();

    // app lock the first customer
    ApplicationLockResponse applicationLockResponse =
        AppLockUtil.lockCustomer(customerToLockId, "GCP APP LOCK CUSTOMER TEST", AADM_User);

    SerenityRest.lastResponse().prettyPrint();
    assertThat(applicationLockResponse).isNotNull();
    assertThat(applicationLockResponse.getApplicationLockId()).isNotNull();

    UseGlobalChangeTo gcpApi = new UseGlobalChangeTo();
    GCPSetupPostRequest gcpSetupPostRequest = new GCPSetupPostRequest();

    // set required fields for the GCP request model
    gcpSetupPostRequest.setFromPersonnelCode(fromEmployeeEmpCode);
    gcpSetupPostRequest.setToPersonnelCode(toEmployeeEmpCode);
    gcpSetupPostRequest.setPersonnelType("Exec");
    gcpSetupPostRequest.setChangeCustomersAndPolicies("Customers");
    gcpSetupPostRequest.setChangePersonalSuspenses(false);
    gcpSetupPostRequest.setChangeCustomerSuspenses("None");
    gcpSetupPostRequest.setChangeAlerts(false);
    gcpSetupPostRequest.setChangePersonalNotes(false);

    // only change our newly created customers
    GCPCustomerFilterPostRequest customerFilterModel = new GCPCustomerFilterPostRequest();
    customerFilterModel.addCustomerIdsItem(customerToLockId);
    customerFilterModel.addCustomerIdsItem(customerToUnlockId);
    gcpSetupPostRequest.setCustomerFilter(customerFilterModel);

    System.out.println("SETUP MODEL BEING SENT : \n");
    Util.printObjectAsJson(gcpSetupPostRequest);

    // make the setup request
    AADM_User.attemptsTo(
        gcpApi.POSTGlobalChangePersonnelSetupOnThePersonnelglobalchangeController(
            gcpSetupPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    GCPSetupResponse gcpSetupResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", GCPSetupResponse.class);

    assertThat(gcpSetupResponse).isNotNull();
    assertThat(gcpSetupResponse.getGlobalChangeHeaderId()).isNotNull();

    GCPStartPostRequest startPostRequest = new GCPStartPostRequest();
    startPostRequest.setGlobalChangeHeaderId(gcpSetupResponse.getGlobalChangeHeaderId());

    AADM_User.attemptsTo(
        gcpApi.GETGlobalChangePersonnelDetailsOnThePersonnelglobalchangeController(
            startPostRequest.getGlobalChangeHeaderId(), ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // start the global change process
    AADM_User.attemptsTo(
        gcpApi.POSTGlobalChangePersonnelStartOnThePersonnelglobalchangeController(
            startPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    GCPStartResponse startResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", GCPStartResponse.class);
    assertThat(startResponse.getIsStarted()).isTrue();

    // let GCP run
    GCPUtil.waitForGCPProcessToComplete(gcpSetupResponse.getGlobalChangeHeaderId(), AADM_User);

    // cross-reference results on the customer controller
    AADM_User.attemptsTo(
        customersApi.GETCustomerOnTheCustomersController(
            customerToLockId, customerToLockResponse.getCustomerNumber(), ""));

    CustomerInfoResponse lockedCustomerInfo =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", CustomerInfoResponse.class);

    // locked customer did not transfer
    assertThat(lockedCustomerInfo.getExecShortName()).isEqualTo(fromEmployee.getShortName());

    AADM_User.attemptsTo(
        customersApi.GETCustomerOnTheCustomersController(
            customerToUnlockId, customerToUnlockResponse.getCustomerNumber(), ""));

    CustomerInfoResponse unlockedCustomerInfo =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", CustomerInfoResponse.class);

    // unlocked customer did transfer
    assertThat(unlockedCustomerInfo.getExecShortName()).isEqualTo(toEmployee.getShortName());

    // release the application lock
    AppLockUtil.releaseApplicationLockByApplicationLockId(
        applicationLockResponse.getApplicationLockId(), AADM_User);
  }

  @Test
  public void gcpStartIgnoresAppLockedPolicy() throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");

    AppLockUtil.releaseAllGCPLocks(AADM_User);

    BasicPolicyInfoResponse randomPolicy = PolicyUtil.selectRandomPolicy(AADM_User, "policy");
    String fromEmployeeEmpCode = randomPolicy.getExecutiveCode();
    UseGlobalChangeTo gcpApi = new UseGlobalChangeTo();

    // get second employee
    String toEmployeeEmpCode = EmployeeUtil.getRandomExec(AADM_User).getEmpCode();

    // if it turns out the two employees are the same no gcp processing will occur
    while (toEmployeeEmpCode.equals(fromEmployeeEmpCode)) {
      toEmployeeEmpCode = EmployeeUtil.getRandomExec(AADM_User).getEmpCode();
    }

    // create a suspense so that there are changes guaranteed for GCP:
    UseSuspenseTo suspenseApi = new UseSuspenseTo();
    SuspensePostRequest suspense =
        SuspenseUtil.createRandomSuspenseTiedToEmployee(fromEmployeeEmpCode, AADM_User);

    AADM_User.attemptsTo(suspenseApi.POSTSuspenseOnTheSuspensesController(suspense, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // app lock the policy
    ApplicationLockResponse applicationLockResponse =
        AppLockUtil.lockPolicy(randomPolicy.getPolicyId(), "GCP APP LOCK POLICY TEST", AADM_User);
    assertThat(applicationLockResponse).isNotNull();
    assertThat(applicationLockResponse.getApplicationLockId()).isNotNull();

    GCPSetupPostRequest gcpSetupPostRequest = new GCPSetupPostRequest();

    // set required fields for the GCP request model
    gcpSetupPostRequest.setFromPersonnelCode(fromEmployeeEmpCode);
    gcpSetupPostRequest.setToPersonnelCode(toEmployeeEmpCode);
    gcpSetupPostRequest.setPersonnelType("Exec");
    gcpSetupPostRequest.setChangeCustomersAndPolicies("B"); // change both
    gcpSetupPostRequest.setChangePersonalSuspenses(true);
    gcpSetupPostRequest.setChangeCustomerSuspenses("None");
    gcpSetupPostRequest.setChangeAlerts(true);
    gcpSetupPostRequest.setChangePersonalNotes(true);

    // make the request
    AADM_User.attemptsTo(
        gcpApi.POSTGlobalChangePersonnelSetupOnThePersonnelglobalchangeController(
            gcpSetupPostRequest, ""));

    if (SerenityRest.lastResponse().getStatusCode() != 200) {
      System.out.println("ERROR IN GCP SET UP: \n");
      SerenityRest.lastResponse().prettyPrint();
    }
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    GCPSetupResponse gcpSetupResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", GCPSetupResponse.class);

    assertThat(gcpSetupResponse).isNotNull();
    assertThat(gcpSetupResponse.getGlobalChangeHeaderId()).isNotNull();

    GCPStartPostRequest startPostRequest = new GCPStartPostRequest();
    startPostRequest.setGlobalChangeHeaderId(gcpSetupResponse.getGlobalChangeHeaderId());

    AADM_User.attemptsTo(
        gcpApi.POSTGlobalChangePersonnelStartOnThePersonnelglobalchangeController(
            startPostRequest, ""));

    if (SerenityRest.lastResponse().getStatusCode() != 200) {
      SerenityRest.lastResponse().prettyPrint();
    }
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // let gcp run
    Thread.sleep(10000);

    BasicPolicyInfoResponse lockedPolicyResponse =
        PolicyUtil.getPolicyById(AADM_User, randomPolicy.getPolicyId());

    assertThat(lockedPolicyResponse.getExecutiveCode()).isEqualTo(fromEmployeeEmpCode);

    // release the policy
    AppLockUtil.releaseApplicationLockByApplicationLockId(
        applicationLockResponse.getApplicationLockId(), AADM_User);

    GCPUtil.waitForGCPProcessToComplete(gcpSetupResponse.getGlobalChangeHeaderId(), AADM_User);
  }
}
