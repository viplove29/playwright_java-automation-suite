package com.vertafore.test.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import net.serenitybdd.core.Serenity;

abstract class SharedDB {

  static void openConnection(String database, String server, String user, String password) {
    String connection =
        "jdbc:sqlserver://"
            + server
            + ";"
            + "database="
            + database
            + ";"
            + "user="
            + user
            + ";"
            + "password="
            + password
            + ";"
            + "encrypt=true;"
            + "trustServerCertificate=true;"
            + "loginTimeout=30;";
    try {
      Serenity.setSessionVariable(database).to(DriverManager.getConnection(connection));
    } catch (SQLException e) {
      System.out.println(connection);
      throw new RuntimeException(e);
    }
  }

  static void closeConnection(String database) {
    Connection connection = Serenity.sessionVariableCalled(database);
    try {
      connection.close();
    } catch (SQLException e) {
      System.out.println("Failed to close connection");
      e.printStackTrace();
    }
  }
}
