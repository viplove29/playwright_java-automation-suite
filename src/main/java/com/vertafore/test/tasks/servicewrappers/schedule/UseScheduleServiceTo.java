package com.vertafore.test.tasks.servicewrappers.schedule;

import com.vertafore.test.abilities.CallTitanApi;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

/** This class was automatically generated on 2020/03/30 11:17:04 */
public class UseScheduleServiceTo {

  private static final String THIS_SERVICE = "schedule";

  public static Performable getScheduleUsingGetOnTheScheduleController(String id) {
    return Task.where(
        "{0} Get Schedule by ID",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("schedules/{id}");
        });
  }

  public static Performable deleteScheduleUsingDeleteOnTheScheduleController(String id) {
    return Task.where(
        "{0} Delete Schedule",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .delete("schedules/{id}");
        });
  }

  public static Performable replaceScheduleUsingPutOnTheScheduleController(Object body, String id) {
    return Task.where(
        "{0} Replace Schedule",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .pathParam("id", id)
              .put("schedules/{id}");
        });
  }

  public static Performable findScheduleByNameUsingGetOnTheScheduleController(
      String name, String filter) {
    return Task.where(
        "{0} Get Schedule by name",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("name", name)
              .queryParam("filter", filter)
              .get("schedules?filter=byName");
        });
  }

  public static Performable createScheduleUsingPostOnTheScheduleController(Object body) {
    return Task.where(
        "{0} Create Schedule",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("schedules");
        });
  }

  public static Performable patchScheduleUsingPatchOnTheScheduleController(Object body, String id) {
    return Task.where(
        "{0} Patch Schedule",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json-patch+json")
              .body(body)
              .pathParam("id", id)
              .patch("schedules/{id}");
        });
  }
}
