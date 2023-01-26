package com.vertafore.test.services.activities;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ActivityClaimResponse;
import com.vertafore.test.models.ems.CustomerIdResponse;
import com.vertafore.test.servicewrappers.UseActivityTo;
import com.vertafore.test.util.CustomerUtil;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_ActivityClaim extends TokenSuperClass {

  /* TODO this test needs checks for ORAN and VADM keys, as well as checks on all the other parts of the post body */
  @Test
  public void PostActivityClaimSuccessfullyPostsOneClaim() {

    Actor AADM_User = theActorCalled("AADM_User");
    CustomerIdResponse randomCustomer = CustomerUtil.stageARandomCustomer(AADM_User);

    UseActivityTo ActivityAPI = new UseActivityTo();
    HashMap<String, String> claimBody = new HashMap<>();
    String randomClaimId = UUID.randomUUID().toString();
    String randomCustId = randomCustomer.getCustomerId();

    claimBody.put("action", "string");
    claimBody.put("ClaimId", randomClaimId);
    claimBody.put("CustomerId", randomCustId);
    claimBody.put("transactionDate", LocalDateTime.now().toString());

    AADM_User.attemptsTo(
        ActivityAPI.POSTActivityClaimOnTheActivitiesController(claimBody, "string"));

    AADM_User.should(seeThatResponse("Activity Claims Returned", res -> res.statusCode(200)));

    ActivityClaimResponse claimResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", ActivityClaimResponse.class);

    assertThat(claimResponse != null).isTrue();

    /* TODO this test is using the GET /activity endpoint to check, this should be wrapped up in a larger test for that endpoint */
    AADM_User.attemptsTo(ActivityAPI.GETActivityOnTheActivitiesController(randomClaimId, "string"));

    AADM_User.should(seeThatResponse("Activity Claims Returned", res -> res.statusCode(200)));
  }
}
