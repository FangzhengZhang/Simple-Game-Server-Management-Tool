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

    @Autowired
    ApplicationShutdownManager shutdownManager = new ApplicationShutdownManager();

    //add logger
    private static final Logger logger = LoggerFactory.getLogger(ConfigService.class);

    @Value("${sgsmt.root.path:}")
    private String appRootPath;

    @Value("${sgsmt.scripts.path:scripts}")
    private String scriptsPath;

    @Value("${sgsmt.info.file.path:informationFile}")
    private String infoFilePath;


    @PostConstruct
    public void initChecker(){

        if(appRootPath == null || appRootPath.isEmpty() ||appRootPath.equals("/")){
            logger.error("sgsmt.root.path is null or empty. Please set it in the application.properties file.");
            // Shut down the spring appliction
            shutdownManager.initiateShutdown(1);
        }
        scriptsPath = appRootPath + scriptsPath;
        infoFilePath = appRootPath + infoFilePath;

        logger.info("appRootPath: " + appRootPath);
        logger.info("scriptsPath: " + scriptsPath);
        logger.info("infoFilePath: " + infoFilePath);

        checkIfPathExists();
        
    }

    /**
     * This function checks if the path exists and if not, if it is not exist, then it will create it.
     */
    private void checkIfPathExists() {
        ArrayList<String> paths = new ArrayList<>();
        paths.add(appRootPath);
        paths.add(scriptsPath);
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
    }

}
