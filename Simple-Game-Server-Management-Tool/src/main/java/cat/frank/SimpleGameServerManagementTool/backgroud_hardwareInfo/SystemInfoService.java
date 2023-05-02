package cat.frank.SimpleGameServerManagementTool.backgroud_hardwareInfo;

import cat.frank.SimpleGameServerManagementTool.backgroud_sgsmtConfig.ImportantDataModel;
import cat.frank.SimpleGameServerManagementTool.backgroud_sgsmtConfig.ImportantDataService;
import cat.frank.SimpleGameServerManagementTool.utility.ShellScriptService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static cat.frank.SimpleGameServerManagementTool.utility.StaticVariables.Get_System_Info_Script_Name;
import static cat.frank.SimpleGameServerManagementTool.utility.StaticVariables.Only_Record_Key;

@Service
public class SystemInfoService {

    // logger for this class
    private static final Logger logger = LoggerFactory.getLogger(SystemInfoService.class);
    private final SystemInfoRepository systemInfoRepository;
    private final ImportantDataService importantDataService;

    public SystemInfoService(SystemInfoRepository systemInfoRepository,
                             ImportantDataService importantDataService) {
        this.systemInfoRepository = systemInfoRepository;
        this.importantDataService = importantDataService;
    }

    @Cacheable("systemInfoModel")
    public SystemInfoModel getSystemInfoModel() {
        return systemInfoRepository.findById(Only_Record_Key).orElse(null);
    }

    @Cacheable("systemInfoModel")
    public void saveSystemInfoModel(SystemInfoModel systemInfoModel) {
        systemInfoRepository.save(systemInfoModel);
    }

    public void initSystemInfoModel() {
        systemInfoRepository.deleteAll();
        ImportantDataModel importantDataModel =  importantDataService.getImportantData();
        // Get Json result from shell script
        String jsonResult = null;
        try {
            jsonResult = ShellScriptService.runShellScript(importantDataModel.getScriptsPath()
                    +Get_System_Info_Script_Name);
        } catch (Exception e) {
            logger.error("Failed to run script: {} to get system info. error is:{}",
                    importantDataModel.getScriptsPath()+Get_System_Info_Script_Name,
                    e.getMessage());
            e.printStackTrace();
        }
        if(jsonResult == null) {
            logger.error("Failed to get system info.");
            return;
        }
        Gson gson = new Gson();
        SystemInfoModel systemInfoModel = gson.fromJson(jsonResult, SystemInfoModel.class);
        systemInfoModel.setId(Only_Record_Key);
        systemInfoRepository.save(systemInfoModel);
    }
}
