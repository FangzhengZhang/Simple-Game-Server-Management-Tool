package cat.frank.SimpleGameServerManagementTool.onStart;

import cat.frank.SimpleGameServerManagementTool.ipCheck.IpCheckJobService;
import cat.frank.SimpleGameServerManagementTool.ipCheck.IpCheckSchedulerService;
import jakarta.annotation.PostConstruct;
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

    private final IpCheckSchedulerService ipCheckSchedulerService;

    @Autowired
    public OnStartJobService(IpCheckSchedulerService ipCheckSchedulerService) {
        this.ipCheckSchedulerService = ipCheckSchedulerService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onStart() {
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
}
