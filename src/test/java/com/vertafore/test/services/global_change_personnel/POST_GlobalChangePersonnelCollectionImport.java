package com.vertafore.test.services.global_change_personnel;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseGlobalChangeTo;
import com.vertafore.test.util.*;
import java.util.*;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_GlobalChangePersonnelCollectionImport extends TokenSuperClass {

  private static final List<String> EXCLUDED_EMP_CODES = Arrays.asList("!!$", "^^]");
  private static final int MAX_TRIES = 5;
  private static final int PROCESSING_DELAY_MS = 5000;

  private GCPImportPostRequest getGCPCollectionImportTestData(Actor actor, Boolean isPolicyOnly) {
    BasicPolicyInfoResponse randomPolicy = PolicyUtil.selectRandomPolicy(actor, "policy");
    String fromEmployee = randomPolicy.getExecutiveCode();
    while (EXCLUDED_EMP_CODES.contains(fromEmployee)) {
      randomPolicy = PolicyUtil.selectRandomPolicy(actor, "policy");
      fromEmployee = randomPolicy.getExecutiveCode();
    }
    String toEmployee = EmployeeUtil.getRandomExec(actor).getEmpCode();
    while (EXCLUDED_EMP_CODES.contains(toEmployee) || toEmployee.equals(fromEmployee)) {
      toEmployee = EmployeeUtil.getRandomExec(actor).getEmpCode();
    }

    String fromEmployeeShortName =
        Objects.requireNonNull(EmployeeUtil.getEmployeeDetailsByEmpCode(actor, fromEmployee))
            .getShortName();
    String toEmployeeShortName =
        Objects.requireNonNull(EmployeeUtil.getEmployeeDetailsByEmpCode(actor, toEmployee))
            .getShortName();

    int customerNumber =
        Objects.requireNonNull(
                CustomerUtil.getCustomerInfoByCustomerId(actor, randomPolicy.getCustomerId()))
            .getCustomerNumber();
    String policyId = randomPolicy.getPolicyId();

    GCPImportPostRequest gcpImportPostRequest = new GCPImportPostRequest();
    gcpImportPostRequest.setPersonnelType("Exec");
    gcpImportPostRequest.setFromPersonnelShortName(fromEmployeeShortName);
    gcpImportPostRequest.setToPersonnelShortName(toEmployeeShortName);
    gcpImportPostRequest.setCustomerNumber(customerNumber);
    if (isPolicyOnly) {
      gcpImportPostRequest.setPolicyId(policyId);
    }

    return gcpImportPostRequest;
  }

  @Test
  public void globalChangePersonnelImportForCustomerIsSuccessful() throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");

    List<ErrorLogsResponse> errorsAndWarnings = new ArrayList<>();

    int tries = 0;
    while (tries < MAX_TRIES) {
      AppLockUtil.releaseAllGCPLocks(AADM_User);

      UseGlobalChangeTo gcpApi = new UseGlobalChangeTo();

      GCPCollectionImportPostRequest gcpCollectionImportPostRequest =
          new GCPCollectionImportPostRequest();
      GCPImportPostRequest gcpImportPostRequest = getGCPCollectionImportTestData(AADM_User, false);
      gcpCollectionImportPostRequest.addGcpCollectionItem(gcpImportPostRequest);

      gcpCollectionImportPostRequest.setCustomerFileName("AutomationGCPCustomer.csv");
      gcpCollectionImportPostRequest.setCollectionMemo("GCP EMS Automation Customer Only");
      gcpCollectionImportPostRequest.setChangePersonalSuspenses(false);
      gcpCollectionImportPostRequest.setChangeCustomerSuspenses("None");
      gcpCollectionImportPostRequest.setChangeAlerts(false);
      gcpCollectionImportPostRequest.setChangePersonalNotes(false);
      gcpCollectionImportPostRequest.setFromPersonnelCommissionsSetToZero(false);
      gcpCollectionImportPostRequest.setToPersonnelCommissionsSetToDefault(false);

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

      String collectionId = gcpCollectionImportResponse.getGlobalChangeCollectionId();
      String referenceId = gcpCollectionImportResponse.getEventLogReferenceId();
      assertThat(collectionId).isNotNull();
      assertThat(referenceId).isNotNull();

      Thread.sleep(PROCESSING_DELAY_MS);
      errorsAndWarnings = ErrorLogUtil.getErrorsAndWarningsByReferenceId(AADM_User, referenceId);
      tries++;

      if (!ErrorLogUtil.warningMessageIsPresent(
              errorsAndWarnings,
              "Some customers are skipped because of business rules for Global Change for Personnel.")
          && !ErrorLogUtil.errorMessageIsPresent(
              errorsAndWarnings,
              "Unable to get application lock for Global Change for Personnel")) {
        break;
      }
      System.out.println(
          "Customer "
              + gcpImportPostRequest.getCustomerNumber()
              + " was skipped due to business rules or the app lock cou ld not be retrieved.");
    }

    // skip assertions if data could not be found in the max number of tries
    assumeThat(tries)
        .as("Could not find an eligible customer in " + MAX_TRIES + " tries. Skipping test.")
        .isLessThan(MAX_TRIES);
    assertThat(ErrorLogUtil.getNumberOfWarnings(errorsAndWarnings))
        .as("Warnings were found.")
        .isZero();
    assertThat(ErrorLogUtil.getNumberOfErrors(errorsAndWarnings)).as("Errors were found.").isZero();
  }

  @Test
  public void globalChangePersonnelImportForPolicyIsSuccessful() throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");

    List<ErrorLogsResponse> errorsAndWarnings = new ArrayList<>();

    int tries = 0;
    while (tries < MAX_TRIES) {
      AppLockUtil.releaseAllGCPLocks(AADM_User);

      UseGlobalChangeTo gcpApi = new UseGlobalChangeTo();

      GCPCollectionImportPostRequest gcpCollectionImportPostRequest =
          new GCPCollectionImportPostRequest();
      GCPImportPostRequest gcpImportPostRequest = getGCPCollectionImportTestData(AADM_User, true);
      gcpCollectionImportPostRequest.addGcpCollectionItem(gcpImportPostRequest);

      gcpCollectionImportPostRequest.setPolicyFileName("AutomationGCPPolicy.csv");
      gcpCollectionImportPostRequest.setCollectionMemo("GCP EMS Automation Policy Only");
      gcpCollectionImportPostRequest.setChangePersonalSuspenses(false);
      gcpCollectionImportPostRequest.setChangeCustomerSuspenses("None");
      gcpCollectionImportPostRequest.setChangeAlerts(false);
      gcpCollectionImportPostRequest.setChangePersonalNotes(false);
      gcpCollectionImportPostRequest.setFromPersonnelCommissionsSetToZero(false);
      gcpCollectionImportPostRequest.setToPersonnelCommissionsSetToDefault(false);

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
      String collectionId = gcpCollectionImportResponse.getGlobalChangeCollectionId();
      String referenceId = gcpCollectionImportResponse.getEventLogReferenceId();
      assertThat(collectionId).isNotNull();
      assertThat(referenceId).isNotNull();

      Thread.sleep(PROCESSING_DELAY_MS);
      errorsAndWarnings = ErrorLogUtil.getErrorsAndWarningsByReferenceId(AADM_User, referenceId);
      tries++;

      if (!ErrorLogUtil.warningMessageIsPresent(
              errorsAndWarnings,
              "Some policies are skipped because of business rules for Global Change for Personnel.")
          && !ErrorLogUtil.errorMessageIsPresent(
              errorsAndWarnings,
              "Unable to get application lock for Global Change for Personnel")) {
        break;
      }
      System.out.println(
          "Policy "
              + gcpImportPostRequest.getPolicyId()
              + " was skipped due to business rules or the app lock could not be retrieved.");
    }

    // skip assertions if data could not be found in the max number of tries
    assumeThat(tries)
        .as("Could not find an eligible policy in " + MAX_TRIES + " tries. Skipping test.")
        .isLessThan(MAX_TRIES);
    assertThat(ErrorLogUtil.getNumberOfWarnings(errorsAndWarnings))
        .as("Warnings were found.")
        .isZero();
    assertThat(ErrorLogUtil.getNumberOfErrors(errorsAndWarnings)).as("Errors were found.").isZero();
  }

  @Test
  public void globalChangePersonnelImportWithChangeAlertsNotesSuspensesIsSuccessful()
      throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");

    List<ErrorLogsResponse> errorsAndWarnings = new ArrayList<>();

    int tries = 0;
    while (tries < MAX_TRIES) {
      AppLockUtil.releaseAllGCPLocks(AADM_User);

      UseGlobalChangeTo gcpApi = new UseGlobalChangeTo();

      GCPCollectionImportPostRequest gcpCollectionImportPostRequest =
          new GCPCollectionImportPostRequest();
      GCPImportPostRequest gcpImportPostRequest = getGCPCollectionImportTestData(AADM_User, false);
      gcpCollectionImportPostRequest.addGcpCollectionItem(gcpImportPostRequest);

      gcpCollectionImportPostRequest.setCustomerFileName("AutomationGCPAlertNoteSuspense.csv");
      gcpCollectionImportPostRequest.setCollectionMemo("GCP EMS Automation Alert Note Suspense");
      gcpCollectionImportPostRequest.setChangePersonalSuspenses(true);
      gcpCollectionImportPostRequest.setChangeCustomerSuspenses("All");
      gcpCollectionImportPostRequest.setChangeAlerts(true);
      gcpCollectionImportPostRequest.setChangePersonalNotes(true);
      gcpCollectionImportPostRequest.setFromPersonnelCommissionsSetToZero(false);
      gcpCollectionImportPostRequest.setToPersonnelCommissionsSetToDefault(false);

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
      String collectionId = gcpCollectionImportResponse.getGlobalChangeCollectionId();
      String referenceId = gcpCollectionImportResponse.getEventLogReferenceId();
      assertThat(collectionId).isNotNull();
      assertThat(referenceId).isNotNull();

      Thread.sleep(PROCESSING_DELAY_MS);
      errorsAndWarnings = ErrorLogUtil.getErrorsAndWarningsByReferenceId(AADM_User, referenceId);
      tries++;

      if (!ErrorLogUtil.warningMessageIsPresent(
              errorsAndWarnings,
              "Some customers are skipped because of business rules for Global Change for Personnel.")
          && !ErrorLogUtil.errorMessageIsPresent(
              errorsAndWarnings,
              "Unable to get application lock for Global Change for Personnel")) {
        break;
      }
      System.out.println(
          "Customer "
              + gcpImportPostRequest.getCustomerNumber()
              + " was skipped due to business rules or the app lock could not be retrieved.");
    }

    // skip assertions if data could not be found in the max number of tries
    assumeThat(tries)
        .as("Could not find an eligible customer in " + MAX_TRIES + " tries. Skipping test.")
        .isLessThan(MAX_TRIES);
    assertThat(ErrorLogUtil.getNumberOfWarnings(errorsAndWarnings))
        .as("Warnings were found.")
        .isZero();
    assertThat(ErrorLogUtil.getNumberOfErrors(errorsAndWarnings)).as("Errors were found.").isZero();
  }

  @Test
  public void globalChangePersonnelImportWithInvalidPersonnel() throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");

    List<ErrorLogsResponse> errorsAndWarnings = new ArrayList<>();

    int tries = 0;
    while (tries < MAX_TRIES) {
      AppLockUtil.releaseAllGCPLocks(AADM_User);

      UseGlobalChangeTo gcpApi = new UseGlobalChangeTo();

      GCPCollectionImportPostRequest gcpCollectionImportPostRequest =
          new GCPCollectionImportPostRequest();
      GCPImportPostRequest gcpImportPostRequest = getGCPCollectionImportTestData(AADM_User, false);
      gcpImportPostRequest.setFromPersonnelShortName("DOESNOTEXIST1"); // should create error
      gcpImportPostRequest.setToPersonnelShortName("DOESNOTEXIST2");
      gcpCollectionImportPostRequest.addGcpCollectionItem(gcpImportPostRequest);

      gcpCollectionImportPostRequest.setCustomerFileName("AutomationInvalidPersonnelGCP.csv");
      gcpCollectionImportPostRequest.setCollectionMemo("GCP EMS Automation Invalid Personnel");
      gcpCollectionImportPostRequest.setChangePersonalSuspenses(false);
      gcpCollectionImportPostRequest.setChangeCustomerSuspenses("None");
      gcpCollectionImportPostRequest.setChangeAlerts(false);
      gcpCollectionImportPostRequest.setChangePersonalNotes(false);
      gcpCollectionImportPostRequest.setFromPersonnelCommissionsSetToZero(false);
      gcpCollectionImportPostRequest.setToPersonnelCommissionsSetToDefault(false);

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

      String collectionId = gcpCollectionImportResponse.getGlobalChangeCollectionId();
      String referenceId = gcpCollectionImportResponse.getEventLogReferenceId();
      assertThat(collectionId).isNotNull();
      assertThat(referenceId).isNotNull();

      Thread.sleep(PROCESSING_DELAY_MS);
      errorsAndWarnings = ErrorLogUtil.getErrorsAndWarningsByReferenceId(AADM_User, referenceId);
      tries++;

      if (!ErrorLogUtil.warningMessageIsPresent(
              errorsAndWarnings,
              "Some customers are skipped because of business rules for Global Change for Personnel.")
          && !ErrorLogUtil.errorMessageIsPresent(
              errorsAndWarnings,
              "Unable to get application lock for Global Change for Personnel")) {
        break;
      }
      System.out.println(
          "Customer "
              + gcpImportPostRequest.getCustomerNumber()
              + " was skipped due to business rules or the app lock could not be retrieved.");
    }
    // skip assertions if data could not be found in the max number of tries
    assumeThat(tries)
        .as("Could not find an eligible customer in " + MAX_TRIES + " tries. Skipping test.")
        .isLessThan(MAX_TRIES);
    assertThat(ErrorLogUtil.getNumberOfWarnings(errorsAndWarnings)).isEqualTo(3);
    assertThat(ErrorLogUtil.getNumberOfErrors(errorsAndWarnings)).isEqualTo(1);
    assertThat(
            ErrorLogUtil.warningMessageIsPresent(
                errorsAndWarnings, "From personnel 'DOESNOTEXIST1' is not present and skipped."))
        .as("Expected warning message for from personnel not present, but it was not found.")
        .isTrue();
    assertThat(
            ErrorLogUtil.warningMessageIsPresent(
                errorsAndWarnings, "'DOESNOTEXIST1' as 'Exec' does not exist and is skipped."))
        .as("Expected warning message for nonexistent personnel, but it was not found.")
        .isTrue();
    assertThat(
            ErrorLogUtil.warningMessageIsPresent(
                errorsAndWarnings, "'DOESNOTEXIST2' as 'Exec' does not exist and is skipped."))
        .as("Expected warning message for nonexistent personnel, but it was not found.")
        .isTrue();
    assertThat(
            ErrorLogUtil.errorMessageIsPresent(
                errorsAndWarnings, "No records are selected for global change for personnel."))
        .as("Expected error message for no records selected, but it was not found.")
        .isTrue();
  }

  @Test
  public void globalChangePersonnelImportWithInvalidPersonnelType() {
    Actor AADM_User = theActorCalled("AADM_User");

    AppLockUtil.releaseAllGCPLocks(AADM_User);

    UseGlobalChangeTo gcpApi = new UseGlobalChangeTo();

    GCPCollectionImportPostRequest gcpCollectionImportPostRequest =
        new GCPCollectionImportPostRequest();
    GCPImportPostRequest gcpImportPostRequest = getGCPCollectionImportTestData(AADM_User, false);
    gcpImportPostRequest.setPersonnelType("InvalidType"); // should cause direct error with endpoint
    gcpCollectionImportPostRequest.addGcpCollectionItem(gcpImportPostRequest);

    gcpCollectionImportPostRequest.setCustomerFileName("AutomationGCPInvalidType.csv");
    gcpCollectionImportPostRequest.setCollectionMemo("GCP EMS Automation Invalid Personnel Type");
    gcpCollectionImportPostRequest.setChangePersonalSuspenses(false);
    gcpCollectionImportPostRequest.setChangeCustomerSuspenses("None");
    gcpCollectionImportPostRequest.setChangeAlerts(false);
    gcpCollectionImportPostRequest.setChangePersonalNotes(false);
    gcpCollectionImportPostRequest.setFromPersonnelCommissionsSetToZero(false);
    gcpCollectionImportPostRequest.setToPersonnelCommissionsSetToDefault(false);

    AADM_User.attemptsTo(
        gcpApi.POSTGlobalChangePersonnelCollectionImportOnThePersonnelglobalchangeController(
            gcpCollectionImportPostRequest, ""));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);

    ErrorResponse errorResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", ErrorResponse.class);

    assertThat(errorResponse).isNotNull();
    Util.validateErrorResponse(
        "GCPCollection[0] { The PersonnelType must equal one of the following: { 'Exec' or 'P', 'Rep' or 'R', 'Broker' or 'B', 'Sales Center Rep' or 'T' }. }",
        AADM_User);
  }
}
