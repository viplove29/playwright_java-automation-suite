package com.vertafore.test.tasks;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import com.google.gson.Gson;
import com.vertafore.test.abilities.Authenticate;
import io.restassured.http.ContentType;
import java.util.HashMap;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class GetEMSAuthToken implements Performable {
  private static final String LOGIN_KEY_PATH = "/auth";
  private static final String USER_APP_KEY = "AB4C2E22-2006-44AB-AA1A-318E653BA345";
  private static final String LOGIN_USER_PATH = "/auth/loginUser";

  @Override
  public <T extends Actor> void performAs(T actor) {

    HashMap<String, String> appKey = new HashMap<>();
    appKey.put("AppKey", USER_APP_KEY);
    Post.to(LOGIN_KEY_PATH)
        .with(
            List.of(
                req -> req.body(new Gson().toJson(appKey)),
                req -> req.contentType(ContentType.JSON),
                req -> req.relaxedHTTPSValidation()))
        .performAs(actor);
    String loginKey = SerenityRest.lastResponse().getBody().jsonPath().getString("loginKey");

    HashMap<String, String> loginUserBody = new HashMap<>();
    loginUserBody.put("LoginKey", loginKey);
    loginUserBody.put("AgencyNo", "MainQAC-1");
    loginUserBody.put("Username", "EMSAuto");
    loginUserBody.put("Password", "Password2!");
    Post.to(LOGIN_USER_PATH)
        .with(
            List.of(
                req -> req.body(new Gson().toJson(loginUserBody)),
                req -> req.contentType(ContentType.JSON),
                req -> req.relaxedHTTPSValidation()))
        .performAs(actor);
    String accessJwt = SerenityRest.lastResponse().getBody().jsonPath().getString("accessJwt");

    Authenticate.theNewAuthTokenOf(actor, accessJwt);
  }

  public static GetEMSAuthToken forActor() {
    return instrumented(GetEMSAuthToken.class);
  }
}
