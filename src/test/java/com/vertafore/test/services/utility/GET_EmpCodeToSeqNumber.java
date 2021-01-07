package com.vertafore.test.services.utility;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.SeqNumberResponse;
import com.vertafore.test.servicewrappers.UseUtilityTo;
import io.restassured.path.json.JsonPath;
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
public class GET_EmpCodeToSeqNumber {
  private List<EMSActor> actors = new ArrayList<>();

  @Before
  public void getAnAccessToken() {
    actors.addAll(
        List.of(
            new EMSActor().called("bob").withContext("userContext"),
            new EMSActor().called("doug").withContext("appContext"),
            new EMSActor().called("adam").withContext("adminContext"),
            new EMSActor().called("mary").withVersion("19R2")));
    OnStage.setTheStage(GetAnAccessToken(actors));
  }

  @Test
  public void empCodeReturnsCorrectStatusCode() {
    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    UseUtilityTo utilityApi = new UseUtilityTo();

    bob.attemptsTo(utilityApi.GETUtilityEmpCodeToSeqNumberOnTheUtilityController("!#^", "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    SerenityRest.lastResponse().prettyPrint();

    doug.attemptsTo(utilityApi.GETUtilityEmpCodeToSeqNumberOnTheUtilityController("!#^", "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    adam.attemptsTo(utilityApi.GETUtilityEmpCodeToSeqNumberOnTheUtilityController("!#^", "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }

  @Test
  public void empCodeReturnsCorrectSeqNumber() {
    Actor bob = theActorCalled("bob");

    UseUtilityTo utilityApi = new UseUtilityTo();

    bob.attemptsTo(utilityApi.GETUtilityEmpCodeToSeqNumberOnTheUtilityController("!#^", "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    JsonPath jsonPath = LastResponse.received().answeredBy(bob).getBody().jsonPath();

    // Use getObject because return object is a hashmap, not a list
    SeqNumberResponse seqResponse =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getObject("", SeqNumberResponse.class);

    assertThat(seqResponse.getSequenceNumber()).isEqualTo("033035094");
  }
}
