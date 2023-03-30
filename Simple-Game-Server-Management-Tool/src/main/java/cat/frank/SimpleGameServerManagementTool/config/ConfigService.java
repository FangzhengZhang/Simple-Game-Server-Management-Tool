package cat.frank.SimpleGameServerManagementTool.config;


import jakarta.annotation.PostConstruct;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Configuration;

import cat.frank.SimpleGameServerManagementTool.serverIp.IPService;
import cat.frank.SimpleGameServerManagementTool.utility.ApplicationShutdownManager;

@Configuration
public class ConfigService {

    @Autowired
    ApplicationShutdownManager shutdownManager = new ApplicationShutdownManager();

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    IPService ipService = new IPService();

    //add logger
    private static final Logger logger = LoggerFactory.getLogger(ConfigService.class);

    @Value("${sgsmt.root.path:../}")
    private String appRootPath;

    @Value("${sgsmt.scripts.path:scripts}")
    private String scriptsPath;

    @Value("${sgsmt.info.file.path:informationFile}")
    private String infoFilePath;


    @PostConstruct
    public void initChecker(){

        if(appRootPath == null || appRootPath.isEmpty() ||appRootPath.equals("/")){
            logger.error("sgsmt.root.path is null or empty. Please start the application with StartInit.sh or set the Application Home.");
            // Shut down the spring appliction
            shutdownManager.initiateShutdown(1);
        }
        scriptsPath = appRootPath + scriptsPath;
        infoFilePath = appRootPath + infoFilePath;

        logger.info("appRootPath: " + appRootPath);
        logger.info("scriptsPath: " + scriptsPath);
        logger.info("infoFilePath: " + infoFilePath);

        checkIfPathExists();

        Cache cache = cacheManager.getCache("PathCache");
        cache.put("sgsmt.root.path", appRootPath);
        cache.put("sgsmt.scripts.path", scriptsPath);
        cache.put("sgsmt.info.file.path", infoFilePath);

        checkIfIpChanged();
        
    }

    private void checkIfIpChanged() {
        ipService.checkIfIpChanged();
    }

    /**
     * This function checks if the path exists and if not, if it is not exist, then it will create it.
     */
    private void checkIfPathExists() {
        
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
    }

    

}
