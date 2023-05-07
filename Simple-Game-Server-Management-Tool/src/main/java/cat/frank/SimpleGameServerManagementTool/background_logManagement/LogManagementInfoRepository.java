package cat.frank.SimpleGameServerManagementTool.background_logManagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogManagementInfoRepository extends JpaRepository<LogManagementInfoModel, Long> {

}
