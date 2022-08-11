package com.vertafore.test.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UsePurgeTo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class PurgeUtil {
  private static UsePurgeTo purgeAPI = new UsePurgeTo();

  public static Map<String, String> getPurgeFiscalEndDateAndDivisionCode(Actor actor) {
    actor.attemptsTo(purgeAPI.GETPurgeFiscalYearOnThePurgeController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PurgeFiscalYearDivisionResponse purgeFiscalYearDivisionResponse =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getList("", PurgeFiscalYearDivisionResponse.class)
            .get(0);

    Map<String, String> fiscalEndDateAndDivisionCode = new HashMap<>();
    fiscalEndDateAndDivisionCode.put(
        "fiscalEndDate", purgeFiscalYearDivisionResponse.getFiscalEndDate().substring(0, 4));
    fiscalEndDateAndDivisionCode.put(
        "divisionCode", purgeFiscalYearDivisionResponse.getDivisionCode());

    return fiscalEndDateAndDivisionCode;
  }

  public static List<PurgePolicyCandidateResponse> getPurgePolicyResponse(
      Actor actor,
      PagingRequestPurgePolicySearchPostRequest pagingRequestPurgePolicySearchPostRequest) {
    actor.attemptsTo(
        purgeAPI.POSTPurgePoliciesSearchOnThePurgeController(
            pagingRequestPurgePolicySearchPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PagingResponsePurgePolicyCandidateResponse pagingResponsePurgePolicyCandidateResponse =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponsePurgePolicyCandidateResponse.class);

    return pagingResponsePurgePolicyCandidateResponse.getResponse();
  }

  // Continuously check purge status until it is false, when getPurgeStatus returns false
  // the Purge process has completed
  public static void waitForPurgeProcessToComplete(Actor actor) throws InterruptedException {
    int tries = 0;

    while (tries < 60) {
      actor.attemptsTo(purgeAPI.GETPurgeStatusOnThePurgeController());
      assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

      PurgeStatusResponse purgeStatusResponse =
          LastResponse.received()
              .answeredBy(actor)
              .getBody()
              .jsonPath()
              .getObject("", PurgeStatusResponse.class);

      if (!purgeStatusResponse.getIsPurgeRunning()) return;
      tries++;
      Thread.sleep(1000);
    }
    throw new RuntimeException("Purge Policy did not complete after " + tries + " tries");
  }

  public static String purgePolicy(
      Actor actor, PurgePolicyDeletePostRequest purgePolicyDeletePostRequest) {
    actor.attemptsTo(
        purgeAPI.POSTPurgePoliciesDeleteOnThePurgeController(purgePolicyDeletePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PurgeSessionResponse purgeSessionResponse =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", PurgeSessionResponse.class);

    return purgeSessionResponse.getPurgeSessionId();
  }
}
