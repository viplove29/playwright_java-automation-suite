package com.vertafore.test.task.steps.teststeps;

import com.vertafore.test.common.util.ServiceUtils;
import java.io.IOException;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class TaskTestSteps extends ScenarioSteps {

  // import step dependencies
  public ServiceUtils serviceUtils = new ServiceUtils();

  // empty constructor
  public TaskTestSteps() throws IOException {}
  // variables

  // methods
  @Step
  public void createTask() throws IOException {}
}
