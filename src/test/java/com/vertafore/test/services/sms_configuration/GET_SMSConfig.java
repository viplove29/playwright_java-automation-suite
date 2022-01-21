package com.vertafore.test.services.sms_configuration;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.SmsConfigResponse;
import com.vertafore.test.servicewrappers.UseSmsTo;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_SMSConfig {
  private List<EMSActor> actors = new ArrayList<>();

  @Before
  public void getAnAccessToken() {
    actors.addAll(
        List.of(
            new EMSActor().called("bob").withContext("userContext"),
            new EMSActor().called("doug").withContext("appContext"),
            new EMSActor().called("adam").withContext("adminContext")));
    OnStage.setTheStage(GetAnAccessToken(actors));
  }

  // Basic smoke test to confirm the endpoint is functional
  @Test
  public void smsconfigReturnsAgencyPhoneInfo() {
    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    UseSmsTo smsconfigAPI = new UseSmsTo();

    adam.attemptsTo(smsconfigAPI.GETSmsConfigOnTheSmsconfigurationController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    doug.attemptsTo(smsconfigAPI.GETSmsConfigOnTheSmsconfigurationController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    bob.attemptsTo(smsconfigAPI.GETSmsConfigOnTheSmsconfigurationController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    SmsConfigResponse smsResponse =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getObject("", SmsConfigResponse.class);

    // Response body format assertions
    assertThat(smsResponse.getClass().getDeclaredFields().length).isEqualTo(2);
    assertThat(smsResponse.getClass().getDeclaredFields()[0].getName()).isEqualTo("phoneNo");
    assertThat(smsResponse.getClass().getDeclaredFields()[1].getName()).isEqualTo("status");
  }
}
