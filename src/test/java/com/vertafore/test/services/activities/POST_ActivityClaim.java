package com.vertafore.test.services.activities;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.assertj.core.api.Assertions.assertThat;

import com.google.gson.Gson;
import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.ActivityClaimResponse;
import com.vertafore.test.servicewrappers.UseActivityTo;
import java.util.ArrayList;
import java.util.HashMap;
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
public class POST_ActivityClaim {

  private List<EMSActor> actors = new ArrayList<>();

  @Before
  public void getAnAccessToken() {
    actors.addAll(List.of(new EMSActor().called("mary").withVersion("19R2")));
    OnStage.setTheStage(GetAnAccessToken(actors));
  }

  @Test
  @WithTag("19R2")
  public void PostActivityClaimSuccessfullyPostsOneClaim() {

    Actor mary = theActorCalled("mary");

    UseActivityTo ActivityAPI = new UseActivityTo();
    HashMap<String, String> tokenBody = new HashMap<>();
    tokenBody.put("action", "string");
    tokenBody.put("ClaimId", "0886D269-F5C6-4428-9DD0-292C8820E5BD");
    tokenBody.put("CustomerId", "F159F623-57E1-4482-B6C7-2AA87DB8B193");
    tokenBody.put("transactionDate", "2020-08-14T16:31:33.225Z");
    String jsonObject = new Gson().toJson(tokenBody);
    mary.attemptsTo(ActivityAPI.POSTActivityClaimOnTheActivitiesController(jsonObject, "string"));

    mary.should(seeThatResponse("Activity Claims Returned", res -> res.statusCode(200)));

    SerenityRest.lastResponse().prettyPrint();
    ActivityClaimResponse claimResponse =
        LastResponse.received()
            .answeredBy(mary)
            .getBody()
            .jsonPath()
            .getObject("", ActivityClaimResponse.class);

    assertThat(claimResponse != null).isTrue();
    // assertThat(claimResponse.getClass().getDeclaredFields().length).isEqualTo(4);

    String activityClaimId = claimResponse.getActivityClaimId();

    mary.attemptsTo(ActivityAPI.GETActivityOnTheActivitiesController(activityClaimId, "string"));

    mary.should(seeThatResponse("Activity Claims Returned", res -> res.statusCode(200)));
  }
}
