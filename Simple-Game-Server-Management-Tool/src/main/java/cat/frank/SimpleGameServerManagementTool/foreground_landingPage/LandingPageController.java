package cat.frank.SimpleGameServerManagementTool.foreground_landingPage;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class LandingPageController {

    @GetMapping("/welcome")
    public ResponseEntity<String> welcomePage(){
        return new ResponseEntity<>("Server says: welcome", HttpStatus.OK);
    }
}
