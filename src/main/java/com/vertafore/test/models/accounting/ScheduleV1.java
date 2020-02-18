package com.vertafore.test.models.accounting;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScheduleV1 {

  private Integer startDay;
  private Integer endDay;

  public Integer getStartDay() {
    return this.startDay;
  }

  public void setStartDay(Integer startDay) {
    this.startDay = startDay;
  }

  public Integer getEndDay() {
    return this.endDay;
  }

  public void setEndDay(Integer endDay) {
    this.endDay = endDay;
  }
}
