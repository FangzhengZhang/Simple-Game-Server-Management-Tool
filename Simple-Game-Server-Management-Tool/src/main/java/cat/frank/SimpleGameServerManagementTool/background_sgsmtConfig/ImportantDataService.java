/**
 * This is the service class for the important data.
 */

package cat.frank.SimpleGameServerManagementTool.background_sgsmtConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ImportantDataService {
    private final ImportantDataRepository importantDataRepository;

    @Autowired
    public ImportantDataService(ImportantDataRepository importantDataRepository) {
        this.importantDataRepository = importantDataRepository;
    }

    public Long getTotalNumRecords() {
        return importantDataRepository.count();
    }

    @Cacheable("ImportantDataModel")
    public ImportantDataModel getImportantData() {
        return importantDataRepository.findById(ImportantDataRepository.onlyId).get();
    }
    @CachePut("ImportantDataModel")
    public void saveImportantData(ImportantDataModel importantDataModel) {
        //importantDataModel.setId(ImportantDataRepository.onlyId);
        importantDataRepository.save(importantDataModel);
    }
}
