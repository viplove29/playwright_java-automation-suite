package com.vertafore.test.util;

import com.vertafore.test.models.ems.*;
import java.util.HashMap;

public class GlobalChangeUtil {

  public static HashMap<String, Object> getBodyForGlobalChangeBusinessUnit(
      String csvByteArray, String changeType, String collectionMemo, String fileName) {
    String globalChangeBUType;
    switch (changeType.toUpperCase()) {
      case "POLICY":
        globalChangeBUType = "Policy Only";
        break;
      case "CUSTOMER":
        globalChangeBUType = "Customer Only";
        break;
      default:
        throw new IllegalArgumentException("Type must be POLICY or CUSTOMER.");
    }
    HashMap<String, Object> body = new HashMap<>();
    body.put("csvFileData", csvByteArray);
    body.put("globalChangeBusinessUnitType", globalChangeBUType);
    body.put("collectionMemo", collectionMemo);
    body.put("fileName", fileName);
    return body;
  }
}
