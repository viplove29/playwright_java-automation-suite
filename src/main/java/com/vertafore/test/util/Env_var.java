package com.vertafore.test.util;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.SystemEnvironmentVariables;

public class Env_var {

  public static final String BASE_URL = getProperty("base_url");
  public static final String LOGIN_KEY_PATH = getProperty("login_key_path");
  public static final String USER_APP_KEY = getProperty("site_db.appkey_user");
  public static final String APP_APP_KEY = getProperty("site_db.appkey_app");
  public static final String ADMIN_APP_KEY = getProperty("site_db.appkey_admin");
  public static final String VERT_APP_KEY = getProperty("site_db.appkey_vert");
  public static final String APP_ACCESS_TO_AGENCY_KEY = "site_db.app_access_to_agency_key";
  public static final String LOGIN_USER_PATH = "login_user_path";
  public static final String LOGIN_APP_PATH = "login_app_path";
  public static final String LOGIN_ADMIN_PATH = "login_admin_path";
  public static final String USERNAME = "ams.username";
  public static final String PASSWORD = "ams.password";
  public static final String VSSO_USERNAME = "ams.vsso_user";
  public static final String VSSO_PASSWORD = "ams.vsso_password";
  public static final String AGENCY_NO = getProperty("ams.agency_no");
  public static final String ADMIN_EMP_CODE = "ams.admin_emp_code";

  private static String getProperty(String property) {
    return EnvironmentSpecificConfiguration.from(
            SystemEnvironmentVariables.createEnvironmentVariables())
        .getProperty(property);
  }
}
