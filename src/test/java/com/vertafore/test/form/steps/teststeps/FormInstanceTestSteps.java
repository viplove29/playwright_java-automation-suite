package com.vertafore.test.form.steps.teststeps;

import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.models.v1.StateProvinceV1;
import com.vertafore.test.common.models.services.auth.EntityV1;
import com.vertafore.test.common.models.services.form.AssociatedObjectV1;
import com.vertafore.test.common.models.services.form.FormInstanceV1;
import com.vertafore.test.common.models.services.form.Format;
import com.vertafore.test.common.models.services.form.MappedFormInstanceV1;
import com.vertafore.test.common.models.services.policy.PolicyVersionV1;
import com.vertafore.test.common.servicewrappers.auth.AuthService;
import com.vertafore.test.common.servicewrappers.form.FormService;
import com.vertafore.test.common.util.ServiceUtils;
import java.io.IOException;
import java.util.HashMap;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.junit.Assert;

public class FormInstanceTestSteps extends ScenarioSteps {

  private FormService formService;
  private ServiceUtils serviceUtils;
  private AuthService authService;

  public FormInstanceTestSteps() throws IOException {
    formService = new FormService();
    serviceUtils = new ServiceUtils();
    authService = new AuthService();
  }

  // variables
  private FormInstanceV1 formInstance;

  @Step
  public void createFormInstance() throws IOException {
    // log in and change contexts
    serviceUtils.loginWithServiceAndContext("DIA User", "form", null, "DIA Entity Context");

    // get our form template version from session
    String formTemplateVersionId = Serenity.sessionVariableCalled("formTemplateVersionId");

    // create a form instance
    formInstance = formService.postFormInstance(buildFormInstanceBody(formTemplateVersionId));

    // save our formInstanceId in session so, when we post a policy version later on a policy we can
    // supply the formInstanceId
    Serenity.setSessionVariable("formInstanceId").to(formInstance.id);
  }

  @Step
  public void createAssociatedObject(String canonicalObjectType) throws IOException {
    // log in and change contexts
    serviceUtils.checkLoginOrLogIn("DIA User", "form", null, "DIA Entity Context");

    // get id's needed from session
    String associatedObjectDefinitionId;
    String objectId = Serenity.sessionVariableCalled("policyId");
    String secondaryObjectId = Serenity.sessionVariableCalled("policyVersionId");

    // if we are creating multiple AO's to a single form instance we need to know how many AO's to
    // create
    // and what the AOD's id's are.
    switch (canonicalObjectType) {
      case "AGENCY":
        associatedObjectDefinitionId =
            Serenity.sessionVariableCalled("agencyAssociatedObjectDefinitionId");
        break;
      case "CUSTOMER":
        associatedObjectDefinitionId =
            Serenity.sessionVariableCalled("customerAssociatedObjectDefinitionId");
        break;
      case "POLICY":
        associatedObjectDefinitionId =
            Serenity.sessionVariableCalled("titanPolicyAssociatedObjectDefinitionId");
        break;
      case "PERSONAL_AUTO_POLICY":
        associatedObjectDefinitionId =
            Serenity.sessionVariableCalled("personalAutoPolicyAssociatedObjectDefinitionId");
        break;
      default:
        throw new IllegalArgumentException(
            "Canonical object type supplied is not supported yet...");
    }

    // create our associated object
    // the object id and secondary object id are the most recent id's from creating stored in
    // session
    // may need to refactor if we want to put multiple policies/version on a form
    AssociatedObjectV1 associatedObject =
        formService.postAssociatedObject(
            buildAssociatedObject(associatedObjectDefinitionId, objectId, secondaryObjectId),
            formInstance.id);
  }

  @Step
  public void validateFormFieldMappingsByTitanPolicy() {
    //    get our titan policy data to validate against
    PolicyVersionV1 policyVersion = Serenity.sessionVariableCalled("policyVersion");

    //    apply our mappings and get an object with values we can assert against
    MappedFormInstanceV1 mappings = formService.applyMappingsToFormInstance(formInstance.id);
    //    make sure our mappings isn't empty before validation.
    if (!mappings.mappedFormData.isEmpty()) {
      HashMap<String, String> mappedData = mappings.mappedFormData.get(0).fillData;

      // make sure our policyVersion isn't empty before validation
      if (policyVersion != null) {

        assertThat(mappedData)
            .containsEntry("AccidentConviction_IncidentDescription_A", policyVersion.policyNumber);

        assertThat(mappedData)
            .containsEntry(
                "AccidentConviction_IncidentDescription_B",
                Format.formatValue(policyVersion.effectiveDate.toString(), Format.SHORT_DATE_US));

        assertThat(mappedData)
            .containsEntry(
                "AccidentConviction_IncidentDescription_C", policyVersion.namedInsureds.get(0));

        assertThat(mappedData)
            .containsEntry("AccidentConviction_IncidentDescription_D", policyVersion.policyStatus);

        assertThat(mappedData)
            .containsEntry(
                "AccidentConviction_IncidentDescription_E",
                Format.formatValue(policyVersion.premium.toString(), Format.ROUND_TO_TWO_DECIMALS));

        assertThat(mappedData)
            .containsEntry(
                "AccidentConviction_IncidentDescription_F",
                Format.formatValue(
                    policyVersion.cancellationDate.toString(), Format.SHORT_DATE_US));

        assertThat(mappedData)
            .containsEntry(
                "AccidentConviction_IncidentDescription_G", policyVersion.cancellationDescription);

        assertThat(mappedData)
            .containsEntry(
                "AccidentConviction_IncidentDescription_H", policyVersion.cancellationReason);

        assertThat(mappedData)
            .containsEntry(
                "AccidentConviction_IncidentDescription_I", policyVersion.cancellationType);

        // new validation for reinstatement, commenting out for now.
        //          assertThat(mappedData)
        //              .containsEntry(
        //                  "AccidentConviction_IncidentDescription_J",
        //                  Format.formatValue(
        //                      policyVersion.reinstatementDate.toString(), Format.SHORT_DATE_US));

        assertThat(mappedData)
            .containsEntry(
                "AccidentConviction_IncidentDescription_K", policyVersion.reinstatementDescription);

        assertThat(mappedData)
            .containsEntry(
                "AccidentConviction_IncidentDescription_L",
                Format.formatValue(
                    policyVersion.unearnedAgencyCommission.toString(),
                    Format.ROUND_TO_TWO_DECIMALS));

        assertThat(mappedData)
            .containsEntry(
                "AccidentConviction_IncidentDescription_M",
                Format.formatValue(
                    policyVersion.unearnedPremium.toString(), Format.ROUND_TO_TWO_DECIMALS));

        assertThat(mappedData)
            .containsEntry(
                "AccidentConviction_IncidentDescription_N",
                Format.formatValue(
                    policyVersion.fullTermPremium.toString(), Format.ROUND_TO_TWO_DECIMALS));

        assertThat(mappedData)
            .containsEntry(
                "AccidentConviction_IncidentDescription_O",
                Format.formatValue(
                    policyVersion.earnedAgencyCommission.toString(), Format.ROUND_TO_TWO_DECIMALS));

        assertThat(mappedData)
            .containsEntry("AccidentConviction_IncidentDescription_P", policyVersion.voidedReason);

        // these next two are not supported yet

        //          assertThat(mappedData)
        //              .containsEntry(
        //                  "AccidentConviction_IncidentDescription_Q",
        //                  Format.formatValue(
        //                      policyVersion.agencyCommission.toString(),
        // Format.ROUND_TO_TWO_DECIMALS));

        //          assertThat(mappedData)
        //              .containsEntry(
        //                  "AccidentConviction_IncidentDescription_R",
        //                  Format.formatValue(
        //                      policyVersion.producerCommission.toString(),
        // Format.ROUND_TO_TWO_DECIMALS));

        assertThat(mappedData)
            .containsEntry(
                "AccidentConviction_IncidentDescription_S",
                Format.formatValue(policyVersion.expirationDate.toString(), Format.SHORT_DATE_US));
      } else {
        Assert.fail("PolicyVersion is null");
      }
    }
  }

  @Step
  public void validateFormFieldMappingsByAgency() {
    // get our agency data from auth service to validate against
    serviceUtils.changeServiceTo("auth");
    EntityV1 agencyData = authService.getAgencyDataFromAuth();

    // Vertafore's address is saved in Auth with the wrong canonical model address
    // for /region it is saved as "CO" but it needs to be saved as "COLORADO"
    // so we need to make a call to AUTH and update the address before we validate our mappings
    if (agencyData.address.region.equals("CO")) {
      // need a copy of the object so I can change "COLORADO" w/o manipulating the original
      EntityV1 agencyDataToChange = authService.getAgencyDataFromAuth();
      // change the state
      agencyDataToChange.address.region = StateProvinceV1.COLORADO.toString();
      // reassign our agencyData to have the new response from update
      agencyData =
          authService.updateEntityByPut(AuthService.CREATE_OR_UPDATE_ENTITY, agencyDataToChange);
    }

    serviceUtils.changeServiceTo("form");
    // apply our mappings to the form and save in variable 'mappings'
    MappedFormInstanceV1 mappings = formService.applyMappingsToFormInstance(formInstance.id);

    if (!mappings.mappedFormData.isEmpty()) {
      HashMap<String, String> mappedData = mappings.mappedFormData.get(0).fillData;

      if (agencyData != null) {
        assertThat(mappedData)
            .containsEntry("Producer_MailingAddress_LineOne_A", agencyData.address.streetAddress);

        assertThat(mappedData)
            .containsEntry("Producer_MailingAddress_LineTwo_A", agencyData.address.streetAddress2);

        assertThat(mappedData)
            .containsEntry("Producer_MailingAddress_CityName_A", agencyData.address.locality);

        assertThat(mappedData)
            .containsEntry(
                "Producer_MailingAddress_StateOrProvinceCode_A",
                Format.formatValue(
                    agencyData.address.region.toString(), Format.STATE_ABBREVIATION));

        assertThat(mappedData)
            .containsEntry("Producer_MailingAddress_PostalCode_A", agencyData.address.postalCode);
      } else {
        Assert.fail("PolicyVersion is null");
      }
    }
  }

  private AssociatedObjectV1 buildAssociatedObject(
      String associatedObjectDefinitionId, String objectId, String secondaryObjectId) {
    AssociatedObjectV1 result = new AssociatedObjectV1();
    result.associatedObjectDefinitionId = associatedObjectDefinitionId;
    result.objectId = objectId;
    result.secondaryObjectId = secondaryObjectId;
    return result;
  }

  private FormInstanceV1 buildFormInstanceBody(String formTemplateVersionId) {
    FormInstanceV1 result = new FormInstanceV1();
    result.formTemplateVersionId = formTemplateVersionId;
    result.name = "form_instance_name";
    result.notes = "form_instance_notes";
    return result;
  }
}
