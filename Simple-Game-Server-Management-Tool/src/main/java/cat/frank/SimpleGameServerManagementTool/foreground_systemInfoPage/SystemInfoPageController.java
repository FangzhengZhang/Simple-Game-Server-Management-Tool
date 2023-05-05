package cat.frank.SimpleGameServerManagementTool.foreground_systemInfoPage;

import cat.frank.SimpleGameServerManagementTool.background_hardwareInfo.SystemInfoModel;
import cat.frank.SimpleGameServerManagementTool.utility.StaticUrls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(StaticUrls.SGSMT_SYS_INFO_BASE_URL )
public class SystemInfoPageController {

    private final SystemInfoPageService systemInfoPageService;

    @Autowired
    public SystemInfoPageController(SystemInfoPageService systemInfoPageService) {
        this.systemInfoPageService = systemInfoPageService;
    }

    @GetMapping(StaticUrls.GET_SYS_INFO_URL_PATH)
    public ResponseEntity<SystemInfoModel> getSystemInfo(){
        return new ResponseEntity<>(systemInfoPageService.getSystemHardwareInfo(), HttpStatus.OK);
    }
}
