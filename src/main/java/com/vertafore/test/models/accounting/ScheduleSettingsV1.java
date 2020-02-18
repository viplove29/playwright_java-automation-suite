package com.vertafore.test.models.accounting;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScheduleSettingsV1 {

	private List<ScheduleV1> schedules;
	private Instant updatedOn;
	private Instant createdOn;

	public List<ScheduleV1> getSchedules() {
		return this.schedules;
	}
	public void setSchedules(List<ScheduleV1> schedules) {
		this.schedules = schedules;
	}

	public Instant getUpdatedOn() {
		return this.updatedOn;
	}
	public void setUpdatedOn(Instant updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Instant getCreatedOn() {
		return this.createdOn;
	}
	public void setCreatedOn(Instant createdOn) {
		this.createdOn = createdOn;
	}

}
