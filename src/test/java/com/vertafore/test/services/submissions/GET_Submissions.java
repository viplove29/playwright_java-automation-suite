package com.vertafore.test.services.submissions;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.BasicPolicyInfoResponse;
import com.vertafore.test.servicewrappers.UseSubmissionsTo;
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
public class GET_Submissions {

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
  public void submissionsSuccessfullyReturnsPoliciesForCustomer() {
    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");
    String customerGUID = "5C10AEB1-E12D-43F8-86F1-CFA9F43ED253";
    UseSubmissionsTo submissionsAPI = new UseSubmissionsTo();

    bob.attemptsTo(
        submissionsAPI.GETSubmissionsOnTheSubmissionsController(
            customerGUID, null, true, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    //    doug.attemptsTo(
    //        submissionsAPI.GETSubmissionsOnTheSubmissionsController(
    //            customerGUID, datetime, true, "string"));
    //    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    //
    adam.attemptsTo(
        submissionsAPI.GETSubmissionsOnTheSubmissionsController(
            customerGUID, null, true, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }

  @Test
  public void submissionsSuccessfullyReturnsAllSubmissionsForCustomer() {
    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    String customerGUID = "5C10AEB1-E12D-43F8-86F1-CFA9F43ED253";

    UseSubmissionsTo submissionsAPI = new UseSubmissionsTo();

    bob.attemptsTo(
        submissionsAPI.GETSubmissionsOnTheSubmissionsController(
            customerGUID, null, false, "string"));
    SerenityRest.lastResponse().prettyPrint();
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<BasicPolicyInfoResponse> fourPolicies =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getList("", BasicPolicyInfoResponse.class);

    assertThat(fourPolicies.size()).isEqualTo(4);

    // change the customer to one that has zero policies
    customerGUID = "39B604F3-BC29-4FCB-A4DF-001D893C6BAC";
    bob.attemptsTo(
        submissionsAPI.GETSubmissionsOnTheSubmissionsController(
            customerGUID, null, false, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<BasicPolicyInfoResponse> zeroPolicies =
        LastResponse.received()
            .answeredBy(doug)
            .getBody()
            .jsonPath()
            .getList("", BasicPolicyInfoResponse.class);

    assertThat(zeroPolicies.size()).isEqualTo(0);
  }

  @Test
  @WithTag("19R2")
  public void submissionsSuccessfullyReturnsAllSubmissions19R2() {
    Actor mary = theActorCalled("mary");
    String customerGUID = "5C10AEB1-E12D-43F8-86F1-CFA9F43ED253";

    UseSubmissionsTo submissionsAPI = new UseSubmissionsTo();

    mary.attemptsTo(
        submissionsAPI.GETSubmissionsOnTheSubmissionsController(
            customerGUID, null, false, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<BasicPolicyInfoResponse> fourPolicies =
        LastResponse.received()
            .answeredBy(mary)
            .getBody()
            .jsonPath()
            .getList("", BasicPolicyInfoResponse.class);

    assertThat(fourPolicies.size()).isEqualTo(4);

    for (BasicPolicyInfoResponse response : fourPolicies) {
      assertThat(response.getPolicySubType()).isEqualTo("S");
    }
  }
}
