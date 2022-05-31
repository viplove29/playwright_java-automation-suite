package com.vertafore.test.util;

import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseGlobalChangeTo;
import com.vertafore.test.servicewrappers.UseSuspenseTo;
import com.vertafore.test.servicewrappers.UseSuspensesTo;
import java.util.Random;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.joda.time.LocalDateTime;

public class SuspenseUtil {

  private static UseSuspenseTo suspenseApi = new UseSuspenseTo();
  private static UseSuspensesTo suspensesApi = new UseSuspensesTo();

  public static SuspensePostRequest createRandomPersonalEmployeeSuspense(
      String empCode, Actor actor) {
    SuspensePostRequest postRequest = new SuspensePostRequest();
    int[] statusOptions = {1, 2};
    int[] priorityOptions = {1, 5, 10};

    String randomDate = LocalDateTime.now().plusDays(new Random().nextInt(30)).toString();
    postRequest.setAssignToEmployeeCode(empCode);
    postRequest.setDueDate(randomDate);
    postRequest.setDescription("description");
    postRequest.setEntityType("5"); // entity type employee
    postRequest.setEntityId(empCode);
    postRequest.setAssignedByEmpCode(empCode);
    postRequest.setStatus(statusOptions[new Random().nextInt(statusOptions.length)]);
    postRequest.setAction(ActivityUtil.getRandomActivityAction(actor));
    postRequest.setPriority(priorityOptions[new Random().nextInt(priorityOptions.length)]);

    return postRequest;
  }

  public static SuspensePostRequest createRandomSuspenseTiedToCustomer(
      String empCode, String customerId, Actor actor) {
    SuspensePostRequest postRequest = new SuspensePostRequest();
    int[] statusOptions = {1, 2};
    int[] priorityOptions = {1, 5, 10};

    String randomDate = LocalDateTime.now().plusDays(new Random().nextInt(30)).toString();
    postRequest.setAssignToEmployeeCode(empCode);
    postRequest.setDueDate(randomDate);
    postRequest.setDescription("description");
    postRequest.setEntityType("4"); // entity type customer
    postRequest.setEntityId(customerId);
    postRequest.setAssignedByEmpCode(empCode);
    postRequest.setStatus(statusOptions[new Random().nextInt(statusOptions.length)]);
    postRequest.setAction(ActivityUtil.getRandomActivityAction(actor));
    postRequest.setPriority(priorityOptions[new Random().nextInt(priorityOptions.length)]);

    return postRequest;
  }

  public static PolicySuspensePostRequest createRandomPolicySuspenseTiedToCustomer(
      String empCode, String customerId, String policyId, Actor actor) {
    PolicySuspensePostRequest postRequest = new PolicySuspensePostRequest();
    String[] priorityOptions = {"1", "5", "10"};
    String randomDate = LocalDateTime.now().plusDays(new Random().nextInt(30)).toString();

    postRequest.setCustomerId(customerId);
    postRequest.setPolicyId(policyId);
    postRequest.setRecipientEmployeeCode(empCode);
    postRequest.setDueDate(randomDate);
    postRequest.setAction(ActivityUtil.getRandomActivityAction(actor));
    postRequest.setPriority(priorityOptions[new Random().nextInt(priorityOptions.length)]);

    return postRequest;
  }

  public static PolicySuspenseCollectionPostRequest
      createMultipleRandomPolicySuspensesTiedToCustomer(
          String empCode, String customerId, String policyId, int numSuspenses, Actor actor) {
    PolicySuspenseCollectionPostRequest collectionPostRequest =
        new PolicySuspenseCollectionPostRequest();

    for (int i = 0; i < numSuspenses; i++) {
      collectionPostRequest.addPolicySuspensesItem(
          createRandomPolicySuspenseTiedToCustomer(empCode, customerId, policyId, actor));
    }

    return collectionPostRequest;
  }

  // inserts a personal employee suspense, a customer suspense, and a policy suspense
  // requires an employee with a policy and a customer tied to it
  public static void insertAllSuspenseTypesUnderEmployee(
      Actor actor, String empCode, String custId, String policyId) {
    SuspensePostRequest postRequest =
        SuspenseUtil.createRandomPersonalEmployeeSuspense(empCode, actor);
    actor.attemptsTo(suspenseApi.POSTSuspenseOnTheSuspensesController(postRequest, ""));

    postRequest = SuspenseUtil.createRandomSuspenseTiedToCustomer(empCode, custId, actor);
    actor.attemptsTo(suspenseApi.POSTSuspenseOnTheSuspensesController(postRequest, ""));

    PolicySuspenseCollectionPostRequest collectionPostRequest =
        SuspenseUtil.createMultipleRandomPolicySuspensesTiedToCustomer(
            empCode, custId, policyId, 1, actor);
    actor.attemptsTo(
        suspensesApi.POSTSuspensesPolicyOnTheSuspensesController(collectionPostRequest, ""));
  }

  /*
  As it stands there is no straightforward way to get suspenses for an employee.
  we cannot retrieve a list of suspenses at all, however through this workaround we can see
  how many policy, customer, and personal suspenses an employee might have.
  There is no currently no way to differentiate between them and get separate counts however
   */
  public static int getNumberOfSuspensesTiedToEmployee(
      Actor actor, String empcode, String personnelType) {
    UseGlobalChangeTo gcpApi = new UseGlobalChangeTo();
    GCPSetupPostRequest gcpSetupPostRequest = new GCPSetupPostRequest();
    String toEmployee = "";

    // get an employee with a matching personnel type to the desired one being checked
    // necessary for GCP processing
    switch (personnelType) {
      case "P":
        toEmployee = EmployeeUtil.getRandomExec(actor).getEmpCode();
        break;
      case "R":
        toEmployee = EmployeeUtil.getRandomRep(actor).getEmpCode();
        break;
      default:
        System.out.println("Personnel type can only be 'P' for exec and 'R' for rep");
    }

    gcpSetupPostRequest.setFromPersonnelCode(empcode);
    gcpSetupPostRequest.setToPersonnelCode(toEmployee);
    gcpSetupPostRequest.setPersonnelType(personnelType);
    gcpSetupPostRequest.setChangeCustomers(true);
    gcpSetupPostRequest.setChangePolicies(true);
    gcpSetupPostRequest.setIncludePersonalSuspenseChanges(true);
    gcpSetupPostRequest.setChangeCustomerSuspense("All");

    // post the setup endpoint to get a GCP processing request with a header GUID
    actor.attemptsTo(
        gcpApi.POSTGlobalChangePersonnelSetupOnThePersonnelglobalchangeController(
            gcpSetupPostRequest, ""));

    GCPSetupResponse gcpSetupResponse =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", GCPSetupResponse.class);

    // use the header guid to get the status details and retrieve the data from the suspenseStatus
    // model
    actor.attemptsTo(
        gcpApi.GETGlobalChangePersonnelStatusOnThePersonnelglobalchangeController(
            gcpSetupResponse.getGlobalChangeHeaderId(), ""));
    GCPStatusResponse statusResponse =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", GCPStatusResponse.class);

    if (statusResponse == null) {

      return -1;
    }
    return statusResponse.getSuspenseStatus().getTotalToProcessCount();
  }
}
