package cat.frank.SimpleGameServerManagementTool.backgroud_ipCheck;

import cat.frank.SimpleGameServerManagementTool.utility.StaticVariables;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Calendar;

import java.util.Date;

@Component
public class IpCheckJob implements Job {

    // Logger for the ipCheckJob class
    private final Logger logger = LoggerFactory.getLogger(IpCheckJob.class);

    private final IPService ipService;
    private final IpCheckJobService ipCheckJobService;

    @Autowired
    public IpCheckJob(IPService ipService, IpCheckJobService ipCheckJobService) {
        this.ipCheckJobService = ipCheckJobService;
        this.ipService = ipService;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        String jobName = dataMap.getString(StaticVariables.IP_Check_Job_Name_Key);
        Long jobID = dataMap.getLong(StaticVariables.IP_Check_Job_ID_Key);
        logger.info("Job: {} with ID: {} is running ...", jobName, jobID);
        ipService.checkIfIpChanged();
        IpCheckJobModel ipCheckJobModel = ipCheckJobService.getIpCheckJobModelByJobID(jobID);
        Date currDate = new Date();
        ipCheckJobModel.setPreviousFireTime(currDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(currDate);               // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, ipCheckJobModel.getRepeatIntervalHour().intValue());
        ipCheckJobModel.setNextFireTime(cal.getTime());
        ipCheckJobService.updateIpCheckJobModel(ipCheckJobModel);
    }
}
