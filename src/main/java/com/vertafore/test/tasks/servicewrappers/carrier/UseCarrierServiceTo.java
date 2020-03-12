package com.vertafore.test.tasks.servicewrappers.carrier;

import com.vertafore.test.abilities.CallTitanApi;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class UseCarrierServiceTo {

  private static final String THIS_SERVICE = "carrier";
  private static final String UPDATE_APPOINTMENT_USING_PATCH_ON_THE_APPOINTMENT_CONTROLLER =
      "appointments/{id}";
  private static final String GET_APPOINTMENT_USING_GET_ON_THE_APPOINTMENT_CONTROLLER =
      "appointments/{id}";
  private static final String DELETE_APPOINTMENT_USING_DELETE_ON_THE_APPOINTMENT_CONTROLLER =
      "appointments/{id}";
  private static final String UPDATE_APPOINTMENT_USING_PUT_ON_THE_APPOINTMENT_CONTROLLER =
      "appointments/{id}";
  private static final String CREATE_APPOINTMENT_USING_POST_ON_THE_APPOINTMENT_CONTROLLER =
      "appointments";
  private static final String FIND_ALL_APPOINTMENTS_USING_GET_ON_THE_APPOINTMENT_CONTROLLER =
      "appointments";
  private static final String
      FIND_APPOINTMENTS_BY_CARRIER_IDS_USING_GET_ON_THE_APPOINTMENT_CONTROLLER =
          "appointments?filter=byCarrierIds&scope=ancestors";
  private static final String SEARCH_USING_GET_ON_THE_APPOINTMENT_CONTROLLER =
      "appointments?filter=searchTerm&scope=ancestors";

  public static Performable updateAppointmentUsingPatchOnTheAppointmentController(
      String id, Object body) {
    return Task.where(
        "{0} Update appointment",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json-patch+json")
              .pathParam("id", id)
              .body(body)
              .patch(UPDATE_APPOINTMENT_USING_PATCH_ON_THE_APPOINTMENT_CONTROLLER);
        });
  }

  public static Performable getAppointmentUsingGetOnTheAppointmentController(String id) {
    return Task.where(
        "{0} Get appointment",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get(GET_APPOINTMENT_USING_GET_ON_THE_APPOINTMENT_CONTROLLER);
        });
  }

  public static Performable deleteAppointmentUsingDeleteOnTheAppointmentController(String id) {
    return Task.where(
        "{0} Delete appointment",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .delete(DELETE_APPOINTMENT_USING_DELETE_ON_THE_APPOINTMENT_CONTROLLER);
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
              .put(UPDATE_APPOINTMENT_USING_PUT_ON_THE_APPOINTMENT_CONTROLLER);
        });
  }

  public static Performable createAppointmentUsingPostOnTheAppointmentController(Object body) {
    return Task.where(
        "{0} Create appointment",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post(CREATE_APPOINTMENT_USING_POST_ON_THE_APPOINTMENT_CONTROLLER);
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
              .get(FIND_ALL_APPOINTMENTS_USING_GET_ON_THE_APPOINTMENT_CONTROLLER);
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
              .get(FIND_APPOINTMENTS_BY_CARRIER_IDS_USING_GET_ON_THE_APPOINTMENT_CONTROLLER);
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
              .get(SEARCH_USING_GET_ON_THE_APPOINTMENT_CONTROLLER);
        });
  }
}
