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
	
	String symbol;
	String name;
	double price;
	Currency currency;
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
	double stock_exchange_long;
	double stock_exchange_short;
	TimeZone timezone;
	String timezone_name;
	String gmt_offset;
	double last_trade_time;
	public String getSymbol() {
		return symbol;
	}
	public StockAndIndexRealTime setSymbol(String symbol) {
		this.symbol = symbol;
		return this;
	}
	public String getName() {
		return name;
	}
	public StockAndIndexRealTime setName(String name) {
		this.name = name;
		return this;
	}
	public double getPrice() {
		return price;
	}
	public StockAndIndexRealTime setPrice(double price) {
		this.price = price;
		return this;
	}
	public Currency getCurrency() {
		return currency;
	}
	public StockAndIndexRealTime setCurrency(Currency currency) {
		this.currency = currency;
		return this;
	}
	public double getPrice_open() {
		return price_open;
	}
	public StockAndIndexRealTime setPrice_open(double price_open) {
		this.price_open = price_open;
		return this;
	}
	public double getDay_high() {
		return day_high;
	}
	public StockAndIndexRealTime setDay_high(double day_high) {
		this.day_high = day_high;
		return this;
	}
	public double getDay_low() {
		return day_low;
	}
	public StockAndIndexRealTime setDay_low(double day_low) {
		this.day_low = day_low;
		return this;
	}
	public double getFifty_two_week_high() {
		return fifty_two_week_high;
	}
	public StockAndIndexRealTime setFifty_two_week_high(double fifty_two_week_high) {
		this.fifty_two_week_high = fifty_two_week_high;
		return this;
	}
	public double getFifty_two_week_low() {
		return fifty_two_week_low;
	}
	public StockAndIndexRealTime setFifty_two_week_low(double fifty_two_week_low) {
		this.fifty_two_week_low = fifty_two_week_low;
		return this;
	}
	public double getDay_change() {
		return day_change;
	}
	public StockAndIndexRealTime setDay_change(double day_change) {
		this.day_change = day_change;
		return this;
	}
	public double getChange_pct() {
		return change_pct;
	}
	public StockAndIndexRealTime setChange_pct(double change_pct) {
		this.change_pct = change_pct;
		return this;
	}
	public double getClose_yesterday() {
		return close_yesterday;
	}
	public StockAndIndexRealTime setClose_yesterday(double close_yesterday) {
		this.close_yesterday = close_yesterday;
		return this;
	}
	public double getMarket_cap() {
		return market_cap;
	}
	public StockAndIndexRealTime setMarket_cap(double market_cap) {
		this.market_cap = market_cap;
		return this;
	}
	public double getVolume() {
		return volume;
	}
	public StockAndIndexRealTime setVolume(double volume) {
		this.volume = volume;
		return this;
	}
	public double getShares() {
		return shares;
	}
	public StockAndIndexRealTime setShares(double shares) {
		this.shares = shares;
		return this;
	}
	public double getStock_exchange_long() {
		return stock_exchange_long;
	}
	public StockAndIndexRealTime setStock_exchange_long(double stock_exchange_long) {
		this.stock_exchange_long = stock_exchange_long;
		return this;
	}
	public double getStock_exchange_short() {
		return stock_exchange_short;
	}
	public StockAndIndexRealTime setStock_exchange_short(double stock_exchange_short) {
		this.stock_exchange_short = stock_exchange_short;
		return this;
	}
	public TimeZone getTimezone() {
		return timezone;
	}
	public StockAndIndexRealTime setTimezone(TimeZone timezone) {
		this.timezone = timezone;
		return this;
	}
	public String getTimezone_name() {
		return timezone_name;
	}
	public StockAndIndexRealTime setTimezone_name(String timezone_name) {
		this.timezone_name = timezone_name;
		return this;
	}
	public String getGmt_offset() {
		return gmt_offset;
	}
	public StockAndIndexRealTime setGmt_offset(String gmt_offset) {
		this.gmt_offset = gmt_offset;
		return this;
	}
	public double getLast_trade_time() {
		return last_trade_time;
	}
	public StockAndIndexRealTime setLast_trade_time(double last_trade_time) {
		this.last_trade_time = last_trade_time;
		return this;
	}
	
	@Override
	public String toString() {
		return "StockAndIndexRealTime [symbol=" + symbol + ", name=" + name + ", price=" + price + ", currency="
				+ currency + ", price_open=" + price_open + ", day_high=" + day_high + ", day_low=" + day_low
				+ ", fifty_two_week_high=" + fifty_two_week_high + ", fifty_two_week_low=" + fifty_two_week_low
				+ ", day_change=" + day_change + ", change_pct=" + change_pct + ", close_yesterday=" + close_yesterday
				+ ", market_cap=" + market_cap + ", volume=" + volume + ", shares=" + shares + ", stock_exchange_long="
				+ stock_exchange_long + ", stock_exchange_short=" + stock_exchange_short + ", timezone=" + timezone
				+ ", timezone_name=" + timezone_name + ", gmt_offset=" + gmt_offset + ", last_trade_time="
				+ last_trade_time + "]";
	}
}
