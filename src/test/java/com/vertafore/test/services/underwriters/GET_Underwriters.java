package com.vertafore.test.services.underwriters;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.UnderwriterResponse;
import com.vertafore.test.servicewrappers.UseUnderwritersTo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_Underwriters {

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
  public void UnderwritersReturnsAllUnderwriters() {
    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    UseUnderwritersTo underwritersAPI = new UseUnderwritersTo();

    // basic status code assertions
    adam.attemptsTo(underwritersAPI.GETUnderwritersOnTheUnderwritersController(null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    doug.attemptsTo(underwritersAPI.GETUnderwritersOnTheUnderwritersController(null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    bob.attemptsTo(underwritersAPI.GETUnderwritersOnTheUnderwritersController(null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<UnderwriterResponse> underwritersResponse =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getList("", UnderwriterResponse.class);

    // basic field name assertions
    assertThat(underwritersResponse.get(0).getClass().getDeclaredFields().length).isEqualTo(5);
    assertThat(underwritersResponse.get(0).getClass().getDeclaredFields()[0].getName())
        .isEqualTo("companyCode");
    assertThat(underwritersResponse.get(0).getClass().getDeclaredFields()[1].getName())
        .isEqualTo("underwriterId");
    assertThat(underwritersResponse.get(0).getClass().getDeclaredFields()[2].getName())
        .isEqualTo("name");
    assertThat(underwritersResponse.get(0).getClass().getDeclaredFields()[3].getName())
        .isEqualTo("email");
    assertThat(underwritersResponse.get(0).getClass().getDeclaredFields()[4].getName())
        .isEqualTo("companyAddressId");

    // pick random index, set variables for specific company check below
    int uwIndex = new Random().nextInt(underwritersResponse.size());
    String companyCode = underwritersResponse.get(uwIndex).getCompanyCode();
    String underwriterId = underwritersResponse.get(uwIndex).getUnderwriterId();
    String name = underwritersResponse.get(uwIndex).getName();
    String email = underwritersResponse.get(uwIndex).getEmail();
    String companyAddressId = underwritersResponse.get(uwIndex).getCompanyAddressId();

    // get with company code from above
    bob.attemptsTo(
        underwritersAPI.GETUnderwritersOnTheUnderwritersController(companyCode, "string"));

    List<UnderwriterResponse> singleCompanyUnderwriters =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getList("", UnderwriterResponse.class);

    /* there may be more than one underwriter for a company, but they should all have the same company code */
    assertThat(singleCompanyUnderwriters.get(0).getCompanyCode()).isEqualTo(companyCode);

    /* filter the list by underwriterId, in the case that a company has multiple underwriters */
    UnderwriterResponse specificUWResponse =
        singleCompanyUnderwriters
            .stream()
            .filter(uwid -> uwid.getUnderwriterId().contains(underwriterId))
            .collect(Collectors.toList())
            .get(0);

    assertThat(specificUWResponse).isNotNull();
    assertThat(specificUWResponse.getUnderwriterId()).isEqualTo(underwriterId);
    assertThat(specificUWResponse.getName()).isEqualTo(name);
    assertThat(specificUWResponse.getEmail()).isEqualTo(email);
    assertThat(specificUWResponse.getCompanyAddressId()).isEqualTo(companyAddressId);
  }
}
