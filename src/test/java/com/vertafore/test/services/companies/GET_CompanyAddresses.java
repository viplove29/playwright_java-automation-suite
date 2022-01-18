package com.vertafore.test.services.companies;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.CompanyAddressResponse;
import com.vertafore.test.servicewrappers.UseCompaniesTo;
import com.vertafore.test.servicewrappers.UseCompanyAddressesTo;
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
public class GET_CompanyAddresses {
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
  public static String randCompanyCode;
  public static String randCompanyAddressId;
  public static String randCompanyEmail;
  public static String randCompanyAddressDescription;
  public static String randCompanyIsDefaultForChecks;
  public static String randCompanyIsDefaultForCLForms;
  public static String randCompanyIsPrimaryAddress;
  public static String randCompanyAddressLine1;
  public static String randCompanyAddressLine2;
  public static String randCompanyCity;
  public static String randCompanyState;
  public static String randCompanyZipCode;
  public static String randCompanyAreaCode;
  public static String randCompanyPhone;
  public static String randCompanyExtension;
  public static String randCompanyFaxAreaCode;
  public static String randCompanyFaxPhone;
  public static String randCompanyFaxExtension;
  public static String randCompanyWebAddress;
  public static String randCompanyCompanyType;

  Random random = new Random();
  int randomInt = random.nextInt(50);

  @Test
  public void companyAddressesReturnsAllCompanyAddresses() {
    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    UseCompaniesTo companyAddressesAPI = new UseCompaniesTo();

    doug.attemptsTo(
        companyAddressesAPI.GETCompaniesAddressesOnTheCompaniesController(null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    adam.attemptsTo(
        companyAddressesAPI.GETCompaniesAddressesOnTheCompaniesController(null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    bob.attemptsTo(
        companyAddressesAPI.GETCompaniesAddressesOnTheCompaniesController(null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<CompanyAddressResponse> companyAddressResponse =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getList("", CompanyAddressResponse.class);

    assertThat(companyAddressResponse.get(0).getClass().getDeclaredFields().length).isEqualTo(20);
    assertThat(companyAddressResponse.size()).isGreaterThan(0);

    // set helper variables for later validation
    randCompanyCode = companyAddressResponse.get(randomInt).getCompanyCode();
    randCompanyAddressId = companyAddressResponse.get(randomInt).getCompanyAddressId();
    randCompanyEmail = companyAddressResponse.get(randomInt).getEmail();
    randCompanyAddressDescription = companyAddressResponse.get(randomInt).getAddressDescription();
    randCompanyIsDefaultForChecks = companyAddressResponse.get(randomInt).getIsDefaultForChecks();
    randCompanyIsDefaultForCLForms = companyAddressResponse.get(randomInt).getIsDefaultForCLForms();
    randCompanyIsPrimaryAddress = companyAddressResponse.get(randomInt).getIsPrimaryAddress();
    randCompanyAddressLine1 = companyAddressResponse.get(randomInt).getAddressLine1();
    randCompanyAddressLine2 = companyAddressResponse.get(randomInt).getAddressLine2();
    randCompanyCity = companyAddressResponse.get(randomInt).getCity();
    randCompanyState = companyAddressResponse.get(randomInt).getState();
    randCompanyZipCode = companyAddressResponse.get(randomInt).getZipCode();
    randCompanyAreaCode = companyAddressResponse.get(randomInt).getAreaCode();
    randCompanyPhone = companyAddressResponse.get(randomInt).getPhone();
    randCompanyExtension = companyAddressResponse.get(randomInt).getExtension();
    randCompanyFaxAreaCode = companyAddressResponse.get(randomInt).getFaxAreaCode();
    randCompanyFaxPhone = companyAddressResponse.get(randomInt).getFaxPhone();
    randCompanyFaxExtension = companyAddressResponse.get(randomInt).getFaxExtension();
    randCompanyWebAddress = companyAddressResponse.get(randomInt).getWebAddress();
    randCompanyCompanyType = companyAddressResponse.get(randomInt).getCompanyType();

    /*Test to validate that one Company Address can be found*/
    bob.attemptsTo(
        companyAddressesAPI.GETCompaniesAddressesOnTheCompaniesController(
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
    assertThat(companyAddress.getClass().getDeclaredFields().length).isEqualTo(20);

    // Response body field data assertions
    assertThat(companyAddress.getCompanyAddressId()).isEqualTo(randCompanyAddressId);
    assertThat(companyAddress.getEmail()).isEqualTo(randCompanyEmail);
    assertThat(companyAddress.getAddressDescription()).isEqualTo(randCompanyAddressDescription);
    assertThat(companyAddress.getIsDefaultForChecks()).isEqualTo(randCompanyIsDefaultForChecks);
    assertThat(companyAddress.getIsDefaultForCLForms()).isEqualTo(randCompanyIsDefaultForCLForms);
    assertThat(companyAddress.getIsPrimaryAddress()).isEqualTo(randCompanyIsPrimaryAddress);
    assertThat(companyAddress.getAddressLine1()).isEqualTo(randCompanyAddressLine1);
    assertThat(companyAddress.getAddressLine2()).isEqualTo(randCompanyAddressLine2);
    assertThat(companyAddress.getCity()).isEqualTo(randCompanyCity);
    assertThat(companyAddress.getState()).isEqualTo(randCompanyState);
    assertThat(companyAddress.getZipCode()).isEqualTo(randCompanyZipCode);
    assertThat(companyAddress.getAreaCode()).isEqualTo(randCompanyAreaCode);
    assertThat(companyAddress.getPhone()).isEqualTo(randCompanyPhone);
    assertThat(companyAddress.getExtension()).isEqualTo(randCompanyExtension);
    assertThat(companyAddress.getFaxAreaCode()).isEqualTo(randCompanyFaxAreaCode);
    assertThat(companyAddress.getFaxPhone()).isEqualTo(randCompanyFaxPhone);
    assertThat(companyAddress.getFaxExtension()).isEqualTo(randCompanyFaxExtension);
    assertThat(companyAddress.getWebAddress()).isEqualTo(randCompanyWebAddress);
    assertThat(companyAddress.getCompanyType()).isEqualTo(randCompanyCompanyType);
  }

  @Test
  public void companyAddressesReturnsAllCompanyAddressesDeprecated() {
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

    assertThat(companyAddressResponse.get(0).getClass().getDeclaredFields().length).isEqualTo(20);
    assertThat(companyAddressResponse.size()).isGreaterThan(0);

    // set helper variables for later validation
    randCompanyCode = companyAddressResponse.get(randomInt).getCompanyCode();
    randCompanyAddressId = companyAddressResponse.get(randomInt).getCompanyAddressId();
    randCompanyEmail = companyAddressResponse.get(randomInt).getEmail();
    randCompanyAddressDescription = companyAddressResponse.get(randomInt).getAddressDescription();
    randCompanyIsDefaultForChecks = companyAddressResponse.get(randomInt).getIsDefaultForChecks();
    randCompanyIsDefaultForCLForms = companyAddressResponse.get(randomInt).getIsDefaultForCLForms();
    randCompanyIsPrimaryAddress = companyAddressResponse.get(randomInt).getIsPrimaryAddress();
    randCompanyAddressLine1 = companyAddressResponse.get(randomInt).getAddressLine1();
    randCompanyAddressLine2 = companyAddressResponse.get(randomInt).getAddressLine2();
    randCompanyCity = companyAddressResponse.get(randomInt).getCity();
    randCompanyState = companyAddressResponse.get(randomInt).getState();
    randCompanyZipCode = companyAddressResponse.get(randomInt).getZipCode();
    randCompanyAreaCode = companyAddressResponse.get(randomInt).getAreaCode();
    randCompanyPhone = companyAddressResponse.get(randomInt).getPhone();
    randCompanyExtension = companyAddressResponse.get(randomInt).getExtension();
    randCompanyFaxAreaCode = companyAddressResponse.get(randomInt).getFaxAreaCode();
    randCompanyFaxPhone = companyAddressResponse.get(randomInt).getFaxPhone();
    randCompanyFaxExtension = companyAddressResponse.get(randomInt).getFaxExtension();
    randCompanyWebAddress = companyAddressResponse.get(randomInt).getWebAddress();
    randCompanyCompanyType = companyAddressResponse.get(randomInt).getCompanyType();

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
    assertThat(companyAddress.getClass().getDeclaredFields().length).isEqualTo(20);

    // Response body field data assertions
    assertThat(companyAddress.getCompanyAddressId()).isEqualTo(randCompanyAddressId);
    assertThat(companyAddress.getEmail()).isEqualTo(randCompanyEmail);
    assertThat(companyAddress.getAddressDescription()).isEqualTo(randCompanyAddressDescription);
    assertThat(companyAddress.getIsDefaultForChecks()).isEqualTo(randCompanyIsDefaultForChecks);
    assertThat(companyAddress.getIsDefaultForCLForms()).isEqualTo(randCompanyIsDefaultForCLForms);
    assertThat(companyAddress.getIsPrimaryAddress()).isEqualTo(randCompanyIsPrimaryAddress);
    assertThat(companyAddress.getAddressLine1()).isEqualTo(randCompanyAddressLine1);
    assertThat(companyAddress.getAddressLine2()).isEqualTo(randCompanyAddressLine2);
    assertThat(companyAddress.getCity()).isEqualTo(randCompanyCity);
    assertThat(companyAddress.getState()).isEqualTo(randCompanyState);
    assertThat(companyAddress.getZipCode()).isEqualTo(randCompanyZipCode);
    assertThat(companyAddress.getAreaCode()).isEqualTo(randCompanyAreaCode);
    assertThat(companyAddress.getPhone()).isEqualTo(randCompanyPhone);
    assertThat(companyAddress.getExtension()).isEqualTo(randCompanyExtension);
    assertThat(companyAddress.getFaxAreaCode()).isEqualTo(randCompanyFaxAreaCode);
    assertThat(companyAddress.getFaxPhone()).isEqualTo(randCompanyFaxPhone);
    assertThat(companyAddress.getFaxExtension()).isEqualTo(randCompanyFaxExtension);
    assertThat(companyAddress.getWebAddress()).isEqualTo(randCompanyWebAddress);
    assertThat(companyAddress.getCompanyType()).isEqualTo(randCompanyCompanyType);
  }
}
