package com.vertafore.test.tasks;

import static com.vertafore.test.abilities.HaveALoginKey.contextForActor;
import static com.vertafore.test.databases.SiteDB.closeDB;
import static com.vertafore.test.databases.SiteDB.getAppKey;
import static com.vertafore.test.util.EnvVariables.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

import com.google.gson.Gson;
import com.vertafore.test.abilities.HaveALoginKey;
import com.vertafore.test.interactions.Get;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.util.HashMap;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class GetALoginKey implements Performable {

  @Override
  public <T extends Actor> void performAs(T actor) {

    String context = contextForActor(actor);
    String appKey;
    String loginKey;

    switch (context) {
      case "userContext":
        appKey = getAppKey("AADM", USER_APP_KEY);
        loginKey = makePOSTAuthCall(actor, appKey);
        closeDB();
        break;
      case "appContext":
        // appKey = getAppKey("ORAN", APP_APP_KEY);
        loginKey = makePOSTAuthCall(actor, APP_APP_KEY);
        break;
      case "adminContext":
        appKey = getAppKey("VADM", ADMIN_APP_KEY);
        loginKey = makePOSTAuthCall(actor, appKey);
        closeDB();
        break;
      default:
        appKey = getAppKey("VERT", VERT_APP_KEY);
        loginKey = makeGETAuthCall(actor, appKey);
        closeDB();
    }
    HaveALoginKey.theNewLoginKeyOf(actor, loginKey);
  }

  public static GetALoginKey forActor() {
    return instrumented(GetALoginKey.class);
  }

  private static String makePOSTAuthCall(Actor actor, String appKey) {
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

    return loginKey;
  }

  private static String makeGETAuthCall(Actor actor, String appKey) {

    RestAssured.useRelaxedHTTPSValidation();

    Get.to(LOGIN_KEY_PATH).with(req -> req.queryParam("secretKey", appKey)).performAs(actor);

    String loginKey = LastResponse.received().answeredBy(actor).getBody().asString();

    return loginKey;
  }
}
