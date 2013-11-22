package fr.maveilletechno.emailschecker.rest;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.maveilletechno.emailschecker.entities.MailJetAccountStatus;

public class RestClientWithHttpClient {

	private static final String API_URL = "http://api.mailjet.com/0.1/";
	private static final String OUTPUT = "?output=json";
	
	private HashMap<String, String> params = new HashMap<String, String>();
	
	private DefaultHttpClient httpClient;
	
	public RestClientWithHttpClient(String username, String password, String[]... parameters) {
		// create default HTTP Client
        httpClient = new DefaultHttpClient();
        
        Credentials credentials = new UsernamePasswordCredentials(username, password);
        httpClient.getCredentialsProvider().setCredentials(AuthScope.ANY, credentials);
        
        for(String[] param : parameters){
        	params.put(param[0], param[1]);
        }
	}
	
	public void doRequest(String service) throws ClientProtocolException, IOException {

		StringBuilder paramStr = new StringBuilder();
		for(String key : params.keySet()){
			paramStr.append("&").append(key).append("=").append(params.get(key));
		}
		
        // Create new getRequest with below mentioned URL
        HttpGet getRequest = new HttpGet(API_URL+service+OUTPUT+paramStr.toString());
        
        //Send the request; It will immediately return the response in HttpResponse object
        HttpResponse response = httpClient.execute(getRequest);
         
        //verify the valid error code first
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            throw new RuntimeException("Failed with HTTP error code : " + statusCode);
        }
         
        //Now pull back the response object
        HttpEntity httpEntity = response.getEntity();
        String apiOutput = EntityUtils.toString(httpEntity);
        
        //Lets see what we got from API
        System.out.println(apiOutput);
        
        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
        MailJetAccountStatus emailStatus = mapper.readValue(apiOutput, MailJetAccountStatus.class);
        
        System.out.println(emailStatus);
	}

	public void closeConnection() {
		if(httpClient.getConnectionManager() != null){
			httpClient.getConnectionManager().shutdown();
		}
	}
}
