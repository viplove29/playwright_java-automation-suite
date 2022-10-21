package com.vertafore.test.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.ems.GCPStatusResponse;
import com.vertafore.test.servicewrappers.UseGlobalChangeTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class GCPUtil {

  private static UseGlobalChangeTo gcpApi = new UseGlobalChangeTo();

  public static void waitForGCPProcessToComplete(String gcpHeaderId, Actor actor)
      throws InterruptedException {
    int tries = 0;
    int sleepTimer = 5000;
    GCPStatusResponse statusResponse;

    do {
      Thread.sleep(sleepTimer);
      actor.attemptsTo(
          gcpApi.GETGlobalChangePersonnelStatusOnThePersonnelglobalchangeController(
              gcpHeaderId, ""));
      assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

      statusResponse =
          LastResponse.received()
              .answeredBy(actor)
              .getBody()
              .jsonPath()
              .getObject("", GCPStatusResponse.class);
      tries++;
    } while (tries < 10 && !statusResponse.getIsGlobalChangeCompleted());

    assertThat(statusResponse.getIsGlobalChangeCompleted())
        .withFailMessage("GCP Process " + gcpHeaderId + " did not complete after 10 tries")
        .isTrue();
  }
}
