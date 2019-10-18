package com.vertafore.test.common.util.titanbase.testdata;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.vertafore.test.common.servicewrappers.auth.EAMAuth;
import com.vertafore.test.common.util.titanbase.TitanRequestSpecBuilder;
import com.vertafore.test.common.util.titanbase.dataimport.TestDataDTO;
import com.vertafore.test.common.util.titanbase.dataimport.TestUserDTO;
import com.vertafore.test.common.util.titanbase.dataimport.UserContextDTO;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import net.serenitybdd.core.Serenity;
import org.apache.commons.collections4.map.CaseInsensitiveMap;

/**
 * DataHandler is a Singleton pattern class that deals with managing things like test data, the
 * {@link com.vertafore.autotest.titanbase.TitanRequestSpecBuilder} object and can also deal with
 * retrieving or refreshing tokens through calls to {@link
 * com.vertafore.autotest.titanbase.auth.EAMAuth}.
 */
public class DataHandler {

  private static DataHandler INSTANCE;
  private Map<String, UserData> userData;
  private TestDataDTO testDataDTO;
  private static TitanRequestSpecBuilder builder;
  private String baseServiceUri;
  private String baseUiUri;
  private final String BASE_PATH = "/{service}/v1/{productId}/{tenantId}/entities/{entityId}";
  private String baseServiceUriOverride = null;
  private String baseUiUriOverride = null;

  /**
   * Constructor that gathers all of the test data, as well as the {@link TitanRequestSpecBuilder}
   * and stores them for access by later testing logic.
   *
   * @param baseServiceUriOverride Uri passed by a step that will force a new baseServiceUri value
   *     or null
   * @throws IOException Exception thrown by the Jackson's (@link ObjectMapper) if the attempt to
   *     read the source fails
   */
  private DataHandler(String baseServiceUriOverride) throws IOException {
    this.baseServiceUriOverride = baseServiceUriOverride;
    userData = new CaseInsensitiveMap<>();

    String baseDir = System.getProperty("user.dir");
    File yaml = new File(baseDir + "/src/test/resources/testData.yaml");

    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

    testDataDTO = null;

    try {
      testDataDTO = mapper.readValue(yaml, TestDataDTO.class);
    } catch (IOException e) {
      throw new IOException("YAML file was unable to be converted by Jackson", e);
    }

    UserData tempUser = new UserData();
    for (TestUserDTO testUserDTO : testDataDTO.getUsers()) {
      tempUser.setUsername(testUserDTO.getUsername());
      tempUser.setPassword(testUserDTO.getPassword());
      tempUser.setDisplayName(testUserDTO.getDisplayName());
      tempUser.setUserId(testUserDTO.getUserId());
      for (UserContextDTO context : testUserDTO.getContext()) {
        tempUser.addContext(
            context.getName(),
            context.getProductId(),
            context.getTenantId(),
            context.getEntityId());
      }

      userData.put(testUserDTO.getDisplayName(), tempUser);

      tempUser = new UserData();
    }

    builder = new TitanRequestSpecBuilder();

    setBaseUri();
    builder.setBaseUri(baseServiceUri);
    builder.setBasePath(BASE_PATH);
    builder.addHeader("Content-Type", "application/json");

    Serenity.getCurrentSession().put("builder", builder);
  }

  /**
   * Singleton pattern getInstance method.
   *
   * @param baseUriOverride Uri passed by a step that will force a new baseServiceUri value or null
   * @return DataHandler instance, new or existing
   * @throws IOException Continues exception from instantiation of DataHandler
   */
  public static DataHandler getInstance(String baseUriOverride) throws IOException {
    if (INSTANCE == null) {
      INSTANCE = new DataHandler(baseUriOverride);
    }

    return INSTANCE;
  }

  /**
   * Returns the base URI for service calls as generated from the testData file or the passed in
   * property or the step overridden value, in growing level of precedence.
   *
   * @return Base service URI
   */
  public String getBaseServiceUri() {
    return baseServiceUri;
  }

  /**
   * Returns the base URI for UI calls as generated from the testData file or the passed in property
   * or the step overridden value, in growing level of precedence.
   *
   * @return Base UI URI
   */
  public String getBaseUiUri() {
    return baseUiUri;
  }

  /**
   * Returns the user object, selected by passing in the value of the displayName. Note, the
   * displayName is distinct from the username.
   *
   * @param displayName Display name of the desired user
   * @return The data object for the desired user
   * @see UserData
   */
  public UserData getUserByName(String displayName) {

    return userData.get(displayName);
  }

  /**
   * Get an auth token for a user or refreshes an already exiting one based on the user's display
   * name, which is distinct from the username. It then sets that token value in the persistent
   * {@link TitanRequestSpecBuilder}.
   *
   * @param displayName Display name of the desired user
   */
  public void handleUserAuth(String displayName) throws IOException {
    UserData user = getUserByName(displayName);
    if (user.getAuthToken() == null || user.isTokenExpired()) {
      performLogin(user);
    } else {
      performRefresh(user);
    }
  }

  /**
   * Takes in a {@link UserData} object and uses the data in it to call {@link EAMAuth} to retrieve
   * a new token. It then sets the auth token, the refresh token, and the retrieved expire time in
   * the provided UserData object.
   *
   * @param user UserData object containing the user to get the new auth token for
   */
  public void performLogin(UserData user) throws IOException {
    EAMAuth auth = new EAMAuth();
    auth.performLogin(user.getUsername(), user.getPassword(), baseServiceUri);
    user.setAuthToken(auth.getAuthToken());
    user.setExpiresOn(auth.getExpiresOn());
    user.setRefreshToken(auth.getRefreshToken());
  }

  /**
   * Presumes a valid user auth token and takes in the {@link UserData} object specific to the user
   * to be refreshed and performs the refresh through {@link EAMAuth}.
   *
   * @param user UserData object containing the user to get the new auth token for
   */
  public void performRefresh(UserData user) throws IOException {
    EAMAuth auth = new EAMAuth();
    auth.performRefresh(user.getRefreshToken(), baseServiceUri);
    user.setRefreshToken(auth.getRefreshToken());
    user.setExpiresOn(auth.getExpiresOn());
  }

  /**
   * Takes any provided override uri, allowing that it can, and usually will be, null, and then
   * first checks for a default uri in the testData information. Then it will check to see if an
   * environment variable with a preferred uri was set under the key "base.uri", overriding the
   * value from the testData file if it exists. Then it examines the overrideBaseUri parameter and,
   * if non-null, overrides the current uri and sets it to that value instead. This is generally
   * done when a single test has a unique uri to target, distinct from the one provided by one of
   * the other two methods.
   */
  private void setBaseUri() {
    if (System.getProperty("base.service.uri") != null) {
      baseServiceUri = System.getProperty("base.service.uri");
    }

    if (baseServiceUriOverride != null) {
      baseServiceUri = baseServiceUriOverride;
    }

    if (System.getProperty("base.ui.uri") != null) {
      baseUiUri = System.getProperty("base.ui.uri");
    }

    if (baseUiUriOverride != null) {
      baseUiUri = baseUiUriOverride;
    }
  }

  /**
   * Returns the {@link TitanRequestSpecBuilder} object after cleaning out any data beyond the
   * context and baseServiceUri and Path.
   *
   * @return The current builder object
   * @see TitanRequestSpecBuilder
   */
  public TitanRequestSpecBuilder getBuilder() {
    builder = new TitanRequestSpecBuilder();
    setBaseUri();
    builder.setBaseUri(baseServiceUri);
    builder.setBasePath(BASE_PATH);
    builder.addPathParam("service", (String) Serenity.getCurrentSession().get("service"));
    builder.addHeader("Content-Type", "application/json");
    UserData user = ((UserData) Serenity.getCurrentSession().get("currentUser"));
    setBaseContext();
    builder.addHeader("Vertafore-Authorization", user.getAuthToken());
    return builder;
  }

  /**
   * Returns the {@link TitanRequestSpecBuilder} object to a pristine state and resets the
   * baseServiceUri to the configured default, then returns that builder object.
   *
   * @return Cleaned TitanRequestSpecBuilder object
   */
  public TitanRequestSpecBuilder getCleanBuilder() {
    builder = new TitanRequestSpecBuilder();
    setBaseUri();
    builder.setBaseUri(baseServiceUri);
    return builder;
  }

  public void setBaseContext() {
    CaseInsensitiveMap<String, String> context =
        (CaseInsensitiveMap<String, String>) Serenity.getCurrentSession().get("currentContext");
    builder.addPathParam("productId", context.get("productId"));
    builder.addPathParam("tenantId", context.get("tenantId"));
    builder.addPathParam("entityId", context.get("entityId"));
    builder.addPathParam("service", (String) Serenity.getCurrentSession().get("service"));
  }

  public Map<String, UserData> getUsersMap() {
    return userData;
  }
}
