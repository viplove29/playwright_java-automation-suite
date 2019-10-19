package com.vertafore.test.common.models.general;

public class PatchBody {
  public PatchOperation op;
  public String from;
  public String path;
  public Object value;

  public PatchOperation getOperation() {
    return op;
  }

  public void setOperation(PatchOperation operation) {
    this.op = operation;
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
