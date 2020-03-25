package com.vertafore.test.tasks.servicewrappers.alert;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import com.vertafore.test.abilities.CallTitanApi;

/**
* This class was automatically generated on 2020/03/25 13:39:28

*/
public class UseAlertServiceTo {

	private static final String THIS_SERVICE = "alert";
	private static final String DISMISS_ALERTS_USING_PUT_ON_THE_ALERT_CONTROLLER = "alerts/dismiss";
	private static final String GET_ALERTS_BY_USER_ID_USING_GET_ON_THE_ALERT_CONTROLLER = "alerts";
	private static final String MARK_ALERTS_AS_READ_USING_POST_ON_THE_ALERT_CONTROLLER = "alerts/read";
	private static final String GET_ALERT_PREFERENCES_USING_GET_ON_THE_ALERT_PREFERENCES_CONTROLLER = "user-preferences";
	private static final String UPDATE_ALERT_PREFERENCES_USING_PUT_ON_THE_ALERT_PREFERENCES_CONTROLLER = "user-preferences";
	private static final String CREATE_ALERT_USING_POST_ON_THE_ALERT_CONTROLLER = "alerts";

	public static Performable dismissAlertsUsingPutOnTheAlertController(Object body){
		return Task.where(
		"{0} Dismiss Alerts", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).put(DISMISS_ALERTS_USING_PUT_ON_THE_ALERT_CONTROLLER);
			}
		);
	}

	public static Performable getAlertsByUserIdUsingGetOnTheAlertController(String pageSize, String page){
		return Task.where(
		"{0} Get Alerts for User", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(GET_ALERTS_BY_USER_ID_USING_GET_ON_THE_ALERT_CONTROLLER);
			}
		);
	}

	public static Performable markAlertsAsReadUsingPostOnTheAlertController(Object body){
		return Task.where(
		"{0} Mark Alerts As Read", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(MARK_ALERTS_AS_READ_USING_POST_ON_THE_ALERT_CONTROLLER);
			}
		);
	}

	public static Performable getAlertPreferencesUsingGetOnTheAlertPreferencesController(){
		return Task.where(
		"{0} Get alert user preferences", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").get(GET_ALERT_PREFERENCES_USING_GET_ON_THE_ALERT_PREFERENCES_CONTROLLER);
			}
		);
	}

	public static Performable updateAlertPreferencesUsingPutOnTheAlertPreferencesController(Object body){
		return Task.where(
		"{0} Replace Alert preferences", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).put(UPDATE_ALERT_PREFERENCES_USING_PUT_ON_THE_ALERT_PREFERENCES_CONTROLLER);
			}
		);
	}

	public static Performable createAlertUsingPostOnTheAlertController(Object body){
		return Task.where(
		"{0} Create Alert", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_ALERT_USING_POST_ON_THE_ALERT_CONTROLLER);
			}
		);
	}



}
