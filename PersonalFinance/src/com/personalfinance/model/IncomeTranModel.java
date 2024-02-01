package com.personalfinance.model;

public class IncomeTranModel {

	private String incomeName;
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

	public void setAmount(double incomeId) {
		this.amount = incomeId;
	}

	public String getIncomeName() {
		return incomeName;
	}

	public void setIncomeName(String incomeName) {
		this.incomeName = incomeName;
	}

	public String getTrandate() {
		return trandate;
	}

	public void setTrandate(String active) {
		this.trandate = active;
	}
}
