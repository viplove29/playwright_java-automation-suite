package com.vertafore.test.services.companyAddresses;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.CompanyAddressResponse;
import com.vertafore.test.servicewrappers.UseCompanyAddressesTo;
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
public class GET_CompanyAddresses {
  private List<EMSActor> actors = new ArrayList<>();

  @Before
  public void getAnAccessToken() {
    actors.addAll(
        List.of(
            new EMSActor().called("bob").withContext("userContext"),
            new EMSActor().called("doug").withContext("appContext"),
            new EMSActor().called("adam").withContext("adminContext"),
            new EMSActor().called("mary").withContext("userContext")));
    OnStage.setTheStage(GetAnAccessToken(actors));
  }

  @Test
  public void companyAddressesReturnsAllCompanyAddresses() {
    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    UseCompanyAddressesTo companyAddressesAPI = new UseCompanyAddressesTo();

    bob.attemptsTo(
        companyAddressesAPI.GETCompanyAddressesOnTheCompaniesControllerDeprecated(null, "string"));
    bob.should(seeThatResponse("Successfully gets response", res -> res.statusCode(200)));

    doug.attemptsTo(
        companyAddressesAPI.GETCompanyAddressesOnTheCompaniesControllerDeprecated(null, "string"));
    doug.should(seeThatResponse("Successfully gets response", res -> res.statusCode(200)));

    adam.attemptsTo(
        companyAddressesAPI.GETCompanyAddressesOnTheCompaniesControllerDeprecated(null, "string"));
    adam.should(seeThatResponse("Context is not valid", res -> res.statusCode(403)));
  }

  @Test
  public void companyAddressesReturnsOneCompanyAddress() {
    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    UseCompanyAddressesTo companyAddressesAPI = new UseCompanyAddressesTo();

    doug.attemptsTo(
        companyAddressesAPI.GETCompanyAddressesOnTheCompaniesControllerDeprecated("!!%", "string"));
    doug.should(seeThatResponse("Successfully gets response", res -> res.statusCode(200)));

    adam.attemptsTo(
        companyAddressesAPI.GETCompanyAddressesOnTheCompaniesControllerDeprecated("!!%", "string"));
    adam.should(seeThatResponse("Context is not valid", res -> res.statusCode(403)));

    bob.attemptsTo(
        companyAddressesAPI.GETCompanyAddressesOnTheCompaniesControllerDeprecated("!!%", "string"));
    bob.should(seeThatResponse("Successfully gets response", res -> res.statusCode(200)));

    SerenityRest.lastResponse().prettyPrint();

    CompanyAddressResponse companyAddress =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getList("", CompanyAddressResponse.class)
            .get(0);

    // Response body format assertions
    assertThat(companyAddress != null).isTrue();
    assertThat(companyAddress.getClass().getDeclaredFields().length).isEqualTo(3);

    // Response body field data assertions
    assertThat(companyAddress.getCompanyAddressId())
        .isEqualTo("b7278590-8f0e-4406-b79f-fad801e41ccc");
    assertThat(companyAddress.getEmail() == null).isTrue();
  }

  @Test
  @WithTag("19R2")
  public void companyAddressesReturnsCompanyAddresses19R2() {
    Actor mary = theActorCalled("mary");

    UseCompanyAddressesTo companyAddressesAPI = new UseCompanyAddressesTo();

    mary.attemptsTo(
        companyAddressesAPI.GETCompanyAddressesOnTheCompaniesControllerDeprecated("!!%", "string"));
    mary.should(seeThatResponse("Successfully gets response", res -> res.statusCode(200)));

    SerenityRest.lastResponse().prettyPrint();

    CompanyAddressResponse companyAddress =
        LastResponse.received()
            .answeredBy(mary)
            .getBody()
            .jsonPath()
            .getList("", CompanyAddressResponse.class)
            .get(0);

    // Response body format assertions
    assertThat(companyAddress != null).isTrue();
    assertThat(companyAddress.getClass().getDeclaredFields().length).isEqualTo(3);

    // Response body field data assertions
    assertThat(companyAddress.getCompanyAddressId())
        .isEqualTo("b7278590-8f0e-4406-b79f-fad801e41ccc");
    assertThat(companyAddress.getEmail() == null).isTrue();
  }
}
