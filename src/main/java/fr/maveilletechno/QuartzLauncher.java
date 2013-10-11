package fr.maveilletechno;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.maveilletechno.job.EmailStatusCheckerJob;

public class QuartzLauncher {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(QuartzLauncher.class);
	
	private static final String EMAIL_STATUS_CHECKER_TRIGGER = "EmailStatusCheckerTrigger";
	private static final String EMAIL_CHECKER_GROUP = "EmailCheckerGroup";
	private static final String EMAIL_STATUS_CHECKER_JOB = "EmailStatusCheckerJob";
	
    public static void main(String[] args) {
        try {
            // Grab the Scheduler instance from the Factory
        	final Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            LOGGER.info(scheduler.getSchedulerName()+" starting...");
        	
            // and start it off
            scheduler.start();
            
            // define the job and tie it to our HelloJob class
            JobDetail job = newJob(EmailStatusCheckerJob.class)
                .withIdentity(EMAIL_STATUS_CHECKER_JOB, EMAIL_CHECKER_GROUP)
                .build();

            // Trigger the job to run now, and then repeat every 40 seconds
            Trigger trigger = newTrigger()
                .withIdentity(EMAIL_STATUS_CHECKER_TRIGGER, EMAIL_CHECKER_GROUP)
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInMinutes(1)
                        .repeatForever())
                .build();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);
            
            // Listen close asked by user
            Runtime.getRuntime().addShutdownHook(new Thread() {
	    	   @Override
	    	   public void run() {
	    		   try {
	    			   LOGGER.info(scheduler.getSchedulerName()+ " shutdown in progress..");
	    			   scheduler.shutdown();
	    		   } catch (SchedulerException e) {
	    			   e.printStackTrace();
	    		   }
	    	   }
            });
            

        } catch (SchedulerException se) {
            se.printStackTrace();
        } 
    }
}