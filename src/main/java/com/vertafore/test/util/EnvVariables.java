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
  public static final String APP_ACCESS_TO_AGENCY_KEY = getProperty("ams.app_access_to_agency_key");
  public static final String LOGIN_USER_PATH = getProperty("login_user_path");
  public static final String LOGIN_APP_PATH = getProperty("login_app_path");
  public static final String LOGIN_ADMIN_PATH = getProperty("login_admin_path");
  public static final String LOGIN_DEPRECATED_PATH = getProperty("login_deprecated_path");
  public static final String USERNAME = getProperty("ams.username");
  public static final String PASSWORD = getProperty("ams.password");
  public static final String VSSO_USERNAME = getProperty("ams.vsso_user");
  public static final String VSSO_PASSWORD = getProperty("ams.vsso_password");
  public static final String AGENCY_NO = getProperty("ams.agency_no");
  public static final String ADMIN_EMP_CODE = getProperty("ams.admin_emp_code");

  private static String getProperty(String property) {
    return EnvironmentSpecificConfiguration.from(
            SystemEnvironmentVariables.createEnvironmentVariables())
        .getProperty(property);
  }
}
