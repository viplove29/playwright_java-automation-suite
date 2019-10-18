package com.vertafore.test.form.steps.stepdefinitions;

import com.vertafore.test.form.steps.teststeps.FormInstanceTestSteps;
import com.vertafore.test.form.steps.teststeps.FormTemplateTestSteps;
import com.vertafore.test.form.steps.teststeps.PolicyTestSteps;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.IOException;
import net.thucydides.core.annotations.Steps;

public class FormServiceIntegrationStepDefinitions {

  // import other test step dependencies
  @Steps FormTemplateTestSteps formTemplateTestSteps;

  @Steps FormInstanceTestSteps formInstanceTestSteps;

  @Steps PolicyTestSteps policyTestSteps;

  // this does all the work needed in form service
  @Given("^That a form template is mapped to a canonical '([^']*?)'$")
  public void createAndMapAFormByCanonicalObjectType(String canonicalObjectType)
      throws IOException, IllegalAccessException {
    switch (canonicalObjectType) {
      case "AGENCY":
        formTemplateTestSteps.createAndMapAgencyForm();
        break;
        //            case "CUSTOMER":
        //                FormTemplateTestSteps.createAndMapCustomerForm();
        //                break;
        //            case "PERSONAL_AUTO_POLICY":
        //                FormTemplateTestSteps.createAndMapPersonalAutoPolicy();
        //                break;
      case "POLICY":
        formTemplateTestSteps.createAndMapCanonicalPolicyForm();
        break;
      default:
        throw new IllegalArgumentException(
            "Canonical object type supplied is not supported yet...");
    }
  }

  //   this changes context to DIA and then creates exposures/policies & saves in session
  @Given("^I create a titan '([^']*?)'$")
  public void createObjectsInOtherServiceByType(String objectTypeToCreate) throws IOException {
    switch (objectTypeToCreate) {
        //              case "EXPOSURE GROUP":
        //                  ExposureTestSteps.createExposureGroup();
        //                  break;
      case "POLICY":
        policyTestSteps.createTitanPolicy();
        policyTestSteps.createTitanPolicyVersion();
        break;
      default:
        throw new IllegalArgumentException(
            "Canonical object type supplied is not supported yet...");
    }
  }
  // this creates form instance in form service & an associated object
  @When("^I create and render a form instance associated to that form with '([^']*?)' data$")
  public void createFormInstance(String canonicalObjectType) throws IOException {
    switch (canonicalObjectType) {
      case "AGENCY":
        formInstanceTestSteps.createFormInstance();
        break;
        //      case "CUSTOMER":
        //        formInstanceTestSteps.createFormInstance();
        //        break;
      case "POLICY":
        formInstanceTestSteps.createFormInstance();
        formInstanceTestSteps.createAssociatedObject(canonicalObjectType);
        break;
        //      case "PERSONAL_AUTO_POLICY":
        //        formInstanceTestSteps.createFormInstance();
        //        break;
      default:
        throw new IllegalArgumentException(
            "Canonical object type supplied is not supported yet...");
    }
  }

  // this validates what is on the form is what we expect assuming the calls to other services
  //   were successful and form-service mapped properly
  @Then("^'([^']*?)' data is in the form$")
  public void validateMappingsAreOnFormByType(String canonicalObjectType) {
    switch (canonicalObjectType) {
      case "AGENCY":
        formInstanceTestSteps.validateFormFieldMappingsByAgency();
        break;
        //      case "CUSTOMER":
        //        FormTemplateTestSteps.createAndMapCustomerForm();
        //        break;
        //      case "PERSONAL_AUTO_POLICY":
        //        FormTemplateTestSteps.createAndMapPersonalAutoPolicy();
        //        break;
      case "POLICY":
        formInstanceTestSteps.validateFormFieldMappingsByTitanPolicy();
        break;
      default:
        throw new IllegalArgumentException(
            "Canonical object type supplied is not supported yet...");
    }
  }
}
