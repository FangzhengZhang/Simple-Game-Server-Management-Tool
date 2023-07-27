package cat.frank.SimpleGameServerManagementTool.background_sgsmtConfig;

import cat.frank.SimpleGameServerManagementTool.foreground_homePage.HomePageHeadMessageModel;
import cat.frank.SimpleGameServerManagementTool.foreground_homePage.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class DBFirstStartInitService {
    private final HomePageService homePageService;

    @Autowired
    public DBFirstStartInitService(HomePageService homePageService) {
        this.homePageService = homePageService;
    }

    public void initDB() {
        initHomePageHeadMessage();
    }

    private void initHomePageHeadMessage() {
        homePageService.addNewMessage("Welcome to Simple Game Server Management Tool!");
        homePageService.addNewMessage("If you want your server available to the public, " +
                "please make sure you set port forward on your router and firewall.");
    }


}
