package com.vertafore.test.customer.steps.teststeps;

import com.vertafore.test.common.util.ServiceUtils;
import java.io.IOException;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class CustomerTestSteps extends ScenarioSteps {

  // import step dependencies
  private ServiceUtils serviceUtils;

  public CustomerTestSteps() throws IOException {
    serviceUtils = new ServiceUtils();
  }

  @Step
  public void createCustomer() throws IOException {}

  @Step
  public void createTitanPolicyVersion() throws IOException {}
}
