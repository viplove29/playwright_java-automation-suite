package com.vertafore.test.form.steps.teststeps;

import com.vertafore.test.common.servicewrappers.exposure.ExposureService;
import java.io.IOException;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class ExposureTestSteps extends ScenarioSteps {

  // import step dependencies
  private ExposureService exposureService;

  // empty constructor
  public ExposureTestSteps() throws IOException {
    exposureService = new ExposureService();
  }

  @Step
  public void createAndMapPersonalAutoPolicyForm() {}
}
