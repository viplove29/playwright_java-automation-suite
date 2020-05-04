package com.vertafore.test.actor.titan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.vertafore.core.util.JsonHelper;
import com.vertafore.test.abilities.Authenticate;
import com.vertafore.test.abilities.HaveTitanContext;
import com.vertafore.test.models.TitanUser;
import com.vertafore.test.tasks.GetTitanAuthToken;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

public class BuildCastOfTitanUsers {

  private static final String DATA_PROJECT_ID = "1495"; // data project id
  private static final String PATH_TO_USERS = "svi/users.json"; // Path to user json file
  private static final String DEFAULT_PASSWORD = "Password1!";
  private static final String DEFAULT_PRODUCT_ID = "AMS-WEB-UI";
  private static final String DEFAULT_BASE_URL = "https://api.dev.titan.v4af.com";

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class JsonTenants {
    public String tenantId;
    public String entityName;
    public List<User> users;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class User {
    public String username;
    public String password;
  }

  // builds cast using static password
  // if it fails then we call out to local-dev
  public static OnlineCast loadAndAuthenticate(List<TitanUser> actorsData) {
    OnlineCast cast = new OnlineCast();
    boolean authFailure = false;

    for (TitanUser singleActor : actorsData) {
      String entityName = singleActor.getEntityName();
      String tenantName = singleActor.getTenantName();
      String userName = singleActor.getUserName();

      String domain =
          System.getProperty("baseUrl") != null ? System.getProperty("baseUrl") : DEFAULT_BASE_URL;

      cast.actorNamed(
          userName,
          Authenticate.with(userName, DEFAULT_PASSWORD),
          HaveTitanContext.of(DEFAULT_PRODUCT_ID, tenantName, entityName),
          CallAnApi.at(domain));
      cast.getActors().forEach(actor -> actor.attemptsTo(GetTitanAuthToken.forActor()));

      if (SerenityRest.lastResponse().getStatusCode() != 200) {
        authFailure = true;
      }
    }

    // if a user was not authenticated then we reach out to local-dev
    if (cast.getActors().size() != actorsData.size() || authFailure) {
      cast = buildAuthenticatedCastFromLocalDev(actorsData);
    }
    return cast;
  }

  private static OnlineCast buildAuthenticatedCastFromLocalDev(List<TitanUser> actorsData) {
    String json = ExtractAll.titanUsers(DATA_PROJECT_ID, PATH_TO_USERS);

    List<JsonTenants> jsonUsers = JsonHelper.deserializeJsonAsList(json, new TypeReference<>() {});

    OnlineCast cast = new OnlineCast();

    for (TitanUser singleActor : actorsData) {
      String entityName = singleActor.getEntityName();
      String tenantName = singleActor.getTenantName();
      String userName = singleActor.getUserName();

      User foundUser =
          jsonUsers
              .stream()
              .filter(t -> t.tenantId.equals(tenantName) && t.entityName.equals(entityName))
              .findFirst()
              .orElseThrow(
                  () ->
                      new IllegalArgumentException(
                          "could not find any users for tenant id: "
                              + tenantName
                              + " and/or entity name: "
                              + entityName))
              .users
              .stream()
              .filter(u -> u.username.equals(userName))
              .findFirst()
              .orElseThrow(
                  () ->
                      new IllegalArgumentException(
                          "could not find user with username: " + userName));

      // handle different domains being passed in at run-time via system properties, default to dev
      // master.
      String domain =
          System.getProperty("baseUrl") != null ? System.getProperty("baseUrl") : DEFAULT_BASE_URL;

      cast.actorNamed(
          userName,
          Authenticate.with(foundUser.username, foundUser.password),
          HaveTitanContext.of(DEFAULT_PRODUCT_ID, tenantName, entityName),
          CallAnApi.at(domain));
      cast.getActors().forEach(actor -> actor.attemptsTo(GetTitanAuthToken.forActor()));
    }
    return cast;
  }
}
