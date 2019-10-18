package com.vertafore.test.form.runners;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    plugin = {"pretty", "html:target/cucumber-html-report"},
    features = "src/test/java/com/vertafore/test/form/features/form-service-integration.feature",
    glue = "src.test.java.com.vertafore.test")
public class TestAll {}
