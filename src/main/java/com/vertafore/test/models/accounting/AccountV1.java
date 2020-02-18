package com.vertafore.test.models.accounting;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountV1 {

	private String code;
	private Boolean isArchived;
	private String journalId;
	private String groupId;
	private String entityId;
	private String label;
	private Instant updatedOn;
	private String type;
	private Instant createdOn;
	private String systemLabel;
	private Boolean isTrust;
	private Integer systemCode;
	private String createdBy;
	private Boolean debitHolding;
	private Boolean isSystemAccount;
	private String modifiedBy;
	private String potentialUse;
	private String id;
	private Boolean isDeferred;

	public String getCode() {
		return this.code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public Boolean isArchived() {
		return this.isArchived;
	}
	public void setArchived(Boolean isArchived) {
		this.isArchived = isArchived;
	}

	public String getJournalId() {
		return this.journalId;
	}
	public void setJournalId(String journalId) {
		this.journalId = journalId;
	}

	public String getGroupId() {
		return this.groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getEntityId() {
		return this.entityId;
	}
	public void setEntityId(String entityId) {
		this.entityId = entityId;
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

	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Instant getCreatedOn() {
		return this.createdOn;
	}
	public void setCreatedOn(Instant createdOn) {
		this.createdOn = createdOn;
	}

	public String getSystemLabel() {
		return this.systemLabel;
	}
	public void setSystemLabel(String systemLabel) {
		this.systemLabel = systemLabel;
	}

	public Boolean isTrust() {
		return this.isTrust;
	}
	public void setTrust(Boolean isTrust) {
		this.isTrust = isTrust;
	}

	public Integer getSystemCode() {
		return this.systemCode;
	}
	public void setSystemCode(Integer systemCode) {
		this.systemCode = systemCode;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Boolean debitHolding() {
		return this.debitHolding;
	}
	public void setDebitHolding(Boolean debitHolding) {
		this.debitHolding = debitHolding;
	}

	public Boolean isSystemAccount() {
		return this.isSystemAccount;
	}
	public void setSystemAccount(Boolean isSystemAccount) {
		this.isSystemAccount = isSystemAccount;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getPotentialUse() {
		return this.potentialUse;
	}
	public void setPotentialUse(String potentialUse) {
		this.potentialUse = potentialUse;
	}

	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public Boolean isDeferred() {
		return this.isDeferred;
	}
	public void setDeferred(Boolean isDeferred) {
		this.isDeferred = isDeferred;
	}

}
