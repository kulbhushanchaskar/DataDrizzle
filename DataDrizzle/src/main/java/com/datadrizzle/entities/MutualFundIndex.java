package com.datadrizzle.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
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
}
