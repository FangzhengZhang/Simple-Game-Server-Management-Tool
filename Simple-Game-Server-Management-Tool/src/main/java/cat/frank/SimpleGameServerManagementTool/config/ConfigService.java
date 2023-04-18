package cat.frank.SimpleGameServerManagementTool.config;


import jakarta.annotation.PostConstruct;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import cat.frank.SimpleGameServerManagementTool.utility.ApplicationShutdownManager;

@Configuration
public class ConfigService {
    //add logger
    private static final Logger logger = LoggerFactory.getLogger(ConfigService.class);

    // get the value from the application.properties file
    @Value("${sgsmt.root.path:}")
    private String appRootPath;

    @Value("${sgsmt.scripts.path:scripts}")
    private String scriptsPath;

    @Value("${sgsmt.info.file.path:informationFile}")
    private String infoFilePath;

    private ApplicationShutdownManager shutdownManager = null;
    private IPService ipService = null;
    private ImportantDataService importantDataService = null;
    // create a function for authored code to be called from the constructor
    @Autowired
    public void setShutdownManager(ApplicationShutdownManager shutdownManager,
                                   IPService ipService,
                                   ImportantDataService importantDataService) {
        this.shutdownManager = shutdownManager;
        this.ipService = ipService;
        this.importantDataService = importantDataService;
    }

    @PostConstruct
    public void initChecker(){

        if(appRootPath == null || appRootPath.isEmpty() ||appRootPath.equals("/")){
            logger.error("sgsmt.root.path is null or empty. Please start the application with StartInit.sh or set the Application Home.");
            // Shut down the spring application
            shutdownManager.initiateShutdown(1);
        }
        scriptsPath = appRootPath + scriptsPath;
        infoFilePath = appRootPath + infoFilePath;

        logger.info("appRootPath: " + appRootPath);
        logger.info("scriptsPath: " + scriptsPath);
        logger.info("infoFilePath: " + infoFilePath);

        checkIfPathExistsAndSaveThem();
        checkIfIpChanged();

    }

    private void checkIfIpChanged() {
        ipService.checkIfIpChanged();
    }

    /**
     * This function checks if the path exists and if not, if it is not exist, then it will create it.
     */
    private void checkIfPathExistsAndSaveThem() {

        if(!java.nio.file.Files.exists(java.nio.file.Paths.get(scriptsPath))){
            logger.error("Scripts path does not exist. Please check the completeness of the application.");
            shutdownManager.initiateShutdown(1);
        }
        ArrayList<String> paths = new ArrayList<>();
        paths.add(infoFilePath);

        for(String path : paths){
            if(!java.nio.file.Files.exists(java.nio.file.Paths.get(path))){
                try {
                    logger.info("Creating path: " + path);
                    java.nio.file.Files.createDirectories(java.nio.file.Paths.get(path));
                } catch (Exception e) {
                    logger.error("Error creating path: " + path);
                    e.printStackTrace();
                }
            }
        }

        importantDataService.saveImportantData(
                new ImportantDataModel(appRootPath, scriptsPath, infoFilePath, null));
    }
}
