package cat.frank.SimpleGameServerManagementTool.foreground_systemInfoPage;

import cat.frank.SimpleGameServerManagementTool.backgroud_hardwareInfo.SystemInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/systemInfo")
public class SystemInfoPageController {

    private final SystemInfoPageService systemInfoPageService;

    @Autowired
    public SystemInfoPageController(SystemInfoPageService systemInfoPageService) {
        this.systemInfoPageService = systemInfoPageService;
    }

    @GetMapping("/getSystemInfo")
    public ResponseEntity<SystemInfoModel> getSystemInfo(){
        return new ResponseEntity<>(systemInfoPageService.getSystemHardwareInfo(), HttpStatus.OK);
    }
}
