package com.datadrizzle.entities;

public class MutualFundIndex {
	
	/*
	 * {"message":"This request is for demonstration purposes only. If you wish to use our API, please sign up and get your personal API token for free.","symbols_requested":3,"symbols_returned":3,
	 * "data":[{"symbol":"AAAAX","name":"DWS RREEF Real Assets Fund - Class A","price":"9.61","close_yesterday":"9.61",
	 * "return_ytd":"1.42","net_assets":"98267850","change_asset_value":"0.05","change_pct":"0.52",
	 * "yield_pct":"1.39","return_day":"0.52","return_1week":"-0.41","return_4week":"-0.66",
	 * "return_13week":"3.36","return_52week":"8.41","return_156week":"3.97","return_260week":"2.76",
	 * "income_dividend":"0.09","income_dividend_date":"2018-06-22 16:00:00","capital_gain":"0.05",
	 * "expense_ratio":"1.22"},
	 * 
	 * 
	 * {"symbol":"AAADX","name":"Aberdeen Income Builder Fund Class A",
	 * "price":"17.06","close_yesterday":"17.06","return_ytd":"0.15","net_assets":"1323875",
	 * "change_asset_value":"0.06","change_pct":"0.35","yield_pct":"3.24","return_day":"0.35",
	 * "return_1week":"1.31","return_4week":"-0.66","return_13week":"1.51","return_52week":"8.29",
	 * "return_156week":"7.00","return_260week":"9.04","income_dividend":"0.12",
	 * "income_dividend_date":"2018-06-29 16:00:00","capital_gain":"0.47","expense_ratio":"1.46"},
	 * 
	 * 
	 * {"symbol":"AAAGX","name":"Thrivent Large Cap Growth Fund Class A","price":"11.94","close_yesterday":"11.94",
	 * "return_ytd":"16.60","net_assets":"238820136","change_asset_value":"0.19","change_pct":"1.62",
	 * "yield_pct":"0.00","return_day":"1.62","return_1week":"3.92","return_4week":"1.79",
	 * "return_13week":"9.94","return_52week":"26.60","return_156week":"14.58",
	 * "return_260week":"15.59","income_dividend":"0.00","income_dividend_date":"2014-12-11 16:00:00",
	 * "capital_gain":"0.27","expense_ratio":"1.15"}]}
	 */
	
	String name;
	double price;
	double close_yesterday;
	double return_ytd;
	double net_assets;
	double change_asset_value;
	double change_pct;
	double yield_pct;
	double return_day;
	double return_1week;
	double return_4week;
	double return_13week;
	double return_52week;
	double return_156week;
	double return_260week;
	double income_dividend;
	String income_dividend_date;
	double capital_gain;
	double expense_ratio;
	
	public MutualFundIndex() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getClose_yesterday() {
		return close_yesterday;
	}

	public void setClose_yesterday(double close_yesterday) {
		this.close_yesterday = close_yesterday;
	}

	public double getReturn_ytd() {
		return return_ytd;
	}

	public void setReturn_ytd(double return_ytd) {
		this.return_ytd = return_ytd;
	}

	public double getNet_assets() {
		return net_assets;
	}

	public void setNet_assets(double net_assets) {
		this.net_assets = net_assets;
	}

	public double getChange_asset_value() {
		return change_asset_value;
	}

	public void setChange_asset_value(double change_asset_value) {
		this.change_asset_value = change_asset_value;
	}

	public double getChange_pct() {
		return change_pct;
	}

	public void setChange_pct(double change_pct) {
		this.change_pct = change_pct;
	}

	public double getYield_pct() {
		return yield_pct;
	}

	public void setYield_pct(double yield_pct) {
		this.yield_pct = yield_pct;
	}

	public double getReturn_day() {
		return return_day;
	}

	public void setReturn_day(double return_day) {
		this.return_day = return_day;
	}

	public double getReturn_1week() {
		return return_1week;
	}

	public void setReturn_1week(double return_1week) {
		this.return_1week = return_1week;
	}

	public double getReturn_4week() {
		return return_4week;
	}

	public void setReturn_4week(double return_4week) {
		this.return_4week = return_4week;
	}

	public double getReturn_13week() {
		return return_13week;
	}

	public void setReturn_13week(double return_13week) {
		this.return_13week = return_13week;
	}

	public double getReturn_52week() {
		return return_52week;
	}

	public void setReturn_52week(double return_52week) {
		this.return_52week = return_52week;
	}

	public double getReturn_156week() {
		return return_156week;
	}

	public void setReturn_156week(double return_156week) {
		this.return_156week = return_156week;
	}

	public double getReturn_260week() {
		return return_260week;
	}

	public void setReturn_260week(double return_260week) {
		this.return_260week = return_260week;
	}

	public double getIncome_dividend() {
		return income_dividend;
	}

	public void setIncome_dividend(double income_dividend) {
		this.income_dividend = income_dividend;
	}

	public String getIncome_dividend_date() {
		return income_dividend_date;
	}

	public void setIncome_dividend_date(String income_dividend_date) {
		this.income_dividend_date = income_dividend_date;
	}

	public double getCapital_gain() {
		return capital_gain;
	}

	public void setCapital_gain(double capital_gain) {
		this.capital_gain = capital_gain;
	}

	public double getExpense_ratio() {
		return expense_ratio;
	}

	public void setExpense_ratio(double expense_ratio) {
		this.expense_ratio = expense_ratio;
	}

	@Override
	public String toString() {
		return "MutualFundIndex [name=" + name + ", price=" + price + ", close_yesterday=" + close_yesterday
				+ ", return_ytd=" + return_ytd + ", net_assets=" + net_assets + ", change_asset_value="
				+ change_asset_value + ", change_pct=" + change_pct + ", yield_pct=" + yield_pct + ", return_day="
				+ return_day + ", return_1week=" + return_1week + ", return_4week=" + return_4week + ", return_13week="
				+ return_13week + ", return_52week=" + return_52week + ", return_156week=" + return_156week
				+ ", return_260week=" + return_260week + ", income_dividend=" + income_dividend
				+ ", income_dividend_date=" + income_dividend_date + ", capital_gain=" + capital_gain
				+ ", expense_ratio=" + expense_ratio + "]";
	}
	
}
