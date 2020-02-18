package com.vertafore.test.models.accounting;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountBalanceV1 {

	private List<AccountBalanceV1> childAccounts;
	private Integer code;
	private Double balance;
	private Double credits;
	private Double debits;
	private String id;
	private String label;
	private String parentId;

	public List<AccountBalanceV1> getChildAccounts() {
		return this.childAccounts;
	}
	public void setChildAccounts(List<AccountBalanceV1> childAccounts) {
		this.childAccounts = childAccounts;
	}

	public Integer getCode() {
		return this.code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}

	public Double getBalance() {
		return this.balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getCredits() {
		return this.credits;
	}
	public void setCredits(Double credits) {
		this.credits = credits;
	}

	public Double getDebits() {
		return this.debits;
	}
	public void setDebits(Double debits) {
		this.debits = debits;
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

	public String getParentId() {
		return this.parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}
