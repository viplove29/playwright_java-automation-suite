package com.vertafore.test.services.lines_of_business;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.servicewrappers.UseLinesOfBusinessTo;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_LOB extends TokenSuperClass {

  /* TODO this whole class needs to be reviewed and updated, and the accompanying LOB class addressed */

  @Test
  public void LOBReturnsCorrectResponseBody() {

    Actor ORAN_App = theActorCalled("ORAN_App");

    UseLinesOfBusinessTo lobAPI = new UseLinesOfBusinessTo();

    ORAN_App.attemptsTo(
        lobAPI.GETLinesOfBusinessOnTheLinesofbusinessControllerDeprecated(false, "string"));

    ORAN_App.should(seeThatResponse("successfully gets response", res -> res.statusCode(200)));
    Object result =
        LastResponse.received()
            .answeredBy(ORAN_App)
            .getBody()
            .jsonPath()
            .getList("", LOB.class)
            .get(0);
    assertThat(result != null).isTrue();
    assertThat(result.getClass().getDeclaredFields().length).isEqualTo(4);

    assertThat(
            LastResponse.received()
                .answeredBy(ORAN_App)
                .getBody()
                .jsonPath()
                .getString("sdeCode")
                .split(", "))
        .contains("GL");
    assertThat(
            LastResponse.received()
                .answeredBy(ORAN_App)
                .getBody()
                .jsonPath()
                .getString("sdeDescription")
                .split(", "))
        .contains("General Liability");
  }

  @Test
  public void LOBReturnsActiveLOBs() {

    Actor ORAN_App = theActorCalled("ORAN_App");

    UseLinesOfBusinessTo lobAPI = new UseLinesOfBusinessTo();

    ORAN_App.attemptsTo(
        lobAPI.GETLinesOfBusinessOnTheLinesofbusinessControllerDeprecated(false, "string"));

    ORAN_App.should(seeThatResponse("successfully gets response", res -> res.statusCode(200)));
    int onlyActive =
        LastResponse.received().answeredBy(ORAN_App).getBody().jsonPath().getList("").size();

    ORAN_App.attemptsTo(
        lobAPI.GETLinesOfBusinessOnTheLinesofbusinessControllerDeprecated(null, "string"));

    ORAN_App.should(seeThatResponse("successfully gets response", res -> res.statusCode(200)));
    int onlyActive2 =
        LastResponse.received().answeredBy(ORAN_App).getBody().jsonPath().getList("").size();
    Check.whether(onlyActive == onlyActive2);
  }

  @Test
  public void LOBReturnsInactiveLOBs() {

    Actor ORAN_App = theActorCalled("ORAN_App");

    UseLinesOfBusinessTo lobAPI = new UseLinesOfBusinessTo();

    ORAN_App.attemptsTo(
        lobAPI.GETLinesOfBusinessOnTheLinesofbusinessControllerDeprecated(true, "string"));

    ORAN_App.should(seeThatResponse("successfully gets response", res -> res.statusCode(200)));
    int includesInactive =
        LastResponse.received().answeredBy(ORAN_App).getBody().jsonPath().getList("").size();

    ORAN_App.attemptsTo(
        lobAPI.GETLinesOfBusinessOnTheLinesofbusinessControllerDeprecated(null, "string"));

    ORAN_App.should(seeThatResponse("successfully gets response", res -> res.statusCode(200)));
    int onlyActive =
        LastResponse.received().answeredBy(ORAN_App).getBody().jsonPath().getList("").size();
    Check.whether(includesInactive > onlyActive);
  }
}
