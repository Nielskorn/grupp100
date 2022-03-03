package servercommunication;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Communicator {
	
	RestTemplate connector;
	String serverUrl ;
	
	public Communicator(String serverUrl) {
		connector = new RestTemplate();
	    this.serverUrl = serverUrl;
	}
	
	public boolean testConnection() throws URISyntaxException {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
		Class<Byte> expectedClass = Byte.class;
		String method = "/connection/test";
		ResponseEntity<Byte> response;
		try {
			response = connector.exchange(serverUrl + method, HttpMethod.GET, requestEntity, expectedClass);
		}catch(IllegalArgumentException e) {
			throw new URISyntaxException(serverUrl+method,e.getCause().getMessage());
		}
		return response.getStatusCode()==HttpStatus.OK;
	}
	
	public HashMap<String,Double> getThresholds(){
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
		Class<HashMap<String,Double>> expectedClass = (Class<HashMap<String, Double>>) new HashMap<String,Double>().getClass();
		String method = "/thresholds/getThresholds";
		ResponseEntity<HashMap<String,Double>> response = null;
		try {
			response = connector.exchange(serverUrl + method, HttpMethod.GET, requestEntity, expectedClass);
		}catch(IllegalArgumentException e) {

		}
		if(response != null && response.getStatusCode().is2xxSuccessful())
			return response.getBody();
		else
			return new HashMap<String,Double>();
	}
	
	public boolean changeThreshold(String thresholdName , double newValue) {
		HashMap<String, Double> postBody = new HashMap<>();
		postBody.put(thresholdName, newValue);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<HashMap<String,Double>> requestEntity = new HttpEntity<>(postBody,headers);
		Class<Byte> expectedClass = Byte.class;
		String method = "/thresholds/changeThreshold";
		ResponseEntity<Byte> response = null;
		try {
			response = connector.exchange(serverUrl + method, HttpMethod.POST, requestEntity, expectedClass);
		}catch(IllegalArgumentException e) {
		}
		if(response != null)
			return response.getStatusCode()==HttpStatus.OK;
		return false;
	}
	
	public HashMap<String, Double> getIndividualDiskUsages(){
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<>("",headers);
		Class<HashMap<String,Double>> expectedClass = (Class<HashMap<String, Double>>) new HashMap<String,Double>().getClass();
		String method = "/database/diskUsage";
		ResponseEntity<HashMap<String,Double>> response = null;
		try {
			response = connector.exchange(serverUrl + method, HttpMethod.GET, requestEntity, expectedClass);
		}catch(IllegalArgumentException e) {
		}
		if(response != null && response.getStatusCode().is2xxSuccessful())
			return response.getBody();
		else
			return new HashMap<String,Double>();
	}
	
	public double getOverAllDiskUsage() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<>("",headers);
		Class<Double> expectedClass = Double.class;
		String method = "/database/overAllDiskUsage";
		ResponseEntity<Double> response = null;
		try {
			response = connector.exchange(serverUrl + method, HttpMethod.GET, requestEntity, expectedClass);
		}catch(IllegalArgumentException e) {
		}
		if(response != null && response.getStatusCode().is2xxSuccessful())
			return response.getBody();
		else
			return 0.0;
	}
	

}
