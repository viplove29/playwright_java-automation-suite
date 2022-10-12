package com.vertafore.test.services.sms_configuration;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.SmsConfigResponse;
import com.vertafore.test.servicewrappers.UseSmsTo;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_SMSConfig extends TokenSuperClass {

  // Basic smoke test to confirm the endpoint is functional
  @Test
  public void smsconfigReturnsAgencyPhoneInfo() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseSmsTo smsconfigAPI = new UseSmsTo();

    VADM_Admin.attemptsTo(smsconfigAPI.GETSmsConfigOnTheSmsconfigurationController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(smsconfigAPI.GETSmsConfigOnTheSmsconfigurationController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);

    AADM_User.attemptsTo(smsconfigAPI.GETSmsConfigOnTheSmsconfigurationController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    SmsConfigResponse smsResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", SmsConfigResponse.class);

    // Response body format assertions
    assertThat(smsResponse.getClass().getDeclaredFields().length).isEqualTo(2);
    assertThat(smsResponse.getClass().getDeclaredFields()[0].getName()).isEqualTo("phoneNo");
    assertThat(smsResponse.getClass().getDeclaredFields()[1].getName()).isEqualTo("status");
  }
}
