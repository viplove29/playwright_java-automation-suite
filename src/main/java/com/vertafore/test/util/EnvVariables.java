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

  public static final String AADM_NAUSER_USERNAME =
      getProperty("read_write_mask_users.no_access_user.username");
  public static final String AADM_NAUSER_PASSWORD =
      getProperty("read_write_mask_users.no_access_user.password");

  public static final String AADM_CBUUSER_USERNAME =
      getProperty("read_write_mask_users.customer_business_unit_access_user.username");
  public static final String AADM_CBUUSER_PASSWORD =
      getProperty("read_write_mask_users.customer_business_unit_access_user.password");

  public static final String AADM_PBUUSER_USERNAME =
      getProperty("read_write_mask_users.policy_business_unit_access_user.username");
  public static final String AADM_PBUUSER_PASSWORD =
      getProperty("read_write_mask_users.policy_business_unit_access_user.password");

  public static final String AADM_EXECUSER_USERNAME =
      getProperty("read_write_mask_users.executive_access_user.username");
  public static final String AADM_EXECUSER_PASSWORD =
      getProperty("read_write_mask_users.executive_access_user.password");

  public static final String AADM_PPUSER_USERNAME =
      getProperty("read_write_mask_users.policy_personnel_access_user.username");
  public static final String AADM_PPUSER_PASSWORD =
      getProperty("read_write_mask_users.policy_personnel_access_user.password");

  public static final String AADM_SGUSER_USERNAME =
      getProperty("read_write_mask_users.service_group_access_user.username");
  public static final String AADM_SGUSER_PASSWORD =
      getProperty("read_write_mask_users.service_group_access_user.password");

  public static final String AGENCY_NO = getProperty("agency_no");
  public static final String ADMIN_EMP_CODE = getProperty("admin_emp_code");
  public static final String APP_ACCESS_TO_AGENCY_KEY = getProperty("app_access_to_agency_key");

  public static final String ALL_ACCESS_BANK = getProperty("access_banks.all_access_bank");
  public static final String EMS_ACCESS_ONLY_BANK =
      getProperty("access_banks.ems_access_only_bank");
  public static final String EMS_ACCESS_EXCLUDED_BANK =
      getProperty("access_banks.ems_access_excluded_bank");
  public static final String DIVISIONAL_ACCESS_ONLY_BANK =
      getProperty("access_banks.divisional_access_only_bank");

  public static final String READ_WRITE_MASK_CUSTOMER_ID =
      getProperty("read_write_mask_users.read_write_mask_customer.customer_id");

  public static final String READ_WRITE_MASK_POLICY_ID =
      getProperty("read_write_mask_users.read_write_mask_customer.policy_id");

  public static final String NO_BANK_TRANSACTION_ACCESS_USERNAME =
      getProperty("no_bank_transactions_access_user.username");
  public static final String NO_BANK_TRANSACTION_ACCESS_PASSWORD =
      getProperty("no_bank_transactions_access_user.password");
  public static final String FULL_BANK_TRANSACTION_ACCESS_USERNAME =
      getProperty("full_bank_transactions_access_user.username");
  public static final String FULL_BANK_TRANSACTION_ACCESS_PASSWORD =
      getProperty("full_bank_transactions_access_user.password");

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
