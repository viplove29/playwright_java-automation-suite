package com.vertafore.test.customer.runners;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "test/customer/features", glue = "com.vertafore.test.customer")
public class TestAll {}
