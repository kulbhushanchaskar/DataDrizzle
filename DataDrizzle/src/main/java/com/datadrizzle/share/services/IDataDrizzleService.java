package com.datadrizzle.share.services;

import java.util.List;

import com.datadrizzle.entities.Chart;
import com.datadrizzle.entities.DataDrizzleConnection;
import com.datadrizzle.share.Either;
import com.datadrizzle.share.Notification;

public interface IDataDrizzleService {
	public Either<Notification, List<Chart<String,Integer>>> testConnection(DataDrizzleConnection connection);
}
