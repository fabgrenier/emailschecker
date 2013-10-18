package fr.maveilletechno.emailschecker.job;

import java.io.IOException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.maveilletechno.emailschecker.QuartzLauncher;
import fr.maveilletechno.emailschecker.rest.MailJetAPI;


public class EmailStatusCheckerJob implements Job {

	private static final Logger LOGGER = LoggerFactory.getLogger(QuartzLauncher.class);
	
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		
		LOGGER.info("EmailStatusCheckerJob launched !");
		
		try {
			MailJetAPI.getInstance().messageList();
		} catch (IOException e) {
			LOGGER.error("unabled to load Mailjet credentials ! ",e);
		}
		
	}

}
