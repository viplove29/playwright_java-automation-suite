package com.vertafore.test.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.javafaker.Faker;
import com.vertafore.test.models.ems.ViewOptionsResponse;
import com.vertafore.test.servicewrappers.UseViewOptionsTo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Random;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class ViewOptionsUtil {

  public static Object getCurrentViewOptions(Actor actor, String ownerType, String settingType) {
    UseViewOptionsTo viewOptionsApi = new UseViewOptionsTo();
    actor.attemptsTo(
        viewOptionsApi.GETViewOptionsOnTheViewoptionsController(ownerType, settingType, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ViewOptionsResponse response =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", ViewOptionsResponse.class);
    assertThat(response).isNotNull();

    return response.getOptions();
  }

  public static Map<String, Object> getCurrentViewOptionsAsMap(
      Actor actor, String ownerType, String settingType) {
    return Util.convertObjectToMap(getCurrentViewOptions(actor, ownerType, settingType));
  }

  public static void randomizeViewOptionsValues(Map<String, Object> viewOptions) {
    Faker faker = new Faker();
    Random random = new Random();
    for (Map.Entry<String, Object> entry : viewOptions.entrySet()) {
      // if a value is null, there is no way to know what data type is expected so leave it as null
      if (entry.getValue() != null) {
        switch (entry.getValue().getClass().getSimpleName()) {
          case "Boolean":
            entry.setValue(random.nextBoolean());
            break;
          case "Integer":
            entry.setValue(random.nextInt(100));
            break;
          case "Float":
            entry.setValue(random.nextFloat() * 100);
            break;
          case "Long":
            entry.setValue(random.nextLong() * 100L);
            break;
          case "Double":
            entry.setValue(random.nextDouble() * 100);
            break;
          case "String":
            if (entry.getKey().toLowerCase().contains("date")) {
              if (random.nextBoolean()) {
                String randomDateFormatted =
                    LocalDateTime.now()
                        .plusDays(new Random().nextInt(30))
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                        .replace(" ", "T");
                entry.setValue(randomDateFormatted);
              } else {
                entry.setValue(null);
              }
            } else {
              entry.setValue(faker.funnyName().name());
            }
            break;
        }
      }
    }
  }
}
