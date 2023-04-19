package cat.frank.SimpleGameServerManagementTool.utility;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    //logger for this class
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    private JavaMailSender javaMailSender;
    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public void sendEmail(String to, String subject, String content) {
        logger.info("send email to: {}, subject: {}, content: {}", to, subject, content);
        MimeMessage message = null;
        MimeMessageHelper helper;
        try{
            message = javaMailSender.createMimeMessage();
            helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            logger.error("send email failed", e);
        }finally {
            logger.info("send email finished");

        }
    }
}
