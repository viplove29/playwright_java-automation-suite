package com.vertafore.test.common.util.titanbase.json;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.time.Instant;

/** This class provides serialization helpers for both Jackson and Gson methods of JSON parsing. */
public class DTOInstantToStringConverter extends StdConverter<Instant, String> {
  public String convert(Instant value) {
    return value.toString();
  }

  public static JsonSerializer<Instant> serializer =
      new JsonSerializer<Instant>() {
        @Override
        public JsonElement serialize(
            Instant src, Type typeOfSrc, JsonSerializationContext context) {
          return src == null ? null : new JsonPrimitive(src.toString());
        }
      };
}
