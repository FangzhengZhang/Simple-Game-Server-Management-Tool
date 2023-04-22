package cat.frank.SimpleGameServerManagementTool.sgsmtConfig;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ImportantDataRepository extends JpaRepository<ImportantDataModel, Long> {
    Long onlyId = 1L;
    Optional<ImportantDataModel> findById(Long id);
}
