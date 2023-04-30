package cat.frank.SimpleGameServerManagementTool.logManagement;

import cat.frank.SimpleGameServerManagementTool.utility.DateTimeUtilityService;
import cat.frank.SimpleGameServerManagementTool.utility.FileScanCopyRenameZipService;
import cat.frank.SimpleGameServerManagementTool.utility.StaticVariables;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static cat.frank.SimpleGameServerManagementTool.utility.StaticVariables.*;

@Component
public class LogManagementJob implements Job {

    // Logger for the LogManagementJob class
    private final Logger logger = LoggerFactory.getLogger(LogManagementJob.class);

    private final LogManagementJobService logManagementJobService;
    private final FileScanCopyRenameZipService fileScanCopyRenameZipService;
    private final DateTimeUtilityService dateTimeUtilityService;

    @Autowired
    public LogManagementJob(LogManagementJobService logManagementJobService,
                            FileScanCopyRenameZipService fileScanCopyRenameZipService,
                            DateTimeUtilityService dateTimeUtilityService) {
        this.logManagementJobService = logManagementJobService;
        this.fileScanCopyRenameZipService = fileScanCopyRenameZipService;
        this.dateTimeUtilityService = dateTimeUtilityService;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        Long jobID = dataMap.getLong(StaticVariables.Log_Management_Job_ID_Key);
        String jobName = dataMap.getString(StaticVariables.Log_Management_Job_Name_Key);
        String logFolderPath = dataMap.getString(StaticVariables.Log_Folder_Path_Key);
        long logSizeLimit = dataMap.getLong(StaticVariables.Log_Size_Limit_Key)* 1024 * 1024;
        int fileMaxCount = dataMap.getInt(StaticVariables.Log_File_Max_Count_Key);

        logger.info("Job: {} with ID: {} is running ...", jobName, jobID);
        LogManagementJobModel logManagementJobModel = logManagementJobService.getLogManagementJobModelByJobID(jobID);
        Date currDate = new Date();
        logManagementJobModel.setPreviousFireTime(currDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(currDate);               // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, Log_Management_Check_Interval_Hour);
        logManagementJobModel.setNextFireTime(cal.getTime());
        doLogRotation(logFolderPath, logSizeLimit, fileMaxCount);
    }

    private void doLogRotation(String logFolderPath, long logSizeLimit, int fileMaxCount) {
        List<Path> logFiles = null;
        try {
            logFiles = fileScanCopyRenameZipService.findFilesTypeStartingWithAndSizeLagerThan(
                    logFolderPath, Log_File_Start_Name, Log_File_Type, logSizeLimit );
        } catch (Exception e) {
            logger.error("Cannot scan folder {}, Error while doing log rotation: {}", logFolderPath,e.getMessage());
        }

        if (logFiles != null && logFiles.size() > 0) {
            for (Path logFile : logFiles) {
                try {
                    Path pathOfLogFolder = Paths.get(logFolderPath);
                    fileScanCopyRenameZipService.renameZipDelete(
                            logFile,
                            pathOfLogFolder,
                            logFile.getFileName().toString() +"_"+
                                    dateTimeUtilityService.getCurrentYMDHTimeString());
                    fileScanCopyRenameZipService.deleteOldestFileIfMoreThan(
                            pathOfLogFolder,
                            Log_File_Start_Name,
                            Log_File_Zip_Type,
                            fileMaxCount);
                } catch (Exception e) {
                    logger.error("Cannot zip file: {}, Error while doing log rotation: {}", logFile, e.getMessage());
                }
            }
        }
    }


}
