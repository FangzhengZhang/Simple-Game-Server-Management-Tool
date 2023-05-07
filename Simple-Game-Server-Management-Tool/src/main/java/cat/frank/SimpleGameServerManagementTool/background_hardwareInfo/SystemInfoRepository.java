package cat.frank.SimpleGameServerManagementTool.background_hardwareInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemInfoRepository extends JpaRepository<SystemInfoModel, Long> {

}
