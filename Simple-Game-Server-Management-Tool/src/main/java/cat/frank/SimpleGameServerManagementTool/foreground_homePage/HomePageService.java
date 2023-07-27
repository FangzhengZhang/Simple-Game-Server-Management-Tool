package cat.frank.SimpleGameServerManagementTool.foreground_homePage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomePageService {
    HomePageHeadMessageRepository homePageHeadMessageRepository;

    @Autowired
    public HomePageService(HomePageHeadMessageRepository homePageHeadMessageRepository) {
        this.homePageHeadMessageRepository = homePageHeadMessageRepository;
    }

    public List<HomePageHeadMessageModel> getAllHomePageHeadMessage() {
        return homePageHeadMessageRepository.findAll();
    }

    public void saveHomePageHeadMessage(HomePageHeadMessageModel homePageHeadMessageModel) {
        homePageHeadMessageRepository.save(homePageHeadMessageModel);
    }

    public void deleteHomePageHeadMessage(HomePageHeadMessageModel homePageHeadMessageModel) {
        homePageHeadMessageRepository.delete(homePageHeadMessageModel);
    }

    public void addNewMessage(String msg){
        HomePageHeadMessageModel homePageHeadMessageModel = new HomePageHeadMessageModel();
        homePageHeadMessageModel.setMessage(msg);
        this.saveHomePageHeadMessage(homePageHeadMessageModel);
    }


}
