package cat.frank.SimpleGameServerManagementTool.background_onStart;

import cat.frank.SimpleGameServerManagementTool.background_hardwareInfo.SystemInfoService;
import cat.frank.SimpleGameServerManagementTool.background_ipCheck.IpCheckSchedulerService;
import cat.frank.SimpleGameServerManagementTool.background_logManagement.LogManagementSchedulerService;
import cat.frank.SimpleGameServerManagementTool.background_sgsmtConfig.SGSMTConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class OnStartJobService {
    // Logger for this class
    private static final Logger logger = LoggerFactory.getLogger(OnStartJobService.class);

    private final SGSMTConfigService sgsmtConfigService;
    private final IpCheckSchedulerService ipCheckSchedulerService;
    private final LogManagementSchedulerService logManagementSchedulerService;
    private final SystemInfoService systemInfoService;


    @Autowired
    public OnStartJobService(SGSMTConfigService sgsmtConfigService,
                             IpCheckSchedulerService ipCheckSchedulerService,
                             LogManagementSchedulerService logManagementSchedulerService,
                             SystemInfoService systemInfoService) {
        this.sgsmtConfigService = sgsmtConfigService;
        this.ipCheckSchedulerService = ipCheckSchedulerService;
        this.logManagementSchedulerService = logManagementSchedulerService;
        this.systemInfoService = systemInfoService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onStart() {
        logger.info("Application is ready to start!");
        checkIfAppIsFirstStart();
        startInitLoadAndCheck();
        startIpCheckJob();
        startLogManagementJob();
        initSystemInfo();
    }

    private void initSystemInfo(){
        try {
            systemInfoService.initSystemInfoModel();
        }catch (Exception e){
            logger.error("Failed to init system info! ", e);
            e.printStackTrace();
        }
    }


    private void startLogManagementJob() {
        try {
            logManagementSchedulerService.createLogManagementJob();
        } catch (Exception e) {
            logger.error("Failed to create and start log manager job! ", e);
            e.printStackTrace();
        }
    }

    private void startIpCheckJob() {
        try {
            ipCheckSchedulerService.createIpCheckJob();
        } catch (Exception e) {
            logger.error("Failed to create and start ip check job! ", e);
            e.printStackTrace();
        }
    }

    private void startInitLoadAndCheck() {
        try {
            sgsmtConfigService.initLoadAndCheck();
        } catch (Exception e) {
            logger.error("Failed to load and check application configuration! ", e);
            e.printStackTrace();
        }
    }

    private void checkIfAppIsFirstStart(){
        try{
            sgsmtConfigService.checkIfAppIsFirstStart();
        }catch (Exception e){
            logger.error("Failed to check if application is first start! ", e);
            e.printStackTrace();
        }
    }

}
