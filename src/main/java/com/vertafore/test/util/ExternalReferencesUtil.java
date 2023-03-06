package com.vertafore.test.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.ems.ExternalReferenceAgencyPutRequest;
import com.vertafore.test.models.ems.ExternalReferenceEmployeePutRequest;
import com.vertafore.test.models.ems.ExternalReferenceIdResponse;
import com.vertafore.test.models.ems.ExternalReferenceResponse;
import com.vertafore.test.servicewrappers.UseExternalReferencesTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class ExternalReferencesUtil {

  private static final UseExternalReferencesTo externalReferencesApi =
      new UseExternalReferencesTo();

  public static String upsertAgencyExternalReference(String name, String value, Actor actor) {
    ExternalReferenceAgencyPutRequest putRequest = new ExternalReferenceAgencyPutRequest();
    putRequest.setExternalReferenceName(name);
    putRequest.setExternalReferenceValue(value);

    actor.attemptsTo(
        externalReferencesApi.PUTExternalReferencesAgencyOnTheExternalreferencesController(
            putRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ExternalReferenceIdResponse response =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", ExternalReferenceIdResponse.class);
    assertThat(response).isNotNull();
    assertThat(response.getExternalReferenceId()).isNotNull();
    assertThat(Util.isValidGUID(response.getExternalReferenceId())).isTrue();

    return response.getExternalReferenceId();
  }

  public static String upsertEmployeeExternalReference(
      String employeeCode, String name, String value, Actor actor) {
    ExternalReferenceEmployeePutRequest putRequest = new ExternalReferenceEmployeePutRequest();
    putRequest.setExternalReferenceName(name);
    putRequest.setExternalReferenceValue(value);
    putRequest.setEmployeeCode(employeeCode);

    actor.attemptsTo(
        externalReferencesApi.PUTExternalReferencesEmployeeOnTheExternalreferencesController(
            putRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ExternalReferenceIdResponse response =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", ExternalReferenceIdResponse.class);
    assertThat(response).isNotNull();
    assertThat(response.getExternalReferenceId()).isNotNull();
    assertThat(Util.isValidGUID(response.getExternalReferenceId())).isTrue();

    return response.getExternalReferenceId();
  }

  public static ExternalReferenceResponse getExternalReferenceById(
      String referenceId, Actor actor) {
    actor.attemptsTo(
        externalReferencesApi.GETExternalReferencesOnTheExternalreferencesController(
            referenceId, ""));
    ExternalReferenceResponse efResponse =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", ExternalReferenceResponse.class);

    assertThat(efResponse).isNotNull();
    assertThat(efResponse.getExternalReferenceId()).isEqualTo(referenceId);

    return efResponse;
  }
}
