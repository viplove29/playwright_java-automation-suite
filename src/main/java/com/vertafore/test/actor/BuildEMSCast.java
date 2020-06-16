package com.vertafore.test.actor;

import static com.vertafore.test.util.EnvVariables.BASE_URL;

import com.vertafore.test.abilities.HaveALoginKey;
import com.vertafore.test.abilities.HaveAnAccessToken;
import com.vertafore.test.models.EMSActor;
import com.vertafore.test.tasks.GetALoginKey;
import com.vertafore.test.tasks.GetAUserToken;
import com.vertafore.test.tasks.GetAnAdminToken;
import com.vertafore.test.tasks.GetAnAppToken;
import java.util.List;
import java.util.Optional;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

public class BuildEMSCast {

  public static OnlineCast GetAnAccessToken(List<EMSActor> actorsData) {
    OnlineCast cast = new OnlineCast();

    for (EMSActor emsActor : actorsData) {
      String name = emsActor.getActorName();
      String context = emsActor.getContext();
      String loginType = emsActor.getLoginType();
      String username = emsActor.getUsername();
      String password = emsActor.getPassword();

      cast.actorNamed(
              name,
              HaveALoginKey.with(context),
              HaveAnAccessToken.with(loginType, username, password),
              CallAnApi.at(BASE_URL))
          .attemptsTo(GetALoginKey.forActor());

      Optional<Actor> madeActor =
          cast.getActors()
              .stream()
              .filter(actor -> actor.getName().equalsIgnoreCase(name))
              .findFirst();
      if (madeActor.isPresent()) {
        Actor actor = madeActor.get();
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

    return cast;
  }
}
