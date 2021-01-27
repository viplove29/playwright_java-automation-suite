package com.vertafore.test.databases;

import net.serenitybdd.core.Serenity;

import java.sql.Connection;

import static com.vertafore.test.util.EnvVariables.*;

public class SiteDB extends SharedDB {

  public static void openDB() {
    openConnection(SITEDB_DATABASE, SITEDB_SERVER, SITEDB_USER, SITEDB_PASSWORD);
  }

  public static void closeDB() {
    closeConnection(SITEDB_DATABASE);
  }

  public static String getUserAppKey(String context) {
    openDB();
    Connection connection = Serenity.sessionVariableCalled(SITEDB_DATABASE);
    if (connection == null) { return USER_APP_KEY;
    } else {
      return USER_APP_KEY;
    }
  }

}
