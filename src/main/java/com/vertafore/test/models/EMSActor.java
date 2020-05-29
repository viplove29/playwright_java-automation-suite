package com.vertafore.test.models;

import com.vertafore.test.abilities.HaveALoginKey;
import com.vertafore.test.abilities.HaveAnAccessToken;
import com.vertafore.test.tasks.GetALoginKey;
import com.vertafore.test.tasks.GetAUserToken;
import com.vertafore.test.tasks.GetAnAdminToken;
import com.vertafore.test.tasks.GetAnAppToken;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

public class EMSActor {
  private static final String BASE_URL = "https://botd-q-360iis-1.devop.vertafore.com/ems";

  public EMSActor(Actor actor, String context) {
    actor
        .can(HaveALoginKey.with(context))
        .can(HaveAnAccessToken.with())
        .can(CallAnApi.at(BASE_URL));

    actor.attemptsTo(GetALoginKey.forActor());

    switch (context) {
      case "appContext":
        actor.attemptsTo(GetAnAppToken.forActor());
        break;
      case "adminContext":
        actor.attemptsTo(GetAnAdminToken.forActor());
        break;
      default:
        actor.attemptsTo(GetAUserToken.forActor());
    }
  }
}
