package com.vertafore.test.actor.ems;

import com.vertafore.test.abilities.HaveALoginKey;
import com.vertafore.test.abilities.HaveAnAccessToken;
import com.vertafore.test.tasks.GetALoginKey;
import com.vertafore.test.tasks.GetAUserToken;
import com.vertafore.test.tasks.GetAnAdminToken;
import com.vertafore.test.tasks.GetAnAppToken;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

public class AuthorizeEMSActor {
  private static final String BASE_URL = "https://botd-q-360iis-1.devop.vertafore.com/ems";

  public AuthorizeEMSActor(Actor emsActor, String context) {
    emsActor
        .can(HaveALoginKey.with(context))
        .can(HaveAnAccessToken.with())
        .can(CallAnApi.at(BASE_URL));

    emsActor.attemptsTo(GetALoginKey.forActor());

    switch (context) {
      case "appContext":
        emsActor.attemptsTo(GetAnAppToken.forActor());
        break;
      case "adminContext":
        emsActor.attemptsTo(GetAnAdminToken.forActor());
        break;
      default:
        emsActor.attemptsTo(GetAUserToken.forActor());
    }
  }
}
