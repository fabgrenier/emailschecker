package fr.maveilletechno.emailschecker.rest;

import java.util.HashMap;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.filter.HttpBasicAuthFilter;
import org.glassfish.jersey.jackson.JacksonFeature;

public class RestClientWithJersey {
	
//	private static Logger LOGGER = LoggerFactory.getLogger(RestClientWithJersey.class);
	
	private static final String API_URL = "http://api.mailjet.com/0.1/";
	private HashMap<String, String> params = new HashMap<String, String>();
	private WebTarget webTarget;
	
	public RestClientWithJersey(String username, String password, String[]... parameters) {
		
		params.put("output", "json");
		for(String[] param : parameters){
	    	params.put(param[0], param[1]);
	    }
		 
		Client client = ClientBuilder.newBuilder()
		      .register(JacksonFeature.class)
			  .build()
			  .register(new HttpBasicAuthFilter(username, password));
		webTarget = client.target(API_URL);
		
	}
	
	public WebTarget doRequest(String service, String[]... parameters){
		WebTarget servicetarget = webTarget.path(service);
		
		for(String key : params.keySet()){
			servicetarget = servicetarget.queryParam(key, params.get(key));
		}
		
		for(String[] param : parameters){
			servicetarget = servicetarget.queryParam(param[0], param[1]);
		}
 
		return servicetarget; 
	}
	
}
