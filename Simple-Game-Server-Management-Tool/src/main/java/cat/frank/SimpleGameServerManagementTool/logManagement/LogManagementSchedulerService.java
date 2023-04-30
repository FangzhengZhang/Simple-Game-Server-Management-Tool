package cat.frank.SimpleGameServerManagementTool.logManagement;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static cat.frank.SimpleGameServerManagementTool.utility.StaticVariables.*;

@Service
public class LogManagementSchedulerService {
    // make a singleton scheduler object
    private final Scheduler scheduler;
    private final LogManagementJobService logManagementJobService;
    private final LogManagementInfoService logManagementInfoService;
    private final String jobName= "Log_Management_Job";
    private final String jobGroupName = "Log_Management_Job_Group";
    private Long jobID = 0L;

    // Logger for the LogManagementSchedulerService class
    private final Logger logger = LoggerFactory.getLogger(LogManagementSchedulerService.class);

    @Autowired
    public LogManagementSchedulerService(Scheduler scheduler,
                                         LogManagementInfoService logManagementInfoService,
                                         LogManagementJobService logManagementJobService) {
        this.scheduler = scheduler;
        this.logManagementInfoService = logManagementInfoService;
        this.logManagementJobService = logManagementJobService;
    }

    // create a log management job
    public void createLogManagementJob() throws SchedulerException {
        logger.info("Creating log management job with JobKey name: {}, and JobKey group name: {} ...",
                jobName, jobGroupName);
        LogManagementInfoModel jobInfo = logManagementInfoService.getLogManagementInfo();
        JobKey jobKey = new JobKey(jobName, jobGroupName);
        JobDetail job = JobBuilder.newJob(LogManagementJob.class).withIdentity(jobKey).build();
        JobDataMap jobDataMap = job.getJobDataMap();
        jobDataMap.put(Log_Management_Job_Name_Key, jobName);
        jobDataMap.put(Log_Management_Job_ID_Key, jobID);
        jobDataMap.put(Log_Folder_Path_Key,jobInfo.getLogFilePath());
        jobDataMap.put(Log_Size_Limit_Key, jobInfo.getFileMaxSizeInMB());
        jobDataMap.put(Log_File_Max_Count_Key, jobInfo.getFileMaxCount());
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("Log_Management_Trigger", "Log_Management_Trigger_Group")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInHours(Log_Management_Check_Interval_Hour)
                        .repeatForever())
                .build();
        logManagementJobService.updateLogManagementJobModel(new LogManagementJobModel(jobName, jobGroupName, jobID++,
                Log_Management_Check_Interval_Hour.longValue()));

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
            logger.info("Scheduling the Log Management job ...");
            LogManagementJobModel logManagementJobModel = logManagementJobService
                    .getLogManagementJobModelByJobNameAndGroup(jobName, jobGroupName);
            logManagementJobModel.setStartTime(new Date());
            logManagementJobModel.setRunning(true);
            scheduler.scheduleJob(job, trigger);
            logManagementJobService.updateLogManagementJobModel(logManagementJobModel);
        } catch (SchedulerException e) {
            logger.error("Failed to schedule the Log Management job ...", e);
            e.printStackTrace();
        }
    }


    // stop job and delete the Log Management job
    public void stopAndDeleteJob() throws SchedulerException {
        // stop the job
        scheduler.shutdown();
        // delete the job
        JobKey jobKey = new JobKey(jobName, jobGroupName);
        scheduler.deleteJob(jobKey);
        LogManagementJobModel logManagementJobModel = logManagementJobService
                .getLogManagementJobModelByJobNameAndGroup(jobName, jobGroupName);
        logManagementJobModel.setRunning(false);
        logManagementJobService.updateLogManagementJobModel(logManagementJobModel);
    }

    // Pause the job
    public void pauseJob() throws SchedulerException {
        JobKey jobKey = new JobKey(jobName, jobGroupName);
        scheduler.pauseJob(jobKey);
        LogManagementJobModel logManagementJobModel = logManagementJobService
                .getLogManagementJobModelByJobNameAndGroup(jobName, jobGroupName);
        logManagementJobModel.setRunning(false);
        logManagementJobService.updateLogManagementJobModel(logManagementJobModel);
    }

    // Resume the job
    public void resumeJob() throws SchedulerException {
        JobKey jobKey = new JobKey(jobName, jobGroupName);
        scheduler.resumeJob(jobKey);
        LogManagementJobModel logManagementJobModel = logManagementJobService
                .getLogManagementJobModelByJobNameAndGroup(jobName, jobGroupName);
        logManagementJobModel.setRunning(true);
        logManagementJobService.updateLogManagementJobModel(logManagementJobModel);
    }


}
