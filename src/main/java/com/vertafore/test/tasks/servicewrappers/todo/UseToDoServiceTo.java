package com.vertafore.test.tasks.servicewrappers.todo;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import com.vertafore.test.abilities.CallTitanApi;

/**
* This class was automatically generated on 2020/03/25 13:40:55

*/
public class UseToDoServiceTo {

	private static final String THIS_SERVICE = "to-do";
	private static final String UPDATE_TASK_USING_PATCH_ON_THE_TASK_CONTROLLER = "tasks/{id}";
	private static final String GET_TASK_BY_ID_USING_GET_ON_THE_TASK_CONTROLLER = "tasks/{id}";
	private static final String REPLACE_TASK_USING_PUT_ON_THE_TASK_CONTROLLER = "tasks/{id}";
	private static final String DELETE_USING_DELETE_ON_THE_TASK_ASSIGNEE_CONTROLLER = "tasks/{taskId}/assignees/{assigneeId}";
	private static final String FIND_A_TASK_NOTES_ON_THE_TASK_NOTE_CONTROLLER = "tasks/{taskId}/notes";
	private static final String GET_TASKS_BY_FILTER_USING_GET_ON_THE_TASK_CONTROLLER = "tasks";
	private static final String DELETE_A_TASK_NOTE_ON_THE_TASK_NOTE_CONTROLLER = "tasks/{taskId}/notes/{noteId}";
	private static final String UPDATE_USING_PUT_ON_THE_TASK_NOTE_CONTROLLER = "tasks/{taskId}/notes/{noteId}";
	private static final String CREATE_USING_POST_ON_THE_TASK_NOTE_CONTROLLER = "tasks/{taskId}/notes";
	private static final String CREATE_TASK_USING_POST_ON_THE_TASK_CONTROLLER = "tasks";
	private static final String BULK_UPDATE_TASK_STATUS_USING_PUT_ON_THE_TASK_CONTROLLER = "tasks/status";
	private static final String ASSIGN_USING_POST_ON_THE_TASK_ASSIGNEE_CONTROLLER = "tasks/{taskId}/assignees";
	private static final String FIND_BY_TASK_ID_USING_GET_ON_THE_TASK_ASSIGNEE_CONTROLLER = "tasks/{taskId}/assignees";
	private static final String GET_TASKS_BY_CUSTOMER_ID_USING_GET_ON_THE_TASK_CONTROLLER = "tasks?filter=byCustomerId";

	public static Performable updateTaskUsingPatchOnTheTaskController(String id, Object body){
		return Task.where(
		"{0} Update a Task", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").pathParam("id", id).body(body).patch(UPDATE_TASK_USING_PATCH_ON_THE_TASK_CONTROLLER);
			}
		);
	}

	public static Performable getTaskByIdUsingGetOnTheTaskController(String id){
		return Task.where(
		"{0} Find a task", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_TASK_BY_ID_USING_GET_ON_THE_TASK_CONTROLLER);
			}
		);
	}

	public static Performable replaceTaskUsingPutOnTheTaskController(String id, Object body){
		return Task.where(
		"{0} Replace a Task", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put(REPLACE_TASK_USING_PUT_ON_THE_TASK_CONTROLLER);
			}
		);
	}

	public static Performable deleteUsingDeleteOnTheTaskAssigneeController(String taskId, String assigneeId){
		return Task.where(
		"{0} Remove user assignment from the task", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("taskId", taskId).pathParam("assigneeId", assigneeId).delete(DELETE_USING_DELETE_ON_THE_TASK_ASSIGNEE_CONTROLLER);
			}
		);
	}

	public static Performable findATaskNotesOnTheTaskNoteController(String taskId, String pageSize, String page){
		return Task.where(
		"{0} Find a task notes", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("taskId", taskId).queryParam("pageSize", pageSize).queryParam("page", page).get(FIND_A_TASK_NOTES_ON_THE_TASK_NOTE_CONTROLLER);
			}
		);
	}

	public static Performable getTasksByFilterUsingGetOnTheTaskController(String sortField, String isDescending, String statuses, String types, String customerName, String dueDate, String assigneeId, String hideCced, String pageSize, String page){
		return Task.where(
		"{0} Get Tasks", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("sortField", sortField).queryParam("isDescending", isDescending).queryParam("statuses", statuses).queryParam("types", types).queryParam("customerName", customerName).queryParam("dueDate", dueDate).queryParam("assigneeId", assigneeId).queryParam("hideCced", hideCced).queryParam("pageSize", pageSize).queryParam("page", page).get(GET_TASKS_BY_FILTER_USING_GET_ON_THE_TASK_CONTROLLER);
			}
		);
	}

	public static Performable deleteATaskNoteOnTheTaskNoteController(String taskId, String noteId){
		return Task.where(
		"{0} Delete a task note", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("taskId", taskId).pathParam("noteId", noteId).delete(DELETE_A_TASK_NOTE_ON_THE_TASK_NOTE_CONTROLLER);
			}
		);
	}

	public static Performable updateUsingPutOnTheTaskNoteController(String taskId, String noteId, Object body){
		return Task.where(
		"{0} Edit a task note", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("taskId", taskId).pathParam("noteId", noteId).body(body).put(UPDATE_USING_PUT_ON_THE_TASK_NOTE_CONTROLLER);
			}
		);
	}

	public static Performable createUsingPostOnTheTaskNoteController(String taskId, Object body){
		return Task.where(
		"{0} Create a task note", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("taskId", taskId).body(body).post(CREATE_USING_POST_ON_THE_TASK_NOTE_CONTROLLER);
			}
		);
	}

	public static Performable createTaskUsingPostOnTheTaskController(Object body){
		return Task.where(
		"{0} Create a task", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_TASK_USING_POST_ON_THE_TASK_CONTROLLER);
			}
		);
	}

	public static Performable bulkUpdateTaskStatusUsingPutOnTheTaskController(Object body){
		return Task.where(
		"{0} Bulk Update tasks", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).put(BULK_UPDATE_TASK_STATUS_USING_PUT_ON_THE_TASK_CONTROLLER);
			}
		);
	}

	public static Performable assignUsingPostOnTheTaskAssigneeController(Object body, String taskId){
		return Task.where(
		"{0} Assign user to the task", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).pathParam("taskId", taskId).post(ASSIGN_USING_POST_ON_THE_TASK_ASSIGNEE_CONTROLLER);
			}
		);
	}

	public static Performable findByTaskIdUsingGetOnTheTaskAssigneeController(String taskId){
		return Task.where(
		"{0} Get all Task assignees", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("taskId", taskId).get(FIND_BY_TASK_ID_USING_GET_ON_THE_TASK_ASSIGNEE_CONTROLLER);
			}
		);
	}

	public static Performable getTasksByCustomerIdUsingGetOnTheTaskController(String customerId, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get Tasks", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("customerId", customerId).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_TASKS_BY_CUSTOMER_ID_USING_GET_ON_THE_TASK_CONTROLLER);
			}
		);
	}



}
