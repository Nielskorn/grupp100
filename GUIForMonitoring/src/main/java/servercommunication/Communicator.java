package servercommunication;

import java.net.URI;
import java.net.URISyntaxException;

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
		HttpEntity<String> requestEntity = new HttpEntity<>("testConnection", headers);
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

}
