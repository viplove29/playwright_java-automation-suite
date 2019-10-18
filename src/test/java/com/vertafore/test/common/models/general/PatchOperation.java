package com.vertafore.test.common.models.general;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PatchOperation {
  ADD,
  REMOVE,
  REPLACE,
  COPY,
  MOVE,
  TEST;

  @JsonValue
  public String getFormattedName() {
    return name().toLowerCase();
  }
}
