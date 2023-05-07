package cat.frank.SimpleGameServerManagementTool.background_logManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LogManagementJobService {
    private final LogManagementJobRepository logManagementJobRepository;

    @Autowired
    public LogManagementJobService(LogManagementJobRepository logManagementJobRepository) {
        this.logManagementJobRepository = logManagementJobRepository;
    }
    @Cacheable("logManagementJobModel")
    public LogManagementJobModel getLogManagementJobModelByJobID(Long jobID) {
        Optional<LogManagementJobModel> logJobRecord = 
                    logManagementJobRepository.findByJobID(jobID);
        return logJobRecord.orElse(null);
    }

    @Cacheable("logManagementJobModel")
    public LogManagementJobModel getLogManagementJobModelByJobNameAndGroup(String jobName, String jobGroup) {
        Optional<LogManagementJobModel> logJobRecord =
                    logManagementJobRepository.findByJobNameAndJobGroup(jobName, jobGroup);
        return logJobRecord.orElse(null);
    }

    @CachePut("logManagementJobModel")
    public void updateLogManagementJobModel(LogManagementJobModel logManagementJobModel) {
        logManagementJobRepository.save(logManagementJobModel);
    }

}
