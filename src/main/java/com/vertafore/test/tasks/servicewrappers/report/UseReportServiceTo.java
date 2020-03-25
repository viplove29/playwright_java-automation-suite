package com.vertafore.test.tasks.servicewrappers.report;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import com.vertafore.test.abilities.CallTitanApi;

/**
* This class was automatically generated on 2020/03/25 13:40:53

*/
public class UseReportServiceTo {

	private static final String THIS_SERVICE = "report";
	private static final String GET_POLICIES_USING_GET_ON_THE_POLICY_REPORT_CONTROLLER = "policies";

	public static Performable getPoliciesUsingGetOnThePolicyReportController(String pageSize, String page){
		return Task.where(
		"{0} Get policies.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(GET_POLICIES_USING_GET_ON_THE_POLICY_REPORT_CONTROLLER);
			}
		);
	}



}
