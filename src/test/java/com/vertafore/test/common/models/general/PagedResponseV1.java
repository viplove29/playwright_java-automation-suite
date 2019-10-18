package com.vertafore.test.common.models.general;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PagedResponseV1 extends com.vertafore.core.ws.model.PagedResponseV1 {
  public String method;
  public Integer numberOfElements;
  public Integer page;
  public Integer pageSize;
  public Integer totalElements;
  public Integer totalPages;
}
