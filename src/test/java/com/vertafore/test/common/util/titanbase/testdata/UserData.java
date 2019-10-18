package com.vertafore.test.common.util.titanbase.testdata;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.map.CaseInsensitiveMap;

/**
 * Largely a DTO with a few other helper methods mixed in, this class stores all of the data for a
 * provided user, including their Auth information and any context(s) they might be associated with.
 */
public class UserData {

  private String username;
  private String password;
  private String displayName;
  private String userId;
  private String authToken;
  private String refreshToken;
  private Instant expiresOn;
  private List<CaseInsensitiveMap<String, String>> context;

  /** Constructor just instantiates the List object so that it can be added to. */
  public UserData() {
    context = new ArrayList<>();
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getAuthToken() {
    return authToken;
  }

  public void setAuthToken(String authToken) {
    this.authToken = authToken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public void setExpiresOn(Instant expiresOn) {
    this.expiresOn = expiresOn;
  }

  public Instant getExpiresOn() {
    return expiresOn;
  }

  /**
   * Compares the current time as an {@link Instant} to the stored time of expiration for the token
   * and returns a result as to wether or not it is expired.
   *
   * @return True if token is expired, false if it is still valid
   */
  public boolean isTokenExpired() {
    return expiresOn.compareTo(Instant.now()) == 0;
  }

  public List<CaseInsensitiveMap<String, String>> getAllContext() {
    return context;
  }

  /**
   * Adds a context entry into the context map.
   *
   * @param contextCommonName Context common name
   * @param productId Context product id
   * @param tenantId Context tenant id
   * @param entityId Context entity id
   */
  public void addContext(
      String contextCommonName, String productId, String tenantId, String entityId) {
    CaseInsensitiveMap<String, String> tempContext = new CaseInsensitiveMap<>();
    tempContext.put("name", contextCommonName);
    tempContext.put("productId", productId);
    tempContext.put("tenantId", tenantId);
    tempContext.put("entityId", entityId);
    context.add(tempContext);
  }

  /**
   * Returns the {@link CaseInsensitiveMap} containing the product, tenant, and entity ids when
   * provided the target entity id.
   *
   * @param entityId The entity id for the desired context
   * @return The map with all three elements of the context
   */
  public CaseInsensitiveMap<String, String> getFullContextByEntity(String entityId) {
    for (CaseInsensitiveMap<String, String> tempContext : context) {
      if (tempContext.get("entityId").equalsIgnoreCase(entityId)) {
        return tempContext;
      }
    }
    throw new IllegalArgumentException(
        "The entity \"" + entityId + "\" was not present for this user");
  }

  /**
   * Returns the {@link CaseInsensitiveMap} containing the product, tenant, and entity ids when
   * provided the target entity id.
   *
   * @param contextCommonName The common name for the desired context
   * @return The map with all three elements of the context
   */
  public CaseInsensitiveMap<String, String> getFullContextByContextName(String contextCommonName) {
    for (CaseInsensitiveMap<String, String> tempContext : context) {
      if (tempContext.get("name").equalsIgnoreCase(contextCommonName)) {
        return tempContext;
      }
    }
    throw new IllegalArgumentException(
        "The context \"" + contextCommonName + "\" was not present for this user");
  }
}
