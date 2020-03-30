package com.vertafore.test.tasks.servicewrappers.todo;

import com.vertafore.test.abilities.CallTitanApi;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

/** This class was automatically generated on 2020/03/30 11:17:06 */
public class UseToDoServiceTo {

  private static final String THIS_SERVICE = "to-do";

  public static Performable bulkUpdateTaskStatusUsingPutOnTheTaskController(Object body) {
    return Task.where(
        "{0} Bulk Update tasks",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .put("tasks/status");
        });
  }

  public static Performable getTasksByFilterUsingGetOnTheTaskController(
      String sortField,
      String isDescending,
      String statuses,
      String types,
      String customerName,
      String dueDate,
      String assigneeId,
      String hideCced,
      String pageSize,
      String page) {
    return Task.where(
        "{0} Get Tasks",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("sortField", sortField)
              .queryParam("isDescending", isDescending)
              .queryParam("statuses", statuses)
              .queryParam("types", types)
              .queryParam("customerName", customerName)
              .queryParam("dueDate", dueDate)
              .queryParam("assigneeId", assigneeId)
              .queryParam("hideCced", hideCced)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get("tasks");
        });
  }

  public static Performable createTaskUsingPostOnTheTaskController(Object body) {
    return Task.where(
        "{0} Create a task",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("tasks");
        });
  }

  public static Performable findByTaskIdUsingGetOnTheTaskAssigneeController(String taskId) {
    return Task.where(
        "{0} Get all Task assignees",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("taskId", taskId)
              .get("tasks/{taskId}/assignees");
        });
  }

  public static Performable findATaskNotesOnTheTaskNoteController(
      String taskId, String pageSize, String page) {
    return Task.where(
        "{0} Find a task notes",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("taskId", taskId)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get("tasks/{taskId}/notes");
        });
  }

  public static Performable getTaskByIdUsingGetOnTheTaskController(String id) {
    return Task.where(
        "{0} Find a task",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("tasks/{id}");
        });
  }

  public static Performable deleteUsingDeleteOnTheTaskAssigneeController(
      String taskId, String assigneeId) {
    return Task.where(
        "{0} Remove user assignment from the task",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("taskId", taskId)
              .pathParam("assigneeId", assigneeId)
              .delete("tasks/{taskId}/assignees/{assigneeId}");
        });
  }

  public static Performable replaceTaskUsingPutOnTheTaskController(String id, Object body) {
    return Task.where(
        "{0} Replace a Task",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .body(body)
              .put("tasks/{id}");
        });
  }

  public static Performable updateUsingPutOnTheTaskNoteController(
      String taskId, String noteId, Object body) {
    return Task.where(
        "{0} Edit a task note",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("taskId", taskId)
              .pathParam("noteId", noteId)
              .body(body)
              .put("tasks/{taskId}/notes/{noteId}");
        });
  }

  public static Performable deleteATaskNoteOnTheTaskNoteController(String taskId, String noteId) {
    return Task.where(
        "{0} Delete a task note",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("taskId", taskId)
              .pathParam("noteId", noteId)
              .delete("tasks/{taskId}/notes/{noteId}");
        });
  }

  public static Performable updateTaskUsingPatchOnTheTaskController(String id, Object body) {
    return Task.where(
        "{0} Update a Task",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json-patch+json")
              .pathParam("id", id)
              .body(body)
              .patch("tasks/{id}");
        });
  }

  public static Performable assignUsingPostOnTheTaskAssigneeController(Object body, String taskId) {
    return Task.where(
        "{0} Assign user to the task",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .pathParam("taskId", taskId)
              .post("tasks/{taskId}/assignees");
        });
  }

  public static Performable getTasksByCustomerIdUsingGetOnTheTaskController(
      String customerId, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Get Tasks",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("customerId", customerId)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("tasks?filter=byCustomerId");
        });
  }

  public static Performable createUsingPostOnTheTaskNoteController(String taskId, Object body) {
    return Task.where(
        "{0} Create a task note",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("taskId", taskId)
              .body(body)
              .post("tasks/{taskId}/notes");
        });
  }
}
