package com.vertafore.test.tasks;

import static com.vertafore.test.abilities.HaveALoginKey.contextForActor;
import static com.vertafore.test.util.EnvVariables.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

import com.google.gson.Gson;
import com.vertafore.test.abilities.HaveALoginKey;
import io.restassured.http.ContentType;
import java.util.HashMap;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class GetALoginKey implements Performable {

  @Override
  public <T extends Actor> void performAs(T actor) {

    String context = contextForActor(actor);
    String appKey;

    switch (context) {
      case "userContext":
        appKey = USER_APP_KEY;
        break;
      case "appContext":
        appKey = APP_APP_KEY;
        break;
      case "adminContext":
        appKey = ADMIN_APP_KEY;
        break;
      default:
        appKey = VERT_APP_KEY;
    }

    HashMap<String, String> authBody = new HashMap<>();
    authBody.put("AppKey", appKey);
    Post.to(LOGIN_KEY_PATH)
        .with(
            List.of(
                req -> req.body(new Gson().toJson(authBody)),
                req -> req.contentType(ContentType.JSON),
                req -> req.relaxedHTTPSValidation()))
        .performAs(actor);
    String loginKey = SerenityRest.lastResponse().getBody().jsonPath().getString("loginKey");

    HaveALoginKey.theNewLoginKeyOf(actor, loginKey);
  }

  public static GetALoginKey forActor() {
    return instrumented(GetALoginKey.class);
  }
}
