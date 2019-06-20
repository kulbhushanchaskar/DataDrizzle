package com.datadrizzle.entities;

import java.util.Currency;
import java.util.TimeZone;

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
public class StockAndIndexRealTime {

	/*"symbol": "AAPL",
    "name": "Apple Inc.",
    "price": "150.73",
    "currency": "USD",
    "price_open": "156.86",
    "day_high": "158.16",
    "day_low": "149.63",
    "52_week_high": "233.47",
    "52_week_low": "149.63",
    "day_change": "-6.10",
    "change_pct": "-3.89",
    "close_yesterday": "156.83",
    "market_cap": "715273820265",
    "volume": "95744384",
    "shares": "4745398000",
    "stock_exchange_long": "NASDAQ Stock Exchange",
    "stock_exchange_short": "NASDAQ",
    "timezone": "EST",
    "timezone_name": "America/New_York",
    "gmt_offset": "-18000",
    "last_trade_time": "2018-12-21 16:00:01"*/
	
//	String symbol;
	String name;
	double price;
	String currency;
	double price_open;
	double day_high;
	double day_low;
	double fifty_two_week_high;
	double fifty_two_week_low;
	double day_change;
	double change_pct;
	double close_yesterday;
	double market_cap;
	double volume;
	double shares;
//	String stock_exchange_long;
//	String stock_exchange_short;
//	String timezone;
//	String timezone_name;
//	String gmt_offset;
//	String last_trade_time;
}
