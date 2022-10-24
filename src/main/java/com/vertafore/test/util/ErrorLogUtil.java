package com.vertafore.test.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseErrorLogsTo;
import java.util.List;
import java.util.stream.Collectors;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class ErrorLogUtil {

  public static UseErrorLogsTo errorLogsApi = new UseErrorLogsTo();

  public static List<ErrorLogsResponse> getErrorsAndWarningsByReferenceId(
      Actor actor, String referenceId) {
    ErrorLogsPostRequest errorLogsPostRequest = new ErrorLogsPostRequest();
    errorLogsPostRequest.setReferenceId(referenceId);
    PagingRequestErrorLogsPostRequest pagingRequestErrorLogsPostRequest =
        new PagingRequestErrorLogsPostRequest();
    pagingRequestErrorLogsPostRequest.setModel(errorLogsPostRequest);

    actor.attemptsTo(
        errorLogsApi.POSTErrorLogsSearchOnTheErrorlogsController(
            pagingRequestErrorLogsPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    return LastResponse.received()
        .answeredBy(actor)
        .getBody()
        .jsonPath()
        .getList("response", ErrorLogsResponse.class);
  }

  public static int getNumberOfWarnings(List<ErrorLogsResponse> errorLogsResponseList) {
    return (int)
        errorLogsResponseList
            .stream()
            .filter(e -> e.getSeverity().equalsIgnoreCase("warning"))
            .count();
  }

  public static List<String> getWarningMessages(List<ErrorLogsResponse> errorLogsResponseList) {
    return errorLogsResponseList
        .stream()
        .filter(e -> e.getSeverity().equalsIgnoreCase("warning"))
        .map(ErrorLogsResponse::getMessage)
        .collect(Collectors.toList());
  }

  public static Boolean warningMessageIsPresent(
      List<ErrorLogsResponse> errorLogsResponseList, String message) {
    return getWarningMessages(errorLogsResponseList)
            .stream()
            .filter(m -> m.contains(message))
            .count()
        > 0;
  }

  public static int getNumberOfErrors(List<ErrorLogsResponse> errorLogsResponseList) {
    return (int)
        errorLogsResponseList
            .stream()
            .filter(e -> e.getSeverity().equalsIgnoreCase("error"))
            .count();
  }

  public static List<String> getErrorMessages(List<ErrorLogsResponse> errorLogsResponseList) {
    return errorLogsResponseList
        .stream()
        .filter(e -> e.getSeverity().equalsIgnoreCase("error"))
        .map(ErrorLogsResponse::getMessage)
        .collect(Collectors.toList());
  }

  public static Boolean errorMessageIsPresent(
      List<ErrorLogsResponse> errorLogsResponseList, String message) {
    return getErrorMessages(errorLogsResponseList).stream().filter(m -> m.contains(message)).count()
        > 0;
  }
}
