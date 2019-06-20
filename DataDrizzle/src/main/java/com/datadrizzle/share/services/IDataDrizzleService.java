package com.datadrizzle.share.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.datadrizzle.entities.Chart;
import com.datadrizzle.entities.DataDrizzleConnection;
import com.datadrizzle.entities.MutualFundHistoryData;
import com.datadrizzle.share.Either;
import com.datadrizzle.share.Notification;
import com.datadrizzle.share.Response;
import com.datadrizzle.ui.model.MutualFundCompany;

public interface IDataDrizzleService {
	public Either<Notification, List<Chart<String,Integer>>> testConnection(DataDrizzleConnection connection);
	public Either<Notification, List<Chart<String, Double>>> getStockAndIndexPrice(List<String> companyNames);
	public Either<Notification, List<Chart<String, Double>>> getMutualFundIndexes(List<String> companyNames);
	public Either<Notification, List<MutualFundCompany>> getMutualfundSymbols();
	public Either<Notification, List<MutualFundHistoryData>> getMutualfundHistory(String symbol);
}
