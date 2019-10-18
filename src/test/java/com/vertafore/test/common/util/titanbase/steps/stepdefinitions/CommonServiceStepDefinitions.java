package com.vertafore.test.common.util.titanbase.steps.stepdefinitions;

import com.vertafore.test.common.util.titanbase.steps.teststeps.CommonServiceTestSteps;
import cucumber.api.java.en.Given;
import java.io.IOException;
import net.thucydides.core.annotations.Steps;

public class CommonServiceStepDefinitions {

  @Steps CommonServiceTestSteps commonServiceTestSteps;

  @Given("^I act as '([^']*?)' using the '([^']*?)' service on the server located at '([^']*?)'$")
  public void i_act_as_user_on_the_service_service_located_at_url(
      String user, String service, String overrideUri) throws IOException {
    if (overrideUri.equalsIgnoreCase("default")) {
      overrideUri = null;
    }
    commonServiceTestSteps.prepareUserTokenAndContext(user, service, overrideUri, null);
  }

  @Given("^I act as '([^']*?)' using the '([^']*?)' service$")
  public void i_act_as_user_on_the_service_service(String user, String service) throws IOException {
    commonServiceTestSteps.prepareUserTokenAndContext(user, service, null, null);
  }

  @Given(
      "^I act as '([^']*?)' using the '([^']*?)' service on the server located at '([^']*?)' in the context '([^']*?)'$")
  public void i_act_as_user_on_the_service_service_located_at_url_in_the_context_contextname(
      String user, String service, String overrideUri, String contextName) throws IOException {
    if (overrideUri.equalsIgnoreCase("default")) {
      overrideUri = null;
    }
    commonServiceTestSteps.prepareUserTokenAndContext(user, service, overrideUri, contextName);
  }

  @Given("^I act as '([^']*?)' using the '([^']*?)' service in the context '([^']*?)'$")
  public void i_act_as_user_on_the_service_service_in_the_context_contextname(
      String user, String service, String contextName) throws IOException {
    commonServiceTestSteps.prepareUserTokenAndContext(user, service, null, contextName);
  }

  @Given("^I set the context to be for the entity '([^']*?)'$")
  public void i_set_the_context_to_be_for_the_entity_entityId(String entityId) throws IOException {
    commonServiceTestSteps.setContextToEntity(entityId);
  }

  @Given("^I set the context to be '([^']*?)'$")
  public void i_set_the_context_to_be_context_name(String contextCommonName) throws IOException {
    commonServiceTestSteps.setContextToContextCommonName(contextCommonName);
  }
}
