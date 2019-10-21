package com.vertafore.test.form.runners;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = "src/test/java/com/vertafore/test/form/features",
    glue = "com.vertafore.test")
public class TestForm {}
