package cat.frank.SimpleGameServerManagementTool.foreground_systemInfoPage;

import cat.frank.SimpleGameServerManagementTool.background_hardwareInfo.SystemInfoModel;
import cat.frank.SimpleGameServerManagementTool.background_hardwareInfo.SystemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemInfoPageService {

    private final SystemInfoService systemInfoService;

    @Autowired
    public SystemInfoPageService(SystemInfoService systemInfoService) {
        this.systemInfoService = systemInfoService;
    }

    public SystemInfoModel getSystemHardwareInfo() {
        return systemInfoService.getSystemInfoModel();
    }

}
