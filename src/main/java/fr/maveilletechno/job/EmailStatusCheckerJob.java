package fr.maveilletechno.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.maveilletechno.QuartzLauncher;


public class EmailStatusCheckerJob implements Job {

	private static final Logger LOGGER = LoggerFactory.getLogger(QuartzLauncher.class);
	
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		
		LOGGER.info("EmailStatusCheckerJob launched !");
		
		
	}

}
