package com.vertafore.test.common.util.titanbase.steps.teststeps;

import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.common.util.titanbase.TitanRequestSpecBuilder;
import com.vertafore.test.common.util.titanbase.testdata.DataHandler;
import com.vertafore.test.common.util.titanbase.testdata.UserData;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import java.io.IOException;
import java.util.Map;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.assertj.core.api.Fail;

public class CommonServiceTestSteps extends ScenarioSteps {

  DataHandler dataHandler;

  @Step
  public void prepareUserTokenAndContext(
      String user, String service, String overrideBaseUri, String contextName) throws IOException {
    dataHandler = DataHandler.getInstance(overrideBaseUri);

    UserData userData = dataHandler.getUserByName(user);
    Serenity.getCurrentSession().put("currentUser", userData);
    Serenity.getCurrentSession().put("service", service);

    CaseInsensitiveMap<String, String> context;
    if (contextName == null) {
      // default context is 1st in context list
      context = userData.getAllContext().get(0);
    } else {
      context = userData.getFullContextByContextName(contextName);
    }
    Serenity.getCurrentSession().put("currentContext", context);

    dataHandler.handleUserAuth(user);
  }

  @Step
  public void setContextToEntity(String entityId) throws IOException {
    dataHandler = DataHandler.getInstance(null);
    UserData user = (UserData) Serenity.getCurrentSession().get("currentUser");
    Map<String, String> context = user.getFullContextByEntity(entityId);
    Serenity.getCurrentSession().put("currentContext", context);
    TitanRequestSpecBuilder builder = dataHandler.getBuilder();
  }

  @Step
  public void setContextToContextCommonName(String contextCommonName) throws IOException {
    dataHandler = DataHandler.getInstance(null);
    UserData user = (UserData) Serenity.getCurrentSession().get("currentUser");
    Map<String, String> context = user.getFullContextByContextName(contextCommonName);
    Serenity.getCurrentSession().put("currentContext", context);
    TitanRequestSpecBuilder builder = dataHandler.getBuilder();
  }

  @Then("^I get an error response message that contains '([^']*?)'$")
  public void error_response_message_should_contain(String message) {
    if (Serenity.hasASessionVariableCalled("response")) {
      Response response = Serenity.sessionVariableCalled("response");
      assertThat(response.body().path("error.message").toString().contains(message))
          .isTrue()
          .withFailMessage("Response does not contain expected message text!");
    } else {
      Fail.fail("No response object currently exists!");
    }
  }
}
