package com.datadrizzle.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.datadrizzle.entities.ArchiveJob;
import com.datadrizzle.entities.Chart;
import com.datadrizzle.entities.DataDrizzleConnection;
import com.datadrizzle.entities.RetensionJobEntity;
import com.datadrizzle.entities.SearchForm;
import com.datadrizzle.entities.TableRow;
import com.datadrizzle.share.Response;
import com.datadrizzle.share.services.IDataDrizzleService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ConnectionController {
	
	@Autowired 
	private IDataDrizzleService datadrizzleService;
	 

	@RequestMapping(method = RequestMethod.POST, value = "/create/connection")
	ResponseEntity<Response<List<Chart<String, Integer>>>> createConnection(@RequestBody DataDrizzleConnection connection) {
		
		Response<List<String>> serviceResp = datadrizzleService.testConnection(connection);
		
		HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "ConnectionController");
        List<String> msg = new ArrayList<>();
        msg.add("first msg");
        msg.add("second msg");
        
//        return ResponseEntity.accepted().headers(headers).body(new Response<List<Chart<String, Integer>>>(msg, serviceResp.getResult(), "200"));
        return null;
		
	}
	
    @GetMapping("/welcome/user")
    @ResponseBody
    public String welcomeUser(@RequestParam(name="name", required=false, defaultValue="Java Fan") String name) {
        return "Welcome";
    }
    
    
    @RequestMapping(method = RequestMethod.POST, value = "/test/connection")
	ResponseEntity<Response<List<String>>> testConnection(@RequestBody DataDrizzleConnection connection) {
		Response<List<String>> serviceResp = datadrizzleService.testConnection(connection);
		
		HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "ConnectionController");
        List<String> msg = new ArrayList<>();
        msg.add("first msg");
        msg.add("second msg");
        
        return ResponseEntity.accepted().headers(headers).body(serviceResp);
	}
    
    @RequestMapping(method = RequestMethod.POST, value = "/archive/connection")
	ResponseEntity<Response<String>> scheduleArchiveJob(@RequestBody ArchiveJob job) {
		Response<String> serviceResp = datadrizzleService.scheduleArchiveJob(job);
		
		HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "ConnectionController");
        List<String> msg = new ArrayList<>();
        msg.add("first msg");
        msg.add("second msg");
        
        return ResponseEntity.accepted().headers(headers).body(serviceResp);
	}
    
    @RequestMapping(method = RequestMethod.POST, value = "/retension/connection")
	ResponseEntity<Response<String>> scheduleRetensionJob(@RequestBody RetensionJobEntity job) {
		Response<String> serviceResp = datadrizzleService.scheduleRetensionJob(job);
		
		HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "ConnectionController");
        List<String> msg = new ArrayList<>();
        msg.add("first msg");
        msg.add("second msg");
        
        return ResponseEntity.accepted().headers(headers).body(serviceResp);
	}
    
    @RequestMapping(method = RequestMethod.POST, value = "/getdata/connection")
	ResponseEntity<Response<List<TableRow>>> getData(@RequestBody SearchForm job) {
		Response<List<TableRow>> serviceResp = datadrizzleService.getData(job);
		
		HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "ConnectionController");
        List<String> msg = new ArrayList<>();
        msg.add("first msg");
        msg.add("second msg");
        
        return ResponseEntity.accepted().headers(headers).body(serviceResp);
	}
    
    @GetMapping(
    		  value = "/getfile",
    		  produces = MediaType.IMAGE_JPEG_VALUE
    		)
    		public @ResponseBody ResponseEntity<Response<byte[]>> getImageWithMediaType(@RequestParam("id") String id) throws IOException {
    	HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "ConnectionController");
        List<String> msg = new ArrayList<>();
        msg.add("first msg");
        msg.add("second msg");
        
    	Response<byte[]> serviceResp = datadrizzleService.getFile(id);
    	 return ResponseEntity.accepted().headers(headers).body(serviceResp);
    	
    		}
    
}