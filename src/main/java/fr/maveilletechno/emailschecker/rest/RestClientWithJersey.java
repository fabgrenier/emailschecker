package fr.maveilletechno.emailschecker.rest;

import java.util.HashMap;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.filter.HttpBasicAuthFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.maveilletechno.emailschecker.entities.MailJetAccountStatus;


public class RestClientWithJersey {
	
	private static Logger LOGGER = LoggerFactory.getLogger(RestClientWithJersey.class);
	
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
	
	public void doRequest(String service){
		try {
			WebTarget servicetarget = webTarget.path(service);
			
			for(String key : params.keySet()){
				servicetarget = servicetarget.queryParam(key, params.get(key));
			}
	 
			MailJetAccountStatus output = servicetarget.request(MediaType.APPLICATION_JSON)
					.get(MailJetAccountStatus.class);

			System.out.println(output);
	 
		} catch (Exception e) {
			LOGGER.error("Http webservice exception", e);
		}
	}
	
}
