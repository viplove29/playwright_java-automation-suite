package com.vertafore.test.services.external_references;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.javafaker.Faker;
import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ExternalReferenceEmployeePutRequest;
import com.vertafore.test.models.ems.ExternalReferenceIdResponse;
import com.vertafore.test.models.ems.ExternalReferenceResponse;
import com.vertafore.test.servicewrappers.UseExternalReferencesTo;
import com.vertafore.test.util.EmployeeUtil;
import com.vertafore.test.util.ExternalReferencesUtil;
import com.vertafore.test.util.Util;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class PUT_ExternalReferencesEmployee extends TokenSuperClass {

  private static UseExternalReferencesTo externalRefencesApi = new UseExternalReferencesTo();

  @Test
  public void putExternalReferencesEmployeeAddsReference() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    Faker faker = new Faker();
    String name = faker.name().username();
    String value = faker.phoneNumber().cellPhone();
    String employeeCode = EmployeeUtil.getRandomEmployee(AADM_User).getEmpCode();

    ExternalReferenceEmployeePutRequest putRequest = new ExternalReferenceEmployeePutRequest();
    putRequest.setExternalReferenceName(name);
    putRequest.setExternalReferenceValue(value);
    putRequest.setEmployeeCode(employeeCode);

    VADM_Admin.attemptsTo(
        externalRefencesApi.PUTExternalReferencesEmployeeOnTheExternalreferencesController(
            putRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // names are unique in the database, so change it for every request
    name = faker.name().username();
    putRequest.setExternalReferenceName(name);

    ORAN_App.attemptsTo(
        externalRefencesApi.PUTExternalReferencesEmployeeOnTheExternalreferencesController(
            putRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    name = faker.name().username();
    putRequest.setExternalReferenceName(name);

    AADM_User.attemptsTo(
        externalRefencesApi.PUTExternalReferencesEmployeeOnTheExternalreferencesController(
            putRequest, ""));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ExternalReferenceIdResponse response =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", ExternalReferenceIdResponse.class);

    assertThat(response).isNotNull();
    assertThat(response.getExternalReferenceId()).isNotNull();
    assertThat(Util.isValidGUID(response.getExternalReferenceId())).isTrue();
  }

  @Test
  public void putExternalReferenceEmployeeUpdatesReference() {
    Actor AADM_User = theActorCalled("AADM_User");
    Faker faker = new Faker();

    String name = faker.name().username();
    String value = faker.phoneNumber().cellPhone();
    String empCode = EmployeeUtil.getRandomEmployee(AADM_User).getEmpCode();

    // stage an external reference and get its GUID
    String referenceId =
        ExternalReferencesUtil.upsertEmployeeExternalReference(empCode, name, value, AADM_User);

    // verify reference has original value
    ExternalReferenceResponse externalReference =
        ExternalReferencesUtil.getExternalReferenceById(referenceId, AADM_User);
    assertThat(externalReference.getExternalKeyValue()).isEqualTo(value);

    // change value and update
    String updatedValue = faker.phoneNumber().cellPhone();
    String updatedReferenceId =
        ExternalReferencesUtil.upsertEmployeeExternalReference(
            empCode, name, updatedValue, AADM_User);

    ExternalReferenceResponse updatedExternalReference =
        ExternalReferencesUtil.getExternalReferenceById(referenceId, AADM_User);
    assertThat(updatedReferenceId).isEqualTo(referenceId); // same reference changed
    assertThat(updatedExternalReference.getExternalKeyValue()).isEqualTo(updatedValue);
  }
}
