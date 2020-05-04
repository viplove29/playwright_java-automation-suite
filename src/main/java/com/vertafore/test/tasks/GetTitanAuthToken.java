package com.vertafore.test.tasks;

import static com.vertafore.test.abilities.Authenticate.passwordForAuthenticatedActor;
import static com.vertafore.test.abilities.Authenticate.usernameForAuthenticatedActor;
import static net.serenitybdd.screenplay.Tasks.instrumented;

import com.vertafore.test.abilities.Authenticate;
import com.vertafore.test.models.auth.IDPUserV1;
import io.restassured.http.ContentType;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.rest.interactions.Post;

/**
 * This class is deliberately built separate from UseAuthServiceTo we need an accessToken to access
 * any of the code-generated classes
 */
public class GetTitanAuthToken implements Performable {
  private static final String TOKEN_PATH = "/auth/v1/token/";

  @Override
  public <T extends Actor> void performAs(T actor) {
    IDPUserV1 user = new IDPUserV1();
    user.setUsername(usernameForAuthenticatedActor(actor));
    user.setPassword(passwordForAuthenticatedActor(actor));

    Post.to(TOKEN_PATH)
        .with(List.of(req -> req.body(user), req -> req.contentType(ContentType.JSON)))
        .performAs(actor);

    String accessToken =
        SerenityRest.lastResponse().getBody().jsonPath().getString("content.accessToken");

    if (accessToken == null || accessToken.isEmpty()) {
      throw new RuntimeException(
          "Could not log in with username: "
              + user.getUsername()
              + " and password: "
              + user.getPassword());
    }

    Authenticate.theNewAuthTokenOf(actor, accessToken);
  }

  public static GetTitanAuthToken forActor() {
    return instrumented(GetTitanAuthToken.class);
  }
}
