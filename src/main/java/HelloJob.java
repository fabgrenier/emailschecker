import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HelloJob implements Job {


	private static final Logger LOGGER = LoggerFactory.getLogger(QuartzTest.class);
	
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		
		LOGGER.info("HelloJob say hello !");
		
		
	}

}
