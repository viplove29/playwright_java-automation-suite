package com.vertafore.test.models.accounting;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JournalV1 {

	private String journalType;
	private String createdBy;
	private String modifiedBy;
	private Integer fiscalMonth;
	private String id;
	private String label;
	private Instant updatedOn;
	private Instant createdOn;

	public String getJournalType() {
		return this.journalType;
	}
	public void setJournalType(String journalType) {
		this.journalType = journalType;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Integer getFiscalMonth() {
		return this.fiscalMonth;
	}
	public void setFcalMonth(Integer fiscalMonth) {
		this.fiscalMonth = fiscalMonth;
	}

	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return this.label;
	}
	public void setLabel(String label) {
		this.label = label;
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
