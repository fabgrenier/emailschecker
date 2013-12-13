package fr.maveilletechno.emailschecker.job;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.maveilletechno.emailschecker.QuartzLauncher;
import fr.maveilletechno.emailschecker.entities.MailjetEmailStatus;
import fr.maveilletechno.emailschecker.rest.MailJetAPI;

public class EmailStatusCheckerJob implements Job {

	private static final Logger LOGGER = LoggerFactory.getLogger(QuartzLauncher.class);
	
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		
		LOGGER.info("EmailStatusCheckerJob launched !");
		
		try {
			Set<MailjetEmailStatus> emails = MailJetAPI.getInstance()
												.reportEmailsent()
												.getEmails();
			
			Map<String, Integer> status = new HashMap<>();
			for(MailjetEmailStatus email: emails){
				if(email.getStatus().equals(MailjetEmailStatus.statusValues.queued.toString())){
					LOGGER.debug("email to "+email.getTo_email()+" with subject "+email.getSubject()+" was not send so far");
				} else {
					if(status.get(email.getStatus()) != null){
						status.put(email.getStatus(), Integer.valueOf((status.get(email.getStatus()) +1)));
					} else {
						status.put(email.getStatus(), Integer.valueOf(1));
					}
				}
			}
			
			for(String key: status.keySet()){
				LOGGER.debug(status.get(key)+" email with status "+key);
			}
			
		} catch (IOException e) {
			LOGGER.error("unabled to load Mailjet credentials ! ",e);
		}
		
	}

}
