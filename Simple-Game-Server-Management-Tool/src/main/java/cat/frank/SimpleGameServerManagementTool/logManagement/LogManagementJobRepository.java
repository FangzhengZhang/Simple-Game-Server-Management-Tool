package cat.frank.SimpleGameServerManagementTool.logManagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LogManagementJobRepository extends JpaRepository<LogManagementJobModel, Long> {
    Optional<LogManagementJobModel> findByJobID(Long jobID);

    Optional<LogManagementJobModel> findByJobNameAndJobGroup(String jobName, String jobGroup);
}
