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

public class QuartzTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(QuartzTest.class);
	
    public static void main(String[] args) {
    	Scheduler scheduler = null;
        try {
            // Grab the Scheduler instance from the Factory
        	scheduler = StdSchedulerFactory.getDefaultScheduler();

            // and start it off
            scheduler.start();
            
            LOGGER.info(scheduler.getSchedulerName());
            
            // define the job and tie it to our HelloJob class
            JobDetail job = newJob(HelloJob.class)
                .withIdentity("job1", "group1")
                .build();

            // Trigger the job to run now, and then repeat every 40 seconds
            Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(40)
                        .repeatForever())
                .build();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);
            
            Thread.sleep(6000);
            
            scheduler.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        } catch (InterruptedException e) {
			e.printStackTrace();
		} 
    }
}