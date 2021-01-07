package com.vertafore.test.services.suspenses;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.ActionResponse;
import com.vertafore.test.servicewrappers.UseSuspensesTo;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import net.thucydides.core.annotations.WithTag;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_SuspenseActions {

  private List<EMSActor> actors = new ArrayList<>();

  @Before
  public void getAnAccessToken() {
    actors.addAll(
        List.of(
            new EMSActor().called("bob").withContext("userContext"),
            new EMSActor().called("doug").withContext("appContext"),
            new EMSActor().called("adam").withContext("adminContext"),
            new EMSActor().called("mary").withContext("userContext").withVersion("19R2")));
    OnStage.setTheStage(GetAnAccessToken(actors));
  }

  @Test
  @WithTag("19R2")
  public void SuspenseActionsReturnsAllSuspenseActions() {

    Actor mary = theActorCalled("mary");

    UseSuspensesTo suspensesAPI = new UseSuspensesTo();

    mary.attemptsTo(suspensesAPI.GETSuspensesActionsOnTheSuspensesController());

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ActionResponse action =
        LastResponse.received()
            .answeredBy(mary)
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
