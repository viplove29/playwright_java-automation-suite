package com.vertafore.test.tasks;

import static com.vertafore.test.abilities.HaveALoginKey.contextForActor;
import static net.serenitybdd.screenplay.Tasks.instrumented;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;

public class GetAnAccessToken implements Performable {

  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(GetALoginKey.forActor());
    String context = contextForActor(actor);

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

  public static GetAnAccessToken forActor() {
    return instrumented(GetAnAccessToken.class);
  }
}
