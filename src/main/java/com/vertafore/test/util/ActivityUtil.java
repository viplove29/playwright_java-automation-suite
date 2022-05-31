package com.vertafore.test.util;

import com.vertafore.test.models.ems.ActionResponse;
import com.vertafore.test.servicewrappers.UseActivityTo;
import java.util.List;
import java.util.Random;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class ActivityUtil {
  private static UseActivityTo activityApi = new UseActivityTo();

  public static String getRandomActivityAction(Actor actor) {
    actor.attemptsTo(activityApi.GETActivityActionsOnTheActivitiesController());
    List<ActionResponse> actions =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getList("", ActionResponse.class);

    if (actions == null || actions.size() == 0) {
      return null;
    }

    int randomIndex = new Random().nextInt(actions.size());
    return actions.get(randomIndex).getActionName();
  }
}
