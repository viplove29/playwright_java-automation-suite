package com.vertafore.test.tasks.servicewrappers.report;

import com.vertafore.test.abilities.CallTitanApi;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

/** This class was automatically generated on 2020/03/30 11:17:04 */
public class UseReportServiceTo {

  private static final String THIS_SERVICE = "report";

  public static Performable getPoliciesUsingGetOnThePolicyReportController(
      String pageSize, String page) {
    return Task.where(
        "{0} Get policies.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get("policies");
        });
  }
}
