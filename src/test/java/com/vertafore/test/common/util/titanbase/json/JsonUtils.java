package com.vertafore.test.common.util.titanbase.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Iterator;
import java.util.Map;

public class JsonUtils {

  private static ObjectMapper objectMapper;
  /**
   * Generates a a custom Jackson ObjectMapper that serializes/deserializes dates/times in ISO 8601
   * format.
   */
  public static ObjectMapper getObjectMapper() {
    if (objectMapper == null) {
      objectMapper = new ObjectMapper();
      objectMapper.registerModule(new JavaTimeModule());
      objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
      objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
      objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
    return objectMapper;
  }

  /**
   * convertResponseStringToJsonString
   *
   * <p>Converts string from a REST request into a JSON string that can be parsed by the mapper of
   * choice.
   *
   * @param input String containing REST response content
   * @return String containing valid JSON for parsing
   */
  public static String convertResponseStringToJsonString(String input) {
    String result = input;
    result =
        result
            .replace("=", "\":\"") // handle equals to colons
            .replace(", ", "\",\"") // handle comma separators
            .replace("{", "{\"") // handle initial quotes
            .replace("}", "\"}") // handle final quotes
            .replace("\"[", "[") // handle beginning quotes for arrays
            .replace("]\"", "]") // handle ending quotes for arrays
            .replace("\"{", "{") // handle beginning quotes for objects
            .replace("}\"", "}") // handle ending quotes for objects
            .replaceAll("\\r", "\\\\r") // handle carriage-return characters
            .replaceAll("\\n", "\\\\n"); // handle linefeed characters
    return result;
  }

  /**
   * biasedCompareJsonObjects
   *
   * <p>Uses gson-based JsonObjects.
   *
   * <p>Compares an expected object with an actual object, caring only that the elements of the
   * expected object appear in the actual object. This is necessary because the response (actual)
   * object returned by the service will frequently contain extra values or non-null values that are
   * not present in the original (expected) request body object. Recurses into other JsonObjects
   * found as part of the JsonTree. Calls a similar function on JsonArrays found as part of the
   * tree. Aborts when a non-match is detected, returning a false result.
   *
   * @param expected JsonObject containing a minority of information
   * @param actual JsonObject containing a majority of information
   * @param flag Boolean flag reflecting current state of matching function result
   * @return Boolean reflecting updated state of matching function result
   */
  public static boolean biasedCompareJsonObjects(
      final JsonObject expected, final JsonObject actual, boolean flag) {
    boolean result = flag;
    for (Map.Entry<String, JsonElement> e : expected.entrySet()) {
      if (!result) return result;
      String k = e.getKey();
      JsonElement v = e.getValue();
      JsonElement w = actual.get(k);
      if (!v.isJsonNull()) {
        if (v.isJsonObject()) {
          result = biasedCompareJsonObjects((JsonObject) v, (JsonObject) w, result);
        } else if (v.isJsonArray()) {
          result = biasedCompareJsonArrays((JsonArray) v, (JsonArray) w, result);
        } else {
          if (!w.toString().contentEquals(v.toString())) {
            result = false;
            break;
          }
        }
      }
    }
    return result;
  }

  /**
   * biasedCompareJsonArrays
   *
   * <p>Uses gson-based JsonArrays.
   *
   * <p>Compares an expected array with an actual array, caring only that the elements of the
   * expected object appear in the actual object. This is necessary because the response (actual)
   * object returned by the service will frequently contain extra values or non-null values that are
   * not present in the original (expected) request body object. Recurses into other JsonArrays
   * found as part of the JsonTree. Calls a similar function for JsonObjects found as part of the
   * JsonTree. Aborts when a non-match is found, returning a false result.
   *
   * @param expected JsonArray containing a minority of information
   * @param actual JsonArray containing a majority of information
   * @param flag Boolean flag reflecting current state of matching function result
   * @return Boolean flag reflecting updated state of matching function result
   */
  public static boolean biasedCompareJsonArrays(
      final JsonArray expected, final JsonArray actual, boolean flag) {
    boolean result = flag;
    if (expected.size() > actual.size()) {
      result = false;
    } else if (expected.size() > 0) {
      for (JsonElement e : expected) {
        if (!result) {
          break;
        }
        if (!e.isJsonNull()) {
          if (e.isJsonArray()) {
            Iterator i = actual.iterator();
            boolean signal = false;
            while (i.hasNext() && !signal) {
              JsonElement a = (JsonElement) i.next();
              if (a.isJsonArray()) {
                if (a.getAsJsonArray().size() <= e.getAsJsonArray().size()) {
                  signal = biasedCompareJsonArrays(e.getAsJsonArray(), a.getAsJsonArray(), true);
                }
              }
            }
          } else if (e.isJsonObject()) {
            Iterator i = actual.iterator();
            boolean signal = false;
            while (i.hasNext() && !signal) {
              JsonElement a = (JsonElement) i.next();
              if (a.isJsonObject()) {
                if (a.getAsJsonObject().size() <= e.getAsJsonObject().size()) {
                  signal = biasedCompareJsonObjects(e.getAsJsonObject(), a.getAsJsonObject(), true);
                }
              }
            }
          } else {
            Iterator i = actual.iterator();
            boolean signal = false;
            while (i.hasNext() && !signal) {
              JsonElement a = (JsonElement) i.next();
              signal = a.toString().contentEquals(e.toString());
            }
          }
        }
      }
    }
    return result;
  }
}
