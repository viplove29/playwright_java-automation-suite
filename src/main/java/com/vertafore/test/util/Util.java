package com.vertafore.test.util;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class Util {

  public static Faker faker = new Faker();

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

  public static void validateErrorResponseContainsString(String errorMessage, Actor actor) {
    HashMap<String, String> errorResponse =
        LastResponse.received().answeredBy(actor).getBody().jsonPath().getObject("", HashMap.class);
    assertThat(errorResponse.get("error")).contains(errorMessage);
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

  public static void printObjectAsJson(Object objectToPrint) {
    Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
    String json = gson.toJson(objectToPrint);
    System.out.println("\n" + json + "\n");
  }

  public static void printObjectAsJsonWithNulls(Object objectToPrint) {
    Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    String json = gson.toJson(objectToPrint);
    System.out.println("\n" + json + "\n");
  }

  public static Map<String, Object> convertObjectToMap(Object object) {
    Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    return gson.fromJson(gson.toJson(object), new TypeToken<Map<String, Object>>() {}.getType());
  }

  public static File createTestFile(String filename) {
    try {
      File myObj = new File(filename);
      if (myObj.createNewFile()) {
        System.out.println("File created: " + myObj.getName());
      } else {
        System.out.println("File already exists.");
      }

      return myObj;
    } catch (IOException e) {
      System.out.println("An error occurred while trying to create file: " + filename);
      e.printStackTrace();
    }

    return null;
  }

  public static Boolean isValidGUID(String guid) {
    Pattern UUID_REGEX_PATTERN =
        Pattern.compile("^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$");

    if (guid == null) {
      return false;
    }
    return UUID_REGEX_PATTERN.matcher(guid).matches();
  }

  public static String randomText(int length) {
    return faker.lorem().characters(length);
  }

  public static Double randomDollarAmount(double minimum, double maximum) {
    return faker.number().randomDouble(2, (long) minimum, (long) maximum);
  }
}
