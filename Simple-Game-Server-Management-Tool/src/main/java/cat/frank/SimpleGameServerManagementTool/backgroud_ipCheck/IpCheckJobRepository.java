package cat.frank.SimpleGameServerManagementTool.backgroud_ipCheck;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IpCheckJobRepository extends JpaRepository<IpCheckJobModel, Long> {
    IpCheckJobModel findByJobNameAndJobGroup(String jobName, String jobGroup);
    IpCheckJobModel findByJobID(Long jobID);
}
