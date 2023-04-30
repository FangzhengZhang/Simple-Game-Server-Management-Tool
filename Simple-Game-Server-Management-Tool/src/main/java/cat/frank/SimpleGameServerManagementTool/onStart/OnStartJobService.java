package cat.frank.SimpleGameServerManagementTool.onStart;

import cat.frank.SimpleGameServerManagementTool.ipCheck.IpCheckSchedulerService;
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


    @Autowired
    public OnStartJobService(SGSMTConfigService sgsmtConfigService,
                             IpCheckSchedulerService ipCheckSchedulerService) {
        this.sgsmtConfigService = sgsmtConfigService;
        this.ipCheckSchedulerService = ipCheckSchedulerService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onStart() {
        startInitLoadAndCheck();
        startIpCheckJob();
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
