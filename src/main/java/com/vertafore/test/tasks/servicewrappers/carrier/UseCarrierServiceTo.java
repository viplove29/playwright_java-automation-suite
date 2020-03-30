package com.vertafore.test.tasks.servicewrappers.carrier;

import com.vertafore.test.abilities.CallTitanApi;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

/** This class was automatically generated on 2020/03/30 11:16:46 */
public class UseCarrierServiceTo {

  private static final String THIS_SERVICE = "carrier";

  public static Performable getAppointmentUsingGetOnTheAppointmentController(String id) {
    return Task.where(
        "{0} Get appointment",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("appointments/{id}");
        });
  }

  public static Performable findAllAppointmentsUsingGetOnTheAppointmentController(
      String pageSize, String page) {
    return Task.where(
        "{0} Get all appointments",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get("appointments");
        });
  }

  public static Performable deleteAppointmentUsingDeleteOnTheAppointmentController(String id) {
    return Task.where(
        "{0} Delete appointment",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .delete("appointments/{id}");
        });
  }

  public static Performable updateAppointmentUsingPutOnTheAppointmentController(
      String id, Object body) {
    return Task.where(
        "{0} Replace appointment",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .body(body)
              .put("appointments/{id}");
        });
  }

  public static Performable findAppointmentsByCarrierIdsUsingGetOnTheAppointmentController(
      String pageSize, String page, String carrierIds, String filter, String scope) {
    return Task.where(
        "{0} Get Appointments by Carrier Ids",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("carrierIds", carrierIds)
              .queryParam("filter", filter)
              .queryParam("scope", scope)
              .get("appointments?filter=byCarrierIds&scope=ancestors");
        });
  }

  public static Performable createAppointmentUsingPostOnTheAppointmentController(Object body) {
    return Task.where(
        "{0} Create appointment",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("appointments");
        });
  }

  public static Performable updateAppointmentUsingPatchOnTheAppointmentController(
      String id, Object body) {
    return Task.where(
        "{0} Update appointment",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json-patch+json")
              .pathParam("id", id)
              .body(body)
              .patch("appointments/{id}");
        });
  }

  public static Performable searchUsingGetOnTheAppointmentController(
      String pageSize, String page, String searchTerm, String filter, String scope) {
    return Task.where(
        "{0} Search by Carrier",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("searchTerm", searchTerm)
              .queryParam("filter", filter)
              .queryParam("scope", scope)
              .get("appointments?filter=searchTerm&scope=ancestors");
        });
  }
}
