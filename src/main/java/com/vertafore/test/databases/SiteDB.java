package com.vertafore.test.databases;

import static com.vertafore.test.util.EnvVariables.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.serenitybdd.core.Serenity;

public class SiteDB extends SharedDB {

  public static void openDB() {
    openConnection(SITEDB_DATABASE, SITEDB_SERVER, SITEDB_USER, SITEDB_PASSWORD);
  }

  public static void closeDB() {
    closeConnection(SITEDB_DATABASE);
  }

  public static String getUserAppKey(String appType) {
    openDB();
    Connection connection = Serenity.sessionVariableCalled(SITEDB_DATABASE);
    if (connection == null) {
      return USER_APP_KEY;
    } else {
      String appId = getAppId(appType);
      String getRowQuery =
          "SELECT * FROM [dbo].[EMS_AppKey]\n"
              + "WHERE AppId = '"
              + appId
              + "'\n"
              + "AND KeyType = 'T'";
      try {
        ResultSet resultSet = connection.createStatement().executeQuery(getRowQuery);
        resultSet.next();
        return resultSet.getString("AppKey");
      } catch (SQLException e) {
        System.out.println(getRowQuery);
        throw new RuntimeException(e);
      }
    }
  }

  public static String getAppId(String appType) {
    String getRowQuery =
        "SELECT * FROM [dbo].[EMS_App] WHERE AppType = '"
            + appType
            + "'"
            + "AND AppName = 'AMS360'";
    Connection connection = Serenity.sessionVariableCalled(SITEDB_DATABASE);
    try {
      ResultSet resultSet = connection.createStatement().executeQuery(getRowQuery);
      resultSet.next();
      return resultSet.getString("AppId");
    } catch (SQLException e) {
      System.out.println(getRowQuery);
      throw new RuntimeException(e);
    }
  }
}
