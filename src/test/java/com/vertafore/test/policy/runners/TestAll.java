package com.vertafore.test.policy.runners;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "test/policy/features", glue = "com.vertafore.test.policy")
public class TestAll {}
