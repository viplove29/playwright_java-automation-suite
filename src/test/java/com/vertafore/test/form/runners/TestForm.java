package com.vertafore.test.form.runners;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    plugin = {"pretty", "html:target/cucumber-html-report"},
    features = "src/test/resources/features",
    glue = "com.vertafore.test",
    tags = "@FormService")
public class TestForm {}
