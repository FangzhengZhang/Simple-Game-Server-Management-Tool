package cat.frank.SimpleGameServerManagementTool.logManagement;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogManagementJob implements Job {

    // Logger for the LogManagementJob class
    private final Logger logger = LoggerFactory.getLogger(LogManagementJob.class);
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

    }
}
