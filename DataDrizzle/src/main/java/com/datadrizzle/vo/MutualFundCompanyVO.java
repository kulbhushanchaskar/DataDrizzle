package com.datadrizzle.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "MutualFundCompany")
@Table(name = "MutualFundCompany")
public class MutualFundCompanyVO {

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "company_name", length = 250)
	String companyName;
	@Column(name = "symbol", length = 250)
	String symbol;
	
	public MutualFundCompanyVO() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return "MutualFundCompany [id=" + id + ", companyName=" + companyName + ", symbol=" + symbol + "]";
	}
}
