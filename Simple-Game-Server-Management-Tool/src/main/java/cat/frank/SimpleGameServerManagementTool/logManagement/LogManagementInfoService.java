package cat.frank.SimpleGameServerManagementTool.logManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static cat.frank.SimpleGameServerManagementTool.utility.StaticVariables.Only_Record_Key;

@Service
public class LogManagementInfoService {

    private final LogManagementInfoRepository logManagementInfoRepository;

    @Autowired
    public LogManagementInfoService(LogManagementInfoRepository logManagementInfoRepository) {
        this.logManagementInfoRepository = logManagementInfoRepository;
    }

    public void initLogManagementInfo(String logFilePath, Long fileMaxSizeInMB, Integer fileMaxCount) {
        logManagementInfoRepository.deleteAll();
        logManagementInfoRepository.save(new LogManagementInfoModel(Only_Record_Key, logFilePath, fileMaxSizeInMB,
                fileMaxCount));
    }

    @Cacheable("LogManagementInfoModel")
    public LogManagementInfoModel getLogManagementInfo() {
        return logManagementInfoRepository.findById(Only_Record_Key).orElse(null);
    }

    @Cacheable("LogManagementInfoModel")
    public void updateLogManagementInfo(LogManagementInfoModel logManagementInfoModel) {
        logManagementInfoRepository.save(logManagementInfoModel);
    }

}
