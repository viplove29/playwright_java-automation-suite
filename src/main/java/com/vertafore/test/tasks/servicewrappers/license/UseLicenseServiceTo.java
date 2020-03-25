package com.vertafore.test.tasks.servicewrappers.license;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import com.vertafore.test.abilities.CallTitanApi;

/**
* This class was automatically generated on 2020/03/25 13:40:47

*/
public class UseLicenseServiceTo {

	private static final String THIS_SERVICE = "license";
	private static final String UPDATE_LICENSE_FOR_CUSTOMER_USING_PUT_ON_THE_LICENSE_CONTROLLER = "licenses/{vertaforeCustomerId}";
	private static final String GET_LICENSES_USING_GET_ON_THE_LICENSE_CONTROLLER = "license-definitions";
	private static final String UPDATE_LICENSE_DEFINITIONS_FROM_TEMPLATES_USING_PUT_ON_THE_LICENSE_CONTROLLER = "licenses-init";

	public static Performable updateLicenseForCustomerUsingPutOnTheLicenseController(Object body, String vertaforeCustomerId){
		return Task.where(
		"{0} Update or create a license", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).pathParam("vertaforeCustomerId", vertaforeCustomerId).put(UPDATE_LICENSE_FOR_CUSTOMER_USING_PUT_ON_THE_LICENSE_CONTROLLER);
			}
		);
	}

	public static Performable getLicensesUsingGetOnTheLicenseController(){
		return Task.where(
		"{0} Get Licenses", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").get(GET_LICENSES_USING_GET_ON_THE_LICENSE_CONTROLLER);
			}
		);
	}

	public static Performable updateLicenseDefinitionsFromTemplatesUsingPutOnTheLicenseController(){
		return Task.where(
		"{0} Triggers the license SVI process for licenses", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").put(UPDATE_LICENSE_DEFINITIONS_FROM_TEMPLATES_USING_PUT_ON_THE_LICENSE_CONTROLLER);
			}
		);
	}



}
