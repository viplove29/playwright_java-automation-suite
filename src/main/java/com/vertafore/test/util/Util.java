package com.vertafore.test.util;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import java.io.File;
import java.util.Objects;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.ensure.Ensure;

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
}
