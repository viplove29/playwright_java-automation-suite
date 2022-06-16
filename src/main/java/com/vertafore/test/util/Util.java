package com.vertafore.test.util;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.assertj.core.api.Assertions.assertThat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import java.io.*;
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

  // method to insert a null field to be serialized at the HIGHEST level of the model
  // e.g. any variable that isn't contained in a subclass/model
  public static String addNullFieldToModel(Object model, String fieldName) {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JsonElement jsonElement = gson.toJsonTree(model);
    jsonElement.getAsJsonObject().add(fieldName, null);
    Gson gsonwithnulls = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    return gsonwithnulls.toJson(jsonElement);
  }

  // method to insert a null field to be serialized at the submodel level
  // e.g. any variable that is contained in a subclass/model
  public static String addNullFieldToSubModel(
      Object parentModel, String subModel, String fieldName) {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JsonElement jsonElement = gson.toJsonTree(parentModel);
    jsonElement.getAsJsonObject().getAsJsonObject(subModel).add(fieldName, null);
    Gson gsonwithnulls = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    return gsonwithnulls.toJson(jsonElement);
  }

  public static void printLastSentRequest(Object request) {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(request);
    System.out.println("\n" + json + "\n");
  }
}
