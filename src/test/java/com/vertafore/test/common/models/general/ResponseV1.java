package com.vertafore.test.common.models.general;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseV1<T> {
  private T content;
  private ErrorResponseV1 error;
  private String requestId;
  private String spanId;
  private String traceId;
  private LimitOffsetPagingInfoV1 paging;
}
