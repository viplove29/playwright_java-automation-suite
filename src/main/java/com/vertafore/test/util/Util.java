package com.vertafore.test.util;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class Util {
  public File getFileByFileName(String fileName, String fileExtension) {

    return new File(
        Objects.requireNonNull(
                getClass().getClassLoader().getResource("files/" + fileName + fileExtension))
            .getFile());
  }

  public static void checkStatusForSuccess() {
    int statusCode = SerenityRest.lastResponse().statusCode();
    theActorInTheSpotlight().attemptsTo(Ensure.that(statusCode).isBetween(200, 299));
  }

  public static void validateErrorResponse(String errorMessage, Actor actor) {
    HashMap<String, String> errorResponse =
        LastResponse.received().answeredBy(actor).getBody().jsonPath().getObject("", HashMap.class);
    assertThat(errorResponse.get("error")).isEqualTo(errorMessage);
  }
}
