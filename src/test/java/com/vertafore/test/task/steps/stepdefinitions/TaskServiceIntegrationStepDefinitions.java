package com.vertafore.test.task.steps.stepdefinitions;

import com.vertafore.test.customer.steps.teststeps.CustomerTestSteps;
import cucumber.api.java.en.Given;
import net.thucydides.core.annotations.Steps;

public class TaskServiceIntegrationStepDefinitions {

  // import other test step dependencies
  @Steps CustomerTestSteps customerTestSteps;

  // this does all the work needed in form service
  @Given("^test step definition here'([^']*?)'$")
  public void stepDefinition(String parameter) {}
}
