package com.vertafore.test.services.companies;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.CompanyResponse;
import com.vertafore.test.servicewrappers.UseCompaniesTo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
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
            new EMSActor().called("adam").withContext("adminContext")));
    OnStage.setTheStage(GetAnAccessToken(actors));
  }

  // Helpers
  public static String randCompanyCode = "";
  public static String randCompanyId = "";
  public static String randCompanyName = "";

  Random random = new Random();
  int randomInt = random.nextInt(100);

  @Test
  public void companiesReturnsAllCompanies() {
    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    UseCompaniesTo companiesAPI = new UseCompaniesTo();

    doug.attemptsTo(companiesAPI.GETCompaniesOnTheCompaniesController(null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    adam.attemptsTo(companiesAPI.GETCompaniesOnTheCompaniesController(null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    bob.attemptsTo(companiesAPI.GETCompaniesOnTheCompaniesController(null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<CompanyResponse> companyResponse =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getList("", CompanyResponse.class);

    assertThat(companyResponse.get(0).getClass().getDeclaredFields().length).isEqualTo(4);
    assertThat(companyResponse.size()).isGreaterThan(0);

    randCompanyCode = companyResponse.get(randomInt).getCompanyCode();
    randCompanyId = companyResponse.get(randomInt).getCompanyId();
    randCompanyName = companyResponse.get(randomInt).getName();

    /*Test to validate that one Company can be found using a random company code and the data can be verified using the first test*/
    bob.attemptsTo(companiesAPI.GETCompaniesOnTheCompaniesController(randCompanyCode, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

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
    assertThat(company.getCompanyId()).isEqualTo(randCompanyId);
    assertThat(company.getName()).isEqualTo(randCompanyName);
  }
}
