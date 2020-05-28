package com.vertafore.test.tasks;

import static com.vertafore.test.abilities.HaveALoginKey.theLoginKeyOf;
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

public class GetAnAppToken implements Performable {
  private static final String LOGIN_APP_PATH = "/auth/loginApp";
  private static final String AGENCY_NO = "MainQAC-1";
  private static final String APP_ACCESS_TO_AGENCY_KEY = "92C9E873-C2F0-4601-A6F5-906935BAB177";

  @Override
  public <T extends Actor> void performAs(T actor) {
    String loginKey = theLoginKeyOf(actor);

    HashMap<String, String> tokenBody = new HashMap<>();
    tokenBody.put("LoginKey", loginKey);
    tokenBody.put("AgencyNo", AGENCY_NO);
    tokenBody.put("AppAccessToAgencyKey", APP_ACCESS_TO_AGENCY_KEY);
    Post.to(LOGIN_APP_PATH)
        .with(
            List.of(
                req -> req.body(new Gson().toJson(tokenBody)),
                req -> req.contentType(ContentType.JSON),
                req -> req.relaxedHTTPSValidation()))
        .performAs(actor);
    String accessJwt = SerenityRest.lastResponse().getBody().jsonPath().getString("accessJwt");

    HaveAnAccessToken.theNewAccessTokenOf(actor, accessJwt);
  }

  public static GetAnAppToken forActor() {
    return instrumented(GetAnAppToken.class);
  }
}
