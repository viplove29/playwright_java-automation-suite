package com.vertafore.test.services.utility;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.EndpointDirectoryResponse;
import com.vertafore.test.servicewrappers.UseUtilityTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class GET_UtilityEndpointDirectory extends TokenSuperClass {

  @Test
  public void GET_EndpointDirectory() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseUtilityTo utilityApi = new UseUtilityTo();

    ORAN_App.attemptsTo(
        utilityApi.GETUtilityEndpointDirectoryOnTheUtilityController(true, true, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    VADM_Admin.attemptsTo(
        utilityApi.GETUtilityEndpointDirectoryOnTheUtilityController(true, true, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        utilityApi.GETUtilityEndpointDirectoryOnTheUtilityController(true, true, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    EndpointDirectoryResponse endpointDirectoryResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", EndpointDirectoryResponse.class);

    int endpointCount = endpointDirectoryResponse.getEndpointCount();
    assertThat(endpointDirectoryResponse).isNotNull();
    assertThat(endpointCount).isGreaterThan(0);
    assertThat(endpointDirectoryResponse.getEndpoints().size()).isEqualTo(endpointCount);
    assertThat(endpointDirectoryResponse.getClass().getDeclaredFields().length).isEqualTo(4);
  }
}
