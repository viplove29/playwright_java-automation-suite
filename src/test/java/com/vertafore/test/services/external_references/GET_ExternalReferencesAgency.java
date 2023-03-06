package com.vertafore.test.services.external_references;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.javafaker.Faker;
import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ExternalReferenceListResponse;
import com.vertafore.test.models.ems.ExternalReferenceResponse;
import com.vertafore.test.servicewrappers.UseExternalReferencesTo;
import com.vertafore.test.util.ExternalReferencesUtil;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class GET_ExternalReferencesAgency extends TokenSuperClass {

  @Test
  public void getExternalReferencesAgencyGetsAgencyExternalReference() {
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
        externalReferencesApi.GETExternalReferencesAgencyOnTheExternalreferencesController(
            name, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        externalReferencesApi.GETExternalReferencesAgencyOnTheExternalreferencesController(
            name, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        externalReferencesApi.GETExternalReferencesAgencyOnTheExternalreferencesController(
            name, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // validate response
    List<ExternalReferenceResponse> efResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", ExternalReferenceListResponse.class)
            .getExternalReferences();
    assertThat(efResponse).isNotNull();
    assertThat(efResponse.size()).isEqualTo(1); // names should be unique

    ExternalReferenceResponse stagedReference = efResponse.get(0);

    assertThat(stagedReference).isNotNull();
    assertThat(stagedReference.getExternalReferenceId()).isEqualTo(referenceId);
    assertThat(stagedReference.getExternalKeyName()).isEqualTo(name);
    assertThat(stagedReference.getExternalKeyValue()).isEqualTo(value);
  }
}
