package com.vertafore.test.services.companies;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.CompanyAddressResponse;
import com.vertafore.test.servicewrappers.UseCompanyAddressesTo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
            new EMSActor().called("mary").withContext("userContext").withVersion("19R2")));
    OnStage.setTheStage(GetAnAccessToken(actors));
  }

  // Helpers
  public static String randCompanyCode = "";
  public static String randCompanyAddress = "";

  Random random = new Random();
  int randomInt = random.nextInt(100);

  @Test
  public void companyAddressesReturnsAllCompanyAddresses() {
    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    UseCompanyAddressesTo companyAddressesAPI = new UseCompanyAddressesTo();

    doug.attemptsTo(
        companyAddressesAPI.GETCompanyAddressesOnTheCompaniesControllerDeprecated(null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    adam.attemptsTo(
        companyAddressesAPI.GETCompanyAddressesOnTheCompaniesControllerDeprecated(null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    bob.attemptsTo(
        companyAddressesAPI.GETCompanyAddressesOnTheCompaniesControllerDeprecated(null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<CompanyAddressResponse> companyAddressResponse =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getList("", CompanyAddressResponse.class);

    assertThat(companyAddressResponse.get(0).getClass().getDeclaredFields().length).isEqualTo(3);
    assertThat(companyAddressResponse.size()).isGreaterThan(0);

    randCompanyCode = companyAddressResponse.get(randomInt).getCompanyCode();
    randCompanyAddress = companyAddressResponse.get(randomInt).getCompanyAddressId();

    /*Test to validate that one Company Address can be found*/
    bob.attemptsTo(
        companyAddressesAPI.GETCompanyAddressesOnTheCompaniesControllerDeprecated(
            randCompanyCode, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

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
    assertThat(companyAddress.getCompanyAddressId()).isEqualTo(randCompanyAddress);
    assertThat(companyAddress.getEmail() == null).isTrue();
  }

  @Test
  @WithTag("19R2")
  public void companyAddressesReturnsAllCompanyAddresses19R2() {
    Actor mary = theActorCalled("mary");

    UseCompanyAddressesTo companyAddressesAPI = new UseCompanyAddressesTo();

    mary.attemptsTo(
        companyAddressesAPI.GETCompanyAddressesOnTheCompaniesControllerDeprecated(null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<CompanyAddressResponse> companyAddresses =
        LastResponse.received()
            .answeredBy(mary)
            .getBody()
            .jsonPath()
            .getList("", CompanyAddressResponse.class);

    assertThat(companyAddresses.get(0).getClass().getDeclaredFields().length).isEqualTo(3);
    assertThat(companyAddresses.size()).isGreaterThan(0);

    randCompanyCode = companyAddresses.get(randomInt).getCompanyCode();
    randCompanyAddress = companyAddresses.get(randomInt).getCompanyAddressId();

    /*Test to validate that in 19R2 one Company address can be found*/
    mary.attemptsTo(
        companyAddressesAPI.GETCompanyAddressesOnTheCompaniesControllerDeprecated(
            randCompanyCode, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

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
    assertThat(companyAddress.getCompanyAddressId()).isEqualTo(randCompanyAddress);
    assertThat(companyAddress.getEmail() == null).isTrue();
  }
}
