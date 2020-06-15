package com.vertafore.test.tasks;

import static com.vertafore.test.abilities.HaveALoginKey.theLoginKeyOf;
import static com.vertafore.test.abilities.HaveAnAccessToken.loginTypeForActor;
import static com.vertafore.test.util.Env_var.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

import com.google.gson.Gson;
import com.vertafore.test.abilities.HaveAnAccessToken;
import io.restassured.http.ContentType;
import java.util.HashMap;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class GetAUserToken implements Performable {
  //  private static final String LOGIN_USER_PATH = "/auth/loginUser";
  //  private static final String AGENCY_NO = "MainQAC-1";
  //  private static final String USERNAME = "EMSAuto";
  //  private static final String PASSWORD = "Password2!";
  //  private static final String VSSO_USERNAME = "EMSAuto2@mailinator.com";
  //  private static final String VSSO_PASSWORD = "Password5!";

  @Override
  public <T extends Actor> void performAs(T actor) {

    String loginType = loginTypeForActor(actor);
    String loginKey = theLoginKeyOf(actor);
    String username;
    String password;

    if (loginType.equals("Native")) {
      username = USERNAME;
      password = PASSWORD;
    } else {
      username = VSSO_USERNAME;
      password = VSSO_PASSWORD;
    }

    HashMap<String, String> tokenBody = new HashMap<>();
    tokenBody.put("LoginKey", loginKey);
    tokenBody.put("AgencyNo", AGENCY_NO);
    tokenBody.put("Username", username);
    tokenBody.put("Password", password);
    Post.to(LOGIN_USER_PATH)
        .with(
            List.of(
                req -> req.body(new Gson().toJson(tokenBody)),
                req -> req.contentType(ContentType.JSON),
                req -> req.relaxedHTTPSValidation()))
        .performAs(actor);
    String accessJwt = SerenityRest.lastResponse().getBody().jsonPath().getString("accessJwt");

    HaveAnAccessToken.theNewAccessTokenOf(actor, accessJwt);
  }

  public static GetAUserToken forActor() {
    return instrumented(GetAUserToken.class);
  }
}
