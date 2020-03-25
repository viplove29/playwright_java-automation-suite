package com.vertafore.test.tasks.servicewrappers.license;

import net.serenitybdd.screenplay.Performable;
import com.vertafore.test.abilities.CallTitanApi;
import net.serenitybdd.screenplay.Task;

/**
* This class was automatically generated on 2020/03/25 14:48:15

*/
public class UseLicenseServiceTo {

	private static final String THIS_SERVICE = "license";

	public static Performable updateLicenseForCustomerUsingPutOnTheLicenseController(Object body, String vertaforeCustomerId){
		return Task.where(
		"{0} Update or create a license", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).pathParam("vertaforeCustomerId", vertaforeCustomerId).put("licenses/{vertaforeCustomerId}");
			}
		);
	}

	public static Performable updateLicenseDefinitionsFromTemplatesUsingPutOnTheLicenseController(){
		return Task.where(
		"{0} Triggers the license SVI process for licenses", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").put("licenses-init");
			}
		);
	}

	public static Performable getLicensesUsingGetOnTheLicenseController(){
		return Task.where(
		"{0} Get Licenses", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").get("license-definitions");
			}
		);
	}



}
