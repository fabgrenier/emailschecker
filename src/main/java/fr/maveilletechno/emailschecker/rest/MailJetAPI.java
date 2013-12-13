package fr.maveilletechno.emailschecker.rest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.maveilletechno.emailschecker.QuartzLauncher;
import fr.maveilletechno.emailschecker.entities.MailJetEmailSentListStatus;

public class MailJetAPI {
	private static final Logger LOGGER = LoggerFactory.getLogger(QuartzLauncher.class);

	private static MailJetAPI mailJetAPI;
	
	private RestClientWithJersey clientJersey;

	public static MailJetAPI getInstance() throws IOException{
		if(mailJetAPI == null){
			mailJetAPI = new MailJetAPI();
		}
		return mailJetAPI;
	}
	
	private MailJetAPI() throws IOException {
		Properties prop = getCredentialsFromPropertiesFile();
		clientJersey = new RestClientWithJersey(prop.getProperty("login"), prop.getProperty("pass"));
	}

	private Properties getCredentialsFromPropertiesFile()
			throws FileNotFoundException, IOException {
		Properties prop = new Properties(); 
		FileInputStream in = new FileInputStream("src/main/resources/mailjetcredentials.properties");
		prop.load(in);
		in.close();
		return prop;
	}

	public MailJetEmailSentListStatus reportEmailsent(){
		WebTarget webService = clientJersey.doRequest("reportEmailsent");
		MailJetEmailSentListStatus output = webService.request(MediaType.APPLICATION_JSON)
				.get(MailJetEmailSentListStatus.class);

		LOGGER.debug(output.toString());
		return output;
	}
	
}
