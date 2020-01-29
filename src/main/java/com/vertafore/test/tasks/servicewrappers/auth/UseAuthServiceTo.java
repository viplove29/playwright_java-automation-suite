package com.vertafore.test.tasks.servicewrappers.auth;

import static com.vertafore.test.abilities.Authenticate.passwordForAuthenticatedActor;
import static com.vertafore.test.abilities.Authenticate.usernameForAuthenticatedActor;
import static com.vertafore.test.abilities.UseADomain.theDomainOf;
import static net.serenitybdd.rest.SerenityRest.rest;

import com.vertafore.test.abilities.HaveAccessToken;
import com.vertafore.test.models.auth.IDPUserV1;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.AnonymousPerformableFunction;
import net.serenitybdd.screenplay.Task;

public class UseAuthServiceTo {

  private static final String TOKEN_PATH = "auth/v1/token/";

  public static AnonymousPerformableFunction getToken() {
    return Task.where(
        "{0} uses auth service to get authenticate and get token",
        actor -> {

          // access token variable
          String accessToken;

          // setup our username/password body
          IDPUserV1 user = new IDPUserV1();
          user.setUsername(usernameForAuthenticatedActor(actor));
          user.setPassword(passwordForAuthenticatedActor(actor));

          // send request off
          rest()
              .with()
              .contentType(ContentType.JSON)
              .body(user)
              .post(theDomainOf(actor) + TOKEN_PATH);

          accessToken =
              SerenityRest.lastResponse().getBody().jsonPath().getString("content.accessToken");

          if (accessToken == null || accessToken.isEmpty()) {
            throw new IllegalArgumentException(
                "Could not log in with username: "
                    + user.getUsername()
                    + " and password: "
                    + user.getPassword());
          }

          actor.can(HaveAccessToken.of(accessToken));
        });
  }
}
