package LOB;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.servicewrappers.UseLinesOfBusinessTo;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_LOB {

  private List<EMSActor> actors = new ArrayList<>();

  @Before
  public void getAnAccessToken() {
    actors.addAll(List.of(new EMSActor().called("doug").withContext("appContext")));
    OnStage.setTheStage(GetAnAccessToken(actors));
  }

  @Test
  public void LOBReturnsCorrectResponseBody() {

    Actor doug = theActorCalled("doug");

    UseLinesOfBusinessTo lobAPI = new UseLinesOfBusinessTo();

    doug.attemptsTo(
        lobAPI.GETLinesOfBusinessOnTheLinesofbusinessControllerDeprecated(false, "string"));

    SerenityRest.lastResponse().prettyPrint();

    doug.should(seeThatResponse("successfully gets response", res -> res.statusCode(200)));
    Object result =
        LastResponse.received().answeredBy(doug).getBody().jsonPath().getList("", LOB.class).get(0);
    assertThat(result != null).isTrue();
    assertThat(result.getClass().getDeclaredFields().length).isEqualTo(4);

    assertThat(
            LastResponse.received()
                .answeredBy(doug)
                .getBody()
                .jsonPath()
                .getString("sdeCode")
                .split(", "))
        .contains("GL");
    assertThat(
            LastResponse.received()
                .answeredBy(doug)
                .getBody()
                .jsonPath()
                .getString("sdeDescription")
                .split(", "))
        .contains("General Liability");
  }

  @Test
  public void LOBReturnsActiveLOBs() {

    Actor doug = theActorCalled("doug");

    UseLinesOfBusinessTo lobAPI = new UseLinesOfBusinessTo();

    doug.attemptsTo(
        lobAPI.GETLinesOfBusinessOnTheLinesofbusinessControllerDeprecated(false, "string"));

    SerenityRest.lastResponse().prettyPrint();

    doug.should(seeThatResponse("successfully gets response", res -> res.statusCode(200)));
    int onlyActive =
        LastResponse.received().answeredBy(doug).getBody().jsonPath().getList("").size();

    doug.attemptsTo(
        lobAPI.GETLinesOfBusinessOnTheLinesofbusinessControllerDeprecated(null, "string"));

    SerenityRest.lastResponse().prettyPrint();

    doug.should(seeThatResponse("successfully gets response", res -> res.statusCode(200)));
    int onlyActive2 =
        LastResponse.received().answeredBy(doug).getBody().jsonPath().getList("").size();
    Check.whether(onlyActive == onlyActive2);
  }

  @Test
  public void LOBReturnsInactiveLOBs() {

    Actor doug = theActorCalled("doug");

    UseLinesOfBusinessTo lobAPI = new UseLinesOfBusinessTo();

    doug.attemptsTo(
        lobAPI.GETLinesOfBusinessOnTheLinesofbusinessControllerDeprecated(true, "string"));

    SerenityRest.lastResponse().prettyPrint();

    doug.should(seeThatResponse("successfully gets response", res -> res.statusCode(200)));
    int includesInactive =
        LastResponse.received().answeredBy(doug).getBody().jsonPath().getList("").size();

    doug.attemptsTo(
        lobAPI.GETLinesOfBusinessOnTheLinesofbusinessControllerDeprecated(null, "string"));

    SerenityRest.lastResponse().prettyPrint();

    doug.should(seeThatResponse("successfully gets response", res -> res.statusCode(200)));
    int onlyActive =
        LastResponse.received().answeredBy(doug).getBody().jsonPath().getList("").size();
    Check.whether(includesInactive > onlyActive);
  }
}
