package com.vertafore.test.utilities.actorextractor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.vertafore.core.util.JsonHelper;
import com.vertafore.test.abilities.Authenticate;
import com.vertafore.test.abilities.CallTitanApi;
import com.vertafore.test.abilities.HaveEntityId;
import com.vertafore.test.abilities.HaveProductId;
import com.vertafore.test.abilities.HaveTenantId;
import com.vertafore.test.abilities.UseADomain;
import com.vertafore.test.models.TitanUser;
import com.vertafore.test.tasks.servicewrappers.auth.UseAuthServiceTo;
import java.util.List;
import net.serenitybdd.screenplay.actors.OnlineCast;

public class JsonToActorsConverter {

  private static final String PROJECT_ID = "1495"; // localdev project id
  private static final String PATH_TO_USERS = "svi/users.json"; // Path to user json file
  private static final String DEFAULT_PASSWORD = "Password1!";
  private static final String DEFAULT_PRODUCT_ID = "AMS-WEB-UI";
  private static final String DEFAULT_BASE_URL = "https://api.dev.titan.v4af.com/";

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class JsonTenants {
    public String tenantId;
    public String entityName;
    public List<User> users;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class User {
    public String displayName;
    public String username;
    public String password;
  }

  public static OnlineCast castOfAuthenticatedActors(List<TitanUser> actorsData) {
    String json = ExtractAll.titanUsers(PROJECT_ID, PATH_TO_USERS);

    List<JsonTenants> jsonUsers = JsonHelper.deserializeJsonAsList(json, new TypeReference<>() {});

    OnlineCast cast = new OnlineCast();

    for (TitanUser singleActor : actorsData) {
      String entityName = singleActor.getEntityName();
      String tenantId = singleActor.getTenantId();
      String displayName = singleActor.getDisplayName();

      User foundUser =
          jsonUsers
              .stream()
              .filter(t -> t.tenantId.equals(tenantId) && t.entityName.equals(entityName))
              .findFirst()
              .orElseThrow(
                  () ->
                      new IllegalArgumentException(
                          "could not find any users for tenant id: "
                              + tenantId
                              + " and/or entity name: "
                              + entityName))
              .users
              .stream()
              .filter(u -> u.displayName.equals(displayName))
              .findFirst()
              .orElseThrow(
                  () ->
                      new IllegalArgumentException(
                          "could not find user with display name: " + displayName));

      cast.actorNamed(
              displayName,
              Authenticate.with(foundUser.username, foundUser.password),
              HaveTenantId.of(tenantId),
              HaveEntityId.of(tenantId),
              HaveProductId.of(DEFAULT_PRODUCT_ID),
              UseADomain.of(DEFAULT_BASE_URL), // for base url
              CallTitanApi.atBaseUrl(DEFAULT_BASE_URL)) // for service/tenant/entity/product config
          .attemptsTo(UseAuthServiceTo.getToken());
    }
    return cast;
  }
}
