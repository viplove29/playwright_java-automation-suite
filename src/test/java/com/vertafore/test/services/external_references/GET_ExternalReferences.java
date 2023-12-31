package com.vertafore.test.services.external_references;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.javafaker.Faker;
import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ExternalReferenceResponse;
import com.vertafore.test.servicewrappers.UseExternalReferencesTo;
import com.vertafore.test.util.ExternalReferencesUtil;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_ExternalReferences extends TokenSuperClass {

  @Test
  public void getExternalReferencesReturnsExternalReference() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseExternalReferencesTo externalReferencesApi = new UseExternalReferencesTo();
    Faker faker = new Faker();

    String name = faker.name().username();
    String value = faker.phoneNumber().cellPhone();

    // stage an external reference and get its GUID
    String referenceId =
        ExternalReferencesUtil.upsertAgencyExternalReference(name, value, AADM_User);

    // baseline tests
    VADM_Admin.attemptsTo(
        externalReferencesApi.GETExternalReferencesOnTheExternalreferencesController(
            referenceId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        externalReferencesApi.GETExternalReferencesOnTheExternalreferencesController(
            referenceId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        externalReferencesApi.GETExternalReferencesOnTheExternalreferencesController(
            referenceId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // validate response
    ExternalReferenceResponse efResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", ExternalReferenceResponse.class);

    assertThat(efResponse).isNotNull();
    assertThat(efResponse.getExternalReferenceId()).isEqualTo(referenceId);
    assertThat(efResponse.getExternalKeyName()).isEqualTo(name);
    assertThat(efResponse.getExternalKeyValue()).isEqualTo(value);
  }
}
