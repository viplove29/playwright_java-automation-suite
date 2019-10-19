package com.vertafore.test.form.steps.teststeps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vertafore.test.common.models.StateProvinceV1;
import com.vertafore.test.common.models.canonical.CanonicalObjectType;
import com.vertafore.test.common.models.services.form.*;
import com.vertafore.test.common.servicewrappers.form.FormService;
import com.vertafore.test.common.util.ServiceUtils;
import io.restassured.response.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.openqa.selenium.InvalidArgumentException;

public class FormTemplateTestSteps extends ScenarioSteps {

  private FormService formService;
  private ServiceUtils serviceUtils;

  public FormTemplateTestSteps() throws IOException {
    formService = new FormService();
    serviceUtils = new ServiceUtils();
  }

  // constant file names
  public static final String ACORD_101 = "ACORD_101";
  public static final String ACORD_90_CO_2015 = "ACORD_90_CO_2015";
  public static final String ACORD_35_2017 = "ACORD_35_2017";
  public static final String ACORD_99 = "ACORD_99";

  @Step
  public void createAndMapAgencyForm() throws IOException, IllegalAccessException {
    // initialize variables
    FormTemplateV1 formTemplate;
    FormTemplateVersionV1 formTemplateVersion;
    Response formTemplateVersionResponse;
    FormTemplateVersionRelatedObjects formTemplateVersionRelatedObjects;
    HashMap<String, String> formFieldNameAndIds;
    AssociatedObjectDefinitionV1 agencyAssociatedObjectDefinition;
    FormListV1 agencyAddressFormList;

    //      create form template
    formTemplate = formService.postFormTemplate(buildGenericFormTemplateBody());

    //        create form template version and save the Response
    formTemplateVersionResponse =
        formService.postFormTemplateVersion(
            formTemplate.id,
            buildGenericFormTemplateVersionByFileName(ACORD_35_2017, "taco-test", "DRAFT"));

    // grab the FormTemplateVersionV1 off the response
    formTemplateVersion = getFormTemplateVersionV1FromResponse(formTemplateVersionResponse);

    // grab the RelatedObjects off the response
    formTemplateVersionRelatedObjects =
        getRelatedObjectsFromFormTemplateVersionResponse(formTemplateVersionResponse);

    //        save the "form_field_name" : "id" from response so we can patch form fields
    formFieldNameAndIds =
        saveFormFieldNameAndIdFromRelatedObjects(formTemplateVersionRelatedObjects);

    //        check form template version created response for default AOD's of type
    agencyAssociatedObjectDefinition =
        checkRelatedObjectsForDefaultAodOrCreateAodByType(
            formTemplateVersionRelatedObjects,
            CanonicalObjectType.AGENCY,
            formTemplate.id,
            formTemplateVersion.id);

    // make form list
    agencyAddressFormList =
        formService.postFormList(
            buildFormListOfAgencyAddresses("/addresses", agencyAssociatedObjectDefinition.id),
            formTemplate.id,
            formTemplateVersion.id);

    // patch
    // build form fields to map our agency data on the form
    FormFieldV1 buildAgencyMailingAddressLineOneA =
        getSingleFormFieldFromRelatedObjects(
            "Producer_MailingAddress_LineOne_A", formTemplateVersionRelatedObjects);
    buildAgencyMailingAddressLineOneA.listIndex = 1;
    buildAgencyMailingAddressLineOneA.formListId = agencyAddressFormList.id;
    buildAgencyMailingAddressLineOneA.path = "/streetAddress";

    FormFieldV1 buildAgencyMailingAddressLineTwoA =
        getSingleFormFieldFromRelatedObjects(
            "Producer_MailingAddress_LineTwo_A", formTemplateVersionRelatedObjects);
    buildAgencyMailingAddressLineTwoA.listIndex = 1;
    buildAgencyMailingAddressLineTwoA.formListId = agencyAddressFormList.id;
    buildAgencyMailingAddressLineTwoA.path = "/streetAddress2";

    FormFieldV1 buildAgencyMailingAddressCityNameA =
        getSingleFormFieldFromRelatedObjects(
            "Producer_MailingAddress_CityName_A", formTemplateVersionRelatedObjects);
    buildAgencyMailingAddressCityNameA.listIndex = 1;
    buildAgencyMailingAddressCityNameA.formListId = agencyAddressFormList.id;
    buildAgencyMailingAddressCityNameA.path = "/locality";

    FormFieldV1 buildAgencyMailingAddressStateOrProvinceCodeA =
        getSingleFormFieldFromRelatedObjects(
            "Producer_MailingAddress_StateOrProvinceCode_A", formTemplateVersionRelatedObjects);
    buildAgencyMailingAddressStateOrProvinceCodeA.listIndex = 1;
    buildAgencyMailingAddressStateOrProvinceCodeA.formListId = agencyAddressFormList.id;
    buildAgencyMailingAddressStateOrProvinceCodeA.path = "/region";
    buildAgencyMailingAddressStateOrProvinceCodeA.format = Format.STATE_ABBREVIATION;

    FormFieldV1 buildAgencyMailingAddressPostalCodeA =
        getSingleFormFieldFromRelatedObjects(
            "Producer_MailingAddress_PostalCode_A", formTemplateVersionRelatedObjects);
    buildAgencyMailingAddressPostalCodeA.listIndex = 1;
    buildAgencyMailingAddressPostalCodeA.formListId = agencyAddressFormList.id;
    buildAgencyMailingAddressPostalCodeA.path = "/postalCode";

    // send it off patch request o
    formService.patchFormField(
        buildAgencyMailingAddressLineOneA,
        formTemplate.id,
        formTemplateVersion.id,
        formFieldNameAndIds.get("Producer_MailingAddress_LineOne_A"));

    formService.patchFormField(
        buildAgencyMailingAddressLineTwoA,
        formTemplate.id,
        formTemplateVersion.id,
        formFieldNameAndIds.get("Producer_MailingAddress_LineTwo_A"));

    formService.patchFormField(
        buildAgencyMailingAddressCityNameA,
        formTemplate.id,
        formTemplateVersion.id,
        formFieldNameAndIds.get("Producer_MailingAddress_CityName_A"));

    formService.patchFormField(
        buildAgencyMailingAddressStateOrProvinceCodeA,
        formTemplate.id,
        formTemplateVersion.id,
        formFieldNameAndIds.get("Producer_MailingAddress_StateOrProvinceCode_A"));

    formService.patchFormField(
        buildAgencyMailingAddressPostalCodeA,
        formTemplate.id,
        formTemplateVersion.id,
        formFieldNameAndIds.get("Producer_MailingAddress_PostalCode_A"));

    //         save variables needed in later steps in global session
    Serenity.setSessionVariable("formTemplateVersionId").to(formTemplateVersion.id);
    Serenity.setSessionVariable("agencyAssociatedObjectDefinitionId")
        .to(agencyAssociatedObjectDefinition.id);
    Serenity.setSessionVariable("formFieldNameAndIds").to(formFieldNameAndIds);
  }

  @Step
  public void createAndMapCustomerForm() {
    //        create form template

    //        create form template version

    //        save the "name" : "id" from response in session

    //        check form template version created response for default AOD's of type
    //        If not there, then create  AOD and save in global sesssion

    //         Make form lists if necessary

    //         patch form fields associated with object type

  }

  @Step
  public void createAndMapCanonicalPolicyForm() throws IOException, IllegalAccessException {
    // initialize variables
    FormTemplateV1 formTemplate;
    FormTemplateVersionV1 formTemplateVersion;
    Response formTemplateVersionResponse;
    FormTemplateVersionRelatedObjects formTemplateVersionRelatedObjects;
    HashMap<String, String> formFieldNameAndIds;
    AssociatedObjectDefinitionV1 titanPolicyAssociatedObjectDefinition;

    //      create form template
    formTemplate = formService.postFormTemplate(buildGenericFormTemplateBody());

    //        create form template version
    formTemplateVersionResponse =
        formService.postFormTemplateVersion(
            formTemplate.id,
            buildGenericFormTemplateVersionByFileName(ACORD_99, "taco-test", "DRAFT"));

    // grab the FormTemplateVersionV1 off the response
    formTemplateVersion = getFormTemplateVersionV1FromResponse(formTemplateVersionResponse);

    // grab the RelatedObjects off the response
    formTemplateVersionRelatedObjects =
        getRelatedObjectsFromFormTemplateVersionResponse(formTemplateVersionResponse);

    //        save the "form_field_name" : "id" from response so we can patch form fields
    formFieldNameAndIds =
        saveFormFieldNameAndIdFromRelatedObjects(formTemplateVersionRelatedObjects);

    //        check form template version created response for default AOD's of type
    titanPolicyAssociatedObjectDefinition =
        checkRelatedObjectsForDefaultAodOrCreateAodByType(
            formTemplateVersionRelatedObjects,
            CanonicalObjectType.POLICY,
            formTemplate.id,
            formTemplateVersion.id);

    // make form list if necessary (skip for now)

    // build form fields to map our policy data on the form
    FormFieldV1 buildPolicyNumber =
        getSingleFormFieldFromRelatedObjects(
            "AccidentConviction_IncidentDescription_A", formTemplateVersionRelatedObjects);
    buildPolicyNumber.path = "/policyNumber";
    buildPolicyNumber.associatedObjectDefinitionId = titanPolicyAssociatedObjectDefinition.id;

    FormFieldV1 buildEffectiveDate =
        getSingleFormFieldFromRelatedObjects(
            "AccidentConviction_IncidentDescription_B", formTemplateVersionRelatedObjects);
    buildEffectiveDate.path = "/effectiveDate";
    buildEffectiveDate.format = Format.SHORT_DATE_US;
    buildEffectiveDate.associatedObjectDefinitionId = titanPolicyAssociatedObjectDefinition.id;

    FormFieldV1 buildNamedInsureds =
        getSingleFormFieldFromRelatedObjects(
            "AccidentConviction_IncidentDescription_C", formTemplateVersionRelatedObjects);
    buildNamedInsureds.path = "/namedInsureds";
    buildNamedInsureds.listIndex = 1;
    buildNamedInsureds.associatedObjectDefinitionId = titanPolicyAssociatedObjectDefinition.id;

    FormFieldV1 buildPolicyStatus =
        getSingleFormFieldFromRelatedObjects(
            "AccidentConviction_IncidentDescription_D", formTemplateVersionRelatedObjects);
    buildPolicyStatus.path = "/policyStatus";
    buildPolicyStatus.associatedObjectDefinitionId = titanPolicyAssociatedObjectDefinition.id;

    FormFieldV1 buildPremium =
        getSingleFormFieldFromRelatedObjects(
            "AccidentConviction_IncidentDescription_E", formTemplateVersionRelatedObjects);
    buildPremium.path = "/premium";
    buildPremium.format = Format.ROUND_TO_TWO_DECIMALS;
    buildPremium.associatedObjectDefinitionId = titanPolicyAssociatedObjectDefinition.id;

    FormFieldV1 buildCancellationDate =
        getSingleFormFieldFromRelatedObjects(
            "AccidentConviction_IncidentDescription_F", formTemplateVersionRelatedObjects);
    buildCancellationDate.path = "/cancellationDate";
    buildCancellationDate.format = Format.SHORT_DATE_US;
    buildCancellationDate.associatedObjectDefinitionId = titanPolicyAssociatedObjectDefinition.id;

    FormFieldV1 buildCancellationDescription =
        getSingleFormFieldFromRelatedObjects(
            "AccidentConviction_IncidentDescription_G", formTemplateVersionRelatedObjects);
    buildCancellationDescription.path = "/cancellationDescription";
    buildCancellationDescription.associatedObjectDefinitionId =
        titanPolicyAssociatedObjectDefinition.id;

    FormFieldV1 buildCancellationReason =
        getSingleFormFieldFromRelatedObjects(
            "AccidentConviction_IncidentDescription_H", formTemplateVersionRelatedObjects);
    buildCancellationReason.path = "/cancellationReason";
    buildCancellationReason.associatedObjectDefinitionId = titanPolicyAssociatedObjectDefinition.id;

    FormFieldV1 buildCancellationType =
        getSingleFormFieldFromRelatedObjects(
            "AccidentConviction_IncidentDescription_I", formTemplateVersionRelatedObjects);
    buildCancellationType.path = "/cancellationType";
    buildCancellationType.associatedObjectDefinitionId = titanPolicyAssociatedObjectDefinition.id;

    FormFieldV1 buildReinstatementDate =
        getSingleFormFieldFromRelatedObjects(
            "AccidentConviction_IncidentDescription_J", formTemplateVersionRelatedObjects);
    buildReinstatementDate.path = "/reinstatementDate";
    buildReinstatementDate.format = Format.SHORT_DATE_US;
    buildReinstatementDate.associatedObjectDefinitionId = titanPolicyAssociatedObjectDefinition.id;

    FormFieldV1 buildReinstatementDescription =
        getSingleFormFieldFromRelatedObjects(
            "AccidentConviction_IncidentDescription_K", formTemplateVersionRelatedObjects);
    buildReinstatementDescription.path = "/reinstatementDescription";
    buildReinstatementDescription.associatedObjectDefinitionId =
        titanPolicyAssociatedObjectDefinition.id;

    FormFieldV1 buildUnearnedAgencyCommission =
        getSingleFormFieldFromRelatedObjects(
            "AccidentConviction_IncidentDescription_L", formTemplateVersionRelatedObjects);
    buildUnearnedAgencyCommission.path = "/unearnedAgencyCommission";
    buildUnearnedAgencyCommission.format = Format.ROUND_TO_TWO_DECIMALS;
    buildUnearnedAgencyCommission.associatedObjectDefinitionId =
        titanPolicyAssociatedObjectDefinition.id;

    FormFieldV1 buildUnearnedPremium =
        getSingleFormFieldFromRelatedObjects(
            "AccidentConviction_IncidentDescription_M", formTemplateVersionRelatedObjects);
    buildUnearnedPremium.path = "/unearnedPremium";
    buildUnearnedPremium.associatedObjectDefinitionId = titanPolicyAssociatedObjectDefinition.id;
    buildUnearnedPremium.format = Format.ROUND_TO_TWO_DECIMALS;

    FormFieldV1 buildFullTermPremium =
        getSingleFormFieldFromRelatedObjects(
            "AccidentConviction_IncidentDescription_N", formTemplateVersionRelatedObjects);
    buildFullTermPremium.path = "/fullTermPremium";
    buildFullTermPremium.associatedObjectDefinitionId = titanPolicyAssociatedObjectDefinition.id;
    buildFullTermPremium.format = Format.ROUND_TO_TWO_DECIMALS;

    FormFieldV1 buildEarnedAgencyCommission =
        getSingleFormFieldFromRelatedObjects(
            "AccidentConviction_IncidentDescription_O", formTemplateVersionRelatedObjects);
    buildEarnedAgencyCommission.path = "/earnedAgencyCommission";
    buildEarnedAgencyCommission.format = Format.ROUND_TO_TWO_DECIMALS;
    buildEarnedAgencyCommission.associatedObjectDefinitionId =
        titanPolicyAssociatedObjectDefinition.id;

    FormFieldV1 buildVoidedReason =
        getSingleFormFieldFromRelatedObjects(
            "AccidentConviction_IncidentDescription_P", formTemplateVersionRelatedObjects);
    buildVoidedReason.path = "/voidedReason";
    buildVoidedReason.associatedObjectDefinitionId = titanPolicyAssociatedObjectDefinition.id;

    FormFieldV1 buildAgencyCommission =
        getSingleFormFieldFromRelatedObjects(
            "AccidentConviction_IncidentDescription_Q", formTemplateVersionRelatedObjects);
    buildAgencyCommission.path = "/agencyCommission";
    buildAgencyCommission.format = Format.ROUND_TO_TWO_DECIMALS;
    buildAgencyCommission.associatedObjectDefinitionId = titanPolicyAssociatedObjectDefinition.id;

    FormFieldV1 buildProducerCommission =
        getSingleFormFieldFromRelatedObjects(
            "AccidentConviction_IncidentDescription_R", formTemplateVersionRelatedObjects);
    buildProducerCommission.path = "/producerCommission";
    buildProducerCommission.format = Format.ROUND_TO_TWO_DECIMALS;
    buildProducerCommission.associatedObjectDefinitionId = titanPolicyAssociatedObjectDefinition.id;

    FormFieldV1 buildExpirationDate =
        getSingleFormFieldFromRelatedObjects(
            "AccidentConviction_IncidentDescription_S", formTemplateVersionRelatedObjects);
    buildExpirationDate.path = "/expirationDate";
    buildExpirationDate.format = Format.SHORT_DATE_US;
    buildExpirationDate.associatedObjectDefinitionId = titanPolicyAssociatedObjectDefinition.id;

    // send it off patch request over the wire
    formService.patchFormField(
        buildPolicyNumber,
        formTemplate.id,
        formTemplateVersion.id,
        formFieldNameAndIds.get("AccidentConviction_IncidentDescription_A"));

    formService.patchFormField(
        buildEffectiveDate,
        formTemplate.id,
        formTemplateVersion.id,
        formFieldNameAndIds.get("AccidentConviction_IncidentDescription_B"));

    formService.patchFormField(
        buildNamedInsureds,
        formTemplate.id,
        formTemplateVersion.id,
        formFieldNameAndIds.get("AccidentConviction_IncidentDescription_C"));

    formService.patchFormField(
        buildPolicyStatus,
        formTemplate.id,
        formTemplateVersion.id,
        formFieldNameAndIds.get("AccidentConviction_IncidentDescription_D"));

    formService.patchFormField(
        buildPremium,
        formTemplate.id,
        formTemplateVersion.id,
        formFieldNameAndIds.get("AccidentConviction_IncidentDescription_E"));

    formService.patchFormField(
        buildCancellationDate,
        formTemplate.id,
        formTemplateVersion.id,
        formFieldNameAndIds.get("AccidentConviction_IncidentDescription_F"));

    formService.patchFormField(
        buildCancellationDescription,
        formTemplate.id,
        formTemplateVersion.id,
        formFieldNameAndIds.get("AccidentConviction_IncidentDescription_G"));

    formService.patchFormField(
        buildCancellationReason,
        formTemplate.id,
        formTemplateVersion.id,
        formFieldNameAndIds.get("AccidentConviction_IncidentDescription_H"));

    formService.patchFormField(
        buildCancellationType,
        formTemplate.id,
        formTemplateVersion.id,
        formFieldNameAndIds.get("AccidentConviction_IncidentDescription_I"));

    formService.patchFormField(
        buildReinstatementDate,
        formTemplate.id,
        formTemplateVersion.id,
        formFieldNameAndIds.get("AccidentConviction_IncidentDescription_J"));

    formService.patchFormField(
        buildReinstatementDescription,
        formTemplate.id,
        formTemplateVersion.id,
        formFieldNameAndIds.get("AccidentConviction_IncidentDescription_K"));

    formService.patchFormField(
        buildUnearnedAgencyCommission,
        formTemplate.id,
        formTemplateVersion.id,
        formFieldNameAndIds.get("AccidentConviction_IncidentDescription_L"));

    formService.patchFormField(
        buildUnearnedPremium,
        formTemplate.id,
        formTemplateVersion.id,
        formFieldNameAndIds.get("AccidentConviction_IncidentDescription_M"));

    formService.patchFormField(
        buildFullTermPremium,
        formTemplate.id,
        formTemplateVersion.id,
        formFieldNameAndIds.get("AccidentConviction_IncidentDescription_N"));

    formService.patchFormField(
        buildEarnedAgencyCommission,
        formTemplate.id,
        formTemplateVersion.id,
        formFieldNameAndIds.get("AccidentConviction_IncidentDescription_O"));
    formService.patchFormField(
        buildVoidedReason,
        formTemplate.id,
        formTemplateVersion.id,
        formFieldNameAndIds.get("AccidentConviction_IncidentDescription_P"));

    // these two fields are not mapped yet
    //    formService.sendFormFieldPatchRequest(
    //       buildAgencyCommission,
    //        formTemplate.id,
    //        formTemplateVersion.id,
    //        formFieldNameAndIds.get("AccidentConviction_IncidentDescription_Q"));
    //
    //    formService.sendFormFieldPatchRequest(
    //        buildProducerCommission,
    //        formTemplate.id,
    //        formTemplateVersion.id,
    //        formFieldNameAndIds.get("AccidentConviction_IncidentDescription_R"));

    formService.patchFormField(
        buildExpirationDate,
        formTemplate.id,
        formTemplateVersion.id,
        formFieldNameAndIds.get("AccidentConviction_IncidentDescription_S"));

    //         save variables needed in later steps in global session
    Serenity.setSessionVariable("formTemplateVersionId").to(formTemplateVersion.id);
    Serenity.setSessionVariable("titanPolicyAssociatedObjectDefinitionId")
        .to(titanPolicyAssociatedObjectDefinition.id);
    Serenity.setSessionVariable("formFieldNameAndIds").to(formFieldNameAndIds);
  }

  @Step
  public void createAndMapPersonalAutoPolicyForm() {
    //        create form template
    //        create form template version
    //        save the "name" : "id" from response in session
    //        check form template version created response for default AOD's of type
    //        If not there, then create  AOD and save in global sesssion
    //         Make form lists if necessary
    //         patch form fields associated with object type
  }

  // private methods & request bodies
  // form template
  private FormTemplateV1 buildGenericFormTemplateBody() {
    FormTemplateV1 requestBody = new FormTemplateV1();
    ArrayList<String> lobs = new ArrayList<>();
    ArrayList<StateProvinceV1> states = new ArrayList<>();
    lobs.add("8673197");
    states.add(StateProvinceV1.OREGON);
    states.add(StateProvinceV1.CALIFORNIA);

    requestBody.publisherType = "ACORD";
    requestBody.publisherSubtype = "99";
    requestBody.name = "IntegrationTest";
    requestBody.type = "APPLICATION";
    requestBody.linesOfBusiness = lobs;
    requestBody.states = states;
    requestBody.isOverflowForm = false;
    return requestBody;
  }

  // form template version
  private FormTemplateVersionV1 buildGenericFormTemplateVersionByFileName(
      String fileName, String name, String versionStatus)
      throws JsonProcessingException, JsonProcessingException {
    // version meta-data
    HashMap<String, String> versionMetaDataMap = new HashMap<>();
    if (name == null) {
      name = "test_name";
    }
    if (versionStatus == null) {
      versionStatus = "DRAFT";
    }
    versionMetaDataMap.put("name", name);
    versionMetaDataMap.put("status", versionStatus);

    // finish building the request body
    FormTemplateVersionV1 requestBody = new FormTemplateVersionV1();
    requestBody.version = new ObjectMapper().writeValueAsString(versionMetaDataMap);
    requestBody.file = serviceUtils.getFileByFileName("form", fileName, ".pdf");
    return requestBody;
  }

  private FormTemplateVersionV1 getFormTemplateVersionV1FromResponse(Response response) {
    return response.getBody().jsonPath().getObject("content", FormTemplateVersionV1.class);
  }

  // get related objects from response after create form template version
  private FormTemplateVersionRelatedObjects getRelatedObjectsFromFormTemplateVersionResponse(
      Response response) {
    return response
        .getBody()
        .jsonPath()
        .getObject("relatedObjects", FormTemplateVersionRelatedObjects.class);
  }

  // associated object definition
  private ArrayList<AssociatedObjectDefinitionV1> buildAssociatedObjectDefinitionRequestBody(
      CanonicalObjectType aodType) {
    ArrayList<AssociatedObjectDefinitionV1> requestBody = new ArrayList<>();
    AssociatedObjectDefinitionV1 aod = new AssociatedObjectDefinitionV1();
    aod.type = aodType;
    aod.name = "integrationTest";
    aod.notes = "integrationTest";
    requestBody.add(aod);

    return requestBody;
  }

  private AssociatedObjectDefinitionV1 checkRelatedObjectsForDefaultAodOrCreateAodByType(
      FormTemplateVersionRelatedObjects relatedObjects,
      CanonicalObjectType needAodOfType,
      String formTemplateId,
      String formTemplateVersionId) {

    Map<String, AssociatedObjectDefinitionV1> aods = relatedObjects.associatedObjectDefinitions;

    // loop through response and find the AOD that was created
    Optional<AssociatedObjectDefinitionV1> foundAod =
        aods.values().stream().filter(aod -> aod.type.equals(needAodOfType)).findFirst();

    if (aods.isEmpty()) {
      return formService.postAssociatedObjectDefinition(
          buildAssociatedObjectDefinitionRequestBody(needAodOfType),
          formTemplateId,
          formTemplateVersionId);
    }
    if (!foundAod.isPresent()) {
      throw new InvalidArgumentException("error finding associated object definition");
    }
    return foundAod.get();
  }

  // form lists

  private FormListV1 buildFormListOfAgencyAddresses(
      String path, String associatedObjectDefinitionId) {
    FormListV1 result = new FormListV1();
    result.associatedObjectDefinitionId = associatedObjectDefinitionId;
    result.path = path;
    result.name = "Agency Addresses";
    return result;
  }

  // form fields
  // Save form_field_name : id in global session to make patching easier.
  private HashMap<String, String> saveFormFieldNameAndIdFromRelatedObjects(
      FormTemplateVersionRelatedObjects relatedObjects) {
    Map<String, FormFieldV1> formFields = relatedObjects.formFields;
    HashMap<String, String> formFieldMap = new HashMap<>();

    for (Map.Entry<String, FormFieldV1> e : formFields.entrySet()) {
      formFieldMap.put(e.getValue().name, e.getValue().id);
    }

    return formFieldMap;
  }

  private FormFieldV1 getSingleFormFieldFromRelatedObjects(
      String formFieldName, FormTemplateVersionRelatedObjects relatedObjects) {
    Map<String, FormFieldV1> formFields = relatedObjects.formFields;
    // loop through response and find the form field with the name we need
    Optional<FormFieldV1> foundFormField =
        formFields.values().stream().filter(ff -> ff.name.equals(formFieldName)).findFirst();
    if (!foundFormField.isPresent()) {
      throw new InvalidArgumentException("error finding form field");
    }
    return foundFormField.get();
  }
}
