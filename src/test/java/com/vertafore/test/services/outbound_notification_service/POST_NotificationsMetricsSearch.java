package com.vertafore.test.services.outbound_notification_service;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseNotificationsTo;
import com.vertafore.test.util.Util;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_NotificationsMetricsSearch extends TokenSuperClass {

  // BaseLine test
  @Test
  public void postNotificationsMetricsBaselineTest() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    PagingRequestOnsMetricsSearchPostRequest postPagingRequest =
        new PagingRequestOnsMetricsSearchPostRequest();
    OnsMetricsSearchPostRequest onsMetricsSearchPostRequest = new OnsMetricsSearchPostRequest();
    postPagingRequest.setModel(onsMetricsSearchPostRequest);

    AADM_User.attemptsTo(
        notificationsApi.POSTNotificationsMetricsSearchOnTheOutboundnotificationserviceController(
            postPagingRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        notificationsApi.POSTNotificationsMetricsSearchOnTheOutboundnotificationserviceController(
            postPagingRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    VADM_Admin.attemptsTo(
        notificationsApi.POSTNotificationsMetricsSearchOnTheOutboundnotificationserviceController(
            postPagingRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PagingResponseOnsMetricsResponse pagingResponseOnsMetricsResponse =
        LastResponse.received()
            .answeredBy(VADM_Admin)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseOnsMetricsResponse.class);
    // check for empty response
    assertThat(pagingResponseOnsMetricsResponse.getResponse().isEmpty()).isTrue();
  }

  @Test
  public void postNotificationsMetricsReturnErrorWithInvalidInput() {

    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    // set from date later than the to date
    PagingRequestOnsMetricsSearchPostRequest postPagingRequest =
        new PagingRequestOnsMetricsSearchPostRequest();
    OnsMetricsSearchPostRequest onsMetricsSearchPostRequest = new OnsMetricsSearchPostRequest();
    onsMetricsSearchPostRequest.setDateFromUtc("2022-12-29T13:00:00");
    onsMetricsSearchPostRequest.setDateToUtc("2022-12-19T13:00:00");
    postPagingRequest.setModel(onsMetricsSearchPostRequest);

    VADM_Admin.attemptsTo(
        notificationsApi.POSTNotificationsMetricsSearchOnTheOutboundnotificationserviceController(
            postPagingRequest, ""));
    Util.validateErrorResponseContainsString(
        "The 'DateToUtc' field has to be greater than or equal to the 'DateFromUtc' field.",
        VADM_Admin);
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);

    // set invalid guid for OnsRecipientId
    postPagingRequest = new PagingRequestOnsMetricsSearchPostRequest();
    onsMetricsSearchPostRequest = new OnsMetricsSearchPostRequest();
    onsMetricsSearchPostRequest.setOnsRecipientId("xxx");
    postPagingRequest.setModel(onsMetricsSearchPostRequest);

    VADM_Admin.attemptsTo(
        notificationsApi.POSTNotificationsMetricsSearchOnTheOutboundnotificationserviceController(
            postPagingRequest, ""));
    Util.validateErrorResponse(
        "The string value of onsRecipientId could not be parsed as the expected GUID.", VADM_Admin);
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
  }
}
