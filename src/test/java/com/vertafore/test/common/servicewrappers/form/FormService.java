package com.vertafore.test.common.servicewrappers.form;

import com.vertafore.test.common.models.general.PatchBody;
import com.vertafore.test.common.models.services.form.AssociatedObjectDefinitionV1;
import com.vertafore.test.common.models.services.form.AssociatedObjectV1;
import com.vertafore.test.common.models.services.form.FormFieldV1;
import com.vertafore.test.common.models.services.form.FormInstanceV1;
import com.vertafore.test.common.models.services.form.FormListV1;
import com.vertafore.test.common.models.services.form.FormTemplateV1;
import com.vertafore.test.common.models.services.form.FormTemplateVersionV1;
import com.vertafore.test.common.models.services.form.MappedFormInstanceV1;
import com.vertafore.test.common.util.ServiceUtils;
import io.restassured.response.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FormService {
  private ServiceUtils serviceUtils;

  public FormService() throws IOException {
    serviceUtils = new ServiceUtils();
  }

  // constants for final path structure parameters
  public static final String BASE_PATH = "/{service}/v1/{productId}/{tenantId}/entities/{entityId}";
  public static final String TEMPLATE_AND_VERSION_CONTEXT =
      "/form-templates/{formTemplateId}/versions/{formTemplateVersionId}";

  // constants for form template parameters
  public static final String FORM_TEMPLATE_CREATE = "/form-templates";
  public static final String FORM_TEMPLATE_GET = "/form-templates/{formTemplateId}";
  public static final String FORM_TEMPLATE_GET_SEARCH =
      "/form-templates/?filter=bySearchTerm&searchTerm={searchTerm}";
  public static final String FORM_TEMPLATE_DELETE = "/form-templates/{formTemplateId}";

  // constants for form template versions parameters
  public static final String FORM_TEMPLATE_VERSION_CREATE =
      "/form-templates/{formTemplateId}/versions";
  public static final String FORM_TEMPLATE_VERSION_GET = TEMPLATE_AND_VERSION_CONTEXT;
  public static final String FORM_TEMPLATE_VERSION_PATCH = TEMPLATE_AND_VERSION_CONTEXT;
  public static final String FORM_TEMPLATE_VERSION_DELETE = TEMPLATE_AND_VERSION_CONTEXT;

  // constants for form field parameters
  public static final String FORM_FIELD_GET =
      TEMPLATE_AND_VERSION_CONTEXT + "/form-fields/{formFieldId}";
  public static final String FORM_FIELD_GET_SEARCH =
      TEMPLATE_AND_VERSION_CONTEXT + "/form-fields?filter=byName&searchTerm={searchTerm}";
  public static final String FORM_FIELD_PATCH =
      TEMPLATE_AND_VERSION_CONTEXT + "/form-fields/{formFieldId}";

  // constants for form list parameters
  public static final String FORM_LIST_CREATE = TEMPLATE_AND_VERSION_CONTEXT + "/form-lists";
  public static final String FORM_LIST_GET =
      TEMPLATE_AND_VERSION_CONTEXT + "/form-lists/{formListId}";
  public static final String FORM_LIST_GET_ALL = TEMPLATE_AND_VERSION_CONTEXT + "/form-lists";
  public static final String FORM_LIST_PATCH =
      TEMPLATE_AND_VERSION_CONTEXT + "/form-lists/{formListId}";

  // constants for form associated object definition parameters
  public static final String ASSOCIATED_OBJECT_DEFINITION_CREATE =
      TEMPLATE_AND_VERSION_CONTEXT + "/associated-object-definitions";
  public static final String ASSOCIATED_OBJECT_DEFINITION_GET =
      TEMPLATE_AND_VERSION_CONTEXT
          + "/associated-object-definitions/{associatedObjectDefinitionId}";
  public static final String ASSOCIATED_OBJECT_DEFINITION_PATCH =
      TEMPLATE_AND_VERSION_CONTEXT
          + "/associated-object-definitions/{associatedObjectDefinitionId}";

  // constants for form instance parameters
  public static final String FORM_INSTANCE_CREATE = "/form-instances";
  public static final String FORM_INSTANCE_GET = "/form-instances/{formInstanceId}";
  public static final String FORM_INSTANCE_GET_SEARCH =
      "/form-instances?filter=bySearchTerm&searchTerm={searchTerm}";
  public static final String FORM_INSTANCE_PATCH = "/form-instances/{formInstanceId}";
  public static final String FORM_INSTANCE_RENDER = "/form-instances/{formInstanceId}/pdf";

  // endpoint to get mappings for a form instance in key:value pairs.
  public static final String APPLY_MAPPINGS = "/form-instances/{formInstanceId}/mappings";

  // constants for form associated object parameters
  public static final String ASSOCIATED_OBJECT_CREATE =
      "/form-instances/{formInstanceId}/associated-objects";
  public static final String ASSOCIATED_OBJECT_GET =
      "/form-instances/{formInstanceId}/associated-objects/{associatedObjectId}";

  // constants for form titan associated object definition parameters
  public static final String TITAN_ASSOCIATED_OBJECT_DEFINITION_GET =
      TEMPLATE_AND_VERSION_CONTEXT + "/titan-associated-object-definitions";

  // FORM TEMPLATE
  public FormTemplateV1 postFormTemplate(FormTemplateV1 requestBody) {
    Response response = serviceUtils.sendPostRequest(FORM_TEMPLATE_CREATE, requestBody);
    return response.getBody().jsonPath().getObject("content", FormTemplateV1.class);
  }

  // FORM TEMPLATE VERSION
  public Response postFormTemplateVersion(
      String formTemplateId, FormTemplateVersionV1 requestBody) {
    // URL
    String hydratedUrl = FORM_TEMPLATE_VERSION_CREATE.replace("{formTemplateId}", formTemplateId);
    // multipart
    HashMap<String, Object> multiPartParams = new HashMap<>();
    multiPartParams.put("file", requestBody.file);
    multiPartParams.put("version", requestBody.version);
    // send request
    return serviceUtils.sendMultiPartPostRequest(multiPartParams, hydratedUrl);
  }

  // ASSOCIATED OBJECT DEFINITION
  public AssociatedObjectDefinitionV1 postAssociatedObjectDefinition(
      ArrayList<AssociatedObjectDefinitionV1> aodBody,
      String formTemplateId,
      String formTemplateVersionId) {

    String hydratedUrl =
        applyTemplateAndVersionContextToUrl(
            ASSOCIATED_OBJECT_DEFINITION_CREATE, formTemplateId, formTemplateVersionId);

    // send request
    Response response = serviceUtils.sendPostRequest(hydratedUrl, aodBody);

    List<AssociatedObjectDefinitionV1> aod =
        response.getBody().jsonPath().getList("content", AssociatedObjectDefinitionV1.class);
    // get whole AOD model just created & return
    return aod.get(0);
  }

  // FORM LISTS
  public FormListV1 postFormList(
      FormListV1 formListBody, String formTemplateId, String formTemplateVersionId) {

    String hydratedUrl =
        applyTemplateAndVersionContextToUrl(
            FORM_LIST_CREATE, formTemplateId, formTemplateVersionId);

    Response response = serviceUtils.sendPostRequest(hydratedUrl, formListBody);
    return response.getBody().jsonPath().getObject("content", FormListV1.class);
  }

  // FORM FIELDS
  public void patchFormField(
      FormFieldV1 formField,
      String formTemplateId,
      String formTemplateVersionId,
      String formFieldId)
      throws IllegalAccessException {
    ArrayList<PatchBody> requestBody = serviceUtils.buildPatchRequestFromModel(formField);

    String hydratedUrl =
        applyTemplateAndVersionContextToUrl(FORM_FIELD_PATCH, formTemplateId, formTemplateVersionId)
            .replace("{formFieldId}", formFieldId);
    ;

    serviceUtils.sendPatchRequest(hydratedUrl, requestBody);
  }
  // ASSOCIATED OBJECT
  public AssociatedObjectV1 postAssociatedObject(
      AssociatedObjectV1 requestBody, String formInstanceId) {
    Response response =
        serviceUtils.sendPostRequest(
            ASSOCIATED_OBJECT_CREATE.replace("{formInstanceId}", formInstanceId), requestBody);
    // return our AssociatedObjectV1
    return response.getBody().jsonPath().getObject("content", AssociatedObjectV1.class);
  }

  // APPLY MAPPINGS
  public MappedFormInstanceV1 applyMappingsToFormInstance(String formInstanceId) {
    Response response =
        serviceUtils.sendGetRequest(APPLY_MAPPINGS.replace("{formInstanceId}", formInstanceId));
    return response.getBody().jsonPath().getObject("content", MappedFormInstanceV1.class);
  }

  // FORM INSTANCE
  public FormInstanceV1 postFormInstance(FormInstanceV1 requestBody) {
    Response response = serviceUtils.sendPostRequest(FORM_INSTANCE_CREATE, requestBody);
    // return our FormInstanceV1 object
    return response.getBody().jsonPath().getObject("content", FormInstanceV1.class);
  }

  // Private
  // applies the formTemplateId and formTemplateVersionId to our forms url
  private String applyTemplateAndVersionContextToUrl(
      String formUrl, String formTemplateId, String formTemplateVersionId) {
    return formUrl
        .replace("{formTemplateId}", formTemplateId)
        .replace("{formTemplateVersionId}", formTemplateVersionId);
  }
}
