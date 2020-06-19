package com.vertafore.test.tasks;

import static com.vertafore.test.abilities.HaveALoginKey.theLoginKeyOf;
import static com.vertafore.test.abilities.HaveAnAccessToken.*;
import static com.vertafore.test.util.EnvVariables.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

import com.vertafore.test.abilities.HaveAnAccessToken;
import com.vertafore.test.interactions.Get;
import java.util.HashMap;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;

public class GetABasicToken implements Performable {

  @Override
  public <T extends Actor> void performAs(T actor) {

    String loginType = loginTypeForActor(actor);
    String username = usernameForActor(actor);
    String password = passwordForActor(actor);
    String loginKey = theLoginKeyOf(actor);

    if (loginType.isBlank() && username.isBlank() && password.isBlank()) {
      username = USERNAME;
      password = PASSWORD;
    } else if (loginType.equals("vsso") && username.isBlank() && password.isBlank()) {
      username = VSSO_USERNAME;
      password = VSSO_PASSWORD;
    }

    HashMap<String, String> queryParams = new HashMap<>();
    queryParams.put("loginGuid", loginKey);
    queryParams.put("username", username);
    queryParams.put("agencyNumber", AGENCY_NO);
    queryParams.put("password", password);

    Get.to(LOGIN_DEPRECATED_PATH)
        .with(List.of(req -> req.queryParams(queryParams)))
        .performAs(actor);
    String accessJwt = SerenityRest.lastResponse().getBody().jsonPath().getString("accessJwt");

    HaveAnAccessToken.theNewAccessTokenOf(actor, accessJwt);
  }

  public static GetABasicToken forActor() {
    return instrumented(GetABasicToken.class);
  }
}
