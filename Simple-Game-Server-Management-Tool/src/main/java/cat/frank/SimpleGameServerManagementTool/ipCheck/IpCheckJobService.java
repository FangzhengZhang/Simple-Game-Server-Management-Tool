package cat.frank.SimpleGameServerManagementTool.ipCheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class IpCheckJobService {
    private final IpCheckJobRepository ipCheckJobRepository;

    @Autowired
    public IpCheckJobService(IpCheckJobRepository ipCheckJobRepository) {
        this.ipCheckJobRepository = ipCheckJobRepository;
    }

    @Cacheable("ipCheckJobModel")
    public IpCheckJobModel getIpCheckJobModelByJobID(Long jobID) {
        return ipCheckJobRepository.findByJobID(jobID);
    }

    @Cacheable("ipCheckJobModel")
    public IpCheckJobModel getIpCheckJobModelByJobNameAndGroup(String jobName, String jobGroup) {
        return ipCheckJobRepository.findByJobNameAndJobGroup(jobName, jobGroup);
    }

    @CachePut("ipCheckJobModel")
    public void updateIpCheckJobModel(IpCheckJobModel ipCheckJobModel) {
        ipCheckJobRepository.save(ipCheckJobModel);
    }
}
