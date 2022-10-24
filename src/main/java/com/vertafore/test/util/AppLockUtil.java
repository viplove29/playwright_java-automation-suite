package com.vertafore.test.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseApplicationLockTo;
import com.vertafore.test.servicewrappers.UseApplicationLocksTo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class AppLockUtil {

  // These values are derived from the CheckOutConstants.cs class in the Main AMS repo
  private static final String CKOUT_TYPE_CUSTOMERSETUP = "10";
  private static final String CKOUT_TYPE_POLICY = "30";
  private static final String CKOUT_TYPE_GCP = "104";
  private static final String CKOUT_TYPE_SA_GLOBALCHANGE_DIVISIONCONSOLIDATION = "133";
  private static final String CKOUT_TYPE_EMS_GLOBALCHANGE_PERSONNEL = "132";

  // Controller API's
  private static UseApplicationLockTo applicationLockApi = new UseApplicationLockTo();
  private static UseApplicationLocksTo applicationLocksApi = new UseApplicationLocksTo();

  public static ApplicationLockResponse lockCustomer(
      String customerId, String description, Actor actor) {
    ApplicationLockCheckoutPostRequest appLockPostRequest =
        new ApplicationLockCheckoutPostRequest();
    appLockPostRequest.setRecordId(customerId);
    appLockPostRequest.setApplicationLockDescription(description);
    appLockPostRequest.setApplicationLockType(CKOUT_TYPE_CUSTOMERSETUP);

    actor.attemptsTo(
        applicationLockApi.POSTApplicationLockCheckoutOnTheApplicationlocksController(
            appLockPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ApplicationLockResponse applicationLockResponse =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", ApplicationLockResponse.class);

    assertThat(applicationLockResponse).isNotNull();
    assertThat(applicationLockResponse.getApplicationLockId()).isNotNull();

    return applicationLockResponse;
  }

  public static ApplicationLockResponse lockPolicy(
      String policyId, String description, Actor actor) {
    ApplicationLockCheckoutPostRequest appLockPostRequest =
        new ApplicationLockCheckoutPostRequest();
    appLockPostRequest.setRecordId(policyId);
    appLockPostRequest.setApplicationLockDescription(description);
    appLockPostRequest.setApplicationLockType(CKOUT_TYPE_POLICY);

    actor.attemptsTo(
        applicationLockApi.POSTApplicationLockCheckoutOnTheApplicationlocksController(
            appLockPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ApplicationLockResponse applicationLockResponse =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", ApplicationLockResponse.class);

    assertThat(applicationLockResponse).isNotNull();
    assertThat(applicationLockResponse.getApplicationLockId()).isNotNull();

    return applicationLockResponse;
  }

  public static ApplicationLockCheckinResponse releaseApplicationLockByApplicationLockId(
      String applicationLockId, Actor actor) {
    ApplicationLockCheckinPostRequest releasePostRequest = new ApplicationLockCheckinPostRequest();
    releasePostRequest.setApplicationLockId(applicationLockId);

    actor.attemptsTo(
        applicationLockApi.POSTApplicationLockCheckinOnTheApplicationlocksController(
            releasePostRequest, ""));

    ApplicationLockCheckinResponse releaseResponse =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", ApplicationLockCheckinResponse.class);

    assertThat(releaseResponse).isNotNull();
    assertThat(releaseResponse.getNumberOfLocksReleased()).isNotNull();
    assertThat(releaseResponse.getNumberOfLocksReleased()).isGreaterThan(0);

    return releaseResponse;
  }

  public static ApplicationLockCheckinResponse releaseApplicationLockByRecordId(
      String recordId, String checkoutType, Actor actor) {

    actor.attemptsTo(
        applicationLocksApi.GETApplicationLocksOnTheApplicationlocksController(checkoutType, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<ApplicationLockResponse> applicationLockResponses =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getList("", ApplicationLockResponse.class);

    assertThat(applicationLockResponses).isNotNull();
    assertThat(applicationLockResponses.isEmpty()).isFalse();

    applicationLockResponses =
        applicationLockResponses
            .stream()
            .filter(applock -> applock.getRecordId().equals(recordId))
            .collect(Collectors.toList());

    assertThat(applicationLockResponses.isEmpty())
        .as("no application locks with that record Id")
        .isFalse();

    ApplicationLockResponse desiredApplicationLock = applicationLockResponses.get(0);

    ApplicationLockCheckinPostRequest releasePostRequest = new ApplicationLockCheckinPostRequest();
    releasePostRequest.setApplicationLockId(desiredApplicationLock.getApplicationLockId());

    actor.attemptsTo(
        applicationLockApi.POSTApplicationLockCheckinOnTheApplicationlocksController(
            releasePostRequest, ""));

    ApplicationLockCheckinResponse releaseResponse =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", ApplicationLockCheckinResponse.class);

    assertThat(releaseResponse).isNotNull();
    assertThat(releaseResponse.getNumberOfLocksReleased()).isNotNull();
    assertThat(releaseResponse.getNumberOfLocksReleased()).isGreaterThan(0);

    return releaseResponse;
  }

  public static ApplicationLockCheckinResponse releaseCustomerByCustomerId(
      String customerId, Actor actor) {
    return releaseApplicationLockByRecordId(customerId, CKOUT_TYPE_CUSTOMERSETUP, actor);
  }

  public static ApplicationLockCheckinResponse releasePolicyByPolicyId(
      String policyId, Actor actor) {
    return releaseApplicationLockByRecordId(policyId, CKOUT_TYPE_POLICY, actor);
  }

  public static List<ApplicationLockResponse> getAllPolicyApplicationLocks(Actor actor) {
    // get all policy locks
    actor.attemptsTo(
        applicationLocksApi.GETApplicationLocksOnTheApplicationlocksController(
            CKOUT_TYPE_POLICY, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode())
        .withFailMessage(SerenityRest.lastResponse().toString())
        .isEqualTo(200);
    List<ApplicationLockResponse> policyLocks =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getList("", ApplicationLockResponse.class);

    assertThat(policyLocks).isNotNull();
    return policyLocks;
  }

  public static void releaseAllPolicyApplicationLocks(Actor actor) {
    List<ApplicationLockResponse> allPolicyLocks = getAllPolicyApplicationLocks(actor);

    for (ApplicationLockResponse policyLock : allPolicyLocks) {
      releaseApplicationLockByApplicationLockId(policyLock.getApplicationLockId(), actor);
    }
  }

  public static List<ApplicationLockResponse> getAllGCPLocks(Actor actor) {
    // 104 locks
    actor.attemptsTo(
        applicationLocksApi.GETApplicationLocksOnTheApplicationlocksController(CKOUT_TYPE_GCP, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode())
        .withFailMessage(SerenityRest.lastResponse().toString())
        .isEqualTo(200);
    List<ApplicationLockResponse> gcpLocks =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getList("", ApplicationLockResponse.class);

    actor.attemptsTo(
        applicationLocksApi.GETApplicationLocksOnTheApplicationlocksController(CKOUT_TYPE_GCP, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode())
        .withFailMessage(SerenityRest.lastResponse().toString())
        .isEqualTo(200);
    // 133 locks
    List<ApplicationLockResponse> gcpDivisionConsolidationLocks =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getList("", ApplicationLockResponse.class);

    // 132 locks
    actor.attemptsTo(
        applicationLocksApi.GETApplicationLocksOnTheApplicationlocksController(CKOUT_TYPE_GCP, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode())
        .withFailMessage(SerenityRest.lastResponse().toString())
        .isEqualTo(200);
    List<ApplicationLockResponse> gcPersonnelLocks =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getList("", ApplicationLockResponse.class);

    List<ApplicationLockResponse> allGCPLocks = new ArrayList<>();
    allGCPLocks.addAll(gcpDivisionConsolidationLocks);
    allGCPLocks.addAll(gcPersonnelLocks);
    allGCPLocks.addAll(gcpLocks);

    return allGCPLocks;
  }

  public static void releaseAllGCPLocks(Actor actor) {
    List<ApplicationLockResponse> allGcpLocks = getAllGCPLocks(actor);

    for (ApplicationLockResponse gcpLock : allGcpLocks) {
      releaseApplicationLockByApplicationLockId(gcpLock.getApplicationLockId(), actor);
    }
  }
}
