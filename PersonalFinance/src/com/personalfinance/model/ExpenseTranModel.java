package com.personalfinance.model;

public class ExpenseTranModel {

	private String expenseName;
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

	public String getExpenseName() {
		return expenseName;
	}

	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}

	public String getTrandate() {
		return trandate;
	}

	public void setTrandate(String active) {
		this.trandate = active;
	}
}
