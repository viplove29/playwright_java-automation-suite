package com.vertafore.test.tasks.servicewrappers.question;

import com.vertafore.test.abilities.CallTitanApi;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

/** This class was automatically generated on 2020/03/30 11:17:00 */
public class UseQuestionServiceTo {

  private static final String THIS_SERVICE = "question";

  public static Performable deleteSectionUsingDeleteOnTheSectionController(String key) {
    return Task.where(
        "{0} Delete section.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("key", key)
              .delete("sections/{key}");
        });
  }

  public static Performable createQuestionListUsingPostOnTheQuestionListController(Object body) {
    return Task.where(
        "{0} Create question list.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("question-lists");
        });
  }

  public static Performable searchQuestionsUsingGetOnTheQuestionController(
      String searchTerm, String status, String pageSize, String page) {
    return Task.where(
        "{0} Search questions.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("searchTerm", searchTerm)
              .queryParam("status", status)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get("questions");
        });
  }

  public static Performable updateSectionUsingPutOnTheSectionController(String key, Object body) {
    return Task.where(
        "{0} Replace section.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("key", key)
              .body(body)
              .put("sections/{key}");
        });
  }

  public static Performable updateQuestionListUsingPutOnTheQuestionListController(
      String key, Object body) {
    return Task.where(
        "{0} Replace question list.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("key", key)
              .body(body)
              .put("question-lists/{key}");
        });
  }

  public static Performable getSectionUsingGetOnTheSectionController(String key) {
    return Task.where(
        "{0} Get section.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("key", key)
              .get("sections/{key}");
        });
  }

  public static Performable getQuestionBySectionKeyUsingGetOnTheQuestionController(
      String sectionKey, String searchTerm, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Get questions by section key.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("sectionKey", sectionKey)
              .queryParam("searchTerm", searchTerm)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("questions?filter=bySectionKey");
        });
  }

  public static Performable deleteQuestionUsingDeleteOnTheQuestionController(String key) {
    return Task.where(
        "{0} Delete question.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("key", key)
              .delete("questions/{key}");
        });
  }

  public static Performable getQuestionsByKeysUsingGetOnTheQuestionController(
      String keys, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Get questions.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("keys", keys)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("questions?filter=byKeys");
        });
  }

  public static Performable getSectionByKeysUsingGetOnTheSectionController(
      String keys, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Get sections.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("keys", keys)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("sections?filter=byKeys");
        });
  }

  public static Performable updateQuestionListUsingPatchOnTheQuestionListController(
      String key, Object body) {
    return Task.where(
        "{0} Update question list.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json-patch+json")
              .pathParam("key", key)
              .body(body)
              .patch("question-lists/{key}");
        });
  }

  public static Performable getQuestionListsUsingGetOnTheQuestionListController(
      String keys, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Get question lists.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("keys", keys)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("question-lists?filter=byKeys");
        });
  }

  public static Performable createSectionUsingPostOnTheSectionController(Object body) {
    return Task.where(
        "{0} Create section.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("sections");
        });
  }

  public static Performable searchSectionsUsingGetOnTheSectionController(
      String searchTerm, String pageSize, String page) {
    return Task.where(
        "{0} Search sections.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("searchTerm", searchTerm)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get("sections");
        });
  }

  public static Performable getQuestionUsingGetOnTheQuestionController(String key) {
    return Task.where(
        "{0} Get question.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("key", key)
              .get("questions/{key}");
        });
  }

  public static Performable getQuestionListsOnTheQuestionListController(
      String searchTerm, String status, String pageSize, String page) {
    return Task.where(
        "{0} Get question lists.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("searchTerm", searchTerm)
              .queryParam("status", status)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get("question-lists");
        });
  }

  public static Performable updateQuestionUsingPatchOnTheQuestionController(
      String key, Object body) {
    return Task.where(
        "{0} Update question.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json-patch+json")
              .pathParam("key", key)
              .body(body)
              .patch("questions/{key}");
        });
  }

  public static Performable getQuestionListByKeyUsingGetOnTheQuestionListController(String key) {
    return Task.where(
        "{0} Get question list.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("key", key)
              .get("question-lists/{key}");
        });
  }

  public static Performable createQuestionUsingPostOnTheQuestionController(Object body) {
    return Task.where(
        "{0} Create question.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("questions");
        });
  }

  public static Performable deleteQuestionListUsingDeleteOnTheQuestionListController(String key) {
    return Task.where(
        "{0} Delete question list.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("key", key)
              .delete("question-lists/{key}");
        });
  }

  public static Performable getQuestionListBySectionKeyUsingGetOnTheQuestionListController(
      String sectionKey, String searchTerm, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Get question lists by section key.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("sectionKey", sectionKey)
              .queryParam("searchTerm", searchTerm)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("question-lists?filter=bySectionKey");
        });
  }

  public static Performable updateSectionUsingPatchOnTheSectionController(String key, Object body) {
    return Task.where(
        "{0} Update section.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json-patch+json")
              .pathParam("key", key)
              .body(body)
              .patch("sections/{key}");
        });
  }

  public static Performable getQuestionByQuestionListKeyUsingGetOnTheQuestionController(
      String questionListKey, String searchTerm, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Get questions by question list key.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("questionListKey", questionListKey)
              .queryParam("searchTerm", searchTerm)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("questions?filter=byQuestionListKey");
        });
  }
}
