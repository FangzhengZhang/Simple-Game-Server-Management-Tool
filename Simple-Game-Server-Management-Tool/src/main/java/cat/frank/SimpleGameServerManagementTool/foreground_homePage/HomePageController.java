package cat.frank.SimpleGameServerManagementTool.foreground_homePage;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.util.List;

import static cat.frank.SimpleGameServerManagementTool.utility.StaticUrls.GET_HOMEPAGE_HEAD_MSG_PATH;
import static cat.frank.SimpleGameServerManagementTool.utility.StaticUrls.SGSMT_HOMEPAGE_BASE_URL;

@RestController()
@RequestMapping(SGSMT_HOMEPAGE_BASE_URL )
public class HomePageController {

    private final HomePageService homePageService;

    public HomePageController(HomePageService homePageService) {
        this.homePageService = homePageService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> welcomePage(){
        return new ResponseEntity<>("Server says: welcome", HttpStatus.OK);
    }

    @GetMapping(GET_HOMEPAGE_HEAD_MSG_PATH)
    public ResponseEntity<List<HomePageHeadMessageModel>> headMessage(){
        List<HomePageHeadMessageModel> allHomePageHeadMessage = homePageService.getAllHomePageHeadMessage();
        return new ResponseEntity<>(allHomePageHeadMessage, HttpStatus.OK);
    }


}
