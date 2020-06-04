package com.vertafore.test.actor;

import com.vertafore.test.abilities.HaveALoginKey;
import com.vertafore.test.abilities.HaveAnAccessToken;
import com.vertafore.test.models.EMSActor;
import com.vertafore.test.tasks.GetALoginKey;
import com.vertafore.test.tasks.GetAUserToken;
import com.vertafore.test.tasks.GetAnAdminToken;
import com.vertafore.test.tasks.GetAnAppToken;
import java.util.List;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

public class BuildEMSCast {
  private static final String BASE_URL = "https://botd-q-360iis-1.devop.vertafore.com/ems";

  public static OnlineCast GetAnAccessToken(List<EMSActor> actorsData) {
    OnlineCast cast = new OnlineCast();

    for (EMSActor emsActor : actorsData) {
      String name = emsActor.getActorName();
      String context = emsActor.getContext();
      String loginType = emsActor.getLoginType();

      cast.actorNamed(
          name,
          HaveALoginKey.with(context),
          HaveAnAccessToken.with(loginType),
          CallAnApi.at(BASE_URL));

      cast.actorNamed(name).attemptsTo(GetALoginKey.forActor());

      switch (context) {
        case "appContext":
          cast.actorNamed(name).attemptsTo(GetAnAppToken.forActor());
          break;
        case "adminContext":
          cast.actorNamed(name).attemptsTo(GetAnAdminToken.forActor());
          break;
        default:
          cast.actorNamed(name).attemptsTo(GetAUserToken.forActor());
      }
    }

    return cast;
  }
}
