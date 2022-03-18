package com.vertafore.test.tasks;

import static com.vertafore.test.abilities.HaveALoginKey.keyTypeForActor;
import static com.vertafore.test.util.EnvVariables.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

import com.vertafore.test.abilities.HaveALoginKey;
import com.vertafore.test.models.ems.AuthAppKeyPostRequest;
import com.vertafore.test.servicewrappers.UseAuthTo;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class GetALoginKey implements Performable {

  @Override
  public <T extends Actor> void performAs(T actor) {

    String keyType = keyTypeForActor(actor);
    String loginKey;
    String appKey;

    switch (keyType) {
      case "AADM":
        appKey = AADM_APP_KEY;
        break;
      case "VERT":
        appKey = VERT_APP_KEY;
        break;
      case "VADM":
        appKey = VADM_APP_KEY;
        break;
      case "AGNY":
        appKey = AGNY_APP_KEY;
        break;
      case "ORAN":
        appKey = ORAN_APP_KEY;
        break;
      default:
        throw new IllegalArgumentException("Invalid Key Type");
    }

    loginKey = callForLoginKey(actor, appKey);
    HaveALoginKey.theNewLoginKeyOf(actor, loginKey);
  }

  public static GetALoginKey forActor() {
    return instrumented(GetALoginKey.class);
  }

  private static String callForLoginKey(Actor actor, String appKey) {
    UseAuthTo authAPI = new UseAuthTo();

    AuthAppKeyPostRequest loginKeyRequest = new AuthAppKeyPostRequest();

    loginKeyRequest.setAppKey(appKey);

    actor.attemptsTo(authAPI.POSTAuthOnTheAuthenticationController(loginKeyRequest, "string"));

    return LastResponse.received().answeredBy(actor).getBody().jsonPath().getString("loginKey");
  }
}
