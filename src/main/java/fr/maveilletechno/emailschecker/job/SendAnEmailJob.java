package fr.maveilletechno.emailschecker.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.maveilletechno.emailschecker.entities.EmailToSend;

public class SendAnEmailJob implements Job {

	private static final Logger LOGGER = LoggerFactory.getLogger(SendAnEmailJob.class);
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		
		EmailToSend email = (EmailToSend) context.getJobDetail().getJobDataMap().get("email");
		
		LOGGER.debug("email : "+ email);
		
		
	}

}
