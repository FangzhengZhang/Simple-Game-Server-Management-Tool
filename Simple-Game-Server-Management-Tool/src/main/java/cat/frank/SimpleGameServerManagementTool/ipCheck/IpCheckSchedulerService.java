package cat.frank.SimpleGameServerManagementTool.ipCheck;

import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

@Service
public class IpCheckSchedulerService {
    private Scheduler scheduler = null;

    public IpCheckSchedulerService() {
        scheduler = new StdSchedulerFactory().getScheduler();
    }

}
