package com.vertafore.test.common.util.titanbase;

import com.vertafore.test.common.util.titanbase.json.JsonUtils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.io.IoBuilder;

/**
 * This class extends {@link io.restassured.builder.RequestSpecBuilder}, adding logic to handle the
 * Titan-specific need for service branch overrides. This attaches that data as cookies to the
 * builder for hands free use by the other project tests.
 */
public class TitanRequestSpecBuilder extends RequestSpecBuilder {

  /**
   * Constructor tries to find the environment variable "service.branches" and, if present, splits
   * up each override pair and inserts it as a cookie into the builder.
   */
  public TitanRequestSpecBuilder() {
    super();
    if (System.getProperty("service.branches") != null
        && !System.getProperty("service.branches").equals("")) {
      String[] overrides = System.getProperty("service.branches").split(";");
      if (Arrays.stream(overrides)
              .filter(override -> !override.contains("="))
              .collect(Collectors.toList())
              .size()
          > 0) {
        throw new IllegalArgumentException(
            "The service branches override values contain at least one invalid "
                + "entry, missing a '=' in the argument.");
      }

      String[] overrideSplit;
      for (String override : overrides) {
        overrideSplit = override.split("=");
        if (overrideSplit[0].length() < 1 || overrideSplit[1].length() < 1) {
          throw new IllegalArgumentException(
              "The service branches override values contain at least one "
                  + "illegal entry where either the key or the value is empty");
        }
      }
      for (String override : overrides) {
        addCookie(
            override.substring(0, override.indexOf('=')),
            override.substring(override.indexOf('=') + 1, override.length()));
      }
    }
    // Set up ISO 8601 date/time serialization/deserialization
    setConfig(
        new RestAssuredConfig()
            .objectMapperConfig(
                new ObjectMapperConfig()
                    .jackson2ObjectMapperFactory((cls, charset) -> JsonUtils.getObjectMapper())));

    Logger logger = LogManager.getLogger(TitanRequestSpecBuilder.class);
    PrintStream logStream = IoBuilder.forLogger(logger).buildPrintStream();
    RestAssuredConfig restAssuredConfig = new RestAssuredConfig();
    LogConfig logConfig = restAssuredConfig.getLogConfig();
    logConfig.defaultStream(logStream).enablePrettyPrinting(true);
    setConfig(restAssuredConfig);
    addFilter(ResponseLoggingFilter.logResponseTo(logStream));
    addFilter(RequestLoggingFilter.logRequestTo(logStream));
  }
}
