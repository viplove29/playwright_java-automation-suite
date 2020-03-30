package com.vertafore.test.tasks.servicewrappers.rating;

import com.vertafore.test.abilities.CallTitanApi;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

/** This class was automatically generated on 2020/03/30 11:17:02 */
public class UseRatingServiceTo {

  private static final String THIS_SERVICE = "rating";

  public static Performable getRateResponsesByCorrelationIdUsingGetOnTheRatingController(
      String correlationId, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Retrieve Rate Responses by Correlation ID",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("correlationId", correlationId)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("rates?filter=byCorrelationId");
        });
  }

  public static Performable
      getRateResponseByCorrelationIdAndRatingUnitIdUsingGetOnTheRatingController(
          String correlationId, String ratingUnitId, String filter) {
    return Task.where(
        "{0} Retrieve Rate Responses by Correlation ID and Rating Unit ID",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("correlationId", correlationId)
              .queryParam("ratingUnitId", ratingUnitId)
              .queryParam("filter", filter)
              .get("rates?filter=byCorrelationIdAndRatingUnitId");
        });
  }

  public static Performable requestRateUsingPostOnTheRatingController(Object body) {
    return Task.where(
        "{0} Request Rate",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("rates");
        });
  }
}
