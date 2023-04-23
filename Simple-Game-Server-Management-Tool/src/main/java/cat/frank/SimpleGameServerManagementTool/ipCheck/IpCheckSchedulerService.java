package cat.frank.SimpleGameServerManagementTool.ipCheck;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static cat.frank.SimpleGameServerManagementTool.utility.StaticVariables.IP_Check_Job_ID_Key;
import static cat.frank.SimpleGameServerManagementTool.utility.StaticVariables.IP_Check_Job_Name_Key;

@Service
public class IpCheckSchedulerService {

    // make a singleton scheduler object
    private final Scheduler scheduler;
    private final IpCheckJobService ipCheckJobService;
    private final String jobName= "IP_Check_Job";
    private final String jobGroupName = "IP_Check_Job_Group";
    private Long jobID = 0L;

    // Logger for the ipCheckSchedulerService class
    private final Logger logger = LoggerFactory.getLogger(IpCheckSchedulerService.class);

    @Autowired
    public IpCheckSchedulerService(Scheduler scheduler, IpCheckJobService ipCheckJobService) throws SchedulerException {
        this.scheduler = scheduler;
        this.ipCheckJobService = ipCheckJobService;
    }

    // create an IP check job
    public void createIpCheckJob() throws SchedulerException {
        logger.info("Creating IP check job with JobKey name: {}, and JobKey group name: {} ...",
                jobName, jobGroupName);
        // build the job
        JobKey jobKey = new JobKey(jobName, jobGroupName);
        JobDetail job = JobBuilder.newJob(IpCheckJob.class).withIdentity(jobKey).build();
        JobDataMap jobDataMap = job.getJobDataMap();
        jobDataMap.put(IP_Check_Job_Name_Key, jobName);
        jobDataMap.putAsString(IP_Check_Job_ID_Key, jobID);
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("IP_Check_Trigger", "IP_Check_Trigger_Group")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInHours(1)
                .repeatForever())
                .build();

        ipCheckJobService.updateIpCheckJobModel(new IpCheckJobModel(jobName, jobGroupName, jobID++, 1L));

        // if the scheduler is not started, start it
        if (!scheduler.isStarted()) {
            try {
                logger.info("The scheduler is not started, Starting scheduler ...");
                scheduler.start();
            } catch (SchedulerException e) {
                logger.error("Failed to start the scheduler ...", e);
                e.printStackTrace();
            }
        }

        // schedule the job
        try {
            logger.info("Scheduling the IP check job ...");
            IpCheckJobModel ipCheckJobModel = ipCheckJobService
                    .getIpCheckJobModelByJobNameAndGroup(jobName, jobGroupName);
            ipCheckJobModel.setStartTime(new Date());
            ipCheckJobModel.setRunning(true);
            scheduler.scheduleJob(job, trigger);
            ipCheckJobService.updateIpCheckJobModel(ipCheckJobModel);
        } catch (SchedulerException e) {
            logger.error("Failed to schedule the IP check job ...", e);
            e.printStackTrace();
        }
    }

    // stop job and delete the IP check job
    public void stopAndDeleteIpCheckJob() throws SchedulerException {
        // stop the job
        scheduler.shutdown();

        // delete the job
        JobKey jobKey = new JobKey(jobName, jobGroupName);
        scheduler.deleteJob(jobKey);
        IpCheckJobModel ipCheckJobModel = ipCheckJobService
                .getIpCheckJobModelByJobNameAndGroup(jobName, jobGroupName);
        ipCheckJobModel.setRunning(false);
        ipCheckJobService.updateIpCheckJobModel(ipCheckJobModel);
    }

    public void interruptIpCheckJob() throws SchedulerException {
        // interrupt the job
        JobKey jobKey = new JobKey(jobName, jobGroupName);
        scheduler.interrupt(jobKey);
    }

    /*
     * List<JobExecutionContext> currentlyExecuting = schedulerFactoryBean.getCurrentlyExecutingJobs();
     *
     * //verifying if job is running
     * for (JobExecutionContext jobExecutionContext : currentlyExecuting) {
     *     if(jobExecutionContext.getJobDetail().getKey().getName().equals("JobKeyNameToInterrupt")){
     *         result = schedulerFactoryBean.interrupt(jobExecutionContext.getJobDetail().getKey());
     *     }
     * }
     */

}
