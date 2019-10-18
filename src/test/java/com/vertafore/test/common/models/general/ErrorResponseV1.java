package com.vertafore.test.common.models.general;

/** This DTO handles any errors that are generated in a request call to Auth or others. */
public class ErrorResponseV1 {
  private String message;
  private Integer status;

  public void setMessage(String message) {
    this.message = message;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public Integer getStatus() {
    return status;
  }
}
