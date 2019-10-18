package com.vertafore.test.common.models.services.accounting.settings;

import java.time.Instant;
import java.util.List;

public class ScheduleSettingsV1 {

  private List<ScheduleV1> schedules;
  private Instant createdOn;
  private Instant updatedOn;

  public List<ScheduleV1> getSchedules() {
    return schedules;
  }

  public void setSchedules(List<ScheduleV1> schedules) {
    this.schedules = schedules;
  }

  public Instant getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Instant createdOn) {
    this.createdOn = createdOn;
  }

  public Instant getUpdatedOn() {
    return updatedOn;
  }

  public void setUpdatedOn(Instant updatedOn) {
    this.updatedOn = updatedOn;
  }

  public static ScheduleSettingsV1 getGenericScheduleSettingsDTO() {
    ScheduleSettingsV1 dto = new ScheduleSettingsV1();
    dto.setSchedules(ScheduleV1.getDefaultListOfSchedulesDTOs());
    return dto;
  }
}
