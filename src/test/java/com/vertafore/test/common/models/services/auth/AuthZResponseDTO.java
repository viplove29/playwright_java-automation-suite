package com.vertafore.test.common.models.services.auth;

// * This is the DTO to handle the overall response from an attempt to get
// * a new token from the Auth service or refresh an existing one.  It can
// * handle both a valid and a failed attempt.

import com.vertafore.test.common.models.general.ErrorResponseV1;

// I think this model is either unnecessary
// we could just cast our response from the server as an OAuthTokenV1
// or if it was absolutely necessary this model could extend from the ResponseV1
public class AuthZResponseDTO {
  private String requestId;
  private String spanId;
  private String traceId;
  private OAuthTokenV1 content;
  private ErrorResponseV1 error;

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public String getSpanId() {
    return spanId;
  }

  public void setSpanId(String spanId) {
    this.spanId = spanId;
  }

  public String getTraceId() {
    return traceId;
  }

  public void setTraceId(String traceId) {
    this.traceId = traceId;
  }

  public OAuthTokenV1 getContent() {
    return content;
  }

  public void setContent(OAuthTokenV1 content) {
    this.content = content;
  }

  public ErrorResponseV1 getError() {
    return error;
  }

  public void setError(ErrorResponseV1 error) {
    this.error = error;
  }
}
