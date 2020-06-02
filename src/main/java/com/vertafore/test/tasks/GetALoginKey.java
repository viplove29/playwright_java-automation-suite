package com.vertafore.test.tasks;

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

  private static final String LOGIN_KEY_PATH = "/auth";
  private static final String USER_APP_KEY = "AB4C2E22-2006-44AB-AA1A-318E653BA345";
  private static final String APP_APP_KEY = "109DF24F-6188-409E-B7FE-9BEA4A673812";
  private static final String ADMIN_APP_KEY = "3A0CE59E-C644-46A6-8697-002687388329";
  private static final String VERT_APP_KEY = "9ABE4D45-106D-49A9-9C6A-0CD685BCC337";

  @Override
  public <T extends Actor> void performAs(T actor) {

    String context = actor.getDescription();
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
