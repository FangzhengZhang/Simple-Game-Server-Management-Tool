package cat.frank.SimpleGameServerManagementTool.backgroud_sgsmtConfig;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ImportantDataRepository extends JpaRepository<ImportantDataModel, Long> {
    Long onlyId = 1L;
    Optional<ImportantDataModel> findById(Long id);
}
