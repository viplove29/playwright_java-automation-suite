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

  public static String getUserAppKey(String context) {
    openDB();
    Connection connection = Serenity.sessionVariableCalled(SITEDB_DATABASE);
    if (connection == null) {
      return USER_APP_KEY;
    } else {
      String appId = "56DFA3AD-CD0D-4C69-9DBD-7E127B748828";
      String getRowQuery = "SELECT * FROM [dbo].[EMS_AppKey] WHERE AppId = '" + appId + "'";
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
}
