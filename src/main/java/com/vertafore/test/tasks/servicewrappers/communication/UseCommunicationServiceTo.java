package com.vertafore.test.tasks.servicewrappers.communication;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import com.vertafore.test.abilities.CallTitanApi;

/**
* This class was automatically generated on 2020/03/25 13:39:33

*/
public class UseCommunicationServiceTo {

	private static final String THIS_SERVICE = "communication";
	private static final String GET_PHONE_CALL_COMMUNICATION_ON_THE_PHONE_CALL_COMMUNICATION_CONTROLLER = "phone-calls/{id}";
	private static final String FIND_BY_TYPES_AND_QUERY_USING_GET_ON_THE_LOG_ENTRY_CONTROLLER = "log-entries?filter=byTypesAndQuery";
	private static final String CREATE_PHONE_CALL_COMMUNICATION_ON_THE_PHONE_CALL_COMMUNICATION_CONTROLLER = "phone-calls";
	private static final String CREATE_TEXT_COMMUNICATION_ON_THE_TEXT_COMMUNICATION_CONTROLLER = "texts";
	private static final String RETRIEVE_LOTS_OF_PHONE_CALL_COMMUNICATION_BY_ID_ON_THE_PHONE_CALL_COMMUNICATION_CONTROLLER = "phone-calls?filter=byIds";
	private static final String CREATE_COMMUNICATION_USING_POST_ON_THE_EMAIL_COMMUNICATION_CONTROLLER = "emails";
	private static final String GET_TEXT_COMMUNICATION_ON_THE_TEXT_COMMUNICATION_CONTROLLER = "texts/{id}";
	private static final String RETRIEVE_LOTS_OF_MEETING_COMMUNICATION_BY_ID_ON_THE_MEETING_COMMUNICATION_CONTROLLER = "meetings?filter=byIds";
	private static final String GET_MEETING_COMMUNICATION_ON_THE_MEETING_COMMUNICATION_CONTROLLER = "meetings/{id}";
	private static final String GET_COMMUNICATION_USING_GET_ON_THE_EMAIL_COMMUNICATION_CONTROLLER = "emails/{id}";
	private static final String FIND_COMMUNICATIONS_BY_IDS_USING_GET_ON_THE_EMAIL_COMMUNICATION_CONTROLLER = "emails?filter=byIds";
	private static final String CREATE_MEETING_COMMUNICATION_ON_THE_MEETING_COMMUNICATION_CONTROLLER = "meetings";
	private static final String RETRIEVE_LOTS_OF_TEXT_COMMUNICATION_BY_ID_ON_THE_TEXT_COMMUNICATION_CONTROLLER = "texts?filter=byIds";

	public static Performable getPhoneCallCommunicationOnThePhoneCallCommunicationController(String id){
		return Task.where(
		"{0} Get Phone Call Communication", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_PHONE_CALL_COMMUNICATION_ON_THE_PHONE_CALL_COMMUNICATION_CONTROLLER);
			}
		);
	}

	public static Performable findByTypesAndQueryUsingGetOnTheLogEntryController(String types, String sortField, String isDescending, String searchTerm, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find Communication by search string and filter by types", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("types", types).queryParam("sortField", sortField).queryParam("isDescending", isDescending).queryParam("searchTerm", searchTerm).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(FIND_BY_TYPES_AND_QUERY_USING_GET_ON_THE_LOG_ENTRY_CONTROLLER);
			}
		);
	}

	public static Performable createPhoneCallCommunicationOnThePhoneCallCommunicationController(Object body){
		return Task.where(
		"{0} Create Phone Call Communication", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_PHONE_CALL_COMMUNICATION_ON_THE_PHONE_CALL_COMMUNICATION_CONTROLLER);
			}
		);
	}

	public static Performable createTextCommunicationOnTheTextCommunicationController(Object body){
		return Task.where(
		"{0} Create Text Communication", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_TEXT_COMMUNICATION_ON_THE_TEXT_COMMUNICATION_CONTROLLER);
			}
		);
	}

	public static Performable retrieveLotsOfPhoneCallCommunicationByIdOnThePhoneCallCommunicationController(String ids, String pageSize, String page, String filter){
		return Task.where(
		"{0} Retrieve lots of Phone Call Communication by id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("ids", ids).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(RETRIEVE_LOTS_OF_PHONE_CALL_COMMUNICATION_BY_ID_ON_THE_PHONE_CALL_COMMUNICATION_CONTROLLER);
			}
		);
	}

	public static Performable createCommunicationUsingPostOnTheEmailCommunicationController(Object body){
		return Task.where(
		"{0} Create Email Communication", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_COMMUNICATION_USING_POST_ON_THE_EMAIL_COMMUNICATION_CONTROLLER);
			}
		);
	}

	public static Performable getTextCommunicationOnTheTextCommunicationController(String id){
		return Task.where(
		"{0} Get Text Communication", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_TEXT_COMMUNICATION_ON_THE_TEXT_COMMUNICATION_CONTROLLER);
			}
		);
	}

	public static Performable retrieveLotsOfMeetingCommunicationByIdOnTheMeetingCommunicationController(String ids, String pageSize, String page, String filter){
		return Task.where(
		"{0} Retrieve lots of Meeting Communication by id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("ids", ids).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(RETRIEVE_LOTS_OF_MEETING_COMMUNICATION_BY_ID_ON_THE_MEETING_COMMUNICATION_CONTROLLER);
			}
		);
	}

	public static Performable getMeetingCommunicationOnTheMeetingCommunicationController(String id){
		return Task.where(
		"{0} Get Meeting Communication", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_MEETING_COMMUNICATION_ON_THE_MEETING_COMMUNICATION_CONTROLLER);
			}
		);
	}

	public static Performable getCommunicationUsingGetOnTheEmailCommunicationController(String id){
		return Task.where(
		"{0} Get Email Communication", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_COMMUNICATION_USING_GET_ON_THE_EMAIL_COMMUNICATION_CONTROLLER);
			}
		);
	}

	public static Performable findCommunicationsByIdsUsingGetOnTheEmailCommunicationController(String ids, String pageSize, String page, String filter){
		return Task.where(
		"{0} Retrieve lots of Email Communication by id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("ids", ids).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(FIND_COMMUNICATIONS_BY_IDS_USING_GET_ON_THE_EMAIL_COMMUNICATION_CONTROLLER);
			}
		);
	}

	public static Performable createMeetingCommunicationOnTheMeetingCommunicationController(Object body){
		return Task.where(
		"{0} Create Meeting Communication", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_MEETING_COMMUNICATION_ON_THE_MEETING_COMMUNICATION_CONTROLLER);
			}
		);
	}

	public static Performable retrieveLotsOfTextCommunicationByIdOnTheTextCommunicationController(String ids, String pageSize, String page, String filter){
		return Task.where(
		"{0} Retrieve lots of Text Communication by id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("ids", ids).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(RETRIEVE_LOTS_OF_TEXT_COMMUNICATION_BY_ID_ON_THE_TEXT_COMMUNICATION_CONTROLLER);
			}
		);
	}



}
