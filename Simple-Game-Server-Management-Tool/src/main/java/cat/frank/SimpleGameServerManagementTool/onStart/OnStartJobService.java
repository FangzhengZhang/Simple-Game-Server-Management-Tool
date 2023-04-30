package cat.frank.SimpleGameServerManagementTool.onStart;

import cat.frank.SimpleGameServerManagementTool.ipCheck.IpCheckSchedulerService;
import cat.frank.SimpleGameServerManagementTool.logManagement.LogManagementSchedulerService;
import cat.frank.SimpleGameServerManagementTool.sgsmtConfig.SGSMTConfigService;
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


    @Autowired
    public OnStartJobService(SGSMTConfigService sgsmtConfigService,
                             IpCheckSchedulerService ipCheckSchedulerService,
                             LogManagementSchedulerService logManagementSchedulerService) {
        this.sgsmtConfigService = sgsmtConfigService;
        this.ipCheckSchedulerService = ipCheckSchedulerService;
        this.logManagementSchedulerService = logManagementSchedulerService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onStart() {
        startInitLoadAndCheck();
        startIpCheckJob();
        startLogManagementJob();
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


}
