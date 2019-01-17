package com.datadrizzle.ui.model;

public class MutualFundCompany {

//	private int id;
	String companyName;
	String symbol;
	
	public MutualFundCompany() {
		super();
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return "MutualFundCompany [companyName=" + companyName + ", symbol=" + symbol + "]";
	}
	
}
