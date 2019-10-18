package com.vertafore.test.common.models.general;

public class PatchBody {
  private PatchOperation operation;
  private String from;
  private String path;
  private Object value;

  public PatchOperation getOperation() {
    return operation;
  }

  public void setOperation(PatchOperation operation) {
    this.operation = operation;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }
}
