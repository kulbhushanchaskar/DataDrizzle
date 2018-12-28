package com.datadrizzle.entities;

import java.util.Currency;
import java.util.TimeZone;

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
//	String currency;
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
	
	public StockAndIndexRealTime() {
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

	public double getPrice_open() {
		return price_open;
	}

	public void setPrice_open(double price_open) {
		this.price_open = price_open;
	}

	public double getDay_high() {
		return day_high;
	}

	public void setDay_high(double day_high) {
		this.day_high = day_high;
	}

	public double getDay_low() {
		return day_low;
	}

	public void setDay_low(double day_low) {
		this.day_low = day_low;
	}

	public double getFifty_two_week_high() {
		return fifty_two_week_high;
	}

	public void setFifty_two_week_high(double fifty_two_week_high) {
		this.fifty_two_week_high = fifty_two_week_high;
	}

	public double getFifty_two_week_low() {
		return fifty_two_week_low;
	}

	public void setFifty_two_week_low(double fifty_two_week_low) {
		this.fifty_two_week_low = fifty_two_week_low;
	}

	public double getDay_change() {
		return day_change;
	}

	public void setDay_change(double day_change) {
		this.day_change = day_change;
	}

	public double getChange_pct() {
		return change_pct;
	}

	public void setChange_pct(double change_pct) {
		this.change_pct = change_pct;
	}

	public double getClose_yesterday() {
		return close_yesterday;
	}

	public void setClose_yesterday(double close_yesterday) {
		this.close_yesterday = close_yesterday;
	}

	public double getMarket_cap() {
		return market_cap;
	}

	public void setMarket_cap(double market_cap) {
		this.market_cap = market_cap;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getShares() {
		return shares;
	}

	public void setShares(double shares) {
		this.shares = shares;
	}

	@Override
	public String toString() {
		return "StockAndIndexRealTime [name=" + name + ", price=" + price + ", price_open=" + price_open + ", day_high="
				+ day_high + ", day_low=" + day_low + ", fifty_two_week_high=" + fifty_two_week_high
				+ ", fifty_two_week_low=" + fifty_two_week_low + ", day_change=" + day_change + ", change_pct="
				+ change_pct + ", close_yesterday=" + close_yesterday + ", market_cap=" + market_cap + ", volume="
				+ volume + ", shares=" + shares + "]";
	}
}
