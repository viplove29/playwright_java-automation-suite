package com.vertafore.test.actor.ems;

import com.vertafore.test.abilities.HaveALoginKey;
import com.vertafore.test.abilities.HaveAnAccessToken;
import com.vertafore.test.models.EMSActor;
import com.vertafore.test.tasks.GetALoginKey;
import com.vertafore.test.tasks.GetAUserToken;
import com.vertafore.test.tasks.GetAnAdminToken;
import com.vertafore.test.tasks.GetAnAppToken;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

public class AuthorizeEMSActor {

  private static final String BASE_URL = "https://botd-q-360iis-1.devop.vertafore.com/ems";

  public static OnlineCast GetAnAccessToken(EMSActor currentActor) {
    OnlineCast cast = new OnlineCast();

    String actorName = currentActor.getActorName();
    String context = currentActor.getContext();

    cast.actorNamed(
        actorName, HaveALoginKey.with(context), HaveAnAccessToken.with(), CallAnApi.at(BASE_URL));

    cast.actorNamed(actorName).attemptsTo(GetALoginKey.forActor());

    switch (context) {
      case "appContext":
        cast.actorNamed(actorName).attemptsTo(GetAnAppToken.forActor());
        break;
      case "adminContext":
        cast.actorNamed(actorName).attemptsTo(GetAnAdminToken.forActor());
        break;
      default:
        cast.actorNamed(actorName).attemptsTo(GetAUserToken.forActor());
    }
    return cast;
  }
}
