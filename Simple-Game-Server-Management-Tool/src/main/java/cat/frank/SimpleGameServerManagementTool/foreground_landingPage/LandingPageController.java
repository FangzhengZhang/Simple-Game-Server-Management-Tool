package cat.frank.SimpleGameServerManagementTool.foreground_landingPage;

import cat.frank.SimpleGameServerManagementTool.utility.StaticUrls;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import static cat.frank.SimpleGameServerManagementTool.utility.StaticUrls.SGSMT_WELCOME_BASE_URL;

@RestController()
@RequestMapping(SGSMT_WELCOME_BASE_URL )
public class LandingPageController {

    @GetMapping()
    public ResponseEntity<String> welcomePage(){
        return new ResponseEntity<>("Server says: welcome", HttpStatus.OK);
    }
}
