package com.datadrizzle.share.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.datadrizzle.entities.Chart;
import com.datadrizzle.entities.DataDrizzleConnection;
import com.datadrizzle.share.Either;
import com.datadrizzle.share.Notification;
import com.datadrizzle.share.Response;

public interface IDataDrizzleService {
	public Either<Notification, List<Chart<String,Integer>>> testConnection(DataDrizzleConnection connection);
	public Either<Notification, List<Chart<String, Double>>> getStockAndIndexPrice(List<String> companyNames);
	public Either<Notification, List<Chart<String, Double>>> getMutualFundIndexes(List<String> companyNames);
	public Either<Notification, List<String>> getMutualfundSymbols();
}
