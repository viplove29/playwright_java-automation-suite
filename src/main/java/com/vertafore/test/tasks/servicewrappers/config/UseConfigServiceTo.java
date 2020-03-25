package com.vertafore.test.tasks.servicewrappers.config;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import com.vertafore.test.abilities.CallTitanApi;

/**
* This class was automatically generated on 2020/03/25 13:39:34

*/
public class UseConfigServiceTo {

	private static final String THIS_SERVICE = "config";
	private static final String GET_CONFIG_USING_GET_ON_THE_CONFIG_CONTROLLER = "namespaces/{namespace}/configs/{configName}";
	private static final String REPLACE_BULK_PATCH_USING_PUT_ON_THE_BULK_PATCH_CONTROLLER = "patches";
	private static final String PUT_OPERATION_USING_PUT_ON_THE_PATCH_CONTROLLER = "namespaces/{namespace}/configs/{configName}/patches/op?sort=[valid json paths - \foo ]";
	private static final String REPLACE_PATCH_USING_PUT_ON_THE_PATCH_CONTROLLER = "namespaces/{namespace}/configs/{configName}/patches?path=[valid json paths - \foo ]&sort=[valid json paths - \foo ]";
	private static final String CREATE_PATCH_USING_POST_ON_THE_PATCH_CONTROLLER = "namespaces/{namespace}/configs/{configName}/patches";
	private static final String GET_PATCH_USING_GET_ON_THE_PATCH_CONTROLLER = "namespaces/{namespace}/configs/{configName}/patches";
	private static final String DELETE_PATCH_USING_DELETE_ON_THE_PATCH_CONTROLLER = "namespaces/{namespace}/configs/{configName}/patches";
	private static final String GET_USER_CONFIG_USING_GET_ON_THE_CONFIG_CONTROLLER = "namespaces/{namespace}/configs/{configName}/users/{userId}";
	private static final String REPLACE_USER_PATCH_USING_PUT_ON_THE_PATCH_CONTROLLER = "namespaces/{namespace}/configs/{configName}/users/{userId}/patches";

	public static Performable getConfigUsingGetOnTheConfigController(String namespace, String configName){
		return Task.where(
		"{0} Find a config by name", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("namespace", namespace).pathParam("configName", configName).get(GET_CONFIG_USING_GET_ON_THE_CONFIG_CONTROLLER);
			}
		);
	}

	public static Performable replaceBulkPatchUsingPutOnTheBulkPatchController(Object body){
		return Task.where(
		"{0} Replace Patches for Configs in Namespaces", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).put(REPLACE_BULK_PATCH_USING_PUT_ON_THE_BULK_PATCH_CONTROLLER);
			}
		);
	}

	public static Performable putOperationUsingPutOnThePatchController(String namespace, String configName, Object body, String sort){
		return Task.where(
		"{0} set patch op", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("namespace", namespace).pathParam("configName", configName).body(body).queryParam("sort", sort).put(PUT_OPERATION_USING_PUT_ON_THE_PATCH_CONTROLLER);
			}
		);
	}

	public static Performable replacePatchUsingPutOnThePatchController(String namespace, String configName, Object body, String path, String sort){
		return Task.where(
		"{0} Replace Patch with the given selector", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("namespace", namespace).pathParam("configName", configName).body(body).queryParam("path", path).queryParam("sort", sort).put(REPLACE_PATCH_USING_PUT_ON_THE_PATCH_CONTROLLER);
			}
		);
	}

	public static Performable createPatchUsingPostOnThePatchController(String namespace, String configName, Object body){
		return Task.where(
		"{0} Create Patch with the given selector", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("namespace", namespace).pathParam("configName", configName).body(body).post(CREATE_PATCH_USING_POST_ON_THE_PATCH_CONTROLLER);
			}
		);
	}

	public static Performable getPatchUsingGetOnThePatchController(String namespace, String configName){
		return Task.where(
		"{0} Get Patch with the given selector", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("namespace", namespace).pathParam("configName", configName).get(GET_PATCH_USING_GET_ON_THE_PATCH_CONTROLLER);
			}
		);
	}

	public static Performable deletePatchUsingDeleteOnThePatchController(String namespace, String configName){
		return Task.where(
		"{0} Delete Patch with the given selector", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("namespace", namespace).pathParam("configName", configName).delete(DELETE_PATCH_USING_DELETE_ON_THE_PATCH_CONTROLLER);
			}
		);
	}

	public static Performable getUserConfigUsingGetOnTheConfigController(String namespace, String configName, String userId){
		return Task.where(
		"{0} Get user preference with the given selector", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("namespace", namespace).pathParam("configName", configName).pathParam("userId", userId).get(GET_USER_CONFIG_USING_GET_ON_THE_CONFIG_CONTROLLER);
			}
		);
	}

	public static Performable replaceUserPatchUsingPutOnThePatchController(String namespace, String configName, String userId, Object body){
		return Task.where(
		"{0} Replace user Patch with the given selector", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("namespace", namespace).pathParam("configName", configName).pathParam("userId", userId).body(body).put(REPLACE_USER_PATCH_USING_PUT_ON_THE_PATCH_CONTROLLER);
			}
		);
	}



}
