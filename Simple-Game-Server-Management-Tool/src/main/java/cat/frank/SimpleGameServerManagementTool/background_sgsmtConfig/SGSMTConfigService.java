package cat.frank.SimpleGameServerManagementTool.background_sgsmtConfig;


import cat.frank.SimpleGameServerManagementTool.background_ipCheck.IPService;
import cat.frank.SimpleGameServerManagementTool.background_logManagement.LogManagementInfoService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import cat.frank.SimpleGameServerManagementTool.utility.ApplicationShutdownManager;
import org.springframework.stereotype.Service;

@Service
public class SGSMTConfigService {
    //add logger
    private static final Logger logger = LoggerFactory.getLogger(SGSMTConfigService.class);

    // get the value from the application.properties file
    @Value("${sgsmt.root.path:}")
    private String appRootPath;

    @Value("${sgsmt.scripts.path:scripts}")
    private String scriptsPath;

    @Value("${sgsmt.info.file.path:informationFile}")
    private String infoFilePath;

    @Value("${sgsmt.email.receiver:null}")
    private String emailReceiver;

    @Value("${sgsmt.email.enabled:false}")
    private boolean isEmailEnabled;

    @Value("${spring.mail.username:null}")
    private String emailSender;

    @Value("${sgsmt.log.path:logs}")
    private String logFolderPath= null;

    @Value("${logging.rotate.max.size.MB:10}")
    private Long fileMaxSizeInMB = 10L;

    @Value("${logging.rotate.max.history:10}")
    private Integer fileMaxCount = 10;


    private final ApplicationShutdownManager shutdownManager;
    private final IPService ipService;
    private final ImportantDataService importantDataService;

    private final LogManagementInfoService logManagementInfoService;

    // create a function for authored code to be called from the constructor
    @Autowired
    public SGSMTConfigService(ApplicationShutdownManager shutdownManager,
                                   IPService ipService,
                                   ImportantDataService importantDataService,
                                   LogManagementInfoService logManagementInfoService) {
        this.shutdownManager = shutdownManager;
        this.ipService = ipService;
        this.importantDataService = importantDataService;
        this.logManagementInfoService = logManagementInfoService;
    }

    public void initLoadAndCheck(){

        if(appRootPath == null || appRootPath.isEmpty() ||appRootPath.equals("/")){
            logger.error("sgsmt.root.path is null or empty. Please start the application with StartInit.sh or set the Application Home.");
            // Shut down the spring application
            shutdownManager.initiateShutdown(1);
        }
        scriptsPath = appRootPath + scriptsPath;
        infoFilePath = appRootPath + infoFilePath;
        logFolderPath = appRootPath + logFolderPath;

        logger.info("appRootPath: " + appRootPath);
        logger.info("scriptsPath: " + scriptsPath);
        logger.info("infoFilePath: " + infoFilePath);
        logger.info("logFolderPath: " + logFolderPath);

        checkIfPathExistsAndSaveThem();
        checkIfIpChanged();
        logManagementInfoService.initLogManagementInfo(logFolderPath, fileMaxSizeInMB, fileMaxCount);

    }

    private void checkIfIpChanged() {
        ipService.checkIfIpChanged();
    }

    /**
     * This function checks if the path exists and if not, if it is not exist, then it will create it.
     */
    private void checkIfPathExistsAndSaveThem() {

        if(!Files.exists(Paths.get(scriptsPath))){
            logger.error("Scripts path does not exist. Please check the completeness of the application.");
            shutdownManager.initiateShutdown(1);
        }
        ArrayList<String> paths = new ArrayList<>();
        paths.add(infoFilePath);

        for(String path : paths){
            Path path1 = Paths.get(path);
            if(!Files.exists(path1)){
                try {
                    logger.info("Creating path: " + path);
                    Files.createDirectories(path1);
                } catch (Exception e) {
                    logger.error("Error creating path: " + path);
                    e.printStackTrace();
                }
            }
        }

        importantDataService.saveImportantData(
                new ImportantDataModel(appRootPath, scriptsPath, infoFilePath, logFolderPath,emailReceiver,
                        isEmailEnabled,null, emailSender));
    }
}
