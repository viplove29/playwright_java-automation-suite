package com.vertafore.test.services.titan;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.TitanEntitlementResponse;
import com.vertafore.test.servicewrappers.UseTitanTo;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_TitanEntitlement extends TokenSuperClass {

  @Test
  public void getTitanEntitlement() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");
    UseTitanTo ActivityAPI = new UseTitanTo();

    ORAN_App.attemptsTo(ActivityAPI.GETTitanEntitlementOnTheTitanController("CLS", ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(ActivityAPI.GETTitanEntitlementOnTheTitanController("CLS", ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(ActivityAPI.GETTitanEntitlementOnTheTitanController("CLS", ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    TitanEntitlementResponse response =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", TitanEntitlementResponse.class);

    assertThat(response).isNotNull();
    assertThat(response.getClass().getDeclaredFields()[0].getName()).isEqualTo("hasEntitlement");
  }
}
