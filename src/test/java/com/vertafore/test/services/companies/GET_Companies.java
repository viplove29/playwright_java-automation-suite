package com.vertafore.test.services.companies;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.CompanyResponse;
import com.vertafore.test.servicewrappers.UseCompaniesTo;
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
public class GET_Companies {
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
  public void companiesReturnsAllCompanies() {
    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    UseCompaniesTo companiesAPI = new UseCompaniesTo();

    bob.attemptsTo(companiesAPI.GETCompaniesOnTheCompaniesController(null, "string"));
    bob.should(seeThatResponse("Successfully gets response", res -> res.statusCode(200)));

    doug.attemptsTo(companiesAPI.GETCompaniesOnTheCompaniesController(null, "string"));
    doug.should(seeThatResponse("Successfully gets response", res -> res.statusCode(200)));

    adam.attemptsTo(companiesAPI.GETCompaniesOnTheCompaniesController(null, "string"));
    adam.should(seeThatResponse("Context is not valid", res -> res.statusCode(403)));
  }

  @Test
  public void companiesReturnsOneCompany() {
    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    UseCompaniesTo companiesAPI = new UseCompaniesTo();

    doug.attemptsTo(companiesAPI.GETCompaniesOnTheCompaniesController("!!&", "string"));
    doug.should(seeThatResponse("Successfully gets response", res -> res.statusCode(200)));

    adam.attemptsTo(companiesAPI.GETCompaniesOnTheCompaniesController("!!&", "string"));
    adam.should(seeThatResponse("Context is not valid", res -> res.statusCode(403)));

    bob.attemptsTo(companiesAPI.GETCompaniesOnTheCompaniesController("!!&", "string"));
    bob.should(seeThatResponse("Successfully gets response", res -> res.statusCode(200)));

    SerenityRest.lastResponse().prettyPrint();

    CompanyResponse company =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getList("", CompanyResponse.class)
            .get(0);

    // Response body format assertions
    assertThat(company != null).isTrue();
    assertThat(company.getClass().getDeclaredFields().length).isEqualTo(4);

    // Response body field data assertions
    assertThat(company.getCompanyId()).isEqualTo("26143d85-f40b-43a2-b4c8-a874e948bff2");
    assertThat(company.getName()).isEqualTo("Test Company");
  }

  @Test
  @WithTag("19R2")
  public void companiesReturnsCompanies19R2() {
    Actor mary = theActorCalled("mary");

    UseCompaniesTo companiesAPI = new UseCompaniesTo();

    mary.attemptsTo(companiesAPI.GETCompaniesOnTheCompaniesController("!!&", "string"));
    mary.should(seeThatResponse("Successfully gets response", res -> res.statusCode(200)));

    SerenityRest.lastResponse().prettyPrint();

    CompanyResponse company =
        LastResponse.received()
            .answeredBy(mary)
            .getBody()
            .jsonPath()
            .getObject("", CompanyResponse.class);

    // Response body format assertions
    assertThat(company != null).isTrue();
    assertThat(company.getClass().getDeclaredFields().length).isEqualTo(4);

    // Response body field data assertions
    assertThat(company.getCompanyId()).isEqualTo("26143d85-f40b-43a2-b4c8-a874e948bff2");
    assertThat(company.getName()).isEqualTo("Test Company");
  }
}
