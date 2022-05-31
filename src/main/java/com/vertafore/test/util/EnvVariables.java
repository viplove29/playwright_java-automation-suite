package com.vertafore.test.util;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.SystemEnvironmentVariables;

public class EnvVariables {

  public static final String BASE_URL = getProperty("base_url");
  public static final String LOGIN_KEY_PATH = getProperty("login_key_path");
  public static final String AADM_APP_KEY = getProperty("appkey_AADM");
  public static final String VERT_APP_KEY = getProperty("appkey_VERT");
  public static final String VADM_APP_KEY = getProperty("appkey_VADM");
  public static final String AGNY_APP_KEY = getProperty("appkey_AGNY");
  public static final String ORAN_APP_KEY = getProperty("appkey_ORAN");
  public static final String ORAN_APP_ID = getProperty("appId_orange");
  public static final String LOGIN_USER_PATH = getProperty("login_user_path");
  public static final String LOGIN_APP_PATH = getProperty("login_app_path");
  public static final String LOGIN_ADMIN_PATH = getProperty("login_admin_path");

  public static final String USERNAME = getProperty("username");
  public static final String PASSWORD = getProperty("password");
  //  public static final String VSSO_USERNAME = getProperty("vsso_user");
  //  public static final String VSSO_PASSWORD = getProperty("vsso_password");
  public static final String AGENCY_NO = getProperty("agency_no");
  public static final String ADMIN_EMP_CODE = getProperty("admin_emp_code");
  public static final String APP_ACCESS_TO_AGENCY_KEY = getProperty("app_access_to_agency_key");

  private static String getProperty(String property) {
    return EnvironmentSpecificConfiguration.from(
            SystemEnvironmentVariables.createEnvironmentVariables())
        .getProperty(property);
  }

  /* these can be used if we add db access at some point- - they'll have to be refactored, but you get the idea */
  //  public static final String SITEDB_SERVER = getProperty("site_db.server");
  //  public static final String SITEDB_USER = getProperty("site_db.user");
  //  public static final String SITEDB_PASSWORD = getProperty("site_db.password");
  //  public static final String SITEDB_DATABASE = getProperty("site_db.database");

}
