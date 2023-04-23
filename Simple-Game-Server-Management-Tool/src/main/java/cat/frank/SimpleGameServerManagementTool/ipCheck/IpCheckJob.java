package cat.frank.SimpleGameServerManagementTool.ipCheck;

import cat.frank.SimpleGameServerManagementTool.utility.StaticVariables;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IpCheckJob implements Job {

    // Logger for the ipCheckJob class
    private final Logger logger = LoggerFactory.getLogger(IpCheckJob.class);

    private final IPService ipService;

    @Autowired
    public IpCheckJob(IPService ipService) {
        this.ipService = ipService;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        String jobName = dataMap.getString(StaticVariables.IP_Check_Job_Name_Key);
        int jobID = dataMap.getInt(StaticVariables.IP_Check_Job_ID_Key);
        logger.info("Job: {} with ID: {} is running ...", jobName, jobID);
        ipService.checkIfIpChanged();
    }
}
