package cat.frank.SimpleGameServerManagementTool.foreground_homePage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomePageHeadMessageRepository extends JpaRepository<HomePageHeadMessageModel,Long> {
}
