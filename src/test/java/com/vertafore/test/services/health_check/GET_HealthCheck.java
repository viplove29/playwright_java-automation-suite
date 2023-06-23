package com.vertafore.test.services.health_check;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.servicewrappers.UseHealthCheckTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class GET_HealthCheck extends TokenSuperClass {

  @Test
  public void getHealthCheckBaselineTest() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseHealthCheckTo healthCheckApi = new UseHealthCheckTo();

    AADM_User.attemptsTo(healthCheckApi.GETHealthCheckOnTheHealthcheckControllerDeprecated());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ORAN_App.attemptsTo(healthCheckApi.GETHealthCheckOnTheHealthcheckControllerDeprecated());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(healthCheckApi.GETHealthCheckOnTheHealthcheckControllerDeprecated());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
