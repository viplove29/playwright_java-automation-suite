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

    // TODO what's the difference between true or false for LatestOnly param?
    doug.attemptsTo(eformsApi.GETEFormsActiveFormsOnTheEformsController(false, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    doug.attemptsTo(eformsApi.GETEFormsActiveFormsOnTheEformsController(true, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    adam.attemptsTo(eformsApi.GETEFormsActiveFormsOnTheEformsController(false, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
    adam.attemptsTo(eformsApi.GETEFormsActiveFormsOnTheEformsController(true, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    bob.attemptsTo(eformsApi.GETEFormsActiveFormsOnTheEformsController(false, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    bob.attemptsTo(eformsApi.GETEFormsActiveFormsOnTheEformsController(true, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AcordFormInfoResponse formResponse =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getList("", AcordFormInfoResponse.class)
            .get(0);

    // basic field name assertions
    assertThat(formResponse.getClass().getDeclaredFields().length).isEqualTo(9);
    assertThat(formResponse.getClass().getDeclaredFields()[0].getName()).isEqualTo("description");
    assertThat(formResponse.getClass().getDeclaredFields()[1].getName()).isEqualTo("number");
    assertThat(formResponse.getClass().getDeclaredFields()[2].getName()).isEqualTo("state");
    assertThat(formResponse.getClass().getDeclaredFields()[3].getName()).isEqualTo("edition");
    assertThat(formResponse.getClass().getDeclaredFields()[4].getName()).isEqualTo("mapRuleIdList");
    assertThat(formResponse.getClass().getDeclaredFields()[5].getName())
        .isEqualTo("resourceIdList");
    assertThat(formResponse.getClass().getDeclaredFields()[6].getName())
        .isEqualTo("hasACORDFieldNames");
    assertThat(formResponse.getClass().getDeclaredFields()[7].getName())
        .isEqualTo("isMappedToDatabase");
    assertThat(formResponse.getClass().getDeclaredFields()[8].getName())
        .isEqualTo("elfFormVersionId");
  }
}
