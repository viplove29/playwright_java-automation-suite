package com.vertafore.test.actor;

import static com.vertafore.test.util.EnvVariables.BASE_URL;

import com.vertafore.test.abilities.HaveALoginKey;
import com.vertafore.test.abilities.HaveAnAccessToken;
import com.vertafore.test.models.EMSActor;
import com.vertafore.test.tasks.*;
import java.util.List;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

public class BuildEMSCast {

  public static OnlineCast GetAccessTokens(List<EMSActor> actorsData) {
    OnlineCast cast = new OnlineCast();

    for (EMSActor emsActor : actorsData) {
      String name = emsActor.getActorName();
      String keyType = emsActor.getKeyType();
      String loginType = emsActor.getLoginType();
      String username = emsActor.getUsername();
      String password = emsActor.getPassword();
      String loginPath = emsActor.getLoginPath();

      cast.actorNamed(
              name,
              HaveALoginKey.with(keyType),
              HaveAnAccessToken.with(loginType, username, password, loginPath),
              CallAnApi.at(BASE_URL))
          .attemptsTo(GetAToken.forActor());
    }

    return cast;
  }
}
