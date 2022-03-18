package com.vertafore.test.services.suspenses;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ActionResponse;
import com.vertafore.test.servicewrappers.UseSuspensesTo;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_SuspenseActions extends TokenSuperClass {

  /* TODO this needs tests for ORAN and VADM keys, as well as dealing with hardcoded info */

  @Test
  public void SuspenseActionsReturnsAllSuspenseActions() {

    Actor AADM_User = theActorCalled("AADM_User");

    UseSuspensesTo suspensesAPI = new UseSuspensesTo();

    AADM_User.attemptsTo(suspensesAPI.GETSuspensesActionsOnTheSuspensesController());

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ActionResponse action =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", ActionResponse.class)
            .get(0);

    assertThat(action != null).isTrue();
    assertThat(action.getClass().getDeclaredFields().length).isEqualTo(4);

    assertThat(action.getActionId()).isEqualTo("43138610-4a9e-4ed0-8f81-0e858048b409");
    assertThat(action.getActionName()).isEqualTo("Acord Forms");
    assertThat(action.getIsClaimAction()).isEqualTo("Y");
    assertThat(action.getIsPolicyAction()).isEqualTo("Y");
  }
}
