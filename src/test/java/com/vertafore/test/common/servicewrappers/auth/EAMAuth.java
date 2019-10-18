package com.vertafore.test.common.servicewrappers.auth;

import com.vertafore.autotest.serenitybase.rest.requestcalls.BaseCalls;
import com.vertafore.test.common.models.services.auth.AuthZResponseDTO;
import com.vertafore.test.common.models.services.auth.IDPUserV1;
import com.vertafore.test.common.util.titanbase.TitanRequestSpecBuilder;
import com.vertafore.test.common.util.titanbase.testdata.DataHandler;
import java.io.IOException;
import java.time.Instant;

/**
 * EAMAuth is used to perform actions involving tokens, such as getting a new token or refreshing an
 * existing one. It also retrieves the Instant of the expire time for a token.
 *
 * @author AJ Johnson
 * @version 1.0.4
 */
public class EAMAuth {
  private TitanRequestSpecBuilder builder;
  private AuthZResponseDTO authZResponseDTO;
  private DataHandler dataHandler;

  /** Constructor strictly for instantiating as it is empty. */
  public EAMAuth() throws IOException {
    dataHandler = DataHandler.getInstance(null);
  }

  /**
   * Performs the action of logging in, setting the {@link AuthZResponseDTO} class property to
   * contain the response information. That property is what will be accessed to get the information
   * from the call.
   *
   * @param username User's username value for the call to get a new token
   * @param password User's password value for the call to get a new token
   */
  public void performLogin(String username, String password, String baseUri) {
    IDPUserV1 requestDTO = new IDPUserV1();
    requestDTO.setUsername(username);
    requestDTO.setPassword(password);
    builder = dataHandler.getCleanBuilder();
    builder.setBody(requestDTO);
    builder.addHeader("Content-Type", "application/json");
    builder.setBaseUri(baseUri);

    authZResponseDTO =
        BaseCalls.makePostUserAuthCall(builder, "/auth/v1/token")
            .getBody()
            .as(AuthZResponseDTO.class);
  }

  /**
   * Performs the action of refreshing an already existing token. Sets the response in the {@link
   * AuthZResponseDTO} class property for access.
   *
   * @param token The refresh token value to be placed in the body of the call.
   */
  public void performRefresh(String token, String baseUri) {
    builder = dataHandler.getCleanBuilder();
    builder.setBody(token);
    builder.setBaseUri(baseUri);
    builder.addHeader("Content-Type", "application/json");
    authZResponseDTO =
        BaseCalls.makePostUserAuthCall(builder, "/auth/v1/token/refresh")
            .getBody()
            .as(AuthZResponseDTO.class);
  }

  /**
   * Returns the current auth token, presumably retrieved in a recent call to create said token.
   *
   * @return Most recently acquired auth token
   */
  public String getAuthToken() {
    try {
      return authZResponseDTO.getContent().getAccessToken();
    } catch (NullPointerException npe) {
      return null;
    }
  }

  /**
   * Returns the current refresh token, presumably retrieve in a recent call to create an auth
   * token.
   *
   * @return Most recently acquired refresh token
   */
  public String getRefreshToken() {
    try {
      return authZResponseDTO.getContent().getRefreshToken();
    } catch (NullPointerException npe) {
      return null;
    }
  }

  /**
   * Returns the time of the expiration of a token when called immediately after the token was
   * retrieved or refreshed. It will be incorrect if it is called at any other time and even when
   * called correctly, it will be off by as much as a second.
   *
   * @return Time that the token will expire
   * @see Instant
   */
  public Instant getExpiresOn() {
    Instant time = Instant.now();
    time = time.plusSeconds(authZResponseDTO.getContent().getExpiresIn());
    return time;
  }
}
