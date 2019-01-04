package com.datadrizzle.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.datadrizzle.entities.Chart;
import com.datadrizzle.share.Either;
import com.datadrizzle.share.Notification;
import com.datadrizzle.share.Response;
import com.datadrizzle.share.services.IDataDrizzleService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class StockController {

	@Autowired
	private IDataDrizzleService datadrizzleService;

	@RequestMapping(method = RequestMethod.POST, value = "/stock/getStockPrice")
	ResponseEntity<Response<List<Chart<String, Double>>>> getStockPrice(@RequestBody List<String> companyNames) {

		Either<Notification, List<Chart<String, Double>>> serviceResp = datadrizzleService
				.getStockAndIndexPrice(companyNames);

		if (!serviceResp.hasNotification())
			System.out.println(serviceResp.getResult());

		HttpHeaders headers = new HttpHeaders();
		headers.add("Responded", "StockController");
		List<String> msg = new ArrayList<>();
		msg.add("first msg");
		msg.add("second msg");

		return ResponseEntity.accepted().headers(headers)
				.body(new Response<List<Chart<String, Double>>>(msg, serviceResp.getResult(), "200"));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/mutualfund")
	ResponseEntity<Response<List<Chart<String, Double>>>> getMutualfund(@RequestBody List<String> companyNames) {
		
		Either<Notification, List<Chart<String, Double>>> serviceResp = datadrizzleService
				.getMutualFundIndexes(companyNames);

		if (!serviceResp.hasNotification())
			System.out.println(serviceResp.getResult());

		HttpHeaders headers = new HttpHeaders();
		headers.add("Responded", "StockController");
		List<String> msg = new ArrayList<>();
		msg.add("first msg");
		msg.add("second msg");

		return ResponseEntity.accepted().headers(headers)
				.body(new Response<List<Chart<String, Double>>>(msg, serviceResp.getResult(), "200"));
	}

}
