package com.vertafore.test.services.utility;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.SeqNumberResponse;
import com.vertafore.test.servicewrappers.UseUtilityTo;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_EmpCodeToSeqNumber extends TokenSuperClass {

  @Test
  public void empCodeReturnsCorrectStatusCode() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseUtilityTo utilityApi = new UseUtilityTo();

    AADM_User.attemptsTo(
        utilityApi.GETUtilityEmpCodeToSeqNumberOnTheUtilityController("!#^", "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    SerenityRest.lastResponse().prettyPrint();

    ORAN_App.attemptsTo(
        utilityApi.GETUtilityEmpCodeToSeqNumberOnTheUtilityController("!#^", "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        utilityApi.GETUtilityEmpCodeToSeqNumberOnTheUtilityController("!#^", "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }

  // TODO - NO HARDCODED DATA! OR WIRE HANGERS!
  @Test
  public void empCodeReturnsCorrectSeqNumber() {
    Actor AADM_User = theActorCalled("AADM_User");

    UseUtilityTo utilityApi = new UseUtilityTo();

    AADM_User.attemptsTo(
        utilityApi.GETUtilityEmpCodeToSeqNumberOnTheUtilityController("!#^", "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Use getObject because return object is a hashmap, not a list
    SeqNumberResponse seqResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", SeqNumberResponse.class);

    assertThat(seqResponse.getSequenceNumber()).isEqualTo("033035094");
  }
}
