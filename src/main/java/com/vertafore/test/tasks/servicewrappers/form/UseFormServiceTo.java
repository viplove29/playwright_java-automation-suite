package com.vertafore.test.tasks.servicewrappers.form;

import com.vertafore.test.abilities.CallTitanApi;
import java.io.File;
import java.net.URLConnection;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class UseFormServiceTo {

  private static final String THIS_SERVICE = "form";
  private static final String UPDATE_FORM_LIST_USING_PATCH_ON_THE_FORM_LIST_CONTROLLER =
      "form-templates/{formTemplateId}/versions/{formTemplateVersionId}/form-lists/{formListId}";
  private static final String SEARCH_FOR_FORM_TEMPLATES_USING_GET_ON_THE_FORM_TEMPLATE_CONTROLLER =
      "form-templates?filter=bySearchTerm";
  private static final String CREATE_FORM_LIST_USING_POST_ON_THE_FORM_LIST_CONTROLLER =
      "form-templates/{formTemplateId}/versions/{formTemplateVersionId}/form-lists";
  private static final String
      GET_TITAN_ASSOCIATED_OBJECT_DEFINITIONS_USING_GET_ON_THE_TITAN_ASSOCIATED_OBJECT_DEFINITION_CONTROLLER =
          "form-templates/{formTemplateId}/versions/{formTemplateVersionId}/titan-associated-object-definitions";
  private static final String UPDATE_FORM_FIELD_USING_PATCH_ON_THE_FORM_FIELD_CONTROLLER =
      "form-templates/{formTemplateId}/versions/{formTemplateVersionId}/form-fields/{formFieldId}";
  private static final String GET_FORM_FIELD_BY_ID_USING_GET_ON_THE_FORM_FIELD_CONTROLLER =
      "form-templates/{formTemplateId}/versions/{formTemplateVersionId}/form-fields/{formFieldId}";
  private static final String
      PREVIEW_FORM_TEMPLATE_VERSION_USING_GET_ON_THE_FORM_TEMPLATE_VERSION_CONTROLLER =
          "form-templates/{formTemplateId}/versions/{formTemplateVersionId}/preview";
  private static final String
      GET_PDF_FOR_FORM_TEMPLATE_VERSION_USING_GET_ON_THE_FORM_TEMPLATE_VERSION_CONTROLLER =
          "form-template-versions/{formTemplateVersionId}/pdf";
  private static final String
      UPDATE_ASSOCIATED_OBJECT_DEFINITION_USING_PATCH_ON_THE_ASSOCIATED_OBJECT_DEFINITION_CONTROLLER =
          "form-templates/{formTemplateId}/versions/{formTemplateVersionId}/associated-object-definitions/{definitionId}";
  private static final String RENDER_FORM_INSTANCES_USING_POST_ON_THE_FORM_INSTANCE_CONTROLLER =
      "form-instances/pdf";
  private static final String GET_PDF_FOR_FORM_INSTANCE_USING_GET_ON_THE_FORM_INSTANCE_CONTROLLER =
      "form-instances/{id}/pdf-template";
  private static final String GET_FORM_LISTS_USING_GET_ON_THE_FORM_LIST_CONTROLLER =
      "form-templates/{formTemplateId}/versions/{formTemplateVersionId}/form-lists";
  private static final String
      CREATE_AN_AUTOGENERATED_FORM_INSTANCE_BASED_ON_TITAN_OBJECT_TYPE_ON_THE_TITAN_FORM_INSTANCE_CONTROLLER =
          "titan-form-instance-requests";
  private static final String CREATE_FORM_INSTANCE_USING_POST_ON_THE_FORM_INSTANCE_CONTROLLER =
      "form-instances";
  private static final String
      GET_ALL_FORM_TEMPLATE_VERSIONS_USING_GET_ON_THE_FORM_TEMPLATE_VERSION_CONTROLLER =
          "form-templates/{formTemplateId}/versions";
  private static final String
      CREATE_ASSOCIATED_OBJECT_USING_POST_ON_THE_ASSOCIATED_OBJECT_CONTROLLER =
          "form-instances/{instanceId}/associated-objects";
  private static final String GET_FORM_INSTANCES_BY_IDS_USING_GET_ON_THE_FORM_INSTANCE_CONTROLLER =
      "form-instances?filter=byIds";
  private static final String
      CREATE_ASSOCIATED_OBJECT_DEFINITIONS_USING_POST_ON_THE_ASSOCIATED_OBJECT_DEFINITION_CONTROLLER =
          "form-templates/{formTemplateId}/versions/{formTemplateVersionId}/associated-object-definitions";
  private static final String
      GET_PDF_FOR_FORM_TEMPLATE_VERSION_ON_THE_FORM_TEMPLATE_VERSION_CONTROLLER =
          "form-templates/{formTemplateId}/versions/{formTemplateVersionId}/pdf";
  private static final String
      CREATE_FORM_TEMPLATE_VERSION_USING_POST_ON_THE_FORM_TEMPLATE_VERSION_CONTROLLER =
          "form-templates/{formTemplateId}/versions";
  private static final String
      APPLY_MAPPINGS_FOR_FORM_INSTANCE_USING_GET_ON_THE_FORM_INSTANCE_CONTROLLER =
          "form-instances/{id}/mappings";
  private static final String GET_FORM_LIST_USING_GET_ON_THE_FORM_LIST_CONTROLLER =
      "form-templates/{formTemplateId}/versions/{formTemplateVersionId}/form-lists/{id}";
  private static final String
      GET_ASSOCIATED_OBJECT_BY_ID_USING_GET_ON_THE_ASSOCIATED_OBJECT_CONTROLLER =
          "form-instances/{formInstanceId}/associated-objects/{id}";
  private static final String SEARCH_FORM_FIELDS_BY_NAME_USING_GET_ON_THE_FORM_FIELD_CONTROLLER =
      "form-templates/{formTemplateId}/versions/{formTemplateVersionId}/form-fields?filter=byName";
  private static final String
      UPDATE_FORM_TEMPLATE_VERSION_USING_PATCH_ON_THE_FORM_TEMPLATE_VERSION_CONTROLLER =
          "form-templates/{formTemplateId}/versions/{formTemplateVersionId}";
  private static final String
      GET_FORM_TEMPLATE_VERSION_USING_GET_ON_THE_FORM_TEMPLATE_VERSION_CONTROLLER =
          "form-templates/{formTemplateId}/versions/{formTemplateVersionId}";
  private static final String
      DELETE_FORM_TEMPLATE_VERSION_USING_DELETE_ON_THE_FORM_TEMPLATE_VERSION_CONTROLLER =
          "form-templates/{formTemplateId}/versions/{formTemplateVersionId}";
  private static final String GET_FORM_TEMPLATE_USING_GET_ON_THE_FORM_TEMPLATE_CONTROLLER =
      "form-templates/{id}";
  private static final String DELETE_FORM_TEMPLATE_USING_DELETE_ON_THE_FORM_TEMPLATE_CONTROLLER =
      "form-templates/{id}";
  private static final String
      GET_ASSOCIATED_OBJECT_DEFINITIONS_BY_ID_USING_GET_ON_THE_ASSOCIATED_OBJECT_DEFINITION_CONTROLLER =
          "form-templates/{formTemplateId}/versions/{formTemplateVersionId}/associated-object-definitions/{id}";
  private static final String
      GET_FORM_TEMPLATE_VERSION_BY_VERSION_ID_ONLY_USING_GET_ON_THE_FORM_TEMPLATE_VERSION_CONTROLLER =
          "form-templates/versions/{formTemplateVersionId}";
  private static final String UPDATE_FORM_INSTANCE_USING_PATCH_ON_THE_FORM_INSTANCE_CONTROLLER =
      "form-instances/{id}";
  private static final String GET_FORM_INSTANCE_USING_GET_ON_THE_FORM_INSTANCE_CONTROLLER =
      "form-instances/{id}";
  private static final String CREATE_FORM_TEMPLATE_USING_POST_ON_THE_FORM_TEMPLATE_CONTROLLER =
      "form-templates";

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
              .patch(UPDATE_FORM_LIST_USING_PATCH_ON_THE_FORM_LIST_CONTROLLER);
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
              .get(SEARCH_FOR_FORM_TEMPLATES_USING_GET_ON_THE_FORM_TEMPLATE_CONTROLLER);
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
              .post(CREATE_FORM_LIST_USING_POST_ON_THE_FORM_LIST_CONTROLLER);
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
                  GET_TITAN_ASSOCIATED_OBJECT_DEFINITIONS_USING_GET_ON_THE_TITAN_ASSOCIATED_OBJECT_DEFINITION_CONTROLLER);
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
              .patch(UPDATE_FORM_FIELD_USING_PATCH_ON_THE_FORM_FIELD_CONTROLLER);
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
              .get(GET_FORM_FIELD_BY_ID_USING_GET_ON_THE_FORM_FIELD_CONTROLLER);
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
              .get(PREVIEW_FORM_TEMPLATE_VERSION_USING_GET_ON_THE_FORM_TEMPLATE_VERSION_CONTROLLER);
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
              .get(
                  GET_PDF_FOR_FORM_TEMPLATE_VERSION_USING_GET_ON_THE_FORM_TEMPLATE_VERSION_CONTROLLER);
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
                  UPDATE_ASSOCIATED_OBJECT_DEFINITION_USING_PATCH_ON_THE_ASSOCIATED_OBJECT_DEFINITION_CONTROLLER);
        });
  }

  public static Performable renderFormInstancesUsingPostOnTheFormInstanceController(Object body) {
    return Task.where(
        "{0} Render one or many form instances on a single PDF.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post(RENDER_FORM_INSTANCES_USING_POST_ON_THE_FORM_INSTANCE_CONTROLLER);
        });
  }

  public static Performable getPdfForFormInstanceUsingGetOnTheFormInstanceController(String id) {
    return Task.where(
        "{0} Get PDF template for Form Instance",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get(GET_PDF_FOR_FORM_INSTANCE_USING_GET_ON_THE_FORM_INSTANCE_CONTROLLER);
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
              .get(GET_FORM_LISTS_USING_GET_ON_THE_FORM_LIST_CONTROLLER);
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
              .post(
                  CREATE_AN_AUTOGENERATED_FORM_INSTANCE_BASED_ON_TITAN_OBJECT_TYPE_ON_THE_TITAN_FORM_INSTANCE_CONTROLLER);
        });
  }

  public static Performable createFormInstanceUsingPostOnTheFormInstanceController(Object body) {
    return Task.where(
        "{0} Create form instance.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post(CREATE_FORM_INSTANCE_USING_POST_ON_THE_FORM_INSTANCE_CONTROLLER);
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
              .get(
                  GET_ALL_FORM_TEMPLATE_VERSIONS_USING_GET_ON_THE_FORM_TEMPLATE_VERSION_CONTROLLER);
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
              .post(CREATE_ASSOCIATED_OBJECT_USING_POST_ON_THE_ASSOCIATED_OBJECT_CONTROLLER);
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
              .get(GET_FORM_INSTANCES_BY_IDS_USING_GET_ON_THE_FORM_INSTANCE_CONTROLLER);
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
                  CREATE_ASSOCIATED_OBJECT_DEFINITIONS_USING_POST_ON_THE_ASSOCIATED_OBJECT_DEFINITION_CONTROLLER);
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
              .get(GET_PDF_FOR_FORM_TEMPLATE_VERSION_ON_THE_FORM_TEMPLATE_VERSION_CONTROLLER);
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
              .post(
                  CREATE_FORM_TEMPLATE_VERSION_USING_POST_ON_THE_FORM_TEMPLATE_VERSION_CONTROLLER);
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
              .get(APPLY_MAPPINGS_FOR_FORM_INSTANCE_USING_GET_ON_THE_FORM_INSTANCE_CONTROLLER);
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
              .get(GET_FORM_LIST_USING_GET_ON_THE_FORM_LIST_CONTROLLER);
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
              .get(GET_ASSOCIATED_OBJECT_BY_ID_USING_GET_ON_THE_ASSOCIATED_OBJECT_CONTROLLER);
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
              .get(SEARCH_FORM_FIELDS_BY_NAME_USING_GET_ON_THE_FORM_FIELD_CONTROLLER);
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
              .patch(
                  UPDATE_FORM_TEMPLATE_VERSION_USING_PATCH_ON_THE_FORM_TEMPLATE_VERSION_CONTROLLER);
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
              .get(GET_FORM_TEMPLATE_VERSION_USING_GET_ON_THE_FORM_TEMPLATE_VERSION_CONTROLLER);
        });
  }

  public static Performable deleteFormTemplateVersionUsingDeleteOnTheFormTemplateVersionController(
      String formTemplateVersionId, String formTemplateId) {
    return Task.where(
        "{0} Delete a Form Template Version",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("formTemplateVersionId", formTemplateVersionId)
              .pathParam("formTemplateId", formTemplateId)
              .delete(
                  DELETE_FORM_TEMPLATE_VERSION_USING_DELETE_ON_THE_FORM_TEMPLATE_VERSION_CONTROLLER);
        });
  }

  public static Performable getFormTemplateUsingGetOnTheFormTemplateController(String id) {
    return Task.where(
        "{0} Get form template.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get(GET_FORM_TEMPLATE_USING_GET_ON_THE_FORM_TEMPLATE_CONTROLLER);
        });
  }

  public static Performable deleteFormTemplateUsingDeleteOnTheFormTemplateController(String id) {
    return Task.where(
        "{0} Delete Form Template",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .delete(DELETE_FORM_TEMPLATE_USING_DELETE_ON_THE_FORM_TEMPLATE_CONTROLLER);
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
                  GET_ASSOCIATED_OBJECT_DEFINITIONS_BY_ID_USING_GET_ON_THE_ASSOCIATED_OBJECT_DEFINITION_CONTROLLER);
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
              .get(
                  GET_FORM_TEMPLATE_VERSION_BY_VERSION_ID_ONLY_USING_GET_ON_THE_FORM_TEMPLATE_VERSION_CONTROLLER);
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
              .patch(UPDATE_FORM_INSTANCE_USING_PATCH_ON_THE_FORM_INSTANCE_CONTROLLER);
        });
  }

  public static Performable getFormInstanceUsingGetOnTheFormInstanceController(String id) {
    return Task.where(
        "{0} Get form instance.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get(GET_FORM_INSTANCE_USING_GET_ON_THE_FORM_INSTANCE_CONTROLLER);
        });
  }

  public static Performable createFormTemplateUsingPostOnTheFormTemplateController(Object body) {
    return Task.where(
        "{0} Create form template.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post(CREATE_FORM_TEMPLATE_USING_POST_ON_THE_FORM_TEMPLATE_CONTROLLER);
        });
  }
}
