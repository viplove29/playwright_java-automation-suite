package com.vertafore.test.tasks.servicewrappers.form;

import com.vertafore.test.abilities.CallTitanApi;
import java.io.File;
import java.net.URLConnection;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

/** This class was automatically generated on 2020/03/30 11:16:55 */
public class UseFormServiceTo {

  private static final String THIS_SERVICE = "form";

  public static Performable deleteFormTemplateVersionUsingDeleteOnTheFormTemplateVersionController(
      String formTemplateVersionId, String formTemplateId) {
    return Task.where(
        "{0} Delete a Form Template Version",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("formTemplateVersionId", formTemplateVersionId)
              .pathParam("formTemplateId", formTemplateId)
              .delete("form-templates/{formTemplateId}/versions/{formTemplateVersionId}");
        });
  }

  public static Performable createFormTemplateUsingPostOnTheFormTemplateController(Object body) {
    return Task.where(
        "{0} Create form template.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("form-templates");
        });
  }

  public static Performable applyMappingsForFormInstanceUsingGetOnTheFormInstanceController(
      String id) {
    return Task.where(
        "{0} Apply mappings for a form instance.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("form-instances/{id}/mappings");
        });
  }

  public static Performable
      createAssociatedObjectDefinitionsUsingPostOnTheAssociatedObjectDefinitionController(
          String formTemplateId, String formTemplateVersionId, Object body) {
    return Task.where(
        "{0} Create associated object definitions.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("formTemplateId", formTemplateId)
              .pathParam("formTemplateVersionId", formTemplateVersionId)
              .body(body)
              .post(
                  "form-templates/{formTemplateId}/versions/{formTemplateVersionId}/associated-object-definitions");
        });
  }

  public static Performable getFormListUsingGetOnTheFormListController(
      String formTemplateId, String formTemplateVersionId, String id) {
    return Task.where(
        "{0} Get form list.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("formTemplateId", formTemplateId)
              .pathParam("formTemplateVersionId", formTemplateVersionId)
              .pathParam("id", id)
              .get(
                  "form-templates/{formTemplateId}/versions/{formTemplateVersionId}/form-lists/{id}");
        });
  }

  public static Performable createAssociatedObjectUsingPostOnTheAssociatedObjectController(
      String instanceId, Object body) {
    return Task.where(
        "{0} Create an associated object for a form instance",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("instanceId", instanceId)
              .body(body)
              .post("form-instances/{instanceId}/associated-objects");
        });
  }

  public static Performable updateFormInstanceUsingPatchOnTheFormInstanceController(
      Object body, String id) {
    return Task.where(
        "{0} Update a Form Instance",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json-patch+json")
              .body(body)
              .pathParam("id", id)
              .patch("form-instances/{id}");
        });
  }

  public static Performable createFormListUsingPostOnTheFormListController(
      String formTemplateId, String formTemplateVersionId, Object body) {
    return Task.where(
        "{0} Create form list.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("formTemplateId", formTemplateId)
              .pathParam("formTemplateVersionId", formTemplateVersionId)
              .body(body)
              .post("form-templates/{formTemplateId}/versions/{formTemplateVersionId}/form-lists");
        });
  }

  public static Performable previewFormTemplateVersionUsingGetOnTheFormTemplateVersionController(
      String formTemplateId, String formTemplateVersionId) {
    return Task.where(
        "{0} Gets data exchange models for a given form template version",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("formTemplateId", formTemplateId)
              .pathParam("formTemplateVersionId", formTemplateVersionId)
              .get("form-templates/{formTemplateId}/versions/{formTemplateVersionId}/preview");
        });
  }

  public static Performable deleteFormTemplateUsingDeleteOnTheFormTemplateController(String id) {
    return Task.where(
        "{0} Delete Form Template",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .delete("form-templates/{id}");
        });
  }

  public static Performable renderFormInstancesUsingPostOnTheFormInstanceController(Object body) {
    return Task.where(
        "{0} Render one or many form instances on a single PDF.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("form-instances/pdf");
        });
  }

  public static Performable updateFormTemplateVersionUsingPatchOnTheFormTemplateVersionController(
      String formTemplateId, Object body, String formTemplateVersionId) {
    return Task.where(
        "{0} Update a Form Template Version",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json-patch+json")
              .pathParam("formTemplateId", formTemplateId)
              .body(body)
              .pathParam("formTemplateVersionId", formTemplateVersionId)
              .patch("form-templates/{formTemplateId}/versions/{formTemplateVersionId}");
        });
  }

  public static Performable getFormTemplateVersionUsingGetOnTheFormTemplateVersionController(
      String formTemplateId, String formTemplateVersionId) {
    return Task.where(
        "{0} Get the form template version.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("formTemplateId", formTemplateId)
              .pathParam("formTemplateVersionId", formTemplateVersionId)
              .get("form-templates/{formTemplateId}/versions/{formTemplateVersionId}");
        });
  }

  public static Performable createFormInstanceUsingPostOnTheFormInstanceController(Object body) {
    return Task.where(
        "{0} Create form instance.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("form-instances");
        });
  }

  public static Performable
      getTitanAssociatedObjectDefinitionsUsingGetOnTheTitanAssociatedObjectDefinitionController(
          String formTemplateId, String formTemplateVersionId) {
    return Task.where(
        "{0} Get the list of titan associated object definitions for the given form template version.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("formTemplateId", formTemplateId)
              .pathParam("formTemplateVersionId", formTemplateVersionId)
              .get(
                  "form-templates/{formTemplateId}/versions/{formTemplateVersionId}/titan-associated-object-definitions");
        });
  }

  public static Performable getFormTemplateUsingGetOnTheFormTemplateController(String id) {
    return Task.where(
        "{0} Get form template.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("form-templates/{id}");
        });
  }

  public static Performable
      getAssociatedObjectDefinitionsByIdUsingGetOnTheAssociatedObjectDefinitionController(
          String formTemplateId, String formTemplateVersionId, String id) {
    return Task.where(
        "{0} Get the associated object definition by the associated object definition id.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("formTemplateId", formTemplateId)
              .pathParam("formTemplateVersionId", formTemplateVersionId)
              .pathParam("id", id)
              .get(
                  "form-templates/{formTemplateId}/versions/{formTemplateVersionId}/associated-object-definitions/{id}");
        });
  }

  public static Performable
      createAnAutogeneratedFormInstanceBasedOnTitanObjectTypeOnTheTitanFormInstanceController(
          Object body) {
    return Task.where(
        "{0} Create an auto-generated form instance based on Titan object type.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("titan-form-instance-requests");
        });
  }

  public static Performable getPdfForFormTemplateVersionUsingGetOnTheFormTemplateVersionController(
      String formTemplateVersionId) {
    return Task.where(
        "{0} Get PDF for Form Template Version",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("formTemplateVersionId", formTemplateVersionId)
              .get("form-template-versions/{formTemplateVersionId}/pdf");
        });
  }

  public static Performable
      updateAssociatedObjectDefinitionUsingPatchOnTheAssociatedObjectDefinitionController(
          String formTemplateId, Object body, String formTemplateVersionId, String definitionId) {
    return Task.where(
        "{0} Update an Associated Object Definition",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json-patch+json")
              .pathParam("formTemplateId", formTemplateId)
              .body(body)
              .pathParam("formTemplateVersionId", formTemplateVersionId)
              .pathParam("definitionId", definitionId)
              .patch(
                  "form-templates/{formTemplateId}/versions/{formTemplateVersionId}/associated-object-definitions/{definitionId}");
        });
  }

  public static Performable
      getFormTemplateVersionByVersionIdOnlyUsingGetOnTheFormTemplateVersionController(
          String formTemplateVersionId) {
    return Task.where(
        "{0} Get the form template version.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("formTemplateVersionId", formTemplateVersionId)
              .get("form-templates/versions/{formTemplateVersionId}");
        });
  }

  public static Performable getPdfForFormTemplateVersionOnTheFormTemplateVersionController(
      String formTemplateVersionId, String formTemplateId) {
    return Task.where(
        "{0} Get PDF for Form Template Version",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("formTemplateVersionId", formTemplateVersionId)
              .pathParam("formTemplateId", formTemplateId)
              .get("form-templates/{formTemplateId}/versions/{formTemplateVersionId}/pdf");
        });
  }

  public static Performable searchForFormTemplatesUsingGetOnTheFormTemplateController(
      String pageSize,
      String page,
      String searchTerm,
      String preferredLobs,
      String states,
      String sortField,
      String formTypes,
      String versionStatus,
      String isAscending,
      String isOverflow,
      String includeCarrierIds,
      String lobs,
      String publisherType,
      String filter) {
    return Task.where(
        "{0} Search for Form Templates",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("searchTerm", searchTerm)
              .queryParam("preferredLobs", preferredLobs)
              .queryParam("states", states)
              .queryParam("sortField", sortField)
              .queryParam("formTypes", formTypes)
              .queryParam("versionStatus", versionStatus)
              .queryParam("isAscending", isAscending)
              .queryParam("isOverflow", isOverflow)
              .queryParam("includeCarrierIds", includeCarrierIds)
              .queryParam("lobs", lobs)
              .queryParam("publisherType", publisherType)
              .queryParam("filter", filter)
              .get("form-templates?filter=bySearchTerm");
        });
  }

  public static Performable getPdfForFormInstanceUsingGetOnTheFormInstanceController(String id) {
    return Task.where(
        "{0} Get PDF template for Form Instance",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("form-instances/{id}/pdf-template");
        });
  }

  public static Performable getFormListsUsingGetOnTheFormListController(
      String formTemplateId, String formTemplateVersionId, String pageSize, String page) {
    return Task.where(
        "{0} Get form lists.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("formTemplateId", formTemplateId)
              .pathParam("formTemplateVersionId", formTemplateVersionId)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get("form-templates/{formTemplateId}/versions/{formTemplateVersionId}/form-lists");
        });
  }

  public static Performable searchFormFieldsByNameUsingGetOnTheFormFieldController(
      String pageSize,
      String page,
      String formTemplateId,
      String formTemplateVersionId,
      String searchTerm,
      String filter) {
    return Task.where(
        "{0} Search form fields by name.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .pathParam("formTemplateId", formTemplateId)
              .pathParam("formTemplateVersionId", formTemplateVersionId)
              .queryParam("searchTerm", searchTerm)
              .queryParam("filter", filter)
              .get(
                  "form-templates/{formTemplateId}/versions/{formTemplateVersionId}/form-fields?filter=byName");
        });
  }

  public static Performable getFormInstanceUsingGetOnTheFormInstanceController(String id) {
    return Task.where(
        "{0} Get form instance.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("form-instances/{id}");
        });
  }

  public static Performable createFormTemplateVersionUsingPostOnTheFormTemplateVersionController(
      String formTemplateId, String version, File file) {
    String mime = URLConnection.guessContentTypeFromName(file.getName());
    return Task.where(
        "{0} Create the form template version.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("multipart/form-data")
              .pathParam("formTemplateId", formTemplateId)
              .queryParam("version", version)
              .multiPart("file", file, mime)
              .post("form-templates/{formTemplateId}/versions");
        });
  }

  public static Performable updateFormFieldUsingPatchOnTheFormFieldController(
      String formTemplateId, String formTemplateVersionId, String formFieldId, Object body) {
    return Task.where(
        "{0} Update the given form field.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("formTemplateId", formTemplateId)
              .pathParam("formTemplateVersionId", formTemplateVersionId)
              .pathParam("formFieldId", formFieldId)
              .body(body)
              .patch(
                  "form-templates/{formTemplateId}/versions/{formTemplateVersionId}/form-fields/{formFieldId}");
        });
  }

  public static Performable getFormInstancesByIdsUsingGetOnTheFormInstanceController(
      String pageSize, String page, String ids, String filter) {
    return Task.where(
        "{0} Get form instances by Ids.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("ids", ids)
              .queryParam("filter", filter)
              .get("form-instances?filter=byIds");
        });
  }

  public static Performable getAssociatedObjectByIdUsingGetOnTheAssociatedObjectController(
      String id, String formInstanceId) {
    return Task.where(
        "{0} Get associated object.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .pathParam("formInstanceId", formInstanceId)
              .get("form-instances/{formInstanceId}/associated-objects/{id}");
        });
  }

  public static Performable getFormFieldByIdUsingGetOnTheFormFieldController(
      String formTemplateId, String formTemplateVersionId, String formFieldId) {
    return Task.where(
        "{0} Get the form field by the form field id.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("formTemplateId", formTemplateId)
              .pathParam("formTemplateVersionId", formTemplateVersionId)
              .pathParam("formFieldId", formFieldId)
              .get(
                  "form-templates/{formTemplateId}/versions/{formTemplateVersionId}/form-fields/{formFieldId}");
        });
  }

  public static Performable updateFormListUsingPatchOnTheFormListController(
      String formTemplateId, String formTemplateVersionId, String formListId, Object body) {
    return Task.where(
        "{0} Update the given form list.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("formTemplateId", formTemplateId)
              .pathParam("formTemplateVersionId", formTemplateVersionId)
              .pathParam("formListId", formListId)
              .body(body)
              .patch(
                  "form-templates/{formTemplateId}/versions/{formTemplateVersionId}/form-lists/{formListId}");
        });
  }

  public static Performable getAllFormTemplateVersionsUsingGetOnTheFormTemplateVersionController(
      String formTemplateId, String versionStatus, String pageSize, String page) {
    return Task.where(
        "{0} Get all form template versions.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("formTemplateId", formTemplateId)
              .queryParam("versionStatus", versionStatus)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get("form-templates/{formTemplateId}/versions");
        });
  }
}
