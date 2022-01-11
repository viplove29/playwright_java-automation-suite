package com.vertafore.test.services.eforms;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.AcordFormInfoResponse;
import com.vertafore.test.servicewrappers.UseEFormsTo;
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
public class GET_ActiveForms {
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

  @Test
  public void eformsActiveFormsReturnsAllActiveForms() {
    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    UseEFormsTo eformsApi = new UseEFormsTo();

    bob.attemptsTo(eformsApi.GETEFormsActiveFormsOnTheEformsController(false, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    bob.attemptsTo(eformsApi.GETEFormsActiveFormsOnTheEformsController(true, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AcordFormInfoResponse form =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getList("", AcordFormInfoResponse.class)
            .get(0);

    // TODO this is a hardcoded response that needs to be addressed
    assertThat(form.getDescription()).isEqualTo("Accidents/Convictions Schedule");
    assertThat(form.getNumber()).isEqualTo("99");
    assertThat(form.getState()).isEqualTo("US");
    assertThat(form.getElfFormVersionId()).isNotNull();

    doug.attemptsTo(eformsApi.GETEFormsActiveFormsOnTheEformsController(false, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    doug.attemptsTo(eformsApi.GETEFormsActiveFormsOnTheEformsController(true, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    adam.attemptsTo(eformsApi.GETEFormsActiveFormsOnTheEformsController(false, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    adam.attemptsTo(eformsApi.GETEFormsActiveFormsOnTheEformsController(true, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }
}
