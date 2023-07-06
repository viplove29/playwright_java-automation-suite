package com.vertafore.test.services.checks;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseChecksTo;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_ChecksPendingSearch extends TokenSuperClass {

  // There should be at least 3 pending checks staged in the testing agency prior to running these

  @Test
  public void checksPendingSearchIsSuccessful() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseChecksTo checksAPI = new UseChecksTo();
    SortedPagingRequestPendingCheckSearchPostRequestPendingCheckSortOptions
        pendingChecksSearchPostRequest =
            new SortedPagingRequestPendingCheckSearchPostRequestPendingCheckSortOptions();
    PendingCheckSearchPostRequest pendingChecksSearchPostRequestModel =
        new PendingCheckSearchPostRequest();
    SortOptionPendingCheckSortOptions sortOptions = new SortOptionPendingCheckSortOptions();
    sortOptions.setFieldSort("checkDate");
    sortOptions.setIsDescendingOrder(true);
    pendingChecksSearchPostRequest.setSortOption(sortOptions);
    pendingChecksSearchPostRequest.setModel(pendingChecksSearchPostRequestModel);

    ORAN_App.attemptsTo(
        checksAPI.POSTChecksPendingSearchOnTheChecksController(pendingChecksSearchPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        checksAPI.POSTChecksPendingSearchOnTheChecksController(pendingChecksSearchPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        checksAPI.POSTChecksPendingSearchOnTheChecksController(pendingChecksSearchPostRequest, ""));

    PagingResponsePendingCheckSearchResponse pagingResponsePendingCheckSearchResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponsePendingCheckSearchResponse.class);

    assertThat(pagingResponsePendingCheckSearchResponse).isNotNull();
    assertThat(pagingResponsePendingCheckSearchResponse.getClass().getDeclaredFields().length)
        .isEqualTo(4);
    assertThat(pagingResponsePendingCheckSearchResponse.getTotalCount()).isGreaterThanOrEqualTo(3);

    List<PendingCheckSearchResponse> pendingChecksSearchResponseList =
        pagingResponsePendingCheckSearchResponse.getResponse();
    assertThat(pendingChecksSearchResponseList).isNotNull();
    assertThat(pendingChecksSearchResponseList.size()).isGreaterThanOrEqualTo(3);

    // verify less checks are returned when excluding handwritten checks
    pendingChecksSearchPostRequestModel.setIncludeHandwritten(false);
    pendingChecksSearchPostRequest.setModel(pendingChecksSearchPostRequestModel);
    AADM_User.attemptsTo(
        checksAPI.POSTChecksPendingSearchOnTheChecksController(pendingChecksSearchPostRequest, ""));

    PagingResponsePendingCheckSearchResponse
        pagingResponsePendingCheckSearchResponseExcludingHandwritten =
            LastResponse.received()
                .answeredBy(AADM_User)
                .getBody()
                .jsonPath()
                .getObject("", PagingResponsePendingCheckSearchResponse.class);

    assertThat(pagingResponsePendingCheckSearchResponseExcludingHandwritten).isNotNull();
    assertThat(
            pagingResponsePendingCheckSearchResponseExcludingHandwritten
                .getClass()
                .getDeclaredFields()
                .length)
        .isEqualTo(4);
    assertThat(pagingResponsePendingCheckSearchResponseExcludingHandwritten.getTotalCount())
        .isGreaterThanOrEqualTo(1);
    assertThat(pagingResponsePendingCheckSearchResponseExcludingHandwritten.getTotalCount())
        .isLessThan(pagingResponsePendingCheckSearchResponse.getTotalCount());

    List<PendingCheckSearchResponse> pendingChecksSearchResponseListExcludingHandwritten =
        pagingResponsePendingCheckSearchResponseExcludingHandwritten.getResponse();
    assertThat(pendingChecksSearchResponseListExcludingHandwritten).isNotNull();
    assertThat(pendingChecksSearchResponseListExcludingHandwritten.size())
        .isGreaterThanOrEqualTo(1);
    assertThat(pendingChecksSearchResponseListExcludingHandwritten.size())
        .isLessThan(pendingChecksSearchResponseList.size());
  }

  @Test
  public void checksPendingSearchReturnsSortedPendingChecks() {
    Actor AADM_User = theActorCalled("AADM_User");

    UseChecksTo checksAPI = new UseChecksTo();

    // Search with sort: date descending
    SortedPagingRequestPendingCheckSearchPostRequestPendingCheckSortOptions
        pendingChecksSearchPostRequestDateDescending =
            new SortedPagingRequestPendingCheckSearchPostRequestPendingCheckSortOptions();
    PendingCheckSearchPostRequest pendingChecksSearchPostRequestModelDateDescending =
        new PendingCheckSearchPostRequest();
    SortOptionPendingCheckSortOptions sortOptionsDateDescending =
        new SortOptionPendingCheckSortOptions();
    sortOptionsDateDescending.setFieldSort("checkDate");
    sortOptionsDateDescending.setIsDescendingOrder(true);
    pendingChecksSearchPostRequestDateDescending.setSortOption(sortOptionsDateDescending);
    pendingChecksSearchPostRequestDateDescending.setModel(
        pendingChecksSearchPostRequestModelDateDescending);

    AADM_User.attemptsTo(
        checksAPI.POSTChecksPendingSearchOnTheChecksController(
            pendingChecksSearchPostRequestDateDescending, ""));

    PagingResponsePendingCheckSearchResponse
        pagingResponsePendingCheckSearchResponseDateDescending =
            LastResponse.received()
                .answeredBy(AADM_User)
                .getBody()
                .jsonPath()
                .getObject("", PagingResponsePendingCheckSearchResponse.class);

    // Verify response is sorted by date descending
    assertThat(pagingResponsePendingCheckSearchResponseDateDescending.getTotalCount())
        .isGreaterThanOrEqualTo(3);
    List<PendingCheckSearchResponse> pendingChecksDateDescending =
        pagingResponsePendingCheckSearchResponseDateDescending.getResponse();
    Long previousDate = pendingChecksDateDescending.get(0).getCheckDate().getTime();
    for (PendingCheckSearchResponse pendingCheck : pendingChecksDateDescending) {
      assertThat(pendingCheck.getCheckDate().getTime()).isLessThanOrEqualTo(previousDate);
    }

    // Search with sort: date ascending
    SortedPagingRequestPendingCheckSearchPostRequestPendingCheckSortOptions
        pendingChecksSearchPostRequestDateAscending =
            new SortedPagingRequestPendingCheckSearchPostRequestPendingCheckSortOptions();
    PendingCheckSearchPostRequest pendingChecksSearchPostRequestModelDateAscending =
        new PendingCheckSearchPostRequest();
    SortOptionPendingCheckSortOptions sortOptionsDateAscending =
        new SortOptionPendingCheckSortOptions();
    sortOptionsDateAscending.setFieldSort("checkDate");
    sortOptionsDateAscending.setIsDescendingOrder(false);
    pendingChecksSearchPostRequestDateAscending.setSortOption(sortOptionsDateAscending);
    pendingChecksSearchPostRequestDateAscending.setModel(
        pendingChecksSearchPostRequestModelDateAscending);

    AADM_User.attemptsTo(
        checksAPI.POSTChecksPendingSearchOnTheChecksController(
            pendingChecksSearchPostRequestDateAscending, ""));

    PagingResponsePendingCheckSearchResponse pagingResponsePendingCheckSearchResponseDateAscending =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponsePendingCheckSearchResponse.class);

    // Verify response is sorted by date ascending
    assertThat(pagingResponsePendingCheckSearchResponseDateAscending.getTotalCount())
        .isGreaterThanOrEqualTo(3);
    List<PendingCheckSearchResponse> pendingChecksDateAscending =
        pagingResponsePendingCheckSearchResponseDateAscending.getResponse();
    previousDate = pendingChecksDateAscending.get(0).getCheckDate().getTime();
    for (PendingCheckSearchResponse pendingCheck : pendingChecksDateAscending) {
      assertThat(pendingCheck.getCheckDate().getTime()).isGreaterThanOrEqualTo(previousDate);
    }

    // Search with sort: type descending
    SortedPagingRequestPendingCheckSearchPostRequestPendingCheckSortOptions
        pendingChecksSearchPostRequestTypeDescending =
            new SortedPagingRequestPendingCheckSearchPostRequestPendingCheckSortOptions();
    PendingCheckSearchPostRequest pendingChecksSearchPostRequestModelTypeDescending =
        new PendingCheckSearchPostRequest();
    SortOptionPendingCheckSortOptions sortOptionsTypeDescending =
        new SortOptionPendingCheckSortOptions();
    sortOptionsTypeDescending.setFieldSort("checkType");
    sortOptionsTypeDescending.setIsDescendingOrder(true);
    pendingChecksSearchPostRequestTypeDescending.setSortOption(sortOptionsTypeDescending);
    pendingChecksSearchPostRequestTypeDescending.setModel(
        pendingChecksSearchPostRequestModelTypeDescending);

    AADM_User.attemptsTo(
        checksAPI.POSTChecksPendingSearchOnTheChecksController(
            pendingChecksSearchPostRequestTypeDescending, ""));

    PagingResponsePendingCheckSearchResponse
        pagingResponsePendingCheckSearchResponseTypeDescending =
            LastResponse.received()
                .answeredBy(AADM_User)
                .getBody()
                .jsonPath()
                .getObject("", PagingResponsePendingCheckSearchResponse.class);

    // Verify response is sorted by type descending
    assertThat(pagingResponsePendingCheckSearchResponseTypeDescending.getTotalCount())
        .isGreaterThanOrEqualTo(3);
    List<PendingCheckSearchResponse> pendingChecksTypeDescending =
        pagingResponsePendingCheckSearchResponseTypeDescending.getResponse();
    String previousType = pendingChecksTypeDescending.get(0).getCheckType();
    for (PendingCheckSearchResponse pendingCheck : pendingChecksTypeDescending) {
      assertThat(pendingCheck.getCheckType()).isLessThanOrEqualTo(previousType);
    }

    // Search with sort: type ascending
    SortedPagingRequestPendingCheckSearchPostRequestPendingCheckSortOptions
        pendingChecksSearchPostRequestTypeAscending =
            new SortedPagingRequestPendingCheckSearchPostRequestPendingCheckSortOptions();
    PendingCheckSearchPostRequest pendingChecksSearchPostRequestModelTypeAscending =
        new PendingCheckSearchPostRequest();
    SortOptionPendingCheckSortOptions sortOptionsTypeAscending =
        new SortOptionPendingCheckSortOptions();
    sortOptionsTypeAscending.setFieldSort("checkType");
    sortOptionsTypeAscending.setIsDescendingOrder(false);
    pendingChecksSearchPostRequestTypeAscending.setSortOption(sortOptionsTypeAscending);
    pendingChecksSearchPostRequestTypeAscending.setModel(
        pendingChecksSearchPostRequestModelTypeAscending);

    AADM_User.attemptsTo(
        checksAPI.POSTChecksPendingSearchOnTheChecksController(
            pendingChecksSearchPostRequestTypeAscending, ""));

    PagingResponsePendingCheckSearchResponse pagingResponsePendingCheckSearchResponseTypeAscending =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponsePendingCheckSearchResponse.class);

    // Verify response is sorted by type ascending
    assertThat(pagingResponsePendingCheckSearchResponseTypeAscending.getTotalCount())
        .isGreaterThanOrEqualTo(3);
    List<PendingCheckSearchResponse> pendingChecksTypeAscending =
        pagingResponsePendingCheckSearchResponseTypeAscending.getResponse();
    previousType = pendingChecksTypeAscending.get(0).getCheckType();
    for (PendingCheckSearchResponse pendingCheck : pendingChecksTypeAscending) {
      assertThat(pendingCheck.getCheckType()).isGreaterThanOrEqualTo(previousType);
    }

    // Search with sort: payee descending
    SortedPagingRequestPendingCheckSearchPostRequestPendingCheckSortOptions
        pendingChecksSearchPostRequestPayeeDescending =
            new SortedPagingRequestPendingCheckSearchPostRequestPendingCheckSortOptions();
    PendingCheckSearchPostRequest pendingChecksSearchPostRequestModelPayeeDescending =
        new PendingCheckSearchPostRequest();
    SortOptionPendingCheckSortOptions sortOptionsPayeeDescending =
        new SortOptionPendingCheckSortOptions();
    sortOptionsPayeeDescending.setFieldSort("checkPayee");
    sortOptionsPayeeDescending.setIsDescendingOrder(true);
    pendingChecksSearchPostRequestPayeeDescending.setSortOption(sortOptionsPayeeDescending);
    pendingChecksSearchPostRequestPayeeDescending.setModel(
        pendingChecksSearchPostRequestModelPayeeDescending);

    AADM_User.attemptsTo(
        checksAPI.POSTChecksPendingSearchOnTheChecksController(
            pendingChecksSearchPostRequestPayeeDescending, ""));

    PagingResponsePendingCheckSearchResponse
        pagingResponsePendingCheckSearchResponsePayeeDescending =
            LastResponse.received()
                .answeredBy(AADM_User)
                .getBody()
                .jsonPath()
                .getObject("", PagingResponsePendingCheckSearchResponse.class);

    // Verify response is sorted by payee descending
    assertThat(pagingResponsePendingCheckSearchResponsePayeeDescending.getTotalCount())
        .isGreaterThanOrEqualTo(3);
    List<PendingCheckSearchResponse> pendingChecksPayeeDescending =
        pagingResponsePendingCheckSearchResponsePayeeDescending.getResponse();
    String previousPayee = pendingChecksPayeeDescending.get(0).getCheckPayee();
    for (PendingCheckSearchResponse pendingCheck : pendingChecksPayeeDescending) {
      assertThat(pendingCheck.getCheckPayee()).isLessThanOrEqualTo(previousPayee);
    }

    // Search with sort: payee ascending
    SortedPagingRequestPendingCheckSearchPostRequestPendingCheckSortOptions
        pendingChecksSearchPostRequestPayeeAscending =
            new SortedPagingRequestPendingCheckSearchPostRequestPendingCheckSortOptions();
    PendingCheckSearchPostRequest pendingChecksSearchPostRequestModelPayeeAscending =
        new PendingCheckSearchPostRequest();
    SortOptionPendingCheckSortOptions sortOptionsPayeeAscending =
        new SortOptionPendingCheckSortOptions();
    sortOptionsPayeeAscending.setFieldSort("checkPayee");
    sortOptionsPayeeAscending.setIsDescendingOrder(false);
    pendingChecksSearchPostRequestPayeeAscending.setSortOption(sortOptionsPayeeAscending);
    pendingChecksSearchPostRequestPayeeAscending.setModel(
        pendingChecksSearchPostRequestModelPayeeAscending);

    AADM_User.attemptsTo(
        checksAPI.POSTChecksPendingSearchOnTheChecksController(
            pendingChecksSearchPostRequestPayeeAscending, ""));

    PagingResponsePendingCheckSearchResponse
        pagingResponsePendingCheckSearchResponsePayeeAscending =
            LastResponse.received()
                .answeredBy(AADM_User)
                .getBody()
                .jsonPath()
                .getObject("", PagingResponsePendingCheckSearchResponse.class);

    // Verify response is sorted by payee ascending
    assertThat(pagingResponsePendingCheckSearchResponsePayeeAscending.getTotalCount())
        .isGreaterThanOrEqualTo(3);
    List<PendingCheckSearchResponse> pendingChecksPayeeAscending =
        pagingResponsePendingCheckSearchResponsePayeeAscending.getResponse();
    previousPayee = pendingChecksPayeeAscending.get(0).getCheckPayee();
    for (PendingCheckSearchResponse pendingCheck : pendingChecksPayeeAscending) {
      assertThat(pendingCheck.getCheckPayee()).isGreaterThanOrEqualTo(previousPayee);
    }

    // Search with sort: amount descending
    SortedPagingRequestPendingCheckSearchPostRequestPendingCheckSortOptions
        pendingChecksSearchPostRequestAmountDescending =
            new SortedPagingRequestPendingCheckSearchPostRequestPendingCheckSortOptions();
    PendingCheckSearchPostRequest pendingChecksSearchPostRequestModelAmountDescending =
        new PendingCheckSearchPostRequest();
    SortOptionPendingCheckSortOptions sortOptionsAmountDescending =
        new SortOptionPendingCheckSortOptions();
    sortOptionsAmountDescending.setFieldSort("checkAmount");
    sortOptionsAmountDescending.setIsDescendingOrder(true);
    pendingChecksSearchPostRequestAmountDescending.setSortOption(sortOptionsAmountDescending);
    pendingChecksSearchPostRequestAmountDescending.setModel(
        pendingChecksSearchPostRequestModelAmountDescending);

    AADM_User.attemptsTo(
        checksAPI.POSTChecksPendingSearchOnTheChecksController(
            pendingChecksSearchPostRequestAmountDescending, ""));

    PagingResponsePendingCheckSearchResponse
        pagingResponsePendingCheckSearchResponseAmountDescending =
            LastResponse.received()
                .answeredBy(AADM_User)
                .getBody()
                .jsonPath()
                .getObject("", PagingResponsePendingCheckSearchResponse.class);

    // Verify response is sorted by amount descending
    assertThat(pagingResponsePendingCheckSearchResponseAmountDescending.getTotalCount())
        .isGreaterThanOrEqualTo(3);
    List<PendingCheckSearchResponse> pendingChecksAmountDescending =
        pagingResponsePendingCheckSearchResponseAmountDescending.getResponse();
    Double previousAmount = pendingChecksAmountDescending.get(0).getCheckAmount();
    for (PendingCheckSearchResponse pendingCheck : pendingChecksAmountDescending) {
      assertThat(pendingCheck.getCheckAmount()).isLessThanOrEqualTo(previousAmount);
    }

    // Search with sort: amount ascending
    SortedPagingRequestPendingCheckSearchPostRequestPendingCheckSortOptions
        pendingChecksSearchPostRequestAmountAscending =
            new SortedPagingRequestPendingCheckSearchPostRequestPendingCheckSortOptions();
    PendingCheckSearchPostRequest pendingChecksSearchPostRequestModelAmountAscending =
        new PendingCheckSearchPostRequest();
    SortOptionPendingCheckSortOptions sortOptionsAmountAscending =
        new SortOptionPendingCheckSortOptions();
    sortOptionsAmountAscending.setFieldSort("checkAmount");
    sortOptionsAmountAscending.setIsDescendingOrder(false);
    pendingChecksSearchPostRequestAmountAscending.setSortOption(sortOptionsAmountAscending);
    pendingChecksSearchPostRequestAmountAscending.setModel(
        pendingChecksSearchPostRequestModelAmountAscending);

    AADM_User.attemptsTo(
        checksAPI.POSTChecksPendingSearchOnTheChecksController(
            pendingChecksSearchPostRequestAmountAscending, ""));

    PagingResponsePendingCheckSearchResponse
        pagingResponsePendingCheckSearchResponseAmountAscending =
            LastResponse.received()
                .answeredBy(AADM_User)
                .getBody()
                .jsonPath()
                .getObject("", PagingResponsePendingCheckSearchResponse.class);

    // Verify response is sorted by amount ascending
    assertThat(pagingResponsePendingCheckSearchResponseAmountAscending.getTotalCount())
        .isGreaterThanOrEqualTo(3);
    List<PendingCheckSearchResponse> pendingChecksAmountAscending =
        pagingResponsePendingCheckSearchResponseAmountAscending.getResponse();
    previousAmount = pendingChecksAmountAscending.get(0).getCheckAmount();
    for (PendingCheckSearchResponse pendingCheck : pendingChecksAmountAscending) {
      assertThat(pendingCheck.getCheckAmount()).isGreaterThanOrEqualTo(previousAmount);
    }
  }
}
