package com.vertafore.test.common.util.titanbase.json;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.time.Instant;

/**
 * This class provides deserialization helpers for both Jackson and Gson methods of JSON parsing.
 */
public class DTOStringToInstantConverter extends StdConverter<String, Instant> {
  public Instant convert(String value) {
    return Instant.parse(value);
  }

  public static JsonDeserializer<Instant> deserializer =
      new JsonDeserializer<Instant>() {
        @Override
        public Instant deserialize(
            JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
          return json == null ? null : Instant.parse(json.getAsString());
        }
      };
}
