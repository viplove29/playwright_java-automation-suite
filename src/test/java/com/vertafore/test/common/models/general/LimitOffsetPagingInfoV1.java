package com.vertafore.test.common.models.general;

public class LimitOffsetPagingInfoV1 extends com.vertafore.core.ws.model.LimitOffsetPagingInfoV1 {
  public int numberOfElements;
  public int pageSize;
  public int page;
  public int totalPages;
  public int totalElements;

  public LimitOffsetPagingInfoV1(int numElements, int pageSize, int page, int totalElements) {
    super(numElements, pageSize, page, totalElements);
  }
}
