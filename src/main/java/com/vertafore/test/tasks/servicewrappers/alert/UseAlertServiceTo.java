package com.vertafore.test.tasks.servicewrappers.alert;

import net.serenitybdd.screenplay.Performable;
import com.vertafore.test.abilities.CallTitanApi;
import net.serenitybdd.screenplay.Task;

/**
* This class was automatically generated on 2020/03/25 14:48:07

*/
public class UseAlertServiceTo {

	private static final String THIS_SERVICE = "alert";

	public static Performable dismissAlertsUsingPutOnTheAlertController(Object body){
		return Task.where(
		"{0} Dismiss Alerts", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).put("alerts/dismiss");
			}
		);
	}

	public static Performable getAlertsByUserIdUsingGetOnTheAlertController(String pageSize, String page){
		return Task.where(
		"{0} Get Alerts for User", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get("alerts");
			}
		);
	}

	public static Performable getAlertPreferencesUsingGetOnTheAlertPreferencesController(){
		return Task.where(
		"{0} Get alert user preferences", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").get("user-preferences");
			}
		);
	}

	public static Performable createAlertUsingPostOnTheAlertController(Object body){
		return Task.where(
		"{0} Create Alert", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("alerts");
			}
		);
	}

	public static Performable markAlertsAsReadUsingPostOnTheAlertController(Object body){
		return Task.where(
		"{0} Mark Alerts As Read", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("alerts/read");
			}
		);
	}

	public static Performable updateAlertPreferencesUsingPutOnTheAlertPreferencesController(Object body){
		return Task.where(
		"{0} Replace Alert preferences", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).put("user-preferences");
			}
		);
	}



}
