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

  public static String getAppKey(String appType, String storedKey) {
    openDB();
    Connection connection = Serenity.sessionVariableCalled(SITEDB_DATABASE);
    if (connection == null) {
      return storedKey;
    } else {
      String appId = getAppId(appType);
      String query =
          "SELECT * FROM [dbo].[EMS_AppKey]\n"
              + "WHERE AppId = '"
              + appId
              + "'\n"
              + "AND KeyType = 'T'";
      try {
        String appKey;
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        if (!resultSet.isBeforeFirst()) {
          appKey = storedKey;
        } else {
          resultSet.next();
          appKey = resultSet.getString(("AppKey"));
        }
        return appKey;
      } catch (SQLException e) {
        System.out.println(query);
        throw new RuntimeException(e);
      }
    }
  }

  public static String getAppId(String appType) {
    String query =
        "SELECT * FROM [dbo].[EMS_App] WHERE AppType = '" + appType + "'" + "AND Status = 'A'";
    Connection connection = Serenity.sessionVariableCalled(SITEDB_DATABASE);
    try {
      ResultSet resultSet = connection.createStatement().executeQuery(query);
      resultSet.next();
      return resultSet.getString("AppId");
    } catch (SQLException e) {
      System.out.println(query);
      throw new RuntimeException(e);
    }
  }
}
