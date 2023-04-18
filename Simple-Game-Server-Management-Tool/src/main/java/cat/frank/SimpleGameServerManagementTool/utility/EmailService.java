package cat.frank.SimpleGameServerManagementTool.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    //logger for this class
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    public void sendEmail(String to, String subject, String content) {
        logger.info("send email to: {}, subject: {}, content: {}", to, subject, content);

    }
}
