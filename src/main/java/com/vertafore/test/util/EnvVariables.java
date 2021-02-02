package com.vertafore.test.util;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.SystemEnvironmentVariables;

public class EnvVariables {

  public static final String BASE_URL = getProperty("base_url");
  public static final String LOGIN_KEY_PATH = getProperty("login_key_path");
  public static final String USER_APP_KEY = getProperty("site_db.appkey_user");
  public static final String APP_APP_KEY = getProperty("site_db.appkey_app");
  public static final String ADMIN_APP_KEY = getProperty("site_db.appkey_admin");
  public static final String VERT_APP_KEY = getProperty("site_db.appkey_vert");
  public static final String LOGIN_USER_PATH = getProperty("login_user_path");
  public static final String LOGIN_APP_PATH = getProperty("login_app_path");
  public static final String LOGIN_ADMIN_PATH = getProperty("login_admin_path");
  public static final String LOGIN_DEPRECATED_PATH = getProperty("login_deprecated_path");
  public static final String SITEDB_SERVER = getProperty("site_db.server");
  public static final String SITEDB_USER = getProperty("site_db.user");
  public static final String SITEDB_PASSWORD = getProperty("site_db.password");
  public static final String SITEDB_DATABASE = getProperty("site_db.database");

  public static String USERNAME(String version) {
    return getProperty(version + ".username");
  }

  public static String PASSWORD(String version) {
    return getProperty(version + ".password");
  }

  public static String VSSO_USERNAME(String version) {
    return getProperty(version + ".vsso_user");
  }

  public static String VSSO_PASSWORD(String version) {
    return getProperty(version + ".vsso_password");
  }

  public static String AGENCY_NO(String version) {
    return getProperty(version + ".agency_no");
  }

  public static String ADMIN_EMP_CODE(String version) {
    return getProperty(version + ".admin_emp_code");
  }

  public static String APP_ACCESS_TO_AGENCY_KEY(String version) {
    return getProperty(version + ".app_access_to_agency_key");
  }

  private static String getProperty(String property) {
    return EnvironmentSpecificConfiguration.from(
            SystemEnvironmentVariables.createEnvironmentVariables())
        .getProperty(property);
  }
}
