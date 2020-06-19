package com.vertafore.test.tasks;

import static com.vertafore.test.abilities.HaveALoginKey.theLoginKeyOf;
import static com.vertafore.test.abilities.HaveALoginKey.versionForActor;
import static com.vertafore.test.abilities.HaveAnAccessToken.*;
import static com.vertafore.test.util.EnvVariables.*;
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

  @Override
  public <T extends Actor> void performAs(T actor) {

    String loginType = loginTypeForActor(actor);
    String username = usernameForActor(actor);
    String password = passwordForActor(actor);
    String loginKey = theLoginKeyOf(actor);
    String version = versionForActor(actor);

    if (loginType.isBlank() && username.isBlank() && password.isBlank()) {
      username = USERNAME(version);
      password = PASSWORD(version);
    } else if (loginType.equals("vsso") && username.isBlank() && password.isBlank()) {
      username = VSSO_USERNAME(version);
      password = VSSO_PASSWORD(version);
    }

    HashMap<String, String> tokenBody = new HashMap<>();
    tokenBody.put("LoginKey", loginKey);
    tokenBody.put("AgencyNo", AGENCY_NO(version));
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
