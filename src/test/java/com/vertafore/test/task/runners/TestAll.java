package com.vertafore.test.task.runners;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "test/task/features", glue = "com.vertafore.test.task")
public class TestAll {}
