package com.vertafore.test.util;

import net.serenitybdd.core.Serenity;

public final class Keys {

  public static String getKey(KeyType keyType) {
    return Serenity.sessionVariableCalled(keyType);
  }

  public static void setKey(KeyType keyType, String keyValue) {
    Serenity.setSessionVariable(keyType).to(keyValue);
  }

  public enum KeyType {
    USER_APP_KEY,
    VERT_APP_KEY
  }
}
