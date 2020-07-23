package com.datadrizzle.share.services;

import java.util.List;

import com.datadrizzle.entities.ArchiveJob;
import com.datadrizzle.entities.Chart;
import com.datadrizzle.entities.DataDrizzleConnection;
import com.datadrizzle.entities.MutualFundHistoryData;
import com.datadrizzle.entities.RetensionJobEntity;
import com.datadrizzle.entities.SearchForm;
import com.datadrizzle.entities.TableRow;
import com.datadrizzle.share.Either;
import com.datadrizzle.share.Notification;
import com.datadrizzle.share.Response;
import com.datadrizzle.ui.model.MutualFundCompany;

public interface IDataDrizzleService {
	public Response<List<String>> testConnection(DataDrizzleConnection connection);
	public Response<String> scheduleArchiveJob(ArchiveJob job);
	public Response<String> scheduleRetensionJob(RetensionJobEntity job);
	public Either<Notification, List<Chart<String, Double>>> getStockAndIndexPrice(List<String> companyNames);
	public Either<Notification, List<Chart<String, Double>>> getMutualFundIndexes(List<String> companyNames);
	public Either<Notification, List<MutualFundCompany>> getMutualfundSymbols();
	public Either<Notification, List<MutualFundHistoryData>> getMutualfundHistory(String symbol);
	public Response<List<TableRow>> getData(SearchForm form);
	public Response<byte[]> getFile(String id);
}
