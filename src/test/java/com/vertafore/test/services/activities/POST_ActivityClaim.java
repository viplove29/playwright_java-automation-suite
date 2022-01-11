package com.vertafore.test.services.activities;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.ActivityClaimResponse;
import com.vertafore.test.servicewrappers.UseActivityTo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_ActivityClaim {

  private List<EMSActor> actors = new ArrayList<>();

  @Before
  public void getAnAccessToken() {
    actors.addAll(List.of(new EMSActor().called("bob").withContext("userContext")));
    OnStage.setTheStage(GetAnAccessToken(actors));
  }

  /* TODO this test needs checks for ORAN and VADM keys, as well as checks on all the other parts of the post body */
  @Test
  public void PostActivityClaimSuccessfullyPostsOneClaim() {

    Actor bob = theActorCalled("bob");

    UseActivityTo ActivityAPI = new UseActivityTo();
    HashMap<String, String> claimBody = new HashMap<>();
    String randomClaimId = UUID.randomUUID().toString();
    String randomCustId = UUID.randomUUID().toString();

    claimBody.put("action", "string");
    claimBody.put("ClaimId", randomClaimId);
    claimBody.put("CustomerId", randomCustId);
    claimBody.put("transactionDate", LocalDateTime.now().toString());

    bob.attemptsTo(ActivityAPI.POSTActivityClaimOnTheActivitiesController(claimBody, "string"));

    bob.should(seeThatResponse("Activity Claims Returned", res -> res.statusCode(200)));

    SerenityRest.lastResponse().prettyPrint();
    ActivityClaimResponse claimResponse =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getObject("", ActivityClaimResponse.class);

    assertThat(claimResponse != null).isTrue();

    /* TODO this test is using the GET /activity endpoint to check, this should be wrapped up in a larger test for that endpoint */
    bob.attemptsTo(ActivityAPI.GETActivityOnTheActivitiesController(randomClaimId, "string"));

    bob.should(seeThatResponse("Activity Claims Returned", res -> res.statusCode(200)));
  }
}
