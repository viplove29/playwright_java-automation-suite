package com.vertafore.test.tasks.servicewrappers.schedule;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import com.vertafore.test.abilities.CallTitanApi;

/**
* This class was automatically generated on 2020/03/25 13:40:54

*/
public class UseScheduleServiceTo {

	private static final String THIS_SERVICE = "schedule";
	private static final String PATCH_SCHEDULE_USING_PATCH_ON_THE_SCHEDULE_CONTROLLER = "schedules/{id}";
	private static final String GET_SCHEDULE_USING_GET_ON_THE_SCHEDULE_CONTROLLER = "schedules/{id}";
	private static final String DELETE_SCHEDULE_USING_DELETE_ON_THE_SCHEDULE_CONTROLLER = "schedules/{id}";
	private static final String REPLACE_SCHEDULE_USING_PUT_ON_THE_SCHEDULE_CONTROLLER = "schedules/{id}";
	private static final String FIND_SCHEDULE_BY_NAME_USING_GET_ON_THE_SCHEDULE_CONTROLLER = "schedules?filter=byName";
	private static final String CREATE_SCHEDULE_USING_POST_ON_THE_SCHEDULE_CONTROLLER = "schedules";

	public static Performable patchScheduleUsingPatchOnTheScheduleController(Object body, String id){
		return Task.where(
		"{0} Patch Schedule", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").body(body).pathParam("id", id).patch(PATCH_SCHEDULE_USING_PATCH_ON_THE_SCHEDULE_CONTROLLER);
			}
		);
	}

	public static Performable getScheduleUsingGetOnTheScheduleController(String id){
		return Task.where(
		"{0} Get Schedule by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_SCHEDULE_USING_GET_ON_THE_SCHEDULE_CONTROLLER);
			}
		);
	}

	public static Performable deleteScheduleUsingDeleteOnTheScheduleController(String id){
		return Task.where(
		"{0} Delete Schedule", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_SCHEDULE_USING_DELETE_ON_THE_SCHEDULE_CONTROLLER);
			}
		);
	}

	public static Performable replaceScheduleUsingPutOnTheScheduleController(Object body, String id){
		return Task.where(
		"{0} Replace Schedule", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).pathParam("id", id).put(REPLACE_SCHEDULE_USING_PUT_ON_THE_SCHEDULE_CONTROLLER);
			}
		);
	}

	public static Performable findScheduleByNameUsingGetOnTheScheduleController(String name, String filter){
		return Task.where(
		"{0} Get Schedule by name", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("name", name).queryParam("filter", filter).get(FIND_SCHEDULE_BY_NAME_USING_GET_ON_THE_SCHEDULE_CONTROLLER);
			}
		);
	}

	public static Performable createScheduleUsingPostOnTheScheduleController(Object body){
		return Task.where(
		"{0} Create Schedule", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_SCHEDULE_USING_POST_ON_THE_SCHEDULE_CONTROLLER);
			}
		);
	}



}
