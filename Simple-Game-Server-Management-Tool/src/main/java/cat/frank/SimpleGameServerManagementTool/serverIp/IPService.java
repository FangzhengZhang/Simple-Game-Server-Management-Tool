package cat.frank.SimpleGameServerManagementTool.serverIp;

import org.springframework.stereotype.Service;

@Service
public class IPService {

    @Autowired
    private CacheManager cacheManager;

    public void checkIfIpChanged() {
        // 
    }
}
