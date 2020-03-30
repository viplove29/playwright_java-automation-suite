package com.vertafore.test.tasks.servicewrappers.communication;

import com.vertafore.test.abilities.CallTitanApi;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

/** This class was automatically generated on 2020/03/30 11:16:49 */
public class UseCommunicationServiceTo {

  private static final String THIS_SERVICE = "communication";

  public static Performable getTextCommunicationOnTheTextCommunicationController(String id) {
    return Task.where(
        "{0} Get Text Communication",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("texts/{id}");
        });
  }

  public static Performable getMeetingCommunicationOnTheMeetingCommunicationController(String id) {
    return Task.where(
        "{0} Get Meeting Communication",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("meetings/{id}");
        });
  }

  public static Performable retrieveLotsOfTextCommunicationByIdOnTheTextCommunicationController(
      String ids, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Retrieve lots of Text Communication by id",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("ids", ids)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("texts?filter=byIds");
        });
  }

  public static Performable createCommunicationUsingPostOnTheEmailCommunicationController(
      Object body) {
    return Task.where(
        "{0} Create Email Communication",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("emails");
        });
  }

  public static Performable
      retrieveLotsOfMeetingCommunicationByIdOnTheMeetingCommunicationController(
          String ids, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Retrieve lots of Meeting Communication by id",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("ids", ids)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("meetings?filter=byIds");
        });
  }

  public static Performable findByTypesAndQueryUsingGetOnTheLogEntryController(
      String types,
      String sortField,
      String isDescending,
      String searchTerm,
      String pageSize,
      String page,
      String filter) {
    return Task.where(
        "{0} Find Communication by search string and filter by types",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("types", types)
              .queryParam("sortField", sortField)
              .queryParam("isDescending", isDescending)
              .queryParam("searchTerm", searchTerm)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("log-entries?filter=byTypesAndQuery");
        });
  }

  public static Performable getPhoneCallCommunicationOnThePhoneCallCommunicationController(
      String id) {
    return Task.where(
        "{0} Get Phone Call Communication",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("phone-calls/{id}");
        });
  }

  public static Performable createPhoneCallCommunicationOnThePhoneCallCommunicationController(
      Object body) {
    return Task.where(
        "{0} Create Phone Call Communication",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("phone-calls");
        });
  }

  public static Performable createTextCommunicationOnTheTextCommunicationController(Object body) {
    return Task.where(
        "{0} Create Text Communication",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("texts");
        });
  }

  public static Performable findCommunicationsByIdsUsingGetOnTheEmailCommunicationController(
      String ids, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Retrieve lots of Email Communication by id",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("ids", ids)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("emails?filter=byIds");
        });
  }

  public static Performable
      retrieveLotsOfPhoneCallCommunicationByIdOnThePhoneCallCommunicationController(
          String ids, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Retrieve lots of Phone Call Communication by id",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("ids", ids)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("phone-calls?filter=byIds");
        });
  }

  public static Performable getCommunicationUsingGetOnTheEmailCommunicationController(String id) {
    return Task.where(
        "{0} Get Email Communication",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("emails/{id}");
        });
  }

  public static Performable createMeetingCommunicationOnTheMeetingCommunicationController(
      Object body) {
    return Task.where(
        "{0} Create Meeting Communication",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("meetings");
        });
  }
}
