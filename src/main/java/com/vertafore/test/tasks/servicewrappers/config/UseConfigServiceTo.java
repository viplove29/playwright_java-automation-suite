package com.vertafore.test.tasks.servicewrappers.config;

import com.vertafore.test.abilities.CallTitanApi;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

/** This class was automatically generated on 2020/03/30 11:16:50 */
public class UseConfigServiceTo {

  private static final String THIS_SERVICE = "config";

  public static Performable createPatchUsingPostOnThePatchController(
      String namespace, String configName, Object body) {
    return Task.where(
        "{0} Create Patch with the given selector",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("namespace", namespace)
              .pathParam("configName", configName)
              .body(body)
              .post("namespaces/{namespace}/configs/{configName}/patches");
        });
  }

  public static Performable replaceUserPatchUsingPutOnThePatchController(
      String namespace, String configName, String userId, Object body) {
    return Task.where(
        "{0} Replace user Patch with the given selector",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("namespace", namespace)
              .pathParam("configName", configName)
              .pathParam("userId", userId)
              .body(body)
              .put("namespaces/{namespace}/configs/{configName}/users/{userId}/patches");
        });
  }

  public static Performable replacePatchUsingPutOnThePatchController(
      String namespace, String configName, Object body, String path, String sort) {
    return Task.where(
        "{0} Replace Patch with the given selector",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("namespace", namespace)
              .pathParam("configName", configName)
              .body(body)
              .queryParam("path", path)
              .queryParam("sort", sort)
              .put(
                  "namespaces/{namespace}/configs/{configName}/patches?path=[valid json paths - \foo ]&sort=[valid json paths - \foo ]");
        });
  }

  public static Performable getUserConfigUsingGetOnTheConfigController(
      String namespace, String configName, String userId) {
    return Task.where(
        "{0} Get user preference with the given selector",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("namespace", namespace)
              .pathParam("configName", configName)
              .pathParam("userId", userId)
              .get("namespaces/{namespace}/configs/{configName}/users/{userId}");
        });
  }

  public static Performable getConfigUsingGetOnTheConfigController(
      String namespace, String configName) {
    return Task.where(
        "{0} Find a config by name",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("namespace", namespace)
              .pathParam("configName", configName)
              .get("namespaces/{namespace}/configs/{configName}");
        });
  }

  public static Performable getPatchUsingGetOnThePatchController(
      String namespace, String configName) {
    return Task.where(
        "{0} Get Patch with the given selector",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("namespace", namespace)
              .pathParam("configName", configName)
              .get("namespaces/{namespace}/configs/{configName}/patches");
        });
  }

  public static Performable replaceBulkPatchUsingPutOnTheBulkPatchController(Object body) {
    return Task.where(
        "{0} Replace Patches for Configs in Namespaces",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .put("patches");
        });
  }

  public static Performable putOperationUsingPutOnThePatchController(
      String namespace, String configName, Object body, String sort) {
    return Task.where(
        "{0} set patch op",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("namespace", namespace)
              .pathParam("configName", configName)
              .body(body)
              .queryParam("sort", sort)
              .put(
                  "namespaces/{namespace}/configs/{configName}/patches/op?sort=[valid json paths - \foo ]");
        });
  }

  public static Performable deletePatchUsingDeleteOnThePatchController(
      String namespace, String configName) {
    return Task.where(
        "{0} Delete Patch with the given selector",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("namespace", namespace)
              .pathParam("configName", configName)
              .delete("namespaces/{namespace}/configs/{configName}/patches");
        });
  }
}
