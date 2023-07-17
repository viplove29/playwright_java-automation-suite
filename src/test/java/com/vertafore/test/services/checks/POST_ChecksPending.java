package com.vertafore.test.services.checks;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseChecksTo;
import com.vertafore.test.util.CheckUtil;
import com.vertafore.test.util.ErrorLogUtil;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_ChecksPending extends TokenSuperClass {

  @Test
  public void postPendingCheckIsSuccessful() throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseChecksTo checksAPI = new UseChecksTo();

    int lastKnownNumberOfPendingChecks = CheckUtil.getNumberOfPendingChecks(AADM_User);
    assumeThat(lastKnownNumberOfPendingChecks)
        .as("There are not enough pending checks in the database to run this test.")
        .isGreaterThanOrEqualTo(2);

    // get a random pending check for posting
    PendingCheckSearchResponse checkToPost =
        CheckUtil.getRandomPendingCheckWithPayeeNameContaining(AADM_User, "post");
    PendingCheckCollectionPostRequest pendingCheckCollectionPostRequest =
        new PendingCheckCollectionPostRequest();
    pendingCheckCollectionPostRequest.addPendingCheckIdsItem(checkToPost.getCashDisbursementId());

    ORAN_App.attemptsTo(
        checksAPI.POSTChecksPendingOnTheChecksController(
            pendingCheckCollectionPostRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // allow time for checks to process
    Thread.sleep(3000);

    // verify there is at least 1 less pending check after posting
    int numberOfPendingChecks = CheckUtil.getNumberOfPendingChecks(AADM_User);
    assertThat(numberOfPendingChecks).isLessThan(lastKnownNumberOfPendingChecks);
    lastKnownNumberOfPendingChecks = numberOfPendingChecks;

    // verify posted check is no longer returned by the endpoint
    assertThat(CheckUtil.checkWithPayeeNameIsPending(AADM_User, checkToPost.getCheckPayee()))
        .isFalse();

    // get new random check since ORAN_App posted the last one
    checkToPost = CheckUtil.getRandomPendingCheckWithPayeeNameContaining(AADM_User, "post");
    pendingCheckCollectionPostRequest = new PendingCheckCollectionPostRequest();
    pendingCheckCollectionPostRequest.addPendingCheckIdsItem(checkToPost.getCashDisbursementId());

    VADM_Admin.attemptsTo(
        checksAPI.POSTChecksPendingOnTheChecksController(
            pendingCheckCollectionPostRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // don't need to get new check because VADM_Admin cannot post
    AADM_User.attemptsTo(
        checksAPI.POSTChecksPendingOnTheChecksController(
            pendingCheckCollectionPostRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PendingCheckCollectionResponse pendingCheckCollectionResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PendingCheckCollectionResponse.class);

    // allow time for checks to process
    Thread.sleep(3000);

    // verify there is at least 1 less pending check after posting
    numberOfPendingChecks = CheckUtil.getNumberOfPendingChecks(AADM_User);
    assertThat(numberOfPendingChecks).isLessThan(lastKnownNumberOfPendingChecks);

    // verify posted check is no longer returned by the endpoint
    assertThat(CheckUtil.checkWithPayeeNameIsPending(AADM_User, checkToPost.getCheckPayee()))
        .isFalse();

    assertThat(pendingCheckCollectionResponse).isNotNull();
    assertThat(pendingCheckCollectionResponse.getClass().getDeclaredFields().length).isEqualTo(2);

    // verify there are no errors in the error log
    assertThat(pendingCheckCollectionResponse.getEventLogReferenceId()).isNotNull();
    assertThat(
            ErrorLogUtil.getErrorsAndWarningsByReferenceId(
                AADM_User, pendingCheckCollectionResponse.getEventLogReferenceId()))
        .isEmpty();
  }

  @Test
  public void postMultiplePendingChecksIsSuccessful() throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");

    UseChecksTo checksAPI = new UseChecksTo();

    int initialNumberOfPendingChecks = CheckUtil.getNumberOfPendingChecks(AADM_User);
    assumeThat(initialNumberOfPendingChecks)
        .as("There are not enough pending checks in the database to run this test.")
        .isGreaterThanOrEqualTo(2);

    // get 2 unique random checks for posting in the same request
    List<PendingCheckSearchResponse> checksToPost =
        CheckUtil.getMultipleRandomPendingChecksWithPayeeNameContaining(AADM_User, 2, "post");

    PendingCheckCollectionPostRequest pendingCheckCollectionPostRequest =
        new PendingCheckCollectionPostRequest();
    for (PendingCheckSearchResponse checkToPost : checksToPost) {
      pendingCheckCollectionPostRequest.addPendingCheckIdsItem(checkToPost.getCashDisbursementId());
    }

    // post both checks at the same time
    AADM_User.attemptsTo(
        checksAPI.POSTChecksPendingOnTheChecksController(
            pendingCheckCollectionPostRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PendingCheckCollectionResponse pendingCheckCollectionResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PendingCheckCollectionResponse.class);

    // allow time for checks to process
    Thread.sleep(3000);

    // verify there are at least 2 less pending checks after posting 2 checks
    assertThat(CheckUtil.getNumberOfPendingChecks(AADM_User))
        .isLessThanOrEqualTo(initialNumberOfPendingChecks - 2);

    // verify posted checks are no longer returned by the endpoint
    for (PendingCheckSearchResponse postedCheck : checksToPost) {
      assertThat(CheckUtil.checkWithPayeeNameIsPending(AADM_User, postedCheck.getCheckPayee()))
          .isFalse();
    }

    assertThat(pendingCheckCollectionResponse).isNotNull();
    assertThat(pendingCheckCollectionResponse.getClass().getDeclaredFields().length).isEqualTo(2);

    // verify there are no errors in the error log
    assertThat(pendingCheckCollectionResponse.getEventLogReferenceId()).isNotNull();
    assertThat(
            ErrorLogUtil.getErrorsAndWarningsByReferenceId(
                AADM_User, pendingCheckCollectionResponse.getEventLogReferenceId()))
        .isEmpty();
  }
}
