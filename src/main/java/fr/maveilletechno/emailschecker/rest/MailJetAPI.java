package fr.maveilletechno.emailschecker.rest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.maveilletechno.emailschecker.QuartzLauncher;

public class MailJetAPI {
	private static final Logger LOGGER = LoggerFactory.getLogger(QuartzLauncher.class);

	private static MailJetAPI mailJetAPI;
	
	private RestClient client;

	public static MailJetAPI getInstance() throws IOException{
		if(mailJetAPI == null){
			mailJetAPI = new MailJetAPI();
		}
		return mailJetAPI;
	}
	
	private MailJetAPI() throws IOException {
		
		Properties prop = getCredentialsFromPropertiesFile();
		
		client = new RestClient(prop.getProperty("login"), prop.getProperty("pass"));
		
	}

	private Properties getCredentialsFromPropertiesFile()
			throws FileNotFoundException, IOException {
		Properties prop = new Properties(); 
		FileInputStream in = new FileInputStream("src/main/resources/mailjetcredentials.properties");
		prop.load(in);
		in.close();
		return prop;
	}

	public void messageList(){
		try {
			client.doRequest("messageList");
		} catch (IOException e) {
			LOGGER.error("Error on REST request", e);
		} finally{
			client.closeConnection();
		}
	}
	
}
