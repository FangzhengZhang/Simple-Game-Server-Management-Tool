package cat.frank.SimpleGameServerManagementTool.utility;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class DateTimeUtilityService {

    public String getCurrentYMDHTimeString() {

        // Get the current date and time using LocalDateTime
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Define a DateTimeFormatter for the desired output format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH");

        // Format the current date and time as a string
        return currentDateTime.format(formatter);
    }
}
