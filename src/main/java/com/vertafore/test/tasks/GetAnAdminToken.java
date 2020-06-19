package com.vertafore.test.tasks;

import static com.vertafore.test.abilities.HaveALoginKey.theLoginKeyOf;
import static com.vertafore.test.abilities.HaveALoginKey.versionForActor;
import static com.vertafore.test.util.EnvVariables.ADMIN_EMP_CODE;
import static com.vertafore.test.util.EnvVariables.LOGIN_ADMIN_PATH;
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

public class GetAnAdminToken implements Performable {

  @Override
  public <T extends Actor> void performAs(T actor) {
    String loginKey = theLoginKeyOf(actor);
    String version = versionForActor(actor);

    HashMap<String, String> tokenBody = new HashMap<>();
    tokenBody.put("LoginKey", loginKey);
    tokenBody.put("Username", ADMIN_EMP_CODE(version));
    Post.to(LOGIN_ADMIN_PATH)
        .with(
            List.of(
                req -> req.body(new Gson().toJson(tokenBody)),
                req -> req.contentType(ContentType.JSON),
                req -> req.relaxedHTTPSValidation()))
        .performAs(actor);
    String accessJwt = SerenityRest.lastResponse().getBody().jsonPath().getString("accessJwt");

    HaveAnAccessToken.theNewAccessTokenOf(actor, accessJwt);
  }

  public static GetAnAdminToken forActor() {
    return instrumented(GetAnAdminToken.class);
  }
}
