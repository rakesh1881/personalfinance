package com.personalfinance.model;

public class AccountTranModel {

	private String accountName;
	private String trandate;
	private double amount;
	private String description;
	private String trankey;

	public String getTrankey() {
		return trankey;
	}

	public void setTrankey(String trankey) {
		this.trankey = trankey;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double accountId) {
		this.amount = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getTrandate() {
		return trandate;
	}

	public void setTrandate(String active) {
		this.trandate = active;
	}
}
